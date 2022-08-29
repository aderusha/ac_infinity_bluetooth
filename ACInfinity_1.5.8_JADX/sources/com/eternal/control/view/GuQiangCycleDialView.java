package com.eternal.control.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import com.eternal.base.utils.GuQiangUtil;
import com.eternal.control.C1760R;
import com.eternal.framework.utils.ConvertUtils;
import com.eternal.widget.wheelview.common.WheelConstants;
import java.util.Locale;

public class GuQiangCycleDialView extends View {
    private static final String DASH = "- -";
    private static final String DEGREE = "℃";
    private static final String FAH = "℉";
    private static final String KPA = "kPa";
    public static final int MODE_TYPE_AUTO_HUM = 6;
    public static final int MODE_TYPE_AUTO_TMP_C = 4;
    public static final int MODE_TYPE_AUTO_TMP_F = 5;
    public static final int MODE_TYPE_CYCLE = 3;
    public static final int MODE_TYPE_OFF = 2;
    public static final int MODE_TYPE_ON = 1;
    public static final int MODE_TYPE_SCHEDULE = 7;
    public static final int MODE_TYPE_TIME_TO_OFF = 9;
    public static final int MODE_TYPE_TIME_TO_ON = 8;
    public static final int MODE_TYPE_VPD = 10;
    private static final String PERCENT = "%";
    private boolean autoCloseAll;
    private Bitmap background;
    private int currentEndShowText;
    private int currentEndTag = 0;
    private int currentStartShowText;
    private int currentStartTag = 0;
    private Bitmap decreaseTrendBitmap;
    private boolean endClose;
    private Bitmap fanBitmap;
    private Bitmap fanSmallBitmap;
    private boolean fillWhenEqual;
    private Bitmap humBitmap;
    private Bitmap increaseTrendBitmap;
    private String innerCircleSpeed = "";
    private String innerCircleTime = "";
    private String innerCircleTmp = "";
    private String innerCircleTmpUnit = "";
    private byte innerState = 0;
    private float innerTimeTextSize;
    private float innerTmpHumTextSize;
    private boolean isDeviceC;
    private boolean isEndCanDraging;
    private boolean isMoved;
    private boolean isStartCanDraging;
    private boolean isStartDraging;
    private Bitmap levelBitmap;
    private Bitmap levelSmallBitmap;
    private int mAlpha = 255;
    private Paint mArcCirclePaint;
    private int mArcKeduLenght = 15;
    private Paint mArcPaint;
    private Paint mArcTextPaint;
    private Paint mBackgroundMiddPaint;
    private String mCurrentCenterBottomShowText = "";
    private int mCurrentMode = 5;
    private float mEndTagViewRealRotateAngel;
    private int mInnerCycleWithMidCycleMargin = 11;
    private Paint mInnerPaint;
    private int mInnerR = 67;
    private int mMiddRStrok = 40;
    private int mMiddRadias;
    private OnChangeListeners mOnChangeListeners;
    private ObjectAnimator mProgressAnim;
    private float mScale = 1.0f;
    private float mStartTagViewRealRotateAngel;
    private Paint mStatusTextPaint;
    private int mWidth;
    private int maxR;
    private int maxTotal = 50;
    private Paint midSideBottomTextPaint;
    private int modifyState;
    private float needRoatteStatusAngel;
    private float needRotatteEndAngel;
    private float needRotatteSartAngel;
    private int outCycleRadius = 20;
    private int outSidePadding = 26;
    private int realMaxTotal = 50;
    private int showFan;
    private boolean showOffOrOn;
    private int slidingBlockRadias = 13;
    private boolean startClose;
    private String statusCenterBottomText = "";
    private String statusCenterText = "";
    private Bitmap statusLineBitmap;
    private Bitmap steadTrendBitmap;
    private float tempAngle;
    private float tempTextAngle;
    private Bitmap tmpBitmap;
    private Bitmap vpdBitmap;

    public interface OnChangeListeners {
        void onChangeAndEndByTouchEnd(boolean z, int i, int i2);

        void onChangeStartAndEnd(int i, int i2);

        void onChangeTouchStart(boolean z);
    }

    public GuQiangCycleDialView(Context context) {
        super(context);
        initView();
    }

    public GuQiangCycleDialView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public GuQiangCycleDialView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        setMeasuredDimension(size, size);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int min = Math.min(i2, i);
        this.mWidth = min;
        this.maxR = min / 2;
        this.mInnerR = getResources().getDimensionPixelSize(C1760R.dimen.dp_66);
        this.mInnerCycleWithMidCycleMargin = getResources().getDimensionPixelSize(C1760R.dimen.dp_0);
        this.mMiddRStrok = getResources().getDimensionPixelSize(C1760R.dimen.dp_35);
        this.outSidePadding = getResources().getDimensionPixelSize(C1760R.dimen.dp_22);
        this.slidingBlockRadias = getResources().getDimensionPixelSize(C1760R.dimen.dp_16) / 2;
        this.innerTimeTextSize = (float) getResources().getDimensionPixelSize(C1760R.dimen.dp_35);
        this.innerTmpHumTextSize = (float) getResources().getDimensionPixelSize(C1760R.dimen.dp_35);
        this.outCycleRadius = this.mInnerR + this.mInnerCycleWithMidCycleMargin + this.mMiddRStrok + this.outSidePadding;
        this.statusLineBitmap = BitmapFactory.decodeResource(getResources(), C1760R.mipmap.status_line);
        this.levelSmallBitmap = createBitmap(C1760R.mipmap.control_cicle_small);
        this.fanSmallBitmap = createBitmap(C1760R.mipmap.kongzhi_fengshan_icon);
        this.levelBitmap = createBitmap2(C1760R.mipmap.control_cicle);
        this.fanBitmap = createBitmap2(C1760R.mipmap.biapan_off_fengshan);
        this.steadTrendBitmap = createBitmap2(C1760R.mipmap.control_stead_trend);
        this.decreaseTrendBitmap = createBitmap2(C1760R.mipmap.control_off_decrease_trend);
        this.increaseTrendBitmap = createBitmap2(C1760R.mipmap.control_off_increase_trend);
        this.tmpBitmap = BitmapFactory.decodeResource(getResources(), C1760R.mipmap.control_tmp);
        this.humBitmap = BitmapFactory.decodeResource(getResources(), C1760R.mipmap.control_hum);
        this.vpdBitmap = BitmapFactory.decodeResource(getResources(), C1760R.mipmap.control_vpd);
        if (this.mWidth != 0) {
            initBackground();
        }
    }

    private Bitmap createBitmap(int i) {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), i);
        int width = decodeResource.getWidth();
        int height = decodeResource.getHeight();
        Matrix matrix = new Matrix();
        int i2 = this.mInnerR;
        matrix.setScale((((float) width) * 4.0f) / ((float) i2), (((float) height) * 4.0f) / ((float) i2));
        Bitmap.createBitmap(decodeResource, 0, 0, width, height, matrix, true);
        return decodeResource;
    }

    private Bitmap createBitmap2(int i) {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), i);
        int width = decodeResource.getWidth();
        int height = decodeResource.getHeight();
        Matrix matrix = new Matrix();
        float f = this.innerTmpHumTextSize;
        float f2 = (float) width;
        matrix.setScale((f * 0.4f) / f2, (f * 0.4f) / f2);
        return Bitmap.createBitmap(decodeResource, 0, 0, width, height, matrix, true);
    }

    public void setDeviceC(boolean z) {
        this.isDeviceC = z;
    }

    public void setFillWhenEqual(boolean z) {
        this.fillWhenEqual = z;
    }

    public void setAutoCloseAll(boolean z) {
        this.autoCloseAll = z;
        invalidate();
    }

    public boolean getAutoCloseAll() {
        return this.autoCloseAll;
    }

    private void initView() {
        initBackgroundMiddPaint();
        initInnerCyclePaint();
        initArcPaint();
        initCenterCycleBottomTextPaint();
        initStatusTextPaint();
        initAnimator();
    }

    public void showFan(int i) {
        this.showFan = i;
    }

    public void showOffOrOn(boolean z) {
        this.showOffOrOn = z;
    }

    private void initStatusTextPaint() {
        Paint paint = new Paint();
        this.mStatusTextPaint = paint;
        paint.setAntiAlias(true);
        this.mStatusTextPaint.setStyle(Paint.Style.FILL);
    }

    private void initCenterCycleBottomTextPaint() {
        Paint paint = new Paint();
        this.midSideBottomTextPaint = paint;
        paint.setAntiAlias(true);
        this.midSideBottomTextPaint.setStyle(Paint.Style.FILL);
        this.midSideBottomTextPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1760R.font.avenir_medium));
    }

    private void initArcPaint() {
        Paint paint = new Paint();
        this.mArcPaint = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mArcCirclePaint = paint2;
        paint2.setAntiAlias(true);
        this.mArcCirclePaint.setStyle(Paint.Style.FILL);
        this.mArcCirclePaint.setStrokeWidth((float) ConvertUtils.dp2px(20.0f));
        this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.white));
        Paint paint3 = new Paint();
        this.mArcTextPaint = paint3;
        paint3.setAntiAlias(true);
        this.mArcTextPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1760R.font.avenir_medium));
        this.mArcTextPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_9));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.background;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mInnerPaint);
        }
        drawMiddCircle(canvas);
        int i = this.mCurrentMode;
        if (i == 2) {
            drawInnerText(canvas);
        } else if (i == 1) {
            if (!this.showOffOrOn) {
                drawArc(canvas);
                drawStartTagView(canvas, (byte) 2);
                if (this.showFan != 0 || this.isDeviceC || this.showOffOrOn) {
                    drawStatusLine(canvas);
                }
                drawStartTagView(canvas, (byte) 1);
            }
            drawInnerText(canvas);
        } else {
            drawArc(canvas);
            drawStartTagView(canvas, (byte) 2);
            drawInnerText(canvas);
            if (this.showFan != 0 || this.isDeviceC || this.showOffOrOn) {
                drawStatusLine(canvas);
            }
            drawStartTagView(canvas, (byte) 1);
        }
    }

    private void drawStatusLine(Canvas canvas) {
        this.mStatusTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_FFF96A));
        this.mStatusTextPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
        canvas.save();
        int i = this.maxR;
        canvas.translate((float) i, (float) i);
        canvas.rotate(-150.0f);
        int i2 = this.mCurrentMode;
        if (i2 == 3) {
            canvas.rotate(this.needRoatteStatusAngel);
        } else if (i2 == 2 || i2 == 1 || i2 == 6 || i2 == 4 || i2 == 5) {
            canvas.rotate(this.needRoatteStatusAngel);
        } else {
            float f = this.needRoatteStatusAngel;
            if (f < 0.0f) {
                canvas.rotate(0.0f);
            } else if (f > 300.0f) {
                canvas.rotate(300.0f);
            } else {
                canvas.rotate(f);
            }
        }
        int width = this.statusLineBitmap.getWidth();
        int height = this.statusLineBitmap.getHeight();
        float f2 = ((float) (-width)) / 2.0f;
        float dimensionPixelSize = (float) ((-this.mInnerR) + getResources().getDimensionPixelSize(C1760R.dimen.dp_13));
        canvas.drawBitmap(this.statusLineBitmap, (Rect) null, new RectF(f2, dimensionPixelSize - ((float) height), ((float) width) + f2, dimensionPixelSize), this.mStatusTextPaint);
        canvas.restore();
    }

    private void drawMidCircleBottomText(Canvas canvas) {
        switch (this.mCurrentMode) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                drawMidCircleBottomTextByCycle(canvas, this.innerCircleSpeed);
                return;
            default:
                return;
        }
    }

    private void drawMidCircleBottomTextByCycle(Canvas canvas, String str) {
        Typeface typeface = this.mInnerPaint.getTypeface();
        this.mInnerPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1760R.font.avenir_heavy));
        this.mInnerPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_20));
        this.mInnerPaint.setColor(-1);
        int i = this.showFan;
        if (i != 0) {
            Bitmap bitmap = i == 2 ? this.levelSmallBitmap : this.fanSmallBitmap;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float measureText = this.mInnerPaint.measureText(str);
            int dimensionPixelSize = getResources().getDimensionPixelSize(C1760R.dimen.dp_5);
            int i2 = this.maxR;
            float f = (float) dimensionPixelSize;
            float f2 = (float) width;
            float f3 = ((float) i2) - (((measureText + f) + f2) / 2.0f);
            float f4 = (float) height;
            float f5 = (((float) ((i2 + this.mInnerR) + this.mInnerCycleWithMidCycleMargin)) + (((float) this.mMiddRStrok) / 2.0f)) - (f4 / 2.0f);
            float f6 = f2 + f3;
            canvas.drawBitmap(bitmap, (Rect) null, new RectF(f3, f5, f6, f4 + f5), this.mInnerPaint);
            canvas.drawText(str, f6 + f, ((float) (this.maxR + this.mInnerR + this.mInnerCycleWithMidCycleMargin)) + (((float) this.mMiddRStrok) / 2.0f) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
        } else {
            String str2 = "0".equals(str) ? "OFF" : "ON";
            if (this.isDeviceC) {
                this.mInnerPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_12));
                str2 = "ALARM";
            }
            canvas.drawText(str2, ((float) this.maxR) - (this.mInnerPaint.measureText(str2) / 2.0f), ((float) (this.maxR + this.mInnerR + this.mInnerCycleWithMidCycleMargin)) + (((float) this.mMiddRStrok) / 2.0f) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
        }
        this.mInnerPaint.setTypeface(typeface);
    }

    public void setStartCanDraging(boolean z) {
        this.isStartCanDraging = z;
        if (z) {
            this.modifyState = 1;
        }
    }

    public void setEndCanDraging(boolean z) {
        this.isEndCanDraging = z;
        if (z) {
            this.modifyState = 2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r0 != 3) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            android.view.ViewParent r0 = r3.getParent()
            r1 = 1
            r0.requestDisallowInterceptTouchEvent(r1)
            boolean r0 = r3.isEnabled()
            if (r0 != 0) goto L_0x0010
            r4 = 0
            return r4
        L_0x0010:
            int r0 = androidx.core.view.MotionEventCompat.getActionMasked(r4)
            if (r0 == 0) goto L_0x0028
            if (r0 == r1) goto L_0x0024
            r2 = 2
            if (r0 == r2) goto L_0x001f
            r2 = 3
            if (r0 == r2) goto L_0x0024
            goto L_0x002b
        L_0x001f:
            boolean r4 = r3.onDragging(r4)
            return r4
        L_0x0024:
            r3.stopDragging(r4)
            goto L_0x002b
        L_0x0028:
            r3.startDragging(r4)
        L_0x002b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.control.view.GuQiangCycleDialView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void drawStartTagView(Canvas canvas, byte b) {
        int i;
        int i2;
        Canvas canvas2 = canvas;
        byte b2 = b;
        this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.white));
        this.mArcPaint.setStyle(Paint.Style.FILL);
        this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(1.0f));
        canvas.save();
        int i3 = this.maxR;
        canvas2.translate((float) i3, (float) i3);
        canvas2.rotate(-150.0f);
        if (!this.isStartCanDraging && ((i2 = this.mCurrentMode) == 1 || i2 == 2)) {
            this.needRotatteSartAngel = ((float) this.currentStartTag) * this.tempTextAngle;
        }
        int i4 = this.slidingBlockRadias;
        float f = (float) ((-this.mMiddRadias) + ((int) (((double) i4) * 0.3d * 1.5d)) + ((int) (((double) i4) * 0.7d * 1.5d)));
        switch (this.mCurrentMode) {
            case 1:
            case 2:
                if (this.showFan != 0) {
                    canvas2.rotate(this.needRotatteSartAngel);
                    if (b2 == 0 || b2 == 1) {
                        this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.white));
                        canvas2.drawCircle(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), (float) this.slidingBlockRadias, this.mArcCirclePaint);
                    }
                    this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
                    if (b2 == 0 || b2 == 2) {
                        canvas.drawLine(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), 0.0f, f, this.mArcPaint);
                        break;
                    }
                }
                break;
            case 3:
                drawTagByCycle(canvas2, b2, f);
                break;
            case 4:
                drawTagByAuto(canvas2, f, "℃", b2);
                break;
            case 5:
                drawTagByAuto(canvas2, f, "℉", b2);
                break;
            case 6:
                drawTagByAuto(canvas2, f, PERCENT, b2);
                break;
            case 7:
                canvas2.rotate(this.needRotatteEndAngel);
                this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
                this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
                if ((b2 == 0 || b2 == 2) && this.currentEndTag != 65535) {
                    i = 65535;
                    canvas.drawLine(0.0f, (float) (-this.outCycleRadius), 0.0f, f, this.mArcPaint);
                } else {
                    i = 65535;
                }
                this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
                if ((b2 == 0 || b2 == 1) && this.currentEndTag != i) {
                    this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_FF6A6A));
                    canvas2.drawCircle(0.0f, (float) (-this.outCycleRadius), (float) this.slidingBlockRadias, this.mArcCirclePaint);
                }
                String stringForTimeByAmPm = GuQiangUtil.stringForTimeByAmPm(this.currentEndShowText);
                if (this.isEndCanDraging) {
                    this.mCurrentCenterBottomShowText = stringForTimeByAmPm;
                }
                float measureText = this.mArcTextPaint.measureText(stringForTimeByAmPm);
                Path path = new Path();
                int dp2px = this.outCycleRadius + ConvertUtils.dp2px(3.0f);
                float f2 = (float) (-dp2px);
                float f3 = (float) dp2px;
                path.addArc(new RectF(f2, f2, f3, f3), -180.0f, 180.0f);
                if ((b2 == 0 || b2 == 1) && this.currentEndTag != i) {
                    float f4 = (measureText / 2.0f) + ((float) this.slidingBlockRadias);
                    canvas.drawTextOnPath(stringForTimeByAmPm, path, ((float) ConvertUtils.dp2px(2.0f)) + f4, 0.0f, this.mArcTextPaint);
                }
                canvas2.rotate(this.needRotatteSartAngel - this.needRotatteEndAngel);
                this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
                this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
                if ((b2 == 0 || b2 == 2) && this.currentStartTag != i) {
                    canvas.drawLine(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), 0.0f, f, this.mArcPaint);
                }
                this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
                if ((b2 == 0 || b2 == 1) && this.currentStartTag != i) {
                    this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_15BAFF));
                    canvas2.drawCircle(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), (float) this.slidingBlockRadias, this.mArcCirclePaint);
                }
                String stringForTimeByAmPm2 = GuQiangUtil.stringForTimeByAmPm(this.currentStartShowText);
                if (this.isStartCanDraging) {
                    this.mCurrentCenterBottomShowText = stringForTimeByAmPm2;
                }
                float measureText2 = this.mArcTextPaint.measureText(stringForTimeByAmPm2);
                Path path2 = new Path();
                int i5 = -(this.mMiddRadias + (this.mMiddRStrok / 2) + ConvertUtils.dp2px(3.0f));
                float f5 = (float) i5;
                float f6 = (float) (-i5);
                RectF rectF = new RectF(f5, f5, f6, f6);
                this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_5CC8FF));
                path2.addArc(rectF, -180.0f, 180.0f);
                if ((b2 == 0 || b2 == 1) && this.currentStartTag != i) {
                    canvas.drawTextOnPath(stringForTimeByAmPm2, path2, (((-measureText2) / 2.0f) - ((float) this.slidingBlockRadias)) - ((float) ConvertUtils.dp2px(2.0f)), 0.0f, this.mArcTextPaint);
                    break;
                }
                break;
            case 8:
                this.mArcTextPaint.setTextAlign(Paint.Align.CENTER);
                canvas2.rotate(this.needRotatteSartAngel);
                this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
                this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
                if (b2 == 0 || b2 == 2) {
                    canvas.drawLine(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), 0.0f, f, this.mArcPaint);
                }
                if (b2 == 0 || b2 == 1) {
                    this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_15BAFF));
                    canvas2.drawCircle(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), (float) this.slidingBlockRadias, this.mArcCirclePaint);
                }
                String stringForTimeNoZero = GuQiangUtil.stringForTimeNoZero(this.currentStartShowText);
                this.mCurrentCenterBottomShowText = stringForTimeNoZero;
                this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
                float measureText3 = this.mArcTextPaint.measureText(stringForTimeNoZero);
                Path path3 = new Path();
                int i6 = -(this.mMiddRadias + (this.mMiddRStrok / 2) + ConvertUtils.dp2px(3.0f));
                float f7 = (float) i6;
                float f8 = (float) (-i6);
                path3.addArc(new RectF(f7, f7, f8, f8), -180.0f, 180.0f);
                if (b2 == 0 || b2 == 1) {
                    canvas.drawTextOnPath(stringForTimeNoZero, path3, (((-measureText3) / 2.0f) - ((float) this.slidingBlockRadias)) - ((float) ConvertUtils.dp2px(2.0f)), 0.0f, this.mArcTextPaint);
                    break;
                }
            case 9:
                this.mArcTextPaint.setTextAlign(Paint.Align.CENTER);
                canvas2.rotate(this.needRotatteSartAngel);
                this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
                this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
                if (b2 == 0 || b2 == 2) {
                    canvas.drawLine(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), 0.0f, f, this.mArcPaint);
                }
                if (b2 == 0 || b2 == 1) {
                    this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_FF6A6A));
                    canvas2.drawCircle(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), (float) this.slidingBlockRadias, this.mArcCirclePaint);
                }
                String stringForTimeNoZero2 = GuQiangUtil.stringForTimeNoZero(this.currentStartShowText);
                this.mCurrentCenterBottomShowText = stringForTimeNoZero2;
                this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
                float measureText4 = this.mArcTextPaint.measureText(stringForTimeNoZero2);
                Path path4 = new Path();
                int i7 = -(this.mMiddRadias + (this.mMiddRStrok / 2) + ConvertUtils.dp2px(3.0f));
                float f9 = (float) i7;
                float f10 = (float) (-i7);
                path4.addArc(new RectF(f9, f9, f10, f10), -180.0f, 180.0f);
                if (b2 == 0 || b2 == 1) {
                    canvas.drawTextOnPath(stringForTimeNoZero2, path4, (((-measureText4) / 2.0f) - ((float) this.slidingBlockRadias)) - ((float) ConvertUtils.dp2px(2.0f)), 0.0f, this.mArcTextPaint);
                    break;
                }
            case 10:
                drawTagByVpd(canvas2, f, KPA, b2);
                break;
        }
        canvas.restore();
    }

    private void drawTagByCycle(Canvas canvas, byte b, float f) {
        Canvas canvas2 = canvas;
        byte b2 = b;
        canvas2.rotate(this.needRotatteSartAngel);
        this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
        this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
        if (b2 == 0 || b2 == 2) {
            canvas.drawLine(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), 0.0f, f, this.mArcPaint);
        }
        if (b2 == 0 || b2 == 1) {
            this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_15BAFF));
            canvas2.drawCircle(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), (float) this.slidingBlockRadias, this.mArcCirclePaint);
        }
        String stringForTimeNoZero = GuQiangUtil.stringForTimeNoZero(this.currentStartShowText);
        this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
        if (this.isStartCanDraging) {
            this.mCurrentCenterBottomShowText = stringForTimeNoZero;
        }
        float measureText = this.mArcTextPaint.measureText(stringForTimeNoZero);
        Path path = new Path();
        int i = -(this.mMiddRadias + (this.mMiddRStrok / 2) + ConvertUtils.dp2px(3.0f));
        float f2 = (float) i;
        float f3 = (float) (-i);
        RectF rectF = new RectF(f2, f2, f3, f3);
        this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_5CC8FF));
        path.addArc(rectF, -180.0f, 180.0f);
        if (b2 == 0 || b2 == 1) {
            canvas.drawTextOnPath(stringForTimeNoZero, path, (((-measureText) / 2.0f) - ((float) this.slidingBlockRadias)) - ((float) ConvertUtils.dp2px(2.0f)), 0.0f, this.mArcTextPaint);
        }
        canvas.restore();
        canvas.save();
        int i2 = this.maxR;
        canvas2.translate((float) i2, (float) i2);
        canvas2.rotate(-150.0f);
        canvas2.rotate(158.0f);
        canvas2.rotate(142.0f - this.needRotatteEndAngel);
        this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
        if (b2 == 0 || b2 == 2) {
            canvas.drawLine(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), 0.0f, f, this.mArcPaint);
        }
        if (b2 == 0 || b2 == 1) {
            this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_FF6A6A));
            canvas2.drawCircle(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), (float) this.slidingBlockRadias, this.mArcCirclePaint);
        }
        canvas.save();
        canvas2.translate(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)));
        canvas2.rotate(-((142.0f - this.needRotatteEndAngel) + 8.0f), 0.0f, 0.0f);
        this.mArcTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
        canvas.restore();
        String stringForTimeNoZero2 = GuQiangUtil.stringForTimeNoZero(this.currentEndShowText);
        if (this.isEndCanDraging) {
            this.mCurrentCenterBottomShowText = stringForTimeNoZero2;
        }
        this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
        float measureText2 = this.mArcTextPaint.measureText(stringForTimeNoZero2);
        Path path2 = new Path();
        int dp2px = this.mMiddRadias + (this.mMiddRStrok / 2) + ConvertUtils.dp2px(3.0f);
        float f4 = (float) (-dp2px);
        float f5 = (float) dp2px;
        path2.addArc(new RectF(f4, f4, f5, f5), -180.0f, 180.0f);
        if (b2 == 0 || b2 == 1) {
            float f6 = (measureText2 / 2.0f) + ((float) this.slidingBlockRadias);
            canvas.drawTextOnPath(stringForTimeNoZero2, path2, ((float) ConvertUtils.dp2px(2.0f)) + f6, 0.0f, this.mArcTextPaint);
        }
    }

    private void drawTagByAuto(Canvas canvas, float f, String str, byte b) {
        Canvas canvas2 = canvas;
        String str2 = str;
        byte b2 = b;
        this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
        this.mArcTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
        this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
        canvas2.rotate(this.needRotatteEndAngel);
        if (!this.endClose) {
            if (b2 == 0 || b2 == 2) {
                canvas.drawLine(0.0f, (float) (-this.outCycleRadius), 0.0f, f, this.mArcPaint);
            }
            if (b2 == 0 || b2 == 1) {
                this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_FF6A6A));
                canvas2.drawCircle(0.0f, (float) (-this.outCycleRadius), (float) this.slidingBlockRadias, this.mArcCirclePaint);
            }
        }
        int i = this.currentEndShowText;
        if ("℉".equals(str2)) {
            i += 32;
        }
        String str3 = i + str2;
        if (this.isEndCanDraging) {
            this.mCurrentCenterBottomShowText = String.valueOf(this.currentEndShowText);
        }
        float measureText = this.mArcTextPaint.measureText(str3);
        Path path = new Path();
        int dp2px = this.outCycleRadius + ConvertUtils.dp2px(3.0f);
        float f2 = (float) (-dp2px);
        float f3 = (float) dp2px;
        path.addArc(new RectF(f2, f2, f3, f3), -180.0f, 180.0f);
        if (!this.endClose && (b2 == 0 || b2 == 1)) {
            canvas.drawTextOnPath(str3, path, ((float) ConvertUtils.dp2px(2.0f)) + (measureText / 2.0f) + ((float) this.slidingBlockRadias), 0.0f, this.mArcTextPaint);
        }
        canvas2.rotate(this.needRotatteSartAngel - this.needRotatteEndAngel);
        this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
        this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
        this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
        if (!this.startClose) {
            if (b2 == 0 || b2 == 2) {
                canvas.drawLine(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), 0.0f, f, this.mArcPaint);
            }
            if (b2 == 0 || b2 == 1) {
                this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_15BAFF));
                canvas2.drawCircle(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), (float) this.slidingBlockRadias, this.mArcCirclePaint);
            }
        }
        int i2 = this.currentStartShowText;
        if ("℉".equals(str2)) {
            i2 += 32;
        }
        String str4 = i2 + str2;
        if (this.isStartCanDraging) {
            this.mCurrentCenterBottomShowText = String.valueOf(this.currentStartShowText);
        }
        float measureText2 = this.mArcTextPaint.measureText(str4);
        Path path2 = new Path();
        int i3 = -(this.mMiddRadias + (this.mMiddRStrok / 2) + ConvertUtils.dp2px(3.0f));
        float f4 = (float) i3;
        float f5 = (float) (-i3);
        path2.addArc(new RectF(f4, f4, f5, f5), -180.0f, 180.0f);
        if (this.startClose) {
            return;
        }
        if (b2 == 0 || b2 == 1) {
            canvas.drawTextOnPath(str4, path2, (((-measureText2) / 2.0f) - ((float) this.slidingBlockRadias)) - ((float) ConvertUtils.dp2px(2.0f)), 0.0f, this.mArcTextPaint);
        }
    }

    private void drawTagByVpd(Canvas canvas, float f, String str, byte b) {
        String str2;
        Canvas canvas2 = canvas;
        String str3 = str;
        byte b2 = b;
        this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
        this.mArcTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
        this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_FF6A6A));
        canvas2.rotate(this.needRotatteEndAngel);
        if (!this.endClose) {
            if (b2 == 0 || b2 == 2) {
                canvas.drawLine(0.0f, (float) (-this.outCycleRadius), 0.0f, f, this.mArcPaint);
            }
            if (b2 == 0 || b2 == 1) {
                this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_FF6A6A));
                canvas2.drawCircle(0.0f, (float) (-this.outCycleRadius), (float) this.slidingBlockRadias, this.mArcCirclePaint);
            }
        }
        int i = this.currentEndShowText;
        if ("℉".equals(str3)) {
            i += 32;
        }
        String str4 = String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) i) / 10.0f)}) + str3;
        if (this.isEndCanDraging) {
            this.mCurrentCenterBottomShowText = String.valueOf(this.currentEndShowText);
        }
        float measureText = this.mArcTextPaint.measureText(str4);
        Path path = new Path();
        int dp2px = this.outCycleRadius + ConvertUtils.dp2px(3.0f);
        float f2 = (float) (-dp2px);
        float f3 = (float) dp2px;
        path.addArc(new RectF(f2, f2, f3, f3), -180.0f, 180.0f);
        if (this.endClose || !(b2 == 0 || b2 == 1)) {
            str2 = "%.1f";
        } else {
            str2 = "%.1f";
            canvas.drawTextOnPath(str4, path, ((float) ConvertUtils.dp2px(2.0f)) + (measureText / 2.0f) + ((float) this.slidingBlockRadias), 0.0f, this.mArcTextPaint);
        }
        canvas2.rotate(this.needRotatteSartAngel - this.needRotatteEndAngel);
        this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
        this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
        this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
        if (!this.startClose) {
            if (b2 == 0 || b2 == 2) {
                canvas.drawLine(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), 0.0f, f, this.mArcPaint);
            }
            if (b2 == 0 || b2 == 1) {
                this.mArcCirclePaint.setColor(ContextCompat.getColor(getContext(), C1760R.C1761color.color_15BAFF));
                canvas2.drawCircle(0.0f, -(((float) this.mMiddRadias) + (((float) this.mMiddRStrok) / 2.0f)), (float) this.slidingBlockRadias, this.mArcCirclePaint);
            }
        }
        int i2 = this.currentStartShowText;
        String str5 = String.format(Locale.ENGLISH, str2, new Object[]{Float.valueOf(((float) i2) / 10.0f)}) + str3;
        if (this.isStartCanDraging) {
            this.mCurrentCenterBottomShowText = String.valueOf(this.currentStartShowText);
        }
        float measureText2 = this.mArcTextPaint.measureText(str5);
        Path path2 = new Path();
        int i3 = -(this.mMiddRadias + (this.mMiddRStrok / 2) + ConvertUtils.dp2px(3.0f));
        float f4 = (float) i3;
        float f5 = (float) (-i3);
        path2.addArc(new RectF(f4, f4, f5, f5), -180.0f, 180.0f);
        if (this.startClose) {
            return;
        }
        if (b2 == 0 || b2 == 1) {
            canvas.drawTextOnPath(str5, path2, (((-measureText2) / 2.0f) - ((float) this.slidingBlockRadias)) - ((float) ConvertUtils.dp2px(2.0f)), 0.0f, this.mArcTextPaint);
        }
    }

    public void setStart(boolean z) {
        this.startClose = !z;
        invalidate();
    }

    public void setEnd(boolean z) {
        this.endClose = !z;
        invalidate();
    }

    public void setStart(boolean z, boolean z2) {
        float f;
        boolean z3 = !z;
        this.startClose = z3;
        if (z2) {
            if (z3) {
                f = 0.0f;
            } else {
                f = this.tempTextAngle * ((float) this.currentStartTag);
            }
            this.needRotatteSartAngel = f;
        }
        invalidate();
    }

    public void setEnd(boolean z, boolean z2) {
        float f;
        boolean z3 = !z;
        this.endClose = z3;
        if (z2) {
            if (z3) {
                f = 300.0f;
            } else {
                f = this.tempTextAngle * ((float) this.currentEndTag);
            }
            this.needRotatteEndAngel = f;
        }
        invalidate();
    }

    public boolean getStart() {
        return !this.startClose;
    }

    public boolean getEnd() {
        return !this.endClose;
    }

    private void drawArc(Canvas canvas) {
        boolean z;
        Canvas canvas2 = canvas;
        this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_80FFFFFF));
        this.mArcPaint.setStyle(Paint.Style.FILL);
        this.mArcPaint.setStrokeWidth((float) ConvertUtils.dp2px(1.0f));
        canvas.save();
        int i = this.maxR;
        canvas2.translate((float) i, (float) i);
        canvas2.rotate(-150.0f);
        int i2 = this.slidingBlockRadias;
        int i3 = (int) (((double) i2) * 0.7d * 1.5d);
        int i4 = (int) (((double) i2) * 0.3d * 1.5d);
        int i5 = 0;
        if (this.mCurrentMode == 3) {
            this.tempAngle = 142.0f / ((float) this.maxTotal);
            this.tempTextAngle = 142.0f / ((float) this.realMaxTotal);
            int i6 = 0;
            while (i6 < this.maxTotal + 1) {
                this.mArcPaint.setColor(getResources().getColor(this.tempAngle * ((float) i6) < this.needRotatteSartAngel ? C1760R.C1761color.color_FFFFFF : C1760R.C1761color.color_80FFFFFF));
                int i7 = this.mMiddRadias;
                canvas.drawLine(0.0f, (float) ((-i7) - ((i6 == 0 || i6 == this.maxTotal) ? i3 + i4 : i3 - i4)), 0.0f, (float) ((-i7) + i3 + i4), this.mArcPaint);
                canvas2.rotate(this.tempAngle);
                i6++;
            }
            canvas.restore();
            canvas.save();
            int i8 = this.maxR;
            canvas2.translate((float) i8, (float) i8);
            canvas2.rotate(-150.0f);
            canvas2.rotate(158.0f);
            while (i5 < this.maxTotal + 1) {
                this.mArcPaint.setColor(getResources().getColor(this.tempAngle * ((float) i5) > 142.0f - this.needRotatteEndAngel ? C1760R.C1761color.color_FFFFFF : C1760R.C1761color.color_80FFFFFF));
                int i9 = this.mMiddRadias;
                canvas.drawLine(0.0f, (float) ((-i9) - ((i5 == 0 || i5 == this.maxTotal) ? i3 + i4 : i3 - i4)), 0.0f, (float) ((-i9) + i3 + i4), this.mArcPaint);
                canvas2.rotate(this.tempAngle);
                i5++;
            }
        } else {
            this.tempAngle = 300.0f / ((float) this.maxTotal);
            this.tempTextAngle = 300.0f / ((float) this.realMaxTotal);
            while (i5 < this.maxTotal + 1) {
                float f = this.tempAngle * ((float) i5);
                switch (this.mCurrentMode) {
                    case 1:
                    case 2:
                    case 8:
                    case 9:
                        this.mArcPaint.setColor(getResources().getColor(f < this.needRotatteSartAngel ? C1760R.C1761color.color_FFFFFF : C1760R.C1761color.color_80FFFFFF));
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 10:
                        float f2 = this.startClose ? 0.0f : this.needRotatteSartAngel;
                        float f3 = this.endClose ? 300.0f : this.needRotatteEndAngel;
                        if (f2 < f3) {
                            this.mArcPaint.setColor(getResources().getColor((f < f2 || f > f3) ? C1760R.C1761color.color_FFFFFF : C1760R.C1761color.color_80FFFFFF));
                            break;
                        } else {
                            int i10 = C1760R.C1761color.color_80FFFFFF;
                            if ((f >= f3 && f <= f2) || (((z = this.startClose) && !this.endClose && f3 == 0.0f) || (!z && this.endClose && f2 == 300.0f))) {
                                i10 = C1760R.C1761color.color_FFFFFF;
                            }
                            this.mArcPaint.setColor(getResources().getColor(i10));
                            break;
                        }
                        break;
                    case 7:
                        if (!this.fillWhenEqual || this.needRotatteSartAngel != this.needRotatteEndAngel) {
                            if (this.needRotatteSartAngel > this.needRotatteEndAngel) {
                                this.mArcPaint.setColor(getResources().getColor((f >= this.needRotatteSartAngel || f <= this.needRotatteEndAngel) ? C1760R.C1761color.color_FFFFFF : C1760R.C1761color.color_80FFFFFF));
                                break;
                            } else {
                                this.mArcPaint.setColor(getResources().getColor((f <= this.needRotatteSartAngel || f >= this.needRotatteEndAngel) ? C1760R.C1761color.color_80FFFFFF : C1760R.C1761color.color_FFFFFF));
                                break;
                            }
                        } else {
                            this.mArcPaint.setColor(getResources().getColor(C1760R.C1761color.color_FFFFFF));
                            break;
                        }
                        break;
                }
                int i11 = this.mMiddRadias;
                canvas.drawLine(0.0f, (float) ((-i11) - (((i5 == 0 || i5 == this.maxTotal) && this.mCurrentMode != 1) ? i3 + i4 : i3 - i4)), 0.0f, (float) ((-i11) + i3 + i4), this.mArcPaint);
                canvas2.rotate(this.tempAngle);
                i5++;
            }
        }
        canvas.restore();
    }

    public void setModeType(int i) {
        this.mCurrentMode = i;
        switch (i) {
            case 1:
            case 2:
                this.maxTotal = 10;
                this.realMaxTotal = 10;
                break;
            case 3:
                this.maxTotal = 24;
                this.realMaxTotal = 1440;
                break;
            case 4:
                this.maxTotal = 45;
                this.realMaxTotal = 90;
                break;
            case 5:
                this.maxTotal = 54;
                this.realMaxTotal = 162;
                break;
            case 6:
                this.maxTotal = 50;
                this.realMaxTotal = 100;
                break;
            case 7:
            case 8:
            case 9:
                this.maxTotal = 48;
                this.realMaxTotal = 1440;
                break;
            case 10:
                this.maxTotal = 50;
                this.realMaxTotal = 100;
                break;
        }
        invalidate();
    }

    private void drawInnterTextByOn(Canvas canvas, String str, String str2, String str3, String str4) {
        this.mInnerPaint.setColor(-1);
        this.mInnerPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11));
        Paint paint = this.mInnerPaint;
        paint.measureText(str + str2);
        drawCenterFan(canvas);
    }

    private void drawInnterTextByOff(Canvas canvas, String str, String str2, String str3, String str4) {
        this.mInnerPaint.setColor(-1);
        this.mInnerPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11));
        Paint paint = this.mInnerPaint;
        paint.measureText(str + str2);
        drawCenterFanModel(canvas);
    }

    private void drawCenterFan(Canvas canvas) {
        if (this.showOffOrOn) {
            if (this.mCurrentMode == 1) {
                this.innerCircleSpeed = "ON";
            } else {
                this.innerCircleSpeed = "OFF";
            }
            this.mInnerPaint.setColor(getResources().getColor(C1760R.C1761color.white));
            this.mInnerPaint.setTextSize(this.innerTmpHumTextSize);
            float measureText = this.mInnerPaint.measureText(this.innerCircleSpeed);
            float caculateTextCenterHeightDistancByBaseLine = caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint);
            String str = this.innerCircleSpeed;
            int i = this.maxR;
            canvas.drawText(str, ((float) i) - (measureText / 2.0f), ((float) i) + caculateTextCenterHeightDistancByBaseLine, this.mInnerPaint);
            return;
        }
        this.mInnerPaint.setColor(getResources().getColor(C1760R.C1761color.white));
        this.mInnerPaint.setTextSize(this.innerTmpHumTextSize);
        float measureText2 = this.mInnerPaint.measureText(this.innerCircleSpeed);
        float caculateTextCenterHeightDistancByBaseLine2 = caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint);
        String str2 = this.innerCircleSpeed;
        int i2 = this.maxR;
        canvas.drawText(str2, ((float) i2) - (measureText2 / 2.0f), ((float) i2) + caculateTextCenterHeightDistancByBaseLine2, this.mInnerPaint);
        Bitmap bitmap = this.showFan == 2 ? this.levelBitmap : this.fanBitmap;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float dimensionPixelSize = (float) ((this.maxR - this.mInnerR) + getResources().getDimensionPixelSize(C1760R.dimen.dp_11));
        float f = (float) height;
        float f2 = ((float) this.maxR) - (f / 2.0f);
        canvas.drawBitmap(bitmap, (Rect) null, new RectF(dimensionPixelSize, f2, ((float) width) + dimensionPixelSize, f + f2), this.mInnerPaint);
        Bitmap offStateBitmap = getOffStateBitmap();
        int width2 = offStateBitmap.getWidth();
        int height2 = offStateBitmap.getHeight();
        float dimensionPixelSize2 = (float) (((this.maxR + this.mInnerR) - width2) - getResources().getDimensionPixelSize(C1760R.dimen.dp_11));
        float f3 = (float) height2;
        float f4 = ((float) this.maxR) - (f3 / 2.0f);
        canvas.drawBitmap(offStateBitmap, (Rect) null, new RectF(dimensionPixelSize2, f4, ((float) width2) + dimensionPixelSize2, f3 + f4), this.mInnerPaint);
    }

    private void drawCenterFanModel(Canvas canvas) {
        if (this.mCurrentMode == 1) {
            this.innerCircleSpeed = "ON";
        } else {
            this.innerCircleSpeed = "OFF";
        }
        this.mInnerPaint.setColor(getResources().getColor(C1760R.C1761color.white));
        this.mInnerPaint.setTextSize(this.innerTmpHumTextSize);
        float measureText = this.mInnerPaint.measureText(this.innerCircleSpeed);
        float caculateTextCenterHeightDistancByBaseLine = caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint);
        String str = this.innerCircleSpeed;
        int i = this.maxR;
        canvas.drawText(str, ((float) i) - (measureText / 2.0f), ((float) i) + caculateTextCenterHeightDistancByBaseLine, this.mInnerPaint);
    }

    /* access modifiers changed from: package-private */
    public Bitmap getOffStateBitmap() {
        byte b = this.innerState;
        if (b == 0) {
            return this.steadTrendBitmap;
        }
        if (b != 1) {
            return this.increaseTrendBitmap;
        }
        return this.decreaseTrendBitmap;
    }

    private void drawInnerTextByAuto(Canvas canvas, String str, String str2, String str3, String str4) {
        int i;
        String str5 = "SET LOW";
        if (this.isStartCanDraging || (i = this.modifyState) == 1) {
            if (this.startClose) {
                str2 = "OFF";
            } else {
                str2 = String.valueOf("℉".equals(str3) ? this.currentStartShowText + 32 : this.currentStartShowText);
            }
        } else if (this.isEndCanDraging || i == 2) {
            if (this.endClose) {
                str2 = "OFF";
            } else {
                str2 = String.valueOf("℉".equals(str3) ? this.currentEndShowText + 32 : this.currentEndShowText);
            }
            str5 = "SET HIGH";
        } else {
            str5 = "";
        }
        this.mInnerPaint.setColor(-1);
        float dimensionPixelSize = (float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11);
        this.mInnerPaint.setTextSize(dimensionPixelSize);
        float measureText = this.mInnerPaint.measureText(str5);
        int i2 = this.maxR;
        canvas.drawText(str5, ((float) i2) - (measureText / 2.0f), ((float) ((i2 - this.mInnerR) + getResources().getDimensionPixelSize(C1760R.dimen.dp_23))) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
        this.mCurrentCenterBottomShowText = str2;
        this.mInnerPaint.setColor(getResources().getColor(C1760R.C1761color.white));
        this.mInnerPaint.setTextSize(this.innerTmpHumTextSize);
        float measureText2 = this.mInnerPaint.measureText(this.mCurrentCenterBottomShowText);
        float caculateTextCenterHeightDistancByBaseLine = caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint);
        String str6 = this.mCurrentCenterBottomShowText;
        int i3 = this.maxR;
        canvas.drawText(str6, ((float) i3) - (measureText2 / 2.0f), ((float) i3) + caculateTextCenterHeightDistancByBaseLine, this.mInnerPaint);
        Bitmap bitmap = PERCENT.equals(str3) ? this.humBitmap : this.tmpBitmap;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = (float) width;
        float dimensionPixelSize2 = ((float) ((this.maxR - this.mInnerR) + getResources().getDimensionPixelSize(C1760R.dimen.dp_15))) - (f / 2.0f);
        float f2 = (float) height;
        float dimensionPixelSize3 = (((float) this.maxR) - (f2 / 2.0f)) + ((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_4));
        canvas.drawBitmap(bitmap, (Rect) null, new RectF(dimensionPixelSize2, dimensionPixelSize3, f + dimensionPixelSize2, f2 + dimensionPixelSize3), this.mInnerPaint);
        this.mInnerPaint.setTextSize(dimensionPixelSize);
        canvas.drawText(str3, (((float) (this.maxR + this.mInnerR)) - this.mInnerPaint.measureText(str3)) - ((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11)), ((float) this.maxR) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint) + ((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_4)), this.mInnerPaint);
    }

    private void drawInnerTextByVpd(Canvas canvas, String str, String str2, String str3, String str4) {
        int i;
        String str5 = "SET LOW";
        if (this.isStartCanDraging || (i = this.modifyState) == 1) {
            if (this.startClose) {
                str2 = "OFF";
            } else {
                str2 = String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) this.currentStartShowText) / 10.0f)});
            }
        } else if (this.isEndCanDraging || i == 2) {
            if (this.endClose) {
                str2 = "OFF";
            } else {
                str2 = String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) this.currentEndShowText) / 10.0f)});
            }
            str5 = "SET HIGH";
        } else {
            str5 = "";
        }
        this.mInnerPaint.setColor(-1);
        float dimensionPixelSize = (float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11);
        this.mInnerPaint.setTextSize(dimensionPixelSize);
        float measureText = this.mInnerPaint.measureText(str5);
        int i2 = this.maxR;
        canvas.drawText(str5, ((float) i2) - (measureText / 2.0f), ((float) ((i2 - this.mInnerR) + getResources().getDimensionPixelSize(C1760R.dimen.dp_23))) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
        this.mCurrentCenterBottomShowText = str2;
        this.mInnerPaint.setColor(getResources().getColor(C1760R.C1761color.white));
        this.mInnerPaint.setTextSize(this.innerTmpHumTextSize);
        float measureText2 = this.mInnerPaint.measureText(this.mCurrentCenterBottomShowText);
        float caculateTextCenterHeightDistancByBaseLine = caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint);
        String str6 = this.mCurrentCenterBottomShowText;
        int i3 = this.maxR;
        canvas.drawText(str6, ((float) i3) - (measureText2 / 2.0f), ((float) i3) + caculateTextCenterHeightDistancByBaseLine, this.mInnerPaint);
        int width = this.vpdBitmap.getWidth();
        int height = this.vpdBitmap.getHeight();
        float f = (float) width;
        float dimensionPixelSize2 = ((float) ((this.maxR - this.mInnerR) + getResources().getDimensionPixelSize(C1760R.dimen.dp_15))) - (f / 2.0f);
        float f2 = (float) height;
        float dimensionPixelSize3 = (((float) this.maxR) - (f2 / 2.0f)) + ((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_4));
        canvas.drawBitmap(this.vpdBitmap, (Rect) null, new RectF(dimensionPixelSize2, dimensionPixelSize3, f + dimensionPixelSize2, f2 + dimensionPixelSize3), this.mInnerPaint);
        this.mInnerPaint.setTextSize(dimensionPixelSize);
        canvas.drawText(str3, (((float) (this.maxR + this.mInnerR)) - this.mInnerPaint.measureText(str3)) - ((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11)), ((float) this.maxR) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint) + ((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_4)), this.mInnerPaint);
    }

    private void drawInnerTextByTimeToOn(Canvas canvas, String str, String str2, String str3, String str4, String str5) {
        int i;
        String str6 = "SET";
        if (this.isStartCanDraging || (i = this.modifyState) == 1) {
            int i2 = this.mCurrentMode;
            str5 = i2 == 8 ? "TO ON" : i2 == 9 ? "TO OFF" : "ON DURATION";
            str = this.mCurrentCenterBottomShowText;
        } else if (this.isEndCanDraging || i == 2) {
            str = this.mCurrentCenterBottomShowText;
            str5 = "OFF DURATION";
        } else if ("0:00".equals(str) || "0".equals(str)) {
            if (this.mCurrentMode == 3 && this.currentEndShowText == 0 && this.currentStartShowText == 0) {
                str5 = "OFF";
            } else if (str5 != null) {
                str5 = str5.replace("TO ", "");
            } else {
                str5 = "";
            }
            str6 = "FINISHED";
        } else {
            str6 = "ACTIVATED";
        }
        this.mInnerPaint.setColor(getResources().getColor(C1760R.C1761color.white));
        this.mInnerPaint.setTextSize(this.innerTimeTextSize * this.mScale);
        canvas.drawText(str, ((float) this.maxR) - (this.mInnerPaint.measureText(str) / 2.0f), ((float) this.maxR) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
        this.midSideBottomTextPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11));
        this.midSideBottomTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_FFFFFF));
        this.midSideBottomTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(str5, (float) this.maxR, (float) (((this.maxR + this.mInnerR) - getResources().getDimensionPixelSize(C1760R.dimen.dp_29)) + caculateTextCenterHeightDisTance(this.midSideBottomTextPaint)), this.midSideBottomTextPaint);
        this.mInnerPaint.setColor(-1);
        this.mInnerPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11));
        this.mInnerPaint.setAlpha(this.mAlpha);
        float measureText = this.mInnerPaint.measureText(str6);
        int i3 = this.maxR;
        canvas.drawText(str6, ((float) i3) - (measureText / 2.0f), ((float) ((i3 - this.mInnerR) + getResources().getDimensionPixelSize(C1760R.dimen.dp_23))) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
    }

    private void drawInnerTextBySchedule(Canvas canvas, String str, String str2, String str3, String str4, String str5) {
        int i;
        boolean z = this.isStartCanDraging;
        String str6 = DASH;
        String str7 = "SET";
        String str8 = "";
        if (z || (i = this.modifyState) == 1) {
            String[] split = this.mCurrentCenterBottomShowText.split(" ");
            if (split.length >= 2) {
                if (this.currentStartTag != 65535) {
                    str6 = split[0];
                    str8 = split[1];
                }
                this.mInnerPaint.setColor(getResources().getColor(C1760R.C1761color.white));
                this.mInnerPaint.setTextSize(this.innerTmpHumTextSize);
                float measureText = this.mInnerPaint.measureText(str6);
                float caculateTextCenterHeightDistancByBaseLine = caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint);
                this.mInnerPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_12));
                float measureText2 = this.mInnerPaint.measureText(str8);
                canvas.drawText(str8, (((float) this.maxR) + ((((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_5)) + measureText) / 2.0f)) - (measureText2 / 2.0f), ((float) this.maxR) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
                this.mInnerPaint.setTextSize(this.innerTimeTextSize * this.mScale);
                canvas.drawText(str6, ((float) this.maxR) - (((measureText2 + measureText) + ((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_5))) / 2.0f), ((float) this.maxR) + caculateTextCenterHeightDistancByBaseLine, this.mInnerPaint);
            }
            str5 = "START TIME";
        } else if (this.isEndCanDraging || i == 2) {
            String[] split2 = this.mCurrentCenterBottomShowText.split(" ");
            if (split2.length >= 2) {
                if (this.currentEndTag != 65535) {
                    str6 = split2[0];
                    str8 = split2[1];
                }
                this.mInnerPaint.setColor(getResources().getColor(C1760R.C1761color.white));
                this.mInnerPaint.setTextSize(this.innerTmpHumTextSize);
                float measureText3 = this.mInnerPaint.measureText(str6);
                float caculateTextCenterHeightDistancByBaseLine2 = caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint);
                this.mInnerPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_12));
                float measureText4 = this.mInnerPaint.measureText(str8);
                canvas.drawText(str8, (((float) this.maxR) + ((((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_5)) + measureText3) / 2.0f)) - (measureText4 / 2.0f), ((float) this.maxR) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
                this.mInnerPaint.setTextSize(this.innerTimeTextSize * this.mScale);
                canvas.drawText(str6, ((float) this.maxR) - (((measureText4 + measureText3) + ((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_5))) / 2.0f), ((float) this.maxR) + caculateTextCenterHeightDistancByBaseLine2, this.mInnerPaint);
            }
            str5 = "END TIME";
        } else {
            if ("0:00".equals(str) || "0".equals(str)) {
                if (this.needRotatteSartAngel == this.needRotatteEndAngel) {
                    str5 = this.fillWhenEqual ? "ON" : "OFF";
                } else if (str5 != null) {
                    str5 = str5.replace("TO ", str8);
                } else {
                    str7 = "FINISHED";
                    str5 = str8;
                }
                str7 = "FINISHED";
            } else {
                str7 = "ACTIVATED";
            }
            this.mInnerPaint.setTextSize(this.innerTimeTextSize * this.mScale);
            this.mInnerPaint.setColor(getResources().getColor(C1760R.C1761color.white));
            canvas.drawText(str, ((float) this.maxR) - (this.mInnerPaint.measureText(str) / 2.0f), ((float) this.maxR) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
        }
        this.midSideBottomTextPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11));
        this.midSideBottomTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_FFFFFF));
        this.midSideBottomTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(str5, (float) this.maxR, (float) (((this.maxR + this.mInnerR) - getResources().getDimensionPixelSize(C1760R.dimen.dp_29)) + caculateTextCenterHeightDisTance(this.midSideBottomTextPaint)), this.midSideBottomTextPaint);
        this.mInnerPaint.setColor(-1);
        this.mInnerPaint.setTextSize((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_11));
        float measureText5 = this.mInnerPaint.measureText(str7);
        int i2 = this.maxR;
        canvas.drawText(str7, ((float) i2) - (measureText5 / 2.0f), ((float) ((i2 - this.mInnerR) + getResources().getDimensionPixelSize(C1760R.dimen.dp_23))) + caculateTextCenterHeightDistancByBaseLine(this.mInnerPaint), this.mInnerPaint);
    }

    private void drawInnerText(Canvas canvas) {
        switch (this.mCurrentMode) {
            case 1:
                drawInnterTextByOn(canvas, this.innerCircleTmp, this.innerCircleTmpUnit, this.innerCircleTime, this.innerCircleSpeed);
                return;
            case 2:
                drawInnterTextByOff(canvas, this.innerCircleTmp, this.innerCircleTmpUnit, this.innerCircleTime, this.innerCircleSpeed);
                return;
            case 3:
            case 8:
            case 9:
                drawInnerTextByTimeToOn(canvas, this.statusCenterText, this.innerCircleTmp, this.innerCircleTmpUnit, this.innerCircleSpeed, this.statusCenterBottomText);
                return;
            case 4:
            case 5:
            case 6:
                drawInnerTextByAuto(canvas, this.innerCircleTime, this.innerCircleTmp, this.innerCircleTmpUnit, this.innerCircleSpeed);
                return;
            case 7:
                drawInnerTextBySchedule(canvas, this.innerCircleTime, this.innerCircleTmp, this.innerCircleTmpUnit, this.innerCircleSpeed, this.statusCenterBottomText);
                return;
            case 10:
                drawInnerTextByVpd(canvas, this.innerCircleTime, this.innerCircleTmp, this.innerCircleTmpUnit, this.innerCircleSpeed);
                return;
            default:
                return;
        }
    }

    public void setInnerCircleText(String str, String str2, String str3, String str4, byte b) {
        this.innerCircleTmp = str;
        this.innerCircleTmpUnit = str2;
        this.innerCircleSpeed = str4;
        this.innerCircleTime = str3;
        this.innerState = b;
        this.modifyState = 0;
    }

    public void setTmp(String str, String str2) {
        this.innerCircleTmp = str;
        this.innerCircleTmpUnit = str2;
    }

    private void initBackground() {
        Paint paint = new Paint(5);
        paint.setStrokeWidth((float) getResources().getDimensionPixelSize(C1760R.dimen.dp_1));
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(C1760R.C1761color.color_BFBFBF));
        int i = this.outCycleRadius;
        int i2 = this.mWidth;
        this.background = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.background);
        int i3 = this.maxR;
        canvas.drawCircle((float) i3, (float) i3, (float) i, paint);
    }

    private void drawMiddCircle(Canvas canvas) {
        this.mBackgroundMiddPaint.setMaskFilter((MaskFilter) null);
        this.mBackgroundMiddPaint.setStyle(Paint.Style.STROKE);
        this.mBackgroundMiddPaint.setStrokeWidth((float) this.mMiddRStrok);
        int i = this.mCurrentMode;
        boolean z = i == 6 || i == 5 || i == 4 || i == 10 || i == 7 ? this.autoCloseAll : i == 2;
        int i2 = this.mMiddRStrok;
        float f = (float) i2;
        float f2 = (float) (this.mWidth - i2);
        int[] iArr = new int[2];
        iArr[0] = getResources().getColor(z ? C1760R.C1761color.color_242425 : C1760R.C1761color.color_21409A);
        iArr[1] = getResources().getColor(z ? C1760R.C1761color.color_242425 : C1760R.C1761color.color_00B8EC);
        this.mBackgroundMiddPaint.setShader(new LinearGradient(f, 0.0f, f2, 0.0f, iArr, (float[]) null, Shader.TileMode.CLAMP));
        int i3 = this.mInnerCycleWithMidCycleMargin + this.mInnerR + (this.mMiddRStrok / 2);
        this.mMiddRadias = i3;
        int i4 = this.maxR;
        canvas.drawCircle((float) i4, (float) i4, (float) i3, this.mBackgroundMiddPaint);
    }

    private void initInnerCyclePaint() {
        Paint paint = new Paint();
        this.mInnerPaint = paint;
        paint.setAntiAlias(true);
        this.mInnerPaint.setStyle(Paint.Style.FILL);
        this.mInnerPaint.setTypeface(ResourcesCompat.getFont(getContext(), C1760R.font.avenir_medium));
    }

    private void initBackgroundMiddPaint() {
        Paint paint = new Paint();
        this.mBackgroundMiddPaint = paint;
        paint.setAntiAlias(true);
        this.mBackgroundMiddPaint.setStyle(Paint.Style.FILL);
    }

    private float caculateTextCenterHeightDistancByBaseLine(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return ((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom;
    }

    private int caculateTextCenterHeightDisTance(Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds("0", 0, 1, rect);
        return (rect.bottom + this.maxR) - (rect.top + this.maxR);
    }

    public void initStartOrEndAngle(int i, int i2, int i3, int i4) {
        this.currentStartTag = i;
        this.currentEndTag = i3;
        int i5 = this.mCurrentMode;
        if (i5 != 3) {
            float f = 300.0f;
            if (i5 == 4 || i5 == 5 || i5 == 6 || i5 == 10) {
                float f2 = 300.0f / ((float) this.realMaxTotal);
                this.tempTextAngle = f2;
                this.needRotatteSartAngel = this.startClose ? 0.0f : ((float) i) * f2;
                if (!this.endClose) {
                    f = f2 * ((float) i3);
                }
                this.needRotatteEndAngel = f;
            } else {
                float f3 = 300.0f / ((float) this.realMaxTotal);
                this.tempTextAngle = f3;
                this.needRotatteSartAngel = ((float) i) * f3;
                this.needRotatteEndAngel = f3 * ((float) i3);
            }
        } else {
            float f4 = 142.0f / ((float) this.realMaxTotal);
            this.tempTextAngle = f4;
            this.needRotatteSartAngel = ((float) i) * f4;
            this.needRotatteEndAngel = f4 * ((float) i3);
        }
        this.currentStartShowText = i2;
        this.currentEndShowText = i4;
        invalidate();
    }

    private void startDragging(MotionEvent motionEvent) {
        OnChangeListeners onChangeListeners;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int i = this.mCurrentMode;
        boolean z = (i != 1 || !this.showOffOrOn) ? i != 2 : false;
        if (z) {
            if (i != 6 && i != 5 && i != 4 && i != 10) {
                float f = this.needRotatteSartAngel;
                int i2 = this.mMiddRadias;
                int i3 = this.maxR;
                setStartCanDraging(isInCircle(f, i2, i3, i3, x, y));
            } else if (!this.startClose) {
                float f2 = this.needRotatteSartAngel;
                int i4 = this.mMiddRadias;
                int i5 = this.maxR;
                setStartCanDraging(isInCircle(f2, i4, i5, i5, x, y));
            }
        }
        if (z && !this.isStartCanDraging) {
            int i6 = this.mCurrentMode;
            if (i6 == 6 || i6 == 5 || i6 == 4 || i6 == 10) {
                if (!this.endClose) {
                    float f3 = this.needRotatteEndAngel;
                    int i7 = this.outCycleRadius;
                    int i8 = this.maxR;
                    setEndCanDraging(endTagViewIsCanMove(f3, i7, i8, i8, x, y));
                }
            } else if (i6 == 3) {
                int i9 = this.mMiddRadias;
                int i10 = this.maxR;
                setEndCanDraging(endTagViewIsCanMove(300.0f - this.needRotatteEndAngel, i9, i10, i10, x, y));
            } else {
                float f4 = this.needRotatteEndAngel;
                int i11 = this.outCycleRadius;
                int i12 = this.maxR;
                setEndCanDraging(endTagViewIsCanMove(f4, i11, i12, i12, x, y));
            }
        }
        this.isStartDraging = true;
        this.isMoved = true;
        boolean z2 = this.isStartCanDraging;
        if ((z2 || this.isEndCanDraging) && (onChangeListeners = this.mOnChangeListeners) != null) {
            onChangeListeners.onChangeTouchStart(z2);
        }
    }

    private boolean onDragging(MotionEvent motionEvent) {
        if ((!this.isStartCanDraging && !this.isEndCanDraging) || !this.isMoved) {
            return false;
        }
        int rotationBetweenLinesForMark = getRotationBetweenLinesForMark(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, motionEvent.getX(), motionEvent.getY());
        int i = this.mCurrentMode;
        if (i == 3) {
            if (this.isStartCanDraging) {
                if (rotationBetweenLinesForMark < 0) {
                    if (rotationBetweenLinesForMark < -10) {
                        this.isMoved = false;
                    }
                    rotationBetweenLinesForMark = 0;
                }
                if (rotationBetweenLinesForMark > 142) {
                    if (rotationBetweenLinesForMark > 152) {
                        this.isMoved = false;
                    }
                    rotationBetweenLinesForMark = 142;
                }
                float f = (float) rotationBetweenLinesForMark;
                this.currentStartTag = (int) (f / this.tempAngle);
                int i2 = ((int) ((f / this.tempTextAngle) / 15.0f)) * 15;
                this.currentStartShowText = i2;
                this.needRotatteSartAngel = f;
                OnChangeListeners onChangeListeners = this.mOnChangeListeners;
                if (onChangeListeners != null) {
                    onChangeListeners.onChangeStartAndEnd(i2, this.currentEndShowText);
                }
                postInvalidate();
                return true;
            } else if (this.isEndCanDraging) {
                if (rotationBetweenLinesForMark < 158) {
                    if (rotationBetweenLinesForMark < 148) {
                        this.isMoved = false;
                    }
                    rotationBetweenLinesForMark = 158;
                }
                if (rotationBetweenLinesForMark > 300) {
                    if (rotationBetweenLinesForMark > 310) {
                        this.isMoved = false;
                    }
                    rotationBetweenLinesForMark = WheelConstants.WHEEL_SCROLL_DELAY_DURATION;
                }
                float f2 = (float) (WheelConstants.WHEEL_SCROLL_DELAY_DURATION - rotationBetweenLinesForMark);
                this.currentEndTag = (int) (f2 / this.tempAngle);
                int i3 = ((int) ((f2 / this.tempTextAngle) / 15.0f)) * 15;
                this.currentEndShowText = i3;
                this.needRotatteEndAngel = f2;
                OnChangeListeners onChangeListeners2 = this.mOnChangeListeners;
                if (onChangeListeners2 != null) {
                    onChangeListeners2.onChangeStartAndEnd(this.currentStartShowText, i3);
                }
                postInvalidate();
                return true;
            }
        } else if (rotationBetweenLinesForMark < 0) {
            if (this.isStartCanDraging) {
                if (rotationBetweenLinesForMark < -10) {
                    this.isMoved = false;
                }
                float f3 = (float) 0;
                this.currentStartTag = (int) (f3 / this.tempAngle);
                this.currentStartShowText = (int) (f3 / this.tempTextAngle);
                rotationBetweenLinesForMark = 0;
            }
            if (this.isEndCanDraging) {
                if (rotationBetweenLinesForMark < -10) {
                    this.isMoved = false;
                }
                float f4 = (float) 0;
                this.currentEndTag = (int) (f4 / this.tempAngle);
                this.currentEndShowText = (int) (f4 / this.tempTextAngle);
            }
            OnChangeListeners onChangeListeners3 = this.mOnChangeListeners;
            if (onChangeListeners3 != null) {
                onChangeListeners3.onChangeStartAndEnd(this.currentStartShowText, this.currentEndShowText);
            }
            postInvalidate();
            return true;
        } else if (rotationBetweenLinesForMark > 300) {
            if (this.isStartCanDraging) {
                if (rotationBetweenLinesForMark > 310) {
                    this.isMoved = false;
                }
                float f5 = (float) WheelConstants.WHEEL_SCROLL_DELAY_DURATION;
                this.currentStartTag = (int) (f5 / this.tempAngle);
                this.currentStartShowText = (int) (f5 / this.tempTextAngle);
                this.needRotatteSartAngel = f5;
                rotationBetweenLinesForMark = WheelConstants.WHEEL_SCROLL_DELAY_DURATION;
            }
            if (this.isEndCanDraging) {
                if (rotationBetweenLinesForMark > 310) {
                    this.isMoved = false;
                }
                float f6 = (float) WheelConstants.WHEEL_SCROLL_DELAY_DURATION;
                this.currentEndTag = (int) (f6 / this.tempAngle);
                this.currentEndShowText = (int) (f6 / this.tempTextAngle);
                this.needRotatteEndAngel = f6;
            }
            OnChangeListeners onChangeListeners4 = this.mOnChangeListeners;
            if (onChangeListeners4 != null) {
                onChangeListeners4.onChangeStartAndEnd(this.currentStartShowText, this.currentEndShowText);
            }
            postInvalidate();
            return true;
        }
        if (this.isStartCanDraging) {
            float f7 = (float) rotationBetweenLinesForMark;
            this.needRotatteSartAngel = f7;
            this.mStartTagViewRealRotateAngel = f7;
            this.currentStartTag = (int) (f7 / this.tempAngle);
            if (i == 9 || i == 8 || i == 7) {
                this.currentStartShowText = ((int) ((f7 / this.tempTextAngle) / 15.0f)) * 15;
            } else {
                this.currentStartShowText = (int) (f7 / this.tempTextAngle);
            }
        }
        if (this.isEndCanDraging) {
            float f8 = (float) rotationBetweenLinesForMark;
            this.needRotatteEndAngel = f8;
            this.mEndTagViewRealRotateAngel = f8;
            this.currentEndTag = (int) (f8 / this.tempAngle);
            if (i == 7) {
                this.currentEndShowText = ((int) ((f8 / this.tempTextAngle) / 15.0f)) * 15;
            } else {
                this.currentEndShowText = (int) (f8 / this.tempTextAngle);
            }
        }
        OnChangeListeners onChangeListeners5 = this.mOnChangeListeners;
        if (onChangeListeners5 != null) {
            onChangeListeners5.onChangeStartAndEnd(this.currentStartShowText, this.currentEndShowText);
        }
        postInvalidate();
        return true;
    }

    private void stopDragging(MotionEvent motionEvent) {
        if (!this.isMoved) {
            boolean z = this.isStartCanDraging;
            setStartCanDraging(false);
            setEndCanDraging(false);
            this.isStartDraging = false;
            OnChangeListeners onChangeListeners = this.mOnChangeListeners;
            if (onChangeListeners != null) {
                onChangeListeners.onChangeAndEndByTouchEnd(z, this.currentStartShowText, this.currentEndShowText);
            }
        } else if (this.isStartCanDraging || this.isEndCanDraging) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int i = this.maxR;
            float rotationBetweenLinesForMark = (float) getRotationBetweenLinesForMark((float) i, (float) i, x, y);
            float f = this.tempAngle;
            float f2 = rotationBetweenLinesForMark % f;
            int i2 = this.mCurrentMode;
            if (i2 == 3 && this.isEndCanDraging) {
                f2 = (rotationBetweenLinesForMark - 158.0f) % f;
            }
            if (i2 == 1 || i2 == 2) {
                if (f2 > f / 2.0f) {
                    if (this.isStartCanDraging) {
                        this.currentStartTag++;
                    }
                    if (this.isEndCanDraging) {
                        this.currentEndTag++;
                    }
                }
                if (this.maxTotal == this.realMaxTotal) {
                    this.currentStartShowText = this.currentStartTag;
                    this.currentEndShowText = this.currentEndTag;
                }
                OnChangeListeners onChangeListeners2 = this.mOnChangeListeners;
                if (onChangeListeners2 != null) {
                    onChangeListeners2.onChangeStartAndEnd(this.currentStartShowText, this.currentEndShowText);
                }
            }
            postInvalidate();
            boolean z2 = this.isStartCanDraging;
            setStartCanDraging(false);
            setEndCanDraging(false);
            this.isStartDraging = false;
            this.isMoved = false;
            OnChangeListeners onChangeListeners3 = this.mOnChangeListeners;
            if (onChangeListeners3 != null) {
                onChangeListeners3.onChangeAndEndByTouchEnd(z2, this.currentStartShowText, this.currentEndShowText);
            }
        }
    }

    private boolean isInCircle(float f, int i, int i2, int i3, int i4, int i5, int i6) {
        double[] caculateXY = caculateXY(i3, i4, i, f > 240.0f ? f - 240.0f : f + 120.0f);
        if (((double) ((int) (Math.pow((double) Math.abs((int) (caculateXY[0] - ((double) i5))), 2.0d) + Math.pow((double) Math.abs((int) (caculateXY[1] - ((double) i6))), 2.0d)))) > Math.pow((double) i2, 2.0d)) {
            return false;
        }
        return true;
    }

    private boolean isInCircle(float f, int i, int i2, int i3, float f2, float f3) {
        int dp2px = ConvertUtils.dp2px((float) this.slidingBlockRadias);
        RectF rectF = new RectF((float) (i2 - dp2px), (float) (this.mInnerR + i3 + this.mInnerCycleWithMidCycleMargin), (float) (i2 + dp2px), (float) (i + i3 + (dp2px * 2)));
        Matrix matrix = new Matrix();
        matrix.setRotate(f + 30.0f, (float) i2, (float) i3);
        matrix.mapRect(rectF);
        return rectF.contains(f2, f3);
    }

    private double[] caculateXY(int i, int i2, int i3, float f) {
        double d = (double) i3;
        double d2 = (((double) f) * 3.141592653589793d) / 180.0d;
        return new double[]{((double) i) + (Math.cos(d2) * d), ((double) i2) + (d * Math.sin(d2))};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r0.mCurrentMode;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean endTagViewIsCanMove(float r1, int r2, int r3, int r4, int r5, int r6, int r7) {
        /*
            r0 = this;
            boolean r1 = r0.isInCircle(r1, r2, r3, r4, r5, r6, r7)
            if (r1 == 0) goto L_0x0019
            int r1 = r0.mCurrentMode
            r2 = 3
            if (r1 == r2) goto L_0x0017
            r2 = 7
            if (r1 == r2) goto L_0x0017
            r2 = 4
            if (r1 == r2) goto L_0x0017
            r2 = 5
            if (r1 == r2) goto L_0x0017
            r2 = 6
            if (r1 != r2) goto L_0x0019
        L_0x0017:
            r1 = 1
            goto L_0x001a
        L_0x0019:
            r1 = 0
        L_0x001a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.control.view.GuQiangCycleDialView.endTagViewIsCanMove(float, int, int, int, int, int, int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r0.mCurrentMode;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean endTagViewIsCanMove(float r1, int r2, int r3, int r4, float r5, float r6) {
        /*
            r0 = this;
            boolean r1 = r0.isInCircle(r1, r2, r3, r4, r5, r6)
            if (r1 == 0) goto L_0x001d
            int r1 = r0.mCurrentMode
            r2 = 3
            if (r1 == r2) goto L_0x001b
            r2 = 7
            if (r1 == r2) goto L_0x001b
            r2 = 4
            if (r1 == r2) goto L_0x001b
            r2 = 5
            if (r1 == r2) goto L_0x001b
            r2 = 6
            if (r1 == r2) goto L_0x001b
            r2 = 10
            if (r1 != r2) goto L_0x001d
        L_0x001b:
            r1 = 1
            goto L_0x001e
        L_0x001d:
            r1 = 0
        L_0x001e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.control.view.GuQiangCycleDialView.endTagViewIsCanMove(float, int, int, int, float, float):boolean");
    }

    private int getRotationBetweenLinesForMark(float f, float f2, float f3, float f4) {
        double d = ((double) (f2 - f2)) / ((double) ((2.0f * f) - f));
        double d2 = ((double) (f4 - f2)) / ((double) (f3 - f));
        double d3 = 180.0d;
        double atan = (Math.atan(Math.abs(d - d2) / ((d * d2) + 1.0d)) / 3.141592653589793d) * 180.0d;
        double d4 = 240.0d;
        int i = (f3 > f ? 1 : (f3 == f ? 0 : -1));
        if (i <= 0 || f4 >= f2) {
            if (i <= 0 || f4 <= f2) {
                d4 = 60.0d;
                int i2 = (f3 > f ? 1 : (f3 == f ? 0 : -1));
                if (i2 >= 0 || f4 <= f2) {
                    if (i2 >= 0 || f4 >= f2) {
                        if ((i == 0 && f4 < f2) || i != 0 || f4 <= f2) {
                            d3 = 0.0d;
                        }
                        return (int) d3;
                    }
                }
            }
            d3 = atan + d4;
            return (int) d3;
        }
        d3 = d4 - atan;
        return (int) d3;
    }

    private void drawTextByCyclePointByCycleEnd(Canvas canvas, String str, float f) {
        if (f < 38.0f) {
            canvas.translate(0.0f, (float) (-(this.mMiddRadias + (ConvertUtils.dp2px((float) this.mMiddRStrok) / 2) + ConvertUtils.dp2px(18.0f))));
            canvas.rotate(-(f + 8.0f), 0.0f, 0.0f);
            this.mArcTextPaint.setTextAlign(Paint.Align.LEFT);
            this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
            canvas.drawText(str, 0.0f, caculateTextCenterHeightDistancByBaseLine(this.mArcTextPaint), this.mArcTextPaint);
        } else if (f <= 37.0f || f >= 112.0f) {
            canvas.translate(0.0f, (float) (-(this.mMiddRadias + (ConvertUtils.dp2px((float) this.mMiddRStrok) / 2) + ConvertUtils.dp2px(18.0f))));
            canvas.rotate(-(f + 8.0f), 0.0f, 0.0f);
            this.mArcTextPaint.setTextAlign(Paint.Align.LEFT);
            this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
            canvas.drawText(str, 0.0f, caculateTextCenterHeightDistancByBaseLine(this.mArcTextPaint), this.mArcTextPaint);
        } else {
            canvas.translate((float) (-(ConvertUtils.dp2px(13.0f) + caculateTextCenterHeightDisTance(this.mArcTextPaint))), (float) (-(this.mMiddRadias + (ConvertUtils.dp2px((float) this.mMiddRStrok) / 2))));
            canvas.rotate(-(f + 8.0f), 0.0f, 0.0f);
            this.mArcTextPaint.setTextAlign(Paint.Align.LEFT);
            this.mArcTextPaint.setColor(getResources().getColor(C1760R.C1761color.color_15BAFF));
            canvas.drawText(str, 0.0f, caculateTextCenterHeightDistancByBaseLine(this.mArcTextPaint), this.mArcTextPaint);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStatusLineAndCenterText(java.lang.String r17, java.lang.String r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            int r2 = r0.mCurrentMode
            java.lang.String r3 = "%d"
            java.lang.String r5 = "%d:%02d"
            java.lang.String r7 = "TO OFF"
            java.lang.String r8 = "TO ON"
            r9 = 1114636288(0x42700000, float:60.0)
            java.lang.String r10 = "0"
            r11 = 3600(0xe10, double:1.7786E-320)
            switch(r2) {
                case 1: goto L_0x0139;
                case 2: goto L_0x0017;
                case 3: goto L_0x00bf;
                case 4: goto L_0x00a8;
                case 5: goto L_0x00a8;
                case 6: goto L_0x00a8;
                case 7: goto L_0x0088;
                case 8: goto L_0x0019;
                case 9: goto L_0x0019;
                case 10: goto L_0x00a8;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x014e
        L_0x0019:
            boolean r2 = android.text.TextUtils.isEmpty(r17)
            if (r2 != 0) goto L_0x014e
            r2 = r5
            long r4 = java.lang.Long.parseLong(r17)
            float r15 = (float) r4
            float r6 = r0.tempTextAngle
            float r15 = r15 * r6
            float r15 = r15 / r9
            r0.needRoatteStatusAngel = r15
            long r13 = r4 / r11
            int r6 = (int) r13
            long r4 = r4 % r11
            r11 = 60
            long r13 = r4 / r11
            int r9 = (int) r13
            long r4 = r4 % r11
            int r5 = (int) r4
            if (r5 <= 0) goto L_0x0049
            r4 = 59
            if (r9 < r4) goto L_0x0041
            int r6 = r6 + 1
            r4 = 0
            goto L_0x004a
        L_0x0041:
            if (r6 != 0) goto L_0x0046
            if (r9 != 0) goto L_0x0046
            goto L_0x0049
        L_0x0046:
            int r4 = r9 + 1
            goto L_0x004a
        L_0x0049:
            r4 = r9
        L_0x004a:
            if (r6 != 0) goto L_0x0063
            if (r4 != 0) goto L_0x0063
            if (r5 == 0) goto L_0x0063
            java.util.Locale r2 = java.util.Locale.ENGLISH
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r9 = 0
            r4[r9] = r5
            java.lang.String r2 = java.lang.String.format(r2, r3, r4)
            r0.statusCenterText = r2
            goto L_0x007c
        L_0x0063:
            r9 = 0
            java.util.Locale r3 = java.util.Locale.ENGLISH
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5[r9] = r6
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r6 = 1
            r5[r6] = r4
            java.lang.String r2 = java.lang.String.format(r3, r2, r5)
            r0.statusCenterText = r2
        L_0x007c:
            boolean r1 = r10.equals(r1)
            if (r1 == 0) goto L_0x0083
            goto L_0x0084
        L_0x0083:
            r7 = r8
        L_0x0084:
            r0.statusCenterBottomText = r7
            goto L_0x014e
        L_0x0088:
            boolean r2 = android.text.TextUtils.isEmpty(r17)
            if (r2 != 0) goto L_0x014e
            long r2 = java.lang.Long.parseLong(r17)
            r4 = 60
            long r2 = r2 / r4
            float r2 = (float) r2
            float r3 = r0.tempTextAngle
            float r2 = r2 * r3
            r0.needRoatteStatusAngel = r2
            boolean r1 = r10.equals(r1)
            if (r1 == 0) goto L_0x00a3
            goto L_0x00a4
        L_0x00a3:
            r7 = r8
        L_0x00a4:
            r0.statusCenterBottomText = r7
            goto L_0x014e
        L_0x00a8:
            boolean r1 = android.text.TextUtils.isEmpty(r17)
            if (r1 != 0) goto L_0x014e
            java.lang.Integer r1 = java.lang.Integer.valueOf(r17)
            int r1 = r1.intValue()
            float r1 = (float) r1
            float r2 = r0.tempTextAngle
            float r1 = r1 * r2
            r0.needRoatteStatusAngel = r1
            goto L_0x014e
        L_0x00bf:
            r2 = r5
            boolean r1 = r10.equals(r1)
            boolean r4 = android.text.TextUtils.isEmpty(r17)
            if (r4 != 0) goto L_0x014e
            long r4 = java.lang.Long.parseLong(r17)
            if (r1 == 0) goto L_0x00d9
            float r6 = (float) r4
            float r10 = r0.tempTextAngle
            float r6 = r6 * r10
            float r6 = r6 / r9
            r0.needRoatteStatusAngel = r6
            goto L_0x00e4
        L_0x00d9:
            r6 = 1133903872(0x43960000, float:300.0)
            float r10 = (float) r4
            float r13 = r0.tempTextAngle
            float r10 = r10 * r13
            float r10 = r10 / r9
            float r6 = r6 - r10
            r0.needRoatteStatusAngel = r6
        L_0x00e4:
            long r9 = r4 / r11
            int r6 = (int) r9
            long r4 = r4 % r11
            int r9 = (int) r4
            int r9 = r9 / 60
            r10 = 60
            long r4 = r4 % r10
            int r5 = (int) r4
            if (r5 <= 0) goto L_0x0100
            r4 = 59
            if (r9 < r4) goto L_0x00f9
            int r6 = r6 + 1
            r9 = 0
            goto L_0x0100
        L_0x00f9:
            if (r6 != 0) goto L_0x00fe
            if (r9 != 0) goto L_0x00fe
            goto L_0x0100
        L_0x00fe:
            int r9 = r9 + 1
        L_0x0100:
            if (r6 != 0) goto L_0x0119
            if (r9 != 0) goto L_0x0119
            if (r5 == 0) goto L_0x0119
            java.util.Locale r2 = java.util.Locale.ENGLISH
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r10 = 0
            r4[r10] = r5
            java.lang.String r2 = java.lang.String.format(r2, r3, r4)
            r0.statusCenterText = r2
            goto L_0x0132
        L_0x0119:
            r10 = 0
            java.util.Locale r3 = java.util.Locale.ENGLISH
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)
            r4[r10] = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)
            r6 = 1
            r4[r6] = r5
            java.lang.String r2 = java.lang.String.format(r3, r2, r4)
            r0.statusCenterText = r2
        L_0x0132:
            if (r1 == 0) goto L_0x0135
            goto L_0x0136
        L_0x0135:
            r7 = r8
        L_0x0136:
            r0.statusCenterBottomText = r7
            goto L_0x014e
        L_0x0139:
            boolean r1 = android.text.TextUtils.isEmpty(r17)
            if (r1 != 0) goto L_0x014e
            java.lang.Integer r1 = java.lang.Integer.valueOf(r17)
            int r1 = r1.intValue()
            float r1 = (float) r1
            float r2 = r0.tempTextAngle
            float r1 = r1 * r2
            r0.needRoatteStatusAngel = r1
        L_0x014e:
            r16.postInvalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.control.view.GuQiangCycleDialView.setStatusLineAndCenterText(java.lang.String, java.lang.String):void");
    }

    public void setOnChangeListeners(OnChangeListeners onChangeListeners) {
        this.mOnChangeListeners = onChangeListeners;
    }

    private void initAnimator() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "scale", new float[]{0.5f, 0.8f, 1.0f});
        this.mProgressAnim = ofFloat;
        ofFloat.setDuration(300);
    }

    public void startAnimation() {
        this.mProgressAnim.start();
    }

    public int getAlphaa() {
        return this.mAlpha;
    }

    public void setScale(float f) {
        this.mScale = f;
        invalidate();
    }
}
