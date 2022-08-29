package com.tencent.bugly.crashreport;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.core.p003os.EnvironmentCompat;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.C3611a;
import com.tencent.bugly.C3612b;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.biz.C3622b;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.crashreport.crash.C3678c;
import com.tencent.bugly.crashreport.crash.C3681d;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.crashreport.crash.p011h5.C3686b;
import com.tencent.bugly.crashreport.crash.p011h5.H5JavaScriptInterface;
import com.tencent.bugly.proguard.C3691a;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3732p;
import com.tencent.bugly.proguard.C3747x;
import com.tencent.bugly.proguard.C3749y;
import java.net.InetAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: BUGLY */
public class CrashReport {

    /* renamed from: a */
    private static Context f395a;

    /* compiled from: BUGLY */
    public static class CrashHandleCallback extends BuglyStrategy.C3610a {
    }

    /* compiled from: BUGLY */
    public interface WebViewInterface {
        void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str);

        CharSequence getContentDescription();

        String getUrl();

        void loadUrl(String str);

        void setJavaScriptEnabled(boolean z);
    }

    public static void enableBugly(boolean z) {
        C3612b.f390a = z;
    }

    public static void initCrashReport(Context context) {
        if (context != null) {
            f395a = context;
            C3612b.m280a((C3611a) CrashModule.getInstance());
            C3612b.m277a(context);
        }
    }

    public static void initCrashReport(Context context, UserStrategy userStrategy) {
        if (context != null) {
            f395a = context;
            C3612b.m280a((C3611a) CrashModule.getInstance());
            C3612b.m278a(context, userStrategy);
        }
    }

    public static void initCrashReport(Context context, String str, boolean z) {
        if (context != null) {
            f395a = context;
            C3612b.m280a((C3611a) CrashModule.getInstance());
            C3612b.m279a(context, str, z, (BuglyStrategy) null);
        }
    }

    public static void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context != null) {
            f395a = context;
            C3612b.m280a((C3611a) CrashModule.getInstance());
            C3612b.m279a(context, str, z, userStrategy);
        }
    }

    public static Context getContext() {
        return f395a;
    }

    public static String getBuglyVersion(Context context) {
        if (context != null) {
            return C3626a.m337a(context).mo24063c();
        }
        C3749y.m940d("Please call with context.", new Object[0]);
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public static void testJavaCrash() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not test Java crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C3626a b = C3626a.m339b();
            if (b != null) {
                b.mo24059b(24096);
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        }
    }

    public static void testNativeCrash() {
        testNativeCrash(false, false, false);
    }

    public static void testNativeCrash(boolean z, boolean z2, boolean z3) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not test native crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C3749y.m934a("start to create a native crash for test!", new Object[0]);
            C3678c.m540a().mo24149a(z, z2, z3);
        }
    }

    public static void testANRCrash() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not test ANR crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C3749y.m934a("start to create a anr crash for test!", new Object[0]);
            C3678c.m540a().mo24160l();
        }
    }

    public static void postException(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C3681d.m575a(thread, i, str, str2, str3, map);
        }
    }

    public static void postException(int i, String str, String str2, String str3, Map<String, String> map) {
        postException(Thread.currentThread(), i, str, str2, str3, map);
    }

    public static void postCatchedException(Throwable th) {
        postCatchedException(th, Thread.currentThread(), false);
    }

    public static void postCatchedException(Throwable th, Thread thread) {
        postCatchedException(th, thread, false);
    }

    public static void postCatchedException(Throwable th, Thread thread, boolean z) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (th == null) {
            C3749y.m940d("throwable is null, just return", new Object[0]);
        } else {
            if (thread == null) {
                thread = Thread.currentThread();
            }
            C3678c.m540a().mo24147a(thread, th, false, (String) null, (byte[]) null, z, true);
        }
    }

    public static void setAllThreadStackEnable(Context context, boolean z, boolean z2) {
        C3626a.m337a(context).mo24057a(z, z2);
    }

    public static void closeNativeReport() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not close native report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C3678c.m540a().mo24155g();
        }
    }

    public static void startCrashReport() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not start crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C3678c.m540a().mo24151c();
        }
    }

    public static void closeCrashReport() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not close crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C3678c.m540a().mo24152d();
        }
    }

    public static void closeBugly() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not close bugly because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (f395a != null) {
            BuglyBroadcastReceiver instance = BuglyBroadcastReceiver.getInstance();
            if (instance != null) {
                instance.unregister(f395a);
            }
            closeCrashReport();
            C3622b.m307a(f395a);
            C3747x a = C3747x.m926a();
            if (a != null) {
                a.mo24346b();
            }
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set tag caught because bugly is disable.");
        } else if (context == null) {
            Log.e(C3749y.f1059a, "setTag args context should not be null");
        } else {
            if (i <= 0) {
                C3749y.m940d("setTag args tagId should > 0", new Object[0]);
            }
            C3626a.m337a(context).mo24052a(i);
            C3749y.m937b("[param] set user scene tag: %d", Integer.valueOf(i));
        }
    }

    public static int getUserSceneTagId(Context context) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get user scene tag because bugly is disable.");
            return -1;
        } else if (context != null) {
            return C3626a.m337a(context).mo24049D();
        } else {
            Log.e(C3749y.f1059a, "getUserSceneTagId args context should not be null");
            return -1;
        }
    }

    public static String getUserData(Context context, String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get user data because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (context == null) {
            Log.e(C3749y.f1059a, "getUserDataValue args context should not be null");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (C3695ab.m679a(str)) {
            return null;
        } else {
            return C3626a.m337a(context).mo24076i(str);
        }
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not put user data because bugly is disable.");
        } else if (context == null) {
            Log.w(C3749y.f1059a, "putUserData args context should not be null");
        } else if (str == null) {
            C3749y.m940d("putUserData args key should not be null or empty", new Object[0]);
        } else if (str2 == null) {
            C3749y.m940d("putUserData args value should not be null", new Object[0]);
        } else {
            if (str2.length() > 200) {
                C3749y.m940d("user data value length over limit %d, it will be cutted!", 200);
                str2 = str2.substring(0, 200);
            }
            C3626a a = C3626a.m337a(context);
            if (a.mo24046A().contains(str)) {
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                C3626a.m337a(context).mo24061b(str, str2);
                C3749y.m939c("replace KV %s %s", str, str2);
            } else if (a.mo24093z() >= 50) {
                C3749y.m940d("user data size is over limit %d, it will be cutted!", 50);
            } else {
                if (str.length() > 50) {
                    C3749y.m940d("user data key length over limit %d , will drop this new key %s", 50, str);
                    str = str.substring(0, 50);
                }
                NativeCrashHandler instance2 = NativeCrashHandler.getInstance();
                if (instance2 != null) {
                    instance2.putKeyValueToNative(str, str2);
                }
                C3626a.m337a(context).mo24061b(str, str2);
                C3749y.m937b("[param] set user data: %s - %s", str, str2);
            }
        }
    }

    public static String removeUserData(Context context, String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not remove user data because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (context == null) {
            Log.e(C3749y.f1059a, "removeUserData args context should not be null");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (C3695ab.m679a(str)) {
            return null;
        } else {
            C3749y.m937b("[param] remove user data: %s", str);
            return C3626a.m337a(context).mo24074h(str);
        }
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        } else if (context != null) {
            return C3626a.m337a(context).mo24046A();
        } else {
            Log.e(C3749y.f1059a, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
    }

    public static int getUserDatasSize(Context context) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get size of user data because bugly is disable.");
            return -1;
        } else if (context != null) {
            return C3626a.m337a(context).mo24093z();
        } else {
            Log.e(C3749y.f1059a, "getUserDatasSize args context should not be null");
            return -1;
        }
    }

    public static String getAppID() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get App ID because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (CrashModule.getInstance().hasInitialized()) {
            return C3626a.m337a(f395a).mo24070f();
        } else {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static void setUserId(String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set user ID because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            setUserId(f395a, str);
        }
    }

    public static void setUserId(Context context, String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set user ID because bugly is disable.");
        } else if (context == null) {
            Log.e(C3749y.f1059a, "Context should not be null when bugly has not been initialed!");
        } else if (TextUtils.isEmpty(str)) {
            C3749y.m940d("userId should not be null", new Object[0]);
        } else {
            if (str.length() > 100) {
                String substring = str.substring(0, 100);
                C3749y.m940d("userId %s length is over limit %d substring to %s", str, 100, substring);
                str = substring;
            }
            if (!str.equals(C3626a.m337a(context).mo24072g())) {
                C3626a.m337a(context).mo24060b(str);
                C3749y.m937b("[user] set userId : %s", str);
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.setNativeUserId(str);
                }
                if (CrashModule.getInstance().hasInitialized()) {
                    C3622b.m305a();
                }
            }
        }
    }

    public static String getUserId() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get user ID because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (CrashModule.getInstance().hasInitialized()) {
            return C3626a.m337a(f395a).mo24072g();
        } else {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static void setDeviceId(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            C3626a.m337a(context).mo24064c(str);
        }
    }

    public static void setDeviceModel(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            C3626a.m337a(context).mo24067d(str);
        }
    }

    public static String getDeviceID(Context context) {
        return C3626a.m337a(context).mo24079k();
    }

    public static String getAppVer() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get app version because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (CrashModule.getInstance().hasInitialized()) {
            return C3626a.m337a(f395a).f501i;
        } else {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static String getAppChannel() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get App channel because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else if (CrashModule.getInstance().hasInitialized()) {
            return C3626a.m337a(f395a).f503k;
        } else {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static void setContext(Context context) {
        f395a = context;
    }

    public static boolean isLastSessionCrash() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        } else if (CrashModule.getInstance().hasInitialized()) {
            return C3678c.m540a().mo24150b();
        } else {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
    }

    public static void setSdkExtraData(Context context, String str, String str2) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not put SDK extra data because bugly is disable.");
        } else if (context != null && !C3695ab.m679a(str) && !C3695ab.m679a(str2)) {
            C3626a.m337a(context).mo24055a(str, str2);
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (CrashModule.getInstance().hasInitialized()) {
            return C3626a.m337a(f395a).f451A;
        } else {
            Log.e(C3749y.f1059a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (context != null) {
            return C3626a.m337a(context).f451A;
        } else {
            C3749y.m940d("Context should not be null.", new Object[0]);
            return null;
        }
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context != null && !C3695ab.m679a(str) && !C3695ab.m679a(str2)) {
            String replace = str.replace("[a-zA-Z[0-9]]+", "");
            if (replace.length() > 100) {
                Log.w(C3749y.f1059a, String.format("putSdkData key length over limit %d, will be cutted.", new Object[]{50}));
                replace = replace.substring(0, 50);
            }
            if (str2.length() > 500) {
                Log.w(C3749y.f1059a, String.format("putSdkData value length over limit %d, will be cutted!", new Object[]{200}));
                str2 = str2.substring(0, 200);
            }
            C3626a.m337a(context).mo24065c(replace, str2);
            C3749y.m937b(String.format("[param] putSdkData data: %s - %s", new Object[]{replace, str2}), new Object[0]);
        }
    }

    @Deprecated
    public static void setIsAppForeground(Context context, boolean z) {
        C3749y.m934a("App fore and back status are no longer supported", new Object[0]);
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
        } else if (context == null) {
            C3749y.m940d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                C3749y.m939c("This is a development device.", new Object[0]);
            } else {
                C3749y.m939c("This is not a development device.", new Object[0]);
            }
            C3626a.m337a(context).f517y = z;
        }
    }

    public static void setSessionIntervalMills(long j) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set 'SessionIntervalMills' because bugly is disable.");
        } else {
            C3622b.m306a(j);
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set App version because bugly is disable.");
        } else if (context == null) {
            Log.w(C3749y.f1059a, "setAppVersion args context should not be null");
        } else if (str == null) {
            Log.w(C3749y.f1059a, "App version is null, will not set");
        } else {
            C3626a.m337a(context).f501i = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppVersion(str);
            }
        }
    }

    public static void setAppChannel(Context context, String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set App channel because Bugly is disable.");
        } else if (context == null) {
            Log.w(C3749y.f1059a, "setAppChannel args context should not be null");
        } else if (str == null) {
            Log.w(C3749y.f1059a, "App channel is null, will not set");
        } else {
            C3626a.m337a(context).f503k = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppChannel(str);
            }
        }
    }

    public static void setAppPackage(Context context, String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set App package because bugly is disable.");
        } else if (context == null) {
            Log.w(C3749y.f1059a, "setAppPackage args context should not be null");
        } else if (str == null) {
            Log.w(C3749y.f1059a, "App package is null, will not set");
        } else {
            C3626a.m337a(context).f495c = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppPackage(str);
            }
        }
    }

    public static void setCrashFilter(String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set App package because bugly is disable.");
            return;
        }
        String str2 = C3749y.f1059a;
        Log.i(str2, "Set crash stack filter: " + str);
        C3678c.f689n = str;
    }

    public static void setCrashRegularFilter(String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set App package because bugly is disable.");
            return;
        }
        String str2 = C3749y.f1059a;
        Log.i(str2, "Set crash stack filter: " + str);
        C3678c.f690o = str;
    }

    public static void setHandleNativeCrashInJava(boolean z) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set App package because bugly is disable.");
            return;
        }
        String str = C3749y.f1059a;
        Log.i(str, "Should handle native crash in Java profile after handled in native profile: " + z);
        NativeCrashHandler.setShouldHandleInJava(z);
    }

    public static void setBuglyDbName(String str) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set DB name because bugly is disable.");
            return;
        }
        String str2 = C3749y.f1059a;
        Log.i(str2, "Set Bugly DB name: " + str);
        C3732p.f994a = str;
    }

    public static void enableObtainId(Context context, boolean z) {
        setCollectPrivacyInfo(context, z);
    }

    public static void setCollectPrivacyInfo(Context context, boolean z) {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not set collect privacy info enable because bugly is disable.");
        } else if (context == null) {
            Log.w(C3749y.f1059a, "setCollectPrivacyInfo args context should not be null");
        } else {
            String str = C3749y.f1059a;
            Log.i(str, "setCollectPrivacyInfo: " + z);
            C3626a.m337a(context).mo24056a(z);
        }
    }

    public static void setServerUrl(String str) {
        if (C3695ab.m679a(str) || !C3695ab.m694c(str)) {
            Log.i(C3749y.f1059a, "URL is invalid.");
            return;
        }
        C3644a.m428a(str);
        StrategyBean.f522a = str;
        StrategyBean.f523b = str;
    }

    public static void uploadUserInfo() {
        if (!C3612b.f390a) {
            Log.w(C3749y.f1059a, "Can not upload user info because bugly is disable.");
        } else if (C3622b.f430a == null) {
            Log.w(C3749y.f1059a, "Can not upload user info because bugly is not init.");
        } else {
            C3622b.f430a.mo24027b();
        }
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    public static boolean setJavascriptMonitor(final WebView webView, boolean z, boolean z2) {
        if (webView == null) {
            Log.w(C3749y.f1059a, "WebView is null.");
            return false;
        }
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setAllowFileAccess(false);
        return setJavascriptMonitor((WebViewInterface) new WebViewInterface() {
            public final String getUrl() {
                return webView.getUrl();
            }

            public final void setJavaScriptEnabled(boolean z) {
                WebSettings settings = webView.getSettings();
                if (!settings.getJavaScriptEnabled()) {
                    settings.setJavaScriptEnabled(true);
                }
            }

            public final void loadUrl(String str) {
                webView.loadUrl(str);
            }

            public final void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str) {
                webView.addJavascriptInterface(h5JavaScriptInterface, str);
            }

            public final CharSequence getContentDescription() {
                return webView.getContentDescription();
            }
        }, z, z2);
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z) {
        return setJavascriptMonitor(webViewInterface, z, false);
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z, boolean z2) {
        if (webViewInterface == null) {
            Log.w(C3749y.f1059a, "WebViewInterface is null.");
            return false;
        } else if (!CrashModule.getInstance().hasInitialized()) {
            C3749y.m941e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        } else {
            C3749y.m934a("Set Javascript exception monitor of webview.", new Object[0]);
            if (!C3612b.f390a) {
                Log.w(C3749y.f1059a, "Can not set JavaScript monitor because bugly is disable.");
                return false;
            }
            C3749y.m939c("URL of webview is %s", webViewInterface.getUrl());
            if (z2 || Build.VERSION.SDK_INT >= 19) {
                C3749y.m934a("Enable the javascript needed by webview monitor.", new Object[0]);
                webViewInterface.setJavaScriptEnabled(true);
                H5JavaScriptInterface instance = H5JavaScriptInterface.getInstance(webViewInterface);
                if (instance != null) {
                    C3749y.m934a("Add a secure javascript interface to the webview.", new Object[0]);
                    webViewInterface.addJavascriptInterface(instance, "exceptionUploader");
                }
                if (z) {
                    C3749y.m934a("Inject bugly.js(v%s) to the webview.", C3686b.m588b());
                    String a = C3686b.m587a();
                    if (a == null) {
                        C3749y.m941e("Failed to inject Bugly.js.", C3686b.m588b());
                        return false;
                    }
                    webViewInterface.loadUrl("javascript:" + a);
                }
                return true;
            }
            C3749y.m941e("This interface is only available for Android 4.4 or later.", new Object[0]);
            return false;
        }
    }

    public static void setHttpProxy(String str, int i) {
        C3691a.m624a(str, i);
    }

    public static void setHttpProxy(InetAddress inetAddress, int i) {
        C3691a.m625a(inetAddress, i);
    }

    public static Proxy getHttpProxy() {
        return C3691a.m631b();
    }

    /* compiled from: BUGLY */
    public static class UserStrategy extends BuglyStrategy {

        /* renamed from: c */
        private CrashHandleCallback f397c;

        public UserStrategy(Context context) {
        }

        public synchronized void setCallBackType(int i) {
            this.f362a = i;
        }

        public synchronized int getCallBackType() {
            return this.f362a;
        }

        public synchronized void setCloseErrorCallback(boolean z) {
            this.f363b = z;
        }

        public synchronized boolean getCloseErrorCallback() {
            return this.f363b;
        }

        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.f397c;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.f397c = crashHandleCallback;
        }
    }
}
