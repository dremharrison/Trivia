package com.example.trivia;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class QuestionFragment extends Fragment {
    TextView tvQuestion, tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4, tvScore;
    String checkCorrectAnswer;
    private static final String TAG = "QuestionFragments";
    Integer score = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        tvQuestion = view.findViewById(R.id.tv_question);
        tvAnswer1 = view.findViewById(R.id.tv_answer_a);
        tvAnswer2 = view.findViewById(R.id.tv_answer_b);
        tvAnswer3 = view.findViewById(R.id.tv_answer_c);
        tvAnswer4 = view.findViewById(R.id.tv_answer_d);
        tvScore = view.findViewById(R.id.tv_score);

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
                if (btnText == checkCorrectAnswer){
                    view.findViewById(R.id.tv_right).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score + 100));
                } else {
                    view.findViewById(R.id.tv_wrong).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score - 100));
                }

            }
        });

        view.findViewById(R.id.btn_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String) tvAnswer2.getText();
                if (btnText == checkCorrectAnswer){
                    view.findViewById(R.id.tv_right).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score + 100));
                } else {
                    view.findViewById(R.id.tv_wrong).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score - 100));
                }


            }
        });

        view.findViewById(R.id.btn_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String) tvAnswer3.getText();
                if (btnText == checkCorrectAnswer){
                    view.findViewById(R.id.tv_right).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score + 100));
                } else {
                    view.findViewById(R.id.tv_wrong).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score - 100));
                }

            }
        });

        view.findViewById(R.id.btn_d).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = (String)tvAnswer4.getText();
                if (btnText == checkCorrectAnswer){
                    view.findViewById(R.id.tv_right).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score + 100));
                } else {
                    view.findViewById(R.id.tv_wrong).setVisibility(View.VISIBLE);
                    tvScore.setText(String.valueOf(score - 100));
                }

            }
        });
        return view;


    }

    public void displayQuestionAndAnswers(String question, String correctAnswer, List<String> answers) {
        tvQuestion.setText(question);
        checkCorrectAnswer = correctAnswer;
        Log.d(TAG, "displayQuestionAndAnswers: " + correctAnswer);
        Collections.shuffle(answers);
        tvAnswer1.setText(answers.get(0));
        tvAnswer2.setText((answers.get(1)));
        tvAnswer3.setText(answers.get(2));
        tvAnswer4.setText(answers.get(3));

    }
}
