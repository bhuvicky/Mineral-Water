package com.bhuvanesh.mineralwater.calendar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.mineralwater.BaseFragment;
import com.bhuvanesh.mineralwater.R;

/**
 * Created by bhuvanesh on 02-02-2017.
 */

public class DayViewCalendarFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dayview_calendar, container, false);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.menu_dayview).setVisible(false);
        inflater.inflate(R.menu.menu_calendar, menu);
    }
}
