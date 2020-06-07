package com.aurama.sholatyuk.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.aurama.sholatyuk.R;
import com.aurama.sholatyuk.adapter.ListKotaAdapter;
import com.aurama.sholatyuk.model.shalat.listkota.KotaItem;
import com.aurama.sholatyuk.view.viewmodel.ListKotaViewModel;

import java.util.ArrayList;

public class ListKotaActivity extends AppCompatActivity {
    private ListKotaAdapter listKotaAdapter;
    private RecyclerView rvListKota;
    private ListKotaViewModel listKotaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kota);

        listKotaAdapter = new ListKotaAdapter(getApplicationContext());
        listKotaAdapter.notifyDataSetChanged();

        rvListKota = findViewById(R.id.rv_list_kota);
        rvListKota.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        listKotaViewModel = new ViewModelProvider(this).get(ListKotaViewModel.class);
        listKotaViewModel.setListkota();
        listKotaViewModel.getListKota().observe(this, getListKota);
    }
    private Observer<ArrayList<KotaItem>> getListKota = new Observer<ArrayList<KotaItem>>() {
        @Override
        public void onChanged(ArrayList<KotaItem> kotaItems) {
            if (kotaItems != null) {
                listKotaAdapter.setData(kotaItems);
                rvListKota.setAdapter(listKotaAdapter);
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_searchview, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listKotaAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
