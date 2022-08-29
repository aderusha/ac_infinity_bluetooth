package androidx.paging.multicast;

import androidx.paging.multicast.Buffer;
import androidx.paging.multicast.ChannelManager;
import java.util.ArrayDeque;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016R \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo27512d2 = {"Landroidx/paging/multicast/BufferImpl;", "T", "Landroidx/paging/multicast/Buffer;", "limit", "", "(I)V", "items", "Ljava/util/ArrayDeque;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "getItems", "()Ljava/util/ArrayDeque;", "add", "", "item", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ChannelManager.kt */
final class BufferImpl<T> implements Buffer<T> {
    private final ArrayDeque<ChannelManager.Message.Dispatch.Value<T>> items;
    private final int limit;

    public BufferImpl(int i) {
        this.limit = i;
        this.items = new ArrayDeque<>(RangesKt.coerceAtMost(i, 10));
    }

    public boolean isEmpty() {
        return Buffer.DefaultImpls.isEmpty(this);
    }

    public ArrayDeque<ChannelManager.Message.Dispatch.Value<T>> getItems() {
        return this.items;
    }

    public void add(ChannelManager.Message.Dispatch.Value<T> value) {
        Intrinsics.checkNotNullParameter(value, "item");
        while (getItems().size() >= this.limit) {
            getItems().pollFirst();
        }
        getItems().offerLast(value);
    }
}
