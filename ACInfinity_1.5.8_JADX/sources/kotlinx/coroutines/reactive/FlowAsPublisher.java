package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\u0006\b\u0000\u0012\u00028\u0000\u0018\u00010\fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo27512d2 = {"Lkotlinx/coroutines/reactive/FlowAsPublisher;", "T", "", "Lorg/reactivestreams/Publisher;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;)V", "subscribe", "", "subscriber", "Lorg/reactivestreams/Subscriber;", "kotlinx-coroutines-reactive"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ReactiveFlow.kt */
final class FlowAsPublisher<T> implements Publisher<T> {
    private final CoroutineContext context;
    private final Flow<T> flow;

    public FlowAsPublisher(Flow<? extends T> flow2, CoroutineContext coroutineContext) {
        this.flow = flow2;
        this.context = coroutineContext;
    }

    public void subscribe(Subscriber<? super T> subscriber) {
        if (subscriber != null) {
            subscriber.onSubscribe(new FlowSubscription(this.flow, subscriber, this.context));
            return;
        }
        throw new NullPointerException();
    }
}
