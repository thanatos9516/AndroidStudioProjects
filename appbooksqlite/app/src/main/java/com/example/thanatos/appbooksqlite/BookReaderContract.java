package com.example.thanatos.appbooksqlite;

import android.provider.BaseColumns;

/**
 * Created by Thanatos on 26/2/2018.
 */

public class BookReaderContract
{
    public BookReaderContract(){}

    public static class FeedBook implements BaseColumns
    {
        public static final String TABLE_NAME = "book";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AUTHOR = "autor" ;
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedBook.TABLE_NAME + " (" +
                    FeedBook._ID + " INTEGER PRIMARY KEY," +
                    FeedBook.COLUMN_NAME_TITLE + " TEXT," +
                    FeedBook.COLUMN_NAME_AUTHOR + " TEXT)" ;

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedBook.TABLE_NAME;
}