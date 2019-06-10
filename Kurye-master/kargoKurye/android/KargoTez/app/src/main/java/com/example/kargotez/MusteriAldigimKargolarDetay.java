package com.example.kargotez;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

public class MusteriAldigimKargolarDetay extends AppCompatActivity {

    TextView edt_tarih,edt_alici_adres,edt_alici_tel,edt_alici,edt_gonderen_tel,edt_gonderen,edt_durum,edt_kurye;


    String kurye;
    String enlem ;
    String boylam;

    String kuryeAdi;



    String durum;

    TextView kurye_durumu;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteri_aldigim_kargolar_detay);
        init();
        detayGetir();









    }


    private void detayGetir(){

        final int position;
        position=getIntent().getExtras().getInt("isim");

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_MUSTERI_ALDIGIM_KARGOLAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(position);


                            String gonderen=jsonObject.getString("gonderen");
                            String gonderen_tel=jsonObject.getString("gonderen_tel");
                            String alici=jsonObject.getString("alici");
                            String alici_tel=jsonObject.getString("alici_tel");
                            String alici_adres=jsonObject.getString("alici_adres");
                            String verilis_tarihi=jsonObject.getString("verilis_tarihi");
                            String durum=jsonObject.getString("durum");
                            kuryeAdi=jsonObject.getString("kurye");

                            edt_gonderen.setText(gonderen);
                            edt_gonderen_tel.setText(gonderen_tel);
                            edt_alici.setText(alici);
                            edt_alici_tel.setText(alici_tel);
                            edt_alici_adres.setText(alici_adres);
                            edt_tarih.setText(verilis_tarihi);
                            edt_durum.setText(durum);
                            edt_kurye.setText(kuryeAdi);


                            ///////////////////////////////////////////////////////////////



                            ///////////////////////////////////////////////////////////////////









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

    }

    private void init(){
        edt_tarih=findViewById(R.id.edt_tarih);
        edt_alici_adres=findViewById(R.id.edt_alici_adres);
        edt_alici_tel=findViewById(R.id.edt_alici_tel);
        edt_alici=findViewById(R.id.edt_alici);
        edt_gonderen_tel=findViewById(R.id.edt_gonderen_tel);
        edt_gonderen=findViewById(R.id.edt_gonderen);
        edt_durum=findViewById(R.id.edt_durum);
        edt_kurye=findViewById(R.id.edt_kurye);



    }
}
