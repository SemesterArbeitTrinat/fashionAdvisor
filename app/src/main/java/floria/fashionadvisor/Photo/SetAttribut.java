package floria.fashionadvisor.Photo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import floria.fashionadvisor.R;

/**
 * Created by flori on 08/04/2018.
 */

public class SetAttribut extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


private Button top;
private Button down;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_attribut);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Parametern");
        setSupportActionBar(toolbar);

        top = (Button) findViewById(R.id.OT);
        down = (Button) findViewById(R.id.UT);


        FrameLayout mFrame= findViewById(R.id.photo_view);
        RadioGroup cut= findViewById(R.id.radioGroup);
        GridView style = (GridView) findViewById(R.id.test);

        Spinner spinner = (Spinner) findViewById(R.id.farbe);
        spinner.setOnItemSelectedListener(this);

        chooseTopCat();
        chooseColor(spinner);

       String[] styleList= getResources().getStringArray(R.array.style_array);
// Create an ArrayAdapter using the string array and a default spinner layout

        TextCheked adapter = new TextCheked(this,styleList);
        style.setAdapter(adapter);

    }


    private void chooseTopCat(){
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TopCat mTopCat = new TopCat() ;
                mTopCat.setIndex(1);
                loadFragment(mTopCat);
                top.setBackgroundColor(Color.DKGRAY);
                down.setBackgroundColor(Color.TRANSPARENT);

            }
        });
// perform setOnClickListener event on Second Button
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopCat mTopCat = new TopCat() ;
                mTopCat.setIndex(2);
                loadFragment(mTopCat);
                down.setBackgroundColor(Color.DKGRAY);
                top.setBackgroundColor(Color.TRANSPARENT);
            }
        });


    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

   private void chooseColor(Spinner spinner){

       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.color_array, android.R.layout.simple_spinner_item);
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
                Intent callNeuAufnehmen = new Intent(SetAttribut.this, NewPhoto.class);
                startActivity(callNeuAufnehmen);
                return true;
        }
        return true;
    }
}


