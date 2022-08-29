package com.google.android.play.core.review;

import android.content.Context;
import com.google.android.play.core.internal.zzce;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class ReviewManagerFactory {
    private ReviewManagerFactory() {
    }

    public static ReviewManager create(Context context) {
        return new zzd(new zzi(zzce.zza(context)));
    }
}
