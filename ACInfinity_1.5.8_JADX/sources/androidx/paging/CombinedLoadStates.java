package androidx.paging;

import androidx.paging.LoadState;
import com.eternal.control.ControlCFragment;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0002J-\u0010\u0014\u001a\u00020\u00152\u001e\u0010\u0016\u001a\u001a\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00150\u0017H\u0000¢\u0006\u0002\b\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001f"}, mo27512d2 = {"Landroidx/paging/CombinedLoadStates;", "", "refresh", "Landroidx/paging/LoadState;", "prepend", "append", "source", "Landroidx/paging/LoadStates;", "mediator", "(Landroidx/paging/LoadState;Landroidx/paging/LoadState;Landroidx/paging/LoadState;Landroidx/paging/LoadStates;Landroidx/paging/LoadStates;)V", "getAppend", "()Landroidx/paging/LoadState;", "getMediator", "()Landroidx/paging/LoadStates;", "getPrepend", "getRefresh", "getSource", "equals", "", "other", "forEach", "", "op", "Lkotlin/Function3;", "Landroidx/paging/LoadType;", "forEach$paging_common", "hashCode", "", "toString", "", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: CombinedLoadStates.kt */
public final class CombinedLoadStates {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final CombinedLoadStates IDLE_SOURCE = new CombinedLoadStates(LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadStates.Companion.getIDLE(), (LoadStates) null, 16, (DefaultConstructorMarker) null);
    private final LoadState append;
    private final LoadStates mediator;
    private final LoadState prepend;
    private final LoadState refresh;
    private final LoadStates source;

    public CombinedLoadStates(LoadState loadState, LoadState loadState2, LoadState loadState3, LoadStates loadStates, LoadStates loadStates2) {
        Intrinsics.checkNotNullParameter(loadState, ControlCFragment.REFRESH);
        Intrinsics.checkNotNullParameter(loadState2, "prepend");
        Intrinsics.checkNotNullParameter(loadState3, "append");
        Intrinsics.checkNotNullParameter(loadStates, "source");
        this.refresh = loadState;
        this.prepend = loadState2;
        this.append = loadState3;
        this.source = loadStates;
        this.mediator = loadStates2;
    }

    public final LoadState getRefresh() {
        return this.refresh;
    }

    public final LoadState getPrepend() {
        return this.prepend;
    }

    public final LoadState getAppend() {
        return this.append;
    }

    public final LoadStates getSource() {
        return this.source;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CombinedLoadStates(LoadState loadState, LoadState loadState2, LoadState loadState3, LoadStates loadStates, LoadStates loadStates2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(loadState, loadState2, loadState3, loadStates, (i & 16) != 0 ? null : loadStates2);
    }

    public final LoadStates getMediator() {
        return this.mediator;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type androidx.paging.CombinedLoadStates");
        CombinedLoadStates combinedLoadStates = (CombinedLoadStates) obj;
        if (!(!Intrinsics.areEqual((Object) this.refresh, (Object) combinedLoadStates.refresh)) && !(!Intrinsics.areEqual((Object) this.prepend, (Object) combinedLoadStates.prepend)) && !(!Intrinsics.areEqual((Object) this.append, (Object) combinedLoadStates.append)) && !(!Intrinsics.areEqual((Object) this.source, (Object) combinedLoadStates.source)) && !(!Intrinsics.areEqual((Object) this.mediator, (Object) combinedLoadStates.mediator))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((((this.refresh.hashCode() * 31) + this.prepend.hashCode()) * 31) + this.append.hashCode()) * 31) + this.source.hashCode()) * 31;
        LoadStates loadStates = this.mediator;
        return hashCode + (loadStates != null ? loadStates.hashCode() : 0);
    }

    public String toString() {
        return "CombinedLoadStates(refresh=" + this.refresh + ", prepend=" + this.prepend + ", append=" + this.append + ", " + "source=" + this.source + ", mediator=" + this.mediator + ')';
    }

    public final void forEach$paging_common(Function3<? super LoadType, ? super Boolean, ? super LoadState, Unit> function3) {
        Intrinsics.checkNotNullParameter(function3, "op");
        LoadStates loadStates = this.source;
        function3.invoke(LoadType.REFRESH, false, loadStates.getRefresh());
        function3.invoke(LoadType.PREPEND, false, loadStates.getPrepend());
        function3.invoke(LoadType.APPEND, false, loadStates.getAppend());
        LoadStates loadStates2 = this.mediator;
        if (loadStates2 != null) {
            function3.invoke(LoadType.REFRESH, true, loadStates2.getRefresh());
            function3.invoke(LoadType.PREPEND, true, loadStates2.getPrepend());
            function3.invoke(LoadType.APPEND, true, loadStates2.getAppend());
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo27512d2 = {"Landroidx/paging/CombinedLoadStates$Companion;", "", "()V", "IDLE_SOURCE", "Landroidx/paging/CombinedLoadStates;", "getIDLE_SOURCE", "()Landroidx/paging/CombinedLoadStates;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: CombinedLoadStates.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CombinedLoadStates getIDLE_SOURCE() {
            return CombinedLoadStates.IDLE_SOURCE;
        }
    }
}
