package com.example.notebookapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notebookapp.Models.Note;
import com.example.notebookapp.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context;
    private List<Note> noteList;

    public NotesAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View noteView = layoutInflater.inflate(R.layout.view_notes, viewGroup, false);

        return new SearchViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        searchViewHolder.noteTitle.setText(noteList.get(i).getNoteTitle());
        searchViewHolder.noteText.setText(noteList.get(i).getNote());
        searchViewHolder.noteDateCreated.setText(noteList.get(i).getCurrentDateOfCreation());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
