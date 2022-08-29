package p014io.reactivex.internal.operators.parallel;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.operators.flowable.FlowableFlatMap;
import p014io.reactivex.parallel.ParallelFlowable;

/* renamed from: io.reactivex.internal.operators.parallel.ParallelFlatMap */
public final class ParallelFlatMap<T, R> extends ParallelFlowable<R> {
    final boolean delayError;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;
    final int maxConcurrency;
    final int prefetch;
    final ParallelFlowable<T> source;

    public ParallelFlatMap(ParallelFlowable<T> parallelFlowable, Function<? super T, ? extends Publisher<? extends R>> function, boolean z, int i, int i2) {
        this.source = parallelFlowable;
        this.mapper = function;
        this.delayError = z;
        this.maxConcurrency = i;
        this.prefetch = i2;
    }

    public int parallelism() {
        return this.source.parallelism();
    }

    public void subscribe(Subscriber<? super R>[] subscriberArr) {
        if (validate(subscriberArr)) {
            int length = subscriberArr.length;
            Subscriber[] subscriberArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                subscriberArr2[i] = FlowableFlatMap.subscribe(subscriberArr[i], this.mapper, this.delayError, this.maxConcurrency, this.prefetch);
            }
            this.source.subscribe(subscriberArr2);
        }
    }
}
