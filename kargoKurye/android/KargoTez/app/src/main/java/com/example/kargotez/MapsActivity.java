package com.example.kargotez;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private ProgressDialog progressDialog;
    private GoogleMap mMap;
    List<Address> adressList;
    Button btnFind, btnMapType, btn_konum_at;
    EditText etLocation;
    ZoomControls zoomControls;

    boolean isTrue=false;

    String kuryeAd;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnFind = findViewById(R.id.btn_Find);
        btnMapType = findViewById(R.id.btn_MapType);
        etLocation = findViewById(R.id.et_Locaiton);
        zoomControls = findViewById(R.id.zoom_Controls);
        btn_konum_at = findViewById(R.id.btn_konum_at);
        progressDialog = new ProgressDialog(this);








        btn_konum_at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konumVarmi();

                if(isTrue==true){

                    Toast.makeText(getApplicationContext(), "Bu kuryenin konumu oluşturulmuştur", Toast.LENGTH_LONG).show();

                }
                if (isTrue==false){

                    final double latti=mMap.getMyLocation().getLatitude();
                    final double lontitude=mMap.getMyLocation().getLongitude();
                    final String kurye=SharedPrefManager.getInstance(getApplicationContext()).getUsername();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constants.URL_KONUM,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);

                                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressDialog.hide();
                                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("kurye", kurye);
                            params.put("enlem", String.valueOf(latti));
                            params.put("boylam", String.valueOf(lontitude));
                            return params;
                        }
                    };


                    RequestHandler.getInstance(MapsActivity.this).addToRequestQueue(stringRequest);

                }





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




        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = etLocation.getText().toString();
                if(!TextUtils.isEmpty(location))
                {
                    Geocoder geocoder = new Geocoder(MapsActivity.this);

                    try {
                        adressList = geocoder.getFromLocationName(location,3,36,26,42,45);
                        while (adressList.size()==0) {
                            adressList = geocoder.getFromLocationName(location,3,36,26,42,45);

                        }

                        if(adressList.size()>0)
                        {
                            Toast.makeText(getApplicationContext(),adressList.size()+"Adet Sonuç bulundu",Toast.LENGTH_LONG).show();

                            for(int i=0;i<adressList.size();i++)
                            {
                                Address address = adressList.get(i);
                                LatLng myLatLng = new LatLng(address.getLatitude(),address.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(myLatLng).title("Here is"+location));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
                            }


                        }
                        else if(adressList.size()==0)
                        {
                            Toast.makeText(getApplicationContext(),"Adres Bulunmadı..",Toast.LENGTH_LONG).show();
                        }




                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void konumVarmi(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_KONUM_KONTROL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            kuryeAd=jsonObject.getString("kurye");




                            if (kuryeAd.equals(SharedPrefManager.getInstance(getApplicationContext()).getUsername())){

                                isTrue=true;


                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("kurye", SharedPrefManager.getInstance(getApplicationContext()).getUsername());

                return params;
            }
        };


        RequestHandler.getInstance(MapsActivity.this).addToRequestQueue(stringRequest);





    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);



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
                    mMap.setMyLocationEnabled(true);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"İzin verilmedi",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}