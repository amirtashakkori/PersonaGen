package com.example.personagen.GenerateUser;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.personagen.Data.Api.ApiService;
import com.example.personagen.Data.DataBase.UserDao;
import com.example.personagen.Data.Model.ApiResponce;
import com.example.personagen.Data.Model.User;
import com.example.personagen.Data.UserRepository;

import java.io.Closeable;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GenerateViewModel extends ViewModel {

    UserRepository repo;
    MutableLiveData<User> users = new MutableLiveData<>();
    MutableLiveData<String> errors = new MutableLiveData<>();
    Disposable disposable;

    public GenerateViewModel(UserRepository repo) {
        this.repo = repo;
    }

    public void generateUser(String gender, String nationality){
        repo.generateRandomUser(gender, nationality)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(User user) {
                        users.postValue(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errors.postValue(e.getMessage());
                    }
                });
    }

    public MutableLiveData<User> getUser() {
        return users;
    }

    public MutableLiveData<String> getError() {
        return errors;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null)
            disposable.dispose();
    }
}
