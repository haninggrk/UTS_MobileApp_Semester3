package com.hgrk.moviedbuts.repositories;

import androidx.lifecycle.MutableLiveData;

import com.hgrk.moviedbuts.helper.Cons;
import com.hgrk.moviedbuts.model.Movies;
import com.hgrk.moviedbuts.model.NowPlaying;
import com.hgrk.moviedbuts.model.Upcoming;
import com.hgrk.moviedbuts.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository repository;
    private ArrayList<NowPlaying.Results> listNowPlaying = new ArrayList<>();
    private ArrayList<Upcoming.Results> listUpComing = new ArrayList<>();
    private MovieRepository() {
    }

    public static MovieRepository getInstance() {
        if (repository == null) {
            repository = new MovieRepository();
        }
        return repository;
    }

    public MutableLiveData<Movies> getMovieData(String movieId) {
        final MutableLiveData<Movies> result = new MutableLiveData<>();
        ApiService.endpoint().getMovieById(movieId, Cons.API_KEY).enqueue((new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        }));
        return result;
    }

    public MutableLiveData<List<NowPlaying.Results>> getNowPlayingData(int current_page_now) {
        final MutableLiveData<List<NowPlaying.Results>> result = new MutableLiveData<>();
        ApiService.endpoint().getNowPlaying( Cons.API_KEY,current_page_now).enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                for(NowPlaying.Results i: response.body().getResults()) {
                    listNowPlaying.add(i);
                }
                result.setValue((List<NowPlaying.Results>)listNowPlaying);
            }
            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {

            }
        });
        return result;
    }
    public MutableLiveData<List<Upcoming.Results>> getUpComingData(int current_page_now) {
        final MutableLiveData<List<Upcoming.Results>> result = new MutableLiveData<>();
        ApiService.endpoint().getUpComing( Cons.API_KEY, current_page_now).enqueue(new Callback<Upcoming>() {
            @Override
            public void onResponse(Call<Upcoming> call, Response<Upcoming> response) {
                for(Upcoming.Results i: response.body().getResults()) {
                    listUpComing.add(i);
                }
                result.setValue((List<Upcoming.Results>)listUpComing);
            }
            @Override
            public void onFailure(Call<Upcoming> call, Throwable t) {

            }
        });
        return result;
    }

}

