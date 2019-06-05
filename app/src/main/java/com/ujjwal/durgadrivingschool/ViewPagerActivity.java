package com.ujjwal.durgadrivingschool;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ujjwal.durgadrivingschool.Adapter.ViewPagerAdapter;
import com.ujjwal.durgadrivingschool.Fragment.LoginFragment;
import com.ujjwal.durgadrivingschool.Fragment.SignupFragment;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);


    }
}
