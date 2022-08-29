package p014io.reactivex.exceptions;

import p014io.reactivex.internal.util.ExceptionHelper;

/* renamed from: io.reactivex.exceptions.Exceptions */
public final class Exceptions {
    private Exceptions() {
        throw new IllegalStateException("No instances!");
    }

    public static RuntimeException propagate(Throwable th) {
        throw ExceptionHelper.wrapOrThrow(th);
    }

    public static void throwIfFatal(Throwable th) {
        if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        } else if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        } else if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }
}
