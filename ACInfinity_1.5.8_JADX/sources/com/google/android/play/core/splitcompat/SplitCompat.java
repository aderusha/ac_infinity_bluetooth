package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.google.android.play.core.internal.zzau;
import com.google.android.play.core.internal.zzaw;
import com.google.android.play.core.internal.zzay;
import com.google.android.play.core.internal.zzbt;
import com.google.android.play.core.splitinstall.zzbe;
import com.google.android.play.core.splitinstall.zzo;
import com.google.android.play.core.splitinstall.zzr;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class SplitCompat {
    public static final /* synthetic */ int zza = 0;
    private static final AtomicReference zzb = new AtomicReference((Object) null);
    /* access modifiers changed from: private */
    public final zze zzc;
    private final zzbe zzd;
    private final Set zze = new HashSet();
    private final zza zzf;

    private SplitCompat(Context context) {
        try {
            zze zze2 = new zze(context);
            this.zzc = zze2;
            this.zzf = new zza(zze2);
            this.zzd = new zzbe(context);
        } catch (PackageManager.NameNotFoundException e) {
            throw new zzbt("Failed to initialize FileStorage", e);
        }
    }

    public static boolean install(Context context) {
        return zzi(context, false);
    }

    public static boolean installActivity(Context context) {
        if (zzj()) {
            return false;
        }
        SplitCompat splitCompat = (SplitCompat) zzb.get();
        if (splitCompat != null) {
            return splitCompat.zzf.zzb(context, splitCompat.zzf());
        }
        if (context.getApplicationContext() != null) {
            install(context.getApplicationContext());
        }
        return install(context);
    }

    public static boolean zzd(Context context) {
        return zzi(context, true);
    }

    public static boolean zze() {
        return zzb.get() != null;
    }

    /* access modifiers changed from: private */
    public final Set zzf() {
        HashSet hashSet;
        synchronized (this.zze) {
            hashSet = new HashSet(this.zze);
        }
        return hashSet;
    }

    /* access modifiers changed from: private */
    public final void zzg(Set set) throws IOException {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zze.zzl(this.zzc.zzg((String) it.next()));
        }
        this.zzd.zzb();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:78:0x0192 */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x018f A[SYNTHETIC, Splitter:B:76:0x018f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void zzh(android.content.Context r9, boolean r10) throws java.io.IOException {
        /*
            r8 = this;
            monitor-enter(r8)
            if (r10 == 0) goto L_0x0009
            com.google.android.play.core.splitcompat.zze r0 = r8.zzc     // Catch:{ all -> 0x022a }
            r0.zzk()     // Catch:{ all -> 0x022a }
            goto L_0x0015
        L_0x0009:
            java.util.concurrent.Executor r0 = com.google.android.play.core.splitcompat.zzd.zza()     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitcompat.zzp r1 = new com.google.android.play.core.splitcompat.zzp     // Catch:{ all -> 0x022a }
            r1.<init>(r8)     // Catch:{ all -> 0x022a }
            r0.execute(r1)     // Catch:{ all -> 0x022a }
        L_0x0015:
            java.lang.String r0 = r9.getPackageName()     // Catch:{ all -> 0x022a }
            r1 = 0
            android.content.pm.PackageManager r2 = r9.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0218 }
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r0, r1)     // Catch:{ NameNotFoundException -> 0x0218 }
            java.lang.String[] r3 = r2.splitNames     // Catch:{ NameNotFoundException -> 0x0218 }
            if (r3 != 0) goto L_0x002c
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ NameNotFoundException -> 0x0218 }
            r2.<init>()     // Catch:{ NameNotFoundException -> 0x0218 }
            goto L_0x0032
        L_0x002c:
            java.lang.String[] r2 = r2.splitNames     // Catch:{ NameNotFoundException -> 0x0218 }
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch:{ NameNotFoundException -> 0x0218 }
        L_0x0032:
            com.google.android.play.core.splitcompat.zze r0 = r8.zzc     // Catch:{ all -> 0x022a }
            java.util.Set r0 = r0.zzj()     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitinstall.zzbe r1 = r8.zzd     // Catch:{ all -> 0x022a }
            java.util.Set r1 = r1.zza()     // Catch:{ all -> 0x022a }
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x022a }
            r3.<init>()     // Catch:{ all -> 0x022a }
            java.util.Iterator r4 = r0.iterator()     // Catch:{ all -> 0x022a }
        L_0x0047:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x022a }
            if (r5 == 0) goto L_0x006e
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitcompat.zzs r5 = (com.google.android.play.core.splitcompat.zzs) r5     // Catch:{ all -> 0x022a }
            java.lang.String r5 = r5.zzb()     // Catch:{ all -> 0x022a }
            boolean r6 = r2.contains(r5)     // Catch:{ all -> 0x022a }
            if (r6 != 0) goto L_0x0067
            java.lang.String r6 = com.google.android.play.core.splitinstall.zzs.zzb(r5)     // Catch:{ all -> 0x022a }
            boolean r6 = r1.contains(r6)     // Catch:{ all -> 0x022a }
            if (r6 == 0) goto L_0x0047
        L_0x0067:
            r3.add(r5)     // Catch:{ all -> 0x022a }
            r4.remove()     // Catch:{ all -> 0x022a }
            goto L_0x0047
        L_0x006e:
            if (r10 == 0) goto L_0x0074
            r8.zzg(r3)     // Catch:{ all -> 0x022a }
            goto L_0x0086
        L_0x0074:
            boolean r1 = r3.isEmpty()     // Catch:{ all -> 0x022a }
            if (r1 != 0) goto L_0x0086
            java.util.concurrent.Executor r1 = com.google.android.play.core.splitcompat.zzd.zza()     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitcompat.zzq r4 = new com.google.android.play.core.splitcompat.zzq     // Catch:{ all -> 0x022a }
            r4.<init>(r8, r3)     // Catch:{ all -> 0x022a }
            r1.execute(r4)     // Catch:{ all -> 0x022a }
        L_0x0086:
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ all -> 0x022a }
            r1.<init>()     // Catch:{ all -> 0x022a }
            java.util.Iterator r3 = r0.iterator()     // Catch:{ all -> 0x022a }
        L_0x008f:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x022a }
            if (r4 == 0) goto L_0x00a9
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitcompat.zzs r4 = (com.google.android.play.core.splitcompat.zzs) r4     // Catch:{ all -> 0x022a }
            java.lang.String r4 = r4.zzb()     // Catch:{ all -> 0x022a }
            boolean r5 = com.google.android.play.core.splitinstall.zzs.zzf(r4)     // Catch:{ all -> 0x022a }
            if (r5 != 0) goto L_0x008f
            r1.add(r4)     // Catch:{ all -> 0x022a }
            goto L_0x008f
        L_0x00a9:
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x022a }
        L_0x00ad:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x022a }
            if (r3 == 0) goto L_0x00c3
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x022a }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x022a }
            boolean r4 = com.google.android.play.core.splitinstall.zzs.zzf(r3)     // Catch:{ all -> 0x022a }
            if (r4 != 0) goto L_0x00ad
            r1.add(r3)     // Catch:{ all -> 0x022a }
            goto L_0x00ad
        L_0x00c3:
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ all -> 0x022a }
            int r3 = r0.size()     // Catch:{ all -> 0x022a }
            r2.<init>(r3)     // Catch:{ all -> 0x022a }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x022a }
        L_0x00d0:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x022a }
            if (r3 == 0) goto L_0x00f8
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitcompat.zzs r3 = (com.google.android.play.core.splitcompat.zzs) r3     // Catch:{ all -> 0x022a }
            java.lang.String r4 = r3.zzb()     // Catch:{ all -> 0x022a }
            boolean r4 = com.google.android.play.core.splitinstall.zzs.zze(r4)     // Catch:{ all -> 0x022a }
            if (r4 != 0) goto L_0x00f4
            java.lang.String r4 = r3.zzb()     // Catch:{ all -> 0x022a }
            java.lang.String r4 = com.google.android.play.core.splitinstall.zzs.zzb(r4)     // Catch:{ all -> 0x022a }
            boolean r4 = r1.contains(r4)     // Catch:{ all -> 0x022a }
            if (r4 == 0) goto L_0x00d0
        L_0x00f4:
            r2.add(r3)     // Catch:{ all -> 0x022a }
            goto L_0x00d0
        L_0x00f8:
            com.google.android.play.core.splitcompat.zzm r0 = new com.google.android.play.core.splitcompat.zzm     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitcompat.zze r1 = r8.zzc     // Catch:{ all -> 0x022a }
            r0.<init>(r1)     // Catch:{ all -> 0x022a }
            com.google.android.play.core.internal.zzaz r1 = com.google.android.play.core.internal.zzba.zza()     // Catch:{ all -> 0x022a }
            java.lang.ClassLoader r3 = r9.getClassLoader()     // Catch:{ all -> 0x022a }
            if (r10 == 0) goto L_0x0111
            java.util.Set r0 = r0.zzc()     // Catch:{ all -> 0x022a }
            r1.zza(r3, r0)     // Catch:{ all -> 0x022a }
            goto L_0x012f
        L_0x0111:
            java.util.Iterator r4 = r2.iterator()     // Catch:{ all -> 0x022a }
        L_0x0115:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x022a }
            if (r5 == 0) goto L_0x012f
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitcompat.zzs r5 = (com.google.android.play.core.splitcompat.zzs) r5     // Catch:{ all -> 0x022a }
            java.util.Set r5 = r0.zzb(r5)     // Catch:{ all -> 0x022a }
            if (r5 != 0) goto L_0x012b
            r4.remove()     // Catch:{ all -> 0x022a }
            goto L_0x0115
        L_0x012b:
            r1.zza(r3, r5)     // Catch:{ all -> 0x022a }
            goto L_0x0115
        L_0x012f:
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x022a }
            r0.<init>()     // Catch:{ all -> 0x022a }
            java.util.Iterator r4 = r2.iterator()     // Catch:{ all -> 0x022a }
        L_0x0138:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x022a }
            if (r5 == 0) goto L_0x0193
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitcompat.zzs r5 = (com.google.android.play.core.splitcompat.zzs) r5     // Catch:{ all -> 0x022a }
            java.util.zip.ZipFile r6 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x018b }
            java.io.File r7 = r5.zza()     // Catch:{ IOException -> 0x018b }
            r6.<init>(r7)     // Catch:{ IOException -> 0x018b }
            java.lang.String r7 = "classes.dex"
            java.util.zip.ZipEntry r7 = r6.getEntry(r7)     // Catch:{ IOException -> 0x0189 }
            r6.close()     // Catch:{ IOException -> 0x0189 }
            if (r7 == 0) goto L_0x0181
            com.google.android.play.core.splitcompat.zze r6 = r8.zzc     // Catch:{ all -> 0x022a }
            java.lang.String r7 = r5.zzb()     // Catch:{ all -> 0x022a }
            java.io.File r6 = r6.zza(r7)     // Catch:{ all -> 0x022a }
            java.io.File r7 = r5.zza()     // Catch:{ all -> 0x022a }
            boolean r6 = r1.zzb(r3, r6, r7, r10)     // Catch:{ all -> 0x022a }
            if (r6 == 0) goto L_0x016d
            goto L_0x0181
        L_0x016d:
            java.lang.String r6 = "SplitCompat"
            java.lang.String r7 = "split was not installed "
            java.io.File r5 = r5.zza()     // Catch:{ all -> 0x022a }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x022a }
            java.lang.String r5 = r7.concat(r5)     // Catch:{ all -> 0x022a }
            android.util.Log.w(r6, r5)     // Catch:{ all -> 0x022a }
            goto L_0x0138
        L_0x0181:
            java.io.File r5 = r5.zza()     // Catch:{ all -> 0x022a }
            r0.add(r5)     // Catch:{ all -> 0x022a }
            goto L_0x0138
        L_0x0189:
            r9 = move-exception
            goto L_0x018d
        L_0x018b:
            r9 = move-exception
            r6 = 0
        L_0x018d:
            if (r6 == 0) goto L_0x0192
            r6.close()     // Catch:{ IOException -> 0x0192 }
        L_0x0192:
            throw r9     // Catch:{ all -> 0x022a }
        L_0x0193:
            com.google.android.play.core.splitcompat.zza r10 = r8.zzf     // Catch:{ all -> 0x022a }
            r10.zza(r9, r0)     // Catch:{ all -> 0x022a }
            java.util.HashSet r9 = new java.util.HashSet     // Catch:{ all -> 0x022a }
            r9.<init>()     // Catch:{ all -> 0x022a }
            java.util.Iterator r10 = r2.iterator()     // Catch:{ all -> 0x022a }
        L_0x01a1:
            boolean r1 = r10.hasNext()     // Catch:{ all -> 0x022a }
            if (r1 == 0) goto L_0x020a
            java.lang.Object r1 = r10.next()     // Catch:{ all -> 0x022a }
            com.google.android.play.core.splitcompat.zzs r1 = (com.google.android.play.core.splitcompat.zzs) r1     // Catch:{ all -> 0x022a }
            java.io.File r2 = r1.zza()     // Catch:{ all -> 0x022a }
            boolean r2 = r0.contains(r2)     // Catch:{ all -> 0x022a }
            if (r2 == 0) goto L_0x01e4
            java.lang.String r2 = r1.zzb()     // Catch:{ all -> 0x022a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x022a }
            int r4 = r2.length()     // Catch:{ all -> 0x022a }
            int r4 = r4 + 30
            r3.<init>(r4)     // Catch:{ all -> 0x022a }
            java.lang.String r4 = "Split '"
            r3.append(r4)     // Catch:{ all -> 0x022a }
            r3.append(r2)     // Catch:{ all -> 0x022a }
            java.lang.String r2 = "' installation emulated"
            r3.append(r2)     // Catch:{ all -> 0x022a }
            java.lang.String r2 = "SplitCompat"
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x022a }
            android.util.Log.d(r2, r3)     // Catch:{ all -> 0x022a }
            java.lang.String r1 = r1.zzb()     // Catch:{ all -> 0x022a }
            r9.add(r1)     // Catch:{ all -> 0x022a }
            goto L_0x01a1
        L_0x01e4:
            java.lang.String r1 = r1.zzb()     // Catch:{ all -> 0x022a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x022a }
            int r3 = r1.length()     // Catch:{ all -> 0x022a }
            int r3 = r3 + 35
            r2.<init>(r3)     // Catch:{ all -> 0x022a }
            java.lang.String r3 = "Split '"
            r2.append(r3)     // Catch:{ all -> 0x022a }
            r2.append(r1)     // Catch:{ all -> 0x022a }
            java.lang.String r1 = "' installation not emulated."
            r2.append(r1)     // Catch:{ all -> 0x022a }
            java.lang.String r1 = "SplitCompat"
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x022a }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x022a }
            goto L_0x01a1
        L_0x020a:
            java.util.Set r10 = r8.zze     // Catch:{ all -> 0x022a }
            monitor-enter(r10)     // Catch:{ all -> 0x022a }
            java.util.Set r0 = r8.zze     // Catch:{ all -> 0x0215 }
            r0.addAll(r9)     // Catch:{ all -> 0x0215 }
            monitor-exit(r10)     // Catch:{ all -> 0x0215 }
            monitor-exit(r8)
            return
        L_0x0215:
            r9 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0215 }
            throw r9     // Catch:{ all -> 0x022a }
        L_0x0218:
            r9 = move-exception
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x022a }
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x022a }
            r2[r1] = r0     // Catch:{ all -> 0x022a }
            java.lang.String r0 = "Cannot load data for application '%s'"
            java.lang.String r0 = java.lang.String.format(r0, r2)     // Catch:{ all -> 0x022a }
            r10.<init>(r0, r9)     // Catch:{ all -> 0x022a }
            throw r10     // Catch:{ all -> 0x022a }
        L_0x022a:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitcompat.SplitCompat.zzh(android.content.Context, boolean):void");
    }

    private static boolean zzi(Context context, boolean z) {
        if (zzj()) {
            return false;
        }
        AtomicReference atomicReference = zzb;
        boolean compareAndSet = atomicReference.compareAndSet((Object) null, new SplitCompat(context));
        SplitCompat splitCompat = (SplitCompat) atomicReference.get();
        if (compareAndSet) {
            zzo.INSTANCE.zzb(new zzaw(context, zzd.zza(), new zzay(context, splitCompat.zzc, new zzau()), splitCompat.zzc, new zzr(), (byte[]) null));
            zzr.zzb(new zzo(splitCompat));
            zzd.zza().execute(new zzn(context));
        }
        try {
            splitCompat.zzh(context, z);
            return true;
        } catch (Exception e) {
            Log.e("SplitCompat", "Error installing additional splits", e);
            return false;
        }
    }

    private static boolean zzj() {
        return Build.VERSION.SDK_INT < 21;
    }
}
