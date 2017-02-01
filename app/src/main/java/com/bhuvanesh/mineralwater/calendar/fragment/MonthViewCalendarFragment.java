package com.bhuvanesh.mineralwater.calendar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bhuvanesh.mineralwater.BaseFragment;
import com.bhuvanesh.mineralwater.R;
import com.bhuvanesh.mineralwater.calendar.adapter.MonthViewCalendarAdapter;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by bhuvanesh on 31-01-2017.
 */

public class MonthViewCalendarFragment extends BaseFragment {

    private GregorianCalendar monthlyCalendar, itemMonthlyCalendar;
    private MonthViewCalendarAdapter mMonthViewCalendarAdapter;

    private TextView title;

    public static MonthViewCalendarFragment newInstance() {
        return new MonthViewCalendarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthview_calendar, container, false);

        Locale.setDefault(Locale.US);
        monthlyCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
        itemMonthlyCalendar = (GregorianCalendar) monthlyCalendar.clone();

        title = (TextView) view.findViewById(R.id.title);

        RelativeLayout previous = (RelativeLayout) view.findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar();
            }
        });

        RelativeLayout next = (RelativeLayout) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();

            }
        });

        GridView gridViewCalendar = (GridView) view.findViewById(R.id.gridview);
        if (mMonthViewCalendarAdapter == null)
            mMonthViewCalendarAdapter = new MonthViewCalendarAdapter(monthlyCalendar);
        gridViewCalendar.setAdapter(mMonthViewCalendarAdapter);

        gridViewCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        refreshCalendar();
        return view;
    }

    private void setPreviousMonth() {
        if (monthlyCalendar.get(GregorianCalendar.MONTH) == monthlyCalendar.getActualMinimum(GregorianCalendar.MONTH)) {
            monthlyCalendar.set(monthlyCalendar.get(GregorianCalendar.YEAR) - 1,
                    monthlyCalendar.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            monthlyCalendar.set(GregorianCalendar.MONTH, monthlyCalendar.get(GregorianCalendar.MONTH) - 1);
        }

        System.out.println("prev month = " + monthlyCalendar.getTimeInMillis());
    }

    private void setNextMonth() {
        if (monthlyCalendar.get(GregorianCalendar.MONTH) == monthlyCalendar.getActualMaximum(GregorianCalendar.MONTH)) {
            monthlyCalendar.set(monthlyCalendar.get(GregorianCalendar.YEAR) + 1,
                    monthlyCalendar.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            monthlyCalendar.set(GregorianCalendar.MONTH, monthlyCalendar.get(GregorianCalendar.MONTH) + 1);
        }
        System.out.println("next month = " + monthlyCalendar.getTimeInMillis());
    }

    private void refreshCalendar() {
        title.setText(DateFormat.format("MMMM yyyy", monthlyCalendar));

        mMonthViewCalendarAdapter.refreshDays();
        mMonthViewCalendarAdapter.notifyDataSetChanged();
    }
}
