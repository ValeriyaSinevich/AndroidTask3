package com.example.valeriyasin.hometask3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

/**
 * Created by valeriyasin on 11/29/16.
 */

public class UpdateCommander extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

//        Log.d(Globals.TAG, "BroadcastReceiver, in onReceive:");

        Intent i = new Intent(context, PostGetterService.class);
        i.putExtra("date", (new Date()).toString());
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(i);
    }
}
