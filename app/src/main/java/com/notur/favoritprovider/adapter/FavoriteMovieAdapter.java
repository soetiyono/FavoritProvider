package com.notur.favoritprovider.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.notur.favoritprovider.R;
import com.notur.favoritprovider.model.FavMovie;

import java.util.ArrayList;



public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder> {
    private ArrayList<FavMovie> data = new ArrayList<>();
    public void setListMovie(ArrayList<FavMovie> movies) {
        data.clear();
        data.addAll(movies);
        notifyDataSetChanged();
    }
    private Context context;

    public FavoriteMovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final FavMovie movie = data.get(i);
        viewHolder.txtTitle.setText(movie.getTitle());
        viewHolder.txtDescription.setText(movie.getDescription());
        viewHolder.txtRate.setText(movie.getRate());

        String uri = movie.getPoster();

        Glide.with(viewHolder.itemView.getContext())
                .load(uri)
                .into(viewHolder.imgPhoto);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(context, DetailMovie.class);
                //intent.putExtra(EXTRA_FAV_MOVIE,movie);
                //context.startActivities(new Intent[]{intent});
            }
        });

    }

    @Override
    public int getItemCount() {
        return (data == null)? 0 : data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtDescription;
        private ImageView imgPhoto;
        private TextView txtRate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            txtDescription = itemView.findViewById(R.id.txt_description);
            imgPhoto = itemView.findViewById(R.id.image_poster);
            txtRate = itemView.findViewById(R.id.txt_rate);
        }
    }
}
