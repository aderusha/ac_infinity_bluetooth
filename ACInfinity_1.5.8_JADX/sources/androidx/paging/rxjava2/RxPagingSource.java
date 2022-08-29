package androidx.paging.rxjava2;

import androidx.paging.PagingSource;
import kotlin.Metadata;
import p014io.reactivex.Single;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B\u0005¢\u0006\u0002\u0010\u0005J+\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH@ø\u0001\u0000¢\u0006\u0002\u0010\nJ(\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00070\f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH&\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, mo27512d2 = {"Landroidx/paging/rxjava2/RxPagingSource;", "Key", "", "Value", "Landroidx/paging/PagingSource;", "()V", "load", "Landroidx/paging/PagingSource$LoadResult;", "params", "Landroidx/paging/PagingSource$LoadParams;", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadSingle", "Lio/reactivex/Single;", "paging-rxjava2_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxPagingSource.kt */
public abstract class RxPagingSource<Key, Value> extends PagingSource<Key, Value> {
    public abstract Single<PagingSource.LoadResult<Key, Value>> loadSingle(PagingSource.LoadParams<Key> loadParams);

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object load(androidx.paging.PagingSource.LoadParams<Key> r5, kotlin.coroutines.Continuation<? super androidx.paging.PagingSource.LoadResult<Key, Value>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.paging.rxjava2.RxPagingSource$load$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            androidx.paging.rxjava2.RxPagingSource$load$1 r0 = (androidx.paging.rxjava2.RxPagingSource$load$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            androidx.paging.rxjava2.RxPagingSource$load$1 r0 = new androidx.paging.rxjava2.RxPagingSource$load$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0044
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            io.reactivex.Single r5 = r4.loadSingle(r5)
            io.reactivex.SingleSource r5 = (p014io.reactivex.SingleSource) r5
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.rx2.RxAwaitKt.await(r5, r0)
            if (r6 != r1) goto L_0x0044
            return r1
        L_0x0044:
            java.lang.String r5 = "loadSingle(params).await()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.rxjava2.RxPagingSource.load(androidx.paging.PagingSource$LoadParams, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
