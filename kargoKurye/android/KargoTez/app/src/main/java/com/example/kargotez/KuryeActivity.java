package com.example.kargotez;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KuryeActivity extends AppCompatActivity implements LocationListener {


    LocationManager locationManager;
    String provider;

    double latti;
    double lontitude;

    String kurye;

    TextView txt_kurye_isim;

    Context context = this;
    ImageView img_teslim, img_subede, img_konum,img_istekler;
    ImageButton img_mesajlar, img_gidecekler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurye);
        init();

        Intent NotifyService = new Intent(KuryeActivity.this, KuryeBildirimService.class);
        startService(NotifyService);



        txt_kurye_isim.setText(SharedPrefManager.getInstance(getApplicationContext()).getUsername());


        img_konum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);
            }
        });

        img_mesajlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KuryeGelenMesajlarActivity.class);
                startActivity(intent);
            }
        });

        img_teslim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KuryeTeslimEdilenKargolarActivity.class);
                startActivity(intent);
            }
        });
        img_subede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, KuryeAlınacakKargolarActivity.class);
                startActivity(intent);

            }
        });

        img_istekler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, KuryeGelenIsteklerListe.class);
                startActivity(intent);
            }
        });


        img_gidecekler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KargoListeActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 100, 1, this);
        konumGuncelle();


    }

    private void konumGuncelle() {
        kurye = SharedPrefManager.getInstance(getApplicationContext()).getUsername();

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST,
                Constants.URL_KONUM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

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
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest2);
    }

    public void init(){
        txt_kurye_isim= (TextView) findViewById(R.id.txt_kurye_isim);
        img_gidecekler=findViewById(R.id.img_gidecekler);
        img_teslim=findViewById(R.id.img_teslim);
        img_subede=findViewById(R.id.img_subede);
        img_mesajlar=findViewById(R.id.img_mesajlar);
        img_konum=findViewById(R.id.img_konum);
        img_istekler=findViewById(R.id.img_istekler);

        Criteria criteria = new Criteria();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        final Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        latti=location.getLatitude();
        lontitude=location.getLongitude();
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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   /* private void konumKontrol(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_KONUM_KONTROL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


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
        RequestHandler.getInstance(KuryeActivity.this).addToRequestQueue(stringRequest);


        if(isTrue==true){

            Toast.makeText(getApplicationContext(), "Bu kuryenin konumu oluşturulmuştur", Toast.LENGTH_LONG).show();

        }
        if (isTrue==false){

            // final double latti=mMap.getMyLocation().getLatitude();
            //final double lontitude=mMap.getMyLocation().getLongitude();
            final String kurye=SharedPrefManager.getInstance(getApplicationContext()).getUsername();

            StringRequest stringRequest2 = new StringRequest(Request.Method.POST,
                    Constants.URL_KONUM,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


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


            RequestHandler.getInstance(KuryeActivity.this).addToRequestQueue(stringRequest2);

        }


    }*/

   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




}
