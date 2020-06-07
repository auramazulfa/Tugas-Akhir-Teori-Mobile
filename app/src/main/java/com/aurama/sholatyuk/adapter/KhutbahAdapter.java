package com.aurama.sholatyuk.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aurama.sholatyuk.R;
import com.aurama.sholatyuk.model.khutbah.AppDatabase;
import com.aurama.sholatyuk.model.khutbah.KhutbahModel;

import java.util.ArrayList;

public class KhutbahAdapter extends RecyclerView.Adapter<KhutbahAdapter.ViewHolder> {
    private Context context;
    private ArrayList<KhutbahModel> khutbahItems = new ArrayList<>();
    private AppDatabase appDatabase;
    public Listener listener;

    public KhutbahAdapter(Context context) {
        this.context = context;
        appDatabase = AppDatabase.initDatabase(this.context);
    }

    public void setData(ArrayList<KhutbahModel> items) {
        khutbahItems.clear();
        khutbahItems.addAll(items);
        notifyDataSetChanged();
    }

    public interface Listener {
        void onClick(int position);
        void onClick1(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public KhutbahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_rangkuman_khutbah,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhutbahAdapter.ViewHolder holder, final int position) {
        holder.tvWaktu.setText(khutbahItems.get(position).getWaktu());
        holder.tvKhatib.setText(khutbahItems.get(position).getKhatib());
        holder.tvKhutbah.setText(khutbahItems.get(position).getKhutbah());

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    KhutbahModel khutbahModel = new KhutbahModel();

                    khutbahModel.setId(khutbahItems.get(position).getId());
                    khutbahModel.setWaktu(khutbahItems.get(position).getWaktu());
                    khutbahModel.setKhatib(khutbahItems.get(position).getKhatib());
                    khutbahModel.setKhutbah(khutbahItems.get(position).getKhutbah());

                    appDatabase.khutbahDAO().deletekhutbah(khutbahModel);
                    if (listener != null) {
                        listener.onClick(position);
                    }

                    Log.d("Khutbah Adapter ", "Sukses Dihapus");
                    Toast.makeText(context, "Sukses Dihapus", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("Khutbah Adapter ", "Gagal Menghapus , msg : " + e.getMessage());
                    Toast.makeText(context, "Gagal Menghapus", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick1(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return khutbahItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWaktu, tvKhatib, tvKhutbah;
        Button btndelete, btnUpdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWaktu = itemView.findViewById(R.id.tv_itemlist_waktu);
            tvKhatib = itemView.findViewById(R.id.tv_itemlist_khatib);
            tvKhutbah = itemView.findViewById(R.id.tv_itemlist_khutbah);
            btndelete = itemView.findViewById(R.id.btn_delete);
            btnUpdate = itemView.findViewById(R.id.btn_update);
        }
    }
}
