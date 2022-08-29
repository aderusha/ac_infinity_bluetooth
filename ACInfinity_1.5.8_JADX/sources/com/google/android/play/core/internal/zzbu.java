package com.google.android.play.core.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzbu extends zzbv {
    zzbu(Object obj, Field field, Class cls) {
        super(obj, field, Array.newInstance(cls, 0).getClass());
    }

    private final Class zzf() {
        return zzd().getType().getComponentType();
    }

    public final void zza(Collection collection) {
        int i;
        Object[] objArr = (Object[]) zzc();
        if (objArr == null) {
            i = 0;
        } else {
            i = objArr.length;
        }
        Object[] objArr2 = (Object[]) Array.newInstance(zzf(), collection.size() + i);
        if (objArr != null) {
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        }
        for (Object obj : collection) {
            objArr2[i] = obj;
            i++;
        }
        zze(objArr2);
    }

    public final void zzb(Collection collection) {
        int i;
        Object[] objArr = (Object[]) zzc();
        int i2 = 0;
        if (objArr == null) {
            i = 0;
        } else {
            i = objArr.length;
        }
        Object[] objArr2 = (Object[]) Array.newInstance(zzf(), i + collection.size());
        if (objArr != null) {
            System.arraycopy(objArr, 0, objArr2, collection.size(), objArr.length);
        }
        for (Object obj : collection) {
            objArr2[i2] = obj;
            i2++;
        }
        zze(objArr2);
    }
}
