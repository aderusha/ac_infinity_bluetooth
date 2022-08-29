package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

@Metadata(mo27511d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u00060\u0003j\u0002`\u0004B\u0005¢\u0006\u0002\u0010\u0005J\r\u0010\u0018\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0019J\r\u0010\u001a\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000e2\u0006\u0010\u001c\u001a\u00020\bH$¢\u0006\u0002\u0010\u001dJ\u001d\u0010\u001e\u001a\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001f0!H\bJ\u0015\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010$R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R:\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u000e2\u0010\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u000e@BX\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0010\u0010\u0005\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, mo27512d2 = {"Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "S", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "()V", "_subscriptionCount", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "<set-?>", "nCollectors", "getNCollectors", "()I", "nextIndex", "", "slots", "getSlots$annotations", "getSlots", "()[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "getSubscriptionCount", "()Lkotlinx/coroutines/flow/StateFlow;", "allocateSlot", "()Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "createSlot", "createSlotArray", "size", "(I)[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "forEachSlotLocked", "", "block", "Lkotlin/Function1;", "freeSlot", "slot", "(Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;)V", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: AbstractSharedFlow.kt */
public abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {
    private MutableStateFlow<Integer> _subscriptionCount;
    /* access modifiers changed from: private */
    public int nCollectors;
    private int nextIndex;
    /* access modifiers changed from: private */
    public S[] slots;

    protected static /* synthetic */ void getSlots$annotations() {
    }

    /* access modifiers changed from: protected */
    public abstract S createSlot();

    /* access modifiers changed from: protected */
    public abstract S[] createSlotArray(int i);

    /* access modifiers changed from: protected */
    public final S[] getSlots() {
        return this.slots;
    }

    /* access modifiers changed from: protected */
    public final int getNCollectors() {
        return this.nCollectors;
    }

    /* access modifiers changed from: protected */
    public final void forEachSlotLocked(Function1<? super S, Unit> function1) {
        AbstractSharedFlowSlot[] access$getSlots;
        if (this.nCollectors != 0 && (access$getSlots = this.slots) != null) {
            for (AbstractSharedFlowSlot abstractSharedFlowSlot : access$getSlots) {
                if (abstractSharedFlowSlot != null) {
                    function1.invoke(abstractSharedFlowSlot);
                }
            }
        }
    }

    public final StateFlow<Integer> getSubscriptionCount() {
        MutableStateFlow<Integer> mutableStateFlow;
        synchronized (this) {
            mutableStateFlow = this._subscriptionCount;
            if (mutableStateFlow == null) {
                mutableStateFlow = StateFlowKt.MutableStateFlow(Integer.valueOf(getNCollectors()));
                this._subscriptionCount = mutableStateFlow;
            }
        }
        return mutableStateFlow;
    }

    /* access modifiers changed from: protected */
    public final S allocateSlot() {
        S s;
        MutableStateFlow<Integer> mutableStateFlow;
        synchronized (this) {
            S[] slots2 = getSlots();
            if (slots2 == null) {
                slots2 = createSlotArray(2);
                this.slots = slots2;
            } else if (getNCollectors() >= slots2.length) {
                S[] copyOf = Arrays.copyOf(slots2, slots2.length * 2);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                this.slots = (AbstractSharedFlowSlot[]) copyOf;
                slots2 = (AbstractSharedFlowSlot[]) copyOf;
            }
            int i = this.nextIndex;
            do {
                s = slots2[i];
                if (s == null) {
                    s = createSlot();
                    slots2[i] = s;
                }
                i++;
                if (i >= slots2.length) {
                    i = 0;
                }
            } while (!s.allocateLocked(this));
            this.nextIndex = i;
            this.nCollectors = getNCollectors() + 1;
            mutableStateFlow = this._subscriptionCount;
        }
        if (mutableStateFlow != null) {
            StateFlowKt.increment(mutableStateFlow, 1);
        }
        return s;
    }

    /* access modifiers changed from: protected */
    public final void freeSlot(S s) {
        MutableStateFlow<Integer> mutableStateFlow;
        int i;
        Continuation[] freeLocked;
        synchronized (this) {
            this.nCollectors = getNCollectors() - 1;
            mutableStateFlow = this._subscriptionCount;
            i = 0;
            if (getNCollectors() == 0) {
                this.nextIndex = 0;
            }
            freeLocked = s.freeLocked(this);
        }
        int length = freeLocked.length;
        while (i < length) {
            Continuation continuation = freeLocked[i];
            i++;
            if (continuation != null) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m1023constructorimpl(unit));
            }
        }
        if (mutableStateFlow != null) {
            StateFlowKt.increment(mutableStateFlow, -1);
        }
    }
}
