package com.example.notebookapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notebookapp.DatabaseControllers.DatabaseReader;
import com.example.notebookapp.Bean.Note;
import com.example.notebookapp.R;
import com.example.notebookapp.adapters.NoteRecyclerAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class NoteViewFragment extends Fragment implements NoteRecyclerAdapter.NoteClickListener {

    private RecyclerView recyclerView;
    private DatabaseReader databaseReader;
    private NoteRecyclerAdapter noteRecyclerAdapter;
    private MaterialSearchBar materialSearchBar;

    private NoteRecyclerAdapter.NoteClickListener noteClickListener;


    List<Note> noteList;
    List<String> searchSuggestList;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_notes_fragment, container, false);
        databaseReader = new DatabaseReader(getContext());
        noteList = databaseReader.getAllNotes();

        //recycler view
        recyclerView = rootView.findViewById(R.id.recycler_search_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        noteRecyclerAdapter = new NoteRecyclerAdapter(noteList, this);
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

                for(String result : searchSuggestList){
                    if(result.toLowerCase().contains(materialSearchBar.getText().toLowerCase()));
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
                if(!enabled){
                    noteRecyclerAdapter = new NoteRecyclerAdapter(databaseReader.getAllNotes(), noteClickListener);

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


        return rootView;
    }
    //search by title
    private void search(String title){
        noteRecyclerAdapter = new NoteRecyclerAdapter(databaseReader.searchForNote(title), this);
        recyclerView.setAdapter(noteRecyclerAdapter);
    }


    @Override
    public void onNoteClick(View v, int clickedPosition) {
        Log.d("Click listener", "Clicked: " + clickedPosition);
    }

}