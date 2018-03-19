package com.example.thanatos.appbooksqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import static com.example.thanatos.appbooksqlite.BookReaderContract.SQL_CREATE_ENTRIES;
import static com.example.thanatos.appbooksqlite.BookReaderContract.SQL_DELETE_ENTRIES;
/**
 * Created by Thanatos on 26/2/2018.
 */

public class BookReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BookBD.db";

    public BookReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        //create books table
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //drop older books table if existed
        db.execSQL(SQL_DELETE_ENTRIES);

        // create fresh books table
        this.onCreate(db);
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over db.execSQL(SQL_DELETE_ENTRIES); onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


    //Método Agregar un libro
    public void addBook(Book book)
    {
        //for logging
        Log.d("addBook", book.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 3. insert
        db.insert(BookReaderContract.FeedBook.TABLE_NAME, // table
                null, //nullColumnHack
                book.toContentValues()); // key/value -> keys = column names/ values = column values

        //     4. close
        db.close();

    }

    //Obtener la lista de libros
    public List<Book> getAllBooks()
    {
        List<Book> books = new LinkedList<Book>();

        //1. Query para la consulta
        String query = "Select * FROM "+ BookReaderContract.FeedBook.TABLE_NAME;

        //2. Obtener la referencia a la DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //3. Recorrer el resultado y crear un objeto Book
        Book book = null;
        if(cursor.moveToFirst()){
            do{
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                books.add(book);
            }while(cursor.moveToNext());
        }
        Log.d("getAllBooks",books.toString());
        return books;
    }


    //Buscar un libro
    public Book getBook(int id)
    {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(BookReaderContract.FeedBook.TABLE_NAME, // a. table
                        new String[]{BookReaderContract.FeedBook._ID, BookReaderContract.FeedBook.COLUMN_NAME_TITLE, BookReaderContract.FeedBook.COLUMN_NAME_AUTHOR}, // b. column names
                        BookReaderContract.FeedBook._ID +" = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having null,
                        null,// g. order by
                        null); // h. limit

// 3. if we got results get the first one
        Book book =null;
        if (cursor != null) {
            cursor.moveToFirst();

// 4. build book object
            book = new Book();
            book.setId(Integer.parseInt(cursor.getString(0)));
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));

            Log.d("getBook(" + id + ")", book.toString());
        }else{
            Log.d("getBook(" + id + ")", "Not found");
        }

// 5. return book
        return book;

    }


    // Actualizar un libro
    public int updateBook(Book book)
    {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //3. updating row
        int i = db.update(BookReaderContract.FeedBook.TABLE_NAME,//table
                book.toContentValues(), // column/value
                BookReaderContract.FeedBook._ID +" = ?", // selections
                new String[] { String.valueOf(book.getId()) });
        //selection args

        // 4. close
        db.close();
        return i;

    }


    // Deleting single book
    public void deleteBook(Book book)
    {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(BookReaderContract.FeedBook.TABLE_NAME,
                BookReaderContract.FeedBook._ID+" = ?",
                new String[] {
                String.valueOf(book.getId()) });
        // 3. close
        db.close();

        Log.d("deleteBook", book.toString());

    }


}
