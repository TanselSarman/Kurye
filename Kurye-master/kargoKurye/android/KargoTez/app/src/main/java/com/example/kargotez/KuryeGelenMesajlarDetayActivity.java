package com.example.kargotez;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
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

public class KuryeGelenMesajlarDetayActivity extends AppCompatActivity {




    TextView txt_kurye_mesaj,txt_musteri_mesaj;

     String kurye;
     String musteri;


    String musteriMesajDurumu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurye_gelen_mesajlar_detay);
        kurye=SharedPrefManager.getInstance(getApplicationContext()).getUsername().trim();
        musteri=getIntent().getExtras().getString("gonderen").trim();

        txt_kurye_mesaj=findViewById(R.id.txt_kurye_mesaj);
        txt_musteri_mesaj=findViewById(R.id.txt_musteri_mesaj);

        kuryeMesajGetir();




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
                            musteriMesajDurumu=jsonObject.getString("deger");
                            txt_musteri_mesaj.setText(musteri+" : "+mesaj);

                            if (musteriMesajDurumu.equals("gorulmedi")){
                                durumGuncelle();

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
                params.put("gonderen",strin);
                params.put("alici",SharedPrefManager.getInstance(getApplicationContext()).getUsername());
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
                params.put("gonderen",musteri);
                params.put("alici",kurye);
                params.put("deger","goruldu");
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);





    }


    private void kuryeMesajGetir(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_KURYE_MESAJ_GETIR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String mesaj=jsonObject.getString("mesaj");

                            txt_kurye_mesaj.setText(mesaj);
                            musteriMesajGetir(musteri);

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
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


}
