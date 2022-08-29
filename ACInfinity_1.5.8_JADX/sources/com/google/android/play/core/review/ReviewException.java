package com.google.android.play.core.review;

import com.google.android.play.core.review.model.zza;
import com.google.android.play.core.tasks.zzj;
import java.util.Locale;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class ReviewException extends zzj {
    private final int zza;

    public ReviewException(int i) {
        super(String.format(Locale.getDefault(), "Review Error(%d): %s", new Object[]{Integer.valueOf(i), zza.zza(i)}));
        this.zza = i;
    }

    public int getErrorCode() {
        return this.zza;
    }
}
