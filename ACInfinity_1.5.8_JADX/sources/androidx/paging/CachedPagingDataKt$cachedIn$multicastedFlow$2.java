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

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/MulticastedPagingData;", "T", "", "prev", "next", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.CachedPagingDataKt$cachedIn$multicastedFlow$2", mo28222f = "CachedPagingData.kt", mo28223i = {0}, mo28224l = {93}, mo28225m = "invokeSuspend", mo28226n = {"next"}, mo28227s = {"L$0"})
/* compiled from: CachedPagingData.kt */
final class CachedPagingDataKt$cachedIn$multicastedFlow$2 extends SuspendLambda implements Function3<MulticastedPagingData<T>, MulticastedPagingData<T>, Continuation<? super MulticastedPagingData<T>>, Object> {
    private /* synthetic */ Object L$0;
    private /* synthetic */ Object L$1;
    int label;

    CachedPagingDataKt$cachedIn$multicastedFlow$2(Continuation continuation) {
        super(3, continuation);
    }

    public final Continuation<Unit> create(MulticastedPagingData<T> multicastedPagingData, MulticastedPagingData<T> multicastedPagingData2, Continuation<? super MulticastedPagingData<T>> continuation) {
        Intrinsics.checkNotNullParameter(multicastedPagingData, "prev");
        Intrinsics.checkNotNullParameter(multicastedPagingData2, "next");
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        CachedPagingDataKt$cachedIn$multicastedFlow$2 cachedPagingDataKt$cachedIn$multicastedFlow$2 = new CachedPagingDataKt$cachedIn$multicastedFlow$2(continuation);
        cachedPagingDataKt$cachedIn$multicastedFlow$2.L$0 = multicastedPagingData;
        cachedPagingDataKt$cachedIn$multicastedFlow$2.L$1 = multicastedPagingData2;
        return cachedPagingDataKt$cachedIn$multicastedFlow$2;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((CachedPagingDataKt$cachedIn$multicastedFlow$2) create((MulticastedPagingData) obj, (MulticastedPagingData) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MulticastedPagingData multicastedPagingData = (MulticastedPagingData) this.L$1;
            this.L$0 = multicastedPagingData;
            this.label = 1;
            return ((MulticastedPagingData) this.L$0).close(this) == coroutine_suspended ? coroutine_suspended : multicastedPagingData;
        } else if (i == 1) {
            MulticastedPagingData multicastedPagingData2 = (MulticastedPagingData) this.L$0;
            ResultKt.throwOnFailure(obj);
            return multicastedPagingData2;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
