package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/NullPaddedDiffResult;", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1", mo28222f = "AsyncPagingDataDiffer.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: AsyncPagingDataDiffer.kt */
final class AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NullPaddedDiffResult>, Object> {
    final /* synthetic */ NullPaddedList $newList;
    final /* synthetic */ NullPaddedList $previousList;
    int label;
    final /* synthetic */ AsyncPagingDataDiffer$differBase$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1(AsyncPagingDataDiffer$differBase$1 asyncPagingDataDiffer$differBase$1, NullPaddedList nullPaddedList, NullPaddedList nullPaddedList2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = asyncPagingDataDiffer$differBase$1;
        this.$previousList = nullPaddedList;
        this.$newList = nullPaddedList2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1(this.this$0, this.$previousList, this.$newList, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return NullPaddedListDiffHelperKt.computeDiff(this.$previousList, this.$newList, this.this$0.this$0.diffCallback);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
