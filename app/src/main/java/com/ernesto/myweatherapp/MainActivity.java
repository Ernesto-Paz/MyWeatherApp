package com.ernesto.myweatherapp;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, WeatherHandling, LocationHandling{
    static final String apiKey = "";
    DecimalFormat formatnumber = new DecimalFormat("##0.#");
    GetLocationInfo<MainActivity> getLocationInfo;
    GetWeatherInfo<MainActivity> getWeatherInfo;
    DrawableManager drawableManager;
    ArrayList<Drawable> clearDay = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity context exists here.
        Resources resources = getResources();
        drawableManager = DrawableManager.getDrawableManagerInstance(this);
        getWeatherInfo = new GetWeatherInfo<>(MainActivity.this);
        getLocationInfo = new GetLocationInfo<>(MainActivity.this);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            getLocationInfo.getLastKnownLocation();

        }else{
            int lastIndex = savedInstanceState.getInt("ImageIndex");
            setBackground(lastIndex);
            setTemperature();
            setDescription();
        }
    }

    public void handleLocationInfo(Location location){
        //Handle new location information here. Use location object to request weather data.
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Log.i("Location info", Double.toString(longitude)+ ", " + Double.toString(latitude));
        if(getWeatherInfo.getStatus() != AsyncTask.Status.RUNNING) {
            getWeatherInfo.execute("http://api.openweathermap.org/data/2.5/weather?lat=" + Double.toString(latitude) + "&lon=" + Double.toString(longitude) + "&units=metric"+ "&appid=" + apiKey);
        }
    }

    public void handleWeatherInfo(String weatherData){
        //Set up UI after getting weather information.
        if(weatherData != null){
            Log.i("Weather Info", weatherData);
            Log.i("Weather Handler", "Handling Weather info!");
            WeatherData parasedWeatherData = WeatherData.createInstance(weatherData);
            //set up UI.
            //get appropriate background.
            setBackground();
            setTemperature();
            setDescription();

        }else{
            //Weatherdata came back as null, throw some kind of error for the user.
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch(requestCode){
            case RequestCodes.GETLASTKNOWNLOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getLocationInfo.getLastKnownLocation();
                }
                else{

                    //no permission deal with it.

                }
                break;
            case RequestCodes.GETLOCATIONUPDATE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getLocationInfo.getLocationUpdate();
                }
                else{

                    //no permission deal with it.

                }
                break;

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt("ImageIndex", DrawableManager.lastIndex);
        super.onSaveInstanceState(outState);
    }

    //If given an integer combines that integer with the WeatherData.weatherId code to pull a specific background.
    // Otherwise uses the WeatherData.weatherId and pulls a random appropriate background.
    public void setBackground(Integer index){
        ImageView background = (ImageView) findViewById(R.id.backgroundImageCurrentWeather);
        if(index == null && WeatherData.isValid == true) {
            if (WeatherData.weatherId >= 200 && WeatherData.weatherId <= 299) {
                //Group 2xx Thunderstorm
                Drawable backgroundImage = drawableManager.getThunderstorm();
                background.setImageDrawable(backgroundImage);
            } else if ((WeatherData.weatherId >= 300 && WeatherData.weatherId <= 399) || (WeatherData.weatherId >= 500 && WeatherData.weatherId <= 599)) {
                //Group 3xx and 5xx, Drizzle and Rain
                Drawable backgroundImage = drawableManager.getRainyDay();
                background.setImageDrawable(backgroundImage);

            } else if (WeatherData.weatherId >= 600 && WeatherData.weatherId <= 699) {
                //Group 6xx Snow
                Drawable backgroundImage = drawableManager.getSnow();
                background.setImageDrawable(backgroundImage);
            } else if (WeatherData.weatherId >= 700 && WeatherData.weatherId <= 799) {
                //Group 7xx Atmosphere
                Drawable backgroundImage = drawableManager.getRainyDay();
                background.setImageDrawable(backgroundImage);


            } else if (WeatherData.weatherId == 800) {
                //Group 800 Clear
                Drawable backgroundImage = drawableManager.getClearDay();
                background.setImageDrawable(backgroundImage);

            } else if (WeatherData.weatherId >= 801 && WeatherData.weatherId <= 809) {
                // Group 80x Clouds
                Drawable backgroundImage = drawableManager.getCloudyDay();
                background.setImageDrawable(backgroundImage);

            } else if (WeatherData.weatherId >= 900 && WeatherData.weatherId <= 909) {
                // Group 90x Extreme
                Drawable backgroundImage = drawableManager.getRainyDay();
                background.setImageDrawable(backgroundImage);


            } else if (WeatherData.weatherId >= 910) {
                //Group 9xx Additional
                Drawable backgroundImage = drawableManager.getRainyDay();
                background.setImageDrawable(backgroundImage);

            } else {
                //Default Case, Show clear day picture
                Drawable backgroundImage = drawableManager.getClearDay();
                background.setImageDrawable(backgroundImage);

            }
        }else if(WeatherData.isValid == true){
            if (WeatherData.weatherId >= 200 && WeatherData.weatherId <= 299) {
                //Group 2xx Thunderstorm
                Drawable backgroundImage = drawableManager.getThunderstorm(index);
                background.setImageDrawable(backgroundImage);
            } else if ((WeatherData.weatherId >= 300 && WeatherData.weatherId <= 399) || (WeatherData.weatherId >= 500 && WeatherData.weatherId <= 599)) {
                //Group 3xx and 5xx, Drizzle and Rain
                Drawable backgroundImage = drawableManager.getRainyDay(index);
                background.setImageDrawable(backgroundImage);

            } else if (WeatherData.weatherId >= 600 && WeatherData.weatherId <= 699) {
                //Group 6xx Snow
                Drawable backgroundImage = drawableManager.getSnow(index);
                background.setImageDrawable(backgroundImage);
            } else if (WeatherData.weatherId >= 700 && WeatherData.weatherId <= 799) {
                //Group 7xx Atmosphere
                Drawable backgroundImage = drawableManager.getRainyDay(index);
                background.setImageDrawable(backgroundImage);


            } else if (WeatherData.weatherId == 800) {
                //Group 800 Clear
                Drawable backgroundImage = drawableManager.getClearDay(index);
                background.setImageDrawable(backgroundImage);

            } else if (WeatherData.weatherId >= 801 && WeatherData.weatherId <= 809) {
                // Group 80x Clouds
                Drawable backgroundImage = drawableManager.getCloudyDay(index);
                background.setImageDrawable(backgroundImage);

            } else if (WeatherData.weatherId >= 900 && WeatherData.weatherId <= 909) {
                // Group 90x Extreme
                Drawable backgroundImage = drawableManager.getRainyDay(index);
                background.setImageDrawable(backgroundImage);


            } else if (WeatherData.weatherId >= 910) {
                //Group 9xx Additional
                Drawable backgroundImage = drawableManager.getRainyDay(index);
                background.setImageDrawable(backgroundImage);

            } else {
                //Default Case, Show clear day picture
                Drawable backgroundImage = drawableManager.getClearDay(index);
                background.setImageDrawable(backgroundImage);

            }

        }
    }

    //slightly hacky overloaded method that allows me to use the same code above instead of copying it into this function.
    public void setBackground(){
        setBackground(null);
    }
    //Checks if WeatherData has been initilized with proper data and then sets the textview temperature.
    public void setTemperature(){
        if(WeatherData.isValid == true) {
            Double temp = WeatherData.temp;
            TextView temperatureView = (TextView) findViewById(R.id.temperature);
            String tempText = formatnumber.format(temp);
            temperatureView.setText(tempText);
        }
    }
    //Check if WeatherData has been initilized with proper data and then sets the textview description.
    public void setDescription(){
        if(WeatherData.isValid == true){
            String desc = WeatherData.weatherDescription;
            TextView descriptionView = (TextView) findViewById(R.id.weatherDescription);
            descriptionView.setText(desc);
        }


    }
}
