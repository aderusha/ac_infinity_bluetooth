package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

@Metadata(mo27511d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, mo27512d2 = {"Lkotlinx/coroutines/ResumeAwaitOnCompletion;", "T", "Lkotlinx/coroutines/JobNode;", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "(Lkotlinx/coroutines/CancellableContinuationImpl;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: JobSupport.kt */
final class ResumeAwaitOnCompletion<T> extends JobNode {
    private final CancellableContinuationImpl<T> continuation;

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public ResumeAwaitOnCompletion(CancellableContinuationImpl<? super T> cancellableContinuationImpl) {
        this.continuation = cancellableContinuationImpl;
    }

    public void invoke(Throwable th) {
        Object state$kotlinx_coroutines_core = getJob().getState$kotlinx_coroutines_core();
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            throw new AssertionError();
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            Throwable th2 = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            Result.Companion companion = Result.Companion;
            this.continuation.resumeWith(Result.m1023constructorimpl(ResultKt.createFailure(th2)));
        } else {
            Object unboxState = JobSupportKt.unboxState(state$kotlinx_coroutines_core);
            Result.Companion companion2 = Result.Companion;
            this.continuation.resumeWith(Result.m1023constructorimpl(unboxState));
        }
    }
}
