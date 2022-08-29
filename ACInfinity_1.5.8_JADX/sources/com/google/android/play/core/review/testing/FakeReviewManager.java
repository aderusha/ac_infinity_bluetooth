package com.google.android.play.core.review.testing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.google.android.play.core.review.ReviewException;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class FakeReviewManager implements ReviewManager {
    private final Context zza;
    private ReviewInfo zzb;

    public FakeReviewManager(Context context) {
        this.zza = context;
    }

    public Task<Void> launchReviewFlow(Activity activity, ReviewInfo reviewInfo) {
        if (reviewInfo != this.zzb) {
            return Tasks.zza(new ReviewException(-2));
        }
        return Tasks.zzb((Object) null);
    }

    public Task<ReviewInfo> requestReviewFlow() {
        ReviewInfo zzc = ReviewInfo.zzc(PendingIntent.getBroadcast(this.zza, 0, new Intent(), Build.VERSION.SDK_INT >= 23 ? 67108864 : 0), false);
        this.zzb = zzc;
        return Tasks.zzb(zzc);
    }
}
