package com.eternal.notification;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.notification.databinding.FragmentAutomationBindingImpl;
import com.eternal.notification.databinding.FragmentAutomationV4BindingImpl;
import com.eternal.notification.databinding.FragmentOtherBindingImpl;
import com.eternal.notification.databinding.FragmentOtherV4BindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_FRAGMENTAUTOMATION = 1;
    private static final int LAYOUT_FRAGMENTAUTOMATIONV4 = 2;
    private static final int LAYOUT_FRAGMENTOTHER = 3;
    private static final int LAYOUT_FRAGMENTOTHERV4 = 4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(4);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(C2420R.layout.fragment_automation, 1);
        sparseIntArray.put(C2420R.layout.fragment_automation_v4, 2);
        sparseIntArray.put(C2420R.layout.fragment_other, 3);
        sparseIntArray.put(C2420R.layout.fragment_other_v4, 4);
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
                        return null;
                    }
                    if ("layout/fragment_other_v4_0".equals(tag)) {
                        return new FragmentOtherV4BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_other_v4 is invalid. Received: " + tag);
                } else if ("layout/fragment_other_0".equals(tag)) {
                    return new FragmentOtherBindingImpl(dataBindingComponent, view);
                } else {
                    throw new IllegalArgumentException("The tag for fragment_other is invalid. Received: " + tag);
                }
            } else if ("layout/fragment_automation_v4_0".equals(tag)) {
                return new FragmentAutomationV4BindingImpl(dataBindingComponent, view);
            } else {
                throw new IllegalArgumentException("The tag for fragment_automation_v4 is invalid. Received: " + tag);
            }
        } else if ("layout/fragment_automation_0".equals(tag)) {
            return new FragmentAutomationBindingImpl(dataBindingComponent, view);
        } else {
            throw new IllegalArgumentException("The tag for fragment_automation is invalid. Received: " + tag);
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
            sparseArray.put(1, "autoModel");
            sparseArray.put(2, "autoModelV4");
            sparseArray.put(3, "otherModel");
            sparseArray.put(4, "otherModelV4");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(4);
            sKeys = hashMap;
            hashMap.put("layout/fragment_automation_0", Integer.valueOf(C2420R.layout.fragment_automation));
            hashMap.put("layout/fragment_automation_v4_0", Integer.valueOf(C2420R.layout.fragment_automation_v4));
            hashMap.put("layout/fragment_other_0", Integer.valueOf(C2420R.layout.fragment_other));
            hashMap.put("layout/fragment_other_v4_0", Integer.valueOf(C2420R.layout.fragment_other_v4));
        }
    }
}
