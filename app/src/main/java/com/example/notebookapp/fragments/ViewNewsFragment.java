package com.example.notebookapp.fragments;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.notebookapp.Bean.News;
import com.example.notebookapp.R;
import com.example.notebookapp.adapters.NewsRecyclerAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ViewNewsFragment extends Fragment  {
    private final String searchUrl = "https://content.guardianapis.com/search?q=";

    private JSONArray jsonArray;


    private List<News> newsList = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private MaterialSearchBar materialSearchBar;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.view_news_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.newsRecyclerView);

        newsRecyclerAdapter = new NewsRecyclerAdapter(getContext(), newsList);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsRecyclerAdapter);

        //material search bar
        materialSearchBar = rootView.findViewById(R.id.searchBar);
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    newsRecyclerAdapter = new NewsRecyclerAdapter(getContext(), newsList);
                    recyclerView.setAdapter(newsRecyclerAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

                //https://developer.android.com/training/monitoring-device-state/connectivity-monitoring#java

                //check for internet connectivity
                //using connectivity manager
                ConnectivityManager cm =
                        (ConnectivityManager)getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);

                //get network info from connectivity manager
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();

//                Log.i("Conn", String.valueOf(isConnected));

                if(isConnected){
                    //search for news in search bar
                    search(text.toString());
                }
                else {
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        return rootView;
    }

    private void search(String text){
         JsonObjectRequest searchJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, searchUrl + text + "&api-key=aeaa25e2-6507-4e4f-b5d8-6142fa09ef81", null,
                 response -> {

                     List<News> newsSearchList = new ArrayList<>();

                     try{
                         jsonArray = response.getJSONObject("response").getJSONArray("results");

                         for (int i = 0; i < jsonArray.length(); i++){

                             JSONObject jsonObject = jsonArray.getJSONObject(i);

                             News news = new News();
                             news.setWebTitle(jsonObject.getString("webTitle"));
                             news.setWebUrl(jsonObject.getString("webUrl"));
                             news.setDate(jsonObject.getString("webPublicationDate"));

                             newsSearchList.add(news);
                         }

                         newsRecyclerAdapter = new NewsRecyclerAdapter(getContext(), newsSearchList);
                         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                         recyclerView.setAdapter(newsRecyclerAdapter);
                         newsRecyclerAdapter.notifyDataSetChanged();

                     } catch (JSONException e){
                         e.printStackTrace();
                     }
                 }, error -> Log.e("JSON error", error.getMessage()));

         RequestQueue requestQueue = Volley.newRequestQueue(getContext());
         requestQueue.add(searchJsonObjectRequest);

    }


}
