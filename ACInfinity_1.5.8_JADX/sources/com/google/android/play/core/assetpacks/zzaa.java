package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnSuccessListener;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzaa implements OnSuccessListener {
    public final /* synthetic */ zzaw zza;

    public /* synthetic */ zzaa(zzaw zzaw) {
        this.zza = zzaw;
    }

    public final void onSuccess(Object obj) {
        AssetPackStates assetPackStates = (AssetPackStates) obj;
        this.zza.zzf();
    }
}
