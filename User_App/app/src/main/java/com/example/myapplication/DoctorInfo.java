package com.example.myapplication;

public class DoctorInfo {

    private String name ;
    private String specialize;
    private int resourceImage ;
    private String price;
    private String clinicName;
    private String email;
    private String phone;
    private String address;
    private String education_degree;
    private String kuraimi_account_number;
    private String clinicPosition_latitude;
    private String clinicPosition_longitude;

    public DoctorInfo(String name, int resourceImage, String price, String clinicName, String email, String phone, String address, String department, String education_degree, String kuraimi_account_number, String clinicPosition_latitude,String clinicPosition_longitude) {
        this.name = name;
        this.resourceImage = resourceImage;
        this.price = price;
        this.clinicName = clinicName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.specialize = department;
        this.education_degree = education_degree;
        this.kuraimi_account_number = kuraimi_account_number;
        this.clinicPosition_latitude = clinicPosition_latitude;
        this.clinicPosition_longitude = clinicPosition_longitude;

    }

    public String getName() {
        return name;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public String getEducation_degree() {
        return education_degree;
    }

    public String getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public String getSpecialize() {
        return specialize;
    }

    public String getKuraimi_account_number() {
        return kuraimi_account_number;
    }

    public String getClinicPosition_latitude() {
        return clinicPosition_latitude;
    }

    public String getClinicPosition_longitude() {
        return clinicPosition_longitude;
    }
}
