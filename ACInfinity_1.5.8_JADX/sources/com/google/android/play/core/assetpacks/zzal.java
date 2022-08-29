package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzv;
import com.google.android.play.core.tasks.zzi;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
class zzal extends zzv {
    final zzi zza;
    final /* synthetic */ zzaw zzb;

    zzal(zzaw zzaw, zzi zzi) {
        this.zzb = zzaw;
        this.zza = zzi;
    }

    public final void zzb(int i, Bundle bundle) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onCancelDownload(%d)", Integer.valueOf(i));
    }

    public final void zzc(Bundle bundle) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onCancelDownloads()", new Object[0]);
    }

    public void zzd(Bundle bundle) {
        this.zzb.zzf.zzs(this.zza);
        int i = bundle.getInt("error_code");
        zzaw.zza.zzb("onError(%d)", Integer.valueOf(i));
        this.zza.zzd(new AssetPackException(i));
    }

    public void zze(Bundle bundle, Bundle bundle2) throws RemoteException {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onGetChunkFileDescriptor", new Object[0]);
    }

    public final void zzf(int i, Bundle bundle) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onGetSession(%d)", Integer.valueOf(i));
    }

    public void zzg(List list) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onGetSessionStates", new Object[0]);
    }

    public void zzh(Bundle bundle, Bundle bundle2) {
        this.zzb.zzg.zzs(this.zza);
        zzaw.zza.zzd("onKeepAlive(%b)", Boolean.valueOf(bundle.getBoolean("keep_alive")));
    }

    public final void zzi(Bundle bundle, Bundle bundle2) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onNotifyChunkTransferred(%s, %s, %d, session=%d)", bundle.getString("module_name"), bundle.getString("slice_id"), Integer.valueOf(bundle.getInt("chunk_number")), Integer.valueOf(bundle.getInt("session_id")));
    }

    public final void zzj(Bundle bundle, Bundle bundle2) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onNotifyModuleCompleted(%s, sessionId=%d)", bundle.getString("module_name"), Integer.valueOf(bundle.getInt("session_id")));
    }

    public final void zzk(Bundle bundle, Bundle bundle2) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onNotifySessionFailed(%d)", Integer.valueOf(bundle.getInt("session_id")));
    }

    public final void zzl(Bundle bundle, Bundle bundle2) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onRemoveModule()", new Object[0]);
    }

    public void zzm(Bundle bundle, Bundle bundle2) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onRequestDownloadInfo()", new Object[0]);
    }

    public void zzn(int i, Bundle bundle) {
        this.zzb.zzf.zzs(this.zza);
        zzaw.zza.zzd("onStartDownload(%d)", Integer.valueOf(i));
    }
}
