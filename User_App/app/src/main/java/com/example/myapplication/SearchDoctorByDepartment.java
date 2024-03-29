package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchDoctorByDepartment extends AppCompatActivity {


    private final static String SPECIALIZE="SPECIALIZE";

    Global_Variables global_variables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor_by_department);
        global_variables = new Global_Variables();

    }

    public void Search_Doctors_Eyes_Department(View view) {

        Intent intent = new Intent(getApplicationContext(),SearchDoctorByName.class);
        intent.putExtra(SPECIALIZE  , global_variables.get_EYES());
        startActivity(intent);



    }

    public void Search_Doctors_Bones_Department(View view) {

        Intent intent = new Intent(getApplicationContext(), SearchDoctorByName.class);
        intent.putExtra(SPECIALIZE  , global_variables.get_BONES());
        startActivity(intent);

    }

    public void Search_Doctors_Teeth_Department(View view) {

        Intent intent = new Intent(getApplicationContext(), SearchDoctorByName.class);
        intent.putExtra(SPECIALIZE  , global_variables.get_TEETH());
        startActivity(intent);


    }

    public void Search_Doctors_Children_Department(View view) {

        Intent intent = new Intent(getApplicationContext(), SearchDoctorByName.class);
        intent.putExtra(SPECIALIZE  , global_variables.get_CHILDREN());
        startActivity(intent);


    }

    public void Search_Doctors_Ear_Department(View view) {

        Intent intent = new Intent(getApplicationContext(), SearchDoctorByName.class);
        intent.putExtra(SPECIALIZE  , global_variables.get_EAR_and_Larynx());
        startActivity(intent);


    }

    public void Search_Doctors_Heart_Department(View view) {

        Intent intent = new Intent(getApplicationContext(), SearchDoctorByName.class);
        intent.putExtra(SPECIALIZE  , global_variables.get_Heart_DISEASES());
        startActivity(intent);


    }

    public void Search_Doctors_Genitals_Department(View view) {

        Intent intent = new Intent(getApplicationContext(), SearchDoctorByName.class);
        intent.putExtra(SPECIALIZE  , global_variables.get_GENITALS_DISEASES());
        startActivity(intent);


    }

    public void Search_Doctors_Brain_Department(View view) {

        Intent intent = new Intent(getApplicationContext(),SearchDoctorByName.class);
        intent.putExtra(SPECIALIZE  , global_variables.get_BRAIN_DISEASES());
        startActivity(intent);


    }
}