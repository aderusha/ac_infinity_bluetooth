package kotlinx.coroutines.reactive;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProducerScope;
import org.reactivestreams.Publisher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000@\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aW\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052/\b\u0001\u0010\f\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0003¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001au\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\u00052\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00032-\u0010\f\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0003¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a[\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n*\u00020\u00142\b\b\u0002\u0010\u000b\u001a\u00020\u00052/\b\u0001\u0010\f\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0003¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0017\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\" \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, mo27512d2 = {"CLOSED", "", "DEFAULT_HANDLER", "Lkotlin/Function2;", "", "Lkotlin/coroutines/CoroutineContext;", "", "SIGNALLED", "publish", "Lorg/reactivestreams/Publisher;", "T", "context", "block", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lorg/reactivestreams/Publisher;", "publishInternal", "scope", "Lkotlinx/coroutines/CoroutineScope;", "exceptionOnCancelHandler", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)Lorg/reactivestreams/Publisher;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lorg/reactivestreams/Publisher;", "kotlinx-coroutines-reactive"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: Publish.kt */
public final class PublishKt {
    private static final long CLOSED = -1;
    private static final Function2<Throwable, CoroutineContext, Unit> DEFAULT_HANDLER = PublishKt$DEFAULT_HANDLER$1.INSTANCE;
    private static final long SIGNALLED = -2;

    public static /* synthetic */ Publisher publish$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return publish(coroutineContext, function2);
    }

    public static final <T> Publisher<T> publish(CoroutineContext coroutineContext, Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (coroutineContext.get(Job.Key) == null) {
            return publishInternal(GlobalScope.INSTANCE, coroutineContext, DEFAULT_HANDLER, function2);
        }
        throw new IllegalArgumentException(("Publisher context cannot contain job in it." + "Its lifecycle should be managed via subscription. Had " + coroutineContext).toString());
    }

    public static /* synthetic */ Publisher publish$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return publish(coroutineScope, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "CoroutineScope.publish is deprecated in favour of top-level publish", replaceWith = @ReplaceWith(expression = "publish(context, block)", imports = {}))
    public static final <T> Publisher<T> publish(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return publishInternal(coroutineScope, coroutineContext, DEFAULT_HANDLER, function2);
    }

    public static final <T> Publisher<T> publishInternal(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2<? super Throwable, ? super CoroutineContext, Unit> function2, Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function22) {
        return new PublishKt$publishInternal$1<>(coroutineScope, coroutineContext, function2, function22);
    }
}
