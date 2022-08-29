package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000eH@"}, mo27512d2 = {"presentNewList", "", "T", "previousList", "Landroidx/paging/NullPaddedList;", "newList", "newCombinedLoadStates", "Landroidx/paging/CombinedLoadStates;", "lastAccessedIndex", "", "onListPresentable", "Lkotlin/Function0;", "", "continuation", "Lkotlin/coroutines/Continuation;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.AsyncPagingDataDiffer$differBase$1", mo28222f = "AsyncPagingDataDiffer.kt", mo28223i = {0, 0, 0, 0, 0}, mo28224l = {99}, mo28225m = "presentNewList", mo28226n = {"this", "previousList", "newList", "onListPresentable", "lastAccessedIndex"}, mo28227s = {"L$0", "L$1", "L$2", "L$3", "I$0"})
/* compiled from: AsyncPagingDataDiffer.kt */
final class AsyncPagingDataDiffer$differBase$1$presentNewList$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AsyncPagingDataDiffer$differBase$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AsyncPagingDataDiffer$differBase$1$presentNewList$1(AsyncPagingDataDiffer$differBase$1 asyncPagingDataDiffer$differBase$1, Continuation continuation) {
        super(continuation);
        this.this$0 = asyncPagingDataDiffer$differBase$1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.presentNewList((NullPaddedList) null, (NullPaddedList) null, (CombinedLoadStates) null, 0, (Function0<Unit>) null, this);
    }
}
