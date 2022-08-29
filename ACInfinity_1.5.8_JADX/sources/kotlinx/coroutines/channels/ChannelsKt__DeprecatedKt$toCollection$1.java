package kotlinx.coroutines.channels;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", mo28222f = "Deprecated.kt", mo28223i = {0, 0}, mo28224l = {487}, mo28225m = "toCollection", mo28226n = {"destination", "$this$consume$iv$iv"}, mo28227s = {"L$0", "L$1"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$toCollection$1<E, C extends Collection<? super E>> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__DeprecatedKt$toCollection$1(Continuation<? super ChannelsKt__DeprecatedKt$toCollection$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.toCollection((ReceiveChannel) null, null, this);
    }
}
