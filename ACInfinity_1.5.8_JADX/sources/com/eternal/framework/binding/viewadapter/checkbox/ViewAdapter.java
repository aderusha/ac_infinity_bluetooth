package com.eternal.framework.binding.viewadapter.checkbox;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.eternal.framework.binding.command.BindingCommand;

public class ViewAdapter {
    public static void setCheckedChanged(CheckBox checkBox, final BindingCommand<Boolean> bindingCommand) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BindingCommand.this.execute(Boolean.valueOf(z));
            }
        });
    }
}
