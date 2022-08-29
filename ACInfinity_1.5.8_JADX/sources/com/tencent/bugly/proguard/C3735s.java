package com.tencent.bugly.proguard;

import android.content.Context;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.tencent.bugly.proguard.s */
/* compiled from: BUGLY */
public final class C3735s {

    /* renamed from: b */
    private static C3735s f1005b;

    /* renamed from: a */
    public Map<String, String> f1006a = null;

    /* renamed from: c */
    private Context f1007c;

    private C3735s(Context context) {
        this.f1007c = context;
    }

    /* renamed from: a */
    public static C3735s m869a(Context context) {
        if (f1005b == null) {
            f1005b = new C3735s(context);
        }
        return f1005b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0188, code lost:
        if (com.tencent.bugly.proguard.C3749y.m935a(r4) != false) goto L_0x018d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x018a, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0169, code lost:
        if (com.tencent.bugly.proguard.C3749y.m935a(r4) != false) goto L_0x018d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x017b A[Catch:{ all -> 0x016e, all -> 0x0195 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] mo24310a(java.lang.String r21, byte[] r22, com.tencent.bugly.proguard.C3746w r23, java.util.Map<java.lang.String, java.lang.String> r24) {
        /*
            r20 = this;
            r1 = r20
            r2 = r22
            r3 = r23
            r4 = 0
            r5 = 0
            if (r21 != 0) goto L_0x0012
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.String r2 = "Failed for no URL."
            com.tencent.bugly.proguard.C3749y.m941e(r2, r0)
            return r4
        L_0x0012:
            if (r2 != 0) goto L_0x0017
            r8 = 0
            goto L_0x0019
        L_0x0017:
            int r0 = r2.length
            long r8 = (long) r0
        L_0x0019:
            r0 = 4
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r0[r5] = r21
            java.lang.Long r10 = java.lang.Long.valueOf(r8)
            r11 = 1
            r0[r11] = r10
            int r10 = android.os.Process.myPid()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r12 = 2
            r0[r12] = r10
            r10 = 3
            int r13 = android.os.Process.myTid()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r0[r10] = r13
            java.lang.String r10 = "request: %s, send: %d (pid=%d | tid=%d)"
            com.tencent.bugly.proguard.C3749y.m939c(r10, r0)
            r10 = r21
            r0 = 0
            r13 = 0
            r14 = 0
        L_0x0045:
            if (r0 > 0) goto L_0x01b6
            if (r13 > 0) goto L_0x01b6
            if (r14 == 0) goto L_0x004e
            r6 = r0
            r14 = 0
            goto L_0x007d
        L_0x004e:
            int r0 = r0 + 1
            if (r0 <= r11) goto L_0x007c
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r6 = "try time: "
            r15.<init>(r6)
            r15.append(r0)
            java.lang.String r6 = r15.toString()
            java.lang.Object[] r7 = new java.lang.Object[r5]
            com.tencent.bugly.proguard.C3749y.m939c(r6, r7)
            java.util.Random r6 = new java.util.Random
            long r11 = java.lang.System.currentTimeMillis()
            r6.<init>(r11)
            r11 = 10000(0x2710, float:1.4013E-41)
            int r6 = r6.nextInt(r11)
            long r11 = (long) r6
            r18 = 10000(0x2710, double:4.9407E-320)
            long r11 = r11 + r18
            android.os.SystemClock.sleep(r11)
        L_0x007c:
            r6 = r0
        L_0x007d:
            android.content.Context r0 = r1.f1007c
            java.lang.String r0 = com.tencent.bugly.crashreport.common.info.C3627b.m393b(r0)
            if (r0 != 0) goto L_0x0090
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.String r11 = "Failed to request for network not avail"
            com.tencent.bugly.proguard.C3749y.m940d(r11, r0)
            r0 = r6
        L_0x008d:
            r11 = 1
            r12 = 2
            goto L_0x0045
        L_0x0090:
            r3.mo24341a((long) r8)
            r11 = r24
            java.net.HttpURLConnection r12 = r1.m871a((java.lang.String) r10, (byte[]) r2, (java.lang.String) r0, (java.util.Map<java.lang.String, java.lang.String>) r11)
            if (r12 == 0) goto L_0x01a1
            int r0 = r12.getResponseCode()     // Catch:{ IOException -> 0x0171 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0171 }
            java.lang.String r15 = "response code "
            r7.<init>(r15)     // Catch:{ IOException -> 0x0171 }
            r7.append(r0)     // Catch:{ IOException -> 0x0171 }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x0171 }
            java.lang.Object[] r15 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x0171 }
            com.tencent.bugly.proguard.C3749y.m939c(r7, r15)     // Catch:{ IOException -> 0x0171 }
            r7 = 200(0xc8, float:2.8E-43)
            if (r0 != r7) goto L_0x00da
            java.util.Map r0 = m872a((java.net.HttpURLConnection) r12)     // Catch:{ IOException -> 0x0171 }
            r1.f1006a = r0     // Catch:{ IOException -> 0x0171 }
            byte[] r7 = m873b(r12)     // Catch:{ IOException -> 0x0171 }
            if (r7 != 0) goto L_0x00c5
            r4 = 0
            goto L_0x00c7
        L_0x00c5:
            int r0 = r7.length     // Catch:{ IOException -> 0x0171 }
            long r4 = (long) r0     // Catch:{ IOException -> 0x0171 }
        L_0x00c7:
            r3.mo24342b(r4)     // Catch:{ IOException -> 0x0171 }
            r12.disconnect()     // Catch:{ all -> 0x00ce }
            goto L_0x00d9
        L_0x00ce:
            r0 = move-exception
            r2 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r2)
            if (r0 != 0) goto L_0x00d9
            r2.printStackTrace()
        L_0x00d9:
            return r7
        L_0x00da:
            r4 = 301(0x12d, float:4.22E-43)
            if (r0 == r4) goto L_0x00ed
            r4 = 302(0x12e, float:4.23E-43)
            if (r0 == r4) goto L_0x00ed
            r4 = 303(0x12f, float:4.25E-43)
            if (r0 == r4) goto L_0x00ed
            r4 = 307(0x133, float:4.3E-43)
            if (r0 != r4) goto L_0x00eb
            goto L_0x00ed
        L_0x00eb:
            r4 = 0
            goto L_0x00ee
        L_0x00ed:
            r4 = 1
        L_0x00ee:
            if (r4 == 0) goto L_0x014c
            java.lang.String r4 = "Location"
            java.lang.String r4 = r12.getHeaderField(r4)     // Catch:{ IOException -> 0x0146 }
            if (r4 != 0) goto L_0x0122
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x011e }
            java.lang.String r5 = "Failed to redirect: %d"
            r4.<init>(r5)     // Catch:{ IOException -> 0x011e }
            r4.append(r0)     // Catch:{ IOException -> 0x011e }
            java.lang.String r0 = r4.toString()     // Catch:{ IOException -> 0x011e }
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x011e }
            com.tencent.bugly.proguard.C3749y.m941e(r0, r5)     // Catch:{ IOException -> 0x011e }
            r12.disconnect()     // Catch:{ all -> 0x0111 }
        L_0x010f:
            r2 = 0
            goto L_0x011d
        L_0x0111:
            r0 = move-exception
            r2 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r2)
            if (r0 != 0) goto L_0x010f
            r2.printStackTrace()
            goto L_0x010f
        L_0x011d:
            return r2
        L_0x011e:
            r0 = move-exception
            r7 = 2
            r14 = 1
            goto L_0x0173
        L_0x0122:
            int r13 = r13 + 1
            java.lang.String r5 = "redirect code: %d ,to:%s"
            r7 = 2
            java.lang.Object[] r6 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x013d }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x013d }
            r10 = 0
            r6[r10] = r0     // Catch:{ IOException -> 0x013d }
            r18 = 1
            r6[r18] = r4     // Catch:{ IOException -> 0x013b }
            com.tencent.bugly.proguard.C3749y.m939c(r5, r6)     // Catch:{ IOException -> 0x013b }
            r10 = r4
            r6 = 0
            r14 = 1
            goto L_0x014f
        L_0x013b:
            r0 = move-exception
            goto L_0x0143
        L_0x013d:
            r0 = move-exception
            goto L_0x0141
        L_0x013f:
            r0 = move-exception
            r7 = 2
        L_0x0141:
            r18 = 1
        L_0x0143:
            r10 = r4
            r6 = 0
            goto L_0x014a
        L_0x0146:
            r0 = move-exception
            r7 = 2
            r18 = 1
        L_0x014a:
            r14 = 1
            goto L_0x0175
        L_0x014c:
            r7 = 2
            r18 = 1
        L_0x014f:
            int r0 = r12.getContentLength()     // Catch:{ IOException -> 0x016c }
            long r4 = (long) r0     // Catch:{ IOException -> 0x016c }
            r16 = 0
            int r0 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r0 >= 0) goto L_0x015c
            r4 = 0
        L_0x015c:
            r3.mo24342b(r4)     // Catch:{ IOException -> 0x016c }
            r12.disconnect()     // Catch:{ all -> 0x0163 }
            goto L_0x018d
        L_0x0163:
            r0 = move-exception
            r4 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r4)
            if (r0 != 0) goto L_0x018d
            goto L_0x018a
        L_0x016c:
            r0 = move-exception
            goto L_0x0175
        L_0x016e:
            r0 = move-exception
            r2 = r0
            goto L_0x0191
        L_0x0171:
            r0 = move-exception
            r7 = 2
        L_0x0173:
            r18 = 1
        L_0x0175:
            boolean r4 = com.tencent.bugly.proguard.C3749y.m935a(r0)     // Catch:{ all -> 0x016e }
            if (r4 != 0) goto L_0x017e
            r0.printStackTrace()     // Catch:{ all -> 0x016e }
        L_0x017e:
            r12.disconnect()     // Catch:{ all -> 0x0182 }
            goto L_0x018d
        L_0x0182:
            r0 = move-exception
            r4 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r4)
            if (r0 != 0) goto L_0x018d
        L_0x018a:
            r4.printStackTrace()
        L_0x018d:
            r0 = r6
            r4 = 0
            goto L_0x01b2
        L_0x0191:
            r12.disconnect()     // Catch:{ all -> 0x0195 }
            goto L_0x01a0
        L_0x0195:
            r0 = move-exception
            r3 = r0
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r3)
            if (r0 != 0) goto L_0x01a0
            r3.printStackTrace()
        L_0x01a0:
            throw r2
        L_0x01a1:
            r4 = 0
            r7 = 2
            r18 = 1
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r5 = "Failed to execute post."
            com.tencent.bugly.proguard.C3749y.m939c(r5, r0)
            r4 = 0
            r3.mo24342b(r4)
            r0 = r6
        L_0x01b2:
            r4 = 0
            r5 = 0
            goto L_0x008d
        L_0x01b6:
            r2 = r4
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3735s.mo24310a(java.lang.String, byte[], com.tencent.bugly.proguard.w, java.util.Map):byte[]");
    }

    /* renamed from: a */
    private static Map<String, String> m872a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List list = (List) headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003b A[Catch:{ all -> 0x0049, all -> 0x0050 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A[SYNTHETIC, Splitter:B:24:0x0040] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] m873b(java.net.HttpURLConnection r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0033 }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ all -> 0x0033 }
            r1.<init>(r5)     // Catch:{ all -> 0x0033 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0031 }
            r5.<init>()     // Catch:{ all -> 0x0031 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x0031 }
        L_0x0016:
            int r3 = r1.read(r2)     // Catch:{ all -> 0x0031 }
            if (r3 <= 0) goto L_0x0021
            r4 = 0
            r5.write(r2, r4, r3)     // Catch:{ all -> 0x0031 }
            goto L_0x0016
        L_0x0021:
            r5.flush()     // Catch:{ all -> 0x0031 }
            byte[] r5 = r5.toByteArray()     // Catch:{ all -> 0x0031 }
            r1.close()     // Catch:{ all -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0030:
            return r5
        L_0x0031:
            r5 = move-exception
            goto L_0x0035
        L_0x0033:
            r5 = move-exception
            r1 = r0
        L_0x0035:
            boolean r2 = com.tencent.bugly.proguard.C3749y.m935a(r5)     // Catch:{ all -> 0x0049 }
            if (r2 != 0) goto L_0x003e
            r5.printStackTrace()     // Catch:{ all -> 0x0049 }
        L_0x003e:
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ all -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0048:
            return r0
        L_0x0049:
            r5 = move-exception
            if (r1 == 0) goto L_0x0054
            r1.close()     // Catch:{ all -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0054:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3735s.m873b(java.net.HttpURLConnection):byte[]");
    }

    /* renamed from: a */
    private HttpURLConnection m871a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            C3749y.m941e("destUrl is null.", new Object[0]);
            return null;
        }
        TrustManager[] trustManagerArr = {new X509TrustManager() {
            public final X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                C3749y.m939c("checkClientTrusted", new Object[0]);
            }

            public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                C3749y.m939c("checkServerTrusted", new Object[0]);
            }
        }};
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpURLConnection a = m870a(str2, str);
        if (a == null) {
            C3749y.m941e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a.setRequestProperty("wup_version", "3.0");
            if (map != null) {
                if (map.size() > 0) {
                    for (Map.Entry next : map.entrySet()) {
                        a.setRequestProperty((String) next.getKey(), URLEncoder.encode((String) next.getValue(), "utf-8"));
                    }
                }
            }
            a.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            a.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            OutputStream outputStream = a.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return a;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            C3749y.m941e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m870a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (C3691a.m631b() != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection(C3691a.m631b());
            } else if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
