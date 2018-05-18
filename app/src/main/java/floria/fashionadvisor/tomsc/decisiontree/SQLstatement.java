package floria.fashionadvisor.tomsc.decisiontree;

/**
 * Created by tomsc on 10.05.2018.
 * Class to do all the SQLstatement with unified methods
 */

public class SQLstatement {


    /**
     * Method to pull data from the sql database
     * @return
     */
    public static void sqlPull()
    {
        /** Cursor dbCursor = db.query(table, columnsToReturn, selection, selectionArgs, null, null, null);
         *
         */


    }

    /**
     * Method to push data to the sql database
     * @param SQLstatement
     */
    public static void sqlPush(String SQLstatement)
    {

    }

/*
    SQLiteDatabase db = mDbHelper.getReadableDatabase();

    // Define a projection that specifies which columns from the database
// you will actually use after this query.
    String[] projection = {
            BaseColumns._ID,
            FeedEntry.COLUMN_NAME_TITLE,
            FeedEntry.COLUMN_NAME_SUBTITLE
    };

    // Filter results WHERE "title" = 'My Title'
    String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
    String[] selectionArgs = { "My Title" };

    // How you want the results sorted in the resulting Cursor
    String sortOrder =
            FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

    Cursor cursor;

    {
        cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
    }

*/
}
