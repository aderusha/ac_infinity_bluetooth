package com.easysocket.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Random;

public class Utils {
    public static <T> Type findGenericityType(Class<T> cls) {
        Type type = ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        if (!(type instanceof TypeVariable)) {
            return type;
        }
        throw new IllegalStateException("没有填写泛型参数");
    }

    public static boolean isStringEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String getRandomChar(int i) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(cArr[random.nextInt(36)]);
        }
        return stringBuffer.toString();
    }

    public static Handler getHandler(boolean z) {
        if (z) {
            return new Handler(Looper.getMainLooper());
        }
        Looper.prepare();
        return new Handler();
    }

    public static void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            try {
                throw new Exception(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void throwNotNull(Object obj, String str) throws Exception {
        if (obj == null) {
            throw new Exception(str);
        }
    }

    public static boolean isNetConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static byte[] concatBytes(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 == null) {
            return bArr;
        }
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
