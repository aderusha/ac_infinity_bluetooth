package com.google.android.play.core.splitinstall;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzw implements Runnable {
    final /* synthetic */ SplitInstallSessionState zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ zzx zzd;

    zzw(zzx zzx, SplitInstallSessionState splitInstallSessionState, int i, int i2) {
        this.zzd = zzx;
        this.zza = splitInstallSessionState;
        this.zzb = i;
        this.zzc = i2;
    }

    public final void run() {
        zzx zzx = this.zzd;
        SplitInstallSessionState splitInstallSessionState = this.zza;
        zzx.zzm(new zza(splitInstallSessionState.sessionId(), this.zzb, this.zzc, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.zzb(), splitInstallSessionState.zza(), splitInstallSessionState.resolutionIntent(), splitInstallSessionState.zzc()));
    }
}
