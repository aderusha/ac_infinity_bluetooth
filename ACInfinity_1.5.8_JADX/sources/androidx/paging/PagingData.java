package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \r*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001\rB#\b\u0000\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR \u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, mo27512d2 = {"Landroidx/paging/PagingData;", "T", "", "flow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "receiver", "Landroidx/paging/UiReceiver;", "(Lkotlinx/coroutines/flow/Flow;Landroidx/paging/UiReceiver;)V", "getFlow$paging_common", "()Lkotlinx/coroutines/flow/Flow;", "getReceiver$paging_common", "()Landroidx/paging/UiReceiver;", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PagingData.kt */
public final class PagingData<T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final PagingData<Object> EMPTY;
    /* access modifiers changed from: private */
    public static final UiReceiver NOOP_RECEIVER;
    private final Flow<PageEvent<T>> flow;
    private final UiReceiver receiver;

    @JvmStatic
    public static final <T> PagingData<T> empty() {
        return Companion.empty();
    }

    @JvmStatic
    public static final <T> PagingData<T> from(List<? extends T> list) {
        return Companion.from(list);
    }

    public PagingData(Flow<? extends PageEvent<T>> flow2, UiReceiver uiReceiver) {
        Intrinsics.checkNotNullParameter(flow2, "flow");
        Intrinsics.checkNotNullParameter(uiReceiver, "receiver");
        this.flow = flow2;
        this.receiver = uiReceiver;
    }

    public final Flow<PageEvent<T>> getFlow$paging_common() {
        return this.flow;
    }

    public final UiReceiver getReceiver$paging_common() {
        return this.receiver;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\b\b\u0001\u0010\r*\u00020\u0001H\u0007J&\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\b\b\u0001\u0010\r*\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H\u0007R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, mo27512d2 = {"Landroidx/paging/PagingData$Companion;", "", "()V", "EMPTY", "Landroidx/paging/PagingData;", "getEMPTY$paging_common$annotations", "getEMPTY$paging_common", "()Landroidx/paging/PagingData;", "NOOP_RECEIVER", "Landroidx/paging/UiReceiver;", "getNOOP_RECEIVER$paging_common", "()Landroidx/paging/UiReceiver;", "empty", "T", "from", "data", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PagingData.kt */
    public static final class Companion {
        public static /* synthetic */ void getEMPTY$paging_common$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final UiReceiver getNOOP_RECEIVER$paging_common() {
            return PagingData.NOOP_RECEIVER;
        }

        public final PagingData<Object> getEMPTY$paging_common() {
            return PagingData.EMPTY;
        }

        @JvmStatic
        public final <T> PagingData<T> empty() {
            PagingData<Object> eMPTY$paging_common = getEMPTY$paging_common();
            Objects.requireNonNull(eMPTY$paging_common, "null cannot be cast to non-null type androidx.paging.PagingData<T>");
            return eMPTY$paging_common;
        }

        @JvmStatic
        public final <T> PagingData<T> from(List<? extends T> list) {
            Intrinsics.checkNotNullParameter(list, "data");
            return new PagingData<>(FlowKt.flowOf(PageEvent.Insert.Companion.Refresh(CollectionsKt.listOf(new TransformablePage(0, list)), 0, 0, new CombinedLoadStates(LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common(), new LoadStates(LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common()), (LoadStates) null, 16, (DefaultConstructorMarker) null))), getNOOP_RECEIVER$paging_common());
        }
    }

    static {
        UiReceiver pagingData$Companion$NOOP_RECEIVER$1 = new PagingData$Companion$NOOP_RECEIVER$1();
        NOOP_RECEIVER = pagingData$Companion$NOOP_RECEIVER$1;
        EMPTY = new PagingData<>(FlowKt.flowOf(PageEvent.Insert.Companion.getEMPTY_REFRESH_LOCAL()), pagingData$Companion$NOOP_RECEIVER$1);
    }
}
