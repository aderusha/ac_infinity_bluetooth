package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.disposables.Disposable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0015\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\f¨\u0006\r¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/rx2/RxAwaitKt$awaitOrDefault$2$1", "Lio/reactivex/MaybeObserver;", "onComplete", "", "onError", "error", "", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "onSuccess", "t", "(Ljava/lang/Object;)V", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: kotlinx.coroutines.rx2.RxAwaitKt$awaitOrDefault$$inlined$suspendCancellableCoroutine$lambda$1 */
/* compiled from: RxAwait.kt */
public final class C3917xde64801c implements MaybeObserver<T> {
    final /* synthetic */ CancellableContinuation $cont;
    final /* synthetic */ Object $default$inlined;
    final /* synthetic */ MaybeSource $this_awaitOrDefault$inlined;

    C3917xde64801c(CancellableContinuation cancellableContinuation, MaybeSource maybeSource, Object obj) {
        this.$cont = cancellableContinuation;
        this.$this_awaitOrDefault$inlined = maybeSource;
        this.$default$inlined = obj;
    }

    public void onSubscribe(Disposable disposable) {
        RxAwaitKt.disposeOnCancellation(this.$cont, disposable);
    }

    public void onComplete() {
        Object obj = this.$default$inlined;
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1023constructorimpl(obj));
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
