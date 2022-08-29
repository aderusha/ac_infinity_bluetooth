package com.eternal.framework.binding.viewadapter.swiperefresh;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.eternal.framework.binding.command.BindingCommand;

public class ViewAdapter {
    public static void onRefreshCommand(SwipeRefreshLayout swipeRefreshLayout, final BindingCommand bindingCommand) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                BindingCommand bindingCommand = BindingCommand.this;
                if (bindingCommand != null) {
                    bindingCommand.execute();
                }
            }
        });
    }

    public static void setRefreshing(SwipeRefreshLayout swipeRefreshLayout, boolean z) {
        swipeRefreshLayout.setRefreshing(z);
    }
}
