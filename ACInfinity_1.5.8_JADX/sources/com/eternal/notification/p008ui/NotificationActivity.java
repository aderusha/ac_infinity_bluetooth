package com.eternal.notification.p008ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.IConnectAction;
import com.eternal.base.ISoftKeyBoardAction;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.BaseRxActivity;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import com.eternal.notification.C2420R;
import com.eternal.widget.guqiang.ProgressToolbar;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

/* renamed from: com.eternal.notification.ui.NotificationActivity */
public class NotificationActivity extends BaseRxActivity {
    public static final String SHOW_DELETE_DIALOG = "show notification delete dialog";
    /* access modifiers changed from: private */
    public Button btnConfirm;
    /* access modifiers changed from: private */
    public TextView connectText;
    byte connectType;
    Fragment content;
    String devId;
    byte deviceType;
    byte deviceVersion;
    private MaterialDialog dialog;
    private Disposable disConnEvent;
    int groupId;
    boolean isDegree;
    String mac;
    private Disposable nameRefresh;
    String notificationStr;
    char notifyEnd;
    String notifyName;
    char notifyStart;
    byte notifyType;
    byte offLevel;
    byte onLevel;
    byte port;
    private Disposable progressEvent;
    /* access modifiers changed from: private */
    public SoftKeyBroadManager softKeyBroadManager;
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            NotificationActivity.this.btnConfirm.requestLayout();
            if (NotificationActivity.this.content instanceof ISoftKeyBoardAction) {
                ((ISoftKeyBoardAction) NotificationActivity.this.content).onSoftKeyboardOpened(i);
            }
        }

        public void onSoftKeyboardClosed() {
            NotificationActivity.this.btnConfirm.requestLayout();
            if (NotificationActivity.this.content instanceof ISoftKeyBoardAction) {
                ((ISoftKeyBoardAction) NotificationActivity.this.content).onSoftKeyboardClosed();
            }
        }
    };
    private Disposable timeEvent;
    private Disposable timeRefresh;
    /* access modifiers changed from: private */
    public ProgressToolbar toolbar;

    private void registerMassage() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2420R.layout.activity_notification);
        ARouter.getInstance().inject(this);
        initFragment(this.mac, this.devId, this.port, this.deviceType, this.deviceVersion, this.notifyType, this.notificationStr, this.isDegree, this.onLevel, this.offLevel, this.connectType);
        initView();
        refreshName();
        registerMassage();
        refreshTime();
    }

    private void initFragment(String str, String str2, byte b, byte b2, byte b3, byte b4, String str3, boolean z, byte b5, byte b6, byte b7) {
        Bundle bundle = new Bundle();
        bundle.putString(ActivityEvent.DEVICE_MAC, str);
        bundle.putString(ActivityEvent.DEVICE_ID, str2);
        bundle.putByte(ActivityEvent.DEVICE_PORT, b);
        bundle.putString(ActivityEvent.NOTIFICATION, str3);
        bundle.putInt(ActivityEvent.NOTIFICATION_GROUP_ID, this.groupId);
        bundle.putBoolean(ActivityEvent.DEVICE_DEGREE, z);
        bundle.putByte(ActivityEvent.DEVICE_TYPE, b2);
        bundle.putByte(ActivityEvent.DEVICE_VERSION, b3);
        bundle.putByte(ActivityEvent.DEVICE_CONNECT_TYPE, b7);
        bundle.putString(ActivityEvent.NOTIFICATION_NAME, this.notifyName);
        bundle.putChar(ActivityEvent.NOTIFICATION_START, this.notifyStart);
        bundle.putChar(ActivityEvent.NOTIFICATION_END, this.notifyEnd);
        bundle.putByte(ActivityEvent.DEVICE_MODEL_ON_LEVEL, b5);
        bundle.putByte(ActivityEvent.DEVICE_MODEL_OFF_LEVEL, b6);
        if (b4 == 1) {
            AutomationFragment automationFragment = new AutomationFragment();
            this.content = automationFragment;
            automationFragment.setArguments(bundle);
            ((ProgressToolbar) findViewById(C2420R.C2423id.toolbar)).setTitle(getString(str3 == null ? C2420R.string.title_add_automation : C2420R.string.title_edit_automation));
        } else {
            bundle.putByte(ActivityEvent.NOTIFICATION_TYPE, b4);
            OtherFragment otherFragment = new OtherFragment();
            this.content = otherFragment;
            otherFragment.setArguments(bundle);
            ((ProgressToolbar) findViewById(C2420R.C2423id.toolbar)).setTitle(getString(str3 == null ? C2420R.string.title_add_alerts : C2420R.string.title_edit_alerts));
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(C2420R.C2423id.layout_content, this.content);
        beginTransaction.commit();
    }

    private void initView() {
        ProgressToolbar progressToolbar = (ProgressToolbar) findViewById(C2420R.C2423id.toolbar);
        this.toolbar = progressToolbar;
        progressToolbar.getBack().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NotificationActivity.this.onBackPressed();
            }
        });
        this.toolbar.getNext().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (NotificationActivity.this.content instanceof AutomationFragment) {
                    ((AutomationFragment) NotificationActivity.this.content).onCancel();
                } else if (NotificationActivity.this.content instanceof OtherFragment) {
                    ((OtherFragment) NotificationActivity.this.content).onCancel();
                }
            }
        });
        Button button = (Button) findViewById(C2420R.C2423id.btn_confirm);
        this.btnConfirm = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (NotificationActivity.this.content instanceof AutomationFragment) {
                    ((AutomationFragment) NotificationActivity.this.content).onConfirm();
                } else if (NotificationActivity.this.content instanceof OtherFragment) {
                    ((OtherFragment) NotificationActivity.this.content).onConfirm();
                }
            }
        });
        this.connectText = (TextView) findViewById(C2420R.C2423id.txt_connect_time);
        if (RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            registerEvent();
        }
        SoftKeyBroadManager softKeyBroadManager2 = new SoftKeyBroadManager(findViewById(C2420R.C2423id.root));
        this.softKeyBroadManager = softKeyBroadManager2;
        softKeyBroadManager2.addSoftKeyboardStateListener(this.softKeyboardStateListener);
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (Math.abs(f2) <= Math.abs(f) || f2 == 0.0f || !NotificationActivity.this.softKeyBroadManager.isSoftKeyboardOpened() || !(NotificationActivity.this.content instanceof ISoftKeyBoardAction)) {
                    return false;
                }
                ((ISoftKeyBoardAction) NotificationActivity.this.content).closeKeyboard();
                return true;
            }
        });
        findViewById(C2420R.C2423id.scrollView).setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void registerEvent() {
        Disposable disposable = this.disConnEvent;
        if (disposable == null || disposable.isDisposed()) {
            this.disConnEvent = RxBus.getDefault().toObservable(BluetoothEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BluetoothEvent>() {
                public void accept(BluetoothEvent bluetoothEvent) {
                    if (bluetoothEvent.what != 0 && bluetoothEvent.what == 2 && NotificationActivity.this.mac.equals(bluetoothEvent.mac)) {
                        NotificationActivity.this.registerEvent();
                    }
                }
            });
        }
        Disposable disposable2 = this.progressEvent;
        if (disposable2 == null || disposable2.isDisposed()) {
            this.progressEvent = RxBus.getDefault().toObservable(ProgressEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ProgressEvent>() {
                public void accept(ProgressEvent progressEvent) throws Exception {
                    if (progressEvent.Statue == 0) {
                        NotificationActivity.this.toolbar.startProgress();
                    } else if (progressEvent.Statue == 1) {
                        NotificationActivity.this.toolbar.endProgress(progressEvent.callback);
                    }
                }
            });
        }
    }

    private void refreshTime() {
        if (!TextUtils.isEmpty(this.mac)) {
            Disposable disposable = this.timeRefresh;
            if (disposable == null || disposable.isDisposed()) {
                this.timeRefresh = RepositoryInjection.providerDeviceRepository().getConnectInfo(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ConnectInfo>() {
                    public void accept(ConnectInfo connectInfo) {
                        if (RepositoryInjection.providerDeviceRepository().isConnect(NotificationActivity.this.mac)) {
                            NotificationActivity.this.connectText.setVisibility(8);
                        } else {
                            NotificationActivity.this.connectText.setText(Utils.getString(C2420R.string.tip_last, ProtocolTransformer.getTime(connectInfo.connectTime)));
                            NotificationActivity.this.connectText.setVisibility(0);
                        }
                        if (NotificationActivity.this.connectType != connectInfo.connectType) {
                            NotificationActivity.this.connectType = connectInfo.connectType;
                            NotificationActivity.this.devId = connectInfo.deviceId;
                            if (NotificationActivity.this.content != null && (NotificationActivity.this.content instanceof IConnectAction)) {
                                ((IConnectAction) NotificationActivity.this.content).setConnectType(NotificationActivity.this.devId, NotificationActivity.this.connectType);
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
    }

    private void refreshName() {
        Disposable disposable = this.nameRefresh;
        if (disposable == null || disposable.isDisposed()) {
            this.nameRefresh = RepositoryInjection.providerDeviceRepository().getDeviceName(this.mac, (byte) 0).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceName>() {
                public void accept(DeviceName deviceName) {
                    if (deviceName != null) {
                        NotificationActivity.this.toolbar.setSubtitle(deviceName.name);
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    KLog.m65e(th);
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
        Disposable disposable2 = this.disConnEvent;
        if (disposable2 != null) {
            disposable2.dispose();
            this.disConnEvent = null;
        }
        Disposable disposable3 = this.progressEvent;
        if (disposable3 != null) {
            disposable3.dispose();
            this.progressEvent = null;
        }
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        unregisterEvent();
        unregisterMassage();
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        Disposable disposable = this.nameRefresh;
        if (disposable != null) {
            disposable.dispose();
            this.nameRefresh = null;
        }
        Disposable disposable2 = this.timeRefresh;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.timeRefresh.dispose();
        }
        if (this.softKeyBroadManager != null) {
            this.softKeyBroadManager = null;
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(C2420R.anim.left_in, C2420R.anim.right_out);
    }
}
