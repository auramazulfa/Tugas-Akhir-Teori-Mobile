package com.aurama.sholatyuk.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aurama.sholatyuk.R;

public class MainActivity extends AppCompatActivity {
    Button btnJadwalSholat, btnRangkumanKhutbah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJadwalSholat = findViewById(R.id.btn_jadwal_sholat);
        btnRangkumanKhutbah = findViewById(R.id.btn_rangkuman_khutbah);

        btnJadwalSholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JadwalSholatActivity.class);
                startActivity(intent);
            }
        });

        btnRangkumanKhutbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KhutbahActivity.class);
                startActivity(intent);
            }
        });
    }
}
