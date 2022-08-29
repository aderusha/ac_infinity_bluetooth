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

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.AsyncPagingDataDiffer$submitData$2", mo28222f = "AsyncPagingDataDiffer.kt", mo28223i = {}, mo28224l = {169}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: AsyncPagingDataDiffer.kt */
final class AsyncPagingDataDiffer$submitData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $id;
    final /* synthetic */ PagingData $pagingData;
    int label;
    final /* synthetic */ AsyncPagingDataDiffer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AsyncPagingDataDiffer$submitData$2(AsyncPagingDataDiffer asyncPagingDataDiffer, int i, PagingData pagingData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = asyncPagingDataDiffer;
        this.$id = i;
        this.$pagingData = pagingData;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new AsyncPagingDataDiffer$submitData$2(this.this$0, this.$id, this.$pagingData, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AsyncPagingDataDiffer$submitData$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.submitDataId.get() == this.$id) {
                AsyncPagingDataDiffer$differBase$1 access$getDifferBase$p = this.this$0.differBase;
                PagingData pagingData = this.$pagingData;
                this.label = 1;
                if (access$getDifferBase$p.collectFrom(pagingData, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
