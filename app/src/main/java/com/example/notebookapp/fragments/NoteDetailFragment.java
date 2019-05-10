package com.example.notebookapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notebookapp.R;

public class NoteDetailFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.note_detail, container, false);


        TextView noteDetailTitleTextView = rootView.findViewById(R.id.noteDetailTitleId);
        TextView noteDetailContentTextView = rootView.findViewById(R.id.noteDetailContentId);
        TextView noteDetailDateTextView = rootView.findViewById(R.id.noteDetailDateId);

        Bundle bundle = getArguments();
        if(bundle != null){
            String noteId = bundle.getString("noteId");
            String noteTitle = bundle.getString("noteTitle");
            String noteContent = bundle.getString("noteContent");
            String noteDate = bundle.getString("noteDate");

            noteDetailTitleTextView.setText(noteTitle);
            noteDetailContentTextView.setText(noteContent);
            noteDetailDateTextView.setText(noteDate);
        }




        return rootView;

    }
}