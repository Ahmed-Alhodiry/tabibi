package com.salah.hodiedahclinicsapp_doctors2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.zip.Inflater;


public class DoctorAppointmentFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    float v = 0;

    public DoctorAppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = (View) inflater.inflate(R.layout.fragment_doctor_appointment, container, false);
        viewPager = root.findViewById(R.id.appointments_viewpager_id);
        tabLayout = root.findViewById(R.id.appointments_tab_layout);

        FragmentManager fm = getParentFragmentManager();
        Appointments_Adapter adapter = new Appointments_Adapter(fm, getLifecycle());
        viewPager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Sure Appointments"));
        tabLayout.addTab(tabLayout.newTab().setText("UnSure Appointments"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        return root;
    }
}