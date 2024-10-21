package com.example.personagen.Data.DataBase;

import androidx.room.TypeConverter;

import com.example.personagen.Data.Model.Dob;
import com.example.personagen.Data.Model.Location;
import com.example.personagen.Data.Model.Login;
import com.example.personagen.Data.Model.Name;
import com.example.personagen.Data.Model.Picture;
import com.example.personagen.Data.Model.Registered;
import com.google.gson.Gson;

public class Converters {
    // Converter for Name class
    @TypeConverter
    public static Name fromStringToName(String value) {
        return new Gson().fromJson(value, Name.class);
    }

    @TypeConverter
    public static String fromNameToString(Name name) {
        return new Gson().toJson(name);
    }

    // Converter for Location class
    @TypeConverter
    public static Location fromStringToLocation(String value) {
        return new Gson().fromJson(value, Location.class);
    }

    @TypeConverter
    public static String fromLocationToString(Location location) {
        return new Gson().toJson(location);
    }

    // Converter for Login class
    @TypeConverter
    public static Login fromStringToLogin(String value) {
        return new Gson().fromJson(value, Login.class);
    }

    @TypeConverter
    public static String fromLoginToString(Login login) {
        return new Gson().toJson(login);
    }

    // Converter for Dob class
    @TypeConverter
    public static Dob fromStringToDob(String value) {
        return new Gson().fromJson(value, Dob.class);
    }

    @TypeConverter
    public static String fromDobToString(Dob dob) {
        return new Gson().toJson(dob);
    }

    // Converter for Registered class
    @TypeConverter
    public static Registered fromStringToRegistered(String value) {
        return new Gson().fromJson(value, Registered.class);
    }

    @TypeConverter
    public static String fromRegisteredToString(Registered registered) {
        return new Gson().toJson(registered);
    }

    // Converter for Picture class
    @TypeConverter
    public static Picture fromStringToPicture(String value) {
        return new Gson().fromJson(value, Picture.class);
    }

    @TypeConverter
    public static String fromPictureToString(Picture picture) {
        return new Gson().toJson(picture);
    }
}
