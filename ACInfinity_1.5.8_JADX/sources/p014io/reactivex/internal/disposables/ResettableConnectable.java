package p014io.reactivex.internal.disposables;

import p014io.reactivex.disposables.Disposable;

/* renamed from: io.reactivex.internal.disposables.ResettableConnectable */
public interface ResettableConnectable {
    void resetIf(Disposable disposable);
}
