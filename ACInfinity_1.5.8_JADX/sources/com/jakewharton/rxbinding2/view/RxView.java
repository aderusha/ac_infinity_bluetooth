package com.jakewharton.rxbinding2.view;

import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Functions;
import com.jakewharton.rxbinding2.internal.Preconditions;
import java.util.concurrent.Callable;
import p014io.reactivex.Observable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Predicate;

public final class RxView {
    public static Observable<Object> attaches(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewAttachesObservable(view, true);
    }

    public static Observable<ViewAttachEvent> attachEvents(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewAttachEventObservable(view);
    }

    public static Observable<Object> detaches(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewAttachesObservable(view, false);
    }

    public static Observable<Object> clicks(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewClickObservable(view);
    }

    public static Observable<DragEvent> drags(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewDragObservable(view, Functions.PREDICATE_ALWAYS_TRUE);
    }

    public static Observable<DragEvent> drags(View view, Predicate<? super DragEvent> predicate) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new ViewDragObservable(view, predicate);
    }

    public static Observable<Object> draws(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewTreeObserverDrawObservable(view);
    }

    public static InitialValueObservable<Boolean> focusChanges(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewFocusChangeObservable(view);
    }

    public static Observable<Object> globalLayouts(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewTreeObserverGlobalLayoutObservable(view);
    }

    public static Observable<MotionEvent> hovers(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewHoverObservable(view, Functions.PREDICATE_ALWAYS_TRUE);
    }

    public static Observable<MotionEvent> hovers(View view, Predicate<? super MotionEvent> predicate) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new ViewHoverObservable(view, predicate);
    }

    public static Observable<Object> layoutChanges(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewLayoutChangeObservable(view);
    }

    public static Observable<ViewLayoutChangeEvent> layoutChangeEvents(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewLayoutChangeEventObservable(view);
    }

    public static Observable<Object> longClicks(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewLongClickObservable(view, Functions.CALLABLE_ALWAYS_TRUE);
    }

    public static Observable<Object> longClicks(View view, Callable<Boolean> callable) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(callable, "handled == null");
        return new ViewLongClickObservable(view, callable);
    }

    public static Observable<Object> preDraws(View view, Callable<Boolean> callable) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(callable, "proceedDrawingPass == null");
        return new ViewTreeObserverPreDrawObservable(view, callable);
    }

    public static Observable<ViewScrollChangeEvent> scrollChangeEvents(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewScrollChangeEventObservable(view);
    }

    public static Observable<Integer> systemUiVisibilityChanges(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewSystemUiVisibilityChangeObservable(view);
    }

    public static Observable<MotionEvent> touches(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewTouchObservable(view, Functions.PREDICATE_ALWAYS_TRUE);
    }

    public static Observable<MotionEvent> touches(View view, Predicate<? super MotionEvent> predicate) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new ViewTouchObservable(view, predicate);
    }

    public static Observable<KeyEvent> keys(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new ViewKeyObservable(view, Functions.PREDICATE_ALWAYS_TRUE);
    }

    public static Observable<KeyEvent> keys(View view, Predicate<? super KeyEvent> predicate) {
        Preconditions.checkNotNull(view, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new ViewKeyObservable(view, predicate);
    }

    @Deprecated
    public static Consumer<? super Boolean> activated(final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                view.setActivated(bool.booleanValue());
            }
        };
    }

    @Deprecated
    public static Consumer<? super Boolean> clickable(final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                view.setClickable(bool.booleanValue());
            }
        };
    }

    @Deprecated
    public static Consumer<? super Boolean> enabled(final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                view.setEnabled(bool.booleanValue());
            }
        };
    }

    @Deprecated
    public static Consumer<? super Boolean> pressed(final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                view.setPressed(bool.booleanValue());
            }
        };
    }

    @Deprecated
    public static Consumer<? super Boolean> selected(final View view) {
        Preconditions.checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                view.setSelected(bool.booleanValue());
            }
        };
    }

    public static Consumer<? super Boolean> visibility(View view) {
        Preconditions.checkNotNull(view, "view == null");
        return visibility(view, 8);
    }

    public static Consumer<? super Boolean> visibility(final View view, final int i) {
        Preconditions.checkNotNull(view, "view == null");
        if (i == 0) {
            throw new IllegalArgumentException("Setting visibility to VISIBLE when false would have no effect.");
        } else if (i == 4 || i == 8) {
            return new Consumer<Boolean>() {
                public void accept(Boolean bool) {
                    view.setVisibility(bool.booleanValue() ? 0 : i);
                }
            };
        } else {
            throw new IllegalArgumentException("Must set visibility to INVISIBLE or GONE when false.");
        }
    }

    private RxView() {
        throw new AssertionError("No instances.");
    }
}
