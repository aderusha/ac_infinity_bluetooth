package androidx.core.location;

import androidx.core.location.LocationManagerCompat;
import java.util.concurrent.Executor;

/* renamed from: androidx.core.location.LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda3 */
public final /* synthetic */ class C0196x7b1274a9 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.GpsStatusTransport f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ GnssStatusCompat f$2;

    public /* synthetic */ C0196x7b1274a9(LocationManagerCompat.GpsStatusTransport gpsStatusTransport, Executor executor, GnssStatusCompat gnssStatusCompat) {
        this.f$0 = gpsStatusTransport;
        this.f$1 = executor;
        this.f$2 = gnssStatusCompat;
    }

    public final void run() {
        this.f$0.mo4902xf623e4b8(this.f$1, this.f$2);
    }
}
