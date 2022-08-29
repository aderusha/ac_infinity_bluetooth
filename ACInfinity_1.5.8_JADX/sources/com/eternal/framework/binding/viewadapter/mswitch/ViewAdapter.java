package com.eternal.framework.binding.viewadapter.mswitch;

import android.widget.CompoundButton;
import android.widget.Switch;
import com.eternal.framework.binding.command.BindingCommand;

public class ViewAdapter {
    public static void setSwitchState(Switch switchR, boolean z) {
        switchR.setChecked(z);
    }

    public static void setThumb(Switch switchR, int i) {
        if (i != 0) {
            switchR.setThumbResource(i);
        }
    }

    public static void setTrack(Switch switchR, int i) {
        if (i != 0) {
            switchR.setTrackResource(i);
        }
    }

    public static void onCheckedChangeCommand(Switch switchR, final BindingCommand<Boolean> bindingCommand) {
        if (bindingCommand != null) {
            switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (compoundButton.isPressed()) {
                        BindingCommand.this.execute(Boolean.valueOf(z));
                    }
                }
            });
        }
    }
}
