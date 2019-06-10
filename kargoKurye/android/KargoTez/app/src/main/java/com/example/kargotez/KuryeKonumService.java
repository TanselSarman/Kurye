package com.example.kargotez;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KuryeKonumService extends Service implements LocationListener {


    LocationManager locationManager;
    String provider;

    Context context = this;

    private GoogleMap mMap;

    double latti;
    double lontitude;


    @Override
    public void onCreate() {
        super.onCreate();

        Criteria criteria = new Criteria();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(criteria, false);



        new Thread(new Runnable() {  // Yeni bir Thread (iş parcacığı) oluşturuyorum.
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() { // Thread'ım başladığında bitmemesi için while
                // ile sonsuz döngüye soktum. senaryo gereği
                while (1 == 1) {
                    try {
                        Thread.sleep(1500); // Her döngümde Thread'ımı 15000 ms uyutuyorum.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // ve 15 saniyem dolduktan sonra bildirimimi basıyorum.


                    //konumGuncelle();


                }
            }
        }).start();  // burada Thread'ımı başlatıyorum.


    }





    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }




    @Override
    public void onLocationChanged(Location location) {

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
