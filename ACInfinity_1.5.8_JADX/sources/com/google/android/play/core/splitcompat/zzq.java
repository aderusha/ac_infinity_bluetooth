package com.google.android.play.core.splitcompat;

import android.util.Log;
import java.util.Set;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzq implements Runnable {
    final /* synthetic */ Set zza;
    final /* synthetic */ SplitCompat zzb;

    zzq(SplitCompat splitCompat, Set set) {
        this.zzb = splitCompat;
        this.zza = set;
    }

    public final void run() {
        try {
            this.zzb.zzg(this.zza);
        } catch (Exception e) {
            Log.e("SplitCompat", "Failed to remove from splitcompat storage split that is already installed", e);
        }
    }
}
