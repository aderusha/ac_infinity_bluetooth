package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/PagingSource;", "Key", "Value", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.Pager$flow$2", mo28222f = "Pager.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: Pager.kt */
final class Pager$flow$2 extends SuspendLambda implements Function1<Continuation<? super PagingSource<Key, Value>>, Object> {
    final /* synthetic */ Function0 $pagingSourceFactory;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Pager$flow$2(Function0 function0, Continuation continuation) {
        super(1, continuation);
        this.$pagingSourceFactory = function0;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new Pager$flow$2(this.$pagingSourceFactory, continuation);
    }

    public final Object invoke(Object obj) {
        return ((Pager$flow$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.$pagingSourceFactory.invoke();
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
