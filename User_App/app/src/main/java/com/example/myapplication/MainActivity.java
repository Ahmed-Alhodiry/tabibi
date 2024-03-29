package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.myapplication.FirebaseCollection.CollectionName;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.net.URLEncoder;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    ImageSlider slider;
    SliderView sliderView;
    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle myToggle;
    NavigationView navigationView;
    private long time;
    private Toast backToast;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference clinicCollection = db.collection(CollectionName.CLINICS.name());

    private final static String ALL_SPECIALIZE="ALL_SPECIALIZE";
    private final static String SPECIALIZE="SPECIALIZE";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          slider = findViewById(R.id.slider);
          ArrayList<SlideModel> slideModels = new ArrayList<>();

          slideModels.add(new SlideModel(R.drawable.doctor_image1, ScaleTypes.FIT));
          slideModels.add(new SlideModel(R.drawable.doctor_image2, ScaleTypes.FIT));
          slideModels.add(new SlideModel(R.drawable.doctor_image3, ScaleTypes.FIT));
          slideModels.add(new SlideModel(R.drawable.doctor_image4, ScaleTypes.FIT));
          slideModels.add(new SlideModel(R.drawable.doctor_image5, ScaleTypes.FIT));
          slideModels.add(new SlideModel(R.drawable.doctor_image6, ScaleTypes.FIT));
          slideModels.add(new SlideModel(R.drawable.doctor_image7, ScaleTypes.FIT));
          slideModels.add(new SlideModel(R.drawable.oip, ScaleTypes.FIT));

          slider.setImageList(slideModels,ScaleTypes.FIT);


        myDrawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);
        myToggle = new ActionBarDrawerToggle(this,myDrawerLayout,R.string.open,R.string.close);
        myDrawerLayout.addDrawerListener(myToggle);
        myToggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();
                if (item.getItemId() == R.id.about_id){
                    myDrawerLayout.closeDrawer(navigationView);
                    Intent intent = new Intent(getApplicationContext(),Splash_Activity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

                }
                else if (id == R.id.share_id){
                    try {
                        myDrawerLayout.closeDrawer(navigationView);
                        Intent share_app = new Intent(Intent.ACTION_SEND);
                        share_app.setType("text/plain");
                        share_app.putExtra(Intent.EXTRA_TEXT,"مرحباً أصدقائي, هذا التطبيق رائع حيث أنه يوفر بيانات شاملة عن الدكاترة, أقترح عليكم تجربته"+" \n"+"\n Tabiby Application"+"");
                        Intent.createChooser(share_app,"مشاركة التطبيق");
                        startActivity(share_app);
                    }catch (Exception e){
                        e.getMessage();
                    }

                }
                else if (id == R.id.evaluation_id){


                    if (checkSwitchingOnOfConnection()){
                        try {
                            myDrawerLayout.closeDrawer(navigationView);
                            Intent Rate_app = new Intent(Intent.ACTION_VIEW);
                            Rate_app.setData(Uri.parse("market://details?id="+getApplicationContext().getPackageName()));
                            startActivity(Rate_app);
                        }catch (Exception e){
                            myDrawerLayout.closeDrawer(navigationView);
                            e.getMessage();
                        }
                    }else{
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.drawer),"!No internet Connection",Snackbar.LENGTH_LONG)
                                .setAction("CHECK IT", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));                                    }
                                });
                        snackbar.show();
                    }

                }

                else if (id == R.id.common_questions_id){
                    myDrawerLayout.closeDrawer(navigationView);
                    Intent intent = new Intent(getApplicationContext(),CommonQuestionsActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.telling_issue){
                    myDrawerLayout.closeDrawer(navigationView);
                    Intent intent = new Intent(getApplicationContext(),Telling_Error_In_App.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.whatsapp_id){
                    myDrawerLayout.closeDrawer(navigationView);
                    String toNumber = "775480089";
                    String url = "https://api.whatsapp.com/send?phone="+ toNumber;

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setPackage("com.whatsapp"); //com.whatsapp or com.whatsapp.w4b
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    /*
                    PackageManager pm=getPackageManager();
                    try {
                        Intent waIntent = new Intent(Intent.ACTION_SEND);
                        waIntent.setType("text/plain");
                        PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                        //Check if package exists or not. If not then code
                        //in catch block will be called
                        waIntent.setPackage("com.whatsapp");
                        waIntent.putExtra(Intent.EXTRA_TEXT, "مرحباً تطبيق طبيبي الطبي");
                        startActivity(Intent.createChooser(waIntent, "Share with"));
                    }
                    catch (PackageManager.NameNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "تطبيق واتساب الرسمي غير مثبت ", Toast.LENGTH_SHORT).show();
                    }


                    PackageManager packageManager = getApplicationContext().getPackageManager();
                    Intent i = new Intent(Intent.ACTION_VIEW);

                    try {
                        String phone = "00967775480089";
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

                    }*/
                }

                if (id == R.id.call_id){

                    String PhoneNum = "775480089";
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+PhoneNum));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "call", Toast.LENGTH_SHORT).show();

                }
                if (id == R.id.email) {
                    try {
                        String[] to = {"salahdoos77@gmail.com"};
                        Intent send_email = new Intent(Intent.ACTION_SEND);
                        send_email.putExtra(Intent.EXTRA_EMAIL, to);
                        send_email.putExtra(Intent.EXTRA_SUBJECT, "مرحباً تطبيق طبيبي الطبي");
                        send_email.putExtra(Intent.EXTRA_TEXT, "السلام عليكم ورحمة الله وبركاته");
                        send_email.setType("message/rfc822");
                        send_email.createChooser(send_email, "Send Email");
                        startActivity(send_email);
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "عفواً لا يوجد تطبيق مراسلة في تلفونك !", Toast.LENGTH_SHORT).show();
                    }
                }

                return true;
            }
        });


    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(myToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (time + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            backToast.cancel();
            return;
        }
        else {
            backToast = Toast.makeText(this, "اضغط مرة أخرى للخروج من التطبيق", Toast.LENGTH_SHORT);
            backToast.show();
        }
        time=System.currentTimeMillis();

    }

    public void Search_Doctors_By_Name(View view) {

        Intent intent = new Intent(getApplicationContext(),SearchDoctorByName.class);
        intent.putExtra(SPECIALIZE , ALL_SPECIALIZE);
        startActivity(intent);
        }

    public void Search_Doctors_In_Specific_Department(View view) {
        Intent intent = new Intent(getApplicationContext(),SearchDoctorByDepartment.class);
        startActivity(intent);
    }

    private  boolean checkSwitchingOnOfConnection()
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true ;
        }
        else {
            return  false ;
        }
    }

    public void openDrawer(View view) {
        myDrawerLayout.open();
    }
}