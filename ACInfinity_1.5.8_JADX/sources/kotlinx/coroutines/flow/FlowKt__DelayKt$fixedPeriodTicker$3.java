package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H@"}, mo27512d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3", mo28222f = "Delay.kt", mo28223i = {0, 1, 2}, mo28224l = {316, 318, 319}, mo28225m = "invokeSuspend", mo28226n = {"$this$produce", "$this$produce", "$this$produce"}, mo28227s = {"L$0", "L$0", "L$0"})
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$fixedPeriodTicker$3 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $delayMillis;
    final /* synthetic */ long $initialDelayMillis;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$fixedPeriodTicker$3(long j, long j2, Continuation<? super FlowKt__DelayKt$fixedPeriodTicker$3> continuation) {
        super(2, continuation);
        this.$initialDelayMillis = j;
        this.$delayMillis = j2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowKt__DelayKt$fixedPeriodTicker$3 flowKt__DelayKt$fixedPeriodTicker$3 = new FlowKt__DelayKt$fixedPeriodTicker$3(this.$initialDelayMillis, this.$delayMillis, continuation);
        flowKt__DelayKt$fixedPeriodTicker$3.L$0 = obj;
        return flowKt__DelayKt$fixedPeriodTicker$3;
    }

    public final Object invoke(ProducerScope<? super Unit> producerScope, Continuation<? super Unit> continuation) {
        return ((FlowKt__DelayKt$fixedPeriodTicker$3) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: kotlinx.coroutines.channels.ProducerScope} */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0056 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0066 A[RETURN] */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x002a
            if (r1 == r4) goto L_0x0011
            if (r1 == r3) goto L_0x0021
            if (r1 != r2) goto L_0x0019
        L_0x0011:
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0042
        L_0x0019:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0021:
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r7
            goto L_0x0057
        L_0x002a:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            r1 = r8
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            long r5 = r7.$initialDelayMillis
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.L$0 = r1
            r7.label = r4
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r5, r8)
            if (r8 != r0) goto L_0x0042
            return r0
        L_0x0042:
            r8 = r7
        L_0x0043:
            kotlinx.coroutines.channels.SendChannel r4 = r1.getChannel()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            r6 = r8
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r8.L$0 = r1
            r8.label = r3
            java.lang.Object r4 = r4.send(r5, r6)
            if (r4 != r0) goto L_0x0057
            return r0
        L_0x0057:
            long r4 = r8.$delayMillis
            r6 = r8
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r8.L$0 = r1
            r8.label = r2
            java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r6)
            if (r4 != r0) goto L_0x0043
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
