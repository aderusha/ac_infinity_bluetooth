package com.eternal.account.model;

import android.text.Spannable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0997R;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.utils.Utils;

public class InviteItemModel {
    public ObservableBoolean isSelect = new ObservableBoolean();
    public NetDevice model;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public BindingCommand<Void> onSelect = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            InviteItemModel.this.isSelect.set(!InviteItemModel.this.isSelect.get());
            Messenger.getDefault().send(InviteItemModel.this, "select item");
        }
    });
    public ObservableField<Spannable> title = new ObservableField<>();
    public MutableLiveData<String> type = new MutableLiveData<>();

    InviteItemModel(NetDevice netDevice, boolean z) {
        this.model = netDevice;
        this.name.setValue(netDevice.deviceName);
        this.type.setValue(Utils.getString(C0997R.string.tip_name_name, ProtocolTransformer.getType(netDevice.deviceType), netDevice.deviceCode));
        this.isSelect.set(z);
    }
}
