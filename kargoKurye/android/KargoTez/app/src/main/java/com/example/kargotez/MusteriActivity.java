package com.example.kargotez;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MusteriActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
    ImageView img_hesapla,img_messages,img_kuryem_nerde,gonderdigim_kargolar,aldigim_kargolar,kargo_istek;
    TextView txt_musteri_adi,txt_kurye_adi;

    Context context=this;

    String kurye = new String();
    String enlem = new String();
    String boylam = new String();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteri);
        img_hesapla= findViewById(R.id.img_hesapla);
        img_messages=  findViewById(R.id.img_messages);
        txt_musteri_adi= (TextView) findViewById(R.id.txt_musteri_adi);
        img_kuryem_nerde=findViewById(R.id.img_kuryem_nerde);
        txt_kurye_adi=findViewById(R.id.txt_kurye_adi);
        gonderdigim_kargolar=findViewById(R.id.gonderdigim_kargolar);
        aldigim_kargolar=findViewById(R.id.aldigim_kargolar);
        kargo_istek=findViewById(R.id.kargo_istek);


        /*Intent NotifyService  = new Intent(MusteriActivity.this, MusteriBildirimService.class);
        startService(NotifyService);*/





       txt_musteri_adi.setText(SharedPrefManager.getInstance(getApplicationContext()).getUsername());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");




        img_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,KargoHesaplaActivity.class);
                startActivity(intent);

            }
        });

        img_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,MusteriGelenMesajlarActivity.class);
                startActivity(intent);


            }
        });

        gonderdigim_kargolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MusteriGonderdigimKargolarActivity.class);
                startActivity(intent);
            }
        });

        aldigim_kargolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MusteriAldigimKargolarActivity.class);
                startActivity(intent);
            }
        });



        img_kuryem_nerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MusteriGelenKargolarımListe.class);
                startActivity(intent);
            }
        });

        kargo_istek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,KargoIstek.class);
                startActivity(intent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Ayarlara Girdin", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
