package com.hjq.xtoast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hjq.xtoast.XToast;
import com.hjq.xtoast.draggable.BaseDraggable;
import com.hjq.xtoast.draggable.MovingDraggable;

public class XToast<X extends XToast> {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private Context mContext;
    private BaseDraggable mDraggable;
    private int mDuration;
    private ToastLifecycle mLifecycle;
    private OnToastListener mListener;
    private View mRootView;
    private boolean mShow;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;

    public XToast(Activity activity) {
        this((Context) activity);
        if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
            addWindowFlags(1024);
        }
        this.mLifecycle = new ToastLifecycle(this, activity);
    }

    public XToast(Application application) {
        this((Context) application);
        if (Build.VERSION.SDK_INT >= 26) {
            setWindowType(2038);
        } else {
            setWindowType(2002);
        }
    }

    private XToast(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mWindowParams = layoutParams;
        layoutParams.height = -2;
        this.mWindowParams.width = -2;
        this.mWindowParams.format = -3;
        this.mWindowParams.windowAnimations = 16973828;
        this.mWindowParams.packageName = context.getPackageName();
        this.mWindowParams.flags = 168;
    }

    public boolean hasWindowFlags(int i) {
        return (i & this.mWindowParams.flags) != 0;
    }

    public X addWindowFlags(int i) {
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        layoutParams.flags = i | layoutParams.flags;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X removeWindowFlags(int i) {
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        layoutParams.flags = (~i) & layoutParams.flags;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setWindowFlags(int i) {
        this.mWindowParams.flags = i;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setWindowType(int i) {
        this.mWindowParams.type = i;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setAnimStyle(int i) {
        this.mWindowParams.windowAnimations = i;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setDraggable() {
        return setDraggable(new MovingDraggable());
    }

    public X setDraggable(BaseDraggable baseDraggable) {
        if (hasWindowFlags(16)) {
            removeWindowFlags(16);
        }
        this.mDraggable = baseDraggable;
        if (isShow()) {
            update();
            this.mDraggable.start(this);
        }
        return this;
    }

    public X setWidth(int i) {
        this.mWindowParams.width = i;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setHeight(int i) {
        this.mWindowParams.height = i;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setDuration(int i) {
        this.mDuration = i;
        if (isShow() && this.mDuration != 0) {
            removeCallbacks();
            postDelayed(new ToastDismissRunnable(this), (long) this.mDuration);
        }
        return this;
    }

    public X setOnToastListener(OnToastListener onToastListener) {
        this.mListener = onToastListener;
        return this;
    }

    public X setGravity(int i) {
        this.mWindowParams.gravity = i;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setOrientation(int i) {
        this.mWindowParams.screenOrientation = i;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setXOffset(int i) {
        this.mWindowParams.x = i;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setYOffset(int i) {
        this.mWindowParams.y = i;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setWindowParams(WindowManager.LayoutParams layoutParams) {
        this.mWindowParams = layoutParams;
        if (isShow()) {
            update();
        }
        return this;
    }

    public X setView(int i) {
        return setView(LayoutInflater.from(this.mContext).inflate(i, new FrameLayout(this.mContext), false));
    }

    public X setView(View view) {
        this.mRootView = view;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && this.mWindowParams.width == -2 && this.mWindowParams.height == -2) {
            setWidth(layoutParams.width);
            setHeight(layoutParams.height);
        }
        if (this.mWindowParams.gravity == 0) {
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                setGravity(((FrameLayout.LayoutParams) layoutParams).gravity);
            } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                setGravity(((LinearLayout.LayoutParams) layoutParams).gravity);
            } else {
                setGravity(17);
            }
        }
        if (isShow()) {
            update();
        }
        return this;
    }

    public X show() {
        if (this.mRootView == null || this.mWindowParams == null) {
            throw new IllegalArgumentException("WindowParams and view cannot be empty");
        }
        if (this.mShow) {
            cancel();
        }
        try {
            this.mWindowManager.addView(this.mRootView, this.mWindowParams);
            this.mShow = true;
            if (this.mDuration != 0) {
                postDelayed(new ToastDismissRunnable(this), (long) this.mDuration);
            }
            BaseDraggable baseDraggable = this.mDraggable;
            if (baseDraggable != null) {
                baseDraggable.start(this);
            }
            ToastLifecycle toastLifecycle = this.mLifecycle;
            if (toastLifecycle != null) {
                toastLifecycle.register();
            }
            OnToastListener onToastListener = this.mListener;
            if (onToastListener != null) {
                onToastListener.onShow(this);
            }
        } catch (WindowManager.BadTokenException | IllegalStateException | NullPointerException e) {
            e.printStackTrace();
        }
        return this;
    }

    public X cancel() {
        if (this.mShow) {
            try {
                ToastLifecycle toastLifecycle = this.mLifecycle;
                if (toastLifecycle != null) {
                    toastLifecycle.unregister();
                }
                this.mWindowManager.removeView(this.mRootView);
                OnToastListener onToastListener = this.mListener;
                if (onToastListener != null) {
                    onToastListener.onDismiss(this);
                }
            } catch (IllegalArgumentException | IllegalStateException | NullPointerException e) {
                e.printStackTrace();
            }
            this.mShow = false;
        }
        return this;
    }

    public void update() {
        this.mWindowManager.updateViewLayout(this.mRootView, this.mWindowParams);
    }

    public void recycle() {
        this.mContext = null;
        this.mWindowManager = null;
        this.mListener = null;
        this.mDraggable = null;
    }

    public boolean isShow() {
        return this.mShow;
    }

    public WindowManager getWindowManager() {
        return this.mWindowManager;
    }

    public WindowManager.LayoutParams getWindowParams() {
        return this.mWindowParams;
    }

    public Context getContext() {
        return this.mContext;
    }

    public View getView() {
        return this.mRootView;
    }

    public <V extends View> V findViewById(int i) {
        View view = this.mRootView;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException("Please setup view");
    }

    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this.mContext, cls));
    }

    public void startActivity(Intent intent) {
        if (!(this.mContext instanceof Activity)) {
            intent.addFlags(268435456);
        }
        this.mContext.startActivity(intent);
    }

    public X setVisibility(int i, int i2) {
        findViewById(i).setVisibility(i2);
        return this;
    }

    public X setText(int i) {
        return setText(16908299, i);
    }

    public X setText(int i, int i2) {
        return setText(i, (CharSequence) this.mContext.getResources().getString(i2));
    }

    public X setText(CharSequence charSequence) {
        return setText(16908299, charSequence);
    }

    public X setText(int i, CharSequence charSequence) {
        ((TextView) findViewById(i)).setText(charSequence);
        return this;
    }

    public X setTextColor(int i, int i2) {
        ((TextView) findViewById(i)).setTextColor(i2);
        return this;
    }

    public X setHint(int i, int i2) {
        return setHint(i, (CharSequence) this.mContext.getResources().getString(i2));
    }

    public X setHint(int i, CharSequence charSequence) {
        ((TextView) findViewById(i)).setHint(charSequence);
        return this;
    }

    public X setHintColor(int i, int i2) {
        ((TextView) findViewById(i)).setHintTextColor(i2);
        return this;
    }

    public X setBackground(int i, int i2) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable = this.mContext.getDrawable(i2);
        } else {
            drawable = this.mContext.getResources().getDrawable(i2);
        }
        return setBackground(i, drawable);
    }

    public X setBackground(int i, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            findViewById(i).setBackground(drawable);
        } else {
            findViewById(i).setBackgroundDrawable(drawable);
        }
        return this;
    }

    public X setImageDrawable(int i, int i2) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable = this.mContext.getDrawable(i2);
        } else {
            drawable = this.mContext.getResources().getDrawable(i2);
        }
        return setImageDrawable(i, drawable);
    }

    public X setImageDrawable(int i, Drawable drawable) {
        ((ImageView) findViewById(i)).setImageDrawable(drawable);
        return this;
    }

    public X setOnClickListener(OnClickListener onClickListener) {
        return setOnClickListener(this.mRootView, onClickListener);
    }

    public X setOnClickListener(int i, OnClickListener onClickListener) {
        return setOnClickListener(findViewById(i), onClickListener);
    }

    private X setOnClickListener(View view, OnClickListener onClickListener) {
        if (hasWindowFlags(16)) {
            removeWindowFlags(16);
        }
        new ViewClickWrapper(this, view, onClickListener);
        return this;
    }

    public X setOnTouchListener(OnTouchListener onTouchListener) {
        return setOnTouchListener(this.mRootView, onTouchListener);
    }

    public X setOnTouchListener(int i, OnTouchListener onTouchListener) {
        return setOnTouchListener(findViewById(i), onTouchListener);
    }

    private X setOnTouchListener(View view, OnTouchListener onTouchListener) {
        if (hasWindowFlags(16)) {
            removeWindowFlags(16);
        }
        new ViewTouchWrapper(this, view, onTouchListener);
        return this;
    }

    public Handler getHandler() {
        return HANDLER;
    }

    public boolean post(Runnable runnable) {
        return postDelayed(runnable, 0);
    }

    public boolean postDelayed(Runnable runnable, long j) {
        if (j < 0) {
            j = 0;
        }
        return postAtTime(runnable, SystemClock.uptimeMillis() + j);
    }

    public boolean postAtTime(Runnable runnable, long j) {
        return HANDLER.postAtTime(runnable, this, j);
    }

    public void removeCallbacks() {
        HANDLER.removeCallbacksAndMessages(this);
    }
}
