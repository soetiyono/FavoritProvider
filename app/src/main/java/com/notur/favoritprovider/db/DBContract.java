package com.notur.favoritprovider.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DBContract {


    public static final String AUTHORITY = "com.notur.finalproject";


    public static final class TableFavorite implements BaseColumns{
        static  String ID_MOVIE = "id";
        static  String TITLE_MOVIE = "title";
        static  String DES_MOVIE = "description";
        static  String RATE_MOVIE = "rate";
        static  String POSTER_MOVIE = "poster";
        static  String BACKDROP_MOVIE = "backdrop";

        static final String TABLE_fAVORITE_MOVIE = "table_movie";

        public static final Uri CONTENT_URI_MOVIE = new Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendPath(TABLE_fAVORITE_MOVIE)
                .build();
    }




}
