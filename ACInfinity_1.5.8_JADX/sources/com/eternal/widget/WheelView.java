package com.eternal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.internal.view.SupportMenu;
import java.util.ArrayList;
import java.util.Iterator;

public class WheelView extends View {
    private static final int CLICK_DISTANCE = 2;
    public static final int DEFAULT_MASK_DARK_COLOR = -654311425;
    public static final int DEFAULT_MASK_LIGHT_COLOR = -1056964609;
    private static final int GO_ON_MOVE_END = 10011;
    private static final int GO_ON_MOVE_INTERRUPTED = 10012;
    private static final int GO_ON_MOVE_REFRESH = 10010;
    private static final int GO_ON_REFRESH_INTERVAL_MILLIS = 10;
    private static final int MOVE_NUMBER = 1;
    private static final int REFRESH_VIEW = 1;
    private static final int SHOWTIME = 200;
    private static final int SLOW_MOVE_SPEED = 1;
    private static final String TAG = "WheelView";
    private boolean _isCyclic = true;
    private Handler callbackHandler;
    private int clickDistance = 2;
    private int clickTimeout = 100;
    /* access modifiers changed from: private */
    public float controlHeight;
    /* access modifiers changed from: private */
    public float controlWidth;
    private ArrayList<String> dataList = new ArrayList<>();
    private int defaultIndex;
    private float density = 1.0f;
    private long downTime = 0;
    private int downY;
    /* access modifiers changed from: private */
    public int goOnDistance;
    int goOnLimit;
    /* access modifiers changed from: private */
    public int goOnMove;
    Interpolator goonInterpolator = new DecelerateInterpolator(2.0f);
    private boolean isClearing = false;
    private boolean isCyclic = true;
    private boolean isEnable = true;
    /* access modifiers changed from: private */
    public boolean isGoOnMove = false;
    /* access modifiers changed from: private */
    public boolean isScrolling = false;
    /* access modifiers changed from: private */
    public ArrayList<ItemObject> itemList = new ArrayList<>();
    /* access modifiers changed from: private */
    public int itemNumber = 7;
    private float lastMeasuredHeight;
    private int lastY;
    private int lineColor = -16777216;
    /* access modifiers changed from: private */
    public float lineHeight = 2.0f;
    private Paint linePaint;
    private LinearGradient linearGradientDown;
    private LinearGradient linearGradientUp;
    private int mMaximumFlingVelocity;
    private int mMinimumFlingVelocity;
    private VelocityTracker mVelocityTracker;
    private int maskDarkColor = DEFAULT_MASK_DARK_COLOR;
    private int maskLightColor = DEFAULT_MASK_LIGHT_COLOR;
    private Paint mastPaint;
    private int moveDistance;
    /* access modifiers changed from: private */
    public Handler moveHandler;
    private HandlerThread moveHandlerThread;
    private boolean noEmpty = true;
    /* access modifiers changed from: private */
    public int normalColor = -16777216;
    /* access modifiers changed from: private */
    public float normalFont = 14.0f;
    /* access modifiers changed from: private */
    public OnSelectListener onSelectListener;
    /* access modifiers changed from: private */
    public int selectedColor = SupportMenu.CATEGORY_MASK;
    /* access modifiers changed from: private */
    public float selectedFont = 22.0f;
    /* access modifiers changed from: private */
    public int showTime = 0;
    /* access modifiers changed from: private */
    public int slowMoveSpeed = 1;
    /* access modifiers changed from: private */
    public ItemObject[] toShowItems;
    /* access modifiers changed from: private */
    public float unitHeight = 50.0f;

    public interface OnSelectListener {
        void endSelect(int i, String str);

        void selecting(int i, String str);
    }

    static /* synthetic */ int access$208(WheelView wheelView) {
        int i = wheelView.showTime;
        wheelView.showTime = i + 1;
        return i;
    }

    static /* synthetic */ int access$912(WheelView wheelView, int i) {
        int i2 = wheelView.moveDistance + i;
        wheelView.moveDistance = i2;
        return i2;
    }

    static /* synthetic */ int access$920(WheelView wheelView, int i) {
        int i2 = wheelView.moveDistance - i;
        wheelView.moveDistance = i2;
        return i2;
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
        initData();
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
        initData();
    }

    public WheelView(Context context) {
        super(context);
        initData();
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.WheelView);
        this.unitHeight = (float) ((int) obtainStyledAttributes.getDimension(C2779R.styleable.WheelView_unitHeight, this.unitHeight));
        this.itemNumber = obtainStyledAttributes.getInt(C2779R.styleable.WheelView_itemNumber, this.itemNumber);
        this.normalFont = obtainStyledAttributes.getDimension(C2779R.styleable.WheelView_normalTextSize, this.normalFont);
        this.selectedFont = obtainStyledAttributes.getDimension(C2779R.styleable.WheelView_selectedTextSize, this.selectedFont);
        this.normalColor = obtainStyledAttributes.getColor(C2779R.styleable.WheelView_normalTextColor, this.normalColor);
        this.selectedColor = obtainStyledAttributes.getColor(C2779R.styleable.WheelView_selectedTextColor, this.selectedColor);
        this.lineColor = obtainStyledAttributes.getColor(C2779R.styleable.WheelView_lineColor, this.lineColor);
        this.lineHeight = obtainStyledAttributes.getDimension(C2779R.styleable.WheelView_lineHeight, this.lineHeight);
        this.noEmpty = obtainStyledAttributes.getBoolean(C2779R.styleable.WheelView_noEmpty, true);
        this.isEnable = obtainStyledAttributes.getBoolean(C2779R.styleable.WheelView_isEnable, true);
        this.isCyclic = obtainStyledAttributes.getBoolean(C2779R.styleable.WheelView_isCyclic, true);
        this.maskDarkColor = obtainStyledAttributes.getColor(C2779R.styleable.WheelView_maskDarkColor, DEFAULT_MASK_DARK_COLOR);
        this.maskLightColor = obtainStyledAttributes.getColor(C2779R.styleable.WheelView_maskLightColor, DEFAULT_MASK_LIGHT_COLOR);
        obtainStyledAttributes.recycle();
        float f = context.getResources().getDisplayMetrics().density;
        this.density = f;
        this.slowMoveSpeed = (int) (1.0f * f);
        this.clickDistance = (int) (f * 2.0f);
        int i = this.itemNumber;
        this.controlHeight = ((float) i) * this.unitHeight;
        this.toShowItems = new ItemObject[(i + 2)];
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.clickTimeout = ViewConfiguration.getTapTimeout();
        this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.callbackHandler = new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        HandlerThread handlerThread = new HandlerThread("goOnHandlerThread");
        this.moveHandlerThread = handlerThread;
        handlerThread.setPriority(1);
        this.moveHandlerThread.start();
        this.moveHandler = new GoOnHandler(this.moveHandlerThread.getLooper());
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        HandlerThread handlerThread = this.moveHandlerThread;
        if (handlerThread != null && handlerThread.isAlive()) {
            this.moveHandlerThread.quit();
            this.moveHandler = null;
        }
        super.onDetachedFromWindow();
    }

    private void _setIsCyclic(boolean z) {
        if (this.dataList.size() < this.itemNumber + 2) {
            this._isCyclic = false;
        } else {
            this._isCyclic = z;
        }
    }

    private class GoOnHandler extends Handler {
        GoOnHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int access$000 = WheelView.this.goOnDistance;
            if (WheelView.this.moveHandler != null) {
                switch (message.what) {
                    case WheelView.GO_ON_MOVE_REFRESH /*10010*/:
                        WheelView.access$208(WheelView.this);
                        WheelView wheelView = WheelView.this;
                        int unused = wheelView.goOnDistance = (int) (wheelView.goonInterpolator.getInterpolation(((float) WheelView.this.showTime) / 200.0f) * ((float) WheelView.this.goOnLimit));
                        WheelView wheelView2 = WheelView.this;
                        wheelView2.actionThreadMove(wheelView2.goOnMove > 0 ? WheelView.this.goOnDistance - access$000 : (WheelView.this.goOnDistance - access$000) * -1);
                        if (WheelView.this.showTime >= 200 || !WheelView.this.isGoOnMove || (WheelView.this.showTime >= 40 && Math.abs(access$000 - WheelView.this.goOnDistance) < WheelView.this.slowMoveSpeed)) {
                            boolean unused2 = WheelView.this.isGoOnMove = false;
                            WheelView.this.moveHandler.sendEmptyMessage(WheelView.GO_ON_MOVE_END);
                            return;
                        }
                        WheelView.this.moveHandler.sendEmptyMessageDelayed(WheelView.GO_ON_MOVE_REFRESH, 10);
                        return;
                    case WheelView.GO_ON_MOVE_END /*10011*/:
                        WheelView wheelView3 = WheelView.this;
                        wheelView3.slowMove(wheelView3.goOnMove > 0 ? WheelView.this.slowMoveSpeed : WheelView.this.slowMoveSpeed * -1);
                        boolean unused3 = WheelView.this.isScrolling = false;
                        boolean unused4 = WheelView.this.isGoOnMove = false;
                        int unused5 = WheelView.this.goOnDistance = 0;
                        WheelView.this.goOnLimit = 0;
                        return;
                    case WheelView.GO_ON_MOVE_INTERRUPTED /*10012*/:
                        WheelView wheelView4 = WheelView.this;
                        WheelView.access$912(wheelView4, wheelView4.goOnMove > 0 ? WheelView.this.goOnDistance - access$000 : (WheelView.this.goOnDistance - access$000) * -1);
                        int unused6 = WheelView.this.goOnDistance = 0;
                        boolean unused7 = WheelView.this.isScrolling = false;
                        boolean unused8 = WheelView.this.isGoOnMove = false;
                        WheelView.this.findItemsToShow();
                        WheelView.this.postInvalidate();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private synchronized void goonMove(int i, long j) {
        this.showTime = 0;
        int abs = Math.abs(i / 10);
        if (((long) this.goOnMove) * j > 0) {
            this.goOnLimit += abs;
        } else {
            this.goOnLimit = abs;
        }
        this.goOnMove = (int) j;
        this.isGoOnMove = true;
        this.moveHandler.sendEmptyMessage(GO_ON_MOVE_REFRESH);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.isEnable) {
            return true;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            if (this.isScrolling) {
                this.isGoOnMove = false;
                Handler handler = this.moveHandler;
                if (handler != null) {
                    handler.removeMessages(GO_ON_MOVE_REFRESH);
                    this.moveHandler.sendEmptyMessage(GO_ON_MOVE_INTERRUPTED);
                }
            }
            this.isScrolling = true;
            this.downY = (int) motionEvent.getY();
            this.lastY = (int) motionEvent.getY();
            this.downTime = System.currentTimeMillis();
        } else if (action == 1) {
            long currentTimeMillis = System.currentTimeMillis() - this.downTime;
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumFlingVelocity);
            int yVelocity = (int) velocityTracker.getYVelocity();
            if (Math.abs(yVelocity) > this.mMinimumFlingVelocity) {
                goonMove(yVelocity, (long) (y - this.downY));
            } else {
                if (Math.abs(y - this.downY) > this.clickDistance || currentTimeMillis > ((long) this.clickTimeout)) {
                    slowMove(y - this.downY);
                } else {
                    int i = this.downY;
                    float f = this.unitHeight;
                    int i2 = this.itemNumber;
                    if (((float) i) >= (((float) (i2 / 2)) * f) + ((f * 1.0f) / 3.0f) || i <= 0) {
                        float f2 = this.controlHeight;
                        if (((float) i) <= (f2 - (((float) (i2 / 2)) * f)) - ((1.0f * f) / 3.0f) || ((float) i) >= f2) {
                            noEmpty(y - i);
                        } else {
                            actionMove(-((int) (f / 3.0f)));
                            slowMove((-((int) this.unitHeight)) / 3);
                        }
                    } else {
                        actionMove((int) (f / 3.0f));
                        slowMove(((int) this.unitHeight) / 3);
                    }
                }
                this.isScrolling = false;
            }
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        } else if (action == 2) {
            this.isGoOnMove = false;
            this.isScrolling = true;
            actionMove(y - this.lastY);
            this.lastY = y;
        }
        return true;
    }

    private void initData() {
        this.isClearing = true;
        this.itemList.clear();
        for (int i = 0; i < this.dataList.size(); i++) {
            ItemObject itemObject = new ItemObject();
            itemObject.f240id = i;
            itemObject.setItemText(this.dataList.get(i));
            itemObject.f241x = 0;
            itemObject.f242y = (int) (((float) i) * this.unitHeight);
            this.itemList.add(itemObject);
        }
        this.isClearing = false;
        _setIsCyclic(this.isCyclic);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            int size = View.MeasureSpec.getSize(i2);
            float f = (float) size;
            if (f < this.controlHeight && size != 0) {
                this.controlHeight = f;
                this.unitHeight = (float) ((int) (f / ((float) this.itemNumber)));
            }
        } else if (mode == 1073741824) {
            float size2 = (float) View.MeasureSpec.getSize(i2);
            this.controlHeight = size2;
            this.unitHeight = (float) ((int) (size2 / ((float) this.itemNumber)));
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i), (int) this.controlHeight);
        if (((double) Math.abs(this.lastMeasuredHeight - this.controlHeight)) > 0.1d) {
            int selected = getSelected();
            initData();
            if (selected != -1) {
                setDefault(selected);
            } else {
                setDefault(this.defaultIndex);
            }
            this.lastMeasuredHeight = this.controlHeight;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.controlWidth = (float) getWidth();
        drawLine(canvas);
        drawList(canvas);
        drawMask(canvas);
    }

    private void drawLine(Canvas canvas) {
        if (this.linePaint == null) {
            Paint paint = new Paint();
            this.linePaint = paint;
            paint.setColor(this.lineColor);
            this.linePaint.setAntiAlias(true);
            this.linePaint.setStrokeWidth(this.lineHeight);
        }
        float f = this.controlHeight;
        float f2 = this.unitHeight;
        float f3 = this.lineHeight;
        canvas.drawLine(0.0f, ((f / 2.0f) - (f2 / 2.0f)) + f3, this.controlWidth, ((f / 2.0f) - (f2 / 2.0f)) + f3, this.linePaint);
        float f4 = this.controlHeight;
        float f5 = this.unitHeight;
        float f6 = this.lineHeight;
        Canvas canvas2 = canvas;
        canvas2.drawLine(0.0f, ((f4 / 2.0f) + (f5 / 2.0f)) - f6, this.controlWidth, ((f4 / 2.0f) + (f5 / 2.0f)) - f6, this.linePaint);
    }

    private synchronized void drawList(Canvas canvas) {
        if (!this.isClearing) {
            synchronized (this.toShowItems) {
                for (ItemObject itemObject : this.toShowItems) {
                    if (itemObject != null) {
                        itemObject.drawSelf(canvas, getMeasuredWidth());
                    }
                }
            }
        }
    }

    private void drawMask(Canvas canvas) {
        if (this.mastPaint == null) {
            this.mastPaint = new Paint();
            this.linearGradientUp = new LinearGradient(0.0f, 0.0f, 0.0f, this.unitHeight, this.maskDarkColor, this.maskLightColor, Shader.TileMode.CLAMP);
            float f = this.controlHeight;
            this.linearGradientDown = new LinearGradient(0.0f, f - this.unitHeight, 0.0f, f, this.maskLightColor, this.maskDarkColor, Shader.TileMode.CLAMP);
        }
        this.mastPaint.setShader(this.linearGradientUp);
        Canvas canvas2 = canvas;
        canvas2.drawRect(0.0f, 0.0f, this.controlWidth, ((float) (this.itemNumber / 2)) * this.unitHeight, this.mastPaint);
        this.mastPaint.setShader(this.linearGradientDown);
        float f2 = this.controlHeight;
        canvas2.drawRect(0.0f, f2 - (((float) (this.itemNumber / 2)) * this.unitHeight), this.controlWidth, f2, this.mastPaint);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0085, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void noEmpty(int r8) {
        /*
            r7 = this;
            boolean r0 = r7.noEmpty
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            com.eternal.widget.WheelView$ItemObject[] r0 = r7.toShowItems
            monitor-enter(r0)
            r7.findItemsToShow()     // Catch:{ all -> 0x0086 }
            com.eternal.widget.WheelView$ItemObject[] r1 = r7.toShowItems     // Catch:{ all -> 0x0086 }
            int r2 = r1.length     // Catch:{ all -> 0x0086 }
            r3 = 0
            r4 = 0
        L_0x0010:
            if (r4 >= r2) goto L_0x002c
            r5 = r1[r4]     // Catch:{ all -> 0x0086 }
            if (r5 == 0) goto L_0x0029
            boolean r6 = r5.selected()     // Catch:{ all -> 0x0086 }
            if (r6 == 0) goto L_0x0029
            float r8 = r5.moveToSelected()     // Catch:{ all -> 0x0086 }
            int r8 = (int) r8     // Catch:{ all -> 0x0086 }
            r7.onEndSelecting(r5)     // Catch:{ all -> 0x0086 }
            r7.defaultMove(r8)     // Catch:{ all -> 0x0086 }
            monitor-exit(r0)     // Catch:{ all -> 0x0086 }
            return
        L_0x0029:
            int r4 = r4 + 1
            goto L_0x0010
        L_0x002c:
            if (r8 <= 0) goto L_0x0057
        L_0x002e:
            com.eternal.widget.WheelView$ItemObject[] r8 = r7.toShowItems     // Catch:{ all -> 0x0086 }
            int r1 = r8.length     // Catch:{ all -> 0x0086 }
            if (r3 >= r1) goto L_0x0084
            r1 = r8[r3]     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x0054
            r8 = r8[r3]     // Catch:{ all -> 0x0086 }
            boolean r8 = r8.couldSelected()     // Catch:{ all -> 0x0086 }
            if (r8 == 0) goto L_0x0054
            com.eternal.widget.WheelView$ItemObject[] r8 = r7.toShowItems     // Catch:{ all -> 0x0086 }
            r8 = r8[r3]     // Catch:{ all -> 0x0086 }
            float r8 = r8.moveToSelected()     // Catch:{ all -> 0x0086 }
            int r8 = (int) r8     // Catch:{ all -> 0x0086 }
            com.eternal.widget.WheelView$ItemObject[] r1 = r7.toShowItems     // Catch:{ all -> 0x0086 }
            r1 = r1[r3]     // Catch:{ all -> 0x0086 }
            r7.onEndSelecting(r1)     // Catch:{ all -> 0x0086 }
            r7.defaultMove(r8)     // Catch:{ all -> 0x0086 }
            monitor-exit(r0)     // Catch:{ all -> 0x0086 }
            return
        L_0x0054:
            int r3 = r3 + 1
            goto L_0x002e
        L_0x0057:
            com.eternal.widget.WheelView$ItemObject[] r8 = r7.toShowItems     // Catch:{ all -> 0x0086 }
            int r8 = r8.length     // Catch:{ all -> 0x0086 }
            int r8 = r8 + -1
        L_0x005c:
            if (r8 < 0) goto L_0x0084
            com.eternal.widget.WheelView$ItemObject[] r1 = r7.toShowItems     // Catch:{ all -> 0x0086 }
            r2 = r1[r8]     // Catch:{ all -> 0x0086 }
            if (r2 == 0) goto L_0x0081
            r1 = r1[r8]     // Catch:{ all -> 0x0086 }
            boolean r1 = r1.couldSelected()     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x0081
            com.eternal.widget.WheelView$ItemObject[] r1 = r7.toShowItems     // Catch:{ all -> 0x0086 }
            r1 = r1[r8]     // Catch:{ all -> 0x0086 }
            float r1 = r1.moveToSelected()     // Catch:{ all -> 0x0086 }
            int r1 = (int) r1     // Catch:{ all -> 0x0086 }
            com.eternal.widget.WheelView$ItemObject[] r2 = r7.toShowItems     // Catch:{ all -> 0x0086 }
            r8 = r2[r8]     // Catch:{ all -> 0x0086 }
            r7.onEndSelecting(r8)     // Catch:{ all -> 0x0086 }
            r7.defaultMove(r1)     // Catch:{ all -> 0x0086 }
            monitor-exit(r0)     // Catch:{ all -> 0x0086 }
            return
        L_0x0081:
            int r8 = r8 + -1
            goto L_0x005c
        L_0x0084:
            monitor-exit(r0)     // Catch:{ all -> 0x0086 }
            return
        L_0x0086:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0086 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.WheelView.noEmpty(int):void");
    }

    private void onEndSelecting(final ItemObject itemObject) {
        if (this.onSelectListener != null) {
            this.callbackHandler.post(new Runnable() {
                public void run() {
                    WheelView.this.onSelectListener.endSelect(itemObject.f240id, itemObject.getItemText());
                }
            });
        }
    }

    private void actionMove(int i) {
        this.moveDistance -= i;
        findItemsToShow();
        invalidate();
    }

    /* access modifiers changed from: private */
    public void actionThreadMove(int i) {
        this.moveDistance -= i;
        findItemsToShow();
        postInvalidate();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x015a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void findItemsToShow() {
        /*
            r8 = this;
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r0 = r8.itemList
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0009
            return
        L_0x0009:
            boolean r0 = r8._isCyclic
            r1 = 0
            if (r0 == 0) goto L_0x00bb
            float r0 = r8.unitHeight
            int r0 = (int) r0
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r2 = r8.itemList
            int r2 = r2.size()
            int r0 = r0 * r2
            if (r0 == 0) goto L_0x0046
            int r2 = r8.moveDistance
            float r2 = (float) r2
            float r3 = r8.unitHeight
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r4 = r8.itemList
            int r4 = r4.size()
            float r4 = (float) r4
            float r3 = r3 * r4
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x0033
            int r2 = r8.moveDistance
            int r2 = r2 % r0
            r8.moveDistance = r2
            goto L_0x0046
        L_0x0033:
            int r2 = r8.moveDistance
            if (r2 >= 0) goto L_0x0046
            int r2 = r2 % r0
            float r0 = r8.unitHeight
            int r0 = (int) r0
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r3 = r8.itemList
            int r3 = r3.size()
            int r0 = r0 * r3
            int r2 = r2 + r0
            r8.moveDistance = r2
        L_0x0046:
            int r0 = r8.moveDistance
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r2 = r8.itemList
            java.lang.Object r2 = r2.get(r1)
            com.eternal.widget.WheelView$ItemObject r2 = (com.eternal.widget.WheelView.ItemObject) r2
            int r2 = r2.f242y
            int r2 = r2 + r0
            float r0 = (float) r2
            float r2 = r8.unitHeight
            float r2 = r0 / r2
            float r2 = java.lang.Math.abs(r2)
            int r2 = (int) r2
            float r3 = r8.unitHeight
            float r4 = (float) r2
            float r3 = r3 * r4
            float r0 = r0 - r3
            int r0 = (int) r0
            com.eternal.widget.WheelView$ItemObject[] r3 = r8.toShowItems
            monitor-enter(r3)
        L_0x0067:
            com.eternal.widget.WheelView$ItemObject[] r4 = r8.toShowItems     // Catch:{ all -> 0x00b8 }
            int r4 = r4.length     // Catch:{ all -> 0x00b8 }
            if (r1 >= r4) goto L_0x00b5
            int r4 = r2 + r1
            if (r4 >= 0) goto L_0x0078
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r5 = r8.itemList     // Catch:{ all -> 0x00b8 }
            int r5 = r5.size()     // Catch:{ all -> 0x00b8 }
            int r4 = r4 + r5
            goto L_0x0087
        L_0x0078:
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r5 = r8.itemList     // Catch:{ all -> 0x00b8 }
            int r5 = r5.size()     // Catch:{ all -> 0x00b8 }
            if (r4 < r5) goto L_0x0087
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r5 = r8.itemList     // Catch:{ all -> 0x00b8 }
            int r5 = r5.size()     // Catch:{ all -> 0x00b8 }
            int r4 = r4 - r5
        L_0x0087:
            com.eternal.widget.WheelView$ItemObject[] r5 = r8.toShowItems     // Catch:{ all -> 0x00b8 }
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r6 = r8.itemList     // Catch:{ all -> 0x00b8 }
            java.lang.Object r6 = r6.get(r4)     // Catch:{ all -> 0x00b8 }
            com.eternal.widget.WheelView$ItemObject r6 = (com.eternal.widget.WheelView.ItemObject) r6     // Catch:{ all -> 0x00b8 }
            r5[r1] = r6     // Catch:{ all -> 0x00b8 }
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r5 = r8.itemList     // Catch:{ all -> 0x00b8 }
            int r5 = r5.size()     // Catch:{ all -> 0x00b8 }
            if (r5 <= 0) goto L_0x00b2
            com.eternal.widget.WheelView$ItemObject[] r5 = r8.toShowItems     // Catch:{ all -> 0x00b8 }
            r5 = r5[r1]     // Catch:{ all -> 0x00b8 }
            float r6 = r8.unitHeight     // Catch:{ all -> 0x00b8 }
            int r4 = r1 - r4
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r7 = r8.itemList     // Catch:{ all -> 0x00b8 }
            int r7 = r7.size()     // Catch:{ all -> 0x00b8 }
            int r4 = r4 % r7
            float r4 = (float) r4     // Catch:{ all -> 0x00b8 }
            float r6 = r6 * r4
            int r4 = (int) r6     // Catch:{ all -> 0x00b8 }
            int r4 = r4 - r0
            r5.move(r4)     // Catch:{ all -> 0x00b8 }
        L_0x00b2:
            int r1 = r1 + 1
            goto L_0x0067
        L_0x00b5:
            monitor-exit(r3)     // Catch:{ all -> 0x00b8 }
            goto L_0x017a
        L_0x00b8:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00b8 }
            throw r0
        L_0x00bb:
            int r0 = r8.moveDistance
            float r0 = (float) r0
            float r2 = r8.unitHeight
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r3 = r8.itemList
            int r3 = r3.size()
            float r3 = (float) r3
            float r2 = r2 * r3
            int r3 = r8.itemNumber
            int r4 = r3 / 2
            float r4 = (float) r4
            float r5 = r8.unitHeight
            float r4 = r4 * r5
            float r2 = r2 - r4
            float r2 = r2 - r5
            r4 = 10012(0x271c, float:1.403E-41)
            r6 = 10010(0x271a, float:1.4027E-41)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0100
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r0 = r8.itemList
            int r0 = r0.size()
            float r0 = (float) r0
            float r5 = r5 * r0
            int r0 = r8.itemNumber
            int r0 = r0 / 2
            float r0 = (float) r0
            float r2 = r8.unitHeight
            float r0 = r0 * r2
            float r5 = r5 - r0
            float r5 = r5 - r2
            int r0 = (int) r5
            r8.moveDistance = r0
            android.os.Handler r0 = r8.moveHandler
            if (r0 == 0) goto L_0x0122
            r0.removeMessages(r6)
            android.os.Handler r0 = r8.moveHandler
            r0.sendEmptyMessage(r4)
            goto L_0x0122
        L_0x0100:
            int r0 = r8.moveDistance
            float r0 = (float) r0
            int r2 = -r3
            int r2 = r2 / 2
            float r2 = (float) r2
            float r2 = r2 * r5
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0122
            int r0 = -r3
            int r0 = r0 / 2
            float r0 = (float) r0
            float r0 = r0 * r5
            int r0 = (int) r0
            r8.moveDistance = r0
            android.os.Handler r0 = r8.moveHandler
            if (r0 == 0) goto L_0x0122
            r0.removeMessages(r6)
            android.os.Handler r0 = r8.moveHandler
            r0.sendEmptyMessage(r4)
        L_0x0122:
            int r0 = r8.moveDistance
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r2 = r8.itemList
            java.lang.Object r2 = r2.get(r1)
            com.eternal.widget.WheelView$ItemObject r2 = (com.eternal.widget.WheelView.ItemObject) r2
            int r2 = r2.f242y
            int r2 = r2 + r0
            float r0 = (float) r2
            float r2 = r8.unitHeight
            float r3 = r0 / r2
            int r3 = (int) r3
            float r4 = (float) r3
            float r2 = r2 * r4
            float r0 = r0 - r2
            int r0 = (int) r0
            com.eternal.widget.WheelView$ItemObject[] r2 = r8.toShowItems
            monitor-enter(r2)
        L_0x013d:
            com.eternal.widget.WheelView$ItemObject[] r4 = r8.toShowItems     // Catch:{ all -> 0x0193 }
            int r4 = r4.length     // Catch:{ all -> 0x0193 }
            if (r1 >= r4) goto L_0x0179
            int r4 = r3 + r1
            r5 = -1
            if (r4 >= 0) goto L_0x0149
        L_0x0147:
            r4 = -1
            goto L_0x0152
        L_0x0149:
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r6 = r8.itemList     // Catch:{ all -> 0x0193 }
            int r6 = r6.size()     // Catch:{ all -> 0x0193 }
            if (r4 < r6) goto L_0x0152
            goto L_0x0147
        L_0x0152:
            if (r4 != r5) goto L_0x015a
            com.eternal.widget.WheelView$ItemObject[] r4 = r8.toShowItems     // Catch:{ all -> 0x0193 }
            r5 = 0
            r4[r1] = r5     // Catch:{ all -> 0x0193 }
            goto L_0x0176
        L_0x015a:
            com.eternal.widget.WheelView$ItemObject[] r5 = r8.toShowItems     // Catch:{ all -> 0x0193 }
            java.util.ArrayList<com.eternal.widget.WheelView$ItemObject> r6 = r8.itemList     // Catch:{ all -> 0x0193 }
            java.lang.Object r6 = r6.get(r4)     // Catch:{ all -> 0x0193 }
            com.eternal.widget.WheelView$ItemObject r6 = (com.eternal.widget.WheelView.ItemObject) r6     // Catch:{ all -> 0x0193 }
            r5[r1] = r6     // Catch:{ all -> 0x0193 }
            com.eternal.widget.WheelView$ItemObject[] r5 = r8.toShowItems     // Catch:{ all -> 0x0193 }
            r5 = r5[r1]     // Catch:{ all -> 0x0193 }
            float r6 = r8.unitHeight     // Catch:{ all -> 0x0193 }
            int r4 = r1 - r4
            float r4 = (float) r4     // Catch:{ all -> 0x0193 }
            float r6 = r6 * r4
            int r4 = (int) r6     // Catch:{ all -> 0x0193 }
            int r4 = r4 - r0
            r5.move(r4)     // Catch:{ all -> 0x0193 }
        L_0x0176:
            int r1 = r1 + 1
            goto L_0x013d
        L_0x0179:
            monitor-exit(r2)     // Catch:{ all -> 0x0193 }
        L_0x017a:
            com.eternal.widget.WheelView$OnSelectListener r0 = r8.onSelectListener
            if (r0 == 0) goto L_0x0192
            com.eternal.widget.WheelView$ItemObject[] r0 = r8.toShowItems
            int r1 = r8.itemNumber
            int r1 = r1 / 2
            r0 = r0[r1]
            if (r0 == 0) goto L_0x0192
            android.os.Handler r0 = r8.callbackHandler
            com.eternal.widget.WheelView$2 r1 = new com.eternal.widget.WheelView$2
            r1.<init>()
            r0.post(r1)
        L_0x0192:
            return
        L_0x0193:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0193 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.WheelView.findItemsToShow():void");
    }

    /* access modifiers changed from: private */
    public synchronized void slowMove(final int i) {
        Handler handler = this.moveHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                public void run() {
                    int i;
                    int i2;
                    WheelView.this.findItemsToShow();
                    int selected = WheelView.this.getSelected();
                    int i3 = 1;
                    if (selected != -1) {
                        i = (int) ((ItemObject) WheelView.this.itemList.get(selected)).moveToSelected();
                    } else {
                        synchronized (WheelView.this.toShowItems) {
                            i2 = 0;
                            if (i <= 0) {
                                int length = WheelView.this.toShowItems.length - 1;
                                while (true) {
                                    if (length >= 0) {
                                        if (WheelView.this.toShowItems[length] != null && WheelView.this.toShowItems[length].couldSelected()) {
                                            i2 = (int) WheelView.this.toShowItems[length].moveToSelected();
                                            break;
                                        }
                                        length--;
                                    } else {
                                        break;
                                    }
                                }
                            } else {
                                int i4 = 0;
                                while (true) {
                                    if (i4 < WheelView.this.toShowItems.length) {
                                        if (WheelView.this.toShowItems[i4] != null && WheelView.this.toShowItems[i4].couldSelected()) {
                                            i2 = (int) WheelView.this.toShowItems[i4].moveToSelected();
                                            break;
                                        }
                                        i4++;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                        i = i2;
                    }
                    int i5 = i > 0 ? i : i * -1;
                    if (i <= 0) {
                        i3 = -1;
                    }
                    int access$600 = WheelView.this.slowMoveSpeed;
                    while (true) {
                        if (i5 == 0) {
                            break;
                        }
                        i5 -= access$600;
                        if (i5 < 0) {
                            WheelView.access$920(WheelView.this, i5 * i3);
                            WheelView.this.findItemsToShow();
                            WheelView.this.postInvalidate();
                            try {
                                Thread.sleep(10);
                                break;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            WheelView.access$920(WheelView.this, access$600 * i3);
                            WheelView.this.findItemsToShow();
                            WheelView.this.postInvalidate();
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    WheelView.this.noEmpty(i);
                }
            });
        }
    }

    private void defaultMove(int i) {
        this.moveDistance -= i;
        findItemsToShow();
        postInvalidate();
    }

    public void setData(ArrayList<String> arrayList) {
        this.dataList = arrayList;
        initData();
    }

    public void refreshData(ArrayList<String> arrayList) {
        setData(arrayList);
        findItemsToShow();
        invalidate();
    }

    public int getSelected() {
        synchronized (this.toShowItems) {
            ItemObject[] itemObjectArr = this.toShowItems;
            int length = itemObjectArr.length;
            int i = 0;
            while (i < length) {
                ItemObject itemObject = itemObjectArr[i];
                if (itemObject == null || !itemObject.selected()) {
                    i++;
                } else {
                    int i2 = itemObject.f240id;
                    return i2;
                }
            }
            return -1;
        }
    }

    public String getSelectedText() {
        synchronized (this.toShowItems) {
            ItemObject[] itemObjectArr = this.toShowItems;
            int length = itemObjectArr.length;
            int i = 0;
            while (i < length) {
                ItemObject itemObject = itemObjectArr[i];
                if (itemObject == null || !itemObject.selected()) {
                    i++;
                } else {
                    String itemText = itemObject.getItemText();
                    return itemText;
                }
            }
            return "";
        }
    }

    public boolean isScrolling() {
        return this.isScrolling;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public void setDefault(int i) {
        if (i >= 0) {
            Handler handler = this.moveHandler;
            if (handler != null) {
                handler.removeMessages(GO_ON_MOVE_REFRESH);
                this.moveHandler.sendEmptyMessage(GO_ON_MOVE_INTERRUPTED);
            }
            if (!this.itemList.isEmpty() && i <= this.itemList.size() - 1) {
                this.defaultIndex = i;
                this.moveDistance = 0;
                Iterator<ItemObject> it = this.itemList.iterator();
                while (it.hasNext()) {
                    it.next().move = 0;
                }
                findItemsToShow();
                defaultMove((int) this.itemList.get(i).moveToSelected());
            }
        }
    }

    public int getListSize() {
        ArrayList<ItemObject> arrayList = this.itemList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public String getItemText(int i) {
        ArrayList<ItemObject> arrayList = this.itemList;
        if (arrayList == null) {
            return "";
        }
        return arrayList.get(i).getItemText();
    }

    public void setOnSelectListener(OnSelectListener onSelectListener2) {
        this.onSelectListener = onSelectListener2;
    }

    public int getItemNumber() {
        return this.itemNumber;
    }

    public void setItemNumber(int i) {
        this.itemNumber = i;
        this.controlHeight = ((float) i) * this.unitHeight;
        this.toShowItems = new ItemObject[(i + 2)];
        requestLayout();
    }

    public boolean isCyclic() {
        return this.isCyclic;
    }

    public void setCyclic(boolean z) {
        this.isCyclic = z;
        _setIsCyclic(z);
    }

    private class ItemObject {

        /* renamed from: id */
        int f240id;
        private String itemText;
        int move;
        private boolean shouldRefreshTextPaint;
        private TextPaint textPaint;
        private Rect textRect;

        /* renamed from: x */
        int f241x;

        /* renamed from: y */
        int f242y;

        private ItemObject() {
            this.f240id = 0;
            this.itemText = "";
            this.f241x = 0;
            this.f242y = 0;
            this.move = 0;
            this.shouldRefreshTextPaint = true;
        }

        public void drawSelf(Canvas canvas, int i) {
            if (isInView()) {
                if (this.textPaint == null) {
                    TextPaint textPaint2 = new TextPaint();
                    this.textPaint = textPaint2;
                    textPaint2.setAntiAlias(true);
                }
                if (this.textRect == null) {
                    this.textRect = new Rect();
                }
                if (couldSelected()) {
                    this.textPaint.setColor(WheelView.this.selectedColor);
                    float moveToSelected = moveToSelected();
                    if (moveToSelected <= 0.0f) {
                        moveToSelected *= -1.0f;
                    }
                    this.textPaint.setTextSize(WheelView.this.normalFont + ((WheelView.this.selectedFont - WheelView.this.normalFont) * (1.0f - (moveToSelected / WheelView.this.unitHeight))));
                } else {
                    this.textPaint.setColor(WheelView.this.normalColor);
                    this.textPaint.setTextSize(WheelView.this.normalFont);
                }
                if (WheelView.this.unitHeight < Math.max(WheelView.this.selectedFont, WheelView.this.normalFont)) {
                    this.textPaint.setTextSize(WheelView.this.unitHeight - (WheelView.this.lineHeight * 2.0f));
                }
                if (this.shouldRefreshTextPaint) {
                    String str = (String) TextUtils.ellipsize(this.itemText, this.textPaint, (float) i, TextUtils.TruncateAt.END);
                    this.itemText = str;
                    this.textPaint.getTextBounds(str, 0, str.length(), this.textRect);
                    if (WheelView.this.selectedFont == WheelView.this.normalFont) {
                        this.shouldRefreshTextPaint = false;
                    }
                }
                canvas.drawText(this.itemText, (((float) this.f241x) + (WheelView.this.controlWidth / 2.0f)) - (((float) this.textRect.width()) / 2.0f), ((float) (this.f242y + this.move)) + (WheelView.this.unitHeight / 2.0f) + (((float) this.textRect.height()) / 2.0f), this.textPaint);
            }
        }

        public synchronized boolean isInView() {
            if (((float) (this.f242y + this.move)) > WheelView.this.controlHeight || ((float) this.f242y) + ((float) this.move) + WheelView.this.unitHeight < 0.0f) {
                return false;
            }
            return true;
        }

        public synchronized void move(int i) {
            this.move = i;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0044, code lost:
            if (((float) (r4.f242y + r4.move)) >= ((((float) (com.eternal.widget.WheelView.access$1400(r4.this$0) / 2)) * com.eternal.widget.WheelView.access$2000(r4.this$0)) + com.eternal.widget.WheelView.access$2000(r4.this$0))) goto L_0x0046;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean couldSelected() {
            /*
                r4 = this;
                monitor-enter(r4)
                r0 = 1
                int r1 = r4.f242y     // Catch:{ all -> 0x0049 }
                int r2 = r4.move     // Catch:{ all -> 0x0049 }
                int r1 = r1 + r2
                float r1 = (float) r1     // Catch:{ all -> 0x0049 }
                com.eternal.widget.WheelView r2 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0049 }
                int r2 = r2.itemNumber     // Catch:{ all -> 0x0049 }
                int r2 = r2 / 2
                float r2 = (float) r2     // Catch:{ all -> 0x0049 }
                com.eternal.widget.WheelView r3 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0049 }
                float r3 = r3.unitHeight     // Catch:{ all -> 0x0049 }
                float r2 = r2 * r3
                com.eternal.widget.WheelView r3 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0049 }
                float r3 = r3.unitHeight     // Catch:{ all -> 0x0049 }
                float r2 = r2 - r3
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r1 <= 0) goto L_0x0046
                int r1 = r4.f242y     // Catch:{ all -> 0x0049 }
                int r2 = r4.move     // Catch:{ all -> 0x0049 }
                int r1 = r1 + r2
                float r1 = (float) r1     // Catch:{ all -> 0x0049 }
                com.eternal.widget.WheelView r2 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0049 }
                int r2 = r2.itemNumber     // Catch:{ all -> 0x0049 }
                int r2 = r2 / 2
                float r2 = (float) r2     // Catch:{ all -> 0x0049 }
                com.eternal.widget.WheelView r3 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0049 }
                float r3 = r3.unitHeight     // Catch:{ all -> 0x0049 }
                float r2 = r2 * r3
                com.eternal.widget.WheelView r3 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0049 }
                float r3 = r3.unitHeight     // Catch:{ all -> 0x0049 }
                float r2 = r2 + r3
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r1 < 0) goto L_0x0047
            L_0x0046:
                r0 = 0
            L_0x0047:
                monitor-exit(r4)
                return r0
            L_0x0049:
                r0 = move-exception
                monitor-exit(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.WheelView.ItemObject.couldSelected():boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0064, code lost:
            return r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean selected() {
            /*
                r5 = this;
                monitor-enter(r5)
                android.graphics.Rect r0 = r5.textRect     // Catch:{ all -> 0x0065 }
                r1 = 0
                if (r0 != 0) goto L_0x0008
                monitor-exit(r5)
                return r1
            L_0x0008:
                int r0 = r5.f242y     // Catch:{ all -> 0x0065 }
                int r2 = r5.move     // Catch:{ all -> 0x0065 }
                int r0 = r0 + r2
                float r0 = (float) r0     // Catch:{ all -> 0x0065 }
                com.eternal.widget.WheelView r2 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0065 }
                int r2 = r2.itemNumber     // Catch:{ all -> 0x0065 }
                int r2 = r2 / 2
                float r2 = (float) r2     // Catch:{ all -> 0x0065 }
                com.eternal.widget.WheelView r3 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0065 }
                float r3 = r3.unitHeight     // Catch:{ all -> 0x0065 }
                float r2 = r2 * r3
                com.eternal.widget.WheelView r3 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0065 }
                float r3 = r3.unitHeight     // Catch:{ all -> 0x0065 }
                r4 = 1073741824(0x40000000, float:2.0)
                float r3 = r3 / r4
                float r2 = r2 - r3
                android.graphics.Rect r3 = r5.textRect     // Catch:{ all -> 0x0065 }
                int r3 = r3.height()     // Catch:{ all -> 0x0065 }
                float r3 = (float) r3     // Catch:{ all -> 0x0065 }
                float r3 = r3 / r4
                float r2 = r2 + r3
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 < 0) goto L_0x0063
                int r0 = r5.f242y     // Catch:{ all -> 0x0065 }
                int r2 = r5.move     // Catch:{ all -> 0x0065 }
                int r0 = r0 + r2
                float r0 = (float) r0     // Catch:{ all -> 0x0065 }
                com.eternal.widget.WheelView r2 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0065 }
                int r2 = r2.itemNumber     // Catch:{ all -> 0x0065 }
                int r2 = r2 / 2
                float r2 = (float) r2     // Catch:{ all -> 0x0065 }
                com.eternal.widget.WheelView r3 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0065 }
                float r3 = r3.unitHeight     // Catch:{ all -> 0x0065 }
                float r2 = r2 * r3
                com.eternal.widget.WheelView r3 = com.eternal.widget.WheelView.this     // Catch:{ all -> 0x0065 }
                float r3 = r3.unitHeight     // Catch:{ all -> 0x0065 }
                float r3 = r3 / r4
                float r2 = r2 + r3
                android.graphics.Rect r3 = r5.textRect     // Catch:{ all -> 0x0065 }
                int r3 = r3.height()     // Catch:{ all -> 0x0065 }
                float r3 = (float) r3
                float r3 = r3 / r4
                float r2 = r2 - r3
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 > 0) goto L_0x0063
                r1 = 1
            L_0x0063:
                monitor-exit(r5)
                return r1
            L_0x0065:
                r0 = move-exception
                monitor-exit(r5)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.WheelView.ItemObject.selected():boolean");
        }

        public String getItemText() {
            return this.itemText;
        }

        public void setItemText(String str) {
            this.shouldRefreshTextPaint = true;
            this.itemText = str;
        }

        public synchronized float moveToSelected() {
            return ((WheelView.this.controlHeight / 2.0f) - (WheelView.this.unitHeight / 2.0f)) - ((float) (this.f242y + this.move));
        }
    }
}
