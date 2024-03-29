package com.salah.hodiedahclinicsapp_doctors2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Adapter_Login extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;
    ViewPager viewPager;

    public Adapter_Login(FragmentManager fm, Context context , int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                com.salah.hodiedahclinicsapp_doctors2.Doctor_Login doctor_login = new com.salah.hodiedahclinicsapp_doctors2.Doctor_Login();
                return  doctor_login;
            case 1:
                com.salah.hodiedahclinicsapp_doctors2.Doctor_Signup doctor_signup = new com.salah.hodiedahclinicsapp_doctors2.Doctor_Signup();
                return  doctor_signup;
            default:
                return null;
        }



    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
