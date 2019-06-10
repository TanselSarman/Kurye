package com.example.kargotez;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusteriGonderdigimKargolarActivity extends AppCompatActivity {

    ListView kargo_gonderdiklerim;

    String alici[];
    String durum[];
    String toplam[];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteri_gonderdigim_kargolar);
        kargo_gonderdiklerim=findViewById(R.id.kargo_gonderdiklerim);


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_MUSTERI_GONDERDIGIM_KARGOLAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            JSONArray jsonArray=new JSONArray(response);
                            int arraySize=jsonArray.length();
                            alici=new String[arraySize];
                            durum=new String[arraySize];
                            toplam=new String[arraySize];

                            for(int i=0;i<arraySize;i++){



                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String alicilar=jsonObject.getString("alici");
                                String durumlar=jsonObject.getString("durum");
                                alici[i]=alicilar;
                                durum[i]=durumlar;
                                toplam[i]=alici[i]+"-"+durum[i];


                            }

                            ArrayAdapter<String> adapter  = new ArrayAdapter<String>(getApplicationContext(),R.layout.listview,toplam);
                            kargo_gonderdiklerim.setAdapter(adapter);






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("isim",SharedPrefManager.getInstance(getApplicationContext()).getUsername());
                return params;
            }


        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        kargo_gonderdiklerim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getApplicationContext(),MusteriGonderdigimKargolarDetay.class);
                intent.putExtra("isim",i);
                startActivity(intent);




            }
        });

    }
}
