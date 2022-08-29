package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3729n;
import com.tencent.bugly.proguard.C3730o;
import com.tencent.bugly.proguard.C3749y;
import java.util.Map;

/* compiled from: BUGLY */
public class Bugly {
    public static final String SDK_IS_DEV = "false";

    /* renamed from: a */
    private static boolean f359a = false;
    public static Context applicationContext = null;

    /* renamed from: b */
    private static String[] f360b = {"BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule"};

    /* renamed from: c */
    private static String[] f361c = {"BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule"};
    public static boolean enable = true;
    public static Boolean isDev;

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, (BuglyStrategy) null);
    }

    public static synchronized void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        synchronized (Bugly.class) {
            if (!f359a) {
                f359a = true;
                Context a = C3695ab.m653a(context);
                applicationContext = a;
                if (a == null) {
                    Log.e(C3749y.f1059a, "init arg 'context' should not be null!");
                    return;
                }
                if (isDev()) {
                    f360b = f361c;
                }
                for (String str2 : f360b) {
                    try {
                        if (str2.equals("BuglyCrashModule")) {
                            C3612b.m280a((C3611a) CrashModule.getInstance());
                        } else if (!str2.equals("BuglyBetaModule") && !str2.equals("BuglyRqdModule")) {
                            str2.equals("BuglyFeedbackModule");
                        }
                    } catch (Throwable th) {
                        C3749y.m938b(th);
                    }
                }
                C3612b.f390a = enable;
                C3612b.m279a(applicationContext, str, z, buglyStrategy);
            }
        }
    }

    public static synchronized String getAppChannel() {
        byte[] bArr;
        synchronized (Bugly.class) {
            C3626a b = C3626a.m339b();
            if (b == null) {
                return null;
            }
            if (TextUtils.isEmpty(b.f503k)) {
                C3730o a = C3730o.m839a();
                if (a == null) {
                    String str = b.f503k;
                    return str;
                }
                Map<String, byte[]> a2 = a.mo24296a(556, (C3729n) null, true);
                if (!(a2 == null || (bArr = a2.get("app_channel")) == null)) {
                    String str2 = new String(bArr);
                    return str2;
                }
            }
            String str3 = b.f503k;
            return str3;
        }
    }

    public static boolean isDev() {
        if (isDev == null) {
            isDev = Boolean.valueOf(Boolean.parseBoolean(SDK_IS_DEV.replace("@", "")));
        }
        return isDev.booleanValue();
    }
}
