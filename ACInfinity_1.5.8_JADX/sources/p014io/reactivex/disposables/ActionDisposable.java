package p014io.reactivex.disposables;

import p014io.reactivex.functions.Action;
import p014io.reactivex.internal.util.ExceptionHelper;

/* renamed from: io.reactivex.disposables.ActionDisposable */
final class ActionDisposable extends ReferenceDisposable<Action> {
    private static final long serialVersionUID = -8219729196779211169L;

    ActionDisposable(Action action) {
        super(action);
    }

    /* access modifiers changed from: protected */
    public void onDisposed(Action action) {
        try {
            action.run();
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }
}
