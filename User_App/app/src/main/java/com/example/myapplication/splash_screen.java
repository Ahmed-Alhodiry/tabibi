package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;

public class splash_screen extends AppCompatActivity {


    ImageView img;
    LottieAnimationView app_name;

    Animation animation1, animation2, animation3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        anim();


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), Welcome_Screens.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage() , Toast.LENGTH_LONG).show();
                }
            }
        };
        thread.start();


    }

    private void anim() {
        img = findViewById(R.id.lottie);
        app_name = findViewById(R.id.appName);

        animation1 = AnimationUtils.loadAnimation(this, R.anim.up);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        animation3 = AnimationUtils.loadAnimation(this, R.anim.scale);

        app_name.setAnimation(animation1);
        //img.setAnimation(animation3);
    }



}
