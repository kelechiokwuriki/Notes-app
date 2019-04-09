package com.example.notebookapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

public class addNotesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_notes_fragment, container, false);

        final DatabaseHelper db = new DatabaseHelper(getActivity());

        final EditText noteTitleText = rootView.findViewById(R.id.editTextNoteTitle);
        final EditText noteText = rootView.findViewById(R.id.editTextNote);

        Button addNoteButton = rootView.findViewById(R.id.buttonAddNote);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note(noteTitleText.getText().toString(), noteText.getText().toString());
                db.insertNote(note);
                Toast.makeText(addNotesFragment.super.getContext(), "Note added", Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }
}
