package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.rx2.RxConvertKt$asObservable$2", mo28222f = "RxConvert.kt", mo28223i = {0, 1}, mo28224l = {150, 151}, mo28225m = "invokeSuspend", mo28226n = {"$this$rxObservable", "$this$rxObservable"}, mo28227s = {"L$0", "L$0"})
/* compiled from: RxConvert.kt */
final class RxConvertKt$asObservable$2 extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_asObservable;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxConvertKt$asObservable$2(ReceiveChannel receiveChannel, Continuation continuation) {
        super(2, continuation);
        this.$this_asObservable = receiveChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RxConvertKt$asObservable$2 rxConvertKt$asObservable$2 = new RxConvertKt$asObservable$2(this.$this_asObservable, continuation);
        rxConvertKt$asObservable$2.L$0 = obj;
        return rxConvertKt$asObservable$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RxConvertKt$asObservable$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0030
            if (r1 == r3) goto L_0x0023
            if (r1 != r2) goto L_0x001b
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r4
            goto L_0x003d
        L_0x001b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0023:
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r8)
            r5 = r7
            goto L_0x004f
        L_0x0030:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            kotlinx.coroutines.channels.ReceiveChannel r1 = r7.$this_asObservable
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
        L_0x003d:
            r4 = r7
        L_0x003e:
            r4.L$0 = r8
            r4.L$1 = r1
            r4.label = r3
            java.lang.Object r5 = r1.hasNext(r4)
            if (r5 != r0) goto L_0x004b
            return r0
        L_0x004b:
            r6 = r4
            r4 = r8
            r8 = r5
            r5 = r6
        L_0x004f:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x006b
            java.lang.Object r8 = r1.next()
            r5.L$0 = r4
            r5.L$1 = r1
            r5.label = r2
            java.lang.Object r8 = r4.send(r8, r5)
            if (r8 != r0) goto L_0x0068
            return r0
        L_0x0068:
            r8 = r4
            r4 = r5
            goto L_0x003e
        L_0x006b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxConvertKt$asObservable$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
