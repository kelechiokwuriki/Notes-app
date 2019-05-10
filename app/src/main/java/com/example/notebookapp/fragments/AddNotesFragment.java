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
import com.example.notebookapp.Bean.Note;
import com.example.notebookapp.R;

public class AddNotesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_notes_fragment, container, false);

        final DatabaseHelper db = new DatabaseHelper(getActivity());

        final EditText noteTitleText = rootView.findViewById(R.id.editTextNoteTitle);
        final EditText noteText = rootView.findViewById(R.id.editTextNote);

        Button addNoteButton = rootView.findViewById(R.id.buttonAddNote);

        addNoteButton.setOnClickListener(v -> {
            if(validateFields(noteTitleText, noteText)){
                Note note = new Note(noteTitleText.getText().toString(), noteText.getText().toString());
                db.insertNote(note);
                Toast.makeText(AddNotesFragment.super.getContext(), "Note added", Toast.LENGTH_LONG).show();

                NoteViewFragment noteViewFragment = new NoteViewFragment();

                //switch to note view fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, noteViewFragment)
                        .addToBackStack(null)
                        .commit();
            }
            else{
                Toast.makeText(AddNotesFragment.super.getContext(),
                        "Please enter at least a note title", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }

    //validate add note input fields
    private boolean validateFields(EditText noteTitleText, EditText noteText){
        if(noteTitleText.toString().trim().isEmpty() || noteText.toString().trim().isEmpty()){
            return false;
        }
        return true;
    }
}
