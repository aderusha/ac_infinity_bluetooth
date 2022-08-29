package androidx.paging;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: Collect.kt */
public final class FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1 implements FlowCollector<T> {
    final /* synthetic */ Ref.ObjectRef $accumulator$inlined;
    final /* synthetic */ FlowCollector $this_flow$inlined;
    final /* synthetic */ FlowExtKt$simpleScan$1 this$0;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1.C04731
            if (r0 == 0) goto L_0x0014
            r0 = r9
            androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1$1 r0 = (androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1.C04731) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1$1 r0 = new androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0080
        L_0x002d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0035:
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r2 = r0.L$0
            androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1 r2 = (androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x006a
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlin.jvm.internal.Ref$ObjectRef r9 = r7.$accumulator$inlined
            androidx.paging.FlowExtKt$simpleScan$1 r2 = r7.this$0
            kotlin.jvm.functions.Function3 r2 = r2.$operation
            kotlin.jvm.internal.Ref$ObjectRef r5 = r7.$accumulator$inlined
            T r5 = r5.element
            r0.L$0 = r7
            r0.L$1 = r9
            r0.label = r4
            r4 = 6
            kotlin.jvm.internal.InlineMarker.mark((int) r4)
            java.lang.Object r8 = r2.invoke(r5, r8, r0)
            r2 = 7
            kotlin.jvm.internal.InlineMarker.mark((int) r2)
            if (r8 != r1) goto L_0x0066
            return r1
        L_0x0066:
            r2 = r7
            r6 = r9
            r9 = r8
            r8 = r6
        L_0x006a:
            r8.element = r9
            kotlinx.coroutines.flow.FlowCollector r8 = r2.$this_flow$inlined
            kotlin.jvm.internal.Ref$ObjectRef r9 = r2.$accumulator$inlined
            T r9 = r9.element
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r8 = r8.emit(r9, r0)
            if (r8 != r1) goto L_0x0080
            return r1
        L_0x0080:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1(FlowExtKt$simpleScan$1 flowExtKt$simpleScan$1, FlowCollector flowCollector, Ref.ObjectRef objectRef) {
        this.this$0 = flowExtKt$simpleScan$1;
        this.$this_flow$inlined = flowCollector;
        this.$accumulator$inlined = objectRef;
    }
}
