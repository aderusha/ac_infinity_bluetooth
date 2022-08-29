package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PageFetcherSnapshotState;
import androidx.paging.PagingSource;
import androidx.paging.ViewportHint;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\u0001\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0011\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\u0010\u0014J\u000e\u0010-\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u0017J\u0006\u0010/\u001a\u00020\u000bJ\u001d\u00100\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0011H@ø\u0001\u0000¢\u0006\u0002\u00101J\u0011\u00102\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u00101J!\u00103\u001a\u00020\u000b2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H@ø\u0001\u0000¢\u0006\u0002\u00108J%\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000:2\u0006\u00104\u001a\u0002052\b\u0010;\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u0010<J#\u0010=\u001a\u00020\u000b2\u0006\u00104\u001a\u0002052\b\u0010.\u001a\u0004\u0018\u00010\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010>J#\u0010?\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020@0\n2\u0006\u00104\u001a\u000205H@ø\u0001\u0000¢\u0006\u0002\u0010AJ7\u0010B\u001a\u0004\u0018\u00018\u0000*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010C2\u0006\u00104\u001a\u0002052\u0006\u0010D\u001a\u00020@2\u0006\u0010E\u001a\u00020@H\u0002¢\u0006\u0002\u0010FJ)\u0010G\u001a\u00020\u000b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010C2\u0006\u00104\u001a\u000205H@ø\u0001\u0000¢\u0006\u0002\u0010HJ\f\u0010I\u001a\u00020\u000b*\u00020JH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0004\u001a\u0004\u0018\u00018\u0000X\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001f0\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001f0\n¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001c\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0011X\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010,X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006K"}, mo27512d2 = {"Landroidx/paging/PageFetcherSnapshot;", "Key", "", "Value", "initialKey", "pagingSource", "Landroidx/paging/PagingSource;", "config", "Landroidx/paging/PagingConfig;", "retryFlow", "Lkotlinx/coroutines/flow/Flow;", "", "triggerRemoteRefresh", "", "remoteMediatorConnection", "Landroidx/paging/RemoteMediatorConnection;", "previousPagingState", "Landroidx/paging/PagingState;", "invalidate", "Lkotlin/Function0;", "(Ljava/lang/Object;Landroidx/paging/PagingSource;Landroidx/paging/PagingConfig;Lkotlinx/coroutines/flow/Flow;ZLandroidx/paging/RemoteMediatorConnection;Landroidx/paging/PagingState;Lkotlin/jvm/functions/Function0;)V", "hintSharedFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Landroidx/paging/ViewportHint;", "getInitialKey$paging_common", "()Ljava/lang/Object;", "Ljava/lang/Object;", "lastHint", "Landroidx/paging/ViewportHint$Access;", "pageEventCh", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/paging/PageEvent;", "pageEventChCollected", "Ljava/util/concurrent/atomic/AtomicBoolean;", "pageEventChannelFlowJob", "Lkotlinx/coroutines/CompletableJob;", "pageEventFlow", "getPageEventFlow", "()Lkotlinx/coroutines/flow/Flow;", "getPagingSource$paging_common", "()Landroidx/paging/PagingSource;", "getRemoteMediatorConnection", "()Landroidx/paging/RemoteMediatorConnection;", "stateHolder", "Landroidx/paging/PageFetcherSnapshotState$Holder;", "accessHint", "viewportHint", "close", "currentPagingState", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doInitialLoad", "doLoad", "loadType", "Landroidx/paging/LoadType;", "generationalHint", "Landroidx/paging/GenerationalViewportHint;", "(Landroidx/paging/LoadType;Landroidx/paging/GenerationalViewportHint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadParams", "Landroidx/paging/PagingSource$LoadParams;", "key", "(Landroidx/paging/LoadType;Ljava/lang/Object;)Landroidx/paging/PagingSource$LoadParams;", "retryLoadError", "(Landroidx/paging/LoadType;Landroidx/paging/ViewportHint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectAsGenerationalViewportHints", "", "(Lkotlinx/coroutines/flow/Flow;Landroidx/paging/LoadType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nextLoadKeyOrNull", "Landroidx/paging/PageFetcherSnapshotState;", "generationId", "presentedItemsBeyondAnchor", "(Landroidx/paging/PageFetcherSnapshotState;Landroidx/paging/LoadType;II)Ljava/lang/Object;", "setLoading", "(Landroidx/paging/PageFetcherSnapshotState;Landroidx/paging/LoadType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startConsumingHints", "Lkotlinx/coroutines/CoroutineScope;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PageFetcherSnapshot.kt */
public final class PageFetcherSnapshot<Key, Value> {
    /* access modifiers changed from: private */
    public final PagingConfig config;
    /* access modifiers changed from: private */
    public final MutableSharedFlow<ViewportHint> hintSharedFlow;
    private final Key initialKey;
    /* access modifiers changed from: private */
    public final Function0<Unit> invalidate;
    /* access modifiers changed from: private */
    public ViewportHint.Access lastHint;
    /* access modifiers changed from: private */
    public final Channel<PageEvent<Value>> pageEventCh;
    /* access modifiers changed from: private */
    public final AtomicBoolean pageEventChCollected;
    private final CompletableJob pageEventChannelFlowJob;
    private final Flow<PageEvent<Value>> pageEventFlow;
    private final PagingSource<Key, Value> pagingSource;
    /* access modifiers changed from: private */
    public final PagingState<Key, Value> previousPagingState;
    private final RemoteMediatorConnection<Key, Value> remoteMediatorConnection;
    /* access modifiers changed from: private */
    public final Flow<Unit> retryFlow;
    /* access modifiers changed from: private */
    public final PageFetcherSnapshotState.Holder<Key, Value> stateHolder;
    /* access modifiers changed from: private */
    public final boolean triggerRemoteRefresh;

    @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            int[] iArr = new int[LoadType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LoadType.REFRESH.ordinal()] = 1;
            int[] iArr2 = new int[LoadType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[LoadType.REFRESH.ordinal()] = 1;
            int[] iArr3 = new int[LoadType.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[LoadType.PREPEND.ordinal()] = 1;
            iArr3[LoadType.APPEND.ordinal()] = 2;
            iArr3[LoadType.REFRESH.ordinal()] = 3;
            int[] iArr4 = new int[LoadType.values().length];
            $EnumSwitchMapping$3 = iArr4;
            iArr4[LoadType.PREPEND.ordinal()] = 1;
            iArr4[LoadType.APPEND.ordinal()] = 2;
            int[] iArr5 = new int[LoadType.values().length];
            $EnumSwitchMapping$4 = iArr5;
            iArr5[LoadType.PREPEND.ordinal()] = 1;
        }
    }

    public PageFetcherSnapshot(Key key, PagingSource<Key, Value> pagingSource2, PagingConfig pagingConfig, Flow<Unit> flow, boolean z, RemoteMediatorConnection<Key, Value> remoteMediatorConnection2, PagingState<Key, Value> pagingState, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(pagingSource2, "pagingSource");
        Intrinsics.checkNotNullParameter(pagingConfig, "config");
        Intrinsics.checkNotNullParameter(flow, "retryFlow");
        Intrinsics.checkNotNullParameter(function0, "invalidate");
        this.initialKey = key;
        this.pagingSource = pagingSource2;
        this.config = pagingConfig;
        this.retryFlow = flow;
        this.triggerRemoteRefresh = z;
        this.remoteMediatorConnection = remoteMediatorConnection2;
        this.previousPagingState = pagingState;
        this.invalidate = function0;
        if (pagingConfig.jumpThreshold == Integer.MIN_VALUE || pagingSource2.getJumpingSupported()) {
            this.hintSharedFlow = SharedFlowKt.MutableSharedFlow$default(1, 0, (BufferOverflow) null, 6, (Object) null);
            this.pageEventChCollected = new AtomicBoolean(false);
            this.pageEventCh = ChannelKt.Channel$default(-2, (BufferOverflow) null, (Function1) null, 6, (Object) null);
            this.stateHolder = new PageFetcherSnapshotState.Holder<>(pagingConfig);
            CompletableJob Job$default = JobKt.Job$default((Job) null, 1, (Object) null);
            this.pageEventChannelFlowJob = Job$default;
            this.pageEventFlow = CancelableChannelFlowKt.cancelableChannelFlow(Job$default, new PageFetcherSnapshot$pageEventFlow$1(this, (Continuation) null));
            return;
        }
        throw new IllegalArgumentException("PagingConfig.jumpThreshold was set, but the associated PagingSource has not marked support for jumps by overriding PagingSource.jumpingSupported to true.".toString());
    }

    public final Key getInitialKey$paging_common() {
        return this.initialKey;
    }

    public final PagingSource<Key, Value> getPagingSource$paging_common() {
        return this.pagingSource;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ PageFetcherSnapshot(java.lang.Object r12, androidx.paging.PagingSource r13, androidx.paging.PagingConfig r14, kotlinx.coroutines.flow.Flow r15, boolean r16, androidx.paging.RemoteMediatorConnection r17, androidx.paging.PagingState r18, kotlin.jvm.functions.Function0 r19, int r20, kotlin.jvm.internal.DefaultConstructorMarker r21) {
        /*
            r11 = this;
            r0 = r20
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0009
            r1 = 0
            r7 = 0
            goto L_0x000b
        L_0x0009:
            r7 = r16
        L_0x000b:
            r1 = r0 & 32
            r2 = 0
            if (r1 == 0) goto L_0x0015
            r1 = r2
            androidx.paging.RemoteMediatorConnection r1 = (androidx.paging.RemoteMediatorConnection) r1
            r8 = r1
            goto L_0x0017
        L_0x0015:
            r8 = r17
        L_0x0017:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0020
            r1 = r2
            androidx.paging.PagingState r1 = (androidx.paging.PagingState) r1
            r9 = r1
            goto L_0x0022
        L_0x0020:
            r9 = r18
        L_0x0022:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x002c
            androidx.paging.PageFetcherSnapshot$1 r0 = androidx.paging.PageFetcherSnapshot.C04921.INSTANCE
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            r10 = r0
            goto L_0x002e
        L_0x002c:
            r10 = r19
        L_0x002e:
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.<init>(java.lang.Object, androidx.paging.PagingSource, androidx.paging.PagingConfig, kotlinx.coroutines.flow.Flow, boolean, androidx.paging.RemoteMediatorConnection, androidx.paging.PagingState, kotlin.jvm.functions.Function0, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final RemoteMediatorConnection<Key, Value> getRemoteMediatorConnection() {
        return this.remoteMediatorConnection;
    }

    public final Flow<PageEvent<Value>> getPageEventFlow() {
        return this.pageEventFlow;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object retryLoadError(LoadType loadType, ViewportHint viewportHint, Continuation<? super Unit> continuation) {
        boolean z = true;
        if (WhenMappings.$EnumSwitchMapping$1[loadType.ordinal()] != 1) {
            if (viewportHint == null) {
                z = false;
            }
            if (z) {
                this.hintSharedFlow.tryEmit(viewportHint);
            } else {
                throw new IllegalStateException("Cannot retry APPEND / PREPEND load on PagingSource without ViewportHint".toString());
            }
        } else {
            Object doInitialLoad = doInitialLoad(continuation);
            if (doInitialLoad == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return doInitialLoad;
            }
        }
        return Unit.INSTANCE;
    }

    public final void accessHint(ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        if (viewportHint instanceof ViewportHint.Access) {
            this.lastHint = (ViewportHint.Access) viewportHint;
        }
        this.hintSharedFlow.tryEmit(viewportHint);
    }

    public final void close() {
        Job.DefaultImpls.cancel$default((Job) this.pageEventChannelFlowJob, (CancellationException) null, 1, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object currentPagingState(kotlin.coroutines.Continuation<? super androidx.paging.PagingState<Key, Value>> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.paging.PageFetcherSnapshot$currentPagingState$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            androidx.paging.PageFetcherSnapshot$currentPagingState$1 r0 = (androidx.paging.PageFetcherSnapshot$currentPagingState$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            androidx.paging.PageFetcherSnapshot$currentPagingState$1 r0 = new androidx.paging.PageFetcherSnapshot$currentPagingState$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 != r4) goto L_0x0037
            java.lang.Object r1 = r0.L$2
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r2 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r2 = (androidx.paging.PageFetcherSnapshotState.Holder) r2
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcherSnapshot r0 = (androidx.paging.PageFetcherSnapshot) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0059
        L_0x0037:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r2 = r5.stateHolder
            kotlinx.coroutines.sync.Mutex r6 = r2.lock
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r6
            r0.label = r4
            java.lang.Object r0 = r6.lock(r3, r0)
            if (r0 != r1) goto L_0x0057
            return r1
        L_0x0057:
            r0 = r5
            r1 = r6
        L_0x0059:
            androidx.paging.PageFetcherSnapshotState r6 = r2.state     // Catch:{ all -> 0x0067 }
            androidx.paging.ViewportHint$Access r0 = r0.lastHint     // Catch:{ all -> 0x0067 }
            androidx.paging.PagingState r6 = r6.currentPagingState$paging_common(r0)     // Catch:{ all -> 0x0067 }
            r1.unlock(r3)
            return r6
        L_0x0067:
            r6 = move-exception
            r1.unlock(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.currentPagingState(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final void startConsumingHints(CoroutineScope coroutineScope) {
        if (this.config.jumpThreshold != Integer.MIN_VALUE) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new PageFetcherSnapshot$startConsumingHints$1(this, (Continuation) null), 3, (Object) null);
        }
        Job unused2 = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new PageFetcherSnapshot$startConsumingHints$2(this, (Continuation) null), 3, (Object) null);
        Job unused3 = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new PageFetcherSnapshot$startConsumingHints$3(this, (Continuation) null), 3, (Object) null);
    }

    private final PagingSource.LoadParams<Key> loadParams(LoadType loadType, Key key) {
        return PagingSource.LoadParams.Companion.create(loadType, key, loadType == LoadType.REFRESH ? this.config.initialLoadSize : this.config.pageSize, this.config.enablePlaceholders);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0274, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0275, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0276, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0279, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x027c, code lost:
        if ((r6 instanceof androidx.paging.PagingSource.LoadResult.Error) == false) goto L_0x02de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x027e, code lost:
        r5 = r7.stateHolder;
        r11 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r5);
        r0.L$0 = r7;
        r0.L$1 = r6;
        r0.L$2 = r5;
        r0.L$3 = r11;
        r0.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0294, code lost:
        if (r11.lock((java.lang.Object) null, r0) != r1) goto L_0x0297;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0296, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:?, code lost:
        r2 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5);
        r5 = new androidx.paging.LoadState.Error(((androidx.paging.PagingSource.LoadResult.Error) r6).getThrowable());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02af, code lost:
        if (r2.setSourceLoadState(androidx.paging.LoadType.REFRESH, r5) == false) goto L_0x02cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02b1, code lost:
        r2 = r7.pageEventCh;
        r6 = new androidx.paging.PageEvent.LoadStateUpdate(androidx.paging.LoadType.REFRESH, false, r5);
        r0.L$0 = r11;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.L$3 = null;
        r0.label = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02cc, code lost:
        if (r2.send(r6, r0) != r1) goto L_0x02cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02ce, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02cf, code lost:
        r0 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:?, code lost:
        r11 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02d2, code lost:
        r0.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02d6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02d7, code lost:
        r9 = r0;
        r0 = r11;
        r11 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02da, code lost:
        r0.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02dd, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02e0, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r11 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5);
        r5 = androidx.paging.LoadType.REFRESH;
        r0.L$0 = r6;
        r0.L$1 = r2;
        r0.L$2 = null;
        r0.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0111, code lost:
        if (r6.setLoading(r11, r5, r0) != r1) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0113, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0114, code lost:
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0115, code lost:
        r11 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0117, code lost:
        r2.unlock((java.lang.Object) null);
        r11 = r5.loadParams(androidx.paging.LoadType.REFRESH, r5.initialKey);
        r2 = r5.pagingSource;
        r0.L$0 = r5;
        r0.L$1 = null;
        r0.label = 3;
        r11 = r2.load(r11, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x012f, code lost:
        if (r11 != r1) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0131, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0132, code lost:
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0133, code lost:
        r6 = (androidx.paging.PagingSource.LoadResult) r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0138, code lost:
        if ((r6 instanceof androidx.paging.PagingSource.LoadResult.Page) == false) goto L_0x027a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x013a, code lost:
        r5 = r7.stateHolder;
        r2 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r5);
        r0.L$0 = r7;
        r0.L$1 = r6;
        r0.L$2 = r5;
        r0.L$3 = r2;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x014f, code lost:
        if (r2.lock((java.lang.Object) null, r0) != r1) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0151, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5).insert(0, androidx.paging.LoadType.REFRESH, (androidx.paging.PagingSource.LoadResult.Page) r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0163, code lost:
        r2.unlock((java.lang.Object) null);
        r2 = r11.booleanValue();
        r5 = r7.stateHolder;
        r3 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r5);
        r0.L$0 = r7;
        r0.L$1 = r6;
        r0.L$2 = r5;
        r0.L$3 = r3;
        r0.Z$0 = r2;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0181, code lost:
        if (r3.lock((java.lang.Object) null, r0) != r1) goto L_0x0184;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0183, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r11 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5);
        r11.setSourceLoadState(androidx.paging.LoadType.REFRESH, androidx.paging.LoadState.NotLoading.Companion.getIncomplete$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x019c, code lost:
        if (((androidx.paging.PagingSource.LoadResult.Page) r6).getPrevKey() != null) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x019e, code lost:
        r11.setSourceLoadState(androidx.paging.LoadType.PREPEND, androidx.paging.LoadState.NotLoading.Companion.getComplete$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01b2, code lost:
        if (((androidx.paging.PagingSource.LoadResult.Page) r6).getNextKey() != null) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01b4, code lost:
        r11.setSourceLoadState(androidx.paging.LoadType.APPEND, androidx.paging.LoadState.NotLoading.Companion.getComplete$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01c1, code lost:
        r11 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01c6, code lost:
        if (r2 == false) goto L_0x0210;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01c8, code lost:
        r3 = r7.stateHolder;
        r11 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r3);
        r0.L$0 = r7;
        r0.L$1 = r6;
        r0.L$2 = r3;
        r0.L$3 = r11;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01dd, code lost:
        if (r11.lock((java.lang.Object) null, r0) != r1) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01df, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01e0, code lost:
        r2 = r11;
        r5 = r6;
        r6 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r11 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r3);
        r3 = r6.pageEventCh;
        r11 = r11.toPageEvent$paging_common((androidx.paging.PagingSource.LoadResult.Page) r5, androidx.paging.LoadType.REFRESH);
        r0.L$0 = r6;
        r0.L$1 = r5;
        r0.L$2 = r2;
        r0.L$3 = null;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0201, code lost:
        if (r3.send(r11, r0) != r1) goto L_0x0204;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0203, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0204, code lost:
        r3 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0206, code lost:
        r11 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0208, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0210, code lost:
        r3 = r6;
        r5 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0214, code lost:
        if (r5.remoteMediatorConnection == null) goto L_0x02de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0216, code lost:
        r11 = (androidx.paging.PagingSource.LoadResult.Page) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x021d, code lost:
        if (r11.getPrevKey() == null) goto L_0x0225;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0223, code lost:
        if (r11.getNextKey() != null) goto L_0x02de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0225, code lost:
        r2 = r5.stateHolder;
        r11 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r2);
        r0.L$0 = r5;
        r0.L$1 = r3;
        r0.L$2 = r2;
        r0.L$3 = r11;
        r0.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x023b, code lost:
        if (r11.lock((java.lang.Object) null, r0) != r1) goto L_0x023e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x023d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x023e, code lost:
        r1 = r11;
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        r11 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r2).currentPagingState$paging_common(r0.lastHint);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x024a, code lost:
        r1.unlock((java.lang.Object) null);
        r3 = (androidx.paging.PagingSource.LoadResult.Page) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0253, code lost:
        if (r3.getPrevKey() != null) goto L_0x025c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0255, code lost:
        r0.remoteMediatorConnection.requestLoad(androidx.paging.LoadType.PREPEND, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0260, code lost:
        if (r3.getNextKey() != null) goto L_0x02de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0262, code lost:
        r0.remoteMediatorConnection.requestLoad(androidx.paging.LoadType.APPEND, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x026b, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x026c, code lost:
        r1.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x026f, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0270, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0271, code lost:
        r3.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object doInitialLoad(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof androidx.paging.PageFetcherSnapshot$doInitialLoad$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            androidx.paging.PageFetcherSnapshot$doInitialLoad$1 r0 = (androidx.paging.PageFetcherSnapshot$doInitialLoad$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            androidx.paging.PageFetcherSnapshot$doInitialLoad$1 r0 = new androidx.paging.PageFetcherSnapshot$doInitialLoad$1
            r0.<init>(r10, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 0
            switch(r2) {
                case 0: goto L_0x00e3;
                case 1: goto L_0x00d3;
                case 2: goto L_0x00c4;
                case 3: goto L_0x00ba;
                case 4: goto L_0x00a5;
                case 5: goto L_0x008e;
                case 6: goto L_0x0079;
                case 7: goto L_0x0065;
                case 8: goto L_0x0050;
                case 9: goto L_0x003a;
                case 10: goto L_0x002e;
                default: goto L_0x0026;
            }
        L_0x0026:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x002e:
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0037 }
            goto L_0x02d0
        L_0x0037:
            r11 = move-exception
            goto L_0x02da
        L_0x003a:
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r0.L$1
            androidx.paging.PagingSource$LoadResult r6 = (androidx.paging.PagingSource.LoadResult) r6
            java.lang.Object r7 = r0.L$0
            androidx.paging.PageFetcherSnapshot r7 = (androidx.paging.PageFetcherSnapshot) r7
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r2
            goto L_0x0297
        L_0x0050:
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r2 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r2 = (androidx.paging.PageFetcherSnapshotState.Holder) r2
            java.lang.Object r3 = r0.L$1
            androidx.paging.PagingSource$LoadResult r3 = (androidx.paging.PagingSource.LoadResult) r3
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcherSnapshot r0 = (androidx.paging.PageFetcherSnapshot) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0240
        L_0x0065:
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r3 = r0.L$1
            androidx.paging.PagingSource$LoadResult r3 = (androidx.paging.PagingSource.LoadResult) r3
            java.lang.Object r5 = r0.L$0
            androidx.paging.PageFetcherSnapshot r5 = (androidx.paging.PageFetcherSnapshot) r5
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0076 }
            goto L_0x0206
        L_0x0076:
            r11 = move-exception
            goto L_0x020c
        L_0x0079:
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r3 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r3 = (androidx.paging.PageFetcherSnapshotState.Holder) r3
            java.lang.Object r5 = r0.L$1
            androidx.paging.PagingSource$LoadResult r5 = (androidx.paging.PagingSource.LoadResult) r5
            java.lang.Object r6 = r0.L$0
            androidx.paging.PageFetcherSnapshot r6 = (androidx.paging.PageFetcherSnapshot) r6
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x01e3
        L_0x008e:
            boolean r2 = r0.Z$0
            java.lang.Object r3 = r0.L$3
            kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
            java.lang.Object r5 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r0.L$1
            androidx.paging.PagingSource$LoadResult r6 = (androidx.paging.PagingSource.LoadResult) r6
            java.lang.Object r7 = r0.L$0
            androidx.paging.PageFetcherSnapshot r7 = (androidx.paging.PageFetcherSnapshot) r7
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0184
        L_0x00a5:
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r0.L$1
            androidx.paging.PagingSource$LoadResult r6 = (androidx.paging.PagingSource.LoadResult) r6
            java.lang.Object r7 = r0.L$0
            androidx.paging.PageFetcherSnapshot r7 = (androidx.paging.PageFetcherSnapshot) r7
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0152
        L_0x00ba:
            java.lang.Object r2 = r0.L$0
            androidx.paging.PageFetcherSnapshot r2 = (androidx.paging.PageFetcherSnapshot) r2
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r2
            goto L_0x0133
        L_0x00c4:
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$0
            androidx.paging.PageFetcherSnapshot r5 = (androidx.paging.PageFetcherSnapshot) r5
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x00d0 }
            goto L_0x0115
        L_0x00d0:
            r11 = move-exception
            goto L_0x02e1
        L_0x00d3:
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r5 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r0.L$0
            androidx.paging.PageFetcherSnapshot r6 = (androidx.paging.PageFetcherSnapshot) r6
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00fe
        L_0x00e3:
            kotlin.ResultKt.throwOnFailure(r11)
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r10.stateHolder
            kotlinx.coroutines.sync.Mutex r11 = r5.lock
            r0.L$0 = r10
            r0.L$1 = r5
            r0.L$2 = r11
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r11.lock(r4, r0)
            if (r2 != r1) goto L_0x00fc
            return r1
        L_0x00fc:
            r6 = r10
            r2 = r11
        L_0x00fe:
            androidx.paging.PageFetcherSnapshotState r11 = r5.state     // Catch:{ all -> 0x00d0 }
            androidx.paging.LoadType r5 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x00d0 }
            r0.L$0 = r6     // Catch:{ all -> 0x00d0 }
            r0.L$1 = r2     // Catch:{ all -> 0x00d0 }
            r0.L$2 = r4     // Catch:{ all -> 0x00d0 }
            r7 = 2
            r0.label = r7     // Catch:{ all -> 0x00d0 }
            java.lang.Object r11 = r6.setLoading(r11, r5, r0)     // Catch:{ all -> 0x00d0 }
            if (r11 != r1) goto L_0x0114
            return r1
        L_0x0114:
            r5 = r6
        L_0x0115:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00d0 }
            r2.unlock(r4)
            androidx.paging.LoadType r11 = androidx.paging.LoadType.REFRESH
            Key r2 = r5.initialKey
            androidx.paging.PagingSource$LoadParams r11 = r5.loadParams(r11, r2)
            androidx.paging.PagingSource<Key, Value> r2 = r5.pagingSource
            r0.L$0 = r5
            r0.L$1 = r4
            r6 = 3
            r0.label = r6
            java.lang.Object r11 = r2.load(r11, r0)
            if (r11 != r1) goto L_0x0132
            return r1
        L_0x0132:
            r7 = r5
        L_0x0133:
            r6 = r11
            androidx.paging.PagingSource$LoadResult r6 = (androidx.paging.PagingSource.LoadResult) r6
            boolean r11 = r6 instanceof androidx.paging.PagingSource.LoadResult.Page
            if (r11 == 0) goto L_0x027a
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r7.stateHolder
            kotlinx.coroutines.sync.Mutex r2 = r5.lock
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r5
            r0.L$3 = r2
            r11 = 4
            r0.label = r11
            java.lang.Object r11 = r2.lock(r4, r0)
            if (r11 != r1) goto L_0x0152
            return r1
        L_0x0152:
            androidx.paging.PageFetcherSnapshotState r11 = r5.state     // Catch:{ all -> 0x0275 }
            androidx.paging.LoadType r5 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0275 }
            r8 = r6
            androidx.paging.PagingSource$LoadResult$Page r8 = (androidx.paging.PagingSource.LoadResult.Page) r8     // Catch:{ all -> 0x0275 }
            boolean r11 = r11.insert(r3, r5, r8)     // Catch:{ all -> 0x0275 }
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)     // Catch:{ all -> 0x0275 }
            r2.unlock(r4)
            boolean r2 = r11.booleanValue()
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r7.stateHolder
            kotlinx.coroutines.sync.Mutex r3 = r5.lock
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r5
            r0.L$3 = r3
            r0.Z$0 = r2
            r11 = 5
            r0.label = r11
            java.lang.Object r11 = r3.lock(r4, r0)
            if (r11 != r1) goto L_0x0184
            return r1
        L_0x0184:
            androidx.paging.PageFetcherSnapshotState r11 = r5.state     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadType r5 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadState$NotLoading$Companion r8 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadState$NotLoading r8 = r8.getIncomplete$paging_common()     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadState r8 = (androidx.paging.LoadState) r8     // Catch:{ all -> 0x0270 }
            r11.setSourceLoadState(r5, r8)     // Catch:{ all -> 0x0270 }
            r5 = r6
            androidx.paging.PagingSource$LoadResult$Page r5 = (androidx.paging.PagingSource.LoadResult.Page) r5     // Catch:{ all -> 0x0270 }
            java.lang.Object r5 = r5.getPrevKey()     // Catch:{ all -> 0x0270 }
            if (r5 != 0) goto L_0x01ab
            androidx.paging.LoadType r5 = androidx.paging.LoadType.PREPEND     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadState$NotLoading$Companion r8 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadState$NotLoading r8 = r8.getComplete$paging_common()     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadState r8 = (androidx.paging.LoadState) r8     // Catch:{ all -> 0x0270 }
            r11.setSourceLoadState(r5, r8)     // Catch:{ all -> 0x0270 }
        L_0x01ab:
            r5 = r6
            androidx.paging.PagingSource$LoadResult$Page r5 = (androidx.paging.PagingSource.LoadResult.Page) r5     // Catch:{ all -> 0x0270 }
            java.lang.Object r5 = r5.getNextKey()     // Catch:{ all -> 0x0270 }
            if (r5 != 0) goto L_0x01c1
            androidx.paging.LoadType r5 = androidx.paging.LoadType.APPEND     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadState$NotLoading$Companion r8 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadState$NotLoading r8 = r8.getComplete$paging_common()     // Catch:{ all -> 0x0270 }
            androidx.paging.LoadState r8 = (androidx.paging.LoadState) r8     // Catch:{ all -> 0x0270 }
            r11.setSourceLoadState(r5, r8)     // Catch:{ all -> 0x0270 }
        L_0x01c1:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0270 }
            r3.unlock(r4)
            if (r2 == 0) goto L_0x0210
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r3 = r7.stateHolder
            kotlinx.coroutines.sync.Mutex r11 = r3.lock
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r3
            r0.L$3 = r11
            r2 = 6
            r0.label = r2
            java.lang.Object r2 = r11.lock(r4, r0)
            if (r2 != r1) goto L_0x01e0
            return r1
        L_0x01e0:
            r2 = r11
            r5 = r6
            r6 = r7
        L_0x01e3:
            androidx.paging.PageFetcherSnapshotState r11 = r3.state     // Catch:{ all -> 0x0076 }
            kotlinx.coroutines.channels.Channel<androidx.paging.PageEvent<Value>> r3 = r6.pageEventCh     // Catch:{ all -> 0x0076 }
            r7 = r5
            androidx.paging.PagingSource$LoadResult$Page r7 = (androidx.paging.PagingSource.LoadResult.Page) r7     // Catch:{ all -> 0x0076 }
            androidx.paging.LoadType r8 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0076 }
            androidx.paging.PageEvent r11 = r11.toPageEvent$paging_common(r7, r8)     // Catch:{ all -> 0x0076 }
            r0.L$0 = r6     // Catch:{ all -> 0x0076 }
            r0.L$1 = r5     // Catch:{ all -> 0x0076 }
            r0.L$2 = r2     // Catch:{ all -> 0x0076 }
            r0.L$3 = r4     // Catch:{ all -> 0x0076 }
            r7 = 7
            r0.label = r7     // Catch:{ all -> 0x0076 }
            java.lang.Object r11 = r3.send(r11, r0)     // Catch:{ all -> 0x0076 }
            if (r11 != r1) goto L_0x0204
            return r1
        L_0x0204:
            r3 = r5
            r5 = r6
        L_0x0206:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0076 }
            r2.unlock(r4)
            goto L_0x0212
        L_0x020c:
            r2.unlock(r4)
            throw r11
        L_0x0210:
            r3 = r6
            r5 = r7
        L_0x0212:
            androidx.paging.RemoteMediatorConnection<Key, Value> r11 = r5.remoteMediatorConnection
            if (r11 == 0) goto L_0x02de
            r11 = r3
            androidx.paging.PagingSource$LoadResult$Page r11 = (androidx.paging.PagingSource.LoadResult.Page) r11
            java.lang.Object r2 = r11.getPrevKey()
            if (r2 == 0) goto L_0x0225
            java.lang.Object r11 = r11.getNextKey()
            if (r11 != 0) goto L_0x02de
        L_0x0225:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r2 = r5.stateHolder
            kotlinx.coroutines.sync.Mutex r11 = r2.lock
            r0.L$0 = r5
            r0.L$1 = r3
            r0.L$2 = r2
            r0.L$3 = r11
            r6 = 8
            r0.label = r6
            java.lang.Object r0 = r11.lock(r4, r0)
            if (r0 != r1) goto L_0x023e
            return r1
        L_0x023e:
            r1 = r11
            r0 = r5
        L_0x0240:
            androidx.paging.PageFetcherSnapshotState r11 = r2.state     // Catch:{ all -> 0x026b }
            androidx.paging.ViewportHint$Access r2 = r0.lastHint     // Catch:{ all -> 0x026b }
            androidx.paging.PagingState r11 = r11.currentPagingState$paging_common(r2)     // Catch:{ all -> 0x026b }
            r1.unlock(r4)
            androidx.paging.PagingSource$LoadResult$Page r3 = (androidx.paging.PagingSource.LoadResult.Page) r3
            java.lang.Object r1 = r3.getPrevKey()
            if (r1 != 0) goto L_0x025c
            androidx.paging.RemoteMediatorConnection<Key, Value> r1 = r0.remoteMediatorConnection
            androidx.paging.LoadType r2 = androidx.paging.LoadType.PREPEND
            r1.requestLoad(r2, r11)
        L_0x025c:
            java.lang.Object r1 = r3.getNextKey()
            if (r1 != 0) goto L_0x02de
            androidx.paging.RemoteMediatorConnection<Key, Value> r0 = r0.remoteMediatorConnection
            androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND
            r0.requestLoad(r1, r11)
            goto L_0x02de
        L_0x026b:
            r11 = move-exception
            r1.unlock(r4)
            throw r11
        L_0x0270:
            r11 = move-exception
            r3.unlock(r4)
            throw r11
        L_0x0275:
            r11 = move-exception
            r2.unlock(r4)
            throw r11
        L_0x027a:
            boolean r11 = r6 instanceof androidx.paging.PagingSource.LoadResult.Error
            if (r11 == 0) goto L_0x02de
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r7.stateHolder
            kotlinx.coroutines.sync.Mutex r11 = r5.lock
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r5
            r0.L$3 = r11
            r2 = 9
            r0.label = r2
            java.lang.Object r2 = r11.lock(r4, r0)
            if (r2 != r1) goto L_0x0297
            return r1
        L_0x0297:
            androidx.paging.PageFetcherSnapshotState r2 = r5.state     // Catch:{ all -> 0x02d6 }
            androidx.paging.LoadState$Error r5 = new androidx.paging.LoadState$Error     // Catch:{ all -> 0x02d6 }
            androidx.paging.PagingSource$LoadResult$Error r6 = (androidx.paging.PagingSource.LoadResult.Error) r6     // Catch:{ all -> 0x02d6 }
            java.lang.Throwable r6 = r6.getThrowable()     // Catch:{ all -> 0x02d6 }
            r5.<init>(r6)     // Catch:{ all -> 0x02d6 }
            androidx.paging.LoadType r6 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x02d6 }
            r8 = r5
            androidx.paging.LoadState r8 = (androidx.paging.LoadState) r8     // Catch:{ all -> 0x02d6 }
            boolean r2 = r2.setSourceLoadState(r6, r8)     // Catch:{ all -> 0x02d6 }
            if (r2 == 0) goto L_0x02cf
            kotlinx.coroutines.channels.Channel<androidx.paging.PageEvent<Value>> r2 = r7.pageEventCh     // Catch:{ all -> 0x02d6 }
            androidx.paging.PageEvent$LoadStateUpdate r6 = new androidx.paging.PageEvent$LoadStateUpdate     // Catch:{ all -> 0x02d6 }
            androidx.paging.LoadType r7 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x02d6 }
            androidx.paging.LoadState r5 = (androidx.paging.LoadState) r5     // Catch:{ all -> 0x02d6 }
            r6.<init>(r7, r3, r5)     // Catch:{ all -> 0x02d6 }
            r0.L$0 = r11     // Catch:{ all -> 0x02d6 }
            r0.L$1 = r4     // Catch:{ all -> 0x02d6 }
            r0.L$2 = r4     // Catch:{ all -> 0x02d6 }
            r0.L$3 = r4     // Catch:{ all -> 0x02d6 }
            r3 = 10
            r0.label = r3     // Catch:{ all -> 0x02d6 }
            java.lang.Object r0 = r2.send(r6, r0)     // Catch:{ all -> 0x02d6 }
            if (r0 != r1) goto L_0x02cf
            return r1
        L_0x02cf:
            r0 = r11
        L_0x02d0:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0037 }
            r0.unlock(r4)
            goto L_0x02de
        L_0x02d6:
            r0 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
        L_0x02da:
            r0.unlock(r4)
            throw r11
        L_0x02de:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x02e1:
            r2.unlock(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.doInitialLoad(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: androidx.paging.PagingSource$LoadResult$Page} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: androidx.paging.PageEvent$Drop} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v69, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v88, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v72, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v76, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v80, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v83, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v84, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v86, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v87, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x03b1, code lost:
        throw new java.lang.IllegalArgumentException("Use doInitialLoad for LoadType == REFRESH");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x03b2, code lost:
        r2 = ((androidx.paging.PagingSource.LoadResult.Page) r6).getPrevKey();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x03c0, code lost:
        if (r9.pagingSource.getKeyReuseSupported() != false) goto L_0x03cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x03ca, code lost:
        if ((!kotlin.jvm.internal.Intrinsics.areEqual(r2, (java.lang.Object) r10.element)) == false) goto L_0x03cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x03cd, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x03cf, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x03d0, code lost:
        if (r2 != false) goto L_0x040d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x03d4, code lost:
        if (r13 != androidx.paging.LoadType.PREPEND) goto L_0x03d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x03d6, code lost:
        r0 = "prevKey";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x03d9, code lost:
        r0 = "nextKey";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x040c, code lost:
        throw new java.lang.IllegalStateException(kotlin.text.StringsKt.trimMargin$default("The same value, " + r10.element + ", was passed as the " + r0 + " in two\n                            | sequential Pages loaded from a PagingSource. Re-using load keys in\n                            | PagingSource is often an error, and must be explicitly enabled by\n                            | overriding PagingSource.keyReuseSupported.\n                            ", (java.lang.String) null, 1, (java.lang.Object) null).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x040d, code lost:
        r2 = r9.stateHolder;
        r14 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r2);
        r3.L$0 = r9;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r0;
        r3.L$6 = r8;
        r3.L$7 = r6;
        r3.L$8 = r2;
        r3.L$9 = r14;
        r3.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x042f, code lost:
        if (r14.lock((java.lang.Object) null, r3) != r4) goto L_0x0432;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0431, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0432, code lost:
        r5 = r2;
        r16 = r9;
        r9 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:?, code lost:
        r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5).insert(r12.getGenerationId(), r13, (androidx.paging.PagingSource.LoadResult.Page) r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x044b, code lost:
        r14.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0453, code lost:
        if (r2.booleanValue() != false) goto L_0x0457;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0457, code lost:
        r5 = (androidx.paging.PagingSource.LoadResult.Page) r6;
        r11.element += r5.getData().size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0469, code lost:
        if (r13 != androidx.paging.LoadType.PREPEND) goto L_0x0474;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x046f, code lost:
        if (r5.getPrevKey() == null) goto L_0x0472;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0476, code lost:
        if (r13 != androidx.paging.LoadType.APPEND) goto L_0x0481;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x047c, code lost:
        if (r5.getNextKey() != null) goto L_0x0481;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x047f, code lost:
        r9.element = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0481, code lost:
        r16 = r9;
        r9 = r0;
        r0 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0489, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x048a, code lost:
        r14.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x048e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0491, code lost:
        if ((r6 instanceof androidx.paging.PagingSource.LoadResult.Error) == false) goto L_0x050f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0493, code lost:
        r5 = r9.stateHolder;
        r0 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r5);
        r3.L$0 = r9;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r6;
        r3.L$4 = r5;
        r3.L$5 = r0;
        r3.L$6 = null;
        r3.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x04af, code lost:
        if (r0.lock((java.lang.Object) null, r3) != r4) goto L_0x04b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x04b1, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x04b2, code lost:
        r2 = r4;
        r4 = r0;
        r0 = r3;
        r3 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:?, code lost:
        r5 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5);
        r7 = new androidx.paging.LoadState.Error(((androidx.paging.PagingSource.LoadResult.Error) r6).getThrowable());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x04cc, code lost:
        if (r5.setSourceLoadState(r3, r7) == false) goto L_0x04f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x04ce, code lost:
        r6 = r9.pageEventCh;
        r8 = new androidx.paging.PageEvent.LoadStateUpdate(r3, false, r7);
        r0.L$0 = r3;
        r0.L$1 = r12;
        r0.L$2 = r4;
        r0.L$3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:?, code lost:
        r0.L$4 = null;
        r0.L$5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:?, code lost:
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x04ec, code lost:
        if (r6.send(r8, r0) != r2) goto L_0x04ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x04ee, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x04ef, code lost:
        r0 = r5;
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x04f1, code lost:
        r12 = r5;
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x04f4, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x04f5, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x04f7, code lost:
        r5.getFailedHintsByLoadType$paging_common().put(r3, r12.getHint());
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0504, code lost:
        r4.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x050a, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0519, code lost:
        if (androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$4[r13.ordinal()] == 1) goto L_0x051e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x051b, code lost:
        r2 = androidx.paging.LoadType.PREPEND;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x051e, code lost:
        r2 = androidx.paging.LoadType.APPEND;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0520, code lost:
        r15 = r9.stateHolder;
        r5 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r15);
        r3.L$0 = r9;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r0;
        r3.L$6 = r8;
        r3.L$7 = r6;
        r3.L$8 = r2;
        r3.L$9 = r15;
        r3.L$10 = r5;
        r3.label = 8;
        r18 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0547, code lost:
        if (r5.lock((java.lang.Object) null, r3) != r4) goto L_0x054a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0549, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x054a, code lost:
        r14 = r12;
        r12 = r10;
        r10 = r8;
        r8 = r6;
        r6 = r2;
        r2 = r5;
        r5 = r15;
        r15 = r13;
        r13 = r11;
        r11 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:?, code lost:
        r0 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5);
        r5 = r0.dropEventOrNull(r6, r14.getHint());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0561, code lost:
        if (r5 == 0) goto L_0x05a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0563, code lost:
        r0.drop(r5);
        r6 = r9.pageEventCh;
        r3.L$0 = r9;
        r3.L$1 = r15;
        r3.L$2 = r14;
        r3.L$3 = r13;
        r3.L$4 = r12;
        r3.L$5 = r11;
        r3.L$6 = r10;
        r3.L$7 = r8;
        r3.L$8 = r2;
        r3.L$9 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x057c, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x057d, code lost:
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:?, code lost:
        r3.L$10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:?, code lost:
        r3.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0587, code lost:
        if (r6.send(r5, r3) != r4) goto L_0x058a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0589, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x058a, code lost:
        r5 = r2;
        r6 = r8;
        r8 = r10;
        r10 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r14;
        r14 = r15;
        r15 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0593, code lost:
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:?, code lost:
        r1 = kotlin.Unit.INSTANCE;
        r9 = r15;
        r15 = r14;
        r14 = r13;
        r13 = r12;
        r12 = r11;
        r11 = r10;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x05a0, code lost:
        r5 = r2;
        r6 = r8;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x05a3, code lost:
        r12.element = r9.nextLoadKeyOrNull(r0, r15, r14.getGenerationId(), r14.presentedItemsBeyondAnchor$paging_common(r15) + r13.element);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x05b6, code lost:
        if (r12.element != null) goto L_0x05da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x05c2, code lost:
        if ((r0.getSourceLoadStates$paging_common().get$paging_common(r15) instanceof androidx.paging.LoadState.Error) != false) goto L_0x05da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x05c6, code lost:
        if (r11.element == false) goto L_0x05cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x05c8, code lost:
        r1 = androidx.paging.LoadState.NotLoading.Companion.getComplete$paging_common();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x05cf, code lost:
        r1 = androidx.paging.LoadState.NotLoading.Companion.getIncomplete$paging_common();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00c0, code lost:
        r1 = null;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x05d5, code lost:
        r0.setSourceLoadState(r15, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x05da, code lost:
        r0 = r0.toPageEvent$paging_common((androidx.paging.PagingSource.LoadResult.Page) r6, r15);
        r1 = r9.pageEventCh;
        r3.L$0 = r9;
        r3.L$1 = r15;
        r3.L$2 = r14;
        r3.L$3 = r13;
        r3.L$4 = r12;
        r3.L$5 = r11;
        r3.L$6 = r8;
        r3.L$7 = r6;
        r3.L$8 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:?, code lost:
        r3.L$9 = null;
        r3.L$10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x05fc, code lost:
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:?, code lost:
        r3.label = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0602, code lost:
        if (r1.send(r0, r3) != r4) goto L_0x0605;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0604, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0605, code lost:
        r0 = r6;
        r6 = r8;
        r10 = r9;
        r8 = r14;
        r14 = r12;
        r16 = r13;
        r13 = r11;
        r11 = r15;
        r15 = r16;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0610, code lost:
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0612, code lost:
        r5.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0618, code lost:
        if ((r6 instanceof androidx.paging.PagingSource.LoadParams.Prepend) == false) goto L_0x0625;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0621, code lost:
        if (((androidx.paging.PagingSource.LoadResult.Page) r0).getPrevKey() != null) goto L_0x0625;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0623, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0625, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0628, code lost:
        if ((r6 instanceof androidx.paging.PagingSource.LoadParams.Append) == false) goto L_0x0634;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0630, code lost:
        if (((androidx.paging.PagingSource.LoadResult.Page) r0).getNextKey() != null) goto L_0x0634;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0632, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0634, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0637, code lost:
        if (r10.remoteMediatorConnection == null) goto L_0x0684;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0639, code lost:
        if (r5 != 0) goto L_0x063d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x063b, code lost:
        if (r0 == 0) goto L_0x0684;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x063d, code lost:
        r12 = r10.stateHolder;
        r6 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r12);
        r3.L$0 = r10;
        r3.L$1 = r11;
        r3.L$2 = r8;
        r3.L$3 = r15;
        r3.L$4 = r14;
        r3.L$5 = r13;
        r3.L$6 = r12;
        r3.L$7 = r6;
        r1 = null;
        r3.L$8 = r1;
        r3.I$0 = r5;
        r3.I$1 = r0;
        r3.label = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0662, code lost:
        if (r6.lock(r1, r3) != r4) goto L_0x0665;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0664, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:?, code lost:
        r2 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r12).currentPagingState$paging_common(r10.lastHint);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x0672, code lost:
        if (r5 == 0) goto L_0x067b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x0674, code lost:
        r10.remoteMediatorConnection.requestLoad(androidx.paging.LoadType.PREPEND, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x067b, code lost:
        if (r0 == 0) goto L_0x0684;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x067d, code lost:
        r10.remoteMediatorConnection.requestLoad(androidx.paging.LoadType.APPEND, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0684, code lost:
        r12 = r10;
        r0 = r13;
        r2 = r14;
        r9 = r15;
        r10 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x068a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x068b, code lost:
        r6.unlock(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x068e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x068f, code lost:
        r1 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0693, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0694, code lost:
        r1 = null;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x0696, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0697, code lost:
        r5 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x010a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x06a0, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x06a1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x06a2, code lost:
        r6 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x06a9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x06aa, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x06ae, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x010b, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x015e, code lost:
        r0 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r5 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5);
        r10 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$2[r0.ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x023b, code lost:
        if (r10 == 1) goto L_0x0290;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x023e, code lost:
        if (r10 == 2) goto L_0x024c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0240, code lost:
        if (r10 == 3) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x024b, code lost:
        throw new java.lang.IllegalStateException("Use doInitialLoad for LoadType == REFRESH");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x024c, code lost:
        r10 = (r5.getInitialPageIndex$paging_common() + r9.getHint().getOriginalPageOffsetLast()) + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x025b, code lost:
        if (r10 >= 0) goto L_0x026a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x025d, code lost:
        r8.element += r11.config.pageSize * (-r10);
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x026a, code lost:
        r12 = kotlin.collections.CollectionsKt.getLastIndex(r5.getPages$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0272, code lost:
        if (r10 > r12) goto L_0x02e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0274, code lost:
        r8.element += ((androidx.paging.PagingSource.LoadResult.Page) r5.getPages$paging_common().get(r10)).getData().size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x028b, code lost:
        if (r10 == r12) goto L_0x02e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x028d, code lost:
        r10 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0290, code lost:
        r10 = (r5.getInitialPageIndex$paging_common() + r9.getHint().getOriginalPageOffsetFirst()) - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x02a7, code lost:
        if (r10 <= kotlin.collections.CollectionsKt.getLastIndex(r5.getPages$paging_common())) goto L_0x02c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x02a9, code lost:
        r8.element += r11.config.pageSize * (r10 - kotlin.collections.CollectionsKt.getLastIndex(r5.getPages$paging_common()));
        r10 = kotlin.collections.CollectionsKt.getLastIndex(r5.getPages$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x02c5, code lost:
        if (r10 < 0) goto L_0x02e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x02c7, code lost:
        r12 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x02c8, code lost:
        r8.element += ((androidx.paging.PagingSource.LoadResult.Page) r5.getPages$paging_common().get(r12)).getData().size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x02df, code lost:
        if (r12 == r10) goto L_0x02e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x02e1, code lost:
        r12 = r12 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x02e4, code lost:
        r5 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x02e6, code lost:
        r2.unlock((java.lang.Object) null);
        r2 = new kotlin.jvm.internal.Ref.ObjectRef();
        r5 = r11.stateHolder;
        r10 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r5);
        r3.L$0 = r11;
        r3.L$1 = r0;
        r3.L$2 = r9;
        r3.L$3 = r8;
        r3.L$4 = r2;
        r3.L$5 = r5;
        r3.L$6 = r10;
        r3.L$7 = r2;
        r3.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x030d, code lost:
        if (r10.lock((java.lang.Object) null, r3) != r4) goto L_0x0310;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x030f, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0310, code lost:
        r12 = r11;
        r11 = r0;
        r0 = r2;
        r16 = r8;
        r8 = r5;
        r5 = r10;
        r10 = r9;
        r9 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r8 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r8);
        r13 = r12.nextLoadKeyOrNull(r8, r11, r10.getGenerationId(), r10.presentedItemsBeyondAnchor$paging_common(r11) + r9.element);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x032d, code lost:
        if (r13 == null) goto L_0x034e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x032f, code lost:
        r3.L$0 = r12;
        r3.L$1 = r11;
        r3.L$2 = r10;
        r3.L$3 = r9;
        r3.L$4 = r2;
        r3.L$5 = r5;
        r3.L$6 = r13;
        r3.L$7 = r0;
        r3.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0345, code lost:
        if (r12.setLoading(r8, r11, r3) != r4) goto L_0x0348;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0347, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0348, code lost:
        r8 = r2;
        r6 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x034a, code lost:
        r5 = r6;
        r2 = r8;
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x034e, code lost:
        r6 = null;
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0350, code lost:
        r5.unlock(r6);
        r0.element = r13;
        r0 = new kotlin.jvm.internal.Ref.BooleanRef();
        r0.element = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x035f, code lost:
        if (r2.element == null) goto L_0x069e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0361, code lost:
        r5 = r12.loadParams(r11, r2.element);
        r6 = r12.pagingSource;
        r3.L$0 = r12;
        r3.L$1 = r11;
        r3.L$2 = r10;
        r3.L$3 = r9;
        r3.L$4 = r2;
        r3.L$5 = r0;
        r3.L$6 = r5;
        r3.L$7 = null;
        r3.L$8 = null;
        r3.label = 4;
        r6 = r6.load(r5, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0383, code lost:
        if (r6 != r4) goto L_0x0386;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0385, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0386, code lost:
        r8 = r5;
        r13 = r11;
        r11 = r9;
        r9 = r12;
        r12 = r10;
        r10 = r2;
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x038d, code lost:
        r6 = (androidx.paging.PagingSource.LoadResult) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0392, code lost:
        if ((r6 instanceof androidx.paging.PagingSource.LoadResult.Page) == false) goto L_0x048f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0394, code lost:
        r2 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$3[r13.ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x039d, code lost:
        if (r2 == 1) goto L_0x03b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x03a0, code lost:
        if (r2 != 2) goto L_0x03aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x03a2, code lost:
        r2 = ((androidx.paging.PagingSource.LoadResult.Page) r6).getNextKey();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:22:0x0105, B:149:0x04e1] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object doLoad(androidx.paging.LoadType r18, androidx.paging.GenerationalViewportHint r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r20
            boolean r3 = r2 instanceof androidx.paging.PageFetcherSnapshot$doLoad$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            androidx.paging.PageFetcherSnapshot$doLoad$1 r3 = (androidx.paging.PageFetcherSnapshot$doLoad$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            androidx.paging.PageFetcherSnapshot$doLoad$1 r3 = new androidx.paging.PageFetcherSnapshot$doLoad$1
            r3.<init>(r1, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 3
            java.lang.String r7 = "Use doInitialLoad for LoadType == REFRESH"
            switch(r5) {
                case 0: goto L_0x01fa;
                case 1: goto L_0x01dc;
                case 2: goto L_0x01b2;
                case 3: goto L_0x018a;
                case 4: goto L_0x0162;
                case 5: goto L_0x0130;
                case 6: goto L_0x010e;
                case 7: goto L_0x00f5;
                case 8: goto L_0x00c3;
                case 9: goto L_0x0092;
                case 10: goto L_0x005f;
                case 11: goto L_0x0035;
                default: goto L_0x002d;
            }
        L_0x002d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0035:
            int r0 = r3.I$1
            int r5 = r3.I$0
            java.lang.Object r6 = r3.L$7
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r12 = r3.L$6
            androidx.paging.PageFetcherSnapshotState$Holder r12 = (androidx.paging.PageFetcherSnapshotState.Holder) r12
            java.lang.Object r13 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r13 = (kotlin.jvm.internal.Ref.BooleanRef) r13
            java.lang.Object r14 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r14 = (kotlin.jvm.internal.Ref.ObjectRef) r14
            java.lang.Object r15 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r15 = (kotlin.jvm.internal.Ref.IntRef) r15
            java.lang.Object r8 = r3.L$2
            androidx.paging.GenerationalViewportHint r8 = (androidx.paging.GenerationalViewportHint) r8
            java.lang.Object r11 = r3.L$1
            androidx.paging.LoadType r11 = (androidx.paging.LoadType) r11
            java.lang.Object r10 = r3.L$0
            androidx.paging.PageFetcherSnapshot r10 = (androidx.paging.PageFetcherSnapshot) r10
            kotlin.ResultKt.throwOnFailure(r2)
            r1 = 0
            goto L_0x0665
        L_0x005f:
            java.lang.Object r0 = r3.L$8
            r5 = r0
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r0 = r3.L$7
            androidx.paging.PagingSource$LoadResult r0 = (androidx.paging.PagingSource.LoadResult) r0
            java.lang.Object r6 = r3.L$6
            androidx.paging.PagingSource$LoadParams r6 = (androidx.paging.PagingSource.LoadParams) r6
            java.lang.Object r8 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r8 = (kotlin.jvm.internal.Ref.BooleanRef) r8
            java.lang.Object r10 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r3.L$2
            androidx.paging.GenerationalViewportHint r12 = (androidx.paging.GenerationalViewportHint) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.LoadType r13 = (androidx.paging.LoadType) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.PageFetcherSnapshot r14 = (androidx.paging.PageFetcherSnapshot) r14
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x00bf }
            r15 = r11
            r11 = r13
            r13 = r8
            r8 = r12
            r16 = r14
            r14 = r10
            r10 = r16
            goto L_0x0610
        L_0x0092:
            java.lang.Object r0 = r3.L$9
            androidx.paging.PageFetcherSnapshotState r0 = (androidx.paging.PageFetcherSnapshotState) r0
            java.lang.Object r5 = r3.L$8
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r3.L$7
            androidx.paging.PagingSource$LoadResult r6 = (androidx.paging.PagingSource.LoadResult) r6
            java.lang.Object r8 = r3.L$6
            androidx.paging.PagingSource$LoadParams r8 = (androidx.paging.PagingSource.LoadParams) r8
            java.lang.Object r10 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r12 = (kotlin.jvm.internal.Ref.IntRef) r12
            java.lang.Object r13 = r3.L$2
            androidx.paging.GenerationalViewportHint r13 = (androidx.paging.GenerationalViewportHint) r13
            java.lang.Object r14 = r3.L$1
            androidx.paging.LoadType r14 = (androidx.paging.LoadType) r14
            java.lang.Object r15 = r3.L$0
            androidx.paging.PageFetcherSnapshot r15 = (androidx.paging.PageFetcherSnapshot) r15
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x00bf }
            goto L_0x0593
        L_0x00bf:
            r0 = move-exception
        L_0x00c0:
            r1 = 0
            goto L_0x069a
        L_0x00c3:
            java.lang.Object r0 = r3.L$10
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r5 = r3.L$9
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r3.L$8
            androidx.paging.LoadType r6 = (androidx.paging.LoadType) r6
            java.lang.Object r8 = r3.L$7
            androidx.paging.PagingSource$LoadResult r8 = (androidx.paging.PagingSource.LoadResult) r8
            java.lang.Object r10 = r3.L$6
            androidx.paging.PagingSource$LoadParams r10 = (androidx.paging.PagingSource.LoadParams) r10
            java.lang.Object r11 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r11 = (kotlin.jvm.internal.Ref.BooleanRef) r11
            java.lang.Object r12 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r13 = (kotlin.jvm.internal.Ref.IntRef) r13
            java.lang.Object r14 = r3.L$2
            androidx.paging.GenerationalViewportHint r14 = (androidx.paging.GenerationalViewportHint) r14
            java.lang.Object r15 = r3.L$1
            androidx.paging.LoadType r15 = (androidx.paging.LoadType) r15
            java.lang.Object r9 = r3.L$0
            androidx.paging.PageFetcherSnapshot r9 = (androidx.paging.PageFetcherSnapshot) r9
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r0
            goto L_0x0555
        L_0x00f5:
            java.lang.Object r0 = r3.L$3
            androidx.paging.PageFetcherSnapshotState r0 = (androidx.paging.PageFetcherSnapshotState) r0
            java.lang.Object r4 = r3.L$2
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            java.lang.Object r5 = r3.L$1
            androidx.paging.GenerationalViewportHint r5 = (androidx.paging.GenerationalViewportHint) r5
            java.lang.Object r3 = r3.L$0
            androidx.paging.LoadType r3 = (androidx.paging.LoadType) r3
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x010a }
            goto L_0x04f1
        L_0x010a:
            r0 = move-exception
            r2 = 0
            goto L_0x050b
        L_0x010e:
            java.lang.Object r0 = r3.L$5
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r5 = r3.L$4
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r3.L$3
            androidx.paging.PagingSource$LoadResult r6 = (androidx.paging.PagingSource.LoadResult) r6
            java.lang.Object r7 = r3.L$2
            androidx.paging.GenerationalViewportHint r7 = (androidx.paging.GenerationalViewportHint) r7
            java.lang.Object r8 = r3.L$1
            androidx.paging.LoadType r8 = (androidx.paging.LoadType) r8
            java.lang.Object r9 = r3.L$0
            androidx.paging.PageFetcherSnapshot r9 = (androidx.paging.PageFetcherSnapshot) r9
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r4
            r12 = r7
            r4 = r0
            r0 = r3
            r3 = r8
            goto L_0x04b6
        L_0x0130:
            java.lang.Object r0 = r3.L$9
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r5 = r3.L$8
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r3.L$7
            androidx.paging.PagingSource$LoadResult r6 = (androidx.paging.PagingSource.LoadResult) r6
            java.lang.Object r8 = r3.L$6
            androidx.paging.PagingSource$LoadParams r8 = (androidx.paging.PagingSource.LoadParams) r8
            java.lang.Object r9 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r10 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r3.L$2
            androidx.paging.GenerationalViewportHint r12 = (androidx.paging.GenerationalViewportHint) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.LoadType r13 = (androidx.paging.LoadType) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.PageFetcherSnapshot r14 = (androidx.paging.PageFetcherSnapshot) r14
            kotlin.ResultKt.throwOnFailure(r2)
            r16 = r14
            r14 = r0
        L_0x015e:
            r0 = r16
            goto L_0x0438
        L_0x0162:
            java.lang.Object r0 = r3.L$6
            androidx.paging.PagingSource$LoadParams r0 = (androidx.paging.PagingSource.LoadParams) r0
            java.lang.Object r5 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r5 = (kotlin.jvm.internal.Ref.BooleanRef) r5
            java.lang.Object r6 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6
            java.lang.Object r8 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r8 = (kotlin.jvm.internal.Ref.IntRef) r8
            java.lang.Object r9 = r3.L$2
            androidx.paging.GenerationalViewportHint r9 = (androidx.paging.GenerationalViewportHint) r9
            java.lang.Object r10 = r3.L$1
            androidx.paging.LoadType r10 = (androidx.paging.LoadType) r10
            java.lang.Object r11 = r3.L$0
            androidx.paging.PageFetcherSnapshot r11 = (androidx.paging.PageFetcherSnapshot) r11
            kotlin.ResultKt.throwOnFailure(r2)
            r12 = r9
            r13 = r10
            r9 = r11
            r10 = r6
            r11 = r8
            r8 = r0
            r0 = r5
            goto L_0x038d
        L_0x018a:
            java.lang.Object r0 = r3.L$7
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            java.lang.Object r5 = r3.L$6
            java.lang.Object r6 = r3.L$5
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r8 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r9 = (kotlin.jvm.internal.Ref.IntRef) r9
            java.lang.Object r10 = r3.L$2
            androidx.paging.GenerationalViewportHint r10 = (androidx.paging.GenerationalViewportHint) r10
            java.lang.Object r11 = r3.L$1
            androidx.paging.LoadType r11 = (androidx.paging.LoadType) r11
            java.lang.Object r12 = r3.L$0
            androidx.paging.PageFetcherSnapshot r12 = (androidx.paging.PageFetcherSnapshot) r12
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x01ae }
            r13 = r5
            goto L_0x034a
        L_0x01ae:
            r0 = move-exception
        L_0x01af:
            r1 = 0
            goto L_0x06a5
        L_0x01b2:
            java.lang.Object r0 = r3.L$7
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            java.lang.Object r5 = r3.L$6
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r8 = r3.L$5
            androidx.paging.PageFetcherSnapshotState$Holder r8 = (androidx.paging.PageFetcherSnapshotState.Holder) r8
            java.lang.Object r9 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9
            java.lang.Object r10 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r3.L$2
            androidx.paging.GenerationalViewportHint r11 = (androidx.paging.GenerationalViewportHint) r11
            java.lang.Object r12 = r3.L$1
            androidx.paging.LoadType r12 = (androidx.paging.LoadType) r12
            java.lang.Object r13 = r3.L$0
            androidx.paging.PageFetcherSnapshot r13 = (androidx.paging.PageFetcherSnapshot) r13
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r13
            goto L_0x031a
        L_0x01dc:
            java.lang.Object r0 = r3.L$5
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r5 = r3.L$4
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r8 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r8 = (kotlin.jvm.internal.Ref.IntRef) r8
            java.lang.Object r9 = r3.L$2
            androidx.paging.GenerationalViewportHint r9 = (androidx.paging.GenerationalViewportHint) r9
            java.lang.Object r10 = r3.L$1
            androidx.paging.LoadType r10 = (androidx.paging.LoadType) r10
            java.lang.Object r11 = r3.L$0
            androidx.paging.PageFetcherSnapshot r11 = (androidx.paging.PageFetcherSnapshot) r11
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r0
            r0 = r10
            goto L_0x022e
        L_0x01fa:
            kotlin.ResultKt.throwOnFailure(r2)
            androidx.paging.LoadType r2 = androidx.paging.LoadType.REFRESH
            if (r0 == r2) goto L_0x0203
            r2 = 1
            goto L_0x0204
        L_0x0203:
            r2 = 0
        L_0x0204:
            if (r2 == 0) goto L_0x06af
            kotlin.jvm.internal.Ref$IntRef r8 = new kotlin.jvm.internal.Ref$IntRef
            r8.<init>()
            r2 = 0
            r8.element = r2
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r1.stateHolder
            kotlinx.coroutines.sync.Mutex r2 = r5.lock
            r3.L$0 = r1
            r3.L$1 = r0
            r9 = r19
            r3.L$2 = r9
            r3.L$3 = r8
            r3.L$4 = r5
            r3.L$5 = r2
            r10 = 1
            r3.label = r10
            r10 = 0
            java.lang.Object r11 = r2.lock(r10, r3)
            if (r11 != r4) goto L_0x022d
            return r4
        L_0x022d:
            r11 = r1
        L_0x022e:
            androidx.paging.PageFetcherSnapshotState r5 = r5.state     // Catch:{ all -> 0x06a9 }
            int[] r10 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$2     // Catch:{ all -> 0x06a9 }
            int r12 = r0.ordinal()     // Catch:{ all -> 0x06a9 }
            r10 = r10[r12]     // Catch:{ all -> 0x06a9 }
            r12 = 1
            if (r10 == r12) goto L_0x0290
            r12 = 2
            if (r10 == r12) goto L_0x024c
            if (r10 == r6) goto L_0x0244
            goto L_0x02e4
        L_0x0244:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x06a9 }
            r0.<init>(r7)     // Catch:{ all -> 0x06a9 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x06a9 }
            throw r0     // Catch:{ all -> 0x06a9 }
        L_0x024c:
            int r10 = r5.getInitialPageIndex$paging_common()     // Catch:{ all -> 0x06a9 }
            androidx.paging.ViewportHint r12 = r9.getHint()     // Catch:{ all -> 0x06a9 }
            int r12 = r12.getOriginalPageOffsetLast()     // Catch:{ all -> 0x06a9 }
            int r10 = r10 + r12
            r12 = 1
            int r10 = r10 + r12
            if (r10 >= 0) goto L_0x026a
            int r12 = r8.element     // Catch:{ all -> 0x06a9 }
            androidx.paging.PagingConfig r13 = r11.config     // Catch:{ all -> 0x06a9 }
            int r13 = r13.pageSize     // Catch:{ all -> 0x06a9 }
            int r10 = -r10
            int r13 = r13 * r10
            int r12 = r12 + r13
            r8.element = r12     // Catch:{ all -> 0x06a9 }
            r10 = 0
        L_0x026a:
            java.util.List r12 = r5.getPages$paging_common()     // Catch:{ all -> 0x06a9 }
            int r12 = kotlin.collections.CollectionsKt.getLastIndex(r12)     // Catch:{ all -> 0x06a9 }
            if (r10 > r12) goto L_0x02e4
        L_0x0274:
            int r13 = r8.element     // Catch:{ all -> 0x06a9 }
            java.util.List r14 = r5.getPages$paging_common()     // Catch:{ all -> 0x06a9 }
            java.lang.Object r14 = r14.get(r10)     // Catch:{ all -> 0x06a9 }
            androidx.paging.PagingSource$LoadResult$Page r14 = (androidx.paging.PagingSource.LoadResult.Page) r14     // Catch:{ all -> 0x06a9 }
            java.util.List r14 = r14.getData()     // Catch:{ all -> 0x06a9 }
            int r14 = r14.size()     // Catch:{ all -> 0x06a9 }
            int r13 = r13 + r14
            r8.element = r13     // Catch:{ all -> 0x06a9 }
            if (r10 == r12) goto L_0x02e4
            int r10 = r10 + 1
            goto L_0x0274
        L_0x0290:
            int r10 = r5.getInitialPageIndex$paging_common()     // Catch:{ all -> 0x06a9 }
            androidx.paging.ViewportHint r12 = r9.getHint()     // Catch:{ all -> 0x06a9 }
            int r12 = r12.getOriginalPageOffsetFirst()     // Catch:{ all -> 0x06a9 }
            int r10 = r10 + r12
            r12 = 1
            int r10 = r10 - r12
            java.util.List r12 = r5.getPages$paging_common()     // Catch:{ all -> 0x06a9 }
            int r12 = kotlin.collections.CollectionsKt.getLastIndex(r12)     // Catch:{ all -> 0x06a9 }
            if (r10 <= r12) goto L_0x02c5
            int r12 = r8.element     // Catch:{ all -> 0x06a9 }
            androidx.paging.PagingConfig r13 = r11.config     // Catch:{ all -> 0x06a9 }
            int r13 = r13.pageSize     // Catch:{ all -> 0x06a9 }
            java.util.List r14 = r5.getPages$paging_common()     // Catch:{ all -> 0x06a9 }
            int r14 = kotlin.collections.CollectionsKt.getLastIndex(r14)     // Catch:{ all -> 0x06a9 }
            int r10 = r10 - r14
            int r13 = r13 * r10
            int r12 = r12 + r13
            r8.element = r12     // Catch:{ all -> 0x06a9 }
            java.util.List r10 = r5.getPages$paging_common()     // Catch:{ all -> 0x06a9 }
            int r10 = kotlin.collections.CollectionsKt.getLastIndex(r10)     // Catch:{ all -> 0x06a9 }
        L_0x02c5:
            if (r10 < 0) goto L_0x02e4
            r12 = 0
        L_0x02c8:
            int r13 = r8.element     // Catch:{ all -> 0x06a9 }
            java.util.List r14 = r5.getPages$paging_common()     // Catch:{ all -> 0x06a9 }
            java.lang.Object r14 = r14.get(r12)     // Catch:{ all -> 0x06a9 }
            androidx.paging.PagingSource$LoadResult$Page r14 = (androidx.paging.PagingSource.LoadResult.Page) r14     // Catch:{ all -> 0x06a9 }
            java.util.List r14 = r14.getData()     // Catch:{ all -> 0x06a9 }
            int r14 = r14.size()     // Catch:{ all -> 0x06a9 }
            int r13 = r13 + r14
            r8.element = r13     // Catch:{ all -> 0x06a9 }
            if (r12 == r10) goto L_0x02e4
            int r12 = r12 + 1
            goto L_0x02c8
        L_0x02e4:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x06a9 }
            r5 = 0
            r2.unlock(r5)
            kotlin.jvm.internal.Ref$ObjectRef r2 = new kotlin.jvm.internal.Ref$ObjectRef
            r2.<init>()
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r11.stateHolder
            kotlinx.coroutines.sync.Mutex r10 = r5.lock
            r3.L$0 = r11
            r3.L$1 = r0
            r3.L$2 = r9
            r3.L$3 = r8
            r3.L$4 = r2
            r3.L$5 = r5
            r3.L$6 = r10
            r3.L$7 = r2
            r12 = 2
            r3.label = r12
            r12 = 0
            java.lang.Object r13 = r10.lock(r12, r3)
            if (r13 != r4) goto L_0x0310
            return r4
        L_0x0310:
            r12 = r11
            r11 = r0
            r0 = r2
            r16 = r8
            r8 = r5
            r5 = r10
            r10 = r9
            r9 = r16
        L_0x031a:
            androidx.paging.PageFetcherSnapshotState r8 = r8.state     // Catch:{ all -> 0x06a1 }
            int r13 = r10.getGenerationId()     // Catch:{ all -> 0x06a1 }
            int r14 = r10.presentedItemsBeyondAnchor$paging_common(r11)     // Catch:{ all -> 0x06a1 }
            int r15 = r9.element     // Catch:{ all -> 0x06a1 }
            int r14 = r14 + r15
            java.lang.Object r13 = r12.nextLoadKeyOrNull(r8, r11, r13, r14)     // Catch:{ all -> 0x06a1 }
            if (r13 == 0) goto L_0x034e
            r3.L$0 = r12     // Catch:{ all -> 0x06a1 }
            r3.L$1 = r11     // Catch:{ all -> 0x06a1 }
            r3.L$2 = r10     // Catch:{ all -> 0x06a1 }
            r3.L$3 = r9     // Catch:{ all -> 0x06a1 }
            r3.L$4 = r2     // Catch:{ all -> 0x06a1 }
            r3.L$5 = r5     // Catch:{ all -> 0x06a1 }
            r3.L$6 = r13     // Catch:{ all -> 0x06a1 }
            r3.L$7 = r0     // Catch:{ all -> 0x06a1 }
            r3.label = r6     // Catch:{ all -> 0x06a1 }
            java.lang.Object r6 = r12.setLoading(r8, r11, r3)     // Catch:{ all -> 0x06a1 }
            if (r6 != r4) goto L_0x0348
            return r4
        L_0x0348:
            r8 = r2
            r6 = r5
        L_0x034a:
            r5 = r6
            r2 = r8
            r6 = 0
            goto L_0x0350
        L_0x034e:
            r6 = 0
            r13 = 0
        L_0x0350:
            r5.unlock(r6)
            r0.element = r13
            kotlin.jvm.internal.Ref$BooleanRef r0 = new kotlin.jvm.internal.Ref$BooleanRef
            r0.<init>()
            r5 = 0
            r0.element = r5
        L_0x035d:
            T r5 = r2.element
            if (r5 == 0) goto L_0x069e
            T r5 = r2.element
            androidx.paging.PagingSource$LoadParams r5 = r12.loadParams(r11, r5)
            androidx.paging.PagingSource<Key, Value> r6 = r12.pagingSource
            r3.L$0 = r12
            r3.L$1 = r11
            r3.L$2 = r10
            r3.L$3 = r9
            r3.L$4 = r2
            r3.L$5 = r0
            r3.L$6 = r5
            r8 = 0
            r3.L$7 = r8
            r3.L$8 = r8
            r8 = 4
            r3.label = r8
            java.lang.Object r6 = r6.load(r5, r3)
            if (r6 != r4) goto L_0x0386
            return r4
        L_0x0386:
            r8 = r5
            r13 = r11
            r11 = r9
            r9 = r12
            r12 = r10
            r10 = r2
            r2 = r6
        L_0x038d:
            r6 = r2
            androidx.paging.PagingSource$LoadResult r6 = (androidx.paging.PagingSource.LoadResult) r6
            boolean r2 = r6 instanceof androidx.paging.PagingSource.LoadResult.Page
            if (r2 == 0) goto L_0x048f
            int[] r2 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$3
            int r5 = r13.ordinal()
            r2 = r2[r5]
            r5 = 1
            if (r2 == r5) goto L_0x03b2
            r5 = 2
            if (r2 != r5) goto L_0x03aa
            r2 = r6
            androidx.paging.PagingSource$LoadResult$Page r2 = (androidx.paging.PagingSource.LoadResult.Page) r2
            java.lang.Object r2 = r2.getNextKey()
            goto L_0x03ba
        L_0x03aa:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r7)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x03b2:
            r5 = 2
            r2 = r6
            androidx.paging.PagingSource$LoadResult$Page r2 = (androidx.paging.PagingSource.LoadResult.Page) r2
            java.lang.Object r2 = r2.getPrevKey()
        L_0x03ba:
            androidx.paging.PagingSource<Key, Value> r14 = r9.pagingSource
            boolean r14 = r14.getKeyReuseSupported()
            if (r14 != 0) goto L_0x03cf
            T r14 = r10.element
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r14)
            r14 = 1
            r2 = r2 ^ r14
            if (r2 == 0) goto L_0x03cd
            goto L_0x03cf
        L_0x03cd:
            r2 = 0
            goto L_0x03d0
        L_0x03cf:
            r2 = 1
        L_0x03d0:
            if (r2 != 0) goto L_0x040d
            androidx.paging.LoadType r0 = androidx.paging.LoadType.PREPEND
            if (r13 != r0) goto L_0x03d9
            java.lang.String r0 = "prevKey"
            goto L_0x03db
        L_0x03d9:
            java.lang.String r0 = "nextKey"
        L_0x03db:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "The same value, "
            r2.append(r3)
            T r3 = r10.element
            r2.append(r3)
            java.lang.String r3 = ", was passed as the "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = " in two\n                            | sequential Pages loaded from a PagingSource. Re-using load keys in\n                            | PagingSource is often an error, and must be explicitly enabled by\n                            | overriding PagingSource.keyReuseSupported.\n                            "
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r2 = 1
            r3 = 0
            java.lang.String r0 = kotlin.text.StringsKt.trimMargin$default(r0, r3, r2, r3)
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x040d:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r2 = r9.stateHolder
            kotlinx.coroutines.sync.Mutex r14 = r2.lock
            r3.L$0 = r9
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r0
            r3.L$6 = r8
            r3.L$7 = r6
            r3.L$8 = r2
            r3.L$9 = r14
            r15 = 5
            r3.label = r15
            r15 = 0
            java.lang.Object r5 = r14.lock(r15, r3)
            if (r5 != r4) goto L_0x0432
            return r4
        L_0x0432:
            r5 = r2
            r16 = r9
            r9 = r0
            goto L_0x015e
        L_0x0438:
            androidx.paging.PageFetcherSnapshotState r2 = r5.state     // Catch:{ all -> 0x0489 }
            int r5 = r12.getGenerationId()     // Catch:{ all -> 0x0489 }
            r15 = r6
            androidx.paging.PagingSource$LoadResult$Page r15 = (androidx.paging.PagingSource.LoadResult.Page) r15     // Catch:{ all -> 0x0489 }
            boolean r2 = r2.insert(r5, r13, r15)     // Catch:{ all -> 0x0489 }
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)     // Catch:{ all -> 0x0489 }
            r5 = 0
            r14.unlock(r5)
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L_0x0457
            goto L_0x069e
        L_0x0457:
            int r2 = r11.element
            r5 = r6
            androidx.paging.PagingSource$LoadResult$Page r5 = (androidx.paging.PagingSource.LoadResult.Page) r5
            java.util.List r14 = r5.getData()
            int r14 = r14.size()
            int r2 = r2 + r14
            r11.element = r2
            androidx.paging.LoadType r2 = androidx.paging.LoadType.PREPEND
            if (r13 != r2) goto L_0x0474
            java.lang.Object r2 = r5.getPrevKey()
            if (r2 == 0) goto L_0x0472
            goto L_0x0474
        L_0x0472:
            r2 = 1
            goto L_0x047f
        L_0x0474:
            androidx.paging.LoadType r2 = androidx.paging.LoadType.APPEND
            if (r13 != r2) goto L_0x0481
            java.lang.Object r2 = r5.getNextKey()
            if (r2 != 0) goto L_0x0481
            goto L_0x0472
        L_0x047f:
            r9.element = r2
        L_0x0481:
            r14 = 0
            r16 = r9
            r9 = r0
            r0 = r16
            goto L_0x0510
        L_0x0489:
            r0 = move-exception
            r2 = 0
            r14.unlock(r2)
            throw r0
        L_0x048f:
            boolean r2 = r6 instanceof androidx.paging.PagingSource.LoadResult.Error
            if (r2 == 0) goto L_0x050f
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r9.stateHolder
            kotlinx.coroutines.sync.Mutex r0 = r5.lock
            r3.L$0 = r9
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r6
            r3.L$4 = r5
            r3.L$5 = r0
            r2 = 0
            r3.L$6 = r2
            r7 = 6
            r3.label = r7
            java.lang.Object r7 = r0.lock(r2, r3)
            if (r7 != r4) goto L_0x04b2
            return r4
        L_0x04b2:
            r2 = r4
            r4 = r0
            r0 = r3
            r3 = r13
        L_0x04b6:
            androidx.paging.PageFetcherSnapshotState r5 = r5.state     // Catch:{ all -> 0x010a }
            androidx.paging.LoadState$Error r7 = new androidx.paging.LoadState$Error     // Catch:{ all -> 0x010a }
            androidx.paging.PagingSource$LoadResult$Error r6 = (androidx.paging.PagingSource.LoadResult.Error) r6     // Catch:{ all -> 0x010a }
            java.lang.Throwable r6 = r6.getThrowable()     // Catch:{ all -> 0x010a }
            r7.<init>(r6)     // Catch:{ all -> 0x010a }
            r6 = r7
            androidx.paging.LoadState r6 = (androidx.paging.LoadState) r6     // Catch:{ all -> 0x010a }
            boolean r6 = r5.setSourceLoadState(r3, r6)     // Catch:{ all -> 0x010a }
            if (r6 == 0) goto L_0x04f7
            kotlinx.coroutines.channels.Channel<androidx.paging.PageEvent<Value>> r6 = r9.pageEventCh     // Catch:{ all -> 0x010a }
            androidx.paging.PageEvent$LoadStateUpdate r8 = new androidx.paging.PageEvent$LoadStateUpdate     // Catch:{ all -> 0x010a }
            androidx.paging.LoadState r7 = (androidx.paging.LoadState) r7     // Catch:{ all -> 0x010a }
            r14 = 0
            r8.<init>(r3, r14, r7)     // Catch:{ all -> 0x010a }
            r0.L$0 = r3     // Catch:{ all -> 0x010a }
            r0.L$1 = r12     // Catch:{ all -> 0x010a }
            r0.L$2 = r4     // Catch:{ all -> 0x010a }
            r0.L$3 = r5     // Catch:{ all -> 0x010a }
            r7 = 0
            r0.L$4 = r7     // Catch:{ all -> 0x04f4 }
            r0.L$5 = r7     // Catch:{ all -> 0x04f4 }
            r7 = 7
            r0.label = r7     // Catch:{ all -> 0x010a }
            java.lang.Object r0 = r6.send(r8, r0)     // Catch:{ all -> 0x010a }
            if (r0 != r2) goto L_0x04ef
            return r2
        L_0x04ef:
            r0 = r5
            r5 = r12
        L_0x04f1:
            r12 = r5
            r5 = r0
            goto L_0x04f7
        L_0x04f4:
            r0 = move-exception
            r2 = r7
            goto L_0x050b
        L_0x04f7:
            java.util.Map r0 = r5.getFailedHintsByLoadType$paging_common()     // Catch:{ all -> 0x010a }
            androidx.paging.ViewportHint r2 = r12.getHint()     // Catch:{ all -> 0x010a }
            r0.put(r3, r2)     // Catch:{ all -> 0x010a }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x010a }
            r2 = 0
            r4.unlock(r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x050b:
            r4.unlock(r2)
            throw r0
        L_0x050f:
            r14 = 0
        L_0x0510:
            int[] r2 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$4
            int r5 = r13.ordinal()
            r2 = r2[r5]
            r5 = 1
            if (r2 == r5) goto L_0x051e
            androidx.paging.LoadType r2 = androidx.paging.LoadType.PREPEND
            goto L_0x0520
        L_0x051e:
            androidx.paging.LoadType r2 = androidx.paging.LoadType.APPEND
        L_0x0520:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r15 = r9.stateHolder
            kotlinx.coroutines.sync.Mutex r5 = r15.lock
            r3.L$0 = r9
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r0
            r3.L$6 = r8
            r3.L$7 = r6
            r3.L$8 = r2
            r3.L$9 = r15
            r3.L$10 = r5
            r14 = 8
            r3.label = r14
            r18 = r0
            r14 = 0
            java.lang.Object r0 = r5.lock(r14, r3)
            if (r0 != r4) goto L_0x054a
            return r4
        L_0x054a:
            r14 = r12
            r12 = r10
            r10 = r8
            r8 = r6
            r6 = r2
            r2 = r5
            r5 = r15
            r15 = r13
            r13 = r11
            r11 = r18
        L_0x0555:
            androidx.paging.PageFetcherSnapshotState r0 = r5.state     // Catch:{ all -> 0x0696 }
            androidx.paging.ViewportHint r5 = r14.getHint()     // Catch:{ all -> 0x0696 }
            androidx.paging.PageEvent$Drop r5 = r0.dropEventOrNull(r6, r5)     // Catch:{ all -> 0x0696 }
            if (r5 == 0) goto L_0x05a0
            r0.drop(r5)     // Catch:{ all -> 0x0696 }
            kotlinx.coroutines.channels.Channel<androidx.paging.PageEvent<Value>> r6 = r9.pageEventCh     // Catch:{ all -> 0x0696 }
            r3.L$0 = r9     // Catch:{ all -> 0x0696 }
            r3.L$1 = r15     // Catch:{ all -> 0x0696 }
            r3.L$2 = r14     // Catch:{ all -> 0x0696 }
            r3.L$3 = r13     // Catch:{ all -> 0x0696 }
            r3.L$4 = r12     // Catch:{ all -> 0x0696 }
            r3.L$5 = r11     // Catch:{ all -> 0x0696 }
            r3.L$6 = r10     // Catch:{ all -> 0x0696 }
            r3.L$7 = r8     // Catch:{ all -> 0x0696 }
            r3.L$8 = r2     // Catch:{ all -> 0x0696 }
            r3.L$9 = r0     // Catch:{ all -> 0x0696 }
            r1 = 0
            r3.L$10 = r1     // Catch:{ all -> 0x059c }
            r1 = 9
            r3.label = r1     // Catch:{ all -> 0x0696 }
            java.lang.Object r1 = r6.send(r5, r3)     // Catch:{ all -> 0x0696 }
            if (r1 != r4) goto L_0x058a
            return r4
        L_0x058a:
            r5 = r2
            r6 = r8
            r8 = r10
            r10 = r11
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r15
            r15 = r9
        L_0x0593:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00bf }
            r9 = r15
            r15 = r14
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            goto L_0x05a3
        L_0x059c:
            r0 = move-exception
            r5 = r2
            goto L_0x069a
        L_0x05a0:
            r5 = r2
            r6 = r8
            r8 = r10
        L_0x05a3:
            int r1 = r14.getGenerationId()     // Catch:{ all -> 0x00bf }
            int r2 = r14.presentedItemsBeyondAnchor$paging_common(r15)     // Catch:{ all -> 0x00bf }
            int r10 = r13.element     // Catch:{ all -> 0x00bf }
            int r2 = r2 + r10
            java.lang.Object r1 = r9.nextLoadKeyOrNull(r0, r15, r1, r2)     // Catch:{ all -> 0x00bf }
            r12.element = r1     // Catch:{ all -> 0x00bf }
            T r1 = r12.element     // Catch:{ all -> 0x00bf }
            if (r1 != 0) goto L_0x05da
            androidx.paging.LoadStates r1 = r0.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x00bf }
            androidx.paging.LoadState r1 = r1.get$paging_common(r15)     // Catch:{ all -> 0x00bf }
            boolean r1 = r1 instanceof androidx.paging.LoadState.Error     // Catch:{ all -> 0x00bf }
            if (r1 != 0) goto L_0x05da
            boolean r1 = r11.element     // Catch:{ all -> 0x00bf }
            if (r1 == 0) goto L_0x05cf
            androidx.paging.LoadState$NotLoading$Companion r1 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x00bf }
            androidx.paging.LoadState$NotLoading r1 = r1.getComplete$paging_common()     // Catch:{ all -> 0x00bf }
            goto L_0x05d5
        L_0x05cf:
            androidx.paging.LoadState$NotLoading$Companion r1 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x00bf }
            androidx.paging.LoadState$NotLoading r1 = r1.getIncomplete$paging_common()     // Catch:{ all -> 0x00bf }
        L_0x05d5:
            androidx.paging.LoadState r1 = (androidx.paging.LoadState) r1     // Catch:{ all -> 0x00bf }
            r0.setSourceLoadState(r15, r1)     // Catch:{ all -> 0x00bf }
        L_0x05da:
            r1 = r6
            androidx.paging.PagingSource$LoadResult$Page r1 = (androidx.paging.PagingSource.LoadResult.Page) r1     // Catch:{ all -> 0x00bf }
            androidx.paging.PageEvent r0 = r0.toPageEvent$paging_common(r1, r15)     // Catch:{ all -> 0x00bf }
            kotlinx.coroutines.channels.Channel<androidx.paging.PageEvent<Value>> r1 = r9.pageEventCh     // Catch:{ all -> 0x00bf }
            r3.L$0 = r9     // Catch:{ all -> 0x00bf }
            r3.L$1 = r15     // Catch:{ all -> 0x00bf }
            r3.L$2 = r14     // Catch:{ all -> 0x00bf }
            r3.L$3 = r13     // Catch:{ all -> 0x00bf }
            r3.L$4 = r12     // Catch:{ all -> 0x00bf }
            r3.L$5 = r11     // Catch:{ all -> 0x00bf }
            r3.L$6 = r8     // Catch:{ all -> 0x00bf }
            r3.L$7 = r6     // Catch:{ all -> 0x00bf }
            r3.L$8 = r5     // Catch:{ all -> 0x00bf }
            r2 = 0
            r3.L$9 = r2     // Catch:{ all -> 0x0693 }
            r3.L$10 = r2     // Catch:{ all -> 0x0693 }
            r2 = 10
            r3.label = r2     // Catch:{ all -> 0x00bf }
            java.lang.Object r0 = r1.send(r0, r3)     // Catch:{ all -> 0x00bf }
            if (r0 != r4) goto L_0x0605
            return r4
        L_0x0605:
            r0 = r6
            r6 = r8
            r10 = r9
            r8 = r14
            r14 = r12
            r16 = r13
            r13 = r11
            r11 = r15
            r15 = r16
        L_0x0610:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00bf }
            r1 = 0
            r5.unlock(r1)
            boolean r1 = r6 instanceof androidx.paging.PagingSource.LoadParams.Prepend
            if (r1 == 0) goto L_0x0625
            r1 = r0
            androidx.paging.PagingSource$LoadResult$Page r1 = (androidx.paging.PagingSource.LoadResult.Page) r1
            java.lang.Object r1 = r1.getPrevKey()
            if (r1 != 0) goto L_0x0625
            r5 = 1
            goto L_0x0626
        L_0x0625:
            r5 = 0
        L_0x0626:
            boolean r1 = r6 instanceof androidx.paging.PagingSource.LoadParams.Append
            if (r1 == 0) goto L_0x0634
            androidx.paging.PagingSource$LoadResult$Page r0 = (androidx.paging.PagingSource.LoadResult.Page) r0
            java.lang.Object r0 = r0.getNextKey()
            if (r0 != 0) goto L_0x0634
            r0 = 1
            goto L_0x0635
        L_0x0634:
            r0 = 0
        L_0x0635:
            androidx.paging.RemoteMediatorConnection<Key, Value> r1 = r10.remoteMediatorConnection
            if (r1 == 0) goto L_0x0684
            if (r5 != 0) goto L_0x063d
            if (r0 == 0) goto L_0x0684
        L_0x063d:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r12 = r10.stateHolder
            kotlinx.coroutines.sync.Mutex r6 = r12.lock
            r3.L$0 = r10
            r3.L$1 = r11
            r3.L$2 = r8
            r3.L$3 = r15
            r3.L$4 = r14
            r3.L$5 = r13
            r3.L$6 = r12
            r3.L$7 = r6
            r1 = 0
            r3.L$8 = r1
            r3.I$0 = r5
            r3.I$1 = r0
            r2 = 11
            r3.label = r2
            java.lang.Object r2 = r6.lock(r1, r3)
            if (r2 != r4) goto L_0x0665
            return r4
        L_0x0665:
            androidx.paging.PageFetcherSnapshotState r2 = r12.state     // Catch:{ all -> 0x068a }
            androidx.paging.ViewportHint$Access r9 = r10.lastHint     // Catch:{ all -> 0x068a }
            androidx.paging.PagingState r2 = r2.currentPagingState$paging_common(r9)     // Catch:{ all -> 0x068a }
            r6.unlock(r1)
            if (r5 == 0) goto L_0x067b
            androidx.paging.RemoteMediatorConnection<Key, Value> r1 = r10.remoteMediatorConnection
            androidx.paging.LoadType r5 = androidx.paging.LoadType.PREPEND
            r1.requestLoad(r5, r2)
        L_0x067b:
            if (r0 == 0) goto L_0x0684
            androidx.paging.RemoteMediatorConnection<Key, Value> r0 = r10.remoteMediatorConnection
            androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND
            r0.requestLoad(r1, r2)
        L_0x0684:
            r12 = r10
            r0 = r13
            r2 = r14
            r9 = r15
            r10 = r8
            goto L_0x068f
        L_0x068a:
            r0 = move-exception
            r6.unlock(r1)
            throw r0
        L_0x068f:
            r1 = r17
            goto L_0x035d
        L_0x0693:
            r0 = move-exception
            r1 = r2
            goto L_0x069a
        L_0x0696:
            r0 = move-exception
            r5 = r2
            goto L_0x00c0
        L_0x069a:
            r5.unlock(r1)
            throw r0
        L_0x069e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x06a1:
            r0 = move-exception
            r6 = r5
            goto L_0x01af
        L_0x06a5:
            r6.unlock(r1)
            throw r0
        L_0x06a9:
            r0 = move-exception
            r1 = 0
            r2.unlock(r1)
            throw r0
        L_0x06af:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r7.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.doLoad(androidx.paging.LoadType, androidx.paging.GenerationalViewportHint, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object setLoading(androidx.paging.PageFetcherSnapshotState<Key, Value> r6, androidx.paging.LoadType r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof androidx.paging.PageFetcherSnapshot$setLoading$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            androidx.paging.PageFetcherSnapshot$setLoading$1 r0 = (androidx.paging.PageFetcherSnapshot$setLoading$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            androidx.paging.PageFetcherSnapshot$setLoading$1 r0 = new androidx.paging.PageFetcherSnapshot$setLoading$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0054
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.paging.LoadState$Loading r8 = androidx.paging.LoadState.Loading.INSTANCE
            androidx.paging.LoadState r8 = (androidx.paging.LoadState) r8
            boolean r6 = r6.setSourceLoadState(r7, r8)
            if (r6 == 0) goto L_0x0054
            kotlinx.coroutines.channels.Channel<androidx.paging.PageEvent<Value>> r6 = r5.pageEventCh
            androidx.paging.PageEvent$LoadStateUpdate r8 = new androidx.paging.PageEvent$LoadStateUpdate
            r2 = 0
            androidx.paging.LoadState$Loading r4 = androidx.paging.LoadState.Loading.INSTANCE
            androidx.paging.LoadState r4 = (androidx.paging.LoadState) r4
            r8.<init>(r7, r2, r4)
            r0.label = r3
            java.lang.Object r6 = r6.send(r8, r0)
            if (r6 != r1) goto L_0x0054
            return r1
        L_0x0054:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.setLoading(androidx.paging.PageFetcherSnapshotState, androidx.paging.LoadType, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Key nextLoadKeyOrNull(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, int i, int i2) {
        if (i != pageFetcherSnapshotState.generationId$paging_common(loadType) || (pageFetcherSnapshotState.getSourceLoadStates$paging_common().get$paging_common(loadType) instanceof LoadState.Error) || i2 >= this.config.prefetchDistance) {
            return null;
        }
        if (loadType == LoadType.PREPEND) {
            return ((PagingSource.LoadResult.Page) CollectionsKt.first(pageFetcherSnapshotState.getPages$paging_common())).getPrevKey();
        }
        return ((PagingSource.LoadResult.Page) CollectionsKt.last(pageFetcherSnapshotState.getPages$paging_common())).getNextKey();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object collectAsGenerationalViewportHints(Flow<Integer> flow, LoadType loadType, Continuation<? super Unit> continuation) {
        Object collect = FlowKt.conflate(FlowExtKt.simpleRunningReduce(FlowExtKt.simpleTransformLatest(flow, new C0494xe29ec4fd((Continuation) null, this, loadType)), new PageFetcherSnapshot$collectAsGenerationalViewportHints$3(loadType, (Continuation) null))).collect(new C0493x2618a1af(this, loadType), continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
