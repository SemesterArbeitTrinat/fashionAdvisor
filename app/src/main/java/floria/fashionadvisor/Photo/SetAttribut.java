package floria.fashionadvisor.Photo;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

import floria.fashionadvisor.MainActivity;
import floria.fashionadvisor.R;

/**
 * Created by flori on 08/04/2018.
 */

public class SetAttribut extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_attribut);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Parametern");
        setSupportActionBar(toolbar);

        FrameLayout mFrame= findViewById(R.id.photo_view);


        GridView test = (GridView) findViewById(R.id.test);

        Spinner spinner = (Spinner) findViewById(R.id.farbe);

        spinner.setOnItemSelectedListener(this);


        //test.setOnItemSelectedListener(this);
        chooseColor(spinner);

            String[] cat= getResources().getStringArray(R.array.cat_array);
// Create an ArrayAdapter using the string array and a default spinner layout

        TextCheked adapter = new TextCheked(this,cat);
        test.setAdapter(adapter);



    }
   private void chooseColor(Spinner spinner){

       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.farbe_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner


       spinner.setAdapter(adapter);

   }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos,long id) {
       // Toast.makeText(getApplicationContext(), pos, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.retour:
                finish();
                Intent callNeuAufnehmen = new Intent(SetAttribut.this, NeuAufnehmen.class);
                startActivity(callNeuAufnehmen);
                return true;

        }
        return true;
    }



}


