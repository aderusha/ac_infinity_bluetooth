package com.google.android.play.core.splitinstall.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.internal.zzaf;
import com.google.android.play.core.internal.zzcf;
import com.google.android.play.core.internal.zzcj;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.splitcompat.zzd;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener;
import com.google.android.play.core.splitinstall.zzg;
import com.google.android.play.core.splitinstall.zzk;
import com.google.android.play.core.splitinstall.zzo;
import com.google.android.play.core.splitinstall.zzs;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class FakeSplitInstallManager implements SplitInstallManager {
    public static final /* synthetic */ int zza = 0;
    private static final long zzb = TimeUnit.SECONDS.toMillis(1);
    private final Handler zzc;
    private final Context zzd;
    private final zzs zze;
    private final zzco zzf;
    private final zzcf zzg;
    private final zzaf zzh;
    private final zzaf zzi;
    private final Executor zzj;
    private final zzg zzk;
    private final File zzl;
    private final AtomicReference zzm;
    private final Set zzn;
    private final Set zzo;
    private final AtomicBoolean zzp;
    private final zzd zzq;

    @Deprecated
    public FakeSplitInstallManager(Context context, File file) {
        this(context, file, new zzs(context, context.getPackageName()), zzj.zza);
    }

    private final zzk zzk() {
        zzk zza2 = this.zze.zza();
        if (zza2 != null) {
            return zza2;
        }
        throw new IllegalStateException("Language information could not be found. Make sure you are using the target application context, not the tests context, and the app is built as a bundle.");
    }

    private final SplitInstallSessionState zzl() {
        return (SplitInstallSessionState) this.zzm.get();
    }

    private final synchronized SplitInstallSessionState zzm(zzp zzp2) {
        SplitInstallSessionState zzl2 = zzl();
        SplitInstallSessionState zza2 = zzp2.zza(zzl2);
        if (this.zzm.compareAndSet(zzl2, zza2)) {
            return zza2;
        }
        return null;
    }

    private final Task zzn(int i) {
        zzm(new zzg(i));
        return Tasks.zza(new SplitInstallException(i));
    }

    private static String zzo(String str) {
        return str.split("\\.config\\.", 2)[0];
    }

    /* access modifiers changed from: private */
    public final void zzp(List list, List list2, List list3, long j, boolean z) {
        List list4 = list;
        this.zzk.zza().zzd(list, new zzo(this, list2, list3, j, z, list));
    }

    private final void zzq(SplitInstallSessionState splitInstallSessionState) {
        this.zzc.post(new zzm(this, splitInstallSessionState));
    }

    /* access modifiers changed from: private */
    public final void zzr(List list, List list2, long j) {
        this.zzn.addAll(list);
        this.zzo.addAll(list2);
        Long valueOf = Long.valueOf(j);
        zzs(5, 0, valueOf, valueOf, (List) null, (Integer) null, (List) null);
    }

    /* access modifiers changed from: private */
    public final boolean zzs(int i, int i2, Long l, Long l2, List list, Integer num, List list2) {
        SplitInstallSessionState zzm2 = zzm(new zzi(num, i, i2, l, l2, list, list2));
        if (zzm2 == null) {
            return false;
        }
        zzq(zzm2);
        return true;
    }

    public final Task<Void> cancelInstall(int i) {
        try {
            SplitInstallSessionState zzm2 = zzm(new zzf(i));
            if (zzm2 != null) {
                zzq(zzm2);
            }
            return Tasks.zzb((Object) null);
        } catch (SplitInstallException e) {
            return Tasks.zza(e);
        }
    }

    public final Task<Void> deferredInstall(List<String> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    public final Task<Void> deferredUninstall(List<String> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    public final Set<String> getInstalledLanguages() {
        HashSet hashSet = new HashSet();
        if (this.zze.zzd() != null) {
            hashSet.addAll(this.zze.zzd());
        }
        hashSet.addAll(this.zzo);
        return hashSet;
    }

    public final Set<String> getInstalledModules() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.zze.zzc());
        hashSet.addAll(this.zzn);
        return hashSet;
    }

    public final Task<SplitInstallSessionState> getSessionState(int i) {
        SplitInstallSessionState zzl2 = zzl();
        if (zzl2 == null || zzl2.sessionId() != i) {
            return Tasks.zza(new SplitInstallException(-4));
        }
        return Tasks.zzb(zzl2);
    }

    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        SplitInstallSessionState zzl2 = zzl();
        return Tasks.zzb(zzl2 != null ? Collections.singletonList(zzl2) : Collections.emptyList());
    }

    public final void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzi.zza(splitInstallStateUpdatedListener);
    }

    public void setShouldNetworkError(boolean z) {
        this.zzp.set(z);
    }

    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int i) throws IntentSender.SendIntentException {
        return false;
    }

    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int i) throws IntentSender.SendIntentException {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0130, code lost:
        if (r0.contains(r7) == false) goto L_0x0139;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.play.core.tasks.Task<java.lang.Integer> startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest r22) {
        /*
            r21 = this;
            r9 = r21
            com.google.android.play.core.splitinstall.testing.zzh r0 = new com.google.android.play.core.splitinstall.testing.zzh     // Catch:{ SplitInstallException -> 0x0257 }
            r1 = r22
            r0.<init>(r1)     // Catch:{ SplitInstallException -> 0x0257 }
            com.google.android.play.core.splitinstall.SplitInstallSessionState r0 = r9.zzm(r0)     // Catch:{ SplitInstallException -> 0x0257 }
            if (r0 == 0) goto L_0x0250
            int r0 = r0.sessionId()     // Catch:{ SplitInstallException -> 0x0257 }
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.List r2 = r22.getLanguages()
            java.util.Iterator r2 = r2.iterator()
        L_0x0020:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0034
            java.lang.Object r3 = r2.next()
            java.util.Locale r3 = (java.util.Locale) r3
            java.lang.String r3 = r3.getLanguage()
            r10.add(r3)
            goto L_0x0020
        L_0x0034:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.io.File r3 = r9.zzl
            com.google.android.play.core.splitinstall.testing.zzk r4 = com.google.android.play.core.splitinstall.testing.zzk.zza
            java.io.File[] r3 = r3.listFiles(r4)
            java.lang.String r4 = "FakeSplitInstallManager"
            if (r3 != 0) goto L_0x0055
            java.lang.String r0 = "Specified splits directory does not exist."
            android.util.Log.w(r4, r0)
            r0 = -5
            com.google.android.play.core.tasks.Task r0 = r9.zzn(r0)
            return r0
        L_0x0055:
            int r5 = r3.length
            r12 = 0
            r13 = 0
        L_0x0059:
            if (r12 >= r5) goto L_0x0199
            r6 = r3[r12]
            java.lang.String r7 = com.google.android.play.core.internal.zzcj.zza(r6)
            java.lang.String r8 = zzo(r7)
            r2.add(r7)
            java.util.List r15 = r22.getModuleNames()
            boolean r8 = r15.contains(r8)
            if (r8 == 0) goto L_0x0133
            java.lang.String r8 = zzo(r7)
            java.util.HashSet r15 = new java.util.HashSet
            com.google.android.play.core.internal.zzcf r1 = r9.zzg
            java.util.List r1 = r1.zza()
            r15.<init>(r1)
            com.google.android.play.core.splitinstall.zzk r1 = r21.zzk()
            r18 = r3
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]
            r16 = 0
            r3[r16] = r8
            java.util.List r3 = java.util.Arrays.asList(r3)
            java.util.Map r1 = r1.zza(r3)
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.Collection r8 = r1.values()
            java.util.Iterator r8 = r8.iterator()
        L_0x00a3:
            boolean r17 = r8.hasNext()
            if (r17 == 0) goto L_0x00b9
            java.lang.Object r17 = r8.next()
            r19 = r5
            r5 = r17
            java.util.Set r5 = (java.util.Set) r5
            r3.addAll(r5)
            r5 = r19
            goto L_0x00a3
        L_0x00b9:
            r19 = r5
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.Iterator r8 = r15.iterator()
        L_0x00c4:
            boolean r15 = r8.hasNext()
            if (r15 == 0) goto L_0x00ef
            java.lang.Object r15 = r8.next()
            java.lang.String r15 = (java.lang.String) r15
            r17 = r8
            java.lang.String r8 = "_"
            boolean r20 = r15.contains(r8)
            if (r20 == 0) goto L_0x00e5
            r20 = r0
            r0 = -1
            java.lang.String[] r0 = r15.split(r8, r0)
            r8 = 0
            r15 = r0[r8]
            goto L_0x00e7
        L_0x00e5:
            r20 = r0
        L_0x00e7:
            r5.add(r15)
            r8 = r17
            r0 = r20
            goto L_0x00c4
        L_0x00ef:
            r20 = r0
            java.util.Set r0 = r9.zzo
            r5.addAll(r0)
            r5.addAll(r10)
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0106:
            boolean r8 = r1.hasNext()
            if (r8 == 0) goto L_0x0126
            java.lang.Object r8 = r1.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            java.lang.Object r15 = r8.getKey()
            boolean r15 = r5.contains(r15)
            if (r15 == 0) goto L_0x0106
            java.lang.Object r8 = r8.getValue()
            java.util.Collection r8 = (java.util.Collection) r8
            r0.addAll(r8)
            goto L_0x0106
        L_0x0126:
            boolean r1 = r3.contains(r7)
            if (r1 == 0) goto L_0x0185
            boolean r0 = r0.contains(r7)
            if (r0 == 0) goto L_0x0139
            goto L_0x0185
        L_0x0133:
            r20 = r0
            r18 = r3
            r19 = r5
        L_0x0139:
            java.util.List r0 = r22.getLanguages()
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Set r3 = r9.zzn
            r1.<init>(r3)
            java.lang.String r3 = ""
            java.lang.String r5 = "base"
            java.lang.String[] r3 = new java.lang.String[]{r3, r5}
            java.util.List r3 = java.util.Arrays.asList(r3)
            r1.addAll(r3)
            com.google.android.play.core.splitinstall.zzk r3 = r21.zzk()
            java.util.Map r1 = r3.zza(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x015f:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x018d
            java.lang.Object r3 = r0.next()
            java.util.Locale r3 = (java.util.Locale) r3
            java.lang.String r5 = r3.getLanguage()
            boolean r5 = r1.containsKey(r5)
            if (r5 == 0) goto L_0x015f
            java.lang.String r3 = r3.getLanguage()
            java.lang.Object r3 = r1.get(r3)
            java.util.Set r3 = (java.util.Set) r3
            boolean r3 = r3.contains(r7)
            if (r3 == 0) goto L_0x015f
        L_0x0185:
            long r0 = r6.length()
            long r13 = r13 + r0
            r11.add(r6)
        L_0x018d:
            int r12 = r12 + 1
            r1 = r22
            r3 = r18
            r5 = r19
            r0 = r20
            goto L_0x0059
        L_0x0199:
            r20 = r0
            java.lang.String r0 = r2.toString()
            java.util.List r1 = r22.getModuleNames()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r6 = r0.length()
            int r6 = r6 + 22
            int r6 = r6 + r3
            r5.<init>(r6)
            java.lang.String r3 = "availableSplits "
            r5.append(r3)
            r5.append(r0)
            java.lang.String r0 = " want "
            r5.append(r0)
            r5.append(r1)
            java.lang.String r0 = r5.toString()
            android.util.Log.i(r4, r0)
            java.util.List r0 = r22.getModuleNames()
            int r0 = r0.size()
            r1 = 1
            if (r0 != r1) goto L_0x01fa
            com.google.android.play.core.internal.zzco r0 = r9.zzf
            java.lang.Object r0 = r0.zza()
            com.google.android.play.core.splitinstall.testing.zzt r0 = (com.google.android.play.core.splitinstall.testing.zzt) r0
            java.util.Map r0 = r0.zzb()
            java.util.List r1 = r22.getModuleNames()
            r3 = 0
            java.lang.Object r1 = r1.get(r3)
            java.lang.Object r0 = r0.get(r1)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 != 0) goto L_0x0206
        L_0x01fa:
            com.google.android.play.core.internal.zzco r0 = r9.zzf
            java.lang.Object r0 = r0.zza()
            com.google.android.play.core.splitinstall.testing.zzt r0 = (com.google.android.play.core.splitinstall.testing.zzt) r0
            java.lang.Integer r0 = r0.zza()
        L_0x0206:
            if (r0 == 0) goto L_0x0211
            int r0 = r0.intValue()
            com.google.android.play.core.tasks.Task r0 = r9.zzn(r0)
            return r0
        L_0x0211:
            java.util.HashSet r0 = new java.util.HashSet
            java.util.List r1 = r22.getModuleNames()
            r0.<init>(r1)
            boolean r0 = r2.containsAll(r0)
            if (r0 != 0) goto L_0x0226
            r0 = -2
            com.google.android.play.core.tasks.Task r0 = r9.zzn(r0)
            return r0
        L_0x0226:
            r0 = 0
            java.lang.Long r4 = java.lang.Long.valueOf(r0)
            java.lang.Long r5 = java.lang.Long.valueOf(r13)
            java.util.List r6 = r22.getModuleNames()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r20)
            r2 = 1
            r3 = 0
            r1 = r21
            r7 = r0
            r8 = r10
            r1.zzs(r2, r3, r4, r5, r6, r7, r8)
            java.util.concurrent.Executor r1 = r9.zzj
            com.google.android.play.core.splitinstall.testing.zzn r2 = new com.google.android.play.core.splitinstall.testing.zzn
            r2.<init>(r9, r11, r10)
            r1.execute(r2)
            com.google.android.play.core.tasks.Task r0 = com.google.android.play.core.tasks.Tasks.zzb(r0)
            return r0
        L_0x0250:
            r0 = -100
            com.google.android.play.core.tasks.Task r0 = r9.zzn(r0)     // Catch:{ SplitInstallException -> 0x0257 }
            return r0
        L_0x0257:
            r0 = move-exception
            int r0 = r0.getErrorCode()
            com.google.android.play.core.tasks.Task r0 = r9.zzn(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager.startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest):com.google.android.play.core.tasks.Task");
    }

    public final void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzi.zzb(splitInstallStateUpdatedListener);
    }

    public final void zza(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzh.zza(splitInstallStateUpdatedListener);
    }

    public final void zzb(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzh.zzb(splitInstallStateUpdatedListener);
    }

    /* access modifiers changed from: package-private */
    public final File zzc() {
        return this.zzl;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(long j, List list, List list2, List list3) {
        long j2 = j;
        long j3 = j2 / 3;
        long j4 = 0;
        int i = 0;
        while (i < 3) {
            j4 = Math.min(j2, j4 + j3);
            zzs(2, 0, Long.valueOf(j4), Long.valueOf(j), (List) null, (Integer) null, (List) null);
            SystemClock.sleep(zzb);
            SplitInstallSessionState zzl2 = zzl();
            if (zzl2.status() != 9 && zzl2.status() != 7 && zzl2.status() != 6) {
                i++;
            } else {
                return;
            }
        }
        this.zzj.execute(new zze(this, list, list2, list3, j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(SplitInstallSessionState splitInstallSessionState) {
        this.zzh.zzc(splitInstallSessionState);
        this.zzi.zzc(splitInstallSessionState);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(List list, List list2, List list3, long j) {
        if (this.zzp.get()) {
            zzs(6, -6, (Long) null, (Long) null, (List) null, (Integer) null, (List) null);
        } else if (this.zzk.zza() != null) {
            zzp(list, list2, list3, j, false);
        } else {
            zzr(list2, list3, j);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(List list, List list2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            String zza2 = zzcj.zza(file);
            Uri fromFile = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(fromFile, this.zzd.getContentResolver().getType(fromFile));
            intent.addFlags(1);
            intent.putExtra("module_name", zzo(zza2));
            intent.putExtra("split_id", zza2);
            arrayList.add(intent);
            arrayList2.add(zzo(zzcj.zza(file)));
        }
        SplitInstallSessionState zzl2 = zzl();
        if (zzl2 != null) {
            this.zzj.execute(new zzl(this, zzl2.totalBytesToDownload(), arrayList, arrayList2, list2));
        }
    }

    FakeSplitInstallManager(Context context, File file, zzs zzs, zzco zzco) {
        Executor zza2 = zzd.zza();
        zzcf zzcf = new zzcf(context);
        zzd zzd2 = zzd.zza;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzm = new AtomicReference();
        this.zzn = Collections.synchronizedSet(new HashSet());
        this.zzo = Collections.synchronizedSet(new HashSet());
        this.zzp = new AtomicBoolean(false);
        this.zzd = context;
        this.zzl = file;
        this.zze = zzs;
        this.zzf = zzco;
        this.zzj = zza2;
        this.zzg = zzcf;
        this.zzq = zzd2;
        this.zzi = new zzaf();
        this.zzh = new zzaf();
        this.zzk = zzo.INSTANCE;
    }
}
