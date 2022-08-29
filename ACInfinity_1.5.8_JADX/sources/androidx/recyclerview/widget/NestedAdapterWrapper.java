package androidx.recyclerview.widget;

import android.view.ViewGroup;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StableIdStorage;
import androidx.recyclerview.widget.ViewTypeStorage;

class NestedAdapterWrapper {
    public final RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;
    private RecyclerView.AdapterDataObserver mAdapterObserver = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            NestedAdapterWrapper nestedAdapterWrapper = NestedAdapterWrapper.this;
            nestedAdapterWrapper.mCachedItemCount = nestedAdapterWrapper.adapter.getItemCount();
            NestedAdapterWrapper.this.mCallback.onChanged(NestedAdapterWrapper.this);
        }

        public void onItemRangeChanged(int i, int i2) {
            NestedAdapterWrapper.this.mCallback.onItemRangeChanged(NestedAdapterWrapper.this, i, i2, (Object) null);
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            NestedAdapterWrapper.this.mCallback.onItemRangeChanged(NestedAdapterWrapper.this, i, i2, obj);
        }

        public void onItemRangeInserted(int i, int i2) {
            NestedAdapterWrapper.this.mCachedItemCount += i2;
            NestedAdapterWrapper.this.mCallback.onItemRangeInserted(NestedAdapterWrapper.this, i, i2);
            if (NestedAdapterWrapper.this.mCachedItemCount > 0 && NestedAdapterWrapper.this.adapter.getStateRestorationPolicy() == RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
            }
        }

        public void onItemRangeRemoved(int i, int i2) {
            NestedAdapterWrapper.this.mCachedItemCount -= i2;
            NestedAdapterWrapper.this.mCallback.onItemRangeRemoved(NestedAdapterWrapper.this, i, i2);
            if (NestedAdapterWrapper.this.mCachedItemCount < 1 && NestedAdapterWrapper.this.adapter.getStateRestorationPolicy() == RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
            }
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            boolean z = true;
            if (i3 != 1) {
                z = false;
            }
            Preconditions.checkArgument(z, "moving more than 1 item is not supported in RecyclerView");
            NestedAdapterWrapper.this.mCallback.onItemRangeMoved(NestedAdapterWrapper.this, i, i2);
        }

        public void onStateRestorationPolicyChanged() {
            NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
        }
    };
    int mCachedItemCount;
    final Callback mCallback;
    private final StableIdStorage.StableIdLookup mStableIdLookup;
    private final ViewTypeStorage.ViewTypeLookup mViewTypeLookup;

    interface Callback {
        void onChanged(NestedAdapterWrapper nestedAdapterWrapper);

        void onItemRangeChanged(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2);

        void onItemRangeChanged(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2, Object obj);

        void onItemRangeInserted(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2);

        void onItemRangeMoved(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2);

        void onItemRangeRemoved(NestedAdapterWrapper nestedAdapterWrapper, int i, int i2);

        void onStateRestorationPolicyChanged(NestedAdapterWrapper nestedAdapterWrapper);
    }

    NestedAdapterWrapper(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter2, Callback callback, ViewTypeStorage viewTypeStorage, StableIdStorage.StableIdLookup stableIdLookup) {
        this.adapter = adapter2;
        this.mCallback = callback;
        this.mViewTypeLookup = viewTypeStorage.createViewTypeWrapper(this);
        this.mStableIdLookup = stableIdLookup;
        this.mCachedItemCount = adapter2.getItemCount();
        adapter2.registerAdapterDataObserver(this.mAdapterObserver);
    }

    /* access modifiers changed from: package-private */
    public void dispose() {
        this.adapter.unregisterAdapterDataObserver(this.mAdapterObserver);
        this.mViewTypeLookup.dispose();
    }

    /* access modifiers changed from: package-private */
    public int getCachedItemCount() {
        return this.mCachedItemCount;
    }

    /* access modifiers changed from: package-private */
    public int getItemViewType(int i) {
        return this.mViewTypeLookup.localToGlobal(this.adapter.getItemViewType(i));
    }

    /* access modifiers changed from: package-private */
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.adapter.onCreateViewHolder(viewGroup, this.mViewTypeLookup.globalToLocal(i));
    }

    /* access modifiers changed from: package-private */
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        this.adapter.bindViewHolder(viewHolder, i);
    }

    public long getItemId(int i) {
        return this.mStableIdLookup.localToGlobal(this.adapter.getItemId(i));
    }
}
