package com.example.notebookapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notebookapp.Models.Note;
import com.example.notebookapp.R;
import com.example.notebookapp.fragments.viewNotesFragment;

public class NotesRecycleAdapter extends RecyclerView.Adapter<NotesRecycleAdapter.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView noteTitle, noteDateCreated;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           noteTitle = itemView.findViewById(R.id.noteTitleIdCardView);
           noteDateCreated = itemView.findViewById(R.id.dateTextInCardView);

       }
   }
}
