package com.thevolume360.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    public static Date getCurrentDate() throws ParseException {
        return getDateFormat().parse(getCurrentDateString());
    }

    public static String getCurrentDateString() {
        return getDateFormat().format(Calendar.getInstance().getTime());
    }

    private static DateFormat getDateFormat() {
        return new SimpleDateFormat("MM/dd/yyyy");
    }
}
