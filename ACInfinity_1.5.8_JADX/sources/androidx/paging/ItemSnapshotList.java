package androidx.paging;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\u0018\u0000*\u0004\b\u0000\u0010\u00012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0002B'\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u0010\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\f¨\u0006\u0013"}, mo27512d2 = {"Landroidx/paging/ItemSnapshotList;", "T", "Lkotlin/collections/AbstractList;", "placeholdersBefore", "", "placeholdersAfter", "items", "", "(IILjava/util/List;)V", "getItems", "()Ljava/util/List;", "getPlaceholdersAfter", "()I", "getPlaceholdersBefore", "size", "getSize", "get", "index", "(I)Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ItemSnapshotList.kt */
public final class ItemSnapshotList<T> extends AbstractList<T> {
    private final List<T> items;
    private final int placeholdersAfter;
    private final int placeholdersBefore;

    public final int getPlaceholdersBefore() {
        return this.placeholdersBefore;
    }

    public final int getPlaceholdersAfter() {
        return this.placeholdersAfter;
    }

    public final List<T> getItems() {
        return this.items;
    }

    public ItemSnapshotList(int i, int i2, List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "items");
        this.placeholdersBefore = i;
        this.placeholdersAfter = i2;
        this.items = list;
    }

    public int getSize() {
        return this.placeholdersBefore + this.items.size() + this.placeholdersAfter;
    }

    public T get(int i) {
        int i2 = this.placeholdersBefore;
        if (i >= 0 && i2 > i) {
            return null;
        }
        int size = this.items.size() + i2;
        if (i2 <= i && size > i) {
            return this.items.get(i - this.placeholdersBefore);
        }
        int size2 = this.placeholdersBefore + this.items.size();
        int size3 = size();
        if (size2 <= i && size3 > i) {
            return null;
        }
        throw new IndexOutOfBoundsException("Illegal attempt to access index " + i + " in ItemSnapshotList of size " + size());
    }
}
