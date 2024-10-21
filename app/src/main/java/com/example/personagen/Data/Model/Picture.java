package com.example.personagen.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable {
    private String large;
    private String medium;
    private String thumbnail;



    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.large);
        dest.writeString(this.medium);
        dest.writeString(this.thumbnail);
    }

    public Picture() {
    }

    protected Picture(Parcel in) {
        this.large = in.readString();
        this.medium = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<Picture> CREATOR = new Parcelable.Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel source) {
            return new Picture(source);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}
