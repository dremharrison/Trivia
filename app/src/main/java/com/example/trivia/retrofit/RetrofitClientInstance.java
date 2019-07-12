package com.example.trivia.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    // Declare and Init our base url
    private static final String BASE_URL = "https://opentdb.com/";

    // Declare Retrofit object
    private static Retrofit retrofit;

    // Create a private constructor
    private RetrofitClientInstance(){}

    // Create public static method to get instance of the retrofit object
    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
