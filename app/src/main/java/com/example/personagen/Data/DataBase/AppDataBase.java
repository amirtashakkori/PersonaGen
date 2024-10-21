package com.example.personagen.Data.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.personagen.Data.Model.User;

@Database(version = 1, exportSchema = false , entities = {User.class})
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context){
        if (appDataBase == null){
            appDataBase = Room.databaseBuilder(context, AppDataBase.class, "db_users").build();
        }
        return appDataBase;
    }

    public abstract UserDao getDao();

}
