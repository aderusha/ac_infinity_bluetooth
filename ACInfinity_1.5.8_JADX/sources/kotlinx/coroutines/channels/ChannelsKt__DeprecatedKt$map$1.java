package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@"}, mo27512d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1", mo28222f = "Deprecated.kt", mo28223i = {0, 0, 1, 1, 2, 2}, mo28224l = {487, 333, 333}, mo28225m = "invokeSuspend", mo28226n = {"$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv"}, mo28227s = {"L$0", "L$2", "L$0", "L$2", "L$0", "L$2"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$map$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel<E> $this_map;
    final /* synthetic */ Function2<E, Continuation<? super R>, Object> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$map$1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super ChannelsKt__DeprecatedKt$map$1> continuation) {
        super(2, continuation);
        this.$this_map = receiveChannel;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$map$1 channelsKt__DeprecatedKt$map$1 = new ChannelsKt__DeprecatedKt$map$1(this.$this_map, this.$transform, continuation);
        channelsKt__DeprecatedKt$map$1.L$0 = obj;
        return channelsKt__DeprecatedKt$map$1;
    }

    public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$map$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0093 A[Catch:{ all -> 0x00d1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x0063
            if (r1 == r5) goto L_0x004e
            if (r1 == r4) goto L_0x0030
            if (r1 != r3) goto L_0x0028
            java.lang.Object r1 = r12.L$3
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r12.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r12.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x00d1 }
            r13 = r8
            r8 = r12
            goto L_0x0076
        L_0x0028:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0030:
            java.lang.Object r1 = r12.L$4
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            java.lang.Object r6 = r12.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r12.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r12.L$1
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r9 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x004a }
            r10 = r12
            goto L_0x00b0
        L_0x004a:
            r13 = move-exception
            r6 = r7
            goto L_0x00d2
        L_0x004e:
            java.lang.Object r1 = r12.L$3
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r12.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r12.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x00d1 }
            r9 = r12
            goto L_0x008b
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
            kotlinx.coroutines.channels.ReceiveChannel<E> r6 = r12.$this_map
            kotlin.jvm.functions.Function2<E, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r1 = r12.$transform
            kotlinx.coroutines.channels.ChannelIterator r7 = r6.iterator()     // Catch:{ all -> 0x00d1 }
            r8 = r12
            r11 = r7
            r7 = r1
            r1 = r11
        L_0x0076:
            r8.L$0 = r13     // Catch:{ all -> 0x00d1 }
            r8.L$1 = r7     // Catch:{ all -> 0x00d1 }
            r8.L$2 = r6     // Catch:{ all -> 0x00d1 }
            r8.L$3 = r1     // Catch:{ all -> 0x00d1 }
            r8.label = r5     // Catch:{ all -> 0x00d1 }
            java.lang.Object r9 = r1.hasNext(r8)     // Catch:{ all -> 0x00d1 }
            if (r9 != r0) goto L_0x0087
            return r0
        L_0x0087:
            r11 = r8
            r8 = r13
            r13 = r9
            r9 = r11
        L_0x008b:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x00d1 }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x00d1 }
            if (r13 == 0) goto L_0x00c9
            java.lang.Object r13 = r1.next()     // Catch:{ all -> 0x00d1 }
            r9.L$0 = r8     // Catch:{ all -> 0x00d1 }
            r9.L$1 = r7     // Catch:{ all -> 0x00d1 }
            r9.L$2 = r6     // Catch:{ all -> 0x00d1 }
            r9.L$3 = r1     // Catch:{ all -> 0x00d1 }
            r9.L$4 = r8     // Catch:{ all -> 0x00d1 }
            r9.label = r4     // Catch:{ all -> 0x00d1 }
            java.lang.Object r13 = r7.invoke(r13, r9)     // Catch:{ all -> 0x00d1 }
            if (r13 != r0) goto L_0x00aa
            return r0
        L_0x00aa:
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r1
            r1 = r9
        L_0x00b0:
            r10.L$0 = r9     // Catch:{ all -> 0x004a }
            r10.L$1 = r8     // Catch:{ all -> 0x004a }
            r10.L$2 = r7     // Catch:{ all -> 0x004a }
            r10.L$3 = r6     // Catch:{ all -> 0x004a }
            r10.L$4 = r2     // Catch:{ all -> 0x004a }
            r10.label = r3     // Catch:{ all -> 0x004a }
            java.lang.Object r13 = r1.send(r13, r10)     // Catch:{ all -> 0x004a }
            if (r13 != r0) goto L_0x00c3
            return r0
        L_0x00c3:
            r1 = r6
            r6 = r7
            r7 = r8
            r13 = r9
            r8 = r10
            goto L_0x0076
        L_0x00c9:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00d1 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r2)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x00d1:
            r13 = move-exception
        L_0x00d2:
            throw r13     // Catch:{ all -> 0x00d3 }
        L_0x00d3:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
