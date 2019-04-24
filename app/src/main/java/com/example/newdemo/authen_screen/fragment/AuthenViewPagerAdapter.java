package com.example.newdemo.authen_screen.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.newdemo.authen_screen.fragment.fragment.LoginFragment;
import com.example.newdemo.authen_screen.fragment.fragment.RegisterFragment;

public class AuthenViewPagerAdapter extends FragmentPagerAdapter {

    private final int NUMBER_PAGE = 2;

    public AuthenViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new LoginFragment();
            case 1:
                return new RegisterFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_PAGE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Login";
            case 1:
                return "Register";
        }
        return null;
    }
}
