package com.example.personagen.UserInfo;

import android.os.Looper;

import androidx.lifecycle.ViewModel;

import com.example.personagen.Data.Model.User;
import com.example.personagen.Data.UserRepository;

import java.util.concurrent.Executors;
import java.util.logging.Handler;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserInfoViewModel extends ViewModel {

    UserRepository repo;
    Disposable disposable;

    public UserInfoViewModel(UserRepository repo) {
        this.repo = repo;
    }

    public void deleteUser(User user){
        repo.deleteUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null)
            disposable.dispose();
    }
}

