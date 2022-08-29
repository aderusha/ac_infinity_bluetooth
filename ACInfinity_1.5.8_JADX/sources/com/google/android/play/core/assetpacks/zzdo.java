package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import androidx.core.app.NotificationCompat;
import com.google.android.play.core.assetpacks.model.zzb;
import com.google.android.play.core.common.LocalTestingException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzcj;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import com.google.android.play.core.tasks.zzi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzdo implements zzy {
    private static final zzag zza = new zzag("FakeAssetPackService");
    private static final AtomicInteger zzb = new AtomicInteger(1);
    private final String zzc;
    private final zzbb zzd;
    private final zzco zze;
    private final Context zzf;
    private final zzed zzg;
    private final zzco zzh;
    private final zzeb zzi;
    private final Handler zzj = new Handler(Looper.getMainLooper());

    zzdo(File file, zzbb zzbb, zzco zzco, Context context, zzed zzed, zzco zzco2, zzeb zzeb) {
        this.zzc = file.getAbsolutePath();
        this.zzd = zzbb;
        this.zze = zzco;
        this.zzf = context;
        this.zzg = zzed;
        this.zzh = zzco2;
        this.zzi = zzeb;
    }

    static long zzk(int i, long j) {
        if (i == 2) {
            return j / 2;
        }
        if (i == 3 || i == 4) {
            return j;
        }
        return 0;
    }

    private final Bundle zzp(int i, String str, int i2) throws LocalTestingException {
        Bundle bundle = new Bundle();
        bundle.putInt("app_version_code", this.zzg.zza());
        bundle.putInt("session_id", i);
        File[] zzs = zzs(str);
        ArrayList arrayList = new ArrayList();
        long j = 0;
        for (File file : zzs) {
            j += file.length();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(i2 == 3 ? new Intent().setData(Uri.EMPTY) : null);
            String zza2 = zzcj.zza(file);
            bundle.putParcelableArrayList(zzb.zzb("chunk_intents", str, zza2), arrayList2);
            bundle.putString(zzb.zzb("uncompressed_hash_sha256", str, zza2), zzr(file));
            bundle.putLong(zzb.zzb("uncompressed_size", str, zza2), file.length());
            arrayList.add(zza2);
        }
        bundle.putStringArrayList(zzb.zza("slice_ids", str), arrayList);
        bundle.putLong(zzb.zza("pack_version", str), (long) this.zzg.zza());
        bundle.putInt(zzb.zza(NotificationCompat.CATEGORY_STATUS, str), i2);
        bundle.putInt(zzb.zza("error_code", str), 0);
        bundle.putLong(zzb.zza("bytes_downloaded", str), zzk(i2, j));
        bundle.putLong(zzb.zza("total_bytes_to_download", str), j);
        bundle.putStringArrayList("pack_names", new ArrayList(Arrays.asList(new String[]{str})));
        bundle.putLong("bytes_downloaded", zzk(i2, j));
        bundle.putLong("total_bytes_to_download", j);
        this.zzj.post(new zzdl(this, new Intent("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE").putExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE", bundle)));
        return bundle;
    }

    private final AssetPackState zzq(String str, int i) throws LocalTestingException {
        String str2 = str;
        long j = 0;
        for (File length : zzs(str)) {
            j += length.length();
        }
        int i2 = i;
        return AssetPackState.zzb(str, i2, 0, zzk(i2, j), j, this.zze.zza(str), 1, String.valueOf(this.zzg.zza()), this.zzi.zza(str));
    }

    private static String zzr(File file) throws LocalTestingException {
        try {
            return zzdq.zza(Arrays.asList(new File[]{file}));
        } catch (NoSuchAlgorithmException e) {
            throw new LocalTestingException("SHA256 algorithm not supported.", e);
        } catch (IOException e2) {
            throw new LocalTestingException(String.format("Could not digest file: %s.", new Object[]{file}), e2);
        }
    }

    private final File[] zzs(String str) throws LocalTestingException {
        File file = new File(this.zzc);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles(new zzdj(str));
            if (listFiles != null) {
                if (r1 != 0) {
                    for (File zza2 : listFiles) {
                        if (zzcj.zza(zza2).equals(str)) {
                            return listFiles;
                        }
                    }
                    throw new LocalTestingException(String.format("No main slice available for pack '%s'.", new Object[]{str}));
                }
                throw new LocalTestingException(String.format("No APKs available for pack '%s'.", new Object[]{str}));
            }
            throw new LocalTestingException(String.format("Failed fetching APKs for pack '%s'.", new Object[]{str}));
        }
        throw new LocalTestingException(String.format("Local testing directory '%s' not found.", new Object[]{file}));
    }

    public final Task zza(int i, String str, String str2, int i2) {
        zza.zzd("getChunkFileDescriptor(session=%d, %s, %s, %d)", Integer.valueOf(i), str, str2, Integer.valueOf(i2));
        zzi zzi2 = new zzi();
        try {
            for (File file : zzs(str)) {
                if (zzcj.zza(file).equals(str2)) {
                    zzi2.zzc(ParcelFileDescriptor.open(file, 268435456));
                    return zzi2.zza();
                }
            }
            throw new LocalTestingException(String.format("Local testing slice for '%s' not found.", new Object[]{str2}));
        } catch (FileNotFoundException e) {
            zza.zze("getChunkFileDescriptor failed", e);
            zzi2.zzb(new LocalTestingException("Asset Slice file not found.", e));
        } catch (LocalTestingException e2) {
            zza.zze("getChunkFileDescriptor failed", e2);
            zzi2.zzb(e2);
        }
    }

    public final Task zzb(List list, zzbe zzbe, Map map) {
        zza.zzd("getPackStates(%s)", list);
        zzi zzi2 = new zzi();
        ((Executor) this.zzh.zza()).execute(new zzdm(this, list, zzbe, zzi2));
        return zzi2.zza();
    }

    public final Task zzc(List list, List list2, Map map) {
        zza.zzd("startDownload(%s)", list2);
        zzi zzi2 = new zzi();
        ((Executor) this.zzh.zza()).execute(new zzdn(this, list2, zzi2, list));
        return zzi2.zza();
    }

    public final Task zzd(Map map) {
        zza.zzd("syncPacks()", new Object[0]);
        return Tasks.zzb(new ArrayList());
    }

    public final void zze(List list) {
        zza.zzd("cancelDownload(%s)", list);
    }

    public final void zzf() {
        zza.zzd("keepAlive", new Object[0]);
    }

    public final void zzg(int i, String str, String str2, int i2) {
        zza.zzd("notifyChunkTransferred", new Object[0]);
    }

    public final void zzh(int i, String str) {
        zza.zzd("notifyModuleCompleted", new Object[0]);
        ((Executor) this.zzh.zza()).execute(new zzdk(this, i, str));
    }

    public final void zzi(int i) {
        zza.zzd("notifySessionFailed", new Object[0]);
    }

    public final void zzj(String str) {
        zza.zzd("removePack(%s)", str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzl(Intent intent) {
        this.zzd.zza(this.zzf, intent);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(List list, zzbe zzbe, zzi zzi2) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                AssetPackState zzq = zzq(str, ((zze) zzbe).zza.zza(8, str));
                j += zzq.totalBytesToDownload();
                hashMap.put(str, zzq);
            } catch (LocalTestingException e) {
                zzi2.zzb(e);
                return;
            }
        }
        zzi2.zzc(new zzbo(j, hashMap));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzn(int i, String str) {
        try {
            zzp(i, str, 4);
        } catch (LocalTestingException e) {
            zza.zze("notifyModuleCompleted failed", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzo(List list, zzi zzi2, List list2) {
        zzi zzi3 = zzi2;
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                AssetPackState zzq = zzq(str, 1);
                j += zzq.totalBytesToDownload();
                hashMap.put(str, zzq);
            } catch (LocalTestingException e) {
                zzi3.zzb(e);
                return;
            }
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            try {
                int andIncrement = zzb.getAndIncrement();
                zzp(andIncrement, str2, 1);
                zzp(andIncrement, str2, 2);
                zzp(andIncrement, str2, 3);
            } catch (LocalTestingException e2) {
                zzi3.zzb(e2);
                return;
            }
        }
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            String str3 = (String) it3.next();
            hashMap.put(str3, AssetPackState.zzb(str3, 4, 0, 0, 0, 0.0d, 1, String.valueOf(this.zzg.zza()), String.valueOf(this.zzg.zza())));
        }
        zzi3.zzc(new zzbo(j, hashMap));
    }
}
