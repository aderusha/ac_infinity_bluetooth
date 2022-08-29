package androidx.paging;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\n"}, mo27512d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1", "androidx/paging/PagingDataTransforms$transform$$inlined$map$5"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: SafeCollector.common.kt */
public final class PagingDataTransforms$flatMap$$inlined$transform$2 implements Flow<PageEvent<R>> {
    final /* synthetic */ Executor $executor$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ Function1 $transform$inlined$1;

    public PagingDataTransforms$flatMap$$inlined$transform$2(Flow flow, Executor executor, Function1 function1) {
        this.$this_unsafeTransform$inlined = flow;
        this.$executor$inlined = executor;
        this.$transform$inlined$1 = function1;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new FlowCollector<PageEvent<T>>() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x0071 A[RETURN] */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                /*
                    r8 = this;
                    boolean r0 = r10 instanceof androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2.C05252.C05261
                    if (r0 == 0) goto L_0x0014
                    r0 = r10
                    androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1 r0 = (androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2.C05252.C05261) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r10 = r0.label
                    int r10 = r10 - r2
                    r0.label = r10
                    goto L_0x0019
                L_0x0014:
                    androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1 r0 = new androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1
                    r0.<init>(r8, r10)
                L_0x0019:
                    java.lang.Object r10 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 0
                    r4 = 2
                    r5 = 1
                    if (r2 == 0) goto L_0x003e
                    if (r2 == r5) goto L_0x0036
                    if (r2 != r4) goto L_0x002e
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L_0x0072
                L_0x002e:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r10)
                    throw r9
                L_0x0036:
                    java.lang.Object r9 = r0.L$0
                    kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L_0x0067
                L_0x003e:
                    kotlin.ResultKt.throwOnFailure(r10)
                    kotlinx.coroutines.flow.FlowCollector r10 = r3
                    androidx.paging.PageEvent r9 = (androidx.paging.PageEvent) r9
                    r2 = r0
                    kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                    androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2 r2 = r2
                    java.util.concurrent.Executor r2 = r2.$executor$inlined
                    kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.ExecutorsKt.from((java.util.concurrent.Executor) r2)
                    kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
                    androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$lambda$1 r6 = new androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$lambda$1
                    r6.<init>(r9, r3, r8)
                    kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                    r0.L$0 = r10
                    r0.label = r5
                    java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                    if (r9 != r1) goto L_0x0064
                    return r1
                L_0x0064:
                    r7 = r10
                    r10 = r9
                    r9 = r7
                L_0x0067:
                    r0.L$0 = r3
                    r0.label = r4
                    java.lang.Object r9 = r9.emit(r10, r0)
                    if (r9 != r1) goto L_0x0072
                    return r1
                L_0x0072:
                    kotlin.Unit r9 = kotlin.Unit.INSTANCE
                    return r9
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2.C05252.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
