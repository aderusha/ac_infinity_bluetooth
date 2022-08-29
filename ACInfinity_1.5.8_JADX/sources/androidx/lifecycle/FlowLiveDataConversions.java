package androidx.lifecycle;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a0\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a2\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, mo27512d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "Landroidx/lifecycle/LiveData;", "asLiveData", "context", "Lkotlin/coroutines/CoroutineContext;", "timeout", "Ljava/time/Duration;", "timeoutInMs", "", "lifecycle-livedata-ktx_release"}, mo27513k = 2, mo27514mv = {1, 1, 15})
/* compiled from: FlowLiveData.kt */
public final class FlowLiveDataConversions {
    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow) {
        return asLiveData$default((Flow) flow, (CoroutineContext) null, 0, 3, (Object) null);
    }

    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow, CoroutineContext coroutineContext) {
        return asLiveData$default((Flow) flow, coroutineContext, 0, 2, (Object) null);
    }

    public static /* synthetic */ LiveData asLiveData$default(Flow flow, CoroutineContext coroutineContext, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            j = 5000;
        }
        return asLiveData(flow, coroutineContext, j);
    }

    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow, CoroutineContext coroutineContext, long j) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$asLiveData");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return CoroutineLiveDataKt.liveData(coroutineContext, j, new FlowLiveDataConversions$asLiveData$1(flow, (Continuation) null));
    }

    public static final <T> Flow<T> asFlow(LiveData<T> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "$this$asFlow");
        return FlowKt.flow(new FlowLiveDataConversions$asFlow$1(liveData, (Continuation) null));
    }

    public static /* synthetic */ LiveData asLiveData$default(Flow flow, CoroutineContext coroutineContext, Duration duration, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asLiveData(flow, coroutineContext, duration);
    }

    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow, CoroutineContext coroutineContext, Duration duration) {
        Intrinsics.checkParameterIsNotNull(flow, "$this$asLiveData");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(duration, "timeout");
        return asLiveData(flow, coroutineContext, duration.toMillis());
    }
}
