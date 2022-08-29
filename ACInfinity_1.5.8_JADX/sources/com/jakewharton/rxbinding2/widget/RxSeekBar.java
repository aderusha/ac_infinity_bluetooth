package com.jakewharton.rxbinding2.widget;

import android.widget.SeekBar;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;

public final class RxSeekBar {
    public static InitialValueObservable<Integer> changes(SeekBar seekBar) {
        Preconditions.checkNotNull(seekBar, "view == null");
        return new SeekBarChangeObservable(seekBar, (Boolean) null);
    }

    public static InitialValueObservable<Integer> userChanges(SeekBar seekBar) {
        Preconditions.checkNotNull(seekBar, "view == null");
        return new SeekBarChangeObservable(seekBar, true);
    }

    public static InitialValueObservable<Integer> systemChanges(SeekBar seekBar) {
        Preconditions.checkNotNull(seekBar, "view == null");
        return new SeekBarChangeObservable(seekBar, false);
    }

    public static InitialValueObservable<SeekBarChangeEvent> changeEvents(SeekBar seekBar) {
        Preconditions.checkNotNull(seekBar, "view == null");
        return new SeekBarChangeEventObservable(seekBar);
    }

    private RxSeekBar() {
        throw new AssertionError("No instances.");
    }
}
