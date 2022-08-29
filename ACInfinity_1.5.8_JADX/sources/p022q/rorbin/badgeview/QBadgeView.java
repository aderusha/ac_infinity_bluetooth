package p022q.rorbin.badgeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;
import p022q.rorbin.badgeview.Badge;

/* renamed from: q.rorbin.badgeview.QBadgeView */
public class QBadgeView extends View implements Badge {
    protected ViewGroup mActivityRoot;
    protected BadgeAnimator mAnimator;
    protected float mBackgroundBorderWidth;
    protected Paint mBadgeBackgroundBorderPaint;
    protected Paint mBadgeBackgroundPaint;
    protected RectF mBadgeBackgroundRect;
    protected PointF mBadgeCenter;
    protected int mBadgeGravity;
    protected int mBadgeNumber;
    protected float mBadgePadding;
    protected String mBadgeText;
    protected Paint.FontMetrics mBadgeTextFontMetrics;
    protected TextPaint mBadgeTextPaint;
    protected RectF mBadgeTextRect;
    protected float mBadgeTextSize;
    protected Bitmap mBitmapClip;
    protected int mColorBackground;
    protected int mColorBackgroundBorder;
    protected int mColorBadgeText;
    protected PointF mControlPoint;
    protected float mDefalutRadius;
    protected PointF mDragCenter;
    protected boolean mDragOutOfRange;
    protected Path mDragPath;
    protected int mDragQuadrant;
    protected Badge.OnDragStateChangedListener mDragStateChangedListener;
    protected boolean mDraggable;
    protected boolean mDragging;
    protected Drawable mDrawableBackground;
    protected boolean mDrawableBackgroundClip;
    protected boolean mExact;
    protected float mFinalDragDistance;
    protected float mGravityOffsetX;
    protected float mGravityOffsetY;
    protected int mHeight;
    protected List<PointF> mInnertangentPoints;
    protected PointF mRowBadgeCenter;
    protected boolean mShowShadow;
    protected View mTargetView;
    protected int mWidth;

    public QBadgeView(Context context) {
        this(context, (AttributeSet) null);
    }

    private QBadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private QBadgeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setLayerType(1, (Paint) null);
        this.mBadgeTextRect = new RectF();
        this.mBadgeBackgroundRect = new RectF();
        this.mDragPath = new Path();
        this.mBadgeCenter = new PointF();
        this.mDragCenter = new PointF();
        this.mRowBadgeCenter = new PointF();
        this.mControlPoint = new PointF();
        this.mInnertangentPoints = new ArrayList();
        TextPaint textPaint = new TextPaint();
        this.mBadgeTextPaint = textPaint;
        textPaint.setAntiAlias(true);
        this.mBadgeTextPaint.setSubpixelText(true);
        this.mBadgeTextPaint.setFakeBoldText(true);
        this.mBadgeTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Paint paint = new Paint();
        this.mBadgeBackgroundPaint = paint;
        paint.setAntiAlias(true);
        this.mBadgeBackgroundPaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mBadgeBackgroundBorderPaint = paint2;
        paint2.setAntiAlias(true);
        this.mBadgeBackgroundBorderPaint.setStyle(Paint.Style.STROKE);
        this.mColorBackground = -1552832;
        this.mColorBadgeText = -1;
        this.mBadgeTextSize = (float) DisplayUtil.dp2px(getContext(), 11.0f);
        this.mBadgePadding = (float) DisplayUtil.dp2px(getContext(), 5.0f);
        this.mBadgeNumber = 0;
        this.mBadgeGravity = 8388661;
        this.mGravityOffsetX = (float) DisplayUtil.dp2px(getContext(), 1.0f);
        this.mGravityOffsetY = (float) DisplayUtil.dp2px(getContext(), 1.0f);
        this.mFinalDragDistance = (float) DisplayUtil.dp2px(getContext(), 90.0f);
        this.mShowShadow = true;
        this.mDrawableBackgroundClip = false;
        if (Build.VERSION.SDK_INT >= 21) {
            setTranslationZ(1000.0f);
        }
    }

    public Badge bindTarget(View view) {
        if (view != null) {
            if (getParent() != null) {
                ((ViewGroup) getParent()).removeView(this);
            }
            ViewParent parent = view.getParent();
            if (parent == null || !(parent instanceof ViewGroup)) {
                throw new IllegalStateException("targetView must have a parent");
            }
            this.mTargetView = view;
            if (parent instanceof BadgeContainer) {
                ((BadgeContainer) parent).addView(this);
            } else {
                ViewGroup viewGroup = (ViewGroup) parent;
                int indexOfChild = viewGroup.indexOfChild(view);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                viewGroup.removeView(view);
                BadgeContainer badgeContainer = new BadgeContainer(getContext());
                if (viewGroup instanceof RelativeLayout) {
                    badgeContainer.setId(view.getId());
                }
                viewGroup.addView(badgeContainer, indexOfChild, layoutParams);
                badgeContainer.addView(view);
                badgeContainer.addView(this);
            }
            return this;
        }
        throw new IllegalStateException("targetView can not be null");
    }

    public View getTargetView() {
        return this.mTargetView;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mActivityRoot == null) {
            findViewRoot(this.mTargetView);
        }
    }

    private void findViewRoot(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getRootView();
        this.mActivityRoot = viewGroup;
        if (viewGroup == null) {
            findActivityRoot(view);
        }
    }

    private void findActivityRoot(View view) {
        if (view.getParent() != null && (view.getParent() instanceof View)) {
            findActivityRoot((View) view.getParent());
        } else if (view instanceof ViewGroup) {
            this.mActivityRoot = (ViewGroup) view;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r0 != 6) goto L_0x00ae;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b8 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0045
            if (r0 == r2) goto L_0x0031
            r3 = 2
            if (r0 == r3) goto L_0x0018
            r3 = 3
            if (r0 == r3) goto L_0x0031
            r3 = 5
            if (r0 == r3) goto L_0x0045
            r3 = 6
            if (r0 == r3) goto L_0x0031
            goto L_0x00ae
        L_0x0018:
            boolean r0 = r5.mDragging
            if (r0 == 0) goto L_0x00ae
            android.graphics.PointF r0 = r5.mDragCenter
            float r3 = r6.getRawX()
            r0.x = r3
            android.graphics.PointF r0 = r5.mDragCenter
            float r3 = r6.getRawY()
            r0.y = r3
            r5.invalidate()
            goto L_0x00ae
        L_0x0031:
            int r0 = r6.getActionIndex()
            int r0 = r6.getPointerId(r0)
            if (r0 != 0) goto L_0x00ae
            boolean r0 = r5.mDragging
            if (r0 == 0) goto L_0x00ae
            r5.mDragging = r1
            r5.onPointerUp()
            goto L_0x00ae
        L_0x0045:
            float r0 = r6.getX()
            float r3 = r6.getY()
            boolean r4 = r5.mDraggable
            if (r4 == 0) goto L_0x00ae
            int r4 = r6.getActionIndex()
            int r4 = r6.getPointerId(r4)
            if (r4 != 0) goto L_0x00ae
            android.graphics.RectF r4 = r5.mBadgeBackgroundRect
            float r4 = r4.left
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x00ae
            android.graphics.RectF r4 = r5.mBadgeBackgroundRect
            float r4 = r4.right
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ae
            android.graphics.RectF r0 = r5.mBadgeBackgroundRect
            float r0 = r0.top
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x00ae
            android.graphics.RectF r0 = r5.mBadgeBackgroundRect
            float r0 = r0.bottom
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ae
            java.lang.String r0 = r5.mBadgeText
            if (r0 == 0) goto L_0x00ae
            r5.initRowBadgeCenter()
            r5.mDragging = r2
            r5.updataListener(r2)
            android.content.Context r0 = r5.getContext()
            r3 = 1088421888(0x40e00000, float:7.0)
            int r0 = p022q.rorbin.badgeview.DisplayUtil.dp2px(r0, r3)
            float r0 = (float) r0
            r5.mDefalutRadius = r0
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r2)
            r5.screenFromWindow(r2)
            android.graphics.PointF r0 = r5.mDragCenter
            float r3 = r6.getRawX()
            r0.x = r3
            android.graphics.PointF r0 = r5.mDragCenter
            float r3 = r6.getRawY()
            r0.y = r3
        L_0x00ae:
            boolean r0 = r5.mDragging
            if (r0 != 0) goto L_0x00b8
            boolean r6 = super.onTouchEvent(r6)
            if (r6 == 0) goto L_0x00b9
        L_0x00b8:
            r1 = 1
        L_0x00b9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p022q.rorbin.badgeview.QBadgeView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void onPointerUp() {
        if (this.mDragOutOfRange) {
            animateHide(this.mDragCenter);
            updataListener(5);
            return;
        }
        reset();
        updataListener(4);
    }

    /* access modifiers changed from: protected */
    public Bitmap createBadgeBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(((int) this.mBadgeBackgroundRect.width()) + DisplayUtil.dp2px(getContext(), 3.0f), ((int) this.mBadgeBackgroundRect.height()) + DisplayUtil.dp2px(getContext(), 3.0f), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawBadge(canvas, new PointF(((float) canvas.getWidth()) / 2.0f, ((float) canvas.getHeight()) / 2.0f), getBadgeCircleRadius());
        return createBitmap;
    }

    /* access modifiers changed from: protected */
    public void screenFromWindow(boolean z) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (z) {
            this.mActivityRoot.addView(this, new FrameLayout.LayoutParams(-1, -1));
        } else {
            bindTarget(this.mTargetView);
        }
    }

    private void showShadowImpl(boolean z) {
        int dp2px = DisplayUtil.dp2px(getContext(), 1.0f);
        int dp2px2 = DisplayUtil.dp2px(getContext(), 1.5f);
        int i = this.mDragQuadrant;
        if (i == 1) {
            dp2px = DisplayUtil.dp2px(getContext(), 1.0f);
            dp2px2 = DisplayUtil.dp2px(getContext(), -1.5f);
        } else if (i == 2) {
            dp2px = DisplayUtil.dp2px(getContext(), -1.0f);
            dp2px2 = DisplayUtil.dp2px(getContext(), -1.5f);
        } else if (i == 3) {
            dp2px = DisplayUtil.dp2px(getContext(), -1.0f);
            dp2px2 = DisplayUtil.dp2px(getContext(), 1.5f);
        } else if (i == 4) {
            dp2px = DisplayUtil.dp2px(getContext(), 1.0f);
            dp2px2 = DisplayUtil.dp2px(getContext(), 1.5f);
        }
        this.mBadgeBackgroundPaint.setShadowLayer(z ? (float) DisplayUtil.dp2px(getContext(), 2.0f) : 0.0f, (float) dp2px, (float) dp2px2, 855638016);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        BadgeAnimator badgeAnimator = this.mAnimator;
        if (badgeAnimator != null && badgeAnimator.isRunning()) {
            this.mAnimator.draw(canvas);
        } else if (this.mBadgeText != null) {
            initPaints();
            float badgeCircleRadius = getBadgeCircleRadius();
            float pointDistance = this.mDefalutRadius * (1.0f - (MathUtil.getPointDistance(this.mRowBadgeCenter, this.mDragCenter) / this.mFinalDragDistance));
            if (!this.mDraggable || !this.mDragging) {
                findBadgeCenter();
                drawBadge(canvas, this.mBadgeCenter, badgeCircleRadius);
                return;
            }
            this.mDragQuadrant = MathUtil.getQuadrant(this.mDragCenter, this.mRowBadgeCenter);
            showShadowImpl(this.mShowShadow);
            boolean z = pointDistance < ((float) DisplayUtil.dp2px(getContext(), 1.5f));
            this.mDragOutOfRange = z;
            if (z) {
                updataListener(3);
                drawBadge(canvas, this.mDragCenter, badgeCircleRadius);
                return;
            }
            updataListener(2);
            drawDragging(canvas, pointDistance, badgeCircleRadius);
            drawBadge(canvas, this.mDragCenter, badgeCircleRadius);
        }
    }

    private void initPaints() {
        showShadowImpl(this.mShowShadow);
        this.mBadgeBackgroundPaint.setColor(this.mColorBackground);
        this.mBadgeBackgroundBorderPaint.setColor(this.mColorBackgroundBorder);
        this.mBadgeBackgroundBorderPaint.setStrokeWidth(this.mBackgroundBorderWidth);
        this.mBadgeTextPaint.setColor(this.mColorBadgeText);
        this.mBadgeTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void drawDragging(Canvas canvas, float f, float f2) {
        float f3;
        float f4;
        float f5;
        float f6 = this.mDragCenter.y - this.mRowBadgeCenter.y;
        float f7 = this.mDragCenter.x - this.mRowBadgeCenter.x;
        this.mInnertangentPoints.clear();
        if (f7 != 0.0f) {
            double d = -1.0d / ((double) (f6 / f7));
            MathUtil.getInnertangentPoints(this.mDragCenter, f2, Double.valueOf(d), this.mInnertangentPoints);
            MathUtil.getInnertangentPoints(this.mRowBadgeCenter, f, Double.valueOf(d), this.mInnertangentPoints);
        } else {
            MathUtil.getInnertangentPoints(this.mDragCenter, f2, Double.valueOf(0.0d), this.mInnertangentPoints);
            MathUtil.getInnertangentPoints(this.mRowBadgeCenter, f, Double.valueOf(0.0d), this.mInnertangentPoints);
        }
        this.mDragPath.reset();
        Path path = this.mDragPath;
        float f8 = this.mRowBadgeCenter.x;
        float f9 = this.mRowBadgeCenter.y;
        int i = this.mDragQuadrant;
        path.addCircle(f8, f9, f, (i == 1 || i == 2) ? Path.Direction.CCW : Path.Direction.CW);
        this.mControlPoint.x = (this.mRowBadgeCenter.x + this.mDragCenter.x) / 2.0f;
        this.mControlPoint.y = (this.mRowBadgeCenter.y + this.mDragCenter.y) / 2.0f;
        this.mDragPath.moveTo(this.mInnertangentPoints.get(2).x, this.mInnertangentPoints.get(2).y);
        this.mDragPath.quadTo(this.mControlPoint.x, this.mControlPoint.y, this.mInnertangentPoints.get(0).x, this.mInnertangentPoints.get(0).y);
        this.mDragPath.lineTo(this.mInnertangentPoints.get(1).x, this.mInnertangentPoints.get(1).y);
        this.mDragPath.quadTo(this.mControlPoint.x, this.mControlPoint.y, this.mInnertangentPoints.get(3).x, this.mInnertangentPoints.get(3).y);
        this.mDragPath.lineTo(this.mInnertangentPoints.get(2).x, this.mInnertangentPoints.get(2).y);
        this.mDragPath.close();
        canvas.drawPath(this.mDragPath, this.mBadgeBackgroundPaint);
        if (this.mColorBackgroundBorder != 0 && this.mBackgroundBorderWidth > 0.0f) {
            this.mDragPath.reset();
            this.mDragPath.moveTo(this.mInnertangentPoints.get(2).x, this.mInnertangentPoints.get(2).y);
            this.mDragPath.quadTo(this.mControlPoint.x, this.mControlPoint.y, this.mInnertangentPoints.get(0).x, this.mInnertangentPoints.get(0).y);
            this.mDragPath.moveTo(this.mInnertangentPoints.get(1).x, this.mInnertangentPoints.get(1).y);
            this.mDragPath.quadTo(this.mControlPoint.x, this.mControlPoint.y, this.mInnertangentPoints.get(3).x, this.mInnertangentPoints.get(3).y);
            int i2 = this.mDragQuadrant;
            if (i2 == 1 || i2 == 2) {
                f3 = this.mInnertangentPoints.get(2).x - this.mRowBadgeCenter.x;
                f5 = this.mRowBadgeCenter.y;
                f4 = this.mInnertangentPoints.get(2).y;
            } else {
                f3 = this.mInnertangentPoints.get(3).x - this.mRowBadgeCenter.x;
                f5 = this.mRowBadgeCenter.y;
                f4 = this.mInnertangentPoints.get(3).y;
            }
            double atan = Math.atan((double) ((f5 - f4) / f3));
            int i3 = this.mDragQuadrant;
            float radianToAngle = 360.0f - ((float) MathUtil.radianToAngle(MathUtil.getTanRadian(atan, i3 + -1 == 0 ? 4 : i3 - 1)));
            if (Build.VERSION.SDK_INT >= 21) {
                this.mDragPath.addArc(this.mRowBadgeCenter.x - f, this.mRowBadgeCenter.y - f, this.mRowBadgeCenter.x + f, this.mRowBadgeCenter.y + f, radianToAngle, 180.0f);
            } else {
                this.mDragPath.addArc(new RectF(this.mRowBadgeCenter.x - f, this.mRowBadgeCenter.y - f, this.mRowBadgeCenter.x + f, this.mRowBadgeCenter.y + f), radianToAngle, 180.0f);
            }
            canvas.drawPath(this.mDragPath, this.mBadgeBackgroundBorderPaint);
        }
    }

    private void drawBadge(Canvas canvas, PointF pointF, float f) {
        if (pointF.x != -1000.0f || pointF.y != -1000.0f) {
            if (this.mBadgeText.isEmpty() || this.mBadgeText.length() == 1) {
                float f2 = (float) ((int) f);
                this.mBadgeBackgroundRect.left = pointF.x - f2;
                this.mBadgeBackgroundRect.top = pointF.y - f2;
                this.mBadgeBackgroundRect.right = pointF.x + f2;
                this.mBadgeBackgroundRect.bottom = pointF.y + f2;
                if (this.mDrawableBackground != null) {
                    drawBadgeBackground(canvas);
                } else {
                    canvas.drawCircle(pointF.x, pointF.y, f, this.mBadgeBackgroundPaint);
                    if (this.mColorBackgroundBorder != 0 && this.mBackgroundBorderWidth > 0.0f) {
                        canvas.drawCircle(pointF.x, pointF.y, f, this.mBadgeBackgroundBorderPaint);
                    }
                }
            } else {
                this.mBadgeBackgroundRect.left = pointF.x - ((this.mBadgeTextRect.width() / 2.0f) + this.mBadgePadding);
                this.mBadgeBackgroundRect.top = pointF.y - ((this.mBadgeTextRect.height() / 2.0f) + (this.mBadgePadding * 0.5f));
                this.mBadgeBackgroundRect.right = pointF.x + (this.mBadgeTextRect.width() / 2.0f) + this.mBadgePadding;
                this.mBadgeBackgroundRect.bottom = pointF.y + (this.mBadgeTextRect.height() / 2.0f) + (this.mBadgePadding * 0.5f);
                float height = this.mBadgeBackgroundRect.height() / 2.0f;
                if (this.mDrawableBackground != null) {
                    drawBadgeBackground(canvas);
                } else {
                    canvas.drawRoundRect(this.mBadgeBackgroundRect, height, height, this.mBadgeBackgroundPaint);
                    if (this.mColorBackgroundBorder != 0 && this.mBackgroundBorderWidth > 0.0f) {
                        canvas.drawRoundRect(this.mBadgeBackgroundRect, height, height, this.mBadgeBackgroundBorderPaint);
                    }
                }
            }
            if (!this.mBadgeText.isEmpty()) {
                canvas.drawText(this.mBadgeText, pointF.x, (((this.mBadgeBackgroundRect.bottom + this.mBadgeBackgroundRect.top) - this.mBadgeTextFontMetrics.bottom) - this.mBadgeTextFontMetrics.top) / 2.0f, this.mBadgeTextPaint);
            }
        }
    }

    private void drawBadgeBackground(Canvas canvas) {
        this.mBadgeBackgroundPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        int i = (int) this.mBadgeBackgroundRect.left;
        int i2 = (int) this.mBadgeBackgroundRect.top;
        int i3 = (int) this.mBadgeBackgroundRect.right;
        int i4 = (int) this.mBadgeBackgroundRect.bottom;
        if (this.mDrawableBackgroundClip) {
            i3 = this.mBitmapClip.getWidth() + i;
            i4 = this.mBitmapClip.getHeight() + i2;
            canvas.saveLayer((float) i, (float) i2, (float) i3, (float) i4, (Paint) null, 31);
        }
        this.mDrawableBackground.setBounds(i, i2, i3, i4);
        this.mDrawableBackground.draw(canvas);
        if (this.mDrawableBackgroundClip) {
            this.mBadgeBackgroundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawBitmap(this.mBitmapClip, (float) i, (float) i2, this.mBadgeBackgroundPaint);
            canvas.restore();
            this.mBadgeBackgroundPaint.setXfermode((Xfermode) null);
            if (this.mBadgeText.isEmpty() || this.mBadgeText.length() == 1) {
                canvas.drawCircle(this.mBadgeBackgroundRect.centerX(), this.mBadgeBackgroundRect.centerY(), this.mBadgeBackgroundRect.width() / 2.0f, this.mBadgeBackgroundBorderPaint);
                return;
            }
            RectF rectF = this.mBadgeBackgroundRect;
            canvas.drawRoundRect(rectF, rectF.height() / 2.0f, this.mBadgeBackgroundRect.height() / 2.0f, this.mBadgeBackgroundBorderPaint);
            return;
        }
        canvas.drawRect(this.mBadgeBackgroundRect, this.mBadgeBackgroundBorderPaint);
    }

    private void createClipLayer() {
        if (this.mBadgeText != null && this.mDrawableBackgroundClip) {
            Bitmap bitmap = this.mBitmapClip;
            if (bitmap != null && !bitmap.isRecycled()) {
                this.mBitmapClip.recycle();
            }
            float badgeCircleRadius = getBadgeCircleRadius();
            if (this.mBadgeText.isEmpty() || this.mBadgeText.length() == 1) {
                int i = ((int) badgeCircleRadius) * 2;
                this.mBitmapClip = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_4444);
                Canvas canvas = new Canvas(this.mBitmapClip);
                canvas.drawCircle(((float) canvas.getWidth()) / 2.0f, ((float) canvas.getHeight()) / 2.0f, ((float) canvas.getWidth()) / 2.0f, this.mBadgeBackgroundPaint);
                return;
            }
            this.mBitmapClip = Bitmap.createBitmap((int) (this.mBadgeTextRect.width() + (this.mBadgePadding * 2.0f)), (int) (this.mBadgeTextRect.height() + this.mBadgePadding), Bitmap.Config.ARGB_4444);
            Canvas canvas2 = new Canvas(this.mBitmapClip);
            if (Build.VERSION.SDK_INT >= 21) {
                canvas2.drawRoundRect(0.0f, 0.0f, (float) canvas2.getWidth(), (float) canvas2.getHeight(), ((float) canvas2.getHeight()) / 2.0f, ((float) canvas2.getHeight()) / 2.0f, this.mBadgeBackgroundPaint);
            } else {
                canvas2.drawRoundRect(new RectF(0.0f, 0.0f, (float) canvas2.getWidth(), (float) canvas2.getHeight()), ((float) canvas2.getHeight()) / 2.0f, ((float) canvas2.getHeight()) / 2.0f, this.mBadgeBackgroundPaint);
            }
        }
    }

    private float getBadgeCircleRadius() {
        float f;
        float f2;
        if (this.mBadgeText.isEmpty()) {
            return this.mBadgePadding;
        }
        if (this.mBadgeText.length() != 1) {
            return this.mBadgeBackgroundRect.height() / 2.0f;
        }
        if (this.mBadgeTextRect.height() > this.mBadgeTextRect.width()) {
            f2 = this.mBadgeTextRect.height() / 2.0f;
            f = this.mBadgePadding;
        } else {
            f2 = this.mBadgeTextRect.width() / 2.0f;
            f = this.mBadgePadding;
        }
        return f2 + (f * 0.5f);
    }

    private void findBadgeCenter() {
        float height = this.mBadgeTextRect.height() > this.mBadgeTextRect.width() ? this.mBadgeTextRect.height() : this.mBadgeTextRect.width();
        switch (this.mBadgeGravity) {
            case 17:
                this.mBadgeCenter.x = ((float) this.mWidth) / 2.0f;
                this.mBadgeCenter.y = ((float) this.mHeight) / 2.0f;
                break;
            case 49:
                this.mBadgeCenter.x = ((float) this.mWidth) / 2.0f;
                this.mBadgeCenter.y = this.mGravityOffsetY + this.mBadgePadding + (this.mBadgeTextRect.height() / 2.0f);
                break;
            case 81:
                this.mBadgeCenter.x = ((float) this.mWidth) / 2.0f;
                this.mBadgeCenter.y = ((float) this.mHeight) - ((this.mGravityOffsetY + this.mBadgePadding) + (this.mBadgeTextRect.height() / 2.0f));
                break;
            case 8388627:
                this.mBadgeCenter.x = this.mGravityOffsetX + this.mBadgePadding + (height / 2.0f);
                this.mBadgeCenter.y = ((float) this.mHeight) / 2.0f;
                break;
            case 8388629:
                this.mBadgeCenter.x = ((float) this.mWidth) - ((this.mGravityOffsetX + this.mBadgePadding) + (height / 2.0f));
                this.mBadgeCenter.y = ((float) this.mHeight) / 2.0f;
                break;
            case 8388659:
                this.mBadgeCenter.x = this.mGravityOffsetX + this.mBadgePadding + (height / 2.0f);
                this.mBadgeCenter.y = this.mGravityOffsetY + this.mBadgePadding + (this.mBadgeTextRect.height() / 2.0f);
                break;
            case 8388661:
                this.mBadgeCenter.x = ((float) this.mWidth) - ((this.mGravityOffsetX + this.mBadgePadding) + (height / 2.0f));
                this.mBadgeCenter.y = this.mGravityOffsetY + this.mBadgePadding + (this.mBadgeTextRect.height() / 2.0f);
                break;
            case 8388691:
                this.mBadgeCenter.x = this.mGravityOffsetX + this.mBadgePadding + (height / 2.0f);
                this.mBadgeCenter.y = ((float) this.mHeight) - ((this.mGravityOffsetY + this.mBadgePadding) + (this.mBadgeTextRect.height() / 2.0f));
                break;
            case 8388693:
                this.mBadgeCenter.x = ((float) this.mWidth) - ((this.mGravityOffsetX + this.mBadgePadding) + (height / 2.0f));
                this.mBadgeCenter.y = ((float) this.mHeight) - ((this.mGravityOffsetY + this.mBadgePadding) + (this.mBadgeTextRect.height() / 2.0f));
                break;
        }
        initRowBadgeCenter();
    }

    private void measureText() {
        this.mBadgeTextRect.left = 0.0f;
        this.mBadgeTextRect.top = 0.0f;
        if (TextUtils.isEmpty(this.mBadgeText)) {
            this.mBadgeTextRect.right = 0.0f;
            this.mBadgeTextRect.bottom = 0.0f;
        } else {
            this.mBadgeTextPaint.setTextSize(this.mBadgeTextSize);
            this.mBadgeTextRect.right = this.mBadgeTextPaint.measureText(this.mBadgeText);
            Paint.FontMetrics fontMetrics = this.mBadgeTextPaint.getFontMetrics();
            this.mBadgeTextFontMetrics = fontMetrics;
            this.mBadgeTextRect.bottom = fontMetrics.descent - this.mBadgeTextFontMetrics.ascent;
        }
        createClipLayer();
    }

    private void initRowBadgeCenter() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        this.mRowBadgeCenter.x = this.mBadgeCenter.x + ((float) iArr[0]);
        this.mRowBadgeCenter.y = this.mBadgeCenter.y + ((float) iArr[1]);
    }

    /* access modifiers changed from: protected */
    public void animateHide(PointF pointF) {
        if (this.mBadgeText != null) {
            BadgeAnimator badgeAnimator = this.mAnimator;
            if (badgeAnimator == null || !badgeAnimator.isRunning()) {
                screenFromWindow(true);
                BadgeAnimator badgeAnimator2 = new BadgeAnimator(createBadgeBitmap(), pointF, this);
                this.mAnimator = badgeAnimator2;
                badgeAnimator2.start();
                setBadgeNumber(0);
            }
        }
    }

    public void reset() {
        this.mDragCenter.x = -1000.0f;
        this.mDragCenter.y = -1000.0f;
        this.mDragQuadrant = 4;
        screenFromWindow(false);
        getParent().requestDisallowInterceptTouchEvent(false);
        invalidate();
    }

    public void hide(boolean z) {
        if (!z || this.mActivityRoot == null) {
            setBadgeNumber(0);
            return;
        }
        initRowBadgeCenter();
        animateHide(this.mRowBadgeCenter);
    }

    public Badge setBadgeNumber(int i) {
        this.mBadgeNumber = i;
        if (i < 0) {
            this.mBadgeText = "";
        } else if (i > 99) {
            this.mBadgeText = this.mExact ? String.valueOf(i) : "99+";
        } else if (i > 0 && i <= 99) {
            this.mBadgeText = String.valueOf(i);
        } else if (i == 0) {
            this.mBadgeText = null;
        }
        measureText();
        invalidate();
        return this;
    }

    public int getBadgeNumber() {
        return this.mBadgeNumber;
    }

    public Badge setBadgeText(String str) {
        this.mBadgeText = str;
        this.mBadgeNumber = 1;
        measureText();
        invalidate();
        return this;
    }

    public String getBadgeText() {
        return this.mBadgeText;
    }

    public Badge setExactMode(boolean z) {
        this.mExact = z;
        int i = this.mBadgeNumber;
        if (i > 99) {
            setBadgeNumber(i);
        }
        return this;
    }

    public boolean isExactMode() {
        return this.mExact;
    }

    public Badge setShowShadow(boolean z) {
        this.mShowShadow = z;
        invalidate();
        return this;
    }

    public boolean isShowShadow() {
        return this.mShowShadow;
    }

    public Badge setBadgeBackgroundColor(int i) {
        this.mColorBackground = i;
        if (i == 0) {
            this.mBadgeTextPaint.setXfermode((Xfermode) null);
        } else {
            this.mBadgeTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }
        invalidate();
        return this;
    }

    public Badge stroke(int i, float f, boolean z) {
        this.mColorBackgroundBorder = i;
        if (z) {
            f = (float) DisplayUtil.dp2px(getContext(), f);
        }
        this.mBackgroundBorderWidth = f;
        invalidate();
        return this;
    }

    public int getBadgeBackgroundColor() {
        return this.mColorBackground;
    }

    public Badge setBadgeBackground(Drawable drawable) {
        return setBadgeBackground(drawable, false);
    }

    public Badge setBadgeBackground(Drawable drawable, boolean z) {
        this.mDrawableBackgroundClip = z;
        this.mDrawableBackground = drawable;
        createClipLayer();
        invalidate();
        return this;
    }

    public Drawable getBadgeBackground() {
        return this.mDrawableBackground;
    }

    public Badge setBadgeTextColor(int i) {
        this.mColorBadgeText = i;
        invalidate();
        return this;
    }

    public int getBadgeTextColor() {
        return this.mColorBadgeText;
    }

    public Badge setBadgeTextSize(float f, boolean z) {
        if (z) {
            f = (float) DisplayUtil.dp2px(getContext(), f);
        }
        this.mBadgeTextSize = f;
        measureText();
        invalidate();
        return this;
    }

    public float getBadgeTextSize(boolean z) {
        return z ? (float) DisplayUtil.px2dp(getContext(), this.mBadgeTextSize) : this.mBadgeTextSize;
    }

    public Badge setBadgePadding(float f, boolean z) {
        if (z) {
            f = (float) DisplayUtil.dp2px(getContext(), f);
        }
        this.mBadgePadding = f;
        createClipLayer();
        invalidate();
        return this;
    }

    public float getBadgePadding(boolean z) {
        return z ? (float) DisplayUtil.px2dp(getContext(), this.mBadgePadding) : this.mBadgePadding;
    }

    public boolean isDraggable() {
        return this.mDraggable;
    }

    public Badge setBadgeGravity(int i) {
        if (i == 8388659 || i == 8388661 || i == 8388691 || i == 8388693 || i == 17 || i == 49 || i == 81 || i == 8388627 || i == 8388629) {
            this.mBadgeGravity = i;
            invalidate();
            return this;
        }
        throw new IllegalStateException("only support Gravity.START | Gravity.TOP , Gravity.END | Gravity.TOP , Gravity.START | Gravity.BOTTOM , Gravity.END | Gravity.BOTTOM , Gravity.CENTER , Gravity.CENTER | Gravity.TOP , Gravity.CENTER | Gravity.BOTTOM ,Gravity.CENTER | Gravity.START , Gravity.CENTER | Gravity.END");
    }

    public int getBadgeGravity() {
        return this.mBadgeGravity;
    }

    public Badge setGravityOffset(float f, boolean z) {
        return setGravityOffset(f, f, z);
    }

    public Badge setGravityOffset(float f, float f2, boolean z) {
        if (z) {
            f = (float) DisplayUtil.dp2px(getContext(), f);
        }
        this.mGravityOffsetX = f;
        if (z) {
            f2 = (float) DisplayUtil.dp2px(getContext(), f2);
        }
        this.mGravityOffsetY = f2;
        invalidate();
        return this;
    }

    public float getGravityOffsetX(boolean z) {
        return z ? (float) DisplayUtil.px2dp(getContext(), this.mGravityOffsetX) : this.mGravityOffsetX;
    }

    public float getGravityOffsetY(boolean z) {
        return z ? (float) DisplayUtil.px2dp(getContext(), this.mGravityOffsetY) : this.mGravityOffsetY;
    }

    private void updataListener(int i) {
        Badge.OnDragStateChangedListener onDragStateChangedListener = this.mDragStateChangedListener;
        if (onDragStateChangedListener != null) {
            onDragStateChangedListener.onDragStateChanged(i, this, this.mTargetView);
        }
    }

    public Badge setOnDragStateChangedListener(Badge.OnDragStateChangedListener onDragStateChangedListener) {
        this.mDraggable = onDragStateChangedListener != null;
        this.mDragStateChangedListener = onDragStateChangedListener;
        return this;
    }

    public PointF getDragCenter() {
        if (!this.mDraggable || !this.mDragging) {
            return null;
        }
        return this.mDragCenter;
    }

    /* renamed from: q.rorbin.badgeview.QBadgeView$BadgeContainer */
    private class BadgeContainer extends ViewGroup {
        /* access modifiers changed from: protected */
        public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
            if (!(getParent() instanceof RelativeLayout)) {
                super.dispatchRestoreInstanceState(sparseArray);
            }
        }

        public BadgeContainer(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
            }
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            View view = null;
            View view2 = null;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (!(childAt instanceof QBadgeView)) {
                    view = childAt;
                } else {
                    view2 = childAt;
                }
            }
            if (view == null) {
                super.onMeasure(i, i2);
                return;
            }
            view.measure(i, i2);
            if (view2 != null) {
                view2.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
            }
            setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }
}
