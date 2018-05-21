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
// Inflate the layout for this fragment
      /*  switch (index){
            case 0:
                return inflater.inflate(R.layout.favoris_fragment, container, false);
            case 1:

                return inflater.inflate(R.layout.tee_shirt_frag, container, false);
            case 2:
                return inflater.inflate(R.layout.favoris_fragment, container, false);
            case 3:
                return inflater.inflate(R.layout.favoris_fragment, container, false);
            case 4:
                return inflater.inflate(R.layout.tee_shirt_frag, container, false);
            case 5:
                return inflater.inflate(R.layout.favoris_fragment, container, false);
            default:
                return null;
        }
       // return inflater.inflate(R.layout.favoris_fragment, container, false);
    }*/
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
        String selecHelp;
        String[] selectionArgs=new String[1];

        if (categorie.equals("Fav")){
             selection = DBOpenHelper.SAMMLUNG_FAVORIT + "=?";
             selecHelp="0";

             }
        else if (categorie.equals("Alle")){

            selection = DBOpenHelper._ID+"=?";
            selecHelp ="_";

        }
        else{
            selection = DBOpenHelper.SAMMLUNG_BEZEICHNUNG + "=?";
            selecHelp= categorie;
        }
        selectionArgs[0]=selecHelp;
        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id, rank, favourite;
            String cut, name, color, category, style_fromDB_String;
            String[] style_fromDB_Array;
            byte[] image;

            id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper._ID));
            rank = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_RANK));
            favourite = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FAVORIT));
            cut = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_SCHNITT));
            name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_BEZEICHNUNG));
            color = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FARBE));
            category = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_KATEGORIE));
            style_fromDB_String = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_STIL));
            image = cursor.getBlob(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FOTO));

            style_fromDB_Array = style_fromDB_String.split("\\|");

            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);


            //Item(String name, String cut, String topcategory, String color, String[] style, Bitmap bitmap, int rank, int id, int favourite
            Item item = new Item(name, cut, category, color, style_fromDB_Array, bitmap, rank, id, favourite);
            alleItem.add(item);
            cursor.moveToNext();
        }

cursor.close();
        return alleItem;

    }
}
