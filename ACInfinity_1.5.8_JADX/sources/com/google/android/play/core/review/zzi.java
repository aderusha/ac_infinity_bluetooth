package com.google.android.play.core.review;

import android.content.Context;
import android.content.Intent;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzam;
import com.google.android.play.core.internal.zzas;
import com.google.android.play.core.internal.zzch;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzi {
    /* access modifiers changed from: private */
    public static final zzag zzb = new zzag("ReviewService");
    zzas zza;
    /* access modifiers changed from: private */
    public final String zzc;

    public zzi(Context context) {
        this.zzc = context.getPackageName();
        if (zzch.zzb(context)) {
            Context context2 = context;
            this.zza = new zzas(context2, zzb, "com.google.android.finsky.inappreviewservice.InAppReviewService", new Intent("com.google.android.finsky.BIND_IN_APP_REVIEW_SERVICE").setPackage("com.android.vending"), zze.zza, (zzam) null);
        }
    }

    public final Task zzb() {
        zzag zzag = zzb;
        zzag.zzd("requestInAppReview (%s)", this.zzc);
        if (this.zza == null) {
            zzag.zzb("Play Store app is either not installed or not the official version", new Object[0]);
            return Tasks.zza(new ReviewException(-1));
        }
        com.google.android.play.core.tasks.zzi zzi = new com.google.android.play.core.tasks.zzi();
        this.zza.zzq(new zzf(this, zzi, zzi), zzi);
        return zzi.zza();
    }
}
