package retrofit2;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import retrofit2.CallAdapter;

class Platform {
    private static final Platform PLATFORM = findPlatform();

    /* access modifiers changed from: package-private */
    @Nullable
    public Executor defaultCallbackExecutor() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isDefaultMethod(Method method) {
        return false;
    }

    Platform() {
    }

    static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException unused) {
        }
        try {
            Class.forName("java.util.Optional");
            return new Java8();
        } catch (ClassNotFoundException unused2) {
            return new Platform();
        }
    }

    /* access modifiers changed from: package-private */
    public CallAdapter.Factory defaultCallAdapterFactory(@Nullable Executor executor) {
        if (executor != null) {
            return new ExecutorCallAdapterFactory(executor);
        }
        return DefaultCallAdapterFactory.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
        throw new UnsupportedOperationException();
    }

    static class Java8 extends Platform {
        Java8() {
        }

        /* access modifiers changed from: package-private */
        public boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        /* access modifiers changed from: package-private */
        public Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
            Constructor<MethodHandles.Lookup> declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(new Object[]{cls, -1}).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
        }
    }

    static class Android extends Platform {
        Android() {
        }

        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        /* access modifiers changed from: package-private */
        public CallAdapter.Factory defaultCallAdapterFactory(@Nullable Executor executor) {
            if (executor != null) {
                return new ExecutorCallAdapterFactory(executor);
            }
            throw new AssertionError();
        }

        static class MainThreadExecutor implements Executor {
            private final Handler handler = new Handler(Looper.getMainLooper());

            MainThreadExecutor() {
            }

            public void execute(Runnable runnable) {
                this.handler.post(runnable);
            }
        }
    }
}
