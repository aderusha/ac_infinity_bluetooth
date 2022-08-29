package kotlinx.coroutines.rx2;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.coroutines.channels.ReceiveChannel;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.ObservableSource;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a5\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005HHø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a5\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005HHø\u0001\u0000¢\u0006\u0002\u0010\b\u001a5\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005HHø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a5\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005HHø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, mo27512d2 = {"collect", "", "T", "Lio/reactivex/MaybeSource;", "action", "Lkotlin/Function1;", "(Lio/reactivex/MaybeSource;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/reactivex/ObservableSource;", "(Lio/reactivex/ObservableSource;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumeEach", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-rx2"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: RxChannel.kt */
public final class RxChannelKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in the favour of Flow")
    public static final <T> ReceiveChannel<T> openSubscription(MaybeSource<T> maybeSource) {
        SubscriptionChannel subscriptionChannel = new SubscriptionChannel();
        maybeSource.subscribe(subscriptionChannel);
        return subscriptionChannel;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in the favour of Flow")
    public static final <T> ReceiveChannel<T> openSubscription(ObservableSource<T> observableSource) {
        SubscriptionChannel subscriptionChannel = new SubscriptionChannel();
        observableSource.subscribe(subscriptionChannel);
        return subscriptionChannel;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[Catch:{ all -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Use collect instead", replaceWith = @kotlin.ReplaceWith(expression = "this.collect(action)", imports = {}))
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object consumeEach(p014io.reactivex.MaybeSource<T> r6, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.rx2.RxChannelKt$consumeEach$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.rx2.RxChannelKt$consumeEach$1 r0 = (kotlinx.coroutines.rx2.RxChannelKt$consumeEach$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.rx2.RxChannelKt$consumeEach$1 r0 = new kotlinx.coroutines.rx2.RxChannelKt$consumeEach$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$2
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0091 }
            r5 = r0
            r0 = r7
            r7 = r4
        L_0x003c:
            r4 = r2
            r2 = r1
            r1 = r5
            goto L_0x006e
        L_0x0040:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ReceiveChannel r2 = openSubscription(r6)
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r8 = r2.iterator()     // Catch:{ all -> 0x0091 }
            r5 = r8
            r8 = r6
            r6 = r5
        L_0x0059:
            r0.L$0 = r7     // Catch:{ all -> 0x0091 }
            r0.L$1 = r2     // Catch:{ all -> 0x0091 }
            r0.L$2 = r8     // Catch:{ all -> 0x0091 }
            r0.L$3 = r6     // Catch:{ all -> 0x0091 }
            r0.label = r3     // Catch:{ all -> 0x0091 }
            java.lang.Object r4 = r6.hasNext(r0)     // Catch:{ all -> 0x0091 }
            if (r4 != r1) goto L_0x006a
            return r1
        L_0x006a:
            r5 = r0
            r0 = r8
            r8 = r4
            goto L_0x003c
        L_0x006e:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x008e }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x008e }
            if (r8 == 0) goto L_0x0082
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x008e }
            r7.invoke(r8)     // Catch:{ all -> 0x008e }
            r8 = r0
            r0 = r1
            r1 = r2
            r2 = r4
            goto L_0x0059
        L_0x0082:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x008e }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r6
        L_0x008e:
            r6 = move-exception
            r2 = r4
            goto L_0x0092
        L_0x0091:
            r6 = move-exception
        L_0x0092:
            throw r6     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxChannelKt.consumeEach(io.reactivex.MaybeSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        throw r6;
     */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Use collect instead", replaceWith = @kotlin.ReplaceWith(expression = "this.collect(action)", imports = {}))
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object consumeEach$$forInline(p014io.reactivex.MaybeSource r4, kotlin.jvm.functions.Function1 r5, kotlin.coroutines.Continuation r6) {
        /*
            kotlinx.coroutines.channels.ReceiveChannel r4 = openSubscription(r4)
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r4.iterator()     // Catch:{ all -> 0x0033 }
        L_0x000c:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0033 }
            java.lang.Object r3 = r2.hasNext(r6)     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0033 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0033 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0033 }
            if (r3 == 0) goto L_0x0027
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0033 }
            r5.invoke(r3)     // Catch:{ all -> 0x0033 }
            goto L_0x000c
        L_0x0027:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r5
        L_0x0033:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxChannelKt.consumeEach$$forInline(io.reactivex.MaybeSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[Catch:{ all -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Use collect instead", replaceWith = @kotlin.ReplaceWith(expression = "this.collect(action)", imports = {}))
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object consumeEach(p014io.reactivex.ObservableSource<T> r6, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.rx2.RxChannelKt$consumeEach$2
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.rx2.RxChannelKt$consumeEach$2 r0 = (kotlinx.coroutines.rx2.RxChannelKt$consumeEach$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.rx2.RxChannelKt$consumeEach$2 r0 = new kotlinx.coroutines.rx2.RxChannelKt$consumeEach$2
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$2
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0091 }
            r5 = r0
            r0 = r7
            r7 = r4
        L_0x003c:
            r4 = r2
            r2 = r1
            r1 = r5
            goto L_0x006e
        L_0x0040:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ReceiveChannel r2 = openSubscription(r6)
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r8 = r2.iterator()     // Catch:{ all -> 0x0091 }
            r5 = r8
            r8 = r6
            r6 = r5
        L_0x0059:
            r0.L$0 = r7     // Catch:{ all -> 0x0091 }
            r0.L$1 = r2     // Catch:{ all -> 0x0091 }
            r0.L$2 = r8     // Catch:{ all -> 0x0091 }
            r0.L$3 = r6     // Catch:{ all -> 0x0091 }
            r0.label = r3     // Catch:{ all -> 0x0091 }
            java.lang.Object r4 = r6.hasNext(r0)     // Catch:{ all -> 0x0091 }
            if (r4 != r1) goto L_0x006a
            return r1
        L_0x006a:
            r5 = r0
            r0 = r8
            r8 = r4
            goto L_0x003c
        L_0x006e:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x008e }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x008e }
            if (r8 == 0) goto L_0x0082
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x008e }
            r7.invoke(r8)     // Catch:{ all -> 0x008e }
            r8 = r0
            r0 = r1
            r1 = r2
            r2 = r4
            goto L_0x0059
        L_0x0082:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x008e }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r6
        L_0x008e:
            r6 = move-exception
            r2 = r4
            goto L_0x0092
        L_0x0091:
            r6 = move-exception
        L_0x0092:
            throw r6     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxChannelKt.consumeEach(io.reactivex.ObservableSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        throw r6;
     */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Use collect instead", replaceWith = @kotlin.ReplaceWith(expression = "this.collect(action)", imports = {}))
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object consumeEach$$forInline(p014io.reactivex.ObservableSource r4, kotlin.jvm.functions.Function1 r5, kotlin.coroutines.Continuation r6) {
        /*
            kotlinx.coroutines.channels.ReceiveChannel r4 = openSubscription(r4)
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r4.iterator()     // Catch:{ all -> 0x0033 }
        L_0x000c:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0033 }
            java.lang.Object r3 = r2.hasNext(r6)     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0033 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0033 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0033 }
            if (r3 == 0) goto L_0x0027
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0033 }
            r5.invoke(r3)     // Catch:{ all -> 0x0033 }
            goto L_0x000c
        L_0x0027:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r5
        L_0x0033:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxChannelKt.consumeEach$$forInline(io.reactivex.ObservableSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[Catch:{ all -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object collect(p014io.reactivex.MaybeSource<T> r6, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.rx2.RxChannelKt$collect$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.rx2.RxChannelKt$collect$1 r0 = (kotlinx.coroutines.rx2.RxChannelKt$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.rx2.RxChannelKt$collect$1 r0 = new kotlinx.coroutines.rx2.RxChannelKt$collect$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$2
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0091 }
            r5 = r0
            r0 = r7
            r7 = r4
        L_0x003c:
            r4 = r2
            r2 = r1
            r1 = r5
            goto L_0x006e
        L_0x0040:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ReceiveChannel r2 = openSubscription(r6)
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r8 = r2.iterator()     // Catch:{ all -> 0x0091 }
            r5 = r8
            r8 = r6
            r6 = r5
        L_0x0059:
            r0.L$0 = r7     // Catch:{ all -> 0x0091 }
            r0.L$1 = r2     // Catch:{ all -> 0x0091 }
            r0.L$2 = r8     // Catch:{ all -> 0x0091 }
            r0.L$3 = r6     // Catch:{ all -> 0x0091 }
            r0.label = r3     // Catch:{ all -> 0x0091 }
            java.lang.Object r4 = r6.hasNext(r0)     // Catch:{ all -> 0x0091 }
            if (r4 != r1) goto L_0x006a
            return r1
        L_0x006a:
            r5 = r0
            r0 = r8
            r8 = r4
            goto L_0x003c
        L_0x006e:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x008e }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x008e }
            if (r8 == 0) goto L_0x0082
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x008e }
            r7.invoke(r8)     // Catch:{ all -> 0x008e }
            r8 = r0
            r0 = r1
            r1 = r2
            r2 = r4
            goto L_0x0059
        L_0x0082:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x008e }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r6
        L_0x008e:
            r6 = move-exception
            r2 = r4
            goto L_0x0092
        L_0x0091:
            r6 = move-exception
        L_0x0092:
            throw r6     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxChannelKt.collect(io.reactivex.MaybeSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object collect$$forInline(p014io.reactivex.MaybeSource r4, kotlin.jvm.functions.Function1 r5, kotlin.coroutines.Continuation r6) {
        /*
            kotlinx.coroutines.channels.ReceiveChannel r4 = openSubscription(r4)
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r4.iterator()     // Catch:{ all -> 0x0033 }
        L_0x000c:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0033 }
            java.lang.Object r3 = r2.hasNext(r6)     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0033 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0033 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0033 }
            if (r3 == 0) goto L_0x0027
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0033 }
            r5.invoke(r3)     // Catch:{ all -> 0x0033 }
            goto L_0x000c
        L_0x0027:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r5
        L_0x0033:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxChannelKt.collect$$forInline(io.reactivex.MaybeSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[Catch:{ all -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object collect(p014io.reactivex.ObservableSource<T> r6, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.rx2.RxChannelKt$collect$2
            if (r0 == 0) goto L_0x0014
            r0 = r8
            kotlinx.coroutines.rx2.RxChannelKt$collect$2 r0 = (kotlinx.coroutines.rx2.RxChannelKt$collect$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.rx2.RxChannelKt$collect$2 r0 = new kotlinx.coroutines.rx2.RxChannelKt$collect$2
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$2
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0091 }
            r5 = r0
            r0 = r7
            r7 = r4
        L_0x003c:
            r4 = r2
            r2 = r1
            r1 = r5
            goto L_0x006e
        L_0x0040:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ReceiveChannel r2 = openSubscription(r6)
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r8 = r2.iterator()     // Catch:{ all -> 0x0091 }
            r5 = r8
            r8 = r6
            r6 = r5
        L_0x0059:
            r0.L$0 = r7     // Catch:{ all -> 0x0091 }
            r0.L$1 = r2     // Catch:{ all -> 0x0091 }
            r0.L$2 = r8     // Catch:{ all -> 0x0091 }
            r0.L$3 = r6     // Catch:{ all -> 0x0091 }
            r0.label = r3     // Catch:{ all -> 0x0091 }
            java.lang.Object r4 = r6.hasNext(r0)     // Catch:{ all -> 0x0091 }
            if (r4 != r1) goto L_0x006a
            return r1
        L_0x006a:
            r5 = r0
            r0 = r8
            r8 = r4
            goto L_0x003c
        L_0x006e:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x008e }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x008e }
            if (r8 == 0) goto L_0x0082
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x008e }
            r7.invoke(r8)     // Catch:{ all -> 0x008e }
            r8 = r0
            r0 = r1
            r1 = r2
            r2 = r4
            goto L_0x0059
        L_0x0082:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x008e }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r6
        L_0x008e:
            r6 = move-exception
            r2 = r4
            goto L_0x0092
        L_0x0091:
            r6 = move-exception
        L_0x0092:
            throw r6     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxChannelKt.collect(io.reactivex.ObservableSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object collect$$forInline(p014io.reactivex.ObservableSource r4, kotlin.jvm.functions.Function1 r5, kotlin.coroutines.Continuation r6) {
        /*
            kotlinx.coroutines.channels.ReceiveChannel r4 = openSubscription(r4)
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r4.iterator()     // Catch:{ all -> 0x0033 }
        L_0x000c:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0033 }
            java.lang.Object r3 = r2.hasNext(r6)     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0033 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0033 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0033 }
            if (r3 == 0) goto L_0x0027
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0033 }
            r5.invoke(r3)     // Catch:{ all -> 0x0033 }
            goto L_0x000c
        L_0x0027:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r5
        L_0x0033:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r5)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx2.RxChannelKt.collect$$forInline(io.reactivex.ObservableSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
