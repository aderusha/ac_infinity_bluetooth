package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo27512d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "androidx/paging/FlowExtKt$simpleFlatMapLatest$1"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1", mo28222f = "PageFetcherSnapshot.kt", mo28223i = {0, 0}, mo28224l = {109, 130}, mo28225m = "invokeSuspend", mo28226n = {"this_$iv", "generationId"}, mo28227s = {"L$1", "I$0"})
/* renamed from: androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1 */
/* compiled from: FlowExt.kt */
public final class C0494xe29ec4fd extends SuspendLambda implements Function3<FlowCollector<? super GenerationalViewportHint>, Integer, Continuation<? super Unit>, Object> {
    final /* synthetic */ LoadType $loadType$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    private /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ PageFetcherSnapshot this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0494xe29ec4fd(Continuation continuation, PageFetcherSnapshot pageFetcherSnapshot, LoadType loadType) {
        super(3, continuation);
        this.this$0 = pageFetcherSnapshot;
        this.$loadType$inlined = loadType;
    }

    public final Continuation<Unit> create(FlowCollector<? super GenerationalViewportHint> flowCollector, Integer num, Continuation<? super Unit> continuation) {
        Intrinsics.checkNotNullParameter(flowCollector, "$this$create");
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        C0494xe29ec4fd pageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1 = new C0494xe29ec4fd(continuation, this.this$0, this.$loadType$inlined);
        pageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1.L$0 = flowCollector;
        pageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1.L$1 = num;
        return pageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((C0494xe29ec4fd) create((FlowCollector) obj, obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: kotlinx.coroutines.flow.FlowCollector} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x002e
            if (r1 == r3) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00c7
        L_0x0014:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x001c:
            int r1 = r10.I$0
            java.lang.Object r5 = r10.L$2
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r10.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r6 = (androidx.paging.PageFetcherSnapshotState.Holder) r6
            java.lang.Object r7 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x005c
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            r7 = r11
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            java.lang.Object r11 = r10.L$1
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            java.lang.Number r11 = (java.lang.Number) r11
            int r1 = r11.intValue()
            androidx.paging.PageFetcherSnapshot r11 = r10.this$0
            androidx.paging.PageFetcherSnapshotState$Holder r6 = r11.stateHolder
            kotlinx.coroutines.sync.Mutex r5 = r6.lock
            r10.L$0 = r7
            r10.L$1 = r6
            r10.L$2 = r5
            r10.I$0 = r1
            r10.label = r3
            java.lang.Object r11 = r5.lock(r4, r10)
            if (r11 != r0) goto L_0x005c
            return r0
        L_0x005c:
            androidx.paging.PageFetcherSnapshotState r11 = r6.state     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadStates r6 = r11.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadType r8 = r10.$loadType$inlined     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadState r6 = r6.get$paging_common(r8)     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadState$NotLoading$Companion r8 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadState$NotLoading r8 = r8.getComplete$paging_common()     // Catch:{ all -> 0x00ca }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r8)     // Catch:{ all -> 0x00ca }
            r8 = 0
            if (r6 == 0) goto L_0x0081
            androidx.paging.GenerationalViewportHint[] r11 = new androidx.paging.GenerationalViewportHint[r8]     // Catch:{ all -> 0x00ca }
            kotlinx.coroutines.flow.Flow r11 = kotlinx.coroutines.flow.FlowKt.flowOf((T[]) r11)     // Catch:{ all -> 0x00ca }
            r5.unlock(r4)
            goto L_0x00b8
        L_0x0081:
            androidx.paging.LoadStates r6 = r11.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadType r9 = r10.$loadType$inlined     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadState r6 = r6.get$paging_common(r9)     // Catch:{ all -> 0x00ca }
            boolean r6 = r6 instanceof androidx.paging.LoadState.Error     // Catch:{ all -> 0x00ca }
            if (r6 != 0) goto L_0x009c
            androidx.paging.LoadType r6 = r10.$loadType$inlined     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadState$NotLoading$Companion r9 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadState$NotLoading r9 = r9.getIncomplete$paging_common()     // Catch:{ all -> 0x00ca }
            androidx.paging.LoadState r9 = (androidx.paging.LoadState) r9     // Catch:{ all -> 0x00ca }
            r11.setSourceLoadState(r6, r9)     // Catch:{ all -> 0x00ca }
        L_0x009c:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00ca }
            r5.unlock(r4)
            androidx.paging.PageFetcherSnapshot r11 = r10.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r11 = r11.hintSharedFlow
            kotlinx.coroutines.flow.Flow r11 = (kotlinx.coroutines.flow.Flow) r11
            if (r1 != 0) goto L_0x00ac
            r3 = 0
        L_0x00ac:
            kotlinx.coroutines.flow.Flow r11 = kotlinx.coroutines.flow.FlowKt.drop(r11, r3)
            androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1$lambda$1 r3 = new androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1$lambda$1
            r3.<init>(r11, r1)
            r11 = r3
            kotlinx.coroutines.flow.Flow r11 = (kotlinx.coroutines.flow.Flow) r11
        L_0x00b8:
            r10.L$0 = r4
            r10.L$1 = r4
            r10.L$2 = r4
            r10.label = r2
            java.lang.Object r11 = r11.collect(r7, r10)
            if (r11 != r0) goto L_0x00c7
            return r0
        L_0x00c7:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00ca:
            r11 = move-exception
            r5.unlock(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.C0494xe29ec4fd.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
