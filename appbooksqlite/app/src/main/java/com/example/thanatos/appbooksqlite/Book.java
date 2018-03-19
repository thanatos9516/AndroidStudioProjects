package com.example.thanatos.appbooksqlite;

import android.content.ContentValues;
/**
 * Created by Thanatos on 26/2/2018.
 */

public class Book
{


    private int id;
    private String title;
    private String author;

    //Constructor sin parámetros
    public Book()
    {

    }

    //Constructor con parámetros
    public Book(int id, String title, String author)
    {
        this.id = id;
        this.title = title;
        this.author = author;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(BookReaderContract.FeedBook._ID, id);
        values.put(BookReaderContract.FeedBook.COLUMN_NAME_TITLE, title);
        values.put(BookReaderContract.FeedBook.COLUMN_NAME_AUTHOR, author);
        return values;
    }
}
