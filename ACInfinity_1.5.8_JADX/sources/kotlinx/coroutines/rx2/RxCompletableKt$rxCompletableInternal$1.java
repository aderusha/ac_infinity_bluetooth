package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo27512d2 = {"<anonymous>", "", "subscriber", "Lio/reactivex/CompletableEmitter;", "subscribe"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: RxCompletable.kt */
final class RxCompletableKt$rxCompletableInternal$1 implements CompletableOnSubscribe {
    final /* synthetic */ Function2 $block;
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ CoroutineScope $scope;

    RxCompletableKt$rxCompletableInternal$1(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2 function2) {
        this.$scope = coroutineScope;
        this.$context = coroutineContext;
        this.$block = function2;
    }

    public final void subscribe(CompletableEmitter completableEmitter) {
        RxCompletableCoroutine rxCompletableCoroutine = new RxCompletableCoroutine(CoroutineContextKt.newCoroutineContext(this.$scope, this.$context), completableEmitter);
        completableEmitter.setCancellable(new RxCancellable(rxCompletableCoroutine));
        rxCompletableCoroutine.start(CoroutineStart.DEFAULT, rxCompletableCoroutine, this.$block);
    }
}
