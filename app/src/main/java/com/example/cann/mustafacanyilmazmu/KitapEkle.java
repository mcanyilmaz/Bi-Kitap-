package com.example.cann.mustafacanyilmazmu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KitapEkle extends AppCompatActivity {

    private Veritabanim vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_ekle);
        vt = new Veritabanim(this);

        final EditText KitapAdi=(EditText) findViewById(R.id.eklenecekKitapAdi);
        final EditText KitapDetayi=(EditText) findViewById(R.id.eklenecekKitapDetay);
        final EditText KitapYazari=(EditText) findViewById(R.id.eklenecekKitapYazar);

        Button ekle =(Button) findViewById(R.id.Eklebutton1); //button nesneleri
        Button mevcutKitapGor = (Button)findViewById(R.id.mevcutKitaplariGor);

        ekle.setOnClickListener(new View.OnClickListener() {//ekle butonunun onclick metodu

            @Override
            public void onClick(View v) {
                try{

                    if(KitapAdi.getText().toString() != "" && KitapYazari.getText().toString() != "" && KitapDetayi.getText().toString() != ""){//girilmesi zorunlu alanlarin kontrolü

                        KayitEkle(KitapAdi.getText().toString(),KitapDetayi.getText().toString(), KitapYazari.getText().toString());
                        Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Eklendi", Toast.LENGTH_SHORT).show();//uyari mesaji

                        Intent i = new Intent(KitapEkle.this,MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Eksik Yerleri Doldurunuz", Toast.LENGTH_SHORT).show();//uyari mesaji
                    }
                    KitapAdi.setText("");
                    KitapDetayi.setText("");
                    KitapYazari.setText("");

                }
                finally{
                }

            }
        });

        mevcutKitapGor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KitapEkle.this,MainActivity.class);
                startActivity(i);
            }
        });



    }
    private void KayitEkle(String kitapAdi, String kitapDetayim, String kitapYazirimiz) {

        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues veri = new ContentValues();
        veri.put("kitapAdi", kitapAdi);
        veri.put("kitapDetayi", kitapDetayim);
        veri.put("kitapYazari", kitapYazirimiz);
        db.insert("kitaplar",null,veri);
    }
}

