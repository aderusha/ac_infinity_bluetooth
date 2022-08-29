package com.google.android.play.core.splitinstall;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzi {
    private final Map zza = new HashMap();

    public final zzi zza(String str, String str2, String str3) {
        if (!this.zza.containsKey(str2)) {
            this.zza.put(str2, new HashMap());
        }
        ((Map) this.zza.get(str2)).put(str, str3);
        return this;
    }

    public final zzk zzb() {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : this.zza.entrySet()) {
            hashMap.put((String) entry.getKey(), Collections.unmodifiableMap(new HashMap((Map) entry.getValue())));
        }
        return new zzk(Collections.unmodifiableMap(hashMap), (zzj) null);
    }
}
