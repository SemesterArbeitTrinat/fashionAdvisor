package floria.fashionadvisor.database;


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

    //Tabelle Stil
    public static final String TABLE_NAME_STIL = "Stil";

    public static final String STIL_ID=" STIL_ID";
    public static final String STIL_STIL="STIL_Stil";

    //Tabelle Bezeichnung
    public static final String TABLE_NAME_BEZEICHNUNG="Bezeichnung";

    public static final String BEZEICHNUNG_ID="BBEZEICHNUNG_ID";
    public static final String BEZEICHNUNG_BEZEICHNUNG="BEZEICHNUNG_Bezeichnung";

    //Tabelle Farbe
    public static final String TABLE_NAME_FARBE="Farbe";

    public static final String FARBE_ID="FARBE_ID";
    public static final String FARBE_FARBE="FARBE_Farbe";

    //Tabelle Schnitt
    public static final String TABLE_NAME_SCHNITT="Schnitt";

    public static final String SCHNITT_ID="SCHNITT_ID";
    public static final String SCHNITT_SCHNITT="SCHNITT_Schnitt";

    //Tabelle Foto
    public static final String TABLE_NAME_FOTO="FOTO";

    public static final String FOTO_ID="FOTO_ID";
    public static final String FOTO_FOTO="FOTO_FOTO";



    //Tabelle Stil anlegen
    private static final String TABLE_STIL_CREATE="CREATE TABLE "
            +TABLE_NAME_STIL+"("+STIL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +STIL_STIL+" TEXT NOT NULL);";

    //Tabelle Bezeichnung anlegen
    private static final String TABLE_BEZEICHNUNG_CREATE="CREATE TABLE "
            +TABLE_NAME_BEZEICHNUNG+"("+BEZEICHNUNG_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +BEZEICHNUNG_BEZEICHNUNG+" TEXT NOT NULL);";

    //Tabelle Farbe anlegen
    private static final String TABLE_FARBE_CREATE="CREATE TABLE "
            +TABLE_NAME_FARBE+"("+FARBE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +FARBE_FARBE+" TEXT NOT NULL);";

    //Tabelle Foto anlegen
    private static final String TABLE_FOTO_CREATE="CREATE TABLE "
            +TABLE_NAME_FOTO+"("+FOTO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +FOTO_FOTO+" BLOB);";

    //Tabelle Schnitt anlegen
    private static final String TABLE_SCHNITT_CREATE="CREATE TABLE "
            +TABLE_NAME_SCHNITT+"("+SCHNITT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SCHNITT_SCHNITT+" TEXT NOT NULL);";


    //Tabelle Sammlung anlegen

    private static final String TABLE_SAMMLUNG_CREATE="CREATE TABLE "
            + TABLE_NAME_SAMMLUNG +"("+_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SAMMLUNG_STIL + " INTEGER, "
            + SAMMLUNG_BEZEICHNUNG +" INTEGER, "
            + SAMMLUNG_FARBE +" INTEGER, "
            + SAMMLUNG_FAVORIT + " INTEGER, "
            + SAMMLUNG_HAEUFIGKEIT + " INTEGER, "
            + SAMMLUNG_FOTO + " INTEGER, "
            + SAMMLUNG_SCHNITT +" INTEGER, "
            +SAMMLUNG_RANK + " INTEGER NOT NULL, "
            +SAMMLUNG_KATEGORIE+ " INTEGER,"
            +" FOREIGN KEY("+SAMMLUNG_STIL +") REFERENCES "+STIL_ID+", "
            +" FOREIGN KEY("+SAMMLUNG_BEZEICHNUNG +") REFERENCES "+ BEZEICHNUNG_ID+", "
            +" FOREIGN KEY(" + SAMMLUNG_FARBE +") REFERENCES "+ FARBE_ID
            +", FOREIGN KEY ("+SAMMLUNG_FOTO +") REFERENCES "+ FOTO_ID
            +", FOREIGN KEY ("+SAMMLUNG_SCHNITT +") REFERENCES "+ SCHNITT_ID
            +" );";


    private static final String TABLE_SAMMLUNG_DROP = "DROP TABLE IF EXISTS" +TABLE_NAME_SAMMLUNG;

    public DBOpenHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION );
        Log.d(TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //Tabelle Stil
            Log.d(TAG, "Die Tabelle wird mit SQL-Befehl: " + TABLE_STIL_CREATE + " angelegt.");
            db.execSQL(TABLE_STIL_CREATE);

            //Tabelle Bezeichnung
            Log.d(TAG, "Die Tabelle wird mit SQL-Befehl: " + TABLE_BEZEICHNUNG_CREATE + " angelegt.");
            db.execSQL(TABLE_BEZEICHNUNG_CREATE);

            //Tabelle Farbe
            Log.d(TAG, "Die Tabelle wird mit SQL-Befehl: " + TABLE_FARBE_CREATE + " angelegt.");
            db.execSQL(TABLE_FARBE_CREATE);

            //Tabelle Foto
            Log.d(TAG, "Die Tabelle wird mit SQL-Befehl: " + TABLE_FOTO_CREATE + " angelegt.");
            db.execSQL(TABLE_FOTO_CREATE);

            //Tabelle Schnitt
            Log.d(TAG, "Die Tabelle wird mit SQL-Befehl: " + TABLE_SCHNITT_CREATE + " angelegt.");
            db.execSQL(TABLE_SCHNITT_CREATE);

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