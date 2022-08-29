package p018me.tatarka.bindingcollectionadapter2.collections;

import androidx.databinding.ListChangeRegistry;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: me.tatarka.bindingcollectionadapter2.collections.DiffObservableList */
public class DiffObservableList<T> extends AbstractList<T> implements ObservableList<T> {
    private final Object LIST_LOCK;
    /* access modifiers changed from: private */
    public final Callback<T> callback;
    private final boolean detectMoves;
    private List<T> list;
    private final DiffObservableList<T>.ObservableListUpdateCallback listCallback;
    /* access modifiers changed from: private */
    public final ListChangeRegistry listeners;

    /* renamed from: me.tatarka.bindingcollectionadapter2.collections.DiffObservableList$Callback */
    public interface Callback<T> {
        boolean areContentsTheSame(T t, T t2);

        boolean areItemsTheSame(T t, T t2);
    }

    public DiffObservableList(Callback<T> callback2) {
        this(callback2, true);
    }

    public DiffObservableList(Callback<T> callback2, boolean z) {
        this.LIST_LOCK = new Object();
        this.list = Collections.emptyList();
        this.listeners = new ListChangeRegistry();
        this.listCallback = new ObservableListUpdateCallback();
        this.callback = callback2;
        this.detectMoves = z;
    }

    public DiffUtil.DiffResult calculateDiff(List<T> list2) {
        ArrayList arrayList;
        synchronized (this.LIST_LOCK) {
            arrayList = new ArrayList(this.list);
        }
        return doCalculateDiff(arrayList, list2);
    }

    private DiffUtil.DiffResult doCalculateDiff(final List<T> list2, final List<T> list3) {
        return DiffUtil.calculateDiff(new DiffUtil.Callback() {
            public int getOldListSize() {
                return list2.size();
            }

            public int getNewListSize() {
                List list = list3;
                if (list != null) {
                    return list.size();
                }
                return 0;
            }

            public boolean areItemsTheSame(int i, int i2) {
                return DiffObservableList.this.callback.areItemsTheSame(list2.get(i), list3.get(i2));
            }

            public boolean areContentsTheSame(int i, int i2) {
                return DiffObservableList.this.callback.areContentsTheSame(list2.get(i), list3.get(i2));
            }
        }, this.detectMoves);
    }

    public void update(List<T> list2, DiffUtil.DiffResult diffResult) {
        synchronized (this.LIST_LOCK) {
            this.list = list2;
        }
        diffResult.dispatchUpdatesTo((ListUpdateCallback) this.listCallback);
    }

    public void update(List<T> list2) {
        DiffUtil.DiffResult doCalculateDiff = doCalculateDiff(this.list, list2);
        this.list = list2;
        doCalculateDiff.dispatchUpdatesTo((ListUpdateCallback) this.listCallback);
    }

    public void addOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback) {
        this.listeners.add(onListChangedCallback);
    }

    public void removeOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback) {
        this.listeners.remove(onListChangedCallback);
    }

    public T get(int i) {
        return this.list.get(i);
    }

    public int size() {
        return this.list.size();
    }

    /* renamed from: me.tatarka.bindingcollectionadapter2.collections.DiffObservableList$ObservableListUpdateCallback */
    class ObservableListUpdateCallback implements ListUpdateCallback {
        ObservableListUpdateCallback() {
        }

        public void onChanged(int i, int i2, Object obj) {
            DiffObservableList.this.listeners.notifyChanged(DiffObservableList.this, i, i2);
        }

        public void onInserted(int i, int i2) {
            DiffObservableList diffObservableList = DiffObservableList.this;
            int unused = diffObservableList.modCount = diffObservableList.modCount + 1;
            DiffObservableList.this.listeners.notifyInserted(DiffObservableList.this, i, i2);
        }

        public void onRemoved(int i, int i2) {
            DiffObservableList diffObservableList = DiffObservableList.this;
            int unused = diffObservableList.modCount = diffObservableList.modCount + 1;
            DiffObservableList.this.listeners.notifyRemoved(DiffObservableList.this, i, i2);
        }

        public void onMoved(int i, int i2) {
            DiffObservableList.this.listeners.notifyMoved(DiffObservableList.this, i, i2, 1);
        }
    }
}
