package com.eternal.framework.http.utils;

public class JsonUtil {
    public static String formatJson(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        char c = 0;
        int i2 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt != ',') {
                if (charAt != '[') {
                    if (charAt != ']') {
                        if (charAt != '{') {
                            if (charAt != '}') {
                                stringBuffer.append(charAt);
                            }
                        }
                    }
                    stringBuffer.append(10);
                    i2--;
                    addIndentBlank(stringBuffer, i2);
                    stringBuffer.append(charAt);
                }
                stringBuffer.append(charAt);
                stringBuffer.append(10);
                i2++;
                addIndentBlank(stringBuffer, i2);
            } else {
                stringBuffer.append(charAt);
                if (c != '\\') {
                    stringBuffer.append(10);
                    addIndentBlank(stringBuffer, i2);
                }
            }
            i++;
            c = charAt;
        }
        return stringBuffer.toString();
    }

    private static void addIndentBlank(StringBuffer stringBuffer, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(9);
        }
    }

    public static String decodeUnicode(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                i = i2 + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 == 'u') {
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < 4) {
                        int i5 = i + 1;
                        char charAt3 = str.charAt(i);
                        switch (charAt3) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                i4 = ((i4 << 4) + charAt3) - 48;
                                break;
                            default:
                                switch (charAt3) {
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                        i4 = (((i4 << 4) + 10) + charAt3) - 65;
                                        break;
                                    default:
                                        switch (charAt3) {
                                            case 'a':
                                            case 'b':
                                            case 'c':
                                            case 'd':
                                            case 'e':
                                            case 'f':
                                                i4 = (((i4 << 4) + 10) + charAt3) - 97;
                                                break;
                                            default:
                                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                                        }
                                }
                        }
                        i3++;
                        i = i5;
                    }
                    stringBuffer.append((char) i4);
                } else {
                    if (charAt2 == 't') {
                        charAt2 = 9;
                    } else if (charAt2 == 'r') {
                        charAt2 = 13;
                    } else if (charAt2 == 'n') {
                        charAt2 = 10;
                    } else if (charAt2 == 'f') {
                        charAt2 = 12;
                    }
                    stringBuffer.append(charAt2);
                }
            } else {
                stringBuffer.append(charAt);
                i = i2;
            }
        }
        return stringBuffer.toString();
    }
}
