package com.google.android.play.core.splitinstall;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzk {
    private final Map zza;

    /* synthetic */ zzk(Map map, zzj zzj) {
        this.zza = map;
    }

    public final Map zza(Collection collection) {
        Set set;
        HashMap hashMap = new HashMap();
        for (String str : this.zza.keySet()) {
            if (!this.zza.containsKey(str)) {
                set = Collections.emptySet();
            } else {
                HashSet hashSet = new HashSet();
                for (Map.Entry entry : ((Map) this.zza.get(str)).entrySet()) {
                    if (collection.contains(entry.getKey())) {
                        hashSet.add((String) entry.getValue());
                    }
                }
                set = Collections.unmodifiableSet(hashSet);
            }
            hashMap.put(str, set);
        }
        return hashMap;
    }
}
