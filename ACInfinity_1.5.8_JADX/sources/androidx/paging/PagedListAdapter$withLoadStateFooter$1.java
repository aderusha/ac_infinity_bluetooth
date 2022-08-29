package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0002\b\n"}, mo27512d2 = {"<anonymous>", "", "T", "", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "loadType", "Landroidx/paging/LoadType;", "loadState", "Landroidx/paging/LoadState;", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: PagedListAdapter.kt */
final class PagedListAdapter$withLoadStateFooter$1 extends Lambda implements Function2<LoadType, LoadState, Unit> {
    final /* synthetic */ LoadStateAdapter $footer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagedListAdapter$withLoadStateFooter$1(LoadStateAdapter loadStateAdapter) {
        super(2);
        this.$footer = loadStateAdapter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LoadType) obj, (LoadState) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        if (loadType == LoadType.APPEND) {
            this.$footer.setLoadState(loadState);
        }
    }
}
