package androidx.paging.rxjava2;

import androidx.paging.CachedPagingDataKt;
import androidx.paging.Pager;
import androidx.paging.PagingData;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.reactive.ReactiveFlowKt;
import kotlinx.coroutines.rx2.RxConvertKt;
import p014io.reactivex.BackpressureStrategy;
import p014io.reactivex.Flowable;
import p014io.reactivex.Observable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00020\u0001\"\b\b\u0000\u0010\u0011*\u00020\u0005*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u001a6\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00020\f\"\b\b\u0000\u0010\u0011*\u00020\u0005*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\"J\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005\"\b\b\u0001\u0010\u0003*\u00020\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00030\u00068GX\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"J\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\f\"\b\b\u0000\u0010\u0004*\u00020\u0005\"\b\b\u0001\u0010\u0003*\u00020\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00030\u00068GX\u0004¢\u0006\f\u0012\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, mo27512d2 = {"flowable", "Lio/reactivex/Flowable;", "Landroidx/paging/PagingData;", "Value", "Key", "", "Landroidx/paging/Pager;", "getFlowable$annotations", "(Landroidx/paging/Pager;)V", "getFlowable", "(Landroidx/paging/Pager;)Lio/reactivex/Flowable;", "observable", "Lio/reactivex/Observable;", "getObservable$annotations", "getObservable", "(Landroidx/paging/Pager;)Lio/reactivex/Observable;", "cachedIn", "T", "scope", "Lkotlinx/coroutines/CoroutineScope;", "paging-rxjava2_release"}, mo27513k = 5, mo27514mv = {1, 4, 2}, mo27517xs = "androidx/paging/rxjava2/PagingRx")
/* compiled from: PagingRx.kt */
final /* synthetic */ class PagingRx__PagingRxKt {
    public static /* synthetic */ void getFlowable$annotations(Pager pager) {
    }

    public static /* synthetic */ void getObservable$annotations(Pager pager) {
    }

    public static final <Key, Value> Observable<PagingData<Value>> getObservable(Pager<Key, Value> pager) {
        Intrinsics.checkNotNullParameter(pager, "$this$observable");
        return RxConvertKt.asObservable$default(FlowKt.conflate(pager.getFlow()), (CoroutineContext) null, 1, (Object) null);
    }

    public static final <Key, Value> Flowable<PagingData<Value>> getFlowable(Pager<Key, Value> pager) {
        Intrinsics.checkNotNullParameter(pager, "$this$flowable");
        return RxConvertKt.asFlowable$default(FlowKt.conflate(pager.getFlow()), (CoroutineContext) null, 1, (Object) null);
    }

    public static final <T> Observable<PagingData<T>> cachedIn(Observable<PagingData<T>> observable, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(observable, "$this$cachedIn");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        Flowable<PagingData<T>> flowable = observable.toFlowable(BackpressureStrategy.LATEST);
        Intrinsics.checkNotNullExpressionValue(flowable, "toFlowable(BackpressureStrategy.LATEST)");
        return RxConvertKt.asObservable$default(CachedPagingDataKt.cachedIn(ReactiveFlowKt.asFlow(flowable), coroutineScope), (CoroutineContext) null, 1, (Object) null);
    }

    public static final <T> Flowable<PagingData<T>> cachedIn(Flowable<PagingData<T>> flowable, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(flowable, "$this$cachedIn");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        return RxConvertKt.asFlowable$default(CachedPagingDataKt.cachedIn(ReactiveFlowKt.asFlow(flowable), coroutineScope), (CoroutineContext) null, 1, (Object) null);
    }
}
