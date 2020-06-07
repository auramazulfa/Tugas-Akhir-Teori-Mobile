package com.aurama.sholatyuk.view.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aurama.sholatyuk.model.shalat.listkota.KotaItem;
import com.aurama.sholatyuk.model.shalat.listkota.KotaResponse;
import com.aurama.sholatyuk.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ListKotaViewModel extends ViewModel {
    private ApiMain apiMain;
    private MutableLiveData<ArrayList<KotaItem>> listkota = new MutableLiveData<>();

    public void setListkota() {
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApiSholat().getListCity().enqueue(new Callback<KotaResponse>() {
            @Override
            public void onResponse(Call<KotaResponse> call, Response<KotaResponse> response) {
                KotaResponse kotaResponse = response.body();
                if (kotaResponse != null && kotaResponse.getKota() != null) {
                    ArrayList<KotaItem> kotaItems = kotaResponse.getKota();
                    listkota.postValue(kotaItems);
                }
            }

            @Override
            public void onFailure(Call<KotaResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });
    }

    public LiveData<ArrayList<KotaItem>> getListKota() {
        return listkota;
    }
}

