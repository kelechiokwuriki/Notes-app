package com.example.notebookapp.fragments;

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

import com.example.notebookapp.DatabaseControllers.DatabaseHelper;
import com.example.notebookapp.Models.Note;
import com.example.notebookapp.R;

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
                if(validateFields(noteTitleText, noteText)){
                    Note note = new Note(noteTitleText.getText().toString(), noteText.getText().toString());
                    db.insertNote(note);
                    Toast.makeText(addNotesFragment.super.getContext(), "Note added", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(addNotesFragment.super.getContext(), "Please enter at least a note title", Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }

    private boolean validateFields(EditText noteTitleText, EditText noteText){
        if((noteText.getText().toString().isEmpty()) && (noteText.getText().toString().isEmpty())){
            return false;
        }
        return true;
    }
}
