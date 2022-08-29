package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, mo27512d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeTransform$1"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.PageFetcherSnapshot$startConsumingHints$1$invokeSuspend$$inlined$filter$1 */
/* compiled from: SafeCollector.common.kt */
public final class C0503x9fc5dc78 implements Flow<ViewportHint> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ PageFetcherSnapshot$startConsumingHints$1 this$0;

    public C0503x9fc5dc78(Flow flow, PageFetcherSnapshot$startConsumingHints$1 pageFetcherSnapshot$startConsumingHints$1) {
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = pageFetcherSnapshot$startConsumingHints$1;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new FlowCollector<ViewportHint>() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                /*
                    r6 = this;
                    boolean r0 = r8 instanceof androidx.paging.C0503x9fc5dc78.C05042.C05051
                    if (r0 == 0) goto L_0x0014
                    r0 = r8
                    androidx.paging.PageFetcherSnapshot$startConsumingHints$1$invokeSuspend$$inlined$filter$1$2$1 r0 = (androidx.paging.C0503x9fc5dc78.C05042.C05051) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r8 = r0.label
                    int r8 = r8 - r2
                    r0.label = r8
                    goto L_0x0019
                L_0x0014:
                    androidx.paging.PageFetcherSnapshot$startConsumingHints$1$invokeSuspend$$inlined$filter$1$2$1 r0 = new androidx.paging.PageFetcherSnapshot$startConsumingHints$1$invokeSuspend$$inlined$filter$1$2$1
                    r0.<init>(r6, r8)
                L_0x0019:
                    java.lang.Object r8 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x0032
                    if (r2 != r3) goto L_0x002a
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L_0x007c
                L_0x002a:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r8)
                    throw r7
                L_0x0032:
                    kotlin.ResultKt.throwOnFailure(r8)
                    kotlinx.coroutines.flow.FlowCollector r8 = r3
                    r2 = r0
                    kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                    r2 = r7
                    androidx.paging.ViewportHint r2 = (androidx.paging.ViewportHint) r2
                    int r4 = r2.getPresentedItemsBefore()
                    int r4 = r4 * -1
                    androidx.paging.PageFetcherSnapshot$startConsumingHints$1$invokeSuspend$$inlined$filter$1 r5 = r2
                    androidx.paging.PageFetcherSnapshot$startConsumingHints$1 r5 = r5.this$0
                    androidx.paging.PageFetcherSnapshot r5 = r5.this$0
                    androidx.paging.PagingConfig r5 = r5.config
                    int r5 = r5.jumpThreshold
                    if (r4 > r5) goto L_0x0068
                    int r2 = r2.getPresentedItemsAfter()
                    int r2 = r2 * -1
                    androidx.paging.PageFetcherSnapshot$startConsumingHints$1$invokeSuspend$$inlined$filter$1 r4 = r2
                    androidx.paging.PageFetcherSnapshot$startConsumingHints$1 r4 = r4.this$0
                    androidx.paging.PageFetcherSnapshot r4 = r4.this$0
                    androidx.paging.PagingConfig r4 = r4.config
                    int r4 = r4.jumpThreshold
                    if (r2 <= r4) goto L_0x0066
                    goto L_0x0068
                L_0x0066:
                    r2 = 0
                    goto L_0x0069
                L_0x0068:
                    r2 = 1
                L_0x0069:
                    java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                    boolean r2 = r2.booleanValue()
                    if (r2 == 0) goto L_0x007f
                    r0.label = r3
                    java.lang.Object r7 = r8.emit(r7, r0)
                    if (r7 != r1) goto L_0x007c
                    return r1
                L_0x007c:
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    goto L_0x0081
                L_0x007f:
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                L_0x0081:
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.paging.C0503x9fc5dc78.C05042.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
