package com.example.simpleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.simpleapp.R.id.fragment;

public class AddSpend extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spend);

        bottomNavigationView = findViewById(R.id.menu_bar);
        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Activity selectedActivity = null;
//
//                switch (item.getItemId()) {
//                    case R.id.add:
//
//                }
//                return false;
//            }
//        });

    }


//    private ActionBar toolbar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_spend);
//
//        toolbar = getSupportActionBar();
//
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R .id.menu_bar);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        toolbar.setTitle("Shop");
//    }
//
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//    = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        private MenuItem item;
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment fragment;
//            switch (item.getItemId()) {
//                case R.id.add:
//                    toolbar.setTitle("Add Spend");
//                    return true;
//                case R.id.per:
//                    toolbar.setTitle("Personal");
//                    return true;
//                case R.id.com:
//                    toolbar.setTitle("Common");
//                    return true;
//                case R.id.list:
//                    toolbar.setTitle("List");
//                    return true;
//                case R.id.chart:
//                    toolbar.setTitle("Chart");
//                    return true;
//            }
//            return false;
//        }
//    };
}