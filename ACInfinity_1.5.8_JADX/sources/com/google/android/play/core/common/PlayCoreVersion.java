package com.google.android.play.core.common;

import android.os.Bundle;
import com.google.android.play.core.internal.zzag;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class PlayCoreVersion {
    private static final Set zza = new HashSet(Arrays.asList(new String[]{"app_update", "review"}));
    private static final Set zzb = new HashSet(Arrays.asList(new String[]{"native", "unity"}));
    private static final Map zzc = new HashMap();
    private static final zzag zzd = new zzag("PlayCoreVersion");

    private PlayCoreVersion() {
    }

    public static synchronized void addVersion(String str, String str2, int i) {
        synchronized (PlayCoreVersion.class) {
            if (!zza.contains(str)) {
                zzd.zze("Illegal module name: %s", str);
            } else if (!zzb.contains(str2)) {
                zzd.zze("Illegal platform name: %s", str2);
            } else {
                zzb(str).put(str2, Integer.valueOf(i));
            }
        }
    }

    public static Bundle zza(String str) {
        Bundle bundle = new Bundle();
        Map zzb2 = zzb(str);
        bundle.putInt("playcore_version_code", ((Integer) zzb2.get("java")).intValue());
        if (zzb2.containsKey("native")) {
            bundle.putInt("playcore_native_version", ((Integer) zzb2.get("native")).intValue());
        }
        if (zzb2.containsKey("unity")) {
            bundle.putInt("playcore_unity_version", ((Integer) zzb2.get("unity")).intValue());
        }
        return bundle;
    }

    public static synchronized Map zzb(String str) {
        Map map;
        synchronized (PlayCoreVersion.class) {
            Map map2 = zzc;
            if (!map2.containsKey(str)) {
                HashMap hashMap = new HashMap();
                hashMap.put("java", 11003);
                map2.put(str, hashMap);
            }
            map = (Map) map2.get(str);
        }
        return map;
    }
}
