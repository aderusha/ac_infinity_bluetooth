package androidx.paging;

import androidx.paging.PageFetcherSnapshot$pageEventFlow$1;
import kotlin.Metadata;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.PageFetcherSnapshot$pageEventFlow$1$2$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C0498x5e48bbd2 implements FlowCollector<PageEvent<Value>> {
    final /* synthetic */ PageFetcherSnapshot$pageEventFlow$1.C05062 this$0;

    public C0498x5e48bbd2(PageFetcherSnapshot$pageEventFlow$1.C05062 r1) {
        this.this$0 = r1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.paging.C0498x5e48bbd2.C04991
            if (r0 == 0) goto L_0x0014
            r0 = r6
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$2$invokeSuspend$$inlined$collect$1$1 r0 = (androidx.paging.C0498x5e48bbd2.C04991) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$2$invokeSuspend$$inlined$collect$1$1 r0 = new androidx.paging.PageFetcherSnapshot$pageEventFlow$1$2$invokeSuspend$$inlined$collect$1$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ ClosedSendChannelException -> 0x0047 }
            goto L_0x0047
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = r0
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            androidx.paging.PageEvent r5 = (androidx.paging.PageEvent) r5
            androidx.paging.PageFetcherSnapshot$pageEventFlow$1$2 r6 = r4.this$0     // Catch:{ ClosedSendChannelException -> 0x0047 }
            androidx.paging.SimpleProducerScope r6 = r7     // Catch:{ ClosedSendChannelException -> 0x0047 }
            r0.label = r3     // Catch:{ ClosedSendChannelException -> 0x0047 }
            java.lang.Object r5 = r6.send(r5, r0)     // Catch:{ ClosedSendChannelException -> 0x0047 }
            if (r5 != r1) goto L_0x0047
            return r1
        L_0x0047:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.C0498x5e48bbd2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
