package com.salah.hodiedahclinicsapp_doctors2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class DoctorsMainActivity extends AppCompatActivity {



    private MeowBottomNavigation bnv_Main;
    //TextView upperTextView;

    private long time;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_main);





        bnv_Main = findViewById(R.id.bnv_Main);
        //upperTextView = findViewById(R.id.txtView1);


        bnv_Main.add(new MeowBottomNavigation.Model(1,R.drawable.doctor3));
        bnv_Main.add(new MeowBottomNavigation.Model(2,R.drawable.add_appointment));
        bnv_Main.add(new MeowBottomNavigation.Model(3,R.drawable.appointments));
        bnv_Main.add(new MeowBottomNavigation.Model(4,R.drawable.settings));

        bnv_Main.show(1,true);
        replace(new DoctorHomeFragment());
        bnv_Main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new DoctorHomeFragment());
                        break;

                    case 2:
                        replace(new DoctorNewAppointmentFragment());
                        break;

                    case 3:
                        replace(new DoctorAppointmentFragment());
                        Toast.makeText(getApplicationContext(), "Doctor Appointments", Toast.LENGTH_SHORT).show();
                        //upperTextView.setText("Doctor Appointments");
                        break;

                    case 4:
                        replace(new DoctorSettingsFragment());
                        break;

                }
                return null;
            }
        });


    }
    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }


    public void Edit_Profile(View view) {
        Intent intent = new Intent(getApplicationContext(),Doctor_PersonalDataFile.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Edit Profile", Toast.LENGTH_SHORT).show();

    }

    public void telling_an_error(View view) {
        Intent intent = new Intent(getApplicationContext(), com.salah.hodiedahclinicsapp_doctors2.Telling_Error_In_App.class);
        startActivity(intent);

    }

    public void onBackPressed() {
        if (time + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            backToast.cancel();
            return;
        }
        else {
            backToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        time=System.currentTimeMillis();

    }


}