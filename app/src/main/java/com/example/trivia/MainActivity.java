package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.trivia.pojo.ResultsItem;
import com.example.trivia.pojo.TriviaResponse;
import com.example.trivia.retrofit.QuestionService;
import com.example.trivia.retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivitys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declare and Init Fragment Manager
        FragmentManager manager = getSupportFragmentManager();

        //Declare and Init Fragment Transaction
        FragmentTransaction transaction = manager.beginTransaction();

        //Declare and Init Fragment
        QuestionFragment questionFragment = new QuestionFragment();

        //Add Fragment
        transaction.add(R.id.frame_layout, questionFragment, "QuestionFragment" ).commit();

        retrofitRequest(20);

        findViewById(R.id.btn_start_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findViewById(R.id.btn_start_game).setVisibility(View.GONE);
                findViewById(R.id.iv_logo).setVisibility(View.GONE);
                findViewById(R.id.spinner_category).setVisibility(View.GONE);
                findViewById(R.id.spinner_difficulty).setVisibility(View.GONE);
                findViewById(R.id.frame_layout).setVisibility(View.VISIBLE);

            }
        });
    }

    public void retrofitRequest(int count) {
        // 1. Declare GiphyService and Init using RetrofitClientInstance
        QuestionService questionService = RetrofitClientInstance.getRetrofit().create(QuestionService.class);

        // Declare GiphyService Return type and Init using the GiphyService from step 1
        Call<TriviaResponse> giphycall = questionService.loadQuestion(count);

        // 3. Use the giphyCall from step 2 and call the .enqueue method
        giphycall.enqueue(new Callback<TriviaResponse>() {
            @Override
            public void onResponse(Call<TriviaResponse> call, retrofit2.Response<TriviaResponse> response) {

                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: Success" + response.body());
                    ResultsItem resultsItem = new ResultsItem();
                    String question = resultsItem.getQuestion();

                } else {
                    Log.d(TAG, "onResponse: Fail");
                }
            }

            @Override
            public void onFailure(Call<TriviaResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }
}
