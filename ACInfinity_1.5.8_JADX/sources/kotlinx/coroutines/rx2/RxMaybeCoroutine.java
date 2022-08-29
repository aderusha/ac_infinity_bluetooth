package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.AbstractCoroutine;
import p014io.reactivex.MaybeEmitter;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0015\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo27512d2 = {"Lkotlinx/coroutines/rx2/RxMaybeCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "subscriber", "Lio/reactivex/MaybeEmitter;", "(Lkotlin/coroutines/CoroutineContext;Lio/reactivex/MaybeEmitter;)V", "onCancelled", "", "cause", "", "handled", "", "onCompleted", "value", "(Ljava/lang/Object;)V", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxMaybe.kt */
final class RxMaybeCoroutine<T> extends AbstractCoroutine<T> {
    private final MaybeEmitter<T> subscriber;

    public RxMaybeCoroutine(CoroutineContext coroutineContext, MaybeEmitter<T> maybeEmitter) {
        super(coroutineContext, true);
        this.subscriber = maybeEmitter;
    }

    /* access modifiers changed from: protected */
    public void onCompleted(T t) {
        if (t == null) {
            try {
                this.subscriber.onComplete();
            } catch (Throwable th) {
                RxCancellableKt.handleUndeliverableException(th, getContext());
            }
        } else {
            this.subscriber.onSuccess(t);
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
