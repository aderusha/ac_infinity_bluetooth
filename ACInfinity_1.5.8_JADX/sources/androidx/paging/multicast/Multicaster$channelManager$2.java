package androidx.paging.multicast;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/multicast/ChannelManager;", "T", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: Multicaster.kt */
final class Multicaster$channelManager$2 extends Lambda implements Function0<ChannelManager<T>> {
    final /* synthetic */ int $bufferSize;
    final /* synthetic */ Multicaster this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Multicaster$channelManager$2(Multicaster multicaster, int i) {
        super(0);
        this.this$0 = multicaster;
        this.$bufferSize = i;
    }

    public final ChannelManager<T> invoke() {
        return new ChannelManager(this.this$0.scope, this.$bufferSize, this.this$0.piggybackingDownstream, this.this$0.onEach, this.this$0.keepUpstreamAlive, this.this$0.source);
    }
}
