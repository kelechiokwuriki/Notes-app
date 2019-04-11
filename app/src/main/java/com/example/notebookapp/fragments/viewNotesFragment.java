package com.example.notebookapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notebookapp.DatabaseControllers.DatabaseReader;
import com.example.notebookapp.R;
import com.example.notebookapp.adapters.NotesAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class viewNotesFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    NotesAdapter notesAdapter;
    DatabaseReader databaseReader;

    MaterialSearchBar materialSearchBar;
    List<String> searchSuggestList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_notes_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_search_view);

        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        materialSearchBar = rootView.findViewById(R.id.searchBar);


        databaseReader = new DatabaseReader(getActivity());


        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);

        searchSuggestList = databaseReader.getNoteTitles();

        materialSearchBar.setLastSuggestions(searchSuggestList);

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggestionList = new ArrayList<>();

                for(String result : suggestionList){
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
                    recyclerView.setAdapter(notesAdapter);
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
//
        notesAdapter = new NotesAdapter(getActivity(), databaseReader.getAllNotes());
        recyclerView.setAdapter(notesAdapter);

        return rootView;
    }

    //search by title
    private void search(String title){
        notesAdapter = new NotesAdapter(getActivity(), databaseReader.searchForNote(title));
        recyclerView.setAdapter(notesAdapter);
    }
}
