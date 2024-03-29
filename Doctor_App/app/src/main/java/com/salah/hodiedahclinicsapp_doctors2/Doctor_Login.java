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
import com.github.captain_miao.optroundcardview.OptRoundCardView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Doctor_Login extends Fragment {

    Button loginBtn;
    Dialog dialog;
    TextView doctor_EMAIL, password, forget_password;
    OptRoundCardView RoundCardView;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    float v;

    androidx.cardview.widget.CardView card;
    LottieAnimationView lottie;
    private static final String TAG = DoctorsMainActivity.class.getName();
    TextView title_txt;
    Dialog loadingDialog;



    private FirebaseAuth auth;
    private FirebaseAnalytics mFirebaseAnalytics;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_doctor__login, container, false);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(root.getContext());
        auth = FirebaseAuth.getInstance();

        doctor_EMAIL = root.findViewById(R.id.LOGIN_doctorEmail);
        password = root.findViewById(R.id.password);
        forget_password = root.findViewById(R.id.forget_password);
        RoundCardView = root.findViewById(R.id.optRoundCardView);


        dialog = new Dialog(container.getContext());
        loginBtn = root.findViewById(R.id.btn_login);

        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingDialog.setCanceledOnTouchOutside(false);
        title_txt = loadingDialog.findViewById(R.id.loading_title);
        title_txt.setText("Login...");



        doctor_EMAIL.setTranslationY(70);
        doctor_EMAIL.animate().translationY(0).alpha(1).setDuration(700).setStartDelay(70).start();
        password.setTranslationY(70);
        password.animate().translationY(0).alpha(1).setDuration(700).setStartDelay(70).start();
        forget_password.setTranslationY(70);
        forget_password.animate().translationY(0).alpha(1).setDuration(700).setStartDelay(70).start();
        loginBtn.setAlpha(v);
        loginBtn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkFields()) {
                    if (checkSwitchingOnOfConnection()) {
                        if (doctor_EMAIL.getText().toString().trim().matches(emailPattern)) {

                            loadingDialog.show();

                            auth.signInWithEmailAndPassword(doctor_EMAIL.getText().toString().trim(), password.getText().toString().trim())
                                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {

                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {


                                                if (!task.isSuccessful())
                                                {
                                                    if (password.length() < 6)
                                                    {
                                                        password.setError("password must be 6 at least");
                                                    }
                                                    else
                                                    {
                                                        loadingDialog.dismiss();
                                                        OpenDialog1();
                                                        Toasty.error(requireActivity(), "Authentication failed, check your email and password or sign up", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                                else
                                                {
                                                    lottie = loadingDialog.findViewById(R.id.lottie_img2);
                                                    card = loadingDialog.findViewById(R.id.card_loading_dialog);
                                                    card.setVisibility(View.GONE);
                                                    lottie.setVisibility(View.VISIBLE);
                                                    final Handler handler = new Handler(Looper.getMainLooper());
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            resetFields();
                                                            Intent intent = new Intent(requireActivity(), DoctorsMainActivity.class);
                                                            loadingDialog.dismiss();
                                                            startActivity(intent);
                                                            //requireActivity().finish();
                                                            Log.d(TAG, "run: Done");
                                                        }
                                                    }, 500);

                                                }
                                            }



                                    });

                        } else {
                            doctor_EMAIL.setError("Please Enter Correct Email");
                            doctor_EMAIL.requestFocus();
                        }
                    } else {
                        Snackbar snackbar = Snackbar.make(root.findViewById(R.id.loginContainer), "!No internet Connection", Snackbar.LENGTH_LONG)
                                .setAction("CHECK IT", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                    }
                                });
                        snackbar.show();
                    }
                } else {
                    Toasty.error(getActivity().getApplicationContext(), "Please Fill All Fields to Login", Toast.LENGTH_SHORT).show();
                }

            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), com.salah.hodiedahclinicsapp_doctors2.Forget_Password.class);
                startActivity(intent);
            }
        });


        return root;
    }


    private void OpenDialog1() {
        dialog.setContentView(R.layout.dialog_login_wrong_data);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageViewClose = dialog.findViewById(R.id.Error_icon);
        Button btnOk = dialog.findViewById(R.id.btn_ok);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getContext(), "Dialog Close", Toast.LENGTH_SHORT).show();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        resetFields();

    }

    private boolean checkFields() {
        int number = 0;
        if (doctor_EMAIL.getText().toString().isEmpty()) {
            doctor_EMAIL.setError("Please Enter User Name");
        } else {
            number++;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Please Enter Password");
        } else {
            number++;
        }
        return (number == 2);
    }

    private void resetFields() {
        doctor_EMAIL.setText("");
        password.setText("");
        doctor_EMAIL.requestFocus();
    }

    private boolean checkSwitchingOnOfConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }


}
