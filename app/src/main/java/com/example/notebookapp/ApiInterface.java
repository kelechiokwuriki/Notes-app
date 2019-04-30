package com.example.notebookapp;

import com.example.notebookapp.Bean.News;
import com.example.notebookapp.Bean.NewsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search?/q=Aberystwyth")
    Call<List<News>> getNews (@Query("api-key") String apiKey );
}
