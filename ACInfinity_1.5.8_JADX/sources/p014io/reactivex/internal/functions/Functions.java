package p014io.reactivex.internal.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscription;
import p014io.reactivex.Notification;
import p014io.reactivex.Scheduler;
import p014io.reactivex.exceptions.OnErrorNotImplementedException;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.BiConsumer;
import p014io.reactivex.functions.BiFunction;
import p014io.reactivex.functions.BooleanSupplier;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.functions.Function3;
import p014io.reactivex.functions.Function4;
import p014io.reactivex.functions.Function5;
import p014io.reactivex.functions.Function6;
import p014io.reactivex.functions.Function7;
import p014io.reactivex.functions.Function8;
import p014io.reactivex.functions.Function9;
import p014io.reactivex.functions.LongConsumer;
import p014io.reactivex.functions.Predicate;
import p014io.reactivex.plugins.RxJavaPlugins;
import p014io.reactivex.schedulers.Timed;

/* renamed from: io.reactivex.internal.functions.Functions */
public final class Functions {
    static final Predicate<Object> ALWAYS_FALSE = new FalsePredicate();
    static final Predicate<Object> ALWAYS_TRUE = new TruePredicate();
    public static final Action EMPTY_ACTION = new EmptyAction();
    static final Consumer<Object> EMPTY_CONSUMER = new EmptyConsumer();
    public static final LongConsumer EMPTY_LONG_CONSUMER = new EmptyLongConsumer();
    public static final Runnable EMPTY_RUNNABLE = new EmptyRunnable();
    public static final Consumer<Throwable> ERROR_CONSUMER = new ErrorConsumer();
    static final Function<Object, Object> IDENTITY = new Identity();
    static final Comparator<Object> NATURAL_COMPARATOR = new NaturalObjectComparator();
    static final Callable<Object> NULL_SUPPLIER = new NullCallable();
    public static final Consumer<Throwable> ON_ERROR_MISSING = new OnErrorMissingConsumer();
    public static final Consumer<Subscription> REQUEST_MAX = new MaxRequestSubscription();

    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T1, T2, R> Function<Object[], R> toFunction(BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
        ObjectHelper.requireNonNull(biFunction, "f is null");
        return new Array2Func(biFunction);
    }

    public static <T1, T2, T3, R> Function<Object[], R> toFunction(Function3<T1, T2, T3, R> function3) {
        ObjectHelper.requireNonNull(function3, "f is null");
        return new Array3Func(function3);
    }

    public static <T1, T2, T3, T4, R> Function<Object[], R> toFunction(Function4<T1, T2, T3, T4, R> function4) {
        ObjectHelper.requireNonNull(function4, "f is null");
        return new Array4Func(function4);
    }

    public static <T1, T2, T3, T4, T5, R> Function<Object[], R> toFunction(Function5<T1, T2, T3, T4, T5, R> function5) {
        ObjectHelper.requireNonNull(function5, "f is null");
        return new Array5Func(function5);
    }

    public static <T1, T2, T3, T4, T5, T6, R> Function<Object[], R> toFunction(Function6<T1, T2, T3, T4, T5, T6, R> function6) {
        ObjectHelper.requireNonNull(function6, "f is null");
        return new Array6Func(function6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Function<Object[], R> toFunction(Function7<T1, T2, T3, T4, T5, T6, T7, R> function7) {
        ObjectHelper.requireNonNull(function7, "f is null");
        return new Array7Func(function7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Function<Object[], R> toFunction(Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function8) {
        ObjectHelper.requireNonNull(function8, "f is null");
        return new Array8Func(function8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Function<Object[], R> toFunction(Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function9) {
        ObjectHelper.requireNonNull(function9, "f is null");
        return new Array9Func(function9);
    }

    public static <T> Function<T, T> identity() {
        return IDENTITY;
    }

    public static <T> Consumer<T> emptyConsumer() {
        return EMPTY_CONSUMER;
    }

    public static <T> Predicate<T> alwaysTrue() {
        return ALWAYS_TRUE;
    }

    public static <T> Predicate<T> alwaysFalse() {
        return ALWAYS_FALSE;
    }

    public static <T> Callable<T> nullSupplier() {
        return NULL_SUPPLIER;
    }

    public static <T> Comparator<T> naturalOrder() {
        return NATURAL_COMPARATOR;
    }

    /* renamed from: io.reactivex.internal.functions.Functions$FutureAction */
    static final class FutureAction implements Action {
        final Future<?> future;

        FutureAction(Future<?> future2) {
            this.future = future2;
        }

        public void run() throws Exception {
            this.future.get();
        }
    }

    public static Action futureAction(Future<?> future) {
        return new FutureAction(future);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$JustValue */
    static final class JustValue<T, U> implements Callable<U>, Function<T, U> {
        final U value;

        JustValue(U u) {
            this.value = u;
        }

        public U call() throws Exception {
            return this.value;
        }

        public U apply(T t) throws Exception {
            return this.value;
        }
    }

    public static <T> Callable<T> justCallable(T t) {
        return new JustValue(t);
    }

    public static <T, U> Function<T, U> justFunction(U u) {
        return new JustValue(u);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$CastToClass */
    static final class CastToClass<T, U> implements Function<T, U> {
        final Class<U> clazz;

        CastToClass(Class<U> cls) {
            this.clazz = cls;
        }

        public U apply(T t) throws Exception {
            return this.clazz.cast(t);
        }
    }

    public static <T, U> Function<T, U> castFunction(Class<U> cls) {
        return new CastToClass(cls);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$ArrayListCapacityCallable */
    static final class ArrayListCapacityCallable<T> implements Callable<List<T>> {
        final int capacity;

        ArrayListCapacityCallable(int i) {
            this.capacity = i;
        }

        public List<T> call() throws Exception {
            return new ArrayList(this.capacity);
        }
    }

    public static <T> Callable<List<T>> createArrayList(int i) {
        return new ArrayListCapacityCallable(i);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$EqualsPredicate */
    static final class EqualsPredicate<T> implements Predicate<T> {
        final T value;

        EqualsPredicate(T t) {
            this.value = t;
        }

        public boolean test(T t) throws Exception {
            return ObjectHelper.equals(t, this.value);
        }
    }

    public static <T> Predicate<T> equalsWith(T t) {
        return new EqualsPredicate(t);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$HashSetCallable */
    enum HashSetCallable implements Callable<Set<Object>> {
        INSTANCE;

        public Set<Object> call() throws Exception {
            return new HashSet();
        }
    }

    public static <T> Callable<Set<T>> createHashSet() {
        return HashSetCallable.INSTANCE;
    }

    /* renamed from: io.reactivex.internal.functions.Functions$NotificationOnNext */
    static final class NotificationOnNext<T> implements Consumer<T> {
        final Consumer<? super Notification<T>> onNotification;

        NotificationOnNext(Consumer<? super Notification<T>> consumer) {
            this.onNotification = consumer;
        }

        public void accept(T t) throws Exception {
            this.onNotification.accept(Notification.createOnNext(t));
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$NotificationOnError */
    static final class NotificationOnError<T> implements Consumer<Throwable> {
        final Consumer<? super Notification<T>> onNotification;

        NotificationOnError(Consumer<? super Notification<T>> consumer) {
            this.onNotification = consumer;
        }

        public void accept(Throwable th) throws Exception {
            this.onNotification.accept(Notification.createOnError(th));
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$NotificationOnComplete */
    static final class NotificationOnComplete<T> implements Action {
        final Consumer<? super Notification<T>> onNotification;

        NotificationOnComplete(Consumer<? super Notification<T>> consumer) {
            this.onNotification = consumer;
        }

        public void run() throws Exception {
            this.onNotification.accept(Notification.createOnComplete());
        }
    }

    public static <T> Consumer<T> notificationOnNext(Consumer<? super Notification<T>> consumer) {
        return new NotificationOnNext(consumer);
    }

    public static <T> Consumer<Throwable> notificationOnError(Consumer<? super Notification<T>> consumer) {
        return new NotificationOnError(consumer);
    }

    public static <T> Action notificationOnComplete(Consumer<? super Notification<T>> consumer) {
        return new NotificationOnComplete(consumer);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$ActionConsumer */
    static final class ActionConsumer<T> implements Consumer<T> {
        final Action action;

        ActionConsumer(Action action2) {
            this.action = action2;
        }

        public void accept(T t) throws Exception {
            this.action.run();
        }
    }

    public static <T> Consumer<T> actionConsumer(Action action) {
        return new ActionConsumer(action);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$ClassFilter */
    static final class ClassFilter<T, U> implements Predicate<T> {
        final Class<U> clazz;

        ClassFilter(Class<U> cls) {
            this.clazz = cls;
        }

        public boolean test(T t) throws Exception {
            return this.clazz.isInstance(t);
        }
    }

    public static <T, U> Predicate<T> isInstanceOf(Class<U> cls) {
        return new ClassFilter(cls);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$BooleanSupplierPredicateReverse */
    static final class BooleanSupplierPredicateReverse<T> implements Predicate<T> {
        final BooleanSupplier supplier;

        BooleanSupplierPredicateReverse(BooleanSupplier booleanSupplier) {
            this.supplier = booleanSupplier;
        }

        public boolean test(T t) throws Exception {
            return !this.supplier.getAsBoolean();
        }
    }

    public static <T> Predicate<T> predicateReverseFor(BooleanSupplier booleanSupplier) {
        return new BooleanSupplierPredicateReverse(booleanSupplier);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$TimestampFunction */
    static final class TimestampFunction<T> implements Function<T, Timed<T>> {
        final Scheduler scheduler;
        final TimeUnit unit;

        TimestampFunction(TimeUnit timeUnit, Scheduler scheduler2) {
            this.unit = timeUnit;
            this.scheduler = scheduler2;
        }

        public Timed<T> apply(T t) throws Exception {
            return new Timed<>(t, this.scheduler.now(this.unit), this.unit);
        }
    }

    public static <T> Function<T, Timed<T>> timestampWith(TimeUnit timeUnit, Scheduler scheduler) {
        return new TimestampFunction(timeUnit, scheduler);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$ToMapKeySelector */
    static final class ToMapKeySelector<K, T> implements BiConsumer<Map<K, T>, T> {
        private final Function<? super T, ? extends K> keySelector;

        ToMapKeySelector(Function<? super T, ? extends K> function) {
            this.keySelector = function;
        }

        public void accept(Map<K, T> map, T t) throws Exception {
            map.put(this.keySelector.apply(t), t);
        }
    }

    public static <T, K> BiConsumer<Map<K, T>, T> toMapKeySelector(Function<? super T, ? extends K> function) {
        return new ToMapKeySelector(function);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$ToMapKeyValueSelector */
    static final class ToMapKeyValueSelector<K, V, T> implements BiConsumer<Map<K, V>, T> {
        private final Function<? super T, ? extends K> keySelector;
        private final Function<? super T, ? extends V> valueSelector;

        ToMapKeyValueSelector(Function<? super T, ? extends V> function, Function<? super T, ? extends K> function2) {
            this.valueSelector = function;
            this.keySelector = function2;
        }

        public void accept(Map<K, V> map, T t) throws Exception {
            map.put(this.keySelector.apply(t), this.valueSelector.apply(t));
        }
    }

    public static <T, K, V> BiConsumer<Map<K, V>, T> toMapKeyValueSelector(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        return new ToMapKeyValueSelector(function2, function);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$ToMultimapKeyValueSelector */
    static final class ToMultimapKeyValueSelector<K, V, T> implements BiConsumer<Map<K, Collection<V>>, T> {
        private final Function<? super K, ? extends Collection<? super V>> collectionFactory;
        private final Function<? super T, ? extends K> keySelector;
        private final Function<? super T, ? extends V> valueSelector;

        ToMultimapKeyValueSelector(Function<? super K, ? extends Collection<? super V>> function, Function<? super T, ? extends V> function2, Function<? super T, ? extends K> function3) {
            this.collectionFactory = function;
            this.valueSelector = function2;
            this.keySelector = function3;
        }

        public void accept(Map<K, Collection<V>> map, T t) throws Exception {
            Object apply = this.keySelector.apply(t);
            Collection collection = map.get(apply);
            if (collection == null) {
                collection = (Collection) this.collectionFactory.apply(apply);
                map.put(apply, collection);
            }
            collection.add(this.valueSelector.apply(t));
        }
    }

    public static <T, K, V> BiConsumer<Map<K, Collection<V>>, T> toMultimapKeyValueSelector(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, Function<? super K, ? extends Collection<? super V>> function3) {
        return new ToMultimapKeyValueSelector(function3, function2, function);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$NaturalComparator */
    enum NaturalComparator implements Comparator<Object> {
        INSTANCE;

        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    public static <T> Comparator<T> naturalComparator() {
        return NaturalComparator.INSTANCE;
    }

    /* renamed from: io.reactivex.internal.functions.Functions$ListSorter */
    static final class ListSorter<T> implements Function<List<T>, List<T>> {
        final Comparator<? super T> comparator;

        ListSorter(Comparator<? super T> comparator2) {
            this.comparator = comparator2;
        }

        public List<T> apply(List<T> list) {
            Collections.sort(list, this.comparator);
            return list;
        }
    }

    public static <T> Function<List<T>, List<T>> listSorter(Comparator<? super T> comparator) {
        return new ListSorter(comparator);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$Array2Func */
    static final class Array2Func<T1, T2, R> implements Function<Object[], R> {

        /* renamed from: f */
        final BiFunction<? super T1, ? super T2, ? extends R> f1078f;

        Array2Func(BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
            this.f1078f = biFunction;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 2) {
                return this.f1078f.apply(objArr[0], objArr[1]);
            }
            throw new IllegalArgumentException("Array of size 2 expected but got " + objArr.length);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$Array3Func */
    static final class Array3Func<T1, T2, T3, R> implements Function<Object[], R> {

        /* renamed from: f */
        final Function3<T1, T2, T3, R> f1079f;

        Array3Func(Function3<T1, T2, T3, R> function3) {
            this.f1079f = function3;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 3) {
                return this.f1079f.apply(objArr[0], objArr[1], objArr[2]);
            }
            throw new IllegalArgumentException("Array of size 3 expected but got " + objArr.length);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$Array4Func */
    static final class Array4Func<T1, T2, T3, T4, R> implements Function<Object[], R> {

        /* renamed from: f */
        final Function4<T1, T2, T3, T4, R> f1080f;

        Array4Func(Function4<T1, T2, T3, T4, R> function4) {
            this.f1080f = function4;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 4) {
                return this.f1080f.apply(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
            throw new IllegalArgumentException("Array of size 4 expected but got " + objArr.length);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$Array5Func */
    static final class Array5Func<T1, T2, T3, T4, T5, R> implements Function<Object[], R> {

        /* renamed from: f */
        private final Function5<T1, T2, T3, T4, T5, R> f1081f;

        Array5Func(Function5<T1, T2, T3, T4, T5, R> function5) {
            this.f1081f = function5;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 5) {
                return this.f1081f.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
            throw new IllegalArgumentException("Array of size 5 expected but got " + objArr.length);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$Array6Func */
    static final class Array6Func<T1, T2, T3, T4, T5, T6, R> implements Function<Object[], R> {

        /* renamed from: f */
        final Function6<T1, T2, T3, T4, T5, T6, R> f1082f;

        Array6Func(Function6<T1, T2, T3, T4, T5, T6, R> function6) {
            this.f1082f = function6;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 6) {
                return this.f1082f.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
            throw new IllegalArgumentException("Array of size 6 expected but got " + objArr.length);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$Array7Func */
    static final class Array7Func<T1, T2, T3, T4, T5, T6, T7, R> implements Function<Object[], R> {

        /* renamed from: f */
        final Function7<T1, T2, T3, T4, T5, T6, T7, R> f1083f;

        Array7Func(Function7<T1, T2, T3, T4, T5, T6, T7, R> function7) {
            this.f1083f = function7;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 7) {
                return this.f1083f.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
            throw new IllegalArgumentException("Array of size 7 expected but got " + objArr.length);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$Array8Func */
    static final class Array8Func<T1, T2, T3, T4, T5, T6, T7, T8, R> implements Function<Object[], R> {

        /* renamed from: f */
        final Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> f1084f;

        Array8Func(Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function8) {
            this.f1084f = function8;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 8) {
                return this.f1084f.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
            throw new IllegalArgumentException("Array of size 8 expected but got " + objArr.length);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$Array9Func */
    static final class Array9Func<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements Function<Object[], R> {

        /* renamed from: f */
        final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> f1085f;

        Array9Func(Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function9) {
            this.f1085f = function9;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 9) {
                return this.f1085f.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
            }
            throw new IllegalArgumentException("Array of size 9 expected but got " + objArr.length);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$Identity */
    static final class Identity implements Function<Object, Object> {
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }

        Identity() {
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$EmptyRunnable */
    static final class EmptyRunnable implements Runnable {
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }

        EmptyRunnable() {
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$EmptyAction */
    static final class EmptyAction implements Action {
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }

        EmptyAction() {
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$EmptyConsumer */
    static final class EmptyConsumer implements Consumer<Object> {
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }

        EmptyConsumer() {
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$ErrorConsumer */
    static final class ErrorConsumer implements Consumer<Throwable> {
        ErrorConsumer() {
        }

        public void accept(Throwable th) {
            RxJavaPlugins.onError(th);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$OnErrorMissingConsumer */
    static final class OnErrorMissingConsumer implements Consumer<Throwable> {
        OnErrorMissingConsumer() {
        }

        public void accept(Throwable th) {
            RxJavaPlugins.onError(new OnErrorNotImplementedException(th));
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$EmptyLongConsumer */
    static final class EmptyLongConsumer implements LongConsumer {
        public void accept(long j) {
        }

        EmptyLongConsumer() {
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$TruePredicate */
    static final class TruePredicate implements Predicate<Object> {
        public boolean test(Object obj) {
            return true;
        }

        TruePredicate() {
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$FalsePredicate */
    static final class FalsePredicate implements Predicate<Object> {
        public boolean test(Object obj) {
            return false;
        }

        FalsePredicate() {
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$NullCallable */
    static final class NullCallable implements Callable<Object> {
        public Object call() {
            return null;
        }

        NullCallable() {
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$NaturalObjectComparator */
    static final class NaturalObjectComparator implements Comparator<Object> {
        NaturalObjectComparator() {
        }

        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* renamed from: io.reactivex.internal.functions.Functions$MaxRequestSubscription */
    static final class MaxRequestSubscription implements Consumer<Subscription> {
        MaxRequestSubscription() {
        }

        public void accept(Subscription subscription) throws Exception {
            subscription.request(Long.MAX_VALUE);
        }
    }

    public static <T> Consumer<T> boundedConsumer(int i) {
        return new BoundedConsumer(i);
    }

    /* renamed from: io.reactivex.internal.functions.Functions$BoundedConsumer */
    public static class BoundedConsumer implements Consumer<Subscription> {
        final int bufferSize;

        BoundedConsumer(int i) {
            this.bufferSize = i;
        }

        public void accept(Subscription subscription) throws Exception {
            subscription.request((long) this.bufferSize);
        }
    }
}
