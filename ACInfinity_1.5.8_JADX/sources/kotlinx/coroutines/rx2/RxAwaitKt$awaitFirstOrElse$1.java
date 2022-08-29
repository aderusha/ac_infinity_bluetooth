package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import p014io.reactivex.ObservableSource;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H@"}, mo27512d2 = {"awaitFirstOrElse", "", "T", "Lio/reactivex/ObservableSource;", "defaultValue", "Lkotlin/Function0;", "continuation", "Lkotlin/coroutines/Continuation;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.rx2.RxAwaitKt", mo28222f = "RxAwait.kt", mo28223i = {0}, mo28224l = {124}, mo28225m = "awaitFirstOrElse", mo28226n = {"defaultValue"}, mo28227s = {"L$0"})
/* compiled from: RxAwait.kt */
final class RxAwaitKt$awaitFirstOrElse$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    RxAwaitKt$awaitFirstOrElse$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RxAwaitKt.awaitFirstOrElse((ObservableSource) null, (Function0) null, this);
    }
}
