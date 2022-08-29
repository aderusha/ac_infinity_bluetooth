package kotlinx.coroutines;

import java.util.concurrent.Executor;

public final /* synthetic */ class CommonPool$$ExternalSyntheticLambda1 implements Executor {
    public static final /* synthetic */ CommonPool$$ExternalSyntheticLambda1 INSTANCE = new CommonPool$$ExternalSyntheticLambda1();

    private /* synthetic */ CommonPool$$ExternalSyntheticLambda1() {
    }

    public final void execute(Runnable runnable) {
        CommonPool.m2416shutdown$lambda16(runnable);
    }
}
