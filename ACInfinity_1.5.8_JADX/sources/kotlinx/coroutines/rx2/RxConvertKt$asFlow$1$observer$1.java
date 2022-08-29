package kotlinx.coroutines.rx2;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0015\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, mo27512d2 = {"kotlinx/coroutines/rx2/RxConvertKt$asFlow$1$observer$1", "Lio/reactivex/Observer;", "onComplete", "", "onError", "e", "", "onNext", "t", "(Ljava/lang/Object;)V", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxConvert.kt */
public final class RxConvertKt$asFlow$1$observer$1 implements Observer<T> {
    final /* synthetic */ AtomicReference $disposableRef;
    final /* synthetic */ ProducerScope $this_callbackFlow;

    RxConvertKt$asFlow$1$observer$1(ProducerScope<? super T> producerScope, AtomicReference atomicReference) {
        this.$this_callbackFlow = producerScope;
        this.$disposableRef = atomicReference;
    }

    public void onComplete() {
        SendChannel.DefaultImpls.close$default(this.$this_callbackFlow, (Throwable) null, 1, (Object) null);
    }

    public void onSubscribe(Disposable disposable) {
        if (!this.$disposableRef.compareAndSet((Object) null, disposable)) {
            disposable.dispose();
        }
    }

    public void onNext(T t) {
        try {
            ChannelsKt.sendBlocking(this.$this_callbackFlow, t);
        } catch (Throwable unused) {
        }
    }

    public void onError(Throwable th) {
        this.$this_callbackFlow.close(th);
    }
}
