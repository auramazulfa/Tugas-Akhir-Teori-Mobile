package com.aurama.sholatyuk.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.aurama.sholatyuk.R;
import com.aurama.sholatyuk.model.shalat.jadwalsholat.JadwalSholatResponse;
import com.aurama.sholatyuk.service.ApiMain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalSholatActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;
    private Button btnLokasi, btnTanggal;
    private String cityId , name;
    private ApiMain apiMain;
    private TextView tvImsak, tvSubuh, tvDhuha, tvDzuhur, tvAshar, tvMaghrib, tvIsya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_sholat);

        btnLokasi = findViewById(R.id.btn_lokasi);
        btnTanggal = findViewById(R.id.btn_tanggal);
        tvImsak = findViewById(R.id.tv_imsak);
        tvSubuh = findViewById(R.id.tv_shalat_subuh);
        tvDhuha = findViewById(R.id.tv_shalat_dhuha);
        tvDzuhur = findViewById(R.id.tv_shalat_dzuhur);
        tvAshar = findViewById(R.id.tv_shalat_ashar);
        tvMaghrib = findViewById(R.id.tv_shalat_maghrib);
        tvIsya = findViewById(R.id.tv_shalat_isya);

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListKotaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        cityId = getIntent().getStringExtra("city_id");
        name = getIntent().getStringExtra("city_name");

        if (name == null) {
            btnLokasi = findViewById(R.id.btn_lokasi);
        } else {
            btnLokasi.setText(name);
            dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date date = new Date();
            String dateString = dateFormat.format(date);
            if (btnTanggal == null) {
                btnTanggal = findViewById(R.id.btn_tanggal);
            }else {
                btnTanggal.setText(dateString);
                requestData(cityId,dateString);
            }
        }
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                Date date = new Date();
                date.setTime(newDate.getTimeInMillis());
                String dateString = dateFormat.format(date);

                btnTanggal.setText(dateString);
                requestData(cityId, dateString);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void requestData(String cityId, String dateString) {

        if (apiMain == null) {
            apiMain = new ApiMain();
        }
        apiMain.getApiSholat().getJadwalSholat(cityId, dateString).enqueue(new Callback<JadwalSholatResponse>() {
            @Override
            public void onResponse(Call<JadwalSholatResponse> call, Response<JadwalSholatResponse> response) {
                JadwalSholatResponse jadwalSholatResponse = response.body();
                tvImsak.setText(jadwalSholatResponse.getJadwalSholatModel().getDataModel().getImsak());
                tvSubuh.setText(jadwalSholatResponse.getJadwalSholatModel().getDataModel().getSubuh());
                tvDhuha.setText(jadwalSholatResponse.getJadwalSholatModel().getDataModel().getDhuha());
                tvDzuhur.setText(jadwalSholatResponse.getJadwalSholatModel().getDataModel().getDzuhur());
                tvAshar.setText(jadwalSholatResponse.getJadwalSholatModel().getDataModel().getAshar());
                tvMaghrib.setText(jadwalSholatResponse.getJadwalSholatModel().getDataModel().getMaghrib());
                tvIsya.setText(jadwalSholatResponse.getJadwalSholatModel().getDataModel().getIsya());
            }

            @Override
            public void onFailure(Call<JadwalSholatResponse> call, Throwable t) {
                Log.e("onFailure: ", " " + t);
            }
        });
    }
}
