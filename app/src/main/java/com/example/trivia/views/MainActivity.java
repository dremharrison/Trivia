package com.example.trivia.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.trivia.R;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivitys";
    QuestionFragment questionFragment;
    CategoryFragment categoryFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager categoryManager = getSupportFragmentManager();
        FragmentTransaction categoryTransaction = categoryManager.beginTransaction();
        categoryFragment = new CategoryFragment();
        categoryTransaction.add(R.id.frame_layout, categoryFragment, "CategoryFragment" ).commit();

        FragmentManager questionManager = getSupportFragmentManager();
        FragmentTransaction questionTransaction = questionManager.beginTransaction();
        questionFragment = new QuestionFragment();
        questionTransaction.add(R.id.frame_layout, questionFragment, "QuestionFragment" ).hide(questionFragment).commit();
    }
}

