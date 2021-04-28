package com.example.simpleapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Goods.class}, version = 1)
public abstract class GoodsDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "goods.db";
    private static GoodsDatabase instance;

    public static synchronized  GoodsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), GoodsDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return  instance;
    }
    public abstract GoodsDAO goodsDAO();
}
