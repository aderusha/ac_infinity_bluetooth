package com.eternal.device.model;

import androidx.databinding.ObservableField;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.device.C1922R;
import com.eternal.device.utils.wifimanager.IWifi;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.component.AppManager;

public class ItemModel {
    public ObservableField<String> device;
    /* access modifiers changed from: private */
    public OnItemClickListener listener;
    public ObservableField<String> name;
    public BindingCommand<Void> onItemClick;
    /* access modifiers changed from: private */
    public String socketIp;
    /* access modifiers changed from: private */
    public int socketPort;
    /* access modifiers changed from: private */
    public IWifi wifi;

    interface OnItemClickListener {
        void onItemClick(IWifi iWifi);
    }

    ItemModel(IWifi iWifi, String str, int i) {
        this(iWifi, str, i, (OnItemClickListener) null);
    }

    ItemModel(IWifi iWifi, String str, int i, OnItemClickListener onItemClickListener) {
        this.name = new ObservableField<>();
        this.device = new ObservableField<>();
        this.onItemClick = new BindingCommand<>((BindingAction) new BindingAction() {
            public void call() {
                if (ItemModel.this.listener != null) {
                    ItemModel.this.listener.onItemClick(ItemModel.this.wifi);
                } else {
                    ARouter.getInstance().build(RouterActivityPath.Device.PAGE_WIFI_CONNECT).withString(ActivityEvent.SSID, ItemModel.this.wifi.name()).withString(ActivityEvent.SOCKET_IP, ItemModel.this.socketIp).withInt(ActivityEvent.SOCKET_PORT, ItemModel.this.socketPort).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
                }
            }
        });
        this.wifi = iWifi;
        this.socketIp = str;
        this.socketPort = i;
        this.name.set(iWifi.name());
        this.listener = onItemClickListener;
    }

    public IWifi getWifi() {
        return this.wifi;
    }
}
