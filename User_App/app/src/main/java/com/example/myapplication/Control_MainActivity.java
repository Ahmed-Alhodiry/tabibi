package com.example.myapplication;

import android.Manifest;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.myapplication.FirebaseCollection.ClinicCollection2;
import com.example.myapplication.FirebaseCollection.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Control_MainActivity extends AppCompatActivity {

    EditText tv_dr_name, tv_dr_email, tv_dr_educationDegree, tv_clinicName, tv_dr_phoneNumber, tv_dr_bookingPrice, tv_dr_kuraimiAccount, tv_address, tv_longitude, tv_latitude;
    Spinner specializes;
    androidx.appcompat.widget.AppCompatButton btn_AddNewDoctor;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference clinic = db.collection(Collection.CLINICS.name());

    androidx.cardview.widget.CardView card;
    LottieAnimationView lottie;

    private static final String TAG = Control_MainActivity.class.getName();

    Dialog dialog;

    private long time;
    private Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_activity_main);

        specializes = (Spinner) findViewById(R.id.tv_dr_specialize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.specializes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specializes.setAdapter(adapter);

        tv_dr_name = findViewById(R.id.tv_dr_name);
        tv_dr_email = findViewById(R.id.tv_dr_email);
        tv_dr_educationDegree = findViewById(R.id.tv_dr_educationDegree);
        tv_clinicName = findViewById(R.id.tv_clinicName);
        tv_dr_phoneNumber = findViewById(R.id.tv_dr_phoneNumber);
        tv_dr_bookingPrice = findViewById(R.id.tv_dr_bookingPrice);
        tv_dr_kuraimiAccount = findViewById(R.id.tv_dr_kuraimiAccount);
        tv_address = findViewById(R.id.tv_address);
        tv_longitude = findViewById(R.id.tv_longitude);
        tv_latitude = findViewById(R.id.tv_latitude);
        btn_AddNewDoctor = findViewById(R.id.btn_AddNewDoctor);


        tv_dr_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (
                        tv_dr_name.getText().toString().contains("1")
                                || tv_dr_name.getText().toString().contains("2")
                                || tv_dr_name.getText().toString().contains("3")
                                || tv_dr_name.getText().toString().contains("4")
                                || tv_dr_name.getText().toString().contains("5")
                                || tv_dr_name.getText().toString().contains("6")
                                || tv_dr_name.getText().toString().contains("7")
                                || tv_dr_name.getText().toString().contains("8")
                                || tv_dr_name.getText().toString().contains("9")
                                || tv_dr_name.getText().toString().contains("0")
                                || tv_dr_name.getText().toString().contains("@")
                                || tv_dr_name.getText().toString().contains("#")
                                || tv_dr_name.getText().toString().contains("$")
                                || tv_dr_name.getText().toString().contains(">")
                                || tv_dr_name.getText().toString().contains("<")
                                || tv_dr_name.getText().toString().contains("?")
                                || tv_dr_name.getText().toString().contains("&")
                                || tv_dr_name.getText().toString().contains("*")

                ) {
                    Toast.makeText(getApplicationContext(), "Sorry!! your Name must'nt have numbers or signs", Toast.LENGTH_SHORT).show();
                    tv_dr_name.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        btn_AddNewDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkFields()) {
                    if (checkSwitchingOnOfConnection()) {
                        if (tv_dr_email.getText().toString().trim().matches(emailPattern)) {
                            //here we will add method
                            addData();
                        } else {
                            tv_dr_email.setError("Please Enter Correct Email");
                            tv_dr_email.requestFocus();
                        }
                    } else {
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.container), "!No internet Connection", Snackbar.LENGTH_LONG)
                                .setAction("CHECK IT", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                    }
                                });
                        snackbar.show();
                    }
                } else {
                    Toast.makeText(getBaseContext().getApplicationContext(), "Please Fill All Fields to Add New Doctor", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void resetFields() {
        tv_dr_name.setText("");
        tv_dr_email.setText("");
        tv_dr_educationDegree.setText("");
        tv_clinicName.setText("");
        tv_dr_phoneNumber.setText("");
        tv_dr_bookingPrice.setText("");
        tv_dr_kuraimiAccount.setText("");
        tv_address.setText("");
        tv_longitude.setText("");
        tv_latitude.setText("");
        specializes.setSelection(0);
    }

    private boolean checkFields() {
        int number = 0;
        if (tv_dr_name.getText().toString().isEmpty()) {
            tv_dr_name.setError("Please Enter Doctor Name");
        } else {
            number++;
        }
        if (tv_dr_email.getText().toString().isEmpty()) {
            tv_dr_email.setError("Please Enter Doctor Email");
        } else {
            number++;
        }
        if (tv_dr_educationDegree.getText().toString().isEmpty()) {
            tv_dr_educationDegree.setError("Please Enter Doctor Education Degree");
        } else {
            number++;
        }
        if (tv_clinicName.getText().toString().isEmpty()) {
            tv_clinicName.setError("Please Enter Clinic Name");
        } else {
            number++;
        }
        if (tv_dr_phoneNumber.getText().toString().isEmpty()) {
            tv_dr_phoneNumber.setError("Please Enter Phone Number");
        } else {
            number++;
        }
        if (tv_dr_bookingPrice.getText().toString().isEmpty()) {
            tv_dr_bookingPrice.setError("Please Enter Booking Price");
        } else {
            number++;
        }
        if (tv_dr_kuraimiAccount.getText().toString().isEmpty()) {
            tv_dr_kuraimiAccount.setError("Please Enter Doctor Kuraimi Number");
        } else {
            number++;
        }
        if (tv_address.getText().toString().isEmpty()) {
            tv_address.setError("Please Enter The Address");
        } else {
            number++;
        }
        if (tv_longitude.getText().toString().isEmpty()) {
            tv_longitude.setError("Please Enter Longitude of Position");
        } else {
            number++;
        }
        if (tv_latitude.getText().toString().isEmpty()) {
            tv_latitude.setError("Please Enter Latitude of Position");
        } else {
            number++;
        }
        if (specializes.getSelectedItem().toString().equals("التخصص الطبي")) {
            //Toast.makeText(getContext(), "Please Select gender", Toast.LENGTH_SHORT).show();
        } else {
            number++;
        }

        return (number == 11);
    }

    private boolean checkSwitchingOnOfConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    private void addData() {


        dialog = new Dialog(this);
        dialog.setContentView(R.layout.control_loading_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        String clinicName = tv_clinicName.getText().toString();
        String typeOfClinic = specializes.getSelectedItem().toString().toLowerCase();
        String price = tv_dr_bookingPrice.getText().toString();

        String number = tv_dr_phoneNumber.getText().toString();
        ArrayList<String> telephones = new ArrayList<>();
        telephones.add(number);

        // add doctor details

        Map<String, Object> doctor = new HashMap<>();
        Map<String, Object> Clinic_Address = new HashMap<>();
        Clinic_Address.put(Collection.Fields.latitude.name(), tv_latitude.getText().toString());
        Clinic_Address.put(Collection.Fields.longitude.name(), tv_longitude.getText().toString());
        Clinic_Address.put(Collection.Fields.street.name(), tv_address.getText().toString());

        doctor.put(Collection.Fields.name.name(), tv_dr_name.getText().toString());
        doctor.put(Collection.Fields.kuraimiAccount.name(), tv_dr_kuraimiAccount.getText().toString());
        doctor.put(Collection.Fields.email.name(), tv_dr_email.getText().toString());
        doctor.put(Collection.Fields.educationDegree.name(), tv_dr_educationDegree.getText().toString());
        doctor.put(Collection.Fields.telephones.name(), telephones);

        ClinicCollection2 clinicCollection = new ClinicCollection2(Clinic_Address, clinicName, doctor, price, typeOfClinic);
        clinic.add(clinicCollection).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Log.e("have added successfully", String.valueOf(true));
                        resetFields();

                        lottie = dialog.findViewById(R.id.lottie_img2);
                        card = dialog.findViewById(R.id.card_loading_dialog);
                        card.setVisibility(View.GONE);
                        lottie.setVisibility(View.VISIBLE);


                        final Handler handler2 = new Handler(Looper.getMainLooper());
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 3000s
                                dialog.dismiss();
                                sendNotification("^_^ ........ ^_^"," تم اضافة الدكتور "+tv_dr_name.getText().toString()+"  بنجاح ");
                                Log.d(TAG, "run: Done");
                            }
                        }, 2000);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("error in addition", e.getMessage());
                    }
                });
    }

public void sendNotification (String message, String title ){

    Intent intent = new Intent(getApplicationContext(), Control_MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
            PendingIntent.FLAG_IMMUTABLE);


    String channelId = "some_channel_id";
    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.Builder notificationBuilder =
            new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_logo)
//                        .setContentTitle(getString(R.string.app_name)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

    NotificationManager notificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    // Since android Oreo notification channel is needed.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationChannel channel = new NotificationChannel(channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }

    assert notificationManager != null;
    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
}
}




