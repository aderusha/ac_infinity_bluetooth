package androidx.paging;

import androidx.paging.LoadState;
import com.eternal.control.ControlCFragment;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J&\u0010\u0012\u001a\u00020\u00132\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00130\u0015H\bø\u0001\u0000J\u0015\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0019J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\u001d\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u001eJ\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u0002\u0007\n\u0005\b20\u0001¨\u0006\""}, mo27512d2 = {"Landroidx/paging/LoadStates;", "", "refresh", "Landroidx/paging/LoadState;", "prepend", "append", "(Landroidx/paging/LoadState;Landroidx/paging/LoadState;Landroidx/paging/LoadState;)V", "getAppend", "()Landroidx/paging/LoadState;", "getPrepend", "getRefresh", "component1", "component2", "component3", "copy", "equals", "", "other", "forEach", "", "op", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "get", "loadType", "get$paging_common", "hashCode", "", "modifyState", "newState", "modifyState$paging_common", "toString", "", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: LoadStates.kt */
public final class LoadStates {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final LoadStates IDLE = new LoadStates(LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadState.NotLoading.Companion.getIncomplete$paging_common());
    private final LoadState append;
    private final LoadState prepend;
    private final LoadState refresh;

    @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[LoadType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LoadType.APPEND.ordinal()] = 1;
            iArr[LoadType.PREPEND.ordinal()] = 2;
            iArr[LoadType.REFRESH.ordinal()] = 3;
            int[] iArr2 = new int[LoadType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[LoadType.REFRESH.ordinal()] = 1;
            iArr2[LoadType.APPEND.ordinal()] = 2;
            iArr2[LoadType.PREPEND.ordinal()] = 3;
        }
    }

    public static /* synthetic */ LoadStates copy$default(LoadStates loadStates, LoadState loadState, LoadState loadState2, LoadState loadState3, int i, Object obj) {
        if ((i & 1) != 0) {
            loadState = loadStates.refresh;
        }
        if ((i & 2) != 0) {
            loadState2 = loadStates.prepend;
        }
        if ((i & 4) != 0) {
            loadState3 = loadStates.append;
        }
        return loadStates.copy(loadState, loadState2, loadState3);
    }

    public final LoadState component1() {
        return this.refresh;
    }

    public final LoadState component2() {
        return this.prepend;
    }

    public final LoadState component3() {
        return this.append;
    }

    public final LoadStates copy(LoadState loadState, LoadState loadState2, LoadState loadState3) {
        Intrinsics.checkNotNullParameter(loadState, ControlCFragment.REFRESH);
        Intrinsics.checkNotNullParameter(loadState2, "prepend");
        Intrinsics.checkNotNullParameter(loadState3, "append");
        return new LoadStates(loadState, loadState2, loadState3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoadStates)) {
            return false;
        }
        LoadStates loadStates = (LoadStates) obj;
        return Intrinsics.areEqual((Object) this.refresh, (Object) loadStates.refresh) && Intrinsics.areEqual((Object) this.prepend, (Object) loadStates.prepend) && Intrinsics.areEqual((Object) this.append, (Object) loadStates.append);
    }

    public int hashCode() {
        LoadState loadState = this.refresh;
        int i = 0;
        int hashCode = (loadState != null ? loadState.hashCode() : 0) * 31;
        LoadState loadState2 = this.prepend;
        int hashCode2 = (hashCode + (loadState2 != null ? loadState2.hashCode() : 0)) * 31;
        LoadState loadState3 = this.append;
        if (loadState3 != null) {
            i = loadState3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "LoadStates(refresh=" + this.refresh + ", prepend=" + this.prepend + ", append=" + this.append + ")";
    }

    public LoadStates(LoadState loadState, LoadState loadState2, LoadState loadState3) {
        Intrinsics.checkNotNullParameter(loadState, ControlCFragment.REFRESH);
        Intrinsics.checkNotNullParameter(loadState2, "prepend");
        Intrinsics.checkNotNullParameter(loadState3, "append");
        this.refresh = loadState;
        this.prepend = loadState2;
        this.append = loadState3;
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

    public final void forEach(Function2<? super LoadType, ? super LoadState, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "op");
        function2.invoke(LoadType.REFRESH, getRefresh());
        function2.invoke(LoadType.PREPEND, getPrepend());
        function2.invoke(LoadType.APPEND, getAppend());
    }

    public final LoadStates modifyState$paging_common(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(loadState, "newState");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i == 1) {
            return copy$default(this, (LoadState) null, (LoadState) null, loadState, 3, (Object) null);
        }
        if (i == 2) {
            return copy$default(this, (LoadState) null, loadState, (LoadState) null, 5, (Object) null);
        }
        if (i == 3) {
            return copy$default(this, loadState, (LoadState) null, (LoadState) null, 6, (Object) null);
        }
        throw new NoWhenBranchMatchedException();
    }

    public final LoadState get$paging_common(LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$1[loadType.ordinal()];
        if (i == 1) {
            return this.refresh;
        }
        if (i == 2) {
            return this.append;
        }
        if (i == 3) {
            return this.prepend;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo27512d2 = {"Landroidx/paging/LoadStates$Companion;", "", "()V", "IDLE", "Landroidx/paging/LoadStates;", "getIDLE", "()Landroidx/paging/LoadStates;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: LoadStates.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LoadStates getIDLE() {
            return LoadStates.IDLE;
        }
    }
}
