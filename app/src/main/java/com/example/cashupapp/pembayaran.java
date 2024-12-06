package com.example.cashupapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.cashupapp.models.database;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pembayaran extends AppCompatActivity {

    public Button btnbayar;
    public EditText bayar;
    public EditText nama;
    public EditText bulan;
    public database db;
    public Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        this.btnbayar = findViewById(R.id.bayarbtn);
        this.bayar = findViewById(R.id.bayartxt);
        this.nama = findViewById(R.id.nama);
        this.bulan = findViewById(R.id.bulan);
        this.btnback = findViewById(R.id.btnback);
        this.db = new database(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), dashboard.class);
                startActivity(intent);
            }
        });

        btnbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bayarinp = bayar.getText().toString();
                String namainp = nama.getText().toString();
                String bulaninp = bulan.getText().toString();

                String SQL = "INSERT INTO kas VALUES('" + namainp + "','" + bulaninp + "','" + bayarinp + "')";
                db.tambahData(SQL);

                bayar.setText("");
                nama.setText("");
                bulan.setText("");

                Intent intent = new Intent(getApplicationContext(), dashboard.class);
                startActivity(intent);
            }
        });
    }
}