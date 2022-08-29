package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, mo27512d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: SafeCollector.common.kt */
public final class CachedPagingDataKt$cachedIn$$inlined$map$1 implements Flow<MulticastedPagingData<T>> {
    final /* synthetic */ CoroutineScope $scope$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    public CachedPagingDataKt$cachedIn$$inlined$map$1(Flow flow, CoroutineScope coroutineScope) {
        this.$this_unsafeTransform$inlined = flow;
        this.$scope$inlined = coroutineScope;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new FlowCollector<PagingData<T>>() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object emit(java.lang.Object r11, kotlin.coroutines.Continuation r12) {
                /*
                    r10 = this;
                    boolean r0 = r12 instanceof androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1.C04652.C04661
                    if (r0 == 0) goto L_0x0014
                    r0 = r12
                    androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1$2$1 r0 = (androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1.C04652.C04661) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r12 = r0.label
                    int r12 = r12 - r2
                    r0.label = r12
                    goto L_0x0019
                L_0x0014:
                    androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1$2$1 r0 = new androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1$2$1
                    r0.<init>(r10, r12)
                L_0x0019:
                    java.lang.Object r12 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x0032
                    if (r2 != r3) goto L_0x002a
                    kotlin.ResultKt.throwOnFailure(r12)
                    goto L_0x0053
                L_0x002a:
                    java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                    java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                    r11.<init>(r12)
                    throw r11
                L_0x0032:
                    kotlin.ResultKt.throwOnFailure(r12)
                    kotlinx.coroutines.flow.FlowCollector r12 = r3
                    r2 = r0
                    kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                    r6 = r11
                    androidx.paging.PagingData r6 = (androidx.paging.PagingData) r6
                    androidx.paging.MulticastedPagingData r11 = new androidx.paging.MulticastedPagingData
                    androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1 r2 = r2
                    kotlinx.coroutines.CoroutineScope r5 = r2.$scope$inlined
                    r7 = 0
                    r8 = 4
                    r9 = 0
                    r4 = r11
                    r4.<init>(r5, r6, r7, r8, r9)
                    r0.label = r3
                    java.lang.Object r11 = r12.emit(r11, r0)
                    if (r11 != r1) goto L_0x0053
                    return r1
                L_0x0053:
                    kotlin.Unit r11 = kotlin.Unit.INSTANCE
                    return r11
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1.C04652.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
