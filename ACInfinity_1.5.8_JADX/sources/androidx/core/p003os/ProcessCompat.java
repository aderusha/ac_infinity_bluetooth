package androidx.core.p003os;

import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import java.lang.reflect.Method;

/* renamed from: androidx.core.os.ProcessCompat */
public final class ProcessCompat {
    private ProcessCompat() {
    }

    public static boolean isApplicationUid(int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.isApplicationUid(i);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return Api17Impl.isApplicationUid(i);
        }
        if (Build.VERSION.SDK_INT == 16) {
            return Api16Impl.isApplicationUid(i);
        }
        return true;
    }

    /* renamed from: androidx.core.os.ProcessCompat$Api24Impl */
    static class Api24Impl {
        private Api24Impl() {
        }

        static boolean isApplicationUid(int i) {
            return Process.isApplicationUid(i);
        }
    }

    /* renamed from: androidx.core.os.ProcessCompat$Api17Impl */
    static class Api17Impl {
        private static Method sMethodUserHandleIsAppMethod;
        private static boolean sResolved;
        private static final Object sResolvedLock = new Object();

        private Api17Impl() {
        }

        static boolean isApplicationUid(int i) {
            try {
                synchronized (sResolvedLock) {
                    if (!sResolved) {
                        sResolved = true;
                        sMethodUserHandleIsAppMethod = UserHandle.class.getDeclaredMethod("isApp", new Class[]{Integer.TYPE});
                    }
                }
                Method method = sMethodUserHandleIsAppMethod;
                if (method != null) {
                    if (((Boolean) method.invoke((Object) null, new Object[]{Integer.valueOf(i)})) == null) {
                        throw new NullPointerException();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    /* renamed from: androidx.core.os.ProcessCompat$Api16Impl */
    static class Api16Impl {
        private static Method sMethodUserIdIsAppMethod;
        private static boolean sResolved;
        private static final Object sResolvedLock = new Object();

        private Api16Impl() {
        }

        static boolean isApplicationUid(int i) {
            try {
                synchronized (sResolvedLock) {
                    if (!sResolved) {
                        sResolved = true;
                        sMethodUserIdIsAppMethod = Class.forName("android.os.UserId").getDeclaredMethod("isApp", new Class[]{Integer.TYPE});
                    }
                }
                Method method = sMethodUserIdIsAppMethod;
                if (method != null) {
                    Boolean bool = (Boolean) method.invoke((Object) null, new Object[]{Integer.valueOf(i)});
                    if (bool != null) {
                        return bool.booleanValue();
                    }
                    throw new NullPointerException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
