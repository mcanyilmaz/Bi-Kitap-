package com.example.cann.mustafacanyilmazmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KayitOl extends AppCompatActivity {

    Veritabanim db;
    EditText editText1,editText2,editText3;
    Button buton1,buton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        db = new Veritabanim(this);
        editText1 =(EditText)findViewById(R.id.email);
        editText2 =(EditText)findViewById(R.id.sifre);
        editText3 =(EditText)findViewById(R.id.sifreOnay);
        buton1 = (Button)findViewById(R.id.kayitOl);
        buton2 = (Button)findViewById(R.id.direkGiris);

        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                if(s1.equals("")|| s2.equals("")|s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Gerekli Alanları Boş Bırakmayınız",Toast.LENGTH_LONG).show();

                }
                else{
                    if (s2.equals(s3)){
                        Boolean kullaniciKontrol = db.kullaniciKontrol(s1);
                        if(kullaniciKontrol==true){
                            Boolean girilenVeri = db.kullaniciEkle(s1,s2);
                            if(girilenVeri==true){
                                Toast.makeText(getApplicationContext(),"Kayıt Başarılı ",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(KayitOl.this,GirisYap.class);
                                startActivity(i);
                                finish();

                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Bu Kayıt Zaten var",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Şifreyi Kontrol Edin", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KayitOl.this,GirisYap.class);
                startActivity(intent);
            }
        });

    }
}
