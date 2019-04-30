package com.example.notebookapp.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.notebookapp.R;

class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView noteTitle, noteText, noteDateCreated;
    public NotesAdapter.NoteClickListener noteClickListener;

    public SearchViewHolder(@NonNull View itemView, NotesAdapter.NoteClickListener noteClickListener) {
        super(itemView);

        noteTitle = itemView.findViewById(R.id.noteTitleIdCardView);
        noteText = itemView.findViewById(R.id.noteTextIdCardView);
        noteDateCreated = itemView.findViewById(R.id.dateTextInCardView);

        this.noteClickListener = noteClickListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        noteClickListener.onNoteClicked(getAdapterPosition());
    }
}

