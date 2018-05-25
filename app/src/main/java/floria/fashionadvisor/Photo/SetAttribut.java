package floria.fashionadvisor.Photo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import floria.fashionadvisor.MainActivity;
import floria.fashionadvisor.Matchen;
import floria.fashionadvisor.R;
import floria.fashionadvisor.database.DB;
import floria.fashionadvisor.database.DBDataSource;
import floria.fashionadvisor.database.Utilities;

import static floria.fashionadvisor.Photo.NewPhoto.DBByteImage;
import static floria.fashionadvisor.Photo.NewPhoto.detectedColor;
import static floria.fashionadvisor.Photo.NewPhoto.DBphotoDrwb;
import static floria.fashionadvisor.Photo.NewPhoto.DBPhotoPath;
import static floria.fashionadvisor.database.Utilities.getBytes;

/**
 * Created by floria on 08/04/2018.
 */

public class SetAttribut extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


private  Spinner mSpinner;
private TopCat mTopCat = new TopCat() ;
private Button top,down,saveAll;
private RadioButton goodcolor,falsecolor,kurz,lang;
private String DBTopcat,DBCat,DBSchnitt,DBFarbe,DBStyle,DBToast;
private Utilities DBFoto;
private Boolean DBallePara=true;
private TextCheked adapter;
private DBDataSource DBspeichern;

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
        DBFoto= new Utilities();
        GridView style = (GridView) findViewById(R.id.test);
        ImageView fotoprw = (ImageView) findViewById(R.id.Fotoprw); // get the reference of ImageView
        fotoprw.setImageDrawable(DBphotoDrwb);
        DBspeichern = new DBDataSource(this);
        DBspeichern.open();

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
         adapter = new TextCheked(this,styleList);
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

    saveAll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBallePara=true;
            werteInDB();
            if (DBallePara==false){
                Toast.makeText(getApplicationContext(), DBToast+" wählen", Toast.LENGTH_LONG).show();
            }
            else {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.px800_tshirt_icon);
                //Drawable d=getResources().getDrawable(R.drawable.ic_launcher_background);
               // Bitmap bitmap= ((BitmapDrawable)d).getBitmap();
               // DBspeichern.addDB(DBStyle,DBCat,DBFarbe,DBSchnitt,8,0,0,DBFoto.getBytes(bitmap),DBTopcat);

               //
                DBspeichern.addDB(DBStyle,DBCat,DBFarbe,DBSchnitt,8,0,0,DBPhotoPath,DBTopcat);

                //DBspeichern.addDB(DBStyle,DBCat,DBFarbe,DBSchnitt,8,0,0,null,DBTopcat);
                showAllListEntries();
                Toast.makeText(getApplicationContext(), "Foto gespeichert", Toast.LENGTH_LONG).show();
                Intent callMain = new Intent(SetAttribut.this, MainActivity.class);
                startActivity(callMain);


            }
        }
    });



}
    private void showAllListEntries () {

        List<DB> DBList = DBspeichern.getAllDB();


   /* ArrayAdapter<DB> adapter = (ArrayAdapter<DB>) mDBListView.getAdapter();

    adapter.clear();
    adapter.addAll(DBList);
    adapter.notifyDataSetChanged();*/
    }

private void werteInDB(){
    DBToast="";
    if (mTopCat.getIndex()==2||mTopCat.getIndex()==1){
        DBCat = mTopCat.getCategorie();
        if (DBCat.equals("- - - - -")) {
            DBallePara=false;
            DBToast=" Kategorie";
            //Toast.makeText(getApplicationContext(), "Kategorie wählen", Toast.LENGTH_LONG).show();
        }

        switch (mTopCat.getIndex()){
            case 1 :
                DBTopcat="unterteil";
                break;
            case 2:
                DBTopcat="oberteil";
                break;
        }

    }
    else {
        DBallePara=false;
       // Toast.makeText(getApplicationContext(), "Kategorie wählen", Toast.LENGTH_LONG).show();
        DBToast=" Kategorie";
    }

    if (kurz.isChecked()){
        DBSchnitt="kurz";
    }
    else if (lang.isChecked()){
        DBSchnitt="lang";
    }
    if (goodcolor.isChecked()){
        if (detectedColor.equals("unbekannt")){
            DBallePara=false;
            if (DBToast.equals("")){ DBToast=" Farbe";}
            else {DBToast=DBToast+" und Farbe";}
           // Toast.makeText(getApplicationContext(), "Farbe wählen", Toast.LENGTH_LONG).show();
        }
        else {
            DBFarbe = detectedColor;
        }
        //Toast.makeText(getApplicationContext(), farbe, Toast.LENGTH_LONG).show();
    }

    else if (falsecolor.isChecked()){
        DBFarbe = mSpinner.getSelectedItem().toString();
        if (DBFarbe.equals("- - - - -")){
            DBallePara=false;
            if (DBToast.equals("")){ DBToast=" Farbe";}
            else {DBToast=DBToast+" und Farbe";}
            //Toast.makeText(getApplicationContext(), "Farbe wählen", Toast.LENGTH_LONG).show();
        }

    }

    adapter.setDBtotalliste("");
    DBStyle=adapter.getDBtotalliste();
    if (DBStyle.equals("00000")){
        DBallePara=false;
        if (DBToast.equals("")){ DBToast=" Style";}
        else {DBToast=DBToast+" und Style";}
        //Toast.makeText(getApplicationContext(), "Style wählen", Toast.LENGTH_LONG).show();
    }
 //   Toast.makeText(getApplicationContext(), DBStyle, Toast.LENGTH_LONG).show();


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


