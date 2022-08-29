package p014io.github.luizgrp.sectionedrecyclerviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import p014io.github.luizgrp.sectionedrecyclerviewadapter.Section;

/* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter */
public class SectionedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 5;
    public static final int VIEW_TYPE_FAILED = 4;
    public static final int VIEW_TYPE_FOOTER = 1;
    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_ITEM_LOADED = 2;
    public static final int VIEW_TYPE_LOADING = 3;
    private static final int VIEW_TYPE_QTY = 6;
    private final Map<String, Integer> sectionViewTypeNumbers = new LinkedHashMap();
    private final Map<String, Section> sections = new LinkedHashMap();
    private int viewTypeCount = 0;

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        for (Map.Entry next : this.sectionViewTypeNumbers.entrySet()) {
            if (i >= ((Integer) next.getValue()).intValue() && i < ((Integer) next.getValue()).intValue() + 6) {
                Section section = this.sections.get(next.getKey());
                int intValue = i - ((Integer) next.getValue()).intValue();
                if (intValue == 0) {
                    viewHolder = getHeaderViewHolder(viewGroup, section);
                } else if (intValue == 1) {
                    viewHolder = getFooterViewHolder(viewGroup, section);
                } else if (intValue == 2) {
                    viewHolder = getItemViewHolder(viewGroup, section);
                } else if (intValue == 3) {
                    viewHolder = getLoadingViewHolder(viewGroup, section);
                } else if (intValue == 4) {
                    viewHolder = getFailedViewHolder(viewGroup, section);
                } else if (intValue == 5) {
                    viewHolder = getEmptyViewHolder(viewGroup, section);
                } else {
                    throw new IllegalArgumentException("Invalid viewType");
                }
            }
        }
        return viewHolder;
    }

    /* access modifiers changed from: package-private */
    public View inflate(int i, ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
    }

    private RecyclerView.ViewHolder getItemViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isItemViewWillBeProvided()) {
            view = section.getItemView(viewGroup);
            Objects.requireNonNull(view, "Section.getItemView() returned null");
        } else {
            Integer itemResourceId = section.getItemResourceId();
            Objects.requireNonNull(itemResourceId, "Missing 'item' resource id");
            view = inflate(itemResourceId.intValue(), viewGroup);
        }
        return section.getItemViewHolder(view);
    }

    private RecyclerView.ViewHolder getHeaderViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isHeaderViewWillBeProvided()) {
            view = section.getHeaderView(viewGroup);
            Objects.requireNonNull(view, "Section.getHeaderView() returned null");
        } else {
            Integer headerResourceId = section.getHeaderResourceId();
            Objects.requireNonNull(headerResourceId, "Missing 'header' resource id");
            view = inflate(headerResourceId.intValue(), viewGroup);
        }
        return section.getHeaderViewHolder(view);
    }

    private RecyclerView.ViewHolder getFooterViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isFooterViewWillBeProvided()) {
            view = section.getFooterView(viewGroup);
            Objects.requireNonNull(view, "Section.getFooterView() returned null");
        } else {
            Integer footerResourceId = section.getFooterResourceId();
            Objects.requireNonNull(footerResourceId, "Missing 'footer' resource id");
            view = inflate(footerResourceId.intValue(), viewGroup);
        }
        return section.getFooterViewHolder(view);
    }

    private RecyclerView.ViewHolder getLoadingViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isLoadingViewWillBeProvided()) {
            view = section.getLoadingView(viewGroup);
            Objects.requireNonNull(view, "Section.getLoadingView() returned null");
        } else {
            Integer loadingResourceId = section.getLoadingResourceId();
            Objects.requireNonNull(loadingResourceId, "Missing 'loading' resource id");
            view = inflate(loadingResourceId.intValue(), viewGroup);
        }
        return section.getLoadingViewHolder(view);
    }

    private RecyclerView.ViewHolder getFailedViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isFailedViewWillBeProvided()) {
            view = section.getFailedView(viewGroup);
            Objects.requireNonNull(view, "Section.getFailedView() returned null");
        } else {
            Integer failedResourceId = section.getFailedResourceId();
            Objects.requireNonNull(failedResourceId, "Missing 'failed' resource id");
            view = inflate(failedResourceId.intValue(), viewGroup);
        }
        return section.getFailedViewHolder(view);
    }

    private RecyclerView.ViewHolder getEmptyViewHolder(ViewGroup viewGroup, Section section) {
        View view;
        if (section.isEmptyViewWillBeProvided()) {
            view = section.getEmptyView(viewGroup);
            Objects.requireNonNull(view, "Section.getEmptyView() returned null");
        } else {
            Integer emptyResourceId = section.getEmptyResourceId();
            Objects.requireNonNull(emptyResourceId, "Missing 'empty' resource id");
            view = inflate(emptyResourceId.intValue(), viewGroup);
        }
        return section.getEmptyViewHolder(view);
    }

    public void addSection(String str, Section section) {
        this.sections.put(str, section);
        this.sectionViewTypeNumbers.put(str, Integer.valueOf(this.viewTypeCount));
        this.viewTypeCount += 6;
    }

    public String addSection(Section section) {
        String uuid = UUID.randomUUID().toString();
        addSection(uuid, section);
        return uuid;
    }

    public Section getSection(String str) {
        return this.sections.get(str);
    }

    public void removeSection(Section section) {
        String str = null;
        for (Map.Entry next : this.sections.entrySet()) {
            if (next.getValue() == section) {
                str = (String) next.getKey();
            }
        }
        if (str != null) {
            removeSection(str);
        }
    }

    public void removeSection(String str) {
        this.sections.remove(str);
        this.sectionViewTypeNumbers.remove(str);
    }

    public void removeAllSections() {
        this.sections.clear();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2;
        int i3 = 0;
        for (Map.Entry<String, Section> value : this.sections.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.isVisible()) {
                int sectionItemsTotal = section.getSectionItemsTotal();
                if (i < i3 || i > (i3 + sectionItemsTotal) - 1) {
                    i3 += sectionItemsTotal;
                } else if (section.hasHeader() && i == i3) {
                    getSectionForPosition(i).onBindHeaderViewHolder(viewHolder);
                    return;
                } else if (!section.hasFooter() || i != i2) {
                    getSectionForPosition(i).onBindContentViewHolder(viewHolder, getPositionInSection(i));
                    return;
                } else {
                    getSectionForPosition(i).onBindFooterViewHolder(viewHolder);
                    return;
                }
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getItemCount() {
        int i = 0;
        for (Map.Entry<String, Section> value : this.sections.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.isVisible()) {
                i += section.getSectionItemsTotal();
            }
        }
        return i;
    }

    public int getItemViewType(int i) {
        int i2;
        int i3 = 0;
        for (Map.Entry next : this.sections.entrySet()) {
            Section section = (Section) next.getValue();
            if (section.isVisible()) {
                int sectionItemsTotal = section.getSectionItemsTotal();
                if (i < i3 || i > (i2 = (i3 + sectionItemsTotal) - 1)) {
                    i3 += sectionItemsTotal;
                } else {
                    int intValue = this.sectionViewTypeNumbers.get(next.getKey()).intValue();
                    if (section.hasHeader() && i == i3) {
                        return intValue;
                    }
                    if (section.hasFooter() && i == i2) {
                        return intValue + 1;
                    }
                    int i4 = C37831.f1074x814321f1[section.getState().ordinal()];
                    if (i4 == 1) {
                        return intValue + 2;
                    }
                    if (i4 == 2) {
                        return intValue + 3;
                    }
                    if (i4 == 3) {
                        return intValue + 4;
                    }
                    if (i4 == 4) {
                        return intValue + 5;
                    }
                    throw new IllegalStateException("Invalid state");
                }
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    /* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter$1 */
    static /* synthetic */ class C37831 {

        /* renamed from: $SwitchMap$io$github$luizgrp$sectionedrecyclerviewadapter$Section$State */
        static final /* synthetic */ int[] f1074x814321f1;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State[] r0 = p014io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1074x814321f1 = r0
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State r1 = p014io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.LOADED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f1074x814321f1     // Catch:{ NoSuchFieldError -> 0x001d }
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State r1 = p014io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f1074x814321f1     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State r1 = p014io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.FAILED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f1074x814321f1     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.github.luizgrp.sectionedrecyclerviewadapter.Section$State r1 = p014io.github.luizgrp.sectionedrecyclerviewadapter.Section.State.EMPTY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p014io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter.C37831.<clinit>():void");
        }
    }

    public int getSectionItemViewType(int i) {
        return getItemViewType(i) % 6;
    }

    public Section getSectionForPosition(int i) {
        int i2 = 0;
        for (Map.Entry<String, Section> value : this.sections.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.isVisible()) {
                int sectionItemsTotal = section.getSectionItemsTotal();
                if (i >= i2 && i <= (i2 + sectionItemsTotal) - 1) {
                    return section;
                }
                i2 += sectionItemsTotal;
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Deprecated
    public int getSectionPosition(int i) {
        return getPositionInSection(i);
    }

    public int getPositionInSection(int i) {
        int i2 = 0;
        for (Map.Entry<String, Section> value : this.sections.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.isVisible()) {
                int sectionItemsTotal = section.getSectionItemsTotal();
                if (i >= i2 && i <= (i2 + sectionItemsTotal) - 1) {
                    return (i - i2) - (section.hasHeader() ? 1 : 0);
                }
                i2 += sectionItemsTotal;
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getSectionPosition(String str) {
        return getSectionPosition(getValidSectionOrThrowException(str));
    }

    public int getSectionPosition(Section section) {
        int i = 0;
        for (Map.Entry<String, Section> value : this.sections.entrySet()) {
            Section section2 = (Section) value.getValue();
            if (section2.isVisible()) {
                if (section2 == section) {
                    return i;
                }
                i += section2.getSectionItemsTotal();
            }
        }
        throw new IllegalArgumentException("Invalid section");
    }

    public Map<String, Section> getCopyOfSectionsMap() {
        LinkedHashMap linkedHashMap;
        synchronized (this.sections) {
            linkedHashMap = new LinkedHashMap(this.sections);
        }
        return linkedHashMap;
    }

    public int getPositionInAdapter(String str, int i) {
        return getPositionInAdapter(getValidSectionOrThrowException(str), i);
    }

    public int getPositionInAdapter(Section section, int i) {
        return getSectionPosition(section) + (section.hasHeader() ? 1 : 0) + i;
    }

    public int getHeaderPositionInAdapter(String str) {
        return getHeaderPositionInAdapter(getValidSectionOrThrowException(str));
    }

    public int getHeaderPositionInAdapter(Section section) {
        if (section.hasHeader()) {
            return getSectionPosition(section);
        }
        throw new IllegalStateException("Section doesn't have a header");
    }

    public int getFooterPositionInAdapter(String str) {
        return getFooterPositionInAdapter(getValidSectionOrThrowException(str));
    }

    public int getFooterPositionInAdapter(Section section) {
        if (section.hasFooter()) {
            return (getSectionPosition(section) + section.getSectionItemsTotal()) - 1;
        }
        throw new IllegalStateException("Section doesn't have a footer");
    }

    public void notifyItemInsertedInSection(String str, int i) {
        callSuperNotifyItemInserted(getPositionInAdapter(str, i));
    }

    public void notifyItemInsertedInSection(Section section, int i) {
        callSuperNotifyItemInserted(getPositionInAdapter(section, i));
    }

    /* access modifiers changed from: package-private */
    public void callSuperNotifyItemInserted(int i) {
        super.notifyItemInserted(i);
    }

    public void notifyItemRangeInsertedInSection(String str, int i, int i2) {
        callSuperNotifyItemRangeInserted(getPositionInAdapter(str, i), i2);
    }

    public void notifyItemRangeInsertedInSection(Section section, int i, int i2) {
        callSuperNotifyItemRangeInserted(getPositionInAdapter(section, i), i2);
    }

    /* access modifiers changed from: package-private */
    public void callSuperNotifyItemRangeInserted(int i, int i2) {
        super.notifyItemRangeInserted(i, i2);
    }

    public void notifyItemRemovedFromSection(String str, int i) {
        callSuperNotifyItemRemoved(getPositionInAdapter(str, i));
    }

    public void notifyItemRemovedFromSection(Section section, int i) {
        callSuperNotifyItemRemoved(getPositionInAdapter(section, i));
    }

    /* access modifiers changed from: package-private */
    public void callSuperNotifyItemRemoved(int i) {
        super.notifyItemRemoved(i);
    }

    public void notifyItemRangeRemovedFromSection(String str, int i, int i2) {
        callSuperNotifyItemRangeRemoved(getPositionInAdapter(str, i), i2);
    }

    public void notifyItemRangeRemovedFromSection(Section section, int i, int i2) {
        callSuperNotifyItemRangeRemoved(getPositionInAdapter(section, i), i2);
    }

    /* access modifiers changed from: package-private */
    public void callSuperNotifyItemRangeRemoved(int i, int i2) {
        super.notifyItemRangeRemoved(i, i2);
    }

    public void notifyItemChangedInSection(String str, int i) {
        callSuperNotifyItemChanged(getPositionInAdapter(str, i));
    }

    public void notifyItemChangedInSection(Section section, int i) {
        callSuperNotifyItemChanged(getPositionInAdapter(section, i));
    }

    public void notifyHeaderChangedInSection(String str) {
        notifyHeaderChangedInSection(getValidSectionOrThrowException(str));
    }

    public void notifyHeaderChangedInSection(Section section) {
        callSuperNotifyItemChanged(getHeaderPositionInAdapter(section));
    }

    public void notifyFooterChangedInSection(String str) {
        notifyFooterChangedInSection(getValidSectionOrThrowException(str));
    }

    public void notifyFooterChangedInSection(Section section) {
        callSuperNotifyItemChanged(getFooterPositionInAdapter(section));
    }

    /* access modifiers changed from: package-private */
    public void callSuperNotifyItemChanged(int i) {
        super.notifyItemChanged(i);
    }

    public void notifyItemRangeChangedInSection(String str, int i, int i2) {
        callSuperNotifyItemRangeChanged(getPositionInAdapter(str, i), i2);
    }

    public void notifyItemRangeChangedInSection(Section section, int i, int i2) {
        callSuperNotifyItemRangeChanged(getPositionInAdapter(section, i), i2);
    }

    /* access modifiers changed from: package-private */
    public void callSuperNotifyItemRangeChanged(int i, int i2) {
        super.notifyItemRangeChanged(i, i2);
    }

    public void notifyItemRangeChangedInSection(String str, int i, int i2, Object obj) {
        callSuperNotifyItemRangeChanged(getPositionInAdapter(str, i), i2, obj);
    }

    public void notifyItemRangeChangedInSection(Section section, int i, int i2, Object obj) {
        callSuperNotifyItemRangeChanged(getPositionInAdapter(section, i), i2, obj);
    }

    /* access modifiers changed from: package-private */
    public void callSuperNotifyItemRangeChanged(int i, int i2, Object obj) {
        super.notifyItemRangeChanged(i, i2, obj);
    }

    public void notifyItemMovedInSection(String str, int i, int i2) {
        callSuperNotifyItemMoved(getPositionInAdapter(str, i), getPositionInAdapter(str, i2));
    }

    public void notifyItemMovedInSection(Section section, int i, int i2) {
        callSuperNotifyItemMoved(getPositionInAdapter(section, i), getPositionInAdapter(section, i2));
    }

    /* access modifiers changed from: package-private */
    public void callSuperNotifyItemMoved(int i, int i2) {
        super.notifyItemMoved(i, i2);
    }

    public void notifyNotLoadedStateChanged(String str, Section.State state) {
        notifyNotLoadedStateChanged(getValidSectionOrThrowException(str), state);
    }

    public void notifyNotLoadedStateChanged(Section section, Section.State state) {
        Section.State state2 = section.getState();
        if (state2 == state) {
            throw new IllegalStateException("No state changed");
        } else if (state == Section.State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedFromLoaded");
        } else if (state2 != Section.State.LOADED) {
            notifyItemChangedInSection(section, 0);
        } else {
            throw new IllegalStateException("Use notifyStateChangedToLoaded");
        }
    }

    public void notifyStateChangedToLoaded(String str, Section.State state) {
        notifyStateChangedToLoaded(getValidSectionOrThrowException(str), state);
    }

    public void notifyStateChangedToLoaded(Section section, Section.State state) {
        Section.State state2 = section.getState();
        if (state2 == state) {
            throw new IllegalStateException("No state changed");
        } else if (state2 == Section.State.LOADED) {
            int contentItemsTotal = section.getContentItemsTotal();
            if (contentItemsTotal == 0) {
                notifyItemRemovedFromSection(section, 0);
                return;
            }
            notifyItemChangedInSection(section, 0);
            if (contentItemsTotal > 1) {
                notifyItemRangeInsertedInSection(section, 1, contentItemsTotal - 1);
            }
        } else if (state == Section.State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedFromLoaded");
        } else {
            throw new IllegalStateException("Use notifyNotLoadedStateChanged");
        }
    }

    public void notifyStateChangedFromLoaded(String str, int i) {
        notifyStateChangedFromLoaded(getValidSectionOrThrowException(str), i);
    }

    public void notifyStateChangedFromLoaded(Section section, int i) {
        if (section.getState() == Section.State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedToLoaded");
        } else if (i == 0) {
            notifyItemInsertedInSection(section, 0);
        } else {
            if (i > 1) {
                notifyItemRangeRemovedFromSection(section, 1, i - 1);
            }
            notifyItemChangedInSection(section, 0);
        }
    }

    public void notifyHeaderInsertedInSection(String str) {
        notifyHeaderInsertedInSection(getValidSectionOrThrowException(str));
    }

    public void notifyHeaderInsertedInSection(Section section) {
        callSuperNotifyItemInserted(getHeaderPositionInAdapter(section));
    }

    public void notifyFooterInsertedInSection(String str) {
        notifyFooterInsertedInSection(getValidSectionOrThrowException(str));
    }

    public void notifyFooterInsertedInSection(Section section) {
        callSuperNotifyItemInserted(getFooterPositionInAdapter(section));
    }

    public void notifyHeaderRemovedFromSection(String str) {
        notifyHeaderRemovedFromSection(getValidSectionOrThrowException(str));
    }

    public void notifyHeaderRemovedFromSection(Section section) {
        callSuperNotifyItemRemoved(getSectionPosition(section));
    }

    public void notifyFooterRemovedFromSection(String str) {
        notifyFooterRemovedFromSection(getValidSectionOrThrowException(str));
    }

    public void notifyFooterRemovedFromSection(Section section) {
        callSuperNotifyItemRemoved(getSectionPosition(section) + section.getSectionItemsTotal());
    }

    public void notifySectionChangedToVisible(String str) {
        notifySectionChangedToVisible(getValidSectionOrThrowException(str));
    }

    public void notifySectionChangedToVisible(Section section) {
        if (section.isVisible()) {
            callSuperNotifyItemRangeInserted(getSectionPosition(section), section.getSectionItemsTotal());
            return;
        }
        throw new IllegalStateException("This section is not visible.");
    }

    public void notifySectionChangedToInvisible(String str, int i) {
        notifySectionChangedToInvisible(getValidSectionOrThrowException(str), i);
    }

    public void notifySectionChangedToInvisible(Section section, int i) {
        if (!section.isVisible()) {
            callSuperNotifyItemRangeRemoved(i, section.getSectionItemsTotal());
            return;
        }
        throw new IllegalStateException("This section is not visible.");
    }

    private Section getValidSectionOrThrowException(String str) {
        Section section = getSection(str);
        if (section != null) {
            return section;
        }
        throw new IllegalArgumentException("Invalid tag: " + str);
    }

    /* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter$EmptyViewHolder */
    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }
}
