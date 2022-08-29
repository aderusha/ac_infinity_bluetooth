package androidx.lifecycle;

import kotlin.Metadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo27512d2 = {"<anonymous>", "", "run", "androidx/lifecycle/DispatchQueue$runOrEnqueue$1$1"}, mo27513k = 3, mo27514mv = {1, 1, 15})
/* compiled from: DispatchQueue.kt */
final class DispatchQueue$runOrEnqueue$$inlined$with$lambda$1 implements Runnable {
    final /* synthetic */ Runnable $runnable$inlined;
    final /* synthetic */ DispatchQueue this$0;

    DispatchQueue$runOrEnqueue$$inlined$with$lambda$1(DispatchQueue dispatchQueue, Runnable runnable) {
        this.this$0 = dispatchQueue;
        this.$runnable$inlined = runnable;
    }

    public final void run() {
        this.this$0.enqueue(this.$runnable$inlined);
    }
}
