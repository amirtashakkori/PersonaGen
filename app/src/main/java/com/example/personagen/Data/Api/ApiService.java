package com.example.personagen.Data.Api;

import com.example.personagen.Data.Model.ApiResponce;
import com.example.personagen.Data.Model.User;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/")
    Single<ApiResponce> getRandomUserNG(@Query("gender") String Gender, @Query("nat") String Nationality);

}
