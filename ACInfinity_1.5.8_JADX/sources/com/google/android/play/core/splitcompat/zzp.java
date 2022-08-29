package com.google.android.play.core.splitcompat;

import android.util.Log;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzp implements Runnable {
    final /* synthetic */ SplitCompat zza;

    zzp(SplitCompat splitCompat) {
        this.zza = splitCompat;
    }

    public final void run() {
        try {
            this.zza.zzc.zzk();
        } catch (Exception e) {
            Log.e("SplitCompat", "Failed to cleanup splitcompat storage", e);
        }
    }
}
