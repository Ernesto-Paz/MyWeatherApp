package com.ernesto.myweatherapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gatobro on 3/24/17.
 */

public class WeatherData {
    private static WeatherData instance;
    public static String weatherMain;
    public static String weatherDescription;
    public static int weatherId;
    public static String weatherIcon;
    public static double temp_max;
    public static double temp_min;
    public static double temp;
    public static double pressure;
    public static double humidity;
    public static String visibility;
    public static double windspeed;
    public static double cloudsall;
    public static int sunrise;
    public static int sunset;
    public static String country;
    public static String city;
    public static boolean isValid = false;



   private WeatherData(String weatherdata){
        try {
            JSONObject data = new JSONObject(weatherdata);
            JSONObject weather =  new JSONArray(data.getString("weather")).getJSONObject(0); // id, main, description, icon
            weatherMain = weather.getString("main");
            weatherId = weather.getInt("id");
            weatherDescription = getDescription(weatherId);
            weatherIcon = weather.getString("icon");

            JSONObject main = data.getJSONObject("main"); //temp, pressure, humidity, temp_min, temp_max
            temp_max = main.getDouble("temp_max");
            temp_min = main.getDouble("temp_min");
            temp = main.getDouble("temp");
            pressure = main.getDouble("pressure");
            humidity = main.getDouble("humidity");

            visibility = data.getString("visibility");

            JSONObject wind = data.getJSONObject("wind"); // speed, deg
            windspeed = wind.getDouble("speed");

            JSONObject clouds = data.getJSONObject("clouds"); // all
            cloudsall = clouds.getDouble("all");

            JSONObject sys = data.getJSONObject("sys"); //type, id, message, country, sunrise, sunset
            country = sys.getString("country");
            sunrise = sys.getInt("sunrise");
            sunset = sys.getInt("sunset");

            city = data.getString("name");

            isValid = true;
            instance = this;
            Log.i("Weather", weatherIcon);
            Log.i("Country", country);

        }catch(JSONException e){
            e.printStackTrace();
            instance = null;
            isValid = false;
        }
    }

    public static WeatherData createInstance(String input){
        return new WeatherData(input);
    }

    public static WeatherData getInstance(){
        if(instance != null){
            return instance;
        }
        else{
            return null;
        }
    }
    private String getDescription(Integer id){
        if(id == null){
            return "";
        }
        else{
            String desc = "";

            if( id >= 200 && id <= 299 ){
                desc = "Thunderstorms";
                if(id == 200 || id == 210 || id == 230){desc = "Light Thunderstorm";}
                else if(id==202 || id == 212 || id ==232){desc = "Heavy Thunderstorm";}
            }
            else if (id >= 300 && id <= 399 ){
                desc = "Drizzle";
                if(id == 300 || id == 310){ desc = "Light Drizzle";}
                else if(id == 302 || id == 312 || id == 314){ desc = "Heavy Drizzle";}
            }
            else if ( id >= 500 && id <= 599 ){
                desc = "Rain";
                if(id == 500 || id == 520 ){desc = "Light Rain";}
                else if(id == 501){desc = "Moderate Rain";}
                else if(id == 502 || id == 503 || id == 522){desc = "Heavy Rain";}
                else if(id == 504){desc = "Extremely Heavy Rain";}
                else if(id == 511){desc = "Freezing Rain";}
            }
            else if ( id >= 600 && id <= 699 ){
                desc = "Snow";
                if(id == 600 || id == 620 ){desc = "Light Snow";}
                else if (id == 602 || id == 622){ desc = "Heavy Snow";}
                else if (id == 611 || id == 612){desc = "Sleet";}
                else if (id == 615 || id == 616){desc = "Rain and Snow";}

            }
            else if (id >= 700 && id <= 799){
                desc = "Unknown Atmospheric Weather";
                if (id == 701){ desc = "Mist"; }
                else if (id == 711){ desc = "Smoke"; }
                else if (id == 721){ desc = "Haze"; }
                else if (id == 731){ desc = "Dust Whirls";}
                else if (id == 741){ desc = "Fog"; }
                else if (id == 751){ desc = "Sand"; }
                else if (id == 761){ desc = "Dust"; }
                else if (id == 762){ desc = "Volcanic Ash";}
                else if (id == 771){ desc = "Squalls"; }
                else if (id == 781){ desc = "Tornado";}
            }
            else if (id == 800) {desc = "Clear Skies";}
            else if (id >= 801 && id <= 809){
                desc = "Cloudy";
                if(id == 801 || id == 802 || id == 803){desc = "Scattered Clouds";}
                else if(id == 804){ desc = "Overcast Clouds";}

            }
            else if(id >= 900 && id <= 909){
                desc = "Extreme Weather";
                if(id == 900){desc = "Tornadoes";}
                else if (id == 901){desc = "Tropical Storm";}
                else if (id == 902){desc = "Hurricane";}
                else if (id == 903){desc  = "Extremely Cold";}
                else if (id == 904){desc = "Extremely Hot";}
                else if (id == 905){desc = "Severe Winds";}
                else if (id == 906){desc = "Hail";}
            }
            else if(id >= 910 && id <= 999){
                desc = "";
                if(id == 951){desc = "Calm";}
                else if(id == 952 || id == 953 || id == 954 || id == 955 || id == 956 ){desc = "Breezy";}
                else if(id == 957 || id == 958 || id == 959){desc = "Gale Force Winds";}
                else if(id == 960 || id == 961){desc = "Storms";}
                else if (id == 962){desc = "Hurricane";}

            }
            return desc;
        }


    }



}
