package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.tasks.zzi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzaz extends zzbb {
    zzaz(zzbc zzbc, zzi zzi) {
        super(zzbc, zzi);
    }

    public final void zzh(List list) throws RemoteException {
        super.zzh(list);
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(SplitInstallSessionState.zzd((Bundle) it.next()));
        }
        this.zza.zze(arrayList);
    }
}
