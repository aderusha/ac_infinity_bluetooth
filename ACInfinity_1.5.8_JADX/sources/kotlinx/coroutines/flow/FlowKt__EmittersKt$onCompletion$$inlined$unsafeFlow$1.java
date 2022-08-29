package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;

@Metadata(mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: SafeCollector.common.kt */
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 implements Flow<T> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ Flow $this_onCompletion$inlined;

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.C38341
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.C38341) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x0056
            if (r2 == r5) goto L_0x0048
            if (r2 == r4) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.flow.internal.SafeCollector r9 = (kotlinx.coroutines.flow.internal.SafeCollector) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0035 }
            goto L_0x008d
        L_0x0035:
            r10 = move-exception
            goto L_0x0097
        L_0x0037:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003f:
            java.lang.Object r9 = r0.L$0
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00b3
        L_0x0048:
            java.lang.Object r9 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 r2 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) r2
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0054 }
            goto L_0x006c
        L_0x0054:
            r9 = move-exception
            goto L_0x009d
        L_0x0056:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r0
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            kotlinx.coroutines.flow.Flow r10 = r8.$this_onCompletion$inlined     // Catch:{ all -> 0x009b }
            r0.L$0 = r8     // Catch:{ all -> 0x009b }
            r0.L$1 = r9     // Catch:{ all -> 0x009b }
            r0.label = r5     // Catch:{ all -> 0x009b }
            java.lang.Object r10 = r10.collect(r9, r0)     // Catch:{ all -> 0x009b }
            if (r10 != r1) goto L_0x006b
            return r1
        L_0x006b:
            r2 = r8
        L_0x006c:
            kotlinx.coroutines.flow.internal.SafeCollector r10 = new kotlinx.coroutines.flow.internal.SafeCollector
            kotlin.coroutines.CoroutineContext r4 = r0.getContext()
            r10.<init>(r9, r4)
            kotlin.jvm.functions.Function3 r9 = r2.$action$inlined     // Catch:{ all -> 0x0093 }
            r0.L$0 = r10     // Catch:{ all -> 0x0093 }
            r0.L$1 = r6     // Catch:{ all -> 0x0093 }
            r0.label = r3     // Catch:{ all -> 0x0093 }
            r2 = 6
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0093 }
            java.lang.Object r9 = r9.invoke(r10, r6, r0)     // Catch:{ all -> 0x0093 }
            r0 = 7
            kotlin.jvm.internal.InlineMarker.mark((int) r0)     // Catch:{ all -> 0x0093 }
            if (r9 != r1) goto L_0x008c
            return r1
        L_0x008c:
            r9 = r10
        L_0x008d:
            r9.releaseIntercepted()
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x0093:
            r9 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        L_0x0097:
            r9.releaseIntercepted()
            throw r10
        L_0x009b:
            r9 = move-exception
            r2 = r8
        L_0x009d:
            kotlinx.coroutines.flow.ThrowingCollector r10 = new kotlinx.coroutines.flow.ThrowingCollector
            r10.<init>(r9)
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            kotlin.jvm.functions.Function3 r2 = r2.$action$inlined
            r0.L$0 = r9
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r10 = kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(r10, r2, r9, r0)
            if (r10 != r1) goto L_0x00b3
            return r1
        L_0x00b3:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.$this_onCompletion$inlined = flow;
        this.$action$inlined = function3;
    }
}
