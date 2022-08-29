package androidx.paging;

import androidx.core.app.NotificationCompat;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u0002H\u00012\u00020\u0002B^\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012L\u0010\u0006\u001aH\b\u0001\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001a\u00104\u001a\b\u0012\u0004\u0012\u00028\u0000052\f\u00106\u001a\b\u0012\u0004\u0012\u00028\u000105J%\u00107\u001a\b\u0012\u0004\u0012\u00028\u0000082\f\u00106\u001a\b\u0012\u0004\u0012\u00028\u000108H@ø\u0001\u0000¢\u0006\u0002\u00109J%\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;2\f\u00106\u001a\b\u0012\u0004\u0012\u00028\u00010;H@ø\u0001\u0000¢\u0006\u0002\u0010<J%\u0010=\u001a\b\u0012\u0004\u0012\u00028\u0000082\f\u00106\u001a\b\u0012\u0004\u0012\u00028\u00010>H@ø\u0001\u0000¢\u0006\u0002\u0010?J&\u0010@\u001a\b\u0012\u0004\u0012\u0002H\u00030#\"\b\b\u0002\u0010\u0003*\u00020\u00022\f\u0010A\u001a\b\u0012\u0004\u0012\u0002H\u00030#H\u0002J\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000;*\b\u0012\u0004\u0012\u00028\u00010;J\"\u0010C\u001a\u00020\u000f\"\b\b\u0002\u0010\u0003*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00030;2\u0006\u0010\u0004\u001a\u00020\u0005J\"\u0010D\u001a\u00020\u000f\"\b\b\u0002\u0010\u0003*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00030;2\u0006\u0010\u0004\u001a\u00020\u0005R\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\\\u0010\u0006\u001aH\b\u0001\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010#0\"¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010)\"\u0004\b.\u0010+R\u001a\u0010/\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0011\"\u0004\b1\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u00103\u0002\u0004\n\u0002\b\u0019¨\u0006E"}, mo27512d2 = {"Landroidx/paging/SeparatorState;", "R", "", "T", "terminalSeparatorType", "Landroidx/paging/TerminalSeparatorType;", "generator", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "before", "after", "Lkotlin/coroutines/Continuation;", "(Landroidx/paging/TerminalSeparatorType;Lkotlin/jvm/functions/Function3;)V", "endTerminalSeparatorDeferred", "", "getEndTerminalSeparatorDeferred", "()Z", "setEndTerminalSeparatorDeferred", "(Z)V", "footerAdded", "getFooterAdded", "setFooterAdded", "getGenerator", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/jvm/functions/Function3;", "headerAdded", "getHeaderAdded", "setHeaderAdded", "loadStates", "Landroidx/paging/MutableLoadStateCollection;", "getLoadStates", "()Landroidx/paging/MutableLoadStateCollection;", "pageStash", "", "Landroidx/paging/TransformablePage;", "getPageStash", "()Ljava/util/List;", "placeholdersAfter", "", "getPlaceholdersAfter", "()I", "setPlaceholdersAfter", "(I)V", "placeholdersBefore", "getPlaceholdersBefore", "setPlaceholdersBefore", "startTerminalSeparatorDeferred", "getStartTerminalSeparatorDeferred", "setStartTerminalSeparatorDeferred", "getTerminalSeparatorType", "()Landroidx/paging/TerminalSeparatorType;", "onDrop", "Landroidx/paging/PageEvent$Drop;", "event", "onEvent", "Landroidx/paging/PageEvent;", "(Landroidx/paging/PageEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onInsert", "Landroidx/paging/PageEvent$Insert;", "(Landroidx/paging/PageEvent$Insert;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onLoadStateUpdate", "Landroidx/paging/PageEvent$LoadStateUpdate;", "(Landroidx/paging/PageEvent$LoadStateUpdate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformablePageToStash", "originalPage", "asRType", "terminatesEnd", "terminatesStart", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: Separators.kt */
final class SeparatorState<R, T extends R> {
    private boolean endTerminalSeparatorDeferred;
    private boolean footerAdded;
    private final Function3<T, T, Continuation<? super R>, Object> generator;
    private boolean headerAdded;
    private final MutableLoadStateCollection loadStates = new MutableLoadStateCollection();
    private final List<TransformablePage<T>> pageStash = new ArrayList();
    private int placeholdersAfter;
    private int placeholdersBefore;
    private boolean startTerminalSeparatorDeferred;
    private final TerminalSeparatorType terminalSeparatorType;

    @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TerminalSeparatorType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[TerminalSeparatorType.FULLY_COMPLETE.ordinal()] = 1;
            iArr[TerminalSeparatorType.SOURCE_COMPLETE.ordinal()] = 2;
            int[] iArr2 = new int[TerminalSeparatorType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[TerminalSeparatorType.FULLY_COMPLETE.ordinal()] = 1;
            iArr2[TerminalSeparatorType.SOURCE_COMPLETE.ordinal()] = 2;
        }
    }

    public final PageEvent.Insert<R> asRType(PageEvent.Insert<T> insert) {
        Intrinsics.checkNotNullParameter(insert, "$this$asRType");
        return insert;
    }

    public SeparatorState(TerminalSeparatorType terminalSeparatorType2, Function3<? super T, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(terminalSeparatorType2, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(function3, "generator");
        this.terminalSeparatorType = terminalSeparatorType2;
        this.generator = function3;
    }

    public final TerminalSeparatorType getTerminalSeparatorType() {
        return this.terminalSeparatorType;
    }

    public final Function3<T, T, Continuation<? super R>, Object> getGenerator() {
        return this.generator;
    }

    public final List<TransformablePage<T>> getPageStash() {
        return this.pageStash;
    }

    public final boolean getEndTerminalSeparatorDeferred() {
        return this.endTerminalSeparatorDeferred;
    }

    public final void setEndTerminalSeparatorDeferred(boolean z) {
        this.endTerminalSeparatorDeferred = z;
    }

    public final boolean getStartTerminalSeparatorDeferred() {
        return this.startTerminalSeparatorDeferred;
    }

    public final void setStartTerminalSeparatorDeferred(boolean z) {
        this.startTerminalSeparatorDeferred = z;
    }

    public final MutableLoadStateCollection getLoadStates() {
        return this.loadStates;
    }

    public final int getPlaceholdersBefore() {
        return this.placeholdersBefore;
    }

    public final void setPlaceholdersBefore(int i) {
        this.placeholdersBefore = i;
    }

    public final int getPlaceholdersAfter() {
        return this.placeholdersAfter;
    }

    public final void setPlaceholdersAfter(int i) {
        this.placeholdersAfter = i;
    }

    public final boolean getFooterAdded() {
        return this.footerAdded;
    }

    public final void setFooterAdded(boolean z) {
        this.footerAdded = z;
    }

    public final boolean getHeaderAdded() {
        return this.headerAdded;
    }

    public final void setHeaderAdded(boolean z) {
        this.headerAdded = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object onEvent(androidx.paging.PageEvent<T> r6, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.paging.SeparatorState$onEvent$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            androidx.paging.SeparatorState$onEvent$1 r0 = (androidx.paging.SeparatorState$onEvent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            androidx.paging.SeparatorState$onEvent$1 r0 = new androidx.paging.SeparatorState$onEvent$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$0
            androidx.paging.SeparatorState r6 = (androidx.paging.SeparatorState) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x007a
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            java.lang.Object r6 = r0.L$0
            androidx.paging.SeparatorState r6 = (androidx.paging.SeparatorState) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0056
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r7)
            boolean r7 = r6 instanceof androidx.paging.PageEvent.Insert
            if (r7 == 0) goto L_0x0059
            androidx.paging.PageEvent$Insert r6 = (androidx.paging.PageEvent.Insert) r6
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r7 = r5.onInsert(r6, r0)
            if (r7 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r6 = r5
        L_0x0056:
            androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
            goto L_0x007c
        L_0x0059:
            boolean r7 = r6 instanceof androidx.paging.PageEvent.Drop
            if (r7 == 0) goto L_0x0068
            androidx.paging.PageEvent$Drop r6 = (androidx.paging.PageEvent.Drop) r6
            androidx.paging.PageEvent$Drop r6 = r5.onDrop(r6)
            r7 = r6
            androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
            r6 = r5
            goto L_0x007c
        L_0x0068:
            boolean r7 = r6 instanceof androidx.paging.PageEvent.LoadStateUpdate
            if (r7 == 0) goto L_0x00b3
            androidx.paging.PageEvent$LoadStateUpdate r6 = (androidx.paging.PageEvent.LoadStateUpdate) r6
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r7 = r5.onLoadStateUpdate(r6, r0)
            if (r7 != r1) goto L_0x0079
            return r1
        L_0x0079:
            r6 = r5
        L_0x007a:
            androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
        L_0x007c:
            boolean r0 = r6.endTerminalSeparatorDeferred
            if (r0 == 0) goto L_0x0097
            java.util.List<androidx.paging.TransformablePage<T>> r0 = r6.pageStash
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0089
            goto L_0x0097
        L_0x0089:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "deferred endTerm, page stash should be empty"
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        L_0x0097:
            boolean r0 = r6.startTerminalSeparatorDeferred
            if (r0 == 0) goto L_0x00b2
            java.util.List<androidx.paging.TransformablePage<T>> r6 = r6.pageStash
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x00a4
            goto L_0x00b2
        L_0x00a4:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "deferred startTerm, page stash should be empty"
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        L_0x00b2:
            return r7
        L_0x00b3:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.onEvent(androidx.paging.PageEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final <T> boolean terminatesStart(PageEvent.Insert<T> insert, TerminalSeparatorType terminalSeparatorType2) {
        LoadStates mediator;
        LoadState prepend;
        Intrinsics.checkNotNullParameter(insert, "$this$terminatesStart");
        Intrinsics.checkNotNullParameter(terminalSeparatorType2, "terminalSeparatorType");
        if (insert.getLoadType() == LoadType.APPEND) {
            return this.startTerminalSeparatorDeferred;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[terminalSeparatorType2.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return insert.getCombinedLoadStates().getSource().getPrepend().getEndOfPaginationReached();
            }
            throw new NoWhenBranchMatchedException();
        } else if (!insert.getCombinedLoadStates().getSource().getPrepend().getEndOfPaginationReached() || ((mediator = insert.getCombinedLoadStates().getMediator()) != null && (prepend = mediator.getPrepend()) != null && !prepend.getEndOfPaginationReached())) {
            return false;
        } else {
            return true;
        }
    }

    public final <T> boolean terminatesEnd(PageEvent.Insert<T> insert, TerminalSeparatorType terminalSeparatorType2) {
        LoadStates mediator;
        LoadState append;
        Intrinsics.checkNotNullParameter(insert, "$this$terminatesEnd");
        Intrinsics.checkNotNullParameter(terminalSeparatorType2, "terminalSeparatorType");
        if (insert.getLoadType() == LoadType.PREPEND) {
            return this.endTerminalSeparatorDeferred;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[terminalSeparatorType2.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return insert.getCombinedLoadStates().getSource().getAppend().getEndOfPaginationReached();
            }
            throw new NoWhenBranchMatchedException();
        } else if (!insert.getCombinedLoadStates().getSource().getAppend().getEndOfPaginationReached() || ((mediator = insert.getCombinedLoadStates().getMediator()) != null && (append = mediator.getAppend()) != null && !append.getEndOfPaginationReached())) {
            return false;
        } else {
            return true;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v36, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: androidx.paging.TransformablePage} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v37, resolved type: int[]} */
    /* JADX WARNING: type inference failed for: r4v30, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r5v42 */
    /* JADX WARNING: type inference failed for: r4v32 */
    /* JADX WARNING: type inference failed for: r4v33 */
    /* JADX WARNING: type inference failed for: r5v43 */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0412, code lost:
        r9 = r8.getHintOriginalPageOffset();
        r10 = r8.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x041b, code lost:
        if (r10 == null) goto L_0x042a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x041d, code lost:
        r10 = (java.lang.Integer) kotlin.collections.CollectionsKt.first(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0423, code lost:
        if (r10 == null) goto L_0x042a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0425, code lost:
        r10 = r10.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x042a, code lost:
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x042b, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, (androidx.paging.TransformablePage) null, r8, r9, r10);
        r5 = r0;
        r6 = r1;
        r8 = r2;
        r9 = r11;
        r10 = r12;
        r11 = r13;
        r12 = r15;
        r13 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x043b, code lost:
        if (r6 != 0) goto L_0x07c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x043d, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r10);
        r1 = r10.intValue();
        r0 = r5;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r14;
        r14 = r19;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0450, code lost:
        if (r5 >= r1) goto L_0x0498;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0452, code lost:
        r15 = r14.generator;
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r9;
        r3.L$6 = r8;
        r3.L$7 = r7;
        r3.L$8 = r12;
        r18 = r7;
        r3.L$9 = null;
        r3.Z$0 = r0;
        r3.I$0 = r6;
        r3.I$1 = r5;
        r3.I$2 = r1;
        r3.label = 3;
        r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r13.getPages().get(r5), r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0484, code lost:
        if (r2 != r4) goto L_0x0487;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0486, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0487, code lost:
        r15 = r14;
        r7 = r18;
        r14 = r12;
        r12 = r10;
        r10 = r8;
        r8 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x048e, code lost:
        r8.add(r2);
        r5 = r5 + 1;
        r8 = r10;
        r10 = r12;
        r12 = r14;
        r14 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0498, code lost:
        r18 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x04a1, code lost:
        if (r13.getLoadType() != androidx.paging.LoadType.APPEND) goto L_0x052f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x04ac, code lost:
        if ((!r14.pageStash.isEmpty()) == false) goto L_0x052f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x04ae, code lost:
        r1 = (androidx.paging.TransformablePage) kotlin.collections.CollectionsKt.last(r14.pageStash);
        r2 = r14.generator;
        r5 = kotlin.collections.CollectionsKt.last(r1.getData());
        kotlin.jvm.internal.Intrinsics.checkNotNull(r10);
        r7 = kotlin.collections.CollectionsKt.first(r10.getData());
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r9;
        r3.L$6 = r8;
        r15 = r18;
        r3.L$7 = r15;
        r3.L$8 = r1;
        r24 = r1;
        r3.L$9 = null;
        r3.Z$0 = r0;
        r3.I$0 = r6;
        r3.label = 4;
        r2 = r2.invoke(r5, r7, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x009b, code lost:
        r0 = r3;
        r8 = r4;
        r15 = r14;
        r3 = r2;
        r14 = r13;
        r13 = r12;
        r12 = r11;
        r11 = r10;
        r10 = r9;
        r9 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x04ef, code lost:
        if (r2 != r4) goto L_0x04f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x04f1, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x04f2, code lost:
        r1 = r6;
        r18 = r13;
        r19 = r14;
        r13 = r11;
        r14 = r12;
        r11 = r9;
        r12 = r10;
        r9 = r0;
        r0 = r3;
        r10 = r8;
        r3 = r2;
        r8 = r4;
        r4 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0502, code lost:
        r2 = r14;
        r6 = r12.getHintOriginalPageOffset();
        r5 = r12.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x050d, code lost:
        if (r5 == null) goto L_0x051d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x050f, code lost:
        r5 = (java.lang.Integer) kotlin.collections.CollectionsKt.first(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0515, code lost:
        if (r5 == null) goto L_0x051d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0517, code lost:
        r7 = r5.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x051d, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x051e, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r2, r3, r4, r12, r6, r7);
        r3 = r0;
        r4 = r8;
        r5 = r9;
        r8 = r10;
        r9 = r11;
        r10 = r13;
        r6 = r14;
        r7 = r15;
        r13 = r18;
        r14 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x052f, code lost:
        r5 = r0;
        r1 = r6;
        r6 = r12;
        r7 = r18;
        r12 = r10;
        r10 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0537, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r12);
        r10.add(r14.transformablePageToStash(r12));
        r0 = r14.generator;
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r6;
        r3.L$3 = r10;
        r3.L$4 = r9;
        r3.L$5 = r8;
        r3.L$6 = r7;
        r3.L$7 = r6;
        r3.L$8 = null;
        r3.L$9 = null;
        r3.Z$0 = r5;
        r3.I$0 = r1;
        r3.label = 5;
        r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r12, r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0563, code lost:
        if (r2 != r4) goto L_0x0566;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0565, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0566, code lost:
        r11 = r6;
        r12 = r13;
        r13 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0569, code lost:
        r6.add(r2);
        r0 = r12.getPages();
        r2 = r9.intValue();
        kotlin.jvm.internal.Intrinsics.checkNotNull(r7);
        r0 = r0.subList(r2, r7.intValue() + 1).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x058b, code lost:
        if (r0.hasNext() == false) goto L_0x07b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x058d, code lost:
        r2 = r0.next();
        r9 = r7;
        r14 = r13;
        r13 = r12;
        r12 = r11;
        r11 = r10;
        r10 = r8;
        r8 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x059c, code lost:
        if (r8.hasNext() == false) goto L_0x06ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00d7, code lost:
        r21 = r14;
        r14 = r8;
        r8 = r11;
        r11 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x059e, code lost:
        r7 = r8.next();
        r6 = (androidx.paging.TransformablePage) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x05b4, code lost:
        if ((!r7.getData().isEmpty()) == false) goto L_0x0662;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x05b6, code lost:
        r0 = r14.generator;
        r2 = kotlin.collections.CollectionsKt.last(r6.getData());
        r15 = kotlin.collections.CollectionsKt.first(r7.getData());
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r9;
        r3.L$6 = r8;
        r3.L$7 = r7;
        r3.L$8 = r6;
        r24 = r6;
        r3.L$9 = null;
        r3.Z$0 = r5;
        r3.I$0 = r1;
        r3.label = 6;
        r2 = r0.invoke(r2, r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x05ea, code lost:
        if (r2 != r4) goto L_0x05ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x05ec, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x05ed, code lost:
        r0 = r3;
        r15 = r11;
        r18 = r12;
        r19 = r13;
        r20 = r14;
        r3 = r2;
        r11 = r7;
        r12 = r8;
        r13 = r9;
        r14 = r10;
        r10 = r24;
        r8 = r4;
        r9 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x05fe, code lost:
        r2 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0608, code lost:
        if (r19.getLoadType() != androidx.paging.LoadType.PREPEND) goto L_0x060f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x060a, code lost:
        r4 = r10.getHintOriginalPageOffset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x060f, code lost:
        r4 = r11.getHintOriginalPageOffset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0613, code lost:
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x061a, code lost:
        if (r19.getLoadType() != androidx.paging.LoadType.PREPEND) goto L_0x0639;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x061c, code lost:
        r4 = r10.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0620, code lost:
        if (r4 == null) goto L_0x062f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0622, code lost:
        r4 = (java.lang.Integer) kotlin.collections.CollectionsKt.last(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0628, code lost:
        if (r4 == null) goto L_0x062f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x062a, code lost:
        r4 = r4.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x062f, code lost:
        r4 = kotlin.collections.CollectionsKt.getLastIndex(r10.getData());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0637, code lost:
        r7 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0639, code lost:
        r4 = r11.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x063d, code lost:
        if (r4 == null) goto L_0x064c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x063f, code lost:
        r4 = (java.lang.Integer) kotlin.collections.CollectionsKt.first(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0645, code lost:
        if (r4 == null) goto L_0x064c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0647, code lost:
        r4 = r4.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x064c, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x064d, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r2, r3, r10, r11, r6, r7);
        r3 = r0;
        r4 = r8;
        r5 = r9;
        r9 = r10;
        r10 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r14;
        r14 = r15;
        r6 = r18;
        r7 = r19;
        r8 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0662, code lost:
        r24 = r6;
        r6 = r12;
        r12 = r9;
        r9 = r24;
        r21 = r10;
        r10 = r7;
        r7 = r13;
        r13 = r21;
        r22 = r11;
        r11 = r8;
        r8 = r14;
        r14 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0680, code lost:
        if ((!r10.getData().isEmpty()) == false) goto L_0x0689;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0682, code lost:
        r14.add(r8.transformablePageToStash(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0689, code lost:
        r0 = r8.generator;
        r3.L$0 = r8;
        r3.L$1 = r7;
        r3.L$2 = r6;
        r3.L$3 = r14;
        r3.L$4 = r13;
        r3.L$5 = r12;
        r3.L$6 = r11;
        r3.L$7 = r10;
        r3.L$8 = r9;
        r3.L$9 = r6;
        r3.Z$0 = r5;
        r3.I$0 = r1;
        r3.label = 7;
        r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r10, r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x06aa, code lost:
        if (r2 != r4) goto L_0x06ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x06ac, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x06ad, code lost:
        r15 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x06b0, code lost:
        r6.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x06bf, code lost:
        if ((!r10.getData().isEmpty()) == false) goto L_0x06c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x06c1, code lost:
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x06c3, code lost:
        r2 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x06c4, code lost:
        r9 = r12;
        r10 = r13;
        r12 = r15;
        r13 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x06d0, code lost:
        if (r13.getLoadType() != androidx.paging.LoadType.PREPEND) goto L_0x0752;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x06dc, code lost:
        if ((!r14.pageStash.isEmpty()) == false) goto L_0x0752;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x06de, code lost:
        r6 = (androidx.paging.TransformablePage) kotlin.collections.CollectionsKt.first(r14.pageStash);
        r0 = r14.generator;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r10);
        r2 = kotlin.collections.CollectionsKt.last(r10.getData());
        r7 = kotlin.collections.CollectionsKt.first(r6.getData());
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r9;
        r3.L$6 = r6;
        r3.L$7 = null;
        r3.L$8 = null;
        r3.L$9 = null;
        r3.Z$0 = r5;
        r3.I$0 = r1;
        r3.label = 8;
        r2 = r0.invoke(r2, r7, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x071d, code lost:
        if (r2 != r4) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x071f, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0720, code lost:
        r2 = r13;
        r6 = r11.getHintOriginalPageOffset();
        r4 = r11.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x072b, code lost:
        if (r4 == null) goto L_0x073a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x072d, code lost:
        r4 = (java.lang.Integer) kotlin.collections.CollectionsKt.last(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0733, code lost:
        if (r4 == null) goto L_0x073a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0735, code lost:
        r4 = r4.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x073a, code lost:
        r4 = kotlin.collections.CollectionsKt.getLastIndex(r11.getData());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0742, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r2, r3, r11, r5, r6, r4);
        r3 = r0;
        r6 = r1;
        r4 = r8;
        r5 = r9;
        r9 = r10;
        r10 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r14;
        r14 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0752, code lost:
        r6 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0753, code lost:
        r0 = r9.intValue() + 1;
        r1 = kotlin.collections.CollectionsKt.getLastIndex(r13.getPages());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0761, code lost:
        if (r0 > r1) goto L_0x07b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0763, code lost:
        r9 = r6;
        r15 = r13;
        r6 = r14;
        r13 = r11;
        r11 = r12;
        r12 = r10;
        r10 = r5;
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x076b, code lost:
        r2 = r6.generator;
        r3.L$0 = r6;
        r3.L$1 = r15;
        r3.L$2 = r11;
        r3.L$3 = r13;
        r3.L$4 = r12;
        r3.L$5 = r11;
        r3.L$6 = null;
        r3.L$7 = null;
        r3.L$8 = null;
        r3.L$9 = null;
        r3.Z$0 = r10;
        r3.I$0 = r9;
        r3.I$1 = r5;
        r3.I$2 = r1;
        r3.label = 9;
        r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r15.getPages().get(r5), r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x079c, code lost:
        if (r2 != r4) goto L_0x079f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x079e, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x079f, code lost:
        r14 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x07a0, code lost:
        r11.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x07a3, code lost:
        if (r5 == r1) goto L_0x07a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x07a5, code lost:
        r5 = r5 + 1;
        r11 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x07a9, code lost:
        r0 = r3;
        r3 = r6;
        r6 = r9;
        r5 = r10;
        r9 = r12;
        r7 = r14;
        r8 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x07b1, code lost:
        r0 = r3;
        r9 = r10;
        r7 = r12;
        r8 = r13;
        r3 = r14;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x07c1, code lost:
        throw new java.lang.UnsupportedOperationException("Empty collection can't be reduced.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x07c2, code lost:
        r0 = r3;
        r7 = r13;
        r8 = r14;
        r3 = r19;
        r13 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x07c8, code lost:
        if (r5 == false) goto L_0x0839;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x07cc, code lost:
        if (r3.footerAdded != false) goto L_0x0839;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x07ce, code lost:
        r3.footerAdded = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x07d1, code lost:
        if (r6 == 0) goto L_0x07dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x07d3, code lost:
        r1 = (androidx.paging.TransformablePage) kotlin.collections.CollectionsKt.last(r3.pageStash);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x07dc, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r9);
        r1 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x07e0, code lost:
        r2 = r7;
        r5 = r3.generator;
        r6 = kotlin.collections.CollectionsKt.last(r1.getData());
        r0.L$0 = r3;
        r0.L$1 = r8;
        r0.L$2 = r7;
        r0.L$3 = r13;
        r0.L$4 = r1;
        r0.L$5 = r2;
        r0.L$6 = null;
        r0.L$7 = null;
        r0.L$8 = null;
        r0.L$9 = null;
        r0.label = 10;
        r0 = r5.invoke(r6, null, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x080a, code lost:
        if (r0 != r4) goto L_0x080d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x080c, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x080d, code lost:
        r15 = r0;
        r16 = r1;
        r14 = r2;
        r5 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0812, code lost:
        r18 = r16.getHintOriginalPageOffset();
        r0 = r16.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x081c, code lost:
        if (r0 == null) goto L_0x082b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x081e, code lost:
        r0 = (java.lang.Integer) kotlin.collections.CollectionsKt.last(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0824, code lost:
        if (r0 == null) goto L_0x082b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0826, code lost:
        r0 = r0.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x082b, code lost:
        r0 = kotlin.collections.CollectionsKt.getLastIndex(r16.getData());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0833, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r14, r15, r16, (androidx.paging.TransformablePage) null, r18, r0);
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x0839, code lost:
        r3.endTerminalSeparatorDeferred = false;
        r3.startTerminalSeparatorDeferred = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x0844, code lost:
        if (r8.getLoadType() != androidx.paging.LoadType.APPEND) goto L_0x084e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0846, code lost:
        r3.pageStash.addAll(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x084e, code lost:
        r3.pageStash.addAll(0, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0855, code lost:
        r15 = r8.getLoadType();
        r8.getPages();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0874, code lost:
        return new androidx.paging.PageEvent.Insert(r15, r7, r8.getPlaceholdersBefore(), r8.getPlaceholdersAfter(), r8.getCombinedLoadStates(), (kotlin.jvm.internal.DefaultConstructorMarker) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:?, code lost:
        return new androidx.paging.PageEvent.Insert(r3, kotlin.collections.CollectionsKt.listOf(androidx.paging.SeparatorsKt.separatorPage(r2, r5, r4, r4)), r1.getPlaceholdersBefore(), r1.getPlaceholdersAfter(), r1.getCombinedLoadStates(), (kotlin.jvm.internal.DefaultConstructorMarker) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:?, code lost:
        return r3.asRType(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x02dc, code lost:
        r3.endTerminalSeparatorDeferred = r4;
        r3.startTerminalSeparatorDeferred = r4;
        r3.headerAdded = r5;
        r3.footerAdded = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x02e4, code lost:
        if (r2 != null) goto L_0x02eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x02eb, code lost:
        r3 = r1.getLoadType();
        r1.getPages();
        r5 = new int[r5];
        r5[r4] = r4;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r5v36, types: [int, boolean] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x01bc  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0883  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0264  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object onInsert(androidx.paging.PageEvent.Insert<T> r24, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent.Insert<R>> r25) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            boolean r3 = r2 instanceof androidx.paging.SeparatorState$onInsert$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            androidx.paging.SeparatorState$onInsert$1 r3 = (androidx.paging.SeparatorState$onInsert$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            androidx.paging.SeparatorState$onInsert$1 r3 = new androidx.paging.SeparatorState$onInsert$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            switch(r5) {
                case 0: goto L_0x0209;
                case 1: goto L_0x01fa;
                case 2: goto L_0x01bc;
                case 3: goto L_0x0181;
                case 4: goto L_0x0145;
                case 5: goto L_0x011c;
                case 6: goto L_0x00df;
                case 7: goto L_0x00a8;
                case 8: goto L_0x0078;
                case 9: goto L_0x0053;
                case 10: goto L_0x0032;
                default: goto L_0x002a;
            }
        L_0x002a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0032:
            java.lang.Object r1 = r3.L$5
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r4 = r3.L$4
            androidx.paging.TransformablePage r4 = (androidx.paging.TransformablePage) r4
            java.lang.Object r5 = r3.L$3
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            java.lang.Object r7 = r3.L$2
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            java.lang.Object r8 = r3.L$1
            androidx.paging.PageEvent$Insert r8 = (androidx.paging.PageEvent.Insert) r8
            java.lang.Object r3 = r3.L$0
            androidx.paging.SeparatorState r3 = (androidx.paging.SeparatorState) r3
            kotlin.ResultKt.throwOnFailure(r2)
            r14 = r1
            r15 = r2
            r16 = r4
            goto L_0x0812
        L_0x0053:
            int r1 = r3.I$2
            int r5 = r3.I$1
            int r9 = r3.I$0
            boolean r10 = r3.Z$0
            java.lang.Object r11 = r3.L$5
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$4
            androidx.paging.TransformablePage r12 = (androidx.paging.TransformablePage) r12
            java.lang.Object r13 = r3.L$3
            java.util.ArrayList r13 = (java.util.ArrayList) r13
            java.lang.Object r14 = r3.L$2
            java.util.ArrayList r14 = (java.util.ArrayList) r14
            java.lang.Object r15 = r3.L$1
            androidx.paging.PageEvent$Insert r15 = (androidx.paging.PageEvent.Insert) r15
            java.lang.Object r6 = r3.L$0
            androidx.paging.SeparatorState r6 = (androidx.paging.SeparatorState) r6
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x07a0
        L_0x0078:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$6
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            java.lang.Object r9 = r3.L$5
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$4
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$3
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$2
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.PageEvent$Insert r13 = (androidx.paging.PageEvent.Insert) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.SeparatorState r14 = (androidx.paging.SeparatorState) r14
            kotlin.ResultKt.throwOnFailure(r2)
        L_0x009b:
            r0 = r3
            r8 = r4
            r15 = r14
            r3 = r2
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r5
            r5 = r6
            goto L_0x0720
        L_0x00a8:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$9
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r9 = r3.L$8
            androidx.paging.TransformablePage r9 = (androidx.paging.TransformablePage) r9
            java.lang.Object r10 = r3.L$7
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$6
            java.util.Iterator r11 = (java.util.Iterator) r11
            java.lang.Object r12 = r3.L$5
            java.lang.Integer r12 = (java.lang.Integer) r12
            java.lang.Object r13 = r3.L$4
            androidx.paging.TransformablePage r13 = (androidx.paging.TransformablePage) r13
            java.lang.Object r14 = r3.L$3
            java.util.ArrayList r14 = (java.util.ArrayList) r14
            java.lang.Object r15 = r3.L$2
            java.util.ArrayList r15 = (java.util.ArrayList) r15
            java.lang.Object r7 = r3.L$1
            androidx.paging.PageEvent$Insert r7 = (androidx.paging.PageEvent.Insert) r7
            java.lang.Object r8 = r3.L$0
            androidx.paging.SeparatorState r8 = (androidx.paging.SeparatorState) r8
            kotlin.ResultKt.throwOnFailure(r2)
        L_0x00d7:
            r21 = r14
            r14 = r8
            r8 = r11
            r11 = r21
            goto L_0x06b0
        L_0x00df:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$8
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            java.lang.Object r7 = r3.L$7
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            java.lang.Object r8 = r3.L$6
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r3.L$5
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$4
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$3
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$2
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.PageEvent$Insert r13 = (androidx.paging.PageEvent.Insert) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.SeparatorState r14 = (androidx.paging.SeparatorState) r14
            kotlin.ResultKt.throwOnFailure(r2)
            r0 = r3
            r15 = r11
            r18 = r12
            r19 = r13
            r20 = r14
            r3 = r2
            r11 = r7
            r12 = r8
            r13 = r9
            r14 = r10
            r8 = r4
            r9 = r5
            r10 = r6
            goto L_0x05fe
        L_0x011c:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$7
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r7 = r3.L$6
            java.lang.Integer r7 = (java.lang.Integer) r7
            java.lang.Object r8 = r3.L$5
            androidx.paging.TransformablePage r8 = (androidx.paging.TransformablePage) r8
            java.lang.Object r9 = r3.L$4
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$3
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            java.lang.Object r11 = r3.L$2
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$1
            androidx.paging.PageEvent$Insert r12 = (androidx.paging.PageEvent.Insert) r12
            java.lang.Object r13 = r3.L$0
            androidx.paging.SeparatorState r13 = (androidx.paging.SeparatorState) r13
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0569
        L_0x0145:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$8
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            java.lang.Object r7 = r3.L$7
            java.lang.Integer r7 = (java.lang.Integer) r7
            java.lang.Object r8 = r3.L$6
            androidx.paging.TransformablePage r8 = (androidx.paging.TransformablePage) r8
            java.lang.Object r9 = r3.L$5
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$4
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$3
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$2
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.PageEvent$Insert r13 = (androidx.paging.PageEvent.Insert) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.SeparatorState r14 = (androidx.paging.SeparatorState) r14
            kotlin.ResultKt.throwOnFailure(r2)
            r0 = r3
            r15 = r7
            r18 = r13
            r19 = r14
            r3 = r2
            r13 = r11
            r14 = r12
            r11 = r9
            r12 = r10
            r9 = r5
            r10 = r8
            r8 = r4
            r4 = r6
            goto L_0x0502
        L_0x0181:
            int r1 = r3.I$2
            int r5 = r3.I$1
            int r6 = r3.I$0
            boolean r7 = r3.Z$0
            java.lang.Object r8 = r3.L$8
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            java.lang.Object r9 = r3.L$7
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$6
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$5
            java.lang.Integer r11 = (java.lang.Integer) r11
            java.lang.Object r12 = r3.L$4
            androidx.paging.TransformablePage r12 = (androidx.paging.TransformablePage) r12
            java.lang.Object r13 = r3.L$3
            java.util.ArrayList r13 = (java.util.ArrayList) r13
            java.lang.Object r14 = r3.L$2
            java.util.ArrayList r14 = (java.util.ArrayList) r14
            java.lang.Object r15 = r3.L$1
            androidx.paging.PageEvent$Insert r15 = (androidx.paging.PageEvent.Insert) r15
            r24 = r1
            java.lang.Object r1 = r3.L$0
            androidx.paging.SeparatorState r1 = (androidx.paging.SeparatorState) r1
            kotlin.ResultKt.throwOnFailure(r2)
            r0 = r7
            r7 = r9
            r9 = r11
            r11 = r13
            r13 = r15
            r15 = r1
            r1 = r24
            goto L_0x048e
        L_0x01bc:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$9
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r3.L$8
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            java.lang.Object r8 = r3.L$7
            java.lang.Integer r8 = (java.lang.Integer) r8
            java.lang.Object r9 = r3.L$6
            androidx.paging.TransformablePage r9 = (androidx.paging.TransformablePage) r9
            java.lang.Object r10 = r3.L$5
            java.lang.Integer r10 = (java.lang.Integer) r10
            java.lang.Object r11 = r3.L$4
            androidx.paging.TransformablePage r11 = (androidx.paging.TransformablePage) r11
            java.lang.Object r12 = r3.L$3
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r13 = r3.L$2
            java.util.ArrayList r13 = (java.util.ArrayList) r13
            java.lang.Object r14 = r3.L$1
            androidx.paging.PageEvent$Insert r14 = (androidx.paging.PageEvent.Insert) r14
            java.lang.Object r15 = r3.L$0
            androidx.paging.SeparatorState r15 = (androidx.paging.SeparatorState) r15
            kotlin.ResultKt.throwOnFailure(r2)
            r0 = r5
            r5 = r6
            r18 = r13
            r19 = r15
            r6 = r2
            r2 = r8
            r13 = r11
            r15 = r12
            r8 = r7
            r11 = r9
            r12 = r10
            goto L_0x0412
        L_0x01fa:
            java.lang.Object r1 = r3.L$1
            androidx.paging.PageEvent$Insert r1 = (androidx.paging.PageEvent.Insert) r1
            java.lang.Object r3 = r3.L$0
            androidx.paging.SeparatorState r3 = (androidx.paging.SeparatorState) r3
            kotlin.ResultKt.throwOnFailure(r2)
            r4 = 0
            r5 = 1
            goto L_0x02dc
        L_0x0209:
            kotlin.ResultKt.throwOnFailure(r2)
            androidx.paging.TerminalSeparatorType r2 = r0.terminalSeparatorType
            boolean r2 = r0.terminatesStart(r1, r2)
            androidx.paging.TerminalSeparatorType r5 = r0.terminalSeparatorType
            boolean r5 = r0.terminatesEnd(r1, r5)
            java.util.List r6 = r24.getPages()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            boolean r7 = r6 instanceof java.util.Collection
            if (r7 == 0) goto L_0x022d
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x022d
        L_0x022b:
            r6 = 1
            goto L_0x0250
        L_0x022d:
            java.util.Iterator r6 = r6.iterator()
        L_0x0231:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x022b
            java.lang.Object r7 = r6.next()
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            java.util.List r7 = r7.getData()
            boolean r7 = r7.isEmpty()
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L_0x0231
            r6 = 0
        L_0x0250:
            boolean r7 = r0.headerAdded
            if (r7 == 0) goto L_0x0261
            androidx.paging.LoadType r7 = r24.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.PREPEND
            if (r7 != r8) goto L_0x0261
            if (r6 == 0) goto L_0x025f
            goto L_0x0261
        L_0x025f:
            r7 = 0
            goto L_0x0262
        L_0x0261:
            r7 = 1
        L_0x0262:
            if (r7 == 0) goto L_0x0883
            boolean r7 = r0.footerAdded
            if (r7 == 0) goto L_0x0275
            androidx.paging.LoadType r7 = r24.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.APPEND
            if (r7 != r8) goto L_0x0275
            if (r6 == 0) goto L_0x0273
            goto L_0x0275
        L_0x0273:
            r7 = 0
            goto L_0x0276
        L_0x0275:
            r7 = 1
        L_0x0276:
            if (r7 == 0) goto L_0x0875
            androidx.paging.MutableLoadStateCollection r7 = r0.loadStates
            androidx.paging.CombinedLoadStates r8 = r24.getCombinedLoadStates()
            r7.set(r8)
            androidx.paging.LoadType r7 = r24.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.APPEND
            if (r7 == r8) goto L_0x028f
            int r7 = r24.getPlaceholdersBefore()
            r0.placeholdersBefore = r7
        L_0x028f:
            androidx.paging.LoadType r7 = r24.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.PREPEND
            if (r7 == r8) goto L_0x029d
            int r7 = r24.getPlaceholdersAfter()
            r0.placeholdersAfter = r7
        L_0x029d:
            if (r6 == 0) goto L_0x032a
            if (r2 != 0) goto L_0x02a8
            if (r5 != 0) goto L_0x02a8
            androidx.paging.PageEvent$Insert r1 = r23.asRType(r24)
            return r1
        L_0x02a8:
            boolean r7 = r0.headerAdded
            if (r7 == 0) goto L_0x02b5
            boolean r7 = r0.footerAdded
            if (r7 == 0) goto L_0x02b5
            androidx.paging.PageEvent$Insert r1 = r23.asRType(r24)
            return r1
        L_0x02b5:
            java.util.List<androidx.paging.TransformablePage<T>> r7 = r0.pageStash
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x032a
            if (r2 == 0) goto L_0x0312
            if (r5 == 0) goto L_0x0312
            boolean r6 = r0.headerAdded
            if (r6 != 0) goto L_0x0312
            boolean r6 = r0.footerAdded
            if (r6 != 0) goto L_0x0312
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r2 = r0.generator
            r3.L$0 = r0
            r3.L$1 = r1
            r5 = 1
            r3.label = r5
            r6 = 0
            java.lang.Object r2 = r2.invoke(r6, r6, r3)
            if (r2 != r4) goto L_0x02da
            return r4
        L_0x02da:
            r3 = r0
            r4 = 0
        L_0x02dc:
            r3.endTerminalSeparatorDeferred = r4
            r3.startTerminalSeparatorDeferred = r4
            r3.headerAdded = r5
            r3.footerAdded = r5
            if (r2 != 0) goto L_0x02eb
            androidx.paging.PageEvent$Insert r1 = r3.asRType(r1)
            goto L_0x0311
        L_0x02eb:
            androidx.paging.LoadType r3 = r1.getLoadType()
            r1.getPages()
            int[] r5 = new int[r5]
            r5[r4] = r4
            androidx.paging.TransformablePage r2 = androidx.paging.SeparatorsKt.separatorPage(r2, r5, r4, r4)
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r2)
            int r5 = r1.getPlaceholdersBefore()
            int r6 = r1.getPlaceholdersAfter()
            androidx.paging.CombinedLoadStates r7 = r1.getCombinedLoadStates()
            r8 = 0
            androidx.paging.PageEvent$Insert r1 = new androidx.paging.PageEvent$Insert
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
        L_0x0311:
            return r1
        L_0x0312:
            if (r5 == 0) goto L_0x031c
            boolean r3 = r0.footerAdded
            if (r3 != 0) goto L_0x031c
            r3 = 1
            r0.endTerminalSeparatorDeferred = r3
            goto L_0x031d
        L_0x031c:
            r3 = 1
        L_0x031d:
            if (r2 == 0) goto L_0x0325
            boolean r2 = r0.headerAdded
            if (r2 != 0) goto L_0x0325
            r0.startTerminalSeparatorDeferred = r3
        L_0x0325:
            androidx.paging.PageEvent$Insert r1 = r23.asRType(r24)
            return r1
        L_0x032a:
            java.util.ArrayList r13 = new java.util.ArrayList
            java.util.List r7 = r24.getPages()
            int r7 = r7.size()
            r13.<init>(r7)
            java.util.ArrayList r12 = new java.util.ArrayList
            java.util.List r7 = r24.getPages()
            int r7 = r7.size()
            r12.<init>(r7)
            r7 = 0
            r8 = r7
            androidx.paging.TransformablePage r8 = (androidx.paging.TransformablePage) r8
            r9 = r7
            java.lang.Integer r9 = (java.lang.Integer) r9
            if (r6 != 0) goto L_0x03b3
            r7 = 0
        L_0x034e:
            java.util.List r8 = r24.getPages()
            int r8 = kotlin.collections.CollectionsKt.getLastIndex(r8)
            if (r7 >= r8) goto L_0x036f
            java.util.List r8 = r24.getPages()
            java.lang.Object r8 = r8.get(r7)
            androidx.paging.TransformablePage r8 = (androidx.paging.TransformablePage) r8
            java.util.List r8 = r8.getData()
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L_0x036f
            int r7 = r7 + 1
            goto L_0x034e
        L_0x036f:
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            java.util.List r9 = r24.getPages()
            java.lang.Object r7 = r9.get(r7)
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            java.util.List r9 = r24.getPages()
            int r9 = kotlin.collections.CollectionsKt.getLastIndex(r9)
        L_0x0385:
            if (r9 <= 0) goto L_0x039e
            java.util.List r10 = r24.getPages()
            java.lang.Object r10 = r10.get(r9)
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.util.List r10 = r10.getData()
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x039e
            int r9 = r9 + -1
            goto L_0x0385
        L_0x039e:
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            java.util.List r11 = r24.getPages()
            java.lang.Object r9 = r11.get(r9)
            androidx.paging.TransformablePage r9 = (androidx.paging.TransformablePage) r9
            r11 = r7
            r21 = r10
            r10 = r8
            r8 = r21
            goto L_0x03b7
        L_0x03b3:
            r11 = r8
            r10 = r9
            r8 = r10
            r9 = r11
        L_0x03b7:
            if (r2 == 0) goto L_0x0438
            boolean r2 = r0.headerAdded
            if (r2 != 0) goto L_0x0438
            r2 = 1
            r0.headerAdded = r2
            if (r6 == 0) goto L_0x03cc
            java.util.List<androidx.paging.TransformablePage<T>> r2 = r0.pageStash
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r2)
            androidx.paging.TransformablePage r2 = (androidx.paging.TransformablePage) r2
            r7 = r2
            goto L_0x03d0
        L_0x03cc:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            r7 = r11
        L_0x03d0:
            r2 = r13
            java.util.List r2 = (java.util.List) r2
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r14 = r0.generator
            java.util.List r15 = r7.getData()
            java.lang.Object r15 = kotlin.collections.CollectionsKt.first(r15)
            r3.L$0 = r0
            r3.L$1 = r1
            r3.L$2 = r13
            r3.L$3 = r12
            r3.L$4 = r11
            r3.L$5 = r10
            r3.L$6 = r9
            r3.L$7 = r8
            r3.L$8 = r7
            r3.L$9 = r2
            r3.Z$0 = r5
            r3.I$0 = r6
            r0 = 2
            r3.label = r0
            r0 = 0
            java.lang.Object r14 = r14.invoke(r0, r15, r3)
            if (r14 != r4) goto L_0x0400
            return r4
        L_0x0400:
            r19 = r23
            r0 = r5
            r15 = r12
            r18 = r13
            r5 = r2
            r2 = r8
            r12 = r10
            r13 = r11
            r8 = r7
            r11 = r9
            r21 = r14
            r14 = r1
            r1 = r6
            r6 = r21
        L_0x0412:
            r7 = 0
            int r9 = r8.getHintOriginalPageOffset()
            java.util.List r10 = r8.getHintOriginalIndices()
            if (r10 == 0) goto L_0x042a
            java.lang.Object r10 = kotlin.collections.CollectionsKt.first(r10)
            java.lang.Integer r10 = (java.lang.Integer) r10
            if (r10 == 0) goto L_0x042a
            int r10 = r10.intValue()
            goto L_0x042b
        L_0x042a:
            r10 = 0
        L_0x042b:
            androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, r7, r8, r9, r10)
            r5 = r0
            r6 = r1
            r8 = r2
            r9 = r11
            r10 = r12
            r11 = r13
            r12 = r15
            r13 = r18
            goto L_0x043b
        L_0x0438:
            r19 = r23
            r14 = r1
        L_0x043b:
            if (r6 != 0) goto L_0x07c2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            int r0 = r10.intValue()
            r1 = r0
            r0 = r5
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r19
            r5 = 0
        L_0x0450:
            if (r5 >= r1) goto L_0x0498
            java.util.List r2 = r13.getPages()
            java.lang.Object r2 = r2.get(r5)
            androidx.paging.TransformablePage r2 = (androidx.paging.TransformablePage) r2
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r15 = r14.generator
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r8
            r3.L$7 = r7
            r3.L$8 = r12
            r18 = r7
            r7 = 0
            r3.L$9 = r7
            r3.Z$0 = r0
            r3.I$0 = r6
            r3.I$1 = r5
            r3.I$2 = r1
            r7 = 3
            r3.label = r7
            java.lang.Object r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r2, r15, r3)
            if (r2 != r4) goto L_0x0487
            return r4
        L_0x0487:
            r15 = r14
            r7 = r18
            r14 = r12
            r12 = r10
            r10 = r8
            r8 = r14
        L_0x048e:
            r8.add(r2)
            r2 = 1
            int r5 = r5 + r2
            r8 = r10
            r10 = r12
            r12 = r14
            r14 = r15
            goto L_0x0450
        L_0x0498:
            r18 = r7
            r2 = 1
            androidx.paging.LoadType r1 = r13.getLoadType()
            androidx.paging.LoadType r5 = androidx.paging.LoadType.APPEND
            if (r1 != r5) goto L_0x052f
            java.util.List<androidx.paging.TransformablePage<T>> r1 = r14.pageStash
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ r2
            if (r1 == 0) goto L_0x052f
            java.util.List<androidx.paging.TransformablePage<T>> r1 = r14.pageStash
            java.lang.Object r1 = kotlin.collections.CollectionsKt.last(r1)
            androidx.paging.TransformablePage r1 = (androidx.paging.TransformablePage) r1
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r2 = r14.generator
            java.util.List r5 = r1.getData()
            java.lang.Object r5 = kotlin.collections.CollectionsKt.last(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            java.util.List r7 = r10.getData()
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r7)
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r8
            r15 = r18
            r3.L$7 = r15
            r3.L$8 = r1
            r24 = r1
            r1 = 0
            r3.L$9 = r1
            r3.Z$0 = r0
            r3.I$0 = r6
            r1 = 4
            r3.label = r1
            java.lang.Object r2 = r2.invoke(r5, r7, r3)
            if (r2 != r4) goto L_0x04f2
            return r4
        L_0x04f2:
            r1 = r6
            r18 = r13
            r19 = r14
            r13 = r11
            r14 = r12
            r11 = r9
            r12 = r10
            r9 = r0
            r0 = r3
            r10 = r8
            r3 = r2
            r8 = r4
            r4 = r24
        L_0x0502:
            r2 = r14
            java.util.List r2 = (java.util.List) r2
            int r6 = r12.getHintOriginalPageOffset()
            java.util.List r5 = r12.getHintOriginalIndices()
            if (r5 == 0) goto L_0x051d
            java.lang.Object r5 = kotlin.collections.CollectionsKt.first(r5)
            java.lang.Integer r5 = (java.lang.Integer) r5
            if (r5 == 0) goto L_0x051d
            int r5 = r5.intValue()
            r7 = r5
            goto L_0x051e
        L_0x051d:
            r7 = 0
        L_0x051e:
            r5 = r12
            androidx.paging.SeparatorsKt.addSeparatorPage(r2, r3, r4, r5, r6, r7)
            r3 = r0
            r4 = r8
            r5 = r9
            r8 = r10
            r9 = r11
            r10 = r13
            r6 = r14
            r7 = r15
            r13 = r18
            r14 = r19
            goto L_0x0537
        L_0x052f:
            r15 = r18
            r5 = r0
            r1 = r6
            r6 = r12
            r7 = r15
            r12 = r10
            r10 = r11
        L_0x0537:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)
            androidx.paging.TransformablePage r0 = r14.transformablePageToStash(r12)
            r10.add(r0)
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r14.generator
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r6
            r3.L$3 = r10
            r3.L$4 = r9
            r3.L$5 = r8
            r3.L$6 = r7
            r3.L$7 = r6
            r2 = 0
            r3.L$8 = r2
            r3.L$9 = r2
            r3.Z$0 = r5
            r3.I$0 = r1
            r2 = 5
            r3.label = r2
            java.lang.Object r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r12, r0, r3)
            if (r2 != r4) goto L_0x0566
            return r4
        L_0x0566:
            r11 = r6
            r12 = r13
            r13 = r14
        L_0x0569:
            r6.add(r2)
            java.util.List r0 = r12.getPages()
            int r2 = r9.intValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            int r6 = r7.intValue()
            r9 = 1
            int r6 = r6 + r9
            java.util.List r0 = r0.subList(r2, r6)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x07b8
            java.lang.Object r2 = r0.next()
            r9 = r7
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r8
            r8 = r0
        L_0x0598:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x06ca
            java.lang.Object r0 = r8.next()
            r7 = r0
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            r6 = r2
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            java.util.List r0 = r7.getData()
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r2 = 1
            r0 = r0 ^ r2
            if (r0 == 0) goto L_0x0662
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r14.generator
            java.util.List r2 = r6.getData()
            java.lang.Object r2 = kotlin.collections.CollectionsKt.last(r2)
            java.util.List r15 = r7.getData()
            java.lang.Object r15 = kotlin.collections.CollectionsKt.first(r15)
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r8
            r3.L$7 = r7
            r3.L$8 = r6
            r24 = r6
            r6 = 0
            r3.L$9 = r6
            r3.Z$0 = r5
            r3.I$0 = r1
            r6 = 6
            r3.label = r6
            java.lang.Object r2 = r0.invoke(r2, r15, r3)
            if (r2 != r4) goto L_0x05ed
            return r4
        L_0x05ed:
            r0 = r3
            r15 = r11
            r18 = r12
            r19 = r13
            r20 = r14
            r3 = r2
            r11 = r7
            r12 = r8
            r13 = r9
            r14 = r10
            r10 = r24
            r8 = r4
            r9 = r5
        L_0x05fe:
            r2 = r18
            java.util.List r2 = (java.util.List) r2
            androidx.paging.LoadType r4 = r19.getLoadType()
            androidx.paging.LoadType r5 = androidx.paging.LoadType.PREPEND
            if (r4 != r5) goto L_0x060f
            int r4 = r10.getHintOriginalPageOffset()
            goto L_0x0613
        L_0x060f:
            int r4 = r11.getHintOriginalPageOffset()
        L_0x0613:
            r6 = r4
            androidx.paging.LoadType r4 = r19.getLoadType()
            androidx.paging.LoadType r5 = androidx.paging.LoadType.PREPEND
            if (r4 != r5) goto L_0x0639
            java.util.List r4 = r10.getHintOriginalIndices()
            if (r4 == 0) goto L_0x062f
            java.lang.Object r4 = kotlin.collections.CollectionsKt.last(r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            if (r4 == 0) goto L_0x062f
            int r4 = r4.intValue()
            goto L_0x0637
        L_0x062f:
            java.util.List r4 = r10.getData()
            int r4 = kotlin.collections.CollectionsKt.getLastIndex(r4)
        L_0x0637:
            r7 = r4
            goto L_0x064d
        L_0x0639:
            java.util.List r4 = r11.getHintOriginalIndices()
            if (r4 == 0) goto L_0x064c
            java.lang.Object r4 = kotlin.collections.CollectionsKt.first(r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            if (r4 == 0) goto L_0x064c
            int r4 = r4.intValue()
            goto L_0x0637
        L_0x064c:
            r7 = 0
        L_0x064d:
            r4 = r10
            r5 = r11
            androidx.paging.SeparatorsKt.addSeparatorPage(r2, r3, r4, r5, r6, r7)
            r3 = r0
            r4 = r8
            r5 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r15
            r6 = r18
            r7 = r19
            r8 = r20
            goto L_0x0674
        L_0x0662:
            r24 = r6
            r6 = r12
            r12 = r9
            r9 = r24
            r21 = r10
            r10 = r7
            r7 = r13
            r13 = r21
            r22 = r11
            r11 = r8
            r8 = r14
            r14 = r22
        L_0x0674:
            java.util.List r0 = r10.getData()
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r2 = 1
            r0 = r0 ^ r2
            if (r0 == 0) goto L_0x0689
            androidx.paging.TransformablePage r0 = r8.transformablePageToStash(r10)
            r14.add(r0)
        L_0x0689:
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r8.generator
            r3.L$0 = r8
            r3.L$1 = r7
            r3.L$2 = r6
            r3.L$3 = r14
            r3.L$4 = r13
            r3.L$5 = r12
            r3.L$6 = r11
            r3.L$7 = r10
            r3.L$8 = r9
            r3.L$9 = r6
            r3.Z$0 = r5
            r3.I$0 = r1
            r2 = 7
            r3.label = r2
            java.lang.Object r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r10, r0, r3)
            if (r2 != r4) goto L_0x06ad
            return r4
        L_0x06ad:
            r15 = r6
            goto L_0x00d7
        L_0x06b0:
            r6.add(r2)
            java.util.List r0 = r10.getData()
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r2 = 1
            r0 = r0 ^ r2
            if (r0 == 0) goto L_0x06c3
            r2 = r10
            goto L_0x06c4
        L_0x06c3:
            r2 = r9
        L_0x06c4:
            r9 = r12
            r10 = r13
            r12 = r15
            r13 = r7
            goto L_0x0598
        L_0x06ca:
            androidx.paging.LoadType r0 = r13.getLoadType()
            androidx.paging.LoadType r2 = androidx.paging.LoadType.PREPEND
            if (r0 != r2) goto L_0x0752
            java.util.List<androidx.paging.TransformablePage<T>> r0 = r14.pageStash
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r2 = 1
            r0 = r0 ^ r2
            if (r0 == 0) goto L_0x0752
            java.util.List<androidx.paging.TransformablePage<T>> r0 = r14.pageStash
            java.lang.Object r0 = kotlin.collections.CollectionsKt.first(r0)
            r6 = r0
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r14.generator
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            java.util.List r2 = r10.getData()
            java.lang.Object r2 = kotlin.collections.CollectionsKt.last(r2)
            java.util.List r7 = r6.getData()
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r7)
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r6
            r8 = 0
            r3.L$7 = r8
            r3.L$8 = r8
            r3.L$9 = r8
            r3.Z$0 = r5
            r3.I$0 = r1
            r8 = 8
            r3.label = r8
            java.lang.Object r2 = r0.invoke(r2, r7, r3)
            if (r2 != r4) goto L_0x009b
            return r4
        L_0x0720:
            r2 = r13
            java.util.List r2 = (java.util.List) r2
            int r6 = r11.getHintOriginalPageOffset()
            java.util.List r4 = r11.getHintOriginalIndices()
            if (r4 == 0) goto L_0x073a
            java.lang.Object r4 = kotlin.collections.CollectionsKt.last(r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            if (r4 == 0) goto L_0x073a
            int r4 = r4.intValue()
            goto L_0x0742
        L_0x073a:
            java.util.List r4 = r11.getData()
            int r4 = kotlin.collections.CollectionsKt.getLastIndex(r4)
        L_0x0742:
            r7 = r4
            r4 = r11
            androidx.paging.SeparatorsKt.addSeparatorPage(r2, r3, r4, r5, r6, r7)
            r3 = r0
            r6 = r1
            r4 = r8
            r5 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r15
            goto L_0x0753
        L_0x0752:
            r6 = r1
        L_0x0753:
            int r0 = r9.intValue()
            r1 = 1
            int r0 = r0 + r1
            java.util.List r1 = r13.getPages()
            int r1 = kotlin.collections.CollectionsKt.getLastIndex(r1)
            if (r0 > r1) goto L_0x07b1
            r9 = r6
            r15 = r13
            r6 = r14
            r13 = r11
            r11 = r12
            r12 = r10
            r10 = r5
            r5 = r0
        L_0x076b:
            java.util.List r0 = r15.getPages()
            java.lang.Object r0 = r0.get(r5)
            androidx.paging.TransformablePage r0 = (androidx.paging.TransformablePage) r0
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r2 = r6.generator
            r3.L$0 = r6
            r3.L$1 = r15
            r3.L$2 = r11
            r3.L$3 = r13
            r3.L$4 = r12
            r3.L$5 = r11
            r7 = 0
            r3.L$6 = r7
            r3.L$7 = r7
            r3.L$8 = r7
            r3.L$9 = r7
            r3.Z$0 = r10
            r3.I$0 = r9
            r3.I$1 = r5
            r3.I$2 = r1
            r7 = 9
            r3.label = r7
            java.lang.Object r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r0, r2, r3)
            if (r2 != r4) goto L_0x079f
            return r4
        L_0x079f:
            r14 = r11
        L_0x07a0:
            r11.add(r2)
            if (r5 == r1) goto L_0x07a9
            int r5 = r5 + 1
            r11 = r14
            goto L_0x076b
        L_0x07a9:
            r0 = r3
            r3 = r6
            r6 = r9
            r5 = r10
            r9 = r12
            r7 = r14
            r8 = r15
            goto L_0x07c8
        L_0x07b1:
            r0 = r3
            r9 = r10
            r7 = r12
            r8 = r13
            r3 = r14
            r13 = r11
            goto L_0x07c8
        L_0x07b8:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Empty collection can't be reduced."
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x07c2:
            r0 = r3
            r7 = r13
            r8 = r14
            r3 = r19
            r13 = r12
        L_0x07c8:
            if (r5 == 0) goto L_0x0839
            boolean r1 = r3.footerAdded
            if (r1 != 0) goto L_0x0839
            r1 = 1
            r3.footerAdded = r1
            if (r6 == 0) goto L_0x07dc
            java.util.List<androidx.paging.TransformablePage<T>> r1 = r3.pageStash
            java.lang.Object r1 = kotlin.collections.CollectionsKt.last(r1)
            androidx.paging.TransformablePage r1 = (androidx.paging.TransformablePage) r1
            goto L_0x07e0
        L_0x07dc:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            r1 = r9
        L_0x07e0:
            r2 = r7
            java.util.List r2 = (java.util.List) r2
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r5 = r3.generator
            java.util.List r6 = r1.getData()
            java.lang.Object r6 = kotlin.collections.CollectionsKt.last(r6)
            r0.L$0 = r3
            r0.L$1 = r8
            r0.L$2 = r7
            r0.L$3 = r13
            r0.L$4 = r1
            r0.L$5 = r2
            r9 = 0
            r0.L$6 = r9
            r0.L$7 = r9
            r0.L$8 = r9
            r0.L$9 = r9
            r10 = 10
            r0.label = r10
            java.lang.Object r0 = r5.invoke(r6, r9, r0)
            if (r0 != r4) goto L_0x080d
            return r4
        L_0x080d:
            r15 = r0
            r16 = r1
            r14 = r2
            r5 = r13
        L_0x0812:
            r17 = 0
            int r18 = r16.getHintOriginalPageOffset()
            java.util.List r0 = r16.getHintOriginalIndices()
            if (r0 == 0) goto L_0x082b
            java.lang.Object r0 = kotlin.collections.CollectionsKt.last(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L_0x082b
            int r0 = r0.intValue()
            goto L_0x0833
        L_0x082b:
            java.util.List r0 = r16.getData()
            int r0 = kotlin.collections.CollectionsKt.getLastIndex(r0)
        L_0x0833:
            r19 = r0
            androidx.paging.SeparatorsKt.addSeparatorPage(r14, r15, r16, r17, r18, r19)
            r13 = r5
        L_0x0839:
            r0 = 0
            r3.endTerminalSeparatorDeferred = r0
            r3.startTerminalSeparatorDeferred = r0
            androidx.paging.LoadType r1 = r8.getLoadType()
            androidx.paging.LoadType r2 = androidx.paging.LoadType.APPEND
            if (r1 != r2) goto L_0x084e
            java.util.List<androidx.paging.TransformablePage<T>> r0 = r3.pageStash
            java.util.Collection r13 = (java.util.Collection) r13
            r0.addAll(r13)
            goto L_0x0855
        L_0x084e:
            java.util.List<androidx.paging.TransformablePage<T>> r1 = r3.pageStash
            java.util.Collection r13 = (java.util.Collection) r13
            r1.addAll(r0, r13)
        L_0x0855:
            androidx.paging.LoadType r15 = r8.getLoadType()
            r8.getPages()
            r16 = r7
            java.util.List r16 = (java.util.List) r16
            int r17 = r8.getPlaceholdersBefore()
            int r18 = r8.getPlaceholdersAfter()
            androidx.paging.CombinedLoadStates r19 = r8.getCombinedLoadStates()
            r20 = 0
            androidx.paging.PageEvent$Insert r0 = new androidx.paging.PageEvent$Insert
            r14 = r0
            r14.<init>(r15, r16, r17, r18, r19, r20)
            return r0
        L_0x0875:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Additional append event after append state is done"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0883:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Additional prepend event after prepend state is done"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.onInsert(androidx.paging.PageEvent$Insert, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final PageEvent.Drop<R> onDrop(PageEvent.Drop<T> drop) {
        Intrinsics.checkNotNullParameter(drop, NotificationCompat.CATEGORY_EVENT);
        this.loadStates.set(drop.getLoadType(), false, LoadState.NotLoading.Companion.getIncomplete$paging_common());
        if (drop.getLoadType() == LoadType.PREPEND) {
            this.placeholdersBefore = drop.getPlaceholdersRemaining();
            this.headerAdded = false;
        } else if (drop.getLoadType() == LoadType.APPEND) {
            this.placeholdersAfter = drop.getPlaceholdersRemaining();
            this.footerAdded = false;
        }
        if (this.pageStash.isEmpty()) {
            if (drop.getLoadType() == LoadType.PREPEND) {
                this.startTerminalSeparatorDeferred = false;
            } else {
                this.endTerminalSeparatorDeferred = false;
            }
        }
        CollectionsKt.removeAll(this.pageStash, new SeparatorState$onDrop$1(new IntRange(drop.getMinPageOffset(), drop.getMaxPageOffset())));
        return drop;
    }

    public final Object onLoadStateUpdate(PageEvent.LoadStateUpdate<T> loadStateUpdate, Continuation<? super PageEvent<R>> continuation) {
        PageEvent.Insert insert;
        if (Intrinsics.areEqual((Object) this.loadStates.get(loadStateUpdate.getLoadType(), loadStateUpdate.getFromMediator()), (Object) loadStateUpdate.getLoadState())) {
            Objects.requireNonNull(loadStateUpdate, "null cannot be cast to non-null type androidx.paging.PageEvent<R>");
            return loadStateUpdate;
        }
        this.loadStates.set(loadStateUpdate.getLoadType(), loadStateUpdate.getFromMediator(), loadStateUpdate.getLoadState());
        if (loadStateUpdate.getLoadType() == LoadType.REFRESH || !loadStateUpdate.getFromMediator() || !loadStateUpdate.getLoadState().getEndOfPaginationReached()) {
            Objects.requireNonNull(loadStateUpdate, "null cannot be cast to non-null type androidx.paging.PageEvent<R>");
            return loadStateUpdate;
        }
        if (loadStateUpdate.getLoadType() == LoadType.PREPEND) {
            insert = PageEvent.Insert.Companion.Prepend(CollectionsKt.emptyList(), this.placeholdersBefore, this.loadStates.snapshot());
        } else {
            insert = PageEvent.Insert.Companion.Append(CollectionsKt.emptyList(), this.placeholdersAfter, this.loadStates.snapshot());
        }
        return onInsert(insert, continuation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x002f, code lost:
        r7 = (java.lang.Integer) kotlin.collections.CollectionsKt.first(r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T> androidx.paging.TransformablePage<T> transformablePageToStash(androidx.paging.TransformablePage<T> r9) {
        /*
            r8 = this;
            androidx.paging.TransformablePage r0 = new androidx.paging.TransformablePage
            int[] r1 = r9.getOriginalPageOffsets()
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.util.List r4 = r9.getData()
            java.lang.Object r4 = kotlin.collections.CollectionsKt.first(r4)
            r5 = 0
            r3[r5] = r4
            java.util.List r4 = r9.getData()
            java.lang.Object r4 = kotlin.collections.CollectionsKt.last(r4)
            r6 = 1
            r3[r6] = r4
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            int r4 = r9.getHintOriginalPageOffset()
            java.lang.Integer[] r2 = new java.lang.Integer[r2]
            java.util.List r7 = r9.getHintOriginalIndices()
            if (r7 == 0) goto L_0x003c
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r7)
            java.lang.Integer r7 = (java.lang.Integer) r7
            if (r7 == 0) goto L_0x003c
            int r7 = r7.intValue()
            goto L_0x003d
        L_0x003c:
            r7 = 0
        L_0x003d:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r2[r5] = r7
            java.util.List r5 = r9.getHintOriginalIndices()
            if (r5 == 0) goto L_0x0056
            java.lang.Object r5 = kotlin.collections.CollectionsKt.last(r5)
            java.lang.Integer r5 = (java.lang.Integer) r5
            if (r5 == 0) goto L_0x0056
            int r9 = r5.intValue()
            goto L_0x005e
        L_0x0056:
            java.util.List r9 = r9.getData()
            int r9 = kotlin.collections.CollectionsKt.getLastIndex(r9)
        L_0x005e:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r2[r6] = r9
            java.util.List r9 = kotlin.collections.CollectionsKt.listOf(r2)
            r0.<init>(r1, r3, r4, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.transformablePageToStash(androidx.paging.TransformablePage):androidx.paging.TransformablePage");
    }
}
