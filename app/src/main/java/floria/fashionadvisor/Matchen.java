package floria.fashionadvisor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

/**
 * Created by floria on 13/03/2018.
 */

public class Matchen extends AppCompatActivity {
private int currentIndex=0;
private int count=3;
private ImageSwitcher myImageSwitcher;
private ImageSwitcher switchUnten;
private Button btnPrw;
private  Button btnNxt;
private Button btnPrwU;
private  Button btnNxtU;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Matchen");

        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        btnPrw = (Button) findViewById(R.id.button);
        btnNxt = (Button) findViewById(R.id.buttonNxt);
        btnPrwU = (Button) findViewById(R.id.button);
        btnNxtU = (Button) findViewById(R.id.buttonNxt);

        ImageSwitcher myImageSwitcher = (ImageSwitcher) findViewById(R.id.switchOben);
        myImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
// TODO Auto-generated method stub

// Create a new ImageView and set it's properties
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });

        ImageSwitcher switchUnten = (ImageSwitcher) findViewById(R.id.switchUnten);
        switchUnten.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
// TODO Auto-generated method stub

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
        obenSwitch();
        untenSwitch();


    }

    private void obenSwitch(){

        btnPrw.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex--;
                //  Check If index reaches maximum then reset it
                if (currentIndex < 0)
                    currentIndex = count;
                //  myImageSwitcher.setImageResource(imageIds[currentIndex]); // set the image in ImageSwitcher
            }
        });

        btnNxt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex++;
                //  Check If index reaches maximum then reset it
                if (currentIndex == count)
                    currentIndex = 0;
                //  myImageSwitcher.setImageResource(imageIds[currentIndex]); // set the image in ImageSwitcher
            }
        });
    }

    private void untenSwitch(){

        btnPrwU.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex--;
                //  Check If index reaches maximum then reset it
                if (currentIndex < 0)
                    currentIndex = count;
                //  myImageSwitcher.setImageResource(imageIds[currentIndex]); // set the image in ImageSwitcher
            }
        });

        btnNxtU.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex++;
                //  Check If index reaches maximum then reset it
                if (currentIndex == count)
                    currentIndex = 0;
                //  myImageSwitcher.setImageResource(imageIds[currentIndex]); // set the image in ImageSwitcher
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
