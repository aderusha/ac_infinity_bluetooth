package kotlinx.coroutines.rx2;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.Job;
import p014io.reactivex.functions.Cancellable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo27512d2 = {"Lkotlinx/coroutines/rx2/RxCancellable;", "Lio/reactivex/functions/Cancellable;", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "cancel", "", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxCancellable.kt */
public final class RxCancellable implements Cancellable {
    private final Job job;

    public RxCancellable(Job job2) {
        this.job = job2;
    }

    public void cancel() {
        Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
    }
}
