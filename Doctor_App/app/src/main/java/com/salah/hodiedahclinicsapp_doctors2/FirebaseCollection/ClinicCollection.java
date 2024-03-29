package com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicCollection {

    private String documentId ;
    private  String clinicName  , typeOfClinic ;
    private long price ;
    private List<String> telephones ;
    private Map<String , Object> address= new HashMap<>();
    private Map<String  ,  Object> doctor =new HashMap<>();

    public ClinicCollection() {
        // empty constructor is  important for data or null object
    }

    public ClinicCollection(String clinicName, String typeOfClinic, long price, List<String> telephones, Map<String, Object> address, Map<String, Object> doctor) {

        this.clinicName = clinicName;
        this.typeOfClinic = typeOfClinic;
        this.price = price;
        this.telephones = telephones;
        this.address = address;
        this.doctor = doctor;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

   @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getTypeOfClinic() {
        return typeOfClinic;
    }

    public long getPrice() {
        return price;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public Map<String, Object> getAddress() {
        return address;
    }

    public Map<String, Object> getDoctor() {
        return doctor;
    }
}
