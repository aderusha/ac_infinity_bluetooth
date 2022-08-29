package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;
import java.io.File;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzdp implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;
    private final zzcs zzc;
    private final zzcs zzd;
    private final zzcs zze;
    private final zzcs zzf;
    private final zzcs zzg;

    public zzdp(zzcs zzcs, zzcs zzcs2, zzcs zzcs3, zzcs zzcs4, zzcs zzcs5, zzcs zzcs6, zzcs zzcs7) {
        this.zza = zzcs;
        this.zzb = zzcs2;
        this.zzc = zzcs3;
        this.zzd = zzcs4;
        this.zze = zzcs5;
        this.zzf = zzcs6;
        this.zzg = zzcs7;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        File file;
        String str = (String) this.zza.zza();
        Object zza2 = this.zzb.zza();
        Object zza3 = this.zzc.zza();
        Context zzb2 = ((zzu) this.zzd).zzb();
        Object zza4 = this.zze.zza();
        zzco zzb3 = zzcq.zzb(this.zzf);
        zzbb zzbb = (zzbb) zza2;
        zzco zzco = (zzco) zza3;
        zzed zzed = (zzed) zza4;
        zzeb zzeb = (zzeb) this.zzg.zza();
        if (str != null) {
            file = new File(zzb2.getExternalFilesDir((String) null), str);
        } else {
            file = zzb2.getExternalFilesDir((String) null);
        }
        return new zzdo(file, zzbb, zzco, zzb2, zzed, zzb3, zzeb);
    }
}
