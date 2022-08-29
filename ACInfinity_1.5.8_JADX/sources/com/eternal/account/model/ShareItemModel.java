package com.eternal.account.model;

import android.text.Spannable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0997R;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.utils.Utils;

public class ShareItemModel {
    public Observable.OnPropertyChangedCallback callback;
    public MutableLiveData<String> headTxt = new MutableLiveData<>();
    public boolean isHeader;
    public MutableLiveData<Boolean> isPending = new MutableLiveData<>();
    public MutableLiveData<Boolean> isShowPending = new MutableLiveData<>();
    public MutableLiveData<Boolean> isShowShareIcon = new MutableLiveData<>();
    public boolean isShowState;
    public NetDevice model;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(ShareItemModel.this, "show delete dialog");
        }
    });
    public BindingCommand<Void> onEdit = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (ShareItemModel.this.isPending.getValue() == Boolean.TRUE && ShareItemModel.this.isShowState) {
                Messenger.getDefault().send(ShareItemModel.this, "show accpet dialog");
            }
        }
    });
    public ObservableField<Spannable> title = new ObservableField<>();
    public MutableLiveData<String> type = new MutableLiveData<>();

    ShareItemModel(NetDevice netDevice, boolean z) {
        this.model = netDevice;
        this.name.setValue(netDevice.deviceName);
        boolean z2 = false;
        this.type.setValue(Utils.getString(C0997R.string.tip_name_name, ProtocolTransformer.getType(netDevice.deviceType), netDevice.deviceCode));
        this.isPending.setValue(Boolean.valueOf(netDevice.isShare == 0));
        this.isShowState = z;
        if (z) {
            this.isShowPending.setValue(this.isPending.getValue());
            this.isShowShareIcon.setValue(Boolean.valueOf(this.isPending.getValue() == Boolean.FALSE ? true : z2));
            return;
        }
        this.isShowPending.setValue(Boolean.FALSE);
        this.isShowShareIcon.setValue(Boolean.FALSE);
    }

    ShareItemModel(String str, boolean z) {
        this.headTxt.setValue(str);
        this.isHeader = z;
    }
}
