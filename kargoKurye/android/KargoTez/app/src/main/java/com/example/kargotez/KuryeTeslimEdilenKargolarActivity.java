package com.example.kargotez;

import android.app.AppComponentFactory;
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

public class KuryeTeslimEdilenKargolarActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;

    ListView kargo_teslim_edilenler;

    String sehir[];
    String alici[];
    String toplam[];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurye_teslim_edilen_kargolar);
        init();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        tesliEdilenKargolar();


    }


    private void init(){
        kargo_teslim_edilenler=findViewById(R.id.kargo_teslim_edilenler);

    }

    private void tesliEdilenKargolar(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_KARGO_AL_TESLIM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {


                            JSONArray jsonArray=new JSONArray(response);
                            int arraySize=jsonArray.length();
                            sehir=new String[arraySize];
                            alici=new String[arraySize];
                            toplam=new String[arraySize];

                            for(int i=0;i<arraySize;i++){



                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String iller=jsonObject.getString("alici_adres");
                                String alicilar=jsonObject.getString("alici");
                                sehir[i]=iller;
                                alici[i]=alicilar;
                                toplam[i]=sehir[i]+"-"+alici[i];


                            }

                            ArrayAdapter<String> adapter  = new ArrayAdapter<String>(getApplicationContext(),R.layout.listview,toplam);
                            kargo_teslim_edilenler.setAdapter(adapter);






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

        kargo_teslim_edilenler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getApplicationContext(),KargoTeslimDetayActivity.class);
                intent.putExtra("sehir",i);
                startActivity(intent);




            }
        });
    }
}
