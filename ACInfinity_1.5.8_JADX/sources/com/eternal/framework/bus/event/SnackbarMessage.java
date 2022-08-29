package com.eternal.framework.bus.event;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class SnackbarMessage extends SingleLiveEvent<Integer> {

    public interface SnackbarObserver {
        void onNewMessage(int i);
    }

    public void observe(LifecycleOwner lifecycleOwner, final SnackbarObserver snackbarObserver) {
        super.observe(lifecycleOwner, new Observer<Integer>() {
            public void onChanged(Integer num) {
                if (num != null) {
                    snackbarObserver.onNewMessage(num.intValue());
                }
            }
        });
    }
}
