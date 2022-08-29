package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo27511d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@"}, mo27512d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1", mo28222f = "Deprecated.kt", mo28223i = {0, 1, 1, 2, 3, 4}, mo28224l = {181, 182, 183, 187, 188}, mo28225m = "invokeSuspend", mo28226n = {"$this$produce", "$this$produce", "e", "$this$produce", "$this$produce", "$this$produce"}, mo28227s = {"L$0", "L$0", "L$2", "L$0", "L$0", "L$0"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$dropWhile$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<E, Continuation<? super Boolean>, Object> $predicate;
    final /* synthetic */ ReceiveChannel<E> $this_dropWhile;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$dropWhile$1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super ChannelsKt__DeprecatedKt$dropWhile$1> continuation) {
        super(2, continuation);
        this.$this_dropWhile = receiveChannel;
        this.$predicate = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$dropWhile$1 channelsKt__DeprecatedKt$dropWhile$1 = new ChannelsKt__DeprecatedKt$dropWhile$1(this.$this_dropWhile, this.$predicate, continuation);
        channelsKt__DeprecatedKt$dropWhile$1.L$0 = obj;
        return channelsKt__DeprecatedKt$dropWhile$1;
    }

    public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$dropWhile$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d2 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ef A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00fb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0071
            if (r1 == r6) goto L_0x0061
            if (r1 == r5) goto L_0x004e
            if (r1 == r4) goto L_0x0042
            if (r1 == r3) goto L_0x0031
            if (r1 != r2) goto L_0x0029
            java.lang.Object r1 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r1
            r9 = r4
            r1 = r0
            r0 = r12
            goto L_0x00e0
        L_0x0029:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0031:
            java.lang.Object r1 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r4
            r4 = r1
            r1 = r0
            r0 = r12
            goto L_0x00f3
        L_0x0042:
            java.lang.Object r1 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r13)
            r10 = r1
            r1 = r0
            r0 = r12
            goto L_0x00d3
        L_0x004e:
            java.lang.Object r1 = r12.L$2
            java.lang.Object r8 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            kotlin.ResultKt.throwOnFailure(r13)
            r10 = r9
            r9 = r1
            r1 = r0
            r0 = r12
            goto L_0x00b9
        L_0x0061:
            java.lang.Object r1 = r12.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r8 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r8
            r8 = r1
            r1 = r0
            r0 = r12
            goto L_0x0098
        L_0x0071:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
            kotlinx.coroutines.channels.ReceiveChannel<E> r1 = r12.$this_dropWhile
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r8 = r13
            r13 = r12
        L_0x0080:
            r9 = r13
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r13.L$0 = r8
            r13.L$1 = r1
            r13.L$2 = r7
            r13.label = r6
            java.lang.Object r9 = r1.hasNext(r9)
            if (r9 != r0) goto L_0x0092
            return r0
        L_0x0092:
            r11 = r0
            r0 = r13
            r13 = r9
            r9 = r8
            r8 = r1
            r1 = r11
        L_0x0098:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00da
            java.lang.Object r13 = r8.next()
            kotlin.jvm.functions.Function2<E, kotlin.coroutines.Continuation<? super java.lang.Boolean>, java.lang.Object> r10 = r0.$predicate
            r0.L$0 = r9
            r0.L$1 = r8
            r0.L$2 = r13
            r0.label = r5
            java.lang.Object r10 = r10.invoke(r13, r0)
            if (r10 != r1) goto L_0x00b5
            return r1
        L_0x00b5:
            r11 = r9
            r9 = r13
            r13 = r10
            r10 = r11
        L_0x00b9:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 != 0) goto L_0x00d5
            r13 = r0
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            r0.L$0 = r10
            r0.L$1 = r7
            r0.L$2 = r7
            r0.label = r4
            java.lang.Object r13 = r10.send(r9, r13)
            if (r13 != r1) goto L_0x00d3
            return r1
        L_0x00d3:
            r9 = r10
            goto L_0x00da
        L_0x00d5:
            r13 = r0
            r0 = r1
            r1 = r8
            r8 = r10
            goto L_0x0080
        L_0x00da:
            kotlinx.coroutines.channels.ReceiveChannel<E> r13 = r0.$this_dropWhile
            kotlinx.coroutines.channels.ChannelIterator r13 = r13.iterator()
        L_0x00e0:
            r4 = r0
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r0.L$0 = r9
            r0.L$1 = r13
            r0.label = r3
            java.lang.Object r4 = r13.hasNext(r4)
            if (r4 != r1) goto L_0x00f0
            return r1
        L_0x00f0:
            r11 = r4
            r4 = r13
            r13 = r11
        L_0x00f3:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x0111
            java.lang.Object r13 = r4.next()
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r0.L$0 = r9
            r0.L$1 = r4
            r0.label = r2
            java.lang.Object r13 = r9.send(r13, r5)
            if (r13 != r1) goto L_0x010f
            return r1
        L_0x010f:
            r13 = r4
            goto L_0x00e0
        L_0x0111:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
