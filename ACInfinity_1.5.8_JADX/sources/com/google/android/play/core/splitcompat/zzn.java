package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.util.Log;
import com.google.android.play.core.splitinstall.zzx;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzn implements Runnable {
    public final /* synthetic */ Context zza;

    public /* synthetic */ zzn(Context context) {
        this.zza = context;
    }

    public final void run() {
        Context context = this.zza;
        int i = SplitCompat.zza;
        try {
            zzx.zzc(context).zzg(true);
        } catch (SecurityException unused) {
            Log.e("SplitCompat", "Failed to set broadcast receiver to always on.");
        }
    }
}
