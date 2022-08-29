package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata(mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.channels.ReceiveChannel$DefaultImpls", mo28222f = "Channel.kt", mo28223i = {}, mo28224l = {355}, mo28225m = "receiveOrNull", mo28226n = {}, mo28227s = {})
/* compiled from: Channel.kt */
final class ReceiveChannel$receiveOrNull$1<E> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    ReceiveChannel$receiveOrNull$1(Continuation<? super ReceiveChannel$receiveOrNull$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ReceiveChannel.DefaultImpls.receiveOrNull((ReceiveChannel) null, this);
    }
}
