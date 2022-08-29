package com.eternal.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.base.BaseActivity;
import com.eternal.base.TipDialog;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.device.databinding.ActivityDeviceBinding;
import com.eternal.device.model.AddModel;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.res.C2663R;

public class DeviceActivity extends BaseActivity<ActivityDeviceBinding, AddModel> {
    public static final String SHOW_CONNECT_DIALOG = "show connect dialog";
    public static final String SHOW_DIALOG = "show dialog";
    /* access modifiers changed from: private */
    public MaterialDialog dialog;

    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_device;
    }

    public int initVariableId() {
        return C1909BR.model;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((AddModel) this.viewModel).init(RepositoryInjection.providerDeviceRepository(), getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, (byte) 1));
        initMessage();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) "show dialog", AddModel.class, new BindingConsumer<AddModel>() {
            public void call(AddModel addModel) {
                View inflate = LayoutInflater.from(DeviceActivity.this).inflate(C2663R.layout.dialog_tip, (ViewGroup) null, false);
                MaterialDialog unused = DeviceActivity.this.dialog = new MaterialDialog.Builder(DeviceActivity.this).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false).build();
                inflate.findViewById(C2663R.C2666id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        DeviceActivity.this.dialog.dismiss();
                    }
                });
                DeviceActivity.this.dialog.getCustomView().findViewById(C2663R.C2666id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((AddModel) DeviceActivity.this.viewModel).pushToMain();
                        DeviceActivity.this.dialog.dismiss();
                    }
                });
                DeviceActivity.this.dialog.show();
            }
        });
        Messenger.getDefault().register((Object) this, (Object) "show connect dialog", AddModel.class, new BindingConsumer<AddModel>() {
            public void call(AddModel addModel) {
                DeviceActivity deviceActivity = DeviceActivity.this;
                MaterialDialog unused = deviceActivity.dialog = TipDialog.showTipDialog(deviceActivity, deviceActivity.getString(C1922R.string.tip_bluetooth), DeviceActivity.this.getString(C1922R.string.tip_bluetooth_only), DeviceActivity.this.getResources().getString(C1922R.string.tip_cancel_lowercase), DeviceActivity.this.getResources().getString(C1922R.string.tip_confirm_lowercase), true, true, new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                }, new View.OnClickListener() {
                    public void onClick(View view) {
                        ((AddModel) DeviceActivity.this.viewModel).saveDevice();
                    }
                });
            }
        });
    }

    public void onBackPressed() {
        ((AddModel) this.viewModel).onBack.execute();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        unregisterMassage();
        ((AddModel) this.viewModel).onDestroy();
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
        super.onDestroy();
    }
}
