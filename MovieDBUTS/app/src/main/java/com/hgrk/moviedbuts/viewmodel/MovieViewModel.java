package com.hgrk.moviedbuts.viewmodel;



import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hgrk.moviedbuts.model.Movies;
import com.hgrk.moviedbuts.model.NowPlaying;
import com.hgrk.moviedbuts.model.Upcoming;
import com.hgrk.moviedbuts.repositories.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance();

    }

    //==begin of viewmodel get movie by id
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();

    public void getMovieById(String movieId) {
        resultGetMovieById = repository.getMovieData(movieId);
    }

    public LiveData<Movies> getResultGetMovieById() {
        return resultGetMovieById;
    }

//==end of viewmodel get movie by id


    //==begin of viewmodel get now playing
    private MutableLiveData<List<NowPlaying.Results>> resultGetNowPlaying= new MutableLiveData<>();
    private MutableLiveData<List<Upcoming.Results>> resultGetUpComing= new MutableLiveData<>();
    public void getNowPlaying(int page){
        resultGetNowPlaying = repository.getNowPlayingData(page);
    }
    public void getUpComing(int page){
        resultGetUpComing = repository.getUpComingData(page);
    }
    public LiveData<List<NowPlaying.Results>> getResultNowPlaying(){
        return  resultGetNowPlaying;
    }
    public LiveData<List<Upcoming.Results>> getResultUpComing(){
        return  resultGetUpComing;
    }
//==end of viewmodel get movie by id
}
