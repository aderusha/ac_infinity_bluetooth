package com.tencent.bugly.crashreport.crash.p011h5;

import android.webkit.JavascriptInterface;
import com.eternal.framework.http.cookie.SerializableCookie;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3749y;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface */
/* compiled from: BUGLY */
public class H5JavaScriptInterface {

    /* renamed from: a */
    private static HashSet<Integer> f733a = new HashSet<>();

    /* renamed from: b */
    private String f734b = null;

    /* renamed from: c */
    private Thread f735c = null;

    /* renamed from: d */
    private String f736d = null;

    /* renamed from: e */
    private Map<String, String> f737e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(CrashReport.WebViewInterface webViewInterface) {
        String str = null;
        if (webViewInterface == null || f733a.contains(Integer.valueOf(webViewInterface.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        f733a.add(Integer.valueOf(webViewInterface.hashCode()));
        Thread currentThread = Thread.currentThread();
        h5JavaScriptInterface.f735c = currentThread;
        if (currentThread != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (int i = 2; i < currentThread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = currentThread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
            }
            str = sb.toString();
        }
        h5JavaScriptInterface.f736d = str;
        HashMap hashMap = new HashMap();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(webViewInterface.getContentDescription());
        hashMap.put("[WebView] ContentDescription", sb2.toString());
        h5JavaScriptInterface.f737e = hashMap;
        return h5JavaScriptInterface;
    }

    /* renamed from: a */
    private static C3685a m586a(String str) {
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                C3685a aVar = new C3685a();
                aVar.f738a = jSONObject.getString("projectRoot");
                if (aVar.f738a == null) {
                    return null;
                }
                aVar.f739b = jSONObject.getString("context");
                if (aVar.f739b == null) {
                    return null;
                }
                aVar.f740c = jSONObject.getString("url");
                if (aVar.f740c == null) {
                    return null;
                }
                aVar.f741d = jSONObject.getString("userAgent");
                if (aVar.f741d == null) {
                    return null;
                }
                aVar.f742e = jSONObject.getString("language");
                if (aVar.f742e == null) {
                    return null;
                }
                aVar.f743f = jSONObject.getString(SerializableCookie.NAME);
                if (aVar.f743f != null) {
                    if (!aVar.f743f.equals("null")) {
                        String string = jSONObject.getString("stacktrace");
                        if (string == null) {
                            return null;
                        }
                        int indexOf = string.indexOf("\n");
                        if (indexOf < 0) {
                            C3749y.m940d("H5 crash stack's format is wrong!", new Object[0]);
                            return null;
                        }
                        aVar.f745h = string.substring(indexOf + 1);
                        aVar.f744g = string.substring(0, indexOf);
                        int indexOf2 = aVar.f744g.indexOf(":");
                        if (indexOf2 > 0) {
                            aVar.f744g = aVar.f744g.substring(indexOf2 + 1);
                        }
                        aVar.f746i = jSONObject.getString("file");
                        if (aVar.f743f == null) {
                            return null;
                        }
                        aVar.f747j = jSONObject.getLong("lineNumber");
                        if (aVar.f747j < 0) {
                            return null;
                        }
                        aVar.f748k = jSONObject.getLong("columnNumber");
                        if (aVar.f748k < 0) {
                            return null;
                        }
                        C3749y.m934a("H5 crash information is following: ", new Object[0]);
                        C3749y.m934a("[projectRoot]: " + aVar.f738a, new Object[0]);
                        C3749y.m934a("[context]: " + aVar.f739b, new Object[0]);
                        C3749y.m934a("[url]: " + aVar.f740c, new Object[0]);
                        C3749y.m934a("[userAgent]: " + aVar.f741d, new Object[0]);
                        C3749y.m934a("[language]: " + aVar.f742e, new Object[0]);
                        C3749y.m934a("[name]: " + aVar.f743f, new Object[0]);
                        C3749y.m934a("[message]: " + aVar.f744g, new Object[0]);
                        C3749y.m934a("[stacktrace]: \n" + aVar.f745h, new Object[0]);
                        C3749y.m934a("[file]: " + aVar.f746i, new Object[0]);
                        C3749y.m934a("[lineNumber]: " + aVar.f747j, new Object[0]);
                        C3749y.m934a("[columnNumber]: " + aVar.f748k, new Object[0]);
                        return aVar;
                    }
                }
                return null;
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    @JavascriptInterface
    public void printLog(String str) {
        C3749y.m940d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            C3749y.m940d("Payload from JS is null.", new Object[0]);
            return;
        }
        String a = C3695ab.m667a(str.getBytes());
        String str2 = this.f734b;
        if (str2 == null || !str2.equals(a)) {
            this.f734b = a;
            C3749y.m940d("Handling JS exception ...", new Object[0]);
            C3685a a2 = m586a(str);
            if (a2 == null) {
                C3749y.m940d("Failed to parse payload.", new Object[0]);
                return;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            if (a2.f738a != null) {
                linkedHashMap2.put("[JS] projectRoot", a2.f738a);
            }
            if (a2.f739b != null) {
                linkedHashMap2.put("[JS] context", a2.f739b);
            }
            if (a2.f740c != null) {
                linkedHashMap2.put("[JS] url", a2.f740c);
            }
            if (a2.f741d != null) {
                linkedHashMap2.put("[JS] userAgent", a2.f741d);
            }
            if (a2.f746i != null) {
                linkedHashMap2.put("[JS] file", a2.f746i);
            }
            if (a2.f747j != 0) {
                linkedHashMap2.put("[JS] lineNumber", Long.toString(a2.f747j));
            }
            linkedHashMap.putAll(linkedHashMap2);
            linkedHashMap.putAll(this.f737e);
            linkedHashMap.put("Java Stack", this.f736d);
            Thread thread = this.f735c;
            if (a2 != null) {
                InnerApi.postH5CrashAsync(thread, a2.f743f, a2.f744g, a2.f745h, linkedHashMap);
                return;
            }
            return;
        }
        C3749y.m940d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
    }
}
