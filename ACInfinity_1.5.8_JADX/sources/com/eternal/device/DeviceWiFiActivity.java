package com.eternal.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.base.BaseActivity;
import com.eternal.device.databinding.ActivityDeviceWifiBinding;
import com.eternal.device.model.AddWiFiModel;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.res.C2663R;

public class DeviceWiFiActivity extends BaseActivity<ActivityDeviceWifiBinding, AddWiFiModel> {
    public static final String SHOW_DIALOG = "show dialog";
    byte deviceType;
    String mac;
    String typeName;

    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_device_wifi;
    }

    public int initVariableId() {
        return C1909BR.addWifiModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((AddWiFiModel) this.viewModel).init(this.mac, this.deviceType, this.typeName);
        initMessage();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) "show dialog", AddWiFiModel.class, new BindingConsumer<AddWiFiModel>() {
            public void call(AddWiFiModel addWiFiModel) {
                View inflate = LayoutInflater.from(DeviceWiFiActivity.this).inflate(C2663R.layout.dialog_tip, (ViewGroup) null, false);
                final MaterialDialog build = new MaterialDialog.Builder(DeviceWiFiActivity.this).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false).build();
                inflate.findViewById(C2663R.C2666id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        build.dismiss();
                    }
                });
                build.getCustomView().findViewById(C2663R.C2666id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((AddWiFiModel) DeviceWiFiActivity.this.viewModel).pushToMain();
                        build.dismiss();
                    }
                });
                build.show();
            }
        });
    }

    public void onBackPressed() {
        ((AddWiFiModel) this.viewModel).onBack.execute();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        unregisterMassage();
        ((AddWiFiModel) this.viewModel).cancel();
        super.onDestroy();
    }
}
