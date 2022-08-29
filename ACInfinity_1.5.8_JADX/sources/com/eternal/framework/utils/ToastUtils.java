package com.eternal.framework.utils;

import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.lang.ref.WeakReference;

public final class ToastUtils {
    private static final int DEFAULT_COLOR = 301989888;
    private static int backgroundColor = DEFAULT_COLOR;
    private static int bgResource = -1;
    private static int gravity = 81;
    private static int messageColor = DEFAULT_COLOR;
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static Toast sToast;
    private static WeakReference<View> sViewWeakReference;
    private static int xOffset;
    private static int yOffset = ((int) (((double) (Utils.getContext().getResources().getDisplayMetrics().density * 64.0f)) + 0.5d));

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void setGravity(int i, int i2, int i3) {
        gravity = i;
        xOffset = i2;
        yOffset = i3;
    }

    public static void setView(int i) {
        sViewWeakReference = new WeakReference<>(((LayoutInflater) Utils.getContext().getSystemService("layout_inflater")).inflate(i, (ViewGroup) null));
    }

    public static void setView(View view) {
        sViewWeakReference = view == null ? null : new WeakReference<>(view);
    }

    public static View getView() {
        View view;
        WeakReference<View> weakReference = sViewWeakReference;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            return view;
        }
        Toast toast = sToast;
        if (toast != null) {
            return toast.getView();
        }
        return null;
    }

    public static void setBackgroundColor(int i) {
        backgroundColor = i;
    }

    public static void setBgResource(int i) {
        bgResource = i;
    }

    public static void setMessageColor(int i) {
        messageColor = i;
    }

    public static void showShortSafe(final CharSequence charSequence) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show(charSequence, 0);
            }
        });
    }

    public static void showShortSafe(final int i) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show(i, 0);
            }
        });
    }

    public static void showShortSafe(final int i, final Object... objArr) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show(i, 0, objArr);
            }
        });
    }

    public static void showShortSafe(final String str, final Object... objArr) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show(str, 0, objArr);
            }
        });
    }

    public static void showLongSafe(final CharSequence charSequence) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show(charSequence, 1);
            }
        });
    }

    public static void showLongSafe(final int i) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show(i, 1);
            }
        });
    }

    public static void showLongSafe(final int i, final Object... objArr) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show(i, 1, objArr);
            }
        });
    }

    public static void showLongSafe(final String str, final Object... objArr) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show(str, 1, objArr);
            }
        });
    }

    public static void showShort(CharSequence charSequence) {
        show(charSequence, 0);
    }

    public static void showShort(int i) {
        show(i, 0);
    }

    public static void showShort(int i, Object... objArr) {
        show(i, 0, objArr);
    }

    public static void showShort(String str, Object... objArr) {
        show(str, 0, objArr);
    }

    public static void showLong(CharSequence charSequence) {
        show(charSequence, 1);
    }

    public static void showLong(int i) {
        show(i, 1);
    }

    public static void showLong(int i, Object... objArr) {
        show(i, 1, objArr);
    }

    public static void showLong(String str, Object... objArr) {
        show(str, 1, objArr);
    }

    public static void showCustomShortSafe() {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show((CharSequence) "", 0);
            }
        });
    }

    public static void showCustomLongSafe() {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.show((CharSequence) "", 1);
            }
        });
    }

    public static void showCustomShort() {
        show((CharSequence) "", 0);
    }

    public static void showCustomLong() {
        show((CharSequence) "", 1);
    }

    /* access modifiers changed from: private */
    public static void show(int i, int i2) {
        show((CharSequence) Utils.getContext().getResources().getText(i).toString(), i2);
    }

    /* access modifiers changed from: private */
    public static void show(int i, int i2, Object... objArr) {
        show((CharSequence) String.format(Utils.getContext().getResources().getString(i), objArr), i2);
    }

    /* access modifiers changed from: private */
    public static void show(String str, int i, Object... objArr) {
        show((CharSequence) String.format(str, objArr), i);
    }

    /* access modifiers changed from: private */
    public static void show(final CharSequence charSequence, final int i) {
        if (Utils.isMainThread()) {
            reShow(charSequence, i);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    ToastUtils.reShow(charSequence, i);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void reShow(CharSequence charSequence, int i) {
        boolean z;
        View view;
        cancel();
        WeakReference<View> weakReference = sViewWeakReference;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            z = false;
        } else {
            Toast toast = new Toast(Utils.getContext());
            sToast = toast;
            toast.setView(view);
            sToast.setDuration(i);
            z = true;
        }
        if (!z) {
            if (messageColor != DEFAULT_COLOR) {
                SpannableString spannableString = new SpannableString(charSequence);
                spannableString.setSpan(new ForegroundColorSpan(messageColor), 0, spannableString.length(), 33);
                sToast = Toast.makeText(Utils.getContext(), spannableString, i);
            } else {
                sToast = Toast.makeText(Utils.getContext(), charSequence, i);
            }
        }
        View view2 = sToast.getView();
        int i2 = bgResource;
        if (i2 != -1) {
            view2.setBackgroundResource(i2);
        } else {
            int i3 = backgroundColor;
            if (i3 != DEFAULT_COLOR) {
                view2.setBackgroundColor(i3);
            }
        }
        sToast.setGravity(gravity, xOffset, yOffset);
        sToast.show();
    }

    public static void cancel() {
        Toast toast = sToast;
        if (toast != null) {
            toast.cancel();
            sToast = null;
        }
    }
}
