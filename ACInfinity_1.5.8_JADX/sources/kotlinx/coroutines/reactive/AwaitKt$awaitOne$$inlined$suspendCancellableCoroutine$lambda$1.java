package kotlinx.coroutines.reactive;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.reactive.AwaitKt;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0015\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0012¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/reactive/AwaitKt$awaitOne$2$1", "Lorg/reactivestreams/Subscriber;", "seenValue", "", "subscription", "Lorg/reactivestreams/Subscription;", "value", "Ljava/lang/Object;", "onComplete", "", "onError", "e", "", "onNext", "t", "(Ljava/lang/Object;)V", "onSubscribe", "sub", "kotlinx-coroutines-reactive"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: Await.kt */
public final class AwaitKt$awaitOne$$inlined$suspendCancellableCoroutine$lambda$1 implements Subscriber<T> {
    final /* synthetic */ CancellableContinuation $cont;
    final /* synthetic */ Object $default$inlined;
    final /* synthetic */ Mode $mode$inlined;
    final /* synthetic */ Publisher $this_awaitOne$inlined;
    private boolean seenValue;
    private Subscription subscription;
    private T value;

    AwaitKt$awaitOne$$inlined$suspendCancellableCoroutine$lambda$1(CancellableContinuation cancellableContinuation, Publisher publisher, Mode mode, Object obj) {
        this.$cont = cancellableContinuation;
        this.$this_awaitOne$inlined = publisher;
        this.$mode$inlined = mode;
        this.$default$inlined = obj;
    }

    public void onSubscribe(final Subscription subscription2) {
        this.subscription = subscription2;
        this.$cont.invokeOnCancellation(new Function1<Throwable, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                subscription2.cancel();
            }
        });
        subscription2.request(this.$mode$inlined == Mode.FIRST ? 1 : Long.MAX_VALUE);
    }

    public void onNext(T t) {
        int i = AwaitKt.WhenMappings.$EnumSwitchMapping$0[this.$mode$inlined.ordinal()];
        if (i == 1 || i == 2) {
            if (!this.seenValue) {
                this.seenValue = true;
                Subscription subscription2 = this.subscription;
                if (subscription2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("subscription");
                }
                subscription2.cancel();
                Result.Companion companion = Result.Companion;
                this.$cont.resumeWith(Result.m1023constructorimpl(t));
            }
        } else if (i != 3 && i != 4 && i != 5) {
        } else {
            if ((this.$mode$inlined == Mode.SINGLE || this.$mode$inlined == Mode.SINGLE_OR_DEFAULT) && this.seenValue) {
                Subscription subscription3 = this.subscription;
                if (subscription3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("subscription");
                }
                subscription3.cancel();
                if (this.$cont.isActive()) {
                    Result.Companion companion2 = Result.Companion;
                    this.$cont.resumeWith(Result.m1023constructorimpl(ResultKt.createFailure(new IllegalArgumentException("More than one onNext value for " + this.$mode$inlined))));
                    return;
                }
                return;
            }
            this.value = t;
            this.seenValue = true;
        }
    }

    public void onComplete() {
        if (this.seenValue) {
            if (this.$cont.isActive()) {
                T t = this.value;
                Result.Companion companion = Result.Companion;
                this.$cont.resumeWith(Result.m1023constructorimpl(t));
            }
        } else if (this.$mode$inlined == Mode.FIRST_OR_DEFAULT || this.$mode$inlined == Mode.SINGLE_OR_DEFAULT) {
            Object obj = this.$default$inlined;
            Result.Companion companion2 = Result.Companion;
            this.$cont.resumeWith(Result.m1023constructorimpl(obj));
        } else if (this.$cont.isActive()) {
            Result.Companion companion3 = Result.Companion;
            this.$cont.resumeWith(Result.m1023constructorimpl(ResultKt.createFailure(new NoSuchElementException("No value received via onNext for " + this.$mode$inlined))));
        }
    }

    public void onError(Throwable th) {
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1023constructorimpl(ResultKt.createFailure(th)));
    }
}
