package com.example.cann.mustafacanyilmazmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class KitapDetay extends AppCompatActivity {
    TextView txt1,txt2,txt3;
    String gelenVeriAdi,gelenVeriDetay,gelenVeriYazar;
    float gelenRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_detay);

        Bundle extras = getIntent().getExtras();
        String value1 = extras.getString("veri1");
        String value2 = extras.getString("veri2");
        String value3 = extras.getString("veri3");

        txt1 = (TextView)findViewById(R.id.cekilecekKonuAdi);
        txt2 = (TextView)findViewById(R.id.cekilecekKonuDetayi);
        txt3 = (TextView)findViewById(R.id.cekilecekKonuYazari);

    //    Toast.makeText(getApplicationContext(),value1,Toast.LENGTH_LONG).show();

        txt1.setText("Kitap Adı :  "  +  " " + value1);
        txt2.setText("Kitap Detayi : "  +  " " + value2);
        txt3.setText("Kitap Yazari : "  +  " " + value3);

        RatingBar ratingBar =findViewById(R.id.ratingBar);

         ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
             @Override
             public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                     gelenRating = rating;
             }
         });
        Button button = findViewById(R.id.ratingBarButon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Puanınız : " + gelenRating,Toast.LENGTH_LONG).show();

            }
        });

    }
}
