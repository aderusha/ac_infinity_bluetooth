package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class AggregateFuture<InputT, OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
    /* access modifiers changed from: private */
    @NullableDecl
    public AggregateFuture<InputT, OutputT>.RunningState runningState;

    AggregateFuture() {
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        super.afterDone();
        AggregateFuture<InputT, OutputT>.RunningState runningState2 = this.runningState;
        if (runningState2 != null) {
            this.runningState = null;
            ImmutableCollection access$000 = runningState2.futures;
            boolean wasInterrupted = wasInterrupted();
            if (wasInterrupted) {
                runningState2.interruptTask();
            }
            if (isCancelled() && (access$000 != null)) {
                UnmodifiableIterator it = access$000.iterator();
                while (it.hasNext()) {
                    ((ListenableFuture) it.next()).cancel(wasInterrupted);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String pendingToString() {
        ImmutableCollection access$000;
        AggregateFuture<InputT, OutputT>.RunningState runningState2 = this.runningState;
        if (runningState2 == null || (access$000 = runningState2.futures) == null) {
            return null;
        }
        return "futures=[" + access$000 + "]";
    }

    /* access modifiers changed from: package-private */
    public final void init(AggregateFuture<InputT, OutputT>.RunningState runningState2) {
        this.runningState = runningState2;
        runningState2.init();
    }

    abstract class RunningState extends AggregateFutureState implements Runnable {
        private final boolean allMustSucceed;
        private final boolean collectsValues;
        /* access modifiers changed from: private */
        public ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;

        /* access modifiers changed from: package-private */
        public abstract void collectOneValue(boolean z, int i, @NullableDecl InputT inputt);

        /* access modifiers changed from: package-private */
        public abstract void handleAllCompleted();

        /* access modifiers changed from: package-private */
        public void interruptTask() {
        }

        RunningState(ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection, boolean z, boolean z2) {
            super(immutableCollection.size());
            this.futures = (ImmutableCollection) Preconditions.checkNotNull(immutableCollection);
            this.allMustSucceed = z;
            this.collectsValues = z2;
        }

        public final void run() {
            decrementCountAndMaybeComplete();
        }

        /* access modifiers changed from: private */
        public void init() {
            if (this.futures.isEmpty()) {
                handleAllCompleted();
            } else if (this.allMustSucceed) {
                final int i = 0;
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
                while (it.hasNext()) {
                    final ListenableFuture listenableFuture = (ListenableFuture) it.next();
                    listenableFuture.addListener(new Runnable() {
                        public void run() {
                            try {
                                RunningState.this.handleOneInputDone(i, listenableFuture);
                            } finally {
                                RunningState.this.decrementCountAndMaybeComplete();
                            }
                        }
                    }, MoreExecutors.directExecutor());
                    i++;
                }
            } else {
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it2 = this.futures.iterator();
                while (it2.hasNext()) {
                    ((ListenableFuture) it2.next()).addListener(this, MoreExecutors.directExecutor());
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0029  */
        /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void handleException(java.lang.Throwable r6) {
            /*
                r5 = this;
                com.google.common.base.Preconditions.checkNotNull(r6)
                boolean r0 = r5.allMustSucceed
                r1 = 1
                if (r0 == 0) goto L_0x001d
                com.google.common.util.concurrent.AggregateFuture r0 = com.google.common.util.concurrent.AggregateFuture.this
                boolean r0 = r0.setException(r6)
                if (r0 == 0) goto L_0x0014
                r5.releaseResourcesAfterFailure()
                goto L_0x001e
            L_0x0014:
                java.util.Set r2 = r5.getOrInitSeenExceptions()
                boolean r2 = com.google.common.util.concurrent.AggregateFuture.addCausalChain(r2, r6)
                goto L_0x001f
            L_0x001d:
                r0 = 0
            L_0x001e:
                r2 = 1
            L_0x001f:
                boolean r3 = r6 instanceof java.lang.Error
                boolean r4 = r5.allMustSucceed
                r0 = r0 ^ r1
                r0 = r0 & r4
                r0 = r0 & r2
                r0 = r0 | r3
                if (r0 == 0) goto L_0x0039
                if (r3 == 0) goto L_0x002e
                java.lang.String r0 = "Input Future failed with Error"
                goto L_0x0030
            L_0x002e:
                java.lang.String r0 = "Got more than one input Future failure. Logging failures after the first"
            L_0x0030:
                java.util.logging.Logger r1 = com.google.common.util.concurrent.AggregateFuture.logger
                java.util.logging.Level r2 = java.util.logging.Level.SEVERE
                r1.log(r2, r0, r6)
            L_0x0039:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AggregateFuture.RunningState.handleException(java.lang.Throwable):void");
        }

        /* access modifiers changed from: package-private */
        public final void addInitialException(Set<Throwable> set) {
            if (!AggregateFuture.this.isCancelled()) {
                boolean unused = AggregateFuture.addCausalChain(set, AggregateFuture.this.tryInternalFastPathGetFailure());
            }
        }

        /* access modifiers changed from: private */
        public void handleOneInputDone(int i, Future<? extends InputT> future) {
            Preconditions.checkState(this.allMustSucceed || !AggregateFuture.this.isDone() || AggregateFuture.this.isCancelled(), "Future was done before all dependencies completed");
            try {
                Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
                if (this.allMustSucceed) {
                    if (future.isCancelled()) {
                        RunningState unused = AggregateFuture.this.runningState = null;
                        AggregateFuture.this.cancel(false);
                        return;
                    }
                    Object done = Futures.getDone(future);
                    if (this.collectsValues) {
                        collectOneValue(this.allMustSucceed, i, done);
                    }
                } else if (this.collectsValues && !future.isCancelled()) {
                    collectOneValue(this.allMustSucceed, i, Futures.getDone(future));
                }
            } catch (ExecutionException e) {
                handleException(e.getCause());
            } catch (Throwable th) {
                handleException(th);
            }
        }

        /* access modifiers changed from: private */
        public void decrementCountAndMaybeComplete() {
            int decrementRemainingAndGet = decrementRemainingAndGet();
            Preconditions.checkState(decrementRemainingAndGet >= 0, "Less than 0 remaining futures");
            if (decrementRemainingAndGet == 0) {
                processCompleted();
            }
        }

        private void processCompleted() {
            if (this.collectsValues && (!this.allMustSucceed)) {
                int i = 0;
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
                while (it.hasNext()) {
                    handleOneInputDone(i, (ListenableFuture) it.next());
                    i++;
                }
            }
            handleAllCompleted();
        }

        /* access modifiers changed from: package-private */
        public void releaseResourcesAfterFailure() {
            this.futures = null;
        }
    }

    /* access modifiers changed from: private */
    public static boolean addCausalChain(Set<Throwable> set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }
}
