package androidx.core.location;

import androidx.core.location.LocationManagerCompat;
import java.util.concurrent.Executor;

/* renamed from: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda1 */
public final /* synthetic */ class C0206xcc169347 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.PreRGnssStatusTransport f$0;
    public final /* synthetic */ Executor f$1;

    public /* synthetic */ C0206xcc169347(LocationManagerCompat.PreRGnssStatusTransport preRGnssStatusTransport, Executor executor) {
        this.f$0 = preRGnssStatusTransport;
        this.f$1 = executor;
    }

    public final void run() {
        this.f$0.lambda$onStopped$1$LocationManagerCompat$PreRGnssStatusTransport(this.f$1);
    }
}
