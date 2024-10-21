package com.example.personagen.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Street implements Parcelable {
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number);
        dest.writeString(this.name);
    }

    public Street() {
    }

    protected Street(Parcel in) {
        this.number = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Street> CREATOR = new Parcelable.Creator<Street>() {
        @Override
        public Street createFromParcel(Parcel source) {
            return new Street(source);
        }

        @Override
        public Street[] newArray(int size) {
            return new Street[size];
        }
    };
}
