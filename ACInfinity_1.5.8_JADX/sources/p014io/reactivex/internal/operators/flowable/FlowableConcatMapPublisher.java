package p014io.reactivex.internal.operators.flowable;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import p014io.reactivex.Flowable;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.util.ErrorMode;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableConcatMapPublisher */
public final class FlowableConcatMapPublisher<T, R> extends Flowable<R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;
    final int prefetch;
    final Publisher<T> source;

    public FlowableConcatMapPublisher(Publisher<T> publisher, Function<? super T, ? extends Publisher<? extends R>> function, int i, ErrorMode errorMode2) {
        this.source = publisher;
        this.mapper = function;
        this.prefetch = i;
        this.errorMode = errorMode2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super R> subscriber) {
        if (!FlowableScalarXMap.tryScalarXMapSubscribe(this.source, subscriber, this.mapper)) {
            this.source.subscribe(FlowableConcatMap.subscribe(subscriber, this.mapper, this.prefetch, this.errorMode));
        }
    }
}
