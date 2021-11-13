package com.hgrk.moviedbuts.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hgrk.moviedbuts.R;
import com.hgrk.moviedbuts.helper.Cons;
import com.hgrk.moviedbuts.model.Movies;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.CardViewViewHolder> {

    private Context context;
    private String tag;
    private List<Movies.ProductionCompaniesDTO> listMovies;
    private List<Movies.GenresDTO> listGenre;
    private List<Movies.ProductionCompaniesDTO> getListMovies(){return listMovies;}
    private List<Movies.GenresDTO> getListGenre(){return listGenre;}
    public void setListMovies(List<Movies.ProductionCompaniesDTO> listMovies){
        this.listMovies = listMovies;
    }
    public void setListGenre(List<Movies.GenresDTO> genre){
        this.listGenre = genre;
    }
    public MoviesAdapter(Context context, String tag){
        this.context = context;
        this.tag = tag;
    }
    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(tag.equalsIgnoreCase("Company")) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_card, parent, false);
        }
        return new MoviesAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {

        final Movies.ProductionCompaniesDTO results = getListMovies().get(position);
        String error_link = "https://rimatour.com/wp-content/uploads/2017/09/No-image-found.jpg";
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, results.getName(), Toast.LENGTH_SHORT).show();

            }
        });
        if (results != null) {
            Glide.with(context).load(Cons.IMG_URL + results.getLogo_path()).into(holder.logo);

        } else{
            Glide.with(context).load(error_link).into(holder.logo);
        }

    }

    @Override
    public int getItemCount() {
        return getListMovies().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        CardView cv;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logo);
            cv = itemView.findViewById(R.id.cv_company);
        }
    }
}
