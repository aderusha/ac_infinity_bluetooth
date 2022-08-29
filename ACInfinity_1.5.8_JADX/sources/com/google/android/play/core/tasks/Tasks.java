package com.google.android.play.core.tasks;

import com.google.android.play.core.internal.zzci;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class Tasks {
    private Tasks() {
    }

    public static <ResultT> ResultT await(Task<ResultT> task) throws ExecutionException, InterruptedException {
        zzci.zza(task, "Task must not be null");
        if (task.isComplete()) {
            return zzc(task);
        }
        zzo zzo = new zzo((zzn) null);
        zzd(task, zzo);
        zzo.zza();
        return zzc(task);
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return zzb((Object) null);
        }
        for (Task requireNonNull : collection) {
            Objects.requireNonNull(requireNonNull, "null tasks are not accepted");
        }
        zzm zzm = new zzm();
        zzq zzq = new zzq(collection.size(), zzm);
        for (Task zzd : collection) {
            zzd(zzd, zzq);
        }
        return zzm;
    }

    public static Task zza(Exception exc) {
        zzm zzm = new zzm();
        zzm.zza(exc);
        return zzm;
    }

    public static Task zzb(Object obj) {
        zzm zzm = new zzm();
        zzm.zzb(obj);
        return zzm;
    }

    private static Object zzc(Task task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    private static void zzd(Task task, zzp zzp) {
        task.addOnSuccessListener(TaskExecutors.zza, zzp);
        task.addOnFailureListener(TaskExecutors.zza, zzp);
    }

    public static <ResultT> ResultT await(Task<ResultT> task, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        zzci.zza(task, "Task must not be null");
        zzci.zza(timeUnit, "TimeUnit must not be null");
        if (task.isComplete()) {
            return zzc(task);
        }
        zzo zzo = new zzo((zzn) null);
        zzd(task, zzo);
        if (zzo.zzb(j, timeUnit)) {
            return zzc(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }
}
