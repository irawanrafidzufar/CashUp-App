package com.example.cashupapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cashupapp.models.database;

import com.example.cashupapp.R;

import java.util.ArrayList;

public class adapter extends BaseAdapter {

    public LayoutInflater inflater;
    public database db;
    ArrayList<modeldatakas> kas;

    public adapter(ArrayList<modeldatakas> modeldatakas, Context context){
        this.kas = modeldatakas;
        this.inflater = LayoutInflater.from(context);
        this.db = new database(context);
    }

    @Override
    public int getCount() {
        return kas.size();
    }

    @Override
    public Object getItem(int i) {
        return kas.get(i).nama.length();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 =inflater.inflate(R.layout.customview_datakas, viewGroup, false);
        TextView nama = view1.findViewById(R.id.namatxt);
        TextView bayar = view1.findViewById(R.id.membayartxt);
        TextView bulan = view1.findViewById(R.id.bulantxt);
        Button buang = view1.findViewById(R.id.buangbtn);
        String byr = Integer.toString(kas.get(i).getBayar());
        nama.setText(kas.get(i).getNama().toString());
        bayar.setText(byr);
        bulan.setText(kas.get(i).getBulan().toString());

        buang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = kas.get(i).getNama().toString();
                String bulan = kas.get(i).getBulan().toString();
                String bayar1 = byr;

                String SQL = "DELETE FROM kas WHERE nama = '" + nama + "' AND bulan = '" + bulan + "' AND bayar = " + bayar1 + "";
                db.hapusData(SQL);

                Toast.makeText(view1.getContext(), "Data berhasil dihapus, Silahkan refresh aplikasi", Toast.LENGTH_SHORT).show();
            }
        });

        return view1;
    }
}
