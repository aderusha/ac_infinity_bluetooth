package com.eternal.base.data.source;

import java.util.Collections;
import java.util.List;

public class MinNumberUtil {
    static int minNumber(List<Integer> list) {
        int size;
        if (list.isEmpty()) {
            return 1;
        }
        if (list.size() != 1) {
            int i = 0;
            for (int i2 = 1; i2 < list.size(); i2++) {
                if (list.get(i).intValue() > list.get(i2).intValue()) {
                    i = i2;
                }
            }
            if (list.get(i).intValue() != 1) {
                return 1;
            }
            Collections.swap(list, i, 0);
            int i3 = 1;
            while (true) {
                if (i3 >= list.size()) {
                    size = list.size();
                    break;
                }
                int i4 = i3 + 1;
                int i5 = i3;
                for (int i6 = i4; i6 < list.size(); i6++) {
                    if (list.get(i5).intValue() > list.get(i6).intValue()) {
                        i5 = i6;
                    }
                }
                Collections.swap(list, i3, i5);
                int i7 = i3 - 1;
                if (list.get(i3).intValue() - list.get(i7).intValue() > 1) {
                    size = list.get(i7).intValue();
                    break;
                }
                i3 = i4;
            }
            return size + 1;
        } else if (list.get(0).intValue() == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    static int minNumberZero(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }
        if (list.size() != 1) {
            int i = 0;
            for (int i2 = 1; i2 < list.size(); i2++) {
                if (list.get(i).intValue() > list.get(i2).intValue()) {
                    i = i2;
                }
            }
            if (list.get(i).intValue() != 0) {
                return 0;
            }
            Collections.swap(list, i, 0);
            int i3 = 1;
            while (i3 < list.size()) {
                int i4 = i3 + 1;
                int i5 = i3;
                for (int i6 = i4; i6 < list.size(); i6++) {
                    if (list.get(i5).intValue() > list.get(i6).intValue()) {
                        i5 = i6;
                    }
                }
                Collections.swap(list, i3, i5);
                int i7 = i3 - 1;
                if (list.get(i3).intValue() - list.get(i7).intValue() > 1) {
                    return list.get(i7).intValue() + 1;
                }
                i3 = i4;
            }
            return list.size();
        } else if (list.get(0).intValue() == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    static void getNumberZero(List<Integer> list, String str, String str2) {
        if (!str2.startsWith(str)) {
            return;
        }
        if (str2.length() == str.length()) {
            list.add(0);
            return;
        }
        try {
            list.add(Integer.valueOf(str2.substring(str.length() + 1)));
        } catch (NumberFormatException unused) {
        }
    }

    static void getNumber(List<Integer> list, String str, String str2) {
        if (!str2.startsWith(str)) {
            return;
        }
        if (str2.length() == str.length()) {
            list.add(0);
            return;
        }
        try {
            Integer valueOf = Integer.valueOf(str2.substring(str.length() + 1));
            if (!list.contains(valueOf)) {
                list.add(valueOf);
            }
        } catch (NumberFormatException unused) {
        }
    }
}
