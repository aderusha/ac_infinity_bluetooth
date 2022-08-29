package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.disposables.Disposable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, mo27512d2 = {"kotlinx/coroutines/rx2/RxAwaitKt$await$2$1", "Lio/reactivex/CompletableObserver;", "onComplete", "", "onError", "e", "", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxAwait.kt */
public final class RxAwaitKt$await$2$1 implements CompletableObserver {
    final /* synthetic */ CancellableContinuation $cont;

    RxAwaitKt$await$2$1(CancellableContinuation cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public void onSubscribe(Disposable disposable) {
        RxAwaitKt.disposeOnCancellation(this.$cont, disposable);
    }

    public void onComplete() {
        Unit unit = Unit.INSTANCE;
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1023constructorimpl(unit));
    }

    public void onError(Throwable th) {
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1023constructorimpl(ResultKt.createFailure(th)));
    }
}
