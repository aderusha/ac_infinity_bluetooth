package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class AssetPackStates {
    public static AssetPackStates zza(Bundle bundle, zzco zzco, zzeb zzeb) {
        return zzd(bundle, zzco, zzeb, new ArrayList(), zzbf.zza);
    }

    public static AssetPackStates zzb(Bundle bundle, zzco zzco, zzeb zzeb, zzbe zzbe) {
        return zzd(bundle, zzco, zzeb, new ArrayList(), zzbe);
    }

    public static AssetPackStates zzc(Bundle bundle, zzco zzco, zzeb zzeb, List list) {
        return zzd(bundle, zzco, zzeb, list, zzbf.zza);
    }

    private static AssetPackStates zzd(Bundle bundle, zzco zzco, zzeb zzeb, List list, zzbe zzbe) {
        Bundle bundle2 = bundle;
        ArrayList<String> stringArrayList = bundle2.getStringArrayList("pack_names");
        HashMap hashMap = new HashMap();
        int size = stringArrayList.size();
        for (int i = 0; i < size; i++) {
            String str = stringArrayList.get(i);
            hashMap.put(str, AssetPackState.zzc(bundle2, str, zzco, zzeb, zzbe));
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            hashMap.put(str2, AssetPackState.zzb(str2, 4, 0, 0, 0, 0.0d, 1, "", ""));
        }
        return new zzbo(bundle2.getLong("total_bytes_to_download"), hashMap);
    }

    public abstract Map<String, AssetPackState> packStates();

    public abstract long totalBytes();
}
