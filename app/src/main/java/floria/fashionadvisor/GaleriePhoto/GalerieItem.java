package floria.fashionadvisor.GaleriePhoto;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import floria.fashionadvisor.R;
import floria.fashionadvisor.database.DBOpenHelper;
import floria.fashionadvisor.tomsc.decisiontree.Item;

import static floria.fashionadvisor.MainActivity.database;
import static java.lang.String.valueOf;

/**
 * Created by flori on 22/03/2018.
 */

public class GalerieItem extends Fragment {

    int index;
    private String nameCat;

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public GalerieItem() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tee_shirt_frag, container, false);
        GridView mGv = (GridView) v.findViewById(R.id.GVgalerie);
        GalerieAdaptater mGalerieAdaptater = new GalerieAdaptater(this.getContext(),R.layout.favoris_fragment,allenItem(nameCat));
        mGv.setAdapter(mGalerieAdaptater);

        return v;
    }



    private ArrayList<Item> allenItem(String categorie){

        ArrayList<Item> alleItem = new ArrayList<>();

        SQLiteDatabase db = database;
        String table = DBOpenHelper.TABLE_NAME_SAMMLUNG;
        String[] columns = {DBOpenHelper._ID,                           //Integer
                DBOpenHelper.SAMMLUNG_SCHNITT,
                DBOpenHelper.SAMMLUNG_BEZEICHNUNG,
                DBOpenHelper.SAMMLUNG_FARBE,
                DBOpenHelper.SAMMLUNG_KATEGORIE,
                DBOpenHelper.SAMMLUNG_STIL,
                DBOpenHelper.SAMMLUNG_RANK,                 //Integer
                DBOpenHelper.SAMMLUNG_FOTO,                 //BLOB
                DBOpenHelper.SAMMLUNG_FAVORIT,              //Integer
        };

        String groupBy = null; //DBOpenHelper._ID;
        String having = null;
        String orderBy =null; //DBOpenHelper._ID+" DESC";
        String limit = null;//"10";
        String selection;
        String[] selectionArgs=new String[1];

        if (categorie.equals("Fav")){
             selection = DBOpenHelper.SAMMLUNG_FAVORIT + "=?";
             selectionArgs[0]=valueOf("1");

             }
        else if (categorie.equals("Alle")){

            selection = null;
            selectionArgs=null;
        }
        else{
            selection = DBOpenHelper.SAMMLUNG_BEZEICHNUNG + "=?";
            selectionArgs[0]= categorie;
        }

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id, rank, favourite;
            String cut, name, color, category, style_fromDB_String,path;
            String[] style_fromDB_Array;


            id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper._ID));
            rank = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_RANK));
            favourite = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FAVORIT));
            cut = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_SCHNITT));
            name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_BEZEICHNUNG));
            color = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FARBE));
            category = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_KATEGORIE));
            style_fromDB_String = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_STIL));
            path = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FOTO));

            style_fromDB_Array = style_fromDB_String.split("\\|");

         //   Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
           // Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.ic_launcher_background);


            //Item(String name, String cut, String topcategory, String color, String[] style, Bitmap bitmap, int rank, int id, int favourite
            Item item = new Item(name, cut, category, color, style_fromDB_Array, path, rank, id, favourite);
            alleItem.add(item);
            cursor.moveToNext();
        }

cursor.close();
        return alleItem;

    }
}
