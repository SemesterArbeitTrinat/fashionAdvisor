package floria.fashionadvisor.database;

/**
 * Created by Seehund on 04.05.2018.
 *
 * */
import android.content.ContentValues;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import floria.fashionadvisor.database.DB;
import floria.fashionadvisor.database.DBDataSource;
import floria.fashionadvisor.MainActivity;
import floria.fashionadvisor.R;
import android.graphics.Bitmap;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TestDB extends AppCompatActivity{

  public static final String LOG_TAG = MainActivity.class.getSimpleName();

  private DBDataSource dataSource;
  public ListView mDBListView;

  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.testdb);

    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    //toolbar.setTitle("TestDB");

    //definir notre toolbar en tant qu'actionBar
    //setSupportActionBar(toolbar);


    Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
    dataSource = new DBDataSource(this);
    dataSource.open();


    Utilities bild= new Utilities();

    dataSource.addDB("Freizeit","T-Shirt","DUNKELPINK","KURZ",10,1,200,bild.getBytes(bild.pfad("storage/emulated/0/Download/Alphas_Dino.bmp")) ,"Oberteil");
    dataSource.addDB("Sport","Jogging","HELLPINK","LANG",2,1,2,bild.getBytes(bild.pfad("storage/emulated/0/Download/Alphas_Dino.bmp")), "Unterteil");
    dataSource.addDB("Sommer","Top","MITTELPINK","KURZ",1,0,1,bild.getBytes(bild.pfad("storage/emulated/0/Download/Alphas_Dino.bmp")), "Oberteil");

    //Unbedingt Bild auf dem Testgerät!!! - Berechtigung Speicherzugriff erteilen!
    //Einzeltest:
    //dataSource.insert(bild.getBytes(bild.pfad("storage/emulated/0/Download/Alphas_Dino.bmp")));

  }


  @Override
  protected void onResume(){
    super.onResume();
    Log.d(LOG_TAG, "Die Datenquelle wird geÃ¶ffnet.");
    dataSource.open();

    Log.d(LOG_TAG, "Folgende Eintraege sind in der Datenbank:");
    // hm
    showAllListEntries();


  }

  @Override
  protected void onPause() {
    super.onPause();

    Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
    dataSource.close();


//HALO TEST

  }

  private void showAllListEntries () {

    List<DB> DBList = dataSource.getAllDB();


   /* ArrayAdapter<DB> adapter = (ArrayAdapter<DB>) mDBListView.getAdapter();

    adapter.clear();
    adapter.addAll(DBList);
    adapter.notifyDataSetChanged();*/
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    //ajoute les entrÃ©es de menu_test Ã  l'ActionBar
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
