package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FilenameFilter;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzeo implements FilenameFilter {
    public static final /* synthetic */ zzeo zza = new zzeo();

    private /* synthetic */ zzeo() {
    }

    public final boolean accept(File file, String str) {
        return zzep.zza.matcher(str).matches();
    }
}
