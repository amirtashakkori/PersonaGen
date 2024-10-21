package com.example.personagen.Data;

import androidx.lifecycle.LiveData;

import com.example.personagen.Data.Api.ApiService;
import com.example.personagen.Data.DataBase.UserDao;
import com.example.personagen.Data.Model.ApiResponce;
import com.example.personagen.Data.Model.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class UserRepository {

    ApiService apiService;
    UserDao dao;

    public UserRepository(ApiService apiService, UserDao dao) {
        this.apiService = apiService;
        this.dao = dao;
    }

    public Single<User> generateRandomUser(String gender, String nationality){
        return apiService.getRandomUserNG(gender, nationality)
                .map(apiResponce -> apiResponce.results.get(0))
                .doOnSuccess(user -> dao.addUser(user));
    }

    public Single<ApiResponce> getGeneratedUser(String gender, String country){
        return apiService.getRandomUserNG(country, gender);
    }

    public LiveData<List<User>> getUsers(){
        return dao.getUsers();
    }

    public Completable deleteUser(User user){
        return Completable.fromAction(() -> dao.deleteUser(user));
    }

}
