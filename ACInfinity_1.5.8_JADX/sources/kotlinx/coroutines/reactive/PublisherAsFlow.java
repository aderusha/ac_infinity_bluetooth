package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import kotlinx.coroutines.flow.internal.SendingCollector;
import org.reactivestreams.Publisher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B1\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001f\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J'\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u001f\u0010\u001b\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u001f\u0010\u001c\u001a\u00020\u00142\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ&\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000e8BX\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, mo27512d2 = {"Lkotlinx/coroutines/reactive/PublisherAsFlow;", "T", "", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "publisher", "Lorg/reactivestreams/Publisher;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lorg/reactivestreams/Publisher;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "requestSize", "", "getRequestSize$annotations", "()V", "getRequestSize", "()J", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectImpl", "injectContext", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectSlowPath", "collectTo", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "kotlinx-coroutines-reactive"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ReactiveFlow.kt */
final class PublisherAsFlow<T> extends ChannelFlow<T> {
    private final Publisher<T> publisher;

    private static /* synthetic */ void getRequestSize$annotations() {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PublisherAsFlow(Publisher publisher2, CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(publisher2, (i2 & 2) != 0 ? EmptyCoroutineContext.INSTANCE : coroutineContext, (i2 & 4) != 0 ? -2 : i, (i2 & 8) != 0 ? BufferOverflow.SUSPEND : bufferOverflow);
    }

    public PublisherAsFlow(Publisher<T> publisher2, CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        super(coroutineContext, i, bufferOverflow);
        this.publisher = publisher2;
    }

    /* access modifiers changed from: protected */
    public ChannelFlow<T> create(CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        return new PublisherAsFlow<>(this.publisher, coroutineContext, i, bufferOverflow);
    }

    private final long getRequestSize() {
        if (this.onBufferOverflow == BufferOverflow.SUSPEND) {
            int i = this.capacity;
            if (i == -2) {
                return (long) Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core();
            }
            if (i == 0) {
                return 1;
            }
            if (i != Integer.MAX_VALUE) {
                long j = (long) this.capacity;
                if (j >= 1) {
                    return j;
                }
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        return Long.MAX_VALUE;
    }

    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        CoroutineContext context = continuation.getContext();
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) this.context.get(ContinuationInterceptor.Key);
        if (continuationInterceptor == null || Intrinsics.areEqual((Object) continuationInterceptor, (Object) (ContinuationInterceptor) context.get(ContinuationInterceptor.Key))) {
            Object collectImpl = collectImpl(context.plus(this.context), flowCollector, continuation);
            return collectImpl == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collectImpl : Unit.INSTANCE;
        }
        Object collectSlowPath = collectSlowPath(flowCollector, continuation);
        if (collectSlowPath == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return collectSlowPath;
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object collectSlowPath(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new PublisherAsFlow$collectSlowPath$2(this, flowCollector, (Continuation) null), continuation);
        if (coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return coroutineScope;
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0093 A[Catch:{ all -> 0x005d }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0094 A[Catch:{ all -> 0x005d }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009b A[Catch:{ all -> 0x005d }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00be A[Catch:{ all -> 0x005d }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object collectImpl(kotlin.coroutines.CoroutineContext r18, kotlinx.coroutines.flow.FlowCollector<? super T> r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r17 = this;
            r1 = r17
            r0 = r20
            boolean r2 = r0 instanceof kotlinx.coroutines.reactive.PublisherAsFlow$collectImpl$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            kotlinx.coroutines.reactive.PublisherAsFlow$collectImpl$1 r2 = (kotlinx.coroutines.reactive.PublisherAsFlow$collectImpl$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            kotlinx.coroutines.reactive.PublisherAsFlow$collectImpl$1 r2 = new kotlinx.coroutines.reactive.PublisherAsFlow$collectImpl$1
            r2.<init>(r1, r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 0
            r7 = 2
            r8 = 1
            if (r4 == 0) goto L_0x0060
            if (r4 == r8) goto L_0x004b
            if (r4 != r7) goto L_0x0043
            long r9 = r2.J$0
            java.lang.Object r4 = r2.L$2
            kotlinx.coroutines.reactive.ReactiveSubscriber r4 = (kotlinx.coroutines.reactive.ReactiveSubscriber) r4
            java.lang.Object r11 = r2.L$1
            kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
            java.lang.Object r12 = r2.L$0
            kotlinx.coroutines.reactive.PublisherAsFlow r12 = (kotlinx.coroutines.reactive.PublisherAsFlow) r12
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x005d }
        L_0x0040:
            r0 = r11
            goto L_0x00b3
        L_0x0043:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x004b:
            long r9 = r2.J$0
            java.lang.Object r4 = r2.L$2
            kotlinx.coroutines.reactive.ReactiveSubscriber r4 = (kotlinx.coroutines.reactive.ReactiveSubscriber) r4
            java.lang.Object r11 = r2.L$1
            kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
            java.lang.Object r12 = r2.L$0
            kotlinx.coroutines.reactive.PublisherAsFlow r12 = (kotlinx.coroutines.reactive.PublisherAsFlow) r12
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x005d }
            goto L_0x0099
        L_0x005d:
            r0 = move-exception
            goto L_0x00cc
        L_0x0060:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.reactive.ReactiveSubscriber r0 = new kotlinx.coroutines.reactive.ReactiveSubscriber
            int r4 = r1.capacity
            kotlinx.coroutines.channels.BufferOverflow r9 = r1.onBufferOverflow
            long r10 = r17.getRequestSize()
            r0.<init>(r4, r9, r10)
            org.reactivestreams.Publisher<T> r4 = r1.publisher
            r9 = r18
            org.reactivestreams.Publisher r4 = kotlinx.coroutines.reactive.ReactiveFlowKt.injectCoroutineContext(r4, r9)
            r9 = r0
            org.reactivestreams.Subscriber r9 = (org.reactivestreams.Subscriber) r9
            r4.subscribe(r9)
            r4 = r0
            r9 = r1
            r10 = r5
            r0 = r19
        L_0x0083:
            r2.L$0 = r9     // Catch:{ all -> 0x005d }
            r2.L$1 = r0     // Catch:{ all -> 0x005d }
            r2.L$2 = r4     // Catch:{ all -> 0x005d }
            r2.J$0 = r10     // Catch:{ all -> 0x005d }
            r2.label = r8     // Catch:{ all -> 0x005d }
            java.lang.Object r12 = r4.takeNextOrNull(r2)     // Catch:{ all -> 0x005d }
            if (r12 != r3) goto L_0x0094
            return r3
        L_0x0094:
            r15 = r10
            r11 = r0
            r0 = r12
            r12 = r9
            r9 = r15
        L_0x0099:
            if (r0 == 0) goto L_0x00c6
            kotlin.coroutines.CoroutineContext r13 = r2.getContext()     // Catch:{ all -> 0x005d }
            kotlinx.coroutines.JobKt.ensureActive((kotlin.coroutines.CoroutineContext) r13)     // Catch:{ all -> 0x005d }
            r2.L$0 = r12     // Catch:{ all -> 0x005d }
            r2.L$1 = r11     // Catch:{ all -> 0x005d }
            r2.L$2 = r4     // Catch:{ all -> 0x005d }
            r2.J$0 = r9     // Catch:{ all -> 0x005d }
            r2.label = r7     // Catch:{ all -> 0x005d }
            java.lang.Object r0 = r11.emit(r0, r2)     // Catch:{ all -> 0x005d }
            if (r0 != r3) goto L_0x0040
            return r3
        L_0x00b3:
            r13 = 1
            long r9 = r9 + r13
            long r13 = r12.getRequestSize()     // Catch:{ all -> 0x005d }
            int r11 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r11 != 0) goto L_0x00c3
            r4.makeRequest()     // Catch:{ all -> 0x005d }
            r10 = r5
            goto L_0x00c4
        L_0x00c3:
            r10 = r9
        L_0x00c4:
            r9 = r12
            goto L_0x0083
        L_0x00c6:
            r4.cancel()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00cc:
            r4.cancel()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.PublisherAsFlow.collectImpl(kotlin.coroutines.CoroutineContext, kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Object collectTo(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation) {
        Object collectImpl = collectImpl(producerScope.getCoroutineContext(), new SendingCollector(producerScope.getChannel()), continuation);
        return collectImpl == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collectImpl : Unit.INSTANCE;
    }
}
