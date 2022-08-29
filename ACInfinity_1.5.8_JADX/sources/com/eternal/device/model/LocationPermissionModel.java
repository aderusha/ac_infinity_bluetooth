package com.eternal.device.model;

import android.app.Application;
import com.eternal.device.C1922R;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.component.BaseViewModel;

public class LocationPermissionModel extends BaseViewModel {
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LocationPermissionModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onSetting = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
        }
    });

    public LocationPermissionModel(Application application) {
        super(application);
    }
}
