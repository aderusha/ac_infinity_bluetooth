package p014io.reactivex.internal.operators.maybe;

import java.util.Arrays;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.disposables.EmptyDisposable;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.operators.maybe.MaybeMap;
import p014io.reactivex.internal.operators.maybe.MaybeZipArray;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeZipIterable */
public final class MaybeZipIterable<T, R> extends Maybe<R> {
    final Iterable<? extends MaybeSource<? extends T>> sources;
    final Function<? super Object[], ? extends R> zipper;

    public MaybeZipIterable(Iterable<? extends MaybeSource<? extends T>> iterable, Function<? super Object[], ? extends R> function) {
        this.sources = iterable;
        this.zipper = function;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super R> maybeObserver) {
        MaybeSource[] maybeSourceArr = new MaybeSource[8];
        try {
            int i = 0;
            for (MaybeSource maybeSource : this.sources) {
                if (maybeSource == null) {
                    EmptyDisposable.error((Throwable) new NullPointerException("One of the sources is null"), (MaybeObserver<?>) maybeObserver);
                    return;
                }
                if (i == maybeSourceArr.length) {
                    maybeSourceArr = (MaybeSource[]) Arrays.copyOf(maybeSourceArr, (i >> 2) + i);
                }
                int i2 = i + 1;
                maybeSourceArr[i] = maybeSource;
                i = i2;
            }
            if (i == 0) {
                EmptyDisposable.complete((MaybeObserver<?>) maybeObserver);
            } else if (i == 1) {
                maybeSourceArr[0].subscribe(new MaybeMap.MapMaybeObserver(maybeObserver, new SingletonArrayFunc()));
            } else {
                MaybeZipArray.ZipCoordinator zipCoordinator = new MaybeZipArray.ZipCoordinator(maybeObserver, i, this.zipper);
                maybeObserver.onSubscribe(zipCoordinator);
                for (int i3 = 0; i3 < i && !zipCoordinator.isDisposed(); i3++) {
                    maybeSourceArr[i3].subscribe(zipCoordinator.observers[i3]);
                }
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, (MaybeObserver<?>) maybeObserver);
        }
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeZipIterable$SingletonArrayFunc */
    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        public R apply(T t) throws Exception {
            return ObjectHelper.requireNonNull(MaybeZipIterable.this.zipper.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
