package p018me.tatarka.bindingcollectionadapter2.collections;

import androidx.databinding.ListChangeRegistry;
import androidx.databinding.ObservableList;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: me.tatarka.bindingcollectionadapter2.collections.MergeObservableList */
public class MergeObservableList<T> extends AbstractList<T> implements ObservableList<T> {
    private final MergeObservableList<T>.ListChangeCallback callback = new ListChangeCallback();
    /* access modifiers changed from: private */
    public final ListChangeRegistry listeners = new ListChangeRegistry();
    /* access modifiers changed from: private */
    public final ArrayList<List<? extends T>> lists = new ArrayList<>();

    public void addOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback) {
        this.listeners.add(onListChangedCallback);
    }

    public void removeOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback) {
        this.listeners.remove(onListChangedCallback);
    }

    public MergeObservableList<T> insertItem(T t) {
        this.lists.add(Collections.singletonList(t));
        this.modCount++;
        this.listeners.notifyInserted(this, size() - 1, 1);
        return this;
    }

    public MergeObservableList<T> insertList(ObservableList<? extends T> observableList) {
        observableList.addOnListChangedCallback(this.callback);
        int size = size();
        this.lists.add(observableList);
        this.modCount++;
        if (!observableList.isEmpty()) {
            this.listeners.notifyInserted(this, size, observableList.size());
        }
        return this;
    }

    public boolean removeItem(T t) {
        int size = this.lists.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            List list = this.lists.get(i2);
            if (!(list instanceof ObservableList)) {
                Object obj = list.get(0);
                if (t == null) {
                    if (obj == null) {
                    }
                } else if (t.equals(obj)) {
                }
                this.lists.remove(i2);
                this.modCount++;
                this.listeners.notifyRemoved(this, i, 1);
                return true;
            }
            i += list.size();
        }
        return false;
    }

    public boolean removeList(ObservableList<? extends T> observableList) {
        int size = this.lists.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            List list = this.lists.get(i2);
            if (list == observableList) {
                observableList.removeOnListChangedCallback(this.callback);
                this.lists.remove(i2);
                this.modCount++;
                this.listeners.notifyRemoved(this, i, list.size());
                return true;
            }
            i += list.size();
        }
        return false;
    }

    public void removeAll() {
        int size = size();
        if (size != 0) {
            int size2 = this.lists.size();
            for (int i = 0; i < size2; i++) {
                List list = this.lists.get(i);
                if (list instanceof ObservableList) {
                    ((ObservableList) list).removeOnListChangedCallback(this.callback);
                }
            }
            this.lists.clear();
            this.modCount++;
            this.listeners.notifyRemoved(this, 0, size);
        }
    }

    public int mergeToBackingIndex(ObservableList<? extends T> observableList, int i) {
        if (i >= 0) {
            int size = this.lists.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                List list = this.lists.get(i2);
                if (observableList != list) {
                    i3 += list.size();
                    i2++;
                } else if (i < list.size()) {
                    return i3 + i;
                } else {
                    throw new IndexOutOfBoundsException();
                }
            }
            throw new IllegalArgumentException();
        }
        throw new IndexOutOfBoundsException();
    }

    public int backingIndexToMerge(ObservableList<? extends T> observableList, int i) {
        if (i >= 0) {
            int size = this.lists.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                List list = this.lists.get(i3);
                if (observableList == list) {
                    int i4 = i - i2;
                    if (i4 < list.size()) {
                        return i4;
                    }
                    throw new IndexOutOfBoundsException();
                }
                i2 += list.size();
            }
            throw new IllegalArgumentException();
        }
        throw new IndexOutOfBoundsException();
    }

    public T get(int i) {
        if (i >= 0) {
            int size = this.lists.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                List list = this.lists.get(i3);
                int i4 = i - i2;
                if (i4 < list.size()) {
                    return list.get(i4);
                }
                i2 += list.size();
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IndexOutOfBoundsException();
    }

    public int size() {
        int size = this.lists.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.lists.get(i2).size();
        }
        return i;
    }

    /* renamed from: me.tatarka.bindingcollectionadapter2.collections.MergeObservableList$ListChangeCallback */
    class ListChangeCallback extends ObservableList.OnListChangedCallback {
        ListChangeCallback() {
        }

        public void onChanged(ObservableList observableList) {
            MergeObservableList mergeObservableList = MergeObservableList.this;
            int unused = mergeObservableList.modCount = mergeObservableList.modCount + 1;
            MergeObservableList.this.listeners.notifyChanged(MergeObservableList.this);
        }

        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            int size = MergeObservableList.this.lists.size();
            int i3 = 0;
            int i4 = 0;
            while (i3 < size) {
                List list = (List) MergeObservableList.this.lists.get(i3);
                if (list == observableList) {
                    MergeObservableList.this.listeners.notifyChanged(MergeObservableList.this, i4 + i, i2);
                    return;
                } else {
                    i4 += list.size();
                    i3++;
                }
            }
        }

        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            MergeObservableList mergeObservableList = MergeObservableList.this;
            int unused = mergeObservableList.modCount = mergeObservableList.modCount + 1;
            int size = MergeObservableList.this.lists.size();
            int i3 = 0;
            int i4 = 0;
            while (i3 < size) {
                List list = (List) MergeObservableList.this.lists.get(i3);
                if (list == observableList) {
                    MergeObservableList.this.listeners.notifyInserted(MergeObservableList.this, i4 + i, i2);
                    return;
                } else {
                    i4 += list.size();
                    i3++;
                }
            }
        }

        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            int size = MergeObservableList.this.lists.size();
            int i4 = 0;
            int i5 = 0;
            while (i4 < size) {
                List list = (List) MergeObservableList.this.lists.get(i4);
                if (list == observableList) {
                    MergeObservableList.this.listeners.notifyMoved(MergeObservableList.this, i + i5, i5 + i2, i3);
                    return;
                } else {
                    i5 += list.size();
                    i4++;
                }
            }
        }

        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            MergeObservableList mergeObservableList = MergeObservableList.this;
            int unused = mergeObservableList.modCount = mergeObservableList.modCount + 1;
            int size = MergeObservableList.this.lists.size();
            int i3 = 0;
            int i4 = 0;
            while (i3 < size) {
                List list = (List) MergeObservableList.this.lists.get(i3);
                if (list == observableList) {
                    MergeObservableList.this.listeners.notifyRemoved(MergeObservableList.this, i4 + i, i2);
                    return;
                } else {
                    i4 += list.size();
                    i3++;
                }
            }
        }
    }
}
