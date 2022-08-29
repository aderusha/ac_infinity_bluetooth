package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 1, 15})
@DebugMetadata(mo28221c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1", mo28222f = "FlowLiveData.kt", mo28223i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 2}, mo28224l = {91, 95, 96}, mo28225m = "invokeSuspend", mo28226n = {"$this$flow", "channel", "observer", "$this$flow", "channel", "observer", "$this$flow", "channel", "observer", "value"}, mo28227s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: FlowLiveData.kt */
final class FlowLiveDataConversions$asFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ LiveData $this_asFlow;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private FlowCollector f47p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowLiveDataConversions$asFlow$1(LiveData liveData, Continuation continuation) {
        super(2, continuation);
        this.$this_asFlow = liveData;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowLiveDataConversions$asFlow$1 flowLiveDataConversions$asFlow$1 = new FlowLiveDataConversions$asFlow$1(this.$this_asFlow, continuation);
        flowLiveDataConversions$asFlow$1.f47p$ = (FlowCollector) obj;
        return flowLiveDataConversions$asFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowLiveDataConversions$asFlow$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ab A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bb A[Catch:{ all -> 0x00f3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r1 = r17
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x0063
            if (r2 == r5) goto L_0x0053
            if (r2 == r4) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r2 = r1.L$4
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r5 = r1.L$2
            androidx.lifecycle.Observer r5 = (androidx.lifecycle.Observer) r5
            java.lang.Object r7 = r1.L$1
            kotlinx.coroutines.channels.Channel r7 = (kotlinx.coroutines.channels.Channel) r7
            java.lang.Object r8 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.throwOnFailure(r18)     // Catch:{ all -> 0x004f }
            r9 = r1
        L_0x0028:
            r16 = r8
            r8 = r2
            r2 = r5
            r5 = r7
            r7 = r16
            goto L_0x009b
        L_0x0030:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0038:
            java.lang.Object r2 = r1.L$3
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r5 = r1.L$2
            androidx.lifecycle.Observer r5 = (androidx.lifecycle.Observer) r5
            java.lang.Object r7 = r1.L$1
            kotlinx.coroutines.channels.Channel r7 = (kotlinx.coroutines.channels.Channel) r7
            java.lang.Object r8 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.throwOnFailure(r18)     // Catch:{ all -> 0x004f }
            r10 = r18
            r9 = r1
            goto L_0x00b3
        L_0x004f:
            r0 = move-exception
            r9 = r1
            goto L_0x00fa
        L_0x0053:
            java.lang.Object r2 = r1.L$2
            androidx.lifecycle.Observer r2 = (androidx.lifecycle.Observer) r2
            java.lang.Object r5 = r1.L$1
            kotlinx.coroutines.channels.Channel r5 = (kotlinx.coroutines.channels.Channel) r5
            java.lang.Object r7 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            kotlin.ResultKt.throwOnFailure(r18)
            goto L_0x0096
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r18)
            kotlinx.coroutines.flow.FlowCollector r7 = r1.f47p$
            r2 = -1
            kotlinx.coroutines.channels.Channel r2 = kotlinx.coroutines.channels.ChannelKt.Channel$default(r2, (kotlinx.coroutines.channels.BufferOverflow) null, (kotlin.jvm.functions.Function1) null, 6, (java.lang.Object) null)
            androidx.lifecycle.FlowLiveDataConversions$asFlow$1$observer$1 r8 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$observer$1
            r8.<init>(r2)
            androidx.lifecycle.Observer r8 = (androidx.lifecycle.Observer) r8
            kotlinx.coroutines.MainCoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getMain()
            kotlinx.coroutines.MainCoroutineDispatcher r9 = r9.getImmediate()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1 r10 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1
            r10.<init>(r1, r8, r6)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r1.L$0 = r7
            r1.L$1 = r2
            r1.L$2 = r8
            r1.label = r5
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r1)
            if (r5 != r0) goto L_0x0094
            return r0
        L_0x0094:
            r5 = r2
            r2 = r8
        L_0x0096:
            kotlinx.coroutines.channels.ChannelIterator r8 = r5.iterator()     // Catch:{ all -> 0x00f7 }
            r9 = r1
        L_0x009b:
            r9.L$0 = r7     // Catch:{ all -> 0x00f5 }
            r9.L$1 = r5     // Catch:{ all -> 0x00f5 }
            r9.L$2 = r2     // Catch:{ all -> 0x00f5 }
            r9.L$3 = r8     // Catch:{ all -> 0x00f5 }
            r9.label = r4     // Catch:{ all -> 0x00f5 }
            java.lang.Object r10 = r8.hasNext(r9)     // Catch:{ all -> 0x00f5 }
            if (r10 != r0) goto L_0x00ac
            return r0
        L_0x00ac:
            r16 = r5
            r5 = r2
            r2 = r8
            r8 = r7
            r7 = r16
        L_0x00b3:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x00f3 }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x00f3 }
            if (r10 == 0) goto L_0x00d2
            java.lang.Object r10 = r2.next()     // Catch:{ all -> 0x00f3 }
            r9.L$0 = r8     // Catch:{ all -> 0x00f3 }
            r9.L$1 = r7     // Catch:{ all -> 0x00f3 }
            r9.L$2 = r5     // Catch:{ all -> 0x00f3 }
            r9.L$3 = r10     // Catch:{ all -> 0x00f3 }
            r9.L$4 = r2     // Catch:{ all -> 0x00f3 }
            r9.label = r3     // Catch:{ all -> 0x00f3 }
            java.lang.Object r10 = r8.emit(r10, r9)     // Catch:{ all -> 0x00f3 }
            if (r10 != r0) goto L_0x0028
            return r0
        L_0x00d2:
            kotlinx.coroutines.GlobalScope r0 = kotlinx.coroutines.GlobalScope.INSTANCE
            r10 = r0
            kotlinx.coroutines.CoroutineScope r10 = (kotlinx.coroutines.CoroutineScope) r10
            kotlinx.coroutines.MainCoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getMain()
            kotlinx.coroutines.MainCoroutineDispatcher r0 = r0.getImmediate()
            r11 = r0
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            r12 = 0
            androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2 r0 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2
            r0.<init>(r9, r5, r6)
            r13 = r0
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            r14 = 2
            r15 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r10, r11, r12, r13, r14, r15)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00f3:
            r0 = move-exception
            goto L_0x00fa
        L_0x00f5:
            r0 = move-exception
            goto L_0x00f9
        L_0x00f7:
            r0 = move-exception
            r9 = r1
        L_0x00f9:
            r5 = r2
        L_0x00fa:
            kotlinx.coroutines.GlobalScope r2 = kotlinx.coroutines.GlobalScope.INSTANCE
            r10 = r2
            kotlinx.coroutines.CoroutineScope r10 = (kotlinx.coroutines.CoroutineScope) r10
            kotlinx.coroutines.MainCoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getMain()
            kotlinx.coroutines.MainCoroutineDispatcher r2 = r2.getImmediate()
            r11 = r2
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            r12 = 0
            androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2 r2 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2
            r2.<init>(r9, r5, r6)
            r13 = r2
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            r14 = 2
            r15 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r10, r11, r12, r13, r14, r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.FlowLiveDataConversions$asFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
