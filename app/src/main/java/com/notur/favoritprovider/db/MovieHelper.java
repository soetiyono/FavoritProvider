package com.notur.favoritprovider.db;

import android.database.Cursor;

import com.notur.favoritprovider.model.FavMovie;

import java.util.ArrayList;

import static com.notur.favoritprovider.db.DBContract.TableFavorite.BACKDROP_MOVIE;
import static com.notur.favoritprovider.db.DBContract.TableFavorite.DES_MOVIE;
import static com.notur.favoritprovider.db.DBContract.TableFavorite.ID_MOVIE;
import static com.notur.favoritprovider.db.DBContract.TableFavorite.POSTER_MOVIE;
import static com.notur.favoritprovider.db.DBContract.TableFavorite.RATE_MOVIE;
import static com.notur.favoritprovider.db.DBContract.TableFavorite.TITLE_MOVIE;

public class MovieHelper {



    public static ArrayList<FavMovie> getMovieFav(Cursor cursor){
        cursor.moveToFirst();
        ArrayList<FavMovie>list = new ArrayList<>();
        FavMovie movie;
        if (cursor.getCount()>0){
            do {
                movie = new FavMovie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_MOVIE)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_MOVIE)));
                movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DES_MOVIE)));
                movie.setRate(cursor.getString(cursor.getColumnIndexOrThrow(RATE_MOVIE)));
                movie.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_MOVIE)));
                movie.setBackdrop(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_MOVIE)));
                list.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return list;
    }


}
