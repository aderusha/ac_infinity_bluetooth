package p014io.reactivex.disposables;

import java.util.ArrayList;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.internal.disposables.DisposableContainer;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.util.ExceptionHelper;
import p014io.reactivex.internal.util.OpenHashSet;

/* renamed from: io.reactivex.disposables.CompositeDisposable */
public final class CompositeDisposable implements Disposable, DisposableContainer {
    volatile boolean disposed;
    OpenHashSet<Disposable> resources;

    public CompositeDisposable() {
    }

    public CompositeDisposable(Disposable... disposableArr) {
        ObjectHelper.requireNonNull(disposableArr, "disposables is null");
        this.resources = new OpenHashSet<>(disposableArr.length + 1);
        for (Disposable disposable : disposableArr) {
            ObjectHelper.requireNonNull(disposable, "A Disposable in the disposables array is null");
            this.resources.add(disposable);
        }
    }

    public CompositeDisposable(Iterable<? extends Disposable> iterable) {
        ObjectHelper.requireNonNull(iterable, "disposables is null");
        this.resources = new OpenHashSet<>();
        for (Disposable disposable : iterable) {
            ObjectHelper.requireNonNull(disposable, "A Disposable item in the disposables sequence is null");
            this.resources.add(disposable);
        }
    }

    public void dispose() {
        if (!this.disposed) {
            synchronized (this) {
                if (!this.disposed) {
                    this.disposed = true;
                    OpenHashSet<Disposable> openHashSet = this.resources;
                    this.resources = null;
                    dispose(openHashSet);
                }
            }
        }
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public boolean add(Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "disposable is null");
        if (!this.disposed) {
            synchronized (this) {
                if (!this.disposed) {
                    OpenHashSet<Disposable> openHashSet = this.resources;
                    if (openHashSet == null) {
                        openHashSet = new OpenHashSet<>();
                        this.resources = openHashSet;
                    }
                    openHashSet.add(disposable);
                    return true;
                }
            }
        }
        disposable.dispose();
        return false;
    }

    public boolean addAll(Disposable... disposableArr) {
        ObjectHelper.requireNonNull(disposableArr, "disposables is null");
        if (!this.disposed) {
            synchronized (this) {
                if (!this.disposed) {
                    OpenHashSet<Disposable> openHashSet = this.resources;
                    if (openHashSet == null) {
                        openHashSet = new OpenHashSet<>(disposableArr.length + 1);
                        this.resources = openHashSet;
                    }
                    for (Disposable disposable : disposableArr) {
                        ObjectHelper.requireNonNull(disposable, "A Disposable in the disposables array is null");
                        openHashSet.add(disposable);
                    }
                    return true;
                }
            }
        }
        for (Disposable dispose : disposableArr) {
            dispose.dispose();
        }
        return false;
    }

    public boolean remove(Disposable disposable) {
        if (!delete(disposable)) {
            return false;
        }
        disposable.dispose();
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0021, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean delete(p014io.reactivex.disposables.Disposable r3) {
        /*
            r2 = this;
            java.lang.String r0 = "disposables is null"
            p014io.reactivex.internal.functions.ObjectHelper.requireNonNull(r3, (java.lang.String) r0)
            boolean r0 = r2.disposed
            r1 = 0
            if (r0 == 0) goto L_0x000b
            return r1
        L_0x000b:
            monitor-enter(r2)
            boolean r0 = r2.disposed     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0012
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            return r1
        L_0x0012:
            io.reactivex.internal.util.OpenHashSet<io.reactivex.disposables.Disposable> r0 = r2.resources     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0020
            boolean r3 = r0.remove(r3)     // Catch:{ all -> 0x0022 }
            if (r3 != 0) goto L_0x001d
            goto L_0x0020
        L_0x001d:
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            r3 = 1
            return r3
        L_0x0020:
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            return r1
        L_0x0022:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p014io.reactivex.disposables.CompositeDisposable.delete(io.reactivex.disposables.Disposable):boolean");
    }

    public void clear() {
        if (!this.disposed) {
            synchronized (this) {
                if (!this.disposed) {
                    OpenHashSet<Disposable> openHashSet = this.resources;
                    this.resources = null;
                    dispose(openHashSet);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0016, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int size() {
        /*
            r2 = this;
            boolean r0 = r2.disposed
            r1 = 0
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            monitor-enter(r2)
            boolean r0 = r2.disposed     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x000d
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            return r1
        L_0x000d:
            io.reactivex.internal.util.OpenHashSet<io.reactivex.disposables.Disposable> r0 = r2.resources     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0015
            int r1 = r0.size()     // Catch:{ all -> 0x0017 }
        L_0x0015:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            return r1
        L_0x0017:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p014io.reactivex.disposables.CompositeDisposable.size():int");
    }

    /* access modifiers changed from: package-private */
    public void dispose(OpenHashSet<Disposable> openHashSet) {
        if (openHashSet != null) {
            ArrayList arrayList = null;
            for (Object obj : openHashSet.keys()) {
                if (obj instanceof Disposable) {
                    try {
                        ((Disposable) obj).dispose();
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(th);
                    }
                }
            }
            if (arrayList == null) {
                return;
            }
            if (arrayList.size() == 1) {
                throw ExceptionHelper.wrapOrThrow((Throwable) arrayList.get(0));
            }
            throw new CompositeException((Iterable<? extends Throwable>) arrayList);
        }
    }
}
