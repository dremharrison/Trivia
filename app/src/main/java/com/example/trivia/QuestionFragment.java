package com.example.trivia;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trivia.pojo.ResultsItem;
import com.example.trivia.pojo.TriviaResponse;
import com.example.trivia.retrofit.QuestionService;
import com.example.trivia.retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class QuestionFragment extends Fragment {
    TextView tvQuestion, tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4, tvScore, tvRight;
    String question, correctAnswer;
    private static final String TAG = "QuestionFragments";
    Integer score = 0;
    List answers;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        retrofitRequest(1, "multiple");
//        displayQuestionAndAnswers();

        tvQuestion = view.findViewById(R.id.tv_question);
        tvAnswer1 = view.findViewById(R.id.tv_answer_a);
        tvAnswer2 = view.findViewById(R.id.tv_answer_b);
        tvAnswer3 = view.findViewById(R.id.tv_answer_c);
        tvAnswer4 = view.findViewById(R.id.tv_answer_d);
        tvScore = view.findViewById(R.id.tv_score);
        tvRight = view.findViewById(R.id.tv_right);

        view.findViewById(R.id.btn_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.findViewById(R.id.frame_layout).setVisibility(View.GONE);
                v.findViewById(R.id.btn_start_game).setVisibility(View.VISIBLE);
                v.findViewById(R.id.iv_logo).setVisibility(View.VISIBLE);
                v.findViewById(R.id.spinner_category).setVisibility(View.VISIBLE);
                v.findViewById(R.id.spinner_difficulty).setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.btn_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String) tvAnswer1.getText();
                if (btnText == correctAnswer){
                    view.findViewById(R.id.tv_right).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score + 100));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    retrofitRequest(1, "multiple");

                } else {
                    view.findViewById(R.id.tv_wrong).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score = + 100));
                    retrofitRequest(1, "multiple");
                }

            }
        });

        view.findViewById(R.id.btn_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String) tvAnswer2.getText();
                if (btnText == correctAnswer){
                    view.findViewById(R.id.tv_right).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score + 100));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    retrofitRequest(1, "multiple");

                } else {
                    view.findViewById(R.id.tv_wrong).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score - 100));
                    retrofitRequest(1, "multiple");
                }


            }
        });

        view.findViewById(R.id.btn_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String) tvAnswer3.getText();
                if (btnText == correctAnswer){
                    view.findViewById(R.id.tv_right).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score + 100));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    retrofitRequest(1, "multiple");
                } else {
                    view.findViewById(R.id.tv_wrong).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score - 100));
                    retrofitRequest(1, "multiple");
                }

            }
        });

        view.findViewById(R.id.btn_d).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String)tvAnswer4.getText();
                if (btnText == correctAnswer){
                    view.findViewById(R.id.tv_right).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score + 100));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    retrofitRequest(1, "multiple");
                } else {
                    view.findViewById(R.id.tv_wrong).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score - 100));
                    retrofitRequest(1, "multiple");
                }

            }
        });

        return view;
    }

    public void retrofitRequest(int count, String type) {



        // 1. Declare GiphyService and Init using RetrofitClientInstance
        QuestionService questionService = RetrofitClientInstance.getRetrofit().create(QuestionService.class);

        // Declare GiphyService Return type and Init using the GiphyService from step 1
        Call<TriviaResponse> triviaResponseCall = questionService.loadQuestion(count, type);

        // 3. Use the giphyCall from step 2 and call the .enqueue method
        triviaResponseCall.enqueue(new Callback<TriviaResponse>() {
            @Override
            public void onResponse(Call<TriviaResponse> call, retrofit2.Response<TriviaResponse> response) {

                answers = new ArrayList<String>();


                if (response.isSuccessful()){
                    tvRight.setVisibility(View.GONE);
                    ResultsItem resultsItem = response.body().getResults().get(0);
                    Log.d(TAG, "onResponse: Success" + resultsItem);
                    correctAnswer = resultsItem.getCorrectAnswer();
                    answers.addAll(resultsItem.getIncorrectAnswers());
                    answers.add(correctAnswer);
                    question = resultsItem.getQuestion();
                    Log.d(TAG, "displayQuestionAndAnswers: " + correctAnswer);

                    tvQuestion.setText(question);
                    Collections.shuffle(answers);
                    tvAnswer1.setText(answers.get(0).toString());
                    tvAnswer2.setText(answers.get(1).toString());
                    tvAnswer3.setText(answers.get(2).toString());
                    tvAnswer4.setText(answers.get(3).toString());
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

//    public void displayQuestionAndAnswers() {
//
//
//    }
}
