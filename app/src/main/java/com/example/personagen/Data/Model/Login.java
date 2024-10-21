package com.example.personagen.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Login implements Parcelable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
    }

    public Login() {
    }

    protected Login(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
}
