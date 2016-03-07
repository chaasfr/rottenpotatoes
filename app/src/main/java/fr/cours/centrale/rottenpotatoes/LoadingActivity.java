package fr.cours.centrale.rottenpotatoes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import fr.cours.centrale.rottenpotatoes.event.Event;
import fr.cours.centrale.rottenpotatoes.event.EventWrapped;
import fr.cours.centrale.rottenpotatoes.film.Film;
import fr.cours.centrale.rottenpotatoes.film.FilmSeanceList;
import fr.cours.centrale.rottenpotatoes.seance.Seance;

public class LoadingActivity extends Activity {

    private static String TAG = LoadingActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    private String urlEvent = "http://centrale.corellis.eu/events.json";
    private String urlFilmSeances = "http://centrale.corellis.eu/filmseances.json";
    private String urlProchainement = "http://centrale.corellis.eu/prochainement.json";
    private String urlSeances = "http://centrale.corellis.eu/seances.json";

    private boolean prochainementsRequestIsDone= false;
    private boolean eventsRequestIsDone= false;
    private boolean filmSeancesRequestIsDone= false;
    private boolean seancesRequestIsDone= false;

    private DBHelper rottenDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        showpDialog();
        rottenDB = new DBHelper(this);

        if(!isDBUpToDate()){
            if(isOnline()) {
                makeProchainementRequest();
                makeSeancesRequest();
                makeFilmSeancesRequest();
                makeEventRequest();
            }
            else noConnectionMessage();
        }
        else showMainActivity();
    }
    @Override
    protected void onResume(){
        super.onResume();
        //showMainActivity();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private boolean isDBUpToDate(){
        /**
        * On regarde uniquement si la DB existe sur le téléphone, puisqu'elle n'est jamais mise à jour sur centrale.corellis.eu.
         * Si on voulait implémenter un versionning, ce serait ici: on comparerait la version de centrale.corellis.eu à celle de rottenDB.
         **/
        boolean dbUpToDate = false;
        String destPath = "/data/data/" + getPackageName()
                + "/databases/" + DBHelper.DATABASE_NAME;
        File f = new File(destPath);
        if (f.exists()){
            dbUpToDate = rottenDB.numberOfFilms() > 1 ;
        }
        return dbUpToDate;
    }

    /**
     * Seule la requête pour "prochainement" retourne un JSONObject, les 3 autres retournent des JSONArray.
     * */
    private void makeProchainementRequest() {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    urlProchainement, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(final JSONObject response) {
                    new Thread(new Runnable() {
                        public void run() {
                            Gson gson = new Gson();
                            FilmSeanceList listFilmProchainement = gson.fromJson(response.toString(), FilmSeanceList.class);
                            if (listFilmProchainement != null) {
                                for (int i = 0; i < listFilmProchainement.getFilmsSeanceList().size(); i++) {
                                    Film film = listFilmProchainement.getFilmsSeanceList().get(i);
                                    addFilmToDB(film, true, false); //true correspond à is_prochainement
                                }
                            }
                            prochainementsRequestIsDone = true;
                            if (checkRequestsAreDone()) showMainActivity();
                        }
                    }).start();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            "failed to retrieve Upcomming Films", Toast.LENGTH_SHORT).show();
                }
            });
            // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void makeSeancesRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlSeances, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                new Thread(new Runnable() {
                    public void run() {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                        for (JsonElement obj : jArray) {
                            Seance seance = gson.fromJson(obj, Seance.class);
                            addSeanceToDB(seance);

                        }
                        seancesRequestIsDone = true;
                        if (checkRequestsAreDone()) showMainActivity();
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve seances", Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void makeFilmSeancesRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlFilmSeances, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                new Thread(new Runnable() {
                    public void run() {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                        for (JsonElement obj : jArray) {
                            Film film = gson.fromJson(obj, Film.class);
                            addFilmToDB(film, false, true); // false car le film n'est pas "prochainement"
                        }
                        filmSeancesRequestIsDone = true;
                        if (checkRequestsAreDone()) showMainActivity();
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Films", Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void makeEventRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlEvent, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                new Thread(new Runnable() {
                    public void run() {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                        for(JsonElement obj : jArray )
                        {
                            EventWrapped eventWrapped = gson.fromJson( obj , EventWrapped.class);
                            if(eventWrapped.getEvents() != null){
                                for(int i = 0; i< eventWrapped.getEvents().size(); i++)
                                {
                                    Event event= eventWrapped.getEvents().get(i);
                                    addEventToDB(event, eventWrapped.getTitre(),eventWrapped.getType());
                                }
                            }
                        }
                        eventsRequestIsDone=true;
                        if(checkRequestsAreDone()) showMainActivity();
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Events", Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void addFilmToDB(Film film, boolean is_Prochainement, boolean is_alafiche){
        Gson gson = new Gson();
        if(!rottenDB.checkIsFilmAlreadyInDb(film.getId())) {
            String media = gson.toJson(film.getMedias());
            String video = gson.toJson(film.getVideos());
            rottenDB.insertFilm(film.getId(), film.getTitre(), film.getTitre_ori(), film.getAffiche(), film.getWeb(), film.getDuree(), film.getDistributeur(), film.getParticipants(),
                    film.getRealisateur(), film.getSynopsis(), film.getAnnee(), film.getDate_sortie(), film.getInfo(), film.getIs_visible(), film.getIs_vente(), film.getGenreid(),
                    film.getCategorieid(), film.getGenre(), film.getCategorie(), film.getReleaseNumber(), film.getPays(), film.getShare_url(), media, video,
                    film.getIs_avp(), film.getIs_alaune(), film.getIs_lastWeek(), is_Prochainement, is_alafiche);
        } else {
            if (is_Prochainement) {
                rottenDB.updateProchainement(film.getId());
            } else if (is_alafiche) rottenDB.updateAlaffiche(film.getId());
        }
    }

    private void addEventToDB(Event event, String titre_wrapped, String type_wrapped){
        String films= event.getFilmsIdAsString();
        //Rajoute les films qui ne sont que dans des événements à la DB
        for(int i = 0;i<event.getFilms().size();i++){
            addFilmToDB(event.getFilms().get(i),false,false);
        }
        rottenDB.insertEvent(event.getId(),event.getTitre(),event.getSoustitre(),event.getAffiche(),event.getDescription(),event.getVad_condition(),
                event.getPartenaire(),event.getDate_deb(),event.getDate_fin(),event.getHeure(),event.getContact(),event.getWeb_label(),event.getEvenementtypeid(),
                films, type_wrapped,titre_wrapped);
    }

    private void addSeanceToDB(Seance seance){
        rottenDB.insertSeance(seance.getId(), seance.getActual_date(), seance.getShow_time(), seance.getIs_troisd(), seance.getIs_malentendant(), seance.getIs_handicape(),
                seance.getNationality(), seance.getCinemaid(), seance.getFilmid(), seance.getTitre(), seance.getCategorieid(), seance.getPerformanceid(), seance.getCinema_salle());
    }

    private Boolean checkRequestsAreDone(){
        return (prochainementsRequestIsDone && eventsRequestIsDone && filmSeancesRequestIsDone && seancesRequestIsDone);
    }

    private void showMainActivity(){
        hidepDialog();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void noConnectionMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No internet connection. You can edit your preferences, but you need internet to load the movies!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showMainActivity();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
