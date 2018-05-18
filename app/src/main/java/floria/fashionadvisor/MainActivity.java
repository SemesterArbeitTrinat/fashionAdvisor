package floria.fashionadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import floria.fashionadvisor.Photo.NewPhoto;

public class MainActivity extends AppCompatActivity {

    // Neue Objekte "Button"
    private Button galerie,matchen,neuphoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SetcontentView ist benutzt um die Ressourcen zu zeigen
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);

        allActivty();



    }

    private void allActivty(){

        // Die drei Ressourcen werden als Objekt gespeichert
        galerie = (Button) findViewById(R.id.galerie);
        matchen = (Button) findViewById(R.id.matchen);
        neuphoto = (Button) findViewById(R.id.neuphoto);

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
    }



}
