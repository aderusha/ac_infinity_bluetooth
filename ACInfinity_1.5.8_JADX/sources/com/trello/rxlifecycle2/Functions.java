package com.trello.rxlifecycle2;

import java.util.concurrent.CancellationException;
import p014io.reactivex.Completable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Function;
import p014io.reactivex.functions.Predicate;

final class Functions {
    static final Function<Object, Completable> CANCEL_COMPLETABLE = new Function<Object, Completable>() {
        public Completable apply(Object obj) throws Exception {
            return Completable.error((Throwable) new CancellationException());
        }
    };
    static final Function<Throwable, Boolean> RESUME_FUNCTION = new Function<Throwable, Boolean>() {
        public Boolean apply(Throwable th) throws Exception {
            if (th instanceof OutsideLifecycleException) {
                return true;
            }
            Exceptions.propagate(th);
            return false;
        }
    };
    static final Predicate<Boolean> SHOULD_COMPLETE = new Predicate<Boolean>() {
        public boolean test(Boolean bool) throws Exception {
            return bool.booleanValue();
        }
    };

    private Functions() {
        throw new AssertionError("No instances!");
    }
}
