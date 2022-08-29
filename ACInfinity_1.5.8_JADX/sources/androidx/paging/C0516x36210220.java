package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "androidx/paging/PagingDataDiffer$collectFrom$2$1$1"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PagingDataDiffer$collectFrom$2$1$1", mo28222f = "PagingDataDiffer.kt", mo28223i = {0, 0}, mo28224l = {142, 180}, mo28225m = "invokeSuspend", mo28226n = {"newPresenter", "onListPresentableCalled"}, mo28227s = {"L$0", "L$1"})
/* renamed from: androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1$lambda$1 */
/* compiled from: PagingDataDiffer.kt */
final class C0516x36210220 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PageEvent $event;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0516x36210220(PageEvent pageEvent, Continuation continuation, PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 pagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1) {
        super(2, continuation);
        this.$event = pageEvent;
        this.this$0 = pagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new C0516x36210220(this.$event, continuation, this.this$0);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((C0516x36210220) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x013f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0028
            if (r1 == r3) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x010f
        L_0x0014:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x001c:
            java.lang.Object r0 = r11.L$1
            kotlin.jvm.internal.Ref$BooleanRef r0 = (kotlin.jvm.internal.Ref.BooleanRef) r0
            java.lang.Object r1 = r11.L$0
            androidx.paging.PagePresenter r1 = (androidx.paging.PagePresenter) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0094
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r12)
            androidx.paging.PageEvent r12 = r11.$event
            boolean r1 = r12 instanceof androidx.paging.PageEvent.Insert
            if (r1 == 0) goto L_0x00fa
            androidx.paging.PageEvent$Insert r12 = (androidx.paging.PageEvent.Insert) r12
            androidx.paging.LoadType r12 = r12.getLoadType()
            androidx.paging.LoadType r1 = androidx.paging.LoadType.REFRESH
            if (r12 != r1) goto L_0x00fa
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            r12.lastAccessedIndexUnfulfilled = r4
            androidx.paging.PagePresenter r1 = new androidx.paging.PagePresenter
            androidx.paging.PageEvent r12 = r11.$event
            androidx.paging.PageEvent$Insert r12 = (androidx.paging.PageEvent.Insert) r12
            r1.<init>(r12)
            kotlin.jvm.internal.Ref$BooleanRef r12 = new kotlin.jvm.internal.Ref$BooleanRef
            r12.<init>()
            r12.element = r4
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r2 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r2 = r2.this$0
            androidx.paging.PagingDataDiffer r4 = r2.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r2 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r2 = r2.this$0
            androidx.paging.PagingDataDiffer r2 = r2.this$0
            androidx.paging.PagePresenter r2 = r2.presenter
            r5 = r2
            androidx.paging.NullPaddedList r5 = (androidx.paging.NullPaddedList) r5
            r6 = r1
            androidx.paging.NullPaddedList r6 = (androidx.paging.NullPaddedList) r6
            androidx.paging.PageEvent r2 = r11.$event
            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
            androidx.paging.CombinedLoadStates r7 = r2.getCombinedLoadStates()
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r2 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r2 = r2.this$0
            androidx.paging.PagingDataDiffer r2 = r2.this$0
            int r8 = r2.lastAccessedIndex
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1$lambda$1$1 r2 = new androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1$lambda$1$1
            r2.<init>(r11, r1, r12)
            r9 = r2
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            r11.L$0 = r1
            r11.L$1 = r12
            r11.label = r3
            r10 = r11
            java.lang.Object r2 = r4.presentNewList(r5, r6, r7, r8, r9, r10)
            if (r2 != r0) goto L_0x0092
            return r0
        L_0x0092:
            r0 = r12
            r12 = r2
        L_0x0094:
            java.lang.Integer r12 = (java.lang.Integer) r12
            boolean r0 = r0.element
            if (r0 == 0) goto L_0x00ec
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r0 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r0 = r0.this$0
            androidx.paging.PagingDataDiffer r0 = r0.this$0
            androidx.paging.PageEvent r2 = r11.$event
            androidx.paging.PageEvent$Insert r2 = (androidx.paging.PageEvent.Insert) r2
            androidx.paging.CombinedLoadStates r2 = r2.getCombinedLoadStates()
            r0.dispatchLoadStates(r2)
            if (r12 != 0) goto L_0x00c4
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            androidx.paging.UiReceiver r12 = r12.receiver
            if (r12 == 0) goto L_0x0250
            androidx.paging.ViewportHint$Initial r0 = r1.initializeHint()
            androidx.paging.ViewportHint r0 = (androidx.paging.ViewportHint) r0
            r12.accessHint(r0)
            goto L_0x0250
        L_0x00c4:
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r0 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r0 = r0.this$0
            androidx.paging.PagingDataDiffer r0 = r0.this$0
            int r2 = r12.intValue()
            r0.lastAccessedIndex = r2
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r0 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r0 = r0.this$0
            androidx.paging.PagingDataDiffer r0 = r0.this$0
            androidx.paging.UiReceiver r0 = r0.receiver
            if (r0 == 0) goto L_0x0250
            int r12 = r12.intValue()
            androidx.paging.ViewportHint$Access r12 = r1.accessHintForPresenterIndex(r12)
            androidx.paging.ViewportHint r12 = (androidx.paging.ViewportHint) r12
            r0.accessHint(r12)
            goto L_0x0250
        L_0x00ec:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "Missing call to onListPresentable after new list was presented. If you are seeing this exception, it is generally an indication of an issue with Paging. Please file a bug so we can fix it at: https://issuetracker.google.com/issues/new?component=413106"
            java.lang.String r0 = r0.toString()
            r12.<init>(r0)
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            throw r12
        L_0x00fa:
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            boolean r12 = r12.postEvents()
            if (r12 == 0) goto L_0x010f
            r11.label = r2
            java.lang.Object r12 = kotlinx.coroutines.YieldKt.yield(r11)
            if (r12 != r0) goto L_0x010f
            return r0
        L_0x010f:
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            androidx.paging.PagePresenter r12 = r12.presenter
            androidx.paging.PageEvent r0 = r11.$event
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r1 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r1 = r1.this$0
            androidx.paging.PagingDataDiffer r1 = r1.this$0
            androidx.paging.PagingDataDiffer$processPageEventCallback$1 r1 = r1.processPageEventCallback
            androidx.paging.PagePresenter$ProcessPageEventCallback r1 = (androidx.paging.PagePresenter.ProcessPageEventCallback) r1
            r12.processEvent(r0, r1)
            androidx.paging.PageEvent r12 = r11.$event
            boolean r12 = r12 instanceof androidx.paging.PageEvent.Drop
            if (r12 == 0) goto L_0x0139
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            r12.lastAccessedIndexUnfulfilled = r4
        L_0x0139:
            androidx.paging.PageEvent r12 = r11.$event
            boolean r0 = r12 instanceof androidx.paging.PageEvent.Insert
            if (r0 == 0) goto L_0x0250
            androidx.paging.PageEvent$Insert r12 = (androidx.paging.PageEvent.Insert) r12
            androidx.paging.CombinedLoadStates r12 = r12.getCombinedLoadStates()
            androidx.paging.LoadState r12 = r12.getPrepend()
            boolean r12 = r12.getEndOfPaginationReached()
            androidx.paging.PageEvent r0 = r11.$event
            androidx.paging.PageEvent$Insert r0 = (androidx.paging.PageEvent.Insert) r0
            androidx.paging.CombinedLoadStates r0 = r0.getCombinedLoadStates()
            androidx.paging.LoadState r0 = r0.getAppend()
            boolean r0 = r0.getEndOfPaginationReached()
            androidx.paging.PageEvent r1 = r11.$event
            androidx.paging.PageEvent$Insert r1 = (androidx.paging.PageEvent.Insert) r1
            androidx.paging.LoadType r1 = r1.getLoadType()
            androidx.paging.LoadType r2 = androidx.paging.LoadType.PREPEND
            if (r1 != r2) goto L_0x016b
            if (r12 != 0) goto L_0x017a
        L_0x016b:
            androidx.paging.PageEvent r12 = r11.$event
            androidx.paging.PageEvent$Insert r12 = (androidx.paging.PageEvent.Insert) r12
            androidx.paging.LoadType r12 = r12.getLoadType()
            androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND
            if (r12 != r1) goto L_0x017c
            if (r0 != 0) goto L_0x017a
            goto L_0x017c
        L_0x017a:
            r12 = 0
            goto L_0x017d
        L_0x017c:
            r12 = 1
        L_0x017d:
            androidx.paging.PageEvent r0 = r11.$event
            androidx.paging.PageEvent$Insert r0 = (androidx.paging.PageEvent.Insert) r0
            java.util.List r0 = r0.getPages()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r1 = r0 instanceof java.util.Collection
            if (r1 == 0) goto L_0x0196
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0196
        L_0x0194:
            r0 = 1
            goto L_0x01b9
        L_0x0196:
            java.util.Iterator r0 = r0.iterator()
        L_0x019a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0194
            java.lang.Object r1 = r0.next()
            androidx.paging.TransformablePage r1 = (androidx.paging.TransformablePage) r1
            java.util.List r1 = r1.getData()
            boolean r1 = r1.isEmpty()
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x019a
            r0 = 0
        L_0x01b9:
            if (r12 != 0) goto L_0x01c6
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            r12.lastAccessedIndexUnfulfilled = r4
            goto L_0x0250
        L_0x01c6:
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            boolean r12 = r12.lastAccessedIndexUnfulfilled
            if (r12 != 0) goto L_0x01d4
            if (r0 == 0) goto L_0x0250
        L_0x01d4:
            if (r0 != 0) goto L_0x021b
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            int r12 = r12.lastAccessedIndex
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r0 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r0 = r0.this$0
            androidx.paging.PagingDataDiffer r0 = r0.this$0
            androidx.paging.PagePresenter r0 = r0.presenter
            int r0 = r0.getPlaceholdersBefore()
            if (r12 < r0) goto L_0x021b
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            int r12 = r12.lastAccessedIndex
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r0 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r0 = r0.this$0
            androidx.paging.PagingDataDiffer r0 = r0.this$0
            androidx.paging.PagePresenter r0 = r0.presenter
            int r0 = r0.getPlaceholdersBefore()
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r1 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r1 = r1.this$0
            androidx.paging.PagingDataDiffer r1 = r1.this$0
            androidx.paging.PagePresenter r1 = r1.presenter
            int r1 = r1.getStorageCount()
            int r0 = r0 + r1
            if (r12 <= r0) goto L_0x021a
            goto L_0x021b
        L_0x021a:
            r3 = 0
        L_0x021b:
            if (r3 == 0) goto L_0x0247
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            androidx.paging.UiReceiver r12 = r12.receiver
            if (r12 == 0) goto L_0x0250
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r0 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r0 = r0.this$0
            androidx.paging.PagingDataDiffer r0 = r0.this$0
            androidx.paging.PagePresenter r0 = r0.presenter
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r1 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r1 = r1.this$0
            androidx.paging.PagingDataDiffer r1 = r1.this$0
            int r1 = r1.lastAccessedIndex
            androidx.paging.ViewportHint$Access r0 = r0.accessHintForPresenterIndex(r1)
            androidx.paging.ViewportHint r0 = (androidx.paging.ViewportHint) r0
            r12.accessHint(r0)
            goto L_0x0250
        L_0x0247:
            androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1 r12 = r11.this$0
            androidx.paging.PagingDataDiffer$collectFrom$2 r12 = r12.this$0
            androidx.paging.PagingDataDiffer r12 = r12.this$0
            r12.lastAccessedIndexUnfulfilled = r4
        L_0x0250:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.C0516x36210220.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
