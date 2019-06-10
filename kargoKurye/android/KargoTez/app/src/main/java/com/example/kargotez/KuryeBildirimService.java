package com.example.kargotez;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.widget.ArrayAdapter;
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
import java.util.Timer;

public class KuryeBildirimService extends Service {

    Notification myNotification;
    NotificationManager nManager;
    Context context=this;


    String deger[];


    String mesajYollayanKurye;
    //String deger;
    Timer timer=new Timer();
    boolean isTrue=true;


    public KuryeBildirimService() {
    }

    public void onCreate() {


        new Thread(new Runnable() {  // Yeni bir Thread (iş parcacığı) oluşturuyorum.
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() { // Thread'ım başladığında bitmemesi için while
                // ile sonsuz döngüye soktum. senaryo gereği
                while (1 == 1){
                    try {
                        Thread.sleep(1500000); // Her döngümde Thread'ımı 15000 ms uyutuyorum.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // ve 15 saniyem dolduktan sonra bildirimimi basıyorum.

                    musteriMesajAtmismi();


                    if(isTrue==false){
                        int i=1;
                        Intent intent=new Intent(context,KuryeGelenMesajlarActivity.class);
                        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                        myNotification=new  Notification.Builder(context)
                                .setContentTitle("SİSTEM")
                                .setContentText("Kuryeden Mesajınız Var")
                                .setContentIntent(pendingIntent)
                                .setDefaults(Notification.DEFAULT_SOUND)
                                .setAutoCancel(true)
                                .setSmallIcon(R.mipmap.ic_launcher).build();
                        nManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        nManager.notify(i,myNotification);
                        i++;
                    }



                }
            }
        }).start();  // burada Thread'ımı başlatıyorum.
    }



    public boolean musteriMesajAtmismi(){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GELEN_MESAJLAR_LISTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            JSONArray jsonArray=new JSONArray(response);
                            int arraySize=jsonArray.length();

                            deger=new String[arraySize];


                            for(int i=0;i<arraySize;i++){

                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                String degerler=jsonObject.getString("deger");

                                deger[i]=degerler;

                                if(deger[i].equals("gorulmedi")){

                                    isTrue=false;

                                }

                            }

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
                params.put("alici",SharedPrefManager.getInstance(getApplicationContext()).getUsername());
                return params;
            }


        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        return isTrue;

    }





    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}