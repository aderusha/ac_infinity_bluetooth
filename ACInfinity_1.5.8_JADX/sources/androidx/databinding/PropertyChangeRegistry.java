package androidx.databinding;

import androidx.databinding.CallbackRegistry;
import androidx.databinding.Observable;

public class PropertyChangeRegistry extends CallbackRegistry<Observable.OnPropertyChangedCallback, Observable, Void> {
    private static final CallbackRegistry.NotifierCallback<Observable.OnPropertyChangedCallback, Observable, Void> NOTIFIER_CALLBACK = new CallbackRegistry.NotifierCallback<Observable.OnPropertyChangedCallback, Observable, Void>() {
        public void onNotifyCallback(Observable.OnPropertyChangedCallback onPropertyChangedCallback, Observable observable, int i, Void voidR) {
            onPropertyChangedCallback.onPropertyChanged(observable, i);
        }
    };

    public PropertyChangeRegistry() {
        super(NOTIFIER_CALLBACK);
    }

    public void notifyChange(Observable observable, int i) {
        notifyCallbacks(observable, i, null);
    }
}
