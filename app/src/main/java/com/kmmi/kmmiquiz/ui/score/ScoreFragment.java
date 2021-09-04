package com.kmmi.kmmiquiz.ui.score;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kmmi.kmmiquiz.R;
import com.kmmi.kmmiquiz.model.Score;
import com.kmmi.kmmiquiz.preferences.UserPreferences;

public class ScoreFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserPreferences preferences = new UserPreferences(requireActivity());
        TextView tvScore = view.findViewById(R.id.tv_scorer);
        TextView tvWrong = view.findViewById(R.id.tv_wrong);
        TextView tvCorrect = view.findViewById(R.id.tv_correct);
        Button btnHome = view.findViewById(R.id.btn_home);

        Score score = preferences.getScore();
        tvScore.setText(String.valueOf(score.getIsScore()));
        tvWrong.setText(requireActivity().getResources().getString(R.string.wrong_s,String.valueOf(score.getWrong())));
        tvCorrect.setText(requireActivity().getResources().getString(R.string.correct_s,String.valueOf(score.getCorrect())));

        btnHome.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_scoreFragment_to_homeFragment);
        });
    }
}