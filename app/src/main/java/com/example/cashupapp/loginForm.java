package com.example.cashupapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cashupapp.models.database;

import java.util.Objects;

public class loginForm extends AppCompatActivity {

    public EditText email;
    public EditText password;
    public ImageButton login;
    public database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);


        this.email = findViewById(R.id.email);
        this.password = findViewById(R.id.password);
        this.login = findViewById(R.id.login);
        this.db = new database(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "SELECT * FROM pengguna WHERE username = '" + email.getText().toString() + "' AND pass = '" + password.getText().toString() + "'";
                try{
                Cursor data = db.TampilkanData(sql);
                if(data.getCount()>0){
                    Toast.makeText(loginForm.this, "Selamat Datang!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginForm.this, dashboard.class);
                    startActivity(intent);
                } else if(data.getCount()==0){
                    Toast.makeText(loginForm.this, "Password atau Username Salah!", Toast.LENGTH_LONG).show();
                    email.setText("");
                    password.setText("");
                }

                } catch (Exception e){
                    Toast.makeText(loginForm.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}