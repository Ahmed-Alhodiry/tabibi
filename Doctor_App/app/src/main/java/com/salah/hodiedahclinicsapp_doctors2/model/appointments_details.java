package com.salah.hodiedahclinicsapp_doctors2.model;

public class appointments_details {

    String name;
    String educationDegree;
    String address;

    public appointments_details(String name, String educationDegree, String address) {
        this.name = name;
        this.educationDegree = educationDegree;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}