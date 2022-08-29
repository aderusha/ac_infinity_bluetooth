package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aS\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042-\u0010\u0005\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006¢\u0006\u0002\b\u000bH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, mo27512d2 = {"cancelableChannelFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "controller", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function2;", "Landroidx/paging/SimpleProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "paging-common"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: CancelableChannelFlow.kt */
public final class CancelableChannelFlowKt {
    public static final <T> Flow<T> cancelableChannelFlow(Job job, Function2<? super SimpleProducerScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(job, "controller");
        Intrinsics.checkNotNullParameter(function2, "block");
        return SimpleChannelFlowKt.simpleChannelFlow(new CancelableChannelFlowKt$cancelableChannelFlow$1(job, function2, (Continuation) null));
    }
}
