package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.trivia.Retrofit.QuestionService;
import com.example.trivia.Retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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
        Call<List<String>> giphycall = questionService.loadQuestion(count);

        // 3. Use the giphyCall from step 2 and call the .enqueue method
        giphycall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {

                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: Success");
                    loadRecyclerView(response.body());

                } else {
                    Log.d(TAG, "onResponse: Fail");
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    private void loadRecyclerView(List<String> strings) {

    }
}
