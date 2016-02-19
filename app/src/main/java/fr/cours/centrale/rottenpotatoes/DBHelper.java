package fr.cours.centrale.rottenpotatoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.cours.centrale.rottenpotatoes.event.Event;
import fr.cours.centrale.rottenpotatoes.film.Film;

/**
 * Created by christian on 16/02/16.
 *
 * Cette classe traduits tous les accès en requêtes SQL pour la base de données.
 * La DB contient 3 tables: films, events et séances. Les films à l'affiche et prochainement sont stockées dans la même table, on rajoute simplement un chanm IS_PROCHAINEMENT pour les différencier.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static String TAG = DBHelper.class.getSimpleName();
    private static DBHelper mInstance = null;

    public static final String DATABASE_NAME = "RottenPotatoes.db";

    public static final String FILMS_TABLE_NAME= "films";
    public static final String FILMS_COLUMN_ID= "id";
    public static final String FILMS_COLUMN_TITRE="titre";
    public static final String FILMS_COLUMN_TITRE_ORI="titre_ori";
    public static final String FILMS_COLUMN_AFFICHE="affiche";
    public static final String FILMS_COLUMN_WEB="web";
    public static final String FILMS_COLUMN_DUREE="duree";
    public static final String FILMS_COLUMN_DISTRIBUTEUR="distributeur";
    public static final String FILMS_COLUMN_PARTICIPANTS="participants";
    public static final String FILMS_COLUMN_REALISATEUR="realisateur";
    public static final String FILMS_COLUMN_SYNOPSIS="synopsis";
    public static final String FILMS_COLUMN_ANNEE="annee";
    public static final String FILMS_COLUMN_DATE_SORTIE="date_sortie";
    public static final String FILMS_COLUMN_INFO="info";
    public static final String FILMS_COLUMN_IS_VISIBLE="is_visible";
    public static final String FILMS_COLUMN_IS_VENTE="is_vente";
    public static final String FILMS_COLUMN_GENREID="genreid";
    public static final String FILMS_COLUMN_CATEGORIEID="categorieid";
    public static final String FILMS_COLUMN_GENRE="genre";
    public static final String FILMS_COLUMN_CATEGORIE="categorie";
    public static final String FILMS_COLUMN_RELEASENUMBER="ReleaseNumber";
    public static final String FILMS_COLUMN_PAYS="pays";
    public static final String FILMS_COLUMN_SHARE_URL="share_url";
    public static final String FILMS_COLUMN_MEDIAS="medias";
    public static final String FILMS_COLUMN_VIDEOS="videoes";
    public static final String FILMS_COLUMN_IS_AVP="is_avp";
    public static final String FILMS_COLUMN_IS_ALAUNE="is_alaune";
    public static final String FILMS_COLUMN_IS_LASTWEEK="is_lastWeek";
    public static final String FILMS_COLUMN_IS_PROCHAINEMENT="is_prochainement";
    public static final String FILMS_COLUMN_IS_ALAFFICHE="is_alafiche";

    public static final String SEANCES_TABLE_NAME="seances";
    public static final String SEANCES_COLUMN_ID="id";
    public static final String SEANCES_COLUMN_ACTUAL_DATE="actual_date";
    public static final String SEANCES_COLUMN_SHOW_TIME="show_time";
    public static final String SEANCES_COLUMN_IS_TROISD="is_troisd";
    public static final String SEANCES_COLUMN_IS_MALENTENDANT="is_malentendant";
    public static final String SEANCES_COLUMN_IS_HANDICAPE="is_handicape";
    public static final String SEANCES_COLUMN_NATIONALITY="nationality";
    public static final String SEANCES_COLUMN_CINEMAID="cinemaid";
    public static final String SEANCES_COLUMN_FILMID="filmid";
    public static final String SEANCES_COLUMN_TITRE="titre";
    public static final String SEANCES_COLUMN_CATEGORIEID="categorieid";
    public static final String SEANCES_COLUMN_PERFORMANCEID="performanceid";
    public static final String SEANCES_COLUMN_CINEMA_SALLE="cinema_salle";

    public static final String EVENTS_TABLE_NAME="events";
    public static final String EVENTS_COLUMN_ID="id";
    public static final String EVENTS_COLUMN_TITRE="titre";
    public static final String EVENTS_COLUMN_SOUSTITRE="soustitre";
    public static final String EVENTS_COLUMN_AFFICHE="affiche";
    public static final String EVENTS_COLUMN_DESCRIPTION="description";
    public static final String EVENTS_COLUMN_VAD_CONDITION="vad_condition";
    public static final String EVENTS_COLUMN_PARTENAIRE="partenaire";
    public static final String EVENTS_COLUMN_DATE_DEB="date_deb";
    public static final String EVENTS_COLUMN_DATE_FIN="date_fin";
    public static final String EVENTS_COLUMN_HEURE="heure";
    public static final String EVENTS_COLUMN_CONTACT="contact";
    public static final String EVENTS_COLUMN_WEB_LABEL="web_label";
    public static final String EVENTS_COLUMN_EVENEMENTTYPEID="evenementtypeid";
    public static final String EVENTS_COLUMN_FILMS="films";
    public static final String EVENTS_COLUMN_TYPE_WRAPPED="type";
    public static final String EVENTS_COLUMN_TITRE_WRAPPED="titre_wrapped";

    public static DBHelper getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new DBHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+FILMS_TABLE_NAME+" (" +
                        FILMS_COLUMN_ID + " integer primary key, "+
                        FILMS_COLUMN_TITRE + " text, " +
                        FILMS_COLUMN_TITRE_ORI + " text, " +
                        FILMS_COLUMN_AFFICHE + " text, "+
                        FILMS_COLUMN_WEB + " text, "+
                        FILMS_COLUMN_DUREE + " integer, "+
                        FILMS_COLUMN_DISTRIBUTEUR + " text, "+
                        FILMS_COLUMN_PARTICIPANTS + " text, "+
                        FILMS_COLUMN_REALISATEUR + " text, "+
                        FILMS_COLUMN_SYNOPSIS + " text, "+
                        FILMS_COLUMN_ANNEE+" integer, "+
                        FILMS_COLUMN_DATE_SORTIE+" text, "+
                        FILMS_COLUMN_INFO+" text, "+
                        FILMS_COLUMN_IS_VISIBLE+ " boolean, "+
                        FILMS_COLUMN_IS_VENTE+ " boolean, "+
                        FILMS_COLUMN_GENREID+ " integer, "+
                        FILMS_COLUMN_CATEGORIEID+ " integer, "+
                        FILMS_COLUMN_GENRE+ " text, "+
                        FILMS_COLUMN_CATEGORIE+ " text, "+
                        FILMS_COLUMN_RELEASENUMBER+ " text, "+
                        FILMS_COLUMN_PAYS+ " text, "+
                        FILMS_COLUMN_SHARE_URL+ " text, "+
                        FILMS_COLUMN_MEDIAS+ " text, "+
                        FILMS_COLUMN_VIDEOS+ " text, "+
                        FILMS_COLUMN_IS_AVP+ " boolean, "+
                        FILMS_COLUMN_IS_ALAUNE+ " boolean, "+
                        FILMS_COLUMN_IS_LASTWEEK+ " boolean, "+
                        FILMS_COLUMN_IS_PROCHAINEMENT+ " boolean, "+
                        FILMS_COLUMN_IS_ALAFFICHE + " boolean "+
                        ")"
        );

        db.execSQL(
                "create table "+SEANCES_TABLE_NAME+" (" +
                        SEANCES_COLUMN_ID + " integer primary key, "+
                        SEANCES_COLUMN_ACTUAL_DATE+ " text, "+
                        SEANCES_COLUMN_SHOW_TIME+ " text, "+
                        SEANCES_COLUMN_IS_TROISD+ " boolean, "+
                        SEANCES_COLUMN_IS_MALENTENDANT+ " boolean, "+
                        SEANCES_COLUMN_IS_HANDICAPE+ " boolean, "+
                        SEANCES_COLUMN_NATIONALITY+ " text, "+
                        SEANCES_COLUMN_CINEMAID+ " integer, "+
                        SEANCES_COLUMN_FILMID+ " integer, "+
                        SEANCES_COLUMN_TITRE+ " text, "+
                        SEANCES_COLUMN_CATEGORIEID+ " integer, "+
                        SEANCES_COLUMN_PERFORMANCEID+ " integer, "+
                        SEANCES_COLUMN_CINEMA_SALLE+ " text "+
                        ")"
        );

        db.execSQL(
                "create table " + EVENTS_TABLE_NAME + " (" +
                        EVENTS_COLUMN_ID + " integer primary key, " +
                        EVENTS_COLUMN_TITRE + " text, " +
                        EVENTS_COLUMN_SOUSTITRE + " text, " +
                        EVENTS_COLUMN_AFFICHE + " text, " +
                        EVENTS_COLUMN_DESCRIPTION + " text, " +
                        EVENTS_COLUMN_VAD_CONDITION + " text, " +
                        EVENTS_COLUMN_PARTENAIRE + " text, " +
                        EVENTS_COLUMN_DATE_DEB + " text, " +
                        EVENTS_COLUMN_DATE_FIN + " text, " +
                        EVENTS_COLUMN_HEURE + " text, " +
                        EVENTS_COLUMN_CONTACT + " text, " +
                        EVENTS_COLUMN_WEB_LABEL + " text, " +
                        EVENTS_COLUMN_EVENEMENTTYPEID + " text, " +
                        EVENTS_COLUMN_FILMS + " text, " +
                        EVENTS_COLUMN_TYPE_WRAPPED + " text, " +
                        EVENTS_COLUMN_TITRE_WRAPPED + " text " +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ FILMS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ SEANCES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ EVENTS_TABLE_NAME);
        onCreate(db);
    }

    public void insertFilm (int id, String titre, String titre_ori, String affiche, String web, int duree, String distributeur, String participants, String realisateur, String synopsis, int annee,
                            String date_sortie, String info, boolean is_visible, boolean is_vente, int genreid, int categorieid, String genre, String categorie, String ReleaseNumber, String pays, String share_url,
                            String medias, String videos, boolean is_avp, boolean is_alaune, boolean is_lastWeek, boolean is_prochainement, boolean is_alafiche){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILMS_COLUMN_ID, id);
        contentValues.put(FILMS_COLUMN_TITRE, titre);
        contentValues.put(FILMS_COLUMN_TITRE_ORI, titre_ori);
        contentValues.put(FILMS_COLUMN_AFFICHE, affiche);
        contentValues.put(FILMS_COLUMN_WEB, web);
        contentValues.put(FILMS_COLUMN_DUREE, duree);
        contentValues.put(FILMS_COLUMN_DISTRIBUTEUR, distributeur);
        contentValues.put(FILMS_COLUMN_PARTICIPANTS, participants);
        contentValues.put(FILMS_COLUMN_REALISATEUR, realisateur);
        contentValues.put(FILMS_COLUMN_SYNOPSIS, synopsis);
        contentValues.put(FILMS_COLUMN_ANNEE, annee);
        contentValues.put(FILMS_COLUMN_DATE_SORTIE, date_sortie);
        contentValues.put(FILMS_COLUMN_INFO, info);
        contentValues.put(FILMS_COLUMN_IS_VISIBLE, is_visible);
        contentValues.put(FILMS_COLUMN_IS_VENTE, is_vente);
        contentValues.put(FILMS_COLUMN_GENREID, genreid);
        contentValues.put(FILMS_COLUMN_CATEGORIEID, categorieid);
        contentValues.put(FILMS_COLUMN_GENRE, genre);
        contentValues.put(FILMS_COLUMN_CATEGORIE, categorie);
        contentValues.put(FILMS_COLUMN_RELEASENUMBER, ReleaseNumber);
        contentValues.put(FILMS_COLUMN_PAYS, pays);
        contentValues.put(FILMS_COLUMN_SHARE_URL,share_url);
        contentValues.put(FILMS_COLUMN_MEDIAS,medias);
        contentValues.put(FILMS_COLUMN_VIDEOS,videos);
        contentValues.put(FILMS_COLUMN_IS_AVP,is_avp);
        contentValues.put(FILMS_COLUMN_IS_ALAUNE,is_alaune);
        contentValues.put(FILMS_COLUMN_IS_LASTWEEK,is_lastWeek);
        contentValues.put(FILMS_COLUMN_IS_PROCHAINEMENT,is_prochainement);
        contentValues.put(FILMS_COLUMN_IS_ALAFFICHE,is_alafiche);
        db.insert(FILMS_TABLE_NAME, null, contentValues);
    }

    public void insertSeance(int id, String actual_date, String show_time, boolean is_troisd, boolean is_malentendant, boolean is_handicape, String nationality, int cinemaid,
                             int filmid, String titre, int categorieid, int performanceid, String cinema_salle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SEANCES_COLUMN_ID,id);
        contentValues.put(SEANCES_COLUMN_ACTUAL_DATE,actual_date);
        contentValues.put(SEANCES_COLUMN_SHOW_TIME,show_time);
        contentValues.put(SEANCES_COLUMN_IS_TROISD,is_troisd);
        contentValues.put(SEANCES_COLUMN_IS_MALENTENDANT,is_malentendant);
        contentValues.put(SEANCES_COLUMN_IS_HANDICAPE,is_handicape);
        contentValues.put(SEANCES_COLUMN_NATIONALITY,nationality);
        contentValues.put(SEANCES_COLUMN_CINEMAID,cinemaid);
        contentValues.put(SEANCES_COLUMN_FILMID,filmid);
        contentValues.put(SEANCES_COLUMN_TITRE,titre);
        contentValues.put(SEANCES_COLUMN_CATEGORIEID,categorieid);
        contentValues.put(SEANCES_COLUMN_PERFORMANCEID,performanceid);
        contentValues.put(SEANCES_COLUMN_CINEMA_SALLE,cinema_salle);
        db.insert(SEANCES_TABLE_NAME,null,contentValues);
    }

    public void insertEvent(String id, String titre, String soustitre, String affiche, String description, String vad_condition, String partenaire, String date_deb, String date_fin,
                            String heure, String contact, String web_label, String evenementtypeid, String films, String type_wrapped, String titre_wrapped){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENTS_COLUMN_TITRE,titre);
        contentValues.put(EVENTS_COLUMN_SOUSTITRE,soustitre);
        contentValues.put(EVENTS_COLUMN_AFFICHE,affiche);
        contentValues.put(EVENTS_COLUMN_DESCRIPTION,description);
        contentValues.put(EVENTS_COLUMN_VAD_CONDITION,vad_condition);
        contentValues.put(EVENTS_COLUMN_PARTENAIRE,partenaire);
        contentValues.put(EVENTS_COLUMN_DATE_DEB,date_deb);
        contentValues.put(EVENTS_COLUMN_DATE_FIN,date_fin);
        contentValues.put(EVENTS_COLUMN_HEURE,heure);
        contentValues.put(EVENTS_COLUMN_CONTACT,contact);
        contentValues.put(EVENTS_COLUMN_WEB_LABEL,web_label);
        contentValues.put(EVENTS_COLUMN_EVENEMENTTYPEID,evenementtypeid);
        contentValues.put(EVENTS_COLUMN_FILMS,films);
        contentValues.put(EVENTS_COLUMN_TYPE_WRAPPED,type_wrapped);
        contentValues.put(EVENTS_COLUMN_TITRE_WRAPPED,titre_wrapped);
        db.insert(EVENTS_TABLE_NAME,null,contentValues);
    }

    public void updateProchainement(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILMS_COLUMN_IS_PROCHAINEMENT,true);
        db.update(FILMS_TABLE_NAME, contentValues,FILMS_COLUMN_ID+"="+id,null);

    }

    public void updateAlaffiche(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILMS_COLUMN_IS_ALAFFICHE,true);
        db.update(FILMS_TABLE_NAME, contentValues,FILMS_COLUMN_ID+"="+id,null);

    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public boolean checkIsFilmAlreadyInDb(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + FILMS_TABLE_NAME + " where " + FILMS_COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public int numberOfSeances(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SEANCES_TABLE_NAME);
        return numRows;
    }

    public int numberOfEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, EVENTS_TABLE_NAME);
        return numRows;
    }

    public int numberOfFilms(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FILMS_TABLE_NAME);
        return numRows;
    }

    public ArrayList<String> getAllseances()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from seances", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(SEANCES_COLUMN_TITRE)));
            res.moveToNext();
        }
        return array_list;
    }

    public List<Film> cursorToFilm(Cursor res){
        List<Film> listFilm = new ArrayList<Film>();
        res.moveToFirst();
        while(res.isAfterLast() == false){

            String paths = res.getString(res.getColumnIndex(FILMS_COLUMN_MEDIAS));
            String videoStringified= res.getString(res.getColumnIndex(FILMS_COLUMN_VIDEOS));
            List<Film.Medias> listMedias = stringToMedias(paths);
            List<Film.Videos> listVideos = stringToVideos(videoStringified);

            Film film = new Film(res.getInt(res.getColumnIndex(FILMS_COLUMN_ID)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_TITRE)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_TITRE_ORI)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_AFFICHE)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_WEB)),
                    res.getInt(res.getColumnIndex(FILMS_COLUMN_DUREE)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_DISTRIBUTEUR)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_PARTICIPANTS)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_REALISATEUR)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_SYNOPSIS)),
                    res.getInt(res.getColumnIndex(FILMS_COLUMN_ANNEE)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_DATE_SORTIE)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_INFO)),
                    res.getInt(res.getColumnIndex(FILMS_COLUMN_IS_VISIBLE))==1,
                    res.getInt(res.getColumnIndex(FILMS_COLUMN_IS_VENTE))==1,
                    res.getInt(res.getColumnIndex(FILMS_COLUMN_GENREID)),
                    res.getInt(res.getColumnIndex(FILMS_COLUMN_CATEGORIEID)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_GENRE)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_CATEGORIE)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_RELEASENUMBER)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_PAYS)),
                    res.getString(res.getColumnIndex(FILMS_COLUMN_SHARE_URL)),
                    listMedias,
                    listVideos,
                    res.getInt(res.getColumnIndex(FILMS_COLUMN_IS_AVP))==1,
                    res.getInt(res.getColumnIndex(FILMS_COLUMN_IS_ALAUNE))==1,
                    res.getInt(res.getColumnIndex(FILMS_COLUMN_IS_LASTWEEK))==1
            );

            listFilm.add(film);
            res.moveToNext();
        }
        return listFilm;
    }

    public Film getFilmById(int Id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + FILMS_TABLE_NAME + " where " + FILMS_COLUMN_ID + "=" + Id + "", null);
        Film film = cursorToFilm(res).get(0);
        return film;
    }

    public List<Film> getAllFilm(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + FILMS_TABLE_NAME, null);

        List<Film> listFilm = cursorToFilm(res);
        return(listFilm);
    }

    public List<Event> cursorToEvent(Cursor res){
        List<Event> listEvents = new ArrayList<Event>();
        res.moveToFirst();

        while(res.isAfterLast() == false){

            List<Film> listFilms = stringToFilms(res.getString(res.getColumnIndex(EVENTS_COLUMN_FILMS)));

            Event event = new Event(res.getString(res.getColumnIndex(EVENTS_COLUMN_ID)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_TITRE)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_SOUSTITRE)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_AFFICHE)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_DESCRIPTION)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_VAD_CONDITION)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_PARTENAIRE)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_DATE_DEB)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_DATE_FIN)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_HEURE)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_CONTACT)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_WEB_LABEL)),
                    res.getString(res.getColumnIndex(EVENTS_COLUMN_EVENEMENTTYPEID)),
                    listFilms);
            event.setTitre_wrapped(res.getString(res.getColumnIndex(EVENTS_COLUMN_TITRE_WRAPPED)));
            event.setType_wrapped(res.getString(res.getColumnIndex(EVENTS_COLUMN_TYPE_WRAPPED)));

            listEvents.add(event);
            res.moveToNext();
        }
        return listEvents;
    }

    public List<Event> getAllEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + EVENTS_TABLE_NAME, null);
        List<Event> listEvents= cursorToEvent(res);
        return(listEvents);
    }

    public List<Film.Medias> stringToMedias(String paths){
        ArrayList<Film.Medias> listMedias = new ArrayList<Film.Medias>();
        if(paths.length()>0) {
            List<String> pathList = Arrays.asList(paths.split(","));

            for (int i = 0; i < pathList.size(); i++) {
                listMedias.add(new Film.Medias(pathList.get(i)));
            }
        }
        return listMedias;
    }

    public List<Film.Videos> stringToVideos(String videoStringified){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        ArrayList<Film.Videos> listVideos = new ArrayList<Film.Videos>();
        if(videoStringified.length()>10) {
            JsonArray jArray = parser.parse(videoStringified).getAsJsonArray();
            for(JsonElement obj : jArray ) {
                Film.Videos video = gson.fromJson( obj , Film.Videos.class);
                        listVideos.add(video);
            }
        }
        return listVideos;
    }

    public List<Film> stringToFilms(String filmStringified){
        List<Film> listFilm = new ArrayList<Film>();

        List<String> listFilmId = Arrays.asList(filmStringified.split(","));
        for (int i = 0; i < listFilmId.size(); i++){
            listFilm.add(getFilmById(Integer.parseInt(listFilmId.get(i))));
        }
        return(listFilm);
    }
}
