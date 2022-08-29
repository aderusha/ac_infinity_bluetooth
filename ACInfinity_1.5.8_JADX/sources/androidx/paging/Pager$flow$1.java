package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/PagingSource;", "Key", "Value", "", "invoke", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: Pager.kt */
final /* synthetic */ class Pager$flow$1 extends FunctionReferenceImpl implements Function1<Continuation<? super PagingSource<Key, Value>>, Object>, SuspendFunction {
    Pager$flow$1(Function0 function0) {
        super(1, function0, SuspendingPagingSourceFactory.class, "create", "create(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    public final Object invoke(Continuation<? super PagingSource<Key, Value>> continuation) {
        return ((SuspendingPagingSourceFactory) ((Function0) this.receiver)).create(continuation);
    }
}
