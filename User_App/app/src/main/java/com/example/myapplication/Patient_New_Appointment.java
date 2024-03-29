package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.myapplication.FirebaseCollection.AppointmentCollection;
import com.example.myapplication.FirebaseCollection.CollectionName;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class Patient_New_Appointment extends AppCompatActivity {

    View container;
    Global_Variables global_variables;
    androidx.appcompat.widget.AppCompatButton book_now_btn;
    EditText patient_name, patient_address, patient_number, patient_age;
    Spinner patient_gender;
    TextView tvSelectedDate;
    Dialog dialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference clinicCollection = db.collection(CollectionName.CLINICS.name());
    CollectionReference appointmentCollection = db.collection(CollectionName.appointments.name());

    private int mYear, mMonth, mDay;
    private String K_Account = "";
    private String doctorEmail = "";

    androidx.cardview.widget.CardView card;
    LottieAnimationView lottie;
    private static final String TAG = Patient_New_Appointment.class.getName();

    Dialog loadingDialog;


    public Patient_New_Appointment() {
        //required
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__new__appointment);
        //global_variables = (Global_Variables) getApplicationContext();

        doctorEmail = getIntent().getStringExtra("doctorEmail");


        container = findViewById(R.id.container2);
        patient_name = findViewById(R.id.patient_name);
        patient_address = findViewById(R.id.patient_address);
        patient_number = findViewById(R.id.patient_number);
        patient_age = findViewById(R.id.patient_age);
        patient_gender = findViewById(R.id.patient_gender);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        book_now_btn = findViewById(R.id.book_now_btn);

        K_Account = "3052469014";

        dialog = new Dialog(findViewById(R.id.container2).getContext());

        tvSelectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(container.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("ResourceAsColor")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if (dayOfMonth < mDay && monthOfYear < mMonth || dayOfMonth < mDay && monthOfYear == mMonth || year < mYear || year > mYear || monthOfYear > mMonth) {
                                    Snackbar snackbar = Snackbar.make(findViewById(R.id.container2), "خطأ! في تاريخ الحجز", Snackbar.LENGTH_LONG)
                                            .setAction("تعديل التاريخ", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    tvSelectedDate.callOnClick();
                                                }
                                            });
                                    snackbar.show();
                                } else {
                                    tvSelectedDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        patient_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (
                        patient_name.getText().toString().contains("1")
                                || patient_name.getText().toString().contains("2")
                                || patient_name.getText().toString().contains("3")
                                || patient_name.getText().toString().contains("4")
                                || patient_name.getText().toString().contains("5")
                                || patient_name.getText().toString().contains("6")
                                || patient_name.getText().toString().contains("7")
                                || patient_name.getText().toString().contains("8")
                                || patient_name.getText().toString().contains("9")
                                || patient_name.getText().toString().contains("0")
                                || patient_name.getText().toString().contains("@")
                                || patient_name.getText().toString().contains("#")
                                || patient_name.getText().toString().contains("$")
                                || patient_name.getText().toString().contains(">")
                                || patient_name.getText().toString().contains("<")
                                || patient_name.getText().toString().contains("?")
                                || patient_name.getText().toString().contains("&")
                                || patient_name.getText().toString().contains("*")

                ) {
                    Toasty.error(getApplicationContext(), "المعذرة يجب أن لايحتوي الإسم على أرقام أو رموز", Toast.LENGTH_SHORT).show();
                    patient_name.setText("");
                    patient_name.setFocusable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.patient_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        book_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (checkFields()) {
                    if (checkSwitchingOnOfConnection()) {
                        //this method is used to add new appointment
                        addAppointment();
                        //bookNow();

                    } else {
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.login_containerLayout), "لايوجد انترنت!", Snackbar.LENGTH_LONG)
                                .setAction("فتح الشبكة", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                    }
                                });
                        snackbar.show();
                    }

                } else {

                    Toasty.error(getApplication().getApplicationContext(), "يرجى ملئ جميع الحقول,", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void OpenDialog1() {
        dialog.setContentView(R.layout.dialog_book_now);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageViewClose = dialog.findViewById(R.id.success_icon);
        Button btnOk = dialog.findViewById(R.id.btn_ok);
        TextView Doctor_Kuraimi_Account = dialog.findViewById(R.id.accountNumber);
        TextView copy = dialog.findViewById(R.id.copy);
        Doctor_Kuraimi_Account.setText(K_Account);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String text = Doctor_Kuraimi_Account.getText().toString();
                ClipData myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getApplicationContext(), "تم النسخ",
                        Toast.LENGTH_SHORT).show();
            }
        });

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "انتقل لتطبيق الكريمي", Toast.LENGTH_SHORT).show();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                PackageManager pm = getPackageManager();
                try {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    PackageInfo info = pm.getPackageInfo("com.kuraimi", PackageManager.GET_META_DATA);
                    waIntent.setPackage("com.whatsapp");
                    startActivity(Intent.createChooser(waIntent, "Share with"));
                } catch (PackageManager.NameNotFoundException e) {
                    Toasty.error(getApplicationContext(), "تطبيق الكريمي غير مثبت في جهازك ,يرجى التسديد عند حضور الموعد", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }


    private void notification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            Bitmap bitmap_image = BitmapFactory.decodeResource(getResources(), R.drawable.doctor_img);
            Bitmap big_bitmap_image = BitmapFactory.decodeResource(getResources(), R.drawable.doctor_img);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                    .setContentTitle("تم ارسال حجزك بنجاح نتمنى لك الشفاء العاجل ^_^")
                    .setContentText("يرجى دفع مبلغ الحجز للحساب :" + "\n" + K_Account)
                    .setSmallIcon(IconCompat.createWithBitmap(bitmap_image))
                    .setLargeIcon(big_bitmap_image)
                    .setAutoCancel(true);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.notification);
            mediaPlayer.start();
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this, Patient_New_Appointment.class), PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            managerCompat.notify(999, builder.build());
        }

    }


    private boolean checkFields() {
        int number = 0;
        if (patient_name.getText().toString().isEmpty()) {
            patient_name.setError("أدخل اسم المريض");
        } else {
            number++;
        }
        if (patient_address.getText().toString().isEmpty()) {
            patient_address.setError("أدخل عنوان المريض");
        } else {
            number++;
        }
        if (patient_number.getText().toString().isEmpty()) {
            patient_number.setError("أدخل رقم التلفون");
        } else {
            number++;
        }
        if (patient_age.getText().toString().isEmpty()) {
            patient_age.setError("أدخل عمر المريض");
        } else {
            number++;
        }
        if (patient_gender.getSelectedItem().toString().equals("حدد الجنس")) {
            //Toast.makeText(getContext(), "Please Select gender", Toast.LENGTH_SHORT).show();
        } else {
            number++;
        }
        if (tvSelectedDate.getText().toString().equals("الرجاء تحديد تاريخ الموعد")) {
            // Toast.makeText(getContext(), "Please Select Booking Date", Toast.LENGTH_SHORT).show();
        } else {
            number++;
        }
        return (number == 6);
    }

    private void resetFields() {
        patient_name.setText("");
        patient_address.setText("");
        patient_number.setText("");
        patient_age.setText("");
        patient_gender.setSelection(0);
        tvSelectedDate.setText("الرجاء تحديد تاريخ الموعد");
        patient_name.requestFocus();
    }

    private boolean checkSwitchingOnOfConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    private void addAppointment() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.control_loading_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        String patientName = patient_name.getText().toString();
        String patientAddress = patient_address.getText().toString();
        String patientNumber = patient_number.getText().toString();
        String patientAge = patient_age.getText().toString();

        String gender = patient_gender.getSelectedItem().toString();
        String dateAppointment = tvSelectedDate.getText().toString();
        Map<String, Object> patient = new HashMap<>();
        patient.put(CollectionName.Fields.name.name(), patientName);
        patient.put(CollectionName.Fields.address.name(), patientAddress);
        patient.put(CollectionName.Fields.number.name(), patientNumber);
        patient.put(CollectionName.Fields.age.name(), patientAge);
        patient.put(CollectionName.Fields.gender.name(), gender);


        AppointmentCollection appointment = new AppointmentCollection(patient, false, false, "himself", doctorEmail, dateAppointment);

        appointmentCollection.add(appointment).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                resetFields();

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
                }, 500);

                final Handler handler2 = new Handler(Looper.getMainLooper());
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        //notification();
                        sendNotification("^_^ ........ ^_^"," تم اضافة حجزك الطبي بنجاح ");
                        OpenDialog1();
                        Toasty.success(getApplicationContext(), "تمت الاضافة بنجاح ^_^!", Toast.LENGTH_SHORT, true).show();
                    }
                }, 2000);

            }
        });



    }


    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), Doctor_Details.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void bookNow() {

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();

        resetFields();

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 1000s
                lottie = loadingDialog.findViewById(R.id.lottie_img2);
                card = loadingDialog.findViewById(R.id.card_loading_dialog);
                card.setVisibility(View.GONE);
                lottie.setVisibility(View.VISIBLE);
            }
        }, 500);

        final Handler handler2 = new Handler(Looper.getMainLooper());
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismiss();
                //notification();
                Toasty.success(Patient_New_Appointment.this,"^_^ تم الخجز بنجاح",Toast.LENGTH_SHORT,true).show();
                
                OpenDialog1();
                //Toast.makeText(Patient_New_Appointment.this, "Added Successfully ^_^", Toast.LENGTH_SHORT).show();

            }
        }, 2000);

    }


    public void sendNotification (String message, String title ){

        Intent intent = new Intent(getApplicationContext(), Patient_New_Appointment.class);
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