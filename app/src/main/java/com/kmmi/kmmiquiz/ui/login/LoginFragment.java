package com.kmmi.kmmiquiz.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.kmmi.kmmiquiz.R;
import com.kmmi.kmmiquiz.model.User;
import com.kmmi.kmmiquiz.preferences.UserPreferences;


public class LoginFragment extends Fragment {
    private UserPreferences mUserPreferences;
    private User user = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText edtUsername = view.findViewById(R.id.edt_username);
        TextInputEditText edtPassword = view.findViewById(R.id.edt_password);
        Button btnLogin = view.findViewById(R.id.btn_login);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        mUserPreferences = new UserPreferences(requireActivity());
        user = new User();

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            if (TextUtils.isEmpty(username)) {
                edtUsername.setError("Username masiih kosong");
            }else if (TextUtils.isEmpty(password)){
                edtPassword.setError("Password masih kosong");
            }else {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
                user.setUsername(username);
                user.setPasswrod(password);
                mUserPreferences.setUser(user);
                mUserPreferences.setLogin(true);
            }
        });

        btnCancel.setOnClickListener(v -> {
            edtUsername.setText("");
            edtPassword.setText("");
        });
    }
}