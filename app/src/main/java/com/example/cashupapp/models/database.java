package com.example.cashupapp.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "KasKelas";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_USERS = "pengguna";
    private static final String TABLE_KAS = "kas";

    private static final String COLUMN_USER_NAME = "username";
    private static final String COLUMN_USER_PASS = "pass";

    private static final String COLUMN_KAS_NAMA = "nama";
    private static final String COLUMN_KAS_BULAN = "bulan";
    private static final String COLUMN_KAS_BAYAR = "bayar";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (" + COLUMN_USER_NAME + " TEXT," + COLUMN_USER_PASS + " TEXT)";
    private static final String CREATE_TABLE_KAS = "CREATE TABLE " + TABLE_KAS + " (" + COLUMN_KAS_NAMA + " TEXT, " + COLUMN_KAS_BULAN + " TEXT," + COLUMN_KAS_BAYAR + " INTEGER)";

    public database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_KAS);

        String SQL = "INSERT INTO " + TABLE_USERS + " (" + COLUMN_USER_NAME + ", " + COLUMN_USER_PASS + ") VALUES ('admin', 'admin')";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    public void tambahData(String SQL){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL);
        db.close();
    }

    public Cursor TampilkanData(String SQL){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery(SQL, null);
        return data;
    }

    public void hapusData(String SQL){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL);
        db.close();
    }
}
