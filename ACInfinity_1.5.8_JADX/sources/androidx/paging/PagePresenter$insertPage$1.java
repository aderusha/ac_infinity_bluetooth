package androidx.paging;

import androidx.paging.PagePresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0002\b\n"}, mo27512d2 = {"<anonymous>", "", "T", "", "type", "Landroidx/paging/LoadType;", "fromMediator", "", "state", "Landroidx/paging/LoadState;", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: PagePresenter.kt */
final class PagePresenter$insertPage$1 extends Lambda implements Function3<LoadType, Boolean, LoadState, Unit> {
    final /* synthetic */ PagePresenter.ProcessPageEventCallback $callback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagePresenter$insertPage$1(PagePresenter.ProcessPageEventCallback processPageEventCallback) {
        super(3);
        this.$callback = processPageEventCallback;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((LoadType) obj, ((Boolean) obj2).booleanValue(), (LoadState) obj3);
        return Unit.INSTANCE;
    }

    public final void invoke(LoadType loadType, boolean z, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "type");
        Intrinsics.checkNotNullParameter(loadState, "state");
        this.$callback.onStateUpdate(loadType, z, loadState);
    }
}
