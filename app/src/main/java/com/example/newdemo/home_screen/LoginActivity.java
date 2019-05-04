package com.example.newdemo.home_screen;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.newdemo.R;
import com.example.newdemo.adapter.AuthenViewPagerAdapter;
import com.example.newdemo.dbcontext.RealmContext;
import com.example.newdemo.json_models.response.UserInfor;

public class LoginActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;
    AuthenViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        checkLogin();
    }

    private void init() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        adapter = new AuthenViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void checkLogin(){
        UserInfor userInfor = RealmContext.getInstance().getUser();
        if(userInfor != null){
            gotoHome();
        }
    }

    private void gotoHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
