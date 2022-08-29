package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata(mo27511d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u000006:\u0004defgB)\u0012 \u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0013\u001a\u000e\u0012\u0002\b\u00030\u0011j\u0006\u0012\u0002\b\u0003`\u00122\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0019\u001a\u00020\u0018H\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001f\u001a\u00020\u00032\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 J#\u0010!\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\b!\u0010\"J\u001b\u0010!\u001a\u00020\b2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\b!\u0010#J)\u0010&\u001a\u00020\u00032\u0018\u0010%\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`$H\u0016¢\u0006\u0004\b&\u0010\u0007J\u0019\u0010'\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00028\u0000H\u0014¢\u0006\u0004\b+\u0010,J#\u0010/\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010.\u001a\u0006\u0012\u0002\b\u00030-H\u0014¢\u0006\u0004\b/\u00100J\u0017\u00102\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u000201H\u0014¢\u0006\u0004\b2\u00103JX\u00109\u001a\u00020\u0003\"\u0004\b\u0001\u001042\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010-2\u0006\u0010\u0010\u001a\u00028\u00002(\u00108\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000107\u0012\u0006\u0012\u0004\u0018\u00010\u001a05H\u0002ø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010;J\u001d\u0010=\u001a\b\u0012\u0002\b\u0003\u0018\u00010<2\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b=\u0010>J\u001b\u0010?\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b?\u0010;J\u0017\u0010@\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010<H\u0014¢\u0006\u0004\b@\u0010AJ\u0011\u0010B\u001a\u0004\u0018\u00010\u0018H\u0004¢\u0006\u0004\bB\u0010CJ\u000f\u0010E\u001a\u00020DH\u0016¢\u0006\u0004\bE\u0010FJ$\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030G2\u0006\u0010\u0010\u001a\u00028\u0000ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bH\u0010,J+\u0010J\u001a\u00020\u0003*\u0006\u0012\u0002\b\u0003072\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\bJ\u0010KR\u0014\u0010M\u001a\u00020D8TX\u0004¢\u0006\u0006\u001a\u0004\bL\u0010FR\u001a\u0010P\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d8DX\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u001a\u0010R\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d8DX\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010OR\u0014\u0010S\u001a\u00020\n8$X¤\u0004¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0014\u0010U\u001a\u00020\n8$X¤\u0004¢\u0006\u0006\u001a\u0004\bU\u0010TR\u0011\u0010V\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bV\u0010TR\u0014\u0010W\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\bW\u0010TR#\u0010[\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000060X8F¢\u0006\u0006\u001a\u0004\bY\u0010ZR.\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00048\u0004X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\\R\u001a\u0010^\u001a\u00020]8\u0004X\u0004¢\u0006\f\n\u0004\b^\u0010_\u001a\u0004\b`\u0010aR\u0014\u0010c\u001a\u00020D8BX\u0004¢\u0006\u0006\u001a\u0004\bb\u0010F\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006h"}, mo27512d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", "E", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "", "countQueueSize", "()I", "element", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "describeSendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "describeTryOffer", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Closed;", "closed", "helpClose", "(Lkotlinx/coroutines/channels/Closed;)V", "helpCloseAndGetSendException", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "(Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "Lkotlinx/coroutines/channels/Handler;", "handler", "invokeOnClose", "invokeOnCloseHandler", "(Ljava/lang/Throwable;)V", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "R", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectSend", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendSuspend", "takeFirstReceiveOrPeekClosed", "()Lkotlinx/coroutines/channels/ReceiveOrClosed;", "takeFirstSendOrPeekClosed", "()Lkotlinx/coroutines/channels/Send;", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "trySend", "helpCloseAndResumeWithSendException", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)V", "getBufferDebugString", "bufferDebugString", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForReceive", "getClosedForSend", "closedForSend", "isBufferAlwaysFull", "()Z", "isBufferFull", "isClosedForSend", "isFullImpl", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueueDebugStateString", "queueDebugStateString", "SendBuffered", "SendBufferedDesc", "SendSelect", "TryOfferDesc", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: AbstractChannel.kt */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    private volatile /* synthetic */ Object onCloseHandler = null;
    protected final Function1<E, Unit> onUndeliveredElement;
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();

    /* access modifiers changed from: protected */
    public String getBufferDebugString() {
        return "";
    }

    /* access modifiers changed from: protected */
    public abstract boolean isBufferAlwaysFull();

    /* access modifiers changed from: protected */
    public abstract boolean isBufferFull();

    /* access modifiers changed from: protected */
    public void onClosedIdempotent(LockFreeLinkedListNode lockFreeLinkedListNode) {
    }

    public AbstractSendChannel(Function1<? super E, Unit> function1) {
        this.onUndeliveredElement = function1;
    }

    /* access modifiers changed from: protected */
    public final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    /* access modifiers changed from: protected */
    public Object offerInternal(E e) {
        ReceiveOrClosed takeFirstReceiveOrPeekClosed;
        Symbol tryResumeReceive;
        do {
            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (takeFirstReceiveOrPeekClosed == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(e, (LockFreeLinkedListNode.PrepareOp) null);
        } while (tryResumeReceive == null);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        takeFirstReceiveOrPeekClosed.completeResumeReceive(e);
        return takeFirstReceiveOrPeekClosed.getOfferResult();
    }

    /* access modifiers changed from: protected */
    public Object offerSelectInternal(E e, SelectInstance<?> selectInstance) {
        TryOfferDesc describeTryOffer = describeTryOffer(e);
        Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(describeTryOffer);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        ReceiveOrClosed receiveOrClosed = (ReceiveOrClosed) describeTryOffer.getResult();
        receiveOrClosed.completeResumeReceive(e);
        return receiveOrClosed.getOfferResult();
    }

    /* access modifiers changed from: protected */
    public final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        Closed<?> closed = prevNode instanceof Closed ? (Closed) prevNode : null;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    /* access modifiers changed from: protected */
    public final Closed<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        Closed<?> closed = nextNode instanceof Closed ? (Closed) nextNode : null;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        r1 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlinx.coroutines.channels.Send takeFirstSendOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
        L_0x0004:
            java.lang.Object r1 = r0.getNext()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = 0
            if (r1 != r0) goto L_0x000f
        L_0x000d:
            r1 = r2
            goto L_0x0028
        L_0x000f:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.Send
            if (r3 != 0) goto L_0x0014
            goto L_0x000d
        L_0x0014:
            r2 = r1
            kotlinx.coroutines.channels.Send r2 = (kotlinx.coroutines.channels.Send) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed
            if (r2 == 0) goto L_0x0022
            boolean r2 = r1.isRemoved()
            if (r2 != 0) goto L_0x0022
            goto L_0x0028
        L_0x0022:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r1.removeOrNext()
            if (r2 != 0) goto L_0x002b
        L_0x0028:
            kotlinx.coroutines.channels.Send r1 = (kotlinx.coroutines.channels.Send) r1
            return r1
        L_0x002b:
            r2.helpRemovePrev()
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstSendOrPeekClosed():kotlinx.coroutines.channels.Send");
    }

    /* access modifiers changed from: protected */
    public final ReceiveOrClosed<?> sendBuffered(E e) {
        LockFreeLinkedListNode prevNode;
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        LockFreeLinkedListNode sendBuffered = new SendBuffered(e);
        do {
            prevNode = lockFreeLinkedListNode.getPrevNode();
            if (prevNode instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) prevNode;
            }
        } while (!prevNode.addNext(sendBuffered, lockFreeLinkedListNode));
        return null;
    }

    /* access modifiers changed from: protected */
    public final LockFreeLinkedListNode.AddLastDesc<?> describeSendBuffered(E e) {
        return new SendBufferedDesc<>(this.queue, e);
    }

    @Metadata(mo27511d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, mo27512d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
    /* compiled from: AbstractChannel.kt */
    private static class SendBufferedDesc<E> extends LockFreeLinkedListNode.AddLastDesc<SendBuffered<? extends E>> {
        public SendBufferedDesc(LockFreeLinkedListHead lockFreeLinkedListHead, E e) {
            super(lockFreeLinkedListHead, new SendBuffered(e));
        }

        /* access modifiers changed from: protected */
        public Object failure(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            return null;
        }
    }

    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    /* access modifiers changed from: private */
    public final boolean isFullImpl() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    public final Object send(E e, Continuation<? super Unit> continuation) {
        if (offerInternal(e) == AbstractChannelKt.OFFER_SUCCESS) {
            return Unit.INSTANCE;
        }
        Object sendSuspend = sendSuspend(e, continuation);
        return sendSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? sendSuspend : Unit.INSTANCE;
    }

    public boolean offer(E e) {
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        try {
            return SendChannel.DefaultImpls.offer(this, e);
        } catch (Throwable th) {
            Function1<E, Unit> function1 = this.onUndeliveredElement;
            if (function1 == null || (callUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, (UndeliveredElementException) null, 2, (Object) null)) == null) {
                throw th;
            }
            ExceptionsKt.addSuppressed(callUndeliveredElementCatchingException$default, th);
            throw callUndeliveredElementCatchingException$default;
        }
    }

    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    public final Object m2428trySendJP2dKIU(E e) {
        Object offerInternal = offerInternal(e);
        if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
            return ChannelResult.Companion.m2452successJP2dKIU(Unit.INSTANCE);
        }
        if (offerInternal == AbstractChannelKt.OFFER_FAILED) {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                return ChannelResult.Companion.m2451failurePtdJZtk();
            }
            return ChannelResult.Companion.m2450closedJP2dKIU(helpCloseAndGetSendException(closedForSend));
        } else if (offerInternal instanceof Closed) {
            return ChannelResult.Companion.m2450closedJP2dKIU(helpCloseAndGetSendException((Closed) offerInternal));
        } else {
            throw new IllegalStateException(Intrinsics.stringPlus("trySend returned ", offerInternal).toString());
        }
    }

    private final Throwable helpCloseAndGetSendException(Closed<?> closed) {
        helpClose(closed);
        return closed.getSendException();
    }

    private final Throwable helpCloseAndGetSendException(E e, Closed<?> closed) {
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        helpClose(closed);
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, (UndeliveredElementException) null, 2, (Object) null)) == null) {
            return closed.getSendException();
        }
        ExceptionsKt.addSuppressed(callUndeliveredElementCatchingException$default, closed.getSendException());
        throw callUndeliveredElementCatchingException$default;
    }

    /* access modifiers changed from: private */
    public final void helpCloseAndResumeWithSendException(Continuation<?> continuation, E e, Closed<?> closed) {
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        helpClose(closed);
        Throwable sendException = closed.getSendException();
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, (UndeliveredElementException) null, 2, (Object) null)) == null) {
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m1023constructorimpl(ResultKt.createFailure(sendException)));
            return;
        }
        Throwable th = callUndeliveredElementCatchingException$default;
        ExceptionsKt.addSuppressed(th, sendException);
        Result.Companion companion2 = Result.Companion;
        continuation.resumeWith(Result.m1023constructorimpl(ResultKt.createFailure(th)));
    }

    /* access modifiers changed from: protected */
    public Object enqueueSend(Send send) {
        boolean z;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysFull()) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
            do {
                prevNode = lockFreeLinkedListNode.getPrevNode();
                if (prevNode instanceof ReceiveOrClosed) {
                    return prevNode;
                }
            } while (!prevNode.addNext(send, lockFreeLinkedListNode));
            return null;
        }
        LockFreeLinkedListNode lockFreeLinkedListNode2 = this.queue;
        LockFreeLinkedListNode lockFreeLinkedListNode3 = send;
        LockFreeLinkedListNode.CondAddOp abstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1 = new AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1(lockFreeLinkedListNode3, this);
        while (true) {
            LockFreeLinkedListNode prevNode2 = lockFreeLinkedListNode2.getPrevNode();
            if (!(prevNode2 instanceof ReceiveOrClosed)) {
                int tryCondAddNext = prevNode2.tryCondAddNext(lockFreeLinkedListNode3, lockFreeLinkedListNode2, abstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1);
                z = true;
                if (tryCondAddNext != 1) {
                    if (tryCondAddNext == 2) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            } else {
                return prevNode2;
            }
        }
        if (!z) {
            return AbstractChannelKt.ENQUEUE_FAILED;
        }
        return null;
    }

    public boolean close(Throwable th) {
        boolean z;
        Closed closed = new Closed(th);
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        while (true) {
            LockFreeLinkedListNode prevNode = lockFreeLinkedListNode.getPrevNode();
            z = true;
            if (!(prevNode instanceof Closed)) {
                if (prevNode.addNext(closed, lockFreeLinkedListNode)) {
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            closed = (Closed) this.queue.getPrevNode();
        }
        helpClose(closed);
        if (z) {
            invokeOnCloseHandler(th);
        }
        return z;
    }

    private final void invokeOnCloseHandler(Throwable th) {
        Object obj = this.onCloseHandler;
        if (obj != null && obj != AbstractChannelKt.HANDLER_INVOKED && onCloseHandler$FU.compareAndSet(this, obj, AbstractChannelKt.HANDLER_INVOKED)) {
            ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1)).invoke(th);
        }
    }

    public void invokeOnClose(Function1<? super Throwable, Unit> function1) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = onCloseHandler$FU;
        if (!atomicReferenceFieldUpdater.compareAndSet(this, (Object) null, function1)) {
            Object obj = this.onCloseHandler;
            if (obj == AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException(Intrinsics.stringPlus("Another handler was already registered: ", obj));
        }
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend != null && atomicReferenceFieldUpdater.compareAndSet(this, function1, AbstractChannelKt.HANDLER_INVOKED)) {
            function1.invoke(closedForSend.closeCause);
        }
    }

    private final void helpClose(Closed<?> closed) {
        Object r2 = InlineList.m2474constructorimpl$default((Object) null, 1, (DefaultConstructorMarker) null);
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            Receive receive = prevNode instanceof Receive ? (Receive) prevNode : null;
            if (receive == null) {
                break;
            } else if (!receive.remove()) {
                receive.helpRemove();
            } else {
                r2 = InlineList.m2479plusFjFbRPM(r2, receive);
            }
        }
        if (r2 != null) {
            if (!(r2 instanceof ArrayList)) {
                ((Receive) r2).resumeReceiveClosed(closed);
            } else {
                Objects.requireNonNull(r2, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
                ArrayList arrayList = (ArrayList) r2;
                int size = arrayList.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i = size - 1;
                        ((Receive) arrayList.get(size)).resumeReceiveClosed(closed);
                        if (i < 0) {
                            break;
                        }
                        size = i;
                    }
                }
            }
        }
        onClosedIdempotent(closed);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        r1 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public kotlinx.coroutines.channels.ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
        L_0x0004:
            java.lang.Object r1 = r0.getNext()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = 0
            if (r1 != r0) goto L_0x000f
        L_0x000d:
            r1 = r2
            goto L_0x0028
        L_0x000f:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r3 != 0) goto L_0x0014
            goto L_0x000d
        L_0x0014:
            r2 = r1
            kotlinx.coroutines.channels.ReceiveOrClosed r2 = (kotlinx.coroutines.channels.ReceiveOrClosed) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed
            if (r2 == 0) goto L_0x0022
            boolean r2 = r1.isRemoved()
            if (r2 != 0) goto L_0x0022
            goto L_0x0028
        L_0x0022:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r1.removeOrNext()
            if (r2 != 0) goto L_0x002b
        L_0x0028:
            kotlinx.coroutines.channels.ReceiveOrClosed r1 = (kotlinx.coroutines.channels.ReceiveOrClosed) r1
            return r1
        L_0x002b:
            r2.helpRemovePrev()
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstReceiveOrPeekClosed():kotlinx.coroutines.channels.ReceiveOrClosed");
    }

    /* access modifiers changed from: protected */
    public final TryOfferDesc<E> describeTryOffer(E e) {
        return new TryOfferDesc<>(e, this.queue);
    }

    @Metadata(mo27511d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0016R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0012"}, mo27512d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
    /* compiled from: AbstractChannel.kt */
    protected static final class TryOfferDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>> {
        public final E element;

        public TryOfferDesc(E e, LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
            this.element = e;
        }

        /* access modifiers changed from: protected */
        public Object failure(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (!(lockFreeLinkedListNode instanceof ReceiveOrClosed)) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            return null;
        }

        public Object onPrepare(LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol tryResumeReceive = ((ReceiveOrClosed) prepareOp.affected).tryResumeReceive(this.element, prepareOp);
            if (tryResumeReceive == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            if (tryResumeReceive == AtomicKt.RETRY_ATOMIC) {
                return AtomicKt.RETRY_ATOMIC;
            }
            if (!DebugKt.getASSERTIONS_ENABLED()) {
                return null;
            }
            if (tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN) {
                return null;
            }
            throw new AssertionError();
        }
    }

    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return new AbstractSendChannel$onSend$1(this);
    }

    /* access modifiers changed from: private */
    public final <R> void registerSelectSend(SelectInstance<? super R> selectInstance, E e, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            if (isFullImpl()) {
                SendSelect sendSelect = new SendSelect(e, this, selectInstance, function2);
                Object enqueueSend = enqueueSend(sendSelect);
                if (enqueueSend == null) {
                    selectInstance.disposeOnSelect(sendSelect);
                    return;
                } else if (enqueueSend instanceof Closed) {
                    throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException(e, (Closed) enqueueSend));
                } else if (enqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueSend instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + enqueueSend + ' ').toString());
                }
            }
            Object offerSelectInternal = offerSelectInternal(e, selectInstance);
            if (offerSelectInternal != SelectKt.getALREADY_SELECTED()) {
                if (offerSelectInternal != AbstractChannelKt.OFFER_FAILED && offerSelectInternal != AtomicKt.RETRY_ATOMIC) {
                    if (offerSelectInternal == AbstractChannelKt.OFFER_SUCCESS) {
                        UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
                        return;
                    } else if (offerSelectInternal instanceof Closed) {
                        throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException(e, (Closed) offerSelectInternal));
                    } else {
                        throw new IllegalStateException(Intrinsics.stringPlus("offerSelectInternal returned ", offerSelectInternal).toString());
                    }
                }
            } else {
                return;
            }
        }
    }

    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) + '{' + getQueueDebugStateString() + '}' + getBufferDebugString();
    }

    private final String getQueueDebugStateString() {
        String str;
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            return "EmptyQueue";
        }
        if (nextNode instanceof Closed) {
            str = nextNode.toString();
        } else if (nextNode instanceof Receive) {
            str = "ReceiveQueued";
        } else if (nextNode instanceof Send) {
            str = "SendQueued";
        } else {
            str = Intrinsics.stringPlus("UNEXPECTED:", nextNode);
        }
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (prevNode == nextNode) {
            return str;
        }
        String str2 = str + ",queueSize=" + countQueueSize();
        if (!(prevNode instanceof Closed)) {
            return str2;
        }
        return str2 + ",closedForSend=" + prevNode;
    }

    private final int countQueueSize() {
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        int i = 0;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) lockFreeLinkedListHead.getNext(); !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) lockFreeLinkedListHead); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                i++;
            }
        }
        return i;
    }

    @Metadata(mo27511d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u0004BV\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t\u0012(\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0014\u0010\u0017\u001a\u00020\u00152\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0015H\u0016R7\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b8\u0006X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00028\u0001X\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t8\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, mo27512d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", "E", "R", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "pollResult", "channel", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/AbstractSendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getPollResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "completeResumeSend", "", "dispose", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "undeliveredElement", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
    /* compiled from: AbstractChannel.kt */
    private static final class SendSelect<E, R> extends Send implements DisposableHandle {
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> block;
        public final AbstractSendChannel<E> channel;
        private final E pollResult;
        public final SelectInstance<R> select;

        public E getPollResult() {
            return this.pollResult;
        }

        public SendSelect(E e, AbstractSendChannel<E> abstractSendChannel, SelectInstance<? super R> selectInstance, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
            this.pollResult = e;
            this.channel = abstractSendChannel;
            this.select = selectInstance;
            this.block = function2;
        }

        public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp prepareOp) {
            return (Symbol) this.select.trySelectOther(prepareOp);
        }

        public void completeResumeSend() {
            CancellableKt.startCoroutineCancellable$default(this.block, this.channel, this.select.getCompletion(), (Function1) null, 4, (Object) null);
        }

        public void dispose() {
            if (remove()) {
                undeliveredElement();
            }
        }

        public void resumeSendClosed(Closed<?> closed) {
            if (this.select.trySelect()) {
                this.select.resumeSelectWithException(closed.getSendException());
            }
        }

        public void undeliveredElement() {
            Function1<E, Unit> function1 = this.channel.onUndeliveredElement;
            if (function1 != null) {
                OnUndeliveredElementKt.callUndeliveredElement(function1, getPollResult(), this.select.getCompletion().getContext());
            }
        }

        public String toString() {
            return "SendSelect@" + DebugStringsKt.getHexAddress(this) + '(' + getPollResult() + ")[" + this.channel + ", " + this.select + ']';
        }
    }

    @Metadata(mo27511d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0012\u0010\u0003\u001a\u00028\u00018\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, mo27512d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "E", "Lkotlinx/coroutines/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
    /* compiled from: AbstractChannel.kt */
    public static final class SendBuffered<E> extends Send {
        public final E element;

        public void completeResumeSend() {
        }

        public SendBuffered(E e) {
            this.element = e;
        }

        public Object getPollResult() {
            return this.element;
        }

        public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
            if (prepareOp != null) {
                prepareOp.finishPrepare();
            }
            return symbol;
        }

        public void resumeSendClosed(Closed<?> closed) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
                throw new AssertionError();
            }
        }

        public String toString() {
            return "SendBuffered@" + DebugStringsKt.getHexAddress(this) + '(' + this.element + ')';
        }
    }

    /* access modifiers changed from: private */
    public final Object sendSuspend(E e, Continuation<? super Unit> continuation) {
        SendElement sendElement;
        CancellableContinuationImpl<? super Unit> orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        CancellableContinuation cancellableContinuation = orCreateCancellableContinuation;
        while (true) {
            if (isFullImpl()) {
                if (this.onUndeliveredElement == null) {
                    sendElement = new SendElement(e, cancellableContinuation);
                } else {
                    sendElement = new SendElementWithUndeliveredHandler(e, cancellableContinuation, this.onUndeliveredElement);
                }
                Object enqueueSend = enqueueSend(sendElement);
                if (enqueueSend == null) {
                    CancellableContinuationKt.removeOnCancellation(cancellableContinuation, sendElement);
                    break;
                } else if (enqueueSend instanceof Closed) {
                    helpCloseAndResumeWithSendException(cancellableContinuation, e, (Closed) enqueueSend);
                    break;
                } else if (enqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueSend instanceof Receive)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("enqueueSend returned ", enqueueSend).toString());
                }
            }
            Object offerInternal = offerInternal(e);
            if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m1023constructorimpl(unit));
                break;
            } else if (offerInternal != AbstractChannelKt.OFFER_FAILED) {
                if (offerInternal instanceof Closed) {
                    helpCloseAndResumeWithSendException(cancellableContinuation, e, (Closed) offerInternal);
                } else {
                    throw new IllegalStateException(Intrinsics.stringPlus("offerInternal returned ", offerInternal).toString());
                }
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }
}
