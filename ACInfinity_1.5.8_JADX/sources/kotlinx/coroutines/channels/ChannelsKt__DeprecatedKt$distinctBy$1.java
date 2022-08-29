package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@"}, mo27512d2 = {"<anonymous>", "", "E", "K", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1", mo28222f = "Deprecated.kt", mo28223i = {0, 0, 1, 1, 1, 2, 2, 2}, mo28224l = {387, 388, 390}, mo28225m = "invokeSuspend", mo28226n = {"$this$produce", "keys", "$this$produce", "keys", "e", "$this$produce", "keys", "k"}, mo28227s = {"L$0", "L$1", "L$0", "L$1", "L$3", "L$0", "L$1", "L$3"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$distinctBy$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<E, Continuation<? super K>, Object> $selector;
    final /* synthetic */ ReceiveChannel<E> $this_distinctBy;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$distinctBy$1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super ChannelsKt__DeprecatedKt$distinctBy$1> continuation) {
        super(2, continuation);
        this.$this_distinctBy = receiveChannel;
        this.$selector = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$distinctBy$1 channelsKt__DeprecatedKt$distinctBy$1 = new ChannelsKt__DeprecatedKt$distinctBy$1(this.$this_distinctBy, this.$selector, continuation);
        channelsKt__DeprecatedKt$distinctBy$1.L$0 = obj;
        return channelsKt__DeprecatedKt$distinctBy$1;
    }

    public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$distinctBy$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x005a
            if (r1 == r4) goto L_0x0045
            if (r1 == r3) goto L_0x002d
            if (r1 != r2) goto L_0x0025
            java.lang.Object r1 = r11.L$3
            java.lang.Object r5 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r11.L$1
            java.util.HashSet r6 = (java.util.HashSet) r6
            java.lang.Object r7 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r11
            goto L_0x00d1
        L_0x0025:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x002d:
            java.lang.Object r1 = r11.L$3
            java.lang.Object r5 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r11.L$1
            java.util.HashSet r6 = (java.util.HashSet) r6
            java.lang.Object r7 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r7
            r7 = r6
            r6 = r1
            r1 = r0
            r0 = r11
            goto L_0x00b1
        L_0x0045:
            java.lang.Object r1 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r11.L$1
            java.util.HashSet r5 = (java.util.HashSet) r5
            java.lang.Object r6 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r12)
            r7 = r6
            r6 = r5
            r5 = r1
            r1 = r0
            r0 = r11
            goto L_0x008d
        L_0x005a:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            kotlinx.coroutines.channels.ReceiveChannel<E> r5 = r11.$this_distinctBy
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
            r6 = r12
            r12 = r11
            r10 = r5
            r5 = r1
            r1 = r10
        L_0x0071:
            r7 = r12
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r12.L$0 = r6
            r12.L$1 = r5
            r12.L$2 = r1
            r8 = 0
            r12.L$3 = r8
            r12.label = r4
            java.lang.Object r7 = r1.hasNext(r7)
            if (r7 != r0) goto L_0x0086
            return r0
        L_0x0086:
            r10 = r0
            r0 = r12
            r12 = r7
            r7 = r6
            r6 = r5
            r5 = r1
            r1 = r10
        L_0x008d:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x00e1
            java.lang.Object r12 = r5.next()
            kotlin.jvm.functions.Function2<E, kotlin.coroutines.Continuation<? super K>, java.lang.Object> r8 = r0.$selector
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r5
            r0.L$3 = r12
            r0.label = r3
            java.lang.Object r8 = r8.invoke(r12, r0)
            if (r8 != r1) goto L_0x00ac
            return r1
        L_0x00ac:
            r10 = r6
            r6 = r12
            r12 = r8
            r8 = r7
            r7 = r10
        L_0x00b1:
            boolean r9 = r7.contains(r12)
            if (r9 != 0) goto L_0x00db
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r0.L$0 = r8
            r0.L$1 = r7
            r0.L$2 = r5
            r0.L$3 = r12
            r0.label = r2
            java.lang.Object r6 = r8.send(r6, r9)
            if (r6 != r1) goto L_0x00cb
            return r1
        L_0x00cb:
            r6 = r7
            r7 = r8
            r10 = r1
            r1 = r12
            r12 = r0
            r0 = r10
        L_0x00d1:
            r8 = r6
            java.util.Collection r8 = (java.util.Collection) r8
            r8.add(r1)
            r1 = r5
            r5 = r6
            r6 = r7
            goto L_0x0071
        L_0x00db:
            r12 = r0
            r0 = r1
            r1 = r5
            r5 = r7
            r6 = r8
            goto L_0x0071
        L_0x00e1:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
