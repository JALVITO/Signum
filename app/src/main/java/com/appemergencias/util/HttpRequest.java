package com.appemergencias.util;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rober on 26/08/2018.
 */


public abstract class HttpRequest extends AsyncTask<Void, Void, String> {
    //Request Variables
    public enum REQUEST_TYPES { GET, POST, POST2LEV}
    private REQUEST_TYPES request_type;
    private String url_route, parameters;
    private ArrayList<Pair<String, String>> PostParameters;
    private ArrayList<Pair<String, ArrayList<ArrayList<Pair<String, String>>>>> Post2LevParameters;
    private final String REQUEST_URL;
    private Uri.Builder builder;

    //GET Constructor
    public HttpRequest(REQUEST_TYPES request_type, String url_route, String parameters) {
        //Request Variables
        this.url_route = url_route;
        this.parameters = parameters;
        this.request_type = request_type;
        this.REQUEST_URL = "https://emergencyappservice.herokuapp.com";
    }

    //POST Constructor
    public HttpRequest(REQUEST_TYPES request_type, String url_route, ArrayList<Pair<String, String>> PostParameters) {
        //RequestVariables
        this.url_route = url_route;
        this.PostParameters = PostParameters;
        this.request_type = request_type;
        this.REQUEST_URL = "https://emergencyappservice.herokuapp.com";
    }

    @Override
    protected abstract void onPreExecute();

    @Override
    protected final String doInBackground(Void... voids) {

        URL url = null;
        String linea = "";
        int response = 0;
        StringBuilder result = null;

        try {
            Log.d("-----------", "Entre pt2");
            //url = new URL(REQUEST_URL + this.url_route + API_KEY);
            switch (this.request_type) {
                case GET:
                    //pass the real parameters for the login
                    url = new URL(REQUEST_URL + this.url_route + this.parameters);
                    Log.d("Url ----", url.toString());
                    break;
                case POST:
                    url = new URL(REQUEST_URL + this.url_route);
                    break;
                case POST2LEV:
                    url = new URL(REQUEST_URL + this.url_route);
                    break;
                default:
                    throw new Exception("Not a valid verb");
            }
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            //conection.setDoOutput(true);
            //conection.connect();
            switch (this.request_type) {
                case GET:
                    conection.connect();
                    conection.setRequestMethod("GET");

                    break;
                default:
                    throw new Exception("Not a valid verb");
            }

            response = conection.getResponseCode();

            result = new StringBuilder();

            if (response == HttpURLConnection.HTTP_OK) {
                //change between inputstream and output stream in separated functions
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            } else {
                result.append("{'success':false}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("-----", "llegue hasta el final");
        return result.toString();
    }

    @Override
    protected abstract void onPostExecute(String server_response);

}

