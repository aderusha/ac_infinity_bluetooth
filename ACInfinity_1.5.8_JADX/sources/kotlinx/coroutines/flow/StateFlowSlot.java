package kotlinx.coroutines.flow;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

@Metadata(mo27511d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0013B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u001b\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ)\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f0\u000b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\u0002J\r\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0011\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, mo27512d2 = {"Lkotlinx/coroutines/flow/StateFlowSlot;", "<init>", "()V", "Lkotlinx/coroutines/flow/StateFlowImpl;", "flow", "", "allocateLocked", "(Lkotlinx/coroutines/flow/StateFlowImpl;)Z", "", "awaitPending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "Lkotlin/coroutines/Continuation;", "freeLocked", "(Lkotlinx/coroutines/flow/StateFlowImpl;)[Lkotlin/coroutines/Continuation;", "makePending", "takePending", "()Z", "kotlinx-coroutines-core", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: StateFlow.kt */
final class StateFlowSlot extends AbstractSharedFlowSlot<StateFlowImpl<?>> {
    static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowSlot.class, Object.class, "_state");
    volatile /* synthetic */ Object _state = null;

    public boolean allocateLocked(StateFlowImpl<?> stateFlowImpl) {
        if (this._state != null) {
            return false;
        }
        this._state = StateFlowKt.NONE;
        return true;
    }

    public Continuation<Unit>[] freeLocked(StateFlowImpl<?> stateFlowImpl) {
        this._state = null;
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }

    public final boolean takePending() {
        Object andSet = _state$FU.getAndSet(this, StateFlowKt.NONE);
        Intrinsics.checkNotNull(andSet);
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(andSet instanceof CancellableContinuationImpl))) {
            throw new AssertionError();
        } else if (andSet == StateFlowKt.PENDING) {
            return true;
        } else {
            return false;
        }
    }

    public final void makePending() {
        while (true) {
            Object obj = this._state;
            if (obj != null && obj != StateFlowKt.PENDING) {
                if (obj == StateFlowKt.NONE) {
                    if (_state$FU.compareAndSet(this, obj, StateFlowKt.PENDING)) {
                        return;
                    }
                } else if (_state$FU.compareAndSet(this, obj, StateFlowKt.NONE)) {
                    Unit unit = Unit.INSTANCE;
                    Result.Companion companion = Result.Companion;
                    ((CancellableContinuationImpl) obj).resumeWith(Result.m1023constructorimpl(unit));
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final Object awaitPending(Continuation<? super Unit> continuation) {
        boolean z = true;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(this._state instanceof CancellableContinuationImpl))) {
            if (!_state$FU.compareAndSet(this, StateFlowKt.NONE, cancellableContinuation)) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (this._state != StateFlowKt.PENDING) {
                        z = false;
                    }
                    if (!z) {
                        throw new AssertionError();
                    }
                }
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m1023constructorimpl(unit));
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        }
        throw new AssertionError();
    }
}
