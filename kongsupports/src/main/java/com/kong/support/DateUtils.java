package com.kong.support;

import com.sun.istack.internal.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String unixTime2FormatString(long unixTime, @NotNull String format) {
        unixTime *= 1000;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(unixTime);
        Date time = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(time);
    }
}
