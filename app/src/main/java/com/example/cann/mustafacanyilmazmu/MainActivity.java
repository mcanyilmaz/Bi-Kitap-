package com.example.cann.mustafacanyilmazmu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Veritabanim veritabanim;
    public static int idSnf;
    String KitapAdi;
    String KitapDetayim;
    String KitapYazarim;
    private String[] SELECT = { "id", "kitapAdi", "kitapDetayi","kitapYazari"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        veritabanim = new Veritabanim(this);
        SQLiteDatabase db = veritabanim.getReadableDatabase();

        Cursor cursor = db.query("kitaplar", SELECT, null,null, null, null, null);//select islemi
        final ListView listemiz=(ListView) findViewById(R.id.listview1);
        startManagingCursor(cursor);

        final ArrayList<String> txt = new ArrayList<String>();
        final ArrayList<String> ad = new ArrayList<String>();
        final ArrayList<String> detay = new ArrayList<String>();
        final ArrayList<String> yazar = new ArrayList<String>();
        final ArrayList<Integer> idtxt = new ArrayList<Integer>();


        int i = 1;
        while(cursor.moveToNext()){
            KitapAdi = cursor.getString((cursor.getColumnIndex("kitapAdi")));
           // long id = cursor.getLong(cursor.getColumnIndex("id"));
            KitapDetayim = cursor.getString((cursor.getColumnIndex("kitapDetayi")));
            KitapYazarim = cursor.getString((cursor.getColumnIndex("kitapYazari")));
           // int h_No = cursor.getInt((cursor.getColumnIndex("hesap_no")));


            //idtxt.add((int)id);
            ad.add(KitapAdi);
            detay.add(KitapDetayim);
            yazar.add(KitapYazarim);
            txt.add(KitapAdi);

            // txt.add(k_adi+ " , "+ sifrem+ " , "+k_yeri);
            i++;

        }


        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, txt);

        listemiz.setAdapter(veriAdaptoru);

        listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {//listview in click metodu
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int arg2, long arg3) {

//                idSnf = idtxt.get(arg2 );//seçilenin ID degerini integer degiskenine atariz.

               // Toast.makeText(getApplicationContext(), txt.get(arg2) , Toast.LENGTH_LONG).show();
               Intent i = new Intent(MainActivity.this,KitapDetay.class);
                i.putExtra("veri1",ad.get(arg2));
                i.putExtra("veri2",detay.get(arg2));
                i.putExtra("veri3",yazar.get(arg2));
              //  i.putExtra("veri2",sifrem);
               // i.putExtra("veri3",k_yeri);
                startActivity(i);

            }
        });

    }
}
