package com.eternal.main;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.main.databinding.ActivityMainBindingImpl;
import com.eternal.main.databinding.ItemMainBindingImpl;
import com.eternal.main.databinding.LayoutPortBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYMAIN = 1;
    private static final int LAYOUT_ITEMMAIN = 2;
    private static final int LAYOUT_LAYOUTPORT = 3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(3);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(C2343R.layout.activity_main, 1);
        sparseIntArray.put(C2343R.layout.item_main, 2);
        sparseIntArray.put(C2343R.layout.layout_port, 3);
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
                    return null;
                }
                if ("layout/layout_port_0".equals(tag)) {
                    return new LayoutPortBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_port is invalid. Received: " + tag);
            } else if ("layout/item_main_0".equals(tag)) {
                return new ItemMainBindingImpl(dataBindingComponent, view);
            } else {
                throw new IllegalArgumentException("The tag for item_main is invalid. Received: " + tag);
            }
        } else if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(dataBindingComponent, view);
        } else {
            throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
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
            SparseArray<String> sparseArray = new SparseArray<>(5);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "item");
            sparseArray.put(2, "model");
            sparseArray.put(3, "open");
            sparseArray.put(4, "port");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(3);
            sKeys = hashMap;
            hashMap.put("layout/activity_main_0", Integer.valueOf(C2343R.layout.activity_main));
            hashMap.put("layout/item_main_0", Integer.valueOf(C2343R.layout.item_main));
            hashMap.put("layout/layout_port_0", Integer.valueOf(C2343R.layout.layout_port));
        }
    }
}
