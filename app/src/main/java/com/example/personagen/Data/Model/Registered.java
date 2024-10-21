package com.example.personagen.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Registered implements Parcelable {
    private String date;
    private int age;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeInt(this.age);
    }

    public Registered() {
    }

    protected Registered(Parcel in) {
        this.date = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<Registered> CREATOR = new Parcelable.Creator<Registered>() {
        @Override
        public Registered createFromParcel(Parcel source) {
            return new Registered(source);
        }

        @Override
        public Registered[] newArray(int size) {
            return new Registered[size];
        }
    };
}
