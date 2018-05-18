package floria.fashionadvisor.tomsc.decisiontree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomsc on 10.05.2018.
 */

public class Outfit {

    private Item upperPart;
    private Item lowerPart;

    private ClothingPartList upperPartQueue;
    private ClothingPartList lowerPartQueue;



    public Outfit(String style)
    {
        //ClothingAttributes attributes = new ClothingAttributes(color);          //add Arraylist to attributes

        this.lowerPartQueue = new ClothingPartList(style);                  //get a lowerPartQueue with DecisionTreeLowerPart
        this.lowerPart = lowerPartQueue.getClothingPartList().get(0);                                             // put first item of lowerPartQueue as lowerPart
        this.upperPartQueue = new ClothingPartList(lowerPart);
        this.upperPart = upperPartQueue.getClothingPartList().get(0);



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
        setLowerPart(upperPartQueue.getClothingPartList().get(key + 1));
        return upperPart;
    }

    public Item showNextLowerPart() {
        int key = lowerPartQueue.getClothingPartList().indexOf(lowerPart);
        setLowerPart(lowerPartQueue.getClothingPartList().get(key + 1));
        return lowerPart;
    }






}
