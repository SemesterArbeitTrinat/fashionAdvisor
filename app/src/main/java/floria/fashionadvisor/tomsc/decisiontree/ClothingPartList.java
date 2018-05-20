package floria.fashionadvisor.tomsc.decisiontree;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import floria.fashionadvisor.database.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static floria.fashionadvisor.MainActivity.database;
import static floria.fashionadvisor.tomsc.decisiontree.ChooseProbability.sortMaxMin;
import floria.fashionadvisor.byteArrayPhoto.SavePhotoTask;

/**
 * Created by tomsc on 10.05.2018.
 */

public class ClothingPartList {

    /**
     * Method to get a new List of clothing Items
     * @param style
     * @return randomizedList_sorted
     */

    private List<Item> clothingPartList;

    public ClothingPartList (String style, String kategorie)
    {


        List<Item> unrandomizedList = new ArrayList<>();

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
        String selection = DBOpenHelper.SAMMLUNG_STIL + "=? AND " + DBOpenHelper.SAMMLUNG_KATEGORIE + "=? ";
        String[] selectionArgs = {style, kategorie};

        String groupBy = null;
        String having = null;
        String orderBy = null;                                              //"bezeichnung DESC";
        String limit = null;//"10";

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
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
            unrandomizedList.add(item);
            cursor.moveToNext();
        }

        cursor.close();

        List<Item> randomizedList_sorted;

        randomizedList_sorted = sortMaxMin(unrandomizedList);

        this.clothingPartList = randomizedList_sorted;
    }




    public Item getItem(int index)
    {
        return clothingPartList.get(index);
    }

    public int size ()
    {
        return clothingPartList.size();
    }






    public List<Item> getClothingPartList() {
        return clothingPartList;
    }

    public void setClothingPartList(List<Item> clothingPartList) {
        this.clothingPartList = clothingPartList;
    }
}

