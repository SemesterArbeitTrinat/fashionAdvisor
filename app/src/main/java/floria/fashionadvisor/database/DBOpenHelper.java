package floria.fashionadvisor.database;

/**
 * Created by Seehund on 24.04.2018.import android.content.ContentValues;
 */
 import android.content.Context;
 import android.database.sqlite.SQLiteDatabase;
 import android.database.sqlite.SQLiteException;
 import android.database.sqlite.SQLiteOpenHelper;
 import android.nfc.Tag;
 import android.util.Log;


 public class DBOpenHelper extends SQLiteOpenHelper {

     private static final String TAG = DBOpenHelper.class
     .getSimpleName();

     //Name und Version der Datenbank

     private static final String DATABASE_NAME ="FA_DB.db";
     private static final int DATABASE_VERSION = 1;

     //Haupttabelle_Sammlung
     public static final String TABLE_NAME_SAMMLUNG = "Sammlung";

     public static final String _ID="_id";
     public static final String SAMMLUNG_STIL = "Stil";
     public static final String SAMMLUNG_BEZEICHNUNG = "Bezeichnung";
     public static final String SAMMLUNG_FARBE = "Farbe";
     public static final String SAMMLUNG_SCHNITT = "Schnitt";
     public static final String SAMMLUNG_RANK = "Rank";
     public static final String SAMMLUNG_FAVORIT = "Favorit";
     public static final String SAMMLUNG_HAEUFIGKEIT = "Haeufigkeit";
     public static final String SAMMLUNG_FOTO = "Foto";
     public static final String SAMMLUNG_KATEGORIE= "Kategorie";




     //Tabelle Sammlung anlegen

     private static final String TABLE_SAMMLUNG_CREATE="CREATE TABLE "
             + TABLE_NAME_SAMMLUNG +"("+_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
             + SAMMLUNG_STIL + " TEXT, "
             + SAMMLUNG_BEZEICHNUNG +" TEXT, "
             + SAMMLUNG_FARBE +" TEXT, "
             + SAMMLUNG_FAVORIT + " INTEGER, "
             + SAMMLUNG_HAEUFIGKEIT + " INTEGER, "
             + SAMMLUNG_FOTO + " TEXT, "
             + SAMMLUNG_SCHNITT +" TEXT, "
             +SAMMLUNG_RANK + " INTEGER, "
             +SAMMLUNG_KATEGORIE+ " TEXT);";


     private static final String TABLE_SAMMLUNG_DROP = "DROP TABLE IF EXISTS" +TABLE_NAME_SAMMLUNG;

    public DBOpenHelper(Context context){
         super(context, DATABASE_NAME,null,DATABASE_VERSION );
         Log.d(TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
     }

 @Override
 public void onCreate(SQLiteDatabase db) {
     try {

         //Tabelle Sammlung
         Log.d(TAG, "Die Tabelle wird mit SQL-Befehl: " + TABLE_SAMMLUNG_CREATE + " angelegt.");
         db.execSQL(TABLE_SAMMLUNG_CREATE);

     }
     catch (Exception ex) {
         Log.e(TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
     }
 }




 @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
         Log.w(TAG, "Upgrade der DB Version von " + oldVersion+ " zu "+ newVersion+ " ; alle Daten werden gelöscht");
         db.execSQL(TABLE_SAMMLUNG_DROP);
         onCreate(db);
 }
 }
