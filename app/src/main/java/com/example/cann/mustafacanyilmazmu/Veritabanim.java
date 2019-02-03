package com.example.cann.mustafacanyilmazmu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Veritabanim extends SQLiteOpenHelper {

    private static final int SURUM = 1;
    private static final String VERITABANI= "mustafaCanYilmaz";
    SQLiteDatabase sqlDatabase;
    Veritabanim vt;

    public Veritabanim(Context con)
    {
        super(con,VERITABANI,null,SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//sinifin onCreate metodunda veritabanimizin tablosunu olusturuyoruz. ve veritabanimizdaki kolonlari da olusturuyoruz.
        db.execSQL("CREATE TABLE kullanicilar(id INTEGER PRIMARY KEY AUTOINCREMENT, kullaniciAdi text,kullaniciSifre text);");
        db.execSQL("CREATE TABLE kitaplar (id INTEGER PRIMARY KEY AUTOINCREMENT,kitapAdi TEXT, kitapDetayi TEXT, kitapYazari TEXT);");

    }

    public Cursor selectQuery(String query) {
        Cursor c1 = null;
        try {

            if (sqlDatabase.isOpen()) {
                sqlDatabase.close();

            }
            sqlDatabase = vt.getWritableDatabase();
            c1 = sqlDatabase.rawQuery(query, null);

        } catch (Exception e) {

            System.out.println("Veritabani Hatası " + e);

        }
        return c1;

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF exists kullanicilar");
        db.execSQL("DROP TABLE IF exists kitaplar");
        onCreate(db);
    }

    public boolean kullaniciEkle(String kullaniciAdi, String kullaniciSifre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("kullaniciAdi",kullaniciAdi);
        contentValues.put("kullaniciSifre",kullaniciSifre);
        long ins = db.insert("kullanicilar",null,contentValues);
        if(ins==-1)return false;
        else return true;
    }



    public Boolean kullaniciKontrol(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from kullanicilar where kullaniciAdi =?",new String[]{email});
        if(cursor.getCount()>0)return false;
        else return true;
    }



    // Burada kadi şifre doğrulayacağıoz
    public boolean KullaniciAdiSifreKontrol(String kullaniciAdi, String kullaniciSifre){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from kullanicilar where kullaniciAdi=? and kullaniciSifre=?",new String[]{kullaniciAdi, kullaniciSifre});
        if(cursor.getCount()>0)return true;
        else return false;
    }

    /*
    public Cursor allData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from konular",null);
        return cursor;
    }

     public boolean konuEkle(String konuAdim, String konuDetayim){
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("konuAdi",konuAdim);
        contentValues1.put("konuDetay",konuDetayim);
        long ins1 = db1.insert("konular",null,contentValues1);
        if(ins1==-1)return false;
        else return true;
    }

    public Cursor allDataKonular(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("select konuAdi from konular ",null);
        return cursor1;
    }
    */
}
