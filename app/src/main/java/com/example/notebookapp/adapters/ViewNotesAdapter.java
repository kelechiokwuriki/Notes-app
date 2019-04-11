package com.example.notebookapp.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.notebookapp.R;

class SearchViewHolder extends RecyclerView.ViewHolder{

    public TextView noteTitle, noteText, noteDateCreated;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);

        noteTitle = itemView.findViewById(R.id.noteTitleIdCardView);
        noteText = itemView.findViewById(R.id.noteTextIdCardView);
        noteDateCreated = itemView.findViewById(R.id.dateTextInCardView);
    }
}

