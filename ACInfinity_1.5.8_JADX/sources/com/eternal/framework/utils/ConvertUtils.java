package com.eternal.framework.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.view.View;
import com.eternal.framework.utils.constant.TimeConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class ConvertUtils {
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static double byte2MemorySize(long j, int i) {
        if (j < 0) {
            return -1.0d;
        }
        return ((double) j) / ((double) i);
    }

    public static long memorySize2Byte(long j, int i) {
        if (j < 0) {
            return -1;
        }
        return j * ((long) i);
    }

    public static long timeSpan2Millis(long j, int i) {
        return j * ((long) i);
    }

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = hexDigits;
            cArr[i] = cArr2[(bArr[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    public static byte[] hexString2Bytes(String str) {
        if (isSpace(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[(length >> 1)];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((hex2Dec(charArray[i]) << 4) | hex2Dec(charArray[i + 1]));
        }
        return bArr;
    }

    private static int hex2Dec(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException();
    }

    public static byte[] chars2Bytes(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return null;
        }
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    public static char[] bytes2Chars(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    public static String byte2FitMemorySize(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return String.format("%.3fB", new Object[]{Double.valueOf(((double) j) + 5.0E-4d)});
        } else if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            return String.format("%.3fKB", new Object[]{Double.valueOf((((double) j) / 1024.0d) + 5.0E-4d)});
        } else if (j < 1073741824) {
            return String.format("%.3fMB", new Object[]{Double.valueOf((((double) j) / 1048576.0d) + 5.0E-4d)});
        } else {
            return String.format("%.3fGB", new Object[]{Double.valueOf((((double) j) / 1.073741824E9d) + 5.0E-4d)});
        }
    }

    public static long millis2TimeSpan(long j, int i) {
        return j / ((long) i);
    }

    public static String millis2FitTimeSpan(long j, int i) {
        if (j <= 0 || i <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = {"天", "小时", "分钟", "秒", "毫秒"};
        int[] iArr = {TimeConstants.DAY, TimeConstants.HOUR, TimeConstants.MIN, 1000, 1};
        int min = Math.min(i, 5);
        for (int i2 = 0; i2 < min; i2++) {
            if (j >= ((long) iArr[i2])) {
                long j2 = j / ((long) iArr[i2]);
                j -= ((long) iArr[i2]) * j2;
                sb.append(j2);
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }

    public static String bytes2Bits(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            for (int i = 7; i >= 0; i--) {
                sb.append(((b >> i) & 1) == 0 ? '0' : '1');
            }
        }
        return sb.toString();
    }

    public static byte[] bits2Bytes(String str) {
        int length = str.length() % 8;
        int length2 = str.length() / 8;
        if (length != 0) {
            while (length < 8) {
                str = "0" + str;
                length++;
            }
            length2++;
        }
        byte[] bArr = new byte[length2];
        for (int i = 0; i < length2; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[i] = (byte) (bArr[i] << 1);
                bArr[i] = (byte) (bArr[i] | (str.charAt((i * 8) + i2) - '0'));
            }
        }
        return bArr;
    }

    public static ByteArrayOutputStream input2OutputStream(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    return byteArrayOutputStream;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(inputStream);
        }
    }

    public ByteArrayInputStream output2InputStream(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());
    }

    public static byte[] inputStream2Bytes(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return input2OutputStream(inputStream).toByteArray();
    }

    public static InputStream bytes2InputStream(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new ByteArrayInputStream(bArr);
    }

    public static byte[] outputStream2Bytes(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return ((ByteArrayOutputStream) outputStream).toByteArray();
    }

    public static OutputStream bytes2OutputStream(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(bArr);
                CloseUtils.closeIO(byteArrayOutputStream);
                return byteArrayOutputStream;
            } catch (IOException e) {
                e = e;
                try {
                    e.printStackTrace();
                    CloseUtils.closeIO(byteArrayOutputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    CloseUtils.closeIO(byteArrayOutputStream2);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            byteArrayOutputStream = null;
            e.printStackTrace();
            CloseUtils.closeIO(byteArrayOutputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            CloseUtils.closeIO(byteArrayOutputStream2);
            throw th;
        }
    }

    public static String inputStream2String(InputStream inputStream, String str) {
        if (inputStream != null && !isSpace(str)) {
            try {
                return new String(inputStream2Bytes(inputStream), str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static InputStream string2InputStream(String str, String str2) {
        if (str != null && !isSpace(str2)) {
            try {
                return new ByteArrayInputStream(str.getBytes(str2));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String outputStream2String(OutputStream outputStream, String str) {
        if (outputStream != null && !isSpace(str)) {
            try {
                return new String(outputStream2Bytes(outputStream), str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static OutputStream string2OutputStream(String str, String str2) {
        if (str != null && !isSpace(str2)) {
            try {
                return bytes2OutputStream(str.getBytes(str2));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap bytes2Bitmap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.getContext().getResources(), bitmap);
    }

    public static byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat compressFormat) {
        if (drawable == null) {
            return null;
        }
        return bitmap2Bytes(drawable2Bitmap(drawable), compressFormat);
    }

    public static Drawable bytes2Drawable(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return bitmap2Drawable(bytes2Bitmap(bArr));
    }

    public static Bitmap view2Bitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return createBitmap;
    }

    public static int dp2px(float f) {
        return (int) ((f * Utils.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dp(float f) {
        return (int) ((f / Utils.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) ((f * Utils.getContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int px2sp(float f) {
        return (int) ((f / Utils.getContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
