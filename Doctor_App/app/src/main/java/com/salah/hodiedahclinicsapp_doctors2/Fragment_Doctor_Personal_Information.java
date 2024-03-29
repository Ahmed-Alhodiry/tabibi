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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Personal_Information#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Doctor_Personal_Information extends Fragment {

    Switch aSwitch;
    View layout,layout_specialize;
    EditText personal_Info_DoctorName, personal_Info_DoctorEmail, personal_Info_DoctorPhone, personal_Info_DoctorEducationDegree, personal_Info_KuraimyAccountNumber;
    androidx.appcompat.widget.AppCompatButton btnSaveChanges;
    Dialog dialog;
    Spinner spinner;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    Animation animation;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Doctor_Personal_Information() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Doctor_Personal_Information.
     */
    // TODO: Rename and change types and number of parameters
    public static com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Personal_Information newInstance(String param1, String param2) {
        com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Personal_Information fragment = new com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_Personal_Information();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment__doctor__personal__information, container, false);

        dialog=new Dialog(container.getContext());

        aSwitch = root.findViewById(R.id.switch1);
        personal_Info_DoctorName = root.findViewById(R.id.personal_Info_DoctorName);
        personal_Info_DoctorEmail = root.findViewById(R.id.personal_Info_DoctorEmail);
        personal_Info_DoctorPhone = root.findViewById(R.id.personal_Info_DoctorPhone);
        personal_Info_DoctorEducationDegree = root.findViewById(R.id.personal_Info_DoctorEducationDegree);
        personal_Info_KuraimyAccountNumber = root.findViewById(R.id.personal_Info_KuraimyAccountNumber);
        btnSaveChanges = root.findViewById(R.id.btn_saveChanges);
        layout = root.findViewById(R.id.container_layout);
        layout_specialize = root.findViewById(R.id.layout_specialize);
        spinner = root.findViewById(R.id.personal_Info_SpinnerSpecialize);



        Spinner spinner = (Spinner) root.findViewById(R.id.personal_Info_SpinnerSpecialize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.specializes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    anim();
                    resetFields();

                    personal_Info_DoctorName.setEnabled(true);
                    layout_specialize.setEnabled(true);
                    spinner.setEnabled(true);
                    personal_Info_DoctorEmail.setEnabled(true);
                    personal_Info_DoctorPhone.setEnabled(true);
                    personal_Info_DoctorEducationDegree.setEnabled(true);
                    personal_Info_KuraimyAccountNumber.setEnabled(true);
                    btnSaveChanges.setEnabled(true);

                    personal_Info_DoctorName.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    layout_specialize.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    personal_Info_DoctorEmail.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    personal_Info_DoctorPhone.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    personal_Info_DoctorEducationDegree.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    personal_Info_KuraimyAccountNumber.setBackground(getResources().getDrawable(R.drawable.layout_shape));
                    btnSaveChanges.setBackground(getResources().getDrawable(R.drawable.btn_shape2));
                    aSwitch.setBackgroundColor(getResources().getColor(R.color.about_us_background));
                    layout.setBackgroundColor(getResources().getColor(R.color.white));

                }else {
                    resetFields();
                    //anim();
                    personal_Info_DoctorName.setEnabled(false);
                    layout_specialize.setEnabled(false);
                    spinner.setEnabled(false);
                    personal_Info_DoctorEmail.setEnabled(false);
                    personal_Info_DoctorPhone.setEnabled(false);
                    personal_Info_DoctorEducationDegree.setEnabled(false);
                    personal_Info_KuraimyAccountNumber.setEnabled(false);
                    btnSaveChanges.setEnabled(false);

                    personal_Info_DoctorName.setBackground(getResources().getDrawable(R.drawable.shape3));
                    layout_specialize.setBackground(getResources().getDrawable(R.drawable.shape3));
                    personal_Info_DoctorEmail.setBackground(getResources().getDrawable(R.drawable.shape3));
                    personal_Info_DoctorPhone.setBackground(getResources().getDrawable(R.drawable.shape3));
                    personal_Info_DoctorEducationDegree.setBackground(getResources().getDrawable(R.drawable.shape3));
                    personal_Info_KuraimyAccountNumber.setBackground(getResources().getDrawable(R.drawable.shape3));
                    btnSaveChanges.setBackground(getResources().getDrawable(R.drawable.btn_shape3));
                    layout.setBackgroundColor(getResources().getColor(R.color.colordisable));

                    aSwitch.setBackgroundColor(getResources().getColor(R.color.colordisable));
                }
            }
        });

        personal_Info_DoctorName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (
                        personal_Info_DoctorName.getText().toString().contains("1")
                                || personal_Info_DoctorName.getText().toString().contains("2")
                                || personal_Info_DoctorName.getText().toString().contains("3")
                                || personal_Info_DoctorName.getText().toString().contains("4")
                                || personal_Info_DoctorName.getText().toString().contains("5")
                                || personal_Info_DoctorName.getText().toString().contains("6")
                                || personal_Info_DoctorName.getText().toString().contains("7")
                                || personal_Info_DoctorName.getText().toString().contains("8")
                                || personal_Info_DoctorName.getText().toString().contains("9")
                                || personal_Info_DoctorName.getText().toString().contains("0")
                                || personal_Info_DoctorName.getText().toString().contains("@")
                                || personal_Info_DoctorName.getText().toString().contains("#")
                                || personal_Info_DoctorName.getText().toString().contains("$")
                                || personal_Info_DoctorName.getText().toString().contains(">")
                                || personal_Info_DoctorName.getText().toString().contains("<")
                                || personal_Info_DoctorName.getText().toString().contains("?")
                                || personal_Info_DoctorName.getText().toString().contains("&")
                                || personal_Info_DoctorName.getText().toString().contains("*")

                ){
                    Toast.makeText(getContext(), "Sorry!! your Name must'nt have numbers or signs", Toast.LENGTH_SHORT).show();
                    personal_Info_DoctorName.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
                        if(personal_Info_DoctorEmail.getText().toString().trim().matches(emailPattern))
                        {
                            OpenDialog1();
                        }
                        else
                        {
                            personal_Info_DoctorEmail.setError("Please Enter Correct Email");
                            personal_Info_DoctorEmail.requestFocus();
                        }
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
        if (personal_Info_DoctorName.getText().toString().isEmpty()){
            personal_Info_DoctorName.setError("Please Enter Doctor Name");
        }
        else
        {
            number++;
        }
        if(spinner.getSelectedItem().toString().equals("Your Specialization")){
            //
        }
        else
        {
            number++ ;
        }
        if(personal_Info_DoctorEmail.getText().toString().isEmpty()){
            personal_Info_DoctorEmail.setError("Please Enter Doctor Email");   }
        else
        {
            number++;
        }
        if(personal_Info_DoctorPhone.getText().toString().isEmpty()){
            personal_Info_DoctorPhone.setError("Please Enter Your Number");   }
        else
        {
            number++  ;
        }
        if(personal_Info_DoctorEducationDegree.getText().toString().isEmpty()){
            personal_Info_DoctorEducationDegree.setError("Please Enter Your Education Degree");   }
        else
        {
            number++  ;
        }
        if(personal_Info_KuraimyAccountNumber.getText().toString().isEmpty()){
            personal_Info_KuraimyAccountNumber.setError("Please Enter Your Kuraimi Account");   }
        else
        {
            number++  ;
        }
        return (number==6);
    }

    private  void resetFields()
    {
        personal_Info_DoctorName.setText("");
        spinner.setSelection(0);
        personal_Info_DoctorEmail.setText("");
        personal_Info_DoctorPhone.setText("");
        personal_Info_DoctorEducationDegree.setText("");
        personal_Info_KuraimyAccountNumber.setText("");
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