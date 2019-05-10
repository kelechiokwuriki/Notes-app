package com.example.notebookapp.DatabaseControllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.notebookapp.Bean.News;
import com.example.notebookapp.Bean.Note;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseReader extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "NOTESDB.db";
    public static final String NOTE_TABLE = "Notes_table";

    public DatabaseReader(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public List<String> getNoteTitles(){
        SQLiteDatabase notesDatabase = this.getReadableDatabase();
        SQLiteQueryBuilder notesQueryBuilder = new SQLiteQueryBuilder();
        String[] sqlSelect = {"TITLE"};

        notesQueryBuilder.setTables(NOTE_TABLE);

        Cursor cursor = notesQueryBuilder.query(notesDatabase, sqlSelect, null, null, null,
                null, null);
        List<String> noteList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                noteList.add(cursor.getString(cursor.getColumnIndex("TITLE")));
            }
            while(cursor.moveToNext());
        }
        notesDatabase.close();
        return noteList;
    }


    public List<Note> getAllNotes(){

        SQLiteDatabase notesDatabase = getReadableDatabase();
        SQLiteQueryBuilder notesQueryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID","TITLE","NOTE","DATE_CREATED"};

        notesQueryBuilder.setTables(NOTE_TABLE);

        Cursor cursor = notesQueryBuilder.query(notesDatabase, sqlSelect, null, null, null,
                null, null);

        List<Note> noteList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setNoteId(cursor.getInt(cursor.getColumnIndex("ID")));
                note.setNoteTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                note.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));
                note.setDateOfCreation(cursor.getString(cursor.getColumnIndex("DATE_CREATED")));
                noteList.add(note);
            } while(cursor.moveToNext());
        }
        notesDatabase.close();
        return noteList;
    }


    public Note getNoteById(int id){
        SQLiteDatabase newsDatabase = getReadableDatabase();
        SQLiteQueryBuilder newsQueryBuilder = new SQLiteQueryBuilder();
        Note note = null;

        String[] sqlSelect = {"ID","TITLE","NOTE","DATE_CREATED"};

        newsQueryBuilder.setTables(NOTE_TABLE);

        Cursor cursor = newsQueryBuilder.query(newsDatabase, sqlSelect, "ID = ?",
                new String[]{ String.valueOf(id) }, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                note = new Note();
                note.setNoteId(cursor.getInt(cursor.getColumnIndex("ID")));
                note.setNoteTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                note.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));
                note.setDateOfCreation(cursor.getString(cursor.getColumnIndex("DATE_CREATED")));

            } while(cursor.moveToNext());
        }

        return note;
    }

    //get all matching note search
    public List<Note> searchForNote(String noteTitle){
        SQLiteDatabase notesDatabase = getReadableDatabase();
        SQLiteQueryBuilder notesQueryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID","TITLE","NOTE","DATE_CREATED"};

        notesQueryBuilder.setTables(NOTE_TABLE);

        Cursor cursor = notesQueryBuilder.query(notesDatabase, sqlSelect, "TITLE LIKE ?",
                new String[]{"%"+noteTitle+"%"} , null, null,
                null, null);


        List<Note> noteList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setNoteId(cursor.getInt(cursor.getColumnIndex("ID")));
                note.setNoteTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                note.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));
                note.setDateOfCreation(cursor.getString(cursor.getColumnIndex("DATE_CREATED")));

                noteList.add(note);
            } while(cursor.moveToNext());
        }
        notesDatabase.close();
        return noteList;
    }


}
