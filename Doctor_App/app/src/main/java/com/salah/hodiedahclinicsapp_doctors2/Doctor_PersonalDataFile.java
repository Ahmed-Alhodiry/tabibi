package com.salah.hodiedahclinicsapp_doctors2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Doctor_PersonalDataFile extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__personal_data_file);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_information));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_clinic));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_calender_checked));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final Adapter_Doctor_PersonalDataFile adapter = new Adapter_Doctor_PersonalDataFile(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTranslationY(100);
        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem( tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }
}