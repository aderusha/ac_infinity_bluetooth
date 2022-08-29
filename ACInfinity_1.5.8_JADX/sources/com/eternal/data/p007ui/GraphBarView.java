package com.eternal.data.p007ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import com.eternal.data.C1835R;
import com.eternal.data.p007ui.GraphAdapter;
import com.eternal.framework.utils.ConvertUtils;
import com.eternal.framework.utils.KLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.eternal.data.ui.GraphBarView */
public abstract class GraphBarView extends SurfaceView implements SurfaceHolder.Callback, GraphAdapter.Callback {
    private static final byte BAR_NUM = 8;
    protected GraphAdapter adapter;
    private int barDistance;
    private CopyOnWriteArrayList<Integer> barNum;
    private Paint barPaint;
    private CopyOnWriteArrayList<Rect> barRect;
    private int barWidth;
    private int distance;
    private Paint graphBoundsLinePaint;
    private float graphHeight;
    private Paint graphLabelsLightTextPaint;
    private int graphWidth;
    private int index;
    private Paint labelLinePaint;
    private Paint labelMadelPaint;
    private Paint labelPaint;
    private ReentrantReadWriteLock lock;
    private SurfaceHolder mHolder;
    private SimpleDateFormat monthFormat;
    private RectF scrollerLabelBounds;
    private Paint scrollerLabelPaint;
    private String scrollerLabelText;
    private Rect scrollerLabelTextBounds;
    private Paint scrollerLabelTextPaint;
    private Paint scrollerLinePaint;
    private boolean scrollerOpen;
    private int scrollerX;
    private int[] size;
    private int standardPadding;
    private Rect textBounds;
    private SimpleDateFormat yearFormat;

    /* access modifiers changed from: package-private */
    public abstract String formatMaxMin(int i);

    /* access modifiers changed from: package-private */
    public abstract String formatValue(int i);

    /* access modifiers changed from: package-private */
    public abstract int getDefaultColor();

    /* access modifiers changed from: package-private */
    public abstract int getDistance();

    /* access modifiers changed from: package-private */
    public abstract TimeValue getItem(int i);

    /* access modifiers changed from: package-private */
    public abstract int getMax();

    /* access modifiers changed from: package-private */
    public abstract int getMin();

    public GraphBarView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GraphBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GraphBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.size = new int[8];
        getHolder().addCallback(this);
        init();
    }

    public void setAdapter(GraphAdapter graphAdapter) {
        this.adapter = graphAdapter;
        graphAdapter.addCallback(this);
    }

    private void init() {
        this.standardPadding = getResources().getDimensionPixelSize(C1835R.dimen.dp_4);
        this.barNum = new CopyOnWriteArrayList<>();
        this.barRect = new CopyOnWriteArrayList<>();
        this.textBounds = new Rect();
        this.scrollerLabelBounds = new RectF();
        this.scrollerLabelTextBounds = new Rect();
        this.monthFormat = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
        this.yearFormat = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        this.lock = new ReentrantReadWriteLock();
        initPaint();
    }

    private void initPaint() {
        Paint paint = new Paint(1);
        this.labelPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.labelPaint.setTextSize(getResources().getDimension(C1835R.dimen.dp_10));
        this.labelPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1835R.font.avenir_book));
        this.labelPaint.setColor(-4210753);
        Paint paint2 = new Paint(1);
        this.barPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.barPaint.setColor(getDefaultColor());
        Paint paint3 = new Paint(1);
        this.graphLabelsLightTextPaint = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.graphLabelsLightTextPaint.setColor(-1);
        this.graphLabelsLightTextPaint.setTextSize(getResources().getDimension(C1835R.dimen.dp_10));
        this.graphLabelsLightTextPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1835R.font.avenir_book));
        Paint paint4 = new Paint(1);
        this.graphBoundsLinePaint = paint4;
        paint4.setStyle(Paint.Style.STROKE);
        this.graphBoundsLinePaint.setColor(-1);
        this.graphBoundsLinePaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint5 = new Paint(1);
        this.labelLinePaint = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        this.labelLinePaint.setColor(-1);
        this.labelLinePaint.setStrokeWidth((float) ConvertUtils.sp2px(0.5f));
        Paint paint6 = new Paint(1);
        this.labelMadelPaint = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        this.labelMadelPaint.setColor(-1);
        this.labelMadelPaint.setStrokeWidth((float) ConvertUtils.sp2px(1.0f));
        Paint paint7 = new Paint(1);
        this.scrollerLabelPaint = paint7;
        paint7.setStyle(Paint.Style.FILL);
        this.scrollerLabelPaint.setColor(-1);
        Paint paint8 = new Paint(1);
        this.scrollerLabelTextPaint = paint8;
        paint8.setStyle(Paint.Style.FILL);
        this.scrollerLabelTextPaint.setColor(-16777216);
        this.scrollerLabelTextPaint.setTextSize(getResources().getDimension(C1835R.dimen.dp_12));
        this.scrollerLabelTextPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1835R.font.avenir_medium));
        Paint paint9 = new Paint(1);
        this.scrollerLinePaint = paint9;
        paint9.setStyle(Paint.Style.STROKE);
        this.scrollerLinePaint.setColor(-1);
        this.scrollerLinePaint.setStrokeWidth(2.0f);
    }

    public void draw() {
        int i;
        SimpleDateFormat simpleDateFormat;
        SurfaceHolder surfaceHolder = this.mHolder;
        if (surfaceHolder == null) {
            KLog.m65e("holder is locked");
            return;
        }
        Canvas lockCanvas = surfaceHolder.lockCanvas();
        if (lockCanvas == null) {
            KLog.m65e("canvas is locked");
            return;
        }
        lockCanvas.save();
        lockCanvas.drawColor(ContextCompat.getColor(getContext(), C1835R.C1836color.f152bg));
        lockCanvas.translate(0.0f, (this.labelPaint.getTextSize() * 2.0f) + (((float) this.standardPadding) * 8.0f));
        int i2 = -32768;
        if (this.adapter.size <= 0 || (this.barRect.isEmpty() && this.adapter.isInit())) {
            i = -32768;
        } else {
            i2 = getMin();
            i = getMax();
        }
        lockCanvas.drawText(String.format(Locale.ENGLISH, "MIN %s", new Object[]{formatMaxMin(i2)}), 0.0f, 0.0f, this.labelPaint);
        String format = String.format("MAX %s", new Object[]{formatMaxMin(i)});
        lockCanvas.drawText(format, ((float) this.graphWidth) - this.labelPaint.measureText(format), 0.0f, this.labelPaint);
        lockCanvas.translate(0.0f, (float) this.standardPadding);
        lockCanvas.drawLine(0.0f, 0.0f, (float) this.graphWidth, 0.0f, this.graphBoundsLinePaint);
        drawLabel(lockCanvas);
        Iterator<Rect> it = this.barRect.iterator();
        while (it.hasNext()) {
            lockCanvas.drawRect(it.next(), this.barPaint);
        }
        if (this.barRect.isEmpty() && this.adapter.isInit()) {
            lockCanvas.drawText("No data is available for this period.", (((float) this.graphWidth) - this.labelPaint.measureText("No data is available for this period.")) / 2.0f, (this.graphHeight + this.labelPaint.getTextSize()) / 2.0f, this.labelPaint);
        }
        lockCanvas.translate(0.0f, this.graphHeight);
        lockCanvas.drawLine(0.0f, 0.0f, (float) this.graphWidth, 0.0f, this.graphBoundsLinePaint);
        lockCanvas.translate(0.0f, (-this.graphHeight) - ((float) (this.standardPadding * 2)));
        if (this.scrollerOpen) {
            this.lock.readLock().lock();
            float f = (float) this.scrollerX;
            int i3 = this.standardPadding;
            lockCanvas.drawLine(f, (this.labelPaint.getTextSize() * -2.0f) - (((float) i3) * 4.0f), (float) this.scrollerX, this.graphHeight + (((float) i3) * 2.0f), this.scrollerLinePaint);
            float width = this.scrollerLabelBounds.width() / 2.0f;
            lockCanvas.drawRoundRect(this.scrollerLabelBounds, width, width, this.scrollerLabelPaint);
            if (!TextUtils.isEmpty(this.scrollerLabelText)) {
                lockCanvas.drawText(this.scrollerLabelText, (float) this.scrollerLabelTextBounds.left, (float) this.scrollerLabelTextBounds.bottom, this.scrollerLabelTextPaint);
            }
            this.lock.readLock().unlock();
        }
        lockCanvas.translate(0.0f, this.graphHeight + (this.graphLabelsLightTextPaint.getTextSize() * 2.0f) + (this.labelPaint.getTextSize() * 2.0f) + ((float) (this.standardPadding * 8)));
        if (this.adapter.timeDistance >= 31536000) {
            simpleDateFormat = this.yearFormat;
        } else {
            simpleDateFormat = this.monthFormat;
        }
        String upperCase = simpleDateFormat.format(new Date(this.adapter.minTime * 1000)).toUpperCase();
        this.labelPaint.getTextBounds(upperCase, 0, upperCase.length(), this.textBounds);
        lockCanvas.drawText(upperCase, 0.0f, 0.0f, this.labelPaint);
        String upperCase2 = simpleDateFormat.format(new Date((this.adapter.minTime + ((long) this.adapter.timeDistance)) * 1000)).toUpperCase();
        this.labelPaint.getTextBounds(upperCase2, 0, upperCase2.length(), this.textBounds);
        lockCanvas.drawText(upperCase2, (float) ((getWidth() - this.textBounds.width()) - 3), 0.0f, this.labelPaint);
        int height = 1 - (this.textBounds.height() / 2);
        int i4 = this.standardPadding;
        float f2 = (float) height;
        lockCanvas.drawLine(((float) this.textBounds.width()) + (((float) i4) * 3.0f), f2, ((float) (getWidth() - this.textBounds.width())) - (((float) i4) * 3.0f), f2, this.labelLinePaint);
        lockCanvas.restore();
        this.mHolder.unlockCanvasAndPost(lockCanvas);
    }

    private void drawLabel(Canvas canvas) {
        Canvas canvas2 = canvas;
        ArrayList arrayList = new ArrayList(this.barRect);
        Iterator<Integer> it = this.barNum.iterator();
        int i = 0;
        while (it.hasNext()) {
            Integer next = it.next();
            String formatValue = formatValue(next.intValue() * getDistance());
            float f = (float) (arrayList.size() > i ? ((Rect) arrayList.get(i)).left : 0);
            canvas2.drawText(formatValue, ((((float) this.barWidth) - this.graphLabelsLightTextPaint.measureText(formatValue)) / 2.0f) + f, this.graphHeight + ((float) this.standardPadding) + this.graphLabelsLightTextPaint.getTextSize(), this.graphLabelsLightTextPaint);
            canvas.drawLine(f + (((float) this.barWidth) / 2.0f), ((float) ((int) (((float) this.standardPadding) * 2.5f))) + this.graphHeight + this.graphLabelsLightTextPaint.getTextSize(), f + (((float) this.barWidth) / 2.0f), ((float) ((int) (((float) this.standardPadding) * 1.5f))) + this.graphHeight + (this.graphLabelsLightTextPaint.getTextSize() * 2.0f), this.labelMadelPaint);
            String formatValue2 = formatValue(i == this.barNum.size() + -1 ? getMax() : ((this.distance - 1) + next.intValue()) * getDistance());
            canvas2.drawText(formatValue2, f + ((((float) this.barWidth) - this.graphLabelsLightTextPaint.measureText(formatValue2)) / 2.0f), this.graphHeight + (this.graphLabelsLightTextPaint.getTextSize() * 3.0f) + ((float) (this.standardPadding * 3)), this.graphLabelsLightTextPaint);
            i++;
        }
    }

    public void updatePath(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        this.barNum.clear();
        this.barRect.clear();
        if (this.adapter.length >= 2) {
            long j = (this.adapter.minTime / 60) * 60;
            long j2 = ((long) this.adapter.timeDistance) + j + 59;
            for (int i7 = 0; i7 < 8; i7++) {
                this.size[i7] = 0;
            }
            float distance2 = (float) getDistance();
            int round = Math.round((((float) getMax()) * 1.0f) / distance2);
            int round2 = Math.round((((float) getMin()) * 1.0f) / distance2);
            int i8 = round - round2;
            if (i8 > 0) {
                i2 = Math.min(i8 + 1, 8);
                i3 = i8 >= 8 ? ((int) Math.floor((double) (((float) i8) / 8.0f))) + 1 : 1;
            } else {
                i3 = 1;
                i2 = 1;
            }
            this.distance = i3;
            for (int i9 = 0; i9 < i2; i9++) {
                this.barNum.add(Integer.valueOf(round2 + (i9 * i3)));
            }
            int round3 = Math.round((((float) getItem(0).value) * 1.0f) / distance2);
            if (getItem(0).time <= j2 && (i6 = (round3 - round2) / i3) >= 0 && i6 < 8) {
                int[] iArr = this.size;
                iArr[i6] = iArr[i6] + 1;
            }
            for (int i10 = 1; i10 < this.adapter.length - 1; i10++) {
                int round4 = (Math.round((((float) getItem(i10).value) * 1.0f) / distance2) - round2) / i3;
                if (round4 >= 0 && round4 < 8) {
                    int[] iArr2 = this.size;
                    iArr2[round4] = iArr2[round4] + 1;
                }
            }
            int round5 = Math.round((((float) getItem(this.adapter.length - 1).value) * 1.0f) / distance2);
            if (getItem(this.adapter.length - 1).time >= j && (i5 = (round5 - round2) / i3) >= 0 && i5 < 8) {
                int[] iArr3 = this.size;
                iArr3[i5] = iArr3[i5] + 1;
            }
            int i11 = this.size[0];
            for (int i12 = 1; i12 < 8; i12++) {
                int[] iArr4 = this.size;
                if (i11 < iArr4[i12]) {
                    i11 = iArr4[i12];
                }
            }
            while (this.size[0] == 0 && !this.barNum.isEmpty()) {
                for (int i13 = 0; i13 < 1; i13++) {
                    int[] iArr5 = this.size;
                    int i14 = iArr5[0];
                    System.arraycopy(iArr5, 1, iArr5, 0, iArr5.length - 1);
                    int[] iArr6 = this.size;
                    iArr6[iArr6.length - 1] = i14;
                    this.barNum.remove(0);
                }
            }
            while (!this.barNum.isEmpty() && this.size[this.barNum.size() - 1] == 0) {
                CopyOnWriteArrayList<Integer> copyOnWriteArrayList = this.barNum;
                copyOnWriteArrayList.remove(copyOnWriteArrayList.size() - 1);
            }
            if (i11 == 0 || this.barNum.size() == 0) {
                this.barNum.clear();
            } else {
                if (i11 < 10) {
                    i4 = i11 + 1;
                } else {
                    i4 = i11 + (i11 / 10);
                }
                int size2 = ((this.graphWidth - (this.barNum.size() * this.barWidth)) - ((this.barNum.size() - 1) * this.barDistance)) / 2;
                for (int i15 = 0; i15 < 8; i15++) {
                    Rect rect = new Rect(size2, yFromNum(i4, this.size[i15]), this.barWidth + size2, (int) this.graphHeight);
                    size2 += this.barDistance + this.barWidth;
                    this.barRect.add(rect);
                }
            }
            this.scrollerOpen = false;
        }
    }

    private int yFromNum(int i, int i2) {
        float f = this.graphHeight;
        return (int) (f - ((((float) i2) * f) / ((float) i)));
    }

    public void hidePositionScroller(boolean z) {
        this.scrollerOpen = z;
        positionScroller(0.0f);
    }

    private void positionScroller(float f) {
        if (this.scrollerOpen) {
            int i = this.barDistance;
            int round = Math.round(((f - (((float) ((this.graphWidth - (this.barNum.size() * this.barWidth)) - ((this.barNum.size() - 1) * i))) / 2.0f)) - (((float) i) / 2.0f)) / ((float) (i + this.barWidth)));
            this.index = round;
            if (round > this.barNum.size() - 1) {
                this.index = this.barNum.size() - 1;
            }
            if (this.index < 0) {
                this.index = 0;
            }
            if (this.index < this.barNum.size()) {
                int[] iArr = this.size;
                int i2 = this.index;
                if (iArr[i2] != 0) {
                    int intValue = this.barNum.get(i2).intValue();
                    Locale locale = Locale.ENGLISH;
                    Object[] objArr = new Object[3];
                    objArr[0] = formatValue(getDistance() * intValue);
                    objArr[1] = formatValue(this.index == this.barNum.size() - 1 ? getMax() : ((intValue - 1) + this.distance) * getDistance());
                    objArr[2] = Integer.valueOf(this.size[this.index]);
                    this.scrollerLabelText = String.format(locale, "%s - %s •%d", objArr);
                    scaleScroller();
                }
            }
            this.scrollerOpen = false;
        }
        postDraw();
    }

    private void scaleScroller() {
        if (this.barRect.size() > this.index) {
            this.lock.writeLock().lock();
            try {
                int i = (int) (((double) this.standardPadding) * 2.0d);
                int i2 = this.barRect.get(this.index).left + (this.barWidth / 2);
                this.scrollerX = i2;
                int i3 = this.standardPadding;
                if (i2 >= i3 && i2 <= this.graphWidth - i3) {
                    String str = this.scrollerLabelText;
                    if (str != null) {
                        this.scrollerLabelTextPaint.getTextBounds(str, 0, str.length(), this.scrollerLabelTextBounds);
                        Rect rect = this.scrollerLabelTextBounds;
                        rect.offsetTo((int) (((float) this.scrollerX) - (((float) rect.width()) / 2.0f)), (int) ((((double) this.labelPaint.getTextSize()) * -2.0d) + (((double) this.standardPadding) * -3.0d)));
                        if (this.scrollerLabelTextBounds.left < i) {
                            Rect rect2 = this.scrollerLabelTextBounds;
                            rect2.offsetTo(i, rect2.top);
                        }
                        int i4 = this.scrollerLabelTextBounds.right + i;
                        int i5 = this.graphWidth;
                        if (i4 > i5) {
                            Rect rect3 = this.scrollerLabelTextBounds;
                            rect3.offsetTo((i5 - rect3.width()) - i, this.scrollerLabelTextBounds.top);
                        }
                        this.scrollerLabelBounds.set((float) (this.scrollerLabelTextBounds.left - i), (float) (this.scrollerLabelTextBounds.top - i), (float) (this.scrollerLabelTextBounds.right + i), (float) (this.scrollerLabelTextBounds.bottom + i));
                        this.lock.writeLock().unlock();
                        return;
                    }
                }
                this.scrollerOpen = false;
            } finally {
                this.lock.writeLock().unlock();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            this.scrollerOpen = true;
            positionScroller(motionEvent.getX());
        }
        return true;
    }

    private void postDraw() {
        new Thread(new Runnable() {
            public void run() {
                GraphBarView.this.draw();
            }
        }).start();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mHolder = surfaceHolder;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.graphHeight = (((float) i3) - (this.labelPaint.getTextSize() * 6.0f)) - (((float) this.standardPadding) * 15.0f);
        this.graphWidth = i2;
        int i4 = i2 / 13;
        this.barWidth = i4;
        this.barDistance = (int) ((((float) i2) - (((float) i4) * 8.0f)) / 8.0f);
        if (this.adapter != null) {
            postDraw();
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mHolder = null;
    }

    public void setScrollerOpen(boolean z) {
        this.scrollerOpen = z;
    }

    private float caculateTextCenterHeightDistancByBaseLine(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return ((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom;
    }
}
