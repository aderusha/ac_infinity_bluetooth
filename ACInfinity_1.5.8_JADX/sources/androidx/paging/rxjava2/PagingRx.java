package androidx.paging.rxjava2;

import androidx.paging.Pager;
import androidx.paging.PagingData;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import p014io.reactivex.Flowable;
import p014io.reactivex.Maybe;
import p014io.reactivex.Observable;
import p014io.reactivex.Single;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"androidx/paging/rxjava2/PagingRx__PagingRxKt", "androidx/paging/rxjava2/PagingRx__RxPagingDataKt"}, mo27513k = 4, mo27514mv = {1, 4, 2})
public final class PagingRx {
    public static final <T> Flowable<PagingData<T>> cachedIn(Flowable<PagingData<T>> flowable, CoroutineScope coroutineScope) {
        return PagingRx__PagingRxKt.cachedIn(flowable, coroutineScope);
    }

    public static final <T> Observable<PagingData<T>> cachedIn(Observable<PagingData<T>> observable, CoroutineScope coroutineScope) {
        return PagingRx__PagingRxKt.cachedIn(observable, coroutineScope);
    }

    public static final <T> PagingData<T> filter(PagingData<T> pagingData, Function1<? super T, ? extends Single<Boolean>> function1) {
        return PagingRx__RxPagingDataKt.filter(pagingData, function1);
    }

    public static final <T, R> PagingData<R> flatMap(PagingData<T> pagingData, Function1<? super T, ? extends Single<Iterable<R>>> function1) {
        return PagingRx__RxPagingDataKt.flatMap(pagingData, function1);
    }

    public static final <Key, Value> Flowable<PagingData<Value>> getFlowable(Pager<Key, Value> pager) {
        return PagingRx__PagingRxKt.getFlowable(pager);
    }

    public static final <Key, Value> Observable<PagingData<Value>> getObservable(Pager<Key, Value> pager) {
        return PagingRx__PagingRxKt.getObservable(pager);
    }

    public static final <T extends R, R> PagingData<R> insertSeparators(PagingData<T> pagingData, Function2<? super T, ? super T, ? extends Maybe<R>> function2) {
        return PagingRx__RxPagingDataKt.insertSeparators(pagingData, function2);
    }

    public static final <T, R> PagingData<R> map(PagingData<T> pagingData, Function1<? super T, ? extends Single<R>> function1) {
        return PagingRx__RxPagingDataKt.map(pagingData, function1);
    }
}
