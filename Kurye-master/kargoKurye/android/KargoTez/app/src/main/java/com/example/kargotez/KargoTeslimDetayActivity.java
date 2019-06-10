package com.example.kargotez;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class KargoTeslimDetayActivity extends AppCompatActivity {

    TextView edt_tarih,edt_alici_adres,edt_alici_tel,edt_alici,edt_gonderen_tel,edt_gonderen;
    Button button_kurye_mesaj,button_guncelle;


    private ProgressDialog progressDialog;
    String alici;
    String durum;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kargo_teslim_detay);
        init();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        detayGetir();



    }


    private void detayGetir(){

        final int position;
        position=getIntent().getExtras().getInt("sehir");

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_KARGO_AL_TESLIM,
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

        button_guncelle=findViewById(R.id.button_guncelle);





    }
}
