package com.salah.hodiedahclinicsapp_doctors2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class DoctorHomeFragment extends Fragment {

    Global_Variables global_variables;

    TextView dr_name,dr_specialize,dr_ClinicName,dr_booking_price,dr_email,dr_PhoneNumber,dr_education_drgree,dr_address ,dr_Kuraimi_account;


    public DoctorHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_doctor_home, container, false);


        dr_name = view.findViewById(R.id.dr_Name);
        dr_specialize = view.findViewById(R.id.dr_specialize);
        dr_ClinicName = view.findViewById(R.id.dr_ClinicName);
        dr_booking_price = view.findViewById(R.id.dr_booking_price);
        dr_email = view.findViewById(R.id.dr_email);
        dr_PhoneNumber = view.findViewById(R.id.dr_PhoneNumber);
        dr_education_drgree = view.findViewById(R.id.dr_education_drgree);
        dr_address = view.findViewById(R.id.dr_address);
        dr_Kuraimi_account = view.findViewById(R.id.dr_kuraimi_account);

        /*
        dr_name.setText(global_variables.getDR_NAME().toString());
        dr_ClinicName.setText(global_variables.getDR_CLINIC_NAME().toString());
        dr_specialize.setText(global_variables.getDR_SPECIALIZE().toString());
        dr_booking_price.setText(String.valueOf(global_variables.getDR_BOOKING_PRICE()));
        dr_email.setText(global_variables.getDR_EMAIL().toString());
        dr_PhoneNumber.setText(global_variables.getDR_PHONE_NUMBER().toString());
        dr_education_drgree.setText(global_variables.getDR_EDUCATION_DEGREE());
        dr_address.setText(global_variables.getDR_ADDRESS());
        dr_Kuraimi_account.setText(global_variables.getKuraimi_account());
         */


        return view;
    }
}