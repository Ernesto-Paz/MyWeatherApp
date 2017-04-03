package com.ernesto.myweatherapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Gatobro on 3/20/17.
 */

public class GetLocationInfo <T extends Activity & LocationHandling> implements LocationListener{

    private T activity;
    private LocationManager locationManager;
    private static Location currentLocation;


    GetLocationInfo(T context){
        activity = context; //casting passed in context to the activity.
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
    }


   public void getLastKnownLocation() {

       int permission = ContextCompat.checkSelfPermission(activity, "android.permission.ACCESS_COARSE_LOCATION");
       Log.i("Permission", Integer.toString(permission));

       if (permission == PackageManager.PERMISSION_DENIED) {
           ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, RequestCodes.GETLASTKNOWNLOCATION);
           currentLocation = null;

       } else if (permission == PackageManager.PERMISSION_GRANTED) {

           Log.i("Got Permission", "Getting last known location.");
           currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
           if(currentLocation == null){
               Log.i("Null Location", "Last known location is null. Requesting update.");
               getLocationUpdate();
           }
           else{
               onLocationChanged(currentLocation);
           }
       } else {
           Log.e("Permission Denied", "Unable to get Location.");
           currentLocation = null;
       }

   }

   public void getLocationUpdate(){
       int permission = ContextCompat.checkSelfPermission(activity, "android.permission.ACCESS_COARSE_LOCATION");
       if(permission == PackageManager.PERMISSION_DENIED){
           ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, RequestCodes.GETLOCATIONUPDATE);
       }
       else if (permission == PackageManager.PERMISSION_GRANTED){
           locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
       }
   }

    public double[] getCoordinates(){

       if(currentLocation != null){
           Log.i("Get Coordinates", "Getting coordinates from location object.");
           double latitude = currentLocation.getLatitude();
           double longitude = currentLocation.getLongitude();
           double[] coordinates = new double[2];
           coordinates[0] = latitude;
           coordinates[1] = longitude;
           return coordinates;
       }
        else{
            Log.e("Location null", "Unable to get Coordinates.");
            return null;
       }

   }


    @Override
    public void onLocationChanged(Location location) {
        Log.i("Update Received", "Location info updated. Sending location to activity for handling.");
        activity.handleLocationInfo(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
