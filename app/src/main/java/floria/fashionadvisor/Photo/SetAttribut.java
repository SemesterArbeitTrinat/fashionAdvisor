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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import floria.fashionadvisor.R;

import static floria.fashionadvisor.Photo.NewPhoto.detectedColor;

/**
 * Created by floria on 08/04/2018.
 */

public class SetAttribut extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


private  Spinner mSpinner;
private TopCat mTopCat = new TopCat() ;
private Button top;
private Button down;
private Button saveAll;
private RadioButton goodcolor;
private RadioButton falsecolor;
private RadioButton kurz;
private RadioButton lang;
private String DBTopcat;
private String DBCat;
private String DBSchnitt;
private String DBFarbe;
private String DBStyle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_attribut);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Parametern");
        setSupportActionBar(toolbar);

        top = (Button) findViewById(R.id.OT);
        down = (Button) findViewById(R.id.UT);
        saveAll = (Button) findViewById(R.id.save);

        FrameLayout mFrame= findViewById(R.id.photo_view);
        RadioGroup cut= findViewById(R.id.radioGroup);
        RadioGroup farbe= findViewById(R.id.radioGroupColor);
        goodcolor= (RadioButton) findViewById(R.id.goodColor);
        falsecolor= (RadioButton) findViewById(R.id.falseColor);
        kurz= (RadioButton) findViewById(R.id.Kurz);
        lang= (RadioButton) findViewById(R.id.Lang);
        GridView style = (GridView) findViewById(R.id.test);

        mSpinner = (Spinner) findViewById(R.id.farbe);

        mSpinner.setOnItemSelectedListener(this);



        chooseTopCat();
        chooseColor();
        saveInDB();
        // Create an ArrayAdapter using the string array and a default spinner layout


       //Style_array ist die Liste mit allem Stylen.
       // Diese wird genommen und als String Tabelle gespeichert
       String[] styleList= getResources().getStringArray(R.array.style_array);
       //TextCheked ist eine Java Classe die extends BaseAdaptater
        TextCheked adapter = new TextCheked(this,styleList);
        //Style ist die Gridview
        style.setAdapter(adapter);

    }


    private void chooseTopCat(){

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bColor= top.getDrawingCacheBackgroundColor();
                mTopCat = new TopCat() ;
                mTopCat.setIndex(2);
                loadFragment(mTopCat);
                top.setBackgroundColor(Color.GRAY);
                down.setBackgroundColor(bColor);

            }
        });
// perform setOnClickListener event on Second Button
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bColor= down.getDrawingCacheBackgroundColor();
                mTopCat = new TopCat() ;
                mTopCat.setIndex(1);
                loadFragment(mTopCat);
                down.setBackgroundColor(Color.GRAY);
                top.setBackgroundColor(bColor);
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

   private void chooseColor(){

        goodcolor.setText(detectedColor);

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.color_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
       mSpinner.setAdapter(adapter);

   }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos,long id) {
       // Toast.makeText(getApplicationContext(), pos, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

private void saveInDB(){
    String farbe="none";
    saveAll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

 werteInDB();

        }
    });
}

private void werteInDB(){

    if (mTopCat.getIndex()==2){
        DBTopcat="Oberteil";
    }
    else if (mTopCat.getIndex()==1){
        DBTopcat="Unterteil";
    }
    else {
        Toast.makeText(getApplicationContext(), "Kategorie wählen", Toast.LENGTH_LONG).show();
    }

    DBCat = mTopCat.getCategorie();
    if (DBCat!="- - - - -") {
        //DBCat = mTopCat.getCategorie();
        Toast.makeText(getApplicationContext(), DBCat, Toast.LENGTH_LONG).show();
    }
    else if(DBCat.contentEquals("- - - - -")) {
        Toast.makeText(getApplicationContext(), "Kategorie wählen", Toast.LENGTH_LONG).show();
    }
    if (kurz.isChecked()){
        DBSchnitt="kurz";
    }
    else if (lang.isChecked()){
        DBSchnitt="lang";
    }
    if (goodcolor.isChecked()){
        if (detectedColor=="unbekannt"){
            Toast.makeText(getApplicationContext(), "Farbe wählen", Toast.LENGTH_LONG).show();
        }
        else {
            DBFarbe = detectedColor;
        }
        //Toast.makeText(getApplicationContext(), farbe, Toast.LENGTH_LONG).show();
    }

    else if (falsecolor.isChecked()){

        DBFarbe = mSpinner.getSelectedItem().toString();
        // Toast.makeText(getApplicationContext(), farbe, Toast.LENGTH_LONG).show();
    }




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


