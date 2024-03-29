package com.example.myapplication.FirebaseCollection;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class AppointmentCollection {
    private Map<String  , Object> patient = new HashMap<>();
    private boolean state ;
    private boolean paymentState;
    private String relation ;
    private String doctorEmail ;
    private   String documentId  ;
    private  String dateAppointment ;

    public AppointmentCollection() {
        // this constructor is required
    }

    public AppointmentCollection(Map<String, Object> patient, boolean state, boolean paymentState, String relation, String doctorEmail , String dateAppointment) {
        this.patient = patient;
        this.state = state;
        this.paymentState = paymentState;
        this.relation = relation;
        this.doctorEmail = doctorEmail;
        this.dateAppointment = dateAppointment;

    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Map<String, Object> getPatient() {
        return patient;
    }

    public boolean isState() {
        return state;
    }

    public boolean isPaymentState() {
        return paymentState;
    }

    public String getRelation() {
        return relation;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public String getDateAppointment() {
        return dateAppointment;
    }


    public void setPatient(Map<String, Object> patient) {
        this.patient = patient;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setPaymentState(boolean paymentState) {
        this.paymentState = paymentState;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setDoctorEmail(String doctorName) {
        this.doctorEmail = doctorEmail;
    }

    public void setDateAppointment(String dateAppointment) {
        this.dateAppointment = dateAppointment;
    }
}
