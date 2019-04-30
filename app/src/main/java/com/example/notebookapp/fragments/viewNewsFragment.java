package com.example.notebookapp.fragments;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
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


public class viewNewsFragment extends Fragment {
    private final String URL = "https://content.guardianapis.com/search?q=football&api-key=aeaa25e2-6507-4e4f-b5d8-6142fa09ef81";
    private final String searchUrl = "https://content.guardianapis.com/search?q=";

    private JsonObjectRequest jsonObjectRequest, searchJsonRequest;
    private JSONArray jsonArray;

    private News news;

    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private List<News> newsList = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;

    private NewsRecyclerAdapter newsRecyclerAdapter;
    private MaterialSearchBar materialSearchBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_news_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.newsRecyclerView);

        newsRecyclerAdapter = new NewsRecyclerAdapter(getContext(), newsList);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsRecyclerAdapter);

        //GET DATA FROM API
//        getData();


        //material search bar
        materialSearchBar = rootView.findViewById(R.id.searchBar);
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){

                    Log.i("Search state method", "Search state changed");

                    newsRecyclerAdapter = new NewsRecyclerAdapter(getContext(), newsList);
                    recyclerView.setAdapter(newsRecyclerAdapter);
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



//        Log.i("News list OUT", newsList.toString());



//        requestQueue = Volley.newRequestQueue(getContext());
//        requestQueue.add(jsonObjectRequest);


        return rootView;
    }

    private void getData(){
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    jsonArray = response.getJSONObject("response").getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        News news = new News();
                        news.setWebTitle(jsonObject.getString("webTitle"));
                        news.setWebUrl(jsonObject.getString("webPublicationDate"));

                        newsList.add(news);
                    }



                    newsRecyclerAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley error", "Error: " + error.getMessage());
                Log.e("SITE INFO ERROR", "Site Info Error: " + error.getMessage());
            }
        });

    }

    private void search(String text){
        Log.i("Search method", "search ");

         JsonObjectRequest searchJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, searchUrl + text + "&api-key=aeaa25e2-6507-4e4f-b5d8-6142fa09ef81", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<News> newsSearchList = new ArrayList<>();

                        try{
                            jsonArray = response.getJSONObject("response").getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                News news = new News();
                                news.setWebTitle(jsonObject.getString("webTitle"));
                                news.setWebUrl(jsonObject.getString("webPublicationDate"));

                                newsSearchList.add(news);
                            }

                            Log.i("Searched data", newsSearchList.toString());


                            newsRecyclerAdapter = new NewsRecyclerAdapter(getContext(), newsSearchList);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(newsRecyclerAdapter);

                            newsRecyclerAdapter.notifyDataSetChanged();

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

         RequestQueue searchQ = Volley.newRequestQueue(getContext());
         searchQ.add(searchJsonObjectRequest);

    }

}
