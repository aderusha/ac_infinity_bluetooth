package com.jakewharton.rxbinding2.widget;

import android.widget.SeekBar;
import java.util.Objects;

final class AutoValue_SeekBarProgressChangeEvent extends SeekBarProgressChangeEvent {
    private final boolean fromUser;
    private final int progress;
    private final SeekBar view;

    AutoValue_SeekBarProgressChangeEvent(SeekBar seekBar, int i, boolean z) {
        Objects.requireNonNull(seekBar, "Null view");
        this.view = seekBar;
        this.progress = i;
        this.fromUser = z;
    }

    public SeekBar view() {
        return this.view;
    }

    public int progress() {
        return this.progress;
    }

    public boolean fromUser() {
        return this.fromUser;
    }

    public String toString() {
        return "SeekBarProgressChangeEvent{view=" + this.view + ", progress=" + this.progress + ", fromUser=" + this.fromUser + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SeekBarProgressChangeEvent)) {
            return false;
        }
        SeekBarProgressChangeEvent seekBarProgressChangeEvent = (SeekBarProgressChangeEvent) obj;
        if (this.view.equals(seekBarProgressChangeEvent.view()) && this.progress == seekBarProgressChangeEvent.progress() && this.fromUser == seekBarProgressChangeEvent.fromUser()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.progress) * 1000003) ^ (this.fromUser ? 1231 : 1237);
    }
}
