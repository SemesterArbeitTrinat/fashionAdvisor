package floria.fashionadvisor;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import floria.fashionadvisor.Photo.SetAttribut;
import floria.fashionadvisor.tomsc.decisiontree.Outfit;

import static floria.fashionadvisor.tomsc.decisiontree.Ranking.setRankDown;
import static floria.fashionadvisor.tomsc.decisiontree.Ranking.setRankUp;

/**
 * Created by floria on 13/03/2018.
 */

public class Matchen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

private ImageSwitcher myImageSwitcher;
private ImageSwitcher switchUnten;
private Button btnPrw;
private  Button btnNxt;
private  Button btnPrwU;
private  Button btnNxtU;
private Button acept;
private Spinner styleSelected;
private Outfit mOutfit;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Matchen");

        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        btnPrw = (Button) findViewById(R.id.button);
        btnNxt = (Button) findViewById(R.id.buttonNxt);
        btnPrwU = (Button) findViewById(R.id.buttonPrwUnten);
        btnNxtU = (Button) findViewById(R.id.buttonNxtUnten);
        acept = (Button) findViewById(R.id.accept);

         myImageSwitcher = (ImageSwitcher) findViewById(R.id.switchOben);
        myImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {

// Create a new ImageView and set it's properties
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });

        switchUnten = (ImageSwitcher) findViewById(R.id.switchUnten);
        switchUnten.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {

// Create a new ImageView and set it's properties
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });
// Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked

        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        // set the animation type to ImageSwitcher
        myImageSwitcher.setInAnimation(in);
        myImageSwitcher.setOutAnimation(out);
        switchUnten.setInAnimation(in);
        switchUnten.setOutAnimation(out);

        styleSelected = (Spinner) findViewById(R.id.styleWahl);
        styleSelected.setOnItemSelectedListener(this);
        chooseStyle();


        obenSwitch();
        untenSwitch();
        choosedMatchen();


    }

    private void chooseStyle(){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.style_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        styleSelected.setAdapter(adapter);

    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {

        mOutfit= new Outfit(styleSelected.getSelectedItem().toString());
        Drawable drawable =new BitmapDrawable(BitmapFactory.decodeFile(mOutfit.getUpperPart().getPath()));
        myImageSwitcher.setImageDrawable(drawable);
       // myImageSwitcher.setImageResource(R.drawable.px800_tshirt_icon);
         Drawable drawable1 = new BitmapDrawable(BitmapFactory.decodeFile(mOutfit.getLowerPart().getPath()));
        switchUnten.setImageDrawable(drawable1);
        // Toast.makeText(getApplicationContext(), pos, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    private void obenSwitch(){

        btnPrw.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         Drawable drawable =new BitmapDrawable(BitmapFactory.decodeFile(mOutfit.showPrwUpperPart().getPath()));
         myImageSwitcher.setImageDrawable(drawable); // set the image in ImageSwitcher


            }
        });


        //Nächste Teil anzeigen
        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setRankDown(mOutfit.getUpperPart());
                //Nächste Bitmap von der Liste speichern
             Drawable drawable = new BitmapDrawable(BitmapFactory.decodeFile(mOutfit.showNextUpperPart().getPath()));
               //Den Bitmap in den ImageSwitcher zuordnen
            myImageSwitcher.setImageDrawable(drawable);
            }
        });
    }

    private void untenSwitch(){
        btnPrwU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Drawable drawable = new BitmapDrawable(BitmapFactory.decodeFile(mOutfit.showPrwLowerPart().getPath()));
               switchUnten.setImageDrawable(drawable);
            }
        });

        btnNxtU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRankDown(mOutfit.getLowerPart());
               Drawable drawable = new BitmapDrawable(BitmapFactory.decodeFile(mOutfit.showNextLowerPart().getPath()));
               switchUnten.setImageDrawable(drawable);
            }
        });
    }

    private void choosedMatchen(){

        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRankUp(mOutfit.getLowerPart());
                setRankUp(mOutfit.getUpperPart());
                Toast.makeText(getApplicationContext(), "Outfit gewählt", Toast.LENGTH_LONG).show();
                Intent callMain = new Intent(Matchen.this, MainActivity.class);
                startActivity(callMain);

            }
        });

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
                return true;

        }
        return true;
    }

}
