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
import p014io.reactivex.Observable;
import p014io.reactivex.exceptions.Exceptions;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000F\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\u001a[\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2/\b\u0001\u0010\n\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b¢\u0006\u0002\b\u000fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a_\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2-\u0010\n\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b¢\u0006\u0002\b\u000fH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0017H\u0000\u001a_\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u00020\u00132\b\b\u0002\u0010\b\u001a\u00020\t2/\b\u0001\u0010\n\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b¢\u0006\u0002\b\u000fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0014\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, mo27512d2 = {"CLOSED", "", "OPEN", "SIGNALLED", "rxObservable", "Lio/reactivex/Observable;", "T", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lio/reactivex/Observable;", "rxObservableInternal", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lio/reactivex/Observable;", "isFatal", "", "", "kotlinx-coroutines-rx2"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: RxObservable.kt */
public final class RxObservableKt {
    private static final int CLOSED = -1;
    private static final int OPEN = 0;
    private static final int SIGNALLED = -2;

    public static /* synthetic */ Observable rxObservable$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return rxObservable(coroutineContext, function2);
    }

    public static final <T> Observable<T> rxObservable(CoroutineContext coroutineContext, Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (coroutineContext.get(Job.Key) == null) {
            return rxObservableInternal(GlobalScope.INSTANCE, coroutineContext, function2);
        }
        throw new IllegalArgumentException(("Observable context cannot contain job in it." + "Its lifecycle should be managed via Disposable handle. Had " + coroutineContext).toString());
    }

    public static /* synthetic */ Observable rxObservable$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return rxObservable(coroutineScope, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "CoroutineScope.rxObservable is deprecated in favour of top-level rxObservable", replaceWith = @ReplaceWith(expression = "rxObservable(context, block)", imports = {}))
    public static final <T> Observable<T> rxObservable(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return rxObservableInternal(coroutineScope, coroutineContext, function2);
    }

    private static final <T> Observable<T> rxObservableInternal(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return Observable.create(new RxObservableKt$rxObservableInternal$1(coroutineScope, coroutineContext, function2));
    }

    public static final boolean isFatal(Throwable th) {
        try {
            Exceptions.throwIfFatal(th);
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }
}
