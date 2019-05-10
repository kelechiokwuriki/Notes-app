package com.example.notebookapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.notebookapp.DatabaseControllers.DatabaseHelper;
import com.example.notebookapp.DatabaseControllers.DatabaseReader;
import com.example.notebookapp.Bean.Note;
import com.example.notebookapp.R;
import com.example.notebookapp.adapters.NoteRecyclerAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class NoteViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReader databaseReader;
    private NoteRecyclerAdapter noteRecyclerAdapter;
    private MaterialSearchBar materialSearchBar;

    List<Note> noteList;
    List<String> searchSuggestList;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_notes_fragment, container, false);
        databaseReader = new DatabaseReader(getContext());
        noteList = databaseReader.getAllNotes();

        FloatingActionButton addNoteFloatButton = rootView.findViewById(R.id.addNoteFloatBtn);
        addNoteFloatButton.setOnClickListener(v -> {
            AddNotesFragment addNotesFragment = new AddNotesFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, addNotesFragment)
                    .addToBackStack(null)
                    .commit();
        });



        //recycler view
        recyclerView = rootView.findViewById(R.id.view_notes_recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        noteRecyclerAdapter = new NoteRecyclerAdapter(noteList);
        recyclerView.setAdapter(noteRecyclerAdapter);

        //material search bar
        materialSearchBar = rootView.findViewById(R.id.searchBar);
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);

        //get note titles
        searchSuggestList = databaseReader.getNoteTitles();

        materialSearchBar.setLastSuggestions(searchSuggestList);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggestionList = new ArrayList<>();

                for (String result : searchSuggestList) {
                    if (result.toLowerCase().contains(materialSearchBar.getText().toLowerCase())) ;
                    suggestionList.add(result);
                }
                materialSearchBar.setLastSuggestions(suggestionList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled) {
                    noteRecyclerAdapter = new NoteRecyclerAdapter(databaseReader.getAllNotes());

                    recyclerView.setAdapter(noteRecyclerAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                search(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                databaseHelper.deleteNote(noteRecyclerAdapter.getNoteAtPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        return rootView;
    }

    //search by title
    private void search(String title) {
        noteRecyclerAdapter = new NoteRecyclerAdapter(databaseReader.searchForNote(title));
        recyclerView.setAdapter(noteRecyclerAdapter);
    }
}

