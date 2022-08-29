package com.eternal.account.model;

import android.text.Spannable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0997R;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.utils.Utils;

public class TimeZoneItemModel {
    public Observable.OnPropertyChangedCallback callback;
    public NetDevice model;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public BindingCommand<Void> onEdit = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_TIME_ZONE_SEARCH).withString(ActivityEvent.DEVICE_ID, TimeZoneItemModel.this.model.deviceId).withString(ActivityEvent.DEVICE_TIME_ZONE, TimeZoneItemModel.this.model.devTimeZone).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public MutableLiveData<String> timeZone = new MutableLiveData<>();
    public ObservableField<Spannable> title = new ObservableField<>();
    public MutableLiveData<String> type = new MutableLiveData<>();

    TimeZoneItemModel(NetDevice netDevice) {
        this.model = netDevice;
        this.name.setValue(netDevice.deviceName);
        this.type.setValue(Utils.getString(C0997R.string.tip_name_name, ProtocolTransformer.getType(netDevice.deviceType), netDevice.deviceCode));
        this.timeZone.setValue(netDevice.devTimeZone);
    }
}
