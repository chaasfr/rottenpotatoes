package fr.cours.centrale.rottenpotatoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by christian on 16/02/16.
 */
public class DBHelper extends SQLiteOpenHelper {
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

    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
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
                        SEANCES_COLUMN_CINEMA_SALLE+ "text, "+
                        ")"
        );

        db.execSQL(
                "create table "+EVENTS_TABLE_NAME+" (" +
                        EVENTS_COLUMN_ID + " integer primary key, "+
                        EVENTS_COLUMN_TITRE+ " text, "+
                        EVENTS_COLUMN_SOUSTITRE+ " text, "+
                        EVENTS_COLUMN_AFFICHE+ " text, "+
                        EVENTS_COLUMN_DESCRIPTION+ " text, "+
                        EVENTS_COLUMN_VAD_CONDITION+ " text, "+
                        EVENTS_COLUMN_PARTENAIRE+ " text, "+
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact  (String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
