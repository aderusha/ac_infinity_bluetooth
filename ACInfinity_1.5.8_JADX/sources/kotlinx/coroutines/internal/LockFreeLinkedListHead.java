package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0007\u001a\u00020\b\"\u000e\b\u0000\u0010\t\u0018\u0001*\u00060\u0001j\u0002`\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\b0\fH\bJ\u0010\u0010\r\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\nH\u0014J\u0006\u0010\u000e\u001a\u00020\u0004J\r\u0010\u000f\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0010R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005¨\u0006\u0011"}, mo27512d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "()V", "isEmpty", "", "()Z", "isRemoved", "forEach", "", "T", "Lkotlinx/coroutines/internal/Node;", "block", "Lkotlin/Function1;", "nextIfRemoved", "remove", "validate", "validate$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: LockFreeLinkedList.kt */
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    public boolean isRemoved() {
        return false;
    }

    /* access modifiers changed from: protected */
    public LockFreeLinkedListNode nextIfRemoved() {
        return null;
    }

    public final boolean isEmpty() {
        return getNext() == this;
    }

    public final /* synthetic */ <T extends LockFreeLinkedListNode> void forEach(Function1<? super T, Unit> function1) {
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) getNext(); !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) this); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                function1.invoke(lockFreeLinkedListNode);
            }
        }
    }

    public final boolean remove() {
        throw new IllegalStateException("head cannot be removed".toString());
    }

    public final void validate$kotlinx_coroutines_core() {
        LockFreeLinkedListNode lockFreeLinkedListNode = this;
        LockFreeLinkedListNode lockFreeLinkedListNode2 = (LockFreeLinkedListNode) getNext();
        while (!Intrinsics.areEqual((Object) lockFreeLinkedListNode2, (Object) this)) {
            LockFreeLinkedListNode nextNode = lockFreeLinkedListNode2.getNextNode();
            lockFreeLinkedListNode2.validateNode$kotlinx_coroutines_core(lockFreeLinkedListNode, nextNode);
            lockFreeLinkedListNode = lockFreeLinkedListNode2;
            lockFreeLinkedListNode2 = nextNode;
        }
        validateNode$kotlinx_coroutines_core(lockFreeLinkedListNode, (LockFreeLinkedListNode) getNext());
    }
}
