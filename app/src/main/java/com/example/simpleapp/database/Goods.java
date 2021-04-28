package com.example.simpleapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "goods")
public class Goods {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String goodsName;
    private int goodsPrice;
    private String personBuyGoods;
    private String dateBuyGoods;

    public Goods(String goodsName, int goodsPrice, String personBuyGoods, String dateBuyGoods) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.personBuyGoods = personBuyGoods;
        this.dateBuyGoods = dateBuyGoods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getPersonBuyGoods() {
        return personBuyGoods;
    }

    public void setPersonBuyGoods(String personBuyGoods) {
        this.personBuyGoods = personBuyGoods;
    }

    public String getDateBuyGoods() {
        return dateBuyGoods;
    }

    public void setDateBuyGoods(String dateBuyGoods) {
        this.dateBuyGoods = dateBuyGoods;
    }
}
