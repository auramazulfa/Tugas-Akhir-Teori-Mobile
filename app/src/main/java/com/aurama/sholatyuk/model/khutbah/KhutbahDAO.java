package com.aurama.sholatyuk.model.khutbah;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KhutbahDAO {
    @Insert
    long insertKhutbah(KhutbahModel khutbahModel);

    @Delete
    int deletekhutbah(KhutbahModel khutbahModel);

    @Query("SELECT * FROM data_khutbah")
    List<KhutbahModel> getDataKhutbah();

    @Update
    int updateKhutbah(KhutbahModel khutbahModel);
}
