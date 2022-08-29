package com.eternal.advance;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.advance.databinding.FragmentAdvanceBindingImpl;
import com.eternal.advance.databinding.FragmentAdvanceV4BindingImpl;
import com.eternal.advance.databinding.ItemChildNotificationV4BindingImpl;
import com.eternal.advance.databinding.ItemNotificationBindingImpl;
import com.eternal.advance.databinding.ItemNotificationV4BindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_FRAGMENTADVANCE = 1;
    private static final int LAYOUT_FRAGMENTADVANCEV4 = 2;
    private static final int LAYOUT_ITEMCHILDNOTIFICATIONV4 = 3;
    private static final int LAYOUT_ITEMNOTIFICATION = 4;
    private static final int LAYOUT_ITEMNOTIFICATIONV4 = 5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(5);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(C1202R.layout.fragment_advance, 1);
        sparseIntArray.put(C1202R.layout.fragment_advance_v4, 2);
        sparseIntArray.put(C1202R.layout.item_child_notification_v4, 3);
        sparseIntArray.put(C1202R.layout.item_notification, 4);
        sparseIntArray.put(C1202R.layout.item_notification_v4, 5);
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        } else if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        if ("layout/item_notification_v4_0".equals(tag)) {
                            return new ItemNotificationV4BindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for item_notification_v4 is invalid. Received: " + tag);
                    } else if ("layout/item_notification_0".equals(tag)) {
                        return new ItemNotificationBindingImpl(dataBindingComponent, view);
                    } else {
                        throw new IllegalArgumentException("The tag for item_notification is invalid. Received: " + tag);
                    }
                } else if ("layout/item_child_notification_v4_0".equals(tag)) {
                    return new ItemChildNotificationV4BindingImpl(dataBindingComponent, view);
                } else {
                    throw new IllegalArgumentException("The tag for item_child_notification_v4 is invalid. Received: " + tag);
                }
            } else if ("layout/fragment_advance_v4_0".equals(tag)) {
                return new FragmentAdvanceV4BindingImpl(dataBindingComponent, view);
            } else {
                throw new IllegalArgumentException("The tag for fragment_advance_v4 is invalid. Received: " + tag);
            }
        } else if ("layout/fragment_advance_0".equals(tag)) {
            return new FragmentAdvanceBindingImpl(dataBindingComponent, view);
        } else {
            throw new IllegalArgumentException("The tag for fragment_advance is invalid. Received: " + tag);
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.eternal.framework.DataBinderMapperImpl());
        arrayList.add(new com.eternal.widget.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(6);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "childV4");
            sparseArray.put(2, "item");
            sparseArray.put(3, "itemV4");
            sparseArray.put(4, "model");
            sparseArray.put(5, "modelV4");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(5);
            sKeys = hashMap;
            hashMap.put("layout/fragment_advance_0", Integer.valueOf(C1202R.layout.fragment_advance));
            hashMap.put("layout/fragment_advance_v4_0", Integer.valueOf(C1202R.layout.fragment_advance_v4));
            hashMap.put("layout/item_child_notification_v4_0", Integer.valueOf(C1202R.layout.item_child_notification_v4));
            hashMap.put("layout/item_notification_0", Integer.valueOf(C1202R.layout.item_notification));
            hashMap.put("layout/item_notification_v4_0", Integer.valueOf(C1202R.layout.item_notification_v4));
        }
    }
}
