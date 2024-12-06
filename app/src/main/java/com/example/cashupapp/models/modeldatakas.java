package com.example.cashupapp.models;

public class modeldatakas {
    public String nama;
    public String bulan;
    public int bayar;

    public modeldatakas(String nama, String bulan, int bayar) {
        this.nama = nama;
        this.bulan = bulan;
        this.bayar = bayar;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public int getBayar() {
        return bayar;
    }

    public void setBayar(int bayar) {
        this.bayar = bayar;
    }

}
