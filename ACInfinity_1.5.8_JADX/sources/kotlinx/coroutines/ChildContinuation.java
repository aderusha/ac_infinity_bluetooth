package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;

@Metadata(mo27511d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002R\u0014\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, mo27512d2 = {"Lkotlinx/coroutines/ChildContinuation;", "Lkotlinx/coroutines/JobCancellingNode;", "child", "Lkotlinx/coroutines/CancellableContinuationImpl;", "(Lkotlinx/coroutines/CancellableContinuationImpl;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: JobSupport.kt */
public final class ChildContinuation extends JobCancellingNode {
    public final CancellableContinuationImpl<?> child;

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public ChildContinuation(CancellableContinuationImpl<?> cancellableContinuationImpl) {
        this.child = cancellableContinuationImpl;
    }

    public void invoke(Throwable th) {
        CancellableContinuationImpl<?> cancellableContinuationImpl = this.child;
        cancellableContinuationImpl.parentCancelled$kotlinx_coroutines_core(cancellableContinuationImpl.getContinuationCancellationCause(getJob()));
    }
}
