package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0011\u0010\u000b\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\nH\u0003R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, mo27512d2 = {"Landroidx/lifecycle/EmittedSource;", "Lkotlinx/coroutines/DisposableHandle;", "source", "Landroidx/lifecycle/LiveData;", "mediator", "Landroidx/lifecycle/MediatorLiveData;", "(Landroidx/lifecycle/LiveData;Landroidx/lifecycle/MediatorLiveData;)V", "disposed", "", "dispose", "", "disposeNow", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeSource", "lifecycle-livedata-ktx_release"}, mo27513k = 1, mo27514mv = {1, 1, 15})
/* compiled from: CoroutineLiveData.kt */
public final class EmittedSource implements DisposableHandle {
    private boolean disposed;
    private final MediatorLiveData<?> mediator;
    private final LiveData<?> source;

    public EmittedSource(LiveData<?> liveData, MediatorLiveData<?> mediatorLiveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "source");
        Intrinsics.checkParameterIsNotNull(mediatorLiveData, "mediator");
        this.source = liveData;
        this.mediator = mediatorLiveData;
    }

    public final Object disposeNow(Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new EmittedSource$disposeNow$2(this, (Continuation) null), continuation);
    }

    public void dispose() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().getImmediate()), (CoroutineContext) null, (CoroutineStart) null, new EmittedSource$dispose$1(this, (Continuation) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void removeSource() {
        if (!this.disposed) {
            this.mediator.removeSource(this.source);
            this.disposed = true;
        }
    }
}
