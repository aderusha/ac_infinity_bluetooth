package androidx.paging.rxjava2;

import androidx.paging.PagingData;
import androidx.paging.PagingDataTransforms;
import androidx.paging.TerminalSeparatorType;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import p014io.reactivex.Maybe;
import p014io.reactivex.Single;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aA\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\u0007¢\u0006\u0002\b\b\u001aQ\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\n*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u001e\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u0002H\u0002\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\f0\u00060\u0005H\u0007¢\u0006\u0002\b\r\u001aU\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\b\b\u0000\u0010\u0002*\u0002H\n\"\b\b\u0001\u0010\n*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\"\u0010\u000f\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00110\u0010H\u0007¢\u0006\u0002\b\u0012\u001aK\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\n*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00060\u0005H\u0007¢\u0006\u0002\b\u0014¨\u0006\u0015"}, mo27512d2 = {"filterAsync", "Landroidx/paging/PagingData;", "T", "", "predicate", "Lkotlin/Function1;", "Lio/reactivex/Single;", "", "filter", "flatMapAsync", "R", "transform", "", "flatMap", "insertSeparatorsAsync", "generator", "Lkotlin/Function2;", "Lio/reactivex/Maybe;", "insertSeparators", "mapAsync", "map", "paging-rxjava2_release"}, mo27513k = 5, mo27514mv = {1, 4, 2}, mo27517xs = "androidx/paging/rxjava2/PagingRx")
/* compiled from: RxPagingData.kt */
final /* synthetic */ class PagingRx__RxPagingDataKt {
    public static final <T, R> PagingData<R> map(PagingData<T> pagingData, Function1<? super T, ? extends Single<R>> function1) {
        Intrinsics.checkNotNullParameter(pagingData, "$this$mapAsync");
        Intrinsics.checkNotNullParameter(function1, "transform");
        return PagingDataTransforms.map(pagingData, new PagingRx__RxPagingDataKt$mapAsync$1(function1, (Continuation) null));
    }

    public static final <T, R> PagingData<R> flatMap(PagingData<T> pagingData, Function1<? super T, ? extends Single<Iterable<R>>> function1) {
        Intrinsics.checkNotNullParameter(pagingData, "$this$flatMapAsync");
        Intrinsics.checkNotNullParameter(function1, "transform");
        return PagingDataTransforms.flatMap(pagingData, new PagingRx__RxPagingDataKt$flatMapAsync$1(function1, (Continuation) null));
    }

    public static final <T> PagingData<T> filter(PagingData<T> pagingData, Function1<? super T, ? extends Single<Boolean>> function1) {
        Intrinsics.checkNotNullParameter(pagingData, "$this$filterAsync");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        return PagingDataTransforms.filter(pagingData, new PagingRx__RxPagingDataKt$filterAsync$1(function1, (Continuation) null));
    }

    public static final <T extends R, R> PagingData<R> insertSeparators(PagingData<T> pagingData, Function2<? super T, ? super T, ? extends Maybe<R>> function2) {
        Intrinsics.checkNotNullParameter(pagingData, "$this$insertSeparatorsAsync");
        Intrinsics.checkNotNullParameter(function2, "generator");
        return PagingDataTransforms.insertSeparators$default(pagingData, (TerminalSeparatorType) null, new PagingRx__RxPagingDataKt$insertSeparatorsAsync$1(function2, (Continuation) null), 1, (Object) null);
    }
}
