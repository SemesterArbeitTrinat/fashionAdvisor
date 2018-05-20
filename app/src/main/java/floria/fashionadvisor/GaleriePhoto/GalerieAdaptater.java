package floria.fashionadvisor.GaleriePhoto;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import floria.fashionadvisor.Galerie;
import floria.fashionadvisor.MainActivity;
import floria.fashionadvisor.R;
import floria.fashionadvisor.tomsc.decisiontree.Item;

/**
 * Created by flori on 20/05/2018.
 */

public class GalerieAdaptater extends ArrayAdapter {


    private Context context;
    private LayoutInflater inflter;

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
    public View getView(int position, View convertView, ViewGroup parent) {

        //view = inflter.inflate(R.layout.activity_gv, null);
        //final CheckedTextView mTextV = (CheckedTextView) view.findViewById(R.id.mTextV);
        View v = convertView;
        v=inflter.inflate(R.layout.favoris_fragment,null);
       // LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //v = inflater.inflate(R.layout.favoris_fragment, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_launcher_background);

        Button deletes= (Button) v.findViewById(R.id.button2);
        deletes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "Gelöscht", Toast.LENGTH_LONG).show();
            }
        });
        //Drawable drawable = new BitmapDrawable(alleCatItem.get(position).getBitmaph());
        //imageView.setImageDrawable(drawable);


        return v;

    }








}
