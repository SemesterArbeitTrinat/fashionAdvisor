package floria.fashionadvisor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by flori on 13/03/2018.
 */

public class NeuAufnehmen extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neuaufnehmen);

        Toolbar toolbar =  findViewById(R.id.toolbar);

        toolbar.setTitle("Photo");

        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);


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
                NeuAufnehmen.this.finish();
                return true;

        }
        return true;
    }
}
