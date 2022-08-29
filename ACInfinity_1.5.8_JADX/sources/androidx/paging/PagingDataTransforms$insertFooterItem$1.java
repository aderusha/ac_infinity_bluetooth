package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u0001H\u00012\b\u0010\u0004\u001a\u0004\u0018\u0001H\u0001H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "T", "", "<anonymous parameter 0>", "after", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PagingDataTransforms$insertFooterItem$1", mo28222f = "PagingDataTransforms.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: PagingDataTransforms.kt */
final class PagingDataTransforms$insertFooterItem$1 extends SuspendLambda implements Function3<T, T, Continuation<? super T>, Object> {
    final /* synthetic */ Object $item;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingDataTransforms$insertFooterItem$1(Object obj, Continuation continuation) {
        super(3, continuation);
        this.$item = obj;
    }

    public final Continuation<Unit> create(T t, T t2, Continuation<? super T> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        PagingDataTransforms$insertFooterItem$1 pagingDataTransforms$insertFooterItem$1 = new PagingDataTransforms$insertFooterItem$1(this.$item, continuation);
        pagingDataTransforms$insertFooterItem$1.L$0 = t2;
        return pagingDataTransforms$insertFooterItem$1;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((PagingDataTransforms$insertFooterItem$1) create(obj, obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.L$0 == null) {
                return this.$item;
            }
            return null;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
