package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.AbstractCoroutine;
import p014io.reactivex.SingleEmitter;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u0015\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo27512d2 = {"Lkotlinx/coroutines/rx2/RxSingleCoroutine;", "T", "", "Lkotlinx/coroutines/AbstractCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "subscriber", "Lio/reactivex/SingleEmitter;", "(Lkotlin/coroutines/CoroutineContext;Lio/reactivex/SingleEmitter;)V", "onCancelled", "", "cause", "", "handled", "", "onCompleted", "value", "(Ljava/lang/Object;)V", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxSingle.kt */
final class RxSingleCoroutine<T> extends AbstractCoroutine<T> {
    private final SingleEmitter<T> subscriber;

    public RxSingleCoroutine(CoroutineContext coroutineContext, SingleEmitter<T> singleEmitter) {
        super(coroutineContext, true);
        this.subscriber = singleEmitter;
    }

    /* access modifiers changed from: protected */
    public void onCompleted(T t) {
        try {
            this.subscriber.onSuccess(t);
        } catch (Throwable th) {
            RxCancellableKt.handleUndeliverableException(th, getContext());
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Throwable th, boolean z) {
        try {
            if (!this.subscriber.tryOnError(th)) {
                RxCancellableKt.handleUndeliverableException(th, getContext());
            }
        } catch (Throwable th2) {
            RxCancellableKt.handleUndeliverableException(th2, getContext());
        }
    }
}
