package com.aurama.sholatyuk.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aurama.sholatyuk.R;
import com.aurama.sholatyuk.model.shalat.listkota.KotaItem;
import com.aurama.sholatyuk.view.activity.JadwalSholatActivity;

import java.util.ArrayList;

public class ListKotaAdapter extends RecyclerView.Adapter<ListKotaAdapter.ViewHolder> implements Filterable {
    private ArrayList<KotaItem> kotaItems = new ArrayList<>();
    private ArrayList<KotaItem> kotaItemsSearch = new ArrayList<>();
    private Context context;

    public ListKotaAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<KotaItem> items) {
        kotaItems.clear();
        kotaItemsSearch.clear();
        kotaItems.addAll(items);
        kotaItemsSearch.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListKotaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_kota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListKotaAdapter.ViewHolder holder, final int position) {
        holder.namaKota.setText(kotaItems.get(position).getNama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Kamu Memilih : " +
                        kotaItems.get(holder.getAdapterPosition()).getNama(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, JadwalSholatActivity.class);
                intent.putExtra("city_id", kotaItems.get(position).getId());
                intent.putExtra("city_name", kotaItems.get(position).getNama());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return kotaItems.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchString = charSequence.toString();
                if (searchString.isEmpty()) {
                    kotaItems = kotaItemsSearch;
                } else {
                    ArrayList<KotaItem> tempFilteredList = new ArrayList<>();
                    for (KotaItem kotaItem : kotaItemsSearch) {
                        if (kotaItem.getNama().toLowerCase().contains(searchString) || kotaItem.getNama().toUpperCase().contains(searchString)) {
                            tempFilteredList.add(kotaItem);
                        }
                    }
                    kotaItems = tempFilteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = kotaItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                kotaItems = (ArrayList<KotaItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaKota;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaKota = itemView.findViewById(R.id.tv_nama_kota);
        }
    }
}

