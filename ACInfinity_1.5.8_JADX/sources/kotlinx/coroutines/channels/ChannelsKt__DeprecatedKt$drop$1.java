package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo27511d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@"}, mo27512d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1", mo28222f = "Deprecated.kt", mo28223i = {0, 0, 1, 2}, mo28224l = {164, 169, 170}, mo28225m = "invokeSuspend", mo28226n = {"$this$produce", "remaining", "$this$produce", "$this$produce"}, mo28227s = {"L$0", "I$0", "L$0", "L$0"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$drop$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {

    /* renamed from: $n */
    final /* synthetic */ int f1203$n;
    final /* synthetic */ ReceiveChannel<E> $this_drop;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$drop$1(int i, ReceiveChannel<? extends E> receiveChannel, Continuation<? super ChannelsKt__DeprecatedKt$drop$1> continuation) {
        super(2, continuation);
        this.f1203$n = i;
        this.$this_drop = receiveChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$drop$1 channelsKt__DeprecatedKt$drop$1 = new ChannelsKt__DeprecatedKt$drop$1(this.f1203$n, this.$this_drop, continuation);
        channelsKt__DeprecatedKt$drop$1.L$0 = obj;
        return channelsKt__DeprecatedKt$drop$1;
    }

    public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$drop$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x004e
            if (r1 == r4) goto L_0x003b
            if (r1 == r3) goto L_0x002a
            if (r1 != r2) goto L_0x0022
            java.lang.Object r1 = r9.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r4
            r4 = r1
            r1 = r0
            r0 = r9
            goto L_0x00a2
        L_0x0022:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x002a:
            java.lang.Object r1 = r9.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r10)
            r5 = r4
            r4 = r1
            r1 = r0
            r0 = r9
            goto L_0x00b5
        L_0x003b:
            int r1 = r9.I$0
            java.lang.Object r5 = r9.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r6
            r6 = r5
            r5 = r1
            r1 = r0
            r0 = r9
            goto L_0x0081
        L_0x004e:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
            int r1 = r9.f1203$n
            if (r1 < 0) goto L_0x005b
            r5 = 1
            goto L_0x005c
        L_0x005b:
            r5 = 0
        L_0x005c:
            if (r5 == 0) goto L_0x00d6
            if (r1 <= 0) goto L_0x009a
            kotlinx.coroutines.channels.ReceiveChannel<E> r5 = r9.$this_drop
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
            r6 = r10
            r10 = r9
        L_0x0068:
            r7 = r10
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r10.L$0 = r6
            r10.L$1 = r5
            r10.I$0 = r1
            r10.label = r4
            java.lang.Object r7 = r5.hasNext(r7)
            if (r7 != r0) goto L_0x007a
            return r0
        L_0x007a:
            r8 = r0
            r0 = r10
            r10 = r7
            r7 = r6
            r6 = r5
            r5 = r1
            r1 = r8
        L_0x0081:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x0098
            r6.next()
            int r10 = r5 + -1
            if (r10 != 0) goto L_0x0091
            goto L_0x0098
        L_0x0091:
            r5 = r6
            r6 = r7
            r8 = r1
            r1 = r10
            r10 = r0
            r0 = r8
            goto L_0x0068
        L_0x0098:
            r10 = r7
            goto L_0x009c
        L_0x009a:
            r1 = r0
            r0 = r9
        L_0x009c:
            kotlinx.coroutines.channels.ReceiveChannel<E> r4 = r0.$this_drop
            kotlinx.coroutines.channels.ChannelIterator r4 = r4.iterator()
        L_0x00a2:
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r0.L$0 = r10
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r5 = r4.hasNext(r5)
            if (r5 != r1) goto L_0x00b2
            return r1
        L_0x00b2:
            r8 = r5
            r5 = r10
            r10 = r8
        L_0x00b5:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00d3
            java.lang.Object r10 = r4.next()
            r6 = r0
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r0.L$0 = r5
            r0.L$1 = r4
            r0.label = r2
            java.lang.Object r10 = r5.send(r10, r6)
            if (r10 != r1) goto L_0x00d1
            return r1
        L_0x00d1:
            r10 = r5
            goto L_0x00a2
        L_0x00d3:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00d6:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "Requested element count "
            r10.append(r0)
            r10.append(r1)
            java.lang.String r0 = " is less than zero."
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r10 = r10.toString()
            r0.<init>(r10)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
