package com.aurama.sholatyuk.model.khutbah;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {KhutbahModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract KhutbahDAO khutbahDAO();
    public static AppDatabase appDatabase;
    public static AppDatabase initDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    "khutbah"
            ).allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
