package com.eternal.main.model;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.main.C2343R;

public class PortModel {
    public ObservableBoolean dashVisibility = new ObservableBoolean();
    public ObservableField<String> fanSize = new ObservableField<>();
    public ObservableInt fanState = new ObservableInt();
    public ObservableInt fanType = new ObservableInt();
    public ObservableBoolean fanVisibility = new ObservableBoolean();
    public ObservableBoolean iconVisible = new ObservableBoolean();
    public ObservableBoolean isConnet = new ObservableBoolean();
    public ObservableBoolean isPlug = new ObservableBoolean();
    /* access modifiers changed from: private */
    public OnClickListener listener;
    private String mac;
    public ObservableField<String> name = new ObservableField<>();
    public BindingCommand<Void> onClick = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (PortModel.this.listener != null) {
                PortModel.this.listener.onClick(PortModel.this.portId);
            }
        }
    });
    public byte portId;
    public ObservableBoolean powerOff = new ObservableBoolean();
    public ObservableBoolean powerVisibility = new ObservableBoolean();
    public ObservableBoolean visibility = new ObservableBoolean();
    public ObservableField<String> workType = new ObservableField<>();
    public ObservableBoolean workTypeVisibility = new ObservableBoolean();

    interface OnClickListener {
        void onClick(byte b);
    }

    public void setListener(OnClickListener onClickListener) {
        this.listener = onClickListener;
    }

    PortModel() {
    }

    PortModel(PortInfo portInfo) {
        if (portInfo.fanType == 0) {
            this.fanVisibility.set(true);
            this.fanState.set(getState(portInfo.fanState));
            this.fanSize.set(String.valueOf(portInfo.fan));
            return;
        }
        String str = "ON";
        if (portInfo.fanType == 1) {
            this.fanVisibility.set(false);
            this.fanSize.set(portInfo.fan == 0 ? "OFF" : str);
            return;
        }
        this.fanVisibility.set(false);
        this.fanSize.set(str);
    }

    /* access modifiers changed from: package-private */
    public void update(PortInfo portInfo, byte b, boolean z) {
        this.iconVisible.set(portInfo.f138id != 0);
        this.portId = portInfo.f138id;
        this.visibility.set(true);
        this.name.set(ProtocolTransformer.getDisplayPortName(portInfo.f138id, b, portInfo.name));
        if (portInfo.isPlug) {
            if (z) {
                this.fanVisibility.set(false);
                this.powerVisibility.set(true);
                this.powerOff.set(portInfo.fan == 0);
            } else {
                this.powerVisibility.set(false);
                this.fanVisibility.set(true);
                this.fanState.set(getState(portInfo.fanState));
                this.fanSize.set(String.valueOf(portInfo.fan));
            }
            if (portInfo.isAdv) {
                portInfo.workType = 11;
            }
            if (portInfo.workType == 0) {
                this.workTypeVisibility.set(false);
            } else {
                this.workTypeVisibility.set(true);
                this.workType.set(ProtocolTransformer.getModeString(portInfo.workType));
            }
            this.isPlug.set(true);
            this.dashVisibility.set(false);
            this.fanType.set(ProtocolTransformer.getPlugIcon(portInfo.portType, portInfo.fanType, this.isConnet.get(), b));
            return;
        }
        this.isPlug.set(false);
        this.fanVisibility.set(false);
        this.powerVisibility.set(false);
        this.dashVisibility.set(true);
        this.workTypeVisibility.set(false);
        this.fanType.set(ProtocolTransformer.getPlugIcon(0, 0, false, b));
    }

    private int getState(byte b) {
        return b != 0 ? b != 1 ? !this.isConnet.get() ? C2343R.mipmap.increase_trend_grey : C2343R.mipmap.increase_trend : !this.isConnet.get() ? C2343R.mipmap.decrease_trend_grey : C2343R.mipmap.decrease_trend : !this.isConnet.get() ? C2343R.mipmap.stead_trend_grey : C2343R.mipmap.stead_trend;
    }
}
