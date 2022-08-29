package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: Collect.kt */
public final class FlowSubscription$consumeFlow$$inlined$collect$1 implements FlowCollector<T> {
    final /* synthetic */ FlowSubscription this$0;

    public FlowSubscription$consumeFlow$$inlined$collect$1(FlowSubscription flowSubscription) {
        this.this$0 = flowSubscription;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1.C39131
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1$1 r0 = (kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1.C39131) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1$1 r0 = new kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1$1 r7 = (kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1.C39131) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1 r7 = (kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0087
        L_0x0032:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r0
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            kotlinx.coroutines.reactive.FlowSubscription r8 = r6.this$0
            org.reactivestreams.Subscriber<? super T> r8 = r8.subscriber
            r8.onNext(r7)
            kotlinx.coroutines.reactive.FlowSubscription r7 = r6.this$0
            java.util.concurrent.atomic.AtomicLongFieldUpdater r8 = kotlinx.coroutines.reactive.FlowSubscription.requested$FU
            long r7 = r8.decrementAndGet(r7)
            r4 = 0
            int r2 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r2 > 0) goto L_0x007e
            r0.L$0 = r6
            r0.L$1 = r0
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r7 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r7.<init>(r8, r3)
            r7.initCancellability()
            r8 = r7
            kotlinx.coroutines.CancellableContinuation r8 = (kotlinx.coroutines.CancellableContinuation) r8
            kotlinx.coroutines.reactive.FlowSubscription r2 = r6.this$0
            r2.producer = r8
            java.lang.Object r7 = r7.getResult()
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r7 != r8) goto L_0x007b
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L_0x007b:
            if (r7 != r1) goto L_0x0087
            return r1
        L_0x007e:
            kotlinx.coroutines.reactive.FlowSubscription r7 = r6.this$0
            kotlin.coroutines.CoroutineContext r7 = r7.getCoroutineContext()
            kotlinx.coroutines.JobKt.ensureActive((kotlin.coroutines.CoroutineContext) r7)
        L_0x0087:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.FlowSubscription$consumeFlow$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
