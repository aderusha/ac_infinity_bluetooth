package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u0001H@"}, mo27512d2 = {"<anonymous>", "", "T1", "T2", "R", "it"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1", mo28222f = "Combine.kt", mo28223i = {}, mo28224l = {132, 135, 135}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: Combine.kt */
final class CombineKt$zipImpl$1$1$2$1$1 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel<Object> $second;
    final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
    final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
    final /* synthetic */ T1 $value;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CombineKt$zipImpl$1$1$2$1$1(ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, T1 t1, Continuation<? super CombineKt$zipImpl$1$1$2$1$1> continuation) {
        super(2, continuation);
        this.$second = receiveChannel;
        this.$this_unsafeFlow = flowCollector;
        this.$transform = function3;
        this.$value = t1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CombineKt$zipImpl$1$1$2$1$1(this.$second, this.$this_unsafeFlow, this.$transform, this.$value, continuation);
    }

    public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$1$1$2$1$1) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0076 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x0030
            if (r1 == r5) goto L_0x0026
            if (r1 == r4) goto L_0x001e
            if (r1 != r3) goto L_0x0016
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0077
        L_0x0016:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001e:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0069
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.channels.ChannelResult r9 = (kotlinx.coroutines.channels.ChannelResult) r9
            java.lang.Object r9 = r9.m2449unboximpl()
            goto L_0x0041
        L_0x0030:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r9 = r8.$second
            r1 = r8
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r8.label = r5
            java.lang.Object r9 = r9.m2456receiveCatchingJP2dKIU(r1)
            if (r9 != r0) goto L_0x0041
            return r0
        L_0x0041:
            kotlinx.coroutines.flow.FlowCollector<R> r1 = r8.$this_unsafeFlow
            boolean r5 = r9 instanceof kotlinx.coroutines.channels.ChannelResult.Failed
            if (r5 == 0) goto L_0x0055
            java.lang.Throwable r9 = kotlinx.coroutines.channels.ChannelResult.m2441exceptionOrNullimpl(r9)
            if (r9 != 0) goto L_0x0054
            kotlinx.coroutines.flow.internal.AbortFlowException r9 = new kotlinx.coroutines.flow.internal.AbortFlowException
            r9.<init>(r1)
            java.lang.Throwable r9 = (java.lang.Throwable) r9
        L_0x0054:
            throw r9
        L_0x0055:
            kotlin.jvm.functions.Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r5 = r8.$transform
            T1 r6 = r8.$value
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r9 != r7) goto L_0x005e
            r9 = r2
        L_0x005e:
            r8.L$0 = r1
            r8.label = r4
            java.lang.Object r9 = r5.invoke(r6, r9, r8)
            if (r9 != r0) goto L_0x0069
            return r0
        L_0x0069:
            r4 = r8
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r8.L$0 = r2
            r8.label = r3
            java.lang.Object r9 = r1.emit(r9, r4)
            if (r9 != r0) goto L_0x0077
            return r0
        L_0x0077:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
