package com.eternal.data;

import android.content.Context;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {
    public static DateFormat getShortHourUsingAndroidOsDateTimeSetting(Context context) {
        if (android.text.format.DateFormat.is24HourFormat(context)) {
            return new SimpleDateFormat("H:mm", Locale.ENGLISH);
        }
        return new SimpleDateFormat("h:mma", Locale.ENGLISH);
    }

    public static DateFormat getShortDateTimeFormatUsingAndroidOSDateTimeSetting(Context context) {
        if (android.text.format.DateFormat.is24HourFormat(context)) {
            return new SimpleDateFormat("yy/MM/dd H:mm", Locale.ENGLISH);
        }
        return new SimpleDateFormat("MM/dd/yy h:mm a", Locale.ENGLISH);
    }
}
