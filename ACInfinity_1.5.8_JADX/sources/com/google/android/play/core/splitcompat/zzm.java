package com.google.android.play.core.splitcompat;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzm {
    private static final Pattern zza = Pattern.compile("lib/([^/]+)/(.*\\.so)$");
    private final zze zzb;

    zzm(zze zze) throws IOException {
        this.zzb = zze;
    }

    static /* bridge */ /* synthetic */ Set zza(zzm zzm, Set set, zzs zzs, ZipFile zipFile) {
        HashSet hashSet = new HashSet();
        zzm.zzf(zzs, set, new zzi(zzm, hashSet, zzs, zipFile));
        return hashSet;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00f7 A[SYNTHETIC, Splitter:B:33:0x00f7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zze(com.google.android.play.core.splitcompat.zzs r13, com.google.android.play.core.splitcompat.zzj r14) throws java.io.IOException {
        /*
            java.util.zip.ZipFile r0 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x00f3 }
            java.io.File r1 = r13.zza()     // Catch:{ IOException -> 0x00f3 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x00f3 }
            java.lang.String r13 = r13.zzb()     // Catch:{ IOException -> 0x00f1 }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ IOException -> 0x00f1 }
            r1.<init>()     // Catch:{ IOException -> 0x00f1 }
            java.util.Enumeration r2 = r0.entries()     // Catch:{ IOException -> 0x00f1 }
        L_0x0016:
            boolean r3 = r2.hasMoreElements()     // Catch:{ IOException -> 0x00f1 }
            r4 = 2
            java.lang.String r5 = "SplitCompat"
            r6 = 0
            r7 = 1
            if (r3 == 0) goto L_0x006a
            java.lang.Object r3 = r2.nextElement()     // Catch:{ IOException -> 0x00f1 }
            java.util.zip.ZipEntry r3 = (java.util.zip.ZipEntry) r3     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r8 = r3.getName()     // Catch:{ IOException -> 0x00f1 }
            java.util.regex.Pattern r9 = zza     // Catch:{ IOException -> 0x00f1 }
            java.util.regex.Matcher r8 = r9.matcher(r8)     // Catch:{ IOException -> 0x00f1 }
            boolean r9 = r8.matches()     // Catch:{ IOException -> 0x00f1 }
            if (r9 == 0) goto L_0x0016
            java.lang.String r9 = r8.group(r7)     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r8 = r8.group(r4)     // Catch:{ IOException -> 0x00f1 }
            r10 = 3
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ IOException -> 0x00f1 }
            r10[r6] = r13     // Catch:{ IOException -> 0x00f1 }
            r10[r7] = r8     // Catch:{ IOException -> 0x00f1 }
            r10[r4] = r9     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r4 = "NativeLibraryExtractor: split '%s' has native library '%s' for ABI '%s'"
            java.lang.String r4 = java.lang.String.format(r4, r10)     // Catch:{ IOException -> 0x00f1 }
            android.util.Log.d(r5, r4)     // Catch:{ IOException -> 0x00f1 }
            java.lang.Object r4 = r1.get(r9)     // Catch:{ IOException -> 0x00f1 }
            java.util.Set r4 = (java.util.Set) r4     // Catch:{ IOException -> 0x00f1 }
            if (r4 != 0) goto L_0x0061
            java.util.HashSet r4 = new java.util.HashSet     // Catch:{ IOException -> 0x00f1 }
            r4.<init>()     // Catch:{ IOException -> 0x00f1 }
            r1.put(r9, r4)     // Catch:{ IOException -> 0x00f1 }
        L_0x0061:
            com.google.android.play.core.splitcompat.zzl r5 = new com.google.android.play.core.splitcompat.zzl     // Catch:{ IOException -> 0x00f1 }
            r5.<init>(r3, r8)     // Catch:{ IOException -> 0x00f1 }
            r4.add(r5)     // Catch:{ IOException -> 0x00f1 }
            goto L_0x0016
        L_0x006a:
            java.util.HashMap r13 = new java.util.HashMap     // Catch:{ IOException -> 0x00f1 }
            r13.<init>()     // Catch:{ IOException -> 0x00f1 }
            java.lang.String[] r2 = android.os.Build.SUPPORTED_ABIS     // Catch:{ IOException -> 0x00f1 }
            int r3 = r2.length     // Catch:{ IOException -> 0x00f1 }
            r8 = 0
        L_0x0073:
            if (r8 >= r3) goto L_0x00e1
            r9 = r2[r8]     // Catch:{ IOException -> 0x00f1 }
            boolean r10 = r1.containsKey(r9)     // Catch:{ IOException -> 0x00f1 }
            if (r10 == 0) goto L_0x00d1
            java.lang.Object[] r10 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x00f1 }
            r10[r6] = r9     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r11 = "NativeLibraryExtractor: there are native libraries for supported ABI %s; will use this ABI"
            java.lang.String r10 = java.lang.String.format(r11, r10)     // Catch:{ IOException -> 0x00f1 }
            android.util.Log.d(r5, r10)     // Catch:{ IOException -> 0x00f1 }
            java.lang.Object r10 = r1.get(r9)     // Catch:{ IOException -> 0x00f1 }
            java.util.Set r10 = (java.util.Set) r10     // Catch:{ IOException -> 0x00f1 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ IOException -> 0x00f1 }
        L_0x0094:
            boolean r11 = r10.hasNext()     // Catch:{ IOException -> 0x00f1 }
            if (r11 == 0) goto L_0x00de
            java.lang.Object r11 = r10.next()     // Catch:{ IOException -> 0x00f1 }
            com.google.android.play.core.splitcompat.zzl r11 = (com.google.android.play.core.splitcompat.zzl) r11     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r12 = r11.zza     // Catch:{ IOException -> 0x00f1 }
            boolean r12 = r13.containsKey(r12)     // Catch:{ IOException -> 0x00f1 }
            if (r12 == 0) goto L_0x00ba
            java.lang.Object[] r12 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r11 = r11.zza     // Catch:{ IOException -> 0x00f1 }
            r12[r6] = r11     // Catch:{ IOException -> 0x00f1 }
            r12[r7] = r9     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r11 = "NativeLibraryExtractor: skipping library %s for ABI %s; already present for a better ABI"
            java.lang.String r11 = java.lang.String.format(r11, r12)     // Catch:{ IOException -> 0x00f1 }
            android.util.Log.d(r5, r11)     // Catch:{ IOException -> 0x00f1 }
            goto L_0x0094
        L_0x00ba:
            java.lang.String r12 = r11.zza     // Catch:{ IOException -> 0x00f1 }
            r13.put(r12, r11)     // Catch:{ IOException -> 0x00f1 }
            java.lang.Object[] r12 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r11 = r11.zza     // Catch:{ IOException -> 0x00f1 }
            r12[r6] = r11     // Catch:{ IOException -> 0x00f1 }
            r12[r7] = r9     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r11 = "NativeLibraryExtractor: using library %s for ABI %s"
            java.lang.String r11 = java.lang.String.format(r11, r12)     // Catch:{ IOException -> 0x00f1 }
            android.util.Log.d(r5, r11)     // Catch:{ IOException -> 0x00f1 }
            goto L_0x0094
        L_0x00d1:
            java.lang.Object[] r10 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x00f1 }
            r10[r6] = r9     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r9 = "NativeLibraryExtractor: there are no native libraries for supported ABI %s"
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ IOException -> 0x00f1 }
            android.util.Log.d(r5, r9)     // Catch:{ IOException -> 0x00f1 }
        L_0x00de:
            int r8 = r8 + 1
            goto L_0x0073
        L_0x00e1:
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ IOException -> 0x00f1 }
            java.util.Collection r13 = r13.values()     // Catch:{ IOException -> 0x00f1 }
            r1.<init>(r13)     // Catch:{ IOException -> 0x00f1 }
            r14.zza(r0, r1)     // Catch:{ IOException -> 0x00f1 }
            r0.close()     // Catch:{ IOException -> 0x00f1 }
            return
        L_0x00f1:
            r13 = move-exception
            goto L_0x00f5
        L_0x00f3:
            r13 = move-exception
            r0 = 0
        L_0x00f5:
            if (r0 == 0) goto L_0x00fa
            r0.close()     // Catch:{ IOException -> 0x00fa }
        L_0x00fa:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitcompat.zzm.zze(com.google.android.play.core.splitcompat.zzs, com.google.android.play.core.splitcompat.zzj):void");
    }

    /* access modifiers changed from: private */
    public final void zzf(zzs zzs, Set set, zzk zzk) throws IOException {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zzl zzl = (zzl) it.next();
            File zzc = this.zzb.zzc(zzs.zzb(), zzl.zza);
            boolean z = false;
            if (zzc.exists() && zzc.length() == zzl.zzb.getSize() && zze.zzp(zzc)) {
                z = true;
            }
            zzk.zza(zzl, zzc, z);
        }
    }

    /* access modifiers changed from: package-private */
    public final Set zzb(zzs zzs) throws IOException {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        HashSet hashSet = new HashSet();
        zze(zzs, new zzg(this, zzs, hashSet, atomicBoolean));
        if (atomicBoolean.get()) {
            return hashSet;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final Set zzc() throws IOException {
        Log.d("SplitCompat", "NativeLibraryExtractor: synchronizing native libraries");
        Set<zzs> zzj = this.zzb.zzj();
        for (String str : this.zzb.zzh()) {
            Iterator it = zzj.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((zzs) it.next()).zzb().equals(str)) {
                        break;
                    }
                } else {
                    Log.i("SplitCompat", String.format("NativeLibraryExtractor: extracted split '%s' has no corresponding split; deleting", new Object[]{str}));
                    this.zzb.zzn(str);
                    break;
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (zzs zzs : zzj) {
            HashSet hashSet2 = new HashSet();
            zze(zzs, new zzh(this, hashSet2, zzs));
            for (File file : this.zzb.zzi(zzs.zzb())) {
                if (!hashSet2.contains(file)) {
                    Log.i("SplitCompat", String.format("NativeLibraryExtractor: file '%s' found in split '%s' that is not in the split file '%s'; removing", new Object[]{file.getAbsolutePath(), zzs.zzb(), zzs.zza().getAbsolutePath()}));
                    this.zzb.zzo(file);
                }
            }
            hashSet.addAll(hashSet2);
        }
        return hashSet;
    }
}
