package com.google.android.play.core.review;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzd implements ReviewManager {
    private final zzi zza;
    private final Handler zzb = new Handler(Looper.getMainLooper());

    zzd(zzi zzi) {
        this.zza = zzi;
    }

    public final Task<Void> launchReviewFlow(Activity activity, ReviewInfo reviewInfo) {
        if (reviewInfo.zzb()) {
            return Tasks.zzb((Object) null);
        }
        Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", reviewInfo.zza());
        intent.putExtra("window_flags", activity.getWindow().getDecorView().getWindowSystemUiVisibility());
        zzi zzi = new zzi();
        intent.putExtra("result_receiver", new zzc(this, this.zzb, zzi));
        activity.startActivity(intent);
        return zzi.zza();
    }

    public final Task<ReviewInfo> requestReviewFlow() {
        return this.zza.zzb();
    }
}
