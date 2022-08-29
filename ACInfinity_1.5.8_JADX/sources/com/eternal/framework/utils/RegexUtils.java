package com.eternal.framework.utils;

import com.eternal.framework.utils.constant.RegexConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtils {
    private RegexUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isMobileSimple(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_MOBILE_SIMPLE, charSequence);
    }

    public static boolean isMobileExact(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_MOBILE_EXACT, charSequence);
    }

    public static boolean isPassword(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_PASSWORD, charSequence);
    }

    public static boolean isTel(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_TEL, charSequence);
    }

    public static boolean isIDCard15(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_ID_CARD15, charSequence);
    }

    public static boolean isIDCard18(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_ID_CARD18, charSequence);
    }

    public static boolean isEmail(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_EMAIL, charSequence);
    }

    public static boolean isDeviceSoftwareVersion(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_DEVICE_SOFTWARE_VERSION, charSequence);
    }

    public static boolean isURL(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_URL, charSequence);
    }

    public static boolean isZh(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_ZH, charSequence);
    }

    public static boolean isUsername(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_USERNAME, charSequence);
    }

    public static boolean isDate(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_DATE, charSequence);
    }

    public static boolean isIP(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_IP, charSequence);
    }

    public static boolean isMatch(String str, CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0 && Pattern.matches(str, charSequence);
    }

    public static List<String> getMatches(String str, CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(str).matcher(charSequence);
        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        return arrayList;
    }

    public static String[] getSplits(String str, String str2) {
        if (str == null) {
            return null;
        }
        return str.split(str2);
    }

    public static String getReplaceFirst(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        return Pattern.compile(str2).matcher(str).replaceFirst(str3);
    }

    public static String getReplaceAll(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        return Pattern.compile(str2).matcher(str).replaceAll(str3);
    }
}
