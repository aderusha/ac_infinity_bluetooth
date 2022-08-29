package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import p014io.reactivex.ObservableSource;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bHH"}, mo27512d2 = {"consumeEach", "", "T", "Lio/reactivex/ObservableSource;", "action", "Lkotlin/Function1;", "", "continuation", "Lkotlin/coroutines/Continuation;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.rx2.RxChannelKt", mo28222f = "RxChannel.kt", mo28223i = {0, 0, 0}, mo28224l = {118}, mo28225m = "consumeEach", mo28226n = {"action", "$this$consume$iv$iv", "cause$iv$iv"}, mo28227s = {"L$0", "L$1", "L$2"})
/* compiled from: RxChannel.kt */
public final class RxChannelKt$consumeEach$2 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    public RxChannelKt$consumeEach$2(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RxChannelKt.consumeEach((ObservableSource) null, (Function1) null, (Continuation<? super Unit>) this);
    }
}
