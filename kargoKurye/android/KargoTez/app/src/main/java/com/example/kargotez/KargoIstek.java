package com.example.kargotez;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KargoIstek extends AppCompatActivity implements OnMapReadyCallback {

    private ProgressDialog progressDialog;
    private GoogleMap mMap;

    Button btnFind, btnMapType, btn_konum_at;
    EditText etLocation;
    ZoomControls zoomControls;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kargo_istek);
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

                    final double latti=mMap.getMyLocation().getLatitude();
                    final double lontitude=mMap.getMyLocation().getLongitude();
                    final String kullanici=SharedPrefManager.getInstance(getApplicationContext()).getUsername();
                    final String sehir=SharedPrefManager.getInstance(getApplicationContext()).getUserSehır();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constants.URL_KARGO_ISTEK,
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
                            params.put("kullanici", kullanici);
                            params.put("enlem", String.valueOf(latti));
                            params.put("boylam", String.valueOf(lontitude));
                            params.put("sehir", sehir);
                            return params;
                        }
                    };


                    RequestHandler.getInstance(KargoIstek.this).addToRequestQueue(stringRequest);







            }
        });









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
