package com.kmmi.kmmiquiz.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.kmmi.kmmiquiz.model.Score;
import com.kmmi.kmmiquiz.model.User;

public class UserPreferences {
    private static final String PREFS_NAME = "user_pref";
    private static final String IS_LOGIN = "is_login";

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final String IS_SCORE = "is_score";
    private static final String IS_CORRECT = "is_correct";
    private static final String IS_WRONG = "is_wrong";
    
    private static final String DARK_MODE = "dark_mode";

    private final SharedPreferences preferences;

    public UserPreferences(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }

    public void setLogin(Boolean login) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_LOGIN, login);
        editor.apply();
    }

    public boolean getLogin() {
        return preferences.getBoolean(IS_LOGIN,false);
    }

    public void setScore(Score score) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(IS_SCORE,score.getIsScore());
        editor.putInt(IS_CORRECT, score.getCorrect());
        editor.putInt(IS_WRONG, score.getWrong());
        editor.apply();
    }

    public Score getScore() {
        Score score = new Score();
        score.setIsScore(preferences.getInt(IS_SCORE,0));
        score.setCorrect(preferences.getInt(IS_CORRECT,0));
        score.setWrong(preferences.getInt(IS_WRONG,0));
        return score;
    }

    public void setUser(User user)  {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME, user.getUsername());
        editor.putString(PASSWORD, user.getPasswrod());
        editor.apply();
    }

    public User getUser() {
        User user = new User();
        user.setUsername(preferences.getString(USERNAME,""));
        user.setPasswrod(preferences.getString(PASSWORD,""));
        return user;
    }

    public void setDarkMode(Boolean mode) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(DARK_MODE, mode);
        editor.apply();
    }

    public boolean getDarkMode() {
        return preferences.getBoolean(DARK_MODE, false);
    }
}
