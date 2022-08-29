package androidx.core.content;

public final /* synthetic */ class PackageManagerCompat$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ UnusedAppRestrictionsBackportServiceConnection f$0;

    public /* synthetic */ PackageManagerCompat$$ExternalSyntheticLambda0(UnusedAppRestrictionsBackportServiceConnection unusedAppRestrictionsBackportServiceConnection) {
        this.f$0 = unusedAppRestrictionsBackportServiceConnection;
    }

    public final void run() {
        this.f$0.disconnectFromService();
    }
}
