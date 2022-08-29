package kotlinx.coroutines.rx2;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.rx2.RxAwaitKt;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0015\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0012¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/rx2/RxAwaitKt$awaitOne$2$1", "Lio/reactivex/Observer;", "seenValue", "", "subscription", "Lio/reactivex/disposables/Disposable;", "value", "Ljava/lang/Object;", "onComplete", "", "onError", "e", "", "onNext", "t", "(Ljava/lang/Object;)V", "onSubscribe", "sub", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxAwait.kt */
public final class RxAwaitKt$awaitOne$$inlined$suspendCancellableCoroutine$lambda$1 implements Observer<T> {
    final /* synthetic */ CancellableContinuation $cont;
    final /* synthetic */ Object $default$inlined;
    final /* synthetic */ Mode $mode$inlined;
    final /* synthetic */ ObservableSource $this_awaitOne$inlined;
    private boolean seenValue;
    private Disposable subscription;
    private T value;

    RxAwaitKt$awaitOne$$inlined$suspendCancellableCoroutine$lambda$1(CancellableContinuation cancellableContinuation, ObservableSource observableSource, Mode mode, Object obj) {
        this.$cont = cancellableContinuation;
        this.$this_awaitOne$inlined = observableSource;
        this.$mode$inlined = mode;
        this.$default$inlined = obj;
    }

    public void onSubscribe(final Disposable disposable) {
        this.subscription = disposable;
        this.$cont.invokeOnCancellation(new Function1<Throwable, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                disposable.dispose();
            }
        });
    }

    public void onNext(T t) {
        int i = RxAwaitKt.WhenMappings.$EnumSwitchMapping$0[this.$mode$inlined.ordinal()];
        if (i == 1 || i == 2) {
            if (!this.seenValue) {
                this.seenValue = true;
                Result.Companion companion = Result.Companion;
                this.$cont.resumeWith(Result.m1023constructorimpl(t));
                Disposable disposable = this.subscription;
                if (disposable == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("subscription");
                }
                disposable.dispose();
            }
        } else if (i != 3 && i != 4) {
        } else {
            if (this.$mode$inlined != Mode.SINGLE || !this.seenValue) {
                this.value = t;
                this.seenValue = true;
                return;
            }
            if (this.$cont.isActive()) {
                Result.Companion companion2 = Result.Companion;
                this.$cont.resumeWith(Result.m1023constructorimpl(ResultKt.createFailure(new IllegalArgumentException("More than one onNext value for " + this.$mode$inlined))));
            }
            Disposable disposable2 = this.subscription;
            if (disposable2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subscription");
            }
            disposable2.dispose();
        }
    }

    public void onComplete() {
        if (this.seenValue) {
            if (this.$cont.isActive()) {
                T t = this.value;
                Result.Companion companion = Result.Companion;
                this.$cont.resumeWith(Result.m1023constructorimpl(t));
            }
        } else if (this.$mode$inlined == Mode.FIRST_OR_DEFAULT) {
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
