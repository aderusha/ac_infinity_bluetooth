package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final float LOAD_FACTOR = 1.0f;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    @MonotonicNonNullDecl
    transient Object[] elements;
    /* access modifiers changed from: private */
    @MonotonicNonNullDecl
    public transient long[] entries;
    transient int modCount;
    private transient int size;
    @MonotonicNonNullDecl
    private transient int[] table;

    /* access modifiers changed from: private */
    public static int getHash(long j) {
        return (int) (j >>> 32);
    }

    private static int getNext(long j) {
        return (int) j;
    }

    private static long swapNext(long j, int i) {
        return (j & HASH_MASK) | (((long) i) & NEXT_MASK);
    }

    /* access modifiers changed from: package-private */
    public int adjustAfterRemove(int i, int i2) {
        return i - 1;
    }

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    public static <E> CompactHashSet<E> create(E... eArr) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int i) {
        return new CompactHashSet<>(i);
    }

    CompactHashSet() {
        init(3);
    }

    CompactHashSet(int i) {
        init(i);
    }

    /* access modifiers changed from: package-private */
    public void init(int i) {
        Preconditions.checkArgument(i >= 0, "Initial capacity must be non-negative");
        this.modCount = Math.max(1, i);
    }

    /* access modifiers changed from: package-private */
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    /* access modifiers changed from: package-private */
    public void allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int i = this.modCount;
        this.table = newTable(Hashing.closedTableSize(i, 1.0d));
        this.entries = newEntries(i);
        this.elements = new Object[i];
    }

    private static int[] newTable(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private static long[] newEntries(int i) {
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1);
        return jArr;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    public boolean add(@NullableDecl E e) {
        if (needsAllocArrays()) {
            allocArrays();
        }
        long[] jArr = this.entries;
        Object[] objArr = this.elements;
        int smearedHash = Hashing.smearedHash(e);
        int hashTableMask = hashTableMask() & smearedHash;
        int i = this.size;
        int[] iArr = this.table;
        int i2 = iArr[hashTableMask];
        if (i2 == -1) {
            iArr[hashTableMask] = i;
        } else {
            while (true) {
                long j = jArr[i2];
                if (getHash(j) == smearedHash && Objects.equal(e, objArr[i2])) {
                    return false;
                }
                int next = getNext(j);
                if (next == -1) {
                    jArr[i2] = swapNext(j, i);
                    break;
                }
                i2 = next;
            }
        }
        if (i != Integer.MAX_VALUE) {
            int i3 = i + 1;
            resizeMeMaybe(i3);
            insertEntry(i, e, smearedHash);
            this.size = i3;
            int length = this.table.length;
            if (Hashing.needsResizing(i, length, 1.0d)) {
                resizeTable(length * 2);
            }
            this.modCount++;
            return true;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    /* access modifiers changed from: package-private */
    public void insertEntry(int i, E e, int i2) {
        this.entries[i] = (((long) i2) << 32) | NEXT_MASK;
        this.elements[i] = e;
    }

    private void resizeMeMaybe(int i) {
        int length = this.entries.length;
        if (i > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = Integer.MAX_VALUE;
            }
            if (max != length) {
                resizeEntries(max);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resizeEntries(int i) {
        this.elements = Arrays.copyOf(this.elements, i);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1);
        }
        this.entries = copyOf;
    }

    private void resizeTable(int i) {
        int[] newTable = newTable(i);
        long[] jArr = this.entries;
        int length = newTable.length - 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            int hash = getHash(jArr[i2]);
            int i3 = hash & length;
            int i4 = newTable[i3];
            newTable[i3] = i2;
            jArr[i2] = (((long) hash) << 32) | (((long) i4) & NEXT_MASK);
        }
        this.table = newTable;
    }

    public boolean contains(@NullableDecl Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        int smearedHash = Hashing.smearedHash(obj);
        int i = this.table[hashTableMask() & smearedHash];
        while (i != -1) {
            long j = this.entries[i];
            if (getHash(j) == smearedHash && Objects.equal(obj, this.elements[i])) {
                return true;
            }
            i = getNext(j);
        }
        return false;
    }

    public boolean remove(@NullableDecl Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        return remove(obj, Hashing.smearedHash(obj));
    }

    /* access modifiers changed from: private */
    public boolean remove(Object obj, int i) {
        int hashTableMask = hashTableMask() & i;
        int i2 = this.table[hashTableMask];
        if (i2 == -1) {
            return false;
        }
        int i3 = -1;
        while (true) {
            if (getHash(this.entries[i2]) != i || !Objects.equal(obj, this.elements[i2])) {
                int next = getNext(this.entries[i2]);
                if (next == -1) {
                    return false;
                }
                int i4 = next;
                i3 = i2;
                i2 = i4;
            } else {
                if (i3 == -1) {
                    this.table[hashTableMask] = getNext(this.entries[i2]);
                } else {
                    long[] jArr = this.entries;
                    jArr[i3] = swapNext(jArr[i3], getNext(jArr[i2]));
                }
                moveLastEntry(i2);
                this.size--;
                this.modCount++;
                return true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void moveLastEntry(int i) {
        int size2 = size() - 1;
        if (i < size2) {
            Object[] objArr = this.elements;
            objArr[i] = objArr[size2];
            objArr[size2] = null;
            long[] jArr = this.entries;
            long j = jArr[size2];
            jArr[i] = j;
            jArr[size2] = -1;
            int hash = getHash(j) & hashTableMask();
            int[] iArr = this.table;
            int i2 = iArr[hash];
            if (i2 == size2) {
                iArr[hash] = i;
                return;
            }
            while (true) {
                long j2 = this.entries[i2];
                int next = getNext(j2);
                if (next == size2) {
                    this.entries[i2] = swapNext(j2, i);
                    return;
                }
                i2 = next;
            }
        } else {
            this.elements[i] = null;
            this.entries[i] = -1;
        }
    }

    /* access modifiers changed from: package-private */
    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public int getSuccessor(int i) {
        int i2 = i + 1;
        if (i2 < this.size) {
            return i2;
        }
        return -1;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int currentIndex;
            int expectedModCount;
            int indexToRemove = -1;

            {
                this.expectedModCount = CompactHashSet.this.modCount;
                this.currentIndex = CompactHashSet.this.firstEntryIndex();
            }

            public boolean hasNext() {
                return this.currentIndex >= 0;
            }

            public E next() {
                checkForConcurrentModification();
                if (hasNext()) {
                    this.indexToRemove = this.currentIndex;
                    E[] eArr = CompactHashSet.this.elements;
                    int i = this.currentIndex;
                    E e = eArr[i];
                    this.currentIndex = CompactHashSet.this.getSuccessor(i);
                    return e;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                this.expectedModCount++;
                CompactHashSet compactHashSet = CompactHashSet.this;
                boolean unused = compactHashSet.remove(compactHashSet.elements[this.indexToRemove], CompactHashSet.getHash(CompactHashSet.this.entries[this.indexToRemove]));
                this.currentIndex = CompactHashSet.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
                this.indexToRemove = -1;
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Object[] toArray() {
        if (needsAllocArrays()) {
            return new Object[0];
        }
        return Arrays.copyOf(this.elements, this.size);
    }

    public <T> T[] toArray(T[] tArr) {
        if (!needsAllocArrays()) {
            return ObjectArrays.toArrayImpl(this.elements, 0, this.size, tArr);
        }
        if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    public void trimToSize() {
        if (!needsAllocArrays()) {
            int i = this.size;
            if (i < this.entries.length) {
                resizeEntries(i);
            }
            int closedTableSize = Hashing.closedTableSize(i, 1.0d);
            if (closedTableSize < this.table.length) {
                resizeTable(closedTableSize);
            }
        }
    }

    public void clear() {
        if (!needsAllocArrays()) {
            this.modCount++;
            Arrays.fill(this.elements, 0, this.size, (Object) null);
            Arrays.fill(this.table, -1);
            Arrays.fill(this.entries, 0, this.size, -1);
            this.size = 0;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.size);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            objectOutputStream.writeObject(this.elements[firstEntryIndex]);
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            init(readInt);
            for (int i = 0; i < readInt; i++) {
                add(objectInputStream.readObject());
            }
            return;
        }
        throw new InvalidObjectException("Invalid size: " + readInt);
    }
}
