package com.example.notebookapp.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notebookapp.Bean.Note;
import com.example.notebookapp.R;
import com.example.notebookapp.fragments.NoteDetailFragment;

import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>  {
    private List<Note> noteList;


    public NoteRecyclerAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_notes, viewGroup, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        //open note detail on click
        viewHolder.itemView.setOnClickListener(v -> {
            int noteId = noteList.get(viewHolder.getAdapterPosition()).getNoteId();
            String noteTitle = noteList.get(viewHolder.getAdapterPosition()).getNoteTitle();
            String noteContent = noteList.get(viewHolder.getAdapterPosition()).getNote();
            String noteDate = noteList.get(viewHolder.getAdapterPosition()).getCurrentDateOfCreation();


            //pass note details to bundle
            NoteDetailFragment noteDetailFragment = new NoteDetailFragment();

            Bundle bundle = new Bundle();
            bundle.putString("noteId", String.valueOf(noteId));
            bundle.putString("noteTitle", noteTitle);
            bundle.putString("noteContent", noteContent);
            bundle.putString("noteDate", noteDate);


            noteDetailFragment.setArguments(bundle);

            AppCompatActivity activity = (AppCompatActivity) viewGroup.getContext();

             activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, noteDetailFragment)
                    .addToBackStack(null)
                    .commit();

        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.noteTitle.setText(noteList.get(i).getNoteTitle());
        viewHolder.noteDateCreated.setText(noteList.get(i).getCurrentDateOfCreation());
        viewHolder.noteText.setText(noteList.get(i).getNote());


//        for(int j=0; j < noteList.size(); i++){
//
//            viewHolder.noteNewsUrl.setText(noteList.get(j).toString());
//        }

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public Note getNoteAtPosition(int position){
        return noteList.get(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView noteTitle, noteDateCreated, noteText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitleIdCardView);
            noteText = itemView.findViewById(R.id.noteTextIdCardView);
            noteDateCreated = itemView.findViewById(R.id.dateTextInCardView);
        }

    }
}
