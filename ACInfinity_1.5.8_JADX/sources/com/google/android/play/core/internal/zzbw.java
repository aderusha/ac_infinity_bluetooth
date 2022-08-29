package com.google.android.play.core.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzbw {
    public static zzbu zza(Object obj, String str, Class cls) {
        return new zzbu(obj, zzh(obj, str), cls);
    }

    public static zzbv zzb(Object obj, String str, Class cls) {
        return new zzbv(obj, zzh(obj, str), cls);
    }

    public static Object zzc(Class cls) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor.newInstance(new Object[0]);
        } catch (Exception e) {
            throw new zzbx(String.format("Failed to invoke default constructor on class %s", new Object[]{cls.getName()}), e);
        }
    }

    public static Object zzd(Object obj, String str, Class cls, Class cls2, Object obj2) {
        try {
            return cls.cast(zzi(obj.getClass(), str, cls2).invoke(obj, new Object[]{obj2}));
        } catch (Exception e) {
            throw new zzbx(String.format("Failed to invoke method %s on an object of type %s", new Object[]{str, obj.getClass()}), e);
        }
    }

    public static Object zze(Object obj, String str, Class cls, Class cls2, Object obj2, Class cls3, Object obj3, Class cls4, Object obj4) {
        try {
            return cls.cast(zzi(obj.getClass(), str, cls2, cls3, cls4).invoke(obj, new Object[]{obj2, obj3, obj4}));
        } catch (Exception e) {
            throw new zzbx(String.format("Failed to invoke method %s on an object of type %s", new Object[]{str, obj.getClass()}), e);
        }
    }

    public static Object zzf(Class cls, String str, Class cls2, Class cls3, Object obj) {
        try {
            return cls2.cast(zzi(cls, "isDexOptNeeded", cls3).invoke((Object) null, new Object[]{obj}));
        } catch (Exception e) {
            throw new zzbx(String.format("Failed to invoke static method %s on type %s", new Object[]{"isDexOptNeeded", cls}), e);
        }
    }

    public static Object zzg(Class cls, String str, Class cls2, Class cls3, Object obj, Class cls4, Object obj2) {
        try {
            return cls2.cast(zzi(cls, "optimizedPathFor", cls3, cls4).invoke((Object) null, new Object[]{obj, obj2}));
        } catch (Exception e) {
            throw new zzbx(String.format("Failed to invoke static method %s on type %s", new Object[]{"optimizedPathFor", cls}), e);
        }
    }

    private static Field zzh(Object obj, String str) {
        Class cls = obj.getClass();
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
            }
        }
        throw new zzbx(String.format("Failed to find a field named %s on an object of instance %s", new Object[]{str, obj.getClass().getName()}));
    }

    private static Method zzi(Class cls, String str, Class... clsArr) {
        Class cls2 = cls;
        while (cls2 != null) {
            try {
                Method declaredMethod = cls2.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
                cls2 = cls2.getSuperclass();
            }
        }
        throw new zzbx(String.format("Could not find a method named %s with parameters %s in type %s", new Object[]{str, Arrays.asList(clsArr), cls}));
    }
}
