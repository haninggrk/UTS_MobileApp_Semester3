package com.hgrk.moviedbuts.retrofit;

import com.hgrk.moviedbuts.model.Movies;
import com.hgrk.moviedbuts.model.NowPlaying;
import com.hgrk.moviedbuts.model.Upcoming;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiEndPoint {

    @GET("movie/{movie_id}")
    Call<Movies> getMovieById(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
            );

    @GET("movie/now_playing")
    Call<NowPlaying> getNowPlaying(
            @Query("api_key") String apiKey,
            @Query("page") int pageIndex
    );
    @GET("movie/upcoming")
    Call<Upcoming> getUpComing(
            @Query("api_key") String apiKey,
            @Query("page") int pageIndex
    );

}