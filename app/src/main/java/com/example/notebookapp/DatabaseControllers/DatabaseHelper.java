package com.example.notebookapp.DatabaseControllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notebookapp.Bean.Note;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NOTESDB.db";
    private static final String NOTE_TABLE = "Notes_table";
    private static final String COLUMN_NOTE_ID = "ID";
    private static final String COLUMN_NOTE_TITLE = "TITLE";
    private static final String COLUMN_NOTE_TEXT = "NOTE";
    private static final String COLUMN_NOTE_NEWS_URL = "NOTE_NEWS_URL";
    private static final String COLUMN_NOTE_DATE_CREATED = "DATE_CREATED";
    public static final String COLUMN_NOTE_DATE_MODIFIED = "DATE_MODIFIED";

    private SQLiteDatabase notesDatabase;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NOTE_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE" +
                " TEXT, NOTE TEXT, DATE_CREATED TEXT, DATE_MODIFIED TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
        onCreate(db);
    }

    public boolean insertNote(Note note){
        notesDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        contentValues.put(COLUMN_NOTE_TEXT, note.getNote());
        contentValues.put(COLUMN_NOTE_DATE_CREATED, note.getCurrentDateOfCreation());
        long result = notesDatabase.insert(NOTE_TABLE, null, contentValues);
        closeDb();


        return result != -1;

    }



    private synchronized void closeDb(){
        if(notesDatabase != null){
            notesDatabase.close();
        }
    }

    public boolean updateNote(Note note){
        SQLiteDatabase notesDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NOTE_NEWS_URL, note.getNewsUrlList().toString());

        String[] whereArgs = {String.valueOf(note.getNoteId())};

        int count = notesDatabase.update(NOTE_TABLE, contentValues, COLUMN_NOTE_ID +" =? ",  whereArgs);

        notesDatabase.close();

        return count != -1;
    }

    public boolean deleteNote(Note note){
        SQLiteDatabase notesDatabase = this.getReadableDatabase();
        String[] whereArgs = {String.valueOf(note.getNoteId())};

        int count = notesDatabase.delete(NOTE_TABLE, COLUMN_NOTE_ID +" =? ", whereArgs);

        notesDatabase.close();

        return count != -1;
    }
}
