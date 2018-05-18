package floria.fashionadvisor.tomsc.decisiontree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomsc on 10.05.2018.
 */

public class ClothingAttributes {
    protected List<String> color = new ArrayList<>();
    protected List<String> style = new ArrayList<>();
    protected List<String> cut = new ArrayList<>();

    public ClothingAttributes(List<String> color, List<String> style, List<String> cut) {
        this.color = color;
        this.style = style;
        this.cut = cut;
    }


    public String[] toArray(List<String> stringList)
    {
        String[] stringArray = new String[stringList.size()];
        stringArray = stringList.toArray(stringArray);

        return stringArray;
    }


}
