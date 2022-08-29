package p014io.reactivex;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* renamed from: io.reactivex.FlowableSubscriber */
public interface FlowableSubscriber<T> extends Subscriber<T> {
    void onSubscribe(Subscription subscription);
}
