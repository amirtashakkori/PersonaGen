package com.example.personagen.Main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.personagen.Data.Model.User;
import com.example.personagen.Data.UserRepository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    UserRepository repo;
    MutableLiveData<List<User>> users;
    MutableLiveData<String> errors = new MutableLiveData<>();
    Disposable disposable;

    public MainViewModel(UserRepository repo) {
        this.repo = repo;
    }

    public LiveData<List<User>> getUsers(){
        return repo.getUsers();
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
                });;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null)
            disposable.dispose();
    }
}
