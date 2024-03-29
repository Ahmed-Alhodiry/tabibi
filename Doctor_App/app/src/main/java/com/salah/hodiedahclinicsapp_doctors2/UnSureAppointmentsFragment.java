package com.salah.hodiedahclinicsapp_doctors2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class UnSureAppointmentsFragment extends Fragment implements SearchView.OnQueryTextListener {

    UnSureAppointmentsAdapter adapter;
    RecyclerView rvUnSureAppointment;
    ArrayList<UnSureAppointments_Data> data = new ArrayList<>();
    OnClickListener clickListener;
    SearchView sv_unSure;


    public UnSureAppointmentsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_un_sure_appointments, container, false);

        sv_unSure = view.findViewById(R.id.unSure_SearchView);

        clickListener = new OnClickListener() {
            @Override
            public void onClick(int index, String name) {

            }

            @Override
            public void onClick2(int index, String name, int key) {
                if (key == 0) {
                    Toasty.success(getContext(), "تم قبول حجز المريض", Toast.LENGTH_SHORT, true).show();
                    data.remove(index);
                    adapter = null;
                    initRV(view);
                } else if (key == 1) {
                    notification();
                    Toasty.warning(getContext(), "تم رفض حجز المريض", Toast.LENGTH_SHORT, true).show();
                    data.remove(index);
                    adapter = null;
                    initRV(view);
                }

            }
        };


        rvUnSureAppointment = view.findViewById(R.id.rv_unsure_appointment);
        data.add(new UnSureAppointments_Data("Mohammed Hassen Al-mortada", "Al-Kurniesh Street", "21 years old", "Male", "775480089", "12-8-2021"));
        data.add(new UnSureAppointments_Data("Ali Mohammed Nasser", "Al-Hawak Street", "30 years old", "Male", "736047420", "14-10-2021"));
        data.add(new UnSureAppointments_Data("Ameera Abdu Saleh Al-Gdawie", "Jamal Street", "17 years old", "Female", "770255793", "1-11-2021"));
        data.add(new UnSureAppointments_Data("Yosef Salim Ali Eissa", "Sana'a Street", "45 years old", "Female", "77490390", "20-9-2021"));
        data.add(new UnSureAppointments_Data("Yahya Ahmed Hassen Mohammed", "Hamdy Street", "21 years old", "Male", "711203010", "29-12-2021"));
        data.add(new UnSureAppointments_Data("Mohammed Hassen Al-mortada", "Al-Kurniesh Street", "21 years old", "Male", "775480089", "12-8-2021"));
        data.add(new UnSureAppointments_Data("Ali Mohammed Nasser", "Bajil Street", "30 years old", "Male", "736047420", "14-10-2021"));
        data.add(new UnSureAppointments_Data("Jalilah Abdu Saleh Al-Gdawie", "Madina Street", "17 years old", "Female", "770255793", "1-11-2021"));
        data.add(new UnSureAppointments_Data("Sameerah Salim Ali Eissa", "Al-monaira", "45 years old", "Female", "77490390", "20-9-2021"));
        data.add(new UnSureAppointments_Data("Emad Ahmed Hassen Mohammed", "Al-mina'a Street", "21 years old", "Male", "711203010", "29-12-2021"));

        initRV(view);
        initListener();
        return view;
    }

    private void notification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            Bitmap bitmap_image = BitmapFactory.decodeResource(getResources(), R.drawable.doctor_img);
            Bitmap big_bitmap_image = BitmapFactory.decodeResource(getResources(), R.drawable.doctor_img);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(requireActivity(), "n")
                    .setContentTitle("تم حذف احد الحجوزات")
                    .setContentText(" ^_^")
                    .setSmallIcon(IconCompat.createWithBitmap(bitmap_image))
                    .setLargeIcon(big_bitmap_image)
                    .setAutoCancel(true);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(requireActivity());
            MediaPlayer mediaPlayer = MediaPlayer.create(requireActivity(), R.raw.notification);
            mediaPlayer.start();
            managerCompat.notify(999, builder.build());

        }

    }


    private void initListener() {
        sv_unSure.setOnQueryTextListener(this);
    }


    private void initRV(View view) {
        Toasty.info(view.getContext(), data.size() + " حجوزات غير مؤكدة", Toast.LENGTH_SHORT, true).show();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new UnSureAppointmentsAdapter(view.getContext(), data, clickListener);
        rvUnSureAppointment.setLayoutManager(layoutManager);
        rvUnSureAppointment.setAdapter(adapter);
        rvUnSureAppointment.setHasFixedSize(true);
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
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.AppointmentCollection;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.CollectionName;
import com.salah.hodiedahclinicsapp_doctors2.R;
import com.salah.hodiedahclinicsapp_doctors2.UnSureAppointmentsAdapter;

import java.util.ArrayList;


public class UnSureAppointmentsFragment extends Fragment implements SearchView.OnQueryTextListener {

    UnSureAppointmentsAdapter adapter  ;
    RecyclerView rvUnSureAppointment ;
    ArrayList<AppointmentCollection> data = new ArrayList<>();
    OnClickListener clickListener;
    SearchView sv_unSure;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference clinicCollection = db.collection(CollectionName.CLINICS.name());



    public UnSureAppointmentsFragment() {
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
        View view  = inflater.inflate(R.layout.fragment_un_sure_appointments, container, false);

        sv_unSure = view.findViewById(R.id.unSure_SearchView);

        clickListener = new OnClickListener() {
            @Override
            public void onClick(int index, String name) {

            }

            @Override
            public void onClick2(int index , String name ,int key) {
                if (key == 0) {
                    Toast.makeText(getContext(), "Patient "+name+" is Accepted", Toast.LENGTH_SHORT).show();
                }else if(key == 1) {
                    Toast.makeText(getContext(), "Patient "+name+" is Refused", Toast.LENGTH_SHORT).show();
                    data.remove(index);
                    adapter=null ;
                    initRV(view);
                }

            }
        };

        getALlAppointments();


        rvUnSureAppointment = view.findViewById(R.id.rv_unsure_appointment);

        initRV(view);
        initListener();
        return view;
    }

    private void initListener(){
        sv_unSure.setOnQueryTextListener(this);
    }

    private  void initRV(View view)
    {
        getALlAppointments();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new UnSureAppointmentsAdapter( view.getContext() , data,clickListener);
        rvUnSureAppointment.setLayoutManager(layoutManager);
        rvUnSureAppointment.setAdapter(adapter);
        rvUnSureAppointment.setHasFixedSize(true);
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
        clinicCollection.whereEqualTo(CollectionName.Fields.clinicName.name(), "The Happy Smile Clinic").whereEqualTo(CollectionName.Fields.doctor.name() + "." + CollectionName.Fields.name.name(), "Salah Al-din Ahmed Salim").get().addOnSuccessListener(
                new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        clinicCollection.document(queryDocumentSnapshots.getDocuments().get(0).toString())
                                .collection(CollectionName.appointments.name())
                                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot q : queryDocumentSnapshots) {
                                    AppointmentCollection app = q.toObject(AppointmentCollection.class);
                                    app.setDocumentId(q.getId());
                                    data.add(app);
                                }

                            }
                        });

                    }
                }
        );


    }
}

 */