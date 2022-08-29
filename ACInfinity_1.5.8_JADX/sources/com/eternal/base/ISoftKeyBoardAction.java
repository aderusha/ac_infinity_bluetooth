package com.eternal.base;

public interface ISoftKeyBoardAction {
    void closeKeyboard();

    void onSoftKeyboardClosed();

    void onSoftKeyboardOpened(int i);
}
