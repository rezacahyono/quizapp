package com.kmmi.kmmiquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username;
    private String passwrod;

    public User(Parcel in) {
        username = in.readString();
        passwrod = in.readString();
    }

    public User() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(passwrod);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }
}
