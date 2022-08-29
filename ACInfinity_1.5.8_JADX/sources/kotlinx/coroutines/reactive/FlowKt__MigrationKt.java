package kotlinx.coroutines.reactive;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import org.reactivestreams.Publisher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a'\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0007¢\u0006\u0002\b\u0000\u001a'\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007¢\u0006\u0002\b\t¨\u0006\n"}, mo27512d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "", "Lorg/reactivestreams/Publisher;", "batchSize", "", "asFlowDeprecated", "asPublisherDeprecated", "asPublisher", "kotlinx-coroutines-reactive"}, mo27513k = 5, mo27514mv = {1, 4, 2}, mo27517xs = "kotlinx/coroutines/reactive/FlowKt")
/* compiled from: Migration.kt */
final /* synthetic */ class FlowKt__MigrationKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced in favor of ReactiveFlow extension, please import kotlinx.coroutines.reactive.* instead of kotlinx.coroutines.reactive.FlowKt")
    public static final <T> Flow<T> asFlow(Publisher<T> publisher) {
        return ReactiveFlowKt.asFlow(publisher);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced in favor of ReactiveFlow extension, please import kotlinx.coroutines.reactive.* instead of kotlinx.coroutines.reactive.FlowKt")
    public static final <T> Publisher<T> asPublisher(Flow<? extends T> flow) {
        return ReactiveFlowKt.asPublisher$default(flow, (CoroutineContext) null, 1, (Object) null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "batchSize parameter is deprecated, use .buffer() instead to control the backpressure", replaceWith = @ReplaceWith(expression = "asFlow().buffer(batchSize)", imports = {"kotlinx.coroutines.flow.*"}))
    public static final <T> Flow<T> asFlow(Publisher<T> publisher, int i) {
        return FlowKt__ContextKt.buffer$default(ReactiveFlowKt.asFlow(publisher), i, (BufferOverflow) null, 2, (Object) null);
    }
}
