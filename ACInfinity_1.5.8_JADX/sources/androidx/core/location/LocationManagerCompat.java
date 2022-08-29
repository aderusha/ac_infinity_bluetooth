package androidx.core.location;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.location.GnssStatusCompat;
import androidx.core.p003os.CancellationSignal;
import androidx.core.p003os.ExecutorCompat;
import androidx.core.util.Consumer;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class LocationManagerCompat {
    private static final long GET_CURRENT_LOCATION_TIMEOUT_MS = 30000;
    private static final long MAX_CURRENT_LOCATION_AGE_MS = 10000;
    private static final long PRE_N_LOOPER_TIMEOUT_S = 5;
    private static Field sContextField;
    static final WeakHashMap<LocationListener, List<WeakReference<LocationListenerTransport>>> sLocationListeners = new WeakHashMap<>();
    private static Method sRequestLocationUpdatesExecutorMethod;
    private static Method sRequestLocationUpdatesLooperMethod;

    public static boolean isLocationEnabled(LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.isLocationEnabled(locationManager);
        }
        if (Build.VERSION.SDK_INT <= 19) {
            try {
                if (sContextField == null) {
                    Field declaredField = LocationManager.class.getDeclaredField("mContext");
                    sContextField = declaredField;
                    declaredField.setAccessible(true);
                }
                Context context = (Context) sContextField.get(locationManager);
                if (context != null) {
                    if (Build.VERSION.SDK_INT != 19) {
                        return !TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed"));
                    }
                    if (Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) != 0) {
                        return true;
                    }
                    return false;
                }
            } catch (ClassCastException | IllegalAccessException | NoSuchFieldException | SecurityException unused) {
            }
        }
        if (locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps")) {
            return true;
        }
        return false;
    }

    public static boolean hasProvider(LocationManager locationManager, String str) {
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.hasProvider(locationManager, str);
        }
        if (locationManager.getAllProviders().contains(str)) {
            return true;
        }
        try {
            if (locationManager.getProvider(str) != null) {
                return true;
            }
            return false;
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static void getCurrentLocation(LocationManager locationManager, String str, CancellationSignal cancellationSignal, Executor executor, Consumer<Location> consumer) {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.getCurrentLocation(locationManager, str, cancellationSignal, executor, consumer);
            return;
        }
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        Location lastKnownLocation = locationManager.getLastKnownLocation(str);
        if (lastKnownLocation == null || SystemClock.elapsedRealtime() - LocationCompat.getElapsedRealtimeMillis(lastKnownLocation) >= MAX_CURRENT_LOCATION_AGE_MS) {
            final CancellableLocationListener cancellableLocationListener = new CancellableLocationListener(locationManager, executor, consumer);
            locationManager.requestLocationUpdates(str, 0, 0.0f, cancellableLocationListener, Looper.getMainLooper());
            if (cancellationSignal != null) {
                cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
                    public void onCancel() {
                        CancellableLocationListener.this.cancel();
                    }
                });
            }
            cancellableLocationListener.startTimeout(GET_CURRENT_LOCATION_TIMEOUT_MS);
            return;
        }
        executor.execute(new LocationManagerCompat$$ExternalSyntheticLambda0(consumer, lastKnownLocation));
    }

    public static void requestLocationUpdates(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, Executor executor, LocationListenerCompat locationListenerCompat) {
        LocationManager locationManager2 = locationManager;
        String str2 = str;
        LocationRequestCompat locationRequestCompat2 = locationRequestCompat;
        Executor executor2 = executor;
        LocationListenerCompat locationListenerCompat2 = locationListenerCompat;
        if (Build.VERSION.SDK_INT >= 31) {
            Api31Impl.requestLocationUpdates(locationManager, str, locationRequestCompat.toLocationRequest(), executor2, locationListenerCompat2);
            return;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            try {
                if (sRequestLocationUpdatesExecutorMethod == null) {
                    Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", new Class[]{LocationRequest.class, Executor.class, LocationListener.class});
                    sRequestLocationUpdatesExecutorMethod = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                LocationRequest locationRequest = locationRequestCompat.toLocationRequest(str);
                if (locationRequest != null) {
                    sRequestLocationUpdatesExecutorMethod.invoke(locationManager, new Object[]{locationRequest, executor2, locationListenerCompat2});
                    return;
                }
            } catch (IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
            }
        }
        LocationListenerTransport locationListenerTransport = new LocationListenerTransport(locationListenerCompat2, executor2);
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                if (sRequestLocationUpdatesLooperMethod == null) {
                    Method declaredMethod2 = LocationManager.class.getDeclaredMethod("requestLocationUpdates", new Class[]{LocationRequest.class, LocationListener.class, Looper.class});
                    sRequestLocationUpdatesLooperMethod = declaredMethod2;
                    declaredMethod2.setAccessible(true);
                }
                LocationRequest locationRequest2 = locationRequestCompat.toLocationRequest(str);
                if (locationRequest2 != null) {
                    synchronized (sLocationListeners) {
                        sRequestLocationUpdatesLooperMethod.invoke(locationManager, new Object[]{locationRequest2, locationListenerTransport, Looper.getMainLooper()});
                        locationListenerTransport.register();
                    }
                    return;
                }
            } catch (IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused2) {
            }
        }
        synchronized (sLocationListeners) {
            long intervalMillis = locationRequestCompat.getIntervalMillis();
            locationManager.requestLocationUpdates(str, intervalMillis, locationRequestCompat.getMinUpdateDistanceMeters(), locationListenerTransport, Looper.getMainLooper());
            locationListenerTransport.register();
        }
    }

    public static void requestLocationUpdates(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, LocationListenerCompat locationListenerCompat, Looper looper) {
        LocationManager locationManager2 = locationManager;
        String str2 = str;
        LocationListenerCompat locationListenerCompat2 = locationListenerCompat;
        Looper looper2 = looper;
        if (Build.VERSION.SDK_INT >= 31) {
            Api31Impl.requestLocationUpdates(locationManager, str, locationRequestCompat.toLocationRequest(), ExecutorCompat.create(new Handler(looper2)), locationListenerCompat);
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                if (sRequestLocationUpdatesLooperMethod == null) {
                    Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", new Class[]{LocationRequest.class, LocationListener.class, Looper.class});
                    sRequestLocationUpdatesLooperMethod = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                LocationRequestCompat locationRequestCompat2 = locationRequestCompat;
                try {
                    LocationRequest locationRequest = locationRequestCompat.toLocationRequest(str);
                    if (locationRequest != null) {
                        sRequestLocationUpdatesLooperMethod.invoke(locationManager, new Object[]{locationRequest, locationListenerCompat2, looper2});
                        return;
                    }
                } catch (IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
                }
            } catch (IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused2) {
            }
            locationManager.requestLocationUpdates(str, locationRequestCompat.getIntervalMillis(), locationRequestCompat.getMinUpdateDistanceMeters(), locationListenerCompat, looper);
        }
        LocationRequestCompat locationRequestCompat3 = locationRequestCompat;
        locationManager.requestLocationUpdates(str, locationRequestCompat.getIntervalMillis(), locationRequestCompat.getMinUpdateDistanceMeters(), locationListenerCompat, looper);
    }

    public static void removeUpdates(LocationManager locationManager, LocationListenerCompat locationListenerCompat) {
        WeakHashMap<LocationListener, List<WeakReference<LocationListenerTransport>>> weakHashMap = sLocationListeners;
        synchronized (weakHashMap) {
            List<WeakReference> remove = weakHashMap.remove(locationListenerCompat);
            if (remove != null) {
                for (WeakReference weakReference : remove) {
                    LocationListenerTransport locationListenerTransport = (LocationListenerTransport) weakReference.get();
                    if (locationListenerTransport != null && locationListenerTransport.unregister()) {
                        locationManager.removeUpdates(locationListenerTransport);
                    }
                }
            }
        }
        locationManager.removeUpdates(locationListenerCompat);
    }

    public static String getGnssHardwareModelName(LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getGnssHardwareModelName(locationManager);
        }
        return null;
    }

    public static int getGnssYearOfHardware(LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getGnssYearOfHardware(locationManager);
        }
        return 0;
    }

    private static class GnssLazyLoader {
        static final SimpleArrayMap<Object, Object> sGnssStatusListeners = new SimpleArrayMap<>();

        private GnssLazyLoader() {
        }
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager, GnssStatusCompat.Callback callback, Handler handler) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, ExecutorCompat.create(handler), callback);
        }
        return registerGnssStatusCallback(locationManager, (Executor) new InlineHandlerExecutor(handler), callback);
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager, Executor executor, GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, (Handler) null, executor, callback);
        }
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            myLooper = Looper.getMainLooper();
        }
        return registerGnssStatusCallback(locationManager, new Handler(myLooper), executor, callback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00cf, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00da, code lost:
        return false;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x00e4 */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0135 A[SYNTHETIC, Splitter:B:103:0x0135] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0116 A[Catch:{ ExecutionException -> 0x010c, TimeoutException -> 0x00f3, all -> 0x00f0, all -> 0x0132 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x012b A[Catch:{ ExecutionException -> 0x010c, TimeoutException -> 0x00f3, all -> 0x00f0, all -> 0x0132 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean registerGnssStatusCallback(android.location.LocationManager r9, android.os.Handler r10, java.util.concurrent.Executor r11, androidx.core.location.GnssStatusCompat.Callback r12) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 1
            r2 = 0
            r3 = 30
            if (r0 < r3) goto L_0x002c
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r0 = androidx.core.location.LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners
            monitor-enter(r0)
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r10 = androidx.core.location.LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners     // Catch:{ all -> 0x0029 }
            java.lang.Object r10 = r10.get(r12)     // Catch:{ all -> 0x0029 }
            androidx.core.location.LocationManagerCompat$GnssStatusTransport r10 = (androidx.core.location.LocationManagerCompat.GnssStatusTransport) r10     // Catch:{ all -> 0x0029 }
            if (r10 != 0) goto L_0x001a
            androidx.core.location.LocationManagerCompat$GnssStatusTransport r10 = new androidx.core.location.LocationManagerCompat$GnssStatusTransport     // Catch:{ all -> 0x0029 }
            r10.<init>(r12)     // Catch:{ all -> 0x0029 }
        L_0x001a:
            boolean r9 = r9.registerGnssStatusCallback(r11, r10)     // Catch:{ all -> 0x0029 }
            if (r9 == 0) goto L_0x0027
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r9 = androidx.core.location.LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners     // Catch:{ all -> 0x0029 }
            r9.put(r12, r10)     // Catch:{ all -> 0x0029 }
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return r1
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return r2
        L_0x0029:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            throw r9
        L_0x002c:
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 24
            if (r0 < r3) goto L_0x0065
            if (r10 == 0) goto L_0x0036
            r0 = 1
            goto L_0x0037
        L_0x0036:
            r0 = 0
        L_0x0037:
            androidx.core.util.Preconditions.checkArgument(r0)
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r0 = androidx.core.location.LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners
            monitor-enter(r0)
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r3 = androidx.core.location.LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners     // Catch:{ all -> 0x0062 }
            java.lang.Object r3 = r3.get(r12)     // Catch:{ all -> 0x0062 }
            androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport r3 = (androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport) r3     // Catch:{ all -> 0x0062 }
            if (r3 != 0) goto L_0x004d
            androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport r3 = new androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport     // Catch:{ all -> 0x0062 }
            r3.<init>(r12)     // Catch:{ all -> 0x0062 }
            goto L_0x0050
        L_0x004d:
            r3.unregister()     // Catch:{ all -> 0x0062 }
        L_0x0050:
            r3.register(r11)     // Catch:{ all -> 0x0062 }
            boolean r9 = r9.registerGnssStatusCallback(r3, r10)     // Catch:{ all -> 0x0062 }
            if (r9 == 0) goto L_0x0060
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r9 = androidx.core.location.LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners     // Catch:{ all -> 0x0062 }
            r9.put(r12, r3)     // Catch:{ all -> 0x0062 }
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            return r1
        L_0x0060:
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            return r2
        L_0x0062:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            throw r9
        L_0x0065:
            if (r10 == 0) goto L_0x0069
            r0 = 1
            goto L_0x006a
        L_0x0069:
            r0 = 0
        L_0x006a:
            androidx.core.util.Preconditions.checkArgument(r0)
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r0 = androidx.core.location.LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners
            monitor-enter(r0)
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r3 = androidx.core.location.LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners     // Catch:{ all -> 0x0154 }
            java.lang.Object r3 = r3.get(r12)     // Catch:{ all -> 0x0154 }
            androidx.core.location.LocationManagerCompat$GpsStatusTransport r3 = (androidx.core.location.LocationManagerCompat.GpsStatusTransport) r3     // Catch:{ all -> 0x0154 }
            if (r3 != 0) goto L_0x0080
            androidx.core.location.LocationManagerCompat$GpsStatusTransport r3 = new androidx.core.location.LocationManagerCompat$GpsStatusTransport     // Catch:{ all -> 0x0154 }
            r3.<init>(r9, r12)     // Catch:{ all -> 0x0154 }
            goto L_0x0083
        L_0x0080:
            r3.unregister()     // Catch:{ all -> 0x0154 }
        L_0x0083:
            r3.register(r11)     // Catch:{ all -> 0x0154 }
            java.util.concurrent.FutureTask r11 = new java.util.concurrent.FutureTask     // Catch:{ all -> 0x0154 }
            androidx.core.location.LocationManagerCompat$$ExternalSyntheticLambda1 r4 = new androidx.core.location.LocationManagerCompat$$ExternalSyntheticLambda1     // Catch:{ all -> 0x0154 }
            r4.<init>(r9, r3)     // Catch:{ all -> 0x0154 }
            r11.<init>(r4)     // Catch:{ all -> 0x0154 }
            android.os.Looper r9 = android.os.Looper.myLooper()     // Catch:{ all -> 0x0154 }
            android.os.Looper r4 = r10.getLooper()     // Catch:{ all -> 0x0154 }
            if (r9 != r4) goto L_0x009e
            r11.run()     // Catch:{ all -> 0x0154 }
            goto L_0x00a4
        L_0x009e:
            boolean r9 = r10.post(r11)     // Catch:{ all -> 0x0154 }
            if (r9 == 0) goto L_0x013d
        L_0x00a4:
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ ExecutionException -> 0x010c, TimeoutException -> 0x00f3, all -> 0x00f0 }
            r4 = 5
            long r4 = r9.toNanos(r4)     // Catch:{ ExecutionException -> 0x010c, TimeoutException -> 0x00f3, all -> 0x00f0 }
            long r6 = java.lang.System.nanoTime()     // Catch:{ ExecutionException -> 0x010c, TimeoutException -> 0x00f3, all -> 0x00f0 }
            long r6 = r6 + r4
            r9 = 0
        L_0x00b2:
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x00e4, ExecutionException -> 0x00e1, TimeoutException -> 0x00de, all -> 0x00db }
            java.lang.Object r4 = r11.get(r4, r8)     // Catch:{ InterruptedException -> 0x00e4, ExecutionException -> 0x00e1, TimeoutException -> 0x00de, all -> 0x00db }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ InterruptedException -> 0x00e4, ExecutionException -> 0x00e1, TimeoutException -> 0x00de, all -> 0x00db }
            boolean r4 = r4.booleanValue()     // Catch:{ InterruptedException -> 0x00e4, ExecutionException -> 0x00e1, TimeoutException -> 0x00de, all -> 0x00db }
            if (r4 == 0) goto L_0x00d0
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r4 = androidx.core.location.LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners     // Catch:{ InterruptedException -> 0x00e4, ExecutionException -> 0x00e1, TimeoutException -> 0x00de, all -> 0x00db }
            r4.put(r12, r3)     // Catch:{ InterruptedException -> 0x00e4, ExecutionException -> 0x00e1, TimeoutException -> 0x00de, all -> 0x00db }
            if (r9 == 0) goto L_0x00ce
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0154 }
            r9.interrupt()     // Catch:{ all -> 0x0154 }
        L_0x00ce:
            monitor-exit(r0)     // Catch:{ all -> 0x0154 }
            return r1
        L_0x00d0:
            if (r9 == 0) goto L_0x00d9
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0154 }
            r9.interrupt()     // Catch:{ all -> 0x0154 }
        L_0x00d9:
            monitor-exit(r0)     // Catch:{ all -> 0x0154 }
            return r2
        L_0x00db:
            r10 = move-exception
            r1 = r9
            goto L_0x0133
        L_0x00de:
            r11 = move-exception
            r1 = r9
            goto L_0x00f5
        L_0x00e1:
            r10 = move-exception
            r1 = r9
            goto L_0x010e
        L_0x00e4:
            long r4 = java.lang.System.nanoTime()     // Catch:{ ExecutionException -> 0x00ee, TimeoutException -> 0x00ec }
            long r4 = r6 - r4
            r9 = 1
            goto L_0x00b2
        L_0x00ec:
            r11 = move-exception
            goto L_0x00f5
        L_0x00ee:
            r10 = move-exception
            goto L_0x010e
        L_0x00f0:
            r10 = move-exception
            r1 = 0
            goto L_0x0133
        L_0x00f3:
            r11 = move-exception
            r1 = 0
        L_0x00f5:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0132 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0132 }
            r12.<init>()     // Catch:{ all -> 0x0132 }
            r12.append(r10)     // Catch:{ all -> 0x0132 }
            java.lang.String r10 = " appears to be blocked, please run registerGnssStatusCallback() directly on a Looper thread or ensure the main Looper is not blocked by this thread"
            r12.append(r10)     // Catch:{ all -> 0x0132 }
            java.lang.String r10 = r12.toString()     // Catch:{ all -> 0x0132 }
            r9.<init>(r10, r11)     // Catch:{ all -> 0x0132 }
            throw r9     // Catch:{ all -> 0x0132 }
        L_0x010c:
            r10 = move-exception
            r1 = 0
        L_0x010e:
            java.lang.Throwable r9 = r10.getCause()     // Catch:{ all -> 0x0132 }
            boolean r9 = r9 instanceof java.lang.RuntimeException     // Catch:{ all -> 0x0132 }
            if (r9 != 0) goto L_0x012b
            java.lang.Throwable r9 = r10.getCause()     // Catch:{ all -> 0x0132 }
            boolean r9 = r9 instanceof java.lang.Error     // Catch:{ all -> 0x0132 }
            if (r9 == 0) goto L_0x0125
            java.lang.Throwable r9 = r10.getCause()     // Catch:{ all -> 0x0132 }
            java.lang.Error r9 = (java.lang.Error) r9     // Catch:{ all -> 0x0132 }
            throw r9     // Catch:{ all -> 0x0132 }
        L_0x0125:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0132 }
            r9.<init>(r10)     // Catch:{ all -> 0x0132 }
            throw r9     // Catch:{ all -> 0x0132 }
        L_0x012b:
            java.lang.Throwable r9 = r10.getCause()     // Catch:{ all -> 0x0132 }
            java.lang.RuntimeException r9 = (java.lang.RuntimeException) r9     // Catch:{ all -> 0x0132 }
            throw r9     // Catch:{ all -> 0x0132 }
        L_0x0132:
            r10 = move-exception
        L_0x0133:
            if (r1 == 0) goto L_0x013c
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0154 }
            r9.interrupt()     // Catch:{ all -> 0x0154 }
        L_0x013c:
            throw r10     // Catch:{ all -> 0x0154 }
        L_0x013d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0154 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0154 }
            r11.<init>()     // Catch:{ all -> 0x0154 }
            r11.append(r10)     // Catch:{ all -> 0x0154 }
            java.lang.String r10 = " is shutting down"
            r11.append(r10)     // Catch:{ all -> 0x0154 }
            java.lang.String r10 = r11.toString()     // Catch:{ all -> 0x0154 }
            r9.<init>(r10)     // Catch:{ all -> 0x0154 }
            throw r9     // Catch:{ all -> 0x0154 }
        L_0x0154:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0154 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.location.LocationManagerCompat.registerGnssStatusCallback(android.location.LocationManager, android.os.Handler, java.util.concurrent.Executor, androidx.core.location.GnssStatusCompat$Callback):boolean");
    }

    public static void unregisterGnssStatusCallback(LocationManager locationManager, GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            synchronized (GnssLazyLoader.sGnssStatusListeners) {
                GnssStatusTransport gnssStatusTransport = (GnssStatusTransport) GnssLazyLoader.sGnssStatusListeners.remove(callback);
                if (gnssStatusTransport != null) {
                    locationManager.unregisterGnssStatusCallback(gnssStatusTransport);
                }
            }
        } else if (Build.VERSION.SDK_INT >= 24) {
            synchronized (GnssLazyLoader.sGnssStatusListeners) {
                PreRGnssStatusTransport preRGnssStatusTransport = (PreRGnssStatusTransport) GnssLazyLoader.sGnssStatusListeners.remove(callback);
                if (preRGnssStatusTransport != null) {
                    preRGnssStatusTransport.unregister();
                    locationManager.unregisterGnssStatusCallback(preRGnssStatusTransport);
                }
            }
        } else {
            synchronized (GnssLazyLoader.sGnssStatusListeners) {
                GpsStatusTransport gpsStatusTransport = (GpsStatusTransport) GnssLazyLoader.sGnssStatusListeners.remove(callback);
                if (gpsStatusTransport != null) {
                    gpsStatusTransport.unregister();
                    locationManager.removeGpsStatusListener(gpsStatusTransport);
                }
            }
        }
    }

    private LocationManagerCompat() {
    }

    private static class LocationListenerTransport implements LocationListener {
        final Executor mExecutor;
        volatile LocationListenerCompat mListener;

        LocationListenerTransport(LocationListenerCompat locationListenerCompat, Executor executor) {
            this.mListener = (LocationListenerCompat) ObjectsCompat.requireNonNull(locationListenerCompat, "invalid null listener");
            this.mExecutor = executor;
        }

        public void register() {
            List list = LocationManagerCompat.sLocationListeners.get(this.mListener);
            if (list == null) {
                list = new ArrayList(1);
                LocationManagerCompat.sLocationListeners.put(this.mListener, list);
            } else if (Build.VERSION.SDK_INT >= 24) {
                list.removeIf(C0203xa0af9a6b.INSTANCE);
            } else {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((WeakReference) it.next()).get() == null) {
                        it.remove();
                    }
                }
            }
            list.add(new WeakReference(this));
        }

        static /* synthetic */ boolean lambda$register$0(WeakReference weakReference) {
            return weakReference.get() == null;
        }

        public boolean unregister() {
            LocationListenerCompat locationListenerCompat = this.mListener;
            if (locationListenerCompat == null) {
                return false;
            }
            this.mListener = null;
            List list = LocationManagerCompat.sLocationListeners.get(locationListenerCompat);
            if (list == null) {
                return true;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                list.removeIf(C0204xa0af9a6c.INSTANCE);
            } else {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((WeakReference) it.next()).get() == null) {
                        it.remove();
                    }
                }
            }
            if (!list.isEmpty()) {
                return true;
            }
            LocationManagerCompat.sLocationListeners.remove(locationListenerCompat);
            return true;
        }

        static /* synthetic */ boolean lambda$unregister$1(WeakReference weakReference) {
            return weakReference.get() == null;
        }

        public void onLocationChanged(Location location) {
            LocationListenerCompat locationListenerCompat = this.mListener;
            if (locationListenerCompat != null) {
                this.mExecutor.execute(new C0198xa0af9a66(this, locationListenerCompat, location));
            }
        }

        /* renamed from: lambda$onLocationChanged$2$LocationManagerCompat$LocationListenerTransport */
        public /* synthetic */ void mo4908x9fcf8003(LocationListenerCompat locationListenerCompat, Location location) {
            if (this.mListener == locationListenerCompat) {
                locationListenerCompat.onLocationChanged(location);
            }
        }

        public void onLocationChanged(List<Location> list) {
            LocationListenerCompat locationListenerCompat = this.mListener;
            if (locationListenerCompat != null) {
                this.mExecutor.execute(new C0202xa0af9a6a(this, locationListenerCompat, list));
            }
        }

        /* renamed from: lambda$onLocationChanged$3$LocationManagerCompat$LocationListenerTransport */
        public /* synthetic */ void mo4909x9f591a04(LocationListenerCompat locationListenerCompat, List list) {
            if (this.mListener == locationListenerCompat) {
                locationListenerCompat.onLocationChanged(list);
            }
        }

        public void onFlushComplete(int i) {
            LocationListenerCompat locationListenerCompat = this.mListener;
            if (locationListenerCompat != null) {
                this.mExecutor.execute(new C0197xa0af9a65(this, locationListenerCompat, i));
            }
        }

        /* renamed from: lambda$onFlushComplete$4$LocationManagerCompat$LocationListenerTransport */
        public /* synthetic */ void mo4907x48add8a3(LocationListenerCompat locationListenerCompat, int i) {
            if (this.mListener == locationListenerCompat) {
                locationListenerCompat.onFlushComplete(i);
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            LocationListenerCompat locationListenerCompat = this.mListener;
            if (locationListenerCompat != null) {
                this.mExecutor.execute(new C0201xa0af9a69(this, locationListenerCompat, str, i, bundle));
            }
        }

        /* renamed from: lambda$onStatusChanged$5$LocationManagerCompat$LocationListenerTransport */
        public /* synthetic */ void mo4912x76f5b169(LocationListenerCompat locationListenerCompat, String str, int i, Bundle bundle) {
            if (this.mListener == locationListenerCompat) {
                locationListenerCompat.onStatusChanged(str, i, bundle);
            }
        }

        public void onProviderEnabled(String str) {
            LocationListenerCompat locationListenerCompat = this.mListener;
            if (locationListenerCompat != null) {
                this.mExecutor.execute(new C0200xa0af9a68(this, locationListenerCompat, str));
            }
        }

        /* renamed from: lambda$onProviderEnabled$6$LocationManagerCompat$LocationListenerTransport */
        public /* synthetic */ void mo4911x2ab33898(LocationListenerCompat locationListenerCompat, String str) {
            if (this.mListener == locationListenerCompat) {
                locationListenerCompat.onProviderEnabled(str);
            }
        }

        public void onProviderDisabled(String str) {
            LocationListenerCompat locationListenerCompat = this.mListener;
            if (locationListenerCompat != null) {
                this.mExecutor.execute(new C0199xa0af9a67(this, locationListenerCompat, str));
            }
        }

        /* renamed from: lambda$onProviderDisabled$7$LocationManagerCompat$LocationListenerTransport */
        public /* synthetic */ void mo4910xe3a76a4e(LocationListenerCompat locationListenerCompat, String str) {
            if (this.mListener == locationListenerCompat) {
                locationListenerCompat.onProviderDisabled(str);
            }
        }
    }

    private static class GnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;

        GnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        public void onStarted() {
            this.mCallback.onStarted();
        }

        public void onStopped() {
            this.mCallback.onStopped();
        }

        public void onFirstFix(int i) {
            this.mCallback.onFirstFix(i);
        }

        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(gnssStatus));
        }
    }

    private static class PreRGnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;
        volatile Executor mExecutor;

        PreRGnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            boolean z = true;
            Preconditions.checkArgument(executor != null, "invalid null executor");
            if (this.mExecutor != null) {
                z = false;
            }
            Preconditions.checkState(z);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        public void onStarted() {
            Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new C0205xcc169346(this, executor));
            }
        }

        public /* synthetic */ void lambda$onStarted$0$LocationManagerCompat$PreRGnssStatusTransport(Executor executor) {
            if (this.mExecutor == executor) {
                this.mCallback.onStarted();
            }
        }

        public void onStopped() {
            Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new C0206xcc169347(this, executor));
            }
        }

        public /* synthetic */ void lambda$onStopped$1$LocationManagerCompat$PreRGnssStatusTransport(Executor executor) {
            if (this.mExecutor == executor) {
                this.mCallback.onStopped();
            }
        }

        public void onFirstFix(int i) {
            Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new C0207xcc169348(this, executor, i));
            }
        }

        /* renamed from: lambda$onFirstFix$2$LocationManagerCompat$PreRGnssStatusTransport */
        public /* synthetic */ void mo4921xb268b4e2(Executor executor, int i) {
            if (this.mExecutor == executor) {
                this.mCallback.onFirstFix(i);
            }
        }

        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new C0208xcc169349(this, executor, gnssStatus));
            }
        }

        /* renamed from: lambda$onSatelliteStatusChanged$3$LocationManagerCompat$PreRGnssStatusTransport */
        public /* synthetic */ void mo4922xc6dd7c5(Executor executor, GnssStatus gnssStatus) {
            if (this.mExecutor == executor) {
                this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(gnssStatus));
            }
        }
    }

    private static class GpsStatusTransport implements GpsStatus.Listener {
        final GnssStatusCompat.Callback mCallback;
        volatile Executor mExecutor;
        private final LocationManager mLocationManager;

        GpsStatusTransport(LocationManager locationManager, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mLocationManager = locationManager;
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            Preconditions.checkState(this.mExecutor == null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        public void onGpsStatusChanged(int i) {
            GpsStatus gpsStatus;
            Executor executor = this.mExecutor;
            if (executor != null) {
                if (i == 1) {
                    executor.execute(new C0193x7b1274a6(this, executor));
                } else if (i == 2) {
                    executor.execute(new C0194x7b1274a7(this, executor));
                } else if (i == 3) {
                    GpsStatus gpsStatus2 = this.mLocationManager.getGpsStatus((GpsStatus) null);
                    if (gpsStatus2 != null) {
                        executor.execute(new C0195x7b1274a8(this, executor, gpsStatus2.getTimeToFirstFix()));
                    }
                } else if (i == 4 && (gpsStatus = this.mLocationManager.getGpsStatus((GpsStatus) null)) != null) {
                    executor.execute(new C0196x7b1274a9(this, executor, GnssStatusCompat.wrap(gpsStatus)));
                }
            }
        }

        /* renamed from: lambda$onGpsStatusChanged$0$LocationManagerCompat$GpsStatusTransport */
        public /* synthetic */ void mo4899xdff4455b(Executor executor) {
            if (this.mExecutor == executor) {
                this.mCallback.onStarted();
            }
        }

        /* renamed from: lambda$onGpsStatusChanged$1$LocationManagerCompat$GpsStatusTransport */
        public /* synthetic */ void mo4900xe7597a7a(Executor executor) {
            if (this.mExecutor == executor) {
                this.mCallback.onStopped();
            }
        }

        /* renamed from: lambda$onGpsStatusChanged$2$LocationManagerCompat$GpsStatusTransport */
        public /* synthetic */ void mo4901xeebeaf99(Executor executor, int i) {
            if (this.mExecutor == executor) {
                this.mCallback.onFirstFix(i);
            }
        }

        /* renamed from: lambda$onGpsStatusChanged$3$LocationManagerCompat$GpsStatusTransport */
        public /* synthetic */ void mo4902xf623e4b8(Executor executor, GnssStatusCompat gnssStatusCompat) {
            if (this.mExecutor == executor) {
                this.mCallback.onSatelliteStatusChanged(gnssStatusCompat);
            }
        }
    }

    private static class Api31Impl {
        private Api31Impl() {
        }

        static boolean hasProvider(LocationManager locationManager, String str) {
            return locationManager.hasProvider(str);
        }

        static void requestLocationUpdates(LocationManager locationManager, String str, LocationRequest locationRequest, Executor executor, LocationListener locationListener) {
            locationManager.requestLocationUpdates(str, locationRequest, executor, locationListener);
        }
    }

    private static class Api30Impl {
        private Api30Impl() {
        }

        static void getCurrentLocation(LocationManager locationManager, String str, CancellationSignal cancellationSignal, Executor executor, Consumer<Location> consumer) {
            android.os.CancellationSignal cancellationSignal2 = cancellationSignal != null ? (android.os.CancellationSignal) cancellationSignal.getCancellationSignalObject() : null;
            Objects.requireNonNull(consumer);
            locationManager.getCurrentLocation(str, cancellationSignal2, executor, new LocationManagerCompat$Api30Impl$$ExternalSyntheticLambda0(consumer));
        }
    }

    private static class Api28Impl {
        private Api28Impl() {
        }

        static boolean isLocationEnabled(LocationManager locationManager) {
            return locationManager.isLocationEnabled();
        }

        static String getGnssHardwareModelName(LocationManager locationManager) {
            return locationManager.getGnssHardwareModelName();
        }

        static int getGnssYearOfHardware(LocationManager locationManager) {
            return locationManager.getGnssYearOfHardware();
        }
    }

    private static final class CancellableLocationListener implements LocationListener {
        private Consumer<Location> mConsumer;
        private final Executor mExecutor;
        private final LocationManager mLocationManager;
        private final Handler mTimeoutHandler = new Handler(Looper.getMainLooper());
        Runnable mTimeoutRunnable;
        private boolean mTriggered;

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        CancellableLocationListener(LocationManager locationManager, Executor executor, Consumer<Location> consumer) {
            this.mLocationManager = locationManager;
            this.mExecutor = executor;
            this.mConsumer = consumer;
        }

        public void cancel() {
            synchronized (this) {
                if (!this.mTriggered) {
                    this.mTriggered = true;
                    cleanup();
                }
            }
        }

        public void startTimeout(long j) {
            synchronized (this) {
                if (!this.mTriggered) {
                    C01911 r0 = new Runnable() {
                        public void run() {
                            CancellableLocationListener.this.mTimeoutRunnable = null;
                            CancellableLocationListener.this.onLocationChanged((Location) null);
                        }
                    };
                    this.mTimeoutRunnable = r0;
                    this.mTimeoutHandler.postDelayed(r0, j);
                }
            }
        }

        public void onProviderDisabled(String str) {
            onLocationChanged((Location) null);
        }

        public void onLocationChanged(Location location) {
            synchronized (this) {
                if (!this.mTriggered) {
                    this.mTriggered = true;
                    this.mExecutor.execute(new C0192x27d5f43a(this.mConsumer, location));
                    cleanup();
                }
            }
        }

        private void cleanup() {
            this.mConsumer = null;
            this.mLocationManager.removeUpdates(this);
            Runnable runnable = this.mTimeoutRunnable;
            if (runnable != null) {
                this.mTimeoutHandler.removeCallbacks(runnable);
                this.mTimeoutRunnable = null;
            }
        }
    }

    private static final class InlineHandlerExecutor implements Executor {
        private final Handler mHandler;

        InlineHandlerExecutor(Handler handler) {
            this.mHandler = (Handler) Preconditions.checkNotNull(handler);
        }

        public void execute(Runnable runnable) {
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                runnable.run();
            } else if (!this.mHandler.post((Runnable) Preconditions.checkNotNull(runnable))) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }
}
