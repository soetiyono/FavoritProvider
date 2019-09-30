package com.notur.favoritprovider.service;

import android.database.Cursor;

public interface Callback {
    void postExecute(Cursor movies);
}
