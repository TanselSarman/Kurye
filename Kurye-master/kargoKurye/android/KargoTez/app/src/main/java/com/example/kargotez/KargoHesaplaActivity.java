package com.example.kargotez;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class KargoHesaplaActivity extends AppCompatActivity {

    EditText edt_karo_genislik,edt_kargo_boyu,edt_kargo_agirlik;
    Button btn_hesapla;
    TextView txt_cevap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kargo_hesapla);
        init();

        btn_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String agirlik=edt_kargo_agirlik.getText().toString();
                String boyu=edt_kargo_boyu.getText().toString();
                String genislik=edt_karo_genislik.getText().toString();

                int hacim= Integer.parseInt(boyu) * Integer.parseInt(genislik);

                if(hacim< Integer.parseInt(agirlik)){

                    txt_cevap.setText(hacim+" TL");
                }
                else{
                    txt_cevap.setText(agirlik+" TL");

                }
            }
        });
    }

    public void init(){
        edt_karo_genislik= (EditText) findViewById(R.id.edt_karo_genislik);
        edt_kargo_boyu= (EditText) findViewById(R.id.edt_kargo_boyu);
        edt_kargo_agirlik= (EditText) findViewById(R.id.edt_kargo_agirlik);
        btn_hesapla= (Button) findViewById(R.id.btn_hesapla);
        txt_cevap= (TextView) findViewById(R.id.txt_cevap);


    }
}
