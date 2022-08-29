package com.zhihu.matisse.internal.utils;

import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

final class ExifInterfaceCompat {
    private static final int EXIF_DEGREE_FALLBACK_VALUE = -1;
    private static final String TAG = "ExifInterfaceCompat";

    private ExifInterfaceCompat() {
    }

    public static ExifInterface newInstance(String str) throws IOException {
        Objects.requireNonNull(str, "filename should not be null");
        return new ExifInterface(str);
    }

    private static Date getExifDateTime(String str) {
        try {
            String attribute = newInstance(str).getAttribute("DateTime");
            if (TextUtils.isEmpty(attribute)) {
                return null;
            }
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return simpleDateFormat.parse(attribute);
            } catch (ParseException e) {
                Log.d(TAG, "failed to parse date taken", e);
                return null;
            }
        } catch (IOException e2) {
            Log.e(TAG, "cannot read exif", e2);
            return null;
        }
    }

    public static long getExifDateTimeInMillis(String str) {
        Date exifDateTime = getExifDateTime(str);
        if (exifDateTime == null) {
            return -1;
        }
        return exifDateTime.getTime();
    }

    public static int getExifOrientation(String str) {
        try {
            int attributeInt = newInstance(str).getAttributeInt("Orientation", -1);
            if (attributeInt == -1) {
                return 0;
            }
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        } catch (IOException e) {
            Log.e(TAG, "cannot read exif", e);
            return -1;
        }
    }
}
