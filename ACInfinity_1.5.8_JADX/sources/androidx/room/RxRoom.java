package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p014io.reactivex.BackpressureStrategy;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableEmitter;
import p014io.reactivex.FlowableOnSubscribe;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.Scheduler;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.disposables.Disposables;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class RxRoom {
    public static final Object NOTHING = new Object();

    public static Flowable<Object> createFlowable(final RoomDatabase roomDatabase, final String... strArr) {
        return Flowable.create(new FlowableOnSubscribe<Object>() {
            public void subscribe(final FlowableEmitter<Object> flowableEmitter) throws Exception {
                final C06891 r0 = new InvalidationTracker.Observer(strArr) {
                    public void onInvalidated(Set<String> set) {
                        if (!flowableEmitter.isCancelled()) {
                            flowableEmitter.onNext(RxRoom.NOTHING);
                        }
                    }
                };
                if (!flowableEmitter.isCancelled()) {
                    roomDatabase.getInvalidationTracker().addObserver(r0);
                    flowableEmitter.setDisposable(Disposables.fromAction(new Action() {
                        public void run() throws Exception {
                            roomDatabase.getInvalidationTracker().removeObserver(r0);
                        }
                    }));
                }
                if (!flowableEmitter.isCancelled()) {
                    flowableEmitter.onNext(RxRoom.NOTHING);
                }
            }
        }, BackpressureStrategy.LATEST);
    }

    @Deprecated
    public static <T> Flowable<T> createFlowable(RoomDatabase roomDatabase, String[] strArr, Callable<T> callable) {
        return createFlowable(roomDatabase, false, strArr, callable);
    }

    public static <T> Flowable<T> createFlowable(RoomDatabase roomDatabase, boolean z, String[] strArr, Callable<T> callable) {
        Scheduler from = Schedulers.from(getExecutor(roomDatabase, z));
        final Maybe<T> fromCallable = Maybe.fromCallable(callable);
        return createFlowable(roomDatabase, strArr).subscribeOn(from).unsubscribeOn(from).observeOn(from).flatMapMaybe(new Function<Object, MaybeSource<T>>() {
            public MaybeSource<T> apply(Object obj) throws Exception {
                return Maybe.this;
            }
        });
    }

    public static Observable<Object> createObservable(final RoomDatabase roomDatabase, final String... strArr) {
        return Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter<Object> observableEmitter) throws Exception {
                final C06931 r0 = new InvalidationTracker.Observer(strArr) {
                    public void onInvalidated(Set<String> set) {
                        observableEmitter.onNext(RxRoom.NOTHING);
                    }
                };
                roomDatabase.getInvalidationTracker().addObserver(r0);
                observableEmitter.setDisposable(Disposables.fromAction(new Action() {
                    public void run() throws Exception {
                        roomDatabase.getInvalidationTracker().removeObserver(r0);
                    }
                }));
                observableEmitter.onNext(RxRoom.NOTHING);
            }
        });
    }

    @Deprecated
    public static <T> Observable<T> createObservable(RoomDatabase roomDatabase, String[] strArr, Callable<T> callable) {
        return createObservable(roomDatabase, false, strArr, callable);
    }

    public static <T> Observable<T> createObservable(RoomDatabase roomDatabase, boolean z, String[] strArr, Callable<T> callable) {
        Scheduler from = Schedulers.from(getExecutor(roomDatabase, z));
        final Maybe<T> fromCallable = Maybe.fromCallable(callable);
        return createObservable(roomDatabase, strArr).subscribeOn(from).unsubscribeOn(from).observeOn(from).flatMapMaybe(new Function<Object, MaybeSource<T>>() {
            public MaybeSource<T> apply(Object obj) throws Exception {
                return Maybe.this;
            }
        });
    }

    public static <T> Single<T> createSingle(final Callable<T> callable) {
        return Single.create(new SingleOnSubscribe<T>() {
            public void subscribe(SingleEmitter<T> singleEmitter) throws Exception {
                try {
                    singleEmitter.onSuccess(callable.call());
                } catch (EmptyResultSetException e) {
                    singleEmitter.tryOnError(e);
                }
            }
        });
    }

    private static Executor getExecutor(RoomDatabase roomDatabase, boolean z) {
        if (z) {
            return roomDatabase.getTransactionExecutor();
        }
        return roomDatabase.getQueryExecutor();
    }
}
