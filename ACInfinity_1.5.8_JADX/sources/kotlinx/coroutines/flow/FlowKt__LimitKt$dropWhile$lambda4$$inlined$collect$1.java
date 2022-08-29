package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata(mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$lambda-4$$inlined$collect$1  reason: invalid class name */
/* compiled from: Collect.kt */
public final class FlowKt__LimitKt$dropWhile$lambda4$$inlined$collect$1 implements FlowCollector<T> {
    final /* synthetic */ Ref.BooleanRef $matched$inlined;
    final /* synthetic */ Function2 $predicate$inlined;
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

    public FlowKt__LimitKt$dropWhile$lambda4$$inlined$collect$1(Ref.BooleanRef booleanRef, FlowCollector flowCollector, Function2 function2) {
        this.$matched$inlined = booleanRef;
        this.$this_unsafeFlow$inlined = flowCollector;
        this.$predicate$inlined = function2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(T r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$lambda4$$inlined$collect$1.C38431
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$lambda-4$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$lambda4$$inlined$collect$1.C38431) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$lambda-4$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$lambda-4$$inlined$collect$1$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r5) goto L_0x0042
            if (r2 == r4) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0091
        L_0x0030:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0038:
            java.lang.Object r7 = r0.L$1
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$lambda-4$$inlined$collect$1 r2 = (kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$lambda4$$inlined$collect$1) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0075
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0091
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r0
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            kotlin.jvm.internal.Ref$BooleanRef r8 = r6.$matched$inlined
            boolean r8 = r8.element
            if (r8 == 0) goto L_0x005d
            kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow$inlined
            r0.label = r5
            java.lang.Object r7 = r8.emit(r7, r0)
            if (r7 != r1) goto L_0x0091
            return r1
        L_0x005d:
            kotlin.jvm.functions.Function2 r8 = r6.$predicate$inlined
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            r2 = 6
            kotlin.jvm.internal.InlineMarker.mark((int) r2)
            java.lang.Object r8 = r8.invoke(r7, r0)
            r2 = 7
            kotlin.jvm.internal.InlineMarker.mark((int) r2)
            if (r8 != r1) goto L_0x0074
            return r1
        L_0x0074:
            r2 = r6
        L_0x0075:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 != 0) goto L_0x0091
            kotlin.jvm.internal.Ref$BooleanRef r8 = r2.$matched$inlined
            r8.element = r5
            kotlinx.coroutines.flow.FlowCollector r8 = r2.$this_unsafeFlow$inlined
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r7 = r8.emit(r7, r0)
            if (r7 != r1) goto L_0x0091
            return r1
        L_0x0091:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$lambda4$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
