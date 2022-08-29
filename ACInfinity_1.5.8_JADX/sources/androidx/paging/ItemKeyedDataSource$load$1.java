package androidx.paging;

import androidx.paging.DataSource;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\b0\u0007H@"}, mo27512d2 = {"load", "", "Key", "Value", "params", "Landroidx/paging/DataSource$Params;", "continuation", "Lkotlin/coroutines/Continuation;", "Landroidx/paging/DataSource$BaseResult;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.ItemKeyedDataSource", mo28222f = "ItemKeyedDataSource.kt", mo28223i = {}, mo28224l = {171, 177, 178}, mo28225m = "load$paging_common", mo28226n = {}, mo28227s = {})
/* compiled from: ItemKeyedDataSource.kt */
final class ItemKeyedDataSource$load$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ItemKeyedDataSource this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ItemKeyedDataSource$load$1(ItemKeyedDataSource itemKeyedDataSource, Continuation continuation) {
        super(continuation);
        this.this$0 = itemKeyedDataSource;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.load$paging_common((DataSource.Params) null, this);
    }
}
