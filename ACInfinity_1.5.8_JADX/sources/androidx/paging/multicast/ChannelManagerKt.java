package androidx.paging.multicast;

import androidx.paging.multicast.ChannelManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0018\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0000¨\u0006\b"}, mo27512d2 = {"Buffer", "Landroidx/paging/multicast/Buffer;", "T", "limit", "", "markDelivered", "", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "paging-common"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: ChannelManager.kt */
public final class ChannelManagerKt {
    /* access modifiers changed from: private */
    public static final <T> Buffer<T> Buffer(int i) {
        if (i > 0) {
            return new BufferImpl<>(i);
        }
        return new NoBuffer<>();
    }

    public static final <T> boolean markDelivered(ChannelManager.Message.Dispatch.Value<T> value) {
        Intrinsics.checkNotNullParameter(value, "$this$markDelivered");
        return value.getDelivered().complete(Unit.INSTANCE);
    }
}
