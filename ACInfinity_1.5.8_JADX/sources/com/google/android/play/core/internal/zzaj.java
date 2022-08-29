package com.google.android.play.core.internal;

import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzaj implements OnCompleteListener {
    public final /* synthetic */ zzas zza;
    public final /* synthetic */ zzi zzb;

    public /* synthetic */ zzaj(zzas zzas, zzi zzi) {
        this.zza = zzas;
        this.zzb = zzi;
    }

    public final void onComplete(Task task) {
        this.zza.zzr(this.zzb, task);
    }
}
