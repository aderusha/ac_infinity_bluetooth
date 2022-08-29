package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.SendChannel;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\b\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0015\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000eH\u0016J\u0013\u0010\u001b\u001a\u0004\u0018\u00018\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u001cR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, mo27512d2 = {"Lkotlinx/coroutines/reactive/ReactiveSubscriber;", "T", "", "Lorg/reactivestreams/Subscriber;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "requestSize", "", "(ILkotlinx/coroutines/channels/BufferOverflow;J)V", "channel", "Lkotlinx/coroutines/channels/Channel;", "subscription", "Lorg/reactivestreams/Subscription;", "cancel", "", "makeRequest", "onComplete", "onError", "t", "", "onNext", "value", "(Ljava/lang/Object;)V", "onSubscribe", "s", "takeNextOrNull", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-reactive"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ReactiveFlow.kt */
final class ReactiveSubscriber<T> implements Subscriber<T> {
    private final Channel<T> channel;
    private final long requestSize;
    private Subscription subscription;

    public ReactiveSubscriber(int i, BufferOverflow bufferOverflow, long j) {
        this.requestSize = j;
        this.channel = ChannelKt.Channel$default(i == 0 ? 1 : i, bufferOverflow, (Function1) null, 4, (Object) null);
    }

    public final Object takeNextOrNull(Continuation<? super T> continuation) {
        return ChannelsKt.receiveOrNull(this.channel, continuation);
    }

    public void onNext(T t) {
        if (!this.channel.offer(t)) {
            throw new IllegalArgumentException(("Element " + t + " was not added to channel because it was full, " + this.channel).toString());
        }
    }

    public void onComplete() {
        SendChannel.DefaultImpls.close$default(this.channel, (Throwable) null, 1, (Object) null);
    }

    public void onError(Throwable th) {
        this.channel.close(th);
    }

    public void onSubscribe(Subscription subscription2) {
        this.subscription = subscription2;
        makeRequest();
    }

    public final void makeRequest() {
        Subscription subscription2 = this.subscription;
        if (subscription2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subscription");
        }
        subscription2.request(this.requestSize);
    }

    public final void cancel() {
        Subscription subscription2 = this.subscription;
        if (subscription2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subscription");
        }
        subscription2.cancel();
    }
}
