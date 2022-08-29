package com.eternal.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.IConfirmAction;
import com.eternal.base.IConnectAction;
import com.eternal.base.IDegreeAction;
import com.eternal.base.ILevel;
import com.eternal.base.ISoftKeyBoardAction;
import com.eternal.base.ITFPAction;
import com.eternal.base.IToolBarAction;
import com.eternal.base.LogService;
import com.eternal.base.TipDialog;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.FirmwareVersion;
import com.eternal.base.concat.NetLogData;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.database.entity.Log;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.router.RouterFragmentPath;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseRxActivity;
import com.eternal.framework.http.RxHttpUtils;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.AppUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.StringUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.widget.guqiang.ProgressToolbar;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;
import p022q.rorbin.badgeview.Badge;
import p022q.rorbin.badgeview.QBadgeView;

public class DetailsActivity extends BaseRxActivity implements ILevel {
    static final String TAG_FETCH_LOG = "tag fetch log";
    /* access modifiers changed from: private */
    public Button btnConfirm;
    private Disposable connectEvent;
    /* access modifiers changed from: private */
    public TextView connectText;
    byte connectType;
    String devId;
    byte deviceType;
    byte deviceVersion;
    /* access modifiers changed from: private */
    public MaterialDialog dialog;
    String firmwareVersion;
    private Fragment fragAdvance;
    private Fragment fragControl;
    private Fragment fragData;
    private Fragment fragLog;
    /* access modifiers changed from: private */
    public List<Fragment> fragments;
    String hardwareVersion;
    /* access modifiers changed from: private */
    public TabLayout layout;
    /* access modifiers changed from: private */
    public Badge logBadge;
    private Disposable logNum;
    /* access modifiers changed from: private */
    public Handler mLogHandler = new Handler();
    /* access modifiers changed from: private */
    public Runnable mLogRunnable = new Runnable() {
        public void run() {
            DetailsActivity.this.refreshLog();
            DetailsActivity.this.mLogHandler.postDelayed(DetailsActivity.this.mLogRunnable, 2000);
        }
    };
    String mac;
    private Disposable nameRefresh;
    /* access modifiers changed from: private */
    public Disposable netLogRefresh;
    /* access modifiers changed from: private */
    public Fragment nowFragment;
    byte port;
    /* access modifiers changed from: private */
    public int portCount;
    /* access modifiers changed from: private */
    public Disposable progressEvent;
    /* access modifiers changed from: private */
    public SoftKeyBroadManager softKeyBroadManager;
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            DetailsActivity.this.btnConfirm.requestLayout();
            if (DetailsActivity.this.nowFragment instanceof ISoftKeyBoardAction) {
                ((ISoftKeyBoardAction) DetailsActivity.this.nowFragment).onSoftKeyboardOpened(i);
            }
            DetailsActivity.this.btnConfirm.setVisibility(0);
            DetailsActivity.this.layout.setVisibility(8);
        }

        public void onSoftKeyboardClosed() {
            DetailsActivity.this.btnConfirm.requestLayout();
            if (DetailsActivity.this.nowFragment instanceof ISoftKeyBoardAction) {
                ((ISoftKeyBoardAction) DetailsActivity.this.nowFragment).onSoftKeyboardClosed();
            }
            DetailsActivity.this.btnConfirm.setVisibility(8);
            DetailsActivity.this.layout.setVisibility(0);
        }
    };
    String softwareVersion;
    private Disposable timeEvent;
    private Disposable timeRefresh;
    private String[] titles;
    /* access modifiers changed from: private */
    public ProgressToolbar toolbar;
    private Disposable versionDis;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            this.fragControl = supportFragmentManager.findFragmentByTag("control");
            this.fragAdvance = supportFragmentManager.findFragmentByTag("advance");
            this.fragData = supportFragmentManager.findFragmentByTag("data");
            this.fragLog = supportFragmentManager.findFragmentByTag("log");
        }
        super.onCreate(bundle);
        setContentView(C1903R.layout.activity_details);
        ARouter.getInstance().inject(this);
        initView();
        initFragment();
        if (ActivityEvent.ACTION_LOG.equals(getIntent().getAction())) {
            this.nowFragment = this.fragments.get(0);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.show(this.nowFragment);
            beginTransaction.commit();
            TabLayout tabLayout = this.layout;
            tabLayout.getTabAt(tabLayout.getTabCount() - 1).select();
        } else {
            this.nowFragment = this.fragments.get(0);
            FragmentTransaction beginTransaction2 = getSupportFragmentManager().beginTransaction();
            beginTransaction2.show(this.nowFragment);
            beginTransaction2.commit();
        }
        queryVersion();
    }

    private void queryVersion() {
        this.versionDis = RepositoryInjection.providerDeviceRepository().getSoftwareVersion(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            public void accept(String str) throws Exception {
                if (!TextUtils.isEmpty(str)) {
                    DetailsActivity detailsActivity = DetailsActivity.this;
                    if (StringUtils.compareVersion(str, detailsActivity.softwareVersion) != 1) {
                        str = DetailsActivity.this.softwareVersion;
                    }
                    detailsActivity.softwareVersion = str;
                }
                DetailsActivity.this.fetchFirmwareVersion();
            }
        });
    }

    /* access modifiers changed from: private */
    public void fetchFirmwareVersion() {
        ApiHelper.getAccountApi().getFirmwareVersion(this.deviceType, this.softwareVersion, this.hardwareVersion).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<FirmwareVersion>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                KLog.m65e(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(final FirmwareVersion firmwareVersion) {
                if (firmwareVersion != null) {
                    if (DetailsActivity.this.checkSupportVersion(firmwareVersion.androidSupportVersion)) {
                        DetailsActivity detailsActivity = DetailsActivity.this;
                        MaterialDialog unused = detailsActivity.dialog = TipDialog.showTipDialog(detailsActivity, "Update Required", "You are using an older version of this app. Please update the app for continued support, the latest features, and bug fixes.", detailsActivity.getResources().getString(C1903R.string.tip_cancel_lowercase), DetailsActivity.this.getResources().getString(C1903R.string.tip_ok_lowercase), false, true, new View.OnClickListener() {
                            public void onClick(View view) {
                                DetailsActivity.this.onBack((View) null);
                            }
                        }, new View.OnClickListener() {
                            public void onClick(View view) {
                                ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C1903R.anim.left_in, C1903R.anim.right_out).withAction(ActivityEvent.ACTION_APP_UPDATE).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
                                    public void onArrival(Postcard postcard) {
                                        DetailsActivity.this.finish();
                                    }
                                });
                            }
                        });
                    } else if (DetailsActivity.this.checkFirmwareUpdate(firmwareVersion.firmwareVersion)) {
                        DetailsActivity detailsActivity2 = DetailsActivity.this;
                        DetailsActivity detailsActivity3 = detailsActivity2;
                        MaterialDialog unused2 = detailsActivity2.dialog = TipDialog.showTipDialog(detailsActivity3, "Firmware Update", String.format(Locale.ENGLISH, "Firmware version %s is available. Please update your firmware to get the latest features.", new Object[]{firmwareVersion.fVersion}), DetailsActivity.this.getResources().getString(C1903R.string.tip_later), DetailsActivity.this.getResources().getString(C1903R.string.tip_ok_lowercase), true, true, new View.OnClickListener() {
                            public void onClick(View view) {
                            }
                        }, new View.OnClickListener() {
                            public void onClick(View view) {
                                DetailsActivity.this.pushToFirmwareUpdatePage(firmwareVersion.fUrl, firmwareVersion.fVersion);
                            }
                        });
                    }
                }
            }
        });
    }

    public void pushToFirmwareUpdatePage(String str, String str2) {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_VERSION).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_PORT, this.port).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).withString(ActivityEvent.DEVICE_FIRMWARE_URL, str).withString(ActivityEvent.UPDATE_VERSION, str2).withString(ActivityEvent.DEVICE_SOFTWARE_VERSION, this.softwareVersion).withString(ActivityEvent.DEVICE_FIRMWARE_VERSION, this.firmwareVersion).withString(ActivityEvent.DEVICE_HARDWARE_VERSION, this.hardwareVersion).withBoolean(ActivityEvent.IS_APP_UPDATE, false).withTransition(C1903R.anim.right_in, C1903R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean checkSupportVersion(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String appVersionName = AppUtils.getAppVersionName();
        if (!TextUtils.isEmpty(appVersionName) && StringUtils.compareVersion(str, appVersionName) == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean checkFirmwareUpdate(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return ProtocolTransformer.isSupportFirmwareUpdate(this.softwareVersion, this.deviceType);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (ActivityEvent.ACTION_LOG.equals(intent.getAction())) {
            TabLayout tabLayout = this.layout;
            tabLayout.getTabAt(tabLayout.getTabCount() - 1).select();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.toolbar.cancelProgress();
        unregisterEvent();
        Disposable disposable = this.nameRefresh;
        if (disposable != null) {
            disposable.dispose();
            this.nameRefresh = null;
        }
        Disposable disposable2 = this.versionDis;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.versionDis.dispose();
        }
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        Disposable disposable3 = this.timeRefresh;
        if (disposable3 != null) {
            disposable3.dispose();
        }
        stopRefreshLog();
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void registerEvent() {
        Disposable disposable = this.progressEvent;
        if (disposable == null || disposable.isDisposed()) {
            this.progressEvent = RxBus.getDefault().toObservable(ProgressEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ProgressEvent>() {
                public void accept(ProgressEvent progressEvent) {
                    byte b = progressEvent.Statue;
                    if (b == 0) {
                        DetailsActivity.this.toolbar.startProgress();
                    } else if (b == 1) {
                        DetailsActivity.this.toolbar.endProgress(progressEvent.callback);
                    } else if (b != 3) {
                        DetailsActivity.this.toolbar.setAnimatorProgress(progressEvent.percent);
                    } else {
                        DetailsActivity.this.toolbar.initProgress(progressEvent.percent, PathInterpolatorCompat.MAX_NUM_POINTS);
                    }
                }
            });
        }
    }

    private void unregisterEvent() {
        Disposable disposable = this.timeEvent;
        if (disposable != null) {
            disposable.dispose();
            this.timeEvent = null;
        }
        Disposable disposable2 = this.connectEvent;
        if (disposable2 != null) {
            disposable2.dispose();
            this.connectEvent = null;
        }
        Disposable disposable3 = this.progressEvent;
        if (disposable3 != null) {
            disposable3.dispose();
            this.toolbar.cancelProgress();
            this.progressEvent = null;
        }
    }

    private void initView() {
        this.titles = getResources().getStringArray(C1903R.array.details_title);
        ProgressToolbar progressToolbar = (ProgressToolbar) findViewById(C1903R.C1906id.toolbar);
        this.toolbar = progressToolbar;
        progressToolbar.setTitle(this.titles[0]);
        this.toolbar.getBack().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DetailsActivity.this.onBack(view);
            }
        });
        this.toolbar.getNext().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DetailsActivity.this.onNext(view);
            }
        });
        this.toolbar.getSecondNext().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DetailsActivity.this.nowFragment instanceof IToolBarAction) {
                    ((IToolBarAction) DetailsActivity.this.nowFragment).onSecond();
                }
            }
        });
        this.connectText = (TextView) findViewById(C1903R.C1906id.txt_connect_time);
        TabLayout tabLayout = (TabLayout) findViewById(C1903R.C1906id.tb_bottom);
        this.layout = tabLayout;
        TabLayout.Tab newTab = tabLayout.newTab();
        View inflate = LayoutInflater.from(this).inflate(C1903R.layout.details_item_log, newTab.parent, false);
        final AppCompatImageView appCompatImageView = (AppCompatImageView) inflate.findViewById(C1903R.C1906id.tabicon);
        this.logBadge = new QBadgeView(this).bindTarget(appCompatImageView).setExactMode(false).setBadgeGravity(8388661);
        newTab.setCustomView(inflate);
        appCompatImageView.setSelected(false);
        TabLayout tabLayout2 = this.layout;
        tabLayout2.addTab(tabLayout2.newTab().setIcon(C1903R.C1905drawable.control_icon_selector), 0);
        byte b = this.deviceType;
        int i = 2;
        if (b == 1 || b == 6 || b == 2 || b == 7 || b == 8 || b == 11 || b == 9 || b == 12) {
            TabLayout tabLayout3 = this.layout;
            tabLayout3.addTab(tabLayout3.newTab().setIcon(C1903R.C1905drawable.advance_icon_selector), 1);
        } else {
            i = 1;
        }
        TabLayout tabLayout4 = this.layout;
        tabLayout4.addTab(tabLayout4.newTab().setIcon(C1903R.C1905drawable.datapage_icon_selector), i);
        this.layout.addTab(newTab, i + 1);
        this.layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabReselected(TabLayout.Tab tab) {
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }

            public void onTabSelected(TabLayout.Tab tab) {
                DetailsActivity.this.replaceFragment(tab.getPosition());
                if (tab.getPosition() == DetailsActivity.this.layout.getTabCount() - 1) {
                    appCompatImageView.setSelected(true);
                } else {
                    appCompatImageView.setSelected(false);
                }
            }
        });
        Button button = (Button) findViewById(C1903R.C1906id.btn_confirm);
        this.btnConfirm = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DetailsActivity.this.nowFragment instanceof IConfirmAction) {
                    ((IConfirmAction) DetailsActivity.this.nowFragment).onConfirm();
                }
                if (DetailsActivity.this.softKeyBroadManager.isSoftKeyboardOpened() && (DetailsActivity.this.nowFragment instanceof ISoftKeyBoardAction)) {
                    ((ISoftKeyBoardAction) DetailsActivity.this.nowFragment).closeKeyboard();
                }
            }
        });
        refreshLog();
        refreshName();
        refreshTime();
        if (RepositoryInjection.providerDeviceRepository().isConnect(this.mac) || ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
            registerEvent();
        }
        registerConnect();
        SoftKeyBroadManager softKeyBroadManager2 = new SoftKeyBroadManager(findViewById(C1903R.C1906id.layout_main));
        this.softKeyBroadManager = softKeyBroadManager2;
        softKeyBroadManager2.addSoftKeyboardStateListener(this.softKeyboardStateListener);
    }

    private void registerConnect() {
        this.connectEvent = RxBus.getDefault().toObservable(BluetoothEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BluetoothEvent>() {
            public void accept(BluetoothEvent bluetoothEvent) throws Exception {
                if (DetailsActivity.this.mac.equals(bluetoothEvent.mac)) {
                    if (bluetoothEvent.what == 0) {
                        if (DetailsActivity.this.progressEvent != null && !ProtocolTransformer.isWorkWiFi(DetailsActivity.this.deviceType, DetailsActivity.this.connectType)) {
                            DetailsActivity.this.progressEvent.dispose();
                            Disposable unused = DetailsActivity.this.progressEvent = null;
                        }
                        if (DetailsActivity.this.fragments != null) {
                            for (Fragment fragment : DetailsActivity.this.fragments) {
                                if (fragment instanceof IConnectAction) {
                                    ((IConnectAction) fragment).disconnected();
                                }
                            }
                            return;
                        }
                        return;
                    }
                    boolean z = true;
                    if (bluetoothEvent.what == 1) {
                        if (DetailsActivity.this.logBadge.getBadgeNumber() != bluetoothEvent.f135i1) {
                            DetailsActivity.this.logBadge.setBadgeNumber(bluetoothEvent.f135i1);
                        }
                    } else if (bluetoothEvent.what == 2) {
                        DetailsActivity.this.registerEvent();
                        DetailsActivity.this.refreshLog();
                        if (DetailsActivity.this.fragments != null) {
                            for (Fragment fragment2 : DetailsActivity.this.fragments) {
                                if (fragment2 instanceof IConnectAction) {
                                    ((IConnectAction) fragment2).connected();
                                }
                            }
                        }
                    } else if (bluetoothEvent.what == 5) {
                        if (DetailsActivity.this.nowFragment instanceof IDegreeAction) {
                            IDegreeAction iDegreeAction = (IDegreeAction) DetailsActivity.this.nowFragment;
                            if (bluetoothEvent.f135i1 != 1) {
                                z = false;
                            }
                            iDegreeAction.setDegree(z);
                        }
                    } else if (bluetoothEvent.what == 7 && (bluetoothEvent.obj instanceof DeviceTFP)) {
                        DeviceTFP deviceTFP = (DeviceTFP) bluetoothEvent.obj;
                        DetailsActivity.this.port = deviceTFP.choosePort;
                        if (DetailsActivity.this.nowFragment instanceof ITFPAction) {
                            ((ITFPAction) DetailsActivity.this.nowFragment).refreshTFP(deviceTFP);
                        }
                    }
                }
            }
        });
    }

    private void refreshName() {
        Disposable disposable = this.nameRefresh;
        if (disposable == null || disposable.isDisposed()) {
            this.nameRefresh = RepositoryInjection.providerDeviceRepository().getDeviceName(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<DeviceName>>() {
                public void accept(List<DeviceName> list) {
                    if (list != null) {
                        int unused = DetailsActivity.this.portCount = list.size();
                        Collections.sort(list, new Comparator<DeviceName>() {
                            public int compare(DeviceName deviceName, DeviceName deviceName2) {
                                return deviceName.port - deviceName2.port;
                            }
                        });
                        Iterator<DeviceName> it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            DeviceName next = it.next();
                            if (next.port == 0) {
                                DetailsActivity.this.toolbar.setSubtitle(next.name);
                                break;
                            }
                        }
                        if (DetailsActivity.this.fragments != null) {
                            for (Fragment fragment : DetailsActivity.this.fragments) {
                                if (fragment instanceof IToolBarAction) {
                                    ((IToolBarAction) fragment).onNameUpdate(list);
                                }
                            }
                        }
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    KLog.m65e(th);
                }
            });
        }
    }

    private void refreshTime() {
        Disposable disposable = this.timeRefresh;
        if (disposable == null || disposable.isDisposed()) {
            this.timeRefresh = RepositoryInjection.providerDeviceRepository().getConnectInfo(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ConnectInfo>() {
                public void accept(ConnectInfo connectInfo) {
                    if (RepositoryInjection.providerDeviceRepository().isConnect(DetailsActivity.this.mac)) {
                        DetailsActivity.this.connectText.setVisibility(8);
                    } else {
                        DetailsActivity.this.connectText.setVisibility(0);
                        DetailsActivity.this.connectText.setText(Utils.getString(C1903R.string.tip_last, ProtocolTransformer.getTime(connectInfo.connectTime)));
                    }
                    if (connectInfo.connectType != DetailsActivity.this.connectType) {
                        DetailsActivity.this.connectType = connectInfo.connectType;
                        DetailsActivity.this.devId = connectInfo.deviceId;
                        if (DetailsActivity.this.fragments != null) {
                            for (Fragment fragment : DetailsActivity.this.fragments) {
                                if (fragment instanceof IConnectAction) {
                                    ((IConnectAction) fragment).setConnectType(DetailsActivity.this.devId, DetailsActivity.this.connectType);
                                }
                            }
                        }
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    KLog.m65e(th);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        refreshLog();
        this.mLogHandler.postDelayed(this.mLogRunnable, 2000);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        stopRefreshLog();
        this.mLogHandler.removeCallbacks(this.mLogRunnable);
    }

    private void stopRefreshLog() {
        Disposable disposable = this.logNum;
        if (disposable != null) {
            disposable.dispose();
            this.logNum = null;
        }
        Disposable disposable2 = this.netLogRefresh;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.netLogRefresh.dispose();
        }
        RxHttpUtils.cancel(TAG_FETCH_LOG);
    }

    /* access modifiers changed from: private */
    public void refreshLog() {
        if (!ProtocolTransformer.isDeviceC(this.deviceType)) {
            if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
                fetchLogs();
            } else if (!LogService.getInstance().isLoding(this.mac) && this.portCount > 0) {
                LogService.getInstance().initELog(this.mac, (byte) this.portCount);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Observable<BaseData<NetLogData>> getPageAndNext(final int i) {
        return RepositoryInjection.providerLogRepository().getFirstNetLog(this.mac).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).flatMapObservable(new Function<Log, ObservableSource<BaseData<NetLogData>>>() {
            public ObservableSource<BaseData<NetLogData>> apply(Log log) throws Exception {
                return ApiHelper.getLogApi().getLogList(UserCache.getInstance().getToken(), DetailsActivity.this.devId, log.netId, log.time / 1000, i);
            }
        }).concatMap(new Function<BaseData<NetLogData>, ObservableSource<? extends BaseData<NetLogData>>>() {
            public ObservableSource<? extends BaseData<NetLogData>> apply(final BaseData<NetLogData> baseData) throws Exception {
                return (baseData.getData() == null || baseData.getData().rows == null || baseData.getData().rows.size() <= 0) ? Observable.just(baseData) : RepositoryInjection.providerLogRepository().addLogs(DetailsActivity.this.mac, baseData.getData().rows).flatMapObservable(new Function<Boolean, ObservableSource<BaseData<NetLogData>>>() {
                    public ObservableSource<BaseData<NetLogData>> apply(Boolean bool) throws Exception {
                        if (((NetLogData) baseData.getData()).rows.size() < i) {
                            return Observable.just(baseData);
                        }
                        return Observable.just(baseData).concatWith(DetailsActivity.this.getPageAndNext(i));
                    }
                });
            }
        });
    }

    private void fetchLogs() {
        Disposable disposable = this.netLogRefresh;
        if (disposable == null || disposable.isDisposed()) {
            getPageAndNext(500).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<NetLogData>() {
                /* access modifiers changed from: protected */
                public void onError(String str) {
                }

                /* access modifiers changed from: protected */
                public void onSuccess(NetLogData netLogData) {
                }

                /* access modifiers changed from: protected */
                public String setTag() {
                    return DetailsActivity.TAG_FETCH_LOG;
                }

                public void doOnSubscribe(Disposable disposable) {
                    Disposable unused = DetailsActivity.this.netLogRefresh = disposable;
                }
            });
        }
    }

    private void initFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.fragments = new ArrayList(4);
        ARouter instance = ARouter.getInstance();
        byte b = this.deviceType;
        if (b == 1 || b == 6 || b == 2 || b == 7 || b == 8 || b == 11 || b == 9 || b == 12) {
            if (this.fragControl == null) {
                this.fragControl = (Fragment) instance.build(RouterFragmentPath.Control.PAGE_CONTROL).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_PORT, this.port).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withByte(ActivityEvent.DEVICE_VERSION, this.deviceVersion).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).navigation(this);
                beginTransaction.add(C1903R.C1906id.frame_layout, this.fragControl, "control");
            }
            Fragment fragment = this.fragControl;
            this.nowFragment = fragment;
            this.fragments.add(fragment);
            if (this.fragAdvance == null) {
                this.fragAdvance = (Fragment) instance.build(RouterFragmentPath.Advance.PAGE_ADVANCE).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_PORT, this.port).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withByte(ActivityEvent.DEVICE_VERSION, this.deviceVersion).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).navigation(this);
                beginTransaction.add(C1903R.C1906id.frame_layout, this.fragAdvance, "advance");
            }
            beginTransaction.hide(this.fragAdvance);
            this.fragments.add(this.fragAdvance);
        } else {
            if (this.fragControl == null) {
                this.fragControl = (Fragment) instance.build(RouterFragmentPath.Control.PAGE_CONTROL_C).withString(ActivityEvent.DEVICE_MAC, this.mac).withByte(ActivityEvent.DEVICE_PORT, this.port).navigation(this);
                beginTransaction.add(C1903R.C1906id.frame_layout, this.fragControl, "control");
            }
            this.fragments.add(this.fragControl);
            this.nowFragment = this.fragControl;
        }
        if (this.fragData == null) {
            this.fragData = (Fragment) instance.build(RouterFragmentPath.Data.PAGE_DATA).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_PORT, this.port).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withByte(ActivityEvent.DEVICE_VERSION, this.deviceVersion).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).navigation(this);
            beginTransaction.add(C1903R.C1906id.frame_layout, this.fragData, "data");
        }
        beginTransaction.hide(this.fragData);
        this.fragments.add(this.fragData);
        if (this.fragLog == null) {
            if (ProtocolTransformer.isDeviceC(this.deviceType)) {
                this.fragLog = (Fragment) instance.build(RouterFragmentPath.Log.PAGE_LOG_C).withString(ActivityEvent.DEVICE_MAC, this.mac).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withByte(ActivityEvent.DEVICE_PORT, this.port).navigation(this);
            } else {
                this.fragLog = (Fragment) instance.build(RouterFragmentPath.Log.PAGE_LOG).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_PORT, this.port).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withByte(ActivityEvent.DEVICE_VERSION, this.deviceVersion).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).navigation(this);
            }
            beginTransaction.add(C1903R.C1906id.frame_layout, this.fragLog, "log");
        }
        beginTransaction.hide(this.fragLog);
        this.fragments.add(this.fragLog);
        beginTransaction.commit();
    }

    /* access modifiers changed from: private */
    public void replaceFragment(int i) {
        if (i < this.fragments.size()) {
            this.toolbar.cancelProgress();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.hide(this.nowFragment);
            Fragment fragment = this.fragments.get(i);
            this.nowFragment = fragment;
            beginTransaction.show(fragment);
            beginTransaction.commitAllowingStateLoss();
        }
        byte b = this.deviceType;
        if ((b == 14 || b == 15 || b == 4 || b == 5) && i > 0) {
            i++;
        }
        this.toolbar.setTitle(this.titles[i]);
        String[] strArr = this.titles;
        if (i == strArr.length - 1) {
            this.toolbar.getSecondNext().setVisibility(0);
            if (this.toolbar.getSecondNext() instanceof ImageView) {
                ((ImageView) this.toolbar.getSecondNext()).setImageResource(C1903R.C1905drawable.log_filter_selector);
            }
        } else if (i == 0 || i == strArr.length - 2) {
            this.toolbar.getSecondNext().setVisibility(0);
            if (this.toolbar.getSecondNext() instanceof ImageView) {
                ((ImageView) this.toolbar.getSecondNext()).setImageResource(C1903R.mipmap.nav_info);
            }
        } else {
            this.toolbar.getSecondNext().setVisibility(8);
        }
    }

    public void onBackPressed() {
        onBack((View) null);
    }

    public void onBack(View view) {
        finish();
        overridePendingTransition(C1903R.anim.left_in, C1903R.anim.right_out);
    }

    public void onNext(View view) {
        byte[] levels = getLevels();
        ARouter.getInstance().build(RouterActivityPath.Setting.PAGE_SETTING).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_PORT, this.port).withByte(ActivityEvent.DEVICE_VERSION, this.deviceVersion).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).withString(ActivityEvent.DEVICE_SOFTWARE_VERSION, this.softwareVersion).withString(ActivityEvent.DEVICE_HARDWARE_VERSION, this.hardwareVersion).withString(ActivityEvent.DEVICE_FIRMWARE_VERSION, this.firmwareVersion).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withByte(ActivityEvent.DEVICE_MODEL_ON_LEVEL, levels[0]).withByte(ActivityEvent.DEVICE_MODEL_OFF_LEVEL, levels[1]).withTransition(C1903R.anim.right_in, C1903R.anim.left_out).navigation(this);
    }

    public byte[] getLevels() {
        for (Fragment next : this.fragments) {
            if (next instanceof ILevel) {
                return ((ILevel) next).getLevels();
            }
        }
        return new byte[2];
    }
}
