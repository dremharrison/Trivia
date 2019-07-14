package com.example.trivia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.trivia.pojo.ResultsItem;
import com.example.trivia.pojo.TriviaResponse;
import com.example.trivia.retrofit.QuestionService;
import com.example.trivia.retrofit.RetrofitClientInstance;
import com.example.trivia.utils.GameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivitys";
    QuestionFragment questionFragment;
    Spinner categorySpinner, difficultySpinner, numOfQuestionsSpinner;
    String difficultySpinnerText, categorySpinnerText, numOfQuestionsSpinnerText, categorySpinnerTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numOfQuestionsSpinner = findViewById(R.id.spinner_numOfQuestions);
        categorySpinner = findViewById(R.id.spinner_category);
        difficultySpinner = findViewById(R.id.spinner_difficulty);

        //Declare and Init Fragment Manager
        FragmentManager manager = getSupportFragmentManager();

        //Declare and Init Fragment Transaction
        FragmentTransaction transaction = manager.beginTransaction();

        //Declare and Init Fragment
        questionFragment = new QuestionFragment();

        //Add Fragment
        transaction.add(R.id.frame_layout, questionFragment, "QuestionFragment" ).commit();

        findViewById(R.id.btn_start_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findViewById(R.id.btn_start_game).setVisibility(View.GONE);
                findViewById(R.id.iv_logo).setVisibility(View.GONE);
                findViewById(R.id.spinner_category).setVisibility(View.GONE);
                findViewById(R.id.spinner_difficulty).setVisibility(View.GONE);
                findViewById(R.id.spinner_numOfQuestions).setVisibility(View.GONE);
                findViewById(R.id.frame_layout).setVisibility(View.VISIBLE);

                numOfQuestionsSpinnerText = numOfQuestionsSpinner.getSelectedItem().toString();
                categorySpinnerText = categorySpinner.getSelectedItem().toString();
                difficultySpinnerText = difficultySpinner.getSelectedItem().toString();
                Log.d(TAG, "onCreate: numOfQuestions " + numOfQuestionsSpinnerText);
                Log.d(TAG, "onCreate: category " + categorySpinnerText);
                Log.d(TAG, "onCreate: difficulty " + difficultySpinnerText);

                categorySpinnerTextNumber = GameUtils.getCategoryNumber(categorySpinnerText);

                Log.d(TAG, "onClick: " + categorySpinnerTextNumber);


                Bundle bundle = new Bundle();
                bundle.putString("numOfQuestions", numOfQuestionsSpinnerText);
                bundle.putString("category", categorySpinnerTextNumber);
                bundle.putString("difficulty", difficultySpinnerText);
                questionFragment.getQuestions(bundle);

            }
        });
    }

//    public void retrofitRequest(int count, String type) {
//        final List answers = new ArrayList<String>();
//
//
//        // 1. Declare GiphyService and Init using RetrofitClientInstance
//        QuestionService questionService = RetrofitClientInstance.getRetrofit().create(QuestionService.class);
//
//        // Declare GiphyService Return type and Init using the GiphyService from step 1
//        Call<TriviaResponse> triviaResponseCall = questionService.loadQuestion(count, type);
//
//        // 3. Use the giphyCall from step 2 and call the .enqueue method
//        triviaResponseCall.enqueue(new Callback<TriviaResponse>() {
//            @Override
//            public void onResponse(Call<TriviaResponse> call, retrofit2.Response<TriviaResponse> response) {
//
//                if (response.isSuccessful()){
//                    ResultsItem resultsItem = response.body().getResults().get(0);
//                    Log.d(TAG, "onResponse: Success" + response.body());
//                    String correctAnswer = resultsItem.getCorrectAnswer();
//                    answers.addAll(resultsItem.getIncorrectAnswers());
//                    answers.add(correctAnswer);
//                    String question = resultsItem.getQuestion();
//                    sendQuestionAndAnswers(question, correctAnswer, answers);
//
//                } else {
//                    Log.d(TAG, "onResponse: Fail");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TriviaResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//            }
//        });
//
//    }

    public void sendQuestionAndAnswers(String question, String correctAnswer, List<String> answers) {

    }
}

