package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u0001H\u0002H\u00020\u0005H\n¢\u0006\u0002\b\u0007"}, mo27512d2 = {"<anonymous>", "", "T", "", "emitter", "Lio/reactivex/ObservableEmitter;", "kotlin.jvm.PlatformType", "subscribe"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: RxConvert.kt */
final class RxConvertKt$asObservable$1<T> implements ObservableOnSubscribe<T> {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Flow $this_asObservable;

    RxConvertKt$asObservable$1(Flow flow, CoroutineContext coroutineContext) {
        this.$this_asObservable = flow;
        this.$context = coroutineContext;
    }

    public final void subscribe(ObservableEmitter<T> observableEmitter) {
        observableEmitter.setCancellable(new RxCancellable(BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(this.$context), CoroutineStart.ATOMIC, new RxConvertKt$asObservable$1$job$1(this, observableEmitter, (Continuation) null))));
    }
}
