package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00050\u0004B'\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0018\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0004¢\u0006\u0002\u0010\tJ\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0015\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005H\u0002R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, mo27512d2 = {"Landroidx/paging/SuspendingPagingSourceFactory;", "Key", "", "Value", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "delegate", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function0;)V", "create", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invoke", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: SuspendingPagingSourceFactory.kt */
public final class SuspendingPagingSourceFactory<Key, Value> implements Function0<PagingSource<Key, Value>> {
    /* access modifiers changed from: private */
    public final Function0<PagingSource<Key, Value>> delegate;
    private final CoroutineDispatcher dispatcher;

    public SuspendingPagingSourceFactory(CoroutineDispatcher coroutineDispatcher, Function0<? extends PagingSource<Key, Value>> function0) {
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(function0, "delegate");
        this.dispatcher = coroutineDispatcher;
        this.delegate = function0;
    }

    public final Object create(Continuation<? super PagingSource<Key, Value>> continuation) {
        return BuildersKt.withContext(this.dispatcher, new SuspendingPagingSourceFactory$create$2(this, (Continuation) null), continuation);
    }

    public PagingSource<Key, Value> invoke() {
        return this.delegate.invoke();
    }
}
