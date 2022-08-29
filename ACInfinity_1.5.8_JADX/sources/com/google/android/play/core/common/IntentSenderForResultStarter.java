package com.google.android.play.core.common;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

/* compiled from: com.google.android.play:core@@1.10.3 */
public interface IntentSenderForResultStarter {
    void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException;
}
