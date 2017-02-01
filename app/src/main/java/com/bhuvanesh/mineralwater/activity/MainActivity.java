package com.bhuvanesh.mineralwater.activity;

import android.os.Bundle;

import com.bhuvanesh.mineralwater.BaseActivity;
import com.bhuvanesh.mineralwater.R;
import com.bhuvanesh.mineralwater.calendar.fragment.MonthViewCalendarFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replace(R.id.rlayout_container, MonthViewCalendarFragment.newInstance());
    }

}
