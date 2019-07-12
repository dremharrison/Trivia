package com.example.trivia.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuestionService {
    @GET("shibes")
    Call<List<String>> loadQuestion(@Query("count") int count);
}
