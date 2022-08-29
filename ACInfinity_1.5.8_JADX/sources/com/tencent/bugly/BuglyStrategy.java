package com.tencent.bugly;

import com.tencent.bugly.crashreport.common.info.C3626a;
import java.util.Map;

/* compiled from: BUGLY */
public class BuglyStrategy {

    /* renamed from: a */
    protected int f362a = 31;

    /* renamed from: b */
    protected boolean f363b = false;

    /* renamed from: c */
    private String f364c;

    /* renamed from: d */
    private String f365d;

    /* renamed from: e */
    private String f366e;

    /* renamed from: f */
    private long f367f;

    /* renamed from: g */
    private String f368g;

    /* renamed from: h */
    private String f369h;

    /* renamed from: i */
    private String f370i;

    /* renamed from: j */
    private boolean f371j = true;

    /* renamed from: k */
    private boolean f372k = true;

    /* renamed from: l */
    private boolean f373l = false;

    /* renamed from: m */
    private boolean f374m = false;

    /* renamed from: n */
    private boolean f375n = true;

    /* renamed from: o */
    private Class<?> f376o = null;

    /* renamed from: p */
    private boolean f377p = true;

    /* renamed from: q */
    private boolean f378q = true;

    /* renamed from: r */
    private boolean f379r = true;

    /* renamed from: s */
    private boolean f380s = true;

    /* renamed from: t */
    private boolean f381t = false;

    /* renamed from: u */
    private C3610a f382u;

    /* renamed from: v */
    private boolean f383v = false;

    public synchronized BuglyStrategy setBuglyLogUpload(boolean z) {
        this.f377p = z;
        return this;
    }

    public synchronized BuglyStrategy setRecordUserInfoOnceADay(boolean z) {
        this.f381t = z;
        return this;
    }

    public synchronized BuglyStrategy setUploadProcess(boolean z) {
        this.f379r = z;
        return this;
    }

    public synchronized boolean isUploadProcess() {
        return this.f379r;
    }

    public synchronized boolean isBuglyLogUpload() {
        return this.f377p;
    }

    public synchronized boolean recordUserInfoOnceADay() {
        return this.f381t;
    }

    public boolean isReplaceOldChannel() {
        return this.f378q;
    }

    public void setReplaceOldChannel(boolean z) {
        this.f378q = z;
    }

    public synchronized String getAppVersion() {
        String str = this.f364c;
        if (str != null) {
            return str;
        }
        return C3626a.m339b().f501i;
    }

    public synchronized BuglyStrategy setAppVersion(String str) {
        this.f364c = str;
        return this;
    }

    public synchronized BuglyStrategy setUserInfoActivity(Class<?> cls) {
        this.f376o = cls;
        return this;
    }

    public synchronized Class<?> getUserInfoActivity() {
        return this.f376o;
    }

    public synchronized String getAppChannel() {
        String str = this.f365d;
        if (str != null) {
            return str;
        }
        return C3626a.m339b().f503k;
    }

    public synchronized BuglyStrategy setAppChannel(String str) {
        this.f365d = str;
        return this;
    }

    public synchronized String getAppPackageName() {
        String str = this.f366e;
        if (str != null) {
            return str;
        }
        return C3626a.m339b().f495c;
    }

    public synchronized BuglyStrategy setAppPackageName(String str) {
        this.f366e = str;
        return this;
    }

    public synchronized long getAppReportDelay() {
        return this.f367f;
    }

    public synchronized BuglyStrategy setAppReportDelay(long j) {
        this.f367f = j;
        return this;
    }

    public synchronized String getLibBuglySOFilePath() {
        return this.f368g;
    }

    public synchronized BuglyStrategy setLibBuglySOFilePath(String str) {
        this.f368g = str;
        return this;
    }

    public synchronized String getDeviceID() {
        return this.f369h;
    }

    public synchronized BuglyStrategy setDeviceID(String str) {
        this.f369h = str;
        return this;
    }

    public synchronized String getDeviceModel() {
        return this.f370i;
    }

    public synchronized BuglyStrategy setDeviceModel(String str) {
        this.f370i = str;
        return this;
    }

    public synchronized boolean isEnableNativeCrashMonitor() {
        return this.f371j;
    }

    public synchronized BuglyStrategy setEnableNativeCrashMonitor(boolean z) {
        this.f371j = z;
        return this;
    }

    public synchronized BuglyStrategy setEnableUserInfo(boolean z) {
        this.f375n = z;
        return this;
    }

    public synchronized boolean isEnableUserInfo() {
        return this.f375n;
    }

    public synchronized boolean isEnableCatchAnrTrace() {
        return this.f373l;
    }

    public void setEnableCatchAnrTrace(boolean z) {
        this.f373l = z;
    }

    public void setEnableRecordAnrMainStack(boolean z) {
        this.f374m = z;
    }

    public boolean isEnableRecordAnrMainStack() {
        return this.f374m;
    }

    public synchronized boolean isEnableANRCrashMonitor() {
        return this.f372k;
    }

    public synchronized BuglyStrategy setEnableANRCrashMonitor(boolean z) {
        this.f372k = z;
        return this;
    }

    public synchronized C3610a getCrashHandleCallback() {
        return this.f382u;
    }

    public synchronized BuglyStrategy setCrashHandleCallback(C3610a aVar) {
        this.f382u = aVar;
        return this;
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

    public boolean isMerged() {
        return this.f383v;
    }

    @Deprecated
    public void setMerged(boolean z) {
        this.f383v = z;
    }

    public synchronized void setUploadSpotCrash(boolean z) {
        this.f380s = z;
    }

    public synchronized boolean isUploadSpotCrash() {
        return this.f380s;
    }

    /* renamed from: com.tencent.bugly.BuglyStrategy$a */
    /* compiled from: BUGLY */
    public static class C3610a {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_BLOCK = 7;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 100000;

        public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            return null;
        }

        public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            return null;
        }
    }
}
