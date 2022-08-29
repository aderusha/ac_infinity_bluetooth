package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

@Metadata(mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$lambda-12$$inlined$collect$1  reason: invalid class name */
/* compiled from: Collect.kt */
public final class FlowKt__TransformKt$runningReduce$lambda12$$inlined$collect$1 implements FlowCollector<T> {
    final /* synthetic */ Ref.ObjectRef $accumulator$inlined;
    final /* synthetic */ Function3 $operation$inlined;
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

    public FlowKt__TransformKt$runningReduce$lambda12$$inlined$collect$1(Ref.ObjectRef objectRef, Function3 function3, FlowCollector flowCollector) {
        this.$accumulator$inlined = objectRef;
        this.$operation$inlined = function3;
        this.$this_unsafeFlow$inlined = flowCollector;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0084 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(T r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$lambda12$$inlined$collect$1.C38811
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$lambda-12$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$lambda12$$inlined$collect$1.C38811) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$lambda-12$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$lambda-12$$inlined$collect$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0085
        L_0x002d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0035:
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$lambda-12$$inlined$collect$1 r2 = (kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$lambda12$$inlined$collect$1) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r6 = r9
            r9 = r8
            r8 = r6
            goto L_0x006f
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlin.jvm.internal.Ref$ObjectRef r9 = r7.$accumulator$inlined
            T r2 = r9.element
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r2 != r5) goto L_0x0054
        L_0x0052:
            r2 = r7
            goto L_0x006f
        L_0x0054:
            kotlin.jvm.functions.Function3 r2 = r7.$operation$inlined
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
            if (r8 != r1) goto L_0x0052
            return r1
        L_0x006f:
            r9.element = r8
            kotlinx.coroutines.flow.FlowCollector r8 = r2.$this_unsafeFlow$inlined
            kotlin.jvm.internal.Ref$ObjectRef r9 = r2.$accumulator$inlined
            T r9 = r9.element
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r8 = r8.emit(r9, r0)
            if (r8 != r1) goto L_0x0085
            return r1
        L_0x0085:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$runningReduce$lambda12$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
