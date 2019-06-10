package com.example.kargotez;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class KuryeMesajActivity extends AppCompatActivity {

    EditText msg;
    Button send;
    TextView txt_kurye_mesaj,txt_musteri_mesaj;

    private String gonderen;
    private String alici;
    Context context=this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj_kurye);
        msg=findViewById(R.id.msg);
        alici=getIntent().getExtras().getString("alici").trim();
        gonderen=SharedPrefManager.getInstance(getApplicationContext()).getUsername().trim();
        txt_kurye_mesaj=findViewById(R.id.txt_kurye_mesaj);
        txt_musteri_mesaj=findViewById(R.id.txt_musteri_mesaj);
        msg.setText("Kargonuzu 1 saat içinde getireceğiz müsait misiniz?");


        send=findViewById(R.id.send);

        kuryeMesajGetir();



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_kurye_mesaj.getText() == "null"){
                    createMesaj();
                }
                else{
                    Toast.makeText(KuryeMesajActivity.this,"Sadece Bir Kez Mesaj Atabilirsiniz",Toast.LENGTH_LONG).show();
                }
            }
        });
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
                            txt_musteri_mesaj.setText(alici+" : "+mesaj);
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
                            musteriMesajGetir(alici);

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
                params.put("gonderen",gonderen);
                params.put("alici",alici);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void createMesaj() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_MESAJ,
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
                params.put("gonderen",gonderen);
                params.put("alici",alici);
                params.put("mesaj",msg.getText().toString());

                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
