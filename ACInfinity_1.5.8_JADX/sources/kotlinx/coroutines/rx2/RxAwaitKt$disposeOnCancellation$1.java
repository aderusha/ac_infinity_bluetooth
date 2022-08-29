package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import p014io.reactivex.disposables.Disposable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo27512d2 = {"<anonymous>", "", "it", "", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: RxAwait.kt */
final class RxAwaitKt$disposeOnCancellation$1 extends Lambda implements Function1<Throwable, Unit> {

    /* renamed from: $d */
    final /* synthetic */ Disposable f1213$d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RxAwaitKt$disposeOnCancellation$1(Disposable disposable) {
        super(1);
        this.f1213$d = disposable;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.f1213$d.dispose();
    }
}
