package com.eternal.account;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.databinding.ActivityAccountBindingImpl;
import com.eternal.account.databinding.ActivityChangePasswordBindingImpl;
import com.eternal.account.databinding.ActivityCreateBindingImpl;
import com.eternal.account.databinding.ActivityExistBindingImpl;
import com.eternal.account.databinding.ActivityFeedbackBindingImpl;
import com.eternal.account.databinding.ActivityFirmwareUpdateBindingImpl;
import com.eternal.account.databinding.ActivityInviteBindingImpl;
import com.eternal.account.databinding.ActivityLegalInformationBindingImpl;
import com.eternal.account.databinding.ActivityLoginBindingImpl;
import com.eternal.account.databinding.ActivityPreferencesBindingImpl;
import com.eternal.account.databinding.ActivityResetPasswordBindingImpl;
import com.eternal.account.databinding.ActivitySearchBindingImpl;
import com.eternal.account.databinding.ActivityShareBindingImpl;
import com.eternal.account.databinding.ActivitySupportBindingImpl;
import com.eternal.account.databinding.ActivityTechnicalGuidesBindingImpl;
import com.eternal.account.databinding.ActivityTimeZoneBindingImpl;
import com.eternal.account.databinding.ActivityVerifyBindingImpl;
import com.eternal.account.databinding.ActivityVersionBindingImpl;
import com.eternal.account.databinding.ItemHeaderBindingImpl;
import com.eternal.account.databinding.ItemInviteBindingImpl;
import com.eternal.account.databinding.ItemPhotoBindingImpl;
import com.eternal.account.databinding.ItemSearchBindingImpl;
import com.eternal.account.databinding.ItemShareBindingImpl;
import com.eternal.account.databinding.ItemTimeZoneBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYACCOUNT = 1;
    private static final int LAYOUT_ACTIVITYCHANGEPASSWORD = 2;
    private static final int LAYOUT_ACTIVITYCREATE = 3;
    private static final int LAYOUT_ACTIVITYEXIST = 4;
    private static final int LAYOUT_ACTIVITYFEEDBACK = 5;
    private static final int LAYOUT_ACTIVITYFIRMWAREUPDATE = 6;
    private static final int LAYOUT_ACTIVITYINVITE = 7;
    private static final int LAYOUT_ACTIVITYLEGALINFORMATION = 8;
    private static final int LAYOUT_ACTIVITYLOGIN = 9;
    private static final int LAYOUT_ACTIVITYPREFERENCES = 10;
    private static final int LAYOUT_ACTIVITYRESETPASSWORD = 11;
    private static final int LAYOUT_ACTIVITYSEARCH = 12;
    private static final int LAYOUT_ACTIVITYSHARE = 13;
    private static final int LAYOUT_ACTIVITYSUPPORT = 14;
    private static final int LAYOUT_ACTIVITYTECHNICALGUIDES = 15;
    private static final int LAYOUT_ACTIVITYTIMEZONE = 16;
    private static final int LAYOUT_ACTIVITYVERIFY = 17;
    private static final int LAYOUT_ACTIVITYVERSION = 18;
    private static final int LAYOUT_ITEMHEADER = 19;
    private static final int LAYOUT_ITEMINVITE = 20;
    private static final int LAYOUT_ITEMPHOTO = 21;
    private static final int LAYOUT_ITEMSEARCH = 22;
    private static final int LAYOUT_ITEMSHARE = 23;
    private static final int LAYOUT_ITEMTIMEZONE = 24;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(24);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(C0997R.layout.activity_account, 1);
        sparseIntArray.put(C0997R.layout.activity_change_password, 2);
        sparseIntArray.put(C0997R.layout.activity_create, 3);
        sparseIntArray.put(C0997R.layout.activity_exist, 4);
        sparseIntArray.put(C0997R.layout.activity_feedback, 5);
        sparseIntArray.put(C0997R.layout.activity_firmware_update, 6);
        sparseIntArray.put(C0997R.layout.activity_invite, 7);
        sparseIntArray.put(C0997R.layout.activity_legal_information, 8);
        sparseIntArray.put(C0997R.layout.activity_login, 9);
        sparseIntArray.put(C0997R.layout.activity_preferences, 10);
        sparseIntArray.put(C0997R.layout.activity_reset_password, 11);
        sparseIntArray.put(C0997R.layout.activity_search, 12);
        sparseIntArray.put(C0997R.layout.activity_share, 13);
        sparseIntArray.put(C0997R.layout.activity_support, 14);
        sparseIntArray.put(C0997R.layout.activity_technical_guides, 15);
        sparseIntArray.put(C0997R.layout.activity_time_zone, 16);
        sparseIntArray.put(C0997R.layout.activity_verify, 17);
        sparseIntArray.put(C0997R.layout.activity_version, 18);
        sparseIntArray.put(C0997R.layout.item_header, 19);
        sparseIntArray.put(C0997R.layout.item_invite, 20);
        sparseIntArray.put(C0997R.layout.item_photo, 21);
        sparseIntArray.put(C0997R.layout.item_search, 22);
        sparseIntArray.put(C0997R.layout.item_share, 23);
        sparseIntArray.put(C0997R.layout.item_time_zone, 24);
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
                    if ("layout/activity_account_0".equals(tag)) {
                        return new ActivityAccountBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_account is invalid. Received: " + tag);
                case 2:
                    if ("layout/activity_change_password_0".equals(tag)) {
                        return new ActivityChangePasswordBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_change_password is invalid. Received: " + tag);
                case 3:
                    if ("layout/activity_create_0".equals(tag)) {
                        return new ActivityCreateBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_create is invalid. Received: " + tag);
                case 4:
                    if ("layout/activity_exist_0".equals(tag)) {
                        return new ActivityExistBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_exist is invalid. Received: " + tag);
                case 5:
                    if ("layout/activity_feedback_0".equals(tag)) {
                        return new ActivityFeedbackBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_feedback is invalid. Received: " + tag);
                case 6:
                    if ("layout/activity_firmware_update_0".equals(tag)) {
                        return new ActivityFirmwareUpdateBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_firmware_update is invalid. Received: " + tag);
                case 7:
                    if ("layout/activity_invite_0".equals(tag)) {
                        return new ActivityInviteBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_invite is invalid. Received: " + tag);
                case 8:
                    if ("layout/activity_legal_information_0".equals(tag)) {
                        return new ActivityLegalInformationBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_legal_information is invalid. Received: " + tag);
                case 9:
                    if ("layout/activity_login_0".equals(tag)) {
                        return new ActivityLoginBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
                case 10:
                    if ("layout/activity_preferences_0".equals(tag)) {
                        return new ActivityPreferencesBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_preferences is invalid. Received: " + tag);
                case 11:
                    if ("layout/activity_reset_password_0".equals(tag)) {
                        return new ActivityResetPasswordBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_reset_password is invalid. Received: " + tag);
                case 12:
                    if ("layout/activity_search_0".equals(tag)) {
                        return new ActivitySearchBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_search is invalid. Received: " + tag);
                case 13:
                    if ("layout/activity_share_0".equals(tag)) {
                        return new ActivityShareBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_share is invalid. Received: " + tag);
                case 14:
                    if ("layout/activity_support_0".equals(tag)) {
                        return new ActivitySupportBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_support is invalid. Received: " + tag);
                case 15:
                    if ("layout/activity_technical_guides_0".equals(tag)) {
                        return new ActivityTechnicalGuidesBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_technical_guides is invalid. Received: " + tag);
                case 16:
                    if ("layout/activity_time_zone_0".equals(tag)) {
                        return new ActivityTimeZoneBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_time_zone is invalid. Received: " + tag);
                case 17:
                    if ("layout/activity_verify_0".equals(tag)) {
                        return new ActivityVerifyBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_verify is invalid. Received: " + tag);
                case 18:
                    if ("layout/activity_version_0".equals(tag)) {
                        return new ActivityVersionBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_version is invalid. Received: " + tag);
                case 19:
                    if ("layout/item_header_0".equals(tag)) {
                        return new ItemHeaderBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_header is invalid. Received: " + tag);
                case 20:
                    if ("layout/item_invite_0".equals(tag)) {
                        return new ItemInviteBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_invite is invalid. Received: " + tag);
                case 21:
                    if ("layout/item_photo_0".equals(tag)) {
                        return new ItemPhotoBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_photo is invalid. Received: " + tag);
                case 22:
                    if ("layout/item_search_0".equals(tag)) {
                        return new ItemSearchBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_search is invalid. Received: " + tag);
                case 23:
                    if ("layout/item_share_0".equals(tag)) {
                        return new ItemShareBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_share is invalid. Received: " + tag);
                case 24:
                    if ("layout/item_time_zone_0".equals(tag)) {
                        return new ItemTimeZoneBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_time_zone is invalid. Received: " + tag);
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
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.eternal.framework.DataBinderMapperImpl());
        arrayList.add(new com.eternal.widget.DataBinderMapperImpl());
        arrayList.add(new com.telink.p010lt.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(24);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "accountModel");
            sparseArray.put(2, "changeModel");
            sparseArray.put(3, "createModel");
            sparseArray.put(4, "existModel");
            sparseArray.put(5, "feedbackModel");
            sparseArray.put(6, "firmwareUpdateModel");
            sparseArray.put(7, "inviteModel");
            sparseArray.put(8, "itemInvite");
            sparseArray.put(9, "itemPhoto");
            sparseArray.put(10, "itemSearch");
            sparseArray.put(11, "itemShare");
            sparseArray.put(12, "itemTimeZone");
            sparseArray.put(13, "legalModel");
            sparseArray.put(14, "loginModel");
            sparseArray.put(15, "preferenceModel");
            sparseArray.put(16, "resetModel");
            sparseArray.put(17, "searchModel");
            sparseArray.put(18, "shareModel");
            sparseArray.put(19, "supportModel");
            sparseArray.put(20, "technicalGuides");
            sparseArray.put(21, "timeZoneModel");
            sparseArray.put(22, "verifyModel");
            sparseArray.put(23, "versionModel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(24);
            sKeys = hashMap;
            hashMap.put("layout/activity_account_0", Integer.valueOf(C0997R.layout.activity_account));
            hashMap.put("layout/activity_change_password_0", Integer.valueOf(C0997R.layout.activity_change_password));
            hashMap.put("layout/activity_create_0", Integer.valueOf(C0997R.layout.activity_create));
            hashMap.put("layout/activity_exist_0", Integer.valueOf(C0997R.layout.activity_exist));
            hashMap.put("layout/activity_feedback_0", Integer.valueOf(C0997R.layout.activity_feedback));
            hashMap.put("layout/activity_firmware_update_0", Integer.valueOf(C0997R.layout.activity_firmware_update));
            hashMap.put("layout/activity_invite_0", Integer.valueOf(C0997R.layout.activity_invite));
            hashMap.put("layout/activity_legal_information_0", Integer.valueOf(C0997R.layout.activity_legal_information));
            hashMap.put("layout/activity_login_0", Integer.valueOf(C0997R.layout.activity_login));
            hashMap.put("layout/activity_preferences_0", Integer.valueOf(C0997R.layout.activity_preferences));
            hashMap.put("layout/activity_reset_password_0", Integer.valueOf(C0997R.layout.activity_reset_password));
            hashMap.put("layout/activity_search_0", Integer.valueOf(C0997R.layout.activity_search));
            hashMap.put("layout/activity_share_0", Integer.valueOf(C0997R.layout.activity_share));
            hashMap.put("layout/activity_support_0", Integer.valueOf(C0997R.layout.activity_support));
            hashMap.put("layout/activity_technical_guides_0", Integer.valueOf(C0997R.layout.activity_technical_guides));
            hashMap.put("layout/activity_time_zone_0", Integer.valueOf(C0997R.layout.activity_time_zone));
            hashMap.put("layout/activity_verify_0", Integer.valueOf(C0997R.layout.activity_verify));
            hashMap.put("layout/activity_version_0", Integer.valueOf(C0997R.layout.activity_version));
            hashMap.put("layout/item_header_0", Integer.valueOf(C0997R.layout.item_header));
            hashMap.put("layout/item_invite_0", Integer.valueOf(C0997R.layout.item_invite));
            hashMap.put("layout/item_photo_0", Integer.valueOf(C0997R.layout.item_photo));
            hashMap.put("layout/item_search_0", Integer.valueOf(C0997R.layout.item_search));
            hashMap.put("layout/item_share_0", Integer.valueOf(C0997R.layout.item_share));
            hashMap.put("layout/item_time_zone_0", Integer.valueOf(C0997R.layout.item_time_zone));
        }
    }
}
