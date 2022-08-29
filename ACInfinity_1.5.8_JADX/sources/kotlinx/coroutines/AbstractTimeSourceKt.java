package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;

@Metadata(mo27511d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\t\u0010\u0006\u001a\u00020\u0007H\b\u001a\t\u0010\b\u001a\u00020\u0007H\b\u001a\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\b\u001a\t\u0010\u000e\u001a\u00020\nH\b\u001a\t\u0010\u000f\u001a\u00020\nH\b\u001a\t\u0010\u0010\u001a\u00020\nH\b\u001a\u0011\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\b\u001a\t\u0010\u0014\u001a\u00020\nH\b\u001a\u0019\u0010\u0015\u001a\u00060\u0016j\u0002`\u00172\n\u0010\u0018\u001a\u00060\u0016j\u0002`\u0017H\b\"\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u0019"}, mo27512d2 = {"timeSource", "Lkotlinx/coroutines/AbstractTimeSource;", "getTimeSource", "()Lkotlinx/coroutines/AbstractTimeSource;", "setTimeSource", "(Lkotlinx/coroutines/AbstractTimeSource;)V", "currentTimeMillis", "", "nanoTime", "parkNanos", "", "blocker", "", "nanos", "registerTimeLoopThread", "trackTask", "unTrackTask", "unpark", "thread", "Ljava/lang/Thread;", "unregisterTimeLoopThread", "wrapTask", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "kotlinx-coroutines-core"}, mo27513k = 2, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: AbstractTimeSource.kt */
public final class AbstractTimeSourceKt {
    private static AbstractTimeSource timeSource;

    public static final AbstractTimeSource getTimeSource() {
        return timeSource;
    }

    public static final void setTimeSource(AbstractTimeSource abstractTimeSource) {
        timeSource = abstractTimeSource;
    }

    private static final long currentTimeMillis() {
        AbstractTimeSource timeSource2 = getTimeSource();
        return timeSource2 == null ? System.currentTimeMillis() : timeSource2.currentTimeMillis();
    }

    private static final long nanoTime() {
        AbstractTimeSource timeSource2 = getTimeSource();
        return timeSource2 == null ? System.nanoTime() : timeSource2.nanoTime();
    }

    private static final Runnable wrapTask(Runnable runnable) {
        AbstractTimeSource timeSource2 = getTimeSource();
        return timeSource2 == null ? runnable : timeSource2.wrapTask(runnable);
    }

    private static final void trackTask() {
        AbstractTimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.trackTask();
        }
    }

    private static final void unTrackTask() {
        AbstractTimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.unTrackTask();
        }
    }

    private static final void registerTimeLoopThread() {
        AbstractTimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.registerTimeLoopThread();
        }
    }

    private static final void unregisterTimeLoopThread() {
        AbstractTimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.unregisterTimeLoopThread();
        }
    }

    private static final void parkNanos(Object obj, long j) {
        AbstractTimeSource timeSource2 = getTimeSource();
        if (timeSource2 == null) {
            LockSupport.parkNanos(obj, j);
        } else {
            timeSource2.parkNanos(obj, j);
        }
    }

    private static final void unpark(Thread thread) {
        AbstractTimeSource timeSource2 = getTimeSource();
        if (timeSource2 == null) {
            LockSupport.unpark(thread);
        } else {
            timeSource2.unpark(thread);
        }
    }
}
