package androidx.paging;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PagedList$dispatchStateChangeAsync$1", mo28222f = "PagedList.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: PagedList.kt */
final class PagedList$dispatchStateChangeAsync$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LoadState $state;
    final /* synthetic */ LoadType $type;
    int label;
    final /* synthetic */ PagedList this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagedList$dispatchStateChangeAsync$1(PagedList pagedList, LoadType loadType, LoadState loadState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = pagedList;
        this.$type = loadType;
        this.$state = loadState;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new PagedList$dispatchStateChangeAsync$1(this.this$0, this.$type, this.$state, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PagedList$dispatchStateChangeAsync$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CollectionsKt.removeAll(this.this$0.loadStateListeners, C05111.INSTANCE);
            for (WeakReference weakReference : this.this$0.loadStateListeners) {
                Function2 function2 = (Function2) weakReference.get();
                if (function2 != null) {
                    Unit unit = (Unit) function2.invoke(this.$type, this.$state);
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
