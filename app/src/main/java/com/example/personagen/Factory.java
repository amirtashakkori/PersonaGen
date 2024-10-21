package com.example.personagen;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.personagen.Data.UserRepository;
import com.example.personagen.GenerateUser.GenerateViewModel;
import com.example.personagen.Main.MainViewModel;
import com.example.personagen.UserInfo.UserInfoViewModel;

public class Factory implements ViewModelProvider.Factory {

    UserRepository repo;

    public Factory(UserRepository repo) {
        this.repo = repo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass, @NonNull CreationExtras extras) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(repo);

        else if (modelClass.isAssignableFrom(GenerateViewModel.class))
            return (T) new GenerateViewModel(repo);

        else if (modelClass.isAssignableFrom(UserInfoViewModel.class))
            return (T) new UserInfoViewModel(repo);

        else
            throw new IllegalArgumentException("ViewModel is not valid!");

    }
}
