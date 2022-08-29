package androidx.paging;

import androidx.paging.PagingSource;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004\"\b\b\u0002\u0010\u0003*\u00020\u0004*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/PagingSource$LoadResult$Page;", "K", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PagedList$Companion$create$resolvedInitialPage$1", mo28222f = "PagedList.kt", mo28223i = {}, mo28224l = {183}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: PagedList.kt */
final class PagedList$Companion$create$resolvedInitialPage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PagingSource.LoadResult.Page<K, T>>, Object> {
    final /* synthetic */ PagingSource $pagingSource;
    final /* synthetic */ Ref.ObjectRef $params;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagedList$Companion$create$resolvedInitialPage$1(PagingSource pagingSource, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.$pagingSource = pagingSource;
        this.$params = objectRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new PagedList$Companion$create$resolvedInitialPage$1(this.$pagingSource, this.$params, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PagedList$Companion$create$resolvedInitialPage$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.$pagingSource.load((PagingSource.LoadParams.Refresh) this.$params.element, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PagingSource.LoadResult loadResult = (PagingSource.LoadResult) obj;
        if (loadResult instanceof PagingSource.LoadResult.Page) {
            return (PagingSource.LoadResult.Page) loadResult;
        }
        if (loadResult instanceof PagingSource.LoadResult.Error) {
            throw ((PagingSource.LoadResult.Error) loadResult).getThrowable();
        }
        throw new NoWhenBranchMatchedException();
    }
}
