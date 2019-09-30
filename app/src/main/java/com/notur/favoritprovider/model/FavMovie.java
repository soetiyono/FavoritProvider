package com.notur.favoritprovider.model;

import android.os.Parcel;
import android.os.Parcelable;


public class FavMovie implements Parcelable {

    public FavMovie(){}

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    private String title;
    private String poster;
    private String description;
    private String backdrop;
    private String rate;

    private FavMovie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        poster = in.readString();
        description = in.readString();
        backdrop = in.readString();
        rate = in.readString();
    }

    public static final Creator<FavMovie> CREATOR = new Creator<FavMovie>() {
        @Override
        public FavMovie createFromParcel(Parcel in) {
            return new FavMovie(in);
        }

        @Override
        public FavMovie[] newArray(int size) {
            return new FavMovie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(poster);
        dest.writeString(description);
        dest.writeString(backdrop);
        dest.writeString(rate);
    }
}
