package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u0001H\u0002H\u00020\u0005H\n¢\u0006\u0002\b\u0007"}, mo27512d2 = {"<anonymous>", "", "T", "", "subscriber", "Lio/reactivex/ObservableEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: RxObservable.kt */
final class RxObservableKt$rxObservableInternal$1<T> implements ObservableOnSubscribe<T> {
    final /* synthetic */ Function2 $block;
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ CoroutineScope $scope;

    RxObservableKt$rxObservableInternal$1(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function2 function2) {
        this.$scope = coroutineScope;
        this.$context = coroutineContext;
        this.$block = function2;
    }

    public final void subscribe(ObservableEmitter<T> observableEmitter) {
        RxObservableCoroutine rxObservableCoroutine = new RxObservableCoroutine(CoroutineContextKt.newCoroutineContext(this.$scope, this.$context), observableEmitter);
        observableEmitter.setCancellable(new RxCancellable(rxObservableCoroutine));
        rxObservableCoroutine.start(CoroutineStart.DEFAULT, rxObservableCoroutine, this.$block);
    }
}
