package floria.fashionadvisor.database;

/**
 * Created by Seehund on 24.04.2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBDataSource {

    private static final String LOG_TAG = DBDataSource.class.getSimpleName();

    public static SQLiteDatabase database;
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
            DBOpenHelper.SAMMLUNG_FOTO

    };

    //Tabelle Stil
    private String[] Stil={
            DBOpenHelper.STIL_ID,
            DBOpenHelper.STIL_STIL
    };

    //Tabelle Bezeichnung
    private String[] Bezeichnung={
            DBOpenHelper.BEZEICHNUNG_ID,
            DBOpenHelper.BEZEICHNUNG_BEZEICHNUNG
    };

    //Tabelle Farbe
    private String[ ]Farbe={
            DBOpenHelper.FARBE_ID,
            DBOpenHelper.FARBE_FARBE
    };

    //Tabelle Schnitt
    private String[] Schnitt={
            DBOpenHelper.SCHNITT_ID,
            DBOpenHelper.SCHNITT_SCHNITT,
    };

    //Tabelle Foto
    private String[] Foto={
            DBOpenHelper.FOTO_ID,
            DBOpenHelper.FOTO_FOTO
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


    public void addDB (int Stil, int Bezeichnung, int Farbe, int Schnitt, int Rank, int Favorit, int Haeufigkeit, String Photo){

        ContentValues values= new ContentValues();

        values.put(DBOpenHelper.SAMMLUNG_BEZEICHNUNG,Bezeichnung);
        values.put(DBOpenHelper.SAMMLUNG_FARBE,Farbe);
        values.put(DBOpenHelper.SAMMLUNG_FAVORIT,Favorit);
        values.put(DBOpenHelper.SAMMLUNG_FOTO, Photo);
        values.put(DBOpenHelper.SAMMLUNG_STIL,Stil);
        values.put(DBOpenHelper.SAMMLUNG_SCHNITT, Schnitt);
        values.put(DBOpenHelper.SAMMLUNG_RANK, Rank);
        values.put(DBOpenHelper.SAMMLUNG_HAEUFIGKEIT, Haeufigkeit);

        database.insert(DBOpenHelper.TABLE_NAME_SAMMLUNG,null,values);

    }

    public void addinTAB_Farbe(String Farbe){

        ContentValues values =new ContentValues();
        values.put(DBOpenHelper.FARBE_FARBE, Farbe);
        database.insert(DBOpenHelper.TABLE_NAME_FARBE,null,values);

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
        return database.insert(DBOpenHelper.TABLE_NAME_SAMMLUNG, null, createContentValues(image));
    }

    private ContentValues createContentValues(byte[] image) {
        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.SAMMLUNG_FOTO, image);
        return cv;
    }

    public DB cursorToDB(Cursor cursor){
        int idIndex =cursor.getColumnIndex(DBOpenHelper._ID);
        int idBezeichnung = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_BEZEICHNUNG);
        int idFarbe =cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FARBE);
        int idFavorit =cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FAVORIT);
        int idPhoto = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FOTO);
        int idStil = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_STIL);
        int idSchnitt = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_SCHNITT);
        int idRank= cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_RANK);
        int idHaeufigkeit= cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_HAEUFIGKEIT);



        int id = cursor.getInt(idIndex);
        int bezeichnung= cursor.getInt(idBezeichnung);
        int farbe= cursor.getInt(idFarbe);
        int favorit= cursor.getInt(idFavorit);
        String photo= cursor.getString(idPhoto);
        int stil= cursor.getInt(idStil);
        int schnitt= cursor.getInt(idSchnitt);
        int rank= cursor.getInt(idRank);
        int haeufigkeit=cursor.getInt(idHaeufigkeit);


        DB DB = new DB(id,stil, bezeichnung,farbe,schnitt,rank,favorit,haeufigkeit,photo);

        return DB;

    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public FARBE cursortofarbe(Cursor cursor) {
        int idFarbe_id= cursor.getColumnIndex(DBOpenHelper.FARBE_ID);
        int idFarbe_Farbe= cursor.getColumnIndex(DBOpenHelper.FARBE_FARBE);

        int farbe_id_=cursor.getInt(idFarbe_id);
        String farbe_farbe= cursor.getString(idFarbe_Farbe);

        FARBE FARBE= new FARBE(farbe_id_,farbe_farbe);

        return FARBE;
    }





    public List<DB> getAllDB(){
        ArrayList<DB> DBList = new ArrayList<DB>();

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
        return DBList;

    }

    public List getAllFarbe(){
        ArrayList FarbeList= new ArrayList();
        Cursor cursor=database.query(DBOpenHelper.TABLE_NAME_FARBE,Farbe, null,null,null,null,null);

        cursor.moveToFirst();
        FARBE FARBE;
        while (!cursor.isAfterLast()){
            FARBE= cursortofarbe(cursor);
            FarbeList.add(Farbe);
            Log.d(LOG_TAG, "ID: "+FARBE.getId()+ " gehört zu:  " + FARBE.toStringfarbe());
            cursor.moveToNext();
        }
        cursor.close();
        return FarbeList;
    }



}