package kotlinx.coroutines;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final /* synthetic */ class CommonPool$$ExternalSyntheticLambda2 implements ThreadFactory {
    public final /* synthetic */ AtomicInteger f$0;

    public /* synthetic */ CommonPool$$ExternalSyntheticLambda2(AtomicInteger atomicInteger) {
        this.f$0 = atomicInteger;
    }

    public final Thread newThread(Runnable runnable) {
        return CommonPool.m2414createPlainPool$lambda12(this.f$0, runnable);
    }
}
