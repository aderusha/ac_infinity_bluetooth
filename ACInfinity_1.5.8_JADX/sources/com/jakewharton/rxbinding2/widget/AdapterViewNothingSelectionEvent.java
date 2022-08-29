package com.jakewharton.rxbinding2.widget;

import android.widget.AdapterView;

public abstract class AdapterViewNothingSelectionEvent extends AdapterViewSelectionEvent {
    public static AdapterViewSelectionEvent create(AdapterView<?> adapterView) {
        return new AutoValue_AdapterViewNothingSelectionEvent(adapterView);
    }

    AdapterViewNothingSelectionEvent() {
    }
}
