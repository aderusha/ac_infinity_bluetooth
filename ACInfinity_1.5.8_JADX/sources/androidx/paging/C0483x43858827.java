package androidx.paging;

import androidx.paging.PageFetcher$injectRemoteEvents$1;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.PageFetcher$injectRemoteEvents$1$2$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C0483x43858827 implements FlowCollector<LoadStates> {
    final /* synthetic */ Ref.ObjectRef $prev$inlined;
    final /* synthetic */ PageFetcher$injectRemoteEvents$1.C04912 this$0;

    public C0483x43858827(PageFetcher$injectRemoteEvents$1.C04912 r1, Ref.ObjectRef objectRef) {
        this.this$0 = r1;
        this.$prev$inlined = objectRef;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.paging.C0483x43858827.C04841
            if (r0 == 0) goto L_0x0014
            r0 = r10
            androidx.paging.PageFetcher$injectRemoteEvents$1$2$invokeSuspend$$inlined$collect$1$1 r0 = (androidx.paging.C0483x43858827.C04841) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            androidx.paging.PageFetcher$injectRemoteEvents$1$2$invokeSuspend$$inlined$collect$1$1 r0 = new androidx.paging.PageFetcher$injectRemoteEvents$1$2$invokeSuspend$$inlined$collect$1$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x005a
            if (r2 == r5) goto L_0x004e
            if (r2 == r4) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r9 = r0.L$1
            androidx.paging.LoadStates r9 = (androidx.paging.LoadStates) r9
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcher$injectRemoteEvents$1$2$invokeSuspend$$inlined$collect$1 r0 = (androidx.paging.C0483x43858827) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x010f
        L_0x0039:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0041:
            java.lang.Object r9 = r0.L$1
            androidx.paging.LoadStates r9 = (androidx.paging.LoadStates) r9
            java.lang.Object r2 = r0.L$0
            androidx.paging.PageFetcher$injectRemoteEvents$1$2$invokeSuspend$$inlined$collect$1 r2 = (androidx.paging.C0483x43858827) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00d5
        L_0x004e:
            java.lang.Object r9 = r0.L$1
            androidx.paging.LoadStates r9 = (androidx.paging.LoadStates) r9
            java.lang.Object r2 = r0.L$0
            androidx.paging.PageFetcher$injectRemoteEvents$1$2$invokeSuspend$$inlined$collect$1 r2 = (androidx.paging.C0483x43858827) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x009c
        L_0x005a:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r0
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            androidx.paging.LoadStates r9 = (androidx.paging.LoadStates) r9
            kotlin.jvm.internal.Ref$ObjectRef r10 = r8.$prev$inlined
            T r10 = r10.element
            androidx.paging.LoadStates r10 = (androidx.paging.LoadStates) r10
            androidx.paging.LoadState r10 = r10.getRefresh()
            androidx.paging.LoadState r2 = r9.getRefresh()
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r2)
            r10 = r10 ^ r5
            if (r10 == 0) goto L_0x009b
            androidx.paging.PageFetcher$injectRemoteEvents$1$2 r10 = r8.this$0
            androidx.paging.MutableLoadStateCollection r10 = r1
            androidx.paging.LoadType r2 = androidx.paging.LoadType.REFRESH
            androidx.paging.LoadState r6 = r9.getRefresh()
            r10.set(r2, r5, r6)
            androidx.paging.PageFetcher$injectRemoteEvents$1$2 r10 = r8.this$0
            androidx.paging.PageFetcher$injectRemoteEvents$1$1 r10 = r3
            androidx.paging.LoadType r2 = androidx.paging.LoadType.REFRESH
            androidx.paging.LoadState r6 = r9.getRefresh()
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r10.invoke((androidx.paging.LoadType) r2, (androidx.paging.LoadState) r6, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r10 != r1) goto L_0x009b
            return r1
        L_0x009b:
            r2 = r8
        L_0x009c:
            kotlin.jvm.internal.Ref$ObjectRef r10 = r2.$prev$inlined
            T r10 = r10.element
            androidx.paging.LoadStates r10 = (androidx.paging.LoadStates) r10
            androidx.paging.LoadState r10 = r10.getPrepend()
            androidx.paging.LoadState r6 = r9.getPrepend()
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r6)
            r10 = r10 ^ r5
            if (r10 == 0) goto L_0x00d5
            androidx.paging.PageFetcher$injectRemoteEvents$1$2 r10 = r2.this$0
            androidx.paging.MutableLoadStateCollection r10 = r1
            androidx.paging.LoadType r6 = androidx.paging.LoadType.PREPEND
            androidx.paging.LoadState r7 = r9.getPrepend()
            r10.set(r6, r5, r7)
            androidx.paging.PageFetcher$injectRemoteEvents$1$2 r10 = r2.this$0
            androidx.paging.PageFetcher$injectRemoteEvents$1$1 r10 = r3
            androidx.paging.LoadType r6 = androidx.paging.LoadType.PREPEND
            androidx.paging.LoadState r7 = r9.getPrepend()
            r0.L$0 = r2
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r10 = r10.invoke((androidx.paging.LoadType) r6, (androidx.paging.LoadState) r7, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r10 != r1) goto L_0x00d5
            return r1
        L_0x00d5:
            kotlin.jvm.internal.Ref$ObjectRef r10 = r2.$prev$inlined
            T r10 = r10.element
            androidx.paging.LoadStates r10 = (androidx.paging.LoadStates) r10
            androidx.paging.LoadState r10 = r10.getAppend()
            androidx.paging.LoadState r4 = r9.getAppend()
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r4)
            r10 = r10 ^ r5
            if (r10 == 0) goto L_0x0110
            androidx.paging.PageFetcher$injectRemoteEvents$1$2 r10 = r2.this$0
            androidx.paging.MutableLoadStateCollection r10 = r1
            androidx.paging.LoadType r4 = androidx.paging.LoadType.APPEND
            androidx.paging.LoadState r6 = r9.getAppend()
            r10.set(r4, r5, r6)
            androidx.paging.PageFetcher$injectRemoteEvents$1$2 r10 = r2.this$0
            androidx.paging.PageFetcher$injectRemoteEvents$1$1 r10 = r3
            androidx.paging.LoadType r4 = androidx.paging.LoadType.APPEND
            androidx.paging.LoadState r5 = r9.getAppend()
            r0.L$0 = r2
            r0.L$1 = r9
            r0.label = r3
            java.lang.Object r10 = r10.invoke((androidx.paging.LoadType) r4, (androidx.paging.LoadState) r5, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r10 != r1) goto L_0x010e
            return r1
        L_0x010e:
            r0 = r2
        L_0x010f:
            r2 = r0
        L_0x0110:
            kotlin.jvm.internal.Ref$ObjectRef r10 = r2.$prev$inlined
            r10.element = r9
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.C0483x43858827.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
