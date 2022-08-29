package com.eternal.data.p007ui;

import androidx.paging.PagedList;
import com.eternal.base.concat.TmpHum;
import com.eternal.framework.utils.KLog;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import p014io.reactivex.BackpressureStrategy;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableEmitter;
import p014io.reactivex.FlowableOnSubscribe;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.functions.Function;
import p014io.reactivex.processors.PublishProcessor;
import p014io.reactivex.schedulers.Schedulers;

/* renamed from: com.eternal.data.ui.DiffUtils */
public class DiffUtils extends PagedList.Callback {
    /* access modifiers changed from: private */
    public FlowableEmitter<Event> eventEmitter;
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.MINUTES, new LinkedBlockingDeque(2), new ThreadFactoryBuilder().setNameFormat("diff-poll-%d").build());
    /* access modifiers changed from: private */
    public boolean isAll;
    /* access modifiers changed from: private */
    public boolean isRemove;
    /* access modifiers changed from: private */
    public Listener listener;
    /* access modifiers changed from: private */
    public PagedList<TmpHum> now;
    /* access modifiers changed from: private */
    public int oldSize;
    private PublishProcessor<Event> processor;
    /* access modifiers changed from: private */
    public FlowableEmitter<Boolean> resultEmitter;
    /* access modifiers changed from: private */
    public int start;
    /* access modifiers changed from: private */
    public long startTime;

    /* renamed from: com.eternal.data.ui.DiffUtils$Listener */
    public interface Listener {
        void onEnd(PagedList<TmpHum> pagedList, int i, boolean z);
    }

    public void onChanged(int i, int i2) {
    }

    static /* synthetic */ int access$204(DiffUtils diffUtils) {
        int i = diffUtils.start + 1;
        diffUtils.start = i;
        return i;
    }

    public DiffUtils() {
        PublishProcessor<Event> create = PublishProcessor.create();
        this.processor = create;
        create.onBackpressureLatest().subscribeOn(Schedulers.m983io()).flatMap(new Function<Event, Publisher<Boolean>>() {
            public Publisher<Boolean> apply(Event event) throws Exception {
                boolean unused = DiffUtils.this.isRemove = false;
                int unused2 = DiffUtils.this.start = 0;
                PagedList unused3 = DiffUtils.this.now = event.now;
                boolean unused4 = DiffUtils.this.isAll = false;
                int unused5 = DiffUtils.this.oldSize = event.old.size();
                if (DiffUtils.this.oldSize == 0) {
                    return Flowable.just(true);
                }
                long unused6 = DiffUtils.this.startTime = event.old.get(0).time;
                DiffUtils.this.now.addWeakCallback((List) null, DiffUtils.this);
                return Flowable.create(new FlowableOnSubscribe<Boolean>() {
                    public void subscribe(FlowableEmitter<Boolean> flowableEmitter) {
                        FlowableEmitter unused = DiffUtils.this.resultEmitter = flowableEmitter;
                        if (DiffUtils.this.now.size() < DiffUtils.this.oldSize) {
                            boolean unused2 = DiffUtils.this.isRemove = true;
                            int unused3 = DiffUtils.this.start = DiffUtils.this.oldSize - DiffUtils.this.now.size();
                            DiffUtils.this.resultEmitter.onNext(true);
                            DiffUtils.this.resultEmitter.onComplete();
                            return;
                        }
                        boolean unused4 = DiffUtils.this.isRemove = false;
                        while (true) {
                            if (DiffUtils.this.start >= DiffUtils.this.now.size()) {
                                break;
                            } else if (DiffUtils.this.getItem(DiffUtils.this.start).time <= DiffUtils.this.startTime) {
                                boolean unused5 = DiffUtils.this.isAll = true;
                                break;
                            } else {
                                DiffUtils.access$204(DiffUtils.this);
                            }
                        }
                        if (!DiffUtils.this.isAll) {
                            return;
                        }
                        if (DiffUtils.this.now.size() < DiffUtils.this.start + DiffUtils.this.oldSize) {
                            TmpHum unused6 = DiffUtils.this.getItem(DiffUtils.this.now.size() - 1);
                            return;
                        }
                        DiffUtils.this.resultEmitter.onNext(true);
                        DiffUtils.this.resultEmitter.onComplete();
                    }
                }, BackpressureStrategy.LATEST);
            }
        }).subscribe(new FlowableSubscriber<Boolean>() {
            Subscription sub;

            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(Subscription subscription) {
                this.sub = subscription;
                subscription.request(1);
            }

            public void onNext(Boolean bool) {
                if (DiffUtils.this.listener != null) {
                    DiffUtils.this.listener.onEnd(DiffUtils.this.now, DiffUtils.this.start, DiffUtils.this.isRemove);
                }
                this.sub.request(1);
            }
        });
        Flowable.create(new FlowableOnSubscribe<Event>() {
            public void subscribe(FlowableEmitter<Event> flowableEmitter) {
                FlowableEmitter unused = DiffUtils.this.eventEmitter = flowableEmitter;
            }
        }, BackpressureStrategy.LATEST).subscribeOn(Schedulers.m983io()).flatMap(new Function<Event, Publisher<Boolean>>() {
            public Publisher<Boolean> apply(Event event) throws Exception {
                boolean unused = DiffUtils.this.isRemove = false;
                int unused2 = DiffUtils.this.start = 0;
                PagedList unused3 = DiffUtils.this.now = event.now;
                boolean unused4 = DiffUtils.this.isAll = false;
                long unused5 = DiffUtils.this.startTime = event.old.get(0).time;
                int unused6 = DiffUtils.this.oldSize = event.old.size();
                DiffUtils.this.now.addWeakCallback((List) null, DiffUtils.this);
                return Flowable.create(new FlowableOnSubscribe<Boolean>() {
                    public void subscribe(FlowableEmitter<Boolean> flowableEmitter) {
                        FlowableEmitter unused = DiffUtils.this.resultEmitter = flowableEmitter;
                        if (DiffUtils.this.now.size() < DiffUtils.this.oldSize) {
                            boolean unused2 = DiffUtils.this.isRemove = true;
                            int unused3 = DiffUtils.this.start = DiffUtils.this.oldSize - DiffUtils.this.now.size();
                            DiffUtils.this.resultEmitter.onNext(true);
                            DiffUtils.this.resultEmitter.onComplete();
                            return;
                        }
                        boolean unused4 = DiffUtils.this.isRemove = false;
                        while (true) {
                            if (DiffUtils.this.start >= DiffUtils.this.now.size()) {
                                break;
                            } else if (DiffUtils.this.getItem(DiffUtils.this.start).time <= DiffUtils.this.startTime) {
                                boolean unused5 = DiffUtils.this.isAll = true;
                                break;
                            } else {
                                DiffUtils.access$204(DiffUtils.this);
                            }
                        }
                        if (!DiffUtils.this.isAll) {
                            return;
                        }
                        if (DiffUtils.this.now.size() < DiffUtils.this.start + DiffUtils.this.oldSize) {
                            TmpHum unused6 = DiffUtils.this.getItem(DiffUtils.this.now.size() - 1);
                            return;
                        }
                        DiffUtils.this.resultEmitter.onNext(true);
                        DiffUtils.this.resultEmitter.onComplete();
                    }
                }, BackpressureStrategy.LATEST);
            }
        }).subscribe(new FlowableSubscriber<Boolean>() {
            Subscription sub;

            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(Subscription subscription) {
                this.sub = subscription;
                subscription.request(1);
            }

            public void onNext(Boolean bool) {
                if (DiffUtils.this.listener != null) {
                    DiffUtils.this.listener.onEnd(DiffUtils.this.now, DiffUtils.this.start, DiffUtils.this.isRemove);
                }
                this.sub.request(1);
            }
        });
    }

    public void init(List<TmpHum> list, PagedList<TmpHum> pagedList) {
        FlowableEmitter<Event> flowableEmitter = this.eventEmitter;
        if (flowableEmitter != null) {
            flowableEmitter.onNext(new Event(list, pagedList));
        }
        PublishProcessor<Event> publishProcessor = this.processor;
        if (publishProcessor != null) {
            publishProcessor.onNext(new Event(list, pagedList));
        }
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: private */
    public TmpHum getItem(int i) {
        this.now.loadAround(i);
        return this.now.get(i);
    }

    private void getFirst() {
        this.executor.execute(new Runnable() {
            public void run() {
                while (true) {
                    if (DiffUtils.this.start >= DiffUtils.this.now.size()) {
                        break;
                    }
                    DiffUtils diffUtils = DiffUtils.this;
                    if (diffUtils.getItem(diffUtils.start).time <= DiffUtils.this.startTime) {
                        boolean unused = DiffUtils.this.isAll = true;
                        break;
                    }
                    DiffUtils.access$204(DiffUtils.this);
                }
                if (!DiffUtils.this.isAll) {
                    return;
                }
                if (DiffUtils.this.now.size() < DiffUtils.this.start + DiffUtils.this.oldSize) {
                    DiffUtils diffUtils2 = DiffUtils.this;
                    TmpHum unused2 = diffUtils2.getItem(diffUtils2.now.size() - 1);
                    return;
                }
                DiffUtils.this.resultEmitter.onNext(true);
                DiffUtils.this.resultEmitter.onComplete();
            }
        });
    }

    private void getAll() {
        this.executor.execute(new Runnable() {
            public void run() {
                if (DiffUtils.this.now.size() < DiffUtils.this.start + DiffUtils.this.oldSize) {
                    DiffUtils diffUtils = DiffUtils.this;
                    TmpHum unused = diffUtils.getItem(diffUtils.now.size() - 1);
                    return;
                }
                DiffUtils.this.now.removeWeakCallback(DiffUtils.this);
                DiffUtils.this.resultEmitter.onNext(true);
                DiffUtils.this.resultEmitter.onComplete();
            }
        });
    }

    public void onInserted(int i, int i2) {
        if (this.isAll) {
            getAll();
        } else {
            getFirst();
        }
    }

    public void onRemoved(int i, int i2) {
        KLog.m62d("onRemoved position:" + i + ",count:" + i2);
    }

    /* renamed from: com.eternal.data.ui.DiffUtils$Event */
    private static class Event {
        public final PagedList<TmpHum> now;
        public final List<TmpHum> old;

        public Event(List<TmpHum> list, PagedList<TmpHum> pagedList) {
            this.old = list;
            this.now = pagedList;
        }
    }
}
