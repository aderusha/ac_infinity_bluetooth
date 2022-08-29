package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzy implements Runnable {
    final /* synthetic */ SplitInstallRequest zza;
    final /* synthetic */ zzaa zzb;

    zzy(zzaa zzaa, SplitInstallRequest splitInstallRequest) {
        this.zzb = zzaa;
        this.zza = splitInstallRequest;
    }

    public final void run() {
        zzx zzc = this.zzb.zzb;
        List<String> moduleNames = this.zza.getModuleNames();
        List zzd = zzaa.zze(this.zza.getLanguages());
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", 0);
        bundle.putInt(NotificationCompat.CATEGORY_STATUS, 5);
        bundle.putInt("error_code", 0);
        if (!moduleNames.isEmpty()) {
            bundle.putStringArrayList("module_names", new ArrayList(moduleNames));
        }
        if (!zzd.isEmpty()) {
            bundle.putStringArrayList("languages", new ArrayList(zzd));
        }
        bundle.putLong("total_bytes_to_download", 0);
        bundle.putLong("bytes_downloaded", 0);
        zzc.zzm(SplitInstallSessionState.zzd(bundle));
    }
}
