package com.google.android.play.core.appupdate;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import com.google.android.play.core.common.IntentSenderForResultStarter;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zze implements IntentSenderForResultStarter {
    final /* synthetic */ Activity zza;

    zze(zzf zzf, Activity activity) {
        this.zza = activity;
    }

    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        this.zza.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }
}
