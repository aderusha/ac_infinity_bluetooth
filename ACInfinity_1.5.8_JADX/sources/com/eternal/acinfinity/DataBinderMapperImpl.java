package com.eternal.acinfinity;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(0);

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        if (INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || view.getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
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
        ArrayList arrayList = new ArrayList(17);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.eternal.about.DataBinderMapperImpl());
        arrayList.add(new com.eternal.account.DataBinderMapperImpl());
        arrayList.add(new com.eternal.advance.DataBinderMapperImpl());
        arrayList.add(new com.eternal.control.DataBinderMapperImpl());
        arrayList.add(new com.eternal.data.DataBinderMapperImpl());
        arrayList.add(new com.eternal.details.DataBinderMapperImpl());
        arrayList.add(new com.eternal.device.DataBinderMapperImpl());
        arrayList.add(new com.eternal.export.DataBinderMapperImpl());
        arrayList.add(new com.eternal.framework.DataBinderMapperImpl());
        arrayList.add(new com.eternal.log.DataBinderMapperImpl());
        arrayList.add(new com.eternal.main.DataBinderMapperImpl());
        arrayList.add(new com.eternal.notification.DataBinderMapperImpl());
        arrayList.add(new com.eternal.settings.DataBinderMapperImpl());
        arrayList.add(new com.eternal.start.DataBinderMapperImpl());
        arrayList.add(new com.eternal.widget.DataBinderMapperImpl());
        arrayList.add(new com.telink.p010lt.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(49);
            sKeys = sparseArray;
            sparseArray.put(1, "CModel");
            sparseArray.put(2, "Item");
            sparseArray.put(0, "_all");
            sparseArray.put(3, "accountModel");
            sparseArray.put(4, "addWifiModel");
            sparseArray.put(5, "autoModel");
            sparseArray.put(6, "autoModelV4");
            sparseArray.put(7, "changeModel");
            sparseArray.put(8, "childV4");
            sparseArray.put(9, "chooseModel");
            sparseArray.put(10, "createModel");
            sparseArray.put(11, "existModel");
            sparseArray.put(12, "feedbackModel");
            sparseArray.put(13, "firmwareUpdateModel");
            sparseArray.put(14, "guideModel");
            sparseArray.put(15, "helpModel");
            sparseArray.put(16, "inviteModel");
            sparseArray.put(17, "item");
            sparseArray.put(18, "itemInvite");
            sparseArray.put(19, "itemPhoto");
            sparseArray.put(20, "itemSearch");
            sparseArray.put(21, "itemShare");
            sparseArray.put(22, "itemTimeZone");
            sparseArray.put(23, "itemV4");
            sparseArray.put(24, "legalModel");
            sparseArray.put(25, "loginModel");
            sparseArray.put(26, "model");
            sparseArray.put(27, "modelV4");
            sparseArray.put(28, "modelc");
            sparseArray.put(29, "open");
            sparseArray.put(30, "otherModel");
            sparseArray.put(31, "otherModelV4");
            sparseArray.put(32, "permissionModel");
            sparseArray.put(33, "port");
            sparseArray.put(34, "preferenceModel");
            sparseArray.put(35, "promptModel");
            sparseArray.put(36, "resetModel");
            sparseArray.put(37, "searchModel");
            sparseArray.put(38, "shareModel");
            sparseArray.put(39, "supportModel");
            sparseArray.put(40, "targetModel");
            sparseArray.put(41, "technicalGuides");
            sparseArray.put(42, "timeZoneModel");
            sparseArray.put(43, "verifyModel");
            sparseArray.put(44, "versionModel");
            sparseArray.put(45, "wifiConnectModel");
            sparseArray.put(46, "wifiListModel");
            sparseArray.put(47, "wifiModel");
            sparseArray.put(48, "wifiSettingModel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(0);

        private InnerLayoutIdLookup() {
        }
    }
}
