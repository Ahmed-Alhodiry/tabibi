package com.salah.hodiedahclinicsapp_doctors2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.CollectionName;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.CollectionName;

import java.util.Collection;


public class Doctor_Signup extends Fragment {

    Button signUpBtn;



    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference clinic = db.collection(CollectionName.CLINICS.name().toString());
    TextView doctor_name,clinic_name,doctor_email,doctor_phoneNumber,cv_path;
    float v =0;
    Dialog dialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_doctor__signup, container,false);

        dialog=new Dialog(container.getContext());

        signUpBtn = root.findViewById(R.id.btn_signup);
        doctor_name = root.findViewById(R.id.doctor_name);
        clinic_name = root.findViewById(R.id.Clinic_Info_ClinicName);
        doctor_email = root.findViewById(R.id.doctor_email);
        doctor_phoneNumber = root.findViewById(R.id.doctor_phoneNumber);
        cv_path = root.findViewById(R.id.cv_path);

        doctor_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (
                        doctor_name.getText().toString().contains("1")
                                || doctor_name.getText().toString().contains("2")
                                || doctor_name.getText().toString().contains("3")
                                || doctor_name.getText().toString().contains("4")
                                || doctor_name.getText().toString().contains("5")
                                || doctor_name.getText().toString().contains("6")
                                || doctor_name.getText().toString().contains("7")
                                || doctor_name.getText().toString().contains("8")
                                || doctor_name.getText().toString().contains("9")
                                || doctor_name.getText().toString().contains("0")
                                || doctor_name.getText().toString().contains("@")
                                || doctor_name.getText().toString().contains("#")
                                || doctor_name.getText().toString().contains("$")
                                || doctor_name.getText().toString().contains(">")
                                || doctor_name.getText().toString().contains("<")
                                || doctor_name.getText().toString().contains("?")
                                || doctor_name.getText().toString().contains("&")
                                || doctor_name.getText().toString().contains("*")

                ){
                    Toast.makeText(getContext(), "Sorry!! your Name must'nt have numbers or signs", Toast.LENGTH_SHORT).show();
                    doctor_name.setText("");
                    doctor_name.setFocusable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        clinic_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(   clinic_name.getText().toString().contains("1")
                        || clinic_name.getText().toString().contains("2")
                        || clinic_name.getText().toString().contains("3")
                        || clinic_name.getText().toString().contains("4")
                        || clinic_name.getText().toString().contains("5")
                        || clinic_name.getText().toString().contains("6")
                        || clinic_name.getText().toString().contains("7")
                        || clinic_name.getText().toString().contains("8")
                        || clinic_name.getText().toString().contains("9")
                        || clinic_name.getText().toString().contains("0")
                        || clinic_name.getText().toString().contains("@")
                        || clinic_name.getText().toString().contains("#")
                        || clinic_name.getText().toString().contains("$")
                        || clinic_name.getText().toString().contains(">")
                        || clinic_name.getText().toString().contains("<")
                        || clinic_name.getText().toString().contains("?")
                        || clinic_name.getText().toString().contains("&")
                        || clinic_name.getText().toString().contains("*")
                ){

                    Toast.makeText(getContext(), "Sorry!! Clinic Name must'nt have numbers or signs", Toast.LENGTH_SHORT).show();
                    clinic_name.setText("");
                    clinic_name.setFocusable(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(checkFields())
                {
                    if(checkSwitchingOnOfConnection())
                    {
                        if(doctor_email.getText().toString().trim().matches(emailPattern))
                        {
                            sendMail();
                        }
                        else
                        {
                            doctor_email.setError("Please Enter Correct Email");
                            doctor_email.requestFocus();
                        }
                    }
                    else
                    {
                        Snackbar snackbar = Snackbar.make(root.findViewById(R.id.signup_container), "!No internet Connection", Snackbar.LENGTH_LONG)
                                .setAction("CHECK IT", new View.OnClickListener() {
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
                    Toast.makeText(getActivity().getApplicationContext()  , "Please Fill All Fields" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    private void sendMail() {
        String mail = doctor_email.getText().toString().trim();
        String subject = "New Doctor Wants to Register in Hodiedah Clinics App";
        String message =
                "Information about this doctor : \n" +"Doctor Name : "+doctor_name.getText().toString().trim()+
                "\n Clinic Name : "+clinic_name.getText().toString().trim()+"\n Doctor Email : "+doctor_email.getText().toString().trim() +
                "\n Doctor Phone Number : "+doctor_phoneNumber.getText().toString().trim();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this.getContext(),mail,subject,message);
        javaMailAPI.execute();
        resetFields();
        OpenDialog1();

    }

    private boolean checkFields()
    {
        int number=0 ;
        if (doctor_name.getText().toString().isEmpty()){
            doctor_name.setError("Please Enter Doctor Name");
        }
        else
        {
            number++;
        }
        if(clinic_name.getText().toString().isEmpty()){
            clinic_name.setError("Please Enter Clinic Name");   }
        else
        {
            number++ ;
        }
        if(doctor_email.getText().toString().isEmpty()){
            doctor_email.setError("Please Enter Doctor Email");   }
        else
        {
            number++;
        }
        if(doctor_phoneNumber.getText().toString().isEmpty()){
            doctor_phoneNumber.setError("Please Enter Your Number");   }
        else
        {
            number++  ;
        }
        return (number==4);
    }

    private  void resetFields()
    {
        doctor_name.setText("");
        clinic_name.setText("");
        doctor_email.setText("");
        doctor_phoneNumber.setText("");
        doctor_name.requestFocus();
    }


    private  boolean checkSwitchingOnOfConnection()
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true ;
        }
        else {
            return  false ;
        }
    }


    private void OpenDialog1()
    {
        dialog.setContentView(R.layout.dialog_signup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LottieAnimationView imageViewClose = dialog.findViewById(R.id.Error_icon);
        ImageButton doctor_cv = dialog.findViewById(R.id.doctor_cv);
        dialog.show();

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                resetFields();
                Toast.makeText(getContext(), "you will not be added to this app until sending CV", Toast.LENGTH_SHORT).show();
            }
        });

        doctor_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String[] to = {"salahdoos77@gmail.com"};
                    Intent send_email = new Intent(Intent.ACTION_SEND);
                    send_email.putExtra(Intent.EXTRA_EMAIL, to);
                    send_email.putExtra(Intent.EXTRA_SUBJECT, "sharingConnection");
                    send_email.putExtra(Intent.EXTRA_TEXT, "السلام عليكم ورحمة الله وبركاته الرجاء ارفاق سيرتك الذاتية");
                    send_email.setType("message/rfc822");
                    send_email.createChooser(send_email, "Send Email");
                    getContext().startActivity(send_email);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "عفواً لا يوجد تطبيق مراسلة في تلفونك !", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
                resetFields();
            }
        });


    }



}

