package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.account.AccountActivity;
import com.eternal.account.ChangePasswordActivity;
import com.eternal.account.CreateActivity;
import com.eternal.account.ExistActivity;
import com.eternal.account.FeedbackActivity;
import com.eternal.account.FirmwareUpdateActivity;
import com.eternal.account.InviteActivity;
import com.eternal.account.LegalInformationActivity;
import com.eternal.account.LoginActivity;
import com.eternal.account.PreferencesActivity;
import com.eternal.account.ResetPasswordActivity;
import com.eternal.account.SearchActivity;
import com.eternal.account.ShareActivity;
import com.eternal.account.SupportActivity;
import com.eternal.account.TechnicalGuidesActivity;
import com.eternal.account.TimeZoneActivity;
import com.eternal.account.VerifyActivity;
import com.eternal.account.VersionActivity;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import java.util.HashMap;
import java.util.Map;

public class ARouter$$Group$$account implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterActivityPath.Account.PAGE_ACCOUNT, RouteMeta.build(RouteType.ACTIVITY, AccountActivity.class, "/account/accounts", "account", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.SHOW_SHARE_DOT, 0);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_CREATE, RouteMeta.build(RouteType.ACTIVITY, CreateActivity.class, "/account/create", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_EXIST, RouteMeta.build(RouteType.ACTIVITY, ExistActivity.class, "/account/exist", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_FEEDBACK, RouteMeta.build(RouteType.ACTIVITY, FeedbackActivity.class, "/account/feedback", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_FIRMWARE_UPDATE, RouteMeta.build(RouteType.ACTIVITY, FirmwareUpdateActivity.class, "/account/firmwareupdate", "account", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.DEVICE_FIRMWARE_URL, 8);
                put(ActivityEvent.DEVICE_PORT, 1);
                put(ActivityEvent.DEVICE_TYPE, 1);
                put(ActivityEvent.DEVICE_ID, 8);
                put(ActivityEvent.DEVICE_CONNECT_TYPE, 1);
                put(ActivityEvent.DEVICE_MAC, 8);
                put(ActivityEvent.UPDATE_VERSION, 8);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_INVITE, RouteMeta.build(RouteType.ACTIVITY, InviteActivity.class, "/account/invite", "account", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.DEVICE_ID, 8);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_LEGAL_INFORMATION, RouteMeta.build(RouteType.ACTIVITY, LegalInformationActivity.class, "/account/legalinformation", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_LOGIN, RouteMeta.build(RouteType.ACTIVITY, LoginActivity.class, "/account/login", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_PREFERENCE, RouteMeta.build(RouteType.ACTIVITY, PreferencesActivity.class, "/account/preference", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_SHARE, RouteMeta.build(RouteType.ACTIVITY, ShareActivity.class, "/account/share", "account", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.SHOW_SHARE_DOT, 0);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_SUPPORT, RouteMeta.build(RouteType.ACTIVITY, SupportActivity.class, "/account/support", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_TECHNICAL_GUIDES, RouteMeta.build(RouteType.ACTIVITY, TechnicalGuidesActivity.class, "/account/technicalguides", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_TIME_ZONE, RouteMeta.build(RouteType.ACTIVITY, TimeZoneActivity.class, "/account/timezone", "account", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.SHOW_SHARE_DOT, 0);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_TIME_ZONE_SEARCH, RouteMeta.build(RouteType.ACTIVITY, SearchActivity.class, "/account/timezonesearch", "account", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.DEVICE_ID, 8);
                put(ActivityEvent.DEVICE_TIME_ZONE, 8);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_VERIFY, RouteMeta.build(RouteType.ACTIVITY, VerifyActivity.class, "/account/verify", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_VERSION, RouteMeta.build(RouteType.ACTIVITY, VersionActivity.class, "/account/version", "account", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.DEVICE_FIRMWARE_URL, 8);
                put(ActivityEvent.DEVICE_PORT, 1);
                put(ActivityEvent.DEVICE_TYPE, 1);
                put(ActivityEvent.DEVICE_SOFTWARE_VERSION, 8);
                put(ActivityEvent.DEVICE_FIRMWARE_VERSION, 8);
                put(ActivityEvent.DEVICE_MAC, 8);
                put(ActivityEvent.IS_APP_UPDATE, 0);
                put(ActivityEvent.DEVICE_HARDWARE_VERSION, 8);
                put(ActivityEvent.UPDATE_VERSION, 8);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_CHANGE_PASSWORD, RouteMeta.build(RouteType.ACTIVITY, ChangePasswordActivity.class, "/account/changepassword", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Account.PAGE_FORGOT_PASSWORD, RouteMeta.build(RouteType.ACTIVITY, ResetPasswordActivity.class, "/account/forgotpassword", "account", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
