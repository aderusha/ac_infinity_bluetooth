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
import p014io.reactivex.Completable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aA\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aI\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001aG\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, mo27512d2 = {"rxCompletable", "Lio/reactivex/Completable;", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lio/reactivex/Completable;", "rxCompletableInternal", "scope", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lio/reactivex/Completable;", "kotlinx-coroutines-rx2"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: RxCompletable.kt */
public final class RxCompletableKt {
    public static /* synthetic */ Completable rxCompletable$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return rxCompletable(coroutineContext, function2);
    }

    public static final Completable rxCompletable(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (coroutineContext.get(Job.Key) == null) {
            return rxCompletableInternal(GlobalScope.INSTANCE, coroutineContext, function2);
        }
        throw new IllegalArgumentException(("Completable context cannot contain job in it." + "Its lifecycle should be managed via Disposable handle. Had " + coroutineContext).toString());
    }

    public static /* synthetic */ Completable rxCompletable$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return rxCompletable(coroutineScope, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "CoroutineScope.rxCompletable is deprecated in favour of top-level rxCompletable", replaceWith = @ReplaceWith(expression = "rxCompletable(context, block)", imports = {}))
    public static final Completable rxCompletable(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return rxCompletableInternal(coroutineScope, coroutineContext, function2);
    }

    private static final Completable rxCompletableInternal(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return Completable.create(new RxCompletableKt$rxCompletableInternal$1(coroutineScope, coroutineContext, function2));
    }
}
