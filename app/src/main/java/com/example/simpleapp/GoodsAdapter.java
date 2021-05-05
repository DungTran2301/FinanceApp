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

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodViewHolder>{
    private List<Goods> listGoods;
    protected void setData(List <Goods> list) {
        this.listGoods = list;
        Collections.reverse(listGoods);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public GoodsAdapter.GoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false);
        return new GoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsAdapter.GoodViewHolder holder, int position) {
        Goods goods = listGoods.get(position);
        if (goods == null) return;
        holder.tvGoodsName.setText(goods.getGoodsName());
        holder.tvGoodsPrice.setText(String.format("%,d", goods.getGoodsPrice()) + " Đ");
        holder.tvPersonAdd.setText(goods.getPersonBuyGoods());
        holder.tvGoodsDate.setText(goods.getDateBuyGoods());
    }

    @Override
    public int getItemCount() {
        if (listGoods != null) {
            return listGoods.size();
        }
        return 0;
    }
    public static class GoodViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGoodsName;
        private TextView tvGoodsPrice;
        private TextView tvPersonAdd;
        private TextView tvGoodsDate;

        public GoodViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            tvGoodsPrice = itemView.findViewById(R.id.tv_goods_price);
            tvPersonAdd = itemView.findViewById(R.id.tv_person_add);
            tvGoodsDate = itemView.findViewById(R.id.tv_goods_date);

        }
    }
}

