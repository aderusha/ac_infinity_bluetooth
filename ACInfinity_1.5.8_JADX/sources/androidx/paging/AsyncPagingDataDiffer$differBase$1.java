package androidx.paging;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016JM\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, mo27512d2 = {"androidx/paging/AsyncPagingDataDiffer$differBase$1", "Landroidx/paging/PagingDataDiffer;", "postEvents", "", "presentNewList", "", "previousList", "Landroidx/paging/NullPaddedList;", "newList", "newCombinedLoadStates", "Landroidx/paging/CombinedLoadStates;", "lastAccessedIndex", "onListPresentable", "Lkotlin/Function0;", "", "(Landroidx/paging/NullPaddedList;Landroidx/paging/NullPaddedList;Landroidx/paging/CombinedLoadStates;ILkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: AsyncPagingDataDiffer.kt */
public final class AsyncPagingDataDiffer$differBase$1 extends PagingDataDiffer<T> {
    final /* synthetic */ AsyncPagingDataDiffer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AsyncPagingDataDiffer$differBase$1(AsyncPagingDataDiffer asyncPagingDataDiffer, DifferCallback differCallback, CoroutineDispatcher coroutineDispatcher) {
        super(differCallback, coroutineDispatcher);
        this.this$0 = asyncPagingDataDiffer;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: kotlin.jvm.functions.Function0<kotlin.Unit>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: androidx.paging.NullPaddedList<T>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object presentNewList(androidx.paging.NullPaddedList<T> r5, androidx.paging.NullPaddedList<T> r6, androidx.paging.CombinedLoadStates r7, int r8, kotlin.jvm.functions.Function0<kotlin.Unit> r9, kotlin.coroutines.Continuation<? super java.lang.Integer> r10) {
        /*
            r4 = this;
            boolean r7 = r10 instanceof androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1
            if (r7 == 0) goto L_0x0014
            r7 = r10
            androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1 r7 = (androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1) r7
            int r0 = r7.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x0014
            int r10 = r7.label
            int r10 = r10 - r1
            r7.label = r10
            goto L_0x0019
        L_0x0014:
            androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1 r7 = new androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$1
            r7.<init>(r4, r10)
        L_0x0019:
            java.lang.Object r10 = r7.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0047
            if (r1 != r2) goto L_0x003f
            int r8 = r7.I$0
            java.lang.Object r5 = r7.L$3
            r9 = r5
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            java.lang.Object r5 = r7.L$2
            r6 = r5
            androidx.paging.NullPaddedList r6 = (androidx.paging.NullPaddedList) r6
            java.lang.Object r5 = r7.L$1
            androidx.paging.NullPaddedList r5 = (androidx.paging.NullPaddedList) r5
            java.lang.Object r7 = r7.L$0
            androidx.paging.AsyncPagingDataDiffer$differBase$1 r7 = (androidx.paging.AsyncPagingDataDiffer$differBase$1) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x009c
        L_0x003f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r10)
            int r10 = r5.getSize()
            r1 = 0
            if (r10 != 0) goto L_0x0062
            r9.invoke()
            androidx.paging.AsyncPagingDataDiffer r5 = r4.this$0
            androidx.paging.DifferCallback r5 = r5.getDifferCallback$paging_runtime_release()
            int r6 = r6.getSize()
            r5.onInserted(r1, r6)
            goto L_0x00b2
        L_0x0062:
            int r10 = r6.getSize()
            if (r10 != 0) goto L_0x0079
            r9.invoke()
            androidx.paging.AsyncPagingDataDiffer r6 = r4.this$0
            androidx.paging.DifferCallback r6 = r6.getDifferCallback$paging_runtime_release()
            int r5 = r5.getSize()
            r6.onRemoved(r1, r5)
            goto L_0x00b2
        L_0x0079:
            androidx.paging.AsyncPagingDataDiffer r10 = r4.this$0
            kotlinx.coroutines.CoroutineDispatcher r10 = r10.workerDispatcher
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1 r1 = new androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1
            r1.<init>(r4, r5, r6, r3)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r7.L$0 = r4
            r7.L$1 = r5
            r7.L$2 = r6
            r7.L$3 = r9
            r7.I$0 = r8
            r7.label = r2
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r10, r1, r7)
            if (r10 != r0) goto L_0x009b
            return r0
        L_0x009b:
            r7 = r4
        L_0x009c:
            androidx.paging.NullPaddedDiffResult r10 = (androidx.paging.NullPaddedDiffResult) r10
            r9.invoke()
            androidx.paging.AsyncPagingDataDiffer r7 = r7.this$0
            androidx.recyclerview.widget.ListUpdateCallback r7 = r7.updateCallback
            androidx.paging.NullPaddedListDiffHelperKt.dispatchDiff(r5, r7, r6, r10)
            int r5 = androidx.paging.NullPaddedListDiffHelperKt.transformAnchorIndex(r5, r10, r6, r8)
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
        L_0x00b2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.AsyncPagingDataDiffer$differBase$1.presentNewList(androidx.paging.NullPaddedList, androidx.paging.NullPaddedList, androidx.paging.CombinedLoadStates, int, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public boolean postEvents() {
        return this.this$0.getInGetItem$paging_runtime_release();
    }
}
