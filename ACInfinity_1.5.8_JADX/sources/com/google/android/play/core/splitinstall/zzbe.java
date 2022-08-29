package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzbe {
    private final Context zza;

    public zzbe(Context context) {
        this.zza = context;
    }

    private final SharedPreferences zze() {
        return this.zza.getSharedPreferences("playcore_split_install_internal", 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r1 = new java.util.HashSet();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Set zza() {
        /*
            r4 = this;
            java.lang.Class<com.google.android.play.core.splitinstall.zzbe> r0 = com.google.android.play.core.splitinstall.zzbe.class
            monitor-enter(r0)
            android.content.SharedPreferences r1 = r4.zze()     // Catch:{ Exception -> 0x001c }
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ Exception -> 0x001c }
            r2.<init>()     // Catch:{ Exception -> 0x001c }
            java.lang.String r3 = "modules_to_uninstall_if_emulated"
            java.util.Set r1 = r1.getStringSet(r3, r2)     // Catch:{ Exception -> 0x001c }
            if (r1 != 0) goto L_0x0021
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ Exception -> 0x001c }
            r1.<init>()     // Catch:{ Exception -> 0x001c }
            goto L_0x0021
        L_0x001a:
            r1 = move-exception
            goto L_0x0023
        L_0x001c:
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ all -> 0x001a }
            r1.<init>()     // Catch:{ all -> 0x001a }
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r1
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.zzbe.zza():java.util.Set");
    }

    public final void zzb() {
        synchronized (zzbe.class) {
            zze().edit().putStringSet("modules_to_uninstall_if_emulated", new HashSet()).apply();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:2|3|(3:6|7|4)|17|(2:9|10)|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(java.util.Collection r5) {
        /*
            r4 = this;
            java.lang.Class<com.google.android.play.core.splitinstall.zzbe> r0 = com.google.android.play.core.splitinstall.zzbe.class
            monitor-enter(r0)
            java.util.Set r1 = r4.zza()     // Catch:{ all -> 0x0038 }
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ all -> 0x0038 }
            r2.<init>(r1)     // Catch:{ all -> 0x0038 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0038 }
            r1 = 0
        L_0x0011:
            boolean r3 = r5.hasNext()     // Catch:{ all -> 0x0038 }
            if (r3 == 0) goto L_0x0023
            java.lang.Object r3 = r5.next()     // Catch:{ all -> 0x0038 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0038 }
            boolean r3 = r2.add(r3)     // Catch:{ all -> 0x0038 }
            r1 = r1 | r3
            goto L_0x0011
        L_0x0023:
            if (r1 == 0) goto L_0x0036
            android.content.SharedPreferences r5 = r4.zze()     // Catch:{ Exception -> 0x0036 }
            android.content.SharedPreferences$Editor r5 = r5.edit()     // Catch:{ Exception -> 0x0036 }
            java.lang.String r1 = "modules_to_uninstall_if_emulated"
            android.content.SharedPreferences$Editor r5 = r5.putStringSet(r1, r2)     // Catch:{ Exception -> 0x0036 }
            r5.apply()     // Catch:{ Exception -> 0x0036 }
        L_0x0036:
            monitor-exit(r0)     // Catch:{ all -> 0x0038 }
            return
        L_0x0038:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0038 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.zzbe.zzc(java.util.Collection):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:2|3|(4:6|(2:8|21)(2:9|22)|19|4)|20|(2:11|12)|13|14) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(java.util.Collection r7) {
        /*
            r6 = this;
            java.lang.Class<com.google.android.play.core.splitinstall.zzbe> r0 = com.google.android.play.core.splitinstall.zzbe.class
            monitor-enter(r0)
            java.util.Set r1 = r6.zza()     // Catch:{ all -> 0x003e }
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ all -> 0x003e }
            r2.<init>()     // Catch:{ all -> 0x003e }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x003e }
            r3 = 0
        L_0x0011:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x003e }
            if (r4 == 0) goto L_0x0029
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x003e }
            boolean r5 = r7.contains(r4)     // Catch:{ all -> 0x003e }
            if (r5 == 0) goto L_0x0025
            r3 = 1
            goto L_0x0011
        L_0x0025:
            r2.add(r4)     // Catch:{ all -> 0x003e }
            goto L_0x0011
        L_0x0029:
            if (r3 == 0) goto L_0x003c
            android.content.SharedPreferences r7 = r6.zze()     // Catch:{ Exception -> 0x003c }
            android.content.SharedPreferences$Editor r7 = r7.edit()     // Catch:{ Exception -> 0x003c }
            java.lang.String r1 = "modules_to_uninstall_if_emulated"
            android.content.SharedPreferences$Editor r7 = r7.putStringSet(r1, r2)     // Catch:{ Exception -> 0x003c }
            r7.apply()     // Catch:{ Exception -> 0x003c }
        L_0x003c:
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            return
        L_0x003e:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.zzbe.zzd(java.util.Collection):void");
    }
}
