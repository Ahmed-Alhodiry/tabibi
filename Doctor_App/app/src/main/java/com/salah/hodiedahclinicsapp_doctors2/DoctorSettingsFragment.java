package com.salah.hodiedahclinicsapp_doctors2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.salah.hodiedahclinicsapp_doctors2.DoctorSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorSettingsFragment extends Fragment {

    TextView account_settings,rate_app,share_app,tell_error,common_questions,about_us;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoctorSettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoctorSettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static com.salah.hodiedahclinicsapp_doctors2.DoctorSettingsFragment newInstance(String param1, String param2) {
        com.salah.hodiedahclinicsapp_doctors2.DoctorSettingsFragment fragment = new com.salah.hodiedahclinicsapp_doctors2.DoctorSettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_doctor_settings, container, false);

        rate_app = root.findViewById(R.id.txt_rate_app);
        account_settings = root.findViewById(R.id.account_settings);
        share_app = root.findViewById(R.id.txt_share_app);
        tell_error = root.findViewById(R.id.txt_tell_error);
        common_questions = root.findViewById(R.id.txt_common_questions);
        about_us = root.findViewById(R.id.txt_about_us);


        rate_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSwitchingOnOfConnection()){
                    try {
                        Intent Rate_app = new Intent(Intent.ACTION_VIEW);
                        Rate_app.setData(Uri.parse("market://details?id="+getContext().getPackageName()));
                        startActivity(Rate_app);
                    }catch (Exception e){
                        e.getMessage();
                    }
                }else{
                    Snackbar snackbar = Snackbar.make(root.findViewById(R.id.layout_settings),"!No internet Connection",Snackbar.LENGTH_LONG)
                            .setAction("CHECK IT", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                }
                            });
                    snackbar.show();
                }
            }
        });
        account_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(getContext(), "Account settings", Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }
            }
        });
        share_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent share_app = new Intent(Intent.ACTION_SEND);
                    share_app.setType("text/plain");
                    share_app.putExtra(Intent.EXTRA_TEXT,"Hi My Friend , this is a great application to give you full information about all the doctors in Hodiedah city, I suggest you to try it"+" \n"+"\n Hodiedah Clinics Application"+"https://play.google.com/store/apps/details?id="+getContext().getPackageName());
                    Intent.createChooser(share_app,"مشاركة التطبيق");
                    startActivity(share_app);
                }catch (Exception e){
                    e.getMessage();
                }
            }
        });
        tell_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), com.salah.hodiedahclinicsapp_doctors2.Telling_Error_In_App.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Telling an Error in App", Toast.LENGTH_SHORT).show();
            }
        });
        common_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CommonQuestionsActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Common Questions", Toast.LENGTH_SHORT).show();
            }
        });
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "About us", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    private  boolean checkSwitchingOnOfConnection()
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true ;
        }
        else {
            return  false ;
        }
    }
}