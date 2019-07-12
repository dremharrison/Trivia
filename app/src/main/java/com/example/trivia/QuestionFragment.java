package com.example.trivia;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class QuestionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

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
        return view;
    }
}
