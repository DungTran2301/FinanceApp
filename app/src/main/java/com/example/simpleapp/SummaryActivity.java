package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simpleapp.database.Goods;
import com.example.simpleapp.database.GoodsDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {
    private TextView tvTotal;
    private TextView tvDung;
    private TextView tvAnh;
    private TextView tvMoneyDungPay;
    private TextView tvMoneyTAPay;

    private TextView tvFinalMoney;
    private GoodsAdapter goodsAdapter;
    private List<Goods> listGoods;
    private EditText edtMoneyHouse, edtElectricNumber, edtElectricMoneyPerNumber, edtWaterNumber, edtWaterMoneyPerNumber, edtMoneyInternet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        initUiSummary();
        listGoods = new ArrayList<>();
        listGoods = GoodsDatabase.getInstance(this).goodsDAO().getListGoods();
        long total = sumMoney("Total");
        long dungChi = sumMoney("Dang Dung");
        long anhChi = sumMoney("Trung Anh");

        tvTotal.setText(String.format("%,d", total) + " đ");
        tvDung.setText(String.format("%,d", dungChi) + " đ");
        tvAnh.setText(String.format("%,d", anhChi) + " đ");

        Button tinhtien = findViewById(R.id.btn_tinh);
        tinhtien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long totalMoney = getFinalMoney();
                long hieu = Math.abs(dungChi - anhChi);
                if (dungChi >= anhChi) {
                    long dungPay = (totalMoney - hieu)/2;
                    long anhPay = (totalMoney + hieu)/2;
                    tvMoneyDungPay.setText(String.format("%,d", dungPay) + " đ");
                    tvMoneyTAPay.setText(String.format("%,d", anhPay) + " đ");
                } else {
                    long dungPay = (totalMoney + hieu)/2;
                    long anhPay = (totalMoney - hieu)/2;
                    tvMoneyDungPay.setText(String.format("%,d", dungPay) + " đ");
                    tvMoneyTAPay.setText(String.format("%,d", anhPay) + " đ");
                }
            }
        });

        Button back_home = findViewById(R.id.button_back_summary);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipientsIntent = new Intent(this, RecipientsFragment.class);
                recipientsIntent.setData(getActivity().getIntent().getData());
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.summaryActivity, new HomeFragment()).commit();
            }
        });
    }

    private void initUiSummary() {
        tvTotal = findViewById(R.id.tv_total_money);
        tvDung = findViewById(R.id.tv_dung_money);
        tvAnh = findViewById(R.id.tv_anh_money);
        tvMoneyDungPay = findViewById(R.id.tv_money_dung_pay);
        tvMoneyTAPay = findViewById(R.id.tv_money_tanh_pay);
        edtMoneyHouse = findViewById(R.id.edt_money_house);
        edtElectricNumber = findViewById(R.id.edt_electric_number);
        edtElectricMoneyPerNumber= findViewById(R.id.edt_electric_money_per_number);
        edtWaterNumber = findViewById(R.id.edt_water_number);
        edtWaterMoneyPerNumber = findViewById(R.id.edt_water_money_per_number);
        edtMoneyInternet = findViewById(R.id.edt_money_internet);
    }
    public long getFinalMoney() {
        long finalMoney = 0;
        
        long moneyHouse = Long.valueOf(edtMoneyHouse.getText().toString().trim());
        int electricNumber = Integer.parseInt(edtElectricNumber.getText().toString().trim());
        int electricMoneyPerNumber = Integer.parseInt(edtElectricMoneyPerNumber.getText().toString().trim());
        int waterNumber = Integer.parseInt(edtWaterNumber.getText().toString().trim());
        int waterMoneyPerNumber = Integer.parseInt(edtWaterMoneyPerNumber.getText().toString().trim());
        int moneyInternet = Integer.parseInt(edtMoneyInternet.getText().toString().trim());
        finalMoney = moneyHouse + electricNumber * electricMoneyPerNumber + waterNumber *waterMoneyPerNumber + moneyInternet;


        return finalMoney;
    }
    public long sumMoney(String s) {
        long sum = 0;
        String thisMonth = getMonth(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
        if (s.equals("Total")) {
            for (int i=listGoods.size()-1; i>=0; i--) {
                Goods goods = listGoods.get(i);

                if (getMonth(goods.getDateBuyGoods()).equals(thisMonth)) {
                    sum += goods.getGoodsPrice();
                }
                else break;
            }
        } else {
            for (int i=listGoods.size()-1; i>=0; i--) {
                Goods goods = listGoods.get(i);

                if (getMonth(goods.getDateBuyGoods()).equals(thisMonth)) {
                    if ( goods.getPersonBuyGoods().equals(s))
                        sum += goods.getGoodsPrice();
                }
            }
        }
        return sum;
    }
    private String getMonth(String s) {
        String tg = "";
        for (int i=0; i<3; i++) {
            tg += s.charAt(i) + "";
        }
        return tg;
    }
}