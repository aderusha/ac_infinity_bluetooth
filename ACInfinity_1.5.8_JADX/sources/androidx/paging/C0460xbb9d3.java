package androidx.paging;

import kotlin.Metadata;
import kotlin.collections.IndexedValue;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C0460xbb9d3 implements FlowCollector<IndexedValue<? extends PageEvent<T>>> {
    final /* synthetic */ CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1 this$0;

    public C0460xbb9d3(CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1 cachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1) {
        this.this$0 = cachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.paging.C0460xbb9d3.C04611
            if (r0 == 0) goto L_0x0014
            r0 = r7
            androidx.paging.CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1$invokeSuspend$$inlined$collect$1$1 r0 = (androidx.paging.C0460xbb9d3.C04611) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            androidx.paging.CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1$invokeSuspend$$inlined$collect$1$1 r0 = new androidx.paging.CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1$invokeSuspend$$inlined$collect$1$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0084
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0035:
            java.lang.Object r6 = r0.L$1
            kotlin.collections.IndexedValue r6 = (kotlin.collections.IndexedValue) r6
            java.lang.Object r2 = r0.L$0
            androidx.paging.CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1$invokeSuspend$$inlined$collect$1 r2 = (androidx.paging.C0460xbb9d3) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0062
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r0
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            kotlin.collections.IndexedValue r6 = (kotlin.collections.IndexedValue) r6
            androidx.paging.CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1 r7 = r5.this$0
            androidx.paging.TemporaryDownstream r7 = r7.$snapshot
            r7.close()
            androidx.paging.CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1 r7 = r5.this$0
            kotlinx.coroutines.Job r7 = r7.$historyCollection
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r7.join(r0)
            if (r7 != r1) goto L_0x0061
            return r1
        L_0x0061:
            r2 = r5
        L_0x0062:
            int r7 = r6.getIndex()
            androidx.paging.CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1 r4 = r2.this$0
            kotlin.jvm.internal.Ref$IntRef r4 = r4.$lastReceivedHistoryIndex
            int r4 = r4.element
            if (r7 <= r4) goto L_0x0084
            androidx.paging.CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1 r7 = r2.this$0
            androidx.paging.SimpleProducerScope r7 = r7.$this_simpleChannelFlow
            java.lang.Object r6 = r6.getValue()
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r6 = r7.send(r6, r0)
            if (r6 != r1) goto L_0x0084
            return r1
        L_0x0084:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.C0460xbb9d3.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
