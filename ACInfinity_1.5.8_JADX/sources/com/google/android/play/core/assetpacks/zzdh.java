package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.zza;
import com.google.android.play.core.internal.zzag;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzdh {
    private static final zzag zza = new zzag("ExtractorTaskFinder");
    private final zzde zzb;
    private final zzbh zzc;
    private final zzbu zzd;
    private final zza zze;

    zzdh(zzde zzde, zzbh zzbh, zzbu zzbu, zza zza2) {
        this.zzb = zzde;
        this.zzc = zzbh;
        this.zzd = zzbu;
        this.zze = zza2;
    }

    private final boolean zzb(zzdb zzdb, zzdc zzdc) {
        zzbh zzbh = this.zzc;
        zzda zzda = zzdb.zzc;
        return new zzen(zzbh, zzda.zza, zzdb.zzb, zzda.zzb, zzdc.zza).zzm();
    }

    private static boolean zzc(zzdc zzdc) {
        int i = zzdc.zzf;
        return i == 1 || i == 2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: com.google.android.play.core.assetpacks.zzdw} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.play.core.assetpacks.zzdw} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: com.google.android.play.core.assetpacks.zzdt} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: com.google.android.play.core.assetpacks.zzdt} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: com.google.android.play.core.assetpacks.zzeq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: com.google.android.play.core.assetpacks.zzeq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v50, resolved type: com.google.android.play.core.assetpacks.zzei} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v51, resolved type: com.google.android.play.core.assetpacks.zzei} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v5, resolved type: com.google.android.play.core.assetpacks.zzeq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v6, resolved type: com.google.android.play.core.assetpacks.zzdt} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v17, resolved type: com.google.android.play.core.assetpacks.zzdw} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v18, resolved type: com.google.android.play.core.assetpacks.zzei} */
    /* JADX WARNING: type inference failed for: r0v47 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ae, code lost:
        if (r0 == null) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        zza.zza("Found final move task for session %s with pack %s.", java.lang.Integer.valueOf(r7.zza), r7.zzc.zza);
        r11 = r7.zza;
        r8 = r7.zzc;
        r10 = new com.google.android.play.core.assetpacks.zzdw(r11, r8.zza, r7.zzb, r8.zzb, r8.zzc);
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.play.core.assetpacks.zzdg zza() {
        /*
            r33 = this;
            r1 = r33
            com.google.android.play.core.assetpacks.zzde r0 = r1.zzb     // Catch:{ all -> 0x03f7 }
            r0.zzj()     // Catch:{ all -> 0x03f7 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x03f7 }
            r2.<init>()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzde r0 = r1.zzb     // Catch:{ all -> 0x03f7 }
            java.util.Map r0 = r0.zzg()     // Catch:{ all -> 0x03f7 }
            java.util.Collection r0 = r0.values()     // Catch:{ all -> 0x03f7 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x001a:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x03f7 }
            if (r3 == 0) goto L_0x0034
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdb r3 = (com.google.android.play.core.assetpacks.zzdb) r3     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r4 = r3.zzc     // Catch:{ all -> 0x03f7 }
            int r4 = r4.zzd     // Catch:{ all -> 0x03f7 }
            boolean r4 = com.google.android.play.core.assetpacks.zzbg.zzb(r4)     // Catch:{ all -> 0x03f7 }
            if (r4 == 0) goto L_0x001a
            r2.add(r3)     // Catch:{ all -> 0x03f7 }
            goto L_0x001a
        L_0x0034:
            boolean r0 = r2.isEmpty()     // Catch:{ all -> 0x03f7 }
            r3 = 0
            if (r0 == 0) goto L_0x0041
            com.google.android.play.core.assetpacks.zzde r0 = r1.zzb
        L_0x003d:
            r0.zzl()
            return r3
        L_0x0041:
            com.google.android.play.core.common.zza r0 = r1.zze     // Catch:{ all -> 0x03f7 }
            java.lang.String r4 = "assetOnlyUpdates"
            boolean r0 = r0.zza(r4)     // Catch:{ all -> 0x03f7 }
            r4 = 2
            r5 = 1
            r6 = 0
            if (r0 == 0) goto L_0x00b7
            com.google.android.play.core.assetpacks.zzbh r0 = r1.zzc     // Catch:{ all -> 0x03f7 }
            java.util.Map r0 = r0.zzt()     // Catch:{ all -> 0x03f7 }
            java.util.Iterator r7 = r2.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x0058:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x03f7 }
            if (r8 == 0) goto L_0x00ad
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdb r8 = (com.google.android.play.core.assetpacks.zzdb) r8     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r8.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r9.zza     // Catch:{ all -> 0x03f7 }
            java.lang.Object r9 = r0.get(r9)     // Catch:{ all -> 0x03f7 }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ all -> 0x03f7 }
            if (r9 == 0) goto L_0x0058
            com.google.android.play.core.assetpacks.zzda r10 = r8.zzc     // Catch:{ all -> 0x03f7 }
            long r10 = r10.zzb     // Catch:{ all -> 0x03f7 }
            long r12 = r9.longValue()     // Catch:{ all -> 0x03f7 }
            int r9 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r9 != 0) goto L_0x0058
            com.google.android.play.core.internal.zzag r0 = zza     // Catch:{ all -> 0x03f7 }
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ all -> 0x03f7 }
            int r9 = r8.zza     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03f7 }
            r7[r6] = r9     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r8.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r9.zza     // Catch:{ all -> 0x03f7 }
            r7[r5] = r9     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = "Found promote pack task for session %s with pack %s."
            r0.zza(r9, r7)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzei r0 = new com.google.android.play.core.assetpacks.zzei     // Catch:{ all -> 0x03f7 }
            int r11 = r8.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r7 = r8.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r12 = r7.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzbh r7 = r1.zzc     // Catch:{ all -> 0x03f7 }
            int r13 = r7.zza(r12)     // Catch:{ all -> 0x03f7 }
            int r14 = r8.zzb     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r7 = r8.zzc     // Catch:{ all -> 0x03f7 }
            long r7 = r7.zzb     // Catch:{ all -> 0x03f7 }
            r10 = r0
            r15 = r7
            r10.<init>(r11, r12, r13, r14, r15)     // Catch:{ all -> 0x03f7 }
            goto L_0x00ae
        L_0x00ad:
            r0 = r3
        L_0x00ae:
            if (r0 != 0) goto L_0x00b1
            goto L_0x00b7
        L_0x00b1:
            com.google.android.play.core.assetpacks.zzde r2 = r1.zzb
        L_0x00b3:
            r2.zzl()
            return r0
        L_0x00b7:
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x00bb:
            boolean r7 = r0.hasNext()     // Catch:{ all -> 0x03f7 }
            if (r7 == 0) goto L_0x012a
            java.lang.Object r7 = r0.next()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdb r7 = (com.google.android.play.core.assetpacks.zzdb) r7     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzbh r8 = r1.zzc     // Catch:{ IOException -> 0x010b }
            com.google.android.play.core.assetpacks.zzda r9 = r7.zzc     // Catch:{ IOException -> 0x010b }
            java.lang.String r10 = r9.zza     // Catch:{ IOException -> 0x010b }
            int r11 = r7.zzb     // Catch:{ IOException -> 0x010b }
            long r12 = r9.zzb     // Catch:{ IOException -> 0x010b }
            int r8 = r8.zzb(r10, r11, r12)     // Catch:{ IOException -> 0x010b }
            com.google.android.play.core.assetpacks.zzda r9 = r7.zzc     // Catch:{ IOException -> 0x010b }
            java.util.List r9 = r9.zzf     // Catch:{ IOException -> 0x010b }
            int r9 = r9.size()     // Catch:{ IOException -> 0x010b }
            if (r8 != r9) goto L_0x00bb
            com.google.android.play.core.internal.zzag r0 = zza     // Catch:{ all -> 0x03f7 }
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ all -> 0x03f7 }
            int r9 = r7.zza     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03f7 }
            r8[r6] = r9     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r9.zza     // Catch:{ all -> 0x03f7 }
            r8[r5] = r9     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = "Found final move task for session %s with pack %s."
            r0.zza(r9, r8)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdw r0 = new com.google.android.play.core.assetpacks.zzdw     // Catch:{ all -> 0x03f7 }
            int r11 = r7.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r8 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r12 = r8.zza     // Catch:{ all -> 0x03f7 }
            int r13 = r7.zzb     // Catch:{ all -> 0x03f7 }
            long r14 = r8.zzb     // Catch:{ all -> 0x03f7 }
            java.lang.String r7 = r8.zzc     // Catch:{ all -> 0x03f7 }
            r10 = r0
            r16 = r7
            r10.<init>(r11, r12, r13, r14, r16)     // Catch:{ all -> 0x03f7 }
            goto L_0x012b
        L_0x010b:
            r0 = move-exception
            com.google.android.play.core.assetpacks.zzck r2 = new com.google.android.play.core.assetpacks.zzck     // Catch:{ all -> 0x03f7 }
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x03f7 }
            int r4 = r7.zza     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x03f7 }
            r3[r6] = r4     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r4 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r4 = r4.zza     // Catch:{ all -> 0x03f7 }
            r3[r5] = r4     // Catch:{ all -> 0x03f7 }
            java.lang.String r4 = "Failed to check number of completed merges for session %s, pack %s"
            java.lang.String r3 = java.lang.String.format(r4, r3)     // Catch:{ all -> 0x03f7 }
            int r4 = r7.zza     // Catch:{ all -> 0x03f7 }
            r2.<init>(r3, r0, r4)     // Catch:{ all -> 0x03f7 }
            throw r2     // Catch:{ all -> 0x03f7 }
        L_0x012a:
            r0 = r3
        L_0x012b:
            if (r0 != 0) goto L_0x00b1
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x0131:
            boolean r7 = r0.hasNext()     // Catch:{ all -> 0x03f7 }
            r8 = 3
            if (r7 == 0) goto L_0x01b2
            java.lang.Object r7 = r0.next()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdb r7 = (com.google.android.play.core.assetpacks.zzdb) r7     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r7.zzc     // Catch:{ all -> 0x03f7 }
            int r10 = r9.zzd     // Catch:{ all -> 0x03f7 }
            boolean r10 = com.google.android.play.core.assetpacks.zzbg.zzb(r10)     // Catch:{ all -> 0x03f7 }
            if (r10 == 0) goto L_0x0131
            java.util.List r9 = r9.zzf     // Catch:{ all -> 0x03f7 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x014e:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x03f7 }
            if (r10 == 0) goto L_0x0131
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdc r10 = (com.google.android.play.core.assetpacks.zzdc) r10     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzbh r11 = r1.zzc     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r12 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r13 = r12.zza     // Catch:{ all -> 0x03f7 }
            int r14 = r7.zzb     // Catch:{ all -> 0x03f7 }
            long r3 = r12.zzb     // Catch:{ all -> 0x03f7 }
            java.lang.String r15 = r10.zza     // Catch:{ all -> 0x03f7 }
            r12 = r13
            r13 = r14
            r16 = r15
            r14 = r3
            java.io.File r3 = r11.zzq(r12, r13, r14, r16)     // Catch:{ all -> 0x03f7 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x03f7 }
            if (r3 == 0) goto L_0x01af
            com.google.android.play.core.internal.zzag r0 = zza     // Catch:{ all -> 0x03f7 }
            java.lang.Object[] r3 = new java.lang.Object[r8]     // Catch:{ all -> 0x03f7 }
            int r4 = r7.zza     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x03f7 }
            r3[r6] = r4     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r4 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r4 = r4.zza     // Catch:{ all -> 0x03f7 }
            r3[r5] = r4     // Catch:{ all -> 0x03f7 }
            java.lang.String r4 = r10.zza     // Catch:{ all -> 0x03f7 }
            r9 = 2
            r3[r9] = r4     // Catch:{ all -> 0x03f7 }
            java.lang.String r4 = "Found merge task for session %s with pack %s and slice %s."
            r0.zza(r4, r3)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdt r0 = new com.google.android.play.core.assetpacks.zzdt     // Catch:{ all -> 0x03f7 }
            int r3 = r7.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r4 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r4.zza     // Catch:{ all -> 0x03f7 }
            int r7 = r7.zzb     // Catch:{ all -> 0x03f7 }
            long r11 = r4.zzb     // Catch:{ all -> 0x03f7 }
            java.lang.String r4 = r10.zza     // Catch:{ all -> 0x03f7 }
            r18 = r0
            r19 = r3
            r20 = r9
            r21 = r7
            r22 = r11
            r24 = r4
            r18.<init>(r19, r20, r21, r22, r24)     // Catch:{ all -> 0x03f7 }
            goto L_0x01b3
        L_0x01af:
            r3 = 0
            r4 = 2
            goto L_0x014e
        L_0x01b2:
            r0 = 0
        L_0x01b3:
            if (r0 != 0) goto L_0x00b1
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x01b9:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x03f7 }
            if (r3 == 0) goto L_0x023f
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdb r3 = (com.google.android.play.core.assetpacks.zzdb) r3     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r4 = r3.zzc     // Catch:{ all -> 0x03f7 }
            int r7 = r4.zzd     // Catch:{ all -> 0x03f7 }
            boolean r7 = com.google.android.play.core.assetpacks.zzbg.zzb(r7)     // Catch:{ all -> 0x03f7 }
            if (r7 == 0) goto L_0x01b9
            java.util.List r4 = r4.zzf     // Catch:{ all -> 0x03f7 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x01d5:
            boolean r7 = r4.hasNext()     // Catch:{ all -> 0x03f7 }
            if (r7 == 0) goto L_0x01b9
            java.lang.Object r7 = r4.next()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdc r7 = (com.google.android.play.core.assetpacks.zzdc) r7     // Catch:{ all -> 0x03f7 }
            boolean r9 = r1.zzb(r3, r7)     // Catch:{ all -> 0x03f7 }
            if (r9 == 0) goto L_0x01d5
            com.google.android.play.core.assetpacks.zzbh r10 = r1.zzc     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r3.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r11 = r9.zza     // Catch:{ all -> 0x03f7 }
            int r12 = r3.zzb     // Catch:{ all -> 0x03f7 }
            long r13 = r9.zzb     // Catch:{ all -> 0x03f7 }
            java.lang.String r15 = r7.zza     // Catch:{ all -> 0x03f7 }
            java.io.File r9 = r10.zzp(r11, r12, r13, r15)     // Catch:{ all -> 0x03f7 }
            boolean r9 = r9.exists()     // Catch:{ all -> 0x03f7 }
            if (r9 == 0) goto L_0x01d5
            com.google.android.play.core.internal.zzag r0 = zza     // Catch:{ all -> 0x03f7 }
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ all -> 0x03f7 }
            int r9 = r3.zza     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03f7 }
            r4[r6] = r9     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r3.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r9.zza     // Catch:{ all -> 0x03f7 }
            r4[r5] = r9     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r7.zza     // Catch:{ all -> 0x03f7 }
            r10 = 2
            r4[r10] = r9     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = "Found verify task for session %s with pack %s and slice %s."
            r0.zza(r9, r4)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzeq r0 = new com.google.android.play.core.assetpacks.zzeq     // Catch:{ all -> 0x03f7 }
            int r4 = r3.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r3.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r10 = r9.zza     // Catch:{ all -> 0x03f7 }
            int r3 = r3.zzb     // Catch:{ all -> 0x03f7 }
            long r11 = r9.zzb     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r7.zza     // Catch:{ all -> 0x03f7 }
            java.lang.String r13 = r7.zzb     // Catch:{ all -> 0x03f7 }
            long r14 = r7.zzc     // Catch:{ all -> 0x03f7 }
            r18 = r0
            r19 = r4
            r20 = r10
            r21 = r3
            r22 = r11
            r24 = r9
            r25 = r13
            r26 = r14
            r18.<init>(r19, r20, r21, r22, r24, r25, r26)     // Catch:{ all -> 0x03f7 }
            goto L_0x0240
        L_0x023f:
            r0 = 0
        L_0x0240:
            if (r0 != 0) goto L_0x00b1
            java.util.Iterator r3 = r2.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x0246:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x03f7 }
            r4 = 4
            if (r0 == 0) goto L_0x0331
            java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x03f7 }
            r7 = r0
            com.google.android.play.core.assetpacks.zzdb r7 = (com.google.android.play.core.assetpacks.zzdb) r7     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r0 = r7.zzc     // Catch:{ all -> 0x03f7 }
            int r9 = r0.zzd     // Catch:{ all -> 0x03f7 }
            boolean r9 = com.google.android.play.core.assetpacks.zzbg.zzb(r9)     // Catch:{ all -> 0x03f7 }
            if (r9 == 0) goto L_0x0246
            java.util.List r0 = r0.zzf     // Catch:{ all -> 0x03f7 }
            java.util.Iterator r9 = r0.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x0264:
            boolean r0 = r9.hasNext()     // Catch:{ all -> 0x03f7 }
            if (r0 == 0) goto L_0x0246
            java.lang.Object r0 = r9.next()     // Catch:{ all -> 0x03f7 }
            r10 = r0
            com.google.android.play.core.assetpacks.zzdc r10 = (com.google.android.play.core.assetpacks.zzdc) r10     // Catch:{ all -> 0x03f7 }
            boolean r0 = zzc(r10)     // Catch:{ all -> 0x03f7 }
            if (r0 != 0) goto L_0x0264
            com.google.android.play.core.assetpacks.zzen r0 = new com.google.android.play.core.assetpacks.zzen     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzbh r11 = r1.zzc     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r12 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r13 = r12.zza     // Catch:{ all -> 0x03f7 }
            int r14 = r7.zzb     // Catch:{ all -> 0x03f7 }
            r16 = r9
            long r8 = r12.zzb     // Catch:{ all -> 0x03f7 }
            java.lang.String r12 = r10.zza     // Catch:{ all -> 0x03f7 }
            r18 = r0
            r19 = r11
            r20 = r13
            r21 = r14
            r22 = r8
            r24 = r12
            r18.<init>(r19, r20, r21, r22, r24)     // Catch:{ all -> 0x03f7 }
            int r0 = r0.zza()     // Catch:{ IOException -> 0x029b }
            goto L_0x02a9
        L_0x029b:
            r0 = move-exception
            r8 = r0
            com.google.android.play.core.internal.zzag r0 = zza     // Catch:{ all -> 0x03f7 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x03f7 }
            r9[r6] = r8     // Catch:{ all -> 0x03f7 }
            java.lang.String r8 = "Slice checkpoint corrupt, restarting extraction. %s"
            r0.zzb(r8, r9)     // Catch:{ all -> 0x03f7 }
            r0 = 0
        L_0x02a9:
            r8 = -1
            if (r0 == r8) goto L_0x032c
            java.util.List r8 = r10.zzd     // Catch:{ all -> 0x03f7 }
            java.lang.Object r8 = r8.get(r0)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzcz r8 = (com.google.android.play.core.assetpacks.zzcz) r8     // Catch:{ all -> 0x03f7 }
            boolean r8 = r8.zza     // Catch:{ all -> 0x03f7 }
            if (r8 == 0) goto L_0x032c
            com.google.android.play.core.internal.zzag r3 = zza     // Catch:{ all -> 0x03f7 }
            r8 = 5
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x03f7 }
            int r9 = r10.zze     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03f7 }
            r8[r6] = r9     // Catch:{ all -> 0x03f7 }
            int r9 = r7.zza     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03f7 }
            r8[r5] = r9     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r9.zza     // Catch:{ all -> 0x03f7 }
            r11 = 2
            r8[r11] = r9     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r10.zza     // Catch:{ all -> 0x03f7 }
            r11 = 3
            r8[r11] = r9     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x03f7 }
            r8[r4] = r9     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = "Found extraction task using compression format %s for session %s, pack %s, slice %s, chunk %s."
            r3.zza(r9, r8)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzbu r3 = r1.zzd     // Catch:{ all -> 0x03f7 }
            int r8 = r7.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r9.zza     // Catch:{ all -> 0x03f7 }
            java.lang.String r11 = r10.zza     // Catch:{ all -> 0x03f7 }
            java.io.InputStream r32 = r3.zza(r8, r9, r11, r0)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzce r3 = new com.google.android.play.core.assetpacks.zzce     // Catch:{ all -> 0x03f7 }
            int r8 = r7.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r9 = r7.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r11 = r9.zza     // Catch:{ all -> 0x03f7 }
            int r12 = r7.zzb     // Catch:{ all -> 0x03f7 }
            long r13 = r9.zzb     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r9.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r15 = r10.zza     // Catch:{ all -> 0x03f7 }
            int r5 = r10.zze     // Catch:{ all -> 0x03f7 }
            java.util.List r10 = r10.zzd     // Catch:{ all -> 0x03f7 }
            int r28 = r10.size()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r7 = r7.zzc     // Catch:{ all -> 0x03f7 }
            r16 = r5
            long r4 = r7.zze     // Catch:{ all -> 0x03f7 }
            int r7 = r7.zzd     // Catch:{ all -> 0x03f7 }
            r18 = r3
            r19 = r8
            r20 = r11
            r21 = r12
            r22 = r13
            r24 = r9
            r25 = r15
            r26 = r16
            r27 = r0
            r29 = r4
            r31 = r7
            r18.<init>(r19, r20, r21, r22, r24, r25, r26, r27, r28, r29, r31, r32)     // Catch:{ all -> 0x03f7 }
            goto L_0x0332
        L_0x032c:
            r9 = r16
            r8 = 3
            goto L_0x0264
        L_0x0331:
            r3 = 0
        L_0x0332:
            if (r3 != 0) goto L_0x03f3
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x0338:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x03f7 }
            if (r2 == 0) goto L_0x03e5
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdb r2 = (com.google.android.play.core.assetpacks.zzdb) r2     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r3 = r2.zzc     // Catch:{ all -> 0x03f7 }
            int r4 = r3.zzd     // Catch:{ all -> 0x03f7 }
            boolean r4 = com.google.android.play.core.assetpacks.zzbg.zzb(r4)     // Catch:{ all -> 0x03f7 }
            if (r4 == 0) goto L_0x0338
            java.util.List r3 = r3.zzf     // Catch:{ all -> 0x03f7 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x03f7 }
        L_0x0354:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x03f7 }
            if (r4 == 0) goto L_0x0338
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzdc r4 = (com.google.android.play.core.assetpacks.zzdc) r4     // Catch:{ all -> 0x03f7 }
            boolean r5 = zzc(r4)     // Catch:{ all -> 0x03f7 }
            if (r5 == 0) goto L_0x0354
            java.util.List r5 = r4.zzd     // Catch:{ all -> 0x03f7 }
            java.lang.Object r5 = r5.get(r6)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzcz r5 = (com.google.android.play.core.assetpacks.zzcz) r5     // Catch:{ all -> 0x03f7 }
            boolean r5 = r5.zza     // Catch:{ all -> 0x03f7 }
            if (r5 == 0) goto L_0x0354
            boolean r5 = r1.zzb(r2, r4)     // Catch:{ all -> 0x03f7 }
            if (r5 != 0) goto L_0x0354
            com.google.android.play.core.internal.zzag r0 = zza     // Catch:{ all -> 0x03f7 }
            r5 = 4
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ all -> 0x03f7 }
            int r5 = r4.zzf     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x03f7 }
            r3[r6] = r5     // Catch:{ all -> 0x03f7 }
            int r5 = r2.zza     // Catch:{ all -> 0x03f7 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x03f7 }
            r7 = 1
            r3[r7] = r5     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r5 = r2.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r5 = r5.zza     // Catch:{ all -> 0x03f7 }
            r8 = 2
            r3[r8] = r5     // Catch:{ all -> 0x03f7 }
            java.lang.String r5 = r4.zza     // Catch:{ all -> 0x03f7 }
            r9 = 3
            r3[r9] = r5     // Catch:{ all -> 0x03f7 }
            java.lang.String r5 = "Found patch slice task using patch format %s for session %s, pack %s, slice %s."
            r0.zza(r5, r3)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzbu r0 = r1.zzd     // Catch:{ all -> 0x03f7 }
            int r3 = r2.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r5 = r2.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r5 = r5.zza     // Catch:{ all -> 0x03f7 }
            java.lang.String r7 = r4.zza     // Catch:{ all -> 0x03f7 }
            java.io.InputStream r30 = r0.zza(r3, r5, r7, r6)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzef r0 = new com.google.android.play.core.assetpacks.zzef     // Catch:{ all -> 0x03f7 }
            int r3 = r2.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r5 = r2.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r5 = r5.zza     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzbh r6 = r1.zzc     // Catch:{ all -> 0x03f7 }
            int r20 = r6.zza(r5)     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzbh r6 = r1.zzc     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r7 = r2.zzc     // Catch:{ all -> 0x03f7 }
            java.lang.String r7 = r7.zza     // Catch:{ all -> 0x03f7 }
            long r21 = r6.zzc(r7)     // Catch:{ all -> 0x03f7 }
            int r6 = r2.zzb     // Catch:{ all -> 0x03f7 }
            com.google.android.play.core.assetpacks.zzda r2 = r2.zzc     // Catch:{ all -> 0x03f7 }
            long r7 = r2.zzb     // Catch:{ all -> 0x03f7 }
            int r2 = r4.zzf     // Catch:{ all -> 0x03f7 }
            java.lang.String r9 = r4.zza     // Catch:{ all -> 0x03f7 }
            long r10 = r4.zzc     // Catch:{ all -> 0x03f7 }
            r17 = r0
            r18 = r3
            r19 = r5
            r23 = r6
            r24 = r7
            r26 = r2
            r27 = r9
            r28 = r10
            r17.<init>(r18, r19, r20, r21, r23, r24, r26, r27, r28, r30)     // Catch:{ all -> 0x03f7 }
            goto L_0x03e6
        L_0x03e5:
            r0 = 0
        L_0x03e6:
            if (r0 == 0) goto L_0x03ec
            com.google.android.play.core.assetpacks.zzde r2 = r1.zzb
            goto L_0x00b3
        L_0x03ec:
            com.google.android.play.core.assetpacks.zzde r0 = r1.zzb
            r0.zzl()
            r2 = 0
            return r2
        L_0x03f3:
            com.google.android.play.core.assetpacks.zzde r0 = r1.zzb
            goto L_0x003d
        L_0x03f7:
            r0 = move-exception
            com.google.android.play.core.assetpacks.zzde r2 = r1.zzb
            r2.zzl()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.zzdh.zza():com.google.android.play.core.assetpacks.zzdg");
    }
}
