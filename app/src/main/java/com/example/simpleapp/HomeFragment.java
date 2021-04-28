package com.example.simpleapp;

import android.content.Intent;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simpleapp.database.Goods;
import com.example.simpleapp.database.GoodsDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RecyclerView rcvGoodsHome;
    private View view;
    private TextView textViewSumMoney;
    private GoodsAdapterHome goodsAdapterHome;
    private List<Goods> listGoods;
    private Button summary_button;
    private Button tasks_button;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initUiHome();
        rcvGoodsHome = view.findViewById(R.id.rcv_goods_home);
        goodsAdapterHome = new GoodsAdapterHome();
        listGoods = new ArrayList<>();
        listGoods = GoodsDatabase.getInstance(getActivity()).goodsDAO().getListGoods();
        goodsAdapterHome.setData(listGoods);

        textViewSumMoney.setText("Đ " + String.format("%,d", sumMoney()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvGoodsHome.setLayoutManager(linearLayoutManager);
        rcvGoodsHome.setAdapter(goodsAdapterHome);

        Button to_logout = view.findViewById(R.id.home_logout);
        to_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                toLogoutActivity();
                Intent intent = new Intent(getActivity(), LoginTab.class);
                getActivity().startActivity(intent);
            }
        });

        summary_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SummaryActivity.class);
                getActivity().startActivity(intent);
            }
        });

        tasks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaskActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return  view;
    }
    private void initUiHome() {
        rcvGoodsHome = view.findViewById(R.id.rcv_goods_home);
        textViewSumMoney = view.findViewById(R.id.home_text_sum_money);
        summary_button = view.findViewById(R.id.home_summary);
        tasks_button = view.findViewById(R.id.home_tasks);
    }

    public long sumMoney() {
        long sum = 0;
        String thisMonth = getMonth(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
        for (int i=listGoods.size()-1; i>=0; i--) {
            Goods goods = listGoods.get(i);

            if (getMonth(goods.getDateBuyGoods()).equals(thisMonth)) {
                sum += goods.getGoodsPrice();
            }
            else break;
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
    public void toLogoutActivity() {
//        Intent intent = new Intent(this.HomeFragment., LoginTab.class);
//        startActivity(intent);
    }
}