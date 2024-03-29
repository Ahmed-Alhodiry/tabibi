package com.salah.hodiedahclinicsapp_doctors2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_WorkTime#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Doctor_WorkTime extends Fragment {

    com.google.android.material.checkbox.MaterialCheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7;
    Switch aSwitch;
    View layout,layout2;
    Dialog dialog;
    androidx.appcompat.widget.AppCompatButton btn_saveChanges3;

    Animation animation;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Doctor_WorkTime() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Doctor_WorkTime.
     */
    // TODO: Rename and change types and number of parameters
    public static com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_WorkTime newInstance(String param1, String param2) {
        com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_WorkTime fragment = new com.salah.hodiedahclinicsapp_doctors2.Fragment_Doctor_WorkTime();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment__doctor__work_time, container, false);

        dialog=new Dialog(container.getContext());

        aSwitch = root.findViewById(R.id.switch3);
        cb1 = root.findViewById(R.id.Saturday);
        cb2 = root.findViewById(R.id.Sunday);
        cb3 = root.findViewById(R.id.Monday);
        cb4 = root.findViewById(R.id.Tuesday);
        cb5 = root.findViewById(R.id.Wednesday);
        cb6 = root.findViewById(R.id.Thursday);
        cb7 = root.findViewById(R.id.Friday);
        btn_saveChanges3 = root.findViewById(R.id.btn_saveChanges3);
        layout = root.findViewById(R.id.container_layout);
        layout2 = root.findViewById(R.id.container_layout2);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    anim();
                    cb1.setEnabled(true);
                    cb2.setEnabled(true);
                    cb3.setEnabled(true);
                    cb4.setEnabled(true);
                    cb5.setEnabled(true);
                    cb6.setEnabled(true);
                    cb7.setEnabled(true);

                    cb1.setTextColor(R.color.blue);
                    cb2.setTextColor(R.color.blue);
                    cb3.setTextColor(R.color.blue);
                    cb4.setTextColor(R.color.blue);
                    cb5.setTextColor(R.color.blue);
                    cb6.setTextColor(R.color.blue);
                    cb7.setTextColor(R.color.blue);

                    btn_saveChanges3.setEnabled(true);
                    btn_saveChanges3.setBackground(getResources().getDrawable(R.drawable.btn_shape2));
                    aSwitch.setBackgroundColor(getResources().getColor(R.color.about_us_background));
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                    layout2.setBackgroundColor(getResources().getColor(R.color.white));
                }else {
                    cb1.setEnabled(false);
                    cb2.setEnabled(false);
                    cb3.setEnabled(false);
                    cb4.setEnabled(false);
                    cb5.setEnabled(false);
                    cb6.setEnabled(false);
                    cb7.setEnabled(false);

                    cb1.setTextColor(R.color.black);
                    cb2.setTextColor(R.color.black);
                    cb3.setTextColor(R.color.black);
                    cb4.setTextColor(R.color.black);
                    cb5.setTextColor(R.color.black);
                    cb6.setTextColor(R.color.black);
                    cb7.setTextColor(R.color.black);

                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                    cb5.setChecked(false);
                    cb6.setChecked(false);
                    cb7.setChecked(false);

                    btn_saveChanges3.setEnabled(false);
                    btn_saveChanges3.setBackground(getResources().getDrawable(R.drawable.btn_shape3));
                    layout.setBackgroundColor(getResources().getColor(R.color.colordisable));
                    layout2.setBackgroundColor(getResources().getColor(R.color.colordisable));
                    aSwitch.setBackgroundColor(getResources().getColor(R.color.colordisable));
                }
            }
        });

        btn_saveChanges3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                OpenDialog1();
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
        aSwitch.setChecked(false);
        aSwitch.callOnClick();

    }
    private void anim() {

        animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale);
        layout.setAnimation(animation);
    }

}