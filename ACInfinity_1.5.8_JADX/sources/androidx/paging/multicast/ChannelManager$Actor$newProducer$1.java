package androidx.paging.multicast;

import androidx.paging.multicast.ChannelManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "p1", "Landroidx/paging/multicast/ChannelManager$Message;", "invoke", "(Landroidx/paging/multicast/ChannelManager$Message;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: ChannelManager.kt */
final /* synthetic */ class ChannelManager$Actor$newProducer$1 extends FunctionReferenceImpl implements Function2<ChannelManager.Message<T>, Continuation<? super Unit>, Object>, SuspendFunction {
    ChannelManager$Actor$newProducer$1(ChannelManager.Actor actor) {
        super(2, actor, ChannelManager.Actor.class, "send", "send(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    public final Object invoke(ChannelManager.Message<T> message, Continuation<? super Unit> continuation) {
        return ((ChannelManager.Actor) this.receiver).send(message, continuation);
    }
}
