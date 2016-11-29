package com.example.valeriyasin.hometask3;

import java.util.Observable;

/**
 * Created by valeriyasin on 11/29/16.
 */

public class OldestPostObservable extends Observable{
    private String date;
    OldestPostObservable() {
        addObserver(Activity1.getInstance().getRecyclerViewAdapter());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
