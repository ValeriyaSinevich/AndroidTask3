package com.example.valeriyasin.hometask3;

/**
 * Created by valeriyasin on 11/29/16.
 */

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class PostGetterService extends Service {

    final String LOG_TAG = "myLogs";

    final int STATUS_OK = 0;
    final String TABLE_NAME = "PostDB";

    DBConnectionHandler dbConnectionHandler;
    SQLiteDatabase db;
    PostGetter postGetter;

    private class PostGetter extends AsyncTask<String, Void, String> {
        PostGetterService service;

        PostGetter(PostGetterService service) {
            this.service = service;
        }
        //in this method we retrieve the Num earliest (but older than input param date -
        // default - now) posts and add them to the data base (if not already there)
        //we aso update the oldestDate string class to notify the RecyclerViewAdapter about the change
        @Override
        protected String doInBackground(String... params) {
            String url = "";//???????????????????????????????
            int status = STATUS_OK;
            while (status == STATUS_OK) {
                try {
//                System.out.println("ATG_dib");
                    URL u = new URL(url);
                    HttpsURLConnection connection = (HttpsURLConnection) u.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.connect();
                    status = connection.getResponseCode();
                    if (status != STATUS_OK) {
                        return "no more posts " + status;
                    } else {
                        System.out.println("Going to get dome response...");
                        String response = getResponseString(connection.getInputStream());
                        Post newPost =  getPostFromResponseString(response);
                        service.insertDownloadedPost(newPost);
                        Activity1.getInstance().getOldestPostObservable().setDate(date);
                        return "";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String token) {
        }
    }

    public void insertDownloadedPost(Post post) {
        ContentValues cv = new ContentValues();
        // получаем данные из полей ввода
        String name = post.getText().toString();
        String email = post.getText().toString();
        db.insert(TABLE_NAME, null, cv);
    }

    Post getPostFromResponseString(String response) {
        return new Post();
    }

    public void onCreate() {
        super.onCreate();
        dbConnectionHandler = new DBConnectionHandler(this);
        db = dbConnectionHandler.getWritableDatabase();
        postGetter = new PostGetter(this);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        postGetter.execute(intent.getStringExtra("date"));
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

}
