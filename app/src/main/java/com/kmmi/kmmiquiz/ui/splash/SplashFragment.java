package com.kmmi.kmmiquiz.ui.splash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kmmi.kmmiquiz.R;
import com.kmmi.kmmiquiz.preferences.UserPreferences;

public class SplashFragment extends Fragment {
    private final static Long TIME_SPLAH = 1000L;
    private UserPreferences mUserPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserPreferences = new UserPreferences(requireActivity());
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (mUserPreferences.getLogin()) {
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);
            }else  {
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);
            }
        },TIME_SPLAH);
    }
}