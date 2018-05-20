package floria.fashionadvisor.tomsc.decisiontree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomsc on 10.05.2018.
 */

public class Outfit {

    private Item upperPart;
    private Item lowerPart;
    private int lenghtListUnten;
    private int lenghtListOben;

    private ClothingPartList upperPartQueue;
    private ClothingPartList lowerPartQueue;


    public int getLenghtListUnten() {
        return lenghtListUnten;
    }
    public int getLenghtListOben() {
        return lenghtListOben;
    }

     public Outfit(String style)
    {
        //ClothingAttributes attributes = new ClothingAttributes(color);          //add Arraylist to attributes

        this.lowerPartQueue = new ClothingPartList(style, "unterteil");                  //get a lowerPartQueue with DecisionTreeLowerPart
        this.upperPartQueue = new ClothingPartList(style, "oberteil");
        this.lowerPart = lowerPartQueue.getItem(0);                                             // put first item of lowerPartQueue as lowerPart
        this.upperPart = upperPartQueue.getItem(0);
        lenghtListUnten = lowerPartQueue.size();
        lenghtListOben= upperPartQueue.size();
    }


    public Item getUpperPart() {
        return upperPart;
    }

    public void setUpperPart(Item upperPart) {
        this.upperPart = upperPart;
    }

    public Item getLowerPart() {
        return lowerPart;
    }

    public void setLowerPart(Item lowerPart) {
        this.lowerPart = lowerPart;
    }


    public Item showNextUpperPart() {
        int key = upperPartQueue.getClothingPartList().indexOf(upperPart);
        if (lenghtListOben==key) {
            setUpperPart(upperPartQueue.getClothingPartList().get(0));
        }
        else{setUpperPart(upperPartQueue.getClothingPartList().get(key + 1));}
        return upperPart;
    }

    public Item showNextLowerPart() {
        int key = lowerPartQueue.getClothingPartList().indexOf(lowerPart);
        if (lenghtListUnten==key) {
            setLowerPart(lowerPartQueue.getClothingPartList().get(0));
        }
        else{setLowerPart(lowerPartQueue.getClothingPartList().get(key + 1));}
        return lowerPart;
    }
    public Item showPrwUpperPart() {
        int key = upperPartQueue.getClothingPartList().indexOf(upperPart);
        if (key==0) {
            setUpperPart(upperPartQueue.getClothingPartList().get(lenghtListOben));
        }
        else{setUpperPart(upperPartQueue.getClothingPartList().get(key - 1));}
        return upperPart;
    }

    public Item showPrwLowerPart() {
        int key = lowerPartQueue.getClothingPartList().indexOf(lowerPart);
        if (key==0) {
            setLowerPart(lowerPartQueue.getClothingPartList().get(lenghtListUnten));
        }
        else{setLowerPart(lowerPartQueue.getClothingPartList().get(key - 1));}
        return lowerPart;
    }







}
