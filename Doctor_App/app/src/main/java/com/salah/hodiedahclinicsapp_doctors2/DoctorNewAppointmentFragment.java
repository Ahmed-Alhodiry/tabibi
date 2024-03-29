package com.salah.hodiedahclinicsapp_doctors2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.AppointmentCollection;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.CollectionName;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.CollectionName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class DoctorNewAppointmentFragment extends Fragment  {

    private static final String ERROR_IN_ADD_APPOINTMENT ="error at adding an appointment" ;
    private int mYear, mMonth, mDay;
    TextView tvSelectedDate;
    androidx.appcompat.widget.AppCompatButton book_now;
    private     String clinicId;
    private EditText patient_name,patient_address,patient_number,patient_age;
    Spinner patient_gender;
    Global_Variables global_variables;

    androidx.cardview.widget.CardView card;
    LottieAnimationView lottie;
    private static final String TAG = DoctorsMainActivity.class.getName();

    Dialog loadingDialog;


    FirebaseFirestore db = FirebaseFirestore.getInstance() ;
 CollectionReference clinicCollection = db.collection(CollectionName.CLINICS.name().toString());
    CollectionReference appointmentCollection = db.collection(CollectionName.appointments.name());


    public DoctorNewAppointmentFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_doctor_new_appointment, container, false);
//        global_variables = (Global_Variables) getApplicationContext();

        patient_name = root.findViewById(R.id.patient_name);
        patient_address = root.findViewById(R.id.patient_address);
        patient_number = root.findViewById(R.id.patient_number);
        patient_age = root.findViewById(R.id.patient_age);
        patient_gender = root.findViewById(R.id.personal_Info_SpinnerSpecialize);
        book_now = root.findViewById(R.id.book_now_btn);

        tvSelectedDate = root.findViewById(R.id.tvSelectedDate);
        tvSelectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (dayOfMonth < mDay && monthOfYear < mMonth || dayOfMonth < mDay && monthOfYear == mMonth || year < mYear || year > mYear && monthOfYear > mMonth) {
                            Snackbar snackbar = Snackbar.make(root.findViewById(R.id.dr_add_new_appointment_container),"Error, Wrong Date",Snackbar.LENGTH_LONG)
                                    .setAction("Change it", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            tvSelectedDate.callOnClick();
                                        }
                                    });
                            snackbar.show();
                        }
                        else {
                            tvSelectedDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        Spinner spinner = (Spinner) root.findViewById(R.id.personal_Info_SpinnerSpecialize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
        R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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

                ){
                    Toasty.error(getContext(), "Sorry!! your Name must'nt have numbers or signs", Toast.LENGTH_SHORT).show();
                    patient_name.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        book_now.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(checkFields())
              {
                  if(checkSwitchingOnOfConnection()) {
                      addAppointment();
                  }
                  else {
                      Snackbar snackbar = Snackbar.make(root.findViewById(R.id.loginContainer), "!No internet Connection", Snackbar.LENGTH_LONG)
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
                    Toasty.error(getActivity().getApplicationContext()  , "Please Fill All Fields to Save The Appointment" ,Toast.LENGTH_SHORT).show();
                }

            }});

        return root;
    }

    private void addAppointment() {

        loadingDialog = new Dialog(requireActivity());
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();

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

        AppointmentCollection appointment = new AppointmentCollection(patient, false, false, "himself", global_variables.getDR_NAME(), dateAppointment);

        appointmentCollection.add(appointment).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
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
                        //OpenDialog1();
                        Toast.makeText(requireActivity(), "Added Successfully ^_^", Toast.LENGTH_SHORT).show();

                    }
                }, 2000);

            }
        });

    }

    private boolean checkFields()
    {
        int number=0 ;
        if (patient_name.getText().toString().isEmpty()){
        patient_name.setError("Please Enter Patient Name");
       }
        else
        {
            number++;
        }
     if(patient_address.getText().toString().isEmpty()){
         patient_address.setError("Please Enter Patient address");   }
     else
     {
         number++ ;
     }
    if(patient_number.getText().toString().isEmpty()){
        patient_number.setError("Please Enter Patient number");   }
    else
    {
        number++;
    }
    if(patient_age.getText().toString().isEmpty()){
        patient_age.setError("Please Enter Patient age");   }
    else
    {
        number++  ;
    }
     if(patient_gender.getSelectedItem().toString().equals("Select Gender")){
         //Toast.makeText(getContext(), "Please Select gender", Toast.LENGTH_SHORT).show();
     }
     else
     {
         number++;
     }
     if(tvSelectedDate.getText().toString().equals("Please Select Date")){
       // Toast.makeText(getContext(), "Please Select Booking Date", Toast.LENGTH_SHORT).show();
    }
     else{
         number++;

    }
        return (number==6);
    }

    private  void resetFields()
    {
        patient_name.setText("");
        patient_address.setText("");
        patient_number.setText("");
        patient_age.setText("");
        patient_gender.setSelection(0);
        tvSelectedDate.setText("Please Select Date");
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



}
