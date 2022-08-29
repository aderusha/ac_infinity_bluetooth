package p014io.reactivex.internal.operators.flowable;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Emitter;
import p014io.reactivex.Flowable;
import p014io.reactivex.Scheduler;
import p014io.reactivex.flowables.ConnectableFlowable;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.BiConsumer;
import p014io.reactivex.functions.BiFunction;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.functions.Functions;
import p014io.reactivex.internal.functions.ObjectHelper;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper */
public final class FlowableInternalHelper {
    private FlowableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$SimpleGenerator */
    static final class SimpleGenerator<T, S> implements BiFunction<S, Emitter<T>, S> {
        final Consumer<Emitter<T>> consumer;

        SimpleGenerator(Consumer<Emitter<T>> consumer2) {
            this.consumer = consumer2;
        }

        public S apply(S s, Emitter<T> emitter) throws Exception {
            this.consumer.accept(emitter);
            return s;
        }
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleGenerator(Consumer<Emitter<T>> consumer) {
        return new SimpleGenerator(consumer);
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$SimpleBiGenerator */
    static final class SimpleBiGenerator<T, S> implements BiFunction<S, Emitter<T>, S> {
        final BiConsumer<S, Emitter<T>> consumer;

        SimpleBiGenerator(BiConsumer<S, Emitter<T>> biConsumer) {
            this.consumer = biConsumer;
        }

        public S apply(S s, Emitter<T> emitter) throws Exception {
            this.consumer.accept(s, emitter);
            return s;
        }
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleBiGenerator(BiConsumer<S, Emitter<T>> biConsumer) {
        return new SimpleBiGenerator(biConsumer);
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$ItemDelayFunction */
    static final class ItemDelayFunction<T, U> implements Function<T, Publisher<T>> {
        final Function<? super T, ? extends Publisher<U>> itemDelay;

        ItemDelayFunction(Function<? super T, ? extends Publisher<U>> function) {
            this.itemDelay = function;
        }

        public Publisher<T> apply(T t) throws Exception {
            return new FlowableTakePublisher((Publisher) ObjectHelper.requireNonNull(this.itemDelay.apply(t), "The itemDelay returned a null Publisher"), 1).map(Functions.justFunction(t)).defaultIfEmpty(t);
        }
    }

    public static <T, U> Function<T, Publisher<T>> itemDelay(Function<? super T, ? extends Publisher<U>> function) {
        return new ItemDelayFunction(function);
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$SubscriberOnNext */
    static final class SubscriberOnNext<T> implements Consumer<T> {
        final Subscriber<T> subscriber;

        SubscriberOnNext(Subscriber<T> subscriber2) {
            this.subscriber = subscriber2;
        }

        public void accept(T t) throws Exception {
            this.subscriber.onNext(t);
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$SubscriberOnError */
    static final class SubscriberOnError<T> implements Consumer<Throwable> {
        final Subscriber<T> subscriber;

        SubscriberOnError(Subscriber<T> subscriber2) {
            this.subscriber = subscriber2;
        }

        public void accept(Throwable th) throws Exception {
            this.subscriber.onError(th);
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$SubscriberOnComplete */
    static final class SubscriberOnComplete<T> implements Action {
        final Subscriber<T> subscriber;

        SubscriberOnComplete(Subscriber<T> subscriber2) {
            this.subscriber = subscriber2;
        }

        public void run() throws Exception {
            this.subscriber.onComplete();
        }
    }

    public static <T> Consumer<T> subscriberOnNext(Subscriber<T> subscriber) {
        return new SubscriberOnNext(subscriber);
    }

    public static <T> Consumer<Throwable> subscriberOnError(Subscriber<T> subscriber) {
        return new SubscriberOnError(subscriber);
    }

    public static <T> Action subscriberOnComplete(Subscriber<T> subscriber) {
        return new SubscriberOnComplete(subscriber);
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$FlatMapWithCombinerInner */
    static final class FlatMapWithCombinerInner<U, R, T> implements Function<U, R> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;

        /* renamed from: t */
        private final T f1111t;

        FlatMapWithCombinerInner(BiFunction<? super T, ? super U, ? extends R> biFunction, T t) {
            this.combiner = biFunction;
            this.f1111t = t;
        }

        public R apply(U u) throws Exception {
            return this.combiner.apply(this.f1111t, u);
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$FlatMapWithCombinerOuter */
    static final class FlatMapWithCombinerOuter<T, R, U> implements Function<T, Publisher<R>> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final Function<? super T, ? extends Publisher<? extends U>> mapper;

        FlatMapWithCombinerOuter(BiFunction<? super T, ? super U, ? extends R> biFunction, Function<? super T, ? extends Publisher<? extends U>> function) {
            this.combiner = biFunction;
            this.mapper = function;
        }

        public Publisher<R> apply(T t) throws Exception {
            return new FlowableMapPublisher((Publisher) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher"), new FlatMapWithCombinerInner(this.combiner, t));
        }
    }

    public static <T, U, R> Function<T, Publisher<R>> flatMapWithCombiner(Function<? super T, ? extends Publisher<? extends U>> function, BiFunction<? super T, ? super U, ? extends R> biFunction) {
        return new FlatMapWithCombinerOuter(biFunction, function);
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$FlatMapIntoIterable */
    static final class FlatMapIntoIterable<T, U> implements Function<T, Publisher<U>> {
        private final Function<? super T, ? extends Iterable<? extends U>> mapper;

        FlatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> function) {
            this.mapper = function;
        }

        public Publisher<U> apply(T t) throws Exception {
            return new FlowableFromIterable((Iterable) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Iterable"));
        }
    }

    public static <T, U> Function<T, Publisher<U>> flatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> function) {
        return new FlatMapIntoIterable(function);
    }

    public static <T> Callable<ConnectableFlowable<T>> replayCallable(Flowable<T> flowable) {
        return new ReplayCallable(flowable);
    }

    public static <T> Callable<ConnectableFlowable<T>> replayCallable(Flowable<T> flowable, int i) {
        return new BufferedReplayCallable(flowable, i);
    }

    public static <T> Callable<ConnectableFlowable<T>> replayCallable(Flowable<T> flowable, int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        return new BufferedTimedReplay(flowable, i, j, timeUnit, scheduler);
    }

    public static <T> Callable<ConnectableFlowable<T>> replayCallable(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler) {
        return new TimedReplay(flowable, j, timeUnit, scheduler);
    }

    public static <T, R> Function<Flowable<T>, Publisher<R>> replayFunction(Function<? super Flowable<T>, ? extends Publisher<R>> function, Scheduler scheduler) {
        return new ReplayFunction(function, scheduler);
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$RequestMax */
    public enum RequestMax implements Consumer<Subscription> {
        INSTANCE;

        public void accept(Subscription subscription) throws Exception {
            subscription.request(Long.MAX_VALUE);
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$ZipIterableFunction */
    static final class ZipIterableFunction<T, R> implements Function<List<Publisher<? extends T>>, Publisher<? extends R>> {
        private final Function<? super Object[], ? extends R> zipper;

        ZipIterableFunction(Function<? super Object[], ? extends R> function) {
            this.zipper = function;
        }

        public Publisher<? extends R> apply(List<Publisher<? extends T>> list) {
            return Flowable.zipIterable(list, this.zipper, false, Flowable.bufferSize());
        }
    }

    public static <T, R> Function<List<Publisher<? extends T>>, Publisher<? extends R>> zipIterable(Function<? super Object[], ? extends R> function) {
        return new ZipIterableFunction(function);
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$ReplayCallable */
    static final class ReplayCallable<T> implements Callable<ConnectableFlowable<T>> {
        private final Flowable<T> parent;

        ReplayCallable(Flowable<T> flowable) {
            this.parent = flowable;
        }

        public ConnectableFlowable<T> call() {
            return this.parent.replay();
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$BufferedReplayCallable */
    static final class BufferedReplayCallable<T> implements Callable<ConnectableFlowable<T>> {
        private final int bufferSize;
        private final Flowable<T> parent;

        BufferedReplayCallable(Flowable<T> flowable, int i) {
            this.parent = flowable;
            this.bufferSize = i;
        }

        public ConnectableFlowable<T> call() {
            return this.parent.replay(this.bufferSize);
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$BufferedTimedReplay */
    static final class BufferedTimedReplay<T> implements Callable<ConnectableFlowable<T>> {
        private final int bufferSize;
        private final Flowable<T> parent;
        private final Scheduler scheduler;
        private final long time;
        private final TimeUnit unit;

        BufferedTimedReplay(Flowable<T> flowable, int i, long j, TimeUnit timeUnit, Scheduler scheduler2) {
            this.parent = flowable;
            this.bufferSize = i;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = scheduler2;
        }

        public ConnectableFlowable<T> call() {
            return this.parent.replay(this.bufferSize, this.time, this.unit, this.scheduler);
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$TimedReplay */
    static final class TimedReplay<T> implements Callable<ConnectableFlowable<T>> {
        private final Flowable<T> parent;
        private final Scheduler scheduler;
        private final long time;
        private final TimeUnit unit;

        TimedReplay(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler2) {
            this.parent = flowable;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = scheduler2;
        }

        public ConnectableFlowable<T> call() {
            return this.parent.replay(this.time, this.unit, this.scheduler);
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableInternalHelper$ReplayFunction */
    static final class ReplayFunction<T, R> implements Function<Flowable<T>, Publisher<R>> {
        private final Scheduler scheduler;
        private final Function<? super Flowable<T>, ? extends Publisher<R>> selector;

        ReplayFunction(Function<? super Flowable<T>, ? extends Publisher<R>> function, Scheduler scheduler2) {
            this.selector = function;
            this.scheduler = scheduler2;
        }

        public Publisher<R> apply(Flowable<T> flowable) throws Exception {
            return Flowable.fromPublisher((Publisher) ObjectHelper.requireNonNull(this.selector.apply(flowable), "The selector returned a null Publisher")).observeOn(this.scheduler);
        }
    }
}
