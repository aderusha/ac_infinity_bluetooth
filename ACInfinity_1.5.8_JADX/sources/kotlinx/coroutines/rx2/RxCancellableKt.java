package kotlinx.coroutines.rx2;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import p014io.reactivex.plugins.RxJavaPlugins;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, mo27512d2 = {"handleUndeliverableException", "", "cause", "", "context", "Lkotlin/coroutines/CoroutineContext;", "kotlinx-coroutines-rx2"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: RxCancellable.kt */
public final class RxCancellableKt {
    public static final void handleUndeliverableException(Throwable th, CoroutineContext coroutineContext) {
        if (!(th instanceof CancellationException)) {
            try {
                RxJavaPlugins.onError(th);
            } catch (Throwable unused) {
                CoroutineExceptionHandlerKt.handleCoroutineException(coroutineContext, th);
            }
        }
    }
}
