package com.salah.hodiedahclinicsapp_doctors2;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Doctors_Signin_and_Signup extends AppCompatActivity {
/*
    TextView cv_path;
    private final int CHOOSE_PDF_FROM_DEVICE =1001;
    private static String TAG = "Doctors_Signin_and_Signup";
 */
ImageButton btn_get_CV;





    TabLayout tabLayout;
    ViewPager viewPager;

    Dialog firstDialog;
    Dialog secondDialog;



    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors__signin_and__signup);


        firstDialog=new Dialog(this);
        secondDialog=new Dialog(this);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        //cv_path = findViewById(R.id.cv_path);
       // btn_get_CV = findViewById(R.id.doctor_cv);


        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("SignUp"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final com.salah.hodiedahclinicsapp_doctors2.Adapter_Login adapter = new com.salah.hodiedahclinicsapp_doctors2.Adapter_Login(getSupportFragmentManager(),this,tabLayout.getTabCount());
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

/*
    private void callChooseFileFromDevice(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent,CHOOSE_PDF_FROM_DEVICE);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case CHOOSE_PDF_FROM_DEVICE:
                if(resultCode==RESULT_OK){
                    String FilePath = data.getData().getPath();
                    cv_path.setText(FilePath);
                }
                break;

        }
    }

 */


}