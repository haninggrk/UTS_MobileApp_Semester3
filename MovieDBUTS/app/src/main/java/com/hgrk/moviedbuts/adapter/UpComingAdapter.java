package com.hgrk.moviedbuts.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hgrk.moviedbuts.R;
import com.hgrk.moviedbuts.helper.Cons;

import com.hgrk.moviedbuts.model.Upcoming;
import com.hgrk.moviedbuts.view.MovieDetails;

import java.util.ArrayList;
import java.util.List;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.CardViewViewHolder> {
    private Context contex;
    private List<Upcoming.Results> ListUpComing;
    private List<Upcoming.Results> getListUpComing(){
        return ListUpComing;
    }
    public void setListUpcoming(List<Upcoming.Results> ListUpComing){
        this.ListUpComing=ListUpComing;
    }
    public void set(List<Upcoming.Results>ListUpComing){
        this.ListUpComing=ListUpComing;
    }
    public UpComingAdapter(Context contex){
        this.contex=contex;
    }

    @NonNull
    @Override
    public UpComingAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent, false);
        return new UpComingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpComingAdapter.CardViewViewHolder holder, int position) {
        final Upcoming.Results results=getListUpComing().get(position);
        holder.lbl_title.setText(results.getTitle());


        Glide.with(contex).load(Cons.IMG_URL+ results.getBackdrop_path()).into(holder.img_poster);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (contex, MovieDetails.class);
                intent.putExtra("movie_id",""+results.getId());
                intent.putExtra("movie_title",results.getTitle());
                intent.putExtra("movie_backdrop",results.getBackdrop_path());
                intent.putExtra("movie_date",results.getRelease_date());
                intent.putExtra("movie_score",results.getVote_average());
                intent.putExtra("movie_desc",results.getOverview());
                intent.putExtra("movie_pic", results.getPoster_path());
                intent.putExtra("movie_voters", results.getVote_count());
                intent.putExtra("movie_id",results.getId());
                intent.putIntegerArrayListExtra("movie_genre", new ArrayList<Integer>(results.getGenre_ids()));
                contex.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListUpComing().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView lbl_title,lbl_overview,lbl_release_date;
        CardView cv;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster=itemView.findViewById((R.id.movie_card_preview));
            lbl_title=itemView.findViewById(R.id.movie_card_name);
            cv=itemView.findViewById(R.id.movie_card_cv);
        }
    }
}
