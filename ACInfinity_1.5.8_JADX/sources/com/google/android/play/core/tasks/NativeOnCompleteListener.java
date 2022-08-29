package com.google.android.play.core.tasks;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class NativeOnCompleteListener implements OnCompleteListener<Object> {
    private final long zza;
    private final int zzb;

    public NativeOnCompleteListener(long j, int i) {
        this.zza = j;
        this.zzb = i;
    }

    public native void nativeOnComplete(long j, int i, Object obj, int i2);

    public void onComplete(Task<Object> task) {
        if (!task.isComplete()) {
            int i = this.zzb;
            StringBuilder sb = new StringBuilder(50);
            sb.append("onComplete called for incomplete task: ");
            sb.append(i);
            throw new IllegalStateException(sb.toString());
        } else if (task.isSuccessful()) {
            nativeOnComplete(this.zza, this.zzb, task.getResult(), 0);
        } else {
            Exception exception = task.getException();
            if (!(exception instanceof zzj)) {
                nativeOnComplete(this.zza, this.zzb, (Object) null, -100);
                return;
            }
            int errorCode = ((zzj) exception).getErrorCode();
            if (errorCode != 0) {
                nativeOnComplete(this.zza, this.zzb, (Object) null, errorCode);
                return;
            }
            int i2 = this.zzb;
            StringBuilder sb2 = new StringBuilder(51);
            sb2.append("TaskException has error code 0 on task: ");
            sb2.append(i2);
            throw new IllegalStateException(sb2.toString());
        }
    }
}
