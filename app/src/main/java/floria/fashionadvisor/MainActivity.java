package floria.fashionadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button galerie,matchen,neuphoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);

        allActivty();



    }

    private void allActivty(){
        galerie = (Button) findViewById(R.id.galerie);
        matchen = (Button) findViewById(R.id.matchen);
        neuphoto = (Button) findViewById(R.id.neuphoto);

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

                Intent callNeuAufnehmen = new Intent(MainActivity.this, NeuAufnehmen.class);
                startActivity(callNeuAufnehmen);
            }
        });
    }



}
