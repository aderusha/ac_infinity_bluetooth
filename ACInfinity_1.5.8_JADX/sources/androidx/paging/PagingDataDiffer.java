package androidx.paging;

import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000*\u0001\u001e\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001a\u0010%\u001a\u00020\u001a2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001a0\u0019J\u001f\u0010'\u001a\u00020\u001a2\f\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)H@ø\u0001\u0000¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\nH\u0002J\u001a\u0010-\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010.\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010/J\u0017\u00100\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010.\u001a\u00020\u0010¢\u0006\u0002\u0010/J\b\u00101\u001a\u00020\u0012H\u0016JM\u00102\u001a\u0004\u0018\u00010\u00102\f\u00103\u001a\b\u0012\u0004\u0012\u00028\u0000042\f\u00105\u001a\b\u0012\u0004\u0012\u00028\u0000042\u0006\u00106\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u001a08H¦@ø\u0001\u0000¢\u0006\u0002\u00109J\u0006\u0010:\u001a\u00020\u001aJ\u001a\u0010;\u001a\u00020\u001a2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001a0\u0019J\u0006\u0010<\u001a\u00020\u001aJ\f\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000>R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001a0\u00190\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eX\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\"\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b#\u0010$\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, mo27512d2 = {"Landroidx/paging/PagingDataDiffer;", "T", "", "differCallback", "Landroidx/paging/DifferCallback;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Landroidx/paging/DifferCallback;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_combinedLoadState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/paging/CombinedLoadStates;", "collectFromRunner", "Landroidx/paging/SingleRunner;", "combinedLoadStates", "Landroidx/paging/MutableLoadStateCollection;", "lastAccessedIndex", "", "lastAccessedIndexUnfulfilled", "", "loadStateFlow", "Lkotlinx/coroutines/flow/Flow;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "loadStateListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function1;", "", "presenter", "Landroidx/paging/PagePresenter;", "processPageEventCallback", "androidx/paging/PagingDataDiffer$processPageEventCallback$1", "Landroidx/paging/PagingDataDiffer$processPageEventCallback$1;", "receiver", "Landroidx/paging/UiReceiver;", "size", "getSize", "()I", "addLoadStateListener", "listener", "collectFrom", "pagingData", "Landroidx/paging/PagingData;", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchLoadStates", "states", "get", "index", "(I)Ljava/lang/Object;", "peek", "postEvents", "presentNewList", "previousList", "Landroidx/paging/NullPaddedList;", "newList", "newCombinedLoadStates", "onListPresentable", "Lkotlin/Function0;", "(Landroidx/paging/NullPaddedList;Landroidx/paging/NullPaddedList;Landroidx/paging/CombinedLoadStates;ILkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refresh", "removeLoadStateListener", "retry", "snapshot", "Landroidx/paging/ItemSnapshotList;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PagingDataDiffer.kt */
public abstract class PagingDataDiffer<T> {
    /* access modifiers changed from: private */
    public final MutableStateFlow<CombinedLoadStates> _combinedLoadState;
    private final SingleRunner collectFromRunner;
    /* access modifiers changed from: private */
    public final MutableLoadStateCollection combinedLoadStates;
    /* access modifiers changed from: private */
    public final DifferCallback differCallback;
    /* access modifiers changed from: private */
    public volatile int lastAccessedIndex;
    /* access modifiers changed from: private */
    public volatile boolean lastAccessedIndexUnfulfilled;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<Function1<CombinedLoadStates, Unit>> loadStateListeners;
    /* access modifiers changed from: private */
    public final CoroutineDispatcher mainDispatcher;
    /* access modifiers changed from: private */
    public PagePresenter<T> presenter;
    /* access modifiers changed from: private */
    public final PagingDataDiffer$processPageEventCallback$1 processPageEventCallback;
    /* access modifiers changed from: private */
    public UiReceiver receiver;

    public boolean postEvents() {
        return false;
    }

    public abstract Object presentNewList(NullPaddedList<T> nullPaddedList, NullPaddedList<T> nullPaddedList2, CombinedLoadStates combinedLoadStates2, int i, Function0<Unit> function0, Continuation<? super Integer> continuation);

    public PagingDataDiffer(DifferCallback differCallback2, CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkNotNullParameter(differCallback2, "differCallback");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "mainDispatcher");
        this.differCallback = differCallback2;
        this.mainDispatcher = coroutineDispatcher;
        this.presenter = PagePresenter.Companion.initial$paging_common();
        MutableLoadStateCollection mutableLoadStateCollection = new MutableLoadStateCollection();
        this.combinedLoadStates = mutableLoadStateCollection;
        this.loadStateListeners = new CopyOnWriteArrayList<>();
        this.collectFromRunner = new SingleRunner(false, 1, (DefaultConstructorMarker) null);
        this.processPageEventCallback = new PagingDataDiffer$processPageEventCallback$1(this);
        this._combinedLoadState = StateFlowKt.MutableStateFlow(mutableLoadStateCollection.snapshot());
        addLoadStateListener(new Function1<CombinedLoadStates, Unit>(this) {
            final /* synthetic */ PagingDataDiffer this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((CombinedLoadStates) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(CombinedLoadStates combinedLoadStates) {
                Intrinsics.checkNotNullParameter(combinedLoadStates, "it");
                this.this$0._combinedLoadState.setValue(combinedLoadStates);
            }
        });
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PagingDataDiffer(DifferCallback differCallback2, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(differCallback2, (i & 2) != 0 ? Dispatchers.getMain() : coroutineDispatcher);
    }

    /* access modifiers changed from: private */
    public final void dispatchLoadStates(CombinedLoadStates combinedLoadStates2) {
        if (!Intrinsics.areEqual((Object) this.combinedLoadStates.snapshot(), (Object) combinedLoadStates2)) {
            this.combinedLoadStates.set(combinedLoadStates2);
            for (Function1 invoke : this.loadStateListeners) {
                invoke.invoke(combinedLoadStates2);
            }
        }
    }

    public final Object collectFrom(PagingData<T> pagingData, Continuation<? super Unit> continuation) {
        Object runInIsolation$default = SingleRunner.runInIsolation$default(this.collectFromRunner, 0, new PagingDataDiffer$collectFrom$2(this, pagingData, (Continuation) null), continuation, 1, (Object) null);
        return runInIsolation$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? runInIsolation$default : Unit.INSTANCE;
    }

    public final T get(int i) {
        this.lastAccessedIndexUnfulfilled = true;
        this.lastAccessedIndex = i;
        UiReceiver uiReceiver = this.receiver;
        if (uiReceiver != null) {
            uiReceiver.accessHint(this.presenter.accessHintForPresenterIndex(i));
        }
        return this.presenter.get(i);
    }

    public final T peek(int i) {
        return this.presenter.get(i);
    }

    public final ItemSnapshotList<T> snapshot() {
        return this.presenter.snapshot();
    }

    public final void retry() {
        UiReceiver uiReceiver = this.receiver;
        if (uiReceiver != null) {
            uiReceiver.retry();
        }
    }

    public final void refresh() {
        UiReceiver uiReceiver = this.receiver;
        if (uiReceiver != null) {
            uiReceiver.refresh();
        }
    }

    public final int getSize() {
        return this.presenter.getSize();
    }

    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this._combinedLoadState;
    }

    public final void addLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.loadStateListeners.add(function1);
        function1.invoke(this.combinedLoadStates.snapshot());
    }

    public final void removeLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.loadStateListeners.remove(function1);
    }
}
