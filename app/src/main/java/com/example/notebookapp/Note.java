package com.example.notebookapp;

import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Note {
    private String noteTitle;
    private String note;

    private Date date;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    String dateOfCreation;
    String dateofModification;

    public Note(String noteTitle, String note){
        this.noteTitle = noteTitle;
        this.note = note;

        date = new Date();
        this.dateOfCreation = String.valueOf(formatter.format(date));
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public String getCurrentDateOfCreation(){
        return dateOfCreation;
    }

    public void setDateOfModification(String dateofModification){
        this.dateofModification = dateofModification;
    }

    public String getDateofModification(){
        return dateofModification;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteTitle='" + noteTitle + '\'' +
                ", note='" + note + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", dateofModification=" + dateofModification +
                '}';
    }

}
