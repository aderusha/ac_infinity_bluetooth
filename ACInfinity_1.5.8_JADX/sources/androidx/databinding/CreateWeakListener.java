package androidx.databinding;

import java.lang.ref.ReferenceQueue;

interface CreateWeakListener {
    WeakListener create(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue);
}
