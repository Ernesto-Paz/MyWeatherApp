package com.ernesto.myweatherapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gatobro on 3/27/17.
 */

public class DrawableManager {
    Context context;
    public static DrawableManager instance = null;
    public static Integer lastIndex;
    Random randomInt = new Random();
    Resources resources;
    public static ColorDrawable defaultBackground = new ColorDrawable(Color.WHITE);
    ArrayList<Integer> clearDay = new ArrayList<>();
    ArrayList<Integer> clearNight = new ArrayList<>();
    ArrayList<Integer> cloudyDay = new ArrayList<>();
    ArrayList<Integer> cloudyNight = new ArrayList<>();
    ArrayList<Integer> rainyDay = new ArrayList<>();
    ArrayList<Integer> rainyNight = new ArrayList<>();
    ArrayList<Integer> snow = new ArrayList<>();
    ArrayList<Integer> thunderstorm = new ArrayList<>();

    private DrawableManager(Context contextref){
        context = contextref;
        resources = context.getResources();
        clearDay.add(R.drawable.clearday0);
        clearDay.add(R.drawable.clearday1);
        clearDay.add(R.drawable.clearday2);
        clearDay.add(R.drawable.clearday3);
        clearNight.add(R.drawable.clearnight0);
        clearNight.add(R.drawable.clearnight1);
        clearNight.add(R.drawable.clearnight2);
        clearNight.add(R.drawable.clearnight3);
        clearNight.add(R.drawable.clearnight3);
        clearNight.add(R.drawable.clearnight4);
        clearNight.add(R.drawable.clearnight5);
        clearNight.add(R.drawable.clearnight6);
        cloudyDay.add(R.drawable.cloudyday0);
        cloudyDay.add(R.drawable.cloudyday1);
        cloudyDay.add(R.drawable.cloudyday2);
        cloudyDay.add(R.drawable.cloudyday3);
        cloudyDay.add(R.drawable.cloudyday4);
        cloudyDay.add(R.drawable.cloudyday5);
        cloudyNight.add(R.drawable.cloudynight0);
        cloudyNight.add(R.drawable.cloudynight1);
        cloudyNight.add(R.drawable.cloudynight2);
        rainyDay.add(R.drawable.rainyday0);
        rainyDay.add(R.drawable.rainyday1);
        rainyDay.add(R.drawable.rainyday2);
        rainyNight.add(R.drawable.rainynight0);
        rainyNight.add(R.drawable.rainynight1);
        rainyNight.add(R.drawable.rainynight2);
        rainyNight.add(R.drawable.rainynight3);
        rainyNight.add(R.drawable.rainynight4);
        snow.add(R.drawable.snow0);
        snow.add(R.drawable.snow1);
        snow.add(R.drawable.snow2);
        snow.add(R.drawable.snow3);
        snow.add(R.drawable.snow4);
        thunderstorm.add(R.drawable.thunderstorm0);
        thunderstorm.add(R.drawable.thunderstorm1);
        thunderstorm.add(R.drawable.thunderstorm2);
        thunderstorm.add(R.drawable.thunderstorm3);
        thunderstorm.add(R.drawable.thunderstorm4);
        instance = this;
    }

    public static DrawableManager getDrawableManagerInstance(Context contextref){
        if(instance == null){
            return new DrawableManager(contextref);
        }else{

            return instance;

        }

    }
    private int checkBounds(int arraySize, int index){
        if( index >= arraySize ){
            Log.e("Index Overflow", "Adjusting index to fit array.");
            return (arraySize - 1);

        }
        else{

            return index;
        }

    }


    //If given an int, fetches the desired index, checks against list size to avoid null pointers.
    public Drawable getClearDay(int index){
        int arraySize = clearDay.size();
        index = checkBounds(arraySize, index);
        lastIndex = index;
        int drawableId = clearDay.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getClearNight(int index){
        int arraySize = clearNight.size();
        index = checkBounds(arraySize, index);
        lastIndex = index;
        int drawableId = clearNight.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getCloudyDay(int index){
        int arraySize = cloudyDay.size();
        index = checkBounds(arraySize, index);
        lastIndex = index;
        int drawableId = cloudyDay.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getCloudyNight(int index){
        int arraySize = cloudyNight.size();
        index = checkBounds(arraySize, index);
        lastIndex = index;
        int drawableId = cloudyNight.get(index);
        return resources.getDrawable(drawableId, null);
    }

    public Drawable getRainyDay(int index){
        int arraySize = rainyDay.size();
        index = checkBounds(arraySize, index);
        lastIndex = index;
        int drawableId = rainyDay.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getRainyNight(int index){
        int arraySize = rainyNight.size();
        index = checkBounds(arraySize, index);
        lastIndex = index;
        int drawableId = rainyNight.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getSnow(int index){
        int arraySize = snow.size();
        index = checkBounds(arraySize, index);
        lastIndex = index;
        int drawableId = snow.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getThunderstorm(int index){
        int arraySize = thunderstorm.size();
        index = checkBounds(arraySize, index);
        lastIndex = index;
        int drawableId = thunderstorm.get(index);
        return resources.getDrawable(drawableId, null);
    }


/////if not given an int, fetches a random image.

    public Drawable getClearDay(){
        int arraySize = clearDay.size();
        int index = randomInt.nextInt(arraySize);
        lastIndex = index;
        int drawableId = clearDay.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getClearNight(){
        int arraySize = clearNight.size();
        int index = randomInt.nextInt(arraySize);
        lastIndex = index;
        int drawableId = clearNight.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getCloudyDay(){
        int arraySize = cloudyDay.size();
        int index = randomInt.nextInt(arraySize);
        lastIndex = index;
        int drawableId = cloudyDay.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getCloudyNight(){
        int arraySize = cloudyNight.size();
        int index = randomInt.nextInt(arraySize);
        lastIndex = index;
        int drawableId = cloudyNight.get(index);
        return resources.getDrawable(drawableId, null);
    }

    public Drawable getRainyDay(){
        int arraySize = rainyDay.size();
        int index = randomInt.nextInt(arraySize);
        lastIndex = index;
        int drawableId = rainyDay.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getRainyNight(){
        int arraySize = rainyNight.size();
        int index = randomInt.nextInt(arraySize);
        lastIndex = index;
        int drawableId = rainyNight.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getSnow(){
        int arraySize = snow.size();
        int index = randomInt.nextInt(arraySize);
        lastIndex = index;
        int drawableId = snow.get(index);
        return resources.getDrawable(drawableId, null);
    }
    public Drawable getThunderstorm(){
        int arraySize = thunderstorm.size();
        int index = randomInt.nextInt(arraySize);
        lastIndex = index;
        int drawableId = thunderstorm.get(index);
        return resources.getDrawable(drawableId, null);
    }




}
