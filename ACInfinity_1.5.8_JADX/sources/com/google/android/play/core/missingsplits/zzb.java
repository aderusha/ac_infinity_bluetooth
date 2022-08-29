package com.google.android.play.core.missingsplits;

import android.app.ActivityManager;
import android.content.Context;
import com.google.android.play.core.internal.zzag;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzb implements MissingSplitsManager {
    private static final zzag zza = new zzag("MissingSplitsManagerImpl");
    private final Context zzb;
    private final Runtime zzc;
    private final zza zzd;
    private final AtomicReference zze;

    zzb(Context context, Runtime runtime, zza zza2, AtomicReference atomicReference) {
        this.zzb = context;
        this.zzc = runtime;
        this.zzd = zza2;
        this.zze = atomicReference;
    }

    private final List zza() {
        List<ActivityManager.AppTask> appTasks = ((ActivityManager) this.zzb.getSystemService("activity")).getAppTasks();
        if (appTasks != null) {
            return appTasks;
        }
        return Collections.emptyList();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:29|30) */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        zza.zze("App '%s' is not found in PackageManager", r8.zzb.getPackageName());
        r1 = java.util.Collections.emptySet();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0071 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean disableAppIfMissingRequiredSplits() {
        /*
            r8 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            r2 = 0
            if (r0 < r1) goto L_0x01d5
            java.util.concurrent.atomic.AtomicReference r0 = r8.zze
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicReference r3 = r8.zze     // Catch:{ all -> 0x01d2 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x01d2 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x01d2 }
            r4 = 1
            if (r3 != 0) goto L_0x00b6
            java.util.concurrent.atomic.AtomicReference r3 = r8.zze     // Catch:{ all -> 0x01d2 }
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01d2 }
            if (r5 >= r1) goto L_0x001e
        L_0x001b:
            r1 = 0
            goto L_0x00af
        L_0x001e:
            android.content.Context r5 = r8.zzb     // Catch:{ all -> 0x01d2 }
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch:{ all -> 0x01d2 }
            android.content.Context r6 = r8.zzb     // Catch:{ NameNotFoundException -> 0x009c }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ NameNotFoundException -> 0x009c }
            r7 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo(r6, r7)     // Catch:{ NameNotFoundException -> 0x009c }
            if (r5 == 0) goto L_0x001b
            android.os.Bundle r6 = r5.metaData     // Catch:{ NameNotFoundException -> 0x009c }
            if (r6 == 0) goto L_0x001b
            java.lang.Boolean r6 = java.lang.Boolean.TRUE     // Catch:{ NameNotFoundException -> 0x009c }
            android.os.Bundle r5 = r5.metaData     // Catch:{ NameNotFoundException -> 0x009c }
            java.lang.String r7 = "com.android.vending.splits.required"
            java.lang.Object r5 = r5.get(r7)     // Catch:{ NameNotFoundException -> 0x009c }
            boolean r5 = r6.equals(r5)     // Catch:{ NameNotFoundException -> 0x009c }
            if (r5 == 0) goto L_0x001b
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01d2 }
            if (r5 >= r1) goto L_0x004f
            java.util.Set r1 = java.util.Collections.emptySet()     // Catch:{ all -> 0x01d2 }
            goto L_0x0086
        L_0x004f:
            android.content.Context r1 = r8.zzb     // Catch:{ NameNotFoundException -> 0x0071 }
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0071 }
            android.content.Context r5 = r8.zzb     // Catch:{ NameNotFoundException -> 0x0071 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ NameNotFoundException -> 0x0071 }
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r5, r2)     // Catch:{ NameNotFoundException -> 0x0071 }
            java.util.HashSet r5 = new java.util.HashSet     // Catch:{ NameNotFoundException -> 0x0071 }
            r5.<init>()     // Catch:{ NameNotFoundException -> 0x0071 }
            if (r1 == 0) goto L_0x006f
            java.lang.String[] r6 = r1.splitNames     // Catch:{ NameNotFoundException -> 0x0071 }
            if (r6 == 0) goto L_0x006f
            java.lang.String[] r1 = r1.splitNames     // Catch:{ NameNotFoundException -> 0x0071 }
            java.util.Collections.addAll(r5, r1)     // Catch:{ NameNotFoundException -> 0x0071 }
        L_0x006f:
            r1 = r5
            goto L_0x0086
        L_0x0071:
            com.google.android.play.core.internal.zzag r1 = zza     // Catch:{ all -> 0x01d2 }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x01d2 }
            android.content.Context r6 = r8.zzb     // Catch:{ all -> 0x01d2 }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ all -> 0x01d2 }
            r5[r2] = r6     // Catch:{ all -> 0x01d2 }
            java.lang.String r6 = "App '%s' is not found in PackageManager"
            r1.zze(r6, r5)     // Catch:{ all -> 0x01d2 }
            java.util.Set r1 = java.util.Collections.emptySet()     // Catch:{ all -> 0x01d2 }
        L_0x0086:
            boolean r5 = r1.isEmpty()     // Catch:{ all -> 0x01d2 }
            if (r5 != 0) goto L_0x009a
            int r5 = r1.size()     // Catch:{ all -> 0x01d2 }
            if (r5 != r4) goto L_0x001b
            java.lang.String r5 = ""
            boolean r1 = r1.contains(r5)     // Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x001b
        L_0x009a:
            r1 = 1
            goto L_0x00af
        L_0x009c:
            com.google.android.play.core.internal.zzag r1 = zza     // Catch:{ all -> 0x01d2 }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x01d2 }
            android.content.Context r6 = r8.zzb     // Catch:{ all -> 0x01d2 }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ all -> 0x01d2 }
            r5[r2] = r6     // Catch:{ all -> 0x01d2 }
            java.lang.String r6 = "App '%s' is not found in the PackageManager"
            r1.zze(r6, r5)     // Catch:{ all -> 0x01d2 }
            goto L_0x001b
        L_0x00af:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x01d2 }
            r3.set(r1)     // Catch:{ all -> 0x01d2 }
        L_0x00b6:
            java.util.concurrent.atomic.AtomicReference r1 = r8.zze     // Catch:{ all -> 0x01d2 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x01d2 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x01d2 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x01d2 }
            monitor-exit(r0)     // Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x01bf
            java.util.List r0 = r8.zza()
            java.util.Iterator r0 = r0.iterator()
        L_0x00cd:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x010f
            java.lang.Object r1 = r0.next()
            android.app.ActivityManager$AppTask r1 = (android.app.ActivityManager.AppTask) r1
            android.app.ActivityManager$RecentTaskInfo r3 = r1.getTaskInfo()
            if (r3 == 0) goto L_0x00cd
            android.app.ActivityManager$RecentTaskInfo r3 = r1.getTaskInfo()
            android.content.Intent r3 = r3.baseIntent
            if (r3 == 0) goto L_0x00cd
            android.app.ActivityManager$RecentTaskInfo r3 = r1.getTaskInfo()
            android.content.Intent r3 = r3.baseIntent
            android.content.ComponentName r3 = r3.getComponent()
            if (r3 == 0) goto L_0x00cd
            android.app.ActivityManager$RecentTaskInfo r1 = r1.getTaskInfo()
            android.content.Intent r1 = r1.baseIntent
            android.content.ComponentName r1 = r1.getComponent()
            java.lang.String r1 = r1.getClassName()
            java.lang.Class<com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity> r3 = com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity.class
            java.lang.String r3 = r3.getName()
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x00cd
            goto L_0x01be
        L_0x010f:
            java.util.List r0 = r8.zza()
            java.util.Iterator r0 = r0.iterator()
        L_0x0117:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0173
            java.lang.Object r1 = r0.next()
            android.app.ActivityManager$AppTask r1 = (android.app.ActivityManager.AppTask) r1
            android.app.ActivityManager$RecentTaskInfo r1 = r1.getTaskInfo()
            if (r1 == 0) goto L_0x0117
            android.content.Intent r3 = r1.baseIntent
            if (r3 == 0) goto L_0x0117
            android.content.Intent r3 = r1.baseIntent
            android.content.ComponentName r3 = r3.getComponent()
            if (r3 == 0) goto L_0x0117
            android.content.Intent r1 = r1.baseIntent
            android.content.ComponentName r1 = r1.getComponent()
            java.lang.String r3 = r1.getClassName()
            java.lang.Class r1 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x0159 }
        L_0x0143:
            if (r1 == 0) goto L_0x0117
            java.lang.Class<android.app.Activity> r3 = android.app.Activity.class
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x014f
        L_0x014d:
            r0 = 1
            goto L_0x0174
        L_0x014f:
            java.lang.Class r3 = r1.getSuperclass()
            if (r3 == r1) goto L_0x0157
            r1 = r3
            goto L_0x0143
        L_0x0157:
            r1 = 0
            goto L_0x0143
        L_0x0159:
            com.google.android.play.core.internal.zzag r5 = zza
            java.lang.Object[] r6 = new java.lang.Object[r4]
            r6[r2] = r3
            java.lang.String r3 = "ClassNotFoundException when scanning class hierarchy of '%s'"
            r5.zze(r3, r6)
            android.content.Context r3 = r8.zzb     // Catch:{ NameNotFoundException -> 0x0171 }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0171 }
            android.content.pm.ActivityInfo r1 = r3.getActivityInfo(r1, r2)     // Catch:{ NameNotFoundException -> 0x0171 }
            if (r1 == 0) goto L_0x0117
            goto L_0x014d
        L_0x0171:
            goto L_0x0117
        L_0x0173:
            r0 = 0
        L_0x0174:
            com.google.android.play.core.missingsplits.zza r1 = r8.zzd
            r1.zza()
            java.util.List r1 = r8.zza()
            java.util.Iterator r1 = r1.iterator()
        L_0x0181:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0191
            java.lang.Object r3 = r1.next()
            android.app.ActivityManager$AppTask r3 = (android.app.ActivityManager.AppTask) r3
            r3.finishAndRemoveTask()
            goto L_0x0181
        L_0x0191:
            if (r0 == 0) goto L_0x01b9
            android.content.Context r0 = r8.zzb
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            android.content.ComponentName r1 = new android.content.ComponentName
            android.content.Context r3 = r8.zzb
            java.lang.Class<com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity> r5 = com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity.class
            r1.<init>(r3, r5)
            r0.setComponentEnabledSetting(r1, r4, r4)
            android.content.Intent r0 = new android.content.Intent
            android.content.Context r1 = r8.zzb
            java.lang.Class<com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity> r3 = com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity.class
            r0.<init>(r1, r3)
            r1 = 884998144(0x34c00000, float:3.5762787E-7)
            android.content.Intent r0 = r0.addFlags(r1)
            android.content.Context r1 = r8.zzb
            r1.startActivity(r0)
        L_0x01b9:
            java.lang.Runtime r0 = r8.zzc
            r0.exit(r2)
        L_0x01be:
            return r4
        L_0x01bf:
            com.google.android.play.core.missingsplits.zza r0 = r8.zzd
            boolean r0 = r0.zzc()
            if (r0 == 0) goto L_0x01d1
            com.google.android.play.core.missingsplits.zza r0 = r8.zzd
            r0.zzb()
            java.lang.Runtime r0 = r8.zzc
            r0.exit(r2)
        L_0x01d1:
            return r2
        L_0x01d2:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x01d2 }
            throw r1
        L_0x01d5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.missingsplits.zzb.disableAppIfMissingRequiredSplits():boolean");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:27|28) */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        zza.zze("App '%s' is not found in PackageManager", r8.zzb.getPackageName());
        r2 = java.util.Collections.emptySet();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isMissingRequiredSplits() {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicReference r0 = r8.zze
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicReference r1 = r8.zze     // Catch:{ all -> 0x00bf }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x00bf }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x00bf }
            if (r1 != 0) goto L_0x00b1
            java.util.concurrent.atomic.AtomicReference r1 = r8.zze     // Catch:{ all -> 0x00bf }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00bf }
            r3 = 21
            r4 = 1
            r5 = 0
            if (r2 >= r3) goto L_0x001a
        L_0x0017:
            r4 = 0
            goto L_0x00aa
        L_0x001a:
            android.content.Context r2 = r8.zzb     // Catch:{ all -> 0x00bf }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ all -> 0x00bf }
            android.content.Context r6 = r8.zzb     // Catch:{ NameNotFoundException -> 0x0097 }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ NameNotFoundException -> 0x0097 }
            r7 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo(r6, r7)     // Catch:{ NameNotFoundException -> 0x0097 }
            if (r2 == 0) goto L_0x0017
            android.os.Bundle r6 = r2.metaData     // Catch:{ NameNotFoundException -> 0x0097 }
            if (r6 == 0) goto L_0x0017
            java.lang.Boolean r6 = java.lang.Boolean.TRUE     // Catch:{ NameNotFoundException -> 0x0097 }
            android.os.Bundle r2 = r2.metaData     // Catch:{ NameNotFoundException -> 0x0097 }
            java.lang.String r7 = "com.android.vending.splits.required"
            java.lang.Object r2 = r2.get(r7)     // Catch:{ NameNotFoundException -> 0x0097 }
            boolean r2 = r6.equals(r2)     // Catch:{ NameNotFoundException -> 0x0097 }
            if (r2 == 0) goto L_0x0017
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00bf }
            if (r2 >= r3) goto L_0x004b
            java.util.Set r2 = java.util.Collections.emptySet()     // Catch:{ all -> 0x00bf }
            goto L_0x0082
        L_0x004b:
            android.content.Context r2 = r8.zzb     // Catch:{ NameNotFoundException -> 0x006d }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ NameNotFoundException -> 0x006d }
            android.content.Context r3 = r8.zzb     // Catch:{ NameNotFoundException -> 0x006d }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ NameNotFoundException -> 0x006d }
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r5)     // Catch:{ NameNotFoundException -> 0x006d }
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ NameNotFoundException -> 0x006d }
            r3.<init>()     // Catch:{ NameNotFoundException -> 0x006d }
            if (r2 == 0) goto L_0x006b
            java.lang.String[] r6 = r2.splitNames     // Catch:{ NameNotFoundException -> 0x006d }
            if (r6 == 0) goto L_0x006b
            java.lang.String[] r2 = r2.splitNames     // Catch:{ NameNotFoundException -> 0x006d }
            java.util.Collections.addAll(r3, r2)     // Catch:{ NameNotFoundException -> 0x006d }
        L_0x006b:
            r2 = r3
            goto L_0x0082
        L_0x006d:
            com.google.android.play.core.internal.zzag r2 = zza     // Catch:{ all -> 0x00bf }
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x00bf }
            android.content.Context r6 = r8.zzb     // Catch:{ all -> 0x00bf }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ all -> 0x00bf }
            r3[r5] = r6     // Catch:{ all -> 0x00bf }
            java.lang.String r6 = "App '%s' is not found in PackageManager"
            r2.zze(r6, r3)     // Catch:{ all -> 0x00bf }
            java.util.Set r2 = java.util.Collections.emptySet()     // Catch:{ all -> 0x00bf }
        L_0x0082:
            boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x00bf }
            if (r3 != 0) goto L_0x00aa
            int r3 = r2.size()     // Catch:{ all -> 0x00bf }
            if (r3 != r4) goto L_0x0017
            java.lang.String r3 = ""
            boolean r2 = r2.contains(r3)     // Catch:{ all -> 0x00bf }
            if (r2 == 0) goto L_0x0017
            goto L_0x00aa
        L_0x0097:
            com.google.android.play.core.internal.zzag r2 = zza     // Catch:{ all -> 0x00bf }
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x00bf }
            android.content.Context r4 = r8.zzb     // Catch:{ all -> 0x00bf }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x00bf }
            r3[r5] = r4     // Catch:{ all -> 0x00bf }
            java.lang.String r4 = "App '%s' is not found in the PackageManager"
            r2.zze(r4, r3)     // Catch:{ all -> 0x00bf }
            goto L_0x0017
        L_0x00aa:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x00bf }
            r1.set(r2)     // Catch:{ all -> 0x00bf }
        L_0x00b1:
            java.util.concurrent.atomic.AtomicReference r1 = r8.zze     // Catch:{ all -> 0x00bf }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x00bf }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x00bf }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x00bf }
            monitor-exit(r0)     // Catch:{ all -> 0x00bf }
            return r1
        L_0x00bf:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00bf }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.missingsplits.zzb.isMissingRequiredSplits():boolean");
    }
}
