package androidx.core.location;

import android.os.Bundle;
import androidx.core.location.LocationManagerCompat;

/* renamed from: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda4 */
public final /* synthetic */ class C0201xa0af9a69 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.LocationListenerTransport f$0;
    public final /* synthetic */ LocationListenerCompat f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Bundle f$4;

    public /* synthetic */ C0201xa0af9a69(LocationManagerCompat.LocationListenerTransport locationListenerTransport, LocationListenerCompat locationListenerCompat, String str, int i, Bundle bundle) {
        this.f$0 = locationListenerTransport;
        this.f$1 = locationListenerCompat;
        this.f$2 = str;
        this.f$3 = i;
        this.f$4 = bundle;
    }

    public final void run() {
        this.f$0.mo4912x76f5b169(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
