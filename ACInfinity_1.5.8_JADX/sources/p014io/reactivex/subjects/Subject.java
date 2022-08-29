package p014io.reactivex.subjects;

import p014io.reactivex.Observable;
import p014io.reactivex.Observer;

/* renamed from: io.reactivex.subjects.Subject */
public abstract class Subject<T> extends Observable<T> implements Observer<T> {
    public abstract Throwable getThrowable();

    public abstract boolean hasComplete();

    public abstract boolean hasObservers();

    public abstract boolean hasThrowable();

    public final Subject<T> toSerialized() {
        if (this instanceof SerializedSubject) {
            return this;
        }
        return new SerializedSubject(this);
    }
}
