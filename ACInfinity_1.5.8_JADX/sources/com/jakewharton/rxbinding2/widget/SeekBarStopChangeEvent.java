package com.jakewharton.rxbinding2.widget;

import android.widget.SeekBar;

public abstract class SeekBarStopChangeEvent extends SeekBarChangeEvent {
    public static SeekBarStopChangeEvent create(SeekBar seekBar) {
        return new AutoValue_SeekBarStopChangeEvent(seekBar);
    }

    SeekBarStopChangeEvent() {
    }
}
