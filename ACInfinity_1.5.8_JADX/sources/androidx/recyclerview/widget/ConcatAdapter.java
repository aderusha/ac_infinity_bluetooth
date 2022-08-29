package androidx.recyclerview.widget;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ConcatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static final String TAG = "ConcatAdapter";
    private final ConcatAdapterController mController;

    @SafeVarargs
    public ConcatAdapter(RecyclerView.Adapter<? extends RecyclerView.ViewHolder>... adapterArr) {
        this(C0594Config.DEFAULT, adapterArr);
    }

    @SafeVarargs
    public ConcatAdapter(C0594Config config, RecyclerView.Adapter<? extends RecyclerView.ViewHolder>... adapterArr) {
        this(config, (List<? extends RecyclerView.Adapter<? extends RecyclerView.ViewHolder>>) Arrays.asList(adapterArr));
    }

    public ConcatAdapter(List<? extends RecyclerView.Adapter<? extends RecyclerView.ViewHolder>> list) {
        this(C0594Config.DEFAULT, list);
    }

    public ConcatAdapter(C0594Config config, List<? extends RecyclerView.Adapter<? extends RecyclerView.ViewHolder>> list) {
        this.mController = new ConcatAdapterController(this, config);
        for (RecyclerView.Adapter addAdapter : list) {
            addAdapter(addAdapter);
        }
        super.setHasStableIds(this.mController.hasStableIds());
    }

    public boolean addAdapter(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter) {
        return this.mController.addAdapter(adapter);
    }

    public boolean addAdapter(int i, RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter) {
        return this.mController.addAdapter(i, adapter);
    }

    public boolean removeAdapter(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter) {
        return this.mController.removeAdapter(adapter);
    }

    public int getItemViewType(int i) {
        return this.mController.getItemViewType(i);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mController.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        this.mController.onBindViewHolder(viewHolder, i);
    }

    public void setHasStableIds(boolean z) {
        throw new UnsupportedOperationException("Calling setHasStableIds is not allowed on the ConcatAdapter. Use the Config object passed in the constructor to control this behavior");
    }

    public void setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy stateRestorationPolicy) {
        throw new UnsupportedOperationException("Calling setStateRestorationPolicy is not allowed on the ConcatAdapter. This value is inferred from added adapters");
    }

    public long getItemId(int i) {
        return this.mController.getItemId(i);
    }

    /* access modifiers changed from: package-private */
    public void internalSetStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy stateRestorationPolicy) {
        super.setStateRestorationPolicy(stateRestorationPolicy);
    }

    public int getItemCount() {
        return this.mController.getTotalCount();
    }

    public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        return this.mController.onFailedToRecycleView(viewHolder);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        this.mController.onViewAttachedToWindow(viewHolder);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        this.mController.onViewDetachedFromWindow(viewHolder);
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        this.mController.onViewRecycled(viewHolder);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.mController.onAttachedToRecyclerView(recyclerView);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.mController.onDetachedFromRecyclerView(recyclerView);
    }

    public List<? extends RecyclerView.Adapter<? extends RecyclerView.ViewHolder>> getAdapters() {
        return Collections.unmodifiableList(this.mController.getCopyOfAdapters());
    }

    public int findRelativeAdapterPositionIn(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter, RecyclerView.ViewHolder viewHolder, int i) {
        return this.mController.getLocalAdapterPosition(adapter, viewHolder, i);
    }

    /* renamed from: androidx.recyclerview.widget.ConcatAdapter$Config */
    public static final class C0594Config {
        public static final C0594Config DEFAULT = new C0594Config(true, StableIdMode.NO_STABLE_IDS);
        public final boolean isolateViewTypes;
        public final StableIdMode stableIdMode;

        /* renamed from: androidx.recyclerview.widget.ConcatAdapter$Config$StableIdMode */
        public enum StableIdMode {
            NO_STABLE_IDS,
            ISOLATED_STABLE_IDS,
            SHARED_STABLE_IDS
        }

        C0594Config(boolean z, StableIdMode stableIdMode2) {
            this.isolateViewTypes = z;
            this.stableIdMode = stableIdMode2;
        }

        /* renamed from: androidx.recyclerview.widget.ConcatAdapter$Config$Builder */
        public static final class Builder {
            private boolean mIsolateViewTypes = C0594Config.DEFAULT.isolateViewTypes;
            private StableIdMode mStableIdMode = C0594Config.DEFAULT.stableIdMode;

            public Builder setIsolateViewTypes(boolean z) {
                this.mIsolateViewTypes = z;
                return this;
            }

            public Builder setStableIdMode(StableIdMode stableIdMode) {
                this.mStableIdMode = stableIdMode;
                return this;
            }

            public C0594Config build() {
                return new C0594Config(this.mIsolateViewTypes, this.mStableIdMode);
            }
        }
    }
}
