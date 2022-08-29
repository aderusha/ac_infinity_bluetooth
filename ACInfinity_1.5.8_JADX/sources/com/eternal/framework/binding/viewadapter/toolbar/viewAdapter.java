package com.eternal.framework.binding.viewadapter.toolbar;

import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.eternal.framework.binding.command.BindingCommand;

public class viewAdapter {
    public static void onNavigationClick(Toolbar toolbar, final BindingCommand<Void> bindingCommand) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BindingCommand.this.execute();
            }
        });
    }
}
