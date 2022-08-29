package androidx.paging.multicast;

import androidx.paging.multicast.ChannelManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@"}, mo27512d2 = {"doRemove", "", "T", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.multicast.ChannelManager$Actor", mo28222f = "ChannelManager.kt", mo28223i = {}, mo28224l = {206}, mo28225m = "doRemove", mo28226n = {}, mo28227s = {})
/* compiled from: ChannelManager.kt */
final class ChannelManager$Actor$doRemove$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ChannelManager.Actor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelManager$Actor$doRemove$1(ChannelManager.Actor actor, Continuation continuation) {
        super(continuation);
        this.this$0 = actor;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doRemove((SendChannel) null, this);
    }
}
