package com.example.trivia.retrofit;

import com.example.trivia.pojo.TriviaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuestionService {
    @GET("/api.php")
    Call<TriviaResponse> loadQuestion(@Query("amount") int amount);
}
