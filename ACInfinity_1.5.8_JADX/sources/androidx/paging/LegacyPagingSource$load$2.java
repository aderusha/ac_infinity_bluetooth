package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.PagingSource;
import kotlin.Metadata;
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

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/PagingSource$LoadResult$Page;", "Key", "Value", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.LegacyPagingSource$load$2", mo28222f = "LegacyPagingSource.kt", mo28223i = {}, mo28224l = {116}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: LegacyPagingSource.kt */
final class LegacyPagingSource$load$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PagingSource.LoadResult.Page<Key, Value>>, Object> {
    final /* synthetic */ Ref.ObjectRef $dataSourceParams;
    final /* synthetic */ PagingSource.LoadParams $params;
    int label;
    final /* synthetic */ LegacyPagingSource this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LegacyPagingSource$load$2(LegacyPagingSource legacyPagingSource, Ref.ObjectRef objectRef, PagingSource.LoadParams loadParams, Continuation continuation) {
        super(2, continuation);
        this.this$0 = legacyPagingSource;
        this.$dataSourceParams = objectRef;
        this.$params = loadParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new LegacyPagingSource$load$2(this.this$0, this.$dataSourceParams, this.$params, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((LegacyPagingSource$load$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.getDataSource$paging_common().load$paging_common((DataSource.Params) this.$dataSourceParams.element, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        DataSource.BaseResult baseResult = (DataSource.BaseResult) obj;
        return new PagingSource.LoadResult.Page(baseResult.data, (!baseResult.data.isEmpty() || !(this.$params instanceof PagingSource.LoadParams.Prepend)) ? baseResult.getPrevKey() : null, (!baseResult.data.isEmpty() || !(this.$params instanceof PagingSource.LoadParams.Append)) ? baseResult.getNextKey() : null, baseResult.getItemsBefore(), baseResult.getItemsAfter());
    }
}
