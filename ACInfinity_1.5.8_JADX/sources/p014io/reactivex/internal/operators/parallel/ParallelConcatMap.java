package p014io.reactivex.internal.operators.parallel;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.operators.flowable.FlowableConcatMap;
import p014io.reactivex.internal.util.ErrorMode;
import p014io.reactivex.parallel.ParallelFlowable;

/* renamed from: io.reactivex.internal.operators.parallel.ParallelConcatMap */
public final class ParallelConcatMap<T, R> extends ParallelFlowable<R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;
    final int prefetch;
    final ParallelFlowable<T> source;

    public ParallelConcatMap(ParallelFlowable<T> parallelFlowable, Function<? super T, ? extends Publisher<? extends R>> function, int i, ErrorMode errorMode2) {
        this.source = parallelFlowable;
        this.mapper = (Function) ObjectHelper.requireNonNull(function, "mapper");
        this.prefetch = i;
        this.errorMode = (ErrorMode) ObjectHelper.requireNonNull(errorMode2, "errorMode");
    }

    public int parallelism() {
        return this.source.parallelism();
    }

    public void subscribe(Subscriber<? super R>[] subscriberArr) {
        if (validate(subscriberArr)) {
            int length = subscriberArr.length;
            Subscriber[] subscriberArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                subscriberArr2[i] = FlowableConcatMap.subscribe(subscriberArr[i], this.mapper, this.prefetch, this.errorMode);
            }
            this.source.subscribe(subscriberArr2);
        }
    }
}
