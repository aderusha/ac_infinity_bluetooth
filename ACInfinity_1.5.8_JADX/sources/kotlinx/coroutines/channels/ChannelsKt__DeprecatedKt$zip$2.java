package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H@"}, mo27512d2 = {"<anonymous>", "", "E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2", mo28222f = "Deprecated.kt", mo28223i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, mo28224l = {487, 469, 471}, mo28225m = "invokeSuspend", mo28226n = {"$this$produce", "otherIterator", "$this$consume$iv$iv", "$this$produce", "otherIterator", "$this$consume$iv$iv", "element1", "$this$produce", "otherIterator", "$this$consume$iv$iv"}, mo28227s = {"L$0", "L$1", "L$3", "L$0", "L$1", "L$3", "L$5", "L$0", "L$1", "L$3"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$zip$2 extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel<R> $other;
    final /* synthetic */ ReceiveChannel<E> $this_zip;
    final /* synthetic */ Function2<E, R, V> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$zip$2(ReceiveChannel<? extends R> receiveChannel, ReceiveChannel<? extends E> receiveChannel2, Function2<? super E, ? super R, ? extends V> function2, Continuation<? super ChannelsKt__DeprecatedKt$zip$2> continuation) {
        super(2, continuation);
        this.$other = receiveChannel;
        this.$this_zip = receiveChannel2;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$zip$2 channelsKt__DeprecatedKt$zip$2 = new ChannelsKt__DeprecatedKt$zip$2(this.$other, this.$this_zip, this.$transform, continuation);
        channelsKt__DeprecatedKt$zip$2.L$0 = obj;
        return channelsKt__DeprecatedKt$zip$2;
    }

    public final Object invoke(ProducerScope<? super V> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$zip$2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b9 A[Catch:{ all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e9 A[Catch:{ all -> 0x0106 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0079
            if (r1 == r4) goto L_0x005a
            if (r1 == r3) goto L_0x0034
            if (r1 != r2) goto L_0x002c
            java.lang.Object r1 = r13.L$4
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r13.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r13.L$2
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r13.L$1
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x0111 }
            r14 = r13
            goto L_0x0093
        L_0x002c:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0034:
            java.lang.Object r1 = r13.L$5
            java.lang.Object r6 = r13.L$4
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r13.L$3
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r13.L$2
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r9 = r13.L$1
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r10 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x0056 }
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r1
            r1 = r0
            r0 = r13
            goto L_0x00d9
        L_0x0056:
            r14 = move-exception
            r6 = r7
            goto L_0x0112
        L_0x005a:
            java.lang.Object r1 = r13.L$4
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r6 = r13.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r13.L$2
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r13.L$1
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x0111 }
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r1
            r1 = r0
            r0 = r13
            goto L_0x00b1
        L_0x0079:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r14 = r13.L$0
            kotlinx.coroutines.channels.ProducerScope r14 = (kotlinx.coroutines.channels.ProducerScope) r14
            kotlinx.coroutines.channels.ReceiveChannel<R> r1 = r13.$other
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            kotlinx.coroutines.channels.ReceiveChannel<E> r6 = r13.$this_zip
            kotlin.jvm.functions.Function2<E, R, V> r7 = r13.$transform
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch:{ all -> 0x0111 }
            r9 = r14
            r14 = r13
            r12 = r8
            r8 = r1
            r1 = r12
        L_0x0093:
            r14.L$0 = r9     // Catch:{ all -> 0x0111 }
            r14.L$1 = r8     // Catch:{ all -> 0x0111 }
            r14.L$2 = r7     // Catch:{ all -> 0x0111 }
            r14.L$3 = r6     // Catch:{ all -> 0x0111 }
            r14.L$4 = r1     // Catch:{ all -> 0x0111 }
            r14.L$5 = r5     // Catch:{ all -> 0x0111 }
            r14.label = r4     // Catch:{ all -> 0x0111 }
            java.lang.Object r10 = r1.hasNext(r14)     // Catch:{ all -> 0x0111 }
            if (r10 != r0) goto L_0x00a8
            return r0
        L_0x00a8:
            r12 = r0
            r0 = r14
            r14 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r1
            r1 = r12
        L_0x00b1:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x0056 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x0056 }
            if (r14 == 0) goto L_0x0109
            java.lang.Object r14 = r6.next()     // Catch:{ all -> 0x0056 }
            r0.L$0 = r10     // Catch:{ all -> 0x0056 }
            r0.L$1 = r9     // Catch:{ all -> 0x0056 }
            r0.L$2 = r8     // Catch:{ all -> 0x0056 }
            r0.L$3 = r7     // Catch:{ all -> 0x0056 }
            r0.L$4 = r6     // Catch:{ all -> 0x0056 }
            r0.L$5 = r14     // Catch:{ all -> 0x0056 }
            r0.label = r3     // Catch:{ all -> 0x0056 }
            java.lang.Object r11 = r9.hasNext(r0)     // Catch:{ all -> 0x0056 }
            if (r11 != r1) goto L_0x00d2
            return r1
        L_0x00d2:
            r12 = r7
            r7 = r14
            r14 = r11
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r12
        L_0x00d9:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x0106 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x0106 }
            if (r14 != 0) goto L_0x00e9
        L_0x00e1:
            r14 = r0
            r0 = r1
            r1 = r6
            r6 = r8
            r7 = r9
            r8 = r10
            r9 = r11
            goto L_0x0093
        L_0x00e9:
            java.lang.Object r14 = r10.next()     // Catch:{ all -> 0x0106 }
            java.lang.Object r14 = r9.invoke(r7, r14)     // Catch:{ all -> 0x0106 }
            r0.L$0 = r11     // Catch:{ all -> 0x0106 }
            r0.L$1 = r10     // Catch:{ all -> 0x0106 }
            r0.L$2 = r9     // Catch:{ all -> 0x0106 }
            r0.L$3 = r8     // Catch:{ all -> 0x0106 }
            r0.L$4 = r6     // Catch:{ all -> 0x0106 }
            r0.L$5 = r5     // Catch:{ all -> 0x0106 }
            r0.label = r2     // Catch:{ all -> 0x0106 }
            java.lang.Object r14 = r11.send(r14, r0)     // Catch:{ all -> 0x0106 }
            if (r14 != r1) goto L_0x00e1
            return r1
        L_0x0106:
            r14 = move-exception
            r6 = r8
            goto L_0x0112
        L_0x0109:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0056 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r5)
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x0111:
            r14 = move-exception
        L_0x0112:
            throw r14     // Catch:{ all -> 0x0113 }
        L_0x0113:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
