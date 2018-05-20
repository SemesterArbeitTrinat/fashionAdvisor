package floria.fashionadvisor.database;

/**
 * Created by Seehund on 24.04.2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import static floria.fashionadvisor.MainActivity.database;


public class DBDataSource {

    private static final String LOG_TAG = DBDataSource.class.getSimpleName();


    private DBOpenHelper dbHelper;

    private String[] Sammlung = {
            DBOpenHelper._ID,
            DBOpenHelper.SAMMLUNG_STIL,
            DBOpenHelper.SAMMLUNG_BEZEICHNUNG,
            DBOpenHelper.SAMMLUNG_FARBE,
            DBOpenHelper.SAMMLUNG_SCHNITT,
            DBOpenHelper.SAMMLUNG_RANK,
            DBOpenHelper.SAMMLUNG_FAVORIT,
            DBOpenHelper.SAMMLUNG_HAEUFIGKEIT,
            DBOpenHelper.SAMMLUNG_FOTO,
            DBOpenHelper.SAMMLUNG_KATEGORIE

    };



    public DBDataSource (Context context){
        Log.d(LOG_TAG, "Data Source erzeugt den dbHelper.");
        dbHelper=new DBOpenHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Datenbank wird erstellt");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }
    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }


    public void addDB (String Stil, String Bezeichnung, String Farbe, String Schnitt, int Rank, int Favorit, int Haeufigkeit, byte [] Photo, String Kategorie){

        ContentValues values= new ContentValues();

        values.put(DBOpenHelper.SAMMLUNG_BEZEICHNUNG,Bezeichnung);
        values.put(DBOpenHelper.SAMMLUNG_FARBE,Farbe);
        values.put(DBOpenHelper.SAMMLUNG_FAVORIT,Favorit);
        values.put(DBOpenHelper.SAMMLUNG_FOTO, Photo);
        values.put(DBOpenHelper.SAMMLUNG_STIL,Stil);
        values.put(DBOpenHelper.SAMMLUNG_SCHNITT, Schnitt);
        values.put(DBOpenHelper.SAMMLUNG_RANK, Rank);
        values.put(DBOpenHelper.SAMMLUNG_HAEUFIGKEIT, Haeufigkeit);
        values.put(DBOpenHelper.SAMMLUNG_KATEGORIE, Kategorie);

        database.insert(DBOpenHelper.TABLE_NAME_SAMMLUNG,null,values);

    }



   /* public DB createDB(int Stil, int Bezeichnung, int Farbe, int Schnitt, int Rank, int Favorit, int Haeufigkeit, String Photo){

        ContentValues values= new ContentValues();

        values.put(DBOpenHelper.SAMMLUNG_BEZEICHNUNG,Bezeichnung);
        values.put(DBOpenHelper.SAMMLUNG_FARBE,Farbe);
        values.put(DBOpenHelper.SAMMLUNG_FAVORIT,Favorit);
        values.put(DBOpenHelper.SAMMLUNG_FOTO, Photo);
        values.put(DBOpenHelper.SAMMLUNG_STIL,Stil);
        values.put(DBOpenHelper.SAMMLUNG_SCHNITT, Schnitt);
        values.put(DBOpenHelper.SAMMLUNG_RANK, Rank);
        values.put(DBOpenHelper.SAMMLUNG_HAEUFIGKEIT, Haeufigkeit);

        long insertID= database.insert(DBOpenHelper.TABLE_NAME_SAMMLUNG,null,values);

        Cursor cursor=database.query(DBOpenHelper.TABLE_NAME_SAMMLUNG,
                Sammlung, DBOpenHelper._ID+ "="+insertID,
                null, null,null, null);
        cursor.moveToFirst();
        DB DB = cursorToDB(cursor);
        cursor.close();
        return DB;
    }*/

    public long insert(byte[] image) {
        Log.e(LOG_TAG,"Bild gespeichert");
        return database.insert(DBOpenHelper.TABLE_NAME_SAMMLUNG, null, createContentValues(image));

    }

    private ContentValues createContentValues(byte[] image) {
        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.SAMMLUNG_FOTO, image);
        return cv;
    }

    private DB cursorToDB(Cursor cursor){
        int idIndex =cursor.getColumnIndex(DBOpenHelper._ID);
        int idBezeichnung = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_BEZEICHNUNG);
        int idFarbe =cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FARBE);
        int idFavorit =cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FAVORIT);
        int idPhoto = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FOTO);
        int idStil = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_STIL);
        int idSchnitt = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_SCHNITT);
        int idRank= cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_RANK);
        int idHaeufigkeit= cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_HAEUFIGKEIT);
        int idKAtegorie= cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_KATEGORIE);



        int id = cursor.getInt(idIndex);
        String bezeichnung= cursor.getString(idBezeichnung);
        String farbe= cursor.getString(idFarbe);
        int favorit= cursor.getInt(idFavorit);
        byte [] photo= cursor.getBlob(idPhoto);
        String stil= cursor.getString(idStil);
        String schnitt= cursor.getString(idSchnitt);
        int rank= cursor.getInt(idRank);
        int haeufigkeit=cursor.getInt(idHaeufigkeit);
        String kategorie=cursor.getString(idKAtegorie);


        DB DB = new DB(id,stil, bezeichnung,farbe,schnitt,rank,favorit,haeufigkeit,photo,kategorie);

        return DB;

    }





    public List<DB> getAllDB(){
        ArrayList<DB> DBList = new ArrayList<DB>();
        ArrayList FarbeList= new ArrayList();

        Cursor cursor= database.query(DBOpenHelper.TABLE_NAME_SAMMLUNG,Sammlung,
                null,null,null,null,null);


        cursor.moveToFirst();

        DB DB;

        while (!cursor.isAfterLast()){
            DB = cursorToDB(cursor);

            DBList.add(DB);

            Log.d(LOG_TAG, "ID: "+DB.getId()+ " gehört zu:  " + DB.toString());


            cursor.moveToNext();
        }
        cursor.close();
        //cursor1.close();
        return DBList;

    }


}