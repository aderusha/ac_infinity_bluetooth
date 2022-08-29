package com.eternal.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.account.databinding.ActivityVerifyBinding;
import com.eternal.account.model.VersionModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.TipDialog;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.utils.AppUtils;
import com.eternal.framework.utils.KLog;
import com.google.android.play.core.appupdate.AppUpdateManager;

public class VersionActivity extends BaseActivity<ActivityVerifyBinding, VersionModel> {
    public static final String SHOW_UPDATE_DIALOG = "show update dialog";
    final int REQUEST_CODE_UPDATE = 9001;
    byte deviceType;
    private MaterialDialog dialog;
    String firmwareUrl;
    String firmwareVersion;
    String hardwareVersion;
    boolean isAppUpdate;
    private AppUpdateManager mAppUpdateManager;
    String mac;
    byte port;
    String softwareVersion;
    byte type;
    String updateVersion;

    private void initView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.isAppUpdate = getIntent().getBooleanExtra(ActivityEvent.IS_APP_UPDATE, true);
        byte byteExtra = getIntent().getByteExtra(ActivityEvent.DEVICE_CONNECT_TYPE, (byte) 0);
        String stringExtra = getIntent().getStringExtra(ActivityEvent.DEVICE_ID);
        initMessage();
        ((VersionModel) this.viewModel).init(RepositoryInjection.providerDeviceRepository(), this.mac, stringExtra, this.port, this.deviceType, this.firmwareUrl, this.isAppUpdate, this.updateVersion, this.softwareVersion, byteExtra);
        initView();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Messenger.getDefault().unregister(this);
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        super.onDestroy();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) "show update dialog", VersionModel.class, new BindingConsumer<VersionModel>() {
            public void call(VersionModel versionModel) {
                if (VersionActivity.this.isAppUpdate) {
                    VersionActivity.this.appUpdate();
                } else {
                    VersionActivity.this.showFirmwareUpdateDialog();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void showFirmwareUpdateDialog() {
        if (RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            this.dialog = TipDialog.showTipDialog(this, "Firmware Update", getString(C0997R.string.tip_firmware_update), getResources().getString(C0997R.string.tip_later), getResources().getString(C0997R.string.tip_update), true, true, new View.OnClickListener() {
                public void onClick(View view) {
                }
            }, new View.OnClickListener() {
                public void onClick(View view) {
                    ((VersionModel) VersionActivity.this.viewModel).pushToFirmwareUpdatePage();
                }
            });
            return;
        }
        this.dialog = TipDialog.showTipDialog(this, getResources().getString(C0997R.string.tip_connection_lost), getResources().getString(C0997R.string.tip_connect_device), (String) null, getResources().getString(C0997R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null);
    }

    /* access modifiers changed from: private */
    public void appUpdate() {
        AppUtils.openGooglePlay(AppManager.getAppManager().currentActivity());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 9001) {
            return;
        }
        if (i2 == -1) {
            KLog.m75w("GPUpdate", "应用内更新成功");
        } else if (i2 == 0) {
            KLog.m75w("GPUpdate", "应用内更新, 用户取消");
        } else {
            KLog.m75w("GPUpdate", "应用内更新，遇到错误");
        }
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_version;
    }

    public int initVariableId() {
        return C0977BR.versionModel;
    }

    public void onBackPressed() {
        ((VersionModel) this.viewModel).onBack.execute();
    }
}
