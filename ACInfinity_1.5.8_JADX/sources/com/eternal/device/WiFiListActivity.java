package com.eternal.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eternal.base.BaseActivity;
import com.eternal.device.databinding.ActivityWifiLlistBinding;
import com.eternal.device.model.WiFiListModel;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.utils.KLog;
import com.eternal.res.C2663R;

public class WiFiListActivity extends BaseActivity<ActivityWifiLlistBinding, WiFiListModel> {
    public static final String SHOW_DIALOG = "show dialog";
    String socketIp;
    int socketPort;

    private void initView() {
    }

    public int initContentView(Bundle bundle) {
        return C1922R.layout.activity_wifi_llist;
    }

    public int initVariableId() {
        return C1909BR.wifiListModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((WiFiListModel) this.viewModel).init(this.socketIp, this.socketPort);
        initView();
        initMessage();
    }

    private void initMessage() {
        Messenger.getDefault().register((Object) this, (Object) "show dialog", WiFiListModel.class, new BindingConsumer<WiFiListModel>() {
            public void call(WiFiListModel wiFiListModel) {
                View inflate = LayoutInflater.from(WiFiListActivity.this).inflate(C2663R.layout.dialog_tip, (ViewGroup) null, false);
                final MaterialDialog build = new MaterialDialog.Builder(WiFiListActivity.this).backgroundColor(ViewCompat.MEASURED_SIZE_MASK).customView(inflate, false).build();
                inflate.findViewById(C2663R.C2666id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        build.dismiss();
                    }
                });
                build.getCustomView().findViewById(C2663R.C2666id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((WiFiListModel) WiFiListActivity.this.viewModel).pushToMain();
                        build.dismiss();
                    }
                });
                build.show();
            }
        });
    }

    public void onBackPressed() {
        ((WiFiListModel) this.viewModel).onBack.execute();
    }

    private void unregisterMassage() {
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        KLog.m65e("destroy");
        unregisterMassage();
        ((WiFiListModel) this.viewModel).unregisterReceiver();
        super.onDestroy();
    }
}
