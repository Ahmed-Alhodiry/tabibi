package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Patient_SignIn_and_SignUp extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    Dialog firstDialog;
    Dialog secondDialog;

    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__signin_and__signup);


        firstDialog=new Dialog(this);
        secondDialog=new Dialog(this);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);



        tabLayout.addTab(tabLayout.newTab().setText("تسجيل الدخول"));
        tabLayout.addTab(tabLayout.newTab().setText("انشاء حساب"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final Adapter_Login adapter = new Adapter_Login(getSupportFragmentManager(),this,tabLayout.getTabCount());
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

    public void move_to_signUp(View view) {
        viewPager.setCurrentItem(2);


    }
    public void move_to_login(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);



    }

    public void forgetPassword(View view) {
        Intent intent = new Intent(getApplicationContext(),Forget_Password.class);
        startActivity(intent);
    }
}