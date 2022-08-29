package p014io.reactivex.internal.fuseable;

import p014io.reactivex.ObservableSource;

/* renamed from: io.reactivex.internal.fuseable.HasUpstreamObservableSource */
public interface HasUpstreamObservableSource<T> {
    ObservableSource<T> source();
}
