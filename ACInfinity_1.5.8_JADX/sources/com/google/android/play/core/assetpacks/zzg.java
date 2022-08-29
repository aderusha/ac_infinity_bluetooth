package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnSuccessListener;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzg implements OnSuccessListener {
    public final /* synthetic */ zzbh zza;

    public /* synthetic */ zzg(zzbh zzbh) {
        this.zza = zzbh;
    }

    public final void onSuccess(Object obj) {
        this.zza.zzC((List) obj);
    }
}
