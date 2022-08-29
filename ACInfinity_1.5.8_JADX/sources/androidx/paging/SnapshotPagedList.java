package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0016J\"\u0010\u000f\u001a\u00020\u000e2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0011H\u0016J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\bR\u0014\u0010\t\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\n\u001a\u0004\u0018\u00010\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo27512d2 = {"Landroidx/paging/SnapshotPagedList;", "T", "", "Landroidx/paging/PagedList;", "pagedList", "(Landroidx/paging/PagedList;)V", "isDetached", "", "()Z", "isImmutable", "lastKey", "getLastKey", "()Ljava/lang/Object;", "detach", "", "dispatchCurrentLoadState", "callback", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "loadAroundInternal", "index", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: SnapshotPagedList.kt */
public final class SnapshotPagedList<T> extends PagedList<T> {
    private final boolean isDetached = true;
    private final boolean isImmutable = true;
    private final PagedList<T> pagedList;

    public void detach() {
    }

    public void dispatchCurrentLoadState(Function2<? super LoadType, ? super LoadState, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "callback");
    }

    public void loadAroundInternal(int i) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SnapshotPagedList(PagedList<T> pagedList2) {
        super(pagedList2.getPagingSource(), pagedList2.getCoroutineScope$paging_common(), pagedList2.getNotifyDispatcher$paging_common(), pagedList2.getStorage$paging_common().snapshot(), pagedList2.getConfig());
        Intrinsics.checkNotNullParameter(pagedList2, "pagedList");
        this.pagedList = pagedList2;
    }

    public boolean isImmutable() {
        return this.isImmutable;
    }

    public Object getLastKey() {
        return this.pagedList.getLastKey();
    }

    public boolean isDetached() {
        return this.isDetached;
    }
}
