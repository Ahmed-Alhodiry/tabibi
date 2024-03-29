package com.salah.hodiedahclinicsapp_doctors2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SureAppointmentsFragment extends Fragment implements SearchView.OnQueryTextListener{

    androidx.appcompat.widget.Toolbar toolBar ;
    SureAppointmentsAdapter adapter  ;
    RecyclerView rvSureAppointment ;
    ArrayList<SureAppointments_Data> data = new ArrayList<>();
    OnClickListener clickListener;
    SearchView sv_sure;

    public SureAppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_sure_appointments, container, false);

        sv_sure = view.findViewById(R.id.sure_SearchView);
        clickListener = new OnClickListener() {
            @Override
            public void onClick(int index , String name) {
                //  Toast.makeText(getContext(),name +" Attended",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onClick2(int index, String name, int key) {

            }
        };


        rvSureAppointment = view.findViewById(R.id.rv_sure_appointment);
        data.add(new SureAppointments_Data("Mohammed Hassen Al-mortada","Al-Kurniesh Street","21 years old","Male","775480089" , "12-8-2021"));
        data.add(new SureAppointments_Data("Ali Mohammed Nasser","Al-Hawak Street","30 years old","Male","736047420" , "14-10-2021"));
        data.add(new SureAppointments_Data("Ameera Abdu Saleh Al-Gdawie","Jamal Street","17 years old","Female","770255793" , "1-11-2021"));
        data.add(new SureAppointments_Data("Yosef Salim Ali Eissa","Sana'a Street","45 years old","Female","77490390" , "20-9-2021"));
        data.add(new SureAppointments_Data("Yahya Ahmed Hassen Mohammed","Hamdy Street","21 years old","Male","711203010" , "29-12-2021"));
        data.add(new SureAppointments_Data("Mohammed Hassen Al-mortada","Al-Kurniesh Street","21 years old","Male","775480089" , "12-8-2021"));
        data.add(new SureAppointments_Data("Ali Mohammed Nasser","Bajil Street","30 years old","Male","736047420" , "14-10-2021"));
        data.add(new SureAppointments_Data("Jalilah Abdu Saleh Al-Gdawie","Madina Street","17 years old","Female","770255793" , "1-11-2021"));
        data.add(new SureAppointments_Data("Sameerah Salim Ali Eissa","Al-monaira","45 years old","Female","77490390" , "20-9-2021"));
        data.add(new SureAppointments_Data("Emad Ahmed Hassen Mohammed","Al-mina'a Street","21 years old","Male","711203010" , "29-12-2021"));

        initRV(view);
        initListener();
        return view;


    }

    private void initListener(){
        sv_sure.setOnQueryTextListener(this);
    }


    private  void initRV(View view)
    {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new SureAppointmentsAdapter( view.getContext() , data,clickListener);
        rvSureAppointment.setLayoutManager(layoutManager);
        rvSureAppointment.setAdapter(adapter);
        rvSureAppointment.setHasFixedSize(true);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}

/*
package com.salah.hodiedahclinicsapp_doctors2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.AppointmentCollection;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.CollectionName;
import com.salah.hodiedahclinicsapp_doctors2.SureAppointmentsAdapter;

import java.util.ArrayList;
import java.util.List;


public class SureAppointmentsFragment extends Fragment implements SearchView.OnQueryTextListener{

    androidx.appcompat.widget.Toolbar toolBar ;
    SureAppointmentsAdapter adapter  ;
    RecyclerView rvSureAppointment ;
    ArrayList<AppointmentCollection> data = new ArrayList<>();
    OnClickListener clickListener;
    SearchView sv_sure;
    ProgressBar progressBar ;
    TextView tv_noData , tv_noInternetConnection ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference clinicCollection = db.collection(CollectionName.CLINICS.name());

    private ArrayList<AppointmentCollection> coursesArrayList;
    public SureAppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_sure_appointments, container, false);

        sv_sure = view.findViewById(R.id.sure_SearchView);
        progressBar = view.findViewById(R.id.progress_while_loading);
        tv_noData = view.findViewById(R.id.no_data_tv);
        tv_noInternetConnection = view.findViewById(R.id.no_connection_tv);

        getALlAppointments();


        clickListener = new OnClickListener() {
            @Override
            public void onClick(int index , String name) {
              //  Toast.makeText(getContext(),name +" Attended",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onClick2(int index, String name, int key) {

            }
        };


        rvSureAppointment = view.findViewById(R.id.rv_sure_appointment);

        initRV(view);
        initListener();
        return view;


    }

    private void initListener(){
        sv_sure.setOnQueryTextListener(this);
    }


    private  void initRV(View view)
    {
        getALlAppointments();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new SureAppointmentsAdapter( view.getContext() , data,clickListener);
        rvSureAppointment.setLayoutManager(layoutManager);
        rvSureAppointment.setAdapter(adapter);
        rvSureAppointment.setHasFixedSize(true);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }

    // this method is so  necessary into get the appointments_details at the specific clinic
    private void getALlAppointments() {
        db.collection("appointments").whereEqualTo("doctorName", "Salah Ghazi").whereEqualTo("paymentState",false).get().addOnSuccessListener(
                new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot dd : list) {
                                AppointmentCollection c = dd.toObject(AppointmentCollection.class);
                                  coursesArrayList.add(c);
                            }

                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(requireActivity(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                        /*
                        for(QueryDocumentSnapshot  q : queryDocumentSnapshots)
                        {
                            AppointmentCollection app = q.toObject(AppointmentCollection.class);
                            app.setDocumentId(q.getId());
                            data.add(app);
                        }



                    }
                }
        );

    }

}
*/