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

public class EditKhutbahActivity extends AppCompatActivity {
    private Button btnEdit;
    private EditText etWaktu, etKhatib, etKhutbah;
    private int id;
    private String waktu;
    private String khatib;
    private String khutbah;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_khutbah);

        btnEdit = findViewById(R.id.btn_edit);
        etWaktu = findViewById(R.id.et_waktu_edit);
        etKhatib = findViewById(R.id.et_khatib_edit);
        etKhutbah = findViewById(R.id.et_khutbah_edit);

        id = getIntent().getIntExtra("khutbah_id",0);
        waktu = getIntent().getStringExtra("khutbah_waktu");
        khatib = getIntent().getStringExtra("khutbah_khatib");
        khutbah = getIntent().getStringExtra("khutbah_isi");

        etWaktu.setText(waktu);
        etKhatib.setText(khatib);
        etKhutbah.setText(khutbah);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int idEdit = id;
                    String waktuEdit = etWaktu.getText().toString().trim();
                    String khatibEdit = etKhatib.getText().toString().trim();
                    String khutbahEdit = etKhutbah.getText().toString().trim();

                    boolean isEmptyFields = false;

                    if (TextUtils.isEmpty(waktuEdit)) {
                        isEmptyFields = true;
                        etWaktu.setError("Field can't be empty");
                    }

                    if (TextUtils.isEmpty(khutbahEdit)) {
                        isEmptyFields = true;
                        etKhutbah.setError("Field can't be empty");
                    }

                    if(!isEmptyFields) {
                        KhutbahModel khutbahModel = new KhutbahModel();
                        khutbahModel.setId(idEdit);
                        khutbahModel.setWaktu(waktuEdit);
                        khutbahModel.setKhatib(khatibEdit);
                        khutbahModel.setKhutbah(khutbahEdit);

                        appDatabase = AppDatabase.initDatabase(getApplicationContext());
                        appDatabase.khutbahDAO().updateKhutbah(khutbahModel);
                        Intent intent = new Intent(getApplicationContext(), LihatRangkumanKhutbahActivity.class);
                        startActivity(intent);
                        finish();

                        Log.d("Edit Khutbah Activity ", "Sukses Diedit");
                        Toast.makeText(getApplicationContext(), "Sukses Diedit", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    Log.e("Edit Khutbah Activity ", "Gagal Mengedit , msg : " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Gagal Mengedit", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
