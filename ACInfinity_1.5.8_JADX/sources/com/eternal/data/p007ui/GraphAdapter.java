package com.eternal.data.p007ui;

import androidx.paging.PagedList;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.data.p007ui.DiffUtils;
import com.eternal.framework.utils.KLog;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.reactivestreams.Subscription;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.processors.PublishProcessor;
import p014io.reactivex.schedulers.Schedulers;

/* renamed from: com.eternal.data.ui.GraphAdapter */
public class GraphAdapter {
    int aMaxHum;
    int aMaxTmp;
    int aMaxVpd;
    int aMinHum;
    int aMinTmp;
    int aMinVpd;
    int aboveHumScore;
    int aboveTmpScore;
    int aboveVpdScore;
    float avgHum;
    float avgTmp;
    float avgVpd;
    int belowHumScore;
    int belowTmpScore;
    int belowVpdScore;
    private List<Callback> callbacks;
    /* access modifiers changed from: private */
    public PagedList<TmpHum> data;
    long dataMaxTime;
    long dataMinTime;
    long dataTimeDistance;
    /* access modifiers changed from: private */
    public DateFormat format;
    private final boolean isDataDegree;
    /* access modifiers changed from: private */
    public boolean isEmptyData;
    public byte leafTemperatureC;
    int length;
    /* access modifiers changed from: private */
    public PagedList.Callback listener = new PagedList.Callback() {
        public void onChanged(int i, int i2) {
            KLog.m65e("data change position:" + i + " count:" + i2);
        }

        public void onInserted(int i, int i2) {
            boolean unused = GraphAdapter.this.ready = false;
            GraphAdapter.this.processor.onNext(new Space(GraphAdapter.this.minTime, GraphAdapter.this.timeDistance, true));
        }

        public void onRemoved(int i, int i2) {
            KLog.m65e("data removed position:" + i + " count" + i2);
        }
    };
    int maxHum;
    int maxTargetHum;
    int maxTargetTmp;
    int maxTargetVpd;
    int maxTmp;
    int maxVpd;
    int minHum;
    int minTargetHum;
    int minTargetTmp;
    int minTargetVpd;
    long minTime;
    int minTmp;
    int minVpd;
    private byte port;
    int positionOffset;
    /* access modifiers changed from: private */
    public PublishProcessor<Space> processor;
    /* access modifiers changed from: private */
    public boolean ready;
    int size;
    /* access modifiers changed from: private */
    public List<TmpHum> snapshot;
    /* access modifiers changed from: private */
    public TimeCallback timeCallback;
    int timeDistance;
    private DiffUtils util;

    /* renamed from: com.eternal.data.ui.GraphAdapter$Callback */
    public interface Callback {
        void draw();

        void updatePath(int i);
    }

    /* renamed from: com.eternal.data.ui.GraphAdapter$TimeCallback */
    public interface TimeCallback {
        void setData(long j, int i, boolean z, boolean z2, int i2, int i3, int i4, int i5, int i6, int i7, float f, float f2, float f3, int i8, int i9, int i10, int i11, int i12, int i13);

        void setEnd(String str, boolean z);

        void setStart(String str);
    }

    public GraphAdapter(boolean z, byte b) {
        this.isDataDegree = z;
        this.port = b;
        this.ready = false;
        this.callbacks = new ArrayList(4);
        this.format = new SimpleDateFormat("MMM dd, yyyy, h:mm aa", Locale.ENGLISH);
        this.maxTargetTmp = -32768;
        this.minTargetTmp = -32768;
        this.maxTargetHum = -32768;
        this.minTargetHum = -32768;
        this.maxTargetVpd = -32768;
        this.minTargetVpd = -32768;
        initEvent();
        DiffUtils diffUtils = new DiffUtils();
        this.util = diffUtils;
        diffUtils.setListener(new DiffUtils.Listener() {
            public void onEnd(PagedList<TmpHum> pagedList, int i, boolean z) {
                long j;
                PagedList unused = GraphAdapter.this.data = pagedList;
                GraphAdapter.this.size = pagedList.size();
                pagedList.addWeakCallback((List<? extends TmpHum>) null, GraphAdapter.this.listener);
                List unused2 = GraphAdapter.this.snapshot = null;
                if (GraphAdapter.this.size == 0) {
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    GraphAdapter.this.dataMaxTime = currentTimeMillis;
                    GraphAdapter.this.dataMinTime = currentTimeMillis;
                } else {
                    TmpHum access$300 = GraphAdapter.this.getActualItem(0);
                    if (access$300 != null) {
                        GraphAdapter.this.dataMaxTime = access$300.time;
                    }
                    GraphAdapter graphAdapter = GraphAdapter.this;
                    TmpHum access$3002 = graphAdapter.getActualItem(graphAdapter.size - 1);
                    if (access$3002 != null) {
                        GraphAdapter.this.dataMinTime = access$3002.time;
                    }
                }
                GraphAdapter graphAdapter2 = GraphAdapter.this;
                graphAdapter2.dataTimeDistance = graphAdapter2.dataMaxTime - GraphAdapter.this.dataMinTime;
                if (z) {
                    if (GraphAdapter.this.positionOffset > GraphAdapter.this.size) {
                        GraphAdapter graphAdapter3 = GraphAdapter.this;
                        graphAdapter3.positionOffset = graphAdapter3.size - 1;
                    }
                    boolean unused3 = GraphAdapter.this.ready = false;
                    GraphAdapter.this.processor.onNext(new Space(GraphAdapter.this.minTime, GraphAdapter.this.timeDistance, true));
                    return;
                }
                GraphAdapter.this.positionOffset += i;
                if (GraphAdapter.this.size >= 2) {
                    j = GraphAdapter.this.getActualItem(1).time;
                } else {
                    j = GraphAdapter.this.dataMaxTime;
                }
                if (j < GraphAdapter.this.minTime + ((long) GraphAdapter.this.timeDistance)) {
                    boolean unused4 = GraphAdapter.this.ready = false;
                    GraphAdapter.this.processor.onNext(new Space(GraphAdapter.this.minTime, GraphAdapter.this.timeDistance, true));
                    KLog.m68i("refresh history");
                    return;
                }
                KLog.m68i("no refresh history");
            }
        });
    }

    public boolean isDataDegree() {
        return this.isDataDegree;
    }

    private void initEvent() {
        PublishProcessor<Space> create = PublishProcessor.create();
        this.processor = create;
        create.onBackpressureLatest().map(new Function<Space, Integer>() {
            public Integer apply(Space space) throws Exception {
                Space space2 = space;
                boolean z = GraphAdapter.this.dataMaxTime < space2.min + ((long) (space2.distance / 2));
                if (GraphAdapter.this.timeDistance == space2.distance && GraphAdapter.this.minTime == space2.min && !space2.update) {
                    return -1;
                }
                GraphAdapter.this.timeDistance = space2.distance;
                if (GraphAdapter.this.size <= 0 || !z) {
                    GraphAdapter.this.minTime = space2.min;
                } else {
                    GraphAdapter graphAdapter = GraphAdapter.this;
                    graphAdapter.minTime = graphAdapter.dataMaxTime - ((long) (space2.distance / 2));
                }
                int i = GraphAdapter.this.positionOffset + GraphAdapter.this.length;
                GraphAdapter.this.updateSpace();
                if (GraphAdapter.this.timeCallback != null) {
                    GraphAdapter.this.timeCallback.setStart(GraphAdapter.this.format.format(new Date(GraphAdapter.this.minTime * 1000)).toUpperCase());
                    GraphAdapter.this.timeCallback.setEnd(GraphAdapter.this.format.format(new Date((GraphAdapter.this.minTime + ((long) GraphAdapter.this.timeDistance)) * 1000)).toUpperCase(), z);
                    if (GraphAdapter.this.length <= 0 || ((GraphAdapter.this.length < 2 || GraphAdapter.this.isEmptyData) && GraphAdapter.this.isInit())) {
                        GraphAdapter.this.maxTmp = -32768;
                        GraphAdapter.this.aMaxTmp = -32768;
                        GraphAdapter.this.maxHum = -32768;
                        GraphAdapter.this.aMaxHum = -32768;
                        GraphAdapter.this.maxVpd = -32768;
                        GraphAdapter.this.aMaxVpd = -32768;
                        GraphAdapter.this.minTmp = -32768;
                        GraphAdapter.this.aMinTmp = -32768;
                        GraphAdapter.this.minHum = -32768;
                        GraphAdapter.this.aMinHum = -32768;
                        GraphAdapter.this.minVpd = -32768;
                        GraphAdapter.this.aMinVpd = -32768;
                        GraphAdapter.this.aboveTmpScore = -32768;
                        GraphAdapter.this.aboveHumScore = -32768;
                        GraphAdapter.this.aboveVpdScore = -32768;
                        GraphAdapter.this.belowTmpScore = -32768;
                        GraphAdapter.this.belowHumScore = -32768;
                        GraphAdapter.this.belowVpdScore = -32768;
                        GraphAdapter.this.timeCallback.setData(GraphAdapter.this.minTime, GraphAdapter.this.timeDistance, true, z, -32768, -32768, -32768, -32768, -32768, -32768, -32768.0f, -32768.0f, -32768.0f, -32768, -32768, -32768, -32768, -32768, -32768);
                    } else {
                        GraphAdapter.this.timeCallback.setData(GraphAdapter.this.minTime, GraphAdapter.this.timeDistance, false, z, GraphAdapter.this.maxTmp, GraphAdapter.this.minTmp, GraphAdapter.this.maxHum, GraphAdapter.this.minHum, GraphAdapter.this.maxVpd, GraphAdapter.this.minVpd, GraphAdapter.this.avgTmp, GraphAdapter.this.avgHum, GraphAdapter.this.avgVpd, GraphAdapter.this.aboveTmpScore, GraphAdapter.this.belowTmpScore, GraphAdapter.this.aboveHumScore, GraphAdapter.this.belowHumScore, GraphAdapter.this.aboveVpdScore, GraphAdapter.this.belowVpdScore);
                    }
                }
                return Integer.valueOf(i);
            }
        }).subscribeOn(Schedulers.computation()).subscribe(new FlowableSubscriber<Integer>() {
            Subscription sub;

            public void onSubscribe(Subscription subscription) {
                this.sub = subscription;
                subscription.request(1);
            }

            public void onNext(Integer num) {
                if (num.intValue() >= 0) {
                    GraphAdapter.this.draw(num.intValue(), this.sub);
                } else {
                    this.sub.request(1);
                }
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                KLog.m65e(th);
            }

            public void onComplete() {
                KLog.m68i("graph complete");
            }
        });
    }

    /* access modifiers changed from: private */
    public void draw(final int i, final Subscription subscription) {
        Observable.fromIterable(this.callbacks).flatMap(new Function<Callback, ObservableSource<Boolean>>() {
            public ObservableSource<Boolean> apply(Callback callback) throws Exception {
                return Observable.just(callback).map(new Function<Callback, Boolean>() {
                    public Boolean apply(Callback callback) {
                        callback.updatePath(i);
                        callback.draw();
                        return true;
                    }
                }).subscribeOn(Schedulers.m983io());
            }
        }).last(true).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                subscription.request(1);
                boolean unused = GraphAdapter.this.ready = true;
            }
        });
    }

    public void submitList(PagedList<TmpHum> pagedList, boolean z) {
        if (pagedList != null) {
            KLog.m68i("recive new list");
            Iterator it = pagedList.iterator();
            if (z) {
                while (it.hasNext()) {
                    TmpHum tmpHum = (TmpHum) it.next();
                    tmpHum.vpd = Math.round(ProtocolTransformer.getVPD(tmpHum.tmp, tmpHum.hum, this.leafTemperatureC, this.isDataDegree, false) * 100.0f);
                }
            }
            PagedList<TmpHum> pagedList2 = this.data;
            if (pagedList2 != null) {
                this.snapshot = pagedList2.snapshot();
                this.data.removeWeakCallback(this.listener);
                this.data = null;
                this.util.init(this.snapshot, pagedList);
                return;
            }
            List<TmpHum> list = this.snapshot;
            if (list != null) {
                this.util.init(list, pagedList);
                return;
            }
            this.ready = false;
            this.data = pagedList;
            this.size = pagedList.size();
            pagedList.addWeakCallback((List<? extends TmpHum>) null, this.listener);
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            if (this.size == 0) {
                this.dataMaxTime = currentTimeMillis;
                this.dataMinTime = currentTimeMillis;
            } else {
                TmpHum actualItem = getActualItem(0);
                if (actualItem != null) {
                    this.dataMaxTime = actualItem.time;
                }
                TmpHum actualItem2 = getActualItem(this.size - 1);
                if (actualItem2 != null) {
                    this.dataMinTime = actualItem2.time;
                }
            }
            long j = this.dataMaxTime;
            this.dataTimeDistance = j - this.dataMinTime;
            int i = this.timeDistance;
            if (currentTimeMillis < ((long) (i / 2)) + j) {
                j = currentTimeMillis - ((long) i);
            }
            setVisibleTimeSpace(j, i);
        }
    }

    public boolean isReady() {
        return this.ready;
    }

    public boolean isEnd() {
        return this.dataMaxTime - ((long) ((this.timeDistance / 2) * 3)) < this.minTime;
    }

    public long getMinTime() {
        return this.minTime * 1000;
    }

    /* access modifiers changed from: private */
    public TmpHum getActualItem(int i) {
        PagedList<TmpHum> pagedList = this.data;
        if (pagedList == null) {
            return this.snapshot.get(i);
        }
        if (pagedList.size() <= i || i < 0) {
            return null;
        }
        this.data.loadAround(i);
        return this.data.get(i);
    }

    public void addCallback(Callback callback) {
        this.callbacks.add(callback);
    }

    public void setVisibleTimeSpace(long j, int i) {
        if (this.data != null || this.snapshot != null) {
            this.ready = false;
            this.processor.onNext(new Space(j, i, false));
        }
    }

    public void setDistance(int i) {
        if (this.minTime != this.dataMaxTime || i != this.timeDistance) {
            int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            long j = this.dataMaxTime;
            if (((long) currentTimeMillis) < ((long) (i / 2)) + j) {
                j = (long) (currentTimeMillis - i);
            }
            this.minTime = j;
            this.timeDistance = i;
            if (this.data != null) {
                this.processor.onNext(new Space(j, i, true));
            }
        }
    }

    public void setMinTime(int i) {
        KLog.m65e("is ready :" + this.ready);
        long j = this.dataMaxTime;
        int i2 = this.timeDistance;
        long j2 = (long) i;
        if (j - ((long) (i2 / 2)) >= j2) {
            this.minTime = j2;
            if (this.data != null) {
                this.processor.onNext(new Space(j2, i2, true));
            }
        }
    }

    public void setTimeCallback(TimeCallback timeCallback2) {
        this.timeCallback = timeCallback2;
    }

    public void setLeafTemperatureC(byte b) {
        this.leafTemperatureC = b;
    }

    public int getMaxTargetTmp() {
        return this.maxTargetTmp;
    }

    public void setMaxTargetTmp(int i) {
        this.maxTargetTmp = i;
    }

    public int getMinTargetTmp() {
        return this.minTargetTmp;
    }

    public void setMinTargetTmp(int i) {
        this.minTargetTmp = i;
    }

    public int getMaxTargetHum() {
        return this.maxTargetHum;
    }

    public void setMaxTargetHum(int i) {
        this.maxTargetHum = i;
    }

    public int getMinTargetHum() {
        return this.minTargetHum;
    }

    public void setMinTargetHum(int i) {
        this.minTargetHum = i;
    }

    public int getMaxTargetVpd() {
        return this.maxTargetVpd;
    }

    public void setMaxTargetVpd(int i) {
        this.maxTargetVpd = i;
    }

    public int getMinTargetVpd() {
        return this.minTargetVpd;
    }

    public void setMinTargetVpd(int i) {
        this.minTargetVpd = i;
    }

    private void initOffsetAndLength() {
        TmpHum actualItem;
        TmpHum actualItem2;
        long j = (this.minTime / 60) * 60;
        long j2 = ((long) this.timeDistance) + j + 59;
        PagedList<TmpHum> pagedList = this.data;
        if (pagedList == null) {
            this.size = this.snapshot.size();
        } else {
            this.size = pagedList.size();
        }
        if (this.size == 0) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            this.dataMaxTime = currentTimeMillis;
            this.dataMinTime = currentTimeMillis;
        } else {
            TmpHum actualItem3 = getActualItem(0);
            if (actualItem3 != null) {
                this.dataMaxTime = actualItem3.time;
            }
            TmpHum actualItem4 = getActualItem(this.size - 1);
            if (actualItem4 != null) {
                this.dataMinTime = actualItem4.time;
            }
        }
        this.dataTimeDistance = this.dataMaxTime - this.dataMinTime;
        while (true) {
            int i = this.positionOffset;
            if (i < this.size && (actualItem2 = getActualItem(i)) != null && actualItem2.time > j2) {
                this.positionOffset++;
            }
        }
        while (true) {
            int i2 = this.positionOffset;
            if (i2 <= 0 || (actualItem = getActualItem(i2 - 1)) == null || actualItem.time > j2) {
                int i3 = this.positionOffset;
            } else {
                this.positionOffset--;
            }
        }
        int i32 = this.positionOffset;
        if (i32 > 0) {
            this.positionOffset = i32 - 1;
        }
        this.length = 0;
        int i4 = this.positionOffset;
        while (i4 < this.size && (r7 = getActualItem(i4)) != null && r7.time >= j) {
            this.length++;
            i4++;
        }
        int i5 = this.length;
        if (this.positionOffset + i5 < this.size) {
            this.length = i5 + 1;
        }
        TmpHum item = getItem(0);
        TmpHum item2 = getItem(1);
        if (this.length != 2 || item == null || item.time <= j2 || item2 == null || item2.time >= j) {
            this.isEmptyData = false;
        } else {
            this.isEmptyData = true;
        }
    }

    public int getDistance() {
        int i = this.timeDistance;
        if (i < 3600 || i < 21600) {
            return 60;
        }
        if (i < 86400) {
            return 360;
        }
        if (i < 259200) {
            return 720;
        }
        if (i < 604800) {
            return 1800;
        }
        if (i < 1209600) {
            return 3600;
        }
        if (i < 2678400) {
            return 7200;
        }
        if (i < 5356800) {
            return 14400;
        }
        if (i < 10713600) {
            return 28800;
        }
        return i < 21427200 ? 57600 : 86400;
    }

    public int getSize() {
        return this.size;
    }

    public int getLength() {
        return this.length;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x03bb A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x03bc  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0188  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateSpace() {
        /*
            r31 = this;
            r0 = r31
            r31.initOffsetAndLength()
            int r1 = r0.positionOffset
            int r2 = r0.size
            r3 = 1
            int r2 = r2 - r3
            r4 = 0
            if (r1 >= r2) goto L_0x0572
            int r1 = r0.length
            if (r1 > 0) goto L_0x0014
            goto L_0x0572
        L_0x0014:
            int r1 = r31.getDistance()
            long r6 = r0.minTime
            r8 = 60
            long r6 = r6 / r8
            long r6 = r6 * r8
            int r2 = r0.timeDistance
            long r8 = (long) r2
            long r8 = r8 + r6
            r10 = 59
            long r8 = r8 + r10
            com.eternal.base.concat.TmpHum r2 = r0.getItem(r4)
            int r10 = r0.length
            int r10 = r10 - r3
            com.eternal.base.concat.TmpHum r10 = r0.getItem(r10)
            if (r2 == 0) goto L_0x0571
            if (r10 != 0) goto L_0x0037
            goto L_0x0571
        L_0x0037:
            long r10 = r2.time
            int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r12 <= 0) goto L_0x003f
            r10 = 1
            goto L_0x0040
        L_0x003f:
            r10 = 0
        L_0x0040:
            com.eternal.base.concat.TmpHum r11 = r0.getItem(r10)
            if (r11 != 0) goto L_0x0047
            return
        L_0x0047:
            int r12 = r11.getTmpValue()
            r0.minTmp = r12
            char r12 = r11.hum
            r0.minHum = r12
            int r12 = r11.vpd
            r0.minVpd = r12
            int r13 = r0.minTmp
            r0.maxTmp = r13
            int r14 = r0.minHum
            r0.maxHum = r14
            r0.maxVpd = r12
            long r3 = (long) r13
            long r13 = (long) r14
            r17 = r6
            long r5 = (long) r12
            int r7 = r11.tmp
            int r12 = r0.maxTargetTmp
            if (r7 <= r12) goto L_0x006c
            r7 = 1
            goto L_0x0076
        L_0x006c:
            int r7 = r11.tmp
            int r12 = r0.minTargetTmp
            if (r7 >= r12) goto L_0x0075
            r7 = 0
            r12 = 1
            goto L_0x0077
        L_0x0075:
            r7 = 0
        L_0x0076:
            r12 = 0
        L_0x0077:
            char r15 = r11.hum
            r20 = r3
            int r3 = r0.maxTargetHum
            if (r15 <= r3) goto L_0x0081
            r3 = 1
            goto L_0x008b
        L_0x0081:
            char r3 = r11.hum
            int r4 = r0.minTargetHum
            if (r3 >= r4) goto L_0x008a
            r3 = 0
            r4 = 1
            goto L_0x008c
        L_0x008a:
            r3 = 0
        L_0x008b:
            r4 = 0
        L_0x008c:
            int r15 = r11.vpd
            r22 = r3
            int r3 = r0.maxTargetVpd
            if (r15 <= r3) goto L_0x0098
            r3 = 0
            r16 = 1
            goto L_0x00a3
        L_0x0098:
            int r3 = r11.vpd
            int r15 = r0.minTargetVpd
            if (r3 >= r15) goto L_0x00a0
            r3 = 1
            goto L_0x00a1
        L_0x00a0:
            r3 = 0
        L_0x00a1:
            r16 = 0
        L_0x00a3:
            if (r10 == 0) goto L_0x0188
            r24 = r3
            r23 = r4
            long r3 = r2.time
            long r3 = r8 - r3
            float r3 = (float) r3
            r25 = r5
            long r4 = r11.time
            r27 = r7
            long r6 = r2.time
            long r4 = r4 - r6
            float r4 = (float) r4
            float r3 = r3 / r4
            int r4 = r11.getTmpValue()
            int r5 = r2.getTmpValue()
            int r4 = r4 - r5
            float r4 = (float) r4
            float r3 = r3 * r4
            int r4 = r2.getTmpValue()
            float r4 = (float) r4
            float r3 = r3 + r4
            int r3 = (int) r3
            r0.aMinTmp = r3
            long r3 = r2.time
            long r3 = r8 - r3
            float r3 = (float) r3
            long r4 = r11.time
            long r6 = r2.time
            long r4 = r4 - r6
            float r4 = (float) r4
            float r3 = r3 / r4
            char r4 = r11.hum
            char r5 = r2.hum
            int r4 = r4 - r5
            float r4 = (float) r4
            float r3 = r3 * r4
            char r4 = r2.hum
            float r4 = (float) r4
            float r3 = r3 + r4
            int r3 = (int) r3
            r0.aMinHum = r3
            long r3 = r2.time
            long r8 = r8 - r3
            float r3 = (float) r8
            long r4 = r11.time
            long r6 = r2.time
            long r4 = r4 - r6
            float r4 = (float) r4
            float r3 = r3 / r4
            int r4 = r11.vpd
            int r5 = r2.vpd
            int r4 = r4 - r5
            float r4 = (float) r4
            float r3 = r3 * r4
            int r4 = r2.vpd
            float r4 = (float) r4
            float r3 = r3 + r4
            int r3 = (int) r3
            r0.aMinVpd = r3
            int r4 = r0.aMinTmp
            r0.aMaxTmp = r4
            int r4 = r0.aMinHum
            r0.aMaxHum = r4
            r0.aMaxVpd = r3
            long r3 = r0.dataMaxTime
            long r5 = r2.time
            long r5 = r3 - r5
            int r2 = r31.getDistance()
            long r7 = (long) r2
            long r5 = r5 / r7
            int r2 = r31.getDistance()
            long r7 = (long) r2
            long r5 = r5 * r7
            long r3 = r3 - r5
            long r5 = r11.time
            long r5 = r3 - r5
            long r7 = (long) r1
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 < 0) goto L_0x0186
            int r2 = r0.aMaxHum
            char r3 = r11.hum
            if (r2 >= r3) goto L_0x0135
            char r2 = r11.hum
            r0.aMaxHum = r2
            goto L_0x013f
        L_0x0135:
            int r2 = r0.aMinHum
            char r3 = r11.hum
            if (r2 <= r3) goto L_0x013f
            char r2 = r11.hum
            r0.aMinHum = r2
        L_0x013f:
            int r2 = r0.aMaxVpd
            int r3 = r11.vpd
            if (r2 >= r3) goto L_0x014a
            int r2 = r11.vpd
            r0.aMaxVpd = r2
            goto L_0x0154
        L_0x014a:
            int r2 = r0.aMinVpd
            int r3 = r11.vpd
            if (r2 <= r3) goto L_0x0154
            int r2 = r11.vpd
            r0.aMinVpd = r2
        L_0x0154:
            int r2 = r0.aMaxTmp
            int r3 = r11.getTmpValue()
            if (r2 >= r3) goto L_0x0163
            int r2 = r11.getTmpValue()
            r0.aMaxTmp = r2
            goto L_0x0171
        L_0x0163:
            int r2 = r0.aMinTmp
            int r3 = r11.getTmpValue()
            if (r2 <= r3) goto L_0x0171
            int r2 = r11.getTmpValue()
            r0.aMinTmp = r2
        L_0x0171:
            long r2 = r0.dataMaxTime
            long r4 = r11.time
            long r4 = r2 - r4
            int r6 = r31.getDistance()
            long r6 = (long) r6
            long r4 = r4 / r6
            int r6 = r31.getDistance()
            long r6 = (long) r6
            long r4 = r4 * r6
            long r3 = r2 - r4
        L_0x0186:
            r2 = r11
            goto L_0x01bc
        L_0x0188:
            r24 = r3
            r23 = r4
            r25 = r5
            r27 = r7
            int r3 = r0.minTmp
            r0.aMinTmp = r3
            int r3 = r0.minHum
            r0.aMinHum = r3
            int r3 = r0.minVpd
            r0.aMinVpd = r3
            int r3 = r0.maxTmp
            r0.aMaxTmp = r3
            int r3 = r0.maxHum
            r0.aMaxHum = r3
            int r3 = r0.maxVpd
            r0.aMaxVpd = r3
            long r3 = r0.dataMaxTime
            long r5 = r11.time
            long r5 = r3 - r5
            int r7 = r31.getDistance()
            long r7 = (long) r7
            long r5 = r5 / r7
            int r7 = r31.getDistance()
            long r7 = (long) r7
            long r5 = r5 * r7
            long r3 = r3 - r5
        L_0x01bc:
            int r5 = r0.maxTargetTmp
            r6 = -32768(0xffffffffffff8000, float:NaN)
            if (r5 == r6) goto L_0x01c9
            int r5 = r0.minTargetTmp
            if (r5 != r6) goto L_0x01c7
            goto L_0x01c9
        L_0x01c7:
            r15 = 1
            goto L_0x01db
        L_0x01c9:
            int r5 = r0.maxTargetHum
            if (r5 == r6) goto L_0x01d1
            int r5 = r0.minTargetHum
            if (r5 != r6) goto L_0x01c7
        L_0x01d1:
            int r5 = r0.maxTargetVpd
            if (r5 == r6) goto L_0x02e0
            int r5 = r0.minTargetVpd
            if (r5 != r6) goto L_0x01c7
            goto L_0x02e0
        L_0x01db:
            int r10 = r10 + r15
            r4 = r3
            r3 = r22
            r7 = r27
            r6 = 1
        L_0x01e2:
            int r8 = r0.length
            int r8 = r8 - r15
            if (r10 >= r8) goto L_0x02dc
            com.eternal.base.concat.TmpHum r2 = r0.getItem(r10)
            if (r2 != 0) goto L_0x01ee
            return
        L_0x01ee:
            int r8 = r2.tmp
            int r9 = r0.maxTargetTmp
            if (r8 <= r9) goto L_0x01f7
            int r7 = r7 + 1
            goto L_0x01ff
        L_0x01f7:
            int r8 = r2.tmp
            int r9 = r0.minTargetTmp
            if (r8 >= r9) goto L_0x01ff
            int r12 = r12 + 1
        L_0x01ff:
            char r8 = r2.hum
            int r9 = r0.maxTargetHum
            if (r8 <= r9) goto L_0x0208
            int r3 = r3 + 1
            goto L_0x0210
        L_0x0208:
            char r8 = r2.hum
            int r9 = r0.minTargetHum
            if (r8 >= r9) goto L_0x0210
            int r23 = r23 + 1
        L_0x0210:
            int r8 = r2.vpd
            int r9 = r0.maxTargetVpd
            if (r8 <= r9) goto L_0x0219
            int r16 = r16 + 1
            goto L_0x0221
        L_0x0219:
            int r8 = r2.vpd
            int r9 = r0.minTargetVpd
            if (r8 >= r9) goto L_0x0221
            int r24 = r24 + 1
        L_0x0221:
            int r8 = r0.maxHum
            char r9 = r2.hum
            if (r8 >= r9) goto L_0x022c
            char r8 = r2.hum
            r0.maxHum = r8
            goto L_0x0236
        L_0x022c:
            int r8 = r0.minHum
            char r9 = r2.hum
            if (r8 <= r9) goto L_0x0236
            char r8 = r2.hum
            r0.minHum = r8
        L_0x0236:
            int r8 = r0.maxVpd
            int r9 = r2.vpd
            if (r8 >= r9) goto L_0x0241
            int r8 = r2.vpd
            r0.maxVpd = r8
            goto L_0x024b
        L_0x0241:
            int r8 = r0.minVpd
            int r9 = r2.vpd
            if (r8 <= r9) goto L_0x024b
            int r8 = r2.vpd
            r0.minVpd = r8
        L_0x024b:
            int r8 = r0.maxTmp
            int r9 = r2.getTmpValue()
            if (r8 >= r9) goto L_0x025a
            int r8 = r2.getTmpValue()
            r0.maxTmp = r8
            goto L_0x0268
        L_0x025a:
            int r8 = r0.minTmp
            int r9 = r2.getTmpValue()
            if (r8 <= r9) goto L_0x0268
            int r8 = r2.getTmpValue()
            r0.minTmp = r8
        L_0x0268:
            char r8 = r2.hum
            long r8 = (long) r8
            long r13 = r13 + r8
            int r8 = r2.getTmpValue()
            long r8 = (long) r8
            long r20 = r20 + r8
            int r8 = r2.vpd
            long r8 = (long) r8
            long r25 = r25 + r8
            int r6 = r6 + 1
            long r8 = r2.time
            long r8 = r4 - r8
            r27 = r4
            r5 = r3
            long r3 = (long) r1
            int r11 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r11 < 0) goto L_0x02d0
            int r3 = r0.aMaxHum
            char r4 = r2.hum
            if (r3 >= r4) goto L_0x0291
            char r3 = r2.hum
            r0.aMaxHum = r3
            goto L_0x029b
        L_0x0291:
            int r3 = r0.aMinHum
            char r4 = r2.hum
            if (r3 <= r4) goto L_0x029b
            char r3 = r2.hum
            r0.aMinHum = r3
        L_0x029b:
            int r3 = r0.aMaxVpd
            int r4 = r2.vpd
            if (r3 >= r4) goto L_0x02a6
            int r3 = r2.vpd
            r0.aMaxVpd = r3
            goto L_0x02b0
        L_0x02a6:
            int r3 = r0.aMinVpd
            int r4 = r2.vpd
            if (r3 <= r4) goto L_0x02b0
            int r3 = r2.vpd
            r0.aMinVpd = r3
        L_0x02b0:
            int r3 = r0.aMaxTmp
            int r4 = r2.getTmpValue()
            if (r3 >= r4) goto L_0x02bf
            int r3 = r2.getTmpValue()
            r0.aMaxTmp = r3
            goto L_0x02cd
        L_0x02bf:
            int r3 = r0.aMinTmp
            int r4 = r2.getTmpValue()
            if (r3 <= r4) goto L_0x02cd
            int r3 = r2.getTmpValue()
            r0.aMinTmp = r3
        L_0x02cd:
            long r3 = r2.time
            goto L_0x02d2
        L_0x02d0:
            r3 = r27
        L_0x02d2:
            int r10 = r10 + 1
            r15 = 1
            r29 = r3
            r3 = r5
            r4 = r29
            goto L_0x01e2
        L_0x02dc:
            r4 = r23
            goto L_0x03b1
        L_0x02e0:
            r15 = 1
            int r10 = r10 + r15
            r5 = r3
            r3 = r20
            r19 = 1
        L_0x02e7:
            int r7 = r0.length
            int r7 = r7 - r15
            if (r10 >= r7) goto L_0x03a7
            com.eternal.base.concat.TmpHum r2 = r0.getItem(r10)
            if (r2 != 0) goto L_0x02f3
            return
        L_0x02f3:
            int r7 = r0.maxHum
            char r8 = r2.hum
            if (r7 >= r8) goto L_0x02fe
            char r7 = r2.hum
            r0.maxHum = r7
            goto L_0x0308
        L_0x02fe:
            int r7 = r0.minHum
            char r8 = r2.hum
            if (r7 <= r8) goto L_0x0308
            char r7 = r2.hum
            r0.minHum = r7
        L_0x0308:
            int r7 = r0.maxVpd
            int r8 = r2.vpd
            if (r7 >= r8) goto L_0x0313
            int r7 = r2.vpd
            r0.maxVpd = r7
            goto L_0x031d
        L_0x0313:
            int r7 = r0.minVpd
            int r8 = r2.vpd
            if (r7 <= r8) goto L_0x031d
            int r7 = r2.vpd
            r0.minVpd = r7
        L_0x031d:
            int r7 = r0.maxTmp
            int r8 = r2.getTmpValue()
            if (r7 >= r8) goto L_0x032c
            int r7 = r2.getTmpValue()
            r0.maxTmp = r7
            goto L_0x033a
        L_0x032c:
            int r7 = r0.minTmp
            int r8 = r2.getTmpValue()
            if (r7 <= r8) goto L_0x033a
            int r7 = r2.getTmpValue()
            r0.minTmp = r7
        L_0x033a:
            char r7 = r2.hum
            long r7 = (long) r7
            long r13 = r13 + r7
            int r7 = r2.getTmpValue()
            long r7 = (long) r7
            long r3 = r3 + r7
            int r7 = r2.vpd
            long r7 = (long) r7
            long r25 = r25 + r7
            int r19 = r19 + 1
            long r7 = r2.time
            long r7 = r5 - r7
            r20 = r3
            long r3 = (long) r1
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 < 0) goto L_0x03a0
            int r3 = r0.aMaxHum
            char r4 = r2.hum
            if (r3 >= r4) goto L_0x0361
            char r3 = r2.hum
            r0.aMaxHum = r3
            goto L_0x036b
        L_0x0361:
            int r3 = r0.aMinHum
            char r4 = r2.hum
            if (r3 <= r4) goto L_0x036b
            char r3 = r2.hum
            r0.aMinHum = r3
        L_0x036b:
            int r3 = r0.aMaxVpd
            int r4 = r2.vpd
            if (r3 >= r4) goto L_0x0376
            int r3 = r2.vpd
            r0.aMaxVpd = r3
            goto L_0x0380
        L_0x0376:
            int r3 = r0.aMinVpd
            int r4 = r2.vpd
            if (r3 <= r4) goto L_0x0380
            int r3 = r2.vpd
            r0.aMinVpd = r3
        L_0x0380:
            int r3 = r0.aMaxTmp
            int r4 = r2.getTmpValue()
            if (r3 >= r4) goto L_0x038f
            int r3 = r2.getTmpValue()
            r0.aMaxTmp = r3
            goto L_0x039d
        L_0x038f:
            int r3 = r0.aMinTmp
            int r4 = r2.getTmpValue()
            if (r3 <= r4) goto L_0x039d
            int r3 = r2.getTmpValue()
            r0.aMinTmp = r3
        L_0x039d:
            long r3 = r2.time
            r5 = r3
        L_0x03a0:
            int r10 = r10 + 1
            r3 = r20
            r15 = 1
            goto L_0x02e7
        L_0x03a7:
            r20 = r3
            r6 = r19
            r3 = r22
            r4 = r23
            r7 = r27
        L_0x03b1:
            int r1 = r0.length
            r5 = 1
            int r1 = r1 - r5
            com.eternal.base.concat.TmpHum r1 = r0.getItem(r1)
            if (r1 != 0) goto L_0x03bc
            return
        L_0x03bc:
            long r8 = r1.time
            int r5 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r5 >= 0) goto L_0x0411
            com.eternal.base.concat.TmpHum r1 = r1.clone()
            long r8 = r2.time
            long r8 = r17 - r8
            float r5 = (float) r8
            long r8 = r1.time
            long r10 = r2.time
            long r8 = r8 - r10
            float r8 = (float) r8
            float r5 = r5 / r8
            int r8 = r1.getTmpValue()
            int r9 = r2.getTmpValue()
            int r8 = r8 - r9
            float r8 = (float) r8
            float r5 = r5 * r8
            int r8 = r2.getTmpValue()
            float r8 = (float) r8
            float r5 = r5 + r8
            int r5 = (int) r5
            r1.tmp = r5
            long r8 = r2.time
            long r8 = r17 - r8
            float r5 = (float) r8
            long r8 = r1.time
            long r10 = r2.time
            long r8 = r8 - r10
            float r8 = (float) r8
            float r5 = r5 / r8
            char r8 = r1.hum
            char r9 = r2.hum
            int r8 = r8 - r9
            float r8 = (float) r8
            float r5 = r5 * r8
            char r2 = r2.hum
            float r2 = (float) r2
            float r5 = r5 + r2
            int r2 = (int) r5
            char r2 = (char) r2
            r1.hum = r2
            r8 = r17
            r1.time = r8
        L_0x0407:
            r2 = r16
            r8 = r20
            r5 = r24
            r10 = r25
            goto L_0x049f
        L_0x0411:
            int r2 = r1.tmp
            int r5 = r0.maxTargetTmp
            if (r2 <= r5) goto L_0x041a
            int r7 = r7 + 1
            goto L_0x0422
        L_0x041a:
            int r2 = r1.tmp
            int r5 = r0.minTargetTmp
            if (r2 >= r5) goto L_0x0422
            int r12 = r12 + 1
        L_0x0422:
            char r2 = r1.hum
            int r5 = r0.maxTargetHum
            if (r2 <= r5) goto L_0x042b
            int r3 = r3 + 1
            goto L_0x0433
        L_0x042b:
            char r2 = r1.hum
            int r5 = r0.minTargetHum
            if (r2 >= r5) goto L_0x0433
            int r4 = r4 + 1
        L_0x0433:
            int r2 = r1.vpd
            int r5 = r0.maxTargetVpd
            if (r2 <= r5) goto L_0x043c
            int r16 = r16 + 1
            goto L_0x0444
        L_0x043c:
            int r2 = r1.vpd
            int r5 = r0.minTargetVpd
            if (r2 >= r5) goto L_0x0444
            int r24 = r24 + 1
        L_0x0444:
            int r2 = r0.maxHum
            char r5 = r1.hum
            if (r2 >= r5) goto L_0x044f
            char r2 = r1.hum
            r0.maxHum = r2
            goto L_0x0459
        L_0x044f:
            int r2 = r0.minHum
            char r5 = r1.hum
            if (r2 <= r5) goto L_0x0459
            char r2 = r1.hum
            r0.minHum = r2
        L_0x0459:
            int r2 = r0.maxVpd
            int r5 = r1.vpd
            if (r2 >= r5) goto L_0x0464
            int r2 = r1.vpd
            r0.maxVpd = r2
            goto L_0x046e
        L_0x0464:
            int r2 = r0.minVpd
            int r5 = r1.vpd
            if (r2 <= r5) goto L_0x046e
            int r2 = r1.vpd
            r0.minVpd = r2
        L_0x046e:
            int r2 = r0.maxTmp
            int r5 = r1.getTmpValue()
            if (r2 >= r5) goto L_0x047d
            int r2 = r1.getTmpValue()
            r0.maxTmp = r2
            goto L_0x048b
        L_0x047d:
            int r2 = r0.minTmp
            int r5 = r1.getTmpValue()
            if (r2 <= r5) goto L_0x048b
            int r2 = r1.getTmpValue()
            r0.minTmp = r2
        L_0x048b:
            char r2 = r1.hum
            long r8 = (long) r2
            long r13 = r13 + r8
            int r2 = r1.getTmpValue()
            long r8 = (long) r2
            long r20 = r20 + r8
            int r2 = r1.vpd
            long r8 = (long) r2
            long r25 = r25 + r8
            int r6 = r6 + 1
            goto L_0x0407
        L_0x049f:
            int r15 = r0.aMaxHum
            r16 = r5
            char r5 = r1.hum
            if (r15 >= r5) goto L_0x04ac
            char r5 = r1.hum
            r0.aMaxHum = r5
            goto L_0x04b6
        L_0x04ac:
            int r5 = r0.aMinHum
            char r15 = r1.hum
            if (r5 <= r15) goto L_0x04b6
            char r5 = r1.hum
            r0.aMinHum = r5
        L_0x04b6:
            int r5 = r0.aMaxVpd
            int r15 = r1.vpd
            if (r5 >= r15) goto L_0x04c1
            int r5 = r1.vpd
            r0.aMaxVpd = r5
            goto L_0x04cb
        L_0x04c1:
            int r5 = r0.aMinVpd
            int r15 = r1.vpd
            if (r5 <= r15) goto L_0x04cb
            int r5 = r1.vpd
            r0.aMinVpd = r5
        L_0x04cb:
            int r5 = r0.aMaxTmp
            int r15 = r1.getTmpValue()
            if (r5 >= r15) goto L_0x04da
            int r1 = r1.getTmpValue()
            r0.aMaxTmp = r1
            goto L_0x04e8
        L_0x04da:
            int r5 = r0.aMinTmp
            int r15 = r1.getTmpValue()
            if (r5 <= r15) goto L_0x04e8
            int r1 = r1.getTmpValue()
            r0.aMinTmp = r1
        L_0x04e8:
            float r1 = (float) r13
            float r5 = (float) r6
            r6 = 1065353216(0x3f800000, float:1.0)
            float r13 = r5 * r6
            float r1 = r1 / r13
            r0.avgHum = r1
            float r1 = (float) r8
            float r1 = r1 / r13
            r0.avgTmp = r1
            float r1 = (float) r10
            float r1 = r1 / r13
            r0.avgVpd = r1
            int r1 = r0.maxTargetTmp
            r8 = 1120403456(0x42c80000, float:100.0)
            r9 = -32768(0xffffffffffff8000, float:NaN)
            if (r1 == r9) goto L_0x051f
            int r1 = r0.minTargetTmp
            if (r1 != r9) goto L_0x0506
            goto L_0x051f
        L_0x0506:
            float r1 = (float) r7
            float r1 = r1 / r6
            float r1 = r1 / r5
            float r1 = r1 * r8
            int r1 = java.lang.Math.round(r1)
            r0.aboveTmpScore = r1
            float r1 = (float) r12
            float r1 = r1 / r6
            float r1 = r1 / r5
            float r1 = r1 * r8
            int r1 = java.lang.Math.round(r1)
            r0.belowTmpScore = r1
            r1 = -32768(0xffffffffffff8000, float:NaN)
            goto L_0x0525
        L_0x051f:
            r1 = -32768(0xffffffffffff8000, float:NaN)
            r0.aboveTmpScore = r1
            r0.belowTmpScore = r1
        L_0x0525:
            int r7 = r0.maxTargetHum
            if (r7 == r1) goto L_0x0547
            int r7 = r0.minTargetHum
            if (r7 != r1) goto L_0x052e
            goto L_0x0547
        L_0x052e:
            float r1 = (float) r3
            float r1 = r1 / r6
            float r1 = r1 / r5
            float r1 = r1 * r8
            int r1 = java.lang.Math.round(r1)
            r0.aboveHumScore = r1
            float r1 = (float) r4
            float r1 = r1 / r6
            float r1 = r1 / r5
            float r1 = r1 * r8
            int r1 = java.lang.Math.round(r1)
            r0.belowHumScore = r1
            r1 = -32768(0xffffffffffff8000, float:NaN)
            goto L_0x054b
        L_0x0547:
            r0.aboveHumScore = r1
            r0.belowHumScore = r1
        L_0x054b:
            int r3 = r0.maxTargetVpd
            if (r3 == r1) goto L_0x056d
            int r3 = r0.minTargetVpd
            if (r3 != r1) goto L_0x0554
            goto L_0x056d
        L_0x0554:
            float r1 = (float) r2
            float r1 = r1 / r6
            float r1 = r1 / r5
            float r1 = r1 * r8
            int r1 = java.lang.Math.round(r1)
            r0.aboveVpdScore = r1
            r1 = r16
            float r1 = (float) r1
            float r1 = r1 / r6
            float r1 = r1 / r5
            float r1 = r1 * r8
            int r1 = java.lang.Math.round(r1)
            r0.belowVpdScore = r1
            goto L_0x0571
        L_0x056d:
            r0.aboveVpdScore = r1
            r0.belowVpdScore = r1
        L_0x0571:
            return
        L_0x0572:
            r1 = 0
            r0.length = r1
            r0.aMaxHum = r1
            r0.aMinHum = r1
            r0.aMaxVpd = r1
            r0.aMinVpd = r1
            r0.aMaxTmp = r1
            r0.aMinTmp = r1
            r2 = 0
            r0.avgTmp = r2
            r0.avgHum = r2
            r0.avgVpd = r2
            r0.maxHum = r1
            r0.minHum = r1
            r0.maxVpd = r1
            r0.minVpd = r1
            r0.maxTmp = r1
            r0.minTmp = r1
            r1 = -32768(0xffffffffffff8000, float:NaN)
            r0.aboveTmpScore = r1
            r0.aboveHumScore = r1
            r0.aboveVpdScore = r1
            r0.belowTmpScore = r1
            r0.belowHumScore = r1
            r0.belowVpdScore = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.data.p007ui.GraphAdapter.updateSpace():void");
    }

    /* access modifiers changed from: package-private */
    public int nearestSampleIndexForX(int i, float f) {
        int timeFromX = timeFromX(i, f);
        int i2 = this.length;
        if (i2 == 0) {
            return -1;
        }
        long j = (long) timeFromX;
        if (j > this.dataMaxTime) {
            return 0;
        }
        TmpHum item = getItem(i2 - 1);
        if (item == null || j < item.time) {
            return this.length - 1;
        }
        TmpHum item2 = getItem(0);
        if (item2 == null || item2.time == j) {
            return 0;
        }
        int distance = getDistance();
        long j2 = this.dataMaxTime;
        long j3 = (long) distance;
        long j4 = j2 - (((j2 - item2.time) / j3) * j3);
        int i3 = 1;
        int i4 = -1;
        while (i3 < this.length - 1) {
            TmpHum item3 = getItem(i3);
            if (item3 == null) {
                return i4;
            }
            if (j4 - item3.time >= j3) {
                if (item3.time == j) {
                    return i3;
                }
                if (item3.time < j && item2.time > j) {
                    return j - item3.time < item2.time - j ? i3 : i4;
                }
                j4 = item3.time;
                i4 = i3;
                item2 = item3;
            }
            i3++;
        }
        TmpHum item4 = getItem(i3);
        if (item4 == null) {
            return i4;
        }
        if (item4.time == j) {
            return i3;
        }
        if (item4.time >= j || item2.time <= j) {
            return -1;
        }
        return j - item4.time < item2.time - j ? i3 : i4;
    }

    /* access modifiers changed from: package-private */
    public TmpHum getItem(int i) {
        PagedList<TmpHum> pagedList = this.data;
        if (pagedList == null) {
            return this.snapshot.get(i + this.positionOffset);
        }
        int i2 = i + this.positionOffset;
        if (pagedList.size() <= i2 || i2 < 0) {
            return null;
        }
        return this.data.get(i2);
    }

    /* access modifiers changed from: package-private */
    public int timeFromX(int i, float f) {
        if (i > 0) {
            return (int) (((long) ((int) ((f * ((float) this.timeDistance)) / ((float) i)))) + this.minTime);
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public float xFromTime(int i, long j) {
        return (((float) (j - this.minTime)) * ((float) i)) / ((float) this.timeDistance);
    }

    /* access modifiers changed from: package-private */
    public boolean isInit() {
        return (this.data == null && this.snapshot == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public boolean isEmptyData() {
        return this.isEmptyData;
    }

    public void notifyDataSetChanged() {
        if (this.data != null) {
            this.processor.onNext(new Space(this.minTime, this.timeDistance, true));
        }
    }

    /* renamed from: com.eternal.data.ui.GraphAdapter$Space */
    public static class Space {
        final int distance;
        final long min;
        final boolean update;

        public Space(long j, int i, boolean z) {
            this.min = j;
            this.distance = i;
            this.update = z;
        }
    }
}
