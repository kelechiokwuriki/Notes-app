package com.example.notebookapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notebookapp.Bean.Note;
import com.example.notebookapp.R;

import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>{
    private List<Note> noteList;
    private NoteClickListener noteClickListener;

    public NoteRecyclerAdapter(List<Note> noteList, NoteClickListener noteClickListener) {
        this.noteList = noteList;
        this.noteClickListener = noteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_notes, viewGroup, false);

        return new ViewHolder(view, noteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.noteTitle.setText(noteList.get(i).getNoteTitle());
        viewHolder.noteDateCreated.setText(noteList.get(i).getCurrentDateOfCreation());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView noteTitle, noteDateCreated;
        NoteClickListener noteClickListener;

        public ViewHolder(@NonNull View itemView, NoteClickListener noteClickListener) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitleIdCardView);
            noteDateCreated = itemView.findViewById(R.id.dateTextInCardView);
            this.noteClickListener = noteClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            noteClickListener.onNoteClick(v, getAdapterPosition());
        }
    }

    public interface NoteClickListener{
        void onNoteClick(View view, int clickedPosition);
    }

}
