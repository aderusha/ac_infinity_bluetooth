package kotlinx.coroutines.android;

import android.view.Choreographer;
import kotlinx.coroutines.CancellableContinuation;

public final /* synthetic */ class HandlerDispatcherKt$$ExternalSyntheticLambda0 implements Choreographer.FrameCallback {
    public final /* synthetic */ CancellableContinuation f$0;

    public /* synthetic */ HandlerDispatcherKt$$ExternalSyntheticLambda0(CancellableContinuation cancellableContinuation) {
        this.f$0 = cancellableContinuation;
    }

    public final void doFrame(long j) {
        HandlerDispatcherKt.m2424postFrameCallback$lambda6(this.f$0, j);
    }
}
