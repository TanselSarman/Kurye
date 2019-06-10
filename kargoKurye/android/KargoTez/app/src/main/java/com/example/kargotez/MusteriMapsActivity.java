package com.example.kargotez;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ZoomControls;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.Permission;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MusteriMapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private ProgressDialog progressDialog;
    private GoogleMap mMap;
    List<Address> adressList;
    Button btnFind, btnMapType, btn_konum_at;
    EditText etLocation;
    ZoomControls zoomControls;

    String kurye=new String();
    String enlem=new String();
    String boylam=new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteri_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnFind = findViewById(R.id.btn_Find);
        btnMapType = findViewById(R.id.btn_MapType);
        etLocation = findViewById(R.id.et_Locaiton);
        zoomControls = findViewById(R.id.zoom_Controls);
        btn_konum_at = findViewById(R.id.btn_konum_at);
        progressDialog = new ProgressDialog(this);

        kurye=getIntent().getExtras().getString("kurye");
        enlem=getIntent().getExtras().getString("enlem");
        boylam=getIntent().getExtras().getString("boylam");





        btn_konum_at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double enlem1= Double.parseDouble(enlem);
                double boylam1= Double.parseDouble(boylam);

                LatLng myLatLng2 = new LatLng(enlem1,boylam1);
                mMap.addMarker(new MarkerOptions().position(myLatLng2).title("KURYE"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng2));

            }
        });



        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.moveCamera(CameraUpdateFactory.zoomIn());
            }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.moveCamera(CameraUpdateFactory.zoomOut());
            }
        });
        btnMapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    btnMapType.setText("Norm");
                } else if (mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    btnMapType.setText("Sat");
                }
            }
        });



    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(false);



        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);

        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 200) {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                {
                    mMap.setMyLocationEnabled(false);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"İzin verilmedi",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}