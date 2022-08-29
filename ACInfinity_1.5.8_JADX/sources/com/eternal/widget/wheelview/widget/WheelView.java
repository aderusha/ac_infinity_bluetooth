package com.eternal.widget.wheelview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.eternal.widget.wheelview.adapter.ArrayWheelAdapter;
import com.eternal.widget.wheelview.adapter.BaseWheelAdapter;
import com.eternal.widget.wheelview.adapter.SimpleWheelAdapter;
import com.eternal.widget.wheelview.common.WheelConstants;
import com.eternal.widget.wheelview.common.WheelViewException;
import com.eternal.widget.wheelview.graphics.DrawableFactory;
import com.eternal.widget.wheelview.util.WheelUtils;
import java.util.HashMap;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

public class WheelView<T> extends ListView implements IWheelView<T> {
    private boolean mClickable = false;
    private int mCurrentPositon = -1;
    private int mExtraMargin;
    private String mExtraText;
    private boolean mExtraTextBold;
    private int mExtraTextColor;
    private int mExtraTextSize;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 256) {
                if (WheelView.this.mOnWheelItemSelectedListener != null) {
                    WheelView.this.mOnWheelItemSelectedListener.onItemSelected(WheelView.this.getCurrentPosition(), WheelView.this.getSelectionItem(), ((Boolean) message.obj).booleanValue());
                }
                if (WheelView.this.mJoinWheelView == null) {
                    return;
                }
                if (!WheelView.this.mJoinMap.isEmpty()) {
                    WheelView.this.mJoinWheelView.resetDataFromTop((List) WheelView.this.mJoinMap.get(WheelView.this.mList.get(WheelView.this.getCurrentPosition())));
                    return;
                }
                throw new WheelViewException("JoinList is error.");
            } else if (message.what == 257) {
                if (WheelView.this.mOnWheelItemSelectedListener != null) {
                    WheelView.this.mOnWheelItemSelectedListener.onScrolling(WheelView.this.getCurrentPosition(), WheelView.this.getSelectionItem(), ((Boolean) message.obj).booleanValue());
                }
            } else if (message.what == 258 && WheelView.this.mOnWheelItemSelectedListener != null) {
                WheelView.this.mOnWheelItemSelectedListener.onStartScroll(((Boolean) message.obj).booleanValue());
            }
        }
    };
    /* access modifiers changed from: private */
    public int mItemH = 0;
    /* access modifiers changed from: private */
    public HashMap<String, List<T>> mJoinMap;
    /* access modifiers changed from: private */
    public WheelView mJoinWheelView;
    /* access modifiers changed from: private */
    public List<T> mList = null;
    /* access modifiers changed from: private */
    public boolean mLoop = false;
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (WheelView.this.mOnWheelItemClickListener != null) {
                WheelView.this.mOnWheelItemClickListener.onItemClick(WheelView.this.getCurrentPosition(), WheelView.this.getSelectionItem());
            }
        }
    };
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            View childAt;
            if (i == 0 && (childAt = WheelView.this.getChildAt(0)) != null) {
                float y = childAt.getY();
                if (y != 0.0f && WheelView.this.mItemH != 0) {
                    if (Math.abs(y) < ((float) (WheelView.this.mItemH >> 1))) {
                        WheelView.this.smoothScrollBy(WheelView.this.getSmoothDistance(y), 50);
                        return;
                    }
                    WheelView wheelView = WheelView.this;
                    WheelView.this.smoothScrollBy(wheelView.getSmoothDistance(((float) wheelView.mItemH) + y), 50);
                }
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i2 != 0) {
                WheelView.this.refreshCurrentPosition(false, true);
            }
        }
    };
    /* access modifiers changed from: private */
    public OnWheelItemClickListener<T> mOnWheelItemClickListener;
    /* access modifiers changed from: private */
    public OnWheelItemSelectedListener<T> mOnWheelItemSelectedListener;
    private int mSelection = 0;
    private Skin mSkin = Skin.None;
    private WheelViewStyle mStyle;
    private Paint mTextPaint;
    private View.OnTouchListener mTouchListener = WheelView$$ExternalSyntheticLambda0.INSTANCE;
    private BaseWheelAdapter<T> mWheelAdapter;
    /* access modifiers changed from: private */
    public int mWheelSize = 3;

    public interface OnWheelItemClickListener<T> {
        void onItemClick(int i, T t);
    }

    public interface OnWheelItemSelectedListener<T> {
        void onItemSelected(int i, T t, boolean z);

        void onScrolling(int i, T t, boolean z);

        void onStartScroll(boolean z);
    }

    public enum Skin {
        Common,
        Holo,
        None
    }

    public WheelView(Context context) {
        super(context);
        init();
    }

    public WheelView(Context context, WheelViewStyle wheelViewStyle) {
        super(context);
        setStyle(wheelViewStyle);
        init();
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setOnWheelItemSelectedListener(OnWheelItemSelectedListener<T> onWheelItemSelectedListener) {
        this.mOnWheelItemSelectedListener = onWheelItemSelectedListener;
    }

    public void setOnWheelItemClickListener(OnWheelItemClickListener<T> onWheelItemClickListener) {
        this.mOnWheelItemClickListener = onWheelItemClickListener;
    }

    private void init() {
        if (this.mStyle == null) {
            this.mStyle = new WheelViewStyle();
        }
        this.mTextPaint = new Paint(1);
        setTag(WheelConstants.TAG);
        setVerticalScrollBarEnabled(false);
        setScrollingCacheEnabled(false);
        setCacheColorHint(0);
        setFadingEdgeLength(0);
        setOverScrollMode(2);
        setDividerHeight(0);
        setOnItemClickListener(this.mOnItemClickListener);
        setOnScrollListener(this.mOnScrollListener);
        setOnTouchListener(this.mTouchListener);
        if (Build.VERSION.SDK_INT >= 21) {
            setNestedScrollingEnabled(true);
        }
        addOnGlobalLayoutListener();
    }

    private void addOnGlobalLayoutListener() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (WheelView.this.getChildCount() > 0 && WheelView.this.mItemH == 0) {
                    if (Build.VERSION.SDK_INT >= 16) {
                        WheelView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        WheelView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    WheelView wheelView = WheelView.this;
                    int unused = wheelView.mItemH = wheelView.getChildAt(0).getHeight();
                    if (WheelView.this.mItemH != 0) {
                        WheelView.this.getLayoutParams().height = WheelView.this.mItemH * WheelView.this.mWheelSize;
                        WheelView wheelView2 = WheelView.this;
                        wheelView2.refreshVisibleItems(wheelView2.getFirstVisiblePosition(), WheelView.this.getCurrentPosition() + (WheelView.this.mWheelSize / 2), WheelView.this.mWheelSize / 2);
                        WheelView.this.setBackground();
                        return;
                    }
                    throw new WheelViewException("wheel item is error.");
                }
            }
        });
    }

    public WheelViewStyle getStyle() {
        return this.mStyle;
    }

    public void setStyle(WheelViewStyle wheelViewStyle) {
        this.mStyle = wheelViewStyle;
    }

    /* access modifiers changed from: private */
    public void setBackground() {
        Skin skin = this.mSkin;
        int width = getWidth();
        int i = this.mItemH;
        int i2 = this.mWheelSize;
        Drawable createDrawable = DrawableFactory.createDrawable(skin, width, i * i2, this.mStyle, i2, i);
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(createDrawable);
        } else {
            setBackgroundDrawable(createDrawable);
        }
    }

    public Skin getSkin() {
        return this.mSkin;
    }

    public void setSkin(Skin skin) {
        this.mSkin = skin;
    }

    public void setWheelSize(int i) {
        if ((i & 1) != 0) {
            this.mWheelSize = i;
            BaseWheelAdapter<T> baseWheelAdapter = this.mWheelAdapter;
            if (baseWheelAdapter != null) {
                baseWheelAdapter.setWheelSize(i);
                return;
            }
            return;
        }
        throw new WheelViewException("wheel size must be an odd number.");
    }

    public void setLoop(boolean z) {
        if (z != this.mLoop) {
            this.mLoop = z;
            setSelection(0);
            BaseWheelAdapter<T> baseWheelAdapter = this.mWheelAdapter;
            if (baseWheelAdapter != null) {
                baseWheelAdapter.setLoop(z);
            }
        }
    }

    public void setLoopForce(boolean z) {
        if (z != this.mLoop) {
            this.mLoop = z;
            setSelectionForce(0);
            BaseWheelAdapter<T> baseWheelAdapter = this.mWheelAdapter;
            if (baseWheelAdapter != null) {
                baseWheelAdapter.setLoop(z);
            }
        }
    }

    public void setWheelClickable(boolean z) {
        if (z != this.mClickable) {
            this.mClickable = z;
            BaseWheelAdapter<T> baseWheelAdapter = this.mWheelAdapter;
            if (baseWheelAdapter != null) {
                baseWheelAdapter.setClickable(z);
            }
        }
    }

    public void setClickToPosition(boolean z) {
        if (z) {
            this.mWheelAdapter.setOnClickListener(new BaseWheelAdapter.OnClickListener() {
                public void onPositionClick(int i) {
                    int currentPosition = i - WheelView.this.getCurrentPosition();
                    if (WheelView.this.mLoop) {
                        if (currentPosition > WheelView.this.mWheelSize / 2) {
                            currentPosition -= WheelView.this.getWheelCount();
                        } else if (currentPosition < (-WheelView.this.mWheelSize) / 2) {
                            currentPosition += WheelView.this.getWheelCount();
                        }
                    }
                    WheelView wheelView = WheelView.this;
                    wheelView.smoothScrollBy(wheelView.mItemH * currentPosition, 400);
                }
            });
        } else {
            this.mWheelAdapter.setOnClickListener((BaseWheelAdapter.OnClickListener) null);
        }
    }

    public void resetDataFromTop(final List<T> list) {
        if (!WheelUtils.isEmpty(list)) {
            postDelayed(new Runnable() {
                public void run() {
                    WheelView.this.setWheelData(list);
                    if (WheelView.this.getCurrentPosition() >= list.size()) {
                        WheelView.super.setSelection(list.size() - 1);
                    }
                    WheelView.this.refreshCurrentPosition(true, false);
                }
            }, 10);
            return;
        }
        throw new WheelViewException("join map data is error.");
    }

    public void resetData(List<T> list) {
        if (!WheelUtils.isEmpty(list)) {
            setWheelData(list);
            super.setSelection(list.size() - 1);
            refreshCurrentPosition(false, false);
            return;
        }
        throw new WheelViewException("join map data is error.");
    }

    public int getSelection() {
        return this.mSelection;
    }

    public void setSelection(final int i) {
        this.mSelection = i;
        setVisibility(4);
        postDelayed(new Runnable() {
            public void run() {
                WheelView wheelView = WheelView.this;
                WheelView.super.setSelection(wheelView.getRealPosition(i));
                WheelView.this.refreshCurrentPosition(false, false);
                WheelView.this.setVisibility(0);
            }
        }, 500);
    }

    public void setSelectionForce(int i) {
        this.mSelection = i;
        super.setSelection(getRealPosition(i));
        refreshCurrentPosition(false, false);
    }

    public void join(WheelView wheelView) {
        if (wheelView != null) {
            this.mJoinWheelView = wheelView;
            return;
        }
        throw new WheelViewException("wheelview cannot be null.");
    }

    public void joinDatas(HashMap<String, List<T>> hashMap) {
        this.mJoinMap = hashMap;
    }

    /* access modifiers changed from: private */
    public int getRealPosition(int i) {
        if (WheelUtils.isEmpty(this.mList)) {
            return 0;
        }
        return this.mLoop ? (i + ((LockFreeTaskQueueCore.MAX_CAPACITY_MASK / this.mList.size()) * this.mList.size())) - (this.mWheelSize / 2) : i;
    }

    public int getCurrentPosition() {
        return this.mCurrentPositon;
    }

    public T getSelectionItem() {
        int max = Math.max(getCurrentPosition(), 0);
        List<T> list = this.mList;
        if (list == null || list.size() <= max) {
            return null;
        }
        return this.mList.get(max);
    }

    @Deprecated
    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter instanceof BaseWheelAdapter) {
            setWheelAdapter((BaseWheelAdapter) listAdapter);
            return;
        }
        throw new WheelViewException("please invoke setWheelAdapter method.");
    }

    public void setWheelAdapter(BaseWheelAdapter<T> baseWheelAdapter) {
        super.setAdapter(baseWheelAdapter);
        this.mWheelAdapter = baseWheelAdapter;
        baseWheelAdapter.setData(this.mList).setWheelSize(this.mWheelSize).setLoop(this.mLoop).setClickable(this.mClickable);
    }

    public void setWheelData(List<T> list) {
        if (!WheelUtils.isEmpty(list)) {
            this.mList = list;
            BaseWheelAdapter<T> baseWheelAdapter = this.mWheelAdapter;
            if (baseWheelAdapter != null) {
                baseWheelAdapter.setData(list);
                return;
            }
            return;
        }
        throw new WheelViewException("wheel datas are error.");
    }

    public void setExtraText(String str, int i, int i2, int i3) {
        setExtraText(str, i, i2, i3, false);
    }

    public void setExtraText(String str, int i, int i2, int i3, boolean z) {
        this.mExtraText = str;
        this.mExtraTextColor = i;
        this.mExtraTextSize = i2;
        this.mExtraMargin = i3;
        this.mExtraTextBold = z;
    }

    public int getWheelCount() {
        if (!WheelUtils.isEmpty(this.mList)) {
            return this.mList.size();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int getSmoothDistance(float f) {
        if (Math.abs(f) <= 2.0f) {
            return (int) f;
        }
        if (Math.abs(f) < 12.0f) {
            return f > 0.0f ? 2 : -2;
        }
        return (int) (f / 6.0f);
    }

    /* access modifiers changed from: private */
    public void refreshCurrentPosition(boolean z, boolean z2) {
        if (getChildAt(0) != null && this.mItemH != 0) {
            int firstVisiblePosition = getFirstVisiblePosition();
            if (!this.mLoop || firstVisiblePosition != 0) {
                int i = Math.abs(getChildAt(0).getY()) <= ((float) (this.mItemH >> 1)) ? firstVisiblePosition : firstVisiblePosition + 1;
                int i2 = this.mWheelSize;
                refreshVisibleItems(firstVisiblePosition, (i2 / 2) + i, i2 / 2);
                if (this.mLoop) {
                    i = (i + (this.mWheelSize / 2)) % getWheelCount();
                }
                if (i != this.mCurrentPositon || z) {
                    this.mCurrentPositon = i;
                    this.mWheelAdapter.setCurrentPosition(i);
                    Message message = new Message();
                    message.what = WheelConstants.WHEEL_SCROLL_START_WHAT;
                    message.obj = Boolean.valueOf(z2);
                    this.mHandler.sendMessage(message);
                    Message message2 = new Message();
                    message2.what = 257;
                    message2.obj = Boolean.valueOf(z2);
                    this.mHandler.sendMessage(message2);
                    this.mHandler.removeMessages(256);
                    Message message3 = new Message();
                    message3.what = 256;
                    message3.obj = Boolean.valueOf(z2);
                    this.mHandler.sendMessageDelayed(message3, 300);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void refreshVisibleItems(int i, int i2, int i3) {
        for (int i4 = i2 - i3; i4 <= i2 + i3; i4++) {
            View childAt = getChildAt(i4 - i);
            if (childAt != null) {
                BaseWheelAdapter<T> baseWheelAdapter = this.mWheelAdapter;
                if ((baseWheelAdapter instanceof ArrayWheelAdapter) || (baseWheelAdapter instanceof SimpleWheelAdapter)) {
                    refreshTextView(i4, i2, childAt, (TextView) childAt.findViewWithTag(101));
                } else {
                    TextView findTextView = WheelUtils.findTextView(childAt);
                    if (findTextView != null) {
                        refreshTextView(i4, i2, childAt, findTextView);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void refreshTextView(int r12, int r13, android.view.View r14, android.widget.TextView r15) {
        /*
            r11 = this;
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1 = 1098907648(0x41800000, float:16.0)
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3 = -1
            if (r13 != r12) goto L_0x005a
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            int r12 = r12.selectedTextColor
            if (r12 == r3) goto L_0x0015
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            int r2 = r12.selectedTextColor
        L_0x0013:
            r7 = r2
            goto L_0x0022
        L_0x0015:
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            int r12 = r12.textColor
            if (r12 == r3) goto L_0x0020
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            int r2 = r12.textColor
            goto L_0x0013
        L_0x0020:
            r7 = -16777216(0xffffffffff000000, float:-1.7014118E38)
        L_0x0022:
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            int r12 = r12.textSize
            if (r12 == r3) goto L_0x002d
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            int r12 = r12.textSize
            float r1 = (float) r12
        L_0x002d:
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            int r12 = r12.selectedTextSize
            if (r12 == r3) goto L_0x003a
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            int r12 = r12.selectedTextSize
            float r12 = (float) r12
            r8 = r12
            goto L_0x0049
        L_0x003a:
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            float r12 = r12.selectedTextZoom
            int r12 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r12 == 0) goto L_0x0048
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            float r12 = r12.selectedTextZoom
            float r1 = r1 * r12
        L_0x0048:
            r8 = r1
        L_0x0049:
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            boolean r12 = r12.selectedTextBold
            r9 = 1065353216(0x3f800000, float:1.0)
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            android.graphics.Typeface r10 = r12.typeface
            r4 = r11
            r5 = r14
            r6 = r15
            r4.setTextView(r5, r6, r7, r8, r9, r10)
            goto L_0x009f
        L_0x005a:
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r4 = r11.mStyle
            int r4 = r4.textColor
            if (r4 == r3) goto L_0x0066
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r2 = r11.mStyle
            int r2 = r2.textColor
            r7 = r2
            goto L_0x0068
        L_0x0066:
            r7 = -16777216(0xffffffffff000000, float:-1.7014118E38)
        L_0x0068:
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r2 = r11.mStyle
            int r2 = r2.textSize
            if (r2 == r3) goto L_0x0075
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r1 = r11.mStyle
            int r1 = r1.textSize
            float r1 = (float) r1
            r8 = r1
            goto L_0x0077
        L_0x0075:
            r8 = 1098907648(0x41800000, float:16.0)
        L_0x0077:
            int r12 = r12 - r13
            int r12 = java.lang.Math.abs(r12)
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r13 = r11.mStyle
            float r13 = r13.textAlpha
            int r13 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1))
            if (r13 == 0) goto L_0x008a
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r13 = r11.mStyle
            float r13 = r13.textAlpha
            double r0 = (double) r13
            goto L_0x008f
        L_0x008a:
            r0 = 4604480258916220928(0x3fe6666660000000, double:0.699999988079071)
        L_0x008f:
            double r12 = (double) r12
            double r12 = java.lang.Math.pow(r0, r12)
            float r9 = (float) r12
            com.eternal.widget.wheelview.widget.WheelView$WheelViewStyle r12 = r11.mStyle
            android.graphics.Typeface r10 = r12.typeface
            r4 = r11
            r5 = r14
            r6 = r15
            r4.setTextView(r5, r6, r7, r8, r9, r10)
        L_0x009f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.wheelview.widget.WheelView.refreshTextView(int, int, android.view.View, android.widget.TextView):void");
    }

    private void setTextView(View view, TextView textView, int i, float f, float f2, Typeface typeface) {
        textView.setTextColor(i);
        textView.setTextSize(0, f);
        view.setAlpha(f2);
        textView.setTypeface(typeface);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!TextUtils.isEmpty(this.mExtraText)) {
            Rect rect = new Rect(0, this.mItemH * (this.mWheelSize / 2), getWidth(), this.mItemH * ((this.mWheelSize / 2) + 1));
            this.mTextPaint.setTextSize((float) this.mExtraTextSize);
            this.mTextPaint.setColor(this.mExtraTextColor);
            Paint.FontMetricsInt fontMetricsInt = this.mTextPaint.getFontMetricsInt();
            int i = (((rect.bottom + rect.top) - fontMetricsInt.bottom) - fontMetricsInt.top) / 2;
            this.mTextPaint.setTextAlign(Paint.Align.CENTER);
            try {
                this.mTextPaint.setFakeBoldText(this.mExtraTextBold);
            } catch (Exception e) {
                e.printStackTrace();
            }
            canvas.drawText(this.mExtraText, (float) (rect.centerX() + this.mExtraMargin), (float) i, this.mTextPaint);
        }
    }

    public static class WheelViewStyle {
        public int backgroundColor = -1;
        public int holoBorderColor = -1;
        public int holoBorderWidth = -1;
        public boolean selectedTextBold;
        public int selectedTextColor = -1;
        public int selectedTextSize = -1;
        public float selectedTextZoom = -1.0f;
        public float textAlpha = -1.0f;
        public int textColor = -1;
        public int textSize = -1;
        public Typeface typeface;

        public WheelViewStyle() {
        }

        public WheelViewStyle(WheelViewStyle wheelViewStyle) {
            this.backgroundColor = wheelViewStyle.backgroundColor;
            this.holoBorderColor = wheelViewStyle.holoBorderColor;
            this.holoBorderWidth = wheelViewStyle.holoBorderWidth;
            this.textColor = wheelViewStyle.textColor;
            this.selectedTextColor = wheelViewStyle.selectedTextColor;
            this.textSize = wheelViewStyle.textSize;
            this.selectedTextSize = wheelViewStyle.selectedTextSize;
            this.textAlpha = wheelViewStyle.textAlpha;
            this.selectedTextZoom = wheelViewStyle.selectedTextZoom;
            this.selectedTextBold = wheelViewStyle.selectedTextBold;
            this.typeface = wheelViewStyle.typeface;
        }
    }
}
