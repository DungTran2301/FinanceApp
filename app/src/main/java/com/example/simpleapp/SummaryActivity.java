package com.example.simpleapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simpleapp.database.Goods;
import com.example.simpleapp.database.GoodsDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        initUiSummary();
        listGoods = new ArrayList<>();
        listGoods = GoodsDatabase.getInstance(this).goodsDAO().getListGoods();
        long total = sumMoney("Total");
        long dungChi = sumMoney("Đăng Dũng");
        long anhChi = sumMoney("Trung Anh");

        tvTotal.setText(String.format("%,d", total) + " đ");
        tvDung.setText(String.format("%,d", dungChi) + " đ");
        tvAnh.setText(String.format("%,d", anhChi) + " đ");

        Button tinhtien = findViewById(R.id.btn_tinh);
        tinhtien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long totalMoney = getFinalMoney();
                if (totalMoney != -1) {
                    long hieu = Math.abs(dungChi - anhChi);
                    if (dungChi >= anhChi) {
                        long dungPay = (totalMoney - hieu) / 2;
                        long anhPay = (totalMoney + hieu) / 2;
                        tvMoneyDungPay.setText(String.format("%,d", dungPay) + " đ");
                        tvMoneyTAPay.setText(String.format("%,d", anhPay) + " đ");
                    } else {
                        long dungPay = (totalMoney + hieu) / 2;
                        long anhPay = (totalMoney - hieu) / 2;
                        tvMoneyDungPay.setText(String.format("%,d", dungPay) + " đ");
                        tvMoneyTAPay.setText(String.format("%,d", anhPay) + " đ");
                    }
                }
            }
        });

        Button back_home = findViewById(R.id.button_back_summary);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
                startActivity(intent);
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
        String moneyHouse_s = edtMoneyHouse.getText().toString().trim();
        String electricNumber_s = edtElectricNumber.getText().toString().trim();
        String electricMoneyPerNumber_s = edtElectricMoneyPerNumber.getText().toString().trim();
        String waterNumber_s = edtWaterNumber.getText().toString().trim();
        String waterMoneyPerNumber_s = edtWaterMoneyPerNumber.getText().toString().trim();
        String moneyInternet_s = edtMoneyInternet.getText().toString().trim();

        if (moneyHouse_s.equals("") || electricNumber_s.equals("") ||electricMoneyPerNumber_s.equals("") ||
            waterNumber_s.equals("") || waterMoneyPerNumber_s.equals("") || moneyInternet_s.equals("")) {
            if (moneyHouse_s.equals("")) {
                Toast.makeText(this, "Bạn chưa nhập tiền nhà!", Toast.LENGTH_LONG).show();
            } else if (electricNumber_s.equals("")) {
                Toast.makeText(this, "Bạn chưa nhập số điện!", Toast.LENGTH_LONG).show();
            } else if (electricMoneyPerNumber_s.equals("")) {
                Toast.makeText(this, "Bạn chưa nhập đơn giá điện!", Toast.LENGTH_LONG).show();
            } else if (waterNumber_s.equals("")) {
                Toast.makeText(this, "Bạn chưa nhập số nước!", Toast.LENGTH_LONG).show();
            } else if (waterMoneyPerNumber_s.equals("")) {
                Toast.makeText(this, "Bạn chưa nhập đơn giá nước!", Toast.LENGTH_LONG).show();
            } else if (moneyInternet_s.equals("")) {
                Toast.makeText(this, "Bạn chưa nhập tiền internet!", Toast.LENGTH_LONG).show();
            }
            return -1;
        }
        else {
            long moneyHouse = Long.valueOf(moneyHouse_s);
            int electricNumber = Integer.parseInt(electricNumber_s);
            int electricMoneyPerNumber = Integer.parseInt(electricMoneyPerNumber_s);
            int waterNumber = Integer.parseInt(waterNumber_s);
            int waterMoneyPerNumber = Integer.parseInt(waterMoneyPerNumber_s);
            int moneyInternet = Integer.parseInt(moneyInternet_s);
            finalMoney = moneyHouse + electricNumber * electricMoneyPerNumber + waterNumber * waterMoneyPerNumber + moneyInternet;
            return finalMoney;
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public long sumMoney(String s) {
        long sum = 0;
        String thisDate = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault()).format(new Date());
        String thisMonth = getMonth(thisDate);
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
        if (s.length() == 1) {
            tg += "0" + s;
        } else if(s.length() == 2) {
        } else {
            tg += s.charAt(3) + "";
            tg += s.charAt(4) + "";
        }
        return tg;
    }
}