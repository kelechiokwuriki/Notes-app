package com.example.notebookapp.DatabaseControllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.notebookapp.Models.Note;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseReader extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "NOTESDB.db";
    private static final String TABLE_NAME = "Notes_table";


    public DatabaseReader(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public Cursor getAllNotesFromDb(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
        Cursor c = qb.query(db, null, null, null, null, null, null);
        return c;

    }

    public List<String> getNoteTitles(){
        SQLiteDatabase notesDatabase = this.getReadableDatabase();
        SQLiteQueryBuilder notesQueryBuilder = new SQLiteQueryBuilder();
        String[] sqlSelect = {"TITLE"};

        notesQueryBuilder.setTables(TABLE_NAME);

        Cursor cursor = notesQueryBuilder.query(notesDatabase, sqlSelect, null, null, null,
                null, null);
        List<String> noteList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                noteList.add(cursor.getString(cursor.getColumnIndex("TITLE")));
            }
            while(cursor.moveToNext());
        }
        return noteList;
    }


    public List<Note> getAllNotes(){

        SQLiteDatabase notesDatabase = getReadableDatabase();
        SQLiteQueryBuilder notesQueryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID","TITLE","NOTE","DATE_CREATED","DATE_MODIFIED"};

        notesQueryBuilder.setTables(TABLE_NAME);

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
        return noteList;
    }

    //get all matching note search
    public List<Note> searchForNote(String noteTitle){
        SQLiteDatabase notesDatabase = getReadableDatabase();
        SQLiteQueryBuilder notesQueryBuilder = new SQLiteQueryBuilder();
        String[] sqlSelect = {"ID", "TITLE", "NOTE", "DATE_CREATED"};

        notesQueryBuilder.setTables(TABLE_NAME);

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
        return noteList;
    }


}
