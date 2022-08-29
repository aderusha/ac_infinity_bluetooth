package androidx.paging.multicast;

import androidx.paging.multicast.ChannelManager;
import java.util.Collection;
import kotlin.Metadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bb\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H&J\b\u0010\u000b\u001a\u00020\fH\u0016R\u001e\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, mo27512d2 = {"Landroidx/paging/multicast/Buffer;", "T", "", "items", "", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "getItems", "()Ljava/util/Collection;", "add", "", "item", "isEmpty", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ChannelManager.kt */
interface Buffer<T> {
    void add(ChannelManager.Message.Dispatch.Value<T> value);

    Collection<ChannelManager.Message.Dispatch.Value<T>> getItems();

    boolean isEmpty();

    @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
    /* compiled from: ChannelManager.kt */
    public static final class DefaultImpls {
        public static <T> boolean isEmpty(Buffer<T> buffer) {
            return buffer.getItems().isEmpty();
        }
    }
}
