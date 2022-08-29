package androidx.core.p003os;

import androidx.core.util.ObjectsCompat;

/* renamed from: androidx.core.os.OperationCanceledException */
public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        this((String) null);
    }

    public OperationCanceledException(String str) {
        super(ObjectsCompat.toString(str, "The operation has been canceled."));
    }
}
