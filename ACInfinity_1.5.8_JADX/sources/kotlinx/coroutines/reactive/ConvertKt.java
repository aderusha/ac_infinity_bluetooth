package kotlinx.coroutines.reactive;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.reactivestreams.Publisher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, mo27512d2 = {"asPublisher", "Lorg/reactivestreams/Publisher;", "T", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "kotlinx-coroutines-reactive"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: Convert.kt */
public final class ConvertKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in the favour of consumeAsFlow()", replaceWith = @ReplaceWith(expression = "this.consumeAsFlow().asPublisher()", imports = {}))
    public static final <T> Publisher<T> asPublisher(ReceiveChannel<? extends T> receiveChannel, CoroutineContext coroutineContext) {
        return PublishKt.publish(coroutineContext, new ConvertKt$asPublisher$1(receiveChannel, (Continuation) null));
    }

    public static /* synthetic */ Publisher asPublisher$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asPublisher(receiveChannel, coroutineContext);
    }
}
