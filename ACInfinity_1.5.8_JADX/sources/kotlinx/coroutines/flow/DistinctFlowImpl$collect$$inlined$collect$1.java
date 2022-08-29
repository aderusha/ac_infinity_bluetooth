package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

@Metadata(mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Collect.kt */
public final class DistinctFlowImpl$collect$$inlined$collect$1 implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $collector$inlined;
    final /* synthetic */ Ref.ObjectRef $previousKey$inlined;
    final /* synthetic */ DistinctFlowImpl this$0;

    public DistinctFlowImpl$collect$$inlined$collect$1(DistinctFlowImpl distinctFlowImpl, Ref.ObjectRef objectRef, FlowCollector flowCollector) {
        this.this$0 = distinctFlowImpl;
        this.$previousKey$inlined = objectRef;
        this.$collector$inlined = flowCollector;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(T r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1.C38191
            if (r0 == 0) goto L_0x0014
            r0 = r7
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1.C38191) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x006b
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r0
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            kotlinx.coroutines.flow.DistinctFlowImpl r7 = r5.this$0
            kotlin.jvm.functions.Function1<T, java.lang.Object> r7 = r7.keySelector
            java.lang.Object r7 = r7.invoke(r6)
            kotlin.jvm.internal.Ref$ObjectRef r2 = r5.$previousKey$inlined
            T r2 = r2.element
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r2 == r4) goto L_0x005c
            kotlinx.coroutines.flow.DistinctFlowImpl r2 = r5.this$0
            kotlin.jvm.functions.Function2<java.lang.Object, java.lang.Object, java.lang.Boolean> r2 = r2.areEquivalent
            kotlin.jvm.internal.Ref$ObjectRef r4 = r5.$previousKey$inlined
            T r4 = r4.element
            java.lang.Object r2 = r2.invoke(r4, r7)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L_0x006b
        L_0x005c:
            kotlin.jvm.internal.Ref$ObjectRef r2 = r5.$previousKey$inlined
            r2.element = r7
            kotlinx.coroutines.flow.FlowCollector r7 = r5.$collector$inlined
            r0.label = r3
            java.lang.Object r6 = r7.emit(r6, r0)
            if (r6 != r1) goto L_0x006b
            return r1
        L_0x006b:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
