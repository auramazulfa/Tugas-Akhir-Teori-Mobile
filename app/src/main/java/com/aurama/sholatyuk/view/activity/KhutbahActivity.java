package com.aurama.sholatyuk.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aurama.sholatyuk.R;
import com.aurama.sholatyuk.model.khutbah.AppDatabase;
import com.aurama.sholatyuk.model.khutbah.KhutbahModel;

public class KhutbahActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    private EditText etWaktu, etKhatib, etKhutbah;
    private Button btnSimpan, btnLihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khutbah);

        etWaktu = findViewById(R.id.et_waktu);
        etKhatib = findViewById(R.id.et_khatib);
        etKhutbah = findViewById(R.id.et_khutbah);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnLihat = findViewById(R.id.btn_catatan);

        appDatabase = AppDatabase.initDatabase(getApplicationContext());

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String inputWaktu = etWaktu.getText().toString().trim();
                    String inputKhutbah = etKhutbah.getText().toString().trim();

                    boolean isEmptyFields = false;

                    if (TextUtils.isEmpty(inputWaktu)) {
                        isEmptyFields = true;
                        etWaktu.setError("Field can't be empty");
                    }

                    if (TextUtils.isEmpty(inputKhutbah)) {
                        isEmptyFields = true;
                        etKhutbah.setError("Field can't be empty");
                    }

                    if (!isEmptyFields) {
                        KhutbahModel khutbahModel = new KhutbahModel();
                        khutbahModel.setWaktu(etWaktu.getText().toString());
                        khutbahModel.setKhatib(etKhatib.getText().toString());
                        khutbahModel.setKhutbah(etKhutbah.getText().toString());

                        appDatabase.khutbahDAO().insertKhutbah(khutbahModel);

                        etWaktu.setText("");
                        etKhatib.setText("");
                        etKhutbah.setText("");

                        Log.d("FragmentKhutbah ", "Sukses");
                        Toast.makeText(getApplicationContext(), "Catatan Tersimpan", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("FragmentKhutbah ", "Gagal Menyimpan , msg : " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Gagal Menyimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LihatRangkumanKhutbahActivity.class);
                startActivity(intent);
            }
        });
    }
}
