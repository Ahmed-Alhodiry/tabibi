package com.example.myapplication;

import android.app.Application;

public class  Global_Variables extends Application {

    private String DR_NAME;
    private String DR_SPECIALIZE;
    private String DR_CLINIC_NAME;
    private String DR_BOOKING_PRICE;
    private String DR_EMAIL;
    private String DR_PHONE_NUMBER;
    private String DR_EDUCATION_DEGREE;
    private String kuraimi_account;
    private String DR_ADDRESS;
    private String ClinicPosition_latitude;
    private String ClinicPosition_longitude;


    private String _TEETH="الأسنان";
    private String _EYES="العيون";
    private String _BONES="العظام";
    private String _CHILDREN="الأطفال";
    private String _EAR_and_Larynx="الأذن والحنجرة";
    private String _Heart_DISEASES="أمراض القلب";
    private String _GENITALS_DISEASES="الجهاز التناسلي والعقم";
    private String _BRAIN_DISEASES="الدماغ والجهاز العصبي";



    public String get_BONES() {
        return _BONES;
    }

    public String get_CHILDREN() {
        return _CHILDREN;
    }

    public String get_EAR_and_Larynx() {
        return _EAR_and_Larynx;
    }

    public String get_Heart_DISEASES() {
        return _Heart_DISEASES;
    }

    public String get_GENITALS_DISEASES() {
        return _GENITALS_DISEASES;
    }

    public String get_BRAIN_DISEASES() {
        return _BRAIN_DISEASES;
    }

    public String get_TEETH() {
        return _TEETH;
    }

    public String get_EYES() {
        return _EYES;
    }

    public String getClinicPosition_latitude() {
        return ClinicPosition_latitude;
    }

    public void setClinicPosition_latitude(String clinicPosition_latitude) {
        ClinicPosition_latitude = clinicPosition_latitude;
    }

    public String getClinicPosition_longitude() {
        return ClinicPosition_longitude;
    }

    public void setClinicPosition_longitude(String clinicPosition_longitude) {
        ClinicPosition_longitude = clinicPosition_longitude;
    }

    public void setDR_CLINIC_NAME(String DR_CLINIC_NAME) {
        this.DR_CLINIC_NAME = DR_CLINIC_NAME;
    }

    public String getDR_CLINIC_NAME() {
        return DR_CLINIC_NAME;
    }

    public String getKuraimi_account() {
        return kuraimi_account;
    }

    public void setKuraimi_account(String kuraimi_account) {
        this.kuraimi_account = kuraimi_account;
    }

    public String getDR_NAME() {
        return DR_NAME;
    }

    public void setDR_NAME(String DR_NAME) {
        this.DR_NAME = DR_NAME;
    }

    public String getDR_SPECIALIZE() {
        return DR_SPECIALIZE;
    }

    public void setDR_SPECIALIZE(String DR_SPECIALIZE) {
        this.DR_SPECIALIZE = DR_SPECIALIZE;
    }

    public String getDR_BOOKING_PRICE() {
        return DR_BOOKING_PRICE;
    }

    public void setDR_BOOKING_PRICE(String DR_BOOKING_PRICE) {
        this.DR_BOOKING_PRICE = DR_BOOKING_PRICE;
    }

    public String getDR_EMAIL() {
        return DR_EMAIL;
    }

    public void setDR_EMAIL(String DR_EMAIL) {
        this.DR_EMAIL = DR_EMAIL;
    }

    public String getDR_PHONE_NUMBER() {
        return DR_PHONE_NUMBER;
    }

    public void setDR_PHONE_NUMBER(String DR_PHONE_NUMBER) {
        this.DR_PHONE_NUMBER = DR_PHONE_NUMBER;
    }

    public String getDR_EDUCATION_DEGREE() {
        return DR_EDUCATION_DEGREE;
    }

    public void setDR_EDUCATION_DEGREE(String DR_EDUCATION_DEGREE) {
        this.DR_EDUCATION_DEGREE = DR_EDUCATION_DEGREE;
    }

    public String getDR_ADDRESS() {
        return DR_ADDRESS;
    }

    public void setDR_ADDRESS(String DR_ADDRESS) {
        this.DR_ADDRESS = DR_ADDRESS;
    }

}
