package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Welcome_Screens_Adapter extends PagerAdapter {

    Context ctx;

    public Welcome_Screens_Adapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view;
        switch (position) {
            case 0:
                 view = layoutInflater.inflate(R.layout.screen_1,container,false);
                container.addView(view);
                Button btn = view.findViewById(R.id.start_btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Welcome_Screens.viewPager.setCurrentItem(position+1);
                    }
                });
                break;
            case 1:
                 view = layoutInflater.inflate(R.layout.screen_2,container,false);
                container.addView(view);
                Button btn2 = view.findViewById(R.id.start_btn);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Welcome_Screens.viewPager.setCurrentItem(position+1);
                    }
                });
                break;
            case 2:
                view = layoutInflater.inflate(R.layout.screen_3,container,false);
                container.addView(view);
                Button btn3 = view.findViewById(R.id.start_btn);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ctx, Patient_SignIn_and_SignUp.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(intent);
                    }
                });
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
