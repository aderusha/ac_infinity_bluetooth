package com.zhihu.matisse.internal.model;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.utils.PathUtils;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SelectedItemCollection {
    public static final int COLLECTION_IMAGE = 1;
    public static final int COLLECTION_MIXED = 3;
    public static final int COLLECTION_UNDEFINED = 0;
    public static final int COLLECTION_VIDEO = 2;
    public static final String STATE_COLLECTION_TYPE = "state_collection_type";
    public static final String STATE_SELECTION = "state_selection";
    private int mCollectionType = 0;
    private final Context mContext;
    private Set<Item> mItems;

    public SelectedItemCollection(Context context) {
        this.mContext = context;
    }

    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            this.mItems = new LinkedHashSet();
            return;
        }
        this.mItems = new LinkedHashSet(bundle.getParcelableArrayList(STATE_SELECTION));
        this.mCollectionType = bundle.getInt(STATE_COLLECTION_TYPE, 0);
    }

    public void setDefaultSelection(List<Item> list) {
        this.mItems.addAll(list);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArrayList(STATE_SELECTION, new ArrayList(this.mItems));
        bundle.putInt(STATE_COLLECTION_TYPE, this.mCollectionType);
    }

    public Bundle getDataWithBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STATE_SELECTION, new ArrayList(this.mItems));
        bundle.putInt(STATE_COLLECTION_TYPE, this.mCollectionType);
        return bundle;
    }

    public boolean add(Item item) {
        if (!typeConflict(item)) {
            boolean add = this.mItems.add(item);
            if (add) {
                int i = this.mCollectionType;
                if (i == 0) {
                    if (item.isImage()) {
                        this.mCollectionType = 1;
                    } else if (item.isVideo()) {
                        this.mCollectionType = 2;
                    }
                } else if (i == 1) {
                    if (item.isVideo()) {
                        this.mCollectionType = 3;
                    }
                } else if (i == 2 && item.isImage()) {
                    this.mCollectionType = 3;
                }
            }
            return add;
        }
        throw new IllegalArgumentException("Can't select images and videos at the same time.");
    }

    public boolean remove(Item item) {
        boolean remove = this.mItems.remove(item);
        if (remove) {
            if (this.mItems.size() == 0) {
                this.mCollectionType = 0;
            } else if (this.mCollectionType == 3) {
                refineCollectionType();
            }
        }
        return remove;
    }

    public void overwrite(ArrayList<Item> arrayList, int i) {
        if (arrayList.size() == 0) {
            this.mCollectionType = 0;
        } else {
            this.mCollectionType = i;
        }
        this.mItems.clear();
        this.mItems.addAll(arrayList);
    }

    public List<Item> asList() {
        return new ArrayList(this.mItems);
    }

    public List<Uri> asListOfUri() {
        ArrayList arrayList = new ArrayList();
        for (Item contentUri : this.mItems) {
            arrayList.add(contentUri.getContentUri());
        }
        return arrayList;
    }

    public List<String> asListOfString() {
        ArrayList arrayList = new ArrayList();
        for (Item contentUri : this.mItems) {
            arrayList.add(PathUtils.getPath(this.mContext, contentUri.getContentUri()));
        }
        return arrayList;
    }

    public boolean isEmpty() {
        Set<Item> set = this.mItems;
        return set == null || set.isEmpty();
    }

    public boolean isSelected(Item item) {
        return this.mItems.contains(item);
    }

    public IncapableCause isAcceptable(Item item) {
        String str;
        if (maxSelectableReached()) {
            int currentMaxSelectable = currentMaxSelectable();
            try {
                str = this.mContext.getResources().getQuantityString(C3757R.plurals.error_over_count, currentMaxSelectable, new Object[]{Integer.valueOf(currentMaxSelectable)});
            } catch (Resources.NotFoundException unused) {
                str = this.mContext.getString(C3757R.string.error_over_count, new Object[]{Integer.valueOf(currentMaxSelectable)});
            } catch (NoClassDefFoundError unused2) {
                str = this.mContext.getString(C3757R.string.error_over_count, new Object[]{Integer.valueOf(currentMaxSelectable)});
            }
            return new IncapableCause(str);
        } else if (typeConflict(item)) {
            return new IncapableCause(this.mContext.getString(C3757R.string.error_type_conflict));
        } else {
            return PhotoMetadataUtils.isAcceptable(this.mContext, item);
        }
    }

    public boolean maxSelectableReached() {
        return this.mItems.size() == currentMaxSelectable();
    }

    private int currentMaxSelectable() {
        SelectionSpec instance = SelectionSpec.getInstance();
        if (instance.maxSelectable > 0) {
            return instance.maxSelectable;
        }
        int i = this.mCollectionType;
        if (i == 1) {
            return instance.maxImageSelectable;
        }
        if (i == 2) {
            return instance.maxVideoSelectable;
        }
        return instance.maxSelectable;
    }

    public int getCollectionType() {
        return this.mCollectionType;
    }

    private void refineCollectionType() {
        boolean z = false;
        boolean z2 = false;
        for (Item next : this.mItems) {
            if (next.isImage() && !z) {
                z = true;
            }
            if (next.isVideo() && !z2) {
                z2 = true;
            }
        }
        if (z && z2) {
            this.mCollectionType = 3;
        } else if (z) {
            this.mCollectionType = 1;
        } else if (z2) {
            this.mCollectionType = 2;
        }
    }

    public boolean typeConflict(Item item) {
        int i;
        int i2;
        if (SelectionSpec.getInstance().mediaTypeExclusive) {
            if (item.isImage() && ((i2 = this.mCollectionType) == 2 || i2 == 3)) {
                return true;
            }
            if (!item.isVideo() || ((i = this.mCollectionType) != 1 && i != 3)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int count() {
        return this.mItems.size();
    }

    public int checkedNumOf(Item item) {
        int indexOf = new ArrayList(this.mItems).indexOf(item);
        if (indexOf == -1) {
            return Integer.MIN_VALUE;
        }
        return indexOf + 1;
    }
}
