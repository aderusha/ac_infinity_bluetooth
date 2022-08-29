package androidx.paging;

import kotlin.Metadata;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.PageFetcher$injectRemoteEvents$1$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C0485x8e1fc4b5 implements FlowCollector<PageEvent<Value>> {
    final /* synthetic */ MutableLoadStateCollection $loadStates$inlined;
    final /* synthetic */ SimpleProducerScope $this_simpleChannelFlow$inlined;
    final /* synthetic */ PageFetcher$injectRemoteEvents$1 this$0;

    public C0485x8e1fc4b5(PageFetcher$injectRemoteEvents$1 pageFetcher$injectRemoteEvents$1, SimpleProducerScope simpleProducerScope, MutableLoadStateCollection mutableLoadStateCollection) {
        this.this$0 = pageFetcher$injectRemoteEvents$1;
        this.$this_simpleChannelFlow$inlined = simpleProducerScope;
        this.$loadStates$inlined = mutableLoadStateCollection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r17, kotlin.coroutines.Continuation r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            boolean r2 = r1 instanceof androidx.paging.C0485x8e1fc4b5.C04861
            if (r2 == 0) goto L_0x0018
            r2 = r1
            androidx.paging.PageFetcher$injectRemoteEvents$1$invokeSuspend$$inlined$collect$1$1 r2 = (androidx.paging.C0485x8e1fc4b5.C04861) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            androidx.paging.PageFetcher$injectRemoteEvents$1$invokeSuspend$$inlined$collect$1$1 r2 = new androidx.paging.PageFetcher$injectRemoteEvents$1$invokeSuspend$$inlined$collect$1$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 3
            r6 = 2
            r7 = 1
            if (r4 == 0) goto L_0x003e
            if (r4 == r7) goto L_0x0039
            if (r4 == r6) goto L_0x0039
            if (r4 != r5) goto L_0x0031
            goto L_0x0039
        L_0x0031:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00cd
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r2
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r1 = r17
            androidx.paging.PageEvent r1 = (androidx.paging.PageEvent) r1
            boolean r4 = r1 instanceof androidx.paging.PageEvent.Insert
            if (r4 == 0) goto L_0x0086
            androidx.paging.MutableLoadStateCollection r4 = r0.$loadStates$inlined
            r8 = r1
            androidx.paging.PageEvent$Insert r8 = (androidx.paging.PageEvent.Insert) r8
            androidx.paging.CombinedLoadStates r1 = r8.getCombinedLoadStates()
            androidx.paging.LoadStates r1 = r1.getSource()
            androidx.paging.PageFetcher$injectRemoteEvents$1 r5 = r0.this$0
            androidx.paging.RemoteMediatorAccessor r5 = r5.$accessor
            kotlinx.coroutines.flow.StateFlow r5 = r5.getState()
            java.lang.Object r5 = r5.getValue()
            androidx.paging.LoadStates r5 = (androidx.paging.LoadStates) r5
            r4.set(r1, r5)
            androidx.paging.SimpleProducerScope r1 = r0.$this_simpleChannelFlow$inlined
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            androidx.paging.MutableLoadStateCollection r4 = r0.$loadStates$inlined
            androidx.paging.CombinedLoadStates r13 = r4.snapshot()
            r14 = 15
            r15 = 0
            androidx.paging.PageEvent$Insert r4 = androidx.paging.PageEvent.Insert.copy$default(r8, r9, r10, r11, r12, r13, r14, r15)
            r2.label = r7
            java.lang.Object r1 = r1.send(r4, r2)
            if (r1 != r3) goto L_0x00cd
            return r3
        L_0x0086:
            boolean r4 = r1 instanceof androidx.paging.PageEvent.Drop
            if (r4 == 0) goto L_0x00aa
            androidx.paging.MutableLoadStateCollection r4 = r0.$loadStates$inlined
            r5 = r1
            androidx.paging.PageEvent$Drop r5 = (androidx.paging.PageEvent.Drop) r5
            androidx.paging.LoadType r5 = r5.getLoadType()
            r7 = 0
            androidx.paging.LoadState$NotLoading$Companion r8 = androidx.paging.LoadState.NotLoading.Companion
            androidx.paging.LoadState$NotLoading r8 = r8.getIncomplete$paging_common()
            androidx.paging.LoadState r8 = (androidx.paging.LoadState) r8
            r4.set(r5, r7, r8)
            androidx.paging.SimpleProducerScope r4 = r0.$this_simpleChannelFlow$inlined
            r2.label = r6
            java.lang.Object r1 = r4.send(r1, r2)
            if (r1 != r3) goto L_0x00cd
            return r3
        L_0x00aa:
            boolean r4 = r1 instanceof androidx.paging.PageEvent.LoadStateUpdate
            if (r4 == 0) goto L_0x00cd
            androidx.paging.MutableLoadStateCollection r4 = r0.$loadStates$inlined
            r6 = r1
            androidx.paging.PageEvent$LoadStateUpdate r6 = (androidx.paging.PageEvent.LoadStateUpdate) r6
            androidx.paging.LoadType r7 = r6.getLoadType()
            boolean r8 = r6.getFromMediator()
            androidx.paging.LoadState r6 = r6.getLoadState()
            r4.set(r7, r8, r6)
            androidx.paging.SimpleProducerScope r4 = r0.$this_simpleChannelFlow$inlined
            r2.label = r5
            java.lang.Object r1 = r4.send(r1, r2)
            if (r1 != r3) goto L_0x00cd
            return r3
        L_0x00cd:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.C0485x8e1fc4b5.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
