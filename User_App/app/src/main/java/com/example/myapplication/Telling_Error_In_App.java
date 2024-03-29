package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Telling_Error_In_App extends AppCompatActivity {

    ImageView send_by_email;
    EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telling__error__in__app);

        send_by_email = findViewById(R.id.img_email);
        txt = findViewById(R.id.editTextTextMultiLine);

        send_by_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String[] to = {"salahdoos77@gmail.com"};
                    Intent send_email = new Intent(Intent.ACTION_SEND);
                    send_email.putExtra(Intent.EXTRA_EMAIL, to);
                    send_email.putExtra(Intent.EXTRA_SUBJECT, "Hello Hodiedah Clinics App I Will Give You Suggestion");
                    send_email.putExtra(Intent.EXTRA_TEXT, txt.getText().toString());
                    send_email.setType("message/rfc822");
                    send_email.createChooser(send_email, "Send Email");
                    startActivity(send_email);
                } catch (Exception e) {
                    Toast.makeText(Telling_Error_In_App.this, "عفواً لا يوجد تطبيق مراسلة في تلفونك !", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void send_by_whatApp(View view) {

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = txt.getText().toString();

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getApplicationContext(), "WhatsApp not Installed in your Mobile", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}