package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlinx.coroutines.DisposableHandle;
import p014io.reactivex.disposables.Disposable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/JobKt__JobKt$DisposableHandle$1", "Lkotlinx/coroutines/DisposableHandle;", "dispose", "", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: kotlinx.coroutines.rx2.SchedulerCoroutineDispatcher$invokeOnTimeout$$inlined$DisposableHandle$1 */
/* compiled from: Job.kt */
public final class C3920x54f20b78 implements DisposableHandle {
    final /* synthetic */ Disposable $disposable$inlined;

    public C3920x54f20b78(Disposable disposable) {
        this.$disposable$inlined = disposable;
    }

    public void dispose() {
        this.$disposable$inlined.dispose();
    }
}
