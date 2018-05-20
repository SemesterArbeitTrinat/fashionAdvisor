package floria.fashionadvisor.tomsc.decisiontree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tomsc on 10.05.2018.
 */

public class ChooseProbability {


    private static final int ADDING_NUMBER=25;
    private static final int EXPONENT=2;
    private static final int MULTIPLICATOR=100;

    /**
     * Randomize the rank from the databse with the variables above
     * @return int rank
     */

    private static int randomizeRank(int rank)
    {
        double randomized_double = Math.pow((rank + ADDING_NUMBER), EXPONENT) * Math.random() * MULTIPLICATOR;
        //(rank + ADDING_NUMBER)^EXPONENT * Random *MULTIPLICATOR
        int randomized = (int)randomized_double;
        return randomized;

    }


    /**
     * Method to sort the ranks of the items from maximum rank to minimum rank
     * @return
     */
    protected static List<Item> sortMaxMin (List<Item> list) {

        List<Item> listByRandomizedRank = new ArrayList<>();
        for (int i = 0; i<list.size(); i++)
        {
            Item item = list.get(i);
            int rank = item.getRank();
            item.setRandomizedRank(randomizeRank(rank));
            listByRandomizedRank.add(item);
        }

        Collections.sort(listByRandomizedRank);
        return listByRandomizedRank;




    }



}
