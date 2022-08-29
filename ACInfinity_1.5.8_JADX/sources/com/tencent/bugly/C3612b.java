package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.biz.C3622b;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.proguard.C3692aa;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3726m;
import com.tencent.bugly.proguard.C3729n;
import com.tencent.bugly.proguard.C3730o;
import com.tencent.bugly.proguard.C3743v;
import com.tencent.bugly.proguard.C3749y;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.b */
/* compiled from: BUGLY */
public final class C3612b {

    /* renamed from: a */
    public static boolean f390a = true;

    /* renamed from: b */
    public static List<C3611a> f391b = new ArrayList();

    /* renamed from: c */
    public static boolean f392c;

    /* renamed from: d */
    private static C3730o f393d;

    /* renamed from: e */
    private static boolean f394e;

    /* renamed from: a */
    private static boolean m281a(C3626a aVar) {
        List<String> list = aVar.f506n;
        aVar.getClass();
        return list != null && list.contains("bugly");
    }

    /* renamed from: a */
    public static synchronized void m277a(Context context) {
        synchronized (C3612b.class) {
            m278a(context, (BuglyStrategy) null);
        }
    }

    /* renamed from: a */
    public static synchronized void m278a(Context context, BuglyStrategy buglyStrategy) {
        synchronized (C3612b.class) {
            if (f394e) {
                C3749y.m940d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(C3749y.f1059a, "[init] context of init() is null, check it.");
            } else {
                C3626a a = C3626a.m337a(context);
                if (m281a(a)) {
                    f390a = false;
                    return;
                }
                String f = a.mo24070f();
                if (f == null) {
                    Log.e(C3749y.f1059a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
                } else {
                    m279a(context, f, a.f512t, buglyStrategy);
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m279a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        byte[] bArr;
        String str2 = str;
        boolean z2 = z;
        BuglyStrategy buglyStrategy2 = buglyStrategy;
        synchronized (C3612b.class) {
            if (f394e) {
                C3749y.m940d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(C3749y.f1059a, "[init] context is null, check it.");
            } else if (str2 == null) {
                Log.e(C3749y.f1059a, "init arg 'crashReportAppID' should not be null!");
            } else {
                f394e = true;
                if (z2) {
                    f392c = true;
                    C3749y.f1060b = true;
                    C3749y.m940d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                    C3749y.m941e("--------------------------------------------------------------------------------------------", new Object[0]);
                    C3749y.m940d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                    C3749y.m940d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                    C3749y.m940d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                    C3749y.m940d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                    C3749y.m941e("--------------------------------------------------------------------------------------------", new Object[0]);
                    C3749y.m937b("[init] Open debug mode of Bugly.", new Object[0]);
                }
                C3749y.m934a(" crash report start initializing...", new Object[0]);
                C3749y.m937b("[init] Bugly start initializing...", new Object[0]);
                C3749y.m934a("[init] Bugly complete version: v%s", "4.0.4");
                Context a = C3695ab.m653a(context);
                C3626a a2 = C3626a.m337a(a);
                a2.mo24086s();
                C3692aa.m638a(a);
                f393d = C3730o.m840a(a, f391b);
                C3743v.m904a(a);
                C3644a.m427a(a, f391b);
                C3726m a3 = C3726m.m816a(a);
                if (m281a(a2)) {
                    f390a = false;
                    return;
                }
                a2.mo24054a(str2);
                C3749y.m934a("[param] Set APP ID:%s", str2);
                if (buglyStrategy2 != null) {
                    String appVersion = buglyStrategy.getAppVersion();
                    if (!TextUtils.isEmpty(appVersion)) {
                        if (appVersion.length() > 100) {
                            String substring = appVersion.substring(0, 100);
                            C3749y.m940d("appVersion %s length is over limit %d substring to %s", appVersion, 100, substring);
                            appVersion = substring;
                        }
                        a2.f501i = appVersion;
                        C3749y.m934a("[param] Set App version: %s", buglyStrategy.getAppVersion());
                    }
                    try {
                        if (buglyStrategy.isReplaceOldChannel()) {
                            String appChannel = buglyStrategy.getAppChannel();
                            if (!TextUtils.isEmpty(appChannel)) {
                                if (appChannel.length() > 100) {
                                    String substring2 = appChannel.substring(0, 100);
                                    C3749y.m940d("appChannel %s length is over limit %d substring to %s", appChannel, 100, substring2);
                                    appChannel = substring2;
                                }
                                f393d.mo24298a(556, "app_channel", appChannel.getBytes(), (C3729n) null, false);
                                a2.f503k = appChannel;
                            }
                        } else {
                            Map<String, byte[]> a4 = f393d.mo24296a(556, (C3729n) null, true);
                            if (!(a4 == null || (bArr = a4.get("app_channel")) == null)) {
                                a2.f503k = new String(bArr);
                            }
                        }
                        C3749y.m934a("[param] Set App channel: %s", a2.f503k);
                    } catch (Exception e) {
                        if (f392c) {
                            e.printStackTrace();
                        }
                    }
                    String appPackageName = buglyStrategy.getAppPackageName();
                    if (!TextUtils.isEmpty(appPackageName)) {
                        if (appPackageName.length() > 100) {
                            String substring3 = appPackageName.substring(0, 100);
                            C3749y.m940d("appPackageName %s length is over limit %d substring to %s", appPackageName, 100, substring3);
                            appPackageName = substring3;
                        }
                        a2.f495c = appPackageName;
                        C3749y.m934a("[param] Set App package: %s", buglyStrategy.getAppPackageName());
                    }
                    String deviceID = buglyStrategy.getDeviceID();
                    if (deviceID != null) {
                        if (deviceID.length() > 100) {
                            String substring4 = deviceID.substring(0, 100);
                            C3749y.m940d("deviceId %s length is over limit %d substring to %s", deviceID, 100, substring4);
                            deviceID = substring4;
                        }
                        a2.mo24064c(deviceID);
                        C3749y.m934a("[param] Set device ID: %s", deviceID);
                    }
                    String deviceModel = buglyStrategy.getDeviceModel();
                    if (deviceModel != null) {
                        a2.mo24067d(deviceModel);
                        C3749y.m934a("[param] Set device model: %s", deviceModel);
                    }
                    a2.f497e = buglyStrategy.isUploadProcess();
                    C3692aa.f778a = buglyStrategy.isBuglyLogUpload();
                }
                for (int i = 0; i < f391b.size(); i++) {
                    try {
                        if (a3.mo24285a(f391b.get(i).f389id)) {
                            f391b.get(i).init(a, z2, buglyStrategy2);
                        }
                    } catch (Throwable th) {
                        if (!C3749y.m935a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
                C3622b.m308a(a, buglyStrategy2);
                C3644a.m426a().mo24099a(buglyStrategy2 != null ? buglyStrategy.getAppReportDelay() : 0);
                C3749y.m937b("[init] Bugly initialization finished.", new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m280a(C3611a aVar) {
        synchronized (C3612b.class) {
            if (!f391b.contains(aVar)) {
                f391b.add(aVar);
            }
        }
    }
}
