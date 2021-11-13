package com.hgrk.moviedbuts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder> {
    @NonNull
    @Override
    public NowPlayingAdapter.NowPlayingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingAdapter.NowPlayingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class NowPlayingViewHolder extends RecyclerView.ViewHolder {

        ImageView movie_poster;
        TextView movie_title;

        NowPlayingViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_poster = itemView.findViewById(R.id.movie_card_preview);
            movie_title = itemView.findViewById(R.id.movie_card_name);
        }
    }
}
