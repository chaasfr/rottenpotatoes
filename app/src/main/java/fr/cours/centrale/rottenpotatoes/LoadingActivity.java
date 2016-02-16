package fr.cours.centrale.rottenpotatoes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class LoadingActivity extends Activity {

    private static String TAG = LoadingActivity.class.getSimpleName();

    // Progress dialog
    private ProgressDialog pDialog;

    private String urlEvent = "http://centrale.corellis.eu/events.json";
    private String urlFilmSeances = "http://centrale.corellis.eu/filmseances.json";
    private String urlProchainement = "http://centrale.corellis.eu/prochainement.json";
    private String urlSeances = "http://centrale.corellis.eu/seances.json";

    private String listFilmProchainementSerialized = null;
    private String listSeanceSerialized = null;
    private String listFilmsSerialized = null;
    private String listEventWrappedSerialized = null;

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

        deleteCurrentDB();

        makeProchainementRequest();
        makeSeancesRequest();
        makeFilmSeancesRequest();
        makeEventRequest();
    }

    private void deleteCurrentDB(){
        String destPath = "/data/data/" + getPackageName()
                + "/databases/" + DBHelper.DATABASE_NAME;
        File f = new File(destPath);
        if (f.exists()) f.delete();
    }

    /**
     * Seule la requête pour "prochainement" retourne un JSONObject, les 3 autres retournent des JSONArray.
     * */
    private void makeProchainementRequest() {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    urlProchainement, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Gson gson = new Gson();
                    FilmSeanceList listFilmProchainement=gson.fromJson(response.toString(), FilmSeanceList.class);
                    if(listFilmProchainement != null) {
                        for (int i = 0; i < listFilmProchainement.getFilmsSeanceList().size(); i++) {
                            Film film = listFilmProchainement.getFilmsSeanceList().get(i);
                            if(!rottenDB.checkIsFilmAlreadyInDb(film.getId())) {
                                String media = film.getMediasAsString();
                                String video = film.getVideosAsString();
                                rottenDB.insertFilm(film.getId(), film.getTitre(), film.getTitre_ori(), film.getAffiche(), film.getWeb(), film.getDuree(), film.getDistributeur(), film.getParticipants(),
                                        film.getRealisateur(), film.getSynopsis(), film.getAnnee(), film.getDate_sortie(), film.getInfo(), film.getIs_visible(), film.getIs_vente(), film.getGenreid(),
                                        film.getCategorieid(), film.getGenre(), film.getCategorie(), film.getReleaseNumber(), film.getPays(), film.getShare_url(), media, video,
                                        film.getIs_avp(), film.getIs_alaune(), film.getIs_lastWeek(), true);
                            } else rottenDB.updateProchainement(film.getId());
                        }
                    }
                    prochainementsRequestIsDone=true;
                    if(checkRequestsAreDone()) showMainActivity();
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
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                for(JsonElement obj : jArray )
                {
                    Seance seance = gson.fromJson(obj, Seance.class);
                    rottenDB.insertSeance(seance.getId(), seance.getActual_date(), seance.getShow_time(), seance.getIs_troisd(), seance.getIs_malentendant(), seance.getIs_handicape(),
                            seance.getNationality(), seance.getCinemaid(), seance.getFilmid(), seance.getTitre(), seance.getCategorieid(), seance.getPerformanceid(), seance.getCinema_salle());
                }
                seancesRequestIsDone=true;
                if(checkRequestsAreDone()) showMainActivity();
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
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                for(JsonElement obj : jArray )
                {
                    Film film = gson.fromJson( obj , Film.class);
                    if(!rottenDB.checkIsFilmAlreadyInDb(film.getId())) {
                        String media = film.getMediasAsString();
                        String video = film.getVideosAsString();
                        rottenDB.insertFilm(film.getId(), film.getTitre(), film.getTitre_ori(), film.getAffiche(), film.getWeb(), film.getDuree(), film.getDistributeur(), film.getParticipants(),
                                film.getRealisateur(), film.getSynopsis(), film.getAnnee(), film.getDate_sortie(), film.getInfo(), film.getIs_visible(), film.getIs_vente(), film.getGenreid(),
                                film.getCategorieid(), film.getGenre(), film.getCategorie(), film.getReleaseNumber(), film.getPays(), film.getShare_url(), media, video,
                                film.getIs_avp(), film.getIs_alaune(), film.getIs_lastWeek(), false);
                    }
                }
                filmSeancesRequestIsDone=true;
                if(checkRequestsAreDone()) showMainActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Films", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void makeEventRequest() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlEvent, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
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
                            String films= event.getFilmsIdAsString();
                            rottenDB.insertEvent(event.getId(),event.getTitre(),event.getSoustitre(),event.getAffiche(),event.getDescription(),event.getVad_condition(),
                                    event.getPartenaire(),event.getDate_deb(),event.getDate_fin(),event.getHeure(),event.getContact(),event.getWeb_label(),event.getEvenementtypeid(),
                                    films, eventWrapped.getType(),eventWrapped.getTitre());
                        }
                    }

                }
                eventsRequestIsDone=true;
                if(checkRequestsAreDone()) showMainActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Events", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq);
    }

    private Boolean checkRequestsAreDone(){
        return (prochainementsRequestIsDone && eventsRequestIsDone && filmSeancesRequestIsDone && seancesRequestIsDone);
    }

    private void showMainActivity(){
        hidepDialog();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
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
