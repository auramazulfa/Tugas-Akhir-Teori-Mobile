package com.aurama.sholatyuk.model.khutbah;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_khutbah")
public class KhutbahModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "waktu")
    private String waktu;

    @ColumnInfo(name = "khatib")
    private String khatib;

    @ColumnInfo(name = "khutbah")
    private String khutbah;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getKhatib() {
        return khatib;
    }

    public void setKhatib(String khatib) {
        this.khatib = khatib;
    }

    public String getKhutbah() {
        return khutbah;
    }

    public void setKhutbah(String khutbah) {
        this.khutbah = khutbah;
    }
}
