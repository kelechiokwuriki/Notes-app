package com.example.notebookapp;

public interface NoteDaoInterface {
    void addNoteToDb(Note note);

    Note getAllNotes();
}
