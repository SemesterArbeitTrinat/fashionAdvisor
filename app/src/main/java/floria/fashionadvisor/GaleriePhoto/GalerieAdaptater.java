package floria.fashionadvisor.GaleriePhoto;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import floria.fashionadvisor.Galerie;
import floria.fashionadvisor.MainActivity;
import floria.fashionadvisor.R;
import floria.fashionadvisor.database.DB;
import floria.fashionadvisor.database.DBDataSource;
import floria.fashionadvisor.tomsc.decisiontree.Item;
import static floria.fashionadvisor.MainActivity.database;

/**
 * Created by flori on 20/05/2018.
 */

public class GalerieAdaptater extends ArrayAdapter {


    private Context context;
    private LayoutInflater inflter;
    private DBDataSource dataBank;


    ArrayList<Item> alleCatItem = new ArrayList<>();


    public GalerieAdaptater(Context applicationContext, int resource, ArrayList objects) {

        super(applicationContext, resource, objects);
        alleCatItem=objects;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //view = inflter.inflate(R.layout.activity_gv, null);
        //final CheckedTextView mTextV = (CheckedTextView) view.findViewById(R.id.mTextV);
        View v = convertView;
        v=inflter.inflate(R.layout.favoris_fragment,null);
        dataBank = new DBDataSource(getContext());
        dataBank.open();
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
       // imageView.setImageResource(R.drawable.ic_launcher_background);
        final ToggleButton favC= (ToggleButton) v.findViewById(R.id.radioButton);

        //Bitmap von Datenbank
        // alleCatItem ist die Liste mit allen Item durch Kategorie sortiert
       // Drawable drawable = new BitmapDrawable(alleCatItem.get(position).getBitmaph());
        Drawable drawable= new BitmapDrawable(BitmapFactory.decodeFile(alleCatItem.get(position).getPath()));
        imageView.setImageDrawable(drawable);
        /*fav(int favorite, ToogleButton favC) ist um die richtige Zustand von Favoriten
             an die ToogleButton zugeben */
        fav(alleCatItem.get(position).getFavourite(),favC);
        favC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
         int indiz=alleCatItem.get(position).getFavourite();//Der Zustand zu favorite
         switch (indiz){
             case 0:
                 //Erst wird die Datenbank verändert
                 dataBank.updateDBfav(alleCatItem.get(position).getId(),1);
                 /*Dann wird den List<Item> verändert (Sonnst muss erste ein andere Fragment
                    anrufen um den Ansicht zu verändern */
                 alleCatItem.get(position).setFavourite(1);
                 break;
             case 1:
                 dataBank.updateDBfav(alleCatItem.get(position).getId() ,0);
                 alleCatItem.get(position).setFavourite(0);
                 default:
                     break;
         }


            }
            });
        Button deletes= (Button) v.findViewById(R.id.button2);
        deletes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataBank.deleteDB(alleCatItem.get(position).getId());

                Toast.makeText(getContext(), "Gelöscht", Toast.LENGTH_LONG).show();
            }
        });
        //Drawable drawable = new BitmapDrawable(alleCatItem.get(position).getBitmaph());
        //imageView.setImageDrawable(drawable);

      //  dataBank.close();
        return v;

    }

private void fav (int favChek, ToggleButton favC){

        switch (favChek){
            case 0:
                favC.setChecked(false);

                break;
            case 1:
                favC.setChecked(true);
                break;
            default:
                break;

        }


}






}
