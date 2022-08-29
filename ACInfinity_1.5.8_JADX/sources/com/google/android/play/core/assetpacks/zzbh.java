package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.play.core.internal.zzag;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbh {
    private static final zzag zza = new zzag("AssetPackStorage");
    private static final long zzb = TimeUnit.DAYS.toMillis(14);
    private static final long zzc = TimeUnit.DAYS.toMillis(28);
    private final Context zzd;
    private final zzed zze;

    zzbh(Context context, zzed zzed) {
        this.zzd = context;
        this.zze = zzed;
    }

    private static long zzH(File file, boolean z) {
        if (!file.exists()) {
            return -1;
        }
        ArrayList arrayList = new ArrayList();
        if (z && file.listFiles().length > 1) {
            zza.zze("Multiple pack versions found, using highest version code.", new Object[0]);
        }
        try {
            for (File file2 : file.listFiles()) {
                if (!file2.getName().equals("stale.tmp")) {
                    arrayList.add(Long.valueOf(file2.getName()));
                }
            }
        } catch (NumberFormatException e) {
            zza.zzc(e, "Corrupt asset pack directories.", new Object[0]);
        }
        if (arrayList.isEmpty()) {
            return -1;
        }
        Collections.sort(arrayList);
        return ((Long) arrayList.get(arrayList.size() - 1)).longValue();
    }

    private final File zzI(String str) {
        return new File(zzL(), str);
    }

    private final File zzJ(String str, int i, long j) {
        return new File(zzj(str, i, j), "merge.tmp");
    }

    private final File zzK(String str, int i, long j) {
        return new File(new File(new File(zzM(), str), String.valueOf(i)), String.valueOf(j));
    }

    private final File zzL() {
        return new File(this.zzd.getFilesDir(), "assetpacks");
    }

    private final File zzM() {
        return new File(zzL(), "_tmp");
    }

    private static List zzN(PackageInfo packageInfo, String str) {
        ArrayList arrayList = new ArrayList();
        if (packageInfo.splitNames == null) {
            return arrayList;
        }
        int i = (-Arrays.binarySearch(packageInfo.splitNames, str)) - 1;
        while (i < packageInfo.splitNames.length && packageInfo.splitNames[i].startsWith(str)) {
            arrayList.add(packageInfo.applicationInfo.splitSourceDirs[i]);
            i++;
        }
        return arrayList;
    }

    private final List zzO() {
        ArrayList arrayList = new ArrayList();
        try {
            if (zzL().exists()) {
                if (zzL().listFiles() != null) {
                    for (File file : zzL().listFiles()) {
                        if (!file.getCanonicalPath().equals(zzM().getCanonicalPath())) {
                            arrayList.add(file);
                        }
                    }
                    return arrayList;
                }
            }
            return arrayList;
        } catch (IOException e) {
            zza.zzb("Could not process directory while scanning installed packs. %s", e);
        }
    }

    private static void zzP(File file) {
        if (file.listFiles() != null && file.listFiles().length > 1) {
            long zzH = zzH(file, false);
            for (File file2 : file.listFiles()) {
                if (!file2.getName().equals(String.valueOf(zzH)) && !file2.getName().equals("stale.tmp")) {
                    zzQ(file2);
                }
            }
        }
    }

    private static boolean zzQ(File file) {
        File[] listFiles = file.listFiles();
        boolean z = true;
        if (listFiles != null) {
            for (File zzQ : listFiles) {
                z &= zzQ(zzQ);
            }
        }
        if (!file.delete()) {
            return false;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final void zzA(String str, int i, long j, int i2) throws IOException {
        File zzJ = zzJ(str, i, j);
        Properties properties = new Properties();
        properties.put("numberOfMerges", String.valueOf(i2));
        zzJ.getParentFile().mkdirs();
        zzJ.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(zzJ);
        properties.store(fileOutputStream, (String) null);
        fileOutputStream.close();
    }

    /* access modifiers changed from: package-private */
    public final void zzB(String str, int i, long j) {
        File zzI = zzI(str);
        if (zzI.exists()) {
            for (File file : zzI.listFiles()) {
                if (!file.getName().equals(String.valueOf(i)) && !file.getName().equals("stale.tmp")) {
                    zzQ(file);
                } else if (file.getName().equals(String.valueOf(i))) {
                    for (File file2 : file.listFiles()) {
                        if (!file2.getName().equals(String.valueOf(j))) {
                            zzQ(file2);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzC(List list) {
        int zza2 = this.zze.zza();
        for (File file : zzO()) {
            if (!list.contains(file.getName()) && zzH(file, true) != ((long) zza2)) {
                zzQ(file);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzD(String str) {
        if (!zzI(str).exists()) {
            return true;
        }
        return zzQ(zzI(str));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzE(String str, int i, long j) {
        if (!zzK(str, i, j).exists()) {
            return true;
        }
        return zzQ(zzK(str, i, j));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzF(String str, int i, long j) {
        if (!zzh(str, i, j).exists()) {
            return true;
        }
        return zzQ(zzh(str, i, j));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzG(String str) {
        try {
            return zzr(str) != null;
        } catch (IOException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public final int zza(String str) {
        return (int) zzH(zzI(str), true);
    }

    /* access modifiers changed from: package-private */
    public final int zzb(String str, int i, long j) throws IOException {
        File zzJ = zzJ(str, i, j);
        if (!zzJ.exists()) {
            return 0;
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(zzJ);
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("numberOfMerges") != null) {
                try {
                    return Integer.parseInt(properties.getProperty("numberOfMerges"));
                } catch (NumberFormatException e) {
                    throw new zzck("Merge checkpoint file corrupt.", (Exception) e);
                }
            } else {
                throw new zzck("Merge checkpoint file corrupt.");
            }
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final long zzc(String str) {
        return zzH(zzg(str, (int) zzH(zzI(str), true)), true);
    }

    /* access modifiers changed from: package-private */
    public final AssetLocation zzd(String str, String str2, List list) {
        if (list == null) {
            return null;
        }
        String path = new File("assets", str2).getPath();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            try {
                AssetLocation zza2 = zzbt.zza(str3, path);
                if (zza2 != null) {
                    return zza2;
                }
            } catch (IOException e) {
                zza.zzc(e, "Failed to parse APK file '%s' looking for asset '%s'.", str3, str2);
                return null;
            }
        }
        zza.zza("The asset %s is not present in Asset Pack %s. Searched in APKs: %s", str2, str, list);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final AssetLocation zze(String str, String str2, AssetPackLocation assetPackLocation) {
        File file = new File(assetPackLocation.assetsPath(), str2);
        if (file.exists()) {
            return new zzbl(file.getPath(), 0, file.length());
        }
        zza.zza("The asset %s is not present in Asset Pack %s. Searched in folder: %s", str2, str, assetPackLocation.assetsPath());
        return null;
    }

    /* access modifiers changed from: package-private */
    public final AssetPackLocation zzf(String str) throws IOException {
        String zzr = zzr(str);
        if (zzr == null) {
            return null;
        }
        File file = new File(zzr, "assets");
        if (file.isDirectory()) {
            return new zzbm(0, zzr, file.getCanonicalPath());
        }
        zza.zzb("Failed to find assets directory: %s", file);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final File zzg(String str, int i) {
        return new File(zzI(str), String.valueOf(i));
    }

    /* access modifiers changed from: package-private */
    public final File zzh(String str, int i, long j) {
        return new File(zzg(str, i), String.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final File zzi(String str, int i, long j) {
        return new File(zzh(str, i, j), "_metadata");
    }

    /* access modifiers changed from: package-private */
    public final File zzj(String str, int i, long j) {
        return new File(zzK(str, i, j), "_packs");
    }

    /* access modifiers changed from: package-private */
    public final File zzk(String str, int i, long j) {
        return new File(zzi(str, i, j), "properties.dat");
    }

    /* access modifiers changed from: package-private */
    public final File zzl(String str, int i, long j) {
        return new File(new File(zzK(str, i, j), "_slices"), "_metadata");
    }

    /* access modifiers changed from: package-private */
    public final File zzm(String str, int i, long j, String str2) {
        return new File(zzo(str, i, j, str2), "checkpoint_ext.dat");
    }

    /* access modifiers changed from: package-private */
    public final File zzn(String str, int i, long j, String str2) {
        return new File(zzo(str, i, j, str2), "checkpoint.dat");
    }

    /* access modifiers changed from: package-private */
    public final File zzo(String str, int i, long j, String str2) {
        return new File(zzl(str, i, j), str2);
    }

    /* access modifiers changed from: package-private */
    public final File zzp(String str, int i, long j, String str2) {
        return new File(new File(new File(zzK(str, i, j), "_slices"), "_unverified"), str2);
    }

    /* access modifiers changed from: package-private */
    public final File zzq(String str, int i, long j, String str2) {
        return new File(new File(new File(zzK(str, i, j), "_slices"), "_verified"), str2);
    }

    /* access modifiers changed from: package-private */
    public final String zzr(String str) throws IOException {
        int length;
        File file = new File(zzL(), str);
        if (!file.exists()) {
            zza.zza("Pack not found with pack name: %s", str);
            return null;
        }
        File file2 = new File(file, String.valueOf(this.zze.zza()));
        if (!file2.exists()) {
            zza.zza("Pack not found with pack name: %s app version: %s", str, Integer.valueOf(this.zze.zza()));
            return null;
        }
        File[] listFiles = file2.listFiles();
        if (listFiles == null || (length = listFiles.length) == 0) {
            zza.zza("No pack version found for pack name: %s app version: %s", str, Integer.valueOf(this.zze.zza()));
            return null;
        } else if (length <= 1) {
            return listFiles[0].getCanonicalPath();
        } else {
            zza.zzb("Multiple pack versions found for pack name: %s app version: %s", str, Integer.valueOf(this.zze.zza()));
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final List zzs(String str) {
        PackageInfo packageInfo;
        String str2 = null;
        try {
            packageInfo = this.zzd.getPackageManager().getPackageInfo(this.zzd.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            zza.zzb("Could not find PackageInfo.", new Object[0]);
            packageInfo = null;
        }
        if (packageInfo == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 21) {
            if (packageInfo.splitNames == null || packageInfo.applicationInfo.splitSourceDirs == null) {
                zza.zza("No splits present for package %s.", str);
            } else {
                int binarySearch = Arrays.binarySearch(packageInfo.splitNames, str);
                if (binarySearch < 0) {
                    zza.zza("Asset Pack '%s' is not installed.", str);
                } else {
                    str2 = packageInfo.applicationInfo.splitSourceDirs[binarySearch];
                }
            }
            if (str2 == null) {
                arrayList.add(packageInfo.applicationInfo.sourceDir);
                arrayList.addAll(zzN(packageInfo, "config."));
                return arrayList;
            }
            arrayList.add(str2);
            arrayList.addAll(zzN(packageInfo, String.valueOf(str).concat(".config.")));
            return arrayList;
        }
        arrayList.add(packageInfo.applicationInfo.sourceDir);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public final Map zzt() {
        HashMap hashMap = new HashMap();
        for (File name : zzO()) {
            String name2 = name.getName();
            int zzH = (int) zzH(zzI(name2), true);
            long zzH2 = zzH(zzg(name2, zzH), true);
            if (zzh(name2, zzH, zzH2).exists()) {
                hashMap.put(name2, Long.valueOf(zzH2));
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final Map zzu() {
        HashMap hashMap = new HashMap();
        for (String str : zzv().keySet()) {
            hashMap.put(str, Long.valueOf(zzc(str)));
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final Map zzv() {
        HashMap hashMap = new HashMap();
        try {
            for (File file : zzO()) {
                AssetPackLocation zzf = zzf(file.getName());
                if (zzf != null) {
                    hashMap.put(file.getName(), zzf);
                }
            }
        } catch (IOException e) {
            zza.zzb("Could not process directory while scanning installed packs: %s", e);
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final void zzw() {
        for (File file : zzO()) {
            if (file.listFiles() != null) {
                zzP(file);
                long zzH = zzH(file, false);
                if (((long) this.zze.zza()) != zzH) {
                    try {
                        new File(new File(file, String.valueOf(zzH)), "stale.tmp").createNewFile();
                    } catch (IOException unused) {
                        zza.zzb("Could not write staleness marker.", new Object[0]);
                    }
                }
                for (File zzP : file.listFiles()) {
                    zzP(zzP);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzx() {
        if (zzM().exists()) {
            for (File file : zzM().listFiles()) {
                if (System.currentTimeMillis() - file.lastModified() > zzb) {
                    zzQ(file);
                } else {
                    zzP(file);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzy() {
        for (File file : zzO()) {
            if (file.listFiles() != null) {
                for (File file2 : file.listFiles()) {
                    File file3 = new File(file2, "stale.tmp");
                    if (file3.exists() && System.currentTimeMillis() - file3.lastModified() > zzc) {
                        zzQ(file2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzz() {
        zzQ(zzL());
    }
}
