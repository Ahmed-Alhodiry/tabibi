package com.example.myapplication;

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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Forget_Password extends AppCompatActivity {

    TextView user_EMAIL, user_NUMBER;
    androidx.cardview.widget.CardView container;
    Animation animation;
    Button getPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    androidx.cardview.widget.CardView card;
    LottieAnimationView lottie;
    Dialog dialog;
    TextView title_txt;
    private static final String TAG = MainActivity.class.getName();

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
                            title_txt = dialog.findViewById(R.id.loading_title);
                            title_txt.setText("استرجاع كلمة المرور");

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
                                                Toast.makeText(getBaseContext()  , "تم ارسال رابط تغيير كلمة المرور, يرجى التحقق من بريدك الإلكتروني" ,Toast.LENGTH_SHORT).show();
                                            }
                                        }, 2000);

                                    }else{
                                        Toasty.error(getApplicationContext().getApplicationContext(), "(حدث خطأ! (حاول مرةً أخرى", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else
                        {
                            user_EMAIL.setError("أدخل بريد إلكتروني صحيح");
                            user_EMAIL.requestFocus();
                        }
                    }
                    else
                    {
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.optRoundCardView),"لا يتوفر إنترنت!",Snackbar.LENGTH_LONG)
                                .setAction("فتح الشبكة", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));                                    }
                                });
                        snackbar.show();
                    }
                }
                else
                {
                    Toasty.error(getApplicationContext().getApplicationContext(), "يرجى ملئ كل الحقول لإسترجاع كلمة المرور", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    private void anim() {

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        container.setAnimation(animation);
    }

    private boolean checkFields()
    {
        int number=0 ;

        if(user_EMAIL.getText().toString().isEmpty()){
            user_EMAIL.setError("أدخل البريد الإلكتروني");   }
        else
        {
            number++ ;
        }
        if(user_NUMBER.getText().toString().isEmpty()){
            user_NUMBER.setError("أدخل رقم الهاتف");   }
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


    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), Patient_SignIn_and_SignUp.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}