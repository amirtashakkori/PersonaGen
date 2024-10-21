package com.example.personagen.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity(tableName = "table_users")
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @Expose(deserialize = false)
    public long userId;
    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Login login;
    private Dob dob;
    private Registered registered;
    private String phone;
    private String cell;
    private Picture picture;
    private String nat;


    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public Login getLogin() {
        return login;
    }

    public Dob getDob() {
        return dob;
    }

    public Registered getRegistered() {
        return registered;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public Picture getPicture() {
        return picture;
    }

    public String getNat() {
        return nat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.userId);
        dest.writeString(this.gender);
        dest.writeParcelable(this.name, flags);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.email);
        dest.writeParcelable(this.login, flags);
        dest.writeParcelable(this.dob, flags);
        dest.writeParcelable(this.registered, flags);
        dest.writeString(this.phone);
        dest.writeString(this.cell);
        dest.writeParcelable(this.picture, flags);
        dest.writeString(this.nat);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userId = in.readLong();
        this.gender = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.email = in.readString();
        this.login = in.readParcelable(Login.class.getClassLoader());
        this.dob = in.readParcelable(Dob.class.getClassLoader());
        this.registered = in.readParcelable(Registered.class.getClassLoader());
        this.phone = in.readString();
        this.cell = in.readString();
        this.picture = in.readParcelable(Picture.class.getClassLoader());
        this.nat = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
