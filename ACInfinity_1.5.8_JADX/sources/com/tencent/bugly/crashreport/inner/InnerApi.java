package com.tencent.bugly.crashreport.inner;

import com.tencent.bugly.crashreport.crash.C3681d;
import com.tencent.bugly.proguard.C3749y;
import java.util.Map;

/* compiled from: BUGLY */
public class InnerApi {
    public static void postU3dCrashAsync(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C3749y.m941e("post u3d fail args null", new Object[0]);
        }
        C3749y.m934a("post u3d crash %s %s", str, str2);
        C3681d.m575a(Thread.currentThread(), 4, str, str2, str3, (Map<String, String>) null);
    }

    public static void postCocos2dxCrashAsync(int i, String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C3749y.m941e("post cocos2d-x fail args null", new Object[0]);
        } else if (i == 5 || i == 6) {
            C3749y.m934a("post cocos2d-x crash %s %s", str, str2);
            C3681d.m575a(Thread.currentThread(), i, str, str2, str3, (Map<String, String>) null);
        } else {
            C3749y.m941e("post cocos2d-x fail category illeagle: %d", Integer.valueOf(i));
        }
    }

    public static void postH5CrashAsync(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (str == null || str2 == null || str3 == null) {
            C3749y.m941e("post h5 fail args null", new Object[0]);
            return;
        }
        C3749y.m934a("post h5 crash %s %s", str, str2);
        C3681d.m575a(thread, 8, str, str2, str3, map);
    }
}
