package androidx.core.location;

import androidx.core.location.LocationManagerCompat;
import java.util.concurrent.Executor;

/* renamed from: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda2 */
public final /* synthetic */ class C0207xcc169348 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.PreRGnssStatusTransport f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ C0207xcc169348(LocationManagerCompat.PreRGnssStatusTransport preRGnssStatusTransport, Executor executor, int i) {
        this.f$0 = preRGnssStatusTransport;
        this.f$1 = executor;
        this.f$2 = i;
    }

    public final void run() {
        this.f$0.mo4921xb268b4e2(this.f$1, this.f$2);
    }
}
