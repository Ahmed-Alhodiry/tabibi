package com.salah.hodiedahclinicsapp_doctors2;

import android.content.Context;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SureAppointmentsAdapter extends RecyclerView.Adapter<SureAppointmentsAdapter.VHSure>  {

    Context mContext ;
    ArrayList<SureAppointments_Data> original_items;
    com.salah.hodiedahclinicsapp_doctors2.OnClickListener mListener ;
    ArrayList<SureAppointments_Data> items = new ArrayList<>();
    ArrayList<com.salah.hodiedahclinicsapp_doctors2.SureAppointments_Data> fullItems = new ArrayList<>() ;

    public SureAppointmentsAdapter(Context mContext, ArrayList<SureAppointments_Data> items ,com.salah.hodiedahclinicsapp_doctors2.OnClickListener listener   ) {
        this.mContext = mContext;
        this.items = items;
        this.mListener  =  listener;
        this.original_items = new ArrayList<>();
        original_items.addAll(items);

    }

    @NonNull
    @Override
    public VHSure onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_sure_appointment , parent  ,false);

        VHSure vh = new VHSure(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VHSure holder, int position) {

        SureAppointments_Data sureAppointments_data = items.get(position);
        holder.tvPatientName.setText(sureAppointments_data.getName());
        holder.tvAddress.setText(sureAppointments_data.getAddress());
        holder.tvAge.setText(sureAppointments_data.getAge());
        holder.tvPhone.setText(sureAppointments_data.getPhone());
        holder.tvGender.setText(sureAppointments_data.getGender());
        holder.tvBookingDate.setText(sureAppointments_data.getBooking_date());

        holder.cb_attendAppointment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    TransitionManager.beginDelayedTransition(holder.itemView.findViewById(R.id.parent_layout),new AutoTransition());
                    holder.itemView.findViewById(R.id.linearLayout2).setVisibility(View.GONE);
                    holder.itemView.findViewById(R.id.linearLayout5).setVisibility(View.GONE);
                    holder.itemView.findViewById(R.id.linearLayout4).setVisibility(View.VISIBLE);
                    String n = holder.tvPatientName.getText().toString();
                    holder.txt.setText("Patient Name : "+n);
                    holder.cb_attendAppointment.setChecked(false);
                }
                mListener.onClick(position,holder.tvPatientName.getText().toString());
            }
        });

        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(holder.itemView.findViewById(R.id.parent_layout),new AutoTransition());
                holder.itemView.findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
                holder.itemView.findViewById(R.id.linearLayout5).setVisibility(View.VISIBLE);
                holder.itemView.findViewById(R.id.linearLayout4).setVisibility(View.GONE);
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
                List<SureAppointments_Data> collect = original_items.stream().filter(i -> i.getName().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (SureAppointments_Data i : original_items){
                    if (i.getName().toLowerCase().contains(strSearch))
                    {
                        items.add(i);
                    }

                }
            }
        }
        notifyDataSetChanged();
    }

    class VHSure extends RecyclerView.ViewHolder
    {
        TextView  tvPatientName , tvAddress , tvAge , tvGender ,tvPhone ,tvBookingDate;
        TextView  txt;
        ImageButton icon;
        com.google.android.material.checkbox.MaterialCheckBox cb_attendAppointment;
        public VHSure(@NonNull View itemView) {
            super(itemView);
            tvPatientName = itemView.findViewById(R.id.tv_patient_name);
            tvAddress = itemView.findViewById(R.id.tv_patient_address);
            tvAge = itemView.findViewById(R.id.tv_patient_age);
            tvGender = itemView.findViewById(R.id.tv_patient_gender);
            tvPhone = itemView.findViewById(R.id.tv_patient_phone);
            tvBookingDate = itemView.findViewById(R.id.tv_booking_date);
            cb_attendAppointment = itemView.findViewById(R.id.cb_attendAppointment);
            txt = itemView.findViewById(R.id.txtName);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}




/*


package com.salah.hodiedahclinicsapp_doctors2;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.AppointmentCollection;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.ClinicCollection;
import com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection.CollectionName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SureAppointmentsAdapter extends RecyclerView.Adapter<SureAppointmentsAdapter.VHSure>  {

    ArrayList<ClinicCollection> clinics = new ArrayList<>();
    Intent intent = new Intent();
    private String specifiedSearch= null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference clinicRef = db.collection(CollectionName.CLINICS.name().toString());
    CollectionReference clinicCollection = db.collection(CollectionName.CLINICS.name());

    Context mContext ;
    ArrayList<AppointmentCollection> original_items;
    com.salah.hodiedahclinicsapp_doctors2.OnClickListener mListener ;
    ArrayList<AppointmentCollection> items = new ArrayList<>();
    ArrayList<AppointmentCollection> fullItems = new ArrayList<>() ;

    public SureAppointmentsAdapter(Context mContext, ArrayList<AppointmentCollection> items , com.salah.hodiedahclinicsapp_doctors2.OnClickListener listener   ) {
        this.mContext = mContext;
        this.items = items;
        this.mListener  =  listener;
        this.original_items = new ArrayList<>();
        original_items.addAll(items);
    }

    @NonNull
    @Override
    public VHSure onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_sure_appointment , parent  ,false);
        VHSure vh = new VHSure(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VHSure holder, int position) {

        AppointmentCollection appointmentCollection = items.get(position);
        holder.tvPatientName.setText(String.valueOf(appointmentCollection.getPatient().get(CollectionName.Fields.name.name())));
        holder.tvAddress.setText(String.valueOf(appointmentCollection.getPatient().get((CollectionName.Fields.address.name()))));
        holder.tvAge.setText(String.valueOf(appointmentCollection.getPatient().get(CollectionName.Fields.age.name())));
        holder.tvPhone.setText(String.valueOf(appointmentCollection.getPatient().get(CollectionName.Fields.number.name())));
        holder.tvGender.setText(String.valueOf(appointmentCollection.getPatient().get(CollectionName.Fields.gender.name())));
        holder.tvBookingDate.setText(appointmentCollection.getDateAppointment());

        AppointmentCollection clinicCollection = items.get(position);

        holder.cb_attendAppointment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    TransitionManager.beginDelayedTransition(holder.itemView.findViewById(R.id.parent_layout),new AutoTransition());
                    holder.itemView.findViewById(R.id.linearLayout2).setVisibility(View.GONE);
                    holder.itemView.findViewById(R.id.linearLayout5).setVisibility(View.GONE);
                    holder.itemView.findViewById(R.id.linearLayout4).setVisibility(View.VISIBLE);
                    String n = holder.tvPatientName.getText().toString();
                    holder.txt.setText("Patient Name : "+n);
                    holder.cb_attendAppointment.setChecked(false);
                }
                mListener.onClick(position,holder.tvPatientName.getText().toString());
            }
        });

        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(holder.itemView.findViewById(R.id.parent_layout),new AutoTransition());
                holder.itemView.findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
                holder.itemView.findViewById(R.id.linearLayout5).setVisibility(View.VISIBLE);
                holder.itemView.findViewById(R.id.linearLayout4).setVisibility(View.GONE);
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

    class VHSure extends RecyclerView.ViewHolder
    {
        TextView  tvPatientName , tvAddress , tvAge , tvGender ,tvPhone ,tvBookingDate;
        TextView  txt;
        ImageButton icon;
        com.google.android.material.checkbox.MaterialCheckBox cb_attendAppointment;
        public VHSure(@NonNull View itemView) {
            super(itemView);
            tvPatientName = itemView.findViewById(R.id.tv_patient_name);
            tvAddress = itemView.findViewById(R.id.tv_patient_address);
            tvAge = itemView.findViewById(R.id.tv_patient_age);
            tvGender = itemView.findViewById(R.id.tv_patient_gender);
            tvPhone = itemView.findViewById(R.id.tv_patient_phone);
            tvBookingDate = itemView.findViewById(R.id.tv_booking_date);
            cb_attendAppointment = itemView.findViewById(R.id.cb_attendAppointment);
            txt = itemView.findViewById(R.id.txtName);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
*/