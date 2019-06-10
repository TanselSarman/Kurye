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

public class KuryeGelenIsteklerDetay extends AppCompatActivity {


    TextView edt_musteri,edt_musteri_adresi;


    String enlem,boylam,kullanici;

    ImageView button_gelen_kargolar,button_kargo_kaydet;

    String id;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurye_gelen_istekler_detay);
        edt_musteri_adresi=findViewById(R.id.edt_musteri_adresi);
        edt_musteri=findViewById(R.id.edt_musteri);
        button_gelen_kargolar=findViewById(R.id.button_gelen_kargolar);
        button_kargo_kaydet=findViewById(R.id.button_kargo_kaydet);
        detayGetir();




        button_gelen_kargolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),KuryeIstekMaps.class);
                intent.putExtra("enlem",enlem );
                intent.putExtra("boylam",boylam );
                intent.putExtra("kullanici",kullanici );
                startActivity(intent);
            }
        });


        button_kargo_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),KargoKaydet.class);
                intent.putExtra("gonderen",kullanici);
                intent.putExtra("id",id);



                startActivity(intent);

            }
        });






    }



    private void detayGetir(){

        final int position;
        position=getIntent().getExtras().getInt("sehir");


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_KURYE_GELEN_ISTEKLER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(position);

                            id=jsonObject.getString("Id");
                            kullanici=jsonObject.getString("kullanici");
                            String adres=jsonObject.getString("sehir");
                            enlem=jsonObject.getString("enlem");
                            boylam=jsonObject.getString("boylam");


                            edt_musteri.setText(kullanici);
                            edt_musteri_adresi.setText(adres);


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
                params.put("sehir",SharedPrefManager.getInstance(getApplicationContext()).getUserSehır());
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);



    }
}
