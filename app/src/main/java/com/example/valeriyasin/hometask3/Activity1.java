package com.example.valeriyasin.hometask3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

/**
 * Created by valeriyasin on 11/28/16.
 */

public class Activity1 extends AppCompatActivity {
    private static Activity1 instance;
    private OldestPostObservable oldestPostObservable;
    private RecyclerViewAdapter recyclerViewAdapter;
    private AlarmManager alarmManager;

    public void setupAlarm(int seconds) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), UpdateCommander.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                Activity1.this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

//        Log.d(Globals.TAG, "Setup the alarm");


        Calendar calendar = Calendar.getInstance();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupAlarm(10);
        oldestPostObservable = new OldestPostObservable();
    }

    public static Activity1 getInstance() {
        return instance;
    }

    public OldestPostObservable getOldestPostObservable() {
        return oldestPostObservable;
    }

    public RecyclerViewAdapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }


    //in case we want to see the older ones. Important: when we get back to the top, we have to
    //lauch the alarmManager one more time
    //todo: understand how the Recycler view works
    public void getMorePosts() {
        Intent i = new Intent(Activity1.this, PostGetterService.class);
        i.putExtra("date", oldestPostObservable.getDate());
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(i);
        alarmManager.cancel();//?????????
    }
}
