package floria.fashionadvisor.tomsc.decisiontree;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import floria.fashionadvisor.database.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static floria.fashionadvisor.database.DBDataSource.database;
import static floria.fashionadvisor.tomsc.decisiontree.ChooseProbability.sortMaxMin;

/**
 * Created by tomsc on 10.05.2018.
 */

public class ClothingPartList {

    private List<Item> clothingPartList;




    /**
     * Get ClothingPart based on a list of attributes
     * @param style
     */
    public ClothingPartList(String style)
    {

        List<Item> result = new ArrayList<>();
        //String selection = DBOpenHelper.TABLE_NAME_FARBE.FARBE_FARBE;
        //String[] selectionArgs = color.toArray();
        SQLiteDatabase db = database;



        String table = DBOpenHelper.TABLE_NAME_SAMMLUNG;
        String[] columns = {"_id", DBOpenHelper.SAMMLUNG_FARBE, DBOpenHelper.SAMMLUNG_SCHNITT, }; //"Stil", "Bezeichnung", "Farbe", "Schnitt", "Rank", "Favorit", "Kategorie"};
        String selection = "";
        String[] selectionArgs = {""};
        if (style != "")
        {
            selection += DBOpenHelper.STIL_STIL + "=?";
            selectionArgs[0] = style;
        }

        String groupBy = null;
        String having = null;
        String orderBy = null;                                              //"bezeichnung DESC";
        String limit = null;//"10";

        //Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        //String[] columns = {"Farbe"};
        Cursor cursor = db.query(DBOpenHelper.TABLE_NAME_SAMMLUNG, columns, selection, selectionArgs, having, orderBy, limit);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int name_id, cut, color, style_values;
            String name;
            String[] style_db;
            int rank, id, favourite, topcategory;
            //Uri image_path = new Uri.Builder();
            name_id = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_BEZEICHNUNG);
            name = cursor.getString(name_id);
            cut = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_SCHNITT);
            color = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FARBE);
            //style_values = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_STIL);

            //String name_ =cursor.getString(name);
            //String cut_ = cursor.getString(cut);
            //String color_ = cursor.getString(color);

            //style = style_values.split("\\|");                                                            //split values at |

            //id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper._ID));

            /*
            //Get image from BLOB String, convert it to BMP and save it
            byte[] byteArray = cursor.getColumnIndex(DBOpenHelper.FOTO_FOTO);
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            File file = new File(Environment.getExternalStorageDirectory().getPath().toString() + "/ImageDB/" + Integer.toString(id) +"_image" + ".png");
            String path = file.getAbsolutePath();
            image_path = Uri.parse(path);
            ImageIO.write(file, "BMP", new File("filename.bmp"))

            /*
            FileOutputStream filecon = new FileOutputStream(file);
            try {
                sampleResized.compress(Bitmap.CompressFormat.JPEG, 90, filecon);
            } catch (exception e) {
            }
            filecon.close;
            */

            //rank = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_RANK));

            //topcategory = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_KATEGORIE));
            //favourite = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FAVORIT));


            Item item = new Item(name, Integer.toString(cut), null, Integer.toString(color), null, null, 0, 5, 0);
            result.add(item);
            cursor.moveToNext();
        }
        cursor.close();








        List<Item> lowerPartQueue = new ArrayList<>();
        List<Item> sortedByRandomizedRank_lowerPartQueue = sortMaxMin(lowerPartQueue);              //sort min max by giving a List to ChooseProbability and request a List back
        this.clothingPartList = sortedByRandomizedRank_lowerPartQueue;                           //get rank and randomized order of the clothing
    }




    /**
     * get ClothingPart based on previous clothingPart, usually UpperPart
     * @param firstPart
     */

    public ClothingPartList(Item firstPart)
    {

        List<Item> secondPartQueue = new ArrayList<>();
        List<Item> sortedByRandomizedRank_secondPartQueue = sortMaxMin(secondPartQueue);            //sort min max by giving a List to ChooseProbability and request a List back
        this.clothingPartList = sortedByRandomizedRank_secondPartQueue;                           //get rank and randomized order of the clothing

    }


    //get all clothing parts with color == blue, style == freizeit, topcategory == lowerPart
    public List<Item> getClothingPart(ClothingAttributes clothingAttributes)
    {

        List<Item> result = new ArrayList<>();
        //String selection = DBOpenHelper.TABLE_NAME_FARBE.FARBE_FARBE;
        //String[] selectionArgs = color.toArray();
        SQLiteDatabase db = database;



        String table = "TABLE_NAME_SAMMLUNG";
        String[] columns = {"_id", "FARBE_FARBE", "SCHNITT_SCHNITT", }; //"Stil", "Bezeichnung", "Farbe", "Schnitt", "Rank", "Favorit", "Kategorie"};
        String selection = "";
        String[] selectionArgs = {"", "", ""};
        if (clothingAttributes.color.toString() != "")
        {
            selection += "TABLE_FARBE=? OR ";
            selectionArgs[0] = clothingAttributes.color.toString();
        }
        if (clothingAttributes.cut.toString() != "")
        {
            selection += "TABLE_SCHNITT=? OR ";
            selectionArgs[1] = clothingAttributes.cut.toString();
        }
        if (clothingAttributes.style.toString() != "")
        {
            selection += "TABLE_STIL=?";
            selectionArgs[2] = clothingAttributes.style.toString();
        }

        String groupBy = null;
        String having = null;
        String orderBy = null;                                              //"bezeichnung DESC";
        String limit = null;//"10";

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);


        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String name, cut, color, style_values;
            String[] style;
            int rank, id, favourite, topcategory;
            //Uri image_path = new Uri.Builder();
            name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.BEZEICHNUNG_BEZEICHNUNG));
            cut = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SCHNITT_SCHNITT));
            color = cursor.getString(cursor.getColumnIndex(DBOpenHelper.FARBE_FARBE));
            style_values = cursor.getString(cursor.getColumnIndex(DBOpenHelper.STIL_STIL));
            style = style_values.split("\\|");                                                            //split values at |

            id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper._ID));

            /*
            //Get image from BLOB String, convert it to BMP and save it
            byte[] byteArray = cursor.getColumnIndex(DBOpenHelper.FOTO_FOTO);
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            File file = new File(Environment.getExternalStorageDirectory().getPath().toString() + "/ImageDB/" + Integer.toString(id) +"_image" + ".png");
            String path = file.getAbsolutePath();
            image_path = Uri.parse(path);
            ImageIO.write(file, "BMP", new File("filename.bmp"))

            /*
            FileOutputStream filecon = new FileOutputStream(file);
            try {
                sampleResized.compress(Bitmap.CompressFormat.JPEG, 90, filecon);
            } catch (exception e) {
            }
            filecon.close;
            */

            rank = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_RANK));

            topcategory = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_KATEGORIE));
            favourite = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FAVORIT));


            Item item = new Item(name, cut, Integer.toString(topcategory), color, style, null, rank, id, favourite);
            result.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return result;


    }










    public List<Item> getClothingPartList() {
        return clothingPartList;
    }

    public void setClothingPartList(List<Item> clothingPartList) {
        this.clothingPartList = clothingPartList;
    }
}

