package kotlinx.coroutines.rx2;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.reactive.ReactiveFlowKt;
import p014io.reactivex.Completable;
import p014io.reactivex.Flowable;
import p014io.reactivex.Maybe;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Single;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a1\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0007\u001a1\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0007\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\"\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000eH\u0007\u001a,\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a(\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a*\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a,\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a*\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0016\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0017"}, mo27512d2 = {"_asFlowable", "Lio/reactivex/Flowable;", "T", "", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "from", "_asObservable", "Lio/reactivex/Observable;", "asCompletable", "Lio/reactivex/Completable;", "Lkotlinx/coroutines/Job;", "asFlow", "Lio/reactivex/ObservableSource;", "asFlowable", "asMaybe", "Lio/reactivex/Maybe;", "Lkotlinx/coroutines/Deferred;", "asObservable", "Lkotlinx/coroutines/channels/ReceiveChannel;", "asSingle", "Lio/reactivex/Single;", "kotlinx-coroutines-rx2"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: RxConvert.kt */
public final class RxConvertKt {
    public static final Completable asCompletable(Job job, CoroutineContext coroutineContext) {
        return RxCompletableKt.rxCompletable(coroutineContext, new RxConvertKt$asCompletable$1(job, (Continuation) null));
    }

    public static final <T> Maybe<T> asMaybe(Deferred<? extends T> deferred, CoroutineContext coroutineContext) {
        return RxMaybeKt.rxMaybe(coroutineContext, new RxConvertKt$asMaybe$1(deferred, (Continuation) null));
    }

    public static final <T> Single<T> asSingle(Deferred<? extends T> deferred, CoroutineContext coroutineContext) {
        return RxSingleKt.rxSingle(coroutineContext, new RxConvertKt$asSingle$1(deferred, (Continuation) null));
    }

    public static final <T> Flow<T> asFlow(ObservableSource<T> observableSource) {
        return FlowKt.callbackFlow(new RxConvertKt$asFlow$1(observableSource, (Continuation) null));
    }

    public static final <T> Observable<T> asObservable(Flow<? extends T> flow, CoroutineContext coroutineContext) {
        return Observable.create(new RxConvertKt$asObservable$1(flow, coroutineContext));
    }

    public static /* synthetic */ Observable asObservable$default(Flow flow, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asObservable(flow, coroutineContext);
    }

    public static /* synthetic */ Flowable asFlowable$default(Flow flow, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asFlowable(flow, coroutineContext);
    }

    public static final <T> Flowable<T> asFlowable(Flow<? extends T> flow, CoroutineContext coroutineContext) {
        return Flowable.fromPublisher(ReactiveFlowKt.asPublisher(flow, coroutineContext));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of Flow", replaceWith = @ReplaceWith(expression = "this.consumeAsFlow().asObservable(context)", imports = {"kotlinx.coroutines.flow.consumeAsFlow"}))
    public static final <T> Observable<T> asObservable(ReceiveChannel<? extends T> receiveChannel, CoroutineContext coroutineContext) {
        return RxObservableKt.rxObservable(coroutineContext, new RxConvertKt$asObservable$2(receiveChannel, (Continuation) null));
    }
}
