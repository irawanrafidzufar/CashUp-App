package com.example.cashupapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cashupapp.models.adapter;
import com.example.cashupapp.models.database;
import com.example.cashupapp.models.modeldatakas;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    public ListView list;
    public database db;
    public Button search;
    public TextView total;
    public Button btnbayar;
    public EditText inpfilter;
    public ArrayList<modeldatakas> kas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        this.list = findViewById(R.id.listview);
        this.db = new database(getApplicationContext());
        this.btnbayar = findViewById(R.id.btnbayar);
        this.kas = new ArrayList<modeldatakas>();
        this.search = findViewById(R.id.search);
        this.inpfilter = findViewById(R.id.inpfilter);
        this.total = findViewById(R.id.total);
    }

    @Override
    protected void onStart() {
        super.onStart();

        LoadUp();
        total1();



        btnbayar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), pembayaran.class);
            startActivity(intent);
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kas.clear();

                String inputFilter = inpfilter.getText().toString();
                Cursor data = db.TampilkanData("SELECT * FROM kas WHERE bulan LIKE '%" + inputFilter + "%'");
                while (data.moveToNext()){
                    String nama = data.getString(0);
                    String bulan = data.getString(1);
                    int bayar = Integer.parseInt(data.getString(2));
                    kas.add(new modeldatakas(nama, bulan, bayar));
                }

                adapter adapter = new adapter(kas, getApplicationContext());

                list.setAdapter(adapter);
            }
        });
    }

    public void LoadUp(){
        kas.clear();

        Cursor data = db.TampilkanData("SELECT * FROM kas");
        while (data.moveToNext()){
            String nama = data.getString(0);
            String bulan = data.getString(1);
            int bayar = Integer.parseInt(data.getString(2));
            kas.add(new modeldatakas(nama, bulan, bayar));
        }

        adapter adapter = new adapter(kas, getApplicationContext());

        list.setAdapter(adapter);
    }

    public void total1(){
        String sql = "SELECT SUM(bayar) FROM kas";
        Cursor data = db.TampilkanData(sql);
        data.moveToFirst();
        String total2 = Integer.toString(data.getInt(0));
        total.setText("Rp. "+total2);
    }
}