package com.example.personagen.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Dob implements Parcelable {
    private String date;
    private int age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeInt(this.age);
    }

    public Dob() {
    }

    protected Dob(Parcel in) {
        this.date = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<Dob> CREATOR = new Parcelable.Creator<Dob>() {
        @Override
        public Dob createFromParcel(Parcel source) {
            return new Dob(source);
        }

        @Override
        public Dob[] newArray(int size) {
            return new Dob[size];
        }
    };
}
