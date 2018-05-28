package floria.fashionadvisor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import floria.fashionadvisor.Photo.NewPhoto;
import floria.fashionadvisor.database.DBOpenHelper;
import floria.fashionadvisor.database.TestDB;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase database;
    final int REQ_CODE_EXTERNAL_STORAGE_PERMISSION = 45;


private DBOpenHelper dbHelper;
    // Neue Objekte "Button"
    private Button galerie,matchen,neuphoto, testdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Genehmigen der Berechtigungen Speicher
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,new  String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_CODE_EXTERNAL_STORAGE_PERMISSION);
        }



        //SetcontentView ist benutzt um die Ressourcen zu zeigen
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        dbHelper=new DBOpenHelper(this);
        database= dbHelper.getWritableDatabase();
        allActivty();



    }

    private void allActivty(){

        // Die drei Ressourcen werden als Objekt gespeichert
        galerie = (Button) findViewById(R.id.galerie);
        matchen = (Button) findViewById(R.id.matchen);
        neuphoto = (Button) findViewById(R.id.neuphoto);
      //  testdb = (Button) findViewById(R.id.testdb);

        //Listener für den Button Galerie. Diese ruft ein neue Aktivität.
        galerie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent callGalerie = new Intent(MainActivity.this, Galerie.class);
                startActivity(callGalerie);
            }
        });

        matchen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent callMatchen = new Intent(MainActivity.this, Matchen.class);
                startActivity(callMatchen);
            }
        });

        neuphoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent callNeuAufnehmen = new Intent(MainActivity.this, NewPhoto.class);
                startActivity(callNeuAufnehmen);
            }
        });



    /*    testdb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent callTestDB = new Intent(MainActivity.this, TestDB.class);
                startActivity(callTestDB);
            } });*/
    }



}
