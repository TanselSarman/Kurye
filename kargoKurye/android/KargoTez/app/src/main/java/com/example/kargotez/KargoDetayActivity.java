package com.example.kargotez;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class KargoDetayActivity extends AppCompatActivity {

    TextView edt_tarih,edt_alici_adres,edt_alici_tel,edt_alici,edt_gonderen_tel,edt_gonderen;
    ImageView button_kurye_mesaj,button_guncelle;
    Spinner spinner_durumlar;

    private ProgressDialog progressDialog;
    String alici;
    String durum;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kargo_detay);
        init();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        detayGetir();


        spinner_durumlar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                durum=spinner_durumlar.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button_kurye_mesaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KargoDetayActivity.this,KuryeMesajActivity.class);

                intent.putExtra("alici", alici);
                startActivity(intent);
            }
        });

        button_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guncelle();

                Toast.makeText(KargoDetayActivity.this,"Kargo Durumu Başarı ile Güncellendi",Toast.LENGTH_SHORT).show();
            }
        });




    }


    private void guncelle(){


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_KARGO_GUNCELLE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {


                            JSONArray jsonArray=new JSONArray(response);




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
                params.put("gonderen",edt_gonderen.getText().toString());
                params.put("alici",alici);
                params.put("durum",durum);
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);



    }


    private void detayGetir(){

        final int position;
        position=getIntent().getExtras().getInt("sehir");

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_KARGO_LISTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {


                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(position);

                            String gonderen=jsonObject.getString("gonderen");
                            String gonderen_tel=jsonObject.getString("gonderen_tel");
                            alici=jsonObject.getString("alici");
                            String alici_tel=jsonObject.getString("alici_tel");
                            String alici_adres=jsonObject.getString("alici_adres");
                            String verilis_tarihi=jsonObject.getString("verilis_tarihi");

                            edt_gonderen.setText(gonderen);
                            edt_gonderen_tel.setText(gonderen_tel);
                            edt_alici.setText(alici);
                            edt_alici_tel.setText(alici_tel);
                            edt_alici_adres.setText(alici_adres);
                            edt_tarih.setText(verilis_tarihi);


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


    private  void init(){
        edt_tarih= (TextView) findViewById(R.id.edt_tarih);
        edt_alici_adres= (TextView) findViewById(R.id.edt_alici_adres);
        edt_alici_tel= (TextView) findViewById(R.id.edt_alici_tel);
        edt_alici= (TextView) findViewById(R.id.edt_alici);
        edt_gonderen_tel= (TextView) findViewById(R.id.edt_gonderen_tel);
        edt_gonderen= (TextView) findViewById(R.id.edt_gonderen);
        button_kurye_mesaj=findViewById(R.id.button_kurye_mesaj);
        spinner_durumlar=findViewById(R.id.spinner_durumlar);
        button_guncelle=findViewById(R.id.button_guncelle);

        ArrayAdapter adapter=ArrayAdapter.createFromResource(
                KargoDetayActivity.this,
                R.array.DURUM,
                R.layout.collor_spinner_layout
        );

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinner_durumlar.setAdapter(adapter);



    }





}
