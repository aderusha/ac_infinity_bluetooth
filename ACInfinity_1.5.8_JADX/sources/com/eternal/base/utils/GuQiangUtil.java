package com.eternal.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.text.Typography;

public class GuQiangUtil {
    public static String formatCsvTime(long j) {
        return new SimpleDateFormat("h:mm aa @ MMM dd", Locale.ENGLISH).format(new Date(j)).toUpperCase();
    }

    public static String stringForTime(int i) {
        Object obj;
        Object obj2;
        Object obj3;
        int i2 = i % 60;
        int i3 = i / 60;
        if (i3 > 0) {
            StringBuilder sb = new StringBuilder();
            if (i3 > 9) {
                obj2 = Integer.valueOf(i3);
            } else {
                obj2 = "0" + i3;
            }
            sb.append(obj2);
            sb.append(":");
            if (i2 > 9) {
                obj3 = Integer.valueOf(i2);
            } else {
                obj3 = "0" + i2;
            }
            sb.append(obj3);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("00:");
        if (i2 > 9) {
            obj = Integer.valueOf(i2);
        } else {
            obj = "0" + i2;
        }
        sb2.append(obj);
        return sb2.toString();
    }

    public static String stringForTimeNoZero(int i) {
        Object obj;
        Object obj2;
        int i2 = i % 60;
        int i3 = i / 60;
        if (i3 > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(i3);
            sb.append(":");
            if (i2 > 9) {
                obj2 = Integer.valueOf(i2);
            } else {
                obj2 = "0" + i2;
            }
            sb.append(obj2);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("0:");
        if (i2 > 9) {
            obj = Integer.valueOf(i2);
        } else {
            obj = "0" + i2;
        }
        sb2.append(obj);
        return sb2.toString();
    }

    public static String stringForTimeBySched(int i) {
        boolean z;
        Object obj;
        int i2 = i % 60;
        int i3 = i / 60;
        int i4 = 12;
        if (i3 >= 12) {
            i3 -= 12;
            z = true;
        } else {
            z = false;
        }
        if (i3 > 0 && i3 <= 12) {
            i4 = i3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i4);
        sb.append(":");
        if (i2 > 9) {
            obj = Integer.valueOf(i2);
        } else {
            obj = "0" + i2;
        }
        sb.append(obj);
        sb.append(z ? " PM" : " AM");
        return sb.toString();
    }

    public static String stringForTimeByAmPm(int i) {
        int i2 = i % 60;
        int i3 = i / 60;
        if (i3 == 0) {
            return String.format(Locale.ENGLISH, "12:%02d AM", new Object[]{Integer.valueOf(i2)});
        } else if (i3 >= 12) {
            if (i3 == 24) {
                i3 = 11;
                i2 = 59;
            } else if (i3 > 12) {
                i3 -= 12;
            }
            return String.format(Locale.ENGLISH, "%d:%02d PM", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
        } else {
            return String.format(Locale.ENGLISH, "%d:%02d AM", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
        }
    }

    public static String stringForTimeByCycle(int i) {
        int i2 = i % 60;
        int i3 = i / 60;
        if (i3 == 0) {
            return i2 + "MIN";
        } else if (i2 == 0) {
            if (i3 == 1) {
                return "1HR";
            }
            return i3 + "HRS";
        } else if (i3 < 1) {
            return i3 + "HR : " + i2 + "MIN";
        } else {
            return i3 + "HRS : " + i2 + "MIN";
        }
    }

    public static String stringForTimeByCycleSpace(int i) {
        int i2 = i % 60;
        int i3 = i / 60;
        if (i3 == 0) {
            return i2 + " MIN";
        } else if (i2 == 0) {
            if (i3 == 1) {
                return "1 HR";
            }
            return i3 + " HRS";
        } else if (i3 < 1) {
            return i3 + " HR : " + i2 + " MIN";
        } else {
            return i3 + " HRS : " + i2 + " MIN";
        }
    }

    public static String getCurrentTimeFormatToStringByAmPm() {
        return new SimpleDateFormat("K:mm aa", Locale.ENGLISH).format(new Date());
    }

    public static String getCurrentTimeFormatNoAmPm() {
        return new SimpleDateFormat("K:mm", Locale.ENGLISH).format(new Date());
    }

    public static String formatTimeToHourAndMin(long j) {
        String str;
        String str2;
        StringBuilder sb;
        StringBuilder sb2;
        String str3;
        int i = ((int) j) / 3600;
        int i2 = ((int) (j % 3600)) / 60;
        String str4 = "";
        if (i > 0) {
            if (i > 1) {
                sb2.append(i);
                str3 = "HRS";
            } else {
                sb2 = new StringBuilder();
                sb2.append(i);
                str3 = "HR";
            }
            sb2.append(str3);
            str = sb2.toString();
        } else {
            str = str4;
        }
        if (i2 > 0) {
            if (i2 > 1) {
                sb = new StringBuilder();
                sb.append(i2);
                str2 = "MINS";
            } else {
                sb = new StringBuilder();
                sb.append(i2);
                str2 = "MIN";
            }
            sb.append(str2);
            str4 = sb.toString();
        }
        return str + " : " + str4;
    }

    public static String formatTimeToHourAndMinByAdvance(String str) {
        StringBuilder sb;
        String str2;
        int parseInt = Integer.parseInt(str);
        int i = parseInt / 60;
        int i2 = parseInt % 60;
        if (i > 1) {
            sb = new StringBuilder();
            sb.append(i);
            str2 = " HRS ";
        } else {
            sb = new StringBuilder();
            sb.append(i);
            str2 = " HR ";
        }
        sb.append(str2);
        return sb.toString() + " : " + (i2 + " MIN ");
    }

    public static String formatTimeToHourMin(char c) {
        StringBuilder sb;
        String str;
        int i = c / Typography.less;
        String str2 = (c % Typography.less) + " MIN ";
        if (i == 0) {
            return str2;
        }
        if (i > 1) {
            sb = new StringBuilder();
            sb.append(i);
            str = " HRS ";
        } else {
            sb = new StringBuilder();
            sb.append(i);
            str = " HR ";
        }
        sb.append(str);
        return sb.toString() + " : " + str2;
    }

    public static long getString2Date(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String parseLong2Date(long j) {
        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH).format(new Date(j));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
