package com.example.cann.mustafacanyilmazmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GirisYap extends AppCompatActivity {

    EditText editText1,editText2;
    Button button1,button2,button3;
    Veritabanim db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);

        db = new Veritabanim(this);
        editText1 = (EditText)findViewById(R.id.girisEmail);
        editText2 = (EditText)findViewById(R.id.girisSifre);
        button1 = (Button)findViewById(R.id.girisButon);
        button2 = (Button)findViewById(R.id.kayitOlYonlen);
        button3 = (Button)findViewById(R.id.kayitOlmadanDevam);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kullaniciAdiGiris =editText1.getText().toString();
                String kullaniciSifreGiris =editText2.getText().toString();
                Boolean KullaniciAdiSifreDenetle = db.KullaniciAdiSifreKontrol(kullaniciAdiGiris,kullaniciSifreGiris);
                if(KullaniciAdiSifreDenetle==true){
                    Toast.makeText(getApplicationContext(),"Giriş Başarılı",Toast.LENGTH_LONG).show();
                    Intent i1 = new Intent(GirisYap.this,KitapEkle.class);
                   // i1.putExtra("kullaniciAdiGonderme",kullaniciAdiGiris);
                    startActivity(i1);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Kullanıcı Adı veya şifreyi kontrol et",Toast.LENGTH_LONG).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GirisYap.this,KayitOl.class);
                startActivity(i);
                finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GirisYap.this,MainActivity
                        .class);
                startActivity(i);
            }
        });
    }
}


