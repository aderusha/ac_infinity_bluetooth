package androidx.concurrent.futures;

import java.util.concurrent.Executor;

enum DirectExecutor implements Executor {
    INSTANCE;

    public String toString() {
        return "DirectExecutor";
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
