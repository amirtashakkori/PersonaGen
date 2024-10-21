package com.example.personagen.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Name implements Parcelable {
    private String title;
    private String first;
    private String last;

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.first);
        dest.writeString(this.last);
    }

    public Name() {
    }

    protected Name(Parcel in) {
        this.title = in.readString();
        this.first = in.readString();
        this.last = in.readString();
    }

    public static final Parcelable.Creator<Name> CREATOR = new Parcelable.Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel source) {
            return new Name(source);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };
}
