package com.eternal.device;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.databinding.ActivityChooseBindingImpl;
import com.eternal.device.databinding.ActivityDeviceBindingImpl;
import com.eternal.device.databinding.ActivityDeviceWifiBindingImpl;
import com.eternal.device.databinding.ActivityGuideBindingImpl;
import com.eternal.device.databinding.ActivityHelpBindingImpl;
import com.eternal.device.databinding.ActivityLocationPermissionBindingImpl;
import com.eternal.device.databinding.ActivityPromptBindingImpl;
import com.eternal.device.databinding.ActivityWifiBindingImpl;
import com.eternal.device.databinding.ActivityWifiConnectBindingImpl;
import com.eternal.device.databinding.ActivityWifiLlistBindingImpl;
import com.eternal.device.databinding.ActivityWifiSettingBindingImpl;
import com.eternal.device.databinding.ItemWifiBindingImpl;
import com.eternal.device.databinding.ItemWifiSelectBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYCHOOSE = 1;
    private static final int LAYOUT_ACTIVITYDEVICE = 2;
    private static final int LAYOUT_ACTIVITYDEVICEWIFI = 3;
    private static final int LAYOUT_ACTIVITYGUIDE = 4;
    private static final int LAYOUT_ACTIVITYHELP = 5;
    private static final int LAYOUT_ACTIVITYLOCATIONPERMISSION = 6;
    private static final int LAYOUT_ACTIVITYPROMPT = 7;
    private static final int LAYOUT_ACTIVITYWIFI = 8;
    private static final int LAYOUT_ACTIVITYWIFICONNECT = 9;
    private static final int LAYOUT_ACTIVITYWIFILLIST = 10;
    private static final int LAYOUT_ACTIVITYWIFISETTING = 11;
    private static final int LAYOUT_ITEMWIFI = 12;
    private static final int LAYOUT_ITEMWIFISELECT = 13;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(13);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(C1922R.layout.activity_choose, 1);
        sparseIntArray.put(C1922R.layout.activity_device, 2);
        sparseIntArray.put(C1922R.layout.activity_device_wifi, 3);
        sparseIntArray.put(C1922R.layout.activity_guide, 4);
        sparseIntArray.put(C1922R.layout.activity_help, 5);
        sparseIntArray.put(C1922R.layout.activity_location_permission, 6);
        sparseIntArray.put(C1922R.layout.activity_prompt, 7);
        sparseIntArray.put(C1922R.layout.activity_wifi, 8);
        sparseIntArray.put(C1922R.layout.activity_wifi_connect, 9);
        sparseIntArray.put(C1922R.layout.activity_wifi_llist, 10);
        sparseIntArray.put(C1922R.layout.activity_wifi_setting, 11);
        sparseIntArray.put(C1922R.layout.item_wifi, 12);
        sparseIntArray.put(C1922R.layout.item_wifi_select, 13);
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/activity_choose_0".equals(tag)) {
                        return new ActivityChooseBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_choose is invalid. Received: " + tag);
                case 2:
                    if ("layout/activity_device_0".equals(tag)) {
                        return new ActivityDeviceBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_device is invalid. Received: " + tag);
                case 3:
                    if ("layout/activity_device_wifi_0".equals(tag)) {
                        return new ActivityDeviceWifiBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_device_wifi is invalid. Received: " + tag);
                case 4:
                    if ("layout/activity_guide_0".equals(tag)) {
                        return new ActivityGuideBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_guide is invalid. Received: " + tag);
                case 5:
                    if ("layout/activity_help_0".equals(tag)) {
                        return new ActivityHelpBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_help is invalid. Received: " + tag);
                case 6:
                    if ("layout/activity_location_permission_0".equals(tag)) {
                        return new ActivityLocationPermissionBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_location_permission is invalid. Received: " + tag);
                case 7:
                    if ("layout/activity_prompt_0".equals(tag)) {
                        return new ActivityPromptBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_prompt is invalid. Received: " + tag);
                case 8:
                    if ("layout/activity_wifi_0".equals(tag)) {
                        return new ActivityWifiBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_wifi is invalid. Received: " + tag);
                case 9:
                    if ("layout/activity_wifi_connect_0".equals(tag)) {
                        return new ActivityWifiConnectBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_wifi_connect is invalid. Received: " + tag);
                case 10:
                    if ("layout/activity_wifi_llist_0".equals(tag)) {
                        return new ActivityWifiLlistBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_wifi_llist is invalid. Received: " + tag);
                case 11:
                    if ("layout/activity_wifi_setting_0".equals(tag)) {
                        return new ActivityWifiSettingBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_wifi_setting is invalid. Received: " + tag);
                case 12:
                    if ("layout/item_wifi_0".equals(tag)) {
                        return new ItemWifiBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_wifi is invalid. Received: " + tag);
                case 13:
                    if ("layout/item_wifi_select_0".equals(tag)) {
                        return new ItemWifiSelectBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_wifi_select is invalid. Received: " + tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
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
            SparseArray<String> sparseArray = new SparseArray<>(14);
            sKeys = sparseArray;
            sparseArray.put(1, "Item");
            sparseArray.put(0, "_all");
            sparseArray.put(2, "addWifiModel");
            sparseArray.put(3, "chooseModel");
            sparseArray.put(4, "guideModel");
            sparseArray.put(5, "helpModel");
            sparseArray.put(6, "item");
            sparseArray.put(7, "model");
            sparseArray.put(8, "permissionModel");
            sparseArray.put(9, "promptModel");
            sparseArray.put(10, "wifiConnectModel");
            sparseArray.put(11, "wifiListModel");
            sparseArray.put(12, "wifiModel");
            sparseArray.put(13, "wifiSettingModel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(13);
            sKeys = hashMap;
            hashMap.put("layout/activity_choose_0", Integer.valueOf(C1922R.layout.activity_choose));
            hashMap.put("layout/activity_device_0", Integer.valueOf(C1922R.layout.activity_device));
            hashMap.put("layout/activity_device_wifi_0", Integer.valueOf(C1922R.layout.activity_device_wifi));
            hashMap.put("layout/activity_guide_0", Integer.valueOf(C1922R.layout.activity_guide));
            hashMap.put("layout/activity_help_0", Integer.valueOf(C1922R.layout.activity_help));
            hashMap.put("layout/activity_location_permission_0", Integer.valueOf(C1922R.layout.activity_location_permission));
            hashMap.put("layout/activity_prompt_0", Integer.valueOf(C1922R.layout.activity_prompt));
            hashMap.put("layout/activity_wifi_0", Integer.valueOf(C1922R.layout.activity_wifi));
            hashMap.put("layout/activity_wifi_connect_0", Integer.valueOf(C1922R.layout.activity_wifi_connect));
            hashMap.put("layout/activity_wifi_llist_0", Integer.valueOf(C1922R.layout.activity_wifi_llist));
            hashMap.put("layout/activity_wifi_setting_0", Integer.valueOf(C1922R.layout.activity_wifi_setting));
            hashMap.put("layout/item_wifi_0", Integer.valueOf(C1922R.layout.item_wifi));
            hashMap.put("layout/item_wifi_select_0", Integer.valueOf(C1922R.layout.item_wifi_select));
        }
    }
}
