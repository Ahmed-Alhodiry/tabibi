package com.example.myapplication;

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
                Patient_Login patient_login = new Patient_Login();
                return  patient_login;
            case 1:
                Patient_SignUp doctor_signup = new Patient_SignUp();
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
