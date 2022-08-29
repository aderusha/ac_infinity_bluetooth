package com.eternal.framework.component;

import com.trello.rxlifecycle2.components.support.RxFragment;

public class BaseRxFragment extends RxFragment {
    public boolean isBackPressed() {
        return false;
    }
}
