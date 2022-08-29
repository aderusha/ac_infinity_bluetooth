package com.google.android.play.core.internal;

import android.content.Context;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzce {
    public static Context zza(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }
}
