package kotlinx.coroutines.reactive;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
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
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0007\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00020\b0I2\b\u0012\u0004\u0012\u00028\u00000J2\u00020K2\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000'0CB7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0016\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u001b\u001a\u00020\u001a2\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0018H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010 \u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u000fH\u0014¢\u0006\u0004\b \u0010\u0017J\u0017\u0010\"\u001a\u00020\b2\u0006\u0010!\u001a\u00020\bH\u0014¢\u0006\u0004\b\"\u0010#JX\u0010+\u001a\u00020\b\"\u0004\b\u0001\u0010$2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00010%2\u0006\u0010\u001d\u001a\u00028\u00002(\u0010*\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000'\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010(\u0012\u0006\u0012\u0004\u0018\u00010)0\u0006H\u0016ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u0017\u0010/\u001a\u00020\b2\u0006\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b/\u00100J\u001b\u00101\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b1\u00102J\u001b\u00103\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b3\u00102J!\u00104\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u000fH\u0002¢\u0006\u0004\b4\u0010\u0017J\u000f\u00105\u001a\u00020\bH\u0002¢\u0006\u0004\b5\u0010\rJ\u0013\u00106\u001a\u00020\u000f*\u00020\u0007H\u0002¢\u0006\u0004\b6\u0010\u0011R\u0016\u00107\u001a\u00020\u000f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u001c\u0010;\u001a\b\u0012\u0004\u0012\u00028\u00000'8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R(\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u00068\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010<R\u0016\u0010=\u001a\u00020\u000f8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u001c\u0010?\u001a\u00020\u000f8\u0016@\u0016X\u0004¢\u0006\f\n\u0004\b?\u00108\u001a\u0004\b?\u0010>R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\bA\u0010BR(\u0010F\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000'0C8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\b@\bX\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010G\u0002\u0004\n\u0002\b\u0019¨\u0006H"}, mo27512d2 = {"Lkotlinx/coroutines/reactive/PublisherCoroutine;", "T", "Lkotlin/coroutines/CoroutineContext;", "parentContext", "Lorg/reactivestreams/Subscriber;", "subscriber", "Lkotlin/Function2;", "", "", "exceptionOnCancelHandler", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lorg/reactivestreams/Subscriber;Lkotlin/jvm/functions/Function2;)V", "cancel", "()V", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "elem", "doLockedNext", "(Ljava/lang/Object;)V", "handled", "doLockedSignalCompleted", "(Ljava/lang/Throwable;Z)V", "Lkotlin/Function1;", "handler", "", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "element", "offer", "(Ljava/lang/Object;)Z", "onCancelled", "value", "onCompleted", "(Lkotlin/Unit;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "", "block", "registerSelectClause2", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "", "n", "request", "(J)V", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSuspend", "signalCompleted", "unlockAndCheckCompleted", "isFatal", "cancelled", "Z", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "channel", "Lkotlin/jvm/functions/Function2;", "isClosedForSend", "()Z", "isFull", "Lkotlinx/coroutines/sync/Mutex;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lorg/reactivestreams/Subscriber;", "kotlinx-coroutines-reactive", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lorg/reactivestreams/Subscription;"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: Publish.kt */
public final class PublisherCoroutine<T> extends AbstractCoroutine<Unit> implements ProducerScope<T>, Subscription, SelectClause2<T, SendChannel<? super T>> {
    private static final /* synthetic */ AtomicLongFieldUpdater _nRequested$FU = AtomicLongFieldUpdater.newUpdater(PublisherCoroutine.class, "_nRequested");
    private volatile /* synthetic */ long _nRequested = 0;
    private volatile boolean cancelled;
    private final Function2<Throwable, CoroutineContext, Unit> exceptionOnCancelHandler;
    private final boolean isFull;
    private final Mutex mutex;
    private final Subscriber<T> subscriber;

    public PublisherCoroutine(CoroutineContext coroutineContext, Subscriber<T> subscriber2, Function2<? super Throwable, ? super CoroutineContext, Unit> function2) {
        super(coroutineContext, true);
        this.subscriber = subscriber2;
        this.exceptionOnCancelHandler = function2;
        Mutex Mutex = MutexKt.Mutex(true);
        this.mutex = Mutex;
        this.isFull = Mutex.isLocked();
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
        throw new UnsupportedOperationException("PublisherCoroutine doesn't support invokeOnClose");
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
            boolean r0 = r6 instanceof kotlinx.coroutines.reactive.PublisherCoroutine$sendSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.reactive.PublisherCoroutine$sendSuspend$1 r0 = (kotlinx.coroutines.reactive.PublisherCoroutine$sendSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.reactive.PublisherCoroutine$sendSuspend$1 r0 = new kotlinx.coroutines.reactive.PublisherCoroutine$sendSuspend$1
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
            kotlinx.coroutines.reactive.PublisherCoroutine r0 = (kotlinx.coroutines.reactive.PublisherCoroutine) r0
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
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.PublisherCoroutine.sendSuspend(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public SelectClause2<T, SendChannel<T>> getOnSend() {
        return this;
    }

    public <R> void registerSelectClause2(SelectInstance<? super R> selectInstance, T t, Function2<? super SendChannel<? super T>, ? super Continuation<? super R>, ? extends Object> function2) {
        this.mutex.getOnLock().registerSelectClause2(selectInstance, null, new PublisherCoroutine$registerSelectClause2$1(this, t, function2, (Continuation) null));
    }

    /* access modifiers changed from: private */
    public final void doLockedNext(T t) {
        if (isActive()) {
            try {
                this.subscriber.onNext(t);
                while (true) {
                    long j = this._nRequested;
                    if (j < 0 || j == Long.MAX_VALUE) {
                        break;
                    }
                    long j2 = j - 1;
                    if (_nRequested$FU.compareAndSet(this, j, j2)) {
                        if (j2 == 0) {
                            return;
                        }
                    }
                }
                unlockAndCheckCompleted();
            } catch (Throwable th) {
                cancelCoroutine(th);
                unlockAndCheckCompleted();
                throw th;
            }
        } else {
            unlockAndCheckCompleted();
            throw getCancellationException();
        }
    }

    private final void unlockAndCheckCompleted() {
        Mutex.DefaultImpls.unlock$default(this.mutex, (Object) null, 1, (Object) null);
        if (isCompleted() && Mutex.DefaultImpls.tryLock$default(this.mutex, (Object) null, 1, (Object) null)) {
            doLockedSignalCompleted(getCompletionCause(), getCompletionCauseHandled());
        }
    }

    private final void doLockedSignalCompleted(Throwable th, boolean z) {
        try {
            if (this._nRequested >= -1) {
                this._nRequested = -2;
                if (this.cancelled) {
                    if (th != null && !z) {
                        this.exceptionOnCancelHandler.invoke(th, getContext());
                    }
                    Mutex.DefaultImpls.unlock$default(this.mutex, (Object) null, 1, (Object) null);
                    return;
                }
                if (th != null) {
                    if (!(th instanceof CancellationException)) {
                        this.subscriber.onError(th);
                        if (!z && isFatal(th)) {
                            this.exceptionOnCancelHandler.invoke(th, getContext());
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

    public void request(long j) {
        long j2;
        int i;
        long j3;
        if (j <= 0) {
            cancelCoroutine(new IllegalArgumentException("non-positive subscription request " + j));
            return;
        }
        do {
            j2 = this._nRequested;
            i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
            if (i >= 0) {
                long j4 = j2 + j;
                j3 = Long.MAX_VALUE;
                if (j4 >= 0 && j != Long.MAX_VALUE) {
                    j3 = j4;
                }
                if (j2 == j3) {
                    return;
                }
            } else {
                return;
            }
        } while (!_nRequested$FU.compareAndSet(this, j2, j3));
        if (i == 0) {
            unlockAndCheckCompleted();
        }
    }

    private final void signalCompleted(Throwable th, boolean z) {
        long j;
        int i;
        do {
            j = this._nRequested;
            if (j != -2) {
                i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                if (!(i >= 0)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            } else {
                return;
            }
        } while (!_nRequested$FU.compareAndSet(this, j, -1));
        if (i == 0) {
            doLockedSignalCompleted(th, z);
        } else if (Mutex.DefaultImpls.tryLock$default(this.mutex, (Object) null, 1, (Object) null)) {
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

    public void cancel() {
        this.cancelled = true;
        super.cancel((CancellationException) null);
    }

    private final boolean isFatal(Throwable th) {
        return (th instanceof VirtualMachineError) || (th instanceof ThreadDeath) || (th instanceof LinkageError);
    }
}
