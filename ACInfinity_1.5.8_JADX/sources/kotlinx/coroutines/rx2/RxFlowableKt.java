package kotlinx.coroutines.rx2;

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
import kotlinx.coroutines.reactive.PublishKt;
import p014io.reactivex.Flowable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a[\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032/\b\u0001\u0010\n\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\f\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0001¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a_\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\b\b\u0000\u0010\u0007*\u00020\b*\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\u00032/\b\u0001\u0010\n\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\f\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0001¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\" \u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, mo27512d2 = {"RX_HANDLER", "Lkotlin/Function2;", "", "Lkotlin/coroutines/CoroutineContext;", "", "rxFlowable", "Lio/reactivex/Flowable;", "T", "", "context", "block", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lio/reactivex/Flowable;", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lio/reactivex/Flowable;", "kotlinx-coroutines-rx2"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: RxFlowable.kt */
public final class RxFlowableKt {
    private static final Function2<Throwable, CoroutineContext, Unit> RX_HANDLER = RxFlowableKt$RX_HANDLER$1.INSTANCE;

    public static /* synthetic */ Flowable rxFlowable$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return rxFlowable(coroutineContext, function2);
    }

    public static final <T> Flowable<T> rxFlowable(CoroutineContext coroutineContext, Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (coroutineContext.get(Job.Key) == null) {
            return Flowable.fromPublisher(PublishKt.publishInternal(GlobalScope.INSTANCE, coroutineContext, RX_HANDLER, function2));
        }
        throw new IllegalArgumentException(("Flowable context cannot contain job in it." + "Its lifecycle should be managed via Disposable handle. Had " + coroutineContext).toString());
    }

    public static /* synthetic */ Flowable rxFlowable$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return rxFlowable(coroutineScope, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "CoroutineScope.rxFlowable is deprecated in favour of top-level rxFlowable", replaceWith = @ReplaceWith(expression = "rxFlowable(context, block)", imports = {}))
    public static final <T> Flowable<T> rxFlowable(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return Flowable.fromPublisher(PublishKt.publishInternal(coroutineScope, coroutineContext, RX_HANDLER, function2));
    }
}
