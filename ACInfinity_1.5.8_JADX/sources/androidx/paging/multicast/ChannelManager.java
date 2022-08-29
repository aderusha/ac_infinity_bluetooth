package androidx.paging.multicast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0003\u001c\u001d\u001eB^\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\"\u0010\t\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\b\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fø\u0001\u0000¢\u0006\u0002\u0010\u0010J%\u0010\u0014\u001a\u00020\f2\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00170\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ%\u0010\u001b\u001a\u00020\f2\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00170\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018R\u0018\u0010\u0011\u001a\f0\u0012R\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R/\u0010\t\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "bufferSize", "", "piggybackingDownstream", "", "onEach", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "keepUpstreamAlive", "upstream", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/CoroutineScope;IZLkotlin/jvm/functions/Function2;ZLkotlinx/coroutines/flow/Flow;)V", "actor", "Landroidx/paging/multicast/ChannelManager$Actor;", "Lkotlin/jvm/functions/Function2;", "addDownstream", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "(Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeDownstream", "Actor", "ChannelEntry", "Message", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ChannelManager.kt */
public final class ChannelManager<T> {
    private final ChannelManager<T>.Actor actor;
    /* access modifiers changed from: private */
    public final int bufferSize;
    /* access modifiers changed from: private */
    public final boolean keepUpstreamAlive;
    /* access modifiers changed from: private */
    public final Function2<T, Continuation<? super Unit>, Object> onEach;
    /* access modifiers changed from: private */
    public final boolean piggybackingDownstream;
    /* access modifiers changed from: private */
    public final CoroutineScope scope;
    /* access modifiers changed from: private */
    public final Flow<T> upstream;

    public ChannelManager(CoroutineScope coroutineScope, int i, boolean z, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, boolean z2, Flow<? extends T> flow) {
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        Intrinsics.checkNotNullParameter(function2, "onEach");
        Intrinsics.checkNotNullParameter(flow, "upstream");
        this.scope = coroutineScope;
        this.bufferSize = i;
        this.piggybackingDownstream = z;
        this.onEach = function2;
        this.keepUpstreamAlive = z2;
        this.upstream = flow;
        this.actor = new Actor();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ChannelManager(CoroutineScope coroutineScope, int i, boolean z, Function2 function2, boolean z2, Flow flow, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineScope, i, (i2 & 4) != 0 ? false : z, function2, (i2 & 16) != 0 ? false : z2, flow);
    }

    public final Object addDownstream(SendChannel<? super Message.Dispatch.Value<T>> sendChannel, Continuation<? super Unit> continuation) {
        Object send = this.actor.send(new Message.AddChannel(sendChannel), continuation);
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }

    public final Object removeDownstream(SendChannel<? super Message.Dispatch.Value<T>> sendChannel, Continuation<? super Unit> continuation) {
        Object send = this.actor.send(new Message.RemoveChannel(sendChannel), continuation);
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }

    public final Object close(Continuation<? super Unit> continuation) {
        Object close = this.actor.close(continuation);
        return close == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? close : Unit.INSTANCE;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0004\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\rH\u0002J\u001f\u0010\u0011\u001a\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0002J\u001f\u0010\u001a\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0018\u0010\u001d\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000fH\u0002J%\u0010\u001e\u001a\u00020\r2\u0012\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b0 H@ø\u0001\u0000¢\u0006\u0002\u0010!J\u001f\u0010\"\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010#J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0002J\b\u0010%\u001a\u00020\rH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager$Actor;", "Landroidx/paging/multicast/StoreRealActor;", "Landroidx/paging/multicast/ChannelManager$Message;", "(Landroidx/paging/multicast/ChannelManager;)V", "buffer", "Landroidx/paging/multicast/Buffer;", "channels", "", "Landroidx/paging/multicast/ChannelManager$ChannelEntry;", "dispatchedValue", "", "lastDeliveryAck", "Lkotlinx/coroutines/CompletableDeferred;", "", "producer", "Landroidx/paging/multicast/SharedFlowProducer;", "activateIfNecessary", "addEntry", "entry", "(Landroidx/paging/multicast/ChannelManager$ChannelEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doAdd", "msg", "Landroidx/paging/multicast/ChannelManager$Message$AddChannel;", "(Landroidx/paging/multicast/ChannelManager$Message$AddChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doDispatchError", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Error;", "doDispatchValue", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "(Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doHandleUpstreamClose", "doRemove", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handle", "(Landroidx/paging/multicast/ChannelManager$Message;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newProducer", "onClosed", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: ChannelManager.kt */
    private final class Actor extends StoreRealActor<Message<T>> {
        private final Buffer<T> buffer;
        private final List<ChannelEntry<T>> channels = new ArrayList();
        private boolean dispatchedValue;
        private CompletableDeferred<Unit> lastDeliveryAck;
        private SharedFlowProducer<T> producer;

        public Actor() {
            super(ChannelManager.this.scope);
            this.buffer = ChannelManagerKt.Buffer(ChannelManager.this.bufferSize);
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object handle(androidx.paging.multicast.ChannelManager.Message<T> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
            /*
                r6 = this;
                boolean r0 = r8 instanceof androidx.paging.multicast.ChannelManager$Actor$handle$1
                if (r0 == 0) goto L_0x0014
                r0 = r8
                androidx.paging.multicast.ChannelManager$Actor$handle$1 r0 = (androidx.paging.multicast.ChannelManager$Actor$handle$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r8 = r0.label
                int r8 = r8 - r2
                r0.label = r8
                goto L_0x0019
            L_0x0014:
                androidx.paging.multicast.ChannelManager$Actor$handle$1 r0 = new androidx.paging.multicast.ChannelManager$Actor$handle$1
                r0.<init>(r6, r8)
            L_0x0019:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 3
                r4 = 2
                r5 = 1
                if (r2 == 0) goto L_0x0039
                if (r2 == r5) goto L_0x0035
                if (r2 == r4) goto L_0x0035
                if (r2 != r3) goto L_0x002d
                goto L_0x0035
            L_0x002d:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L_0x0035:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x0084
            L_0x0039:
                kotlin.ResultKt.throwOnFailure(r8)
                boolean r8 = r7 instanceof androidx.paging.multicast.ChannelManager.Message.AddChannel
                if (r8 == 0) goto L_0x004b
                androidx.paging.multicast.ChannelManager$Message$AddChannel r7 = (androidx.paging.multicast.ChannelManager.Message.AddChannel) r7
                r0.label = r5
                java.lang.Object r7 = r6.doAdd(r7, r0)
                if (r7 != r1) goto L_0x0084
                return r1
            L_0x004b:
                boolean r8 = r7 instanceof androidx.paging.multicast.ChannelManager.Message.RemoveChannel
                if (r8 == 0) goto L_0x005e
                androidx.paging.multicast.ChannelManager$Message$RemoveChannel r7 = (androidx.paging.multicast.ChannelManager.Message.RemoveChannel) r7
                kotlinx.coroutines.channels.SendChannel r7 = r7.getChannel()
                r0.label = r4
                java.lang.Object r7 = r6.doRemove(r7, r0)
                if (r7 != r1) goto L_0x0084
                return r1
            L_0x005e:
                boolean r8 = r7 instanceof androidx.paging.multicast.ChannelManager.Message.Dispatch.Value
                if (r8 == 0) goto L_0x006d
                androidx.paging.multicast.ChannelManager$Message$Dispatch$Value r7 = (androidx.paging.multicast.ChannelManager.Message.Dispatch.Value) r7
                r0.label = r3
                java.lang.Object r7 = r6.doDispatchValue(r7, r0)
                if (r7 != r1) goto L_0x0084
                return r1
            L_0x006d:
                boolean r8 = r7 instanceof androidx.paging.multicast.ChannelManager.Message.Dispatch.Error
                if (r8 == 0) goto L_0x0077
                androidx.paging.multicast.ChannelManager$Message$Dispatch$Error r7 = (androidx.paging.multicast.ChannelManager.Message.Dispatch.Error) r7
                r6.doDispatchError(r7)
                goto L_0x0084
            L_0x0077:
                boolean r8 = r7 instanceof androidx.paging.multicast.ChannelManager.Message.Dispatch.UpstreamFinished
                if (r8 == 0) goto L_0x0084
                androidx.paging.multicast.ChannelManager$Message$Dispatch$UpstreamFinished r7 = (androidx.paging.multicast.ChannelManager.Message.Dispatch.UpstreamFinished) r7
                androidx.paging.multicast.SharedFlowProducer r7 = r7.getProducer()
                r6.doHandleUpstreamClose(r7)
            L_0x0084:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.ChannelManager.Actor.handle(androidx.paging.multicast.ChannelManager$Message, kotlin.coroutines.Continuation):java.lang.Object");
        }

        private final SharedFlowProducer<T> newProducer() {
            return new SharedFlowProducer<>(ChannelManager.this.scope, ChannelManager.this.upstream, new ChannelManager$Actor$newProducer$1(this));
        }

        private final void doHandleUpstreamClose(SharedFlowProducer<T> sharedFlowProducer) {
            if (this.producer == sharedFlowProducer) {
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                for (ChannelEntry channelEntry : this.channels) {
                    if (channelEntry.getReceivedValue()) {
                        if (!ChannelManager.this.piggybackingDownstream) {
                            channelEntry.close();
                        } else {
                            arrayList.add(channelEntry);
                        }
                    } else if (this.dispatchedValue) {
                        arrayList2.add(channelEntry);
                    } else if (!ChannelManager.this.piggybackingDownstream) {
                        channelEntry.close();
                    } else {
                        arrayList.add(channelEntry);
                    }
                }
                this.channels.clear();
                Collection collection = arrayList2;
                this.channels.addAll(collection);
                this.channels.addAll(arrayList);
                this.producer = null;
                if (!collection.isEmpty()) {
                    activateIfNecessary();
                }
            }
        }

        public void onClosed() {
            for (ChannelEntry close : this.channels) {
                close.close();
            }
            this.channels.clear();
            SharedFlowProducer<T> sharedFlowProducer = this.producer;
            if (sharedFlowProducer != null) {
                sharedFlowProducer.cancel();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0049  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0073  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0089  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ java.lang.Object doDispatchValue(androidx.paging.multicast.ChannelManager.Message.Dispatch.Value<T> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof androidx.paging.multicast.ChannelManager$Actor$doDispatchValue$1
                if (r0 == 0) goto L_0x0014
                r0 = r7
                androidx.paging.multicast.ChannelManager$Actor$doDispatchValue$1 r0 = (androidx.paging.multicast.ChannelManager$Actor$doDispatchValue$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r7 = r0.label
                int r7 = r7 - r2
                r0.label = r7
                goto L_0x0019
            L_0x0014:
                androidx.paging.multicast.ChannelManager$Actor$doDispatchValue$1 r0 = new androidx.paging.multicast.ChannelManager$Actor$doDispatchValue$1
                r0.<init>(r5, r7)
            L_0x0019:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L_0x0049
                if (r2 == r4) goto L_0x003d
                if (r2 != r3) goto L_0x0035
                java.lang.Object r6 = r0.L$1
                java.util.Iterator r6 = (java.util.Iterator) r6
                java.lang.Object r2 = r0.L$0
                androidx.paging.multicast.ChannelManager$Message$Dispatch$Value r2 = (androidx.paging.multicast.ChannelManager.Message.Dispatch.Value) r2
                kotlin.ResultKt.throwOnFailure(r7)
                goto L_0x0083
            L_0x0035:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x003d:
                java.lang.Object r6 = r0.L$1
                androidx.paging.multicast.ChannelManager$Message$Dispatch$Value r6 = (androidx.paging.multicast.ChannelManager.Message.Dispatch.Value) r6
                java.lang.Object r2 = r0.L$0
                androidx.paging.multicast.ChannelManager$Actor r2 = (androidx.paging.multicast.ChannelManager.Actor) r2
                kotlin.ResultKt.throwOnFailure(r7)
                goto L_0x0064
            L_0x0049:
                kotlin.ResultKt.throwOnFailure(r7)
                androidx.paging.multicast.ChannelManager r7 = androidx.paging.multicast.ChannelManager.this
                kotlin.jvm.functions.Function2 r7 = r7.onEach
                java.lang.Object r2 = r6.getValue()
                r0.L$0 = r5
                r0.L$1 = r6
                r0.label = r4
                java.lang.Object r7 = r7.invoke(r2, r0)
                if (r7 != r1) goto L_0x0063
                return r1
            L_0x0063:
                r2 = r5
            L_0x0064:
                androidx.paging.multicast.Buffer<T> r7 = r2.buffer
                r7.add(r6)
                r2.dispatchedValue = r4
                androidx.paging.multicast.Buffer<T> r7 = r2.buffer
                boolean r7 = r7.isEmpty()
                if (r7 == 0) goto L_0x0079
                kotlinx.coroutines.CompletableDeferred r7 = r6.getDelivered()
                r2.lastDeliveryAck = r7
            L_0x0079:
                java.util.List<androidx.paging.multicast.ChannelManager$ChannelEntry<T>> r7 = r2.channels
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.Iterator r7 = r7.iterator()
                r2 = r6
                r6 = r7
            L_0x0083:
                boolean r7 = r6.hasNext()
                if (r7 == 0) goto L_0x009c
                java.lang.Object r7 = r6.next()
                androidx.paging.multicast.ChannelManager$ChannelEntry r7 = (androidx.paging.multicast.ChannelManager.ChannelEntry) r7
                r0.L$0 = r2
                r0.L$1 = r6
                r0.label = r3
                java.lang.Object r7 = r7.dispatchValue(r2, r0)
                if (r7 != r1) goto L_0x0083
                return r1
            L_0x009c:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.ChannelManager.Actor.doDispatchValue(androidx.paging.multicast.ChannelManager$Message$Dispatch$Value, kotlin.coroutines.Continuation):java.lang.Object");
        }

        private final void doDispatchError(Message.Dispatch.Error<T> error) {
            this.dispatchedValue = true;
            for (ChannelEntry dispatchError : this.channels) {
                dispatchError.dispatchError(error.getError());
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ java.lang.Object doRemove(kotlinx.coroutines.channels.SendChannel<? super androidx.paging.multicast.ChannelManager.Message.Dispatch.Value<T>> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof androidx.paging.multicast.ChannelManager$Actor$doRemove$1
                if (r0 == 0) goto L_0x0014
                r0 = r7
                androidx.paging.multicast.ChannelManager$Actor$doRemove$1 r0 = (androidx.paging.multicast.ChannelManager$Actor$doRemove$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r7 = r0.label
                int r7 = r7 - r2
                r0.label = r7
                goto L_0x0019
            L_0x0014:
                androidx.paging.multicast.ChannelManager$Actor$doRemove$1 r0 = new androidx.paging.multicast.ChannelManager$Actor$doRemove$1
                r0.<init>(r5, r7)
            L_0x0019:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x0032
                if (r2 != r3) goto L_0x002a
                kotlin.ResultKt.throwOnFailure(r7)
                goto L_0x007f
            L_0x002a:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x0032:
                kotlin.ResultKt.throwOnFailure(r7)
                java.util.List<androidx.paging.multicast.ChannelManager$ChannelEntry<T>> r7 = r5.channels
                r2 = 0
                java.util.Iterator r7 = r7.iterator()
            L_0x003c:
                boolean r4 = r7.hasNext()
                if (r4 == 0) goto L_0x005a
                java.lang.Object r4 = r7.next()
                androidx.paging.multicast.ChannelManager$ChannelEntry r4 = (androidx.paging.multicast.ChannelManager.ChannelEntry) r4
                boolean r4 = r4.hasChannel(r6)
                java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
                boolean r4 = r4.booleanValue()
                if (r4 == 0) goto L_0x0057
                goto L_0x005b
            L_0x0057:
                int r2 = r2 + 1
                goto L_0x003c
            L_0x005a:
                r2 = -1
            L_0x005b:
                if (r2 < 0) goto L_0x007f
                java.util.List<androidx.paging.multicast.ChannelManager$ChannelEntry<T>> r6 = r5.channels
                r6.remove(r2)
                java.util.List<androidx.paging.multicast.ChannelManager$ChannelEntry<T>> r6 = r5.channels
                boolean r6 = r6.isEmpty()
                if (r6 == 0) goto L_0x007f
                androidx.paging.multicast.ChannelManager r6 = androidx.paging.multicast.ChannelManager.this
                boolean r6 = r6.keepUpstreamAlive
                if (r6 != 0) goto L_0x007f
                androidx.paging.multicast.SharedFlowProducer<T> r6 = r5.producer
                if (r6 == 0) goto L_0x007f
                r0.label = r3
                java.lang.Object r6 = r6.cancelAndJoin(r0)
                if (r6 != r1) goto L_0x007f
                return r1
            L_0x007f:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.ChannelManager.Actor.doRemove(kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ java.lang.Object doAdd(androidx.paging.multicast.ChannelManager.Message.AddChannel<T> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
            /*
                r6 = this;
                boolean r0 = r8 instanceof androidx.paging.multicast.ChannelManager$Actor$doAdd$1
                if (r0 == 0) goto L_0x0014
                r0 = r8
                androidx.paging.multicast.ChannelManager$Actor$doAdd$1 r0 = (androidx.paging.multicast.ChannelManager$Actor$doAdd$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r8 = r0.label
                int r8 = r8 - r2
                r0.label = r8
                goto L_0x0019
            L_0x0014:
                androidx.paging.multicast.ChannelManager$Actor$doAdd$1 r0 = new androidx.paging.multicast.ChannelManager$Actor$doAdd$1
                r0.<init>(r6, r8)
            L_0x0019:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x0036
                if (r2 != r3) goto L_0x002e
                java.lang.Object r7 = r0.L$0
                androidx.paging.multicast.ChannelManager$Actor r7 = (androidx.paging.multicast.ChannelManager.Actor) r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x0051
            L_0x002e:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L_0x0036:
                kotlin.ResultKt.throwOnFailure(r8)
                androidx.paging.multicast.ChannelManager$ChannelEntry r8 = new androidx.paging.multicast.ChannelManager$ChannelEntry
                kotlinx.coroutines.channels.SendChannel r7 = r7.getChannel()
                r2 = 0
                r4 = 2
                r5 = 0
                r8.<init>(r7, r2, r4, r5)
                r0.L$0 = r6
                r0.label = r3
                java.lang.Object r7 = r6.addEntry(r8, r0)
                if (r7 != r1) goto L_0x0050
                return r1
            L_0x0050:
                r7 = r6
            L_0x0051:
                r7.activateIfNecessary()
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.ChannelManager.Actor.doAdd(androidx.paging.multicast.ChannelManager$Message$AddChannel, kotlin.coroutines.Continuation):java.lang.Object");
        }

        private final void activateIfNecessary() {
            if (this.producer == null) {
                SharedFlowProducer<T> newProducer = newProducer();
                this.producer = newProducer;
                this.dispatchedValue = false;
                Intrinsics.checkNotNull(newProducer);
                newProducer.start();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00a0  */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x00e0  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ java.lang.Object addEntry(androidx.paging.multicast.ChannelManager.ChannelEntry<T> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof androidx.paging.multicast.ChannelManager$Actor$addEntry$1
                if (r0 == 0) goto L_0x0014
                r0 = r7
                androidx.paging.multicast.ChannelManager$Actor$addEntry$1 r0 = (androidx.paging.multicast.ChannelManager$Actor$addEntry$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r7 = r0.label
                int r7 = r7 - r2
                r0.label = r7
                goto L_0x0019
            L_0x0014:
                androidx.paging.multicast.ChannelManager$Actor$addEntry$1 r0 = new androidx.paging.multicast.ChannelManager$Actor$addEntry$1
                r0.<init>(r5, r7)
            L_0x0019:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x003b
                if (r2 != r3) goto L_0x0033
                java.lang.Object r6 = r0.L$1
                java.util.Iterator r6 = (java.util.Iterator) r6
                java.lang.Object r2 = r0.L$0
                androidx.paging.multicast.ChannelManager$ChannelEntry r2 = (androidx.paging.multicast.ChannelManager.ChannelEntry) r2
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r2
                goto L_0x009a
            L_0x0033:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x003b:
                kotlin.ResultKt.throwOnFailure(r7)
                java.util.List<androidx.paging.multicast.ChannelManager$ChannelEntry<T>> r7 = r5.channels
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                boolean r2 = r7 instanceof java.util.Collection
                if (r2 == 0) goto L_0x0051
                r2 = r7
                java.util.Collection r2 = (java.util.Collection) r2
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L_0x0051
            L_0x004f:
                r7 = 1
                goto L_0x0070
            L_0x0051:
                java.util.Iterator r7 = r7.iterator()
            L_0x0055:
                boolean r2 = r7.hasNext()
                if (r2 == 0) goto L_0x004f
                java.lang.Object r2 = r7.next()
                androidx.paging.multicast.ChannelManager$ChannelEntry r2 = (androidx.paging.multicast.ChannelManager.ChannelEntry) r2
                boolean r2 = r2.hasChannel(r6)
                java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                boolean r2 = r2.booleanValue()
                if (r2 == 0) goto L_0x0055
                r7 = 0
            L_0x0070:
                if (r7 == 0) goto L_0x00e0
                boolean r7 = r6.getReceivedValue()
                r7 = r7 ^ r3
                if (r7 == 0) goto L_0x00c3
                java.util.List<androidx.paging.multicast.ChannelManager$ChannelEntry<T>> r7 = r5.channels
                r7.add(r6)
                androidx.paging.multicast.Buffer<T> r7 = r5.buffer
                java.util.Collection r7 = r7.getItems()
                boolean r7 = r7.isEmpty()
                r7 = r7 ^ r3
                if (r7 == 0) goto L_0x00b3
                androidx.paging.multicast.Buffer<T> r7 = r5.buffer
                java.util.Collection r7 = r7.getItems()
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.Iterator r7 = r7.iterator()
                r4 = r7
                r7 = r6
                r6 = r4
            L_0x009a:
                boolean r2 = r6.hasNext()
                if (r2 == 0) goto L_0x00c0
                java.lang.Object r2 = r6.next()
                androidx.paging.multicast.ChannelManager$Message$Dispatch$Value r2 = (androidx.paging.multicast.ChannelManager.Message.Dispatch.Value) r2
                r0.L$0 = r7
                r0.L$1 = r6
                r0.label = r3
                java.lang.Object r2 = r7.dispatchValue(r2, r0)
                if (r2 != r1) goto L_0x009a
                return r1
            L_0x00b3:
                kotlinx.coroutines.CompletableDeferred<kotlin.Unit> r6 = r5.lastDeliveryAck
                if (r6 == 0) goto L_0x00c0
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                boolean r6 = r6.complete(r7)
                kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            L_0x00c0:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L_0x00c3:
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                r7.append(r6)
                java.lang.String r6 = " already received a value"
                r7.append(r6)
                java.lang.String r6 = r7.toString()
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r6 = r6.toString()
                r7.<init>(r6)
                java.lang.Throwable r7 = (java.lang.Throwable) r7
                throw r7
            L_0x00e0:
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                r7.append(r6)
                java.lang.String r6 = " is already in the list."
                r7.append(r6)
                java.lang.String r6 = r7.toString()
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r6 = r6.toString()
                r7.<init>(r6)
                java.lang.Throwable r7 = (java.lang.Throwable) r7
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.ChannelManager.Actor.addEntry(androidx.paging.multicast.ChannelManager$ChannelEntry, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B#\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00050\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\f\u001a\u00020\rJ\u0015\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00050\u0004HÂ\u0003J\t\u0010\u000f\u001a\u00020\u0007HÂ\u0003J/\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u001f\u0010\u0014\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\u0014\u0010\u0019\u001a\u00020\u00072\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000J\u001a\u0010\u0019\u001a\u00020\u00072\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00050\u0004J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager$ChannelEntry;", "T", "", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "_receivedValue", "", "(Lkotlinx/coroutines/channels/SendChannel;Z)V", "receivedValue", "getReceivedValue", "()Z", "close", "", "component1", "component2", "copy", "dispatchError", "error", "", "dispatchValue", "value", "(Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "equals", "other", "hasChannel", "entry", "hashCode", "", "toString", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: ChannelManager.kt */
    public static final class ChannelEntry<T> {
        private boolean _receivedValue;
        private final SendChannel<Message.Dispatch.Value<T>> channel;

        private final SendChannel<Message.Dispatch.Value<T>> component1() {
            return this.channel;
        }

        private final boolean component2() {
            return this._receivedValue;
        }

        public static /* synthetic */ ChannelEntry copy$default(ChannelEntry channelEntry, SendChannel<Message.Dispatch.Value<T>> sendChannel, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                sendChannel = channelEntry.channel;
            }
            if ((i & 2) != 0) {
                z = channelEntry._receivedValue;
            }
            return channelEntry.copy(sendChannel, z);
        }

        public final ChannelEntry<T> copy(SendChannel<? super Message.Dispatch.Value<T>> sendChannel, boolean z) {
            Intrinsics.checkNotNullParameter(sendChannel, "channel");
            return new ChannelEntry<>(sendChannel, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ChannelEntry)) {
                return false;
            }
            ChannelEntry channelEntry = (ChannelEntry) obj;
            return Intrinsics.areEqual((Object) this.channel, (Object) channelEntry.channel) && this._receivedValue == channelEntry._receivedValue;
        }

        public int hashCode() {
            SendChannel<Message.Dispatch.Value<T>> sendChannel = this.channel;
            int hashCode = (sendChannel != null ? sendChannel.hashCode() : 0) * 31;
            boolean z = this._receivedValue;
            if (z) {
                z = true;
            }
            return hashCode + (z ? 1 : 0);
        }

        public String toString() {
            return "ChannelEntry(channel=" + this.channel + ", _receivedValue=" + this._receivedValue + ")";
        }

        public ChannelEntry(SendChannel<? super Message.Dispatch.Value<T>> sendChannel, boolean z) {
            Intrinsics.checkNotNullParameter(sendChannel, "channel");
            this.channel = sendChannel;
            this._receivedValue = z;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ChannelEntry(SendChannel sendChannel, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(sendChannel, (i & 2) != 0 ? false : z);
        }

        public final boolean getReceivedValue() {
            return this._receivedValue;
        }

        public final Object dispatchValue(Message.Dispatch.Value<T> value, Continuation<? super Unit> continuation) {
            this._receivedValue = true;
            Object send = this.channel.send(value, continuation);
            if (send == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return send;
            }
            return Unit.INSTANCE;
        }

        public final void dispatchError(Throwable th) {
            Intrinsics.checkNotNullParameter(th, "error");
            this._receivedValue = true;
            this.channel.close(th);
        }

        public final void close() {
            SendChannel.DefaultImpls.close$default(this.channel, (Throwable) null, 1, (Object) null);
        }

        public final boolean hasChannel(SendChannel<? super Message.Dispatch.Value<T>> sendChannel) {
            Intrinsics.checkNotNullParameter(sendChannel, "channel");
            return this.channel == sendChannel;
        }

        public final boolean hasChannel(ChannelEntry<T> channelEntry) {
            Intrinsics.checkNotNullParameter(channelEntry, "entry");
            return this.channel == channelEntry.channel;
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002:\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager$Message;", "T", "", "()V", "AddChannel", "Dispatch", "RemoveChannel", "Landroidx/paging/multicast/ChannelManager$Message$AddChannel;", "Landroidx/paging/multicast/ChannelManager$Message$RemoveChannel;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: ChannelManager.kt */
    public static abstract class Message<T> {
        private Message() {
        }

        public /* synthetic */ Message(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager$Message$AddChannel;", "T", "Landroidx/paging/multicast/ChannelManager$Message;", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "(Lkotlinx/coroutines/channels/SendChannel;)V", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: ChannelManager.kt */
        public static final class AddChannel<T> extends Message<T> {
            private final SendChannel<Dispatch.Value<T>> channel;

            public final SendChannel<Dispatch.Value<T>> getChannel() {
                return this.channel;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public AddChannel(SendChannel<? super Dispatch.Value<T>> sendChannel) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(sendChannel, "channel");
                this.channel = sendChannel;
            }
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager$Message$RemoveChannel;", "T", "Landroidx/paging/multicast/ChannelManager$Message;", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "(Lkotlinx/coroutines/channels/SendChannel;)V", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: ChannelManager.kt */
        public static final class RemoveChannel<T> extends Message<T> {
            private final SendChannel<Dispatch.Value<T>> channel;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public RemoveChannel(SendChannel<? super Dispatch.Value<T>> sendChannel) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(sendChannel, "channel");
                this.channel = sendChannel;
            }

            public final SendChannel<Dispatch.Value<T>> getChannel() {
                return this.channel;
            }
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager$Message$Dispatch;", "T", "Landroidx/paging/multicast/ChannelManager$Message;", "()V", "Error", "UpstreamFinished", "Value", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Error;", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch$UpstreamFinished;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: ChannelManager.kt */
        public static abstract class Dispatch<T> extends Message<T> {
            private Dispatch() {
                super((DefaultConstructorMarker) null);
            }

            public /* synthetic */ Dispatch(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0003\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00028\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0003\u001a\u00028\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Value;", "T", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch;", "value", "delivered", "Lkotlinx/coroutines/CompletableDeferred;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/CompletableDeferred;)V", "getDelivered", "()Lkotlinx/coroutines/CompletableDeferred;", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
            /* compiled from: ChannelManager.kt */
            public static final class Value<T> extends Dispatch<T> {
                private final CompletableDeferred<Unit> delivered;
                private final T value;

                public final T getValue() {
                    return this.value;
                }

                public final CompletableDeferred<Unit> getDelivered() {
                    return this.delivered;
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Value(T t, CompletableDeferred<Unit> completableDeferred) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(completableDeferred, "delivered");
                    this.value = t;
                    this.delivered = completableDeferred;
                }
            }

            @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0003\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager$Message$Dispatch$Error;", "T", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch;", "error", "", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
            /* compiled from: ChannelManager.kt */
            public static final class Error<T> extends Dispatch<T> {
                private final Throwable error;

                public final Throwable getError() {
                    return this.error;
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(Throwable th) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(th, "error");
                    this.error = th;
                }
            }

            @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0003\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00030\u0004¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00030\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo27512d2 = {"Landroidx/paging/multicast/ChannelManager$Message$Dispatch$UpstreamFinished;", "T", "Landroidx/paging/multicast/ChannelManager$Message$Dispatch;", "producer", "Landroidx/paging/multicast/SharedFlowProducer;", "(Landroidx/paging/multicast/SharedFlowProducer;)V", "getProducer", "()Landroidx/paging/multicast/SharedFlowProducer;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
            /* compiled from: ChannelManager.kt */
            public static final class UpstreamFinished<T> extends Dispatch<T> {
                private final SharedFlowProducer<T> producer;

                public final SharedFlowProducer<T> getProducer() {
                    return this.producer;
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public UpstreamFinished(SharedFlowProducer<T> sharedFlowProducer) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(sharedFlowProducer, "producer");
                    this.producer = sharedFlowProducer;
                }
            }
        }
    }
}
