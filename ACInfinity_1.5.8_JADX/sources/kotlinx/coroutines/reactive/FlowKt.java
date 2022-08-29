package kotlinx.coroutines.reactive;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlinx.coroutines.flow.Flow;
import org.reactivestreams.Publisher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"kotlinx/coroutines/reactive/FlowKt__MigrationKt"}, mo27513k = 4, mo27514mv = {1, 4, 2})
/* compiled from: Migration.kt */
public final class FlowKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced in favor of ReactiveFlow extension, please import kotlinx.coroutines.reactive.* instead of kotlinx.coroutines.reactive.FlowKt")
    public static final <T> Flow<T> asFlow(Publisher<T> publisher) {
        return FlowKt__MigrationKt.asFlow(publisher);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "batchSize parameter is deprecated, use .buffer() instead to control the backpressure", replaceWith = @ReplaceWith(expression = "asFlow().buffer(batchSize)", imports = {"kotlinx.coroutines.flow.*"}))
    public static final <T> Flow<T> asFlow(Publisher<T> publisher, int i) {
        return FlowKt__MigrationKt.asFlow(publisher, i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced in favor of ReactiveFlow extension, please import kotlinx.coroutines.reactive.* instead of kotlinx.coroutines.reactive.FlowKt")
    public static final <T> Publisher<T> asPublisher(Flow<? extends T> flow) {
        return FlowKt__MigrationKt.asPublisher(flow);
    }
}
