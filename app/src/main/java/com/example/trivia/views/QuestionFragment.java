package com.example.trivia.views;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trivia.R;
import com.example.trivia.model.CategoryEvent;
import com.example.trivia.pojo.ResultsItem;
import com.example.trivia.pojo.TriviaResponse;
import com.example.trivia.retrofit.QuestionService;
import com.example.trivia.retrofit.RetrofitClientInstance;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class QuestionFragment extends Fragment {
    TextView tvQuestion, tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4, tvScore, tvGameover, tvCategory;
    String question, correctAnswer, categoryText;
    private static final String TAG = "QuestionFragments";
    Integer score = 0;
    List answers;
    Button btnA, btnB, btnC, btnD;
    ProgressDialog progress;
    private int questionIndex = 0;
    CategoryFragment categoryFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        categoryFragment = new CategoryFragment();

        tvQuestion = view.findViewById(R.id.tv_question);
        tvAnswer1 = view.findViewById(R.id.tv_answer_a);
        tvAnswer2 = view.findViewById(R.id.tv_answer_b);
        tvAnswer3 = view.findViewById(R.id.tv_answer_c);
        tvAnswer4 = view.findViewById(R.id.tv_answer_d);
        tvCategory = view.findViewById(R.id.tv_category);
        tvScore = view.findViewById(R.id.tv_score);
        tvGameover = view.findViewById(R.id.tv_gameover);
        btnA = view.findViewById(R.id.btn_a);
        btnB = view.findViewById(R.id.btn_b);
        btnC = view.findViewById(R.id.btn_c);
        btnD = view.findViewById(R.id.btn_d);

        view.findViewById(R.id.btn_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit();
            }
        });

        view.findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit();
            }
        });

        view.findViewById(R.id.btn_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String) tvAnswer1.getText();
                if (btnText == correctAnswer) {
                    rightAnswer();
                } else {
                    wrongAnswer();
                }

            }
        });

        view.findViewById(R.id.btn_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String) tvAnswer2.getText();
                if (btnText == correctAnswer) {
                    rightAnswer();
                } else {
                    wrongAnswer();
                }


            }
        });

        view.findViewById(R.id.btn_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String) tvAnswer3.getText();
                if (btnText == correctAnswer) {
                    rightAnswer();
                } else {
                    wrongAnswer();
                }

            }
        });

        view.findViewById(R.id.btn_d).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String) tvAnswer4.getText();
                if (btnText == correctAnswer) {
                    rightAnswer();
                } else {
                    wrongAnswer();
                }

            }
        });

        return view;
    }

    private void rightAnswer() {
        score = score + 100;
        tvScore.setText(score);
        Toast.makeText(getContext(), "Correct!", Toast.LENGTH_SHORT).show();
    }

    private void wrongAnswer() {
        score = score - 100;
        tvScore.setText(score);
        Toast.makeText(getContext(), "Sorry, correct answer is " + correctAnswer, Toast.LENGTH_SHORT).show();
    }

    private void quit(){
        score = 0;
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, categoryFragment).commit();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(CategoryEvent categoryEvent) {
        String categoryNumber = categoryEvent.getCategoryNumber();
        categoryText = categoryEvent.getCategoryText();
        getQuestions(categoryNumber);
    }

    public void getQuestions(String categoryNumber) {

        progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        retrofitRequest("10", categoryNumber, "medium", "multiple");

    }

    private void retrofitRequest(String count, String category, String difficulty, String type) {


        // 1. Declare GiphyService and Init using RetrofitClientInstance
        QuestionService questionService = RetrofitClientInstance.getRetrofit().create(QuestionService.class);

        // Declare GiphyService Return type and Init using the GiphyService from step 1
        Call<TriviaResponse> triviaResponseCall = questionService.loadQuestion(count, category, difficulty, type);

        // 3. Use the giphyCall from step 2 and call the .enqueue method
        triviaResponseCall.enqueue(new Callback<TriviaResponse>() {
            @Override
            public void onResponse(Call<TriviaResponse> call, retrofit2.Response<TriviaResponse> response) {

                answers = new ArrayList<String>();

                if (response.isSuccessful() && response.body() != null && response.body().getResults().size() > 0) {
                    ResultsItem resultsItem = response.body().getResults().get(questionIndex);
                    Log.d(TAG, "onResponse: Success" + resultsItem);
                    correctAnswer = resultsItem.getCorrectAnswer();
                    answers.addAll(resultsItem.getIncorrectAnswers());
                    answers.add(correctAnswer);
                    question = resultsItem.getQuestion();
                    tvCategory.setText(categoryText);


                    displayQuestionAndAnswers();


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

    public void displayQuestionAndAnswers() {

            progress.dismiss();

            tvQuestion.setText(question);
            Collections.shuffle(answers);
            tvAnswer1.setText(answers.get(0).toString());
            tvAnswer2.setText(answers.get(1).toString());
            tvAnswer3.setText(answers.get(2).toString());
            tvAnswer4.setText(answers.get(3).toString());

            questionIndex++;


    }
}
