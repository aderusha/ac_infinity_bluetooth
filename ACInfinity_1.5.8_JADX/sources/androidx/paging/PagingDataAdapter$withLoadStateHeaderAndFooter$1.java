package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, mo27512d2 = {"<anonymous>", "", "T", "", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "loadStates", "Landroidx/paging/CombinedLoadStates;", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: PagingDataAdapter.kt */
final class PagingDataAdapter$withLoadStateHeaderAndFooter$1 extends Lambda implements Function1<CombinedLoadStates, Unit> {
    final /* synthetic */ LoadStateAdapter $footer;
    final /* synthetic */ LoadStateAdapter $header;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingDataAdapter$withLoadStateHeaderAndFooter$1(LoadStateAdapter loadStateAdapter, LoadStateAdapter loadStateAdapter2) {
        super(1);
        this.$header = loadStateAdapter;
        this.$footer = loadStateAdapter2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CombinedLoadStates) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(CombinedLoadStates combinedLoadStates) {
        Intrinsics.checkNotNullParameter(combinedLoadStates, "loadStates");
        this.$header.setLoadState(combinedLoadStates.getPrepend());
        this.$footer.setLoadState(combinedLoadStates.getAppend());
    }
}
