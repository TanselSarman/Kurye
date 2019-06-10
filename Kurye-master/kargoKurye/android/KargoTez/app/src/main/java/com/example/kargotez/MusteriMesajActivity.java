package com.example.kargotez;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

public class MusteriMesajActivity extends AppCompatActivity {

    TextView txt_kurye_musteri_mesaj,txt_musteri_kurye_mesaj;
    Button btn_evet,btn_hayir;



    String kuryeMesajDurumu;

    String kurye;
    String musteri;




    String olumlu="Müsaitim bekliyorum...";
    String olumsuz="Evde değilim şubeye bırakın...";
     int sayi =0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj_musteri);

        kurye=getIntent().getExtras().getString("gonderen").trim();
        musteri=SharedPrefManager.getInstance(getApplicationContext()).getUsername().trim();





        init();
        kuryeMesajGetir();








        btn_evet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(txt_musteri_kurye_mesaj.getText() == "null"){
                    mesajAt(olumlu);
                    musteriMesajGetir(kurye);
                }
                else{
                    Toast.makeText(MusteriMesajActivity.this,"Sadece Bir Kez Mesaj Atabilirsiniz",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_hayir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txt_musteri_kurye_mesaj.getText() == "null"){
                    mesajAt(olumsuz);
                    musteriMesajGetir(kurye);
                }
                else{
                    Toast.makeText(MusteriMesajActivity.this,"Sadece Bir Kez Mesaj Atabilirsiniz",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void mesajAt(final String str){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_MUSTERİ_KURYE_MESAJ_AT,
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
                params.put("gonderen",musteri);
                params.put("alici",kurye);
                params.put("mesaj",str);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void durumGuncelle(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_MESAJ_DURUM_GUNCELLE,
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
                params.put("gonderen",kurye);
                params.put("alici",musteri);
                params.put("deger","goruldu");
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);





    }



    private void kuryeMesajGetir() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_MUSTERİ_KURYE_MESAJ_GETIR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String mesaj=jsonObject.getString("mesaj");
                            kurye=jsonObject.getString("gonderen");
                            kuryeMesajDurumu=jsonObject.getString("deger");
                            musteriMesajGetir(kurye);

                            if (kuryeMesajDurumu.equals("gorulmedi")){
                                durumGuncelle();

                            }

                            txt_kurye_musteri_mesaj.setText(kurye+" : "+mesaj);
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
                params.put("alici",SharedPrefManager.getInstance(getApplicationContext()).getUsername());
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void musteriMesajGetir(final String strin) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_MUSTERİ_MUSTERİ_MESAJ_GETIR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String mesaj=jsonObject.getString("mesaj");

                            txt_musteri_kurye_mesaj.setText(mesaj);
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
                params.put("gonderen",SharedPrefManager.getInstance(getApplicationContext()).getUsername());
                params.put("alici",strin);


                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    private void init(){
        txt_kurye_musteri_mesaj=findViewById(R.id.txt_kurye_musteri_mesaj);
        txt_musteri_kurye_mesaj=findViewById(R.id.txt_musteri_kurye_mesaj);
        btn_evet=findViewById(R.id.btn_evet);
        btn_hayir=findViewById(R.id.btn_hayir);

    }
}
