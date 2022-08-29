package com.google.android.play.core.internal;

import android.util.Log;
import java.io.File;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbo implements zzbd {
    zzbo() {
    }

    public final boolean zza(Object obj, File file, File file2) {
        try {
            if (!((Boolean) zzbw.zzf(Class.forName("dalvik.system.DexFile"), "isDexOptNeeded", Boolean.class, String.class, file.getPath())).booleanValue()) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException unused) {
            Log.e("SplitCompat", "Unexpected missing dalvik.system.DexFile.");
            return false;
        }
    }
}
