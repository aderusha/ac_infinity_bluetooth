package p018me.tatarka.bindingcollectionadapter2;

import android.os.Looper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/* renamed from: me.tatarka.bindingcollectionadapter2.Utils */
class Utils {
    private static final String TAG = "BCAdapters";

    Utils() {
    }

    static void throwMissingVariable(ViewDataBinding viewDataBinding, int i, int i2) {
        String resourceName = viewDataBinding.getRoot().getContext().getResources().getResourceName(i2);
        String convertBrIdToString = DataBindingUtil.convertBrIdToString(i);
        throw new IllegalStateException("Could not bind variable '" + convertBrIdToString + "' in layout '" + resourceName + "'");
    }

    static void ensureChangeOnMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("You must only modify the ObservableList on the main thread.");
        }
    }

    static <T, A extends BindingCollectionAdapter<T>> A createClass(Class<? extends BindingCollectionAdapter> cls, ItemBinding<T> itemBinding) {
        try {
            return (BindingCollectionAdapter) cls.getConstructor(new Class[]{ItemBinding.class}).newInstance(new Object[]{itemBinding});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
