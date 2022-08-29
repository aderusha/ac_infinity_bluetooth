package kotlinx.coroutines.rx2;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import p014io.reactivex.ObservableEmitter;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00020\u000f0@2\b\u0012\u0004\u0012\u00028\u00000A2\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000%0:B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0013\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u0018\u001a\u00020\u00172\u0014\u0010\u0016\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u000f0\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000bH\u0014¢\u0006\u0004\b\u001d\u0010\u0014J\u0017\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000fH\u0014¢\u0006\u0004\b\u001f\u0010 JX\u0010(\u001a\u00020\u000f\"\u0004\b\u0001\u0010!2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\"2\u0006\u0010\u001a\u001a\u00028\u00002(\u0010'\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000%\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010&\u0012\u0006\u0012\u0004\u0018\u00010\u00010$H\u0016ø\u0001\u0000¢\u0006\u0004\b(\u0010)J\u001b\u0010*\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u001b\u0010,\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b,\u0010+J!\u0010-\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u000bH\u0002¢\u0006\u0004\b-\u0010\u0014J\u000f\u0010.\u001a\u00020\u000fH\u0002¢\u0006\u0004\b.\u0010/R\u001c\u00102\u001a\b\u0012\u0004\u0012\u00028\u00000%8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0016\u00103\u001a\u00020\u000b8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u001c\u00105\u001a\u00020\u000b8\u0016@\u0016X\u0004¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b5\u00104R\u0016\u00108\u001a\u0002078\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b8\u00109R(\u0010=\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000%0:8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<R\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010>\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, mo27512d2 = {"Lkotlinx/coroutines/rx2/RxObservableCoroutine;", "", "T", "Lkotlin/coroutines/CoroutineContext;", "parentContext", "Lio/reactivex/ObservableEmitter;", "subscriber", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lio/reactivex/ObservableEmitter;)V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "elem", "", "doLockedNext", "(Ljava/lang/Object;)V", "handled", "doLockedSignalCompleted", "(Ljava/lang/Throwable;Z)V", "Lkotlin/Function1;", "handler", "", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "element", "offer", "(Ljava/lang/Object;)Z", "onCancelled", "value", "onCompleted", "(Lkotlin/Unit;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectClause2", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSuspend", "signalCompleted", "unlockAndCheckCompleted", "()V", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "channel", "isClosedForSend", "()Z", "isFull", "Z", "Lkotlinx/coroutines/sync/Mutex;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lio/reactivex/ObservableEmitter;", "kotlinx-coroutines-rx2", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxObservable.kt */
final class RxObservableCoroutine<T> extends AbstractCoroutine<Unit> implements ProducerScope<T>, SelectClause2<T, SendChannel<? super T>> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _signal$FU = AtomicIntegerFieldUpdater.newUpdater(RxObservableCoroutine.class, "_signal");
    private volatile /* synthetic */ int _signal = 0;
    private final boolean isFull;
    private final Mutex mutex;
    private final ObservableEmitter<T> subscriber;

    public RxObservableCoroutine(CoroutineContext coroutineContext, ObservableEmitter<T> observableEmitter) {
        super(coroutineContext, true);
        this.subscriber = observableEmitter;
        Mutex Mutex$default = MutexKt.Mutex$default(false, 1, (Object) null);
        this.mutex = Mutex$default;
        this.isFull = Mutex$default.isLocked();
    }

    public SendChannel<T> getChannel() {
        return this;
    }

    public boolean isClosedForSend() {
        return isCompleted();
    }

    public boolean isFull() {
        return this.isFull;
    }

    public boolean close(Throwable th) {
        return cancelCoroutine(th);
    }

    public Void invokeOnClose(Function1<? super Throwable, Unit> function1) {
        throw new UnsupportedOperationException("RxObservableCoroutine doesn't support invokeOnClose");
    }

    public boolean offer(T t) {
        if (!Mutex.DefaultImpls.tryLock$default(this.mutex, (Object) null, 1, (Object) null)) {
            return false;
        }
        doLockedNext(t);
        return true;
    }

    public Object send(T t, Continuation<? super Unit> continuation) {
        if (offer(t)) {
            return Unit.INSTANCE;
        }
        Object sendSuspend = sendSuspend(t, continuation);
        return sendSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? sendSuspend : Unit.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object sendSuspend(T r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.rx2.RxObservableCoroutine$sendSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.rx2.RxObservableCoroutine$sendSuspend$1 r0 = (kotlinx.coroutines.rx2.RxObservableCoroutine$sendSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.rx2.RxObservableCoroutine$sendSuspend$1 r0 = new kotlinx.coroutines.rx2.RxObservableCoroutine$sendSuspend$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r5 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.rx2.RxObservableCoroutine r0 = (kotlinx.coroutines.rx2.RxObservableCoroutine) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004c
        L_0x0030:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.sync.Mutex r6 = r4.mutex
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            r2 = 0
            java.lang.Object r6 = kotlinx.coroutines.sync.Mutex.DefaultImpls.lock$default(r6, r2, r0, r3, r2)
            if (r6 != r1) goto L_0x004b
            return r1
        L_0x004b:
            r0 = r4
        L_0x004c:
            r0.doLockedNext(r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxObservableCoroutine.sendSuspend(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public SelectClause2<T, SendChannel<T>> getOnSend() {
        return this;
    }

    public <R> void registerSelectClause2(SelectInstance<? super R> selectInstance, T t, Function2<? super SendChannel<? super T>, ? super Continuation<? super R>, ? extends Object> function2) {
        this.mutex.getOnLock().registerSelectClause2(selectInstance, null, new RxObservableCoroutine$registerSelectClause2$1(this, t, function2, (Continuation) null));
    }

    /* access modifiers changed from: private */
    public final void doLockedNext(T t) {
        if (isActive()) {
            try {
                this.subscriber.onNext(t);
                unlockAndCheckCompleted();
            } catch (Throwable th) {
                cancelCoroutine(th);
                Mutex.DefaultImpls.unlock$default(this.mutex, (Object) null, 1, (Object) null);
                throw th;
            }
        } else {
            doLockedSignalCompleted(getCompletionCause(), getCompletionCauseHandled());
            throw getCancellationException();
        }
    }

    private final void unlockAndCheckCompleted() {
        Mutex.DefaultImpls.unlock$default(this.mutex, (Object) null, 1, (Object) null);
        if (!isActive() && Mutex.DefaultImpls.tryLock$default(this.mutex, (Object) null, 1, (Object) null)) {
            doLockedSignalCompleted(getCompletionCause(), getCompletionCauseHandled());
        }
    }

    private final void doLockedSignalCompleted(Throwable th, boolean z) {
        try {
            if (this._signal >= -1) {
                this._signal = -2;
                if (th != null) {
                    if (!(th instanceof CancellationException)) {
                        this.subscriber.tryOnError(th);
                        if (!z && RxObservableKt.isFatal(th)) {
                            RxCancellableKt.handleUndeliverableException(th, getContext());
                        }
                    }
                }
                this.subscriber.onComplete();
            }
        } catch (Throwable th2) {
            Mutex.DefaultImpls.unlock$default(this.mutex, (Object) null, 1, (Object) null);
            throw th2;
        }
        Mutex.DefaultImpls.unlock$default(this.mutex, (Object) null, 1, (Object) null);
    }

    private final void signalCompleted(Throwable th, boolean z) {
        if (_signal$FU.compareAndSet(this, 0, -1) && Mutex.DefaultImpls.tryLock$default(this.mutex, (Object) null, 1, (Object) null)) {
            doLockedSignalCompleted(th, z);
        }
    }

    /* access modifiers changed from: protected */
    public void onCompleted(Unit unit) {
        signalCompleted((Throwable) null, false);
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Throwable th, boolean z) {
        signalCompleted(th, z);
    }
}
