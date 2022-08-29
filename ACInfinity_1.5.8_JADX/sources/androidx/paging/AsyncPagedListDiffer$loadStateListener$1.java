package androidx.paging;

import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0015\u0010\u0004\u001a\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b2\u0015\u0010\t\u001a\u00110\n¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b¢\u0006\u0002\b\f"}, mo27512d2 = {"<anonymous>", "", "T", "", "p1", "Landroidx/paging/LoadType;", "Lkotlin/ParameterName;", "name", "type", "p2", "Landroidx/paging/LoadState;", "state", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: AsyncPagedListDiffer.kt */
final /* synthetic */ class AsyncPagedListDiffer$loadStateListener$1 extends FunctionReferenceImpl implements Function2<LoadType, LoadState, Unit> {
    AsyncPagedListDiffer$loadStateListener$1(PagedList.LoadStateManager loadStateManager) {
        super(2, loadStateManager, PagedList.LoadStateManager.class, "onStateChanged", "onStateChanged(Landroidx/paging/LoadType;Landroidx/paging/LoadState;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LoadType) obj, (LoadState) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "p1");
        Intrinsics.checkNotNullParameter(loadState, "p2");
        ((PagedList.LoadStateManager) this.receiver).onStateChanged(loadType, loadState);
    }
}
