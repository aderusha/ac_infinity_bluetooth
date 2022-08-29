package com.eternal.main;

import android.app.NotificationManager;
import android.bluetooth.le.ScanResult;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.BaseActivity;
import com.eternal.base.BluetoothStateReceiver;
import com.eternal.base.TipDialog;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.Version;
import com.eternal.base.data.LogRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.data.ble.BleServer;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.CustomToastUtils;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.utils.AppUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import com.eternal.main.databinding.ActivityMainBinding;
import com.eternal.main.model.ItemModel;
import com.eternal.main.model.MainModel;
import com.eternal.res.C2663R;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.Collections;
import java.util.Iterator;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;
import p018me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainModel> {
    public static final String ON_ITEM_CLICK = "on_item_click";
    public static final String REQUEST_PERMISSION = "request permission";
    public static final int SELECT_DEVICE_REQUEST_CODE = 43;
    public static final String SHOW_DELETE_DIALOG = "show delete dialog";
    public static final String SHOW_LOGOUT_DIALOG = "show logout dialog";
    public static final String SHOW_UPDATE_DIALOG = "show update dialog";
    final int REQUEST_CODE_UPDATE = 9001;
    ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        }

        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(3, 0);
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            int layoutPosition = viewHolder.getLayoutPosition();
            int layoutPosition2 = viewHolder2.getLayoutPosition();
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                adapter.notifyItemMoved(layoutPosition, layoutPosition2);
            }
            Collections.swap(((MainModel) MainActivity.this.viewModel).items, layoutPosition, layoutPosition2);
            return true;
        }

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
            super.onSelectedChanged(viewHolder, i);
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            ((MainModel) MainActivity.this.viewModel).updateSequences();
        }
    };
    private MaterialDialog dialog;
    private AppUpdateManager m_appUpdateManager;
    private BluetoothStateReceiver receiver;
    private Disposable timeDispose;
    /* access modifiers changed from: private */
    public View tipView = null;
    /* access modifiers changed from: private */
    public MaterialDialog updateDialog;

    public int initContentView(Bundle bundle) {
        return C2343R.layout.activity_main;
    }

    public int initVariableId() {
        return C2323BR.model;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initModel();
        initMessage();
        intoLog(getIntent());
        registerReceiver();
        initView();
        requestPermission();
        if (Build.VERSION.SDK_INT >= 31) {
            ((ActivityMainBinding) this.binding).getRoot().getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    if (!((MainModel) MainActivity.this.viewModel).isReady()) {
                        return false;
                    }
                    ((ActivityMainBinding) MainActivity.this.binding).toolBar.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
        }
    }

    private void requestPermission() {
        String[] strArr;
        if (Build.VERSION.SDK_INT >= 31) {
            strArr = new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"};
        } else {
            strArr = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        }
        RxView.clicks(((ActivityMainBinding) this.binding).tvPermission).compose(new RxPermissions((FragmentActivity) this).ensure(strArr)).compose(bindToLifecycle()).subscribe(new MainActivity$$ExternalSyntheticLambda0(this));
    }

    public /* synthetic */ void lambda$requestPermission$0$MainActivity(Boolean bool) throws Exception {
        if (!bool.booleanValue()) {
            CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), getString(Build.VERSION.SDK_INT >= 31 ? C2663R.string.permission_reject_device : C2663R.string.permission_reject_location));
        } else if (!((MainModel) this.viewModel).hasPermission) {
            ((MainModel) this.viewModel).hasPermission = true;
            BleServer.getInstance().enable();
            ((MainModel) this.viewModel).scanDevice();
            if (AppUtils.isNeedTurnOnLocationService(Utils.getApp())) {
                CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C2343R.string.location_disabled));
            }
        }
    }

    private void initView() {
        ((ActivityMainBinding) this.binding).layoutDrawer.setDrawerLockMode(1);
        new ItemTouchHelper(this.callback).attachToRecyclerView(((ActivityMainBinding) this.binding).layoutDevice);
    }

    /* access modifiers changed from: private */
    public void appUpdate() {
        AppUtils.openGooglePlay(AppManager.getAppManager().currentActivity());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 9001) {
            if (i2 == -1) {
                KLog.m75w("GPUpdate", "应用内更新成功");
            } else if (i2 == 0) {
                KLog.m75w("GPUpdate", "应用内更新, 用户取消");
            } else {
                KLog.m75w("GPUpdate", "应用内更新，遇到错误");
            }
        } else if (i == 43 && i2 == -1) {
            KLog.m65e(((ScanResult) intent.getParcelableExtra("android.companion.extra.DEVICE")).toString());
        }
    }

    private void registerReceiver() {
        if (this.receiver == null) {
            this.receiver = new BluetoothStateReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction(BluetoothStateReceiver.GPS_ACTION);
            registerReceiver(this.receiver, intentFilter);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private void unregisterReceiver() {
        BluetoothStateReceiver bluetoothStateReceiver = this.receiver;
        if (bluetoothStateReceiver != null) {
            unregisterReceiver(bluetoothStateReceiver);
            this.receiver = null;
        }
    }

    private void intoLog(Intent intent) {
        ((NotificationManager) getSystemService(ActivityEvent.NOTIFICATION)).cancelAll();
        ShortcutBadger.removeCount(this);
        final LogRepository providerLogRepository = RepositoryInjection.providerLogRepository();
        providerLogRepository.clearNotify();
        if (ActivityEvent.ACTION_LOG.equals(intent.getAction())) {
            final String stringExtra = intent.getStringExtra(ActivityEvent.DEVICE_MAC);
            final String stringExtra2 = intent.getStringExtra(ActivityEvent.DEVICE_ID);
            boolean z = false;
            final byte byteExtra = intent.getByteExtra(ActivityEvent.DEVICE_PORT, (byte) 0);
            final byte byteExtra2 = intent.getByteExtra(ActivityEvent.DEVICE_VERSION, (byte) 0);
            final byte byteExtra3 = intent.getByteExtra(ActivityEvent.DEVICE_CONNECT_TYPE, (byte) 0);
            Iterator it = ((MainModel) this.viewModel).items.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((ItemModel) it.next()).getMac().equalsIgnoreCase(stringExtra)) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                toLog(intent, providerLogRepository, stringExtra, stringExtra2, byteExtra, byteExtra2, byteExtra3);
                return;
            }
            final Intent intent2 = intent;
            RepositoryInjection.providerDeviceRepository().getName(stringExtra, byteExtra).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceName>() {
                public void accept(DeviceName deviceName) throws Exception {
                    if (deviceName != null && !TextUtils.isEmpty(deviceName.name)) {
                        MainActivity.this.toLog(intent2, providerLogRepository, stringExtra, stringExtra2, byteExtra, byteExtra2, byteExtra3);
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void toLog(Intent intent, LogRepository logRepository, String str, String str2, byte b, byte b2, byte b3) {
        byte byteExtra = intent.getByteExtra(ActivityEvent.DEVICE_TYPE, (byte) 0);
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.mac = str;
        deviceInfo.deviceId = str2;
        deviceInfo.port = b;
        deviceInfo.type = byteExtra;
        deviceInfo.choosePort = b;
        ((MainModel) this.viewModel).setSelectedDevice(deviceInfo);
        AppManager.getAppManager().finishActivity(ARouter.getInstance().build(RouterActivityPath.Detail.PAGE_DETAIL).getDestination());
        Postcard withString = ARouter.getInstance().build(RouterActivityPath.Detail.PAGE_DETAIL).withAction(ActivityEvent.ACTION_LOG).withString(ActivityEvent.DEVICE_MAC, str).withString(ActivityEvent.DEVICE_ID, str2).withByte(ActivityEvent.DEVICE_PORT, b).withByte(ActivityEvent.DEVICE_TYPE, byteExtra).withByte(ActivityEvent.DEVICE_VERSION, b2).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, b3).withString(ActivityEvent.DEVICE_SOFTWARE_VERSION, intent.getStringExtra(ActivityEvent.DEVICE_SOFTWARE_VERSION)).withString(ActivityEvent.DEVICE_HARDWARE_VERSION, intent.getStringExtra(ActivityEvent.DEVICE_HARDWARE_VERSION)).withString(ActivityEvent.DEVICE_FIRMWARE_VERSION, intent.getStringExtra(ActivityEvent.DEVICE_FIRMWARE_VERSION));
        if (!RepositoryInjection.providerDeviceRepository().isConnect(str)) {
            logRepository.clearNotify();
        }
        withString.navigation(this);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        if (ActivityEvent.ACTION_ADD_DEVICE.equals(action)) {
            int intExtra = intent.getIntExtra(ActivityEvent.DEVICE_INDEX, -1);
            ((MainModel) this.viewModel).setNewDeviceMac(intent.getStringExtra(ActivityEvent.DEVICE_MAC));
            ((MainModel) this.viewModel).requestConnect(intExtra);
        } else if (ActivityEvent.ACTION_DELETE_DEVICE.equals(action)) {
            byte byteExtra = intent.getByteExtra(ActivityEvent.DEVICE_TYPE, (byte) 1);
            boolean booleanExtra = intent.getBooleanExtra(ActivityEvent.DELETE_ADVANCE_ALL, false);
            if (ProtocolTransformer.isWorkWiFi(byteExtra, intent.getByteExtra(ActivityEvent.DEVICE_CONNECT_TYPE, (byte) 0))) {
                ((MainModel) this.viewModel).removeFromNet(intent.getStringExtra(ActivityEvent.DEVICE_ID), booleanExtra);
            } else {
                ((MainModel) this.viewModel).remove(intent.getStringExtra(ActivityEvent.DEVICE_MAC), booleanExtra);
            }
        } else if (ActivityEvent.ACTION_APP_UPDATE.equals(action)) {
            appUpdate();
        } else {
            intoLog(intent);
        }
    }

    private void initModel() {
        ((MainModel) this.viewModel).init(RepositoryInjection.providerDeviceRepository());
        ((MainModel) this.viewModel).showTip.observe(this, new Observer<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool != null) {
                    if (bool.booleanValue()) {
                        if (MainActivity.this.tipView == null) {
                            ((ActivityMainBinding) MainActivity.this.binding).layoutDevice.setVisibility(8);
                            MainActivity mainActivity = MainActivity.this;
                            View unused = mainActivity.tipView = LayoutInflater.from(mainActivity).inflate(C2343R.layout.first_tip, ((ActivityMainBinding) MainActivity.this.binding).layoutMain, false);
                            ((ActivityMainBinding) MainActivity.this.binding).layoutMain.addView(MainActivity.this.tipView);
                        }
                    } else if (MainActivity.this.tipView != null) {
                        ((ActivityMainBinding) MainActivity.this.binding).layoutMain.removeView(MainActivity.this.tipView);
                        ((ActivityMainBinding) MainActivity.this.binding).layoutDevice.setVisibility(0);
                        View unused2 = MainActivity.this.tipView = null;
                    }
                }
            }
        });
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) "show delete dialog", ItemModel.class, new BindingConsumer<ItemModel>() {
            public void call(ItemModel itemModel) {
                MainActivity.this.showDelete(itemModel);
            }
        });
        Messenger.getDefault().register((Object) this, (Object) ON_ITEM_CLICK, ItemModel.class, new BindingConsumer<ItemModel>() {
            public void call(ItemModel itemModel) {
                ((ActivityMainBinding) MainActivity.this.binding).layoutDevice.closeMenu();
                ((MainModel) MainActivity.this.viewModel).setSelectedDevice(itemModel.getDeviceInfo());
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "show update dialog", Version.class, new BindingConsumer<Version>() {
            public void call(Version version) {
                MainActivity mainActivity = MainActivity.this;
                MaterialDialog unused = mainActivity.updateDialog = TipDialog.showTipDialog(mainActivity, "Software Update", "Version " + version.showVersion + " is available.\nPlease update your app to get the latest features.", MainActivity.this.getResources().getString(C2343R.string.tip_later), MainActivity.this.getResources().getString(C2343R.string.tip_update), !version.isForce, !version.isForce, new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                }, new View.OnClickListener() {
                    public void onClick(View view) {
                        MainActivity.this.appUpdate();
                    }
                });
            }
        });
        Messenger.getDefault().register((Object) this, (Object) SHOW_LOGOUT_DIALOG, MainModel.class, new BindingConsumer<MainModel>() {
            public void call(MainModel mainModel) {
                View inflate = LayoutInflater.from(MainActivity.this).inflate(C2663R.layout.dialog_tip, (ViewGroup) null, false);
                final MaterialDialog build = new MaterialDialog.Builder(MainActivity.this).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false).build();
                ((TextView) inflate.findViewById(C2343R.C2346id.tv_title)).setText("Log out");
                ((TextView) inflate.findViewById(C2343R.C2346id.tv_content)).setText("Are you sure you want to log out?");
                TextView textView = (TextView) inflate.findViewById(C2663R.C2666id.tv_cancel);
                textView.setText(MainActivity.this.getResources().getString(C2343R.string.tip_cancel_lowercase));
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        build.dismiss();
                    }
                });
                TextView textView2 = (TextView) build.getCustomView().findViewById(C2663R.C2666id.tv_confirm);
                textView2.setText(MainActivity.this.getResources().getString(C2343R.string.tip_ok));
                textView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((MainModel) MainActivity.this.viewModel).logout(true, true);
                        build.dismiss();
                    }
                });
                build.show();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) REQUEST_PERMISSION, (BindingAction) new BindingAction() {
            public void call() {
                ((ActivityMainBinding) MainActivity.this.binding).tvPermission.performClick();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showDelete(final ItemModel itemModel) {
        if (!RepositoryInjection.providerDeviceRepository().isConnect(itemModel.getMac()) || itemModel.getDeviceInfo().isShare != 0) {
            this.dialog = TipDialog.showTipDialog(this, getString(C2343R.string.tip_remove_device_title, new Object[]{itemModel.name.get()}), getString(C2343R.string.tip_remove_device_only_content, new Object[]{itemModel.name.get()}), getResources().getString(C2343R.string.tip_cancel_lowercase), getResources().getString(C2343R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
                public void onClick(View view) {
                    ((ActivityMainBinding) MainActivity.this.binding).layoutDevice.closeMenu();
                }
            }, new View.OnClickListener() {
                public void onClick(View view) {
                    ((ActivityMainBinding) MainActivity.this.binding).layoutDevice.closeMenu();
                    if (ProtocolTransformer.isWorkWiFi(itemModel.getType(), itemModel.connectType)) {
                        ((MainModel) MainActivity.this.viewModel).removeFromNet(itemModel, false);
                    } else {
                        ((MainModel) MainActivity.this.viewModel).remove(itemModel, false);
                    }
                }
            });
            return;
        }
        int i = C2343R.string.tip_remove_device_content;
        Object[] objArr = new Object[1];
        objArr[0] = ProtocolTransformer.isDeviceC(itemModel.getType()) ? NotificationCompat.CATEGORY_ALARM : "advance";
        this.dialog = TipDialog.showTipDialog2(this, getString(C2343R.string.tip_remove_device_title, new Object[]{itemModel.name.get()}), Utils.getString(i, objArr), getResources().getString(C2343R.string.tip_cancel_lowercase), getResources().getString(ProtocolTransformer.isDeviceC(itemModel.getType()) ? C2343R.string.tip_delete_device_and_alarm : C2343R.string.tip_delete_device_and_advance), getResources().getString(C2343R.string.tip_remove_device), true, true, new View.OnClickListener() {
            public void onClick(View view) {
                ((ActivityMainBinding) MainActivity.this.binding).layoutDevice.closeMenu();
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                ((ActivityMainBinding) MainActivity.this.binding).layoutDevice.closeMenu();
                if (ProtocolTransformer.isWorkWiFi(itemModel.getType(), itemModel.connectType)) {
                    ((MainModel) MainActivity.this.viewModel).removeFromNet(itemModel, true);
                } else {
                    ((MainModel) MainActivity.this.viewModel).remove(itemModel, true);
                }
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                ((ActivityMainBinding) MainActivity.this.binding).layoutDevice.closeMenu();
                if (ProtocolTransformer.isWorkWiFi(itemModel.getType(), itemModel.connectType)) {
                    ((MainModel) MainActivity.this.viewModel).removeFromNet(itemModel, false);
                } else {
                    ((MainModel) MainActivity.this.viewModel).remove(itemModel, false);
                }
            }
        }, (DialogInterface.OnDismissListener) null);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        ((MainModel) this.viewModel).save();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ((MainModel) this.viewModel).save();
        unregisterReceiver();
        ((MainModel) this.viewModel).stopScan();
        Messenger.getDefault().unregister(this);
        Disposable disposable = this.timeDispose;
        if (disposable != null) {
            disposable.dispose();
            this.timeDispose = null;
        }
        super.onDestroy();
    }
}
