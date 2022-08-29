package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import java.util.ArrayList;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzh implements zzp {
    public final /* synthetic */ SplitInstallRequest zza;

    public /* synthetic */ zzh(SplitInstallRequest splitInstallRequest) {
        this.zza = splitInstallRequest;
    }

    public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
        int i;
        SplitInstallRequest splitInstallRequest = this.zza;
        int i2 = FakeSplitInstallManager.zza;
        if (splitInstallSessionState == null || splitInstallSessionState.hasTerminalStatus()) {
            if (splitInstallSessionState == null) {
                i = 1;
            } else {
                i = 1 + splitInstallSessionState.sessionId();
            }
            return SplitInstallSessionState.create(i, 1, 0, 0, 0, splitInstallRequest.getModuleNames(), new ArrayList());
        }
        throw new SplitInstallException(-1);
    }
}
