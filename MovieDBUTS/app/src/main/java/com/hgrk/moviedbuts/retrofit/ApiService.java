package com.hgrk.moviedbuts.retrofit;

import com.hgrk.moviedbuts.helper.Cons;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static Retrofit retrofit;

    public static ApiEndPoint endpoint(){
        retrofit= new Retrofit.Builder()
                .baseUrl(Cons.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
return   retrofit.create(ApiEndPoint.class);
    }

}
