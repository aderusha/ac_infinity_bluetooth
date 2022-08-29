package com.google.android.play.core.missingsplits;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzch;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class PlayCoreMissingSplitsActivity extends Activity implements DialogInterface.OnClickListener {
    private final String zza() {
        return getApplicationInfo().loadLabel(getPackageManager()).toString();
    }

    private final void zzb(String str) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 66);
        sb.append("market://details?id=");
        sb.append(str);
        sb.append("&referrer=utm_source%3Dplay.core.missingsplits");
        try {
            startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(sb.toString())).setPackage("com.android.vending"));
        } catch (ActivityNotFoundException e) {
            new zzag(getClass().getName()).zzc(e, "Couldn't start missing splits activity for %s", str);
        }
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            zzb(getPackageName());
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder neutralButton = new AlertDialog.Builder(this).setTitle("Installation failed").setCancelable(false).setNeutralButton("Close", this);
        if (zzch.zzb(this)) {
            String zza = zza();
            StringBuilder sb = new StringBuilder(String.valueOf(zza).length() + 91);
            sb.append("The app ");
            sb.append(zza);
            sb.append(" is missing required components and must be reinstalled from the Google Play Store.");
            neutralButton.setMessage(sb.toString()).setPositiveButton("Reinstall", this);
        } else {
            String zza2 = zza();
            StringBuilder sb2 = new StringBuilder(String.valueOf(zza2).length() + 87);
            sb2.append("The app ");
            sb2.append(zza2);
            sb2.append(" is missing required components and must be reinstalled from an official store.");
            neutralButton.setMessage(sb2.toString());
        }
        neutralButton.create().show();
    }
}
