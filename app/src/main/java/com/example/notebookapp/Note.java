package com.example.notebookapp;

import java.util.Calendar;


public class Note {
    private String noteTitle;
    private String note;

    Calendar dateOfCreation;
    Calendar dateofModification;

    public Note(){
        this.dateOfCreation = Calendar.getInstance();
    }

    public Note(String noteTitle, String note){
        this.noteTitle = noteTitle;
        this.note = note;
        this.dateOfCreation = Calendar.getInstance();
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

    public Calendar getCurrentDateOfCreation(){
        return dateOfCreation;
    }

    public void setDateOfModification(Calendar dateofModification){
        this.dateofModification = dateofModification;
    }

    public Calendar getDateofModification(){
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
