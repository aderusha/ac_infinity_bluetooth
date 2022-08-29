package com.google.android.play.core.internal;

import android.util.Log;
import com.google.android.play.core.splitinstall.zzf;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzav implements Runnable {
    final /* synthetic */ List zza;
    final /* synthetic */ zzf zzb;
    final /* synthetic */ zzaw zzc;

    zzav(zzaw zzaw, List list, zzf zzf) {
        this.zzc = zzaw;
        this.zza = list;
        this.zzb = zzf;
    }

    public final void run() {
        try {
            if (this.zzc.zzc.zzb(this.zza)) {
                zzaw.zzc(this.zzc, this.zzb);
            } else {
                zzaw.zzb(this.zzc, this.zza, this.zzb);
            }
        } catch (Exception e) {
            Log.e("SplitCompat", "Error checking verified files.", e);
            this.zzb.zzb(-11);
        }
    }
}
