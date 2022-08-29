package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FilenameFilter;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzdj implements FilenameFilter {
    public final /* synthetic */ String zza;

    public /* synthetic */ zzdj(String str) {
        this.zza = str;
    }

    public final boolean accept(File file, String str) {
        return str.startsWith(String.valueOf(this.zza).concat("-")) && str.endsWith(".apk");
    }
}
