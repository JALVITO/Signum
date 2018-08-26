package com.appemergencias;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.appemergencias.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.appemergencias.MainActivity.numberOfEmergencyMessage;

public class MyService extends Service {


    boolean flag = false;
    boolean done[] = {false};

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //here goes whatever needs to be done after the app closes
        //for instance, we save the cart


        SharedPreferences settings = getSharedPreferences("com.appemergencias", MODE_PRIVATE);
        final String userId = settings.getString("userId", null);
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                while (!done[0]){
                    GetStuff gf = new GetStuff(HttpRequest.REQUEST_TYPES.GET, "/sendMessage/" + userId + "/" + MainActivity.numberOfEmergencyMessage, "");
                    gf.execute();
                }
            }
        });
        thread.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }


    private class GetStuff extends HttpRequest{

        public GetStuff(REQUEST_TYPES request_type, String url_route, String parameters) {
            super(request_type, url_route, parameters);
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String server_response) {
            done[0] = true;
        }
    }
}