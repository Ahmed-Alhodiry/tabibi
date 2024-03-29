package com.example.myapplication.FirebaseCollection;

public class UsersCollection {

    private String userName ;
    private String userEmail ;
    private   String userPassword  ;
    private  String userPhoneNumber ;

    public UsersCollection() {
        //required
    }

    public UsersCollection(String userName, String userEmail, String userPassword, String userPhoneNumber) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }
}
