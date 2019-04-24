package com.example.newdemo.authen_screen.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.newdemo.R;

public class LoginActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;
    AuthenViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        tabLayout = findViewById(R.id.tab_layout);

        viewPager = findViewById(R.id.view_pager);
        adapter = new AuthenViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
