package com.salah.hodiedahclinicsapp_doctors2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Forget_Password extends AppCompatActivity {

    TextView user_EMAIL, user_NUMBER;
    androidx.cardview.widget.CardView container;
    Animation animation;
    Button getPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    androidx.cardview.widget.CardView card;
    LottieAnimationView lottie;
    Dialog dialog;
    //private static final String TAG = MainActivity.class.getName();

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);

        auth = FirebaseAuth.getInstance();

        user_EMAIL = findViewById(R.id.forgetPass_userEmail);
        user_NUMBER = findViewById(R.id.forgetPass_userNumber);
        getPassword = findViewById(R.id.get_password);
        container = findViewById(R.id.card_container);

        dialog=new Dialog(container.getContext());
        anim();



        getPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(checkFields())
                {
                    if(checkSwitchingOnOfConnection())
                    {
                        if(user_EMAIL.getText().toString().trim().matches(emailPattern))
                        {
                            dialog = new Dialog(Forget_Password.this);
                            dialog.setContentView(R.layout.loading_dialog);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.show();

                            auth.sendPasswordResetEmail(user_EMAIL.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){

                                        final Handler handler = new Handler(Looper.getMainLooper());
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //Do something after 1000s
                                                lottie = dialog.findViewById(R.id.lottie_img2);
                                                card = dialog.findViewById(R.id.card_loading_dialog);
                                                card.setVisibility(View.GONE);
                                                lottie.setVisibility(View.VISIBLE);
                                            }
                                        }, 1000);

                                        final Handler handler2 = new Handler(Looper.getMainLooper());
                                        handler2.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //Do something after 3000s
                                                resetFields();
                                                dialog.dismiss();
                                                Toast.makeText(getBaseContext()  , "please check your email to reset your password" ,Toast.LENGTH_SHORT).show();
                                            }
                                        }, 2000);
                                    }else{
                                        Toast.makeText(getBaseContext()  , "!Try again, something wrong happened" ,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else
                        {
                            user_EMAIL.setError("Please Enter Correct Email");
                            user_EMAIL.requestFocus();
                        }
                    }
                    else
                    {
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.optRoundCardView),"!No internet Connection",Snackbar.LENGTH_LONG)
                                .setAction("CHECK IT", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));                                    }
                                });
                        snackbar.show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext()  , "please fill all fields to reset your password" ,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /*
    private void OpenDialog1() {
        dialog.setContentView(R.layout.dialog_signup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageViewClose = dialog.findViewById(R.id.success_icon);
        Button btnOk = dialog.findViewById(R.id. btn_ok);
        TextView txt = dialog.findViewById(R.id.txt_in_wrong_data_dialog);
        txt.setText("تم استرجاع كلمة المرور بنجاح , تحقق من بريدك الالكتروني");
        txt.setTextSize(18);
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Dialog Close", Toast.LENGTH_SHORT).show();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        resetFields();
    }
     */

    private void anim() {

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        container.setAnimation(animation);
    }

    private boolean checkFields()
    {
        int number=0 ;

        if(user_EMAIL.getText().toString().isEmpty()){
            user_EMAIL.setError("Please Enter Your Email");   }
        else
        {
            number++ ;
        }
        if(user_NUMBER.getText().toString().isEmpty()){
            user_NUMBER.setError("Please Enter Your Phone Number");   }
        else
        {
            number++ ;
        }
        return (number==2);
    }

    private  void resetFields()
    {
        user_EMAIL.setText("");
        user_NUMBER.setText("");
        user_EMAIL.requestFocus();
    }

    private  boolean checkSwitchingOnOfConnection()
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true ;
        }
        else {
            return  false ;
        }
    }



}