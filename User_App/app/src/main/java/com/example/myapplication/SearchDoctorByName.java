package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.myapplication.FirebaseCollection.ClinicCollection;
import com.example.myapplication.FirebaseCollection.CollectionName;

import java.util.ArrayList;
import java.util.Objects;

public class SearchDoctorByName extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolBar ;

    RecyclerViewDoctorAdapter adapter;

    ProgressBar progressBar ;

    RecyclerView  rvDoctor;

    OnClickListener clickListener;

    Global_Variables global_variables;

    private final static String ALL_SPECIALIZE="ALL_SPECIALIZE";

    private final static String SPECIALIZE="SPECIALIZE";

    ArrayList<ClinicCollection> clinics = new ArrayList<>();
    Intent intent = new Intent();
    private String specifiedSearch= null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference  clinicRef =db.collection(CollectionName.CLINICS.name().toString()); //db.collection("CLINICS");
    TextView dr_name,dr_specialize,dr_ClinicName,dr_booking_price,dr_email,dr_PhoneNumber,
            dr_education_degree,dr_address, dr_Kuraimi_account,ClinicPosition_latitude,ClinicPosition_longitude , tv_noData , tv_noInternetConnection ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor_by_name);

        global_variables = new Global_Variables();

        intent = getIntent();
        specifiedSearch= intent.getStringExtra(SPECIALIZE);//"ALL_SPECIALIZE";

        toolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
        toolBar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        Objects.requireNonNull(toolBar.getOverflowIcon()).setColorFilter(Color.WHITE , PorterDuff.Mode.SRC_ATOP);

        getSupportActionBar().setTitle("اكتب هنا للبحث");

        rvDoctor =  findViewById(R.id.rv_doctors);
        progressBar = findViewById(R.id.progress_while_loading);

        dr_name = findViewById(R.id.tv_doctor_name);
        dr_specialize = findViewById(R.id.tv_doctor_specialize);
        dr_ClinicName = findViewById(R.id.tv_clinic_name);
        dr_booking_price = findViewById(R.id.tv_booking_price);
        dr_email = findViewById(R.id.tv_doctor_email);
        dr_PhoneNumber = findViewById(R.id.tv_doctor_phone);
        dr_education_degree = findViewById(R.id.tv_Doctor_EducationDegree);
        dr_address = findViewById(R.id.tv_doctor_address);
        dr_Kuraimi_account = findViewById(R.id.tv_doctor_KuraimiAccount);
        ClinicPosition_longitude = findViewById(R.id.tv_position_longitude);
        ClinicPosition_latitude = findViewById(R.id.tv_position_latitude);
        tv_noData = findViewById(R.id.no_data_tv);
        tv_noInternetConnection = findViewById(R.id.no_connection_tv);

        clickListener = new OnClickListener() {
            @Override
            public void onClick(int index) {
                ClinicCollection clinicCollection = clinics.get(index);
                Intent intent = new Intent(getBaseContext(), Doctor_Details.class);
                intent.putExtra("doctor_Name", clinics.get(index).getDoctor().get(CollectionName.Fields.name.name().toString()).toString());
                intent.putExtra("clinic_Name", clinics.get(index).getClinicName().toString());
                intent.putExtra("specialize",clinics.get(index).getTypeOfClinic().toString());
                intent.putExtra("booking_price",  clinics.get(index).getPrice());
                intent.putExtra("doctor_email", clinicCollection.getDoctor().get(CollectionName.Fields.email.name().toString()).toString());
                intent.putExtra("doctor_phone", clinics.get(index).getDoctor().get(CollectionName.Fields.telephones.name().toString()).toString());
                intent.putExtra("education_degree", clinicCollection.getDoctor().get(CollectionName.Fields.educationDegree.name().toString()).toString());
                intent.putExtra("address",clinics.get(index).getClinic_Address().get(CollectionName.Fields.street.name().toString()).toString());
                intent.putExtra("kuraimi_account",clinicCollection.getDoctor().get(CollectionName.Fields.kuraimiAccount.name().toString()).toString());
                intent.putExtra("ClinicPosition_latitude",clinics.get(index).getClinic_Address().get(CollectionName.Fields.latitude.name().toString()).toString());
                intent.putExtra("ClinicPosition_longitude", clinics.get(index).getClinic_Address().get(CollectionName.Fields.longitude.name().toString()).toString());
                startActivity(intent);

                Intent intent2 = new Intent(getBaseContext(),Doctor_Details.class);
                global_variables.setDR_NAME(clinics.get(index).getDoctor().get(CollectionName.Fields.name.name().toString()).toString());
                global_variables.setDR_CLINIC_NAME(clinics.get(index).getClinicName().toString());
                global_variables.setDR_SPECIALIZE(clinics.get(index).getTypeOfClinic().toString());
                global_variables.setDR_BOOKING_PRICE(clinics.get(index).getPrice().toString());
                global_variables.setDR_EMAIL(clinicCollection.getDoctor().get(CollectionName.Fields.email.name().toString()).toString());
                global_variables.setDR_PHONE_NUMBER(clinics.get(index).getDoctor().get(CollectionName.Fields.telephones.name().toString()).toString());
                global_variables.setDR_EDUCATION_DEGREE(clinicCollection.getDoctor().get(CollectionName.Fields.educationDegree.name().toString()).toString());
                global_variables.setDR_ADDRESS(clinics.get(index).getClinic_Address().get(CollectionName.Fields.street.name().toString()).toString());
                global_variables.setKuraimi_account(clinicCollection.getDoctor().get(CollectionName.Fields.kuraimiAccount.name().toString()).toString());
                global_variables.setClinicPosition_latitude(clinics.get(index).getClinic_Address().get(CollectionName.Fields.latitude.name().toString()).toString());
                global_variables.setClinicPosition_longitude(clinics.get(index).getClinic_Address().get(CollectionName.Fields.longitude.name().toString()).toString());
                //startActivity(intent2);




            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (checkSwitchingOnOfConnection())
        {
            tv_noInternetConnection.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            getAllDoctor();
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            tv_noInternetConnection.setVisibility(View.VISIBLE);
            Snackbar snackbar = Snackbar.make(findViewById(R.id.searchByName_containerLayout), "لايوجد إنترنت!", Snackbar.LENGTH_LONG)
                    .setAction("فتح الشبكة", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                            onStart();
                        }
                    });
            snackbar.show();

            tv_noInternetConnection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    onStart();
                }
            });
        }

    }

    private void initRV() {
        progressBar.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerViewDoctorAdapter(clinics , getBaseContext() , clickListener);
        rvDoctor.setLayoutManager(layoutManager);
        rvDoctor.setAdapter(adapter);
        rvDoctor.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        MenuItem searchItem ;
        searchItem = menu.findItem(R.id.search_item);
        searchItem.setIcon(R.drawable.ic_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                if (adapter == null)
                    return  false;
                adapter.getFilter().filter(s.toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                     if(adapter == null)
                         return false;
                    adapter.getFilter().filter(s.toString());
                return false;
            }
        });
        return  true;
    }

    private  void getAllDoctor() {

            if (specifiedSearch.equals(ALL_SPECIALIZE)) {
                clinicRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            tv_noData.setVisibility(View.GONE);
                            tv_noInternetConnection.setVisibility(View.GONE);
                            progressBar.setVisibility(View.VISIBLE);
                            clinics.clear();
                            adapter = null;
                            for (QueryDocumentSnapshot q : queryDocumentSnapshots) {
                                ClinicCollection clinic = q.toObject(ClinicCollection.class);
                                clinic.setDocumentId(q.getId());
                                clinics.add(clinic);
                                clinic = null;
                            }
                            initRV();
                        } else {
                            tv_noData.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getBaseContext(), "لاتوجد أي بيانات", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
            else
            {
                clinicRef.whereEqualTo(CollectionName.Fields.typeOfClinic.name().toString() , specifiedSearch).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            tv_noData.setVisibility(View.GONE);
                            tv_noInternetConnection.setVisibility(View.GONE);
                            progressBar.setVisibility(View.VISIBLE);
                            clinics.clear();
                            adapter = null;
                            for (QueryDocumentSnapshot q : queryDocumentSnapshots) {
                                ClinicCollection clinic = q.toObject(ClinicCollection.class);
                                clinic.setDocumentId(q.getId());
                                clinics.add(clinic);
                                clinic = null;
                            }
                            initRV();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            tv_noData.setVisibility(View.VISIBLE);
                            Toast.makeText(getBaseContext(), "There isn't any clinics for searching", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

    }

    private boolean checkSwitchingOnOfConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        } else {
            return false;
        }
    }


}