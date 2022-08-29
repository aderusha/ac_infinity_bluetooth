package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.tencent.bugly.proguard.ab */
/* compiled from: BUGLY */
public class C3695ab {

    /* renamed from: a */
    private static Map<String, String> f803a;

    /* renamed from: a */
    public static String m665a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (C3749y.m935a(th2)) {
                return "fail";
            }
            th2.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: a */
    public static String m659a() {
        return m661a(System.currentTimeMillis());
    }

    /* renamed from: a */
    public static String m661a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    /* renamed from: a */
    public static String m666a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x006a A[Catch:{ all -> 0x007d }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006f A[SYNTHETIC, Splitter:B:28:0x006f] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] m681a(java.io.File r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r5 = "rqdp{  ZF end}"
            r0 = 0
            if (r6 == 0) goto L_0x008e
            int r1 = r6.length()
            if (r1 != 0) goto L_0x000d
            goto L_0x008e
        L_0x000d:
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "rqdp{  ZF start}"
            com.tencent.bugly.proguard.C3749y.m939c(r3, r2)
            java.lang.String r2 = "UTF-8"
            byte[] r6 = r6.getBytes(r2)     // Catch:{ all -> 0x0062 }
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0062 }
            r2.<init>(r6)     // Catch:{ all -> 0x0062 }
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0062 }
            r6.<init>()     // Catch:{ all -> 0x0062 }
            java.util.zip.ZipOutputStream r3 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x0062 }
            r3.<init>(r6)     // Catch:{ all -> 0x0062 }
            r4 = 8
            r3.setMethod(r4)     // Catch:{ all -> 0x0060 }
            java.util.zip.ZipEntry r4 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x0060 }
            r4.<init>(r7)     // Catch:{ all -> 0x0060 }
            r3.putNextEntry(r4)     // Catch:{ all -> 0x0060 }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x0060 }
        L_0x003b:
            int r4 = r2.read(r7)     // Catch:{ all -> 0x0060 }
            if (r4 <= 0) goto L_0x0045
            r3.write(r7, r1, r4)     // Catch:{ all -> 0x0060 }
            goto L_0x003b
        L_0x0045:
            r3.closeEntry()     // Catch:{ all -> 0x0060 }
            r3.flush()     // Catch:{ all -> 0x0060 }
            r3.finish()     // Catch:{ all -> 0x0060 }
            byte[] r6 = r6.toByteArray()     // Catch:{ all -> 0x0060 }
            r3.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r7 = move-exception
            r7.printStackTrace()
        L_0x005a:
            java.lang.Object[] r7 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.C3749y.m939c(r5, r7)
            return r6
        L_0x0060:
            r6 = move-exception
            goto L_0x0064
        L_0x0062:
            r6 = move-exception
            r3 = r0
        L_0x0064:
            boolean r7 = com.tencent.bugly.proguard.C3749y.m935a(r6)     // Catch:{ all -> 0x007d }
            if (r7 != 0) goto L_0x006d
            r6.printStackTrace()     // Catch:{ all -> 0x007d }
        L_0x006d:
            if (r3 == 0) goto L_0x0077
            r3.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0077:
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.C3749y.m939c(r5, r6)
            return r0
        L_0x007d:
            r6 = move-exception
            if (r3 == 0) goto L_0x0088
            r3.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0088
        L_0x0084:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0088:
            java.lang.Object[] r7 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.C3749y.m939c(r5, r7)
            throw r6
        L_0x008e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3695ab.m681a(java.io.File, java.lang.String, java.lang.String):byte[]");
    }

    /* renamed from: a */
    public static byte[] m682a(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        C3749y.m939c("[Util] Zip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            C3700af a = C3699ae.m711a(2);
            if (a == null) {
                return null;
            }
            return a.mo24241a(bArr);
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m692b(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        C3749y.m939c("[Util] Unzip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            C3700af a = C3699ae.m711a(2);
            if (a == null) {
                return null;
            }
            return a.mo24242b(bArr);
        } catch (Throwable th) {
            if (th.getMessage() != null && th.getMessage().contains("Not in GZIP format")) {
                C3749y.m940d(th.getMessage(), new Object[0]);
            } else if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public static long m683b() {
        try {
            return (((System.currentTimeMillis() + ((long) TimeZone.getDefault().getRawOffset())) / 86400000) * 86400000) - ((long) TimeZone.getDefault().getRawOffset());
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    /* renamed from: a */
    public static String m667a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr);
            byte[] digest = instance.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.util.zip.ZipOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.io.FileInputStream} */
    /* JADX WARNING: type inference failed for: r3v0, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0076 A[Catch:{ all -> 0x0093 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007b A[SYNTHETIC, Splitter:B:36:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0085 A[SYNTHETIC, Splitter:B:41:0x0085] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m677a(java.io.File r6, java.io.File r7, int r8) {
        /*
            java.lang.String r8 = "rqdp{  ZF end}"
            r0 = 0
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "rqdp{  ZF start}"
            com.tencent.bugly.proguard.C3749y.m939c(r2, r1)
            boolean r1 = m676a((java.io.File) r6, (java.io.File) r7)
            if (r1 != 0) goto L_0x0011
            return r0
        L_0x0011:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x006e }
            r2.<init>(r6)     // Catch:{ all -> 0x006e }
            java.util.zip.ZipOutputStream r3 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x006a }
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x006a }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ all -> 0x006a }
            r5.<init>(r7)     // Catch:{ all -> 0x006a }
            r4.<init>(r5)     // Catch:{ all -> 0x006a }
            r3.<init>(r4)     // Catch:{ all -> 0x006a }
            r7 = 8
            r3.setMethod(r7)     // Catch:{ all -> 0x0068 }
            java.util.zip.ZipEntry r7 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x0068 }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x0068 }
            r7.<init>(r6)     // Catch:{ all -> 0x0068 }
            r3.putNextEntry(r7)     // Catch:{ all -> 0x0068 }
            r6 = 5000(0x1388, float:7.006E-42)
            r7 = 1000(0x3e8, float:1.401E-42)
            int r6 = java.lang.Math.max(r6, r7)     // Catch:{ all -> 0x0068 }
            byte[] r6 = new byte[r6]     // Catch:{ all -> 0x0068 }
        L_0x0041:
            int r7 = r2.read(r6)     // Catch:{ all -> 0x0068 }
            if (r7 <= 0) goto L_0x004b
            r3.write(r6, r0, r7)     // Catch:{ all -> 0x0068 }
            goto L_0x0041
        L_0x004b:
            r3.flush()     // Catch:{ all -> 0x0068 }
            r3.closeEntry()     // Catch:{ all -> 0x0068 }
            r2.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0059:
            r3.close()     // Catch:{ IOException -> 0x005d }
            goto L_0x0061
        L_0x005d:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0061:
            java.lang.Object[] r6 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.C3749y.m939c(r8, r6)
            r6 = 1
            return r6
        L_0x0068:
            r6 = move-exception
            goto L_0x006c
        L_0x006a:
            r6 = move-exception
            r3 = r1
        L_0x006c:
            r1 = r2
            goto L_0x0070
        L_0x006e:
            r6 = move-exception
            r3 = r1
        L_0x0070:
            boolean r7 = com.tencent.bugly.proguard.C3749y.m935a(r6)     // Catch:{ all -> 0x0093 }
            if (r7 != 0) goto L_0x0079
            r6.printStackTrace()     // Catch:{ all -> 0x0093 }
        L_0x0079:
            if (r1 == 0) goto L_0x0083
            r1.close()     // Catch:{ IOException -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0083:
            if (r3 == 0) goto L_0x008d
            r3.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r6 = move-exception
            r6.printStackTrace()
        L_0x008d:
            java.lang.Object[] r6 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.C3749y.m939c(r8, r6)
            return r0
        L_0x0093:
            r6 = move-exception
            if (r1 == 0) goto L_0x009e
            r1.close()     // Catch:{ IOException -> 0x009a }
            goto L_0x009e
        L_0x009a:
            r7 = move-exception
            r7.printStackTrace()
        L_0x009e:
            if (r3 == 0) goto L_0x00a8
            r3.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x00a8
        L_0x00a4:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00a8:
            java.lang.Object[] r7 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.C3749y.m939c(r8, r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3695ab.m677a(java.io.File, java.io.File, int):boolean");
    }

    /* renamed from: a */
    private static boolean m676a(File file, File file2) {
        if (file == null || file2 == null || file.equals(file2)) {
            C3749y.m940d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        } else if (!file.exists() || !file.canRead()) {
            C3749y.m940d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        } else {
            try {
                if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                if (!file2.exists()) {
                    file2.createNewFile();
                }
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
            if (!file2.exists() || !file2.canWrite()) {
                return false;
            }
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0073 A[Catch:{ all -> 0x008b }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0078 A[SYNTHETIC, Splitter:B:35:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A[SYNTHETIC, Splitter:B:40:0x0082] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<java.lang.String> m669a(android.content.Context r4, java.lang.String[] r5) {
        /*
            boolean r4 = com.tencent.bugly.crashreport.common.info.AppInfo.m332e(r4)
            if (r4 == 0) goto L_0x0016
            java.util.ArrayList r4 = new java.util.ArrayList
            java.lang.String r5 = "unknown(low memory)"
            java.lang.String[] r5 = new java.lang.String[]{r5}
            java.util.List r5 = java.util.Arrays.asList(r5)
            r4.<init>(r5)
            return r4
        L_0x0016:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ all -> 0x006a }
            java.lang.Process r5 = r1.exec(r5)     // Catch:{ all -> 0x006a }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x006a }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x006a }
            java.io.InputStream r3 = r5.getInputStream()     // Catch:{ all -> 0x006a }
            r2.<init>(r3)     // Catch:{ all -> 0x006a }
            r1.<init>(r2)     // Catch:{ all -> 0x006a }
        L_0x0032:
            java.lang.String r2 = r1.readLine()     // Catch:{ all -> 0x0067 }
            if (r2 == 0) goto L_0x003c
            r4.add(r2)     // Catch:{ all -> 0x0067 }
            goto L_0x0032
        L_0x003c:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0067 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0067 }
            java.io.InputStream r5 = r5.getErrorStream()     // Catch:{ all -> 0x0067 }
            r3.<init>(r5)     // Catch:{ all -> 0x0067 }
            r2.<init>(r3)     // Catch:{ all -> 0x0067 }
        L_0x004a:
            java.lang.String r5 = r2.readLine()     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x0054
            r4.add(r5)     // Catch:{ all -> 0x0065 }
            goto L_0x004a
        L_0x0054:
            r1.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r5 = move-exception
            r5.printStackTrace()
        L_0x005c:
            r2.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0064:
            return r4
        L_0x0065:
            r4 = move-exception
            goto L_0x006d
        L_0x0067:
            r4 = move-exception
            r2 = r0
            goto L_0x006d
        L_0x006a:
            r4 = move-exception
            r1 = r0
            r2 = r1
        L_0x006d:
            boolean r5 = com.tencent.bugly.proguard.C3749y.m935a(r4)     // Catch:{ all -> 0x008b }
            if (r5 != 0) goto L_0x0076
            r4.printStackTrace()     // Catch:{ all -> 0x008b }
        L_0x0076:
            if (r1 == 0) goto L_0x0080
            r1.close()     // Catch:{ IOException -> 0x007c }
            goto L_0x0080
        L_0x007c:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0080:
            if (r2 == 0) goto L_0x008a
            r2.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x008a
        L_0x0086:
            r4 = move-exception
            r4.printStackTrace()
        L_0x008a:
            return r0
        L_0x008b:
            r4 = move-exception
            if (r1 == 0) goto L_0x0096
            r1.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0096:
            if (r2 == 0) goto L_0x00a0
            r2.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a0
        L_0x009c:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00a0:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3695ab.m669a(android.content.Context, java.lang.String[]):java.util.ArrayList");
    }

    /* renamed from: a */
    public static String m662a(Context context, String str) {
        Class<C3695ab> cls = C3695ab.class;
        if (str == null || str.trim().equals("")) {
            return "";
        }
        if (f803a == null) {
            f803a = new HashMap();
            String str2 = "/system/bin/sh";
            if (!new File(str2).exists() || !new File(str2).canExecute()) {
                str2 = "sh";
            }
            ArrayList<String> a = m669a(context, new String[]{str2, "-c", "getprop"});
            if (a != null && a.size() > 0) {
                C3749y.m936b(cls, "Successfully get 'getprop' list.", new Object[0]);
                Pattern compile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                for (String matcher : a) {
                    Matcher matcher2 = compile.matcher(matcher);
                    if (matcher2.find()) {
                        f803a.put(matcher2.group(1), matcher2.group(2));
                    }
                }
                C3749y.m936b(cls, "Systems properties number: %d.", Integer.valueOf(f803a.size()));
            }
        }
        return f803a.containsKey(str) ? f803a.get(str) : "fail";
    }

    /* renamed from: b */
    public static void m687b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m679a(String str) {
        return str == null || str.trim().length() <= 0;
    }

    /* renamed from: b */
    public static void m689b(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.isFile() && file.exists() && file.canWrite()) {
                file.delete();
            }
        }
    }

    /* renamed from: c */
    public static byte[] m695c(long j) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            return sb.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static long m684b(byte[] bArr) {
        if (bArr == null) {
            return -1;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = r1.getApplicationContext();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Context m653a(android.content.Context r1) {
        /*
            if (r1 != 0) goto L_0x0003
            return r1
        L_0x0003:
            android.content.Context r0 = r1.getApplicationContext()
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3695ab.m653a(android.content.Context):android.content.Context");
    }

    /* renamed from: b */
    public static String m685b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static void m674a(Class<?> cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set((Object) null, obj);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static Object m657a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke((Object) null, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static void m673a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).f446a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).f448c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).f447b);
        }
        parcel.writeBundle(bundle);
    }

    /* renamed from: a */
    public static Map<String, PlugInBean> m671a(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (int i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < intValue; i2++) {
            String string = readBundle.getString("pluginVal" + i2 + "plugInId");
            String string2 = readBundle.getString("pluginVal" + i2 + "plugInUUID");
            arrayList2.add(new PlugInBean(string, readBundle.getString("pluginVal" + i2 + "plugInVersion"), string2));
        }
        if (arrayList.size() == arrayList2.size()) {
            hashMap = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
        } else {
            C3749y.m941e("map plugin parcel error!", new Object[0]);
        }
        return hashMap;
    }

    /* renamed from: b */
    public static void m688b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    /* renamed from: b */
    public static Map<String, String> m686b(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = readBundle.getStringArrayList("keys");
        ArrayList<String> stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList == null || stringArrayList2 == null || stringArrayList.size() != stringArrayList2.size()) {
            C3749y.m941e("map parcel error!", new Object[0]);
        } else {
            hashMap = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                hashMap.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static byte[] m680a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    /* renamed from: a */
    public static <T> T m658a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            T createFromParcel = creator.createFromParcel(obtain);
            if (obtain != null) {
                obtain.recycle();
            }
            return createFromParcel;
        } catch (Throwable th) {
            if (obtain != null) {
                obtain.recycle();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static String m660a(int i, String str) {
        String[] strArr;
        if (str == null) {
            strArr = new String[]{"logcat", "-d", "-v", "threadtime"};
        } else {
            strArr = new String[]{"logcat", "-d", "-v", "threadtime", "-s", str};
        }
        Process process = null;
        StringBuilder sb = new StringBuilder();
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
                if (i > 0 && sb.length() > i) {
                    sb.delete(0, sb.length() - i);
                }
            }
            String sb2 = sb.toString();
            if (exec != null) {
                try {
                    exec.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    exec.getInputStream().close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    exec.getErrorStream().close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return sb2;
        } catch (Throwable th) {
            if (process != null) {
                try {
                    process.getOutputStream().close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                try {
                    process.getInputStream().close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                try {
                    process.getErrorStream().close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static Map<String, String> m672a(boolean z, int i, boolean z2) {
        if (!z) {
            C3749y.m939c("get all thread stack not enable", new Object[0]);
            return new HashMap();
        }
        Map<String, String> a = m670a(i, false);
        return a == null ? new HashMap() : a;
    }

    /* renamed from: a */
    private static Map<String, String> m670a(int i, boolean z) {
        HashMap hashMap = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread thread = Looper.getMainLooper().getThread();
        if (!allStackTraces.containsKey(thread)) {
            allStackTraces.put(thread, thread.getStackTrace());
        }
        long id = Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : allStackTraces.entrySet()) {
            if (!z || id != ((Thread) next.getKey()).getId()) {
                int i2 = 0;
                sb.setLength(0);
                if (!(next.getValue() == null || ((StackTraceElement[]) next.getValue()).length == 0)) {
                    StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) next.getValue();
                    int length = stackTraceElementArr.length;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        StackTraceElement stackTraceElement = stackTraceElementArr[i2];
                        if (i > 0 && sb.length() >= i) {
                            sb.append("\n[Stack over limit size :" + i + " , has been cut!]");
                            break;
                        }
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                        i2++;
                    }
                    hashMap.put(((Thread) next.getKey()).getName() + "(" + ((Thread) next.getKey()).getId() + ")", sb.toString());
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static String m664a(Thread thread) {
        if (thread == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static boolean m675a(Context context, String str, long j) {
        C3749y.m939c("[Util] Try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < 10000) {
                    return false;
                }
                C3749y.m939c("[Util] Lock file (%s) is expired, unlock it.", str);
                m691b(context, str);
            }
            if (file.createNewFile()) {
                C3749y.m939c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            C3749y.m939c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            C3749y.m935a(th);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m691b(Context context, String str) {
        C3749y.m939c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            C3749y.m939c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            C3749y.m935a(th);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067 A[SYNTHETIC, Splitter:B:30:0x0067] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m663a(java.io.File r5, int r6, boolean r7) {
        /*
            r0 = 0
            if (r5 == 0) goto L_0x007c
            boolean r1 = r5.exists()
            if (r1 == 0) goto L_0x007c
            boolean r1 = r5.canRead()
            if (r1 != 0) goto L_0x0011
            goto L_0x007c
        L_0x0011:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0060 }
            r1.<init>()     // Catch:{ all -> 0x0060 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0060 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0060 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x0060 }
            r4.<init>(r5)     // Catch:{ all -> 0x0060 }
            java.lang.String r5 = "utf-8"
            r3.<init>(r4, r5)     // Catch:{ all -> 0x0060 }
            r2.<init>(r3)     // Catch:{ all -> 0x0060 }
        L_0x0027:
            java.lang.String r5 = r2.readLine()     // Catch:{ all -> 0x005e }
            if (r5 == 0) goto L_0x0051
            r1.append(r5)     // Catch:{ all -> 0x005e }
            java.lang.String r5 = "\n"
            r1.append(r5)     // Catch:{ all -> 0x005e }
            if (r6 <= 0) goto L_0x0027
            int r5 = r1.length()     // Catch:{ all -> 0x005e }
            if (r5 <= r6) goto L_0x0027
            if (r7 == 0) goto L_0x0047
            int r5 = r1.length()     // Catch:{ all -> 0x005e }
            r1.delete(r6, r5)     // Catch:{ all -> 0x005e }
            goto L_0x0051
        L_0x0047:
            r5 = 0
            int r3 = r1.length()     // Catch:{ all -> 0x005e }
            int r3 = r3 - r6
            r1.delete(r5, r3)     // Catch:{ all -> 0x005e }
            goto L_0x0027
        L_0x0051:
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x005e }
            r2.close()     // Catch:{ Exception -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r6 = move-exception
            com.tencent.bugly.proguard.C3749y.m935a(r6)
        L_0x005d:
            return r5
        L_0x005e:
            r5 = move-exception
            goto L_0x0062
        L_0x0060:
            r5 = move-exception
            r2 = r0
        L_0x0062:
            com.tencent.bugly.proguard.C3749y.m935a(r5)     // Catch:{ all -> 0x0070 }
            if (r2 == 0) goto L_0x006f
            r2.close()     // Catch:{ Exception -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r5 = move-exception
            com.tencent.bugly.proguard.C3749y.m935a(r5)
        L_0x006f:
            return r0
        L_0x0070:
            r5 = move-exception
            if (r2 == 0) goto L_0x007b
            r2.close()     // Catch:{ Exception -> 0x0077 }
            goto L_0x007b
        L_0x0077:
            r6 = move-exception
            com.tencent.bugly.proguard.C3749y.m935a(r6)
        L_0x007b:
            throw r5
        L_0x007c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3695ab.m663a(java.io.File, int, boolean):java.lang.String");
    }

    /* renamed from: a */
    private static BufferedReader m655a(File file) {
        if (file != null && file.exists() && file.canRead()) {
            try {
                return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            } catch (Throwable th) {
                C3749y.m935a(th);
            }
        }
        return null;
    }

    /* renamed from: a */
    public static BufferedReader m656a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str, str2);
            if (file.exists()) {
                if (file.canRead()) {
                    return m655a(file);
                }
            }
            return null;
        } catch (NullPointerException e) {
            C3749y.m935a(e);
            return null;
        }
    }

    /* renamed from: a */
    public static Thread m668a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            C3749y.m941e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m678a(Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        C3747x a = C3747x.m926a();
        if (a != null) {
            return a.mo24344a(runnable);
        }
        String[] split = runnable.getClass().getName().split("\\.");
        return m668a(runnable, split[split.length - 1]) != null;
    }

    /* renamed from: c */
    public static boolean m694c(String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        }
        if (str.length() > 255) {
            C3749y.m934a("URL(%s)'s length is larger than 255.", str);
            return false;
        } else if (str.toLowerCase().startsWith("http")) {
            return true;
        } else {
            C3749y.m934a("URL(%s) is not start with \"http\".", str);
            return false;
        }
    }

    /* renamed from: a */
    public static SharedPreferences m654a(String str, Context context) {
        if (context != null) {
            return context.getSharedPreferences(str, 0);
        }
        return null;
    }

    /* renamed from: b */
    public static void m690b(String str, String str2) {
        if (C3626a.m339b() != null && C3626a.m339b().f455E != null) {
            C3626a.m339b().f455E.edit().putString(str, str2).apply();
        }
    }

    /* renamed from: c */
    public static String m693c(String str, String str2) {
        return (C3626a.m339b() == null || C3626a.m339b().f455E == null) ? "" : C3626a.m339b().f455E.getString(str, str2);
    }
}
