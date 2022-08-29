package com.eternal.base;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.framework.utils.KLog;

public class SlideRecyclerView extends RecyclerView {
    private static final int INVALID_CHILD_WIDTH = -1;
    private static final int INVALID_POSITION = -1;
    private static final int SNAP_VELOCITY = 600;
    private float mFirstX;
    private float mFirstY;
    private ViewGroup mFlingView;
    private boolean mIsSlide;
    private float mLastX;
    private int mMenuViewWidth;
    private int mPosition;
    private Scroller mScroller;
    private Rect mTouchFrame;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private View switchView;

    public SlideRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlideRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlideRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mScroller = new Scroller(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        obtainVelocity(motionEvent);
        int action = motionEvent.getAction();
        boolean z = false;
        if (action == 0) {
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
            }
            float f = (float) x;
            this.mLastX = f;
            this.mFirstX = f;
            this.mFirstY = (float) y;
            int pointToPosition = pointToPosition(x, y);
            this.mPosition = pointToPosition;
            if (pointToPosition != -1) {
                ViewGroup viewGroup = this.mFlingView;
                ViewGroup viewGroup2 = (ViewGroup) getChildAt(pointToPosition - ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition());
                this.mFlingView = viewGroup2;
                if (viewGroup2 == null) {
                    KLog.m61d();
                }
                if (!(viewGroup == null || this.mFlingView == viewGroup || viewGroup.getScrollX() == 0)) {
                    viewGroup.scrollTo(0, 0);
                }
                if (this.mFlingView.getChildCount() == 2) {
                    this.mMenuViewWidth = this.mFlingView.getChildAt(0).getWidth();
                } else {
                    this.mMenuViewWidth = -1;
                }
                this.switchView = this.mFlingView.findViewWithTag("switch");
            }
        } else if (action == 1) {
            releaseVelocity();
        } else if (action == 2) {
            View view = this.switchView;
            boolean isTouched = view instanceof StatueSwitch ? ((StatueSwitch) view).isTouched() : false;
            this.mVelocityTracker.computeCurrentVelocity(1000);
            float xVelocity = this.mVelocityTracker.getXVelocity();
            boolean z2 = Math.abs(xVelocity) > 600.0f && Math.abs(xVelocity) > Math.abs(this.mVelocityTracker.getYVelocity());
            float f2 = (float) x;
            if (Math.abs(f2 - this.mFirstX) >= ((float) this.mTouchSlop) && Math.abs(f2 - this.mFirstX) > Math.abs(((float) y) - this.mFirstY)) {
                z = true;
            }
            if ((z2 || z) && !isTouched) {
                this.mIsSlide = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsSlide || this.mPosition == -1) {
            closeMenu();
            releaseVelocity();
        } else {
            float x = motionEvent.getX();
            obtainVelocity(motionEvent);
            int action = motionEvent.getAction();
            if (action == 1) {
                if (this.mMenuViewWidth != -1) {
                    int scrollX = this.mFlingView.getScrollX();
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    if (this.mVelocityTracker.getXVelocity() < -600.0f) {
                        Scroller scroller = this.mScroller;
                        int i = this.mMenuViewWidth;
                        scroller.startScroll(scrollX, 0, i - scrollX, 0, Math.abs(i - scrollX));
                    } else if (this.mVelocityTracker.getXVelocity() >= 600.0f) {
                        this.mScroller.startScroll(scrollX, 0, -scrollX, 0, Math.abs(scrollX));
                    } else {
                        int i2 = this.mMenuViewWidth;
                        if (scrollX >= i2 / 2) {
                            this.mScroller.startScroll(scrollX, 0, i2 - scrollX, 0, Math.abs(i2 - scrollX));
                        } else {
                            this.mScroller.startScroll(scrollX, 0, -scrollX, 0, Math.abs(scrollX));
                        }
                    }
                    invalidate();
                }
                this.mMenuViewWidth = -1;
                this.mIsSlide = false;
                this.mPosition = -1;
                releaseVelocity();
            } else if (action == 2 && this.mMenuViewWidth != -1) {
                int i3 = (int) (this.mLastX - x);
                int scrollX2 = this.mFlingView.getScrollX();
                int i4 = scrollX2 + i3;
                int i5 = this.mMenuViewWidth;
                if (i4 > i5) {
                    i3 = i5 - scrollX2;
                } else if (i4 < 0) {
                    i3 = -scrollX2;
                }
                if (i3 != 0) {
                    this.mFlingView.scrollBy(i3, 0);
                }
                this.mLastX = x;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void releaseVelocity() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        View view = this.switchView;
        if (view != null && (view instanceof StatueSwitch)) {
            ((StatueSwitch) view).setTouched(false);
        }
    }

    private void obtainVelocity(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
    }

    public int pointToPosition(int i, int i2) {
        if (getLayoutManager() == null) {
            return -1;
        }
        int findFirstVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        Rect rect = this.mTouchFrame;
        if (rect == null) {
            rect = new Rect();
            this.mTouchFrame = rect;
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() == 0) {
                childAt.getHitRect(rect);
                if (rect.contains(i, i2)) {
                    return findFirstVisibleItemPosition + childCount;
                }
            }
        }
        return -1;
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            this.mFlingView.scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
            invalidate();
        }
    }

    public void closeMenu() {
        ViewGroup viewGroup = this.mFlingView;
        if (viewGroup != null && viewGroup.getScrollX() != 0) {
            this.mFlingView.scrollTo(0, 0);
        }
    }
}
