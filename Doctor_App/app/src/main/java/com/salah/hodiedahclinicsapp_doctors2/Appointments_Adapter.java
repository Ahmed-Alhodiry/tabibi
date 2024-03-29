package com.salah.hodiedahclinicsapp_doctors2;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Appointments_Adapter  extends FragmentStateAdapter {
    private Context context;
    int totalTabs;

    public Appointments_Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0 :
                return new com.salah.hodiedahclinicsapp_doctors2.SureAppointmentsFragment();
            case 1 :
                return new com.salah.hodiedahclinicsapp_doctors2.UnSureAppointmentsFragment();
        }
        return new com.salah.hodiedahclinicsapp_doctors2.UnSureAppointmentsFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
