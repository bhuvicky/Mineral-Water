package com.bhuvanesh.mineralwater.activity;

import android.os.Bundle;

import com.bhuvanesh.mineralwater.BaseActivity;
import com.bhuvanesh.mineralwater.R;
import com.bhuvanesh.mineralwater.calendar.fragment.MonthViewCalendarFragment;
import com.bhuvanesh.mineralwater.profile.fragment.CustomerListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replace(R.id.rlayout_container, CustomerListFragment.newInstance());
    }

}
