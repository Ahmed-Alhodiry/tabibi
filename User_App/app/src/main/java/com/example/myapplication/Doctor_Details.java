package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.FirebaseCollection.CollectionName;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.example.myapplication.FirebaseCollection.FIREBASE;

import java.net.URLEncoder;

import es.dmoral.toasty.Toasty;

public class Doctor_Details extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static String user_id;
    FloatingActionButton btn_call;
    Global_Variables global_variables;
    TextView dr_name,dr_specialize,dr_ClinicName,dr_booking_price,dr_email,dr_PhoneNumber,dr_education_drgree,dr_address ,dr_Kuraimi_account;
    String doctor_phone,doctor_email,doctor_Name;
    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__details);

        global_variables = new Global_Variables();

/*
        sharedPreferences = getSharedPreferences("Hodiedah_Clincs", Context.MODE_PRIVATE);
        get_shared_preferences();

        FirebaseApp.initializeApp(this);
        FIREBASE.initFirebase();

        new Handler().postDelayed(()->{
            Intent i;
            if(!user_id.equals("null")){
                i = new Intent(Doctor_Details.this,Patient_New_Appointment.class);
            }else {
                i = new Intent(Doctor_Details.this,Patient_SignIn_and_SignUp.class);
            }
            startActivity(i);
            finish();
        },1000);
 */

        dr_name = findViewById(R.id.dr_name);
        dr_specialize = findViewById(R.id.dr_specialize);
        dr_ClinicName = findViewById(R.id.dr_ClinicName);
        dr_booking_price = findViewById(R.id.dr_booking_price);
        dr_email = findViewById(R.id.dr_email);
        dr_PhoneNumber = findViewById(R.id.dr_PhoneNumber);
        dr_education_drgree = findViewById(R.id.dr_education_drgree);
        dr_address = findViewById(R.id.dr_address);
        dr_Kuraimi_account = findViewById(R.id.dr_kuraimi_account);

        doctor_Name = getIntent().getStringExtra("doctor_Name");
        String clinic_Name = getIntent().getStringExtra("clinic_Name");
        String specialize = getIntent().getStringExtra("specialize");
        String booking_price = getIntent().getStringExtra("booking_price");
        doctor_email = getIntent().getStringExtra("doctor_email");
        doctor_phone = getIntent().getStringExtra("doctor_phone");
        String education_degree = getIntent().getStringExtra("education_degree");
        String address = getIntent().getStringExtra("address");
        String kuraimi_account = getIntent().getStringExtra("kuraimi_account");
        String ClinicPosition_latitude = getIntent().getStringExtra("ClinicPosition_latitude");
        String ClinicPosition_longitude = getIntent().getStringExtra("ClinicPosition_longitude");

        dr_name.setText("د / "+doctor_Name);
        dr_ClinicName.setText(clinic_Name);
        dr_specialize.setText(specialize);
        dr_booking_price.setText(" [ "+booking_price+" ] "+"ريال يمني");
        dr_email.setText(doctor_email);
        dr_PhoneNumber.setText(doctor_phone);
        dr_education_drgree.setText(education_degree);
        dr_address.setText(address);
        dr_Kuraimi_account.setText(kuraimi_account);


        btn_call = findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneNum = doctor_phone;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+PhoneNum));
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "call", Toast.LENGTH_SHORT).show();

            }
        });

    }
/*
    public static void set_shared_preferences(String key,String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();

        get_shared_preferences();
    }

    private static void get_shared_preferences() {
        user_id = sharedPreferences.getString("user_id","null");
    }
    public static void clear_shared_preferences(){
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
*/

    public void position_in_map(View view) {

        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(intent);

    }
/*
    public void communicate_the_doctor_ByCall(View view) {
        try {
            String PhoneNum = "777777777";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+PhoneNum));
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "لايوجد تطبيق اتصال", Toast.LENGTH_SHORT).show();
        }
    }
*/
    public void communicate_the_doctor_ByWhatsApp(View view) {

        String toNumber = doctor_phone;
        String url = "https://api.whatsapp.com/send?phone="+ toNumber;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp"); //com.whatsapp or com.whatsapp.w4b
        i.setData(Uri.parse(url));
        i.putExtra(Intent.EXTRA_TEXT,"مرحباً د / "+doctor_Name);
        startActivity(i);
/*
        PackageManager packageManager = getApplicationContext().getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);

        try {
            String phone = doctor_phone;
            String message = "السلام عليكم ورحمة الله وبركاته";
            String url = "https://api.whatsapp.com/send?phone="+ phone +"&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                getApplicationContext().startActivity(i);
            }
        } catch (Exception e){
            e.printStackTrace();
            Toasty.error(getApplicationContext(), "تطبيق واتساب الرسمي غير مثبت لديك", Toast.LENGTH_LONG).show();
        }

        PackageManager pm=getPackageManager();
        try
        {
            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            waIntent.setPackage("com.whatsapp");
            waIntent.putExtra(Intent.EXTRA_TEXT, "Hi Tabiby App");
            startActivity(Intent.createChooser(waIntent, "Share with"));
        }
        catch (PackageManager.NameNotFoundException e)
        {
            //Toast.makeText(getApplicationContext(), "WhatsApp not Installed in your Mobile", Toast.LENGTH_SHORT).show();
        }
*/

    }

    public void communicate_the_doctor_ByEmail(View view) {
        try {
            String[] to = {doctor_email};
            Intent send_email = new Intent(Intent.ACTION_SEND);
            send_email.putExtra(Intent.EXTRA_EMAIL, to);
            send_email.putExtra(Intent.EXTRA_SUBJECT, "مرحباً د / "+doctor_Name);
            send_email.putExtra(Intent.EXTRA_TEXT, "السلام عليكم ورحمة الله وبركاته");
            send_email.setType("message/rfc822");
            send_email.createChooser(send_email, "Send Email");
            startActivity(send_email);
        } catch (Exception e) {
            Toast.makeText(Doctor_Details.this, "عفواً لا يوجد تطبيق مراسلة في تلفونك !", Toast.LENGTH_SHORT).show();
        }
    }

    public void New_Appointment(View view) {
        Intent intent = new Intent(getApplicationContext(),Patient_New_Appointment.class);
        intent.putExtra("doctorEmail", doctor_email);
        startActivity(intent);
    }
}