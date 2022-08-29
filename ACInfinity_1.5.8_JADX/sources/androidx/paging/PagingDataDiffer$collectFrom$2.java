package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PagingDataDiffer$collectFrom$2", mo28222f = "PagingDataDiffer.kt", mo28223i = {}, mo28224l = {390}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: PagingDataDiffer.kt */
final class PagingDataDiffer$collectFrom$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ PagingData $pagingData;
    int label;
    final /* synthetic */ PagingDataDiffer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingDataDiffer$collectFrom$2(PagingDataDiffer pagingDataDiffer, PagingData pagingData, Continuation continuation) {
        super(1, continuation);
        this.this$0 = pagingDataDiffer;
        this.$pagingData = pagingData;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new PagingDataDiffer$collectFrom$2(this.this$0, this.$pagingData, continuation);
    }

    public final Object invoke(Object obj) {
        return ((PagingDataDiffer$collectFrom$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.receiver = this.$pagingData.getReceiver$paging_common();
            this.label = 1;
            if (this.$pagingData.getFlow$paging_common().collect(new PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1(this), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
