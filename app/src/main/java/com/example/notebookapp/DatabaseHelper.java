package com.example.notebookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "NOTESDB.db";
    public static final String TABLE_NAME = "Notes_table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "TITLE";
    public static final String COLUMN_3 = "NOTE";
    public static final String COLUMN_4 = "DATE_CREATED";
    public static final String COLUMN_5 = "DATE_MODIFIED";
    public static final String COLUMN_6 = "NEWS_URL";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE" +
                " TEXT, NOTE TEXT, DATE_CREATED TEXT, DATE_MODIFIED TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, note.getNoteTitle());
        contentValues.put(COLUMN_3, note.getNote());
        contentValues.put(COLUMN_4, note.getCurrentDateOfCreation());
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }
        else return true;
    }
}
