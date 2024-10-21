package com.example.personagen.Data.Api;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceProvider {

    private static ApiService apiService;

    public static ApiService getApiService(){
        if (apiService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

}
