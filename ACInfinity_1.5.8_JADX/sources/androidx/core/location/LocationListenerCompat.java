package androidx.core.location;

import android.location.LocationListener;
import android.os.Bundle;

public interface LocationListenerCompat extends LocationListener {

    /* renamed from: androidx.core.location.LocationListenerCompat$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onProviderDisabled(LocationListenerCompat locationListenerCompat, String str) {
        }

        public static void $default$onProviderEnabled(LocationListenerCompat locationListenerCompat, String str) {
        }

        public static void $default$onStatusChanged(LocationListenerCompat locationListenerCompat, String str, int i, Bundle bundle) {
        }
    }

    void onProviderDisabled(String str);

    void onProviderEnabled(String str);

    void onStatusChanged(String str, int i, Bundle bundle);
}
