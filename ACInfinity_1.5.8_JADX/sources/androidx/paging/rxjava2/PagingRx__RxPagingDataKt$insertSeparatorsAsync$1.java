package androidx.paging.rxjava2;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.rx2.RxAwaitKt;
import p014io.reactivex.MaybeSource;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0002*\u0002H\u0001\"\b\b\u0001\u0010\u0001*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0002H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "R", "T", "", "before", "after", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.rxjava2.PagingRx__RxPagingDataKt$insertSeparatorsAsync$1", mo28222f = "RxPagingData.kt", mo28223i = {}, mo28224l = {75}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: RxPagingData.kt */
final class PagingRx__RxPagingDataKt$insertSeparatorsAsync$1 extends SuspendLambda implements Function3<T, T, Continuation<? super R>, Object> {
    final /* synthetic */ Function2 $generator;
    private /* synthetic */ Object L$0;
    private /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingRx__RxPagingDataKt$insertSeparatorsAsync$1(Function2 function2, Continuation continuation) {
        super(3, continuation);
        this.$generator = function2;
    }

    public final Continuation<Unit> create(T t, T t2, Continuation<? super R> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        PagingRx__RxPagingDataKt$insertSeparatorsAsync$1 pagingRx__RxPagingDataKt$insertSeparatorsAsync$1 = new PagingRx__RxPagingDataKt$insertSeparatorsAsync$1(this.$generator, continuation);
        pagingRx__RxPagingDataKt$insertSeparatorsAsync$1.L$0 = t;
        pagingRx__RxPagingDataKt$insertSeparatorsAsync$1.L$1 = t2;
        return pagingRx__RxPagingDataKt$insertSeparatorsAsync$1;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((PagingRx__RxPagingDataKt$insertSeparatorsAsync$1) create(obj, obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            Object obj3 = this.L$1;
            this.L$0 = null;
            this.label = 1;
            obj = RxAwaitKt.await((MaybeSource) this.$generator.invoke(obj2, obj3), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
