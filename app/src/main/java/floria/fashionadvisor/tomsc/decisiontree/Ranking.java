package floria.fashionadvisor.tomsc.decisiontree;


/**
 * Created by tomsc on 10.05.2018.
 */

public class Ranking {


    /**
     * Method to set the new rank depending on whether the user chooses the clothing part (+1) or not (-1)
     * @param id
     */
    public static void setRankUp(int id)
    {
        //int UpperPart = com.example.tomsc.decisiontree.OutfitPresentation.getCurrentListedUpperPart();
        //int LowerPart = com.example.tomsc.decisiontree.OutfitPresentation.getCurrentListedUpperPart();

        int oldRankUpperPart/** = SQLstatement(get rank upper part ID)*/;
        int oldRankLowerPart/** = SQLstatement(get rank lower_part ID)*/;

        SQLstatement.sqlPush("/** SQL Statement oldRankUpperPart + 1*/ ");
        SQLstatement.sqlPush("/** SQL Statement oldRankLowerPart + 1*/ ");
    }

    public static void setRankDown(int id)
    {

        int oldRankUpperPart/** = SQLstatement(get rank upper part ID)*/;
        int oldRankLowerPart/** = SQLstatement(get rank lower_part ID)*/;

        SQLstatement.sqlPush("/** SQL Statement oldRankUpperPart - 1*/ ");
        SQLstatement.sqlPush("/** SQL Statement oldRankLowerPart - 1*/ ");
    }

}
