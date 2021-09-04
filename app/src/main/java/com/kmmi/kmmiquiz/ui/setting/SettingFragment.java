package com.kmmi.kmmiquiz.ui.setting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.textfield.TextInputEditText;
import com.kmmi.kmmiquiz.R;
import com.kmmi.kmmiquiz.model.User;
import com.kmmi.kmmiquiz.preferences.UserPreferences;

public class SettingFragment extends Fragment {
    private UserPreferences preferences;

    private TextInputEditText edtChangeUsername;
    private SwitchCompat swDarkMode;
    private User user;
    private static final String TAG = SettingFragment.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swDarkMode = view.findViewById(R.id.switch_dark_mode);
        edtChangeUsername = view.findViewById(R.id.edt_change_username);
        Button btnSave = view.findViewById(R.id.btn_save);

        preferences = new UserPreferences(requireActivity());
        user = preferences.getUser();
        showPreferencesSetting();
        btnSave.setOnClickListener(v -> {
            String changeUsername = edtChangeUsername.getText().toString().trim();
            String password = preferences.getUser().getPasswrod();
            if (!TextUtils.isEmpty(changeUsername)) {
                saveUser(changeUsername, password);
            }

        });
        swDarkMode.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            preferences.setDarkMode(isChecked);
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            Log.d(TAG, "onCheckedChanged: "+isChecked);
        });
    }

    private void saveUser(String username, String password) {
        User changeUser = new User();
        changeUser.setUsername(username);
        changeUser.setPasswrod(password);
        preferences.setUser(changeUser);
    }

    private void showPreferencesSetting() {
        edtChangeUsername.setText(user.getUsername());
        swDarkMode.setChecked(preferences.getDarkMode());
    }
}