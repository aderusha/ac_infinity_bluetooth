package kotlinx.coroutines.reactive;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.flow.Flow;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u001a2\b\u0012\u0004\u0012\u00020\n0\u001bB-\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u000fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000eJ\u0017\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0006@\u0007X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0017R\u001e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00048\u0006@\u0007X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, mo27512d2 = {"Lkotlinx/coroutines/reactive/FlowSubscription;", "T", "Lkotlinx/coroutines/flow/Flow;", "flow", "Lorg/reactivestreams/Subscriber;", "subscriber", "Lkotlin/coroutines/CoroutineContext;", "context", "<init>", "(Lkotlinx/coroutines/flow/Flow;Lorg/reactivestreams/Subscriber;Lkotlin/coroutines/CoroutineContext;)V", "", "cancel", "()V", "consumeFlow", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/Continuation;", "createInitialContinuation", "()Lkotlin/coroutines/Continuation;", "flowProcessing", "", "n", "request", "(J)V", "Lkotlinx/coroutines/flow/Flow;", "Lorg/reactivestreams/Subscriber;", "kotlinx-coroutines-reactive", "Lorg/reactivestreams/Subscription;", "Lkotlinx/coroutines/AbstractCoroutine;"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ReactiveFlow.kt */
public final class FlowSubscription<T> extends AbstractCoroutine<Unit> implements Subscription {
    static final /* synthetic */ AtomicReferenceFieldUpdater producer$FU;
    static final /* synthetic */ AtomicLongFieldUpdater requested$FU;
    public final Flow<T> flow;
    volatile /* synthetic */ Object producer = createInitialContinuation();
    volatile /* synthetic */ long requested = 0;
    public final Subscriber<? super T> subscriber;

    static {
        Class<FlowSubscription> cls = FlowSubscription.class;
        requested$FU = AtomicLongFieldUpdater.newUpdater(cls, "requested");
        producer$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "producer");
    }

    public FlowSubscription(Flow<? extends T> flow2, Subscriber<? super T> subscriber2, CoroutineContext coroutineContext) {
        super(coroutineContext, true);
        this.flow = flow2;
        this.subscriber = subscriber2;
    }

    private final Continuation<Unit> createInitialContinuation() {
        return new C3914x4b4643a3(getCoroutineContext(), this);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object flowProcessing(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1 r0 = (kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1 r0 = new kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.reactive.FlowSubscription r0 = (kotlinx.coroutines.reactive.FlowSubscription) r0
            kotlin.ResultKt.throwOnFailure(r5)     // Catch:{ all -> 0x002e }
            goto L_0x0047
        L_0x002e:
            r5 = move-exception
            goto L_0x004f
        L_0x0030:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4     // Catch:{ all -> 0x004d }
            r0.label = r3     // Catch:{ all -> 0x004d }
            java.lang.Object r5 = r4.consumeFlow(r0)     // Catch:{ all -> 0x004d }
            if (r5 != r1) goto L_0x0046
            return r1
        L_0x0046:
            r0 = r4
        L_0x0047:
            org.reactivestreams.Subscriber<? super T> r5 = r0.subscriber     // Catch:{ all -> 0x002e }
            r5.onComplete()     // Catch:{ all -> 0x002e }
            goto L_0x0067
        L_0x004d:
            r5 = move-exception
            r0 = r4
        L_0x004f:
            boolean r1 = r5 instanceof java.util.concurrent.CancellationException     // Catch:{ all -> 0x005f }
            if (r1 == 0) goto L_0x0059
            org.reactivestreams.Subscriber<? super T> r5 = r0.subscriber     // Catch:{ all -> 0x005f }
            r5.onComplete()     // Catch:{ all -> 0x005f }
            goto L_0x0067
        L_0x0059:
            org.reactivestreams.Subscriber<? super T> r1 = r0.subscriber     // Catch:{ all -> 0x005f }
            r1.onError(r5)     // Catch:{ all -> 0x005f }
            goto L_0x0067
        L_0x005f:
            r5 = move-exception
            kotlin.coroutines.CoroutineContext r0 = r0.getCoroutineContext()
            kotlinx.coroutines.CoroutineExceptionHandlerKt.handleCoroutineException(r0, r5)
        L_0x0067:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.FlowSubscription.flowProcessing(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object consumeFlow(Continuation<? super Unit> continuation) {
        Object collect = this.flow.collect(new FlowSubscription$consumeFlow$$inlined$collect$1(this), continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }

    public void cancel() {
        cancel((CancellationException) null);
    }

    public void request(long j) {
        long j2;
        long j3;
        Continuation continuation;
        if (j > 0) {
            do {
                j2 = this.requested;
                j3 = j2 + j;
                if (j3 <= 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!requested$FU.compareAndSet(this, j2, j3));
            if (j2 <= 0) {
                do {
                    continuation = (Continuation) producer$FU.getAndSet(this, (Object) null);
                } while (continuation == null);
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m1023constructorimpl(unit));
            }
        }
    }
}
