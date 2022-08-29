package androidx.paging.multicast;

import kotlin.Metadata;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.multicast.SharedFlowProducer$collectionJob$1$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C0571x5d4b9271 implements FlowCollector<T> {
    final /* synthetic */ SharedFlowProducer$collectionJob$1 this$0;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof androidx.paging.multicast.C0571x5d4b9271.C05721
            if (r0 == 0) goto L_0x0014
            r0 = r9
            androidx.paging.multicast.SharedFlowProducer$collectionJob$1$invokeSuspend$$inlined$collect$1$1 r0 = (androidx.paging.multicast.C0571x5d4b9271.C05721) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            androidx.paging.multicast.SharedFlowProducer$collectionJob$1$invokeSuspend$$inlined$collect$1$1 r0 = new androidx.paging.multicast.SharedFlowProducer$collectionJob$1$invokeSuspend$$inlined$collect$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 == r5) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x006c
        L_0x002e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0036:
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.CompletableDeferred r8 = (kotlinx.coroutines.CompletableDeferred) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0061
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlinx.coroutines.CompletableDeferred r9 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r4, r5, r4)
            androidx.paging.multicast.SharedFlowProducer$collectionJob$1 r2 = r7.this$0
            androidx.paging.multicast.SharedFlowProducer r2 = r2.this$0
            kotlin.jvm.functions.Function2 r2 = r2.sendUpsteamMessage
            androidx.paging.multicast.ChannelManager$Message$Dispatch$Value r6 = new androidx.paging.multicast.ChannelManager$Message$Dispatch$Value
            r6.<init>(r8, r9)
            r0.L$0 = r9
            r0.label = r5
            java.lang.Object r8 = r2.invoke(r6, r0)
            if (r8 != r1) goto L_0x0060
            return r1
        L_0x0060:
            r8 = r9
        L_0x0061:
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r8 = r8.await(r0)
            if (r8 != r1) goto L_0x006c
            return r1
        L_0x006c:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.C0571x5d4b9271.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public C0571x5d4b9271(SharedFlowProducer$collectionJob$1 sharedFlowProducer$collectionJob$1) {
        this.this$0 = sharedFlowProducer$collectionJob$1;
    }
}
