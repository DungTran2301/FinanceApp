package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class IntroduceActivity extends AppCompatActivity {

    ImageView logo, splashImg;
    TextView tvMadeBy;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        //this.deleteDatabase("goods");
//        tvMadeBy.findViewById(R.id.tv_made_by);
//
//        tvMadeBy.animate().translationY(-1000).setDuration(1000).setStartDelay(4000);
        Button next_intro = findViewById(R.id.next_intro);
        next_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginLayout();
            }
        });
    }
    public void openLoginLayout() {
        Intent intent = new Intent(IntroduceActivity.this, LoginTab.class);
        startActivity(intent);
    }


}