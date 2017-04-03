package com.ernesto.myweatherapp;

/**
 * Created by Gatobro on 3/19/17.
 */

import android.app.Activity;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetWeatherInfo <T extends Activity & WeatherHandling> extends AsyncTask<String, Void, String>{
    T activity;


    GetWeatherInfo(T context){
        activity = (T) context;
    }


    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection Connection;

            Connection = (HttpURLConnection) url.openConnection();
            InputStream input = Connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            int data = reader.read();
            while(data != -1){
                char newdata = (char) data;
                result += newdata;
                data = reader.read();
            }
            return result;


        }catch(IOException e){
            e.printStackTrace();

        }

        return null;
    }

    @Override
    protected void onPostExecute(String weatherData) {
        if(weatherData != null){
        activity.handleWeatherInfo(weatherData);
        }

    }
}
