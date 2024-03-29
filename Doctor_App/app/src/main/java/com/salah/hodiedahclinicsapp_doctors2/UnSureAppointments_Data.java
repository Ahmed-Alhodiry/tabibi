package com.salah.hodiedahclinicsapp_doctors2;

public class UnSureAppointments_Data {

    private String name ;
    private String address;
    private String age;
    private String gender;
    private String phone;
    private String booking_date;

    public UnSureAppointments_Data(String name, String address, String age, String gender, String phone, String booking_date) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.booking_date = booking_date;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getBooking_date() {
        return booking_date;
    }
}
