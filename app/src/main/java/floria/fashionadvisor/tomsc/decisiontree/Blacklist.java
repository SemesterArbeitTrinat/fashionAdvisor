package floria.fashionadvisor.tomsc.decisiontree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomsc on 10.05.2018.
 */

public class Blacklist {

    /**
     * Add entry to the blacklist table in the database
     * @param blacklistEntry
     */
    public static void addBlacklistentry(String blacklistEntry)
    {

    }

    /**
     * Remove entry from the blacklist in the database
     * @param blacklistentry
     */
    public static void removeBlacklistEntry(String blacklistentry)
    {

    }

    public static void addBlacklistAttributes(String[] attributes)
    {
        String[] blacklistEntries/**= SQL Query load blacklistentries*/;
        String[] all_colors/**= SQL Query load all colors*/;
        String[] all_cuts/**=SQL Query load all cuts*/;
        String[] all_styler/** =SQL Query load all styles*/;
        Item item = new Item();

        /** if blacklistEntries.sameColorBlock == true
         * {
         *      item.setColor(all_colors);
         *      item.removeColorEntry(attributes.color);
         *
         *
         */
    }

    public static List<ClothingAttributes> addBlacklistAttributes(Item item)
    {
        List<ClothingAttributes> blacklistAttributes_added = new ArrayList<>();

        /** SQL get all clothing = same style and != same topcategory and eventually same color
         * Ex:
         * lowerPart = lowerPart, jeans, pants, long, (ausgang, freizeit)
         * -> upperPart = upperPart, != jeans, " ", " ", " ", (ausgang, freizeit)
         */

        return blacklistAttributes_added;
    }
}
