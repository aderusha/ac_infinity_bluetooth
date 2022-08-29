package com.eternal.device.model;

import android.app.Application;
import com.eternal.device.C1922R;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.component.BaseViewModel;

public class HelpModel extends BaseViewModel {
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            HelpModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });

    public HelpModel(Application application) {
        super(application);
    }
}
