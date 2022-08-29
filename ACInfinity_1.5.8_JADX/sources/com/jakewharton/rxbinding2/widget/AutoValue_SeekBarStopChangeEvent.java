package com.jakewharton.rxbinding2.widget;

import android.widget.SeekBar;
import java.util.Objects;

final class AutoValue_SeekBarStopChangeEvent extends SeekBarStopChangeEvent {
    private final SeekBar view;

    AutoValue_SeekBarStopChangeEvent(SeekBar seekBar) {
        Objects.requireNonNull(seekBar, "Null view");
        this.view = seekBar;
    }

    public SeekBar view() {
        return this.view;
    }

    public String toString() {
        return "SeekBarStopChangeEvent{view=" + this.view + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SeekBarStopChangeEvent) {
            return this.view.equals(((SeekBarStopChangeEvent) obj).view());
        }
        return false;
    }

    public int hashCode() {
        return this.view.hashCode() ^ 1000003;
    }
}
