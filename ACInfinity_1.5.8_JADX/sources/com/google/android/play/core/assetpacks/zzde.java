package com.google.android.play.core.assetpacks;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.google.android.play.core.assetpacks.model.zzb;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzco;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzde {
    private static final zzag zza = new zzag("ExtractorSessionStoreView");
    private final zzbh zzb;
    private final zzco zzc;
    private final zzco zzd;
    private final zzco zze;
    private final Map zzf = new HashMap();
    private final ReentrantLock zzg = new ReentrantLock();

    zzde(zzbh zzbh, zzco zzco, zzco zzco2, zzco zzco3) {
        this.zzb = zzbh;
        this.zzc = zzco;
        this.zzd = zzco2;
        this.zze = zzco3;
    }

    private final zzdb zzq(int i) {
        Map map = this.zzf;
        Integer valueOf = Integer.valueOf(i);
        zzdb zzdb = (zzdb) map.get(valueOf);
        if (zzdb != null) {
            return zzdb;
        }
        throw new zzck(String.format("Could not find session %d while trying to get it", new Object[]{valueOf}), i);
    }

    private final Object zzr(zzdd zzdd) {
        try {
            this.zzg.lock();
            return zzdd.zza();
        } finally {
            this.zzg.unlock();
        }
    }

    private static String zzs(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        if (stringArrayList != null && !stringArrayList.isEmpty()) {
            return stringArrayList.get(0);
        }
        throw new zzck("Session without pack received.");
    }

    private static List zzt(List list) {
        return list == null ? Collections.emptyList() : list;
    }

    private final Map zzu(List list) {
        return (Map) zzr(new zzcx(this, list));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Boolean zza(Bundle bundle) {
        int i = bundle.getInt("session_id");
        if (i == 0) {
            return true;
        }
        Map map = this.zzf;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf)) {
            return true;
        }
        zzdb zzdb = (zzdb) this.zzf.get(valueOf);
        if (zzdb.zzc.zzd == 6) {
            return false;
        }
        return Boolean.valueOf(!zzbg.zzc(zzdb.zzc.zzd, bundle.getInt(zzb.zza(NotificationCompat.CATEGORY_STATUS, zzs(bundle)))));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Boolean zzb(Bundle bundle) {
        zzdc zzdc;
        Bundle bundle2 = bundle;
        int i = bundle2.getInt("session_id");
        if (i == 0) {
            return false;
        }
        Map map = this.zzf;
        Integer valueOf = Integer.valueOf(i);
        boolean z = true;
        if (map.containsKey(valueOf)) {
            zzdb zzq = zzq(i);
            int i2 = bundle2.getInt(zzb.zza(NotificationCompat.CATEGORY_STATUS, zzq.zzc.zza));
            zzda zzda = zzq.zzc;
            int i3 = zzda.zzd;
            if (zzbg.zzc(i3, i2)) {
                zza.zza("Found stale update for session %s with status %d.", valueOf, Integer.valueOf(i3));
                zzda zzda2 = zzq.zzc;
                String str = zzda2.zza;
                int i4 = zzda2.zzd;
                if (i4 == 4) {
                    ((zzy) this.zzc.zza()).zzh(i, str);
                } else if (i4 == 5) {
                    ((zzy) this.zzc.zza()).zzi(i);
                } else if (i4 == 6) {
                    ((zzy) this.zzc.zza()).zze(Arrays.asList(new String[]{str}));
                }
            } else {
                zzda.zzd = i2;
                if (zzbg.zzd(i2)) {
                    zzn(i);
                    this.zzd.zzc(zzq.zzc.zza);
                } else {
                    for (zzdc zzdc2 : zzda.zzf) {
                        ArrayList parcelableArrayList = bundle2.getParcelableArrayList(zzb.zzb("chunk_intents", zzq.zzc.zza, zzdc2.zza));
                        if (parcelableArrayList != null) {
                            for (int i5 = 0; i5 < parcelableArrayList.size(); i5++) {
                                if (!(parcelableArrayList.get(i5) == null || ((Intent) parcelableArrayList.get(i5)).getData() == null)) {
                                    ((zzcz) zzdc2.zzd.get(i5)).zza = true;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            String zzs = zzs(bundle);
            long j = bundle2.getLong(zzb.zza("pack_version", zzs));
            String string = bundle2.getString(zzb.zza("pack_version_tag", zzs), "");
            int i6 = bundle2.getInt(zzb.zza(NotificationCompat.CATEGORY_STATUS, zzs));
            long j2 = bundle2.getLong(zzb.zza("total_bytes_to_download", zzs));
            ArrayList<String> stringArrayList = bundle2.getStringArrayList(zzb.zza("slice_ids", zzs));
            ArrayList arrayList = new ArrayList();
            for (String str2 : zzt(stringArrayList)) {
                ArrayList parcelableArrayList2 = bundle2.getParcelableArrayList(zzb.zzb("chunk_intents", zzs, str2));
                ArrayList arrayList2 = new ArrayList();
                for (Intent intent : zzt(parcelableArrayList2)) {
                    if (intent == null) {
                        z = false;
                    }
                    arrayList2.add(new zzcz(z));
                    z = true;
                }
                String string2 = bundle2.getString(zzb.zzb("uncompressed_hash_sha256", zzs, str2));
                long j3 = bundle2.getLong(zzb.zzb("uncompressed_size", zzs, str2));
                int i7 = bundle2.getInt(zzb.zzb("patch_format", zzs, str2), 0);
                if (i7 != 0) {
                    zzdc = new zzdc(str2, string2, j3, arrayList2, 0, i7);
                } else {
                    zzdc = new zzdc(str2, string2, j3, arrayList2, bundle2.getInt(zzb.zzb("compression_format", zzs, str2), 0), 0);
                }
                arrayList.add(zzdc);
                z = true;
            }
            this.zzf.put(Integer.valueOf(i), new zzdb(i, bundle2.getInt("app_version_code"), new zzda(zzs, j, i6, j2, arrayList, string)));
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(String str, int i, long j) {
        zzdb zzdb = (zzdb) zzu(Arrays.asList(new String[]{str})).get(str);
        if (zzdb == null || zzbg.zzd(zzdb.zzc.zzd)) {
            zza.zzb(String.format("Could not find pack %s while trying to complete it", new Object[]{str}), new Object[0]);
        }
        this.zzb.zzE(str, i, j);
        zzdb.zzc.zzd = 4;
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzd(int i, int i2) {
        zzq(i).zzc.zzd = 5;
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zze(int i) {
        zzdb zzq = zzq(i);
        zzda zzda = zzq.zzc;
        if (zzbg.zzd(zzda.zzd)) {
            this.zzb.zzE(zzda.zza, zzq.zzb, zzda.zzb);
            zzda zzda2 = zzq.zzc;
            int i2 = zzda2.zzd;
            if (i2 != 5 && i2 != 6) {
                return null;
            }
            this.zzb.zzF(zzda2.zza, zzq.zzb, zzda2.zzb);
            return null;
        }
        throw new zzck(String.format("Could not safely delete session %d because it is not in a terminal state.", new Object[]{Integer.valueOf(i)}), i);
    }

    /* access modifiers changed from: package-private */
    public final Map zzf(List list) {
        return (Map) zzr(new zzcw(this, list));
    }

    /* access modifiers changed from: package-private */
    public final Map zzg() {
        return this.zzf;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map zzh(List list) {
        Map zzu = zzu(list);
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            zzdb zzdb = (zzdb) zzu.get(str);
            if (zzdb == null) {
                hashMap.put(str, 8);
            } else {
                zzda zzda = zzdb.zzc;
                if (zzbg.zza(zzda.zzd)) {
                    try {
                        zzda.zzd = 6;
                        ((Executor) this.zze.zza()).execute(new zzcy(this, zzdb));
                        this.zzd.zzc(str);
                    } catch (zzck unused) {
                        zza.zzd("Session %d with pack %s does not exist, no need to cancel.", Integer.valueOf(zzdb.zza), str);
                    }
                }
                hashMap.put(str, Integer.valueOf(zzdb.zzc.zzd));
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map zzi(List list) {
        int i;
        HashMap hashMap = new HashMap();
        for (zzdb zzdb : this.zzf.values()) {
            String str = zzdb.zzc.zza;
            if (list.contains(str)) {
                zzdb zzdb2 = (zzdb) hashMap.get(str);
                if (zzdb2 == null) {
                    i = -1;
                } else {
                    i = zzdb2.zza;
                }
                if (i < zzdb.zza) {
                    hashMap.put(str, zzdb);
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final void zzj() {
        this.zzg.lock();
    }

    /* access modifiers changed from: package-private */
    public final void zzk(String str, int i, long j) {
        zzr(new zzcv(this, str, i, j));
    }

    /* access modifiers changed from: package-private */
    public final void zzl() {
        this.zzg.unlock();
    }

    /* access modifiers changed from: package-private */
    public final void zzm(int i, int i2) {
        zzr(new zzcs(this, i, 5));
    }

    /* access modifiers changed from: package-private */
    public final void zzn(int i) {
        zzr(new zzcr(this, i));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzo(Bundle bundle) {
        return ((Boolean) zzr(new zzct(this, bundle))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzp(Bundle bundle) {
        return ((Boolean) zzr(new zzcu(this, bundle))).booleanValue();
    }
}
