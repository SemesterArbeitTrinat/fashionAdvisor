package floria.fashionadvisor.database;

/**
 * Created by Seehund on 04.05.2018.
 *
 * */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import floria.fashionadvisor.MainActivity;
import floria.fashionadvisor.R;
import floria.fashionadvisor.tomsc.decisiontree.ClothingAttributes;
import floria.fashionadvisor.tomsc.decisiontree.ClothingPartList;


public class TestDB extends AppCompatActivity {

  public static final String LOG_TAG = MainActivity.class.getSimpleName();

  private DBDataSource dataSource;
  public ListView mDBListView;

  protected void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    //setContentView(R.layout.testdb);

    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    //toolbar.setTitle("TestDB");

    //definir notre toolbar en tant qu'actionBar
    //setSupportActionBar(toolbar);


    Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
    dataSource = new DBDataSource(this);
    dataSource.open();


    dataSource.addDB(1, 1, 1, 1, 1, 1, 1, "1");
    dataSource.addDB(2, 2, 2, 2, 2, 2, 2, "2");
    dataSource.addinTAB_Farbe("BLAU");
    dataSource.addinTAB_Farbe("Pink");

  }

  private void getUpperPart()
  {
    List<String> color = new ArrayList<>();
    color.add("BLAU");
    List<String> cut = new ArrayList<>();
    cut.add("1");
    List<String> style = new ArrayList<>();
    style.add("ausgang");
    ClothingPartList upperPart = new ClothingPartList(new ClothingAttributes(color, style, cut));
  }




  @Override
  protected void onResume(){
    super.onResume();
    Log.d(LOG_TAG, "Die Datenquelle wird geÃ¶ffnet.");
    dataSource.open();

    Log.d(LOG_TAG, "Folgende EintrrÃ¤ge sind in der Datenbank:");
    // hm
    showAllListEntries();

    Log.d(LOG_TAG, "upperPart besteht aus:");
    getUpperPart();

  }

  @Override
  protected void onPause() {
    super.onPause();

    Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
    dataSource.close();
  }

  private void showAllListEntries () {
    List<DB> DBList = dataSource.getAllDB();
    List FarbeList=dataSource.getAllFarbe();

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
