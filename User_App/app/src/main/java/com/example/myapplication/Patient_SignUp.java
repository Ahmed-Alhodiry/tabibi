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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

//import es.dmoral.toasty.Toasty;

public class Patient_SignUp extends Fragment {

    Button signUpBtn;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    TextView user_name, user_password,siginup_user_password2 , user_email;
    Dialog dialog;
    private FirebaseAuth auth;
    androidx.cardview.widget.CardView card;
    LottieAnimationView lottie;
    private static final String TAG = Patient_SignIn_and_SignUp.class.getName();
    TextView title_txt;
    Dialog loadingDialog;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_patient__signup, container, false);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        dialog = new Dialog(container.getContext());
        signUpBtn = root.findViewById(R.id.btn_signup);
        user_name = root.findViewById(R.id.siginup_user_name);
        user_password = root.findViewById(R.id.siginup_user_password);
        user_email = root.findViewById(R.id.siginup_user_email);
        siginup_user_password2 = root.findViewById(R.id.siginup_user_password2);

        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingDialog.setCanceledOnTouchOutside(false);
        title_txt = loadingDialog.findViewById(R.id.loading_title);
        title_txt.setText("إنشاء حساب");


        user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (
                        user_name.getText().toString().contains("1")
                                || user_name.getText().toString().contains("2")
                                || user_name.getText().toString().contains("3")
                                || user_name.getText().toString().contains("4")
                                || user_name.getText().toString().contains("5")
                                || user_name.getText().toString().contains("6")
                                || user_name.getText().toString().contains("7")
                                || user_name.getText().toString().contains("8")
                                || user_name.getText().toString().contains("9")
                                || user_name.getText().toString().contains("0")
                                || user_name.getText().toString().contains("@")
                                || user_name.getText().toString().contains("#")
                                || user_name.getText().toString().contains("$")
                                || user_name.getText().toString().contains(">")
                                || user_name.getText().toString().contains("<")
                                || user_name.getText().toString().contains("?")
                                || user_name.getText().toString().contains("&")
                                || user_name.getText().toString().contains("*")

                ) {
                    Toast.makeText(getContext(), "Sorry!! your Name must'nt have numbers or signs", Toast.LENGTH_SHORT).show();
                    user_name.setText("");
                    user_name.setFocusable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFields())
                {
                    if (checkSwitchingOnOfConnection())
                    {
                        if (user_email.getText().toString().trim().matches(emailPattern)) {

                            if (user_password.length() < 6 || user_password.getText().toString().equals(siginup_user_password2.getText().toString()) )
                            {

                                auth.createUserWithEmailAndPassword(user_email.getText().toString().trim(), user_password.getText().toString().trim())
                                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {

                                                if (task.isSuccessful()) {
                                                    auth.getCurrentUser().sendEmailVerification();
                                                    lottie = loadingDialog.findViewById(R.id.lottie_img2);
                                                    card = loadingDialog.findViewById(R.id.card_loading_dialog);
                                                    title_txt =loadingDialog.findViewById(R.id.loading_title);
                                                    card.setVisibility(View.GONE);
                                                    lottie.setVisibility(View.VISIBLE);
                                                    final Handler handler = new Handler(Looper.getMainLooper());
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            resetFields();
                                                            loadingDialog.dismiss();
                                                            Toast.makeText(requireActivity(), "We send you verified link, Please check your email" ,Toast.LENGTH_SHORT).show();
                                                            OpenDialog1();
                                                            Log.d(TAG, "run: Done");
                                                        }
                                                    }, 500);


                                                } else {
                                                    Toasty.error(requireActivity(), "Something Went wrong!!" + task.getException(),Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                            }
                            else
                            {
                                user_password.setError("كلمة المرور يجب ان تكون متطابقة وأكبر من 6");
                            }
                        }
                        else
                        {
                            user_email.setError("الرجاء إدخال بريد الإلكتروني صحيح");
                            user_email.requestFocus();
                        }
                    }
                    else
                    {
                        Snackbar snackbar = Snackbar.make(root.findViewById(R.id.siginup_containerLayout), "لا يوجد إنترنت!", Snackbar.LENGTH_LONG)
                                .setAction("فتخ الشبكة", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                    }
                                });
                        snackbar.show();
                    }
                }
                else
                {
                    Toasty.error(getActivity().getApplicationContext(), "يرجى ملئ كل الحقول", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return root;
    }


    private void OpenDialog1() {
        dialog.setContentView(R.layout.dialog_signup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView success_icon = dialog.findViewById(R.id.success_icon);
        Button btnOk = dialog.findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        success_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        dialog.show();
        resetFields();
    }


    private boolean checkFields() {
        int number = 0;
        if (user_name.getText().toString().isEmpty()) {
            user_name.setError("أدخل اسم المستخدم");
        } else {
            number++;
        }
        if (user_email.getText().toString().isEmpty()) {
            user_email.setError("أدخل البريد الإلكتروني");
        } else {
            number++;
        }
        if (user_password.getText().toString().isEmpty()) {
            user_password.setError("أدخل كلمة المرور");
        } else {
            number++;
        }
        if (siginup_user_password2.getText().toString().isEmpty()) {
            siginup_user_password2.setError("الرجاء تأكيد كلمة المرور ");
        } else {
            number++;
        }
        return (number == 4);
    }

    private void resetFields() {
        user_name.setText("");
        user_email.setText("");
        user_password.setText("");
        siginup_user_password2.setText("");
        user_name.requestFocus();
    }

    private boolean checkSwitchingOnOfConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }







}

