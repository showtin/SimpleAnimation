package com.example.todoapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "Notes.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_NAME = "notes_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create table in DB query
        String query = "create table " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER primary key autoincrement, "
                + COLUMN_TITLE + " text, "
                + COLUMN_DESCRIPTION + " text, "
                + COLUMN_DATE + " text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if this table was already created
        String query = "drop table if exists " + TABLE_NAME;
        db.execSQL(query);
    }

    /**
     * Method adding note to database
     *
     * @param title       note title
     * @param description note description
     * @param date        date the note was added
     */
    //TODO Make input data processing
    void addNote(String title, String description, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_DATE, date);


        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            System.out.println("failed");
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("added");

            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();

        }
    }

    Cursor getAllNotes() {
        String query = "Select * from notes_table";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }


}
