package floria.fashionadvisor.tomsc.decisiontree;

import android.net.Uri;
import android.graphics.Bitmap;

/**
 * Created by tomsc on 10.05.2018.
 */

public class Item implements Comparable<Item> {

    private String name, cut, topcategory;
    private String color;
    private String[] style = {"","","","",""};
    private Bitmap bitmap;
    private int rank, randomizedRank, id, favourite;


    public Item() {
        this.topcategory = "";
        this.cut = "";
        this.name = "";
        this.color = "";
        this.style[0] = "";
        this.favourite = 0;
        this.rank = 0;
        this.randomizedRank = 0;
        this.id = 0;
        this.bitmap = null;

    }

    public Item(String name, String cut, String topcategory, String color, String[] style, Bitmap bitmap, int rank, int id, int favourite) {
        this.topcategory = topcategory;
        this.cut = cut;
        this.name = name;
        this.color = color;
        this.style = style;
        this.favourite = favourite;
        this.rank = rank;
        this.randomizedRank = 0;
        this.id = id;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public String getTopcategory() {
        return topcategory;
    }

    public void setTopcategory(String topcategory) {
        this.topcategory = topcategory;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String[] getStyle() {
        return style;
    }

    public void setStyle(String[] style) {
        this.style = style;
    }

    public Bitmap getBitmaph() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRandomizedRank() {
        return randomizedRank;
    }

    public void setRandomizedRank(int randomizedRank) {
        this.randomizedRank = randomizedRank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public int compareTo(Item compareitem) {
        int compareRandomizedRank= compareitem.getRandomizedRank();
        /* For Ascending order*/
        return this.randomizedRank-compareRandomizedRank;


    }
}