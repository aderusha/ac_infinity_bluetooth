package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnFailureListener;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzf implements OnFailureListener {
    public static final /* synthetic */ zzf zza = new zzf();

    private /* synthetic */ zzf() {
    }

    public final void onFailure(Exception exc) {
        zzl.zza.zze(String.format("Could not sync active asset packs. %s", new Object[]{exc}), new Object[0]);
    }
}
