package com.eternal.base.utils;

import com.alibaba.android.arouter.utils.Consts;
import com.eternal.framework.utils.constant.TimeConstants;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {
    public static String getCurrentTimeZone() {
        return createGmtOffsetString(true, true, TimeZone.getDefault().getRawOffset());
    }

    public static String getCurrentTimeZoneNumber() {
        return String.valueOf((int) (((double) TimeZone.getDefault().getOffset(new Date().getTime())) / 3600000.0d));
    }

    public static String getCurrentTimeZone3() {
        double offset = ((double) TimeZone.getDefault().getOffset(new Date().getTime())) / 3600000.0d;
        String format = new DecimalFormat("00.00").format(offset);
        StringBuilder sb = new StringBuilder();
        sb.append("GMT");
        sb.append(offset >= 0.0d ? "+" : "");
        sb.append(format);
        return sb.toString().replace(Consts.DOT, ":");
    }

    public static String createGmtOffsetString(boolean z, boolean z2, int i) {
        char c;
        int i2 = i / TimeConstants.MIN;
        if (i2 < 0) {
            c = '-';
            i2 = -i2;
        } else {
            c = '+';
        }
        StringBuilder sb = new StringBuilder(9);
        if (z) {
            sb.append("GMT");
        }
        sb.append(c);
        appendNumber(sb, 2, i2 / 60);
        if (z2) {
            sb.append(':');
        }
        appendNumber(sb, 2, i2 % 60);
        return sb.toString();
    }

    private static void appendNumber(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i2);
        for (int i3 = 0; i3 < i - num.length(); i3++) {
            sb.append('0');
        }
        sb.append(num);
    }

    public static String dateToStamp(String str) throws ParseException {
        return String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(str).getTime());
    }

    public static String formatTime(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Timestamp(j));
    }

    public static long getTimestamp(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(i, i2);
        return instance.getTimeInMillis();
    }

    public static ArrayList<String> getTimeList(int i, int i2, boolean z) {
        ArrayList<String> arrayList = new ArrayList<>((i2 - i) + 1);
        if (z) {
            if (i < 10) {
                int min = Math.min(i2, 10);
                while (i < min) {
                    arrayList.add("0" + i);
                    i++;
                }
            }
            while (i <= i2) {
                arrayList.add(String.valueOf(i));
                i++;
            }
        } else {
            while (i <= i2) {
                arrayList.add(String.valueOf(i));
                i++;
            }
        }
        return arrayList;
    }
}
