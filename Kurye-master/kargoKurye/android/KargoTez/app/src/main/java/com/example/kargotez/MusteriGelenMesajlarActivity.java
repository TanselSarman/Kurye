package com.example.kargotez;

import android.app.ProgressDialog;
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
import java.util.Map;

public class MusteriGelenMesajlarActivity extends AppCompatActivity {



    ListView musteri_gelen_mesajlar;
    private ProgressDialog progressDialog;

    String isim[];
    String deger[];
    String toplam[];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteri_gelen_mesajlar);
        musteri_gelen_mesajlar=findViewById(R.id.musteri_gelen_mesajlar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GELEN_MESAJLAR_LISTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {


                            JSONArray jsonArray=new JSONArray(response);
                            int arraySize=jsonArray.length();
                            isim=new String[arraySize];
                            deger=new String[arraySize];
                            toplam=new String[arraySize];

                            for(int i=0;i<arraySize;i++){



                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String isimler=jsonObject.getString("gonderen");
                                String degerler=jsonObject.getString("deger");
                                isim[i]=isimler;
                                deger[i]=degerler;
                                toplam[i]=isim[i]+"-"+deger[i];


                            }

                            ArrayAdapter<String> adapter  = new ArrayAdapter<String>(getApplicationContext(),R.layout.listview,toplam);
                            musteri_gelen_mesajlar.setAdapter(adapter);






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
                params.put("alici",SharedPrefManager.getInstance(getApplicationContext()).getUsername());
                return params;
            }


        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


        musteri_gelen_mesajlar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getApplicationContext(),MusteriMesajActivity.class);
                intent.putExtra("gonderen",isim[i]);
                startActivity(intent);




            }
        });




    }
}
