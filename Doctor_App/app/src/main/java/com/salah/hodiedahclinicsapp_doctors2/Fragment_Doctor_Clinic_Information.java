package com.salah.hodiedahclinicsapp_doctors2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Clinic_Information#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Doctor_Clinic_Information extends Fragment {


    Switch aSwitch;
    View layout;
    EditText Clinic_Info_ClinicName, Clinic_Info_ClinicAddress, Clinic_Info_ClinicNumber, Clinic_Info_BookingPrice;
    androidx.appcompat.widget.AppCompatButton btnSaveChanges;
    Dialog dialog;

    Animation animation;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Doctor_Clinic_Information() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Doctor_Clinic_Information.
     */
    // TODO: Rename and change types and number of parameters
    public static com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Clinic_Information newInstance(String param1, String param2) {
        com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Clinic_Information fragment = new com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Clinic_Information();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment__doctor__clinic__information, container, false);

        dialog=new Dialog(container.getContext());

        aSwitch = root.findViewById(R.id.switch2);
        Clinic_Info_ClinicName = root.findViewById(R.id.Clinic_Info_ClinicName);
        Clinic_Info_ClinicAddress = root.findViewById(R.id.Clinic_Info_ClinicAddress);
        Clinic_Info_ClinicNumber = root.findViewById(R.id.Clinic_Info_ClinicNumber);
        Clinic_Info_BookingPrice = root.findViewById(R.id.Clinic_Info_BookingPrice);
        layout = root.findViewById(R.id.container_layout);
        btnSaveChanges = root.findViewById(R.id.btn_saveChanges2);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    anim();
                    resetFields();

                    Clinic_Info_ClinicName.setEnabled(true);
                    Clinic_Info_ClinicAddress.setEnabled(true);
                    Clinic_Info_ClinicNumber.setEnabled(true);
                    Clinic_Info_BookingPrice.setEnabled(true);
                    btnSaveChanges.setEnabled(true);

                    Clinic_Info_ClinicName.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    Clinic_Info_ClinicAddress.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    Clinic_Info_ClinicNumber.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    Clinic_Info_BookingPrice.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    btnSaveChanges.setBackground(getResources().getDrawable(R.drawable.btn_shape2));
                    aSwitch.setBackgroundColor(getResources().getColor(R.color.about_us_background));
                    layout.setBackgroundColor(getResources().getColor(R.color.white));

                }else {
                    resetFields();
                    anim();
                    Clinic_Info_ClinicName.setEnabled(false);
                    Clinic_Info_ClinicAddress.setEnabled(false);
                    Clinic_Info_ClinicNumber.setEnabled(false);
                    Clinic_Info_BookingPrice.setEnabled(false);
                    btnSaveChanges.setEnabled(false);

                    Clinic_Info_ClinicName.setBackground(getResources().getDrawable(R.drawable.shape3));
                    Clinic_Info_ClinicAddress.setBackground(getResources().getDrawable(R.drawable.shape3));
                    Clinic_Info_ClinicNumber.setBackground(getResources().getDrawable(R.drawable.shape3));
                    Clinic_Info_BookingPrice.setBackground(getResources().getDrawable(R.drawable.shape3));
                    btnSaveChanges.setBackground(getResources().getDrawable(R.drawable.btn_shape3));
                    layout.setBackgroundColor(getResources().getColor(R.color.colordisable));

                    aSwitch.setBackgroundColor(getResources().getColor(R.color.colordisable));
                 }
            }
        });

        btnSaveChanges.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(checkFields())
                {
                    if(checkSwitchingOnOfConnection())
                    {
                        OpenDialog1();
                    }
                    else
                    {
                        Snackbar snackbar = Snackbar.make(root.findViewById(R.id.optRoundCardView),"!No internet Connection",Snackbar.LENGTH_LONG)
                                .setAction("CHECK IT", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));                                    }
                                });
                        snackbar.show();                    }
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext()  , "please fill all fields to save your edited information" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        Clinic_Info_ClinicName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (
                        Clinic_Info_ClinicName.getText().toString().contains("1")
                                || Clinic_Info_ClinicName.getText().toString().contains("2")
                                || Clinic_Info_ClinicName.getText().toString().contains("3")
                                || Clinic_Info_ClinicName.getText().toString().contains("4")
                                || Clinic_Info_ClinicName.getText().toString().contains("5")
                                || Clinic_Info_ClinicName.getText().toString().contains("6")
                                || Clinic_Info_ClinicName.getText().toString().contains("7")
                                || Clinic_Info_ClinicName.getText().toString().contains("8")
                                || Clinic_Info_ClinicName.getText().toString().contains("9")
                                || Clinic_Info_ClinicName.getText().toString().contains("0")
                                || Clinic_Info_ClinicName.getText().toString().contains("@")
                                || Clinic_Info_ClinicName.getText().toString().contains("#")
                                || Clinic_Info_ClinicName.getText().toString().contains("$")
                                || Clinic_Info_ClinicName.getText().toString().contains(">")
                                || Clinic_Info_ClinicName.getText().toString().contains("<")
                                || Clinic_Info_ClinicName.getText().toString().contains("?")
                                || Clinic_Info_ClinicName.getText().toString().contains("&")
                                || Clinic_Info_ClinicName.getText().toString().contains("*")

                ){
                    Toast.makeText(getContext(), "Sorry!! your Clinic Name must'nt have numbers or signs", Toast.LENGTH_SHORT).show();
                    Clinic_Info_ClinicName.setText("");
                    Clinic_Info_ClinicName.setFocusable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return root;
    }

    private void OpenDialog1() {
        dialog.setContentView(R.layout.dialog_save_changes);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageViewClose = dialog.findViewById(R.id.Error_icon);
        Button btnOk = dialog.findViewById(R.id. btn_ok);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getContext(), "Dialog Close", Toast.LENGTH_SHORT).show();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        resetFields();
        aSwitch.setChecked(false);
        aSwitch.callOnClick();
    }
    private void anim() {

        animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale);
        layout.setAnimation(animation);
    }

    private boolean checkFields()
    {
        int number=0 ;
        if (Clinic_Info_ClinicName.getText().toString().isEmpty()){
            Clinic_Info_ClinicName.setError("Please Enter Clinic Name");
        }
        else
        {
            number++;
        }
        if(Clinic_Info_ClinicAddress.getText().toString().isEmpty()){
            Clinic_Info_ClinicAddress.setError("Please Enter Clinic Address");   }
        else
        {
            number++ ;
        }
        if(Clinic_Info_ClinicNumber.getText().toString().isEmpty()){
            Clinic_Info_ClinicNumber.setError("Please Enter Clinic Number");   }
        else
        {
            number++;
        }
        if(Clinic_Info_BookingPrice.getText().toString().isEmpty()){
            Clinic_Info_BookingPrice.setError("Please Enter Booking Price");   }
        else
        {
            number++  ;
        }
        return (number==4);
    }

    private  void resetFields()
    {
        Clinic_Info_ClinicName.setText("");
        Clinic_Info_ClinicAddress.setText("");
        Clinic_Info_ClinicNumber.setText("");
        Clinic_Info_BookingPrice.setText("");
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