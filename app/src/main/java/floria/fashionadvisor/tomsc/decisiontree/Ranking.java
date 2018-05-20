package floria.fashionadvisor.tomsc.decisiontree;


import static floria.fashionadvisor.database.DBDataSource.database;


/**
 * Created by tomsc on 10.05.2018.
 */

public class Ranking {


    /**
     * Method to set the new rank depending on whether the user chooses the clothing part (+1) or not (-1)
     * @param item
     */
    public static void setRankUp(Item item) {

        int newRank;
        if (item.getRank() == 10) {
            newRank = 10;
        } else {
            newRank = item.getRank() + 1;
        }
        wirteRankToDatabase(item, newRank);

    }


    public static void setRankDown(Item item)
    {
        int newRank;
        if (item.getRank() == 1) {
            newRank = 1;
        } else {
            newRank = item.getRank() - 1;
        }
        wirteRankToDatabase(item, newRank);
    }

    private static void wirteRankToDatabase (Item item, int rank)
    {
        //SQL term to get insert into a row by ID
    }

}
