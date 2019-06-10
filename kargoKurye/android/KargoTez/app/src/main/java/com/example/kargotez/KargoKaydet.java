package com.example.kargotez;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class KargoKaydet extends AppCompatActivity {

    EditText edt_gonderen,edt_gonderen_tel,edt_alici,edt_alici_tel;

    Spinner spinner_alici_adres;

    Button button_kaydet;

    String gonderen;

    String il;

    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kargo_kaydet);
        init();

        gonderen=getIntent().getExtras().getString("gonderen");
        id=getIntent().getExtras().getString("id");

        edt_gonderen.setText(gonderen);


        spinner_alici_adres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                il=spinner_alici_adres.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kargoKaydet();
                istekSil();
            }
        });






    }

    private void istekSil(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_KURYE_ISTEK_SIL,
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
                params.put("id", String.valueOf(Integer.parseInt(id)));

                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);





    }



    private void kargoKaydet(){


        final String gonderen_tel = edt_gonderen_tel.getText().toString().trim();
        final String alici_isim = edt_alici.getText().toString().trim();
        final String alici_tel = edt_alici_tel.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_KURYE_KARGO_KAYDET,
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
                params.put("gonderen",gonderen);
                params.put("gonderen_tel",gonderen_tel);
                params.put("alici",alici_isim);
                params.put("alici_tel",alici_tel);
                params.put("alici_adres",il);
                params.put("kurye",SharedPrefManager.getInstance(getApplicationContext()).getUsername());
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);






    }


    private void init(){
        edt_gonderen=findViewById(R.id.edt_gonderen);
        edt_gonderen_tel=findViewById(R.id.edt_gonderen_tel);
        edt_alici=findViewById(R.id.edt_alici);
        edt_alici_tel=findViewById(R.id.edt_alici_tel);
        spinner_alici_adres=findViewById(R.id.spinner_alici_adres);
        button_kaydet=findViewById(R.id.button_kaydet);

        ArrayAdapter adapter=ArrayAdapter.createFromResource(
                KargoKaydet.this,
                R.array.SEHİRLER,
                R.layout.collor_spinner_layout
        );

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinner_alici_adres.setAdapter(adapter);




    }
}
