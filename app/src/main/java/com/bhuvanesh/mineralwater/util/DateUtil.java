package com.bhuvanesh.mineralwater.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by bhuvanesh on 31-01-2017.
 */

public final class DateUtil {

    public static final String DATE_TIME_FORMAT_TYPE_dd = "dd";
    public static final String DATE_TIME_FORMAT_TYPE_dd_MM_yyyy = "dd/MM/yyyy";

    public static String getFormattedString(long timeStamp, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return dateFormat.format(calendar.getTime());
    }
}
