package androidx.recyclerview.widget;

import android.util.SparseArray;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;

interface ViewTypeStorage {

    public interface ViewTypeLookup {
        void dispose();

        int globalToLocal(int i);

        int localToGlobal(int i);
    }

    ViewTypeLookup createViewTypeWrapper(NestedAdapterWrapper nestedAdapterWrapper);

    NestedAdapterWrapper getWrapperForGlobalType(int i);

    public static class SharedIdRangeViewTypeStorage implements ViewTypeStorage {
        SparseArray<List<NestedAdapterWrapper>> mGlobalTypeToWrapper = new SparseArray<>();

        public NestedAdapterWrapper getWrapperForGlobalType(int i) {
            List list = this.mGlobalTypeToWrapper.get(i);
            if (list != null && !list.isEmpty()) {
                return (NestedAdapterWrapper) list.get(0);
            }
            throw new IllegalArgumentException("Cannot find the wrapper for global view type " + i);
        }

        public ViewTypeLookup createViewTypeWrapper(NestedAdapterWrapper nestedAdapterWrapper) {
            return new WrapperViewTypeLookup(nestedAdapterWrapper);
        }

        /* access modifiers changed from: package-private */
        public void removeWrapper(NestedAdapterWrapper nestedAdapterWrapper) {
            for (int size = this.mGlobalTypeToWrapper.size() - 1; size >= 0; size--) {
                List valueAt = this.mGlobalTypeToWrapper.valueAt(size);
                if (valueAt.remove(nestedAdapterWrapper) && valueAt.isEmpty()) {
                    this.mGlobalTypeToWrapper.removeAt(size);
                }
            }
        }

        class WrapperViewTypeLookup implements ViewTypeLookup {
            final NestedAdapterWrapper mWrapper;

            public int globalToLocal(int i) {
                return i;
            }

            WrapperViewTypeLookup(NestedAdapterWrapper nestedAdapterWrapper) {
                this.mWrapper = nestedAdapterWrapper;
            }

            public int localToGlobal(int i) {
                List list = SharedIdRangeViewTypeStorage.this.mGlobalTypeToWrapper.get(i);
                if (list == null) {
                    list = new ArrayList();
                    SharedIdRangeViewTypeStorage.this.mGlobalTypeToWrapper.put(i, list);
                }
                if (!list.contains(this.mWrapper)) {
                    list.add(this.mWrapper);
                }
                return i;
            }

            public void dispose() {
                SharedIdRangeViewTypeStorage.this.removeWrapper(this.mWrapper);
            }
        }
    }

    public static class IsolatedViewTypeStorage implements ViewTypeStorage {
        SparseArray<NestedAdapterWrapper> mGlobalTypeToWrapper = new SparseArray<>();
        int mNextViewType = 0;

        /* access modifiers changed from: package-private */
        public int obtainViewType(NestedAdapterWrapper nestedAdapterWrapper) {
            int i = this.mNextViewType;
            this.mNextViewType = i + 1;
            this.mGlobalTypeToWrapper.put(i, nestedAdapterWrapper);
            return i;
        }

        public NestedAdapterWrapper getWrapperForGlobalType(int i) {
            NestedAdapterWrapper nestedAdapterWrapper = this.mGlobalTypeToWrapper.get(i);
            if (nestedAdapterWrapper != null) {
                return nestedAdapterWrapper;
            }
            throw new IllegalArgumentException("Cannot find the wrapper for global view type " + i);
        }

        public ViewTypeLookup createViewTypeWrapper(NestedAdapterWrapper nestedAdapterWrapper) {
            return new WrapperViewTypeLookup(nestedAdapterWrapper);
        }

        /* access modifiers changed from: package-private */
        public void removeWrapper(NestedAdapterWrapper nestedAdapterWrapper) {
            for (int size = this.mGlobalTypeToWrapper.size() - 1; size >= 0; size--) {
                if (this.mGlobalTypeToWrapper.valueAt(size) == nestedAdapterWrapper) {
                    this.mGlobalTypeToWrapper.removeAt(size);
                }
            }
        }

        class WrapperViewTypeLookup implements ViewTypeLookup {
            private SparseIntArray mGlobalToLocalMapping = new SparseIntArray(1);
            private SparseIntArray mLocalToGlobalMapping = new SparseIntArray(1);
            final NestedAdapterWrapper mWrapper;

            WrapperViewTypeLookup(NestedAdapterWrapper nestedAdapterWrapper) {
                this.mWrapper = nestedAdapterWrapper;
            }

            public int localToGlobal(int i) {
                int indexOfKey = this.mLocalToGlobalMapping.indexOfKey(i);
                if (indexOfKey > -1) {
                    return this.mLocalToGlobalMapping.valueAt(indexOfKey);
                }
                int obtainViewType = IsolatedViewTypeStorage.this.obtainViewType(this.mWrapper);
                this.mLocalToGlobalMapping.put(i, obtainViewType);
                this.mGlobalToLocalMapping.put(obtainViewType, i);
                return obtainViewType;
            }

            public int globalToLocal(int i) {
                int indexOfKey = this.mGlobalToLocalMapping.indexOfKey(i);
                if (indexOfKey >= 0) {
                    return this.mGlobalToLocalMapping.valueAt(indexOfKey);
                }
                throw new IllegalStateException("requested global type " + i + " does not belong to the adapter:" + this.mWrapper.adapter);
            }

            public void dispose() {
                IsolatedViewTypeStorage.this.removeWrapper(this.mWrapper);
            }
        }
    }
}
