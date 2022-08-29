package androidx.recyclerview.widget;

import androidx.collection.LongSparseArray;

interface StableIdStorage {

    public interface StableIdLookup {
        long localToGlobal(long j);
    }

    StableIdLookup createStableIdLookup();

    public static class NoStableIdStorage implements StableIdStorage {
        private final StableIdLookup mNoIdLookup = new StableIdLookup() {
            public long localToGlobal(long j) {
                return -1;
            }
        };

        public StableIdLookup createStableIdLookup() {
            return this.mNoIdLookup;
        }
    }

    public static class SharedPoolStableIdStorage implements StableIdStorage {
        private final StableIdLookup mSameIdLookup = new StableIdLookup() {
            public long localToGlobal(long j) {
                return j;
            }
        };

        public StableIdLookup createStableIdLookup() {
            return this.mSameIdLookup;
        }
    }

    public static class IsolatedStableIdStorage implements StableIdStorage {
        long mNextStableId = 0;

        /* access modifiers changed from: package-private */
        public long obtainId() {
            long j = this.mNextStableId;
            this.mNextStableId = 1 + j;
            return j;
        }

        public StableIdLookup createStableIdLookup() {
            return new WrapperStableIdLookup();
        }

        class WrapperStableIdLookup implements StableIdLookup {
            private final LongSparseArray<Long> mLocalToGlobalLookup = new LongSparseArray<>();

            WrapperStableIdLookup() {
            }

            public long localToGlobal(long j) {
                Long l = this.mLocalToGlobalLookup.get(j);
                if (l == null) {
                    l = Long.valueOf(IsolatedStableIdStorage.this.obtainId());
                    this.mLocalToGlobalLookup.put(j, l);
                }
                return l.longValue();
            }
        }
    }
}
