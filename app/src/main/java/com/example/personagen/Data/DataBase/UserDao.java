package com.example.personagen.Data.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.personagen.Data.Model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * from table_users")
    LiveData<List<User>> getUsers();

    @Insert
    void addUser(User user);

    @Delete
    int deleteUser(User user);

}
