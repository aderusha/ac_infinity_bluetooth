package com.google.android.play.core.review;

import android.app.Activity;
import com.google.android.play.core.tasks.Task;

/* compiled from: com.google.android.play:core@@1.10.3 */
public interface ReviewManager {
    Task<Void> launchReviewFlow(Activity activity, ReviewInfo reviewInfo);

    Task<ReviewInfo> requestReviewFlow();
}
