package com.google.android.play.core.assetpacks;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzbg {
    public static boolean zza(int i) {
        return i == 1 || i == 7 || i == 2 || i == 3;
    }

    public static boolean zzb(int i) {
        return i == 2 || i == 7 || i == 3;
    }

    static boolean zzc(int i, int i2) {
        if (i == 5) {
            if (i2 != 5) {
                return true;
            }
            i = 5;
        }
        if (i == 6) {
            if (i2 != 6 && i2 != 5) {
                return true;
            }
            i = 6;
        }
        if (i == 4 && i2 != 4) {
            return true;
        }
        if (i == 3 && (i2 == 2 || i2 == 7 || i2 == 1 || i2 == 8)) {
            return true;
        }
        if (i == 2) {
            return i2 == 1 || i2 == 8;
        }
        return false;
    }

    public static boolean zzd(int i) {
        return i == 5 || i == 6 || i == 4;
    }
}
