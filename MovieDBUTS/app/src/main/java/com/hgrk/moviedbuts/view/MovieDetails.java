package com.hgrk.moviedbuts.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hgrk.moviedbuts.R;
import com.hgrk.moviedbuts.adapter.MoviesAdapter;
import com.hgrk.moviedbuts.helper.Cons;
import com.hgrk.moviedbuts.model.Movies;
import com.hgrk.moviedbuts.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MovieDetails extends AppCompatActivity {
    ImageView detail_preview, detail_preview_vertical;
    TextView detail_title, detail_rating, detail_genre, detail_date, detail_overview, detail_description;
    RecyclerView rv_company;
    MovieViewModel movieVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
         detail_preview=findViewById(R.id.detail_preview);
         detail_preview_vertical=findViewById(R.id.detail_preview_vertical);
         detail_date=findViewById(R.id.detail_genre);
         detail_genre=findViewById(R.id.detail_date);
         detail_genre.setText("");
        detail_rating=findViewById(R.id.detail_rating);
        detail_title=findViewById(R.id.detail_title);
        detail_overview=findViewById(R.id.detail_description);
        detail_description=findViewById(R.id.detail_description);
        rv_company = findViewById(R.id.detail_rv_company);
        movieVM = new ViewModelProvider(this).get(MovieViewModel.class);
        Intent intent =getIntent();
        Glide.with(getApplicationContext()).load(Cons.IMG_URL+ intent.getStringExtra("movie_backdrop")).into(detail_preview);
        Glide.with(getApplicationContext()).load(Cons.IMG_URL+ intent.getStringExtra("movie_pic")).into(detail_preview_vertical);
        detail_overview.setText(intent.getStringExtra("movie_desc"));
        detail_title.setText(intent.getStringExtra("movie_title"));
        detail_date.setText("Release: "+intent.getStringExtra("movie_date"));
        ArrayList<Integer> genre = intent.getIntegerArrayListExtra("movie_genre");
        double score = intent.getDoubleExtra("movie_score",0);
        detail_rating.setText(String.valueOf(score)+" Rating ("+intent.getIntExtra("movie_voters",0)+" Vote)");
        movieVM.getMovieById(String.valueOf(intent.getIntExtra("movie_id",0)));
        movieVM.getResultGetMovieById().observe(this, showMovie);

    }
    private Observer<Movies> showMovie = new Observer<Movies>(){

        @Override

        public void onChanged(Movies movies) {
            LinearLayoutManager lm = new LinearLayoutManager(MovieDetails.this, LinearLayoutManager.HORIZONTAL, false);
            rv_company.setLayoutManager(lm);
            MoviesAdapter adapter = new MoviesAdapter(MovieDetails.this,"Company");
            adapter.setListMovies(movies.getProduction_companies());
            adapter.setListGenre(movies.getGenres());
            rv_company.setAdapter(adapter);
            for(int i=0; i<movies.getGenres().size();i++){
                detail_genre.setText(detail_genre.getText().toString()+movies.getGenres().get(i).getName()+" | ");
            }

        }
    };
}