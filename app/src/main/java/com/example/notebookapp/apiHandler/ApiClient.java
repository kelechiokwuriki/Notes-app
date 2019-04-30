package com.example.notebookapp.apiHandler;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://content.guardianapis.com/search?q= " + "api-key=aeaa25e2-6507-4e4f-b5d8-6142fa09ef81";
    public static Retrofit retrofit;

    public static Retrofit getApiClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
