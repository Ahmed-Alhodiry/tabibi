package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.Objects;


public class Welcome_Screens extends AppCompatActivity {

    public static ViewPager viewPager;
    Welcome_Screens_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screens);

        //Objects.requireNonNull(getSupportActionBar()).hide();

        notification();
        viewPager = findViewById(R.id.pager);
        adapter = new Welcome_Screens_Adapter(this);
        viewPager.setAdapter(adapter);


    }



        private void notification() {
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.positive_notification);
            mediaPlayer.start();
        }

    public void skip(View view) {
        Intent intent = new Intent(getApplicationContext(), Patient_SignIn_and_SignUp.class);
        startActivity(intent);
    }
}