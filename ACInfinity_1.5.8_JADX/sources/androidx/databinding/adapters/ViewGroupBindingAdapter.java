package androidx.databinding.adapters;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

public class ViewGroupBindingAdapter {

    public interface OnAnimationEnd {
        void onAnimationEnd(Animation animation);
    }

    public interface OnAnimationRepeat {
        void onAnimationRepeat(Animation animation);
    }

    public interface OnAnimationStart {
        void onAnimationStart(Animation animation);
    }

    public interface OnChildViewAdded {
        void onChildViewAdded(View view, View view2);
    }

    public interface OnChildViewRemoved {
        void onChildViewRemoved(View view, View view2);
    }

    public static void setAnimateLayoutChanges(ViewGroup viewGroup, boolean z) {
        if (z) {
            viewGroup.setLayoutTransition(new LayoutTransition());
        } else {
            viewGroup.setLayoutTransition((LayoutTransition) null);
        }
    }

    public static void setListener(ViewGroup viewGroup, final OnChildViewAdded onChildViewAdded, final OnChildViewRemoved onChildViewRemoved) {
        if (onChildViewAdded == null && onChildViewRemoved == null) {
            viewGroup.setOnHierarchyChangeListener((ViewGroup.OnHierarchyChangeListener) null);
        } else {
            viewGroup.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
                public void onChildViewAdded(View view, View view2) {
                    OnChildViewAdded onChildViewAdded = OnChildViewAdded.this;
                    if (onChildViewAdded != null) {
                        onChildViewAdded.onChildViewAdded(view, view2);
                    }
                }

                public void onChildViewRemoved(View view, View view2) {
                    OnChildViewRemoved onChildViewRemoved = onChildViewRemoved;
                    if (onChildViewRemoved != null) {
                        onChildViewRemoved.onChildViewRemoved(view, view2);
                    }
                }
            });
        }
    }

    public static void setListener(ViewGroup viewGroup, final OnAnimationStart onAnimationStart, final OnAnimationEnd onAnimationEnd, final OnAnimationRepeat onAnimationRepeat) {
        if (onAnimationStart == null && onAnimationEnd == null && onAnimationRepeat == null) {
            viewGroup.setLayoutAnimationListener((Animation.AnimationListener) null);
        } else {
            viewGroup.setLayoutAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    OnAnimationStart onAnimationStart = OnAnimationStart.this;
                    if (onAnimationStart != null) {
                        onAnimationStart.onAnimationStart(animation);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    OnAnimationEnd onAnimationEnd = onAnimationEnd;
                    if (onAnimationEnd != null) {
                        onAnimationEnd.onAnimationEnd(animation);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                    OnAnimationRepeat onAnimationRepeat = onAnimationRepeat;
                    if (onAnimationRepeat != null) {
                        onAnimationRepeat.onAnimationRepeat(animation);
                    }
                }
            });
        }
    }
}
