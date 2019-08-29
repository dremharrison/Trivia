package com.example.trivia.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.trivia.R;
import com.example.trivia.model.CategoryEvent;
import com.example.trivia.utils.GameUtils;

import org.greenrobot.eventbus.EventBus;

public class CategoryFragment extends Fragment {
    QuestionFragment questionFragment;
    Spinner categorySpinner;
    String categorySpinnerText, categorySpinnerTextNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_category, container, false);

        categorySpinner = view.findViewById(R.id.spinner_category);

        questionFragment = new QuestionFragment();

         view.findViewById(R.id.btn_start_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_layout, questionFragment).commit();

                categorySpinnerText = categorySpinner.getSelectedItem().toString();
                categorySpinnerTextNumber = GameUtils.getCategoryNumber(categorySpinnerText);

                EventBus.getDefault().post(new CategoryEvent(categorySpinnerTextNumber,categorySpinnerText));

            }
        });

         return view;
    }



}
