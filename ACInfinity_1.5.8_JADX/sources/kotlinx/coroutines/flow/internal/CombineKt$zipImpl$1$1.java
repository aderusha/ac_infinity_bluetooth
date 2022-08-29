package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H@"}, mo27512d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1", mo28222f = "Combine.kt", mo28223i = {0}, mo28224l = {129}, mo28225m = "invokeSuspend", mo28226n = {"second"}, mo28227s = {"L$0"})
/* compiled from: Combine.kt */
final class CombineKt$zipImpl$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T1> $flow;
    final /* synthetic */ Flow<T2> $flow2;
    final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
    final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CombineKt$zipImpl$1$1(FlowCollector<? super R> flowCollector, Flow<? extends T2> flow, Flow<? extends T1> flow2, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super CombineKt$zipImpl$1$1> continuation) {
        super(2, continuation);
        this.$this_unsafeFlow = flowCollector;
        this.$flow2 = flow;
        this.$flow = flow2;
        this.$transform = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$1 = new CombineKt$zipImpl$1$1(this.$this_unsafeFlow, this.$flow2, this.$flow, this.$transform, continuation);
        combineKt$zipImpl$1$1.L$0 = obj;
        return combineKt$zipImpl$1$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: kotlinx.coroutines.channels.ReceiveChannel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            r22 = this;
            r1 = r22
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0026
            if (r2 != r3) goto L_0x001e
            java.lang.Object r0 = r1.L$0
            r2 = r0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r23)     // Catch:{ AbortFlowException -> 0x001b }
            goto L_0x0098
        L_0x0018:
            r0 = move-exception
            goto L_0x00aa
        L_0x001b:
            r0 = move-exception
            goto L_0x00a1
        L_0x001e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r23)
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            r6 = 0
            r7 = 0
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$second$1 r5 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$second$1
            kotlinx.coroutines.flow.Flow<T2> r8 = r1.$flow2
            r5.<init>(r8, r4)
            r8 = r5
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r5 = r2
            kotlinx.coroutines.channels.ReceiveChannel r5 = kotlinx.coroutines.channels.ProduceKt.produce$default(r5, r6, r7, r8, r9, r10)
            kotlinx.coroutines.CompletableJob r6 = kotlinx.coroutines.JobKt.Job$default((kotlinx.coroutines.Job) r4, (int) r3, (java.lang.Object) r4)
            r7 = r5
            kotlinx.coroutines.channels.SendChannel r7 = (kotlinx.coroutines.channels.SendChannel) r7
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$1 r8 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$1
            kotlinx.coroutines.flow.FlowCollector<R> r9 = r1.$this_unsafeFlow
            r8.<init>(r6, r9)
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            r7.invokeOnClose(r8)
            kotlin.coroutines.CoroutineContext r13 = r2.getCoroutineContext()     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            java.lang.Object r14 = kotlinx.coroutines.internal.ThreadContextKt.threadContextElements(r13)     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            kotlin.coroutines.CoroutineContext r2 = r2.getCoroutineContext()     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            kotlin.coroutines.CoroutineContext r2 = r2.plus(r6)     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            r7 = 0
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2 r8 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            kotlinx.coroutines.flow.Flow<T1> r12 = r1.$flow     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            kotlinx.coroutines.flow.FlowCollector<R> r9 = r1.$this_unsafeFlow     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            kotlin.jvm.functions.Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r10 = r1.$transform     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            r18 = 0
            r11 = r8
            r15 = r5
            r16 = r9
            r17 = r10
            r11.<init>(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            r18 = r8
            kotlin.jvm.functions.Function2 r18 = (kotlin.jvm.functions.Function2) r18     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            r19 = r1
            kotlin.coroutines.Continuation r19 = (kotlin.coroutines.Continuation) r19     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            r20 = 4
            r21 = 0
            r1.L$0 = r5     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            r1.label = r3     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            r15 = r2
            r16 = r6
            r17 = r7
            java.lang.Object r2 = kotlinx.coroutines.flow.internal.ChannelFlowKt.withContextUndispatched$default(r15, r16, r17, r18, r19, r20, r21)     // Catch:{ AbortFlowException -> 0x009f, all -> 0x009c }
            if (r2 != r0) goto L_0x0097
            return r0
        L_0x0097:
            r2 = r5
        L_0x0098:
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r2, (java.util.concurrent.CancellationException) r4, (int) r3, (java.lang.Object) r4)
            goto L_0x00a7
        L_0x009c:
            r0 = move-exception
            r2 = r5
            goto L_0x00aa
        L_0x009f:
            r0 = move-exception
            r2 = r5
        L_0x00a1:
            kotlinx.coroutines.flow.FlowCollector<R> r5 = r1.$this_unsafeFlow     // Catch:{ all -> 0x0018 }
            kotlinx.coroutines.flow.internal.FlowExceptions_commonKt.checkOwnership(r0, r5)     // Catch:{ all -> 0x0018 }
            goto L_0x0098
        L_0x00a7:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00aa:
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r2, (java.util.concurrent.CancellationException) r4, (int) r3, (java.lang.Object) r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
