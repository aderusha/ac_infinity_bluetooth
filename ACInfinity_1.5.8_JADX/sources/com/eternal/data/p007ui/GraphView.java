package com.eternal.data.p007ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Insets;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowInsets;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.data.C1835R;
import com.eternal.data.p007ui.GraphAdapter;
import com.eternal.framework.utils.ConvertUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.processors.PublishProcessor;
import p014io.reactivex.schedulers.Schedulers;

/* renamed from: com.eternal.data.ui.GraphView */
public class GraphView extends SurfaceView implements SurfaceHolder.Callback, GraphAdapter.Callback {
    public final int GRAPH_HEIGHT;
    public final int GRAPH_LEVEL_HEIGHT;
    public final int MISS_PADDING;
    protected GraphAdapter adapter;
    private Bitmap backwardBitmap;
    private RectF backwardBounds;
    private SimpleDateFormat dateFormat;
    /* access modifiers changed from: private */
    public boolean draggingScroller;
    private List<Rect> exclusionRects;
    private DecimalFormat format;
    private Bitmap forwardBitmap;
    private RectF forwardBounds;
    private GestureDetector gestureDetector;
    private Paint graphAvgLinePaint;
    private Paint graphBoundsLinePaint;
    private Paint graphBoundsTopLinePaint;
    private float graphHeight;
    public GraphHum graphHum;
    private Paint graphLabelsLightTextPaint;
    private Paint graphLabelsTextPaint;
    private float graphLevelHeight;
    private Paint graphLevelValuePaint;
    private CopyOnWriteArrayList<GraphLevel> graphLevels;
    private int graphMartinTop;
    private Paint graphRulerPaint;
    private Paint graphTimeLinePaint;
    public GraphTmp graphTmp;
    private Paint graphValuePaint;
    public GraphVpd graphVpd;
    /* access modifiers changed from: private */
    public int graphWidth;
    private String humLabelText;
    private boolean humVisible;
    private boolean isDegree;
    /* access modifiers changed from: private */
    public boolean isPanning;
    /* access modifiers changed from: private */
    public boolean isSliding;
    private Paint labelLinePaint;
    private Bitmap levelBitmap;
    private Paint levelMissPaint;
    private ReentrantReadWriteLock lock;
    private SurfaceHolder mHolder;
    private Paint missPaint;
    private SimpleDateFormat monthFormat;
    private Bitmap plugBitmap;
    private int portCount;
    private byte portMode;
    private ScaleGestureDetector scaleGestureDetector;
    /* access modifiers changed from: private */
    public float scrollBarHeight;
    private Paint scrollBarPaint;
    private float scrollBarWidth;
    private Paint scrollerCirclePaint;
    private int scrollerHum;
    private RectF scrollerHumLabelBounds;
    private Rect scrollerHumLabelTextBounds;
    private float scrollerHumY;
    private RectF scrollerLabelBounds;
    private int scrollerLabelHeight;
    private Paint scrollerLabelPaint;
    private String scrollerLabelText;
    private Rect scrollerLabelTextBounds;
    private Paint scrollerLabelTextPaint;
    private Paint scrollerLinePaint;
    /* access modifiers changed from: private */
    public boolean scrollerOpen;
    private Paint scrollerRectPaint;
    private boolean scrollerRight;
    private Paint scrollerTimeLabelTextPaint;
    private Path scrollerTimePath;
    private long scrollerTimestamp;
    private Paint scrollerVerticalLinePaint;
    private int scrollerVpd;
    private RectF scrollerVpdLabelBounds;
    private Rect scrollerVpdLabelTextBounds;
    private float scrollerVpdY;
    private float scrollerX;
    private int separatorHeight;
    private boolean showBottomGraph;
    private boolean showHumLine;
    private boolean showLevel;
    private int showPortLines;
    private boolean showTmpLine;
    private boolean showVpdLine;
    private Rect sliderBackgroundBounds;
    /* access modifiers changed from: private */
    public RectF sliderBounds;
    private int sliderCorner;
    private float sliderPadding;
    private Paint sliderPaint;
    /* access modifiers changed from: private */
    public boolean sliderVisible;
    private int sliderWidth;
    private float standardPadding;
    private final PublishProcessor<TmpHum> subject;
    private Rect textBounds;
    private float timeBarHeight;
    private float timePadding;
    private int topSpaceHeight;
    private String vpdLabelText;
    private boolean vpdVisible;
    private SimpleDateFormat yearFormat;

    /* renamed from: com.eternal.data.ui.GraphView$IGraphDelegate */
    interface IGraphDelegate {
        String formatValue(float f);

        String formatValue2(float f, float f2);

        int getAMax();

        int getAMin();

        float getAvg();

        int getDefaultColor();

        Path getGraphPath();

        TimeValue getItem(int i);

        int getMax();

        int getMaxTarget();

        int getMin();

        int getMinTarget();

        Path getMissPath();
    }

    public final int valueFormTime(long j, long j2, int i, int i2, long j3) {
        return (int) (((((float) (j3 - j)) / ((float) (j2 - j))) * ((float) (i2 - i))) + ((float) i));
    }

    public GraphView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GraphView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GraphView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.GRAPH_HEIGHT = getResources().getDimensionPixelSize(C1835R.dimen.dp_300);
        this.GRAPH_LEVEL_HEIGHT = getResources().getDimensionPixelSize(C1835R.dimen.dp_126);
        this.MISS_PADDING = 60;
        this.isDegree = true;
        init();
        PublishProcessor<TmpHum> create = PublishProcessor.create();
        this.subject = create;
        create.onBackpressureLatest().observeOn(Schedulers.computation()).subscribe(new Consumer<TmpHum>() {
            public void accept(TmpHum tmpHum) throws Exception {
                GraphView.this.updateScrollerText(tmpHum);
                GraphView.this.scaleScroller();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        this.format = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    public void setDegree(boolean z) {
        this.graphTmp.setDegree(z);
    }

    public void setShowLevel(boolean z) {
        this.showLevel = z;
    }

    public void setShowBottomGraph(boolean z) {
        this.showBottomGraph = z;
    }

    public void setHumVisible(boolean z) {
        this.humVisible = z;
    }

    public void setVpdVisible(boolean z) {
        this.vpdVisible = z;
    }

    public void setAdapter(GraphAdapter graphAdapter) {
        this.adapter = graphAdapter;
        graphAdapter.addCallback(this);
        this.graphTmp = new GraphTmp(graphAdapter);
        this.graphHum = new GraphHum(graphAdapter);
        this.graphVpd = new GraphVpd(graphAdapter);
    }

    public void setPortCount(int i, byte b) {
        if (this.portCount != i && this.adapter != null) {
            this.portCount = i;
            for (byte isDeviceMultiPort = ProtocolTransformer.isDeviceMultiPort(b); isDeviceMultiPort < i; isDeviceMultiPort = (byte) (isDeviceMultiPort + 1)) {
                this.graphLevels.add(new GraphLevel(this.adapter, isDeviceMultiPort, b == 11));
            }
        }
    }

    private void init() {
        getHolder().addCallback(this);
        this.standardPadding = (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_4);
        this.timePadding = (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_12);
        this.textBounds = new Rect();
        this.scrollerLabelTextBounds = new Rect();
        this.scrollerLabelBounds = new RectF();
        this.scrollerHumLabelTextBounds = new Rect();
        this.scrollerHumLabelBounds = new RectF();
        this.scrollerVpdLabelTextBounds = new Rect();
        this.scrollerVpdLabelBounds = new RectF();
        this.sliderBackgroundBounds = new Rect();
        this.backwardBounds = new RectF();
        this.forwardBounds = new RectF();
        this.sliderBounds = new RectF();
        this.scrollerTimePath = new Path();
        this.exclusionRects = new ArrayList();
        this.graphLevels = new CopyOnWriteArrayList<>();
        this.dateFormat = new SimpleDateFormat("MMM dd, yyyy, h:mm aa", Locale.ENGLISH);
        this.monthFormat = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
        this.yearFormat = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        this.humLabelText = "";
        this.vpdLabelText = "";
        this.lock = new ReentrantReadWriteLock();
        initPaint();
        initGestureRecognizers();
        this.backwardBitmap = BitmapFactory.decodeResource(getResources(), C1835R.mipmap.backward);
        this.forwardBitmap = BitmapFactory.decodeResource(getResources(), C1835R.mipmap.forward);
        this.levelBitmap = BitmapFactory.decodeResource(getResources(), C1835R.mipmap.graph_level_icon);
        this.plugBitmap = BitmapFactory.decodeResource(getResources(), C1835R.mipmap.graph_plug_icon);
    }

    private void initPaint() {
        Paint paint = new Paint(1);
        this.graphTimeLinePaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.graphTimeLinePaint.setColor(-1);
        this.graphTimeLinePaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint2 = new Paint(1);
        this.graphLabelsLightTextPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.graphLabelsLightTextPaint.setColor(-1);
        this.graphLabelsLightTextPaint.setTextSize(getResources().getDimension(C1835R.dimen.dp_10));
        this.graphLabelsLightTextPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1835R.font.avenir_book));
        Paint paint3 = new Paint(1);
        this.graphLabelsTextPaint = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.graphLabelsTextPaint.setColor(-4210753);
        this.graphLabelsTextPaint.setTextSize(getResources().getDimension(C1835R.dimen.dp_10));
        this.graphLabelsTextPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1835R.font.avenir_book));
        Paint paint4 = new Paint(1);
        this.graphBoundsLinePaint = paint4;
        paint4.setStyle(Paint.Style.STROKE);
        this.graphBoundsLinePaint.setColor(-4076584);
        this.graphBoundsLinePaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint5 = new Paint(1);
        this.graphBoundsTopLinePaint = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        this.graphBoundsTopLinePaint.setColor(-1);
        this.graphBoundsTopLinePaint.setAlpha(WorkQueueKt.MASK);
        this.graphBoundsTopLinePaint.setStrokeWidth((float) ConvertUtils.sp2px(0.5f));
        Paint paint6 = new Paint(1);
        this.labelLinePaint = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        this.labelLinePaint.setColor(-1);
        this.labelLinePaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint7 = new Paint(1);
        this.scrollerLabelTextPaint = paint7;
        paint7.setStyle(Paint.Style.FILL);
        this.scrollerLabelTextPaint.setColor(-14408667);
        this.scrollerLabelTextPaint.setTextSize(getResources().getDimension(C1835R.dimen.dp_10));
        this.scrollerLabelTextPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1835R.font.avenir_medium));
        Paint paint8 = new Paint(1);
        this.scrollerTimeLabelTextPaint = paint8;
        paint8.setStyle(Paint.Style.FILL);
        this.scrollerTimeLabelTextPaint.setColor(-14408667);
        this.scrollerTimeLabelTextPaint.setTextSize(getResources().getDimension(C1835R.dimen.dp_12));
        this.scrollerTimeLabelTextPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1835R.font.avenir_medium));
        Paint paint9 = new Paint(1);
        this.graphAvgLinePaint = paint9;
        paint9.setStyle(Paint.Style.STROKE);
        this.graphAvgLinePaint.setColor(-6777194);
        this.graphAvgLinePaint.setPathEffect(new DashPathEffect(new float[]{(float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2), (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_8), (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2), (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_8)}, 0.0f));
        this.graphAvgLinePaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint10 = new Paint(1);
        this.graphValuePaint = paint10;
        paint10.setStyle(Paint.Style.STROKE);
        CornerPathEffect cornerPathEffect = new CornerPathEffect(50.0f);
        this.graphValuePaint.setPathEffect(cornerPathEffect);
        this.graphValuePaint.setColor(-15353089);
        this.graphValuePaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint11 = new Paint(1);
        this.missPaint = paint11;
        paint11.setStyle(Paint.Style.STROKE);
        this.missPaint.setPathEffect(cornerPathEffect);
        this.missPaint.setColor(-10263706);
        this.missPaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint12 = new Paint(1);
        this.scrollerLinePaint = paint12;
        paint12.setStyle(Paint.Style.STROKE);
        this.scrollerLinePaint.setColor(-1);
        this.scrollerLinePaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint13 = new Paint(1);
        this.scrollerVerticalLinePaint = paint13;
        paint13.setStyle(Paint.Style.STROKE);
        this.scrollerVerticalLinePaint.setColor(-1);
        this.scrollerVerticalLinePaint.setStrokeWidth((float) ConvertUtils.sp2px(0.5f));
        Paint paint14 = new Paint(1);
        this.scrollerLabelPaint = paint14;
        paint14.setStyle(Paint.Style.FILL);
        this.scrollerLabelPaint.setColor(-4076584);
        Paint paint15 = new Paint(1);
        this.scrollerRectPaint = paint15;
        paint15.setStyle(Paint.Style.FILL);
        this.scrollerRectPaint.setColor(-15921907);
        Paint paint16 = new Paint(1);
        this.scrollerCirclePaint = paint16;
        paint16.setStyle(Paint.Style.STROKE);
        this.scrollerCirclePaint.setColor(-1);
        this.scrollerCirclePaint.setStrokeWidth((float) ConvertUtils.sp2px(0.5f));
        Paint paint17 = new Paint(1);
        this.graphRulerPaint = paint17;
        paint17.setStyle(Paint.Style.STROKE);
        this.graphRulerPaint.setColor(-8092540);
        this.graphRulerPaint.setStrokeWidth((float) ConvertUtils.sp2px(0.5f));
        Paint paint18 = new Paint(1);
        this.graphLevelValuePaint = paint18;
        paint18.setStyle(Paint.Style.STROKE);
        this.graphLevelValuePaint.setColor(-15353089);
        this.graphLevelValuePaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint19 = new Paint(1);
        this.levelMissPaint = paint19;
        paint19.setStyle(Paint.Style.STROKE);
        this.levelMissPaint.setColor(-10263706);
        this.levelMissPaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint20 = new Paint(1);
        this.scrollBarPaint = paint20;
        paint20.setStyle(Paint.Style.FILL);
        this.scrollBarPaint.setColor(-14408667);
        Paint paint21 = new Paint(1);
        this.sliderPaint = paint21;
        paint21.setStyle(Paint.Style.FILL);
        this.sliderPaint.setColor(-4076584);
    }

    private void initGestureRecognizers() {
        this.gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            public void onLongPress(MotionEvent motionEvent) {
            }

            public void onShowPress(MotionEvent motionEvent) {
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                KLog.m68i("onSingleTapUp!");
                if (GraphView.this.adapter.length >= 2) {
                    if (motionEvent.getY() > (((float) GraphView.this.getHeight()) - GraphView.this.scrollBarHeight) - ((float) GraphView.this.getResources().getDimensionPixelSize(C1835R.dimen.dp_6))) {
                        GraphView.this.onScrollBarTap(motionEvent);
                    } else {
                        GraphView graphView = GraphView.this;
                        boolean unused = graphView.scrollerOpen = !graphView.scrollerOpen;
                        boolean unused2 = GraphView.this.draggingScroller = false;
                        GraphView.this.positionScroller(motionEvent.getX());
                    }
                }
                return false;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (GraphView.this.sliderVisible && !GraphView.this.isSliding && GraphView.this.sliderBounds.left <= motionEvent.getX() && GraphView.this.sliderBounds.right >= motionEvent.getX() && (((float) GraphView.this.getHeight()) - GraphView.this.sliderBounds.height()) - ((float) (GraphView.this.getResources().getDimensionPixelSize(C1835R.dimen.dp_6) * 2)) <= motionEvent.getY()) {
                    boolean unused = GraphView.this.isSliding = true;
                }
                if ((((float) GraphView.this.getHeight()) - GraphView.this.sliderBounds.height()) - ((float) (GraphView.this.getResources().getDimensionPixelSize(C1835R.dimen.dp_6) * 2)) > motionEvent.getY() && (Math.abs(f) > 50.0f || (Math.abs(f) > Math.abs(f2) && !GraphView.this.isPanning && !GraphView.this.isSliding))) {
                    GraphView.this.onPanStart(motionEvent2);
                    boolean unused2 = GraphView.this.isPanning = true;
                }
                if (GraphView.this.isSliding) {
                    GraphView.this.onSlideMoved(motionEvent2, f);
                } else if (GraphView.this.isPanning) {
                    GraphView.this.onPanMoved(motionEvent2, f);
                }
                return true;
            }
        });
        this.scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.OnScaleGestureListener() {
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                int focusX;
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (scaleFactor < 0.5f) {
                    scaleFactor = 0.5f;
                }
                float f = ((float) GraphView.this.adapter.timeDistance) * (1.0f - scaleFactor);
                int i = (int) (((float) GraphView.this.adapter.timeDistance) + f);
                if (i < 1800 || i > 31536000 || (focusX = (int) ((scaleGestureDetector.getFocusX() / ((float) GraphView.this.graphWidth)) * f)) == 0) {
                    return true;
                }
                boolean unused = GraphView.this.scrollerOpen = false;
                GraphView.this.adapter.setVisibleTimeSpace(GraphView.this.adapter.minTime - ((long) focusX), GraphView.this.adapter.timeDistance + (focusX * 2));
                return true;
            }

            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                KLog.m68i("Scale end!");
            }
        });
    }

    public void hidePositionScroller(boolean z) {
        this.scrollerOpen = z;
        positionScroller(0.0f);
    }

    public void updatePositionScroller() {
        positionScroller(this.scrollerX);
    }

    /* access modifiers changed from: private */
    public void positionScroller(float f) {
        KLog.m65e("数据页 当前线程：" + Thread.currentThread().getName());
        if (this.scrollerOpen) {
            int nearestSampleIndexForX = this.adapter.nearestSampleIndexForX(this.graphWidth, f);
            if (nearestSampleIndexForX < 0) {
                TmpHum item = this.adapter.getItem(nearestSampleIndexForX);
                if (item != null) {
                    float xFromTime = this.adapter.xFromTime(this.graphWidth, item.time);
                    float f2 = this.standardPadding;
                    if (xFromTime < f2 || xFromTime > ((float) this.graphWidth) - f2) {
                        this.scrollerOpen = false;
                    }
                } else {
                    return;
                }
            } else if (this.adapter.length != 2 || ((this.adapter.getItem(0) == null || this.adapter.getItem(0).time < this.adapter.minTime + ((long) this.adapter.timeDistance)) && (this.adapter.getItem(1) == null || this.adapter.getItem(1).time > this.adapter.minTime))) {
                TmpHum item2 = this.adapter.getItem(nearestSampleIndexForX);
                if (item2 != null) {
                    float xFromTime2 = this.adapter.xFromTime(this.graphWidth, item2.time);
                    float f3 = this.standardPadding;
                    if (xFromTime2 < f3 || xFromTime2 > ((float) this.graphWidth) - f3) {
                        this.scrollerOpen = false;
                    } else {
                        this.subject.onNext(item2);
                    }
                } else {
                    return;
                }
            } else {
                TmpHum item3 = this.adapter.getItem(1);
                if (item3 != null) {
                    updateScrollerText(item3);
                    this.scrollerOpen = false;
                } else {
                    return;
                }
            }
        }
        postDraw();
    }

    /* access modifiers changed from: private */
    public void updateScrollerText(TmpHum tmpHum) {
        this.graphTmp.tmpLabelText = String.format(Locale.ENGLISH, "TEMP: %s", new Object[]{this.graphTmp.formatValue((float) tmpHum.tmp)});
        this.humLabelText = String.format(Locale.ENGLISH, "HUMID: %s", new Object[]{this.graphHum.formatValue((float) tmpHum.hum)});
        this.vpdLabelText = String.format(Locale.ENGLISH, "VPD: %s", new Object[]{this.graphVpd.formatValue((float) tmpHum.vpd)});
        mergeLevel(tmpHum, this.showLevel);
        this.scrollerTimestamp = tmpHum.time;
        this.graphTmp.scrollerTmp = tmpHum.getTmpValue();
        this.scrollerHum = tmpHum.hum;
        this.scrollerVpd = tmpHum.vpd;
        Iterator<GraphLevel> it = this.graphLevels.iterator();
        while (it.hasNext()) {
            it.next().setScrollerLevel(tmpHum, this.showLevel);
        }
        String upperCase = this.dateFormat.format(new Date(this.scrollerTimestamp * 1000)).toUpperCase();
        this.scrollerLabelText = String.format(Locale.ENGLISH, "%s", new Object[]{upperCase});
    }

    public void mergeLevel(TmpHum tmpHum, boolean z) {
        Iterator<GraphLevel> it = this.graphLevels.iterator();
        while (it.hasNext()) {
            GraphLevel next = it.next();
            next.merge = false;
            next.mergeShow = false;
            next.labelText = next.port + "";
        }
        if (this.graphLevels.size() == 1) {
            this.graphLevels.get(0).setLabelText(tmpHum, z, "");
            return;
        }
        for (int i = 0; i < this.graphLevels.size(); i++) {
            if (((this.showPortLines >> this.graphLevels.get(i).port) & 1) != 0) {
                int level = this.graphLevels.get(i).getLevel(tmpHum);
                boolean z2 = !z || level == 15;
                for (int i2 = i + 1; i2 < this.graphLevels.size(); i2++) {
                    if (((this.showPortLines >> this.graphLevels.get(i2).port) & 1) != 0) {
                        int level2 = this.graphLevels.get(i2).getLevel(tmpHum);
                        boolean z3 = !z || level2 == 15;
                        if (!this.graphLevels.get(i).merge && !this.graphLevels.get(i2).merge && z2 == z3 && ((z2 && this.graphLevels.get(i).getOff(tmpHum) == this.graphLevels.get(i2).getOff(tmpHum)) || (!z2 && level == level2))) {
                            this.graphLevels.get(i).labelText += " & " + this.graphLevels.get(i2).port;
                            this.graphLevels.get(i2).merge = true;
                            this.graphLevels.get(i).mergeShow = true;
                        }
                    }
                }
                this.graphLevels.get(i).labelText = String.format(Locale.ENGLISH, Utils.getString(C1835R.string.level_label), new Object[]{this.graphLevels.get(i).labelText});
                this.graphLevels.get(i).setLabelText(tmpHum, z, this.graphLevels.get(i).labelText);
                this.graphLevels.get(i).setIcon(z2 ? this.plugBitmap : this.levelBitmap);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0274  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0279  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void scaleScroller() {
        /*
            r12 = this;
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r12.lock
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.lock()
            float r0 = r12.standardPadding
            double r0 = (double) r0
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r0 = r0 * r2
            int r0 = (int) r0
            com.eternal.data.ui.GraphAdapter r1 = r12.adapter
            int r2 = r12.graphWidth
            long r3 = r12.scrollerTimestamp
            float r1 = r1.xFromTime(r2, r3)
            r12.scrollerX = r1
            float r2 = r12.standardPadding
            r3 = 0
            int r4 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x05e4
            int r4 = r12.graphWidth
            float r4 = (float) r4
            float r4 = r4 - r2
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 > 0) goto L_0x05e4
            java.lang.String r1 = r12.scrollerLabelText
            if (r1 != 0) goto L_0x0032
            goto L_0x05e4
        L_0x0032:
            android.graphics.Paint r2 = r12.scrollerTimeLabelTextPaint
            int r4 = r1.length()
            android.graphics.Rect r5 = r12.scrollerLabelTextBounds
            r2.getTextBounds(r1, r3, r4, r5)
            android.graphics.Rect r1 = r12.scrollerLabelTextBounds
            int r1 = r1.width()
            android.graphics.Paint r2 = r12.scrollerTimeLabelTextPaint
            android.graphics.Rect r4 = r12.scrollerLabelTextBounds
            java.lang.String r5 = "112"
            r6 = 1
            r2.getTextBounds(r5, r3, r6, r4)
            android.graphics.Rect r2 = r12.scrollerLabelTextBounds
            r2.left = r3
            android.graphics.Rect r2 = r12.scrollerLabelTextBounds
            r2.right = r1
            android.graphics.Rect r1 = r12.scrollerLabelTextBounds
            float r2 = r12.scrollerX
            int r4 = r1.width()
            float r4 = (float) r4
            r7 = 1073741824(0x40000000, float:2.0)
            float r4 = r4 / r7
            float r2 = r2 - r4
            int r2 = (int) r2
            int r4 = r12.graphMartinTop
            int r4 = -r4
            android.content.res.Resources r8 = r12.getResources()
            int r9 = com.eternal.data.C1835R.dimen.dp_6
            int r8 = r8.getDimensionPixelSize(r9)
            int r4 = r4 + r8
            r1.offsetTo(r2, r4)
            android.graphics.Rect r1 = r12.scrollerLabelTextBounds
            int r1 = r1.left
            if (r1 >= r0) goto L_0x0081
            android.graphics.Rect r1 = r12.scrollerLabelTextBounds
            int r2 = r1.top
            r1.offsetTo(r0, r2)
        L_0x0081:
            android.graphics.Rect r1 = r12.scrollerLabelTextBounds
            int r1 = r1.right
            int r1 = r1 + r0
            int r2 = r12.graphWidth
            if (r1 <= r2) goto L_0x0099
            android.graphics.Rect r1 = r12.scrollerLabelTextBounds
            int r4 = r1.width()
            int r2 = r2 - r4
            int r2 = r2 - r0
            android.graphics.Rect r4 = r12.scrollerLabelTextBounds
            int r4 = r4.top
            r1.offsetTo(r2, r4)
        L_0x0099:
            android.graphics.RectF r1 = r12.scrollerLabelBounds
            android.graphics.Rect r2 = r12.scrollerLabelTextBounds
            int r2 = r2.left
            int r2 = r2 - r0
            float r2 = (float) r2
            android.graphics.Rect r4 = r12.scrollerLabelTextBounds
            int r4 = r4.top
            android.content.res.Resources r8 = r12.getResources()
            int r9 = com.eternal.data.C1835R.dimen.dp_6
            int r8 = r8.getDimensionPixelSize(r9)
            int r4 = r4 - r8
            float r4 = (float) r4
            android.graphics.Rect r8 = r12.scrollerLabelTextBounds
            int r8 = r8.right
            int r8 = r8 + r0
            float r0 = (float) r8
            android.graphics.Rect r8 = r12.scrollerLabelTextBounds
            int r8 = r8.bottom
            android.content.res.Resources r9 = r12.getResources()
            int r10 = com.eternal.data.C1835R.dimen.dp_6
            int r9 = r9.getDimensionPixelSize(r10)
            int r8 = r8 + r9
            float r8 = (float) r8
            r1.set(r2, r4, r0, r8)
            android.graphics.Paint r0 = r12.scrollerLabelTextPaint
            com.eternal.data.ui.GraphTmp r1 = r12.graphTmp
            android.graphics.Rect r1 = r1.scrollerTmpLabelTextBounds
            r0.getTextBounds(r5, r3, r6, r1)
            com.eternal.data.ui.GraphTmp r0 = r12.graphTmp
            android.graphics.Rect r0 = r0.scrollerTmpLabelTextBounds
            int r0 = r0.height()
            com.eternal.data.ui.GraphTmp r1 = r12.graphTmp
            int r2 = r1.scrollerTmp
            float r2 = (float) r2
            com.eternal.data.ui.GraphTmp r4 = r12.graphTmp
            float r2 = r12.yFromValue(r2, r4)
            r1.scrollerTmpY = r2
            android.graphics.Paint r1 = r12.scrollerLabelTextPaint
            com.eternal.data.ui.GraphTmp r2 = r12.graphTmp
            java.lang.String r2 = r2.tmpLabelText
            com.eternal.data.ui.GraphTmp r4 = r12.graphTmp
            java.lang.String r4 = r4.tmpLabelText
            int r4 = r4.length()
            com.eternal.data.ui.GraphTmp r5 = r12.graphTmp
            android.graphics.Rect r5 = r5.scrollerTmpLabelTextBounds
            r1.getTextBounds(r2, r3, r4, r5)
            com.eternal.data.ui.GraphTmp r1 = r12.graphTmp
            android.graphics.Rect r1 = r1.scrollerTmpLabelTextBounds
            r1.top = r3
            com.eternal.data.ui.GraphTmp r1 = r12.graphTmp
            android.graphics.Rect r1 = r1.scrollerTmpLabelTextBounds
            r1.bottom = r0
            int r1 = r12.scrollerHum
            float r1 = (float) r1
            com.eternal.data.ui.GraphHum r2 = r12.graphHum
            float r1 = r12.yFromValue(r1, r2)
            r12.scrollerHumY = r1
            android.graphics.Paint r1 = r12.scrollerLabelTextPaint
            java.lang.String r2 = r12.humLabelText
            int r4 = r2.length()
            android.graphics.Rect r5 = r12.scrollerHumLabelTextBounds
            r1.getTextBounds(r2, r3, r4, r5)
            android.graphics.Rect r1 = r12.scrollerHumLabelTextBounds
            r1.top = r3
            android.graphics.Rect r1 = r12.scrollerHumLabelTextBounds
            r1.bottom = r0
            int r1 = r12.scrollerVpd
            float r1 = (float) r1
            com.eternal.data.ui.GraphVpd r2 = r12.graphVpd
            float r1 = r12.yFromValue(r1, r2)
            r12.scrollerVpdY = r1
            android.graphics.Paint r1 = r12.scrollerLabelTextPaint
            java.lang.String r2 = r12.vpdLabelText
            int r4 = r2.length()
            android.graphics.Rect r5 = r12.scrollerVpdLabelTextBounds
            r1.getTextBounds(r2, r3, r4, r5)
            android.graphics.Rect r1 = r12.scrollerVpdLabelTextBounds
            r1.top = r3
            android.graphics.Rect r1 = r12.scrollerVpdLabelTextBounds
            r1.bottom = r0
            java.util.concurrent.CopyOnWriteArrayList<com.eternal.data.ui.GraphLevel> r1 = r12.graphLevels
            java.util.Iterator r1 = r1.iterator()
        L_0x014f:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0175
            java.lang.Object r2 = r1.next()
            com.eternal.data.ui.GraphLevel r2 = (com.eternal.data.p007ui.GraphLevel) r2
            int r4 = r2.value
            float r4 = (float) r4
            float r4 = r12.yFromLevelValue(r4)
            r2.setScrollerY(r4)
            android.graphics.Paint r4 = r12.scrollerLabelTextPaint
            android.content.res.Resources r5 = r12.getResources()
            int r8 = com.eternal.data.C1835R.dimen.dp_3
            int r5 = r5.getDimensionPixelSize(r8)
            r2.setTextBounds(r4, r0, r5)
            goto L_0x014f
        L_0x0175:
            com.eternal.data.ui.GraphTmp r1 = r12.graphTmp
            float r1 = r1.scrollerTmpY
            float r2 = r12.scrollerHumY
            float r4 = r12.scrollerVpdY
            android.content.res.Resources r5 = r12.getResources()
            int r8 = com.eternal.data.C1835R.dimen.dp_12
            int r5 = r5.getDimensionPixelSize(r8)
            android.content.res.Resources r8 = r12.getResources()
            int r9 = com.eternal.data.C1835R.dimen.dp_3
            int r8 = r8.getDimensionPixelSize(r9)
            int r5 = r5 + r8
            int r5 = r5 + r0
            float r0 = (float) r5
            boolean r5 = r12.humVisible
            if (r5 == 0) goto L_0x02bb
            boolean r5 = r12.vpdVisible
            if (r5 == 0) goto L_0x02bb
            com.eternal.data.ui.GraphTmp r1 = r12.graphTmp
            float r1 = r1.scrollerTmpY
            float r2 = r12.scrollerHumY
            float r4 = r12.scrollerVpdY
            float r2 = java.lang.Math.min(r2, r4)
            float r1 = java.lang.Math.min(r1, r2)
            com.eternal.data.ui.GraphTmp r2 = r12.graphTmp
            float r2 = r2.scrollerTmpY
            float r4 = r12.scrollerHumY
            float r5 = r12.scrollerVpdY
            float r4 = java.lang.Math.max(r4, r5)
            float r2 = java.lang.Math.max(r2, r4)
            com.eternal.data.ui.GraphTmp r4 = r12.graphTmp
            float r4 = r4.scrollerTmpY
            float r5 = r12.scrollerHumY
            float r4 = java.lang.Math.max(r4, r5)
            float r5 = r12.scrollerVpdY
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x01dd
            com.eternal.data.ui.GraphTmp r4 = r12.graphTmp
            float r4 = r4.scrollerTmpY
            float r5 = r12.scrollerHumY
            float r4 = java.lang.Math.min(r4, r5)
            float r5 = r12.scrollerVpdY
            float r4 = java.lang.Math.max(r4, r5)
            goto L_0x01ed
        L_0x01dd:
            com.eternal.data.ui.GraphTmp r4 = r12.graphTmp
            float r4 = r4.scrollerTmpY
            float r5 = r12.scrollerHumY
            float r4 = java.lang.Math.max(r4, r5)
            float r5 = r12.scrollerVpdY
            float r4 = java.lang.Math.min(r4, r5)
        L_0x01ed:
            float r5 = r1 + r0
            int r8 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r8 >= 0) goto L_0x01f5
            r8 = 1
            goto L_0x01f6
        L_0x01f5:
            r8 = 0
        L_0x01f6:
            float r9 = r4 + r0
            int r10 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r10 >= 0) goto L_0x01fe
            r10 = 1
            goto L_0x01ff
        L_0x01fe:
            r10 = 0
        L_0x01ff:
            if (r8 != 0) goto L_0x020e
            if (r10 != 0) goto L_0x020e
        L_0x0203:
            float r2 = r2 - r1
            float r2 = r2 / r7
            float r2 = r2 + r1
            float r1 = r2 - r0
            float r4 = r2 + r0
            r11 = r4
            r4 = r2
            r2 = r11
            goto L_0x023c
        L_0x020e:
            if (r8 != 0) goto L_0x022a
            float r5 = r1 - r4
            float r5 = r5 + r0
            float r5 = r5 / r7
            float r9 = r9 + r5
            int r8 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r8 >= 0) goto L_0x021b
            r8 = 1
            goto L_0x021c
        L_0x021b:
            r8 = 0
        L_0x021c:
            if (r8 == 0) goto L_0x0221
            float r1 = r1 - r5
            float r4 = r4 + r5
            goto L_0x023c
        L_0x0221:
            float r2 = r2 - r1
            float r2 = r2 / r7
            float r4 = r2 + r1
            float r1 = r4 - r0
            float r2 = r4 + r0
            goto L_0x023c
        L_0x022a:
            if (r10 != 0) goto L_0x023c
            float r8 = r4 - r2
            float r8 = r8 + r0
            float r8 = r8 / r7
            float r5 = r5 + r8
            int r5 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r5 >= 0) goto L_0x0237
            r5 = 1
            goto L_0x0238
        L_0x0237:
            r5 = 0
        L_0x0238:
            if (r5 == 0) goto L_0x0203
            float r4 = r4 - r8
            float r2 = r2 + r8
        L_0x023c:
            com.eternal.data.ui.GraphTmp r5 = r12.graphTmp
            float r5 = r5.scrollerTmpY
            float r8 = r12.scrollerHumY
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 > 0) goto L_0x024e
            float r5 = r12.scrollerVpdY
            int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x024e
            goto L_0x02b8
        L_0x024e:
            com.eternal.data.ui.GraphTmp r5 = r12.graphTmp
            float r5 = r5.scrollerTmpY
            float r8 = r12.scrollerVpdY
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 > 0) goto L_0x0260
            float r5 = r12.scrollerHumY
            int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x0260
            goto L_0x02bb
        L_0x0260:
            float r5 = r12.scrollerHumY
            com.eternal.data.ui.GraphTmp r8 = r12.graphTmp
            float r8 = r8.scrollerTmpY
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 > 0) goto L_0x0279
            com.eternal.data.ui.GraphTmp r5 = r12.graphTmp
            float r5 = r5.scrollerTmpY
            float r8 = r12.scrollerVpdY
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 > 0) goto L_0x0279
            r11 = r2
            r2 = r1
            r1 = r4
            r4 = r11
            goto L_0x02bb
        L_0x0279:
            float r5 = r12.scrollerHumY
            float r8 = r12.scrollerVpdY
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 > 0) goto L_0x028d
            com.eternal.data.ui.GraphTmp r5 = r12.graphTmp
            float r5 = r5.scrollerTmpY
            int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x028d
            r11 = r2
            r2 = r1
        L_0x028b:
            r1 = r11
            goto L_0x02bb
        L_0x028d:
            float r5 = r12.scrollerVpdY
            com.eternal.data.ui.GraphTmp r8 = r12.graphTmp
            float r8 = r8.scrollerTmpY
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 > 0) goto L_0x02a4
            com.eternal.data.ui.GraphTmp r5 = r12.graphTmp
            float r5 = r5.scrollerTmpY
            float r8 = r12.scrollerHumY
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 > 0) goto L_0x02a4
            r11 = r4
            r4 = r1
            goto L_0x028b
        L_0x02a4:
            float r5 = r12.scrollerVpdY
            float r8 = r12.scrollerHumY
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 > 0) goto L_0x02b8
            com.eternal.data.ui.GraphTmp r5 = r12.graphTmp
            float r5 = r5.scrollerTmpY
            int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x02b8
            r11 = r4
            r4 = r1
            r1 = r2
            goto L_0x02ba
        L_0x02b8:
            r11 = r4
            r4 = r2
        L_0x02ba:
            r2 = r11
        L_0x02bb:
            boolean r5 = r12.humVisible
            if (r5 == 0) goto L_0x02e2
            boolean r5 = r12.vpdVisible
            if (r5 == 0) goto L_0x02e2
            com.eternal.data.ui.GraphTmp r5 = r12.graphTmp
            android.graphics.Rect r5 = r5.scrollerTmpLabelTextBounds
            int r5 = r5.width()
            android.graphics.Rect r8 = r12.scrollerHumLabelTextBounds
            int r8 = r8.width()
            int r5 = java.lang.Math.max(r5, r8)
            float r5 = (float) r5
            android.graphics.Rect r8 = r12.scrollerVpdLabelTextBounds
            int r8 = r8.width()
            float r8 = (float) r8
            float r5 = java.lang.Math.max(r5, r8)
            goto L_0x02eb
        L_0x02e2:
            com.eternal.data.ui.GraphTmp r5 = r12.graphTmp
            android.graphics.Rect r5 = r5.scrollerTmpLabelTextBounds
            int r5 = r5.width()
            float r5 = (float) r5
        L_0x02eb:
            java.util.concurrent.CopyOnWriteArrayList<com.eternal.data.ui.GraphLevel> r8 = r12.graphLevels
            java.util.Iterator r8 = r8.iterator()
        L_0x02f1:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0306
            java.lang.Object r9 = r8.next()
            com.eternal.data.ui.GraphLevel r9 = (com.eternal.data.p007ui.GraphLevel) r9
            float r9 = r9.getTextWidth()
            float r5 = java.lang.Math.max(r5, r9)
            goto L_0x02f1
        L_0x0306:
            int r8 = r12.graphWidth
            float r8 = (float) r8
            float r9 = r12.scrollerX
            float r8 = r8 - r9
            android.content.res.Resources r9 = r12.getResources()
            int r10 = com.eternal.data.C1835R.dimen.dp_18
            int r9 = r9.getDimensionPixelSize(r10)
            float r9 = (float) r9
            float r5 = r5 + r9
            int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x031d
            r3 = 1
        L_0x031d:
            r12.scrollerRight = r3
            if (r3 == 0) goto L_0x0380
            com.eternal.data.ui.GraphTmp r3 = r12.graphTmp
            android.graphics.Rect r3 = r3.scrollerTmpLabelTextBounds
            float r5 = r12.scrollerX
            android.content.res.Resources r6 = r12.getResources()
            int r8 = com.eternal.data.C1835R.dimen.dp_12
            int r6 = r6.getDimensionPixelSize(r8)
            float r6 = (float) r6
            float r5 = r5 + r6
            int r5 = (int) r5
            com.eternal.data.ui.GraphTmp r6 = r12.graphTmp
            android.graphics.Rect r6 = r6.scrollerTmpLabelTextBounds
            int r6 = r6.height()
            float r6 = (float) r6
            float r6 = r6 / r7
            float r1 = r1 - r6
            int r1 = (int) r1
            r3.offsetTo(r5, r1)
            android.graphics.Rect r1 = r12.scrollerHumLabelTextBounds
            float r3 = r12.scrollerX
            android.content.res.Resources r5 = r12.getResources()
            int r6 = com.eternal.data.C1835R.dimen.dp_12
            int r5 = r5.getDimensionPixelSize(r6)
            float r5 = (float) r5
            float r3 = r3 + r5
            int r3 = (int) r3
            android.graphics.Rect r5 = r12.scrollerHumLabelTextBounds
            int r5 = r5.height()
            float r5 = (float) r5
            float r5 = r5 / r7
            float r2 = r2 - r5
            int r2 = (int) r2
            r1.offsetTo(r3, r2)
            android.graphics.Rect r1 = r12.scrollerVpdLabelTextBounds
            float r2 = r12.scrollerX
            android.content.res.Resources r3 = r12.getResources()
            int r5 = com.eternal.data.C1835R.dimen.dp_12
            int r3 = r3.getDimensionPixelSize(r5)
            float r3 = (float) r3
            float r2 = r2 + r3
            int r2 = (int) r2
            android.graphics.Rect r3 = r12.scrollerVpdLabelTextBounds
            int r3 = r3.height()
            float r3 = (float) r3
            float r3 = r3 / r7
            float r4 = r4 - r3
            int r3 = (int) r4
            r1.offsetTo(r2, r3)
            goto L_0x03f5
        L_0x0380:
            com.eternal.data.ui.GraphTmp r3 = r12.graphTmp
            android.graphics.Rect r3 = r3.scrollerTmpLabelTextBounds
            float r5 = r12.scrollerX
            android.content.res.Resources r6 = r12.getResources()
            int r8 = com.eternal.data.C1835R.dimen.dp_12
            int r6 = r6.getDimensionPixelSize(r8)
            float r6 = (float) r6
            float r5 = r5 - r6
            int r5 = (int) r5
            com.eternal.data.ui.GraphTmp r6 = r12.graphTmp
            android.graphics.Rect r6 = r6.scrollerTmpLabelTextBounds
            int r6 = r6.width()
            int r5 = r5 - r6
            com.eternal.data.ui.GraphTmp r6 = r12.graphTmp
            android.graphics.Rect r6 = r6.scrollerTmpLabelTextBounds
            int r6 = r6.height()
            float r6 = (float) r6
            float r6 = r6 / r7
            float r1 = r1 - r6
            int r1 = (int) r1
            r3.offsetTo(r5, r1)
            android.graphics.Rect r1 = r12.scrollerHumLabelTextBounds
            float r3 = r12.scrollerX
            android.content.res.Resources r5 = r12.getResources()
            int r6 = com.eternal.data.C1835R.dimen.dp_12
            int r5 = r5.getDimensionPixelSize(r6)
            float r5 = (float) r5
            float r3 = r3 - r5
            int r3 = (int) r3
            android.graphics.Rect r5 = r12.scrollerHumLabelTextBounds
            int r5 = r5.width()
            int r3 = r3 - r5
            android.graphics.Rect r5 = r12.scrollerHumLabelTextBounds
            int r5 = r5.height()
            float r5 = (float) r5
            float r5 = r5 / r7
            float r2 = r2 - r5
            int r2 = (int) r2
            r1.offsetTo(r3, r2)
            android.graphics.Rect r1 = r12.scrollerVpdLabelTextBounds
            float r2 = r12.scrollerX
            android.content.res.Resources r3 = r12.getResources()
            int r5 = com.eternal.data.C1835R.dimen.dp_12
            int r3 = r3.getDimensionPixelSize(r5)
            float r3 = (float) r3
            float r2 = r2 - r3
            int r2 = (int) r2
            android.graphics.Rect r3 = r12.scrollerVpdLabelTextBounds
            int r3 = r3.width()
            int r2 = r2 - r3
            android.graphics.Rect r3 = r12.scrollerVpdLabelTextBounds
            int r3 = r3.height()
            float r3 = (float) r3
            float r3 = r3 / r7
            float r4 = r4 - r3
            int r3 = (int) r4
            r1.offsetTo(r2, r3)
        L_0x03f5:
            boolean r1 = r12.showBottomGraph
            if (r1 == 0) goto L_0x0409
            float r1 = r12.graphHeight
            float r2 = r12.timeBarHeight
            float r3 = r1 + r2
            float r4 = r12.graphLevelHeight
            float r1 = r1 + r4
            float r1 = r1 + r2
            float r0 = r0 / r7
            boolean r2 = r12.scrollerRight
            r12.reorder(r3, r1, r0, r2)
        L_0x0409:
            com.eternal.data.ui.GraphTmp r0 = r12.graphTmp
            android.graphics.RectF r0 = r0.scrollerTmpLabelBounds
            com.eternal.data.ui.GraphTmp r1 = r12.graphTmp
            android.graphics.Rect r1 = r1.scrollerTmpLabelTextBounds
            int r1 = r1.left
            android.content.res.Resources r2 = r12.getResources()
            int r3 = com.eternal.data.C1835R.dimen.dp_6
            int r2 = r2.getDimensionPixelSize(r3)
            int r1 = r1 - r2
            float r1 = (float) r1
            com.eternal.data.ui.GraphTmp r2 = r12.graphTmp
            android.graphics.Rect r2 = r2.scrollerTmpLabelTextBounds
            int r2 = r2.top
            android.content.res.Resources r3 = r12.getResources()
            int r4 = com.eternal.data.C1835R.dimen.dp_6
            int r3 = r3.getDimensionPixelSize(r4)
            int r2 = r2 - r3
            float r2 = (float) r2
            com.eternal.data.ui.GraphTmp r3 = r12.graphTmp
            android.graphics.Rect r3 = r3.scrollerTmpLabelTextBounds
            int r3 = r3.right
            android.content.res.Resources r4 = r12.getResources()
            int r5 = com.eternal.data.C1835R.dimen.dp_6
            int r4 = r4.getDimensionPixelSize(r5)
            int r3 = r3 + r4
            float r3 = (float) r3
            com.eternal.data.ui.GraphTmp r4 = r12.graphTmp
            android.graphics.Rect r4 = r4.scrollerTmpLabelTextBounds
            int r4 = r4.bottom
            android.content.res.Resources r5 = r12.getResources()
            int r6 = com.eternal.data.C1835R.dimen.dp_6
            int r5 = r5.getDimensionPixelSize(r6)
            int r4 = r4 + r5
            float r4 = (float) r4
            r0.set(r1, r2, r3, r4)
            android.graphics.RectF r0 = r12.scrollerHumLabelBounds
            android.graphics.Rect r1 = r12.scrollerHumLabelTextBounds
            int r1 = r1.left
            android.content.res.Resources r2 = r12.getResources()
            int r3 = com.eternal.data.C1835R.dimen.dp_6
            int r2 = r2.getDimensionPixelSize(r3)
            int r1 = r1 - r2
            float r1 = (float) r1
            android.graphics.Rect r2 = r12.scrollerHumLabelTextBounds
            int r2 = r2.top
            android.content.res.Resources r3 = r12.getResources()
            int r4 = com.eternal.data.C1835R.dimen.dp_6
            int r3 = r3.getDimensionPixelSize(r4)
            int r2 = r2 - r3
            float r2 = (float) r2
            android.graphics.Rect r3 = r12.scrollerHumLabelTextBounds
            int r3 = r3.right
            android.content.res.Resources r4 = r12.getResources()
            int r5 = com.eternal.data.C1835R.dimen.dp_6
            int r4 = r4.getDimensionPixelSize(r5)
            int r3 = r3 + r4
            float r3 = (float) r3
            android.graphics.Rect r4 = r12.scrollerHumLabelTextBounds
            int r4 = r4.bottom
            android.content.res.Resources r5 = r12.getResources()
            int r6 = com.eternal.data.C1835R.dimen.dp_6
            int r5 = r5.getDimensionPixelSize(r6)
            int r4 = r4 + r5
            float r4 = (float) r4
            r0.set(r1, r2, r3, r4)
            android.graphics.RectF r0 = r12.scrollerVpdLabelBounds
            android.graphics.Rect r1 = r12.scrollerVpdLabelTextBounds
            int r1 = r1.left
            android.content.res.Resources r2 = r12.getResources()
            int r3 = com.eternal.data.C1835R.dimen.dp_6
            int r2 = r2.getDimensionPixelSize(r3)
            int r1 = r1 - r2
            float r1 = (float) r1
            android.graphics.Rect r2 = r12.scrollerVpdLabelTextBounds
            int r2 = r2.top
            android.content.res.Resources r3 = r12.getResources()
            int r4 = com.eternal.data.C1835R.dimen.dp_6
            int r3 = r3.getDimensionPixelSize(r4)
            int r2 = r2 - r3
            float r2 = (float) r2
            android.graphics.Rect r3 = r12.scrollerVpdLabelTextBounds
            int r3 = r3.right
            android.content.res.Resources r4 = r12.getResources()
            int r5 = com.eternal.data.C1835R.dimen.dp_6
            int r4 = r4.getDimensionPixelSize(r5)
            int r3 = r3 + r4
            float r3 = (float) r3
            android.graphics.Rect r4 = r12.scrollerVpdLabelTextBounds
            int r4 = r4.bottom
            android.content.res.Resources r5 = r12.getResources()
            int r6 = com.eternal.data.C1835R.dimen.dp_6
            int r5 = r5.getDimensionPixelSize(r6)
            int r4 = r4 + r5
            float r4 = (float) r4
            r0.set(r1, r2, r3, r4)
            boolean r0 = r12.showBottomGraph
            if (r0 == 0) goto L_0x05da
            java.util.concurrent.CopyOnWriteArrayList<com.eternal.data.ui.GraphLevel> r0 = r12.graphLevels
            java.util.Iterator r0 = r0.iterator()
        L_0x04ec:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x05da
            java.lang.Object r1 = r0.next()
            com.eternal.data.ui.GraphLevel r1 = (com.eternal.data.p007ui.GraphLevel) r1
            boolean r2 = r1.showIcon
            if (r2 == 0) goto L_0x0593
            android.graphics.RectF r2 = r1.bitmapBounds
            android.graphics.Rect r3 = r1.scrollerTextBounds1
            int r3 = r3.right
            int r4 = r1.padding
            int r4 = r4 * 2
            int r3 = r3 + r4
            float r3 = (float) r3
            android.graphics.Rect r4 = r1.scrollerTextBounds1
            int r4 = r4.top
            float r4 = (float) r4
            android.graphics.Rect r5 = r1.scrollerTextBounds1
            int r5 = r5.right
            int r6 = r1.padding
            int r6 = r6 * 2
            int r5 = r5 + r6
            float r5 = (float) r5
            android.graphics.RectF r6 = r1.bitmapBounds
            float r6 = r6.width()
            float r5 = r5 + r6
            android.graphics.Rect r6 = r1.scrollerTextBounds1
            int r6 = r6.bottom
            float r6 = (float) r6
            r2.set(r3, r4, r5, r6)
            android.graphics.Rect r2 = r1.scrollerTextBounds2
            android.graphics.RectF r3 = r1.bitmapBounds
            float r3 = r3.right
            int r4 = r1.padding
            float r4 = (float) r4
            float r3 = r3 + r4
            int r3 = (int) r3
            android.graphics.Rect r4 = r1.scrollerTextBounds1
            int r4 = r4.top
            android.graphics.RectF r5 = r1.bitmapBounds
            float r5 = r5.right
            int r6 = r1.padding
            float r6 = (float) r6
            float r5 = r5 + r6
            int r5 = (int) r5
            android.graphics.Rect r6 = r1.scrollerTextBounds2
            int r6 = r6.width()
            int r5 = r5 + r6
            android.graphics.Rect r6 = r1.scrollerTextBounds1
            int r6 = r6.bottom
            r2.set(r3, r4, r5, r6)
            android.graphics.RectF r2 = r1.scrollerBounds
            android.graphics.Rect r3 = r1.scrollerTextBounds1
            int r3 = r3.left
            android.content.res.Resources r4 = r12.getResources()
            int r5 = com.eternal.data.C1835R.dimen.dp_6
            int r4 = r4.getDimensionPixelSize(r5)
            int r3 = r3 - r4
            float r3 = (float) r3
            android.graphics.Rect r4 = r1.scrollerTextBounds1
            int r4 = r4.top
            android.content.res.Resources r5 = r12.getResources()
            int r6 = com.eternal.data.C1835R.dimen.dp_6
            int r5 = r5.getDimensionPixelSize(r6)
            int r4 = r4 - r5
            float r4 = (float) r4
            android.graphics.Rect r5 = r1.scrollerTextBounds2
            int r5 = r5.right
            android.content.res.Resources r6 = r12.getResources()
            int r7 = com.eternal.data.C1835R.dimen.dp_6
            int r6 = r6.getDimensionPixelSize(r7)
            int r5 = r5 + r6
            float r5 = (float) r5
            android.graphics.Rect r1 = r1.scrollerTextBounds2
            int r1 = r1.bottom
            android.content.res.Resources r6 = r12.getResources()
            int r7 = com.eternal.data.C1835R.dimen.dp_6
            int r6 = r6.getDimensionPixelSize(r7)
            int r1 = r1 + r6
            float r1 = (float) r1
            r2.set(r3, r4, r5, r1)
            goto L_0x04ec
        L_0x0593:
            android.graphics.RectF r2 = r1.scrollerBounds
            android.graphics.Rect r3 = r1.scrollerTextBounds1
            int r3 = r3.left
            android.content.res.Resources r4 = r12.getResources()
            int r5 = com.eternal.data.C1835R.dimen.dp_6
            int r4 = r4.getDimensionPixelSize(r5)
            int r3 = r3 - r4
            float r3 = (float) r3
            android.graphics.Rect r4 = r1.scrollerTextBounds1
            int r4 = r4.top
            android.content.res.Resources r5 = r12.getResources()
            int r6 = com.eternal.data.C1835R.dimen.dp_6
            int r5 = r5.getDimensionPixelSize(r6)
            int r4 = r4 - r5
            float r4 = (float) r4
            android.graphics.Rect r5 = r1.scrollerTextBounds1
            int r5 = r5.right
            android.content.res.Resources r6 = r12.getResources()
            int r7 = com.eternal.data.C1835R.dimen.dp_6
            int r6 = r6.getDimensionPixelSize(r7)
            int r5 = r5 + r6
            float r5 = (float) r5
            android.graphics.Rect r1 = r1.scrollerTextBounds1
            int r1 = r1.bottom
            android.content.res.Resources r6 = r12.getResources()
            int r7 = com.eternal.data.C1835R.dimen.dp_6
            int r6 = r6.getDimensionPixelSize(r7)
            int r1 = r1 + r6
            float r1 = (float) r1
            r2.set(r3, r4, r5, r1)
            goto L_0x04ec
        L_0x05da:
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r12.lock
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.unlock()
            return
        L_0x05e4:
            r12.scrollerOpen = r3
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r12.lock
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.unlock()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.data.p007ui.GraphView.scaleScroller():void");
    }

    public void reorder(float f, float f2, float f3, boolean z) {
        int i;
        boolean z2;
        ArrayList<GraphLevel> arrayList = new ArrayList<>();
        Iterator<GraphLevel> it = this.graphLevels.iterator();
        while (it.hasNext()) {
            GraphLevel next = it.next();
            if (!next.merge && (1 & (this.showPortLines >> next.port)) != 0) {
                arrayList.add(next);
            }
        }
        Collections.sort(arrayList, new Comparator<GraphLevel>() {
            public int compare(GraphLevel graphLevel, GraphLevel graphLevel2) {
                return (int) (graphLevel.scrollerY - graphLevel2.scrollerY);
            }
        });
        ArrayList<Range> arrayList2 = new ArrayList<>();
        for (GraphLevel graphLevel : arrayList) {
            arrayList2.add(new Range(graphLevel.scrollerY - f3, graphLevel.scrollerY + f3));
        }
        do {
            Iterator it2 = arrayList2.iterator();
            i = 0;
            if (!it2.hasNext()) {
                break;
            }
            Range range = (Range) it2.next();
            z2 = false;
            while (it2.hasNext()) {
                Range range2 = (Range) it2.next();
                if (range.end > range2.start) {
                    float f4 = (range.end - range2.start) / 2.0f;
                    float f5 = range.start - f4;
                    float f6 = range2.end + f4;
                    if (f5 < f) {
                        f6 = (f6 - f5) + f;
                        f5 = f;
                    }
                    if (f6 > f2) {
                        f5 = f2 - (f6 - f5);
                        f6 = f2;
                    }
                    range.start = f5;
                    range.end = f6;
                    it2.remove();
                    z2 = true;
                } else {
                    range = range2;
                }
            }
        } while (z2);
        for (Range range3 : arrayList2) {
            while (range3.end - range3.start > 0.0f && arrayList.size() > i) {
                if (z) {
                    ((GraphLevel) arrayList.get(i)).scrollerTextBounds1.offsetTo((int) (this.scrollerX + ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_12))), (int) ((range3.start + f3) - (((float) ((GraphLevel) arrayList.get(i)).scrollerTextBounds1.height()) / 2.0f)));
                } else {
                    ((GraphLevel) arrayList.get(i)).scrollerTextBounds1.offsetTo(((int) (this.scrollerX - ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_12)))) - ((int) ((GraphLevel) arrayList.get(i)).getTextWidth()), (int) ((range3.start + f3) - (((float) ((GraphLevel) arrayList.get(i)).scrollerTextBounds1.height()) / 2.0f)));
                }
                range3.start += f3 * 2.0f;
                i++;
            }
        }
    }

    /* renamed from: com.eternal.data.ui.GraphView$Range */
    static class Range {
        float end;
        float start;

        public Range(float f, float f2) {
            this.start = f;
            this.end = f2;
        }
    }

    private void onPanEnd(MotionEvent motionEvent) {
        this.draggingScroller = false;
    }

    /* access modifiers changed from: private */
    public void onPanMoved(MotionEvent motionEvent, float f) {
        int i;
        if (this.draggingScroller) {
            positionScroller(motionEvent.getX());
            return;
        }
        long j = this.adapter.minTime;
        long j2 = this.adapter.minTime + ((long) this.adapter.timeDistance);
        if (j2 > j && (i = this.graphWidth) != 0) {
            int i2 = (int) ((f / ((float) i)) * ((float) (j2 - j)));
            GraphAdapter graphAdapter = this.adapter;
            graphAdapter.setVisibleTimeSpace(j + ((long) i2), graphAdapter.timeDistance);
        }
    }

    /* access modifiers changed from: private */
    public void onPanStart(MotionEvent motionEvent) {
        if (this.scrollerOpen) {
            if (Math.abs(motionEvent.getX() - this.scrollerX) < ((float) ConvertUtils.sp2px(40.0f))) {
                this.draggingScroller = true;
            } else if (this.scrollerLabelBounds.left < motionEvent.getX() && this.scrollerLabelBounds.right > motionEvent.getX() && this.scrollerLabelBounds.top + ((float) this.graphMartinTop) < motionEvent.getX() && this.scrollerLabelBounds.bottom + ((float) this.graphMartinTop) > motionEvent.getY()) {
                this.draggingScroller = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void onSlideMoved(MotionEvent motionEvent, float f) {
        if (this.adapter.dataMaxTime > this.adapter.dataMinTime && this.sliderWidth != 0) {
            long min = Math.min(Math.max(this.adapter.minTime - ((long) ((int) ((f / (this.scrollBarWidth - ((float) this.sliderWidth))) * ((float) (this.adapter.dataTimeDistance - ((long) this.adapter.timeDistance)))))), this.adapter.dataMinTime), this.adapter.dataMaxTime - ((long) this.adapter.timeDistance));
            GraphAdapter graphAdapter = this.adapter;
            graphAdapter.setVisibleTimeSpace(min, graphAdapter.timeDistance);
        }
    }

    /* access modifiers changed from: private */
    public void onScrollBarTap(MotionEvent motionEvent) {
        long j;
        if (this.sliderVisible && this.adapter.dataMaxTime > this.adapter.dataMinTime && this.sliderWidth != 0) {
            if (motionEvent.getX() < this.sliderBounds.centerX()) {
                j = this.adapter.minTime - ((long) (this.adapter.timeDistance / 5));
            } else {
                j = this.adapter.minTime + ((long) (this.adapter.timeDistance / 5));
            }
            long min = Math.min(Math.max(j, this.adapter.dataMinTime), this.adapter.dataMaxTime - ((long) this.adapter.timeDistance));
            GraphAdapter graphAdapter = this.adapter;
            graphAdapter.setVisibleTimeSpace(min, graphAdapter.timeDistance);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            if (this.isPanning) {
                onPanEnd(motionEvent);
            }
            this.isPanning = false;
            this.isSliding = false;
            getParent().requestDisallowInterceptTouchEvent(false);
            getParent().getParent().getParent();
        }
        this.scaleGestureDetector.onTouchEvent(motionEvent);
        if (!this.scaleGestureDetector.isInProgress()) {
            this.gestureDetector.onTouchEvent(motionEvent);
            if ((this.isPanning || this.isSliding) && motionEvent.getAction() == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (motionEvent.getAction() == 2) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return true;
    }

    public void draw() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str;
        String str2;
        SurfaceHolder surfaceHolder = this.mHolder;
        if (surfaceHolder != null) {
            Canvas lockCanvas = surfaceHolder.lockCanvas();
            if (lockCanvas == null) {
                KLog.m65e("canvas has locked");
                return;
            }
            lockCanvas.drawColor(ContextCompat.getColor(getContext(), C1835R.C1836color.f152bg));
            lockCanvas.save();
            int i6 = -32768;
            if (this.adapter.length <= 0 || ((this.adapter.length < 2 || this.adapter.isEmptyData()) && this.adapter.isInit())) {
                i5 = -32768;
                i4 = -32768;
                i3 = -32768;
                i2 = -32768;
                i = -32768;
            } else {
                i6 = this.graphTmp.getMax();
                i5 = this.graphHum.getMax();
                i4 = this.graphVpd.getMax();
                i3 = this.graphTmp.getMin();
                i2 = this.graphHum.getMin();
                i = this.graphVpd.getMin();
            }
            lockCanvas.translate(0.0f, (float) this.graphMartinTop);
            boolean z = this.showTmpLine;
            if (z && this.humVisible && this.showHumLine) {
                str = this.graphTmp.formatValue2((float) i6, 1.05f) + " / " + this.graphHum.formatValue2((float) i5, 1.05f);
            } else if (z) {
                str = this.graphTmp.formatValue2((float) i6, 1.05f);
            } else {
                str = (!this.humVisible || !this.showHumLine) ? "" : this.graphHum.formatValue2((float) i5, 1.05f);
            }
            float caculateTextCenterHeightDistancByBaseLine = caculateTextCenterHeightDistancByBaseLine(this.graphLabelsTextPaint) - ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_6));
            lockCanvas.drawText(str, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_16), ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_0_5)) + caculateTextCenterHeightDistancByBaseLine, this.graphLabelsTextPaint);
            if (this.vpdVisible && this.showVpdLine) {
                String formatValue2 = this.graphVpd.formatValue2((float) i4, 1.05f);
                lockCanvas.drawText(formatValue2, ((float) (this.graphWidth - getResources().getDimensionPixelSize(C1835R.dimen.dp_16))) - this.graphLabelsTextPaint.measureText(formatValue2), caculateTextCenterHeightDistancByBaseLine + ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_0_5)), this.graphLabelsTextPaint);
            }
            lockCanvas.drawLine(0.0f, 0.0f, (float) this.graphWidth, 0.0f, this.graphBoundsTopLinePaint);
            drawTimeLabels(lockCanvas);
            lockCanvas.translate(0.0f, this.graphHeight);
            lockCanvas.drawLine(0.0f, 0.0f, (float) this.graphWidth, 0.0f, this.graphBoundsLinePaint);
            lockCanvas.translate(0.0f, this.graphLevelHeight + this.timeBarHeight);
            lockCanvas.drawLine(0.0f, 0.0f, (float) this.graphWidth, 0.0f, this.graphBoundsLinePaint);
            lockCanvas.translate(0.0f, ((-this.graphHeight) - this.graphLevelHeight) - this.timeBarHeight);
            lockCanvas.save();
            lockCanvas.clipRect(0.0f, 0.0f, (float) this.graphWidth, this.graphHeight + this.graphLevelHeight + this.timeBarHeight);
            if (this.adapter.length < 2 && this.adapter.isInit()) {
                lockCanvas.drawText("No data is available for this period.", (((float) this.graphWidth) - this.graphLabelsTextPaint.measureText("No data is available for this period.")) / 2.0f, (this.graphHeight + this.graphLabelsTextPaint.getTextSize()) / 2.0f, this.graphLabelsTextPaint);
            }
            if (this.showTmpLine) {
                this.graphValuePaint.setColor(this.graphTmp.getDefaultColor());
                lockCanvas.drawPath(this.graphTmp.getGraphPath(), this.graphValuePaint);
                lockCanvas.drawPath(this.graphTmp.getMissPath(), this.missPaint);
            }
            if (this.humVisible && this.showHumLine) {
                this.graphValuePaint.setColor(this.graphHum.getDefaultColor());
                lockCanvas.drawPath(this.graphHum.getGraphPath(), this.graphValuePaint);
                lockCanvas.drawPath(this.graphHum.getMissPath(), this.missPaint);
            }
            if (this.vpdVisible && this.showVpdLine) {
                this.graphValuePaint.setColor(this.graphVpd.getDefaultColor());
                lockCanvas.drawPath(this.graphVpd.getGraphPath(), this.graphValuePaint);
                lockCanvas.drawPath(this.graphVpd.getMissPath(), this.missPaint);
            }
            Iterator<GraphLevel> it = this.graphLevels.iterator();
            while (it.hasNext()) {
                GraphLevel next = it.next();
                if (((this.showPortLines >> next.port) & 1) == 1) {
                    this.graphLevelValuePaint.setColor(next.getDefaultColor());
                    lockCanvas.drawPath(next.getGraphPath(), this.graphLevelValuePaint);
                    lockCanvas.drawPath(next.getMissPath(), this.levelMissPaint);
                }
            }
            lockCanvas.restore();
            if (this.scrollerOpen) {
                this.lock.readLock().lock();
                float f = this.scrollerX;
                lockCanvas.drawLine(f, (float) (-this.graphMartinTop), f, this.graphHeight + this.graphLevelHeight + this.timeBarHeight, this.scrollerVerticalLinePaint);
                this.scrollerLabelPaint.setColor(-4076584);
                float width = this.scrollerLabelBounds.width() / 2.0f;
                lockCanvas.drawRoundRect(this.scrollerLabelBounds, width, width, this.scrollerLabelPaint);
                this.scrollerTimePath.reset();
                this.scrollerTimePath.moveTo(this.scrollerX - ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_6)), this.scrollerLabelBounds.bottom);
                this.scrollerTimePath.lineTo(this.scrollerX, this.scrollerLabelBounds.bottom + ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_6)));
                this.scrollerTimePath.lineTo(this.scrollerX + ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_6)), this.scrollerLabelBounds.bottom);
                this.scrollerTimePath.lineTo(this.scrollerX + ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_6)), this.scrollerLabelBounds.centerY());
                this.scrollerTimePath.lineTo(this.scrollerX - ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_6)), this.scrollerLabelBounds.centerY());
                this.scrollerTimePath.close();
                lockCanvas.drawPath(this.scrollerTimePath, this.scrollerLabelPaint);
                String str3 = this.scrollerLabelText;
                if (!TextUtils.isEmpty(str3)) {
                    lockCanvas.drawText(str3, (float) this.scrollerLabelTextBounds.left, (float) this.scrollerLabelTextBounds.bottom, this.scrollerTimeLabelTextPaint);
                }
                this.scrollerLabelTextPaint.setColor(-1);
                String str4 = this.graphTmp.tmpLabelText;
                if (this.showTmpLine && !TextUtils.isEmpty(str4)) {
                    this.scrollerLinePaint.setColor(this.graphTmp.getDefaultColor());
                    this.scrollerLabelPaint.setColor(this.graphTmp.getDefaultColor());
                    lockCanvas.drawRect(this.graphTmp.scrollerTmpLabelBounds, this.scrollerRectPaint);
                    lockCanvas.drawRect(this.graphTmp.scrollerTmpLabelBounds, this.scrollerLinePaint);
                    lockCanvas.drawText(str4, (float) this.graphTmp.scrollerTmpLabelTextBounds.left, (float) this.graphTmp.scrollerTmpLabelTextBounds.bottom, this.scrollerLabelTextPaint);
                    lockCanvas.drawCircle(this.scrollerX, this.graphTmp.scrollerTmpY, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2_5), this.scrollerCirclePaint);
                    lockCanvas.drawCircle(this.scrollerX, this.graphTmp.scrollerTmpY, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2), this.scrollerLabelPaint);
                    lockCanvas.drawLine(this.scrollerX, this.graphTmp.scrollerTmpY, this.scrollerRight ? this.graphTmp.scrollerTmpLabelBounds.left : this.graphTmp.scrollerTmpLabelBounds.right, this.graphTmp.scrollerTmpLabelBounds.centerY(), this.scrollerLinePaint);
                }
                String str5 = this.humLabelText;
                if (this.humVisible && this.showHumLine && !TextUtils.isEmpty(str5)) {
                    this.scrollerLinePaint.setColor(this.graphHum.getDefaultColor());
                    this.scrollerLabelPaint.setColor(this.graphHum.getDefaultColor());
                    lockCanvas.drawRect(this.scrollerHumLabelBounds, this.scrollerRectPaint);
                    lockCanvas.drawRect(this.scrollerHumLabelBounds, this.scrollerLinePaint);
                    lockCanvas.drawText(str5, (float) this.scrollerHumLabelTextBounds.left, (float) this.scrollerHumLabelTextBounds.bottom, this.scrollerLabelTextPaint);
                    lockCanvas.drawCircle(this.scrollerX, this.scrollerHumY, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2_5), this.scrollerCirclePaint);
                    lockCanvas.drawCircle(this.scrollerX, this.scrollerHumY, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2), this.scrollerLabelPaint);
                    lockCanvas.drawLine(this.scrollerX, this.scrollerHumY, this.scrollerRight ? this.scrollerHumLabelBounds.left : this.scrollerHumLabelBounds.right, this.scrollerHumLabelBounds.centerY(), this.scrollerLinePaint);
                }
                String str6 = this.vpdLabelText;
                if (this.vpdVisible && this.showVpdLine && !TextUtils.isEmpty(str6)) {
                    this.scrollerLinePaint.setColor(this.graphVpd.getDefaultColor());
                    this.scrollerLabelPaint.setColor(this.graphVpd.getDefaultColor());
                    lockCanvas.drawRect(this.scrollerVpdLabelBounds, this.scrollerRectPaint);
                    lockCanvas.drawRect(this.scrollerVpdLabelBounds, this.scrollerLinePaint);
                    lockCanvas.drawText(str6, (float) this.scrollerVpdLabelTextBounds.left, (float) this.scrollerVpdLabelTextBounds.bottom, this.scrollerLabelTextPaint);
                    lockCanvas.drawCircle(this.scrollerX, this.scrollerVpdY, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2_5), this.scrollerCirclePaint);
                    lockCanvas.drawCircle(this.scrollerX, this.scrollerVpdY, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2), this.scrollerLabelPaint);
                    lockCanvas.drawLine(this.scrollerX, this.scrollerVpdY, this.scrollerRight ? this.scrollerVpdLabelBounds.left : this.scrollerVpdLabelBounds.right, this.scrollerVpdLabelBounds.centerY(), this.scrollerLinePaint);
                }
                if (this.showBottomGraph) {
                    Iterator<GraphLevel> it2 = this.graphLevels.iterator();
                    while (it2.hasNext()) {
                        GraphLevel next2 = it2.next();
                        if (!next2.merge && ((this.showPortLines >> next2.port) & 1) != 0 && next2.scrollerBounds.centerY() >= this.graphHeight + this.timeBarHeight && next2.scrollerBounds.centerY() <= this.graphHeight + this.graphLevelHeight + this.timeBarHeight) {
                            this.scrollerLinePaint.setColor(next2.mergeShow ? ContextCompat.getColor(Utils.getContext(), C1835R.C1836color.color_FFFFFF) : next2.getDefaultColor());
                            this.scrollerLabelPaint.setColor(next2.mergeShow ? ContextCompat.getColor(Utils.getContext(), C1835R.C1836color.color_FFFFFF) : next2.getDefaultColor());
                            lockCanvas.drawRect(next2.scrollerBounds, this.scrollerRectPaint);
                            lockCanvas.drawRect(next2.scrollerBounds, this.scrollerLinePaint);
                            String str7 = next2.labelText;
                            if (!TextUtils.isEmpty(str7)) {
                                lockCanvas.drawText(str7, (float) next2.scrollerTextBounds1.left, (float) next2.scrollerTextBounds1.bottom, this.scrollerLabelTextPaint);
                            }
                            if (next2.showIcon) {
                                String str8 = next2.labelText2;
                                if (!TextUtils.isEmpty(str8)) {
                                    lockCanvas.drawText(str8, (float) next2.scrollerTextBounds2.left, (float) next2.scrollerTextBounds2.bottom, this.scrollerLabelTextPaint);
                                }
                                Bitmap bitmap = next2.bitmap;
                                if (bitmap != null) {
                                    lockCanvas.drawBitmap(bitmap, (Rect) null, next2.bitmapBounds, this.scrollerLabelTextPaint);
                                }
                            }
                            lockCanvas.drawCircle(this.scrollerX, next2.scrollerY, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2_5), this.scrollerCirclePaint);
                            lockCanvas.drawCircle(this.scrollerX, next2.scrollerY, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_2), this.scrollerLabelPaint);
                            lockCanvas.drawLine(this.scrollerX, next2.scrollerY, this.scrollerRight ? next2.scrollerBounds.left : next2.scrollerBounds.right, next2.scrollerBounds.centerY(), this.scrollerLinePaint);
                        }
                    }
                }
                this.lock.readLock().unlock();
            }
            lockCanvas.translate(0.0f, this.graphHeight);
            boolean z2 = this.showTmpLine;
            if (z2 && this.humVisible && this.showHumLine) {
                str2 = this.graphTmp.formatValue2((float) i3, 0.95f) + " / " + this.graphHum.formatValue2((float) i2, 0.95f);
            } else if (z2) {
                str2 = this.graphTmp.formatValue2((float) i3, 0.95f);
            } else {
                str2 = (!this.humVisible || !this.showHumLine) ? null : this.graphHum.formatValue2((float) i2, 0.95f);
            }
            float caculateTextCenterHeightDistancByBaseLine2 = caculateTextCenterHeightDistancByBaseLine(this.graphLabelsTextPaint) - ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_6));
            if (!TextUtils.isEmpty(str2)) {
                lockCanvas.drawText(str2, (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_16), ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_0_5)) + caculateTextCenterHeightDistancByBaseLine2, this.graphLabelsTextPaint);
            }
            if (this.vpdVisible && this.showVpdLine) {
                String formatValue22 = this.graphVpd.formatValue2((float) i, 0.95f);
                lockCanvas.drawText(formatValue22, ((float) (this.graphWidth - getResources().getDimensionPixelSize(C1835R.dimen.dp_16))) - this.graphLabelsTextPaint.measureText(formatValue22), caculateTextCenterHeightDistancByBaseLine2 + ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_0_5)), this.graphLabelsTextPaint);
            }
            if (this.showBottomGraph) {
                lockCanvas.drawText("ON", (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_16), ((this.timeBarHeight + caculateTextCenterHeightDistancByBaseLine(this.graphLabelsTextPaint)) + ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_6))) - ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_0_5)), this.graphLabelsTextPaint);
                lockCanvas.drawText("OFF", (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_18), (((this.timeBarHeight + this.graphLevelHeight) + caculateTextCenterHeightDistancByBaseLine(this.graphLabelsTextPaint)) - ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_6))) + ((float) getResources().getDimensionPixelSize(C1835R.dimen.dp_0_5)), this.graphLabelsTextPaint);
            }
            lockCanvas.translate(0.0f, this.timeBarHeight + this.graphLevelHeight);
            this.sliderBackgroundBounds.set(0, 0, this.graphWidth, (int) this.scrollBarHeight);
            lockCanvas.drawRect(this.sliderBackgroundBounds, this.scrollBarPaint);
            lockCanvas.drawBitmap(this.backwardBitmap, (Rect) null, this.backwardBounds, this.scrollBarPaint);
            lockCanvas.drawBitmap(this.forwardBitmap, (Rect) null, this.forwardBounds, this.scrollBarPaint);
            if (this.sliderVisible) {
                RectF rectF = this.sliderBounds;
                int i7 = this.sliderCorner;
                lockCanvas.drawRoundRect(rectF, (float) i7, (float) i7, this.sliderPaint);
            }
            lockCanvas.restore();
            this.mHolder.unlockCanvasAndPost(lockCanvas);
        } else if (surfaceHolder == null) {
            KLog.m65e("holder is null");
        } else if (!this.adapter.isReady()) {
            KLog.m65e("adapter not ready");
        }
    }

    private void drawTimeLabels(Canvas canvas) {
        int i;
        Rect rect = this.sliderBackgroundBounds;
        float f = this.graphHeight;
        rect.set(0, (int) f, this.graphWidth, (int) (this.timeBarHeight + f));
        canvas.drawRect(this.sliderBackgroundBounds, this.scrollBarPaint);
        List<Date> createLabelTime = createLabelTime();
        List<String> createLabelString = createLabelString(createLabelTime);
        if (createLabelString.size() >= createLabelTime.size()) {
            int i2 = 0;
            while (i2 < createLabelTime.size()) {
                Date date = createLabelTime.get(i2);
                String str = createLabelString.get(i2);
                if (!TextUtils.isEmpty(str)) {
                    String upperCase = str.toUpperCase();
                    int round = Math.round(this.adapter.xFromTime(this.graphWidth, (long) ((int) (date.getTime() / 1000))));
                    Rect rect2 = new Rect();
                    this.graphLabelsLightTextPaint.getTextBounds(upperCase, 0, upperCase.length(), rect2);
                    int width = (rect2.width() + 2) / 2;
                    if (round >= width) {
                        int i3 = this.graphWidth;
                        i = i3 - round < width ? (i3 - rect2.width()) - 2 : round - width;
                    } else {
                        i = 0;
                    }
                    canvas.drawText(upperCase, (float) i, this.graphHeight + this.graphLabelsLightTextPaint.getTextSize(), this.graphLabelsLightTextPaint);
                    i2++;
                } else {
                    return;
                }
            }
            float f2 = this.timeBarHeight;
            float f3 = this.graphHeight;
            canvas.drawLine(0.0f, f2 + f3, (float) this.graphWidth, f2 + f3, this.graphBoundsLinePaint);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x015b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.util.Date> createLabelTime() {
        /*
            r24 = this;
            r0 = r24
            com.eternal.data.ui.GraphAdapter r1 = r0.adapter
            int r1 = r1.timeDistance
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            int r3 = r1 * 3
            r4 = 2
            int r3 = r3 / r4
            long r5 = (long) r3
            com.eternal.data.ui.GraphAdapter r3 = r0.adapter
            long r7 = r3.minTime
            r9 = 60
            long r7 = r7 / r9
            long r7 = r7 * r9
            long r5 = r5 + r7
            r7 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 * r7
            r2.setTimeInMillis(r5)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r11 = 59
            r6 = 15
            r13 = 6
            r14 = -1
            r15 = 11
            r5 = 12
            r7 = 0
            r8 = 6120(0x17e8, float:8.576E-42)
            if (r1 >= r8) goto L_0x0045
            int r1 = r2.get(r5)
            int r1 = r0.roundToInterval(r1, r6)
            r2.set(r5, r1)
            r1 = -15
        L_0x0041:
            r6 = 0
        L_0x0042:
            r14 = 0
            goto L_0x0155
        L_0x0045:
            r8 = 10800(0x2a30, float:1.5134E-41)
            if (r1 >= r8) goto L_0x0059
            int r1 = r2.get(r5)
            r6 = 30
            int r1 = r0.roundToInterval(r1, r6)
            r2.set(r5, r1)
            r1 = -30
            goto L_0x0041
        L_0x0059:
            r8 = 21600(0x5460, float:3.0268E-41)
            if (r1 >= r8) goto L_0x0064
            r2.set(r5, r7)
            r1 = 0
            r6 = 0
            r7 = -1
            goto L_0x0042
        L_0x0064:
            r8 = 43200(0xa8c0, float:6.0536E-41)
            if (r1 >= r8) goto L_0x007b
            r2.set(r5, r7)
            int r1 = r2.get(r15)
            int r1 = r0.roundToInterval(r1, r4)
            r2.set(r15, r1)
            r1 = 0
            r6 = 0
            r7 = -2
            goto L_0x0042
        L_0x007b:
            r8 = 86400(0x15180, float:1.21072E-40)
            if (r1 >= r8) goto L_0x0094
            r2.set(r5, r7)
            int r1 = r2.get(r15)
            r6 = 4
            int r1 = r0.roundToInterval(r1, r6)
            r2.set(r15, r1)
            r1 = -4
            r1 = 0
            r6 = 0
            r7 = -4
            goto L_0x0042
        L_0x0094:
            r8 = 172800(0x2a300, float:2.42144E-40)
            if (r1 >= r8) goto L_0x00ae
            r2.set(r5, r7)
            int r1 = r2.get(r15)
            r6 = 8
            int r1 = r0.roundToInterval(r1, r6)
            r2.set(r15, r1)
            r1 = -8
            r1 = 0
            r6 = 0
            r7 = -8
            goto L_0x0042
        L_0x00ae:
            r8 = 613440(0x95c40, float:8.59613E-40)
            if (r1 >= r8) goto L_0x00bd
            r2.set(r5, r7)
            r2.set(r15, r7)
            r1 = 0
            r6 = 0
            goto L_0x0155
        L_0x00bd:
            r8 = 1209600(0x127500, float:1.69501E-39)
            if (r1 >= r8) goto L_0x00d8
            r2.set(r5, r7)
            r2.set(r15, r7)
            int r1 = r2.get(r13)
            int r1 = r0.roundToInterval(r1, r4)
            r2.set(r13, r1)
            r1 = 0
            r6 = 0
            r14 = -2
            goto L_0x0155
        L_0x00d8:
            r8 = 3888000(0x3b5380, float:5.448248E-39)
            if (r1 >= r8) goto L_0x00ec
            r2.set(r5, r7)
            r2.set(r15, r7)
            r1 = 7
            r2.set(r1, r7)
            r1 = -7
            r1 = 0
            r6 = 0
            r14 = -7
            goto L_0x0155
        L_0x00ec:
            r8 = 7776000(0x76a700, float:1.0896497E-38)
            r13 = 1
            r6 = 5
            if (r1 >= r8) goto L_0x0146
            r2.set(r5, r7)
            r2.set(r15, r7)
            r2.set(r6, r13)
            java.lang.Object r1 = r2.clone()
            java.util.Calendar r1 = (java.util.Calendar) r1
            com.eternal.data.ui.GraphAdapter r8 = r0.adapter
            long r7 = r8.minTime
            long r7 = r7 / r9
            long r7 = r7 * r9
            com.eternal.data.ui.GraphAdapter r15 = r0.adapter
            int r15 = r15.timeDistance
            long r9 = (long) r15
            long r9 = r9 + r7
            long r9 = r9 + r11
        L_0x0110:
            int r15 = r1.get(r6)
            if (r15 != r13) goto L_0x011f
            r1.add(r4, r14)
            r15 = 15
            r1.set(r6, r15)
            goto L_0x0124
        L_0x011f:
            r15 = 15
            r1.set(r6, r13)
        L_0x0124:
            java.util.Date r14 = r1.getTime()
            long r21 = r14.getTime()
            r17 = 1000(0x3e8, double:4.94E-321)
            long r21 = r21 / r17
            int r23 = (r21 > r9 ? 1 : (r21 == r9 ? 0 : -1))
            if (r23 > 0) goto L_0x013b
            int r23 = (r21 > r7 ? 1 : (r21 == r7 ? 0 : -1))
            if (r23 < 0) goto L_0x013b
            r3.add(r14)
        L_0x013b:
            int r14 = (r21 > r7 ? 1 : (r21 == r7 ? 0 : -1))
            if (r14 >= 0) goto L_0x0144
            r1 = 0
            r6 = 0
            r7 = 0
            goto L_0x0042
        L_0x0144:
            r14 = -1
            goto L_0x0110
        L_0x0146:
            r14 = 0
            r2.set(r5, r14)
            r1 = 10
            r2.set(r1, r14)
            r2.set(r6, r13)
            r1 = 0
            r6 = -1
            r7 = 0
        L_0x0155:
            int r8 = r3.size()
            if (r8 != 0) goto L_0x019f
            java.lang.Object r2 = r2.clone()
            java.util.Calendar r2 = (java.util.Calendar) r2
            com.eternal.data.ui.GraphAdapter r8 = r0.adapter
            long r8 = r8.minTime
            r19 = 60
            long r8 = r8 / r19
            long r8 = r8 * r19
            com.eternal.data.ui.GraphAdapter r10 = r0.adapter
            int r10 = r10.timeDistance
            long r4 = (long) r10
            long r4 = r4 + r8
            long r4 = r4 + r11
        L_0x0172:
            r10 = 12
            r2.add(r10, r1)
            r11 = 11
            r2.add(r11, r7)
            r12 = 6
            r2.add(r12, r14)
            r13 = 2
            r2.add(r13, r6)
            long r15 = r2.getTimeInMillis()
            r17 = 1000(0x3e8, double:4.94E-321)
            long r15 = r15 / r17
            int r19 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r19 > 0) goto L_0x019b
            int r19 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r19 < 0) goto L_0x019b
            java.util.Date r10 = r2.getTime()
            r3.add(r10)
        L_0x019b:
            int r10 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x0172
        L_0x019f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.data.p007ui.GraphView.createLabelTime():java.util.List");
    }

    private List<String> createLabelString(List<Date> list) {
        SimpleDateFormat simpleDateFormat;
        String str;
        String str2;
        ArrayList arrayList = new ArrayList();
        int i = this.adapter.timeDistance;
        if (i < 10800) {
            simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        } else if (i < 172800) {
            simpleDateFormat = new SimpleDateFormat("h a", Locale.ENGLISH);
        } else if (i < 345600) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            long j = this.adapter.minTime;
            long j2 = ((long) this.adapter.timeDistance) + j;
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE", Locale.ENGLISH);
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("h a", Locale.ENGLISH);
            Calendar instance = Calendar.getInstance();
            for (Date next : list) {
                instance.setTime(next);
                if (instance.get(11) == 0) {
                    str2 = simpleDateFormat2.format(next);
                } else {
                    str2 = simpleDateFormat3.format(next);
                }
                long time = next.getTime() / 1000;
                if (time <= j2 && time >= j) {
                    arrayList.add(str2);
                }
            }
            simpleDateFormat = simpleDateFormat2;
        } else if (i < 613440) {
            simpleDateFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
        } else if (i < 3888000) {
            simpleDateFormat = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
        } else if (i < 7776000) {
            simpleDateFormat = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
            SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("dd", Locale.ENGLISH);
            Calendar instance2 = Calendar.getInstance();
            long j3 = this.adapter.minTime;
            long j4 = ((long) this.adapter.timeDistance) + j3;
            for (Date next2 : list) {
                instance2.setTime(next2);
                if (instance2.get(5) == 1) {
                    str = simpleDateFormat.format(next2);
                } else {
                    str = simpleDateFormat4.format(next2);
                }
                long time2 = next2.getTime() / 1000;
                if (time2 <= j4 && time2 >= j3) {
                    arrayList.add(str);
                }
            }
        } else {
            simpleDateFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
        }
        if (arrayList.size() == 0) {
            for (Date format2 : list) {
                arrayList.add(simpleDateFormat.format(format2));
            }
        }
        return arrayList;
    }

    public final int roundToInterval(int i, int i2) {
        return (i / i2) * i2;
    }

    /* access modifiers changed from: package-private */
    public void resetPath(IGraphDelegate iGraphDelegate) {
        if (iGraphDelegate != null) {
            if (iGraphDelegate.getGraphPath() != null) {
                iGraphDelegate.getGraphPath().reset();
            }
            if (iGraphDelegate.getMissPath() != null) {
                iGraphDelegate.getMissPath().reset();
            }
        }
    }

    public void updatePath(int i) {
        if (this.graphWidth == 0) {
            KLog.m65e("width not init");
        } else if (this.adapter.length < 2) {
            resetPath(this.graphTmp);
            resetPath(this.graphHum);
            resetPath(this.graphVpd);
            Iterator<GraphLevel> it = this.graphLevels.iterator();
            while (it.hasNext()) {
                resetPath(it.next());
            }
        } else {
            updatePathFromType(this.graphTmp);
            if (this.humVisible) {
                updatePathFromType(this.graphHum);
            }
            if (this.vpdVisible) {
                updatePathFromType(this.graphVpd);
            }
            if (this.showBottomGraph) {
                Iterator<GraphLevel> it2 = this.graphLevels.iterator();
                while (it2.hasNext()) {
                    updateLevelPath(it2.next());
                }
            }
            if (((long) this.adapter.timeDistance) < this.adapter.dataTimeDistance) {
                this.sliderVisible = true;
                this.sliderBounds.offsetTo(Math.min(Math.max((((((float) (this.adapter.minTime - this.adapter.dataMinTime)) / 1.0f) / ((float) (this.adapter.dataTimeDistance - ((long) this.adapter.timeDistance)))) * (this.scrollBarWidth - ((float) this.sliderWidth))) + this.backwardBounds.right + this.sliderPadding, this.backwardBounds.right + this.sliderPadding), (this.forwardBounds.left - this.sliderPadding) - ((float) this.sliderWidth)), 0.0f);
            } else {
                this.sliderVisible = false;
            }
            if (this.scrollerOpen) {
                scaleScroller();
            }
        }
    }

    private void updateLevelPath(GraphLevel graphLevel) {
        float f;
        long j;
        long j2;
        float f2;
        GraphLevel graphLevel2 = graphLevel;
        Path graphPath = graphLevel.getGraphPath();
        Path missPath = graphLevel.getMissPath();
        graphPath.reset();
        missPath.reset();
        TmpHum item = this.adapter.getItem(0);
        if (item != null) {
            int distance = this.adapter.getDistance();
            long j3 = (this.adapter.minTime / 60) * 60;
            long j4 = ((long) this.adapter.timeDistance) + j3 + 59;
            float xFromTime = this.adapter.xFromTime(this.graphWidth, item.time);
            int level = graphLevel2.getLevel(item);
            if (!this.showLevel || level == 15) {
                level = graphLevel2.getOff(item) ? 0 : 10;
            }
            float yFromLevelValue = yFromLevelValue((float) level);
            graphPath.moveTo(xFromTime, yFromLevelValue);
            long j5 = this.adapter.dataMaxTime;
            float f3 = xFromTime;
            int i = level;
            long j6 = this.adapter.dataMaxTime - item.time;
            long j7 = (long) distance;
            long j8 = j5 - ((j6 / j7) * j7);
            int i2 = 1;
            long j9 = j3;
            float f4 = f3;
            long j10 = j8;
            float f5 = yFromLevelValue;
            int i3 = i;
            int i4 = 1;
            long j11 = j10;
            while (true) {
                if (i4 >= this.adapter.length - i2) {
                    f = f4;
                    break;
                }
                TmpHum item2 = this.adapter.getItem(i4);
                if (item2 != null) {
                    i4++;
                    TmpHum item3 = this.adapter.getItem(i4);
                    if (item3 != null) {
                        float f6 = f4;
                        if (item3.time > j4 || j11 - item2.time < j7) {
                            j2 = j7;
                            j = j4;
                            f2 = f6;
                        } else {
                            int level2 = graphLevel2.getLevel(item2);
                            if (!this.showLevel || level2 == 15) {
                                level2 = graphLevel2.getOff(item2) ? 0 : 10;
                            }
                            j2 = j7;
                            float xFromTime2 = this.adapter.xFromTime(this.graphWidth, item2.time);
                            float yFromLevelValue2 = yFromLevelValue((float) level2);
                            j = j4;
                            if (j11 - item2.time >= ((long) (distance + 60))) {
                                f = f6;
                                missPath.moveTo(f, f5);
                                if (level2 != i3) {
                                    missPath.lineTo(f, yFromLevelValue2);
                                }
                                missPath.lineTo(xFromTime2, yFromLevelValue2);
                                graphPath.moveTo(xFromTime2, yFromLevelValue2);
                            } else {
                                f = f6;
                                if (level2 != i3) {
                                    graphPath.lineTo(f, yFromLevelValue2);
                                }
                                graphPath.lineTo(xFromTime2, yFromLevelValue2);
                            }
                            if (item2.time < j9) {
                                break;
                            }
                            f4 = xFromTime2;
                            f5 = yFromLevelValue2;
                            i3 = level2;
                            j11 = item2.time;
                            j7 = j2;
                            j4 = j;
                            i2 = 1;
                        }
                    } else {
                        j2 = j7;
                        j = j4;
                        f2 = f4;
                    }
                    f4 = f2;
                    j7 = j2;
                    j4 = j;
                    i2 = 1;
                } else {
                    return;
                }
            }
            GraphAdapter graphAdapter = this.adapter;
            TmpHum item4 = graphAdapter.getItem(graphAdapter.length - 1);
            if (item4 != null) {
                int level3 = graphLevel2.getLevel(item4);
                if (!this.showLevel || level3 == 15) {
                    level3 = graphLevel2.getOff(item4) ? 0 : 10;
                }
                float xFromTime3 = this.adapter.xFromTime(this.graphWidth, item4.time);
                float yFromLevelValue3 = yFromLevelValue((float) level3);
                if (j11 - item4.time >= ((long) (distance + 60))) {
                    missPath.moveTo(f, f5);
                    if (level3 != i3) {
                        missPath.lineTo(f, yFromLevelValue3);
                    }
                    missPath.lineTo(xFromTime3, yFromLevelValue3);
                    return;
                }
                if (level3 != i3) {
                    graphPath.lineTo(f, yFromLevelValue3);
                }
                graphPath.lineTo(xFromTime3, yFromLevelValue3);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updatePathFromType(com.eternal.data.p007ui.GraphView.IGraphDelegate r27) {
        /*
            r26 = this;
            r9 = r26
            r10 = r27
            android.graphics.Path r11 = r27.getGraphPath()
            android.graphics.Path r12 = r27.getMissPath()
            r11.reset()
            r12.reset()
            r13 = 0
            com.eternal.data.ui.TimeValue r14 = r10.getItem(r13)
            com.eternal.data.ui.GraphAdapter r0 = r9.adapter
            int r15 = r0.getDistance()
            com.eternal.data.ui.GraphAdapter r0 = r9.adapter
            long r0 = r0.minTime
            r2 = 60
            long r0 = r0 / r2
            long r16 = r0 * r2
            com.eternal.data.ui.GraphAdapter r0 = r9.adapter
            int r0 = r0.timeDistance
            long r0 = (long) r0
            long r0 = r16 + r0
            r2 = 59
            long r18 = r0 + r2
            com.eternal.data.ui.GraphAdapter r0 = r9.adapter
            int r1 = r9.graphWidth
            long r2 = r14.time
            float r0 = r0.xFromTime(r1, r2)
            int r1 = r14.value
            float r1 = (float) r1
            float r1 = r9.yFromValue(r1, r10)
            int r2 = r14.value
            int r3 = r27.getAMax()
            r7 = 1
            if (r2 > r3) goto L_0x0056
            int r2 = r14.value
            int r3 = r27.getAMin()
            if (r2 >= r3) goto L_0x0054
            goto L_0x0056
        L_0x0054:
            r13 = 1
            goto L_0x0077
        L_0x0056:
            com.eternal.data.ui.TimeValue r0 = r10.getItem(r7)
            int r1 = r9.graphWidth
            float r8 = (float) r1
            long r1 = r14.time
            long r3 = r0.time
            int r5 = r14.value
            int r6 = r0.value
            r0 = r26
            r21 = r8
            r13 = 1
            r7 = r18
            int r0 = r0.valueFormTime(r1, r3, r5, r6, r7)
            float r0 = (float) r0
            float r1 = r9.yFromValue(r0, r10)
            r0 = r21
        L_0x0077:
            com.eternal.data.ui.GraphAdapter r2 = r9.adapter
            long r2 = r2.dataMaxTime
            com.eternal.data.ui.GraphAdapter r4 = r9.adapter
            long r4 = r4.dataMaxTime
            long r6 = r14.time
            long r4 = r4 - r6
            long r6 = (long) r15
            long r4 = r4 / r6
            long r4 = r4 * r6
            long r2 = r2 - r4
            r14 = r0
            r8 = r1
            r20 = r2
            r0 = 1
            r1 = 0
        L_0x008d:
            com.eternal.data.ui.GraphAdapter r2 = r9.adapter
            int r2 = r2.length
            int r2 = r2 - r13
            if (r0 >= r2) goto L_0x0101
            com.eternal.data.ui.TimeValue r2 = r10.getItem(r0)
            com.eternal.data.ui.GraphAdapter r3 = r9.adapter
            int r0 = r0 + 1
            com.eternal.base.concat.TmpHum r3 = r3.getItem(r0)
            if (r3 == 0) goto L_0x00f9
            long r3 = r3.time
            int r22 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r22 > 0) goto L_0x00f9
            long r3 = r2.time
            long r3 = r20 - r3
            int r22 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r22 < 0) goto L_0x00f9
            com.eternal.data.ui.GraphAdapter r3 = r9.adapter
            int r4 = r9.graphWidth
            r23 = r14
            long r13 = r2.time
            float r3 = r3.xFromTime(r4, r13)
            int r4 = r2.value
            float r4 = (float) r4
            float r4 = r9.yFromValue(r4, r10)
            long r13 = r2.time
            long r13 = r20 - r13
            int r5 = r15 + 60
            r24 = r6
            long r5 = (long) r5
            int r7 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x00dd
            r5 = 2
            r13 = r23
            if (r1 == r5) goto L_0x00d8
            r12.moveTo(r13, r8)
        L_0x00d8:
            r12.lineTo(r3, r4)
            r7 = 2
            goto L_0x00e9
        L_0x00dd:
            r13 = r23
            r5 = 1
            if (r1 == r5) goto L_0x00e5
            r11.moveTo(r13, r8)
        L_0x00e5:
            r11.lineTo(r3, r4)
            r7 = 1
        L_0x00e9:
            long r5 = r2.time
            int r1 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r1 >= 0) goto L_0x00f1
            r14 = r7
            goto L_0x0103
        L_0x00f1:
            long r1 = r2.time
            r20 = r1
            r14 = r3
            r8 = r4
            r1 = r7
            goto L_0x00fd
        L_0x00f9:
            r24 = r6
            r13 = r14
            r14 = r13
        L_0x00fd:
            r6 = r24
            r13 = 1
            goto L_0x008d
        L_0x0101:
            r13 = r14
            r14 = r1
        L_0x0103:
            com.eternal.data.ui.GraphAdapter r0 = r9.adapter
            int r0 = r0.length
            r1 = 1
            int r0 = r0 - r1
            com.eternal.data.ui.TimeValue r7 = r10.getItem(r0)
            com.eternal.data.ui.GraphAdapter r0 = r9.adapter
            int r1 = r9.graphWidth
            long r2 = r7.time
            float r0 = r0.xFromTime(r1, r2)
            int r1 = r7.value
            float r1 = (float) r1
            float r1 = r9.yFromValue(r1, r10)
            int r2 = r7.value
            int r3 = r27.getAMax()
            if (r2 > r3) goto L_0x0136
            int r2 = r7.value
            int r3 = r27.getAMin()
            if (r2 >= r3) goto L_0x012f
            goto L_0x0136
        L_0x012f:
            r23 = r11
            r19 = r12
            r11 = r7
            r12 = r8
            goto L_0x0164
        L_0x0136:
            com.eternal.data.ui.GraphAdapter r0 = r9.adapter
            int r0 = r0.length
            r5 = 2
            int r0 = r0 - r5
            com.eternal.data.ui.TimeValue r0 = r10.getItem(r0)
            r18 = 0
            long r1 = r0.time
            long r3 = r7.time
            int r6 = r0.value
            int r0 = r7.value
            r19 = r0
            r0 = r26
            r23 = r11
            r11 = 2
            r5 = r6
            r6 = r19
            r11 = r7
            r19 = r12
            r12 = r8
            r7 = r16
            int r0 = r0.valueFormTime(r1, r3, r5, r6, r7)
            float r0 = (float) r0
            float r1 = r9.yFromValue(r0, r10)
            r0 = 0
        L_0x0164:
            long r2 = r11.time
            long r20 = r20 - r2
            int r15 = r15 + 60
            long r2 = (long) r15
            int r4 = (r20 > r2 ? 1 : (r20 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x017e
            r2 = 2
            if (r14 == r2) goto L_0x0178
            r2 = r19
            r2.moveTo(r13, r12)
            goto L_0x017a
        L_0x0178:
            r2 = r19
        L_0x017a:
            r2.lineTo(r0, r1)
            goto L_0x018c
        L_0x017e:
            r2 = 1
            if (r14 == r2) goto L_0x0187
            r2 = r23
            r2.moveTo(r13, r12)
            goto L_0x0189
        L_0x0187:
            r2 = r23
        L_0x0189:
            r2.lineTo(r0, r1)
        L_0x018c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.data.p007ui.GraphView.updatePathFromType(com.eternal.data.ui.GraphView$IGraphDelegate):void");
    }

    public final float yFromVpdValue(float f, IGraphDelegate iGraphDelegate) {
        if (this.adapter.length <= 1) {
            return 0.0f;
        }
        int aMax = iGraphDelegate.getAMax() - iGraphDelegate.getAMin();
        if (aMax <= 1) {
            return this.graphHeight * 0.8f;
        }
        float max = Math.max(Math.min((float) iGraphDelegate.getAMax(), f), (float) iGraphDelegate.getAMin());
        float f2 = this.graphHeight;
        return (((((float) iGraphDelegate.getAMax()) - max) / ((float) aMax)) * 0.24f * f2) + (f2 * 0.68f);
    }

    public final float yFromValue(float f, IGraphDelegate iGraphDelegate) {
        if (iGraphDelegate instanceof GraphVpd) {
            return yFromVpdValue(f, iGraphDelegate);
        }
        if (this.adapter.length <= 1) {
            return 0.0f;
        }
        int aMax = iGraphDelegate.getAMax() - iGraphDelegate.getAMin();
        float max = Math.max(Math.min((float) iGraphDelegate.getAMax(), f), (float) iGraphDelegate.getAMin());
        float f2 = this.vpdVisible ? this.graphHeight : this.graphHeight / 0.76f;
        if (aMax <= 1) {
            return f2 * 0.38f;
        }
        return (((((float) iGraphDelegate.getAMax()) - max) / ((float) aMax)) * 0.6f * f2) + (f2 * 0.08f);
    }

    public final float yFromLevelValue(float f) {
        if (this.adapter.length <= 1) {
            return 0.0f;
        }
        float f2 = (float) 12;
        return ((((f2 - f) - 1.0f) * this.graphLevelHeight) / f2) + this.graphHeight + this.timeBarHeight;
    }

    private void postDraw() {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                GraphView.this.draw();
            }
        }).subscribeOn(Schedulers.computation()).subscribe();
    }

    private void postUpdateDraw() {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                GraphView.this.updatePath(0);
                GraphView.this.draw();
            }
        }).subscribeOn(Schedulers.computation()).subscribe();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mHolder = surfaceHolder;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.graphHeight = (float) (this.vpdVisible ? this.GRAPH_HEIGHT : (int) (((double) this.GRAPH_HEIGHT) * 0.7d));
        this.graphMartinTop = getResources().getDimensionPixelSize(C1835R.dimen.dp_42);
        this.graphLevelHeight = this.showBottomGraph ? (float) this.GRAPH_LEVEL_HEIGHT : 0.0f;
        this.graphWidth = i2;
        this.scrollBarHeight = (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_11);
        this.timeBarHeight = (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_13);
        float height = (this.scrollBarHeight / ((float) this.backwardBitmap.getHeight())) * ((float) this.backwardBitmap.getWidth());
        float dimensionPixelSize = (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_10);
        this.backwardBounds.set(dimensionPixelSize, 0.0f, height + dimensionPixelSize, this.scrollBarHeight);
        float width = (((float) this.graphWidth) - dimensionPixelSize) - ((float) this.forwardBitmap.getWidth());
        this.forwardBounds.set(width, 0.0f, ((this.scrollBarHeight / ((float) this.forwardBitmap.getHeight())) * ((float) this.forwardBitmap.getWidth())) + width, this.scrollBarHeight);
        this.sliderPadding = (float) getResources().getDimensionPixelSize(C1835R.dimen.dp_0);
        this.scrollBarWidth = (this.forwardBounds.left - this.backwardBounds.right) - (this.sliderPadding * 2.0f);
        this.sliderWidth = getResources().getDimensionPixelSize(C1835R.dimen.dp_31);
        this.sliderCorner = getResources().getDimensionPixelSize(C1835R.dimen.dp_6);
        this.sliderBounds.set(0.0f, getResources().getDimension(C1835R.dimen.dp_6), (float) this.sliderWidth, this.scrollBarHeight + getResources().getDimension(C1835R.dimen.dp_6));
        if (this.adapter != null) {
            postUpdateDraw();
        }
        updateGestureExclusion();
    }

    private void updateGestureExclusion() {
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT >= 29 && (rootWindowInsets = getRootWindowInsets()) != null) {
            Insets systemGestureInsets = rootWindowInsets.getSystemGestureInsets();
            if (systemGestureInsets.left != 0 || systemGestureInsets.right != 0) {
                this.exclusionRects.clear();
                int dp2px = ConvertUtils.dp2px(200.0f);
                int i = ((int) (this.graphHeight - ((float) dp2px))) / 2;
                this.exclusionRects.add(new Rect(0, this.graphMartinTop + i, systemGestureInsets.left, dp2px));
                this.exclusionRects.add(new Rect(this.graphWidth - systemGestureInsets.right, this.graphMartinTop + i, this.graphWidth, dp2px));
                ViewCompat.setSystemGestureExclusionRects(this, this.exclusionRects);
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        KLog.m65e("surfaceDestroyed");
        this.mHolder = null;
    }

    public void setScrollerOpen(boolean z) {
        this.scrollerOpen = z;
    }

    public void setShowTmpLine(boolean z) {
        this.showTmpLine = z;
    }

    public void setShowHumLine(boolean z) {
        this.showHumLine = z;
    }

    public void setShowVpdLine(boolean z) {
        this.showVpdLine = z;
    }

    public void setShowPortLines(int i) {
        this.showPortLines = i;
    }

    private float caculateTextCenterHeightDistancByBaseLine(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return ((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom;
    }
}
