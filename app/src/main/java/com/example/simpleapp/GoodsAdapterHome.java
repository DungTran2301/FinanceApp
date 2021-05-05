package com.example.simpleapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleapp.database.Goods;

import java.util.Collections;
import java.util.List;

public class GoodsAdapterHome extends RecyclerView.Adapter<GoodsAdapterHome.GoodViewHolder> {
    private List<Goods> list;
    protected void setData(List<Goods> list) {
        this.list = list;
        Collections.reverse(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public GoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_good_home, parent, false);
        return new GoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodViewHolder holder, int position) {
        Goods goods = list.get(position);
        if (goods == null) return;
        holder.tvGoodsNameHome.setText(goods.getGoodsName());
        holder.tvGoodsPriceHome.setText(String.format("%,d", goods.getGoodsPrice()) + " Đ");
        //holder.tvGoodsPriceHome.setText(String.valueOf(goods.getGoodsPrice()));
        holder.tvPersonAddHome.setText(goods.getPersonBuyGoods());
        holder.tvGoodsDateHome.setText(goods.getDateBuyGoods());
    }

    @Override
    public int getItemCount() {
        if(list.size() != 0) return list.size();
        return 0;
    }
    public static class GoodViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGoodsNameHome;
        private TextView tvGoodsPriceHome;
        private TextView tvPersonAddHome;
        private TextView tvGoodsDateHome;
        public GoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGoodsNameHome = itemView.findViewById(R.id.tv_goods_name_home);
            tvGoodsPriceHome = itemView.findViewById(R.id.tv_goods_price_home);
            tvPersonAddHome = itemView.findViewById(R.id.tv_person_add_home);
            tvGoodsDateHome = itemView.findViewById(R.id.tv_goods_date_home);
        }
    }
}
