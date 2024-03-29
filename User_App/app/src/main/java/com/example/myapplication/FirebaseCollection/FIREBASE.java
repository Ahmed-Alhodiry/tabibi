package com.example.myapplication.FirebaseCollection;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FIREBASE {

    public static CollectionReference ref_user;


    public static void initFirebase()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ref_user = db.collection("User");
    }

}
