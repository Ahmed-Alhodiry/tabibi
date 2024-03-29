package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import es.dmoral.toasty.Toasty;


public class Patient_Login extends Fragment {

    Button loginBtn;
    Dialog dialog;
    EditText user_email, password;
    TextView forget_password;
    CardView RoundCardView;
    View layout;
    float v;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Animation animation;



    androidx.cardview.widget.CardView card;
    LottieAnimationView lottie;
    ConstraintLayout dialogContainer;
    private static final String TAG = Patient_SignIn_and_SignUp.class.getName();
    TextView title_txt;
    Dialog loadingDialog;

    private FirebaseAuth auth;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static String user_id;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_patient__login, container, false);


        auth = FirebaseAuth.getInstance();


        user_email = root.findViewById(R.id.login_user_email);
        password = root.findViewById(R.id.login_user_password);
        forget_password = root.findViewById(R.id.forget_password);
        RoundCardView = root.findViewById(R.id.optRoundCardView);
        layout = root.findViewById(R.id.login_containerLayout);

        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.control_loading_dialog);
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingDialog.setCanceledOnTouchOutside(false);
        title_txt = loadingDialog.findViewById(R.id.loading_title);
        title_txt.setText("تسجيل الدخول");

        dialog = new Dialog(container.getContext());
        loginBtn = root.findViewById(R.id.btn_login);

        /*
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkFields())
                {
                    if (checkSwitchingOnOfConnection()) {
                        if (user_email.getText().toString().trim().matches(emailPattern)) {
                            loadingDialog.show();
                            if (user_email.getText().toString().trim().equals("salahdoos77@gmail.com") && password.getText().toString().trim().equals("123456")) {

                                    lottie = loadingDialog.findViewById(R.id.lottie_img2);
                                    card = loadingDialog.findViewById(R.id.card_loading_dialog);
                                    card.setVisibility(View.GONE);
                                    lottie.setVisibility(View.VISIBLE);
                                    final Handler handler = new Handler(Looper.getMainLooper());
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            resetFields();
                                            Intent intent = new Intent(requireActivity(), MainActivity.class);
                                            loadingDialog.dismiss();
                                            startActivity(intent);
                                            requireActivity().finish();
                                            Log.d(TAG, "run: Done");
                                        }
                                    }, 500);
                            }
                            else
                            {
                                OpenDialog1();
                            }
                        } else {
                            user_email.setError("الرجاء إدخال إيميل صحيح");
                            user_email.requestFocus();
                        }

                        } else {
                    Snackbar snackbar = Snackbar.make(root.findViewById(R.id.login_containerLayout), "لايوجد اتصال بالإنترنت !", Snackbar.LENGTH_LONG)
                            .setAction("فتح الشبكة", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                }
                            });
                    snackbar.show();
                }
                }
                else { Toasty.error(getActivity().getApplicationContext(), "يرجى إدخال كل البيانات", Toast.LENGTH_SHORT).show();
                }


            }
        });
        */


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkFields()) {
                    if (checkSwitchingOnOfConnection()) {
                        if (user_email.getText().toString().trim().matches(emailPattern)) {

                            auth.signInWithEmailAndPassword(user_email.getText().toString().trim(), password.getText().toString().trim())
                                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {

                                            if (Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified())
                                            {
                                                if (!task.isSuccessful())
                                                {
                                                    if (password.length() < 6)
                                                    {
                                                        password.setError("كلمة المرور يجب أن تكو أكبر من 6");
                                                    }
                                                    else
                                                    {
                                                        loadingDialog.dismiss();
                                                        OpenDialog1();
                                                        Toasty.error(getActivity().getApplicationContext(), "خطأ في تسجيل الدخول, تحقق من بريدك الإلكتروني وكلمة المرور", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                                else
                                                {
                                                    loadingDialog.show();

                                                    final Handler handler = new Handler(Looper.getMainLooper());
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            //Do something after 1000s
                                                            lottie = loadingDialog.findViewById(R.id.lottie_img2);
                                                            card = loadingDialog.findViewById(R.id.card_loading_dialog);
                                                            card.setVisibility(View.GONE);
                                                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                            lottie.setVisibility(View.VISIBLE);
                                                            //dialogContainer = loadingDialog.findViewById(R.id.dialog_container);
                                                            //dialogContainer.setBackground(getResources().getDrawable(R.drawable.shadow_login_panel));
                                                        }
                                                    }, 1000);

                                                    final Handler handler2 = new Handler(Looper.getMainLooper());
                                                    handler2.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            //Do something after 3000s
                                                            resetFields();
                                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(intent);
                                                            loadingDialog.dismiss();
                                                            Toasty.success(getActivity(), "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT, true).show();
                                                            startActivity(intent);
                                                            //requireActivity().finish();
                                                            }
                                                    }, 2000);


                                                    /*
                                                    final Handler handler = new Handler(Looper.getMainLooper());
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            resetFields();

                                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(intent);

                                                            loadingDialog.dismiss();
                                                            Toasty.success(getActivity(), "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT, true).show();
                                                            startActivity(intent);
                                                            //requireActivity().finish();
                                                            Log.d(TAG, "run: Done");
                                                        }
                                                    }, 1000);

                                                     */
                                                }
                                            }
                                            else
                                            {
                                                loadingDialog.dismiss();
                                                resetFields();
                                                Toasty.error(requireActivity(), "بريك الإلكتروني غير مفعل يرجى تفعيله أولاً", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            user_email.setError("الرجاء إدخال بريد الإلكتروني صحيح");
                            user_email.requestFocus();
                        }
                    } else {
                        Snackbar snackbar = Snackbar.make(root.findViewById(R.id.login_containerLayout), "لا يتوفر إنترنت!", Snackbar.LENGTH_LONG)
                                .setAction("فتح الشبكة", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                    }
                                });
                        snackbar.show();
                    }
                } else {
                    Toasty.error(getActivity().getApplicationContext(), "يرجى ملئ كل الحقول", Toast.LENGTH_SHORT).show();

                }

            }
        });




        return root;
    }


    private void OpenDialog1() {
        dialog.setContentView(R.layout.dialog_login_wrong_data);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageViewClose = dialog.findViewById(R.id.success_icon);
        Button btnOk = dialog.findViewById(R.id.btn_ok);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        resetFields();
    }

    private void anim() {

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
        layout.setAnimation(animation);
    }

    private boolean checkFields() {
        int number = 0;
        if (user_email.getText().toString().isEmpty()) {
            user_email.setError("أدخل البريد الإلكتروني");
        } else {
            number++;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("أدخل كلمة المرور");
        } else {
            number++;
        }
        return (number == 2);
    }

    private void resetFields() {
        user_email.setText("");
        password.setText("");
        user_email.requestFocus();
    }

    private boolean checkSwitchingOnOfConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }



}
