package fr.cours.centrale.rottenpotatoes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public static String filename = "rottenfile";

    public String TAGprochainement = "liste des prochains films";
    public String TAGfilm = "liste des films";
    public String TAGseance = "liste des séances";
    public String TAGevent = "liste des events";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);

        makeProchainementRequest();
        makeSeancesRequest();
        makeFilmSeancesRequest();
        makeEventRequest();
    }

    /**
     * Seule la requête pour "prochainement" retourne un JSONObject, les 3 autres retournent des JSONArray.
     * */
    private void makeProchainementRequest() {
            showpDialog();
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    urlProchainement, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    listFilmProchainementSerialized=response.toString();
/*                    Gson gson = new Gson();
                    listFilmProchainement=gson.fromJson(response.toString(), FilmSeanceList.class);*/
                    hidepDialog();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            "failed to retrieve Upcomming Films", Toast.LENGTH_SHORT).show();
                    // hide the progress dialog
                    hidepDialog();
                    if(checkRequestsAreDone()) showMainActivity();
                }
            });
            // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void makeSeancesRequest() {
        showpDialog();
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlSeances, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                listSeanceSerialized=response.toString();
/*                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                for(JsonElement obj : jArray )
                {
                    Seance seance = gson.fromJson( obj , Seance.class);
                    listSeance.add(seance);
                }*/
                hidepDialog();
                if(checkRequestsAreDone()) showMainActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve seances", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
                if(checkRequestsAreDone()) showMainActivity();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void makeFilmSeancesRequest() {
        showpDialog();
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlFilmSeances, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                listFilmsSerialized= response.toString();
/*                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                for(JsonElement obj : jArray )
                {
                    Film film = gson.fromJson( obj , Film.class);
                    listFilms.add(film);
                }*/
                hidepDialog();
                if(checkRequestsAreDone()) showMainActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Films", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq);
    }

    private void makeEventRequest() {
        showpDialog();
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                urlEvent, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                listEventWrappedSerialized = response.toString();
/*                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(response.toString()).getAsJsonArray();
                for(JsonElement obj : jArray )
                {
                    EventWrapped eventWrapped = gson.fromJson( obj , EventWrapped.class);
                    listEventWrapped.add(eventWrapped);
                }*/
                hidepDialog();
                if(checkRequestsAreDone()) showMainActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "failed to retrieve Events", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq);
    }

    private Boolean checkRequestsAreDone(){
        return (listFilmProchainementSerialized != null && listSeanceSerialized != null && listEventWrappedSerialized != null && listFilmsSerialized != null);
    }

    private void showMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
/*        intent.putExtra(TAGprochainement, listFilmProchainementSerialized);
        intent.putExtra(TAGseance, listSeanceSerialized);
        intent.putExtra(TAGevent, listEventWrappedSerialized);
        intent.putExtra(TAGfilm, listFilmsSerialized);*/

        Log.d(TAG, "starting main");
        Log.d(TAG, listFilmsSerialized);
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
