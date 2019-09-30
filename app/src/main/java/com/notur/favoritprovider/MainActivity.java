package com.notur.favoritprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.notur.favoritprovider.adapter.FavoriteMovieAdapter;
import com.notur.favoritprovider.model.FavMovie;
import com.notur.favoritprovider.service.Callback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.notur.favoritprovider.db.DBContract.TableFavorite.CONTENT_URI_MOVIE;
import static com.notur.favoritprovider.db.MovieHelper.getMovieFav;

public class MainActivity extends AppCompatActivity implements Callback {
    FavoriteMovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new FavoriteMovieAdapter(this);
        RecyclerView rvMoview = findViewById(R.id.rv_fav);
        rvMoview.setLayoutManager(new LinearLayoutManager(this));
        rvMoview.setAdapter(adapter);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(CONTENT_URI_MOVIE, true, myObserver);
        new getFav(this, this).execute();

        getContentResolver().query(CONTENT_URI_MOVIE, null, null, null, null);
    }

    @Override
    public void postExecute(Cursor cursor) {

        ArrayList<FavMovie> listNotes = getMovieFav(cursor);
        if (listNotes.size() > 0) {
            adapter.setListMovie(listNotes);
        } else {
            Toast.makeText(this, "Tidak Ada data saat ini", Toast.LENGTH_SHORT).show();
            adapter.setListMovie(new ArrayList<FavMovie>());
        }
    }




    private static class getFav extends AsyncTask<Void, Void, Cursor> {
        private final WeakReference<Context> weakContext;
        private final WeakReference<Callback> weakCallback;


        private getFav(Context context, Callback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(CONTENT_URI_MOVIE, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor data) {
            super.onPostExecute(data);
            weakCallback.get().postExecute(data);
        }

    }

    static class DataObserver extends ContentObserver {

        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new getFav(context, (MainActivity) context).execute();
        }
    }



}
