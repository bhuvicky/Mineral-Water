package com.bhuvanesh.mineralwater.calendar.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bhuvanesh.mineralwater.R;
import com.bhuvanesh.mineralwater.model.DateEvent;
import com.bhuvanesh.mineralwater.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by bhuvanesh on 31-01-2017.
 */

public class MonthViewCalendarAdapter extends BaseAdapter {

    private GregorianCalendar cMonth, pMonth, pMonthMaxSet, todayCalIns;
    private int firstDay, maxWeekNumber, prevMonthMaxDay, monthLength;
    private static List<DateEvent> onDateEventList = new ArrayList<>();

    public MonthViewCalendarAdapter(GregorianCalendar currentMonth) {
        todayCalIns = (GregorianCalendar) GregorianCalendar.getInstance();
        //timestamp consists of date, month, year, hour, min, sec, milliSec; but milliSec won't accurate between diff calendar instance
        //So, set milliseconds to zero
        clearTimeOnCalendarInstance(todayCalIns);
        System.out.println("today = " + todayCalIns.getTimeInMillis());
        cMonth = currentMonth;
        clearTimeOnCalendarInstance(cMonth);
        pMonth = (GregorianCalendar) cMonth.clone();
        cMonth.set(GregorianCalendar.DAY_OF_MONTH, 1);
    }

    @Override
    public int getCount() {
        return onDateEventList.size();
    }

    @Override
    public DateEvent getItem(int position) {
        return (position < onDateEventList.size() ? onDateEventList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dayView; View viewTodayMark;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        }
        dayView = (TextView) convertView.findViewById(R.id.date);
        viewTodayMark = convertView.findViewById(R.id.view_today_mark);

        long itemDate = onDateEventList.get(position).date;

        long today = todayCalIns.getTimeInMillis();
        if (itemDate == today)
            viewTodayMark.setVisibility(View.VISIBLE);
        else
            viewTodayMark.setVisibility(View.GONE);

        String date = DateUtil.getFormattedString(itemDate, DateUtil.DATE_TIME_FORMAT_TYPE_dd);

        // checking whether the day is in current month or not.
        if ((Integer.parseInt(date) > 1) && (position < firstDay)) {
            // setting offdays to white color.
            dayView.setTextColor(Color.WHITE);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else if ((Integer.parseInt(date) < 7) && (position > 28)) {
            dayView.setTextColor(Color.WHITE);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else {
            // setting curent month's days in blue color.
            dayView.setTextColor(Color.BLUE);
        }

        dayView.setText(date);

        return convertView;
    }

    public void refreshDays() {
        onDateEventList.clear();
        Locale.setDefault(Locale.US);
        pMonth = (GregorianCalendar) cMonth.clone();

        // month start day. ie; sun (1), mon (2), etc (oru date week la ethanavathu naala irruku)
        firstDay = cMonth.get(GregorianCalendar.DAY_OF_WEEK);
        System.out.println("first day = " + firstDay);

        // finding number of weeks in current month. (including prev, next)
        maxWeekNumber = cMonth.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
        System.out.println("cal: maxWeekNumber = " + maxWeekNumber);

        // allocating maximum row number for the gridview.
        monthLength = maxWeekNumber * 7;

        // previous month maximum day 31,30....
        prevMonthMaxDay = getPrevMonthMaxDay();

        /**
         * Calendar instance for getting a complete gridview including the three
         * month's (previous,current,next) dates.
         */
        pMonthMaxSet = (GregorianCalendar) pMonth.clone();

        //setting the start date as previous month's required date.
        pMonthMaxSet.set(GregorianCalendar.DAY_OF_MONTH, prevMonthMaxDay - firstDay + 2);

        for (int i = 0; i < monthLength; i++) {
            DateEvent event = new DateEvent();
            //event.date = String.valueOf(pMonthMaxSet.get(Calendar.DATE));
            event.date = pMonthMaxSet.getTimeInMillis();
            System.out.println("date millis = " + pMonthMaxSet.getTimeInMillis());
            onDateEventList.add(event);
            pMonthMaxSet.add(GregorianCalendar.DATE, 1);
        }
    }

    private int getPrevMonthMaxDay() {
        if (cMonth.get(GregorianCalendar.MONTH) == cMonth.getActualMinimum(GregorianCalendar.MONTH))
            pMonth.set(cMonth.get(GregorianCalendar.YEAR) - 1, cMonth.getActualMaximum(GregorianCalendar.MONTH), 1);
        else
            pMonth.set(GregorianCalendar.MONTH, cMonth.get(GregorianCalendar.MONTH) - 1);

        return pMonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    }

    private void clearTimeOnCalendarInstance(GregorianCalendar calendar) {
//        if we are looking for only date, all these parameters on calendar instance should set as zero.
        calendar.set(GregorianCalendar.MILLISECOND, 0);
        calendar.set(GregorianCalendar.SECOND, 0);
        calendar.set(GregorianCalendar.MINUTE, 0);
        calendar.set(GregorianCalendar.HOUR, 0);
    }
}
