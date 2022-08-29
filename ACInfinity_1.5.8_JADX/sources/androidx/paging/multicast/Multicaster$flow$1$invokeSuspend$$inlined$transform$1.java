package androidx.paging.multicast;

import androidx.paging.multicast.ChannelManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo27512d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.multicast.Multicaster$flow$1$invokeSuspend$$inlined$transform$1", mo28222f = "Multicaster.kt", mo28223i = {}, mo28224l = {215}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: Emitters.kt */
public final class Multicaster$flow$1$invokeSuspend$$inlined$transform$1 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Multicaster$flow$1$invokeSuspend$$inlined$transform$1(Flow flow, Continuation continuation) {
        super(2, continuation);
        this.$this_transform = flow;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Multicaster$flow$1$invokeSuspend$$inlined$transform$1 multicaster$flow$1$invokeSuspend$$inlined$transform$1 = new Multicaster$flow$1$invokeSuspend$$inlined$transform$1(this.$this_transform, continuation);
        multicaster$flow$1$invokeSuspend$$inlined$transform$1.L$0 = obj;
        return multicaster$flow$1$invokeSuspend$$inlined$transform$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((Multicaster$flow$1$invokeSuspend$$inlined$transform$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final FlowCollector flowCollector = (FlowCollector) this.L$0;
            this.label = 1;
            if (this.$this_transform.collect(new FlowCollector<ChannelManager.Message.Dispatch.Value<T>>(this) {
                final /* synthetic */ Multicaster$flow$1$invokeSuspend$$inlined$transform$1 this$0;

                {
                    this.this$0 = r1;
                }

                /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
                /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof androidx.paging.multicast.Multicaster$flow$1$invokeSuspend$$inlined$transform$1.C05691.C05701
                        if (r0 == 0) goto L_0x0014
                        r0 = r6
                        androidx.paging.multicast.Multicaster$flow$1$invokeSuspend$$inlined$transform$1$1$1 r0 = (androidx.paging.multicast.Multicaster$flow$1$invokeSuspend$$inlined$transform$1.C05691.C05701) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L_0x0014
                        int r6 = r0.label
                        int r6 = r6 - r2
                        r0.label = r6
                        goto L_0x0019
                    L_0x0014:
                        androidx.paging.multicast.Multicaster$flow$1$invokeSuspend$$inlined$transform$1$1$1 r0 = new androidx.paging.multicast.Multicaster$flow$1$invokeSuspend$$inlined$transform$1$1$1
                        r0.<init>(r4, r6)
                    L_0x0019:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L_0x0036
                        if (r2 != r3) goto L_0x002e
                        java.lang.Object r5 = r0.L$0
                        androidx.paging.multicast.ChannelManager$Message$Dispatch$Value r5 = (androidx.paging.multicast.ChannelManager.Message.Dispatch.Value) r5
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L_0x004f
                    L_0x002e:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L_0x0036:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlinx.coroutines.flow.FlowCollector r6 = r5
                        r2 = r0
                        kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                        androidx.paging.multicast.ChannelManager$Message$Dispatch$Value r5 = (androidx.paging.multicast.ChannelManager.Message.Dispatch.Value) r5
                        java.lang.Object r2 = r5.getValue()
                        r0.L$0 = r5
                        r0.label = r3
                        java.lang.Object r6 = r6.emit(r2, r0)
                        if (r6 != r1) goto L_0x004f
                        return r1
                    L_0x004f:
                        kotlinx.coroutines.CompletableDeferred r5 = r5.getDelivered()
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        r5.complete(r6)
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.Multicaster$flow$1$invokeSuspend$$inlined$transform$1.C05691.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
