package kotlinx.coroutines.reactive;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.reactivestreams.Publisher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a5\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005HHø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a5\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005HHø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a(\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, mo27512d2 = {"collect", "", "T", "Lorg/reactivestreams/Publisher;", "action", "Lkotlin/Function1;", "(Lorg/reactivestreams/Publisher;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumeEach", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "request", "", "kotlinx-coroutines-reactive"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: Channel.kt */
public final class ChannelKt {
    public static /* synthetic */ ReceiveChannel openSubscription$default(Publisher publisher, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return openSubscription(publisher, i);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Transforming publisher to channel is deprecated, use asFlow() instead")
    public static final <T> ReceiveChannel<T> openSubscription(Publisher<T> publisher, int i) {
        SubscriptionChannel subscriptionChannel = new SubscriptionChannel(i);
        publisher.subscribe(subscriptionChannel);
        return subscriptionChannel;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0076 A[Catch:{ all -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Use collect instead", replaceWith = @kotlin.ReplaceWith(expression = "this.collect(action)", imports = {}))
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object consumeEach(org.reactivestreams.Publisher<T> r6, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.reactive.ChannelKt$consumeEach$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.reactive.ChannelKt$consumeEach$1 r0 = (kotlinx.coroutines.reactive.ChannelKt$consumeEach$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.reactive.ChannelKt$consumeEach$1 r0 = new kotlinx.coroutines.reactive.ChannelKt$consumeEach$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 != r3) goto L_0x003f
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$2
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x003d }
            r5 = r4
            r4 = r7
            r7 = r5
            goto L_0x006e
        L_0x003d:
            r6 = move-exception
            goto L_0x0092
        L_0x003f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = 0
            r2 = 0
            kotlinx.coroutines.channels.ReceiveChannel r6 = openSubscription$default(r6, r8, r3, r2)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch:{ all -> 0x008f }
            r5 = r8
            r8 = r6
            r6 = r5
        L_0x0059:
            r0.L$0 = r7     // Catch:{ all -> 0x008c }
            r0.L$1 = r8     // Catch:{ all -> 0x008c }
            r0.L$2 = r2     // Catch:{ all -> 0x008c }
            r0.L$3 = r6     // Catch:{ all -> 0x008c }
            r0.label = r3     // Catch:{ all -> 0x008c }
            java.lang.Object r4 = r6.hasNext(r0)     // Catch:{ all -> 0x008c }
            if (r4 != r1) goto L_0x006a
            return r1
        L_0x006a:
            r5 = r2
            r2 = r8
            r8 = r4
            r4 = r5
        L_0x006e:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x003d }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x003d }
            if (r8 == 0) goto L_0x0080
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x003d }
            r7.invoke(r8)     // Catch:{ all -> 0x003d }
            r8 = r2
            r2 = r4
            goto L_0x0059
        L_0x0080:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003d }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r6
        L_0x008c:
            r6 = move-exception
            r2 = r8
            goto L_0x0092
        L_0x008f:
            r7 = move-exception
            r2 = r6
            r6 = r7
        L_0x0092:
            throw r6     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.ChannelKt.consumeEach(org.reactivestreams.Publisher, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        throw r7;
     */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Use collect instead", replaceWith = @kotlin.ReplaceWith(expression = "this.collect(action)", imports = {}))
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object consumeEach$$forInline(org.reactivestreams.Publisher r5, kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r0 = 0
            r1 = 1
            r2 = 0
            kotlinx.coroutines.channels.ReceiveChannel r5 = openSubscription$default(r5, r0, r1, r2)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r3 = r5.iterator()     // Catch:{ all -> 0x0033 }
        L_0x000d:
            kotlin.jvm.internal.InlineMarker.mark((int) r0)     // Catch:{ all -> 0x0033 }
            java.lang.Object r4 = r3.hasNext(r7)     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0033 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0033 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0033 }
            if (r4 == 0) goto L_0x0027
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0033 }
            r6.invoke(r4)     // Catch:{ all -> 0x0033 }
            goto L_0x000d
        L_0x0027:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0033:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.ChannelKt.consumeEach$$forInline(org.reactivestreams.Publisher, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0076 A[Catch:{ all -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object collect(org.reactivestreams.Publisher<T> r6, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.reactive.ChannelKt$collect$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.reactive.ChannelKt$collect$1 r0 = (kotlinx.coroutines.reactive.ChannelKt$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.reactive.ChannelKt$collect$1 r0 = new kotlinx.coroutines.reactive.ChannelKt$collect$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 != r3) goto L_0x003f
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$2
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x003d }
            r5 = r4
            r4 = r7
            r7 = r5
            goto L_0x006e
        L_0x003d:
            r6 = move-exception
            goto L_0x0092
        L_0x003f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = 0
            r2 = 0
            kotlinx.coroutines.channels.ReceiveChannel r6 = openSubscription$default(r6, r8, r3, r2)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch:{ all -> 0x008f }
            r5 = r8
            r8 = r6
            r6 = r5
        L_0x0059:
            r0.L$0 = r7     // Catch:{ all -> 0x008c }
            r0.L$1 = r8     // Catch:{ all -> 0x008c }
            r0.L$2 = r2     // Catch:{ all -> 0x008c }
            r0.L$3 = r6     // Catch:{ all -> 0x008c }
            r0.label = r3     // Catch:{ all -> 0x008c }
            java.lang.Object r4 = r6.hasNext(r0)     // Catch:{ all -> 0x008c }
            if (r4 != r1) goto L_0x006a
            return r1
        L_0x006a:
            r5 = r2
            r2 = r8
            r8 = r4
            r4 = r5
        L_0x006e:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x003d }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x003d }
            if (r8 == 0) goto L_0x0080
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x003d }
            r7.invoke(r8)     // Catch:{ all -> 0x003d }
            r8 = r2
            r2 = r4
            goto L_0x0059
        L_0x0080:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003d }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r6
        L_0x008c:
            r6 = move-exception
            r2 = r8
            goto L_0x0092
        L_0x008f:
            r7 = move-exception
            r2 = r6
            r6 = r7
        L_0x0092:
            throw r6     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.ChannelKt.collect(org.reactivestreams.Publisher, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object collect$$forInline(org.reactivestreams.Publisher r5, kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r0 = 0
            r1 = 1
            r2 = 0
            kotlinx.coroutines.channels.ReceiveChannel r5 = openSubscription$default(r5, r0, r1, r2)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r3 = r5.iterator()     // Catch:{ all -> 0x0033 }
        L_0x000d:
            kotlin.jvm.internal.InlineMarker.mark((int) r0)     // Catch:{ all -> 0x0033 }
            java.lang.Object r4 = r3.hasNext(r7)     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0033 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0033 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0033 }
            if (r4 == 0) goto L_0x0027
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0033 }
            r6.invoke(r4)     // Catch:{ all -> 0x0033 }
            goto L_0x000d
        L_0x0027:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0033:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r5, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.ChannelKt.collect$$forInline(org.reactivestreams.Publisher, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
