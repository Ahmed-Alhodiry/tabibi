package com.salah.hodiedahclinicsapp_doctors2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Adapter_Doctor_PersonalDataFile extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;
    ViewPager viewPager;

    public Adapter_Doctor_PersonalDataFile(FragmentManager fm, Context context , int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Personal_Information fragment_doctor_personal_information = new com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Personal_Information();
                return  fragment_doctor_personal_information;
            case 1:
                com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Clinic_Information fragment_doctor_clinic_information = new com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Clinic_Information();
                return  fragment_doctor_clinic_information;
            case 2:
                com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_WorkTime fragment_doctor_workTime = new com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_WorkTime();
                return fragment_doctor_workTime;
            default:
                return null;
        }



    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
