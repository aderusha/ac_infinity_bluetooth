package com.google.android.play.core.splitinstall;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzaa implements SplitInstallManager {
    private final zzbc zza;
    /* access modifiers changed from: private */
    public final zzx zzb;
    private final zzs zzc;
    private final zzbe zzd;
    private final Handler zze = new Handler(Looper.getMainLooper());

    zzaa(zzbc zzbc, zzx zzx, zzs zzs, zzbe zzbe) {
        this.zza = zzbc;
        this.zzb = zzx;
        this.zzc = zzs;
        this.zzd = zzbe;
    }

    /* access modifiers changed from: private */
    public static List zze(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Locale locale = (Locale) it.next();
            if (Build.VERSION.SDK_INT >= 21) {
                arrayList.add(locale.toLanguageTag());
            }
        }
        return arrayList;
    }

    public final Task<Void> cancelInstall(int i) {
        return this.zza.zzc(i);
    }

    public final Task<Void> deferredInstall(List<String> list) {
        return this.zza.zzd(list);
    }

    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        if (Build.VERSION.SDK_INT < 21) {
            return Tasks.zza(new SplitInstallException(-5));
        }
        return this.zza.zze(zze(list));
    }

    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        if (Build.VERSION.SDK_INT < 21) {
            return Tasks.zza(new SplitInstallException(-5));
        }
        return this.zza.zzf(zze(list));
    }

    public final Task<Void> deferredUninstall(List<String> list) {
        this.zzd.zzc(list);
        return this.zza.zzg(list);
    }

    public final Set<String> getInstalledLanguages() {
        Set<String> zzd2 = this.zzc.zzd();
        return zzd2 == null ? Collections.emptySet() : zzd2;
    }

    public final Set<String> getInstalledModules() {
        return this.zzc.zzc();
    }

    public final Task<SplitInstallSessionState> getSessionState(int i) {
        return this.zza.zzh(i);
    }

    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        return this.zza.zzi();
    }

    public final synchronized void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzb.zzf(splitInstallStateUpdatedListener);
    }

    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int i) throws IntentSender.SendIntentException {
        return startConfirmationDialogForResult(splitInstallSessionState, (IntentSenderForResultStarter) new zzz(this, activity), i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004a, code lost:
        if (r2.containsAll(r3) != false) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.play.core.tasks.Task<java.lang.Integer> startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest r6) {
        /*
            r5 = this;
            java.util.List r0 = r6.getLanguages()
            boolean r0 = r0.isEmpty()
            r1 = 21
            if (r0 != 0) goto L_0x001c
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r1) goto L_0x0011
            goto L_0x001c
        L_0x0011:
            com.google.android.play.core.splitinstall.SplitInstallException r6 = new com.google.android.play.core.splitinstall.SplitInstallException
            r0 = -5
            r6.<init>(r0)
            com.google.android.play.core.tasks.Task r6 = com.google.android.play.core.tasks.Tasks.zza(r6)
            return r6
        L_0x001c:
            java.util.List r0 = r6.getLanguages()
            com.google.android.play.core.splitinstall.zzs r2 = r5.zzc
            java.util.Set r2 = r2.zzd()
            if (r2 != 0) goto L_0x0029
            goto L_0x004c
        L_0x0029:
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x0032:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x0046
            java.lang.Object r4 = r0.next()
            java.util.Locale r4 = (java.util.Locale) r4
            java.lang.String r4 = r4.getLanguage()
            r3.add(r4)
            goto L_0x0032
        L_0x0046:
            boolean r0 = r2.containsAll(r3)
            if (r0 == 0) goto L_0x0085
        L_0x004c:
            java.util.List r0 = r6.getModuleNames()
            com.google.android.play.core.splitinstall.zzs r2 = r5.zzc
            java.util.Set r2 = r2.zzc()
            boolean r0 = r2.containsAll(r0)
            if (r0 == 0) goto L_0x0085
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r1) goto L_0x0071
            java.util.List r0 = r6.getModuleNames()
            com.google.android.play.core.splitinstall.zzbe r1 = r5.zzd
            java.util.Set r1 = r1.zza()
            boolean r0 = java.util.Collections.disjoint(r0, r1)
            if (r0 != 0) goto L_0x0071
            goto L_0x0085
        L_0x0071:
            android.os.Handler r0 = r5.zze
            com.google.android.play.core.splitinstall.zzy r1 = new com.google.android.play.core.splitinstall.zzy
            r1.<init>(r5, r6)
            r0.post(r1)
            r6 = 0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            com.google.android.play.core.tasks.Task r6 = com.google.android.play.core.tasks.Tasks.zzb(r6)
            return r6
        L_0x0085:
            com.google.android.play.core.splitinstall.zzbe r0 = r5.zzd
            java.util.List r1 = r6.getModuleNames()
            r0.zzd(r1)
            com.google.android.play.core.splitinstall.zzbc r0 = r5.zza
            java.util.List r1 = r6.getModuleNames()
            java.util.List r6 = r6.getLanguages()
            java.util.List r6 = zze(r6)
            com.google.android.play.core.tasks.Task r6 = r0.zzj(r1, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.zzaa.startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest):com.google.android.play.core.tasks.Task");
    }

    public final synchronized void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzb.zzh(splitInstallStateUpdatedListener);
    }

    public final synchronized void zza(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzb.zzk(splitInstallStateUpdatedListener);
    }

    public final synchronized void zzb(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzb.zzl(splitInstallStateUpdatedListener);
    }

    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int i) throws IntentSender.SendIntentException {
        if (splitInstallSessionState.status() != 8 || splitInstallSessionState.resolutionIntent() == null) {
            return false;
        }
        intentSenderForResultStarter.startIntentSenderForResult(splitInstallSessionState.resolutionIntent().getIntentSender(), i, (Intent) null, 0, 0, 0, (Bundle) null);
        return true;
    }
}
