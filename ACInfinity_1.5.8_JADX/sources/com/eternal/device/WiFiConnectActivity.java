package com.eternal.device;

import android.companion.AssociationRequest;
import android.companion.CompanionDeviceManager;
import android.companion.WifiDeviceFilter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.base.BaseActivity;
import com.eternal.base.TipDialog;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.utils.CustomToastUtils;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.device.databinding.ActivityWifiConnectBinding;
import com.eternal.device.model.WiFiConnectModel;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.utils.AppUtils;
import com.eternal.framework.utils.BarUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.NetworkUtil;
import com.eternal.framework.utils.ScreenUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.res.C2663R;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.CompletableSource;
import p014io.reactivex.android.schedulers.AndroidSchedulers;

public class WiFiConnectActivity extends BaseActivity<ActivityWifiConnectBinding, WiFiConnectModel> {
    public static final String DISMISS_SHEET_DIALOG = "dismiss sheet dialog";
    private static final int SELECT_DEVICE_REQUEST_CODE = 42;
    public static final String SHOW_DIALOG = "show dialog";
    public static final String SHOW_SHEET_DIALOG = "show sheet dialog";
    private CompanionDeviceManager.Callback cdmCallback;
    private WifiDeviceFilter deviceFilter;
    private CompanionDeviceManager deviceManager;
    byte deviceType;
    private MaterialDialog mDialog;
    String mac;
    private AssociationRequest pairingRequest;
    MaterialDialog sheetDialog;
    String socketIp;
    int socketPort;
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            ((ActivityWifiConnectBinding) WiFiConnectActivity.this.binding).ibNext.requestLayout();
            ((ActivityWifiConnectBinding) WiFiConnectActivity.this.binding).tvContent.setVisibility(8);
            ((ActivityWifiConnectBinding) WiFiConnectActivity.this.binding).tvHelp.setVisibility(8);
        }

        public void onSoftKeyboardClosed() {
            ((ActivityWifiConnectBinding) WiFiConnectActivity.this.binding).ibNext.requestLayout();
            Completable.create(new CompletableOnSubscribe() {
                public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                    completableEmitter.onComplete();
                }
            }).delay(200, TimeUnit.MILLISECONDS).andThen((CompletableSource) Completable.create(new CompletableOnSubscribe() {
                public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                    ((ActivityWifiConnectBinding) WiFiConnectActivity.this.binding).tvContent.setVisibility(0);
                    ((ActivityWifiConnectBinding) WiFiConnectActivity.this.binding).tvHelp.setVisibility(0);
                    completableEmitter.onComplete();
                }
            }).subscribeOn(AndroidSchedulers.mainThread())).subscribe();
        }
    };
    String ssid;
    String typeName;
    /* access modifiers changed from: private */
    public boolean visible;

    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_wifi_connect;
    }

    public int initVariableId() {
        return C1909BR.wifiConnectModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((WiFiConnectModel) this.viewModel).init(this.ssid, this.socketIp, this.socketPort, this.deviceType, this.mac, this.typeName);
        initView();
        initMessage();
        if (Build.VERSION.SDK_INT >= 26) {
            this.deviceManager = (CompanionDeviceManager) getSystemService(CompanionDeviceManager.class);
            this.deviceFilter = new WifiDeviceFilter.Builder().build();
            this.pairingRequest = new AssociationRequest.Builder().addDeviceFilter(this.deviceFilter).build();
        }
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) "show dialog", WiFiConnectModel.class, new BindingConsumer<WiFiConnectModel>() {
            public void call(WiFiConnectModel wiFiConnectModel) {
                View inflate = LayoutInflater.from(WiFiConnectActivity.this).inflate(C2663R.layout.dialog_tip, (ViewGroup) null, false);
                final MaterialDialog build = new MaterialDialog.Builder(WiFiConnectActivity.this).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false).build();
                inflate.findViewById(C2663R.C2666id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        build.dismiss();
                    }
                });
                build.getCustomView().findViewById(C2663R.C2666id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((WiFiConnectModel) WiFiConnectActivity.this.viewModel).pushToMain();
                        build.dismiss();
                    }
                });
                build.show();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "show sheet dialog", WiFiConnectModel.class, new BindingConsumer<WiFiConnectModel>() {
            public void call(WiFiConnectModel wiFiConnectModel) {
                if (Build.VERSION.SDK_INT >= 30) {
                    WiFiConnectActivity.this.showSystemWifiDialog();
                } else {
                    WiFiConnectActivity.this.showBottomSheet();
                }
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "dismiss sheet dialog", WiFiConnectModel.class, new BindingConsumer<WiFiConnectModel>() {
            public void call(WiFiConnectModel wiFiConnectModel) {
                WiFiConnectActivity.this.dismissBottomSheet();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showSystemWifiDialog() {
        if (this.cdmCallback == null) {
            this.cdmCallback = new CompanionDeviceManager.Callback() {
                public void onDeviceFound(IntentSender intentSender) {
                    try {
                        if (WiFiConnectActivity.this.visible) {
                            WiFiConnectActivity.this.startIntentSenderForResult(intentSender, 42, (Intent) null, 0, 0, 0);
                        }
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(CharSequence charSequence) {
                    KLog.m65e(charSequence.toString());
                }
            };
        }
        this.deviceManager.associate(this.pairingRequest, this.cdmCallback, (Handler) null);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 42 && i2 == -1) {
            ScanResult scanResult = (ScanResult) intent.getParcelableExtra("android.companion.extra.DEVICE");
            if (!NetworkUtil.is24GHz(scanResult.frequency)) {
                this.mDialog = TipDialog.showTipDialog(this, "5GHz Connection Detected", getString(C1922R.string.tip_5g_detect_content), (String) null, getString(C1922R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null, new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                });
                return;
            }
            ((WiFiConnectModel) this.viewModel).setSSID(scanResult.SSID);
        }
    }

    /* access modifiers changed from: private */
    public void showBottomSheet() {
        if (((WiFiConnectModel) this.viewModel).items.size() <= 0) {
            if (!AppUtils.isLocationEnabled(Utils.getApp())) {
                CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C1922R.string.location_disabled));
                return;
            } else if (!lacksPermission()) {
                ((WiFiConnectModel) this.viewModel).scanWifi();
            } else if (Build.VERSION.SDK_INT >= 26) {
                showSystemWifiDialog();
                return;
            } else {
                CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C1922R.string.permission_reject_location));
                return;
            }
        }
        ViewParent parent = ((ActivityWifiConnectBinding) this.binding).llSheetDialog.getParent();
        ((ActivityWifiConnectBinding) this.binding).llSheetDialog.setVisibility(0);
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(((ActivityWifiConnectBinding) this.binding).llSheetDialog);
        }
        MaterialDialog build = new MaterialDialog.Builder(this).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView((View) ((ActivityWifiConnectBinding) this.binding).llSheetDialog, false).build();
        this.sheetDialog = build;
        build.setCancelable(true);
        Window window = this.sheetDialog.getWindow();
        if (window == null) {
            this.sheetDialog.show();
            return;
        }
        window.setGravity(8388659);
        WindowManager.LayoutParams attributes = this.sheetDialog.getWindow().getAttributes();
        int[] iArr = new int[2];
        ((ActivityWifiConnectBinding) this.binding).tvWifiName.getLocationOnScreen(iArr);
        attributes.x = iArr[0];
        attributes.y = (iArr[1] + ((ActivityWifiConnectBinding) this.binding).tvWifiName.getHeight()) - BarUtils.getStatusBarHeight();
        window.setAttributes(attributes);
        window.setLayout(ScreenUtils.getDisplayDimensions(this).x - (iArr[0] * 2), getResources().getDimensionPixelSize(C1922R.dimen.dp_225));
        this.sheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                ((ActivityWifiConnectBinding) WiFiConnectActivity.this.binding).llSheetDialog.setVisibility(8);
            }
        });
        this.sheetDialog.show();
    }

    private static boolean lacksPermission() {
        return ContextCompat.checkSelfPermission(AppManager.getAppManager().currentActivity(), "android.permission.ACCESS_FINE_LOCATION") == -1;
    }

    /* access modifiers changed from: private */
    public void dismissBottomSheet() {
        MaterialDialog materialDialog = this.sheetDialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.sheetDialog.dismiss();
        }
    }

    private void initView() {
        new SoftKeyBroadManager(((ActivityWifiConnectBinding) this.binding).root).addSoftKeyboardStateListener(this.softKeyboardStateListener);
        ((ActivityWifiConnectBinding) this.binding).etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.getId() != ((ActivityWifiConnectBinding) WiFiConnectActivity.this.binding).etPwd.getId()) {
                    return;
                }
                if (z) {
                    ((WiFiConnectModel) WiFiConnectActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(WiFiConnectActivity.this.getResources().getColor(C1922R.C1923color.white)));
                    if (((WiFiConnectModel) WiFiConnectActivity.this.viewModel).pwdErrVisible.getValue() != Boolean.TRUE) {
                        ((WiFiConnectModel) WiFiConnectActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(WiFiConnectActivity.this.getResources().getColor(C1922R.C1923color.white)));
                    }
                } else if (TextUtils.isEmpty(((WiFiConnectModel) WiFiConnectActivity.this.viewModel).passwordText.getValue())) {
                    ((WiFiConnectModel) WiFiConnectActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(WiFiConnectActivity.this.getResources().getColor(C1922R.C1923color.color_838383)));
                    ((WiFiConnectModel) WiFiConnectActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(WiFiConnectActivity.this.getResources().getColor(C1922R.C1923color.color_838383)));
                } else {
                    ((WiFiConnectModel) WiFiConnectActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(WiFiConnectActivity.this.getResources().getColor(C1922R.C1923color.white)));
                    ((WiFiConnectModel) WiFiConnectActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(WiFiConnectActivity.this.getResources().getColor(C1922R.C1923color.white)));
                }
            }
        });
    }

    public void onBackPressed() {
        ((WiFiConnectModel) this.viewModel).onBack.execute();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Messenger.getDefault().send(Boolean.TRUE, ActivityEvent.BACK_WIFI_PAGE);
        this.visible = true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.visible = false;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        List<String> associations;
        unregisterMassage();
        ((WiFiConnectModel) this.viewModel).onDestroy();
        MaterialDialog materialDialog = this.sheetDialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.sheetDialog.dismiss();
        }
        MaterialDialog materialDialog2 = this.mDialog;
        if (materialDialog2 != null && materialDialog2.isShowing()) {
            this.mDialog.dismiss();
        }
        if (this.deviceManager != null) {
            if (Build.VERSION.SDK_INT >= 26 && (associations = this.deviceManager.getAssociations()) != null) {
                for (String disassociate : associations) {
                    this.deviceManager.disassociate(disassociate);
                }
            }
            this.deviceManager = null;
        }
        if (this.cdmCallback != null) {
            this.cdmCallback = null;
        }
        super.onDestroy();
    }
}
