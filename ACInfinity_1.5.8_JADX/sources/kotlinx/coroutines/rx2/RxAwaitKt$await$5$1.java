package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0015\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo27512d2 = {"kotlinx/coroutines/rx2/RxAwaitKt$await$5$1", "Lio/reactivex/SingleObserver;", "onError", "", "error", "", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "onSuccess", "t", "(Ljava/lang/Object;)V", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxAwait.kt */
public final class RxAwaitKt$await$5$1 implements SingleObserver<T> {
    final /* synthetic */ CancellableContinuation $cont;

    RxAwaitKt$await$5$1(CancellableContinuation cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public void onSubscribe(Disposable disposable) {
        RxAwaitKt.disposeOnCancellation(this.$cont, disposable);
    }

    public void onSuccess(T t) {
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1023constructorimpl(t));
    }

    public void onError(Throwable th) {
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1023constructorimpl(ResultKt.createFailure(th)));
    }
}
