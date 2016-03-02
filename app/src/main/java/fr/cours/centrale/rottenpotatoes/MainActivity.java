package fr.cours.centrale.rottenpotatoes;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import fr.cours.centrale.rottenpotatoes.event.Event;
import fr.cours.centrale.rottenpotatoes.event.EventFragment;
import fr.cours.centrale.rottenpotatoes.film.Film;
import fr.cours.centrale.rottenpotatoes.film.FilmFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FilmFragment.OnFilmSelectedListener, EventFragment.OnEventSelectedListener, ParametersFragment.OnParametersListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private DBHelper rottenDB;
    public static List<Film> listFilmToShow;
    public static List<Event> listEventToShow;

    //PARAMETRES POUR LA RECHERCHE
    public static List<String> listNationality; // liste toutes les langues de films possibles
    public static List<Integer> listNationalitySelected; // liste toutes les langues de films choisies par l'utilisateur
    public static List<String> listCinema;
    public static List<Integer> listCinemaSelected;
    public static List<String> listCategorie;
    public static List<Integer> listCategorieSelected;
    public static int user_choice_troisd;
    public static int user_choice_malentendant;
    public static int user_choice_handicape;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rottenDB = new DBHelper(this);

        loadSharedPref();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        showAlAfficheFragment();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.affiche) {
            showAlAfficheFragment();

        } else if (id == R.id.prochainement) {
            showProchainementFragment();

        } else if (id == R.id.evenements) {
            showEventFragment();

        } else if (id == R.id.preferences) {
            showPreferenceFragment();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void showAlAfficheFragment() {
        Fragment filmFragment = new FilmFragment();

        listFilmToShow = rottenDB.getAllFilmAlAffiche();
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, filmFragment)
                .commit();
    }

    public void showProchainementFragment() {
        Fragment filmFragment = new FilmFragment();

        listFilmToShow = rottenDB.getAllFilmProchainement();
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, filmFragment)
                .commit();
    }

    public void showEventFragment() {
        Fragment eventFragment = new EventFragment();

        listEventToShow = rottenDB.getAllEvents();
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, eventFragment)
                .commit();
    }

    public void showPreferenceFragment() {
        Fragment parametersFragment = new ParametersFragment();

        listCinema = new ArrayList<String>();
        listCinema.add("Le Cazanne"); // cinemaid 1, imposé car pas d'infos dans les JSON...
        listCinema.add("Le Renoir");  // cinemaid 2
        listCinema.add("Le Mazarin"); // cinemaid 3

        listNationality = rottenDB.getAllNationality();
        listCategorie = rottenDB.getAllCategorie();

        listCinemaSelected.add(2);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, parametersFragment)
                .commit();
    }

    @Override
    public void onFilmSelected(Film film) {

    }

    @Override
    public void onEventSelected(Event event) {

    }

    @Override
    public void onParametersInteraction(Uri uri) {

    }

    public final void saveSharedPref() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.USER_WANTS_3D), user_choice_troisd);
        editor.putInt(getString(R.string.USER_WANTS_HANDICAPE), user_choice_handicape);
        editor.putInt(getString(R.string.USER_WANTS_MALENTENDANT), user_choice_malentendant);
        editor.putBoolean(getString(R.string.USER_WANTS_CINEMA1), listCinemaSelected.contains(1));
        editor.putBoolean(getString(R.string.USER_WANTS_CINEMA2), listCinemaSelected.contains(2));
        editor.putBoolean(getString(R.string.USER_WANTS_CINEMA3), listCinemaSelected.contains(3));
        editor.putBoolean(getString(R.string.USER_WANTS_LANGUE1), listNationalitySelected.contains(1));
        editor.putBoolean(getString(R.string.USER_WANTS_LANGUE2), listNationalitySelected.contains(2));
        editor.putBoolean(getString(R.string.USER_WANTS_LANGUE3), listNationalitySelected.contains(3));
        editor.putBoolean(getString(R.string.USER_WANTS_CATEGORIE1), listCategorieSelected.contains(1));
        editor.putBoolean(getString(R.string.USER_WANTS_CATEGORIE2), listCategorieSelected.contains(2));
        editor.putBoolean(getString(R.string.USER_WANTS_CATEGORIE3), listCategorieSelected.contains(3));
        editor.putBoolean(getString(R.string.USER_WANTS_CATEGORIE4), listCategorieSelected.contains(4));
        editor.commit();
    }

    public final void loadSharedPref(){
        int defaultValue = 0; // correspond au choix "pas de préférence"
        listCinemaSelected = new ArrayList<Integer>();
        listNationalitySelected = new ArrayList<Integer>();
        listCategorieSelected = new ArrayList<Integer>();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        user_choice_troisd=sharedPref.getInt(getString(R.string.USER_WANTS_3D), defaultValue);
        user_choice_handicape=sharedPref.getInt(getString(R.string.USER_WANTS_HANDICAPE), defaultValue);
        user_choice_handicape=sharedPref.getInt(getString(R.string.USER_WANTS_MALENTENDANT), defaultValue);

        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_CINEMA1), false)) listCinemaSelected.add(1);
        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_CINEMA2), false)) listCinemaSelected.add(2);
        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_CINEMA3), false)) listCinemaSelected.add(3);
        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_LANGUE1), false)) listNationalitySelected.add(1);
        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_LANGUE2), false)) listNationalitySelected.add(2);
        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_LANGUE3), false)) listNationalitySelected.add(3);
        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_CATEGORIE1), false)) listCategorieSelected.add(1);
        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_CATEGORIE2), false)) listCategorieSelected.add(2);
        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_CATEGORIE3), false)) listCategorieSelected.add(3);
        if(sharedPref.getBoolean(getString(R.string.USER_WANTS_CATEGORIE4), false)) listCategorieSelected.add(4);

    }

    @Override
    public void onStart() {
        super.onStart();
        loadSharedPref();
    }

    @Override
    public void onStop() {
        super.onStop();
        saveSharedPref();
    }
}
