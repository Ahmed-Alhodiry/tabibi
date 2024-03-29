package com.example.myapplication.FirebaseCollection;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicCollection2 {

    Map<String, Object> Clinic_Address;
    String clinicName;
    Map<String, Object> doctor;
    String price;
    String typeOfClinic;

    public ClinicCollection2() {
        // empty constructor is  important for data or null object
    }

    public ClinicCollection2(Map<String, Object> clinic_Address, String clinicName, Map<String, Object> doctor, String price, String typeOfClinic) {
        Clinic_Address = clinic_Address;
        this.clinicName = clinicName;
        this.doctor = doctor;
        this.price = price;
        this.typeOfClinic = typeOfClinic;
    }

    public Map<String, Object> getClinic_Address() {
        return Clinic_Address;
    }

    public String getClinicName() {
        return clinicName;
    }

    public Map<String, Object> getDoctor() {
        return doctor;
    }

    public String getPrice() {
        return price;
    }

    public String getTypeOfClinic() {
        return typeOfClinic;
    }
}
