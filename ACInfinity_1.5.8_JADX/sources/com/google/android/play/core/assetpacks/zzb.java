package com.google.android.play.core.assetpacks;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import com.eternal.base.global.ActivityEvent;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzch;
import com.google.android.play.core.internal.zzx;
import com.google.android.play.core.internal.zzz;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzb extends zzx {
    final NotificationManager zza;
    private final zzag zzb = new zzag("AssetPackExtractionService");
    private final Context zzc;
    private final zzbh zzd;
    private final zzl zze;
    private final zzci zzf;

    zzb(Context context, zzbh zzbh, zzl zzl, zzci zzci) {
        this.zzc = context;
        this.zzd = zzbh;
        this.zze = zzl;
        this.zzf = zzci;
        this.zza = (NotificationManager) context.getSystemService(ActivityEvent.NOTIFICATION);
    }

    private final synchronized void zzd(String str) {
        if (str == null) {
            str = "File downloads by Play";
        }
        this.zza.createNotificationChannel(new NotificationChannel("playcore-assetpacks-service-notification-channel", str, 2));
    }

    private final synchronized void zze(Bundle bundle, zzz zzz) throws RemoteException {
        Notification.Builder builder;
        int i;
        this.zzb.zza("updateServiceState AIDL call", new Object[0]);
        if (zzch.zzb(this.zzc)) {
            if (zzch.zza(this.zzc)) {
                int i2 = bundle.getInt("action_type");
                this.zzf.zzc(zzz);
                if (i2 == 1) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        zzd(bundle.getString("notification_channel_name"));
                    }
                    this.zze.zzg(true);
                    zzci zzci = this.zzf;
                    String string = bundle.getString("notification_title");
                    String string2 = bundle.getString("notification_subtext");
                    long j = bundle.getLong("notification_timeout", 600000);
                    Parcelable parcelable = bundle.getParcelable("notification_on_click_intent");
                    if (Build.VERSION.SDK_INT >= 26) {
                        builder = new Notification.Builder(this.zzc, "playcore-assetpacks-service-notification-channel").setTimeoutAfter(j);
                    } else {
                        builder = new Notification.Builder(this.zzc).setPriority(-2);
                    }
                    if (parcelable instanceof PendingIntent) {
                        builder.setContentIntent((PendingIntent) parcelable);
                    }
                    Notification.Builder ongoing = builder.setSmallIcon(17301633).setOngoing(false);
                    if (string == null) {
                        string = "Downloading additional file";
                    }
                    Notification.Builder contentTitle = ongoing.setContentTitle(string);
                    if (string2 == null) {
                        string2 = "Transferring";
                    }
                    contentTitle.setSubText(string2);
                    if (Build.VERSION.SDK_INT >= 21 && (i = bundle.getInt("notification_color")) != 0) {
                        builder.setColor(i).setVisibility(-1);
                    }
                    zzci.zza(builder.build());
                    this.zzc.bindService(new Intent(this.zzc, ExtractionForegroundService.class), this.zzf, 1);
                    return;
                } else if (i2 == 2) {
                    this.zze.zzg(false);
                    this.zzf.zzb();
                    return;
                } else {
                    this.zzb.zzb("Unknown action type received: %d", Integer.valueOf(i2));
                    zzz.zzd(new Bundle());
                    return;
                }
            }
        }
        zzz.zzd(new Bundle());
    }

    public final void zzb(Bundle bundle, zzz zzz) throws RemoteException {
        this.zzb.zza("clearAssetPackStorage AIDL call", new Object[0]);
        if (!zzch.zzb(this.zzc) || !zzch.zza(this.zzc)) {
            zzz.zzd(new Bundle());
            return;
        }
        this.zzd.zzz();
        zzz.zzc(new Bundle());
    }

    public final void zzc(Bundle bundle, zzz zzz) throws RemoteException {
        zze(bundle, zzz);
    }
}
