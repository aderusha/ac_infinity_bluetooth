package com.google.android.play.core.appupdate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzf implements AppUpdateManager {
    private final zzq zza;
    private final zzb zzb;
    private final Context zzc;
    private final Handler zzd = new Handler(Looper.getMainLooper());

    zzf(zzq zzq, zzb zzb2, Context context) {
        this.zza = zzq;
        this.zzb = zzb2;
        this.zzc = context;
    }

    public final Task<Void> completeUpdate() {
        return this.zza.zzf(this.zzc.getPackageName());
    }

    public final Task<AppUpdateInfo> getAppUpdateInfo() {
        return this.zza.zzg(this.zzc.getPackageName());
    }

    public final synchronized void registerListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.zzb.zzf(installStateUpdatedListener);
    }

    public final Task<Integer> startUpdateFlow(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions) {
        if (appUpdateInfo == null || activity == null || appUpdateOptions == null || appUpdateInfo.zzd()) {
            return Tasks.zza(new InstallException(-4));
        }
        if (!appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions)) {
            return Tasks.zza(new InstallException(-6));
        }
        appUpdateInfo.zzc();
        Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", appUpdateInfo.zza(appUpdateOptions));
        zzi zzi = new zzi();
        intent.putExtra("result_receiver", new zzd(this, this.zzd, zzi));
        activity.startActivity(intent);
        return zzi.zza();
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int i, Activity activity, int i2) throws IntentSender.SendIntentException {
        AppUpdateOptions defaultOptions = AppUpdateOptions.defaultOptions(i);
        if (activity == null) {
            return false;
        }
        return startUpdateFlowForResult(appUpdateInfo, (IntentSenderForResultStarter) new zze(this, activity), defaultOptions, i2);
    }

    public final synchronized void unregisterListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.zzb.zzh(installStateUpdatedListener);
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int i, IntentSenderForResultStarter intentSenderForResultStarter, int i2) throws IntentSender.SendIntentException {
        return startUpdateFlowForResult(appUpdateInfo, intentSenderForResultStarter, AppUpdateOptions.defaultOptions(i), i2);
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions, int i) throws IntentSender.SendIntentException {
        if (activity == null) {
            return false;
        }
        return startUpdateFlowForResult(appUpdateInfo, (IntentSenderForResultStarter) new zze(this, activity), appUpdateOptions, i);
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, IntentSenderForResultStarter intentSenderForResultStarter, AppUpdateOptions appUpdateOptions, int i) throws IntentSender.SendIntentException {
        if (appUpdateInfo == null || intentSenderForResultStarter == null || appUpdateOptions == null || !appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions) || appUpdateInfo.zzd()) {
            return false;
        }
        appUpdateInfo.zzc();
        intentSenderForResultStarter.startIntentSenderForResult(appUpdateInfo.zza(appUpdateOptions).getIntentSender(), i, (Intent) null, 0, 0, 0, (Bundle) null);
        return true;
    }
}
