package com.salah.hodiedahclinicsapp_doctors2.FirebaseCollection;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class AppointmentCollection {
    private Map<String  , Object> patient = new HashMap<>();
    private boolean state ;
    private boolean paymentState;
    private String relation;
    private String doctorName;
    private   String documentId;
    private  String dateAppointment ;

    public AppointmentCollection() {
        // this constructor is required
    }

    public AppointmentCollection(Map<String, Object> patient, boolean state, boolean paymentState, String relation, String doctorName , String dateAppointment) {
        this.patient = patient;
        this.state = state;
        this.paymentState = paymentState;
        this.relation = relation;
        this.doctorName = doctorName;
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

    public String getDoctorName() {
        return doctorName;
    }

    public String getDateAppointment() {
        return dateAppointment;
    }
}
