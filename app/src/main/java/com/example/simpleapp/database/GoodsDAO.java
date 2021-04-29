package com.example.simpleapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GoodsDAO {
    @Insert
    void insertGoods(Goods goods);
    @Query("SELECT * FROM goods")
    List <Goods> getListGoods();

}
