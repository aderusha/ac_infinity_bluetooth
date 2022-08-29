package androidx.paging.rxjava2;

import androidx.paging.ExperimentalPagingApi;
import androidx.paging.LoadType;
import androidx.paging.PagingState;
import androidx.paging.RemoteMediator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import p014io.reactivex.Single;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0011\u0010\u0006\u001a\u00020\u0007H@ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\u0016J-\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J*\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0010H&\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, mo27512d2 = {"Landroidx/paging/rxjava2/RxRemoteMediator;", "Key", "", "Value", "Landroidx/paging/RemoteMediator;", "()V", "initialize", "Landroidx/paging/RemoteMediator$InitializeAction;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initializeSingle", "Lio/reactivex/Single;", "load", "Landroidx/paging/RemoteMediator$MediatorResult;", "loadType", "Landroidx/paging/LoadType;", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/LoadType;Landroidx/paging/PagingState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadSingle", "paging-rxjava2_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
@ExperimentalPagingApi
/* compiled from: RxRemoteMediator.kt */
public abstract class RxRemoteMediator<Key, Value> extends RemoteMediator<Key, Value> {
    public abstract Single<RemoteMediator.MediatorResult> loadSingle(LoadType loadType, PagingState<Key, Value> pagingState);

    public Single<RemoteMediator.InitializeAction> initializeSingle() {
        Single<RemoteMediator.InitializeAction> just = Single.just(RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH);
        Intrinsics.checkNotNullExpressionValue(just, "Single.just(LAUNCH_INITIAL_REFRESH)");
        return just;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object load(androidx.paging.LoadType r5, androidx.paging.PagingState<Key, Value> r6, kotlin.coroutines.Continuation<? super androidx.paging.RemoteMediator.MediatorResult> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.paging.rxjava2.RxRemoteMediator$load$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            androidx.paging.rxjava2.RxRemoteMediator$load$1 r0 = (androidx.paging.rxjava2.RxRemoteMediator$load$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            androidx.paging.rxjava2.RxRemoteMediator$load$1 r0 = new androidx.paging.rxjava2.RxRemoteMediator$load$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0044
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r7)
            io.reactivex.Single r5 = r4.loadSingle(r5, r6)
            io.reactivex.SingleSource r5 = (p014io.reactivex.SingleSource) r5
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.rx2.RxAwaitKt.await(r5, r0)
            if (r7 != r1) goto L_0x0044
            return r1
        L_0x0044:
            java.lang.String r5 = "loadSingle(loadType, state).await()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.rxjava2.RxRemoteMediator.load(androidx.paging.LoadType, androidx.paging.PagingState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object initialize(kotlin.coroutines.Continuation<? super androidx.paging.RemoteMediator.InitializeAction> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof androidx.paging.rxjava2.RxRemoteMediator$initialize$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            androidx.paging.rxjava2.RxRemoteMediator$initialize$1 r0 = (androidx.paging.rxjava2.RxRemoteMediator$initialize$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            androidx.paging.rxjava2.RxRemoteMediator$initialize$1 r0 = new androidx.paging.rxjava2.RxRemoteMediator$initialize$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0044
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r5)
            io.reactivex.Single r5 = r4.initializeSingle()
            io.reactivex.SingleSource r5 = (p014io.reactivex.SingleSource) r5
            r0.label = r3
            java.lang.Object r5 = kotlinx.coroutines.rx2.RxAwaitKt.await(r5, r0)
            if (r5 != r1) goto L_0x0044
            return r1
        L_0x0044:
            java.lang.String r0 = "initializeSingle().await()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.rxjava2.RxRemoteMediator.initialize(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
