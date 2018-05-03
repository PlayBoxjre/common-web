package com.kong.support;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 将 unix time (s) 转换成指定格式的字符串
     * @param unixTime  unix 秒数
     * @param format    时间字符串的格式
     * @return
     */
    public static String unixTime2FormatString(long unixTime, String format) {
        unixTime *= 1000;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(unixTime);
        Date time = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(time);
    }
}
