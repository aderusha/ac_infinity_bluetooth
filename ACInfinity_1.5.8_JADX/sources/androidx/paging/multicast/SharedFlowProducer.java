package androidx.paging.multicast;

import androidx.paging.multicast.ChannelManager;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.Flow;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BH\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012(\u0010\u0007\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0006\u0010\u0010\u001a\u00020\u000bJ\u0011\u0010\u0011\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0006\u0010\u0013\u001a\u00020\u000bR\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R5\u0010\u0007\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, mo27512d2 = {"Landroidx/paging/multicast/SharedFlowProducer;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "src", "Lkotlinx/coroutines/flow/Flow;", "sendUpsteamMessage", "Lkotlin/Function2;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)V", "collectionJob", "Lkotlinx/coroutines/Job;", "Lkotlin/jvm/functions/Function2;", "cancel", "cancelAndJoin", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: SharedFlowProducer.kt */
public final class SharedFlowProducer<T> {
    /* access modifiers changed from: private */
    public final Job collectionJob;
    private final CoroutineScope scope;
    /* access modifiers changed from: private */
    public final Function2<ChannelManager.Message.Dispatch<T>, Continuation<? super Unit>, Object> sendUpsteamMessage;
    /* access modifiers changed from: private */
    public final Flow<T> src;

    public SharedFlowProducer(CoroutineScope coroutineScope, Flow<? extends T> flow, Function2<? super ChannelManager.Message.Dispatch<T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        Intrinsics.checkNotNullParameter(flow, "src");
        Intrinsics.checkNotNullParameter(function2, "sendUpsteamMessage");
        this.scope = coroutineScope;
        this.src = flow;
        this.sendUpsteamMessage = function2;
        this.collectionJob = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.LAZY, new SharedFlowProducer$collectionJob$1(this, (Continuation) null), 1, (Object) null);
    }

    public final void start() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new SharedFlowProducer$start$1(this, (Continuation) null), 3, (Object) null);
    }

    public final Object cancelAndJoin(Continuation<? super Unit> continuation) {
        Object cancelAndJoin = JobKt.cancelAndJoin(this.collectionJob, continuation);
        if (cancelAndJoin == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return cancelAndJoin;
        }
        return Unit.INSTANCE;
    }

    public final void cancel() {
        Job.DefaultImpls.cancel$default(this.collectionJob, (CancellationException) null, 1, (Object) null);
    }
}
