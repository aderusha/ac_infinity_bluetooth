package com.eternal.account;

import android.os.Bundle;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.account.databinding.ActivityFirmwareUpdateBinding;
import com.eternal.account.model.FirmwareUpdateModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.TipDialog;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;

public class FirmwareUpdateActivity extends BaseActivity<ActivityFirmwareUpdateBinding, FirmwareUpdateModel> {
    public static final String SHOW_DIALOG = "show dialog";
    byte connectType;
    String devId;
    /* access modifiers changed from: private */
    public MaterialDialog dialog;
    String firmwareUrl;
    String mac;
    byte port;
    byte type;
    String updateVersion;

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_firmware_update;
    }

    public int initVariableId() {
        return C0977BR.firmwareUpdateModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((FirmwareUpdateModel) this.viewModel).init(RepositoryInjection.providerDeviceRepository(), this.mac, this.devId, this.port, this.type, this.firmwareUrl, this.updateVersion, this.connectType);
        initMessage();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) "show dialog", FirmwareUpdateModel.class, new BindingConsumer<FirmwareUpdateModel>() {
            public void call(FirmwareUpdateModel firmwareUpdateModel) {
                if (FirmwareUpdateActivity.this.dialog == null || !FirmwareUpdateActivity.this.dialog.isShowing()) {
                    FirmwareUpdateActivity firmwareUpdateActivity = FirmwareUpdateActivity.this;
                    MaterialDialog unused = firmwareUpdateActivity.dialog = TipDialog.showTipDialog(firmwareUpdateActivity, "Exit Firmware update?", "Your device will not be updated.", firmwareUpdateActivity.getResources().getString(C0997R.string.tip_do_not_exit), FirmwareUpdateActivity.this.getResources().getString(C0997R.string.tip_exit), true, true, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                            ((FirmwareUpdateModel) FirmwareUpdateActivity.this.viewModel).onBack.execute();
                        }
                    });
                }
            }
        });
    }

    public void onBackPressed() {
        ((FirmwareUpdateModel) this.viewModel).onBack.execute();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        unregisterMassage();
        super.onDestroy();
    }
}
