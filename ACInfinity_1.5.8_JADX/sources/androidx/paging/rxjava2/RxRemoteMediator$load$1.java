package androidx.paging.rxjava2;

import androidx.paging.LoadType;
import androidx.paging.PagingState;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH@"}, mo27512d2 = {"load", "", "Key", "Value", "loadType", "Landroidx/paging/LoadType;", "state", "Landroidx/paging/PagingState;", "continuation", "Lkotlin/coroutines/Continuation;", "Landroidx/paging/RemoteMediator$MediatorResult;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.rxjava2.RxRemoteMediator", mo28222f = "RxRemoteMediator.kt", mo28223i = {}, mo28224l = {93}, mo28225m = "load", mo28226n = {}, mo28227s = {})
/* compiled from: RxRemoteMediator.kt */
final class RxRemoteMediator$load$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RxRemoteMediator this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxRemoteMediator$load$1(RxRemoteMediator rxRemoteMediator, Continuation continuation) {
        super(continuation);
        this.this$0 = rxRemoteMediator;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.load((LoadType) null, (PagingState) null, this);
    }
}
