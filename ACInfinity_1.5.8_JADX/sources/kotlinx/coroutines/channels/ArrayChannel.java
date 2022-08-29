package kotlinx.coroutines.channels;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata(mo27511d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000BB9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012 \u0010\t\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0013\u001a\u00020\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0014¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0014¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00172\u0006\u0010\r\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001c\u0010\u001dJ#\u0010 \u001a\u00020\u00172\u0006\u0010\r\u001a\u00028\u00002\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014¢\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0012H\u0014¢\u0006\u0004\b#\u0010$J\u0011\u0010%\u001a\u0004\u0018\u00010\u0017H\u0014¢\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\u0004\u0018\u00010\u00172\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014¢\u0006\u0004\b'\u0010(J\u0019\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b*\u0010+R\u001e\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170,8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00102\u001a\u00020/8TX\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0016\u00104\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u00020\u00128DX\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u00128DX\u0004¢\u0006\u0006\u001a\u0004\b7\u00106R\u0014\u00108\u001a\u00020\u00128DX\u0004¢\u0006\u0006\u001a\u0004\b8\u00106R\u0014\u00109\u001a\u00020\u00128DX\u0004¢\u0006\u0006\u001a\u0004\b9\u00106R\u0014\u0010:\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b:\u00106R\u0014\u0010;\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b;\u00106R\u0018\u0010>\u001a\u00060<j\u0002`=8\u0002X\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010@¨\u0006A"}, mo27512d2 = {"Lkotlinx/coroutines/channels/ArrayChannel;", "E", "", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/jvm/functions/Function1;)V", "currentSize", "element", "enqueueElement", "(ILjava/lang/Object;)V", "Lkotlinx/coroutines/channels/Receive;", "receive", "", "enqueueReceiveInternal", "(Lkotlinx/coroutines/channels/Receive;)Z", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "ensureCapacity", "(I)V", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "wasClosed", "onCancelIdempotent", "(Z)V", "pollInternal", "()Ljava/lang/Object;", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "updateBufferSize", "(I)Lkotlinx/coroutines/internal/Symbol;", "", "buffer", "[Ljava/lang/Object;", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferDebugString", "I", "head", "isBufferAlwaysEmpty", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isClosedForReceive", "isEmpty", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/AbstractChannel;"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: ArrayChannel.kt */
public class ArrayChannel<E> extends AbstractChannel<E> {
    private Object[] buffer;
    private final int capacity;
    private int head;
    private final ReentrantLock lock;
    private final BufferOverflow onBufferOverflow;
    private volatile /* synthetic */ int size;

    @Metadata(mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
    /* compiled from: ArrayChannel.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean isBufferAlwaysEmpty() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean isBufferAlwaysFull() {
        return false;
    }

    public ArrayChannel(int i, BufferOverflow bufferOverflow, Function1<? super E, Unit> function1) {
        super(function1);
        this.capacity = i;
        this.onBufferOverflow = bufferOverflow;
        if (i < 1 ? false : true) {
            this.lock = new ReentrantLock();
            Object[] objArr = new Object[Math.min(i, 8)];
            ArraysKt.fill$default(objArr, (Object) AbstractChannelKt.EMPTY, 0, 0, 6, (Object) null);
            Unit unit = Unit.INSTANCE;
            this.buffer = objArr;
            this.size = 0;
            return;
        }
        throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + i + " was specified").toString());
    }

    /* access modifiers changed from: protected */
    public final boolean isBufferEmpty() {
        return this.size == 0;
    }

    /* access modifiers changed from: protected */
    public final boolean isBufferFull() {
        return this.size == this.capacity && this.onBufferOverflow == BufferOverflow.SUSPEND;
    }

    public boolean isEmpty() {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            return isEmptyImpl();
        } finally {
            lock2.unlock();
        }
    }

    public boolean isClosedForReceive() {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            lock2.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public Object offerInternal(E e) {
        ReceiveOrClosed takeFirstReceiveOrPeekClosed;
        Symbol tryResumeReceive;
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            int i = this.size;
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                Symbol updateBufferSize = updateBufferSize(i);
                if (updateBufferSize == null) {
                    if (i == 0) {
                        do {
                            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                            if (takeFirstReceiveOrPeekClosed != null) {
                                if (takeFirstReceiveOrPeekClosed instanceof Closed) {
                                    this.size = i;
                                    lock2.unlock();
                                    return takeFirstReceiveOrPeekClosed;
                                }
                                tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(e, (LockFreeLinkedListNode.PrepareOp) null);
                            }
                        } while (tryResumeReceive == null);
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        this.size = i;
                        Unit unit = Unit.INSTANCE;
                        lock2.unlock();
                        takeFirstReceiveOrPeekClosed.completeResumeReceive(e);
                        return takeFirstReceiveOrPeekClosed.getOfferResult();
                    }
                    enqueueElement(i, e);
                    Symbol symbol = AbstractChannelKt.OFFER_SUCCESS;
                    lock2.unlock();
                    return symbol;
                }
                lock2.unlock();
                return updateBufferSize;
            }
            lock2.unlock();
            return closedForSend;
        } catch (Throwable th) {
            lock2.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public Object offerSelectInternal(E e, SelectInstance<?> selectInstance) {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            int i = this.size;
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                Symbol updateBufferSize = updateBufferSize(i);
                if (updateBufferSize == null) {
                    if (i == 0) {
                        while (true) {
                            AbstractSendChannel.TryOfferDesc describeTryOffer = describeTryOffer(e);
                            Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(describeTryOffer);
                            if (performAtomicTrySelect == null) {
                                this.size = i;
                                Object result = describeTryOffer.getResult();
                                Unit unit = Unit.INSTANCE;
                                lock2.unlock();
                                Intrinsics.checkNotNull(result);
                                ReceiveOrClosed receiveOrClosed = (ReceiveOrClosed) result;
                                receiveOrClosed.completeResumeReceive(e);
                                return receiveOrClosed.getOfferResult();
                            } else if (performAtomicTrySelect == AbstractChannelKt.OFFER_FAILED) {
                                break;
                            } else if (performAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                                if (performAtomicTrySelect != SelectKt.getALREADY_SELECTED()) {
                                    if (!(performAtomicTrySelect instanceof Closed)) {
                                        throw new IllegalStateException(Intrinsics.stringPlus("performAtomicTrySelect(describeTryOffer) returned ", performAtomicTrySelect).toString());
                                    }
                                }
                                this.size = i;
                                return performAtomicTrySelect;
                            }
                        }
                    }
                    if (!selectInstance.trySelect()) {
                        this.size = i;
                        Object already_selected = SelectKt.getALREADY_SELECTED();
                        lock2.unlock();
                        return already_selected;
                    }
                    enqueueElement(i, e);
                    Symbol symbol = AbstractChannelKt.OFFER_SUCCESS;
                    lock2.unlock();
                    return symbol;
                }
                lock2.unlock();
                return updateBufferSize;
            }
            lock2.unlock();
            return closedForSend;
        } finally {
            lock2.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public Object enqueueSend(Send send) {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            return super.enqueueSend(send);
        } finally {
            lock2.unlock();
        }
    }

    private final Symbol updateBufferSize(int i) {
        if (i < this.capacity) {
            this.size = i + 1;
            return null;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()];
        if (i2 == 1) {
            return AbstractChannelKt.OFFER_FAILED;
        }
        if (i2 == 2) {
            return AbstractChannelKt.OFFER_SUCCESS;
        }
        if (i2 == 3) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final void enqueueElement(int i, E e) {
        if (i < this.capacity) {
            ensureCapacity(i);
            Object[] objArr = this.buffer;
            objArr[(this.head + i) % objArr.length] = e;
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.onBufferOverflow == BufferOverflow.DROP_OLDEST)) {
                throw new AssertionError();
            }
        }
        Object[] objArr2 = this.buffer;
        int i2 = this.head;
        objArr2[i2 % objArr2.length] = null;
        objArr2[(i + i2) % objArr2.length] = e;
        this.head = (i2 + 1) % objArr2.length;
    }

    private final void ensureCapacity(int i) {
        Object[] objArr = this.buffer;
        if (i >= objArr.length) {
            int min = Math.min(objArr.length * 2, this.capacity);
            Object[] objArr2 = new Object[min];
            if (i > 0) {
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    Object[] objArr3 = this.buffer;
                    objArr2[i2] = objArr3[(this.head + i2) % objArr3.length];
                    if (i3 >= i) {
                        break;
                    }
                    i2 = i3;
                }
            }
            ArraysKt.fill((T[]) objArr2, AbstractChannelKt.EMPTY, i, min);
            this.buffer = objArr2;
            this.head = 0;
        }
    }

    /* access modifiers changed from: protected */
    public Object pollInternal() {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            int i = this.size;
            if (i == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.buffer;
            int i2 = this.head;
            Object obj = objArr[i2];
            Send send = null;
            objArr[i2] = null;
            this.size = i - 1;
            Object obj2 = AbstractChannelKt.POLL_FAILED;
            boolean z = false;
            if (i == this.capacity) {
                Send send2 = null;
                while (true) {
                    Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (takeFirstSendOrPeekClosed == null) {
                        send = send2;
                        break;
                    }
                    Symbol tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend((LockFreeLinkedListNode.PrepareOp) null);
                    if (tryResumeSend != null) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN) {
                                z = true;
                            }
                            if (!z) {
                                throw new AssertionError();
                            }
                        }
                        obj2 = takeFirstSendOrPeekClosed.getPollResult();
                        send = takeFirstSendOrPeekClosed;
                        z = true;
                    } else {
                        takeFirstSendOrPeekClosed.undeliveredElement();
                        send2 = takeFirstSendOrPeekClosed;
                    }
                }
            }
            if (obj2 != AbstractChannelKt.POLL_FAILED && !(obj2 instanceof Closed)) {
                this.size = i;
                Object[] objArr2 = this.buffer;
                objArr2[(this.head + i) % objArr2.length] = obj2;
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit = Unit.INSTANCE;
            lock2.unlock();
            if (z) {
                Intrinsics.checkNotNull(send);
                send.completeResumeSend();
            }
            return obj;
        } finally {
            lock2.unlock();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        if (r7 != kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0058, code lost:
        r8.size = r1;
        r8.buffer[r8.head] = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0060, code lost:
        r0.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0063, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if ((r7 instanceof kotlinx.coroutines.channels.Closed) == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0068, code lost:
        r2 = r7;
        r5 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007c, code lost:
        throw new java.lang.IllegalStateException(kotlin.jvm.internal.Intrinsics.stringPlus("performAtomicTrySelect(describeTryOffer) returned ", r7).toString());
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0086 A[Catch:{ all -> 0x00c1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0092 A[Catch:{ all -> 0x00c1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object pollSelectInternal(kotlinx.coroutines.selects.SelectInstance<?> r9) {
        /*
            r8 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r8.lock
            java.util.concurrent.locks.Lock r0 = (java.util.concurrent.locks.Lock) r0
            r0.lock()
            int r1 = r8.size     // Catch:{ all -> 0x00c1 }
            if (r1 != 0) goto L_0x0017
            kotlinx.coroutines.channels.Closed r9 = r8.getClosedForSend()     // Catch:{ all -> 0x00c1 }
            if (r9 != 0) goto L_0x0013
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch:{ all -> 0x00c1 }
        L_0x0013:
            r0.unlock()
            return r9
        L_0x0017:
            java.lang.Object[] r2 = r8.buffer     // Catch:{ all -> 0x00c1 }
            int r3 = r8.head     // Catch:{ all -> 0x00c1 }
            r4 = r2[r3]     // Catch:{ all -> 0x00c1 }
            r5 = 0
            r2[r3] = r5     // Catch:{ all -> 0x00c1 }
            int r2 = r1 + -1
            r8.size = r2     // Catch:{ all -> 0x00c1 }
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch:{ all -> 0x00c1 }
            int r3 = r8.capacity     // Catch:{ all -> 0x00c1 }
            r6 = 1
            if (r1 != r3) goto L_0x007d
        L_0x002b:
            kotlinx.coroutines.channels.AbstractChannel$TryPollDesc r3 = r8.describeTryPoll()     // Catch:{ all -> 0x00c1 }
            r7 = r3
            kotlinx.coroutines.internal.AtomicDesc r7 = (kotlinx.coroutines.internal.AtomicDesc) r7     // Catch:{ all -> 0x00c1 }
            java.lang.Object r7 = r9.performAtomicTrySelect(r7)     // Catch:{ all -> 0x00c1 }
            if (r7 != 0) goto L_0x0048
            java.lang.Object r5 = r3.getResult()     // Catch:{ all -> 0x00c1 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ all -> 0x00c1 }
            r2 = r5
            kotlinx.coroutines.channels.Send r2 = (kotlinx.coroutines.channels.Send) r2     // Catch:{ all -> 0x00c1 }
            java.lang.Object r2 = r2.getPollResult()     // Catch:{ all -> 0x00c1 }
        L_0x0046:
            r3 = 1
            goto L_0x007e
        L_0x0048:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch:{ all -> 0x00c1 }
            if (r7 != r3) goto L_0x004d
            goto L_0x007d
        L_0x004d:
            java.lang.Object r3 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC     // Catch:{ all -> 0x00c1 }
            if (r7 != r3) goto L_0x0052
            goto L_0x002b
        L_0x0052:
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch:{ all -> 0x00c1 }
            if (r7 != r2) goto L_0x0064
            r8.size = r1     // Catch:{ all -> 0x00c1 }
            java.lang.Object[] r9 = r8.buffer     // Catch:{ all -> 0x00c1 }
            int r1 = r8.head     // Catch:{ all -> 0x00c1 }
            r9[r1] = r4     // Catch:{ all -> 0x00c1 }
            r0.unlock()
            return r7
        L_0x0064:
            boolean r2 = r7 instanceof kotlinx.coroutines.channels.Closed     // Catch:{ all -> 0x00c1 }
            if (r2 == 0) goto L_0x006b
            r2 = r7
            r5 = r2
            goto L_0x0046
        L_0x006b:
            java.lang.String r9 = "performAtomicTrySelect(describeTryOffer) returned "
            java.lang.String r9 = kotlin.jvm.internal.Intrinsics.stringPlus(r9, r7)     // Catch:{ all -> 0x00c1 }
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00c1 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00c1 }
            r1.<init>(r9)     // Catch:{ all -> 0x00c1 }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x00c1 }
            throw r1     // Catch:{ all -> 0x00c1 }
        L_0x007d:
            r3 = 0
        L_0x007e:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch:{ all -> 0x00c1 }
            if (r2 == r7) goto L_0x0092
            boolean r7 = r2 instanceof kotlinx.coroutines.channels.Closed     // Catch:{ all -> 0x00c1 }
            if (r7 != 0) goto L_0x0092
            r8.size = r1     // Catch:{ all -> 0x00c1 }
            java.lang.Object[] r9 = r8.buffer     // Catch:{ all -> 0x00c1 }
            int r7 = r8.head     // Catch:{ all -> 0x00c1 }
            int r7 = r7 + r1
            int r1 = r9.length     // Catch:{ all -> 0x00c1 }
            int r7 = r7 % r1
            r9[r7] = r2     // Catch:{ all -> 0x00c1 }
            goto L_0x00a8
        L_0x0092:
            boolean r9 = r9.trySelect()     // Catch:{ all -> 0x00c1 }
            if (r9 != 0) goto L_0x00a8
            r8.size = r1     // Catch:{ all -> 0x00c1 }
            java.lang.Object[] r9 = r8.buffer     // Catch:{ all -> 0x00c1 }
            int r1 = r8.head     // Catch:{ all -> 0x00c1 }
            r9[r1] = r4     // Catch:{ all -> 0x00c1 }
            java.lang.Object r9 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch:{ all -> 0x00c1 }
            r0.unlock()
            return r9
        L_0x00a8:
            int r9 = r8.head     // Catch:{ all -> 0x00c1 }
            int r9 = r9 + r6
            java.lang.Object[] r1 = r8.buffer     // Catch:{ all -> 0x00c1 }
            int r1 = r1.length     // Catch:{ all -> 0x00c1 }
            int r9 = r9 % r1
            r8.head = r9     // Catch:{ all -> 0x00c1 }
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00c1 }
            r0.unlock()
            if (r3 == 0) goto L_0x00c0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            kotlinx.coroutines.channels.Send r5 = (kotlinx.coroutines.channels.Send) r5
            r5.completeResumeSend()
        L_0x00c0:
            return r4
        L_0x00c1:
            r9 = move-exception
            r0.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.pollSelectInternal(kotlinx.coroutines.selects.SelectInstance):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public boolean enqueueReceiveInternal(Receive<? super E> receive) {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            lock2.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void onCancelIdempotent(boolean z) {
        Function1 function1 = this.onUndeliveredElement;
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            int i = this.size;
            UndeliveredElementException undeliveredElementException = null;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = this.buffer[this.head];
                if (!(function1 == null || obj == AbstractChannelKt.EMPTY)) {
                    undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
                }
                this.buffer[this.head] = AbstractChannelKt.EMPTY;
                this.head = (this.head + 1) % this.buffer.length;
            }
            this.size = 0;
            Unit unit = Unit.INSTANCE;
            lock2.unlock();
            super.onCancelIdempotent(z);
            if (undeliveredElementException != null) {
                throw undeliveredElementException;
            }
        } catch (Throwable th) {
            lock2.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public String getBufferDebugString() {
        return "(buffer:capacity=" + this.capacity + ",size=" + this.size + ')';
    }
}
