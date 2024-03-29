package com.example.myapplication.FirebaseCollection;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicCollection {

    private String documentId ;

    Map<String, Object> Clinic_Address;
    String clinicName;
    Map<String, Object> doctor;
    String price;
    String typeOfClinic;

    public ClinicCollection() {
        // empty constructor is  important for data or null object
    }

    public ClinicCollection(String documentId, Map<String, Object> clinic_Address, String clinicName, Map<String, Object> doctor, String price, String typeOfClinic) {
        this.documentId = documentId;
        this.Clinic_Address = clinic_Address;
        this.clinicName = clinicName;
        this.doctor = doctor;
        this.price = price;
        this.typeOfClinic = typeOfClinic;
    }

    public Map<String, Object> getClinic_Address() {
        return Clinic_Address;
    }

    public void setClinic_Address(Map<String, Object> clinic_Address) {
        Clinic_Address = clinic_Address;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Map<String, Object> getDoctor() {
        return doctor;
    }

    public void setDoctor(Map<String, Object> doctor) {
        this.doctor = doctor;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTypeOfClinic() {
        return typeOfClinic;
    }

    public void setTypeOfClinic(String typeOfClinic) {
        this.typeOfClinic = typeOfClinic;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

   @Exclude
    public String getDocumentId() {
        return documentId;
    }

}
