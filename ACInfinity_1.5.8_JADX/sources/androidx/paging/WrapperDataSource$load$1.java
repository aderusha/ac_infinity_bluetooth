package androidx.paging;

import androidx.paging.DataSource;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u00020\u0001\"\b\b\u0002\u0010\u0004*\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\t0\bH@"}, mo27512d2 = {"load", "", "Key", "ValueFrom", "ValueTo", "params", "Landroidx/paging/DataSource$Params;", "continuation", "Lkotlin/coroutines/Continuation;", "Landroidx/paging/DataSource$BaseResult;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.WrapperDataSource", mo28222f = "WrapperDataSource.kt", mo28223i = {0}, mo28224l = {68}, mo28225m = "load$paging_common$suspendImpl", mo28226n = {"this"}, mo28227s = {"L$0"})
/* compiled from: WrapperDataSource.kt */
final class WrapperDataSource$load$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WrapperDataSource this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WrapperDataSource$load$1(WrapperDataSource wrapperDataSource, Continuation continuation) {
        super(continuation);
        this.this$0 = wrapperDataSource;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return WrapperDataSource.load$paging_common$suspendImpl(this.this$0, (DataSource.Params) null, this);
    }
}
