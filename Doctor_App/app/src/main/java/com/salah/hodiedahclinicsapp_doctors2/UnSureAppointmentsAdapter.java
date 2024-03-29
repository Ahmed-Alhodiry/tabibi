package com.salah.hodiedahclinicsapp_doctors2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UnSureAppointmentsAdapter extends RecyclerView.Adapter<UnSureAppointmentsAdapter.VH_UnSure> {

    Context mContext ;
    ArrayList<UnSureAppointments_Data> original_items  = new ArrayList<>();
    ArrayList<UnSureAppointments_Data> items = new ArrayList<>();
    com.salah.hodiedahclinicsapp_doctors2.OnClickListener mListener ;

    public UnSureAppointmentsAdapter(Context mContext, ArrayList<UnSureAppointments_Data> items ,com.salah.hodiedahclinicsapp_doctors2.OnClickListener listener  ) {
        this.mContext = mContext;
        this.items = items;
        this.mListener  =  listener;
        this.original_items = new ArrayList<>();
        original_items.addAll(items);
    }

    @NonNull
    @Override
    public VH_UnSure onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_unsure_appointments , parent  ,false);

        VH_UnSure vh = new VH_UnSure(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH_UnSure holder, int position) {

        UnSureAppointments_Data unSureAppointments_data = items.get(position);
        holder.tvPatientName.setText(unSureAppointments_data.getName());
        holder.tvAddress.setText(unSureAppointments_data.getAddress());
        holder.tvAge.setText(unSureAppointments_data.getAge());
        holder.tvPhone.setText(unSureAppointments_data.getPhone());
        holder.tvGender.setText(unSureAppointments_data.getGender());
        holder.tvBookingDate.setText(unSureAppointments_data.getBooking_date());

        holder.accept_appointment.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                //holder.relativeLayout.setVisibility(2);
                mListener.onClick2(position , holder.tvPatientName.getText().toString(),0);

            }
        });

        holder.refuse_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick2(position ,holder.tvPatientName.getText().toString(),1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter( final String strSearch){
        if (strSearch.length() == 0){
            items.clear();
            items.addAll(original_items);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<UnSureAppointments_Data> collect = original_items.stream().filter(i -> i.getName().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                items.addAll(collect);
            }else {
                items.clear();
                for (UnSureAppointments_Data i : original_items){
                    if (i.getName().toLowerCase().contains(strSearch))
                    {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    class VH_UnSure extends RecyclerView.ViewHolder
    {

        TextView  tvPatientName , tvAddress , tvAge , tvGender ,tvPhone ,tvBookingDate,accept_appointment,refuse_appointment;
        RelativeLayout relativeLayout;

        public VH_UnSure(@NonNull View itemView) {
            super(itemView);
            tvPatientName = itemView.findViewById(R.id.tv_patient_name);
            tvAddress = itemView.findViewById(R.id.tv_patient_address);
            tvAge = itemView.findViewById(R.id.tv_patient_age);
            tvGender = itemView.findViewById(R.id.tv_patient_gender);
            tvPhone = itemView.findViewById(R.id.tv_patient_phone);
            tvBookingDate = itemView.findViewById(R.id.tv_booking_date);
            accept_appointment = itemView.findViewById(R.id.accept_appointment);
            refuse_appointment = itemView.findViewById(R.id.refuse_appointment);
            relativeLayout = itemView.findViewById(R.id.unsure_appointment_container);
        }
    }
}

/*
package com.salah.hodiedahclinicsapp_doctors2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.AppointmentCollection;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.ClinicCollection;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.CollectionName;
import com.salah.hodiedahclinicsapp_doctors2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UnSureAppointmentsAdapter extends RecyclerView.Adapter<UnSureAppointmentsAdapter.VH_UnSure> {

    Context mContext ;
    ArrayList<AppointmentCollection> original_items  = new ArrayList<>();
    ArrayList<AppointmentCollection> items = new ArrayList<>();
    com.salah.hodiedahclinicsapp_doctors2.OnClickListener mListener ;


    ArrayList<ClinicCollection> clinics = new ArrayList<>();
    Intent intent = new Intent();
    private String specifiedSearch= null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference clinicRef = db.collection(CollectionName.CLINICS.name().toString());
    CollectionReference clinicCollection = db.collection(CollectionName.CLINICS.name());

    ArrayList<AppointmentCollection> fullItems = new ArrayList<>() ;


    public UnSureAppointmentsAdapter(Context mContext, ArrayList<AppointmentCollection> items , OnClickListener listener  ) {
        this.mContext = mContext;
        this.items = items;
        this.mListener  =  listener;
        this.original_items = new ArrayList<>();
        original_items.addAll(items);
    }

    @NonNull
    @Override
    public VH_UnSure onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_unsure_appointments , parent  ,false);

        VH_UnSure vh = new VH_UnSure(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH_UnSure holder, int position) {


        AppointmentCollection appointmentCollection = items.get(position);
        holder.tvPatientName.setText(String.valueOf(appointmentCollection.getPatient().get(CollectionName.Fields.name.name())));
        holder.tvAddress.setText(String.valueOf(appointmentCollection.getPatient().get((CollectionName.Fields.address.name()))));
        holder.tvAge.setText(String.valueOf(appointmentCollection.getPatient().get(CollectionName.Fields.age.name())));
        holder.tvPhone.setText(String.valueOf(appointmentCollection.getPatient().get(CollectionName.Fields.number.name())));
        holder.tvGender.setText(String.valueOf(appointmentCollection.getPatient().get(CollectionName.Fields.gender.name())));
        holder.tvBookingDate.setText(appointmentCollection.getDateAppointment());


        holder.accept_appointment.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                //holder.relativeLayout.setVisibility(2);
                mListener.onClick2(position , holder.tvPatientName.getText().toString(),0);

            }
        });

        holder.refuse_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick2(position ,holder.tvPatientName.getText().toString(),1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter( final String strSearch){

        if (strSearch.length() == 0){
            items.clear();
            items.addAll(original_items);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<AppointmentCollection> collect = original_items.stream().filter(i -> i.getDoctorName().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                items.addAll(collect);
            }
            else {
                items.clear();
                for (AppointmentCollection i : original_items){
                    if (i.getDoctorName().toLowerCase().contains(strSearch))
                    {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();

        }

    class VH_UnSure extends RecyclerView.ViewHolder
    {

        TextView  tvPatientName , tvAddress , tvAge , tvGender ,tvPhone ,tvBookingDate,accept_appointment,refuse_appointment;
        RelativeLayout relativeLayout;

        public VH_UnSure(@NonNull View itemView) {
            super(itemView);
            tvPatientName = itemView.findViewById(R.id.tv_patient_name);
            tvAddress = itemView.findViewById(R.id.tv_patient_address);
            tvAge = itemView.findViewById(R.id.tv_patient_age);
            tvGender = itemView.findViewById(R.id.tv_patient_gender);
            tvPhone = itemView.findViewById(R.id.tv_patient_phone);
            tvBookingDate = itemView.findViewById(R.id.tv_booking_date);
            accept_appointment = itemView.findViewById(R.id.accept_appointment);
            refuse_appointment = itemView.findViewById(R.id.refuse_appointment);
            relativeLayout = itemView.findViewById(R.id.unsure_appointment_container);
        }
    }
}


 */