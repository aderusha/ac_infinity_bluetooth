package androidx.paging.rxjava2;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.rx2.RxAwaitKt;
import p014io.reactivex.SingleSource;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "R", "T", "", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.rxjava2.PagingRx__RxPagingDataKt$mapAsync$1", mo28222f = "RxPagingData.kt", mo28223i = {}, mo28224l = {40}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: RxPagingData.kt */
final class PagingRx__RxPagingDataKt$mapAsync$1 extends SuspendLambda implements Function2<T, Continuation<? super R>, Object> {
    final /* synthetic */ Function1 $transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingRx__RxPagingDataKt$mapAsync$1(Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$transform = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        PagingRx__RxPagingDataKt$mapAsync$1 pagingRx__RxPagingDataKt$mapAsync$1 = new PagingRx__RxPagingDataKt$mapAsync$1(this.$transform, continuation);
        pagingRx__RxPagingDataKt$mapAsync$1.L$0 = obj;
        return pagingRx__RxPagingDataKt$mapAsync$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PagingRx__RxPagingDataKt$mapAsync$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            this.label = 1;
            obj = RxAwaitKt.await((SingleSource) this.$transform.invoke(obj2), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Intrinsics.checkNotNullExpressionValue(obj, "transform(it).await()");
        return obj;
    }
}
