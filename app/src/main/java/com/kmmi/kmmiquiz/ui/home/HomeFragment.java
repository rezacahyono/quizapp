package com.kmmi.kmmiquiz.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kmmi.kmmiquiz.R;
import com.kmmi.kmmiquiz.model.Score;
import com.kmmi.kmmiquiz.model.User;
import com.kmmi.kmmiquiz.preferences.UserPreferences;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnPlay = view.findViewById(R.id.btn_play);
        Button btnSetting = view.findViewById(R.id.btn_setting);
        Button btnExit = view.findViewById(R.id.btn_exit);
        TextView tvScore = view.findViewById(R.id.tv_scorer);
        TextView tvUsername = view.findViewById(R.id.tv_name);
        UserPreferences preferences = new UserPreferences(requireActivity());

        User user = preferences.getUser();
        tvUsername.setText(user.getUsername());

        Score score = preferences.getScore();
        tvScore.setText(String.valueOf(score.getIsScore()));
        btnPlay.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_quizFragment));

        btnSetting.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_settingFragment);
        });

        btnExit.setOnClickListener(v -> {
            requireActivity().finish();
        });
//        if (preferences.getDarkMode()) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
    }


}