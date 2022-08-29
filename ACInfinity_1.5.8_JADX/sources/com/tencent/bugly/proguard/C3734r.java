package com.tencent.bugly.proguard;

import android.util.Pair;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.tencent.bugly.proguard.r */
/* compiled from: BUGLY */
public final class C3734r {
    /* renamed from: a */
    public final Pair<Integer, String> mo24309a(List<String> list) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("Atta-Type", "batch-report");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("attaid", "0d000062340").put("token", "2273782735").put("type", "batch").put("version", "v1.0.0");
            JSONArray jSONArray = new JSONArray();
            for (String put : list) {
                jSONArray.put(put);
            }
            jSONObject.put("datas", jSONArray);
            return m866a("https://h.trace.qq.com/kv", jSONObject.toString(), hashMap);
        } catch (Throwable th) {
            C3749y.m938b(th);
            return new Pair<>(-1, th.getMessage());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r9v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r9v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ae A[SYNTHETIC, Splitter:B:37:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b9 A[SYNTHETIC, Splitter:B:42:0x00b9] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c3 A[DONT_GENERATE] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<java.lang.Integer, java.lang.String> m866a(java.lang.String r7, java.lang.String r8, java.util.Map<java.lang.String, java.lang.String> r9) {
        /*
            r6 = this;
            java.lang.String r0 = "UTF-8"
            r1 = 0
            r2 = -1
            java.net.URL r3 = new java.net.URL     // Catch:{ all -> 0x00a1 }
            r3.<init>(r7)     // Catch:{ all -> 0x00a1 }
            java.net.URLConnection r7 = r3.openConnection()     // Catch:{ all -> 0x00a1 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ all -> 0x00a1 }
            java.lang.String r3 = "POST"
            r7.setRequestMethod(r3)     // Catch:{ all -> 0x009d }
            r3 = 1
            r7.setDoOutput(r3)     // Catch:{ all -> 0x009d }
            r7.setDoInput(r3)     // Catch:{ all -> 0x009d }
            r3 = 0
            r7.setUseCaches(r3)     // Catch:{ all -> 0x009d }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/x-www-form-urlencoded"
            r7.setRequestProperty(r3, r4)     // Catch:{ all -> 0x009d }
            m867a(r7, r9)     // Catch:{ all -> 0x009d }
            r9 = 5000(0x1388, float:7.006E-42)
            r7.setConnectTimeout(r9)     // Catch:{ all -> 0x009d }
            r7.setReadTimeout(r9)     // Catch:{ all -> 0x009d }
            r7.connect()     // Catch:{ all -> 0x009d }
            byte[] r8 = r8.getBytes(r0)     // Catch:{ all -> 0x009d }
            java.io.DataOutputStream r9 = new java.io.DataOutputStream     // Catch:{ all -> 0x009d }
            java.io.OutputStream r3 = r7.getOutputStream()     // Catch:{ all -> 0x009d }
            r9.<init>(r3)     // Catch:{ all -> 0x009d }
            r9.write(r8)     // Catch:{ all -> 0x0097 }
            r9.flush()     // Catch:{ all -> 0x0097 }
            r9.close()     // Catch:{ all -> 0x0097 }
            int r2 = r7.getResponseCode()     // Catch:{ all -> 0x009d }
            r8 = 400(0x190, float:5.6E-43)
            if (r2 < r8) goto L_0x0057
            java.io.InputStream r8 = r7.getErrorStream()     // Catch:{ all -> 0x009d }
            goto L_0x005b
        L_0x0057:
            java.io.InputStream r8 = r7.getInputStream()     // Catch:{ all -> 0x009d }
        L_0x005b:
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ all -> 0x0091 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0091 }
            r3.<init>(r8, r0)     // Catch:{ all -> 0x0091 }
            r9.<init>(r3)     // Catch:{ all -> 0x0091 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            r0.<init>()     // Catch:{ all -> 0x0091 }
        L_0x006a:
            java.lang.String r3 = r9.readLine()     // Catch:{ all -> 0x0091 }
            if (r3 == 0) goto L_0x0079
            r0.append(r3)     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = "\r\n"
            r0.append(r3)     // Catch:{ all -> 0x0091 }
            goto L_0x006a
        L_0x0079:
            r9.close()     // Catch:{ all -> 0x0091 }
            java.lang.String r9 = r0.toString()     // Catch:{ all -> 0x0091 }
            if (r8 != 0) goto L_0x0083
            goto L_0x008b
        L_0x0083:
            r8.close()     // Catch:{ Exception -> 0x0087 }
            goto L_0x008b
        L_0x0087:
            r8 = move-exception
            com.tencent.bugly.proguard.C3749y.m938b(r8)
        L_0x008b:
            if (r7 == 0) goto L_0x00c7
            r7.disconnect()
            goto L_0x00c7
        L_0x0091:
            r9 = move-exception
            r5 = r9
            r9 = r7
            r7 = r8
            r8 = r5
            goto L_0x00a4
        L_0x0097:
            r8 = move-exception
            r5 = r9
            r9 = r7
            r7 = r1
            r1 = r5
            goto L_0x00a4
        L_0x009d:
            r8 = move-exception
            r9 = r7
            r7 = r1
            goto L_0x00a4
        L_0x00a1:
            r8 = move-exception
            r7 = r1
            r9 = r7
        L_0x00a4:
            com.tencent.bugly.proguard.C3749y.m938b(r8)     // Catch:{ all -> 0x00d1 }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x00d1 }
            if (r1 != 0) goto L_0x00ae
            goto L_0x00b6
        L_0x00ae:
            r1.close()     // Catch:{ Exception -> 0x00b2 }
            goto L_0x00b6
        L_0x00b2:
            r0 = move-exception
            com.tencent.bugly.proguard.C3749y.m938b(r0)
        L_0x00b6:
            if (r7 != 0) goto L_0x00b9
            goto L_0x00c1
        L_0x00b9:
            r7.close()     // Catch:{ Exception -> 0x00bd }
            goto L_0x00c1
        L_0x00bd:
            r7 = move-exception
            com.tencent.bugly.proguard.C3749y.m938b(r7)
        L_0x00c1:
            if (r9 == 0) goto L_0x00c6
            r9.disconnect()
        L_0x00c6:
            r9 = r8
        L_0x00c7:
            android.util.Pair r7 = new android.util.Pair
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            r7.<init>(r8, r9)
            return r7
        L_0x00d1:
            r8 = move-exception
            if (r1 != 0) goto L_0x00d5
            goto L_0x00dd
        L_0x00d5:
            r1.close()     // Catch:{ Exception -> 0x00d9 }
            goto L_0x00dd
        L_0x00d9:
            r0 = move-exception
            com.tencent.bugly.proguard.C3749y.m938b(r0)
        L_0x00dd:
            if (r7 != 0) goto L_0x00e0
            goto L_0x00e8
        L_0x00e0:
            r7.close()     // Catch:{ Exception -> 0x00e4 }
            goto L_0x00e8
        L_0x00e4:
            r7 = move-exception
            com.tencent.bugly.proguard.C3749y.m938b(r7)
        L_0x00e8:
            if (r9 == 0) goto L_0x00ed
            r9.disconnect()
        L_0x00ed:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3734r.m866a(java.lang.String, java.lang.String, java.util.Map):android.util.Pair");
    }

    /* renamed from: a */
    private static void m867a(HttpURLConnection httpURLConnection, Map<String, String> map) {
        if (httpURLConnection != null && map != null && !map.isEmpty()) {
            for (Map.Entry next : map.entrySet()) {
                httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
            }
        }
    }
}
