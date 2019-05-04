package com.example.newdemo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.newdemo.authen_screen.fragment.fragment.FriendFragment;
import com.example.newdemo.authen_screen.fragment.fragment.LoginFragment;
import com.example.newdemo.authen_screen.fragment.fragment.NewFeedsFragment;
import com.example.newdemo.authen_screen.fragment.fragment.RegisterFragment;

public class HomeViewPagerAdapter extends FragmentPagerAdapter{

    private final int NUMBER_PAGE = 2;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new NewFeedsFragment();
            case 1:
                return new FriendFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_PAGE;
    }
}
