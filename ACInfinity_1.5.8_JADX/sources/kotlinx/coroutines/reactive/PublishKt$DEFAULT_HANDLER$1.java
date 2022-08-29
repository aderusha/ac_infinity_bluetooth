package kotlinx.coroutines.reactive;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, mo27512d2 = {"<anonymous>", "", "t", "", "ctx", "Lkotlin/coroutines/CoroutineContext;", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: Publish.kt */
final class PublishKt$DEFAULT_HANDLER$1 extends Lambda implements Function2<Throwable, CoroutineContext, Unit> {
    public static final PublishKt$DEFAULT_HANDLER$1 INSTANCE = new PublishKt$DEFAULT_HANDLER$1();

    PublishKt$DEFAULT_HANDLER$1() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Throwable) obj, (CoroutineContext) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th, CoroutineContext coroutineContext) {
        if (!(th instanceof CancellationException)) {
            CoroutineExceptionHandlerKt.handleCoroutineException(coroutineContext, th);
        }
    }
}
